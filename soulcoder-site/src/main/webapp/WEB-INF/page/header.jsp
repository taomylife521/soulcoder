<%--被包含的jsp，要与当前页<%@ page/>内容要一致，不允许contentType="text/html; charset=UTF-8",有空格。--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/bootstrap/css/bootstrap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${path}/statics/adminlte/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
  <link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/dist/css/skins/_all-skins.min.css"> 
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  
  <![endif]-->
  
  <!-- Font Awesome -->
   <link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/bootstrap/css/font-awesome.min.css">
    <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">--%>
  <!-- Ionicons -->
  <%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">--%>
<link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/bootstrap/css/ionicons.min.css">
<%--<link rel="stylesheet" type="text/css" href="${path}/statics/adminlte/plugins/sweetalert/sweetalert.css">--%>

<link href="${path}/statics/adminlte/plugins/daterangepicker/daterangepicker.css" rel="stylesheet" />

<link href="${path}/statics/adminlte/plugins/datepicker/datepicker3.css" rel="stylesheet" />

<link href="${path}/statics/adminlte/plugins/iCheck/all.css" rel="stylesheet" />
<link href="${path}/statics/adminlte/plugins/select2/select2.min.css" rel="stylesheet" />
<link href="${path}/statics/adminlte/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" />
<%--<link href="${path}/statics/adminlte/plugins/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet" />--%>

<style>
 .example-modal .modal {
  position: relative;
  top: auto;
  bottom: auto;
  right: auto;
  left: auto;
  display: block;
  z-index: 1;
 }

 .example-modal .modal {
  background: transparent !important;
 }
</style>




