<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
  <c:set value="${pageContext.request.contextPath}" var="path"  scope="page"/>
  <c:set value="${path}/statics/adminlte/dist/img/user2-160x160.jpg" var="imgurl" scope="page"/>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
 <%@include file="/WEB-INF/page/header.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper" id="soulcoderbox">
	
	  <header class="main-header">
	    <!-- Logo -->
	    <a href="../../index2.html" class="logo">
	      <!-- mini logo for sidebar mini 50x50 pixels -->
	      <span class="logo-mini"><b>S</b>Cod</span>
	      <!-- logo for regular state and mobile devices -->
	      <span class="logo-lg"><b>Soul</b>Coder</span>
	    </a>
	    <!-- Header Navbar: style can be found in header.less -->
	    <nav class="navbar navbar-static-top">
	      <!-- Sidebar toggle button-->
	      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </a>
	
	      <div class="navbar-custom-menu">

	        <ul class="nav navbar-nav">

				<li>
                    <!-- Button trigger modal -->
                    <a  href="javascript:;" data-toggle="modal" data-target="#myModal">
                        <i class="fa fa-lock"></i> 修改密码
                    </a>

				</li>
	          <!-- User Account: style can be found in dropdown.less -->
	          <li class="dropdown user user-menu">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	              <img src="${imgurl}" class="user-image" alt="User Image">
	              <span class="hidden-xs">{{user.realname}}</span>
	            </a>
	            <ul class="dropdown-menu">
	              <!-- User image -->
	              <li class="user-header">
	                <img src="${imgurl}" class="img-circle" alt="User Image">
	
	                <p>
	                 {{user.realname}}
	                  <small> 注册时间:{{user.createtime}}</small>
	                </p>
	              </li>
	             
	             
	              <!-- Menu Footer-->
	              <li class="user-footer">
	                <div class="pull-left">
	                  <a href="#" class="btn btn-default btn-flat">个人资料</a>
	                </div>
	                <div class="pull-right">
	                  <a href="#" class="btn btn-default btn-flat">退出</a>
	                </div>
	              </li>
	            </ul>
	          </li>
	          <!-- Control Sidebar Toggle Button -->
	        </ul>
	      </div>
	    </nav>
	  </header>

	  <!--左边栏。包含logo -->
	  <aside class="main-sidebar">
	    <!-- sidebar: style can be found in sidebar.less -->
	    <section class="sidebar">
	      <!-- Sidebar user panel -->
	      <div class="user-panel">
	        <div class="pull-left image">
	          <img src="${imgurl}" class="img-circle" alt="用户头像">
	        </div>
	        <div class="pull-left info">
	          <p>{{user.realname}}</p>
	          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
	        </div>
	      </div>
	    
	      <!-- sidebar menu: : style can be found in sidebar.less -->
	      <ul class="sidebar-menu">
	        <li class="header">菜单列表</li>
	       <menu-item :item="item" v-for="item in menuList"></menu-item>
	      </ul>
	    </section>
	    <!-- /.sidebar -->
	  </aside>
	
	  <!--内容区域-->
	  <div class="content-wrapper">
		  <section class="content" style="background:#fff;">
		 	 <iframe scrolling="yes" frameborder="0" style="width:100%;min-height:200px;overflow:visible;background:#fff;" :src="mainpage"></iframe>
		  </section>
	  </div>

	  <!-- 底部区域-->
	  <footer class="main-footer">
	    <div class="pull-right hidden-xs">
	      <b>Version</b> 2.3.8
	    </div>
	    <strong>Copyright &copy; 2018-2018 <a href="http://almsaeedstudio.com">soulcoder</a>.</strong> All rights
	    reserved.
	  </footer>



        <!-- 修改密码对话框 -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                    </div>
                    <div class="modal-body">
                        <!-- 修改密码 -->
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">账号</div>
                                        <span class="label label-success" style="vertical-align: bottom;">{{user.username}}</span>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">原密码</div>
                                        <div class="col-sm-6">
                                            <input type="password" class="form-control" v-model="password" placeholder="原密码"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">确认原密码</div>
                                        <div class="col-sm-6">
                                            <input type="password" class="form-control" v-model="confirmpassword" placeholder="确认原密码"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3 control-label">新密码</div>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" v-model="newpassword" placeholder="新密码"/>
                                        </div>
                                    </div>
                                </div>
                            </form>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" @click="updatePassword">修改</button>
                    </div>
                </div>
            </div>
        </div>


	</div>
	<!-- ./wrapper -->
	<%@include file="/WEB-INF/page/footer.jsp" %>
</body>
</html>

<script type="text/javascript" src="${path}/statics/libs/router.js"></script>
<script type="text/javascript" src="${path}/statics/adminlte/dist/js/app.js"></script>
<script type="text/javascript" src="${path}/statics/adminlte//plugins/fastclick/fastclick.js"></script>
<script type="text/javascript" src="${path}/statics/adminlte/dist/js/demo.js"></script>
<script type="text/javascript" src="${path}/statics/js/index.js"></script>


 