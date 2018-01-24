<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
  <c:set value="${pageContext.request.contextPath}" var="path"  scope="page"/>
<html>
<head>
 <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminLTE 2 | Starter${pageContext.request.contextPath}</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 <%@include file="/WEB-INF/page/header.jsp" %>
</head>
<body class="hold-transition login-page">
<div class="login-box" id="loginbox" v-cloak>
		<div class="login-logo">
	   		 <a href="sys/login"><b>Soul</b>Coder</a>
	   </div>
	   
	    <div class="login-box-body">
    <p class="login-box-msg"><b>准备开始把！</b></p>
  
  <div v-if="error" class="alert alert-danger alert-dismissible">
        <h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle"></i> {{errormsg}}</h4>
      </div>
      
    <form>
      <div class="form-group has-feedback">
        <input type="email" class="form-control" placeholder="Email" id="username" v-model="username">
        <span class="fa fa-envelope form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="Password" id="password" v-model="password">
        <span class="fa fa-unlock-alt form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
		       <div>
		        <label>完成验证：</label>
		        <div id="captcha" v-moel="captchaObj">
		            <p id="wait" v-show="isShowCodeTip" >正在加载验证码......</p>
		        </div>
		    </div>
		    <br>
		    <p id="notice" class="hide">请先完成验证</p>
      </div>
      <div class="row">
     
        <!-- /.col -->
        <div class="col-xs-4 pull-right text-center">
          <button type="button" id="submit2" class="btn btn-primary btn-block btn-flat" @click="login">登录</button>
        </div>
        <!-- /.col -->
      </div>
    </form>

 

  </div>
  <!-- /.login-box-body -->
</div>

</body>
</html>

<%--<%@include file="/WEB-INF/page/footer.jsp" %>--%>

 <script type="text/javascript" src="${path}/statics/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
 <script type="text/javascript" src="${path}/statics/adminlte/bootstrap/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="${path}/statics/libs/vue.min.js"></script>
 <script type="text/javascript" src="${path}/statics/libs/global.js"></script>

<script type="text/javascript" src="${path}/statics/libs/gt.js"></script>
<script type="text/javascript" src="${path}/statics/js/login.js"></script>



