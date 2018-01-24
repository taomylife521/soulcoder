/**
 * Created by Aministrator on 2018-01-24.
 */

var vm = new Vue({
    el:"#rolebox",
    data:{
        deptRoleList:{},//部门角色列表
        roleName:'',//角色名称
        roleDeptId:'',//角色部门id
        roleDeptName:'',//角色部门id
        roleDescription:'',//角色描述
        roleId:'',
        sRoleName:'',//要查询的角色名称
        sRoleDeptId:'',//要查询的角色部门id
        sRoleDeptName:'',//要查询的角色部门名称
        ztrees:{}//多个ztree映射关系表

    },
    mounted: function () {//页面元素加载完毕


    },
    created:function(){//vm实例加载完毕
        this.loadDeptList();

    },
    updated: function(){

    },

    methods: {
        //新增用户
        addNewUser: function () {
            swal("add");
        },
        //载入角色树
        loadRoleTreeList: function () {
            $.ajax({
                type: "POST",
                url: "/sys/role/list",
                dataType: "json",
                success: function (result) {
                    if (result.status == 0) {
                        console.log("获取部门失败:" + result);
                        return;
                    }
                    $("ul[name=deptTree]").each(function(){
                       vm.initDeptTree($(this),result.data.deptlist,parent.vm.user.deptid);
                    });
                   // var ztree = $.fn.zTree.init($("ul[name=deptTree]"), deptTreeSettings, result.data.deptlist);
                   //  var node = ztree.getNodeByParam("id", parent.vm.user.deptid);//根据父框架vm的user对象去查值
                   //  if (node != null) {//如果节点不为空则选中
                   //      ztree.selectNode(node);
                   //  }
                }
            });
        },
        //初始化部门树
        initDeptTree:function(treeElement,deptList,selectedDeptId){
            var ztree = $.fn.zTree.init($(treeElement), deptTreeSettings, deptList);
            var node = ztree.getNodeByParam("id", selectedDeptId);//根据父框架vm的user对象去查值
            if (node != null) {//如果节点不为空则选中
                ztree.selectNode(node);
            }
            vm.ztrees[treeElement]=ztree;
        },
        //载入部门列表
        loadDeptList: function () {
            $.ajax({
                type: "POST",
                url: "/sys/dept/list",
                dataType: "json",
                success: function (result) {
                    if (result.status!=1) {
                        console.log("获取部门失败:" + result);
                        return;
                    }
                    $("ul[name=deptTree]").each(function(){
                        vm.initDeptTree($(this),result.data.deptlist,parent.vm.user.deptid);
                    });
                    // var  ztree = $.fn.zTree.init($("ul[name=deptTree]"), deptTreeSettings, result.data.deptlist);
                    // var node = ztree.getNodeByParam("id", parent.vm.user.deptid);//根据父框架vm的user对象去查值
                    // if (node != null) {//如果节点不为空则选中
                    //     ztree.selectNode(node);
                    // }
                }
            });
        },
        //搜索条件上面的部门树
        confirmDeptCopy:function(){
           var ztree= vm.ztrees[$("#deptTreeCopy")]
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            //选择上级部门
            vm.sRoleDeptId = node[0].id;
            vm.sRoleDeptName = node[0].name;
            $('#myModalCopy').modal('hide')
        },
        //确认该部门
        confirmDept: function () {
            var ztree= vm.ztrees[$("#deptTree")]
            var node = ztree.getSelectedNodes();//获取选中的节点赋值到文本框中
            //选择上级部门
            vm.roleDeptId = node[0].id;
            vm.roleDeptName = node[0].name;
            $('#myModal').modal('hide')
        }



    }


});
