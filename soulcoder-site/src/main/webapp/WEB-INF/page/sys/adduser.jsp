<%--
  ~ Copyright (c) 2018.所有代码版权归编码者所有!
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Aministrator
  Date: 2018-01-20
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"  scope="page"/>
<c:set value="${path}/statics/adminlte/dist/img/user2-160x160.jpg" var="imgurl" scope="page"/>
<html>
<head>
   <%@include file="../header.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/plugins/bootstrap-fileinput/css/fileinput.min.css">
    <link rel="stylesheet" href="${path}/statics/adminlte/plugins/ztree/css/demo.css">
    <link rel="stylesheet" href="${path}/statics/adminlte/plugins/ztree/css/metroStyle/metroStyle.css">
    <style>
        .file-input{
            width:100%;
        }
        .file-preview{
            height:300px;

        }
        .avatar-container {
            border-radius: 6px;
            width: 168px;
            height: 168px;
            margin: 0 auto;
        }
        .avatar-img {
            margin: 2px;
            width: 160px;
            height: 160px;
            text-align: center;
            align: center;
            border-radius: 50%;
            padding: 3px;
            border: 3px solid #d2d6de;
        }
    </style>
</head>
<body>

<div id="addUserBox">
<section class="content-header ">
    <h1 class="text-left">
        添加用户
        <small>添加用户信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
        <li><a href="#">添加用户</a></li>
    </ol>
</section>
<!-- 新增用户 -->
<section class="content">
    <div class="box box-primary">

        <div class="box-header">
            <%--<div class="col-xs-8 text-center ">--%>
                <%--<div class="row">--%>
                     <%--<div class="form-group">--%>
                    <%--<div class="file-loading" >--%>
                        <%--<input id="btnUploadImage" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--&lt;%&ndash;<div class="avatar-container text-center">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<img src="${path}/statics/images/avatar.jpg" id="avatarImg" class="avatar-img"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<button type="button" class="btn btn-sm btn-camera" data-btn-type="upload" ><i class="fa fa-camera">&nbsp;上传/更改头像</i></button>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--</div>--%>
        </div>


        <div class="box-body ">


            <div class="row">
                <div class="col-md-4">
                     <div class="form-group">
                         <label>姓名</label>
                        <input type="text" class="form-control"  placeholder="姓名">
                     </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label >性别</label>
                        <div class="input-group">
                            <label class="control-label"> <input type="radio" name="sex"  checked  value="0"> &nbsp;男</label> &nbsp;
                            <label class="control-label"> <input type="radio" name="sex"  value="1">&nbsp; 女</label>
                        </div>
                    </div>

                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <label >用户状态</label>
                        <div class="input-group">
                            <label class="control-label"> <input type="radio" name="userStatus"  checked  value="1"> &nbsp;启用</label> &nbsp;
                            <label class="control-label"> <input type="radio" name="userStatus"  value="0">&nbsp; 禁用</label>
                        </div>


                    </div>

                </div>

            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="text" class="form-control"  placeholder="Email">
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="form-group">
                        <label>出生日期</label>
                        <div class="input-group">
                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                            <input type="text" class="form-control"  data-format="yyyy-mm-dd" id="datepicker" placeholder="出生日期">
                        </div>
                    </div>

                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>手机号</label>
                        <input type="text" class="form-control"  placeholder="手机号">
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="form-group">
                        <label>部门</label>
                        <input type="text" class="form-control" data-toggle="modal" data-target="#myModal"  v-model="deptName" :deptId="deptId"  placeholder="部门">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4 center">
                <div class="form-group ">
                    <div class="file-loading" >
                        <input id="btnUploadImage" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#">
                    </div>
                </div>
                </div>
            </div>


        </div>
        <!-- /.box-body -->

        <div class="box-footer text-center">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" @click="btnAddUser" >确定</button>
        </div>
    </div>
</section>

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
</div>
</body>
</html>

<%@include file="../footer.jsp"%>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.core.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.excheck.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exedit.js"></script>
<script src="${path}/statics/adminlte/plugins/bootstrap-fileinput/js/plugins/piexif.min.js"></script>
<script src="${path}/statics/adminlte/plugins/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="${path}/statics/adminlte/plugins/bootstrap-fileinput/js/zh.js"></script>

<script src="${path}/statics/js/sys/adduser.js"></script>