<%--
  ~ Copyright (c) 2018.所有代码版权归编码者所有!
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Aministrator
  Date: 2018-01-19
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"  scope="page"/>

<html>
<head>
    <title>Title</title>
    <%@include file="/WEB-INF/page/header.jsp"%>
    <link rel="stylesheet" href="${path}/statics/adminlte/plugins/ztree/css/demo.css">
    <link rel="stylesheet" href="${path}/statics/adminlte/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="${path}/statics/adminlte/plugins/typeahead/example.css">
    <link rel="stylesheet" href="${path}/statics/libs/vee-validate/vee-validate.css">

    <style>
        .heightlight {
            background-color:rgb(254, 244, 168)  !important;
            /*whitesmoke*/
        }

        .row{margin-right: 0px; margin-left: 0px;}
        .active{ border-top-color: #acaeaf;}
        .nav-tabs-custom>.nav-tabs>li {
            border-top: 2px solid transparent;
            margin-bottom: -2px;
            margin-right: 5px;
        }
        #rolebox .box-body{padding: 0px}
        #rolebox .nav-tabs-custom{margin-bottom: 0px}
        #rolebox .nav-tabs-custom>.nav-tabs>li.active {
            border-top-color: #eff0f1;
        }
        .control.has-icon>.fa{top:24px}
        .nav-tabs-custom>.tab-content{padding:0px}
    </style>
</head>
<body>
<div id="rolebox">
  <div class="row">
                <!--角色树盒子-->
                <div class="col-md-4">
                    <div class="box-header with-border">
                        <h3 class="box-title">角色树</h3>
                    </div>
                    <div class="box box-primary">
                        <div class="box-body">
                            <!-- 部门列表 -->
                            <div class="row">
                                <div class="col-md-4">

                                    <div class="form-group">
                                        <label>角色名称:</label>
                                        <input type="text" placeholder="角色名称" @change="filterByRoleName" v-model="sRoleName"  id="scrollable-dropdown-menu" data-provide="typeahead"   class="typeahead form-control"/>
                                    </div>

                                </div>

                                <%--<div class="col-md-6">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label>角色部门:</label>--%>
                                        <%--<input type="text" readonly="readonly"  data-toggle="modal" data-target="#myModalCopy" v-model="sRoleDeptName"  :sDeptId="sRoleDeptId"  class="form-control" />--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="col-md-2">--%>
                                    <%--<div class="form-group">--%>
                                        <%--<label ></label>--%>
                                    <%--<button type="button" class="btn margin-r-5 btn-primary pull-right " style="margin-top:21px" @click="loadRoleTreeList">查询</button>--%>
                                    <%--</div>--%>
                                <%--</div>--%>

                            </div>
                            <div class="row">
                                <ul id="roleDeptTree" name="deptTree" style="width: auto;" class="ztree"></ul>
                            </div>

                        </div>
                    </div>
                </div>

                <!--角色信息展示-->
                <div class="col-md-8">
                    <div class="box-header with-border">
                        <h3 class="box-title">角色信息展示</h3>
                    </div>
                    <div class="box box-primary">
                        <div class="box-body">
                            <div class="row">
                             <div class="nav-tabs-custom">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#roleInfo" data-toggle="tab">角色信息</a></li>
                                <li><a href="#rolepermission" data-toggle="tab">角色授权</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="active tab-pane" id="roleInfo">
                                    <div class="row">
                                        <p class="control has-icon has-icon-right">
                                            <label>角色名称:</label>
                                            <input type="text"  v-validate="'required'" name="roleName" placeholder="角色名称" :class="{'input': true, 'is-danger': errors.has('roleName') }" class="form-control" v-model="roleName" :roleId="roleId"/>
                                             <i v-show="errors.has('roleName')" class="fa fa-warning"></i>
                                            <span v-show="errors.has('roleName')" class="help is-danger">{{ errors.first('roleName') }}</span>
                                        </p>
                                    </div>
                                    <div class="row">
                                        <p class="control has-icon has-icon-right">
                                            <label>角色部门:</label>
                                            <input type="text" readonly="readonly"  v-validate="'required'"  name="roleDeptName" data-toggle="modal"  data-target="#myModal" :class="{'input': true, 'is-danger': errors.has('roleDeptName') }"  v-model="roleDeptName"  :deptId="roleDeptId" class="form-control" />
                                            <i v-show="errors.has('roleDeptName')" class="fa fa-warning"></i>
                                            <span v-show="errors.has('roleDeptName')" class="help is-danger">{{ errors.first('roleDeptName') }}</span>
                                        </p>
                                    </div>
                                    <div class="row">
                                        <p class="control has-icon has-icon-right">
                                            <label>角色描述:</label>
                                            <textarea placeholder="角色描述"  v-validate="'required'" class="form-control"  name="roleDescription" :class="{'input': true, 'is-danger': errors.has('roleDescription') }" v-model="roleDescription"></textarea>
                                            <i v-show="errors.has('roleDescription')" class="fa fa-warning"></i>
                                            <span v-show="errors.has('roleDescription')" class="help is-danger">{{ errors.first('roleDescription') }}</span>
                                        </p>
                                    </div>
                                    <div class="row">
                                        <p class="control has-icon has-icon-right">
                                            <label>排序号:</label>
                                            <input type="text" placeholder="排序号"  v-validate="'required|numeric'" class="form-control" name="orderNum" :class="{'input': true, 'is-danger': errors.has('orderNum') }" v-model="orderNum">
                                            <i v-show="errors.has('orderNum')" class="fa fa-warning"></i>
                                            <span v-show="errors.has('orderNum')" class="help is-danger">{{ errors.first('orderNum') }}</span>
                                        </p>
                                    </div>
                                    <div class="row"  style="margin-top: 20px;">
                                        <div class="col-md-6 " >
                                            <button  class="btn-info form-control pull-right" style="width:65px"  @click="addRoleInfo">新增</button>
                                        </div>
                                        <div class="col-md-1 " v-if="roleId>0">
                                                <button  class="btn-primary form-control pull-right" style="width:65px" @click="modifyRoleInfo" >修改</button>
                                        </div>

                                        <div class="col-md-5 " v-if="roleId>0">
                                            <button  class="btn-danger form-control" style="width:65px;margin-left: 10px;"  @click="deleteRoleInfo">删除</button>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.tab-pane -->
                                <div class="tab-pane" id="rolepermission">
                                    <div class="row">
                                        <p class="control has-icon has-icon-right">
                                            <div class="col-md-6">
                                                <label>角色名称:</label>
                                                <input type="text" readonly="readonly"  v-validate="'required'" name="roleName" placeholder="角色名称"  class="form-control" v-model="roleName" :roleId="roleId"/>
                                             </div>
                                        <div class="col-md-6">
                                            <label>角色部门:</label>
                                            <input type="text" readonly="readonly"    name="roleDeptName"   v-model="roleDeptName"  :deptId="roleDeptId" class="form-control" />
                                        </div>
                                        </p>
                                        <p class="control has-icon has-icon-right">


                                        </p>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-md-6">
                                            <strong class="col-sm-5 control-label">功能权限</strong>
                                            <div class="col-sm-10">
                                                <ul id="menuTree" class="ztree"></ul>
                                            </div>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <strong class="col-sm-5 control-label">数据权限</strong>
                                            <div class="col-sm-10">
                                                <ul id="dataTree" class="ztree"></ul>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row" v-if="roleId>0">
                                        <button  class="btn-info form-control" style="width:65px"  @click="updateRoleMenuTree">授权</button>
                                    </div>
                                </div>
                            </div>
                            <!-- /.tab-content -->
                        </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>

            </div>

    <!--部门树弹框-->
    <div class="modal fade" id="myModal" style="display: none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">部门列表</h4>
                </div>
                <div class="modal-body">
                    <!-- 部门列表 -->
                    <div class="zTreeDemoBackground">
                        <ul id="deptTree" name="deptTree" style="width: auto;" class="ztree"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="confirmDept" >确定</button>
                </div>
            </div>
        </div>
    </div>



</div>














</body>
</html>
<%@include file="/WEB-INF/page/footer.jsp"%>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.core.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.excheck.js"></script>
<%--<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exedit.js"></script>--%>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exhide.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/typeahead.jquery.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/handlebars.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/typeahead.bundle.js"></script>

<script src="${path}/statics/libs/vee-validate/vee-validate.js"></script>
<script src="${path}/statics/libs/vee-validate/locale/zh_CN.js"></script><!--该js必须在vee-validate.js后面-->

<script src="${path}/statics/js/sys/sysrole.js"></script>
