/**
 * Created by Aministrator on 2018-01-24.
 */
Vue.use(VeeValidate); // good to go.

//部门树设置

var vm = new Vue({
    el:"#rolebox",
    data:{
        deptRoleList:{},//部门角色列表
        roleName:'',//角色名称
        roleDeptId:-1,//角色部门id
        roleDeptName:'',//角色部门id
        roleDescription:'',//角色描述
        roleId:-1,
        orderNum:'',
        sRoleName:'',//要查询的角色名称
        // sRoleDeptId:'',//要查询的角色部门id
        // sRoleDeptName:'',//要查询的角色部门名称
        ztrees:[],//多个ztree映射关系表
        roleIdMenuTreeMap:[],//多个roleid 菜单映射数据表 key：roleid，value：权限集合
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
        roleMenuTreeSettings : {
            view: {
                selectedMulti: false
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",// id编号命名 默认
                    pIdKey: "parentid",//父id编号命名 默认
                    rootPId: 0//// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
                }
            },
            edit: {
                enable: false
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
        this.loadDeptRoleMenuTree();//获取每个部门角色对应的权限树
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
               // var data="roleid="+vm.roleId+"&rolename="+vm.roleName+"&roledescription="+vm.roleDescription+"&roledeptid="+vm.roleDeptId+"&ordernum="+vm.orderNum;
                var data={
                    rolename:vm.roleName,
                    roledescription:vm.roleDescription,
                    roledeptid:vm.roleDeptId,
                    ordernum:vm.orderNum,
                    roledeptname:vm.roleDeptName,
                    roleid:vm.roleId
                };
                $.ajax({
                    type: "POST",
                    url: "/sys/role/modify",
                    dataType: "json",
                    data:JSON.stringify(data),
                    contentType:'application/json;charset=UTF-8',
                    success: function (result) {
                        if(!vm.ajaxCallInterceptor(result)){
                            return false;
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
        },

        //删除角色信息
        deleteRoleInfo:function(){

            swal({
                title: "确定删除吗？",
                         text: "一旦删除该角色，该角色对应的菜单权限也会一并删除!",
                         type: "warning",
                         showCancelButton: true,
                         confirmButtonColor: "#DD6B55",
                         confirmButtonText: "确定删除！",
                         closeOnConfirm: false,
                         buttons: true,
                         dangerMode: true,
                     })
                .then((willDelete) => {
                        if (!willDelete) {
                          return;
                        }
                        var data={
                            "roleid":vm.roleId,
                            "deptid":vm.roleDeptId
                        }
                        $.ajax({
                            type: "POST",
                            url: "/sys/role/delete",
                            dataType: "json",
                            data:JSON.stringify(data),
                            contentType:'application/json;charset=UTF-8',
                            success: function (result) {
                                if(!vm.ajaxCallInterceptor(result)){
                                    return false;
                                }
                                vm.removeTreeNode("roleDeptTree","id",vm.roleId);//移除节点
                                vm.roleId=-1;
                                vm.roleDeptId=-1;
                                var ztree = vm.getZTreeInstance("menuTree");
                                ztree.checkAllNodes(false);//取消所有选择的节点
                                ztree.expandAll(true);//取消所有展开
                                vm.roleDeptName='';
                                vm.roleName='';
                                vm.roleDescription='';
                                swal("删除成功");
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

                vm.cacheAndBindMenuToRoleMenuTree();//绑定角色授权树
            }

        },
        cacheAndBindMenuToRoleMenuTree:function(){
           var roleMenuTreeData= vm.getRoleMenuZTreeData();
           if(roleMenuTreeData !=null) {
               if (roleMenuTreeData.menutree != null) {
                   vm.bindMenuToRoleTree("menuTree", roleMenuTreeData.menutree);//绑定菜单树
               }
               if (roleMenuTreeData.datatree != null) {
                   vm.bindMenuToRoleTree("dataTree", roleMenuTreeData.datatree);//绑定数据权限树
               }
               return;
           }
            //请求服务端获取角色id对应的权限树
            var roleId=vm.roleId;
            var data={
                "roleid":roleId
            }
            $.ajax({
                type: "POST",
                url: "/sys/role/rolemenutree",
                dataType: "json",
                data:JSON.stringify(data),
                contentType:'application/json;charset=UTF-8',
                success: function (result) {
                    if (!vm.ajaxCallInterceptor(result)) {
                        return false;
                    }
                    vm.roleIdMenuTreeMap.push({"roleId":roleId,"roleMenuTreeData":result.data});//缓存角色id与菜单树的对应关系表
                    if(result.data.menutree!=null && result.data.menutree.length>0) {
                        vm.bindMenuToRoleTree("menuTree", result.data.menutree);//绑定菜单树
                    }
                    if(result.data.datatree!=null && result.data.datatree.length>0) {
                        vm.bindMenuToRoleTree("dataTree", result.data.datatree);//绑定数据权限树
                    }

                }
            });
        },
        //绑定菜单到对应的角色树（角色菜单权限树，角色数据权限树）
        bindMenuToRoleTree:function(roleTreeId,roleTreeData){
            var ztree = vm.getZTreeInstance(roleTreeId);
            ztree.checkAllNodes(false);//取消所有选择的节点
            ztree.expandAll(false);//取消所有展开
            //选中菜单树
            $.each(roleTreeData,function(i){
                var id= roleTreeData[i].id;
                var node = ztree.getNodeByParam("id", id);
                if(node !=null) {
                    ztree.checkNode(node, true, false);//选中节点
                    ztree.expandNode(node, true, false, true);
                }
            });
        },

        //初始化根据登录人的角色加载部门角色菜单树
        loadDeptRoleMenuTree:function(){
          //请求服务端获取角色id对应的权限树
            var data={
                     "roleid":parent.vm.user.roleid,
                   }
             $.ajax({
                      type: "POST",
                      url: "/sys/role/rolemenutree",
                      dataType: "json",
                      data:JSON.stringify(data),
                      contentType:'application/json;charset=UTF-8',
                      success: function (result) {
                          if (!vm.ajaxCallInterceptor(result)) {
                              return false;
                          }
                          vm.roleIdMenuTreeMap.push({roleid: vm.roleId, roleMenuTreeData: result.data});//缓存角色id与菜单树的对应关系表
                          vm.initTree("menuTree", result.data.menutree, vm.roleDeptId, vm.roleMenuTreeSettings,true);
                      }
              });

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
                    if(!vm.ajaxCallInterceptor(result)){
                        return false;
                    }

                    vm.roleTreeSettings.callback.onClick=vm.clickRoleTree;
                    vm.roleTreeSettings.view.fontCss=vm.getFontCss;
                    vm.initTree("roleDeptTree",result.data.rolelist,parent.vm.user.deptid,vm.roleTreeSettings,true);
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
                       // vm.initTree("deptTreeCopy", result.data.rolelist, vm.roleDeptId, deptTreeSettings,true);
                        vm.initTree("deptTree", result.data.rolelist, vm.sRoleDeptId, deptTreeSettings,true);
                        vm.initTree('dataTree',result.data.rolelist,parent.vm.user.deptid,vm.roleMenuTreeSettings,true);
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

        //选中树中的某个节点id
        selectedTreeNode:function(treeElementId,selectedProp,selectedPropValue){
                var ztree = vm.getZTreeInstance(treeElementId);
                var node = ztree.getNodeByParam(selectedProp, selectedPropValue);//根据父框架vm的user对象去查值
                if (node != null) {//如果节点不为空则选中
                    ztree.selectNode(node);
                    ztree.expandNode(node, true, true, true);
                }

        },

        //移除树中的某个节点id
        removeTreeNode:function(treeElementId,selectedProp,selectedPropValue){
            var ztree = vm.getZTreeInstance(treeElementId);
            var node = ztree.getNodeByParam(selectedProp, selectedPropValue);//根据父框架vm的user对象去查值
            if (node != null) {//如果节点不为空则选中
                ztree.removeNode(node);
            }

        },

        //给角色授权
        updateRoleMenuTree:function(){
            var ztree = vm.getZTreeInstance("menuTree");
           var menuCheckedNodes= ztree.getCheckedNodes(true);//获取所有被选中菜单的集合
            var menuIdArray =[];
            $.each(menuCheckedNodes,function(index){
                menuIdArray.push(menuCheckedNodes[index].id);
            });
            var zDataTree = vm.getZTreeInstance("dataTree");
            var dataCheckedNodes= zDataTree.getCheckedNodes(true);//获取所有被选中数据权限的集合
            var deptIdArray =[];
            $.each(dataCheckedNodes,function(index){
                deptIdArray.push(dataCheckedNodes[index].id);
            });

            var data={
                "roleid":vm.roleId,
                "deptid":vm.roleDeptId,
                "menuidlist":menuIdArray,
                "deptidlist":deptIdArray
            }
            $.ajax({
                type: "POST",
                url: "/sys/role/updaterolemenutree",
                dataType: "json",
                 data:JSON.stringify(data),
                contentType:'application/json;charset=UTF-8',
                success: function (result) {
                    if(!vm.ajaxCallInterceptor(result)){
                        return false;
                    }
                    swal("更新成功");
                }
            });
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
        },

        //获取角色id对应菜单树的值
        getRoleMenuZTreeData:function(){
            var menuTreeData=null;
            $.each(vm.roleIdMenuTreeMap,function(i){
                if(vm.roleIdMenuTreeMap[i].roleId == vm.roleId){
                    menuTreeData= vm.roleIdMenuTreeMap[i].roleMenuTreeData;
                }
            });
            return menuTreeData;
        }



    }


});


