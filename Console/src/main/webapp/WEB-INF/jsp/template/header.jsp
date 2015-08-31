<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<ipy-template ipy-id="header">
	<div class="logo">
	    <img src="<%=Constant.RES_PATH%>/image/logo.png" width="200" height="80">
	</div>
	<div class="topbar">
	    <ul class="topmenu">
	        <li>
	            <a href="#wechat-mcht-query">Accounts</a>
	        </li>
	        <li>
	            <a href="#wechat-reqMsg-query">Messages</a>
	        </li>
	        <li>
	            <a href="#wechat-menu-query">Menus</a>
	        </li>
	        <li>
	            <a href="#wechat-media-query">Media</a>
	        </li>
	        <li>
	            <a href="#wechat-news-query">Articles</a>
	        </li>
	    </ul>
	    <div class="user-info" user-id="${admin[0]}">
	        <img class="avatar animation-tada-hover" src="${admin[2]}" alt="avatar">
	        <div class="info">
	            <header>${admin[1]}</header>
	            <ul>
	                <li>
	                    <a class="changePwd" href="#">修改密码</a>
	                </li>
	                <li>
	                    <a class="logout" href="#">退出</a>
	                </li>
	            </ul>
	        </div>
	    </div>
	    <ul class="time"></ul>
	</div>
</ipy-template>