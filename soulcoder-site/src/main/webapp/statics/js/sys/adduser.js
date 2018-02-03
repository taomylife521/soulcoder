/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */
Vue.use(VeeValidate); // good to go.
/**
 * Created by Aministrator on 2018-01-20.
 */
/*ztree的设置*/
//var ztree;
var vm = new Vue({
   el:"#addUserBox",
    data:{
        imgurl:',//头像地址' ,
        realName:'',
        weixin:'',
        qq:'',
        sex:'',
        email:'',
        birthday:'',
        mobile:'',
        deptName:'',
        deptId:'',
        roleId:'',
        roleName:'',
        password:'',
        roleTreeSettings : {
            view: {
                selectedMulti: false,
                view: {
                    fontCss: {}
                }
            },
            check: {
                enable: false
            },

            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentid",//父id对应的键
                    rootPId: 0
                }
            },
            edit: {
                enable: false
            },
            callback: {
                onClick: null
            }
        },
        ztrees:[],//多个ztree映射关系表
    },
    created:function(){
        $('#datepicker').datepicker({
            autoclose: true
        });
        this.loadRoleTreeList(false);
        this.$validator.localize('zh_CN');
    },
    mounted:function(){
        this.$nextTick(

            $("#btnUploadImage").fileinput({
                theme: 'fa',
                showCaption:true,//是否显示文件标题
                showPreview: true,//是否显示预览图
                showRemove:true,//是否显示移除键
                showUpload:true,//是否显示上传按钮,默认true，当没有指定uploadUrl时，这将默认为非Ajax方案的表单提交按钮，对于ajax上传场景，这将使用uploadUrl作为链接。
                showCancel:true,//显示上传取消按钮，默认true，这只会在AJAX上传过程中启用和显示。
                allowedFileExtensions: ['jpg', 'png', 'gif'],//允许上传的文件扩展名的列表
                elErrorContainer: '#errorBlock',
                minFileSize:'1',//默认为0.如果设置为null，则跳过验证，不执行最小值检查。
                maxFileSize:'1024*5',//如果大于此值，则使用msgSizeTooLarge设置引发验证错误。如果设置为0，则表示允许的大小是无限的。默认为0。
                msgSizeTooSmall:'文件 "{name}" ({size} KB) 必须大于 {minSize} KB.',
                msgSizeTooLarge:'文件 "{name}" ({size} KB) 超过了最大上传大小： {maxSize} KB. 请重新上传!',//文件尺寸太大的提示消息
                msgUploadAborted:'文件上传被中止',
                uploadUrl:'/sys/upload',//null值是表单提交，不是空值则是ajax提交
                uploadAsync: true, //默认异步上传
                dropZoneEnabled:'true',//是否启用将文件拖放到文件的拖放区域,这仅适用于基于Ajax的上传。默认为true。
                dropZoneTitle:'拖放头像图片上传',
                 browseClass:"btn btn-info", //按钮样式
                resizeImage:false//是否可以改变图片大小
            }).on("fileuploaded", function (event, data, previewId, index){//文件上传完触发
                    console.log(event);
                    console.log(data);
                    console.log(previewId);
                    console.log(index);
            }).on("filepredelete", function(jqXHR) {//文件删除要触发的
                var abort = true;
                if (confirm("Are you sure you want to delete this image?")) {
                    abort = false;
                }
                return abort; // you can also send any data/object that you can receive on `filecustomerror` event
            })
        );
    },
    methods:{


        //载入角色树
        loadRoleTreeList: function (isOnlyInitRoleTree) {
            //  var data="rolename="+vm.sRoleName+"&deptid="+vm.sRoleDeptId;
            $.ajax({
                type: "POST",
                url: "/sys/role/list",
                dataType: "json",
                // data:data,
                success: function (result) {
                    if(!vm.ajaxCallInterceptor(result)){
                        return false;
                    }

                    vm.roleTreeSettings.callback.onClick=vm.clickRoleTree;
                    vm.roleTreeSettings.view.fontCss=vm.getFontCss;
                    vm.initTree("roleDeptTree",result.data.rolelist,parent.vm.user.deptid,vm.roleTreeSettings,true);
                    if(!isOnlyInitRoleTree) {
                        for (var key = 0; key < result.data.rolelist.length; key++) {//去除所有的角色只留部门
                            if (result.data.rolelist[key].role == true) {
                                result.data.rolelist.splice(key, 1);
                                key -= 1;
                            }
                        }
                        vm.initTree("deptTree", result.data.rolelist, parent.vm.user.deptid, deptTreeSettings,true);
                    }




                }
            });
        },

        //初始化部门树
        initTree:function(treeElementId,treeData,selectedDeptId,settings,isCacheZTreeInstance){
            var ztree = $.fn.zTree.init($("#"+treeElementId), settings, treeData);
            var nodes= ztree.getNodes();
            for(var i=0;i<nodes.length;i++){
                nodes[i].highlight=false;
                var isleaf = nodes[i].isleaf==0 || nodes[i].isleaf=="false"?false:true
                if(nodes[i].isopen && !isleaf){
                    ztree.expandNode(nodes[i], true, true, true);
                }else{
                    ztree.expandNode(nodes[i], false, false, false);
                }
                // nodes[i].open=nodes[i].isopen?true:false;
            }
            var node = ztree.getNodeByParam("id", selectedDeptId);//根据参数查询节点
            if (node != null) {//如果节点不为空则选中
                ztree.selectNode(node);
                node.highlight=true;
                ztree.updateNode(node);
            }
            if(isCacheZTreeInstance) {
                vm.ztrees.push({id: treeElementId, instance: ztree});//缓存树的映射关系
            }
        },

        //获取树对象实例
        getZTreeInstance:function(treeElement){
            var ztree=null;
            $.each(vm.ztrees,function(i){
                if(vm.ztrees[i].id == treeElement){
                    ztree= vm.ztrees[i].instance;
                }
            });
            return ztree;
        },

        //选择部门
        confirmDept:function(){
            var ztree= vm.getZTreeInstance("deptTree");
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            if(node!=null) {
                //选择上级部门
                vm.deptId = node[0].id;
                vm.deptName = node[0].name;
                $('#myModal').modal('hide')
            }else{
                swal("未选择任何部门");
            }
        },

        //选择部门角色
        confirmDeptRole:function(){
            var ztree= vm.getZTreeInstance("roleDeptTree");
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            if(node==null || !node[0].role) {
                swal("未选择任何角色");
                return;
            }


            vm.roleId = node[0].id;
            vm.roleName = node[0].name;
            $('#roleModal').modal('hide')
        },

        //添加用户
        btnAddUser:function(){
            this.$validator.validateAll().then(function(result){ //提交之前验证所有的错误
                if (!result) {
                    return false; //验证不通过
                }
                // 验证通过
                // 验证通过
                var data={
                    user: {
                        realName: vm.realName,
                        sex: vm.sex,
                        userStatus: vm.userStatus,
                        email: vm.email,
                        birthday: vm.birthday,
                        mobile:vm.mobile,
                        deptId:vm.deptId,
                        roleId:vm.roleId,
                        imageUrl:vm.imgurl,
                        qq:vm.qq,
                        weixin:vm.weixin,
                        password:vm.password
                    }
                };
                $.ajax({
                    type: "POST",
                    url: "/sys/role/save",
                    dataType: "json",
                    data:JSON.stringify(data),
                    contentType:'application/json;charset=UTF-8',
                    success: function (result) {
                        if(!vm.ajaxCallInterceptor(result)){
                            vm.selectedTreeNode("roleDeptTree","parentid",vm.roleDeptId);//角色信息展示中的部门树默认选中
                            vm.selectedTreeNode("roleDeptTree","name",vm.roleName);
                            return false;
                        }
                        // if (parseInt(result.status) == 0) {
                        //     swal(result.errormsg);
                        //     vm.selectedTreeNode("roleDeptTree","parentid",vm.roleDeptId);//角色信息展示中的部门树默认选中
                        //     vm.selectedTreeNode("roleDeptTree","name",vm.roleName);
                        //     return;
                        // }


                        //vm.loadRoleTreeList(true);
                        //vm.selectedTreeNode("roleDeptTree","parentid",vm.roleDeptId);//角色信息展示中的部门树默认选中
                        swal("添加成功");
                        window.location.reload();
                    }
                });

            });
        }
    }
});
