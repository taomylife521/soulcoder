/**
 * Created by Aministrator on 2018-01-24.
 */
Vue.use(VeeValidate); // good to go.
var vm = new Vue({
    el:"#rolebox",
    data:{
        deptRoleList:{},//部门角色列表
        roleName:'',//角色名称
        roleDeptId:'',//角色部门id
        roleDeptName:'',//角色部门id
        roleDescription:'',//角色描述
        roleId:'',
        orderNum:'',
        sRoleName:'',//要查询的角色名称
        // sRoleDeptId:'',//要查询的角色部门id
        // sRoleDeptName:'',//要查询的角色部门名称
        ztrees:[],//多个ztree映射关系表
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
        // nodeList : [],
        hiddenNodes:[],//用于存储被隐藏的结点
        roleNameList:[]


    },
    mounted: function () {//页面元素加载完毕

    },
    created:function(){//vm实例加载完毕
        this.loadRoleTreeList(false);//里面有部门对应的各个角色

        // this.validator = new Validator({
        //     roleName: 'required',
        //     roleDeptName: 'required'
        // });
        // this.$set(this, 'errors', this.validator.errors);
        this.$validator.localize('zh_CN');
        //this.loadDeptList();

    },
    updated: function(){

    },

    methods: {




        substringMatcher:function(strs){
            return function findMatches(q, cb) {
                var matches, substringRegex;

                // an array that will be populated with substring matches
                matches = [];

                // regex used to determine if a string contains the substring `q`
                substrRegex = new RegExp(q, 'i');

                // iterate through the pool of strings and for any string that
                // contains the substring `q`, add it to the `matches` array
                $.each(strs, function(i, str) {
                    if (substrRegex.test(str)) {
                        matches.push(str);
                    }
                });
                cb(matches);
            };
        },
        //修改角色信息
        modifyRoleInfo:function(){
            this.$validator.validateAll().then(function(result){ //提交之前验证所有的错误
                if (!result) {
                    return; //验证不通过
                }
                // 验证通过
                var data="roleid="+vm.roleId+"&rolename="+vm.roleName+"&roledescription="+vm.roleDescription+"&roledeptid="+vm.roleDeptId+"&ordernum="+vm.orderNum;
                $.ajax({
                    type: "POST",
                    url: "/sys/role/modify",
                    dataType: "json",
                     data:data,
                    success: function (result) {
                        if (parseInt(result.status) == 0) {
                            console.log("修改失败:" + result.errormsg);
                            return;
                        }
                        swal("修改成功");
                        window.location.reload();
                    }
                });

            });
        },
        //添加角色信息
        addRoleInfo: function () {
            this.$validator.validateAll().then(function(result){ //提交之前验证所有的错误
                if (!result) {
                    return false; //验证不通过
                }
                // 验证通过
                // 验证通过
                var data={
                            rolename:vm.roleName,
                            roledescription:vm.roleDescription,
                            roledeptid:vm.roleDeptId,
                            ordernum:vm.orderNum,
                            roledeptname:vm.roleDeptName
                        };
                //var data="roledeptname="+vm.roleDeptName+"&rolename="+vm.roleName+"&roledescription="+vm.roleDescription+"&roledeptid="+vm.roleDeptId+"&ordernum="+vm.orderNum;
                $.ajax({
                    type: "POST",
                    url: "/sys/role/add",
                    dataType: "json",
                    data:JSON.stringify(data),
                    contentType:'application/json;charset=UTF-8',
                    success: function (result) {
                        if (parseInt(result.status) == 0) {
                            swal(result.errormsg);
                            vm.selectedTreeNode("roleDeptTree","parentid",vm.roleDeptId);//角色信息展示中的部门树默认选中
                            vm.selectedTreeNode("roleDeptTree","name",vm.roleName);
                            return;
                        }
                        //vm.loadRoleTreeList(true);
                        //vm.selectedTreeNode("roleDeptTree","parentid",vm.roleDeptId);//角色信息展示中的部门树默认选中
                        swal("添加成功");
                        window.location.reload();
                    }
                });

            });
        },
        getFontCss: function (treeId, treeNode) {
          return treeNode.highlight ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        },

        //单击角色树
        clickRoleTree:function(event, treeId, treeNode, clickFlag){
           // swal(JSON.stringify(treeNode));
            if(treeNode.role){
                vm.roleId=treeNode.id.split('_')[1];
                vm.roleName=treeNode.name;
                vm.roleDeptId=treeNode.parentid;
                vm.roleDeptName=treeNode.parentname;
                vm.roleDescription = treeNode.description;
                vm.orderNum=treeNode.ordernum;
                vm.selectedTreeNode("deptTree","id",vm.roleDeptId);//角色信息展示中的部门树默认选中
            }

        },

        //过滤ztree显示数据
        filterByRoleName:function(){
            vm.filter($("#scrollable-dropdown-menu"));
        },
        filter: function (obj){
            var zTreeObj= vm.getZTreeInstance("roleDeptTree");
            if(vm.hiddenNodes.length>0) {
                zTreeObj.showNodes(vm.hiddenNodes);  //显示上次搜索后背隐藏的结点
            }
            //查找不符合条件的叶子节点
            function filterFunc(node){
                var _keywords=$(obj).val();
                if(_keywords.length<=0){//如果为空，则直接返回不进行寻找
                    node.highlight =false;//恢复原色
                    zTreeObj.updateNode(node);//更新节点
                    return false;
                }
                if(node.isParent)//是父节点
                {
                    if(node.name.indexOf(_keywords)>-1){//并且索引到值
                        zTreeObj.selectNode(node);
                        node.highlight=true;//高亮
                    }else {
                        node.highlight = false;
                    }
                    zTreeObj.updateNode(node);
                    return false;
                }
                if(node.name.indexOf(_keywords)>-1)//不是父节点,直接匹配,找到匹配的内容项
                {

                    node.highlight=true;//高亮
                    zTreeObj.selectNode(node);
                    zTreeObj.updateNode(node);//更新节点
                    return false;
                }
                else {
                    node.highlight =false;//恢复原色
                    zTreeObj.updateNode(node);//更新节点
                    return true;
                }
            };
            vm.hiddenNodes=zTreeObj.getNodesByFilter(filterFunc);//获取不符合条件的叶子结点
            //隐藏不符合条件的叶子结点
            zTreeObj.hideNodes(vm.hiddenNodes);
            var nodes = zTreeObj.getSelectedNodes();//把所有选择的节点展开
                for(var i=0;i<nodes.length;i++) {
                    zTreeObj.expandNode(nodes[i], true, true, true);
                }

        },
        //载入角色树
        loadRoleTreeList: function (isOnlyInitRoleTree) {
          //  var data="rolename="+vm.sRoleName+"&deptid="+vm.sRoleDeptId;
            $.ajax({
                type: "POST",
                url: "/sys/role/list",
                dataType: "json",
               // data:data,
                success: function (result) {
                    if (result.status == 0) {
                        console.log("获取部门失败:" + result);
                        return;
                    }
                   // vm.roleTreeList=result.data.rolelist;
                    vm.roleTreeSettings.callback.onClick=vm.clickRoleTree;
                    vm.roleTreeSettings.view.fontCss=vm.getFontCss;
                    vm.initDeptTree("roleDeptTree",result.data.rolelist,parent.vm.user.deptid,vm.roleTreeSettings);
                    if(!isOnlyInitRoleTree) {
                        for (var key = 0; key < result.data.rolelist.length; key++) {//去除所有的角色只留部门
                            if (result.data.rolelist[key].role == true) {
                                vm.roleNameList.push(result.data.rolelist[key].name);
                                result.data.rolelist.splice(key, 1);
                                key -= 1;
                            }
                        }
                        var $obj = $("#scrollable-dropdown-menu");
                        $obj.typeahead({
                            hint: true,
                            highlight: true,
                            minLength: 1
                        }, {limit: 3, source: vm.substringMatcher(vm.roleNameList)});//自动提示
                        // bind other two tree
                        vm.initDeptTree("deptTreeCopy", result.data.rolelist, vm.roleDeptId, deptTreeSettings);
                        vm.initDeptTree("deptTree", result.data.rolelist, vm.sRoleDeptId, deptTreeSettings);
                    }




                }
            });
        },
        //初始化部门树
        initDeptTree:function(treeElementId,deptList,selectedDeptId,settings){
            var ztree = $.fn.zTree.init($("#"+treeElementId), settings, deptList);
           var nodes= ztree.getNodes();
           for(var i=0;i<nodes.length;i++){
               nodes[i].highlight=false;
               nodes[i].open=nodes[i].isopen?true:false;
           }
            var node = ztree.getNodeByParam("id", selectedDeptId);//根据参数查询节点
            if (node != null) {//如果节点不为空则选中
                ztree.selectNode(node);
                node.highlight=true;
                ztree.updateNode(node);
            }

            vm.ztrees.push({id:treeElementId,instance:ztree});//缓存树的映射关系
        },
        //选中树中的某个节点id
        selectedTreeNode:function(treeElementId,selectedProp,selectedPropValue){
                var ztree = vm.getZTreeInstance(treeElementId);
                var node = ztree.getNodeByParam(selectedProp, selectedPropValue);//根据父框架vm的user对象去查值
                if (node != null) {//如果节点不为空则选中
                    ztree.selectNode(node);
                    ztree.expandNode(node, true, true, true);
                }

        },

        //搜索条件上面的部门树
        confirmDeptCopy:function(){
          // var ztree= vm.ztrees[$("#deptTreeCopy")]
            var ztree= vm.getZTreeInstance("deptTreeCopy");
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            //选择上级部门
            vm.sRoleDeptId = node[0].id;
            vm.sRoleDeptName = node[0].name;
            $('#myModalCopy').modal('hide');
        },

        //确认该部门
        confirmDept: function () {
         //   var ztree= vm.ztrees[$("#deptTree")]
            var ztree= vm.getZTreeInstance("deptTree");
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            //选择上级部门
            vm.roleDeptId = node[0].id;
            vm.roleDeptName = node[0].name;
            $('#myModal').modal('hide')
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
        }



    }


});


