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
                                        <div class="form-group">
                                            <label>角色名称:</label>
                                            <input type="text" placeholder="角色名称" class="form-control" v-model="roleName" :roleId="roleId"/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label>角色部门:</label>
                                            <input type="text" readonly="readonly"  data-toggle="modal" data-target="#myModal"  v-model="roleDeptName"  :deptId="roleDeptId" class="form-control" />
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label>角色描述:</label>
                                            <textarea placeholder="角色描述" class="form-control" v-model="roleDescription"></textarea>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group">
                                            <label>排序号:</label>
                                            <input type="text" placeholder="排序号" class="form-control" v-model="orderNum">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6 " >
                                            <button  class="btn-primary form-control pull-right" style="width:65px" >修改</button>
                                        </div>
                                        <div class="col-md-6 " >
                                            <button  class="btn-info form-control" style="width:65px" >新增</button>
                                        </div>

                                    </div>
                                </div>
                                <!-- /.tab-pane -->
                                <div class="tab-pane" id="rolepermission">
                                    <!-- The timeline -->
                                    <ul class="timeline timeline-inverse">
                                        <!-- timeline time label -->
                                        <li class="time-label">
                            <span class="bg-red">
                              10 Feb. 2014
                            </span>
                                        </li>
                                        <!-- /.timeline-label -->
                                        <!-- timeline item -->
                                        <li>
                                            <i class="fa fa-envelope bg-blue"></i>

                                            <div class="timeline-item">
                                                <span class="time"><i class="fa fa-clock-o"></i> 12:05</span>

                                                <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>

                                                <div class="timeline-body">
                                                    Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                                    weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                                    jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                                    quora plaxo ideeli hulu weebly balihoo...
                                                </div>
                                                <div class="timeline-footer">
                                                    <a class="btn btn-primary btn-xs">Read more</a>
                                                    <a class="btn btn-danger btn-xs">Delete</a>
                                                </div>
                                            </div>
                                        </li>
                                        <!-- END timeline item -->
                                        <!-- timeline item -->
                                        <li>
                                            <i class="fa fa-user bg-aqua"></i>

                                            <div class="timeline-item">
                                                <span class="time"><i class="fa fa-clock-o"></i> 5 mins ago</span>

                                                <h3 class="timeline-header no-border"><a href="#">Sarah Young</a> accepted your friend request
                                                </h3>
                                            </div>
                                        </li>
                                        <!-- END timeline item -->
                                        <!-- timeline item -->
                                        <li>
                                            <i class="fa fa-comments bg-yellow"></i>

                                            <div class="timeline-item">
                                                <span class="time"><i class="fa fa-clock-o"></i> 27 mins ago</span>

                                                <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                                <div class="timeline-body">
                                                    Take me to your leader!
                                                    Switzerland is small and neutral!
                                                    We are more like Germany, ambitious and misunderstood!
                                                </div>
                                                <div class="timeline-footer">
                                                    <a class="btn btn-warning btn-flat btn-xs">View comment</a>
                                                </div>
                                            </div>
                                        </li>
                                        <!-- END timeline item -->
                                        <!-- timeline time label -->
                                        <li class="time-label">
                            <span class="bg-green">
                              3 Jan. 2014
                            </span>
                                        </li>
                                        <!-- /.timeline-label -->
                                        <!-- timeline item -->
                                        <li>
                                            <i class="fa fa-camera bg-purple"></i>

                                            <div class="timeline-item">
                                                <span class="time"><i class="fa fa-clock-o"></i> 2 days ago</span>

                                                <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                                <div class="timeline-body">
                                                    <img src="http://placehold.it/150x100" alt="..." class="margin">
                                                    <img src="http://placehold.it/150x100" alt="..." class="margin">
                                                    <img src="http://placehold.it/150x100" alt="..." class="margin">
                                                    <img src="http://placehold.it/150x100" alt="..." class="margin">
                                                </div>
                                            </div>
                                        </li>
                                        <!-- END timeline item -->
                                        <li>
                                            <i class="fa fa-clock-o bg-gray"></i>
                                        </li>
                                    </ul>
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

  <%--<!--部门树弹框-->--%>
    <%--<div class="modal fade" id="myModalCopy" style="display: none" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
      <%--<div class="modal-dialog" role="document">--%>
          <%--<div class="modal-content">--%>
              <%--<div class="modal-header">--%>
                  <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                  <%--<h4 class="modal-title" >部门列表</h4>--%>
              <%--</div>--%>
              <%--<div class="modal-body">--%>
                  <%--<!-- 部门列表 -->--%>
                  <%--<div class="zTreeDemoBackground">--%>
                      <%--<ul id="deptTreeCopy" name="deptTree" style="width: auto;" class="ztree"></ul>--%>
                  <%--</div>--%>
              <%--</div>--%>
              <%--<div class="modal-footer">--%>
                  <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
                  <%--<button type="button" class="btn btn-primary" @click="confirmDeptCopy" >确定</button>--%>
              <%--</div>--%>
          <%--</div>--%>
      <%--</div>--%>
  <%--</div>--%>

</div>














</body>
</html>
<%@include file="/WEB-INF/page/footer.jsp"%>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.all.min.js"></script>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.core.js"></script>
<%--<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.excheck.js"></script>--%>
<%--<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exedit.js"></script>--%>
<script src="${path}/statics/adminlte/plugins/ztree/jquery.ztree.exhide.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/typeahead.jquery.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/handlebars.js"></script>
<script src="${path}/statics/adminlte/plugins/typeahead/typeahead.bundle.js"></script>
<script src="${path}/statics/js/sys/sysrole.js"></script>
