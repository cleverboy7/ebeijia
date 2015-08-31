<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
	<title>404 Not Found</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link rel="shortcut icon" href="<%=Constant.RES_PATH%>/image/cloud.ico">
    <link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/base.css">
    <link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/color-scheme.css">
    <link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/404.css">
    <link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/animating.css">
</head>
<body>
    <div class="notfound">
		<h4 class="animate0 bounceInUp">啊 哦 ~ 服 务 器 出 错 了</h4>
		<span class="animate1 bounceInUp">5</span>
		<span class="animate2 bounceInUp">0</span>
		<span class="animate3 bounceInUp">0</span>
		<br>
		<button class="animate4 bounceInUp">返 回 主 页</button>
	</div>
	<script>
		document.body.className = "color-scheme-" + (localStorage.getItem("color-scheme") || "default");
		document.getElementsByTagName("button")[0].onclick = function() {
			location.href = ".";
		};
	</script>
</body>
</html>
