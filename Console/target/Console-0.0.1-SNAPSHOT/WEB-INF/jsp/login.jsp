<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<%@ page import="org.ebeijia.tools.String4J"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8">
	<title>微信管理平台</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
	<meta name="renderer" content="webkit">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="full-screen" content="yes">
	<meta name="x5-fullscreen" content="yes">
	<link rel="shortcut icon" href="<%=Constant.RES_PATH%>/image/i2.ico">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/login.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/animating.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/color-scheme.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/jquery-alerts/jquery.alerts.css">
	<!--[if lte IE 8]><script src="<%=Constant.RES_PATH%>/library/jquery/jquery-1.11.3.min.js"></script><![endif]-->
	<!--[if gte IE 9]><!--><script src="<%=Constant.RES_PATH%>/library/jquery/jquery-2.1.4.min.js"></script><!--<![endif]-->
	<script src="<%=Constant.RES_PATH%>/library/jquery-alerts/jquery.alerts.js"></script>
	<script type="text/javascript">
		var animate=["zoomInDown","fadeInUpBig","fadeInDownBig","bounceIn","bounceInUp","rotateIn","rotateInDownLeft","rotateInDownRight","lightSpeedIn"];
		$(document).ready(function() {
			alertMessage('<%=String4J.stringCode2(request.getAttribute("message"))%>');
			var i = parseInt(animate.length*Math.random());
			$(".animate0, .animate1, .animate2, .animate3, .animate4").addClass(animate[i]);
			$(document.body).addClass("color-scheme-" + (localStorage.getItem("color-scheme") || "default"));
		});
		function alertMessage(message) {
			if (message != "null") {
				jAlert(message, "提示信息");
			}
		}
	</script>
</head>
<body class="loginpage">
	<div class="loginpanel">
		<div class="loginpanelinner">
			<div class="logo animate0">
				<img src="<%=Constant.RES_PATH%>/image/logo.png" alt="ebjcloud">
			</div>
			<form id="loginform" method="post" action="<%=Constant.RRJ_PATH%>/login.html">
				<div class="inputwrapper animate1">
					<input type="text" name="usrName" placeholder="请输入管理员账号">
				</div>
				<div class="inputwrapper animate2">
					<input type="password" name="loginPwd" placeholder="请输入授权密码">
				</div>
				
				<div class="inputwrapper animate3">
					<button type="submit" id="login">登 录</button>
				</div>
				<div class="inputwrapper animate4">
				</div>
			</form>
		</div>
	</div>

	<div class="loginfooter">
		<div>
			<span>Copyright &copy; 2015 上海益倍嘉信息技术有限公司</span>
		</div>
		<div>
			<span>Designed by: ebeijia</span>
		</div>
	</div>
</body>
</html>