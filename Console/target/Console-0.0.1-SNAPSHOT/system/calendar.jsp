<%@page import="org.ebeijia.tools.String4J"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.ebeijia.util.Constant"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=Constant.RES_PATH%>/css/style.d.css" type="text/css" />
<link rel="stylesheet" href="<%=Constant.RES_PATH%>/css/level.css" type="text/css" />
<style >
	.relative{
	position:relative;
	}
</style>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/jquery-migrate-1.1.1.min.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/jquery-ui-1.9.2.min.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/modernizr.min.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=Constant.RES_PATH%>/js/custom.js"></script>
<title>账号信息</title>
<script type='text/javascript'>
	jQuery(document).ready(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		var calendar = jQuery('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			buttonText: {
				prev: '&laquo;',
				next: '&raquo;',
				prevYear: '&nbsp;&lt;&lt;&nbsp;',
				nextYear: '&nbsp;&gt;&gt;&nbsp;',
				today: 'today',
				month: 'month',
				week: 'week',
				day: 'day'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
			},
			editable: true,
			events: [
				{
					title: 'All Day Event',
					start: new Date(y, m, 1)
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false
				}
			]
		});
		
	});

</script>


</head>
<body>
	<div class="mainwrapper">
		<div class="rightpanel">

			<ul class="breadcrumbs">
				<li><a href="/Console/login/right.jsp"><i class="iconfa-home"></i></a>
					<span class="separator"></span></li>
				<li>Calendar</li>
				<li class="right"><a href="" data-toggle="dropdown"
					class="dropdown-toggle"><i class="icon-tint"></i> 更换皮肤</a>
					<ul class="dropdown-menu pull-right skin-color">
						<li><a href="default">默认色</a></li>
						<li><a href="navyblue">玫红色</a></li>
						<li><a href="palegreen">紫色</a></li>
						<li><a href="red">红色</a></li>
						<li><a href="green">绿色</a></li>
						<li><a href="brown">天蓝色</a></li>
					</ul></li>
			</ul>

			<div class="maincontent">
				<div class="maincontentinner">
				 <div id='calendar'></div>
				</div>
				<div class="maincontentinner">
					<div class="footer relative">
						<div class="footer-left" align="center">
							<span>Copyright &copy; 2015上海益倍嘉信息技术有限公司 </span>
						</div>
						<div class="footer-right" align="center">
							<span>Designed by: ebeijia</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
