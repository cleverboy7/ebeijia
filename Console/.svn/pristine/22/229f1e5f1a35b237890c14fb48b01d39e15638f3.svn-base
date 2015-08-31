<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
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
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/base.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/facebox-master/facebox.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/color-scheme.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/animating.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/animation.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/header.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/nav.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/main.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/breadcrumb.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/widget.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/404.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/home.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/menuSyn.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/style/bigWheel.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/jquery-ui/jquery-ui.min.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/jquery-ui/jquery-ui.datepicker.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/jquery-alerts/jquery.alerts.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/jquery-jgrowl/jquery.jgrowl.min.css">
	<link rel="stylesheet" href="<%=Constant.RES_PATH%>/library/kindeditor/themes/default/default.css">
	<!--[if lte IE 8]>
		<script src="<%=Constant.RES_PATH%>/library/html5shiv.min.js"></script>
		<script>html5.addElements("ipy-template widget widget-title widget-content dual-select");</script>
	<![endif]-->
	<script src="<%=Constant.RES_PATH%>/library/modernizr.min.js"></script>
	<!--[if lte IE 8]><script src="<%=Constant.RES_PATH%>/library/jquery/jquery-1.11.3.min.js"></script><![endif]-->
	<!--[if gte IE 9]><!--><script src="<%=Constant.RES_PATH%>/library/jquery/jquery-2.1.4.min.js"></script><!--<![endif]-->
	<script src="<%=Constant.RES_PATH%>/library/jquery-ui/jquery-ui.min.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/jquery-ui/jquery-ui.datepicker-zh-CN.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/utility.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/common.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/time.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/widget.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/jquery-alerts/jquery.alerts.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/jquery-jgrowl/jquery.jgrowl.min.js"></script>
	<!--[if lte IE 8]><script src="<%=Constant.RES_PATH%>/library/jquery-flot/excanvas.min.js"></script><![endif]-->   
	<script src="<%=Constant.RES_PATH%>/library/jquery-flot/jquery.flot.min.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/jquery-flot/jquery.flot.resize.min.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/jquery-flot/jquery.flot.time.min.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/kindeditor/kindeditor-all-min.js"></script>
	<script>
		var projectURL = "<%=Constant.RRJ_PATH%>/";
		var resourceURL = "<%=Constant.RES_PATH%>/";
		var authority = "<%=session.getAttribute("role")%>";
	</script>
</head>
<body class="nav-expand">
	<header class="clear-fix"></header>
	<nav>
		<ul class="nav"></ul>
	</nav>
	<main>
		<div class="breadcrumb-wrapper"></div>
		<div class="container"></div>
	</main>

	<%@ include file="/WEB-INF/jsp/template/header.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/nav.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/404.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/breadcrumb.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/container.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/home.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/widget.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/paginate.jsp"%>
	<%@ include file="/WEB-INF/jsp/template/luckydraw.jsp"%>
	<script src="<%=Constant.RES_PATH%>/library/facebox-master/facebox.js"></script>
	<script src="<%=Constant.RES_PATH%>/library/wScratchPad.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/formAJAX.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/header.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/home.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/admin/adminQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/face/faceRecognition.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/role/roleQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/txnLog/txnLogQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/activity/activityQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/activity/bigWheel.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/activity/redPacketQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/activity/scratchCard.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/kf/kfQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/mass/massQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/mcht/mchtQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/media/mediaAll.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/media/mediaQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/message/reqMsgQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/message/respMsgQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/menu/menuQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/menu/menuSyn.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/news/newsQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/subGroup/groupQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/subscribe/batchMv.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/subscribe/subscriberQuery.js"></script>
	<script src="<%=Constant.RES_PATH%>/script/wechat/template/templateQuery.js"></script>
</body>
</html>