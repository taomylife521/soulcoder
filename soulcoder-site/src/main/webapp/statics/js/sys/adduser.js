/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

/**
 * Created by Aministrator on 2018-01-20.
 */

var vm = new Vue({
   el:"#addUserBox",
    data:{
        imgurl:',//头像地址' ,
        realname:'',
        sex:'',
        email:'',
        birthday:'',
        mobile:'',
        deptName:'',
        deptId:''
    },
    created:function(){
        $('#datepicker').datepicker({
            autoclose: true
        });
        this.loadDeptList();//载入部门列表
    },
    mounted:function(){
        this.$nextTick(
            $("#btnUploadImage").fileinput({
                'theme': 'fa',
                'showCaption':true,//是否显示文件标题
                'showPreview': true,//是否显示预览图
                'showRemove':true,//是否显示移除键
                'showUpload':true,//是否显示上传按钮,默认true，当没有指定uploadUrl时，这将默认为非Ajax方案的表单提交按钮，对于ajax上传场景，这将使用uploadUrl作为链接。
                'showCancel':true,//显示上传取消按钮，默认true，这只会在AJAX上传过程中启用和显示。
                'allowedFileExtensions': ['jpg', 'png', 'gif'],//允许上传的文件扩展名的列表
                'elErrorContainer': '#errorBlock',
                'minFileSize':'1',//默认为0.如果设置为null，则跳过验证，不执行最小值检查。
                'maxFileSize':'1024*5',//如果大于此值，则使用msgSizeTooLarge设置引发验证错误。如果设置为0，则表示允许的大小是无限的。默认为0。
                'msgSizeTooSmall':'文件 "{name}" ({size} KB) 必须大于 {minSize} KB.',
                'msgSizeTooLarge':'文件 "{name}" ({size} KB) 超过了最大上传大小： {maxSize} KB. 请重新上传!',//文件尺寸太大的提示消息
                'msgUploadAborted':'文件上传被中止',
                'uploadUrl':'null',//null值是表单提交，不是空值则是ajax提交
                'dropZoneEnabled':'true',//是否启用将文件拖放到文件的拖放区域,这仅适用于基于Ajax的上传。默认为true。
                'dropZoneTitle':'拖放头像图片上传',
                'resizeImage':true//是否可以改变图片大小
            })
        );
    },
    methods:{
            switchChange:function(){
                swal("aa");
                $("#toggle-state-switch").bootstrapSwitch();
            },
        //载入部门列表
        loadDeptList:function(){
            $.ajax({
                type:"POST",
                url:"/sys/dept/list",
                dataType:"json",
                success:function (result) {
                    if(result.status == 0){
                       swal("获取部门失败:"+result);
                        return;
                    }
                    ztree= $.fn.zTree.init($("#deptTree"), deptTreeSettings, result.data.deptlist);
                    var node = ztree.getNodeByParam("id", parent.vm.user.deptid);//根据父框架vm的user对象去查值
                    if(node != null){//如果节点不为空则选中
                        ztree.selectNode(node);
                    }
                }
            });
        },
        //确认该部门
        confirmDept:function(){
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            //选择上级部门
            vm.deptId = node[0].id;
            vm.deptName = node[0].name;
            $('#myModal').modal('hide')
        },
        //添加用户
        btnAddUser:function(){
            swal('add user');
        }
    }
});
