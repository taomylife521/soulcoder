<%--
  ~ Copyright (c) 2018.所有代码版权归编码者所有!
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Aministrator
  Date: 2018-01-11
  Time: 11:22
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
<body >
<section class="content-header ">
    <h1 class="text-left">
        用户列表
        <small>展示用户列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
        <li><a href="#">用户列表</a></li>
    </ol>
</section>

<section class="content" id="userbox">
    <!--查询条件-->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">检索条件</h3>
            <div class="box-tools pull-right">

                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
            </div>
        </div>

        <div class="box-body">

            <div class="row">
                <div class="col-md-2">
                    <div class="form-group">
                        <label>用户创建起始时间:</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" name="UserCreateTimeRange" v-model="sCondition.createTime" class="form-control" id="reservation">
                        </div>
                    </div>

                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <label>用户手机:</label>
                        <input type="text" name="UserMobile" v-model="sCondition.userMobile"  class="form-control">
                        <!-- /.input group -->
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <label>用户邮箱:</label>
                        <input type="text" name="UserEmail" v-model="sCondition.userEmail"  class="form-control">
                        <!-- /.input group -->
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <label>姓名:</label>
                        <input type="text" name="UserRealName" v-model="sCondition.realName"  class="form-control">
                        <!-- /.input group -->
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <label>部门:</label>
                        <input type="text" readonly="readonly"  data-toggle="modal" data-target="#myModal"  v-model="sCondition.deptName" :deptId="sCondition.deptId"  class="form-control">

                            <%--<ul id="deptTree" v-show="isShowDeptTreeModal" class=" zTreeDemoBackground dropdown-menu" style="margin-top: -15px; margin-left: 14px;"></ul>--%>

                        <!-- /.input group data-toggle="dropdown" @focus="popDeptModal"  -->

                    </div>
                </div>

            </div>

        </div>

        <div class="box-footer">
            <a href="/sys/user/add" class="btn margin-r-5 btn-info pull-right" >新增</a>
            <button type="button" class="btn margin-r-5 btn-info pull-right" @click="editUser">编辑</button>
            <button type="button" class="btn margin-r-5 btn-primary pull-right" @click="loadUserList">查询</button>
        </div>

    </div>
    <div class="box box-default">
        <!--任务列表-->
        <div class="row">
            <div class="col-xs-12">

                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">用户展示列表</h3>
                    </div>
                    <!-- /.box-header stripe   row-border order-column nowrap-->
                    <div class="box-body">
                        <table id="tbUserList" class="table table-bordered table-striped" style="width:100%">
                            <thead>
                            <tr>
                                <th></th>
                                <th>用户编号</th>
                                <th>用户名</th>
                                <th>姓名</th>
                                <th>部门</th>
                                <th>手机号</th>
                                <th>创建时间</th>
                                <th>状态</th>
                            </tr>
                            </thead>
                            <tbody></tbody>
                            <tfoot>

                            </tfoot>
                        </table>
                    </div>
                    <!-- /.box-body -->
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
                        <ul id="deptTree" style="width: auto;" class="ztree"></ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="confirmDept" >确定</button>
                </div>
            </div>
        </div>
    </div>



</section>





</body>
</html>
<%@include file="/WEB-INF/page/footer.jsp"%>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.core.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.excheck.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exedit.js"></script>

<script src="${path}/statics/js/sys/sysuser.js"></script>
