<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<ipy-template ipy-id="home">
	<div class="homepage">
		<div class="homeLeft">
			<div class="main">
				<div class="title">主要功能</div>
				<ul class="mainUl clear-fix">
					<li class="cha-1 animation-tada-hover">
						<a href="#wechat-mcht-query">Accounts</a>
					</li>
					<li class="cha-2 animation-tada-hover">
						<a href="#wechat-reqMsg-query">Messages</a>
					</li>
					<li class="cha-3 animation-tada-hover">
						<a href="#wechat-menu-query">Menus</a>
					</li>
					<li class="cha-4 animation-tada-hover">
						<a href="#wechat-media-query">Media</a>
					</li>
					<li class="cha-5 animation-tada-hover">
						<a href="#wechat-news-query">Articles</a>
					</li>
				</ul>
			</div>
			<widget>
				<widget-title>统计信息</widget-title>
				<widget-content style="padding: 15px;">
					<div id="chartplace" style="height: 300px;"></div>
				</widget-content>
			</widget>
		</div>
		<div class="homeRight">
			<div class="animate6 fadeInDownBig">
				<widget>
					<widget-title>日历</widget-title>
					<widget-content>
						<div id="datepicker"></div>
					</widget-content>
				</widget>
				<div class="loginlist animate1 fadeInDownBig">
					<ul class="ulTag clear-fix">
						<li><a href=""></a><span class="fa fa-user"></span></li>
						<!-- <li class="tagSelected"><a href="#"></a><span class="fa fa-star"></span></li> -->
					</ul>
					<div class="nearestloginlist color">
						<h5>最近一次登录的用户</h5>
						<ul class="usrlist"></ul>
					</div>
					<div class="lovest color"></div>
				</div>
			</div>
		</div>
	</div>
</ipy-template>