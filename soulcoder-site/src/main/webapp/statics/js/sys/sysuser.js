/*
 * Copyright (c) 2018.所有代码版权归编码者所有!
 */

/**
 * Created by Aministrator on 2018-01-11.
 */
var tbl;//table对象
var aoData;//提交服务端数据
/*ztree的设置*/
var ztree;

// $(document).ready(function(){
//    initTable();
// });





var vm = new Vue({
    el:"#userbox",
    data:{
        deptList:{},//部门列表
        sCondition:{//查询条件
            createTime:'1900-01-01/2099-12-01',//创建时间
            userMobile:'',//用户手机
            userEmail:'',//用户邮箱
            realName:'',//姓名
            deptId:'',//部门id
            deptName:'',//部门名称
            orderBy:'u.id'//orderby
        }

    },
    mounted: function () {//页面元素加载完毕
        this.$nextTick(this.initTable());//dom加载完毕去初始化dataTable
        //鼠标移动上去变色
        this.$nextTick(
            //table鼠标移动上去变色
            $('#tbUserList tbody').on( 'mouseenter', 'tr', function () {
            $(this).addClass('heightlight').siblings().removeClass('heightlight');
            } ).on('mouseleave','tr',function(){
                $(this).removeClass('heightlight').siblings().removeClass('heightlight');
            })
        );

    },
    created:function(){//vm实例加载完毕
        this.loadDeptList();//载入部门列表

    },
    updated: function(){

    },

    methods:{
        //新增用户
        addNewUser:function () {
            swal("add");
        },
        //载入用户列表
        loadUserList:function(){
            tbl.ajax.reload();
        },

        //载入部门列表
        loadDeptList:function(){
            console.log(parent);
            $.ajax({
                type:"POST",
                url:"/sys/dept/list",
                dataType:"json",
                success:function (result) {
                    if(result.status == 0){
                        console.log("获取部门失败:"+result);
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
            vm.sCondition.deptId = node[0].id;
            vm.sCondition.deptName = node[0].name;
            $('#myModal').modal('hide')
        },

        //初始化dataTable
        initTable:function(){
            var vmData=this.$data;
            var userCreateTimeMin=vmData.sCondition.createTime.split('/')[0];
            var userCreateTimeMax=vmData.sCondition.createTime.split('/')[1];
            tbl= $('#tbUserList').DataTable({
                    "paging": true,
                    "lengthChange": true,
                    "lengthMenu": [10, 25, 50],
                    "searching": false,//是否允许Datatables开启本地搜索
                    "ordering": false,//是否允许Datatables开启排序
                    "info": true,//控制是否显示表格左下角的信息
                    "autoWidth": true,//自动宽度
                    "language": oLanguageData,//语言文件
                    "processing": true,//有ajax，此项必有
                    "serverSide": true,//有serverSide，ajax项必有
                    "ajax":{//ajax所需参数
                        url: "list",
                        contentType: "application/json",//
                        type:"POST",
                        data: function (req) {//请求给服务端的数据
                           console.log(req);
                            req.usercreatetimemin = userCreateTimeMin;
                            req.usercreatetimemax = userCreateTimeMax;
                            req.mobile = vmData.sCondition.userMobile;
                            req.email = vmData.sCondition.userEmail;
                            req.realname = vmData.sCondition.realName;
                            req.deptid = vmData.sCondition.deptId;
                            req.orderby = vmData.sCondition.orderBy;
                            return JSON.stringify(req);
                        },
                       dataFilter: function (result) {//服务端返回的数据，进行过滤加工给到datatables
                             var json = jQuery.parseJSON(result);
                             var tableJson={};//构造客户端tablejson对象
                             if(json.status==0)
                             {
                                 tableJson.error=json.errormsg;
                                 return JSON.stringify(tableJson);
                             }
                           tableJson.recordsTotal = json.data.recordsTotal;
                           tableJson.recordsFiltered = json.data.recordsFiltered;
                           tableJson.data = json.data.data.userList;
                            return JSON.stringify(tableJson); // return JSON string
                        }
                },
                "columns": [
                    { "data": "id" },
                    { "data": "username" },
                    { "data": "realname" },
                    { "data": "deptName" },
                    { "data": "mobile" },
                    { "data": "createtime" },
                    { "data": "userstatus" }
                ]
            });
        }



    }
});








