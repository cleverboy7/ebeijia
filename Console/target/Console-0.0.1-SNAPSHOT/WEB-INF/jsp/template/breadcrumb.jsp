<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<ipy-template ipy-id="breadcrumb">
	<div class="nav-switcher-wrapper" title="展开/收起菜单">
		<div class="nav-switcher">
			<div class="center-bar"></div>
		</div>
	</div>
	<div class="fullscreen-wrapper" title="全屏">
		<span class="fa fa-arrows-alt"></span>
	</div>
	<ul class="breadcrumb">
		<li class="fa fa-home"></li>
	</ul>
	<div class="color-scheme-switcher">
		<a href="#">
			<span class="fa fa-tint"></span>&nbsp;&nbsp;更换皮肤
		</a>
		<ul>
			<li><a href="#default">默认蓝</a></li>
			<li><a href="#rose-red">粉红色</a></li>
			<li><a href="#purple">紫色</a></li>
			<li><a href="#steel-blue">青灰蓝</a></li>
			<li><a href="#green">粉绿色</a></li>
			<li><a href="#sky-blue">天蓝色</a></li>
		</ul>
	</div>
</ipy-template>