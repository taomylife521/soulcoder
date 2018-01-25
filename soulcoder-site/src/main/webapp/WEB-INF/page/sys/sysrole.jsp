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

    <style>
        .heightlight {
            background-color:rgb(254, 244, 168)  !important;
            /*whitesmoke*/
        }
    </style>
</head>
<body>
<div id="rolebox">
  <div class="row">
                <!--角色树盒子-->
                <div class="col-md-6">
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
                                        <input type="text" placeholder="角色名称" v-model="sRoleName"  id="sRoleName" data-provide="typeahead"   class="typeahead form-control"/>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>角色部门:</label>
                                        <input type="text" readonly="readonly"  data-toggle="modal" data-target="#myModalCopy" v-model="sRoleDeptName"  :sDeptId="sRoleDeptId"  class="form-control" />
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <div class="form-group">
                                        <label ></label>
                                    <button type="button" class="btn margin-r-5 btn-primary pull-right " style="margin-top:21px" @click="loadRoleTreeList">查询</button>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <ul id="roleDeptTree" name="deptTree" style="width: auto;" class="ztree"></ul>
                            </div>

                        </div>
                    </div>
                </div>

                <!--角色信息展示-->
                <div class="col-md-6">
                    <div class="box-header with-border">
                        <h3 class="box-title">角色信息展示</h3>
                    </div>
                    <div class="box box-primary">
                        <div class="box-body">
                            <div class="row">
                                <div>
                                    <div class="form-group">
                                        <label>角色名称:</label>
                                        <input type="text" placeholder="角色名称" class="form-control" v-model="roleName" :roleId="roleId"/>
                                    </div>

                                </div>

                                <div>
                                    <div class="form-group">
                                        <label>角色部门:</label>
                                        <input type="text" readonly="readonly"  data-toggle="modal" data-target="#myModal"  v-model="roleDeptName"  :deptId="roleDeptId" class="form-control" />
                                    </div>
                                </div>

                            </div>

                            <div class="row">
                                    <div class="form-group">
                                        <label>角色描述:</label>
                                        <textarea placeholder="角色描述" class="form-control" v-model="roleDescription"></textarea>
                                    </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <a href="/sys/user/add" class="btn margin-r-5 btn-info pull-right" >新增</a>

                        </div>
                    </div>
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

  <!--部门树弹框-->
    <div class="modal fade" id="myModalCopy" style="display: none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
          <div class="modal-content">
              <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" >部门列表</h4>
              </div>
              <div class="modal-body">
                  <!-- 部门列表 -->
                  <div class="zTreeDemoBackground">
                      <ul id="deptTreeCopy" name="deptTree" style="width: auto;" class="ztree"></ul>
                  </div>
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                  <button type="button" class="btn btn-primary" @click="confirmDeptCopy" >确定</button>
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
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exedit.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/typeahead.jquery.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/handlebars.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/typeahead.bundle.js"></script>
<script src="${path}/statics/js/sys/sysrole.js"></script>
