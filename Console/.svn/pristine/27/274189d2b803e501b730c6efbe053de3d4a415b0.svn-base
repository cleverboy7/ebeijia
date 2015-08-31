$(document).on("home", ".container", function() {
	$$(".mainUl a").on("click", function() {
		$.switch_page($('body > nav a[href="' + $(this).attr("href") + '"]'));
	});

	$$("#datepicker").datepicker();

	$.ajax2({
		url: "list/upTime.html",
		type: "post",
		dataType: "json",
		success: function(data){
			data.forEach(function(a){
				var date = a[2].parseDate();
				var li = '<li><div class="skinimg clear-fix"><div class="animation-tada-hover"><img src="'+ a[3] +'" alt="avatar"></div><div class="uinfo"><h5>'+a[1]+'</h5><span class="gray">系统管理员</span><span>最后登录： '+date+'</span></div></div></li>';
				$$(".usrlist").append(li);
			});
		}
	});

	// simple chart
	var flash = [[0, 11], [1, 9], [2,12], [3, 8], [4, 7], [5, 3], [6, 1]]
		,html5 = [[0, 5], [1, 4], [2,4], [3, 1], [4, 9], [5, 10], [6, 13]]
		,css3 = [[0, 6], [1, 1], [2,9], [3, 12], [4, 10], [5, 12], [6, 11]]
		,date = [[0,"05-11"],[1,"05-12"],[2,"05-13"],[3,"05-14"],[4,"05-15"],[5,"05-16"],[6,"05-17"]]
	
	var plot = $.plot($$("#chartplace"), [
	    {data: flash, label: "关注人数", color: "#6fad04"},
		{data: html5, label: "优质用户", color: "#06c"},
	    {data: css3, label: "僵尸粉", color: "#666"}
	], {
		series: {
			lines: {
				show: true,
				fill: true,
				fillColor: {
					colors: [{opacity: 0.05}, {opacity: 0.15}]
				}
			},
			points: {show: true}
		},
		legend: {position: 'nw'},
		grid: {
			hoverable: true,
			clickable: true,
			borderColor: '#666',
			borderWidth: 2,
			labelMargin: 10
		},
		yaxis: {min: 0, max: 15},
		xaxis: {ticks:date, min: 1}
	});
	
	var previousPoint = null;
	$$("#chartplace").on("plothover", function(event, pos, item) {
		if (item) {
			if (previousPoint != item.dataIndex) {
				previousPoint = item.dataIndex;
				$("#tooltip").remove();
				var x = item.datapoint[0],
				y = item.datapoint[1]
				showTooltip(item.pageX, item.pageY, item.series.label + " " + x + " 人");
			}
		} else {
		   $("#tooltip").remove();
		   previousPoint = null;
		}
	}).on("plotclick", function(event, pos, item) {
		if (item) {
			$$("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
			plot.highlight(item.series, item.datapoint);
		}
	});
});