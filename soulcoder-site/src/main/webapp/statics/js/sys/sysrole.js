/**
 * Created by Aministrator on 2018-01-24.
 */
//部门树设置
// var roleTreeSettings = {
//     view: {
//         selectedMulti: false
//     },
//     check: {
//         enable: false
//     },
//     data: {
//         simpleData: {
//             enable: true,
//             idKey: "id",
//             pIdKey: "parentid",//父id对应的键
//             rootPId: 0
//         }
//     },
//     edit: {
//         enable: false
//     },
//     callback: {
//         onClick: onClick
//     }
// };
var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California'];
$(document).ready(function() {

    $("#sRoleName .typeahead").typeahead( {source: states});
})
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
        ztrees:[],//多个ztree映射关系表
        roleTreeSettings : {
                    view: {
                        selectedMulti: false
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
        roleNameList:[]


    },
    mounted: function () {//页面元素加载完毕

    },
    created:function(){//vm实例加载完毕
        this.loadRoleTreeList();//里面有部门对应的各个角色

        //this.loadDeptList();

    },
    updated: function(){

    },

    methods: {
        //新增用户
        addNewUser: function () {
            swal("add");
        },
        //单击角色树
        clickRoleTree:function(event, treeId, treeNode, clickFlag){
           // swal(JSON.stringify(treeNode));
            if(treeNode.role){
                vm.roleId=treeNode.id;
                vm.roleName=treeNode.name;
                vm.roleDeptId=treeNode.parentid;
                vm.roleDeptName=treeNode.parentname;
                vm.roleDescription = treeNode.description;
            }
        },
        //载入角色树
        loadRoleTreeList: function () {
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
                    vm.initDeptTree("roleDeptTree",result.data.rolelist,parent.vm.user.deptid,vm.roleTreeSettings);
                    for (var key=0;key<result.data.rolelist.length;key++){//去除所有的角色只留部门
                        if(result.data.rolelist[key].role==true){
                            vm.roleNameList.push(result.data.rolelist[key].name);
                            result.data.rolelist.splice(key,1);
                            key-=1;
                        }
                    }

                    // $('#sRoleName .typeahead').typeahead({
                    //         hint: true,
                    //         highlight: true,
                    //         minLength: 1
                    //     },
                    //     {
                    //         name: 'roleNameList',
                    //         source: states//vm.roleNameList
                    //     });
                        // bind other two tree
                        vm.initDeptTree("deptTreeCopy",result.data.rolelist,vm.roleDeptId,deptTreeSettings);
                        vm.initDeptTree("deptTree",result.data.rolelist,vm.sRoleDeptId,deptTreeSettings);




                }
            });
        },
        //初始化部门树
        initDeptTree:function(treeElementId,deptList,selectedDeptId,settings){
            var ztree = $.fn.zTree.init($("#"+treeElementId), settings, deptList);
            var node = ztree.getNodeByParam("id", selectedDeptId);//根据父框架vm的user对象去查值
            if (node != null) {//如果节点不为空则选中
                ztree.selectNode(node);

            }
            vm.ztrees.push({id:treeElementId,instance:ztree});//缓存树的映射关系


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


