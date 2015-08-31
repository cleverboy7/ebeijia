$(document).on("wechat-media-all", ".container", function() {
	new Widget({
		title: "商户选择",
		template: new QueryForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			size: "large",
			optionsAJAX: list_ajax.mcht
		}], [{
			type: "submit",
			text: "选择"
		}]),
		retractable: false
	});

	new Widget({
		title: "当前/最大素材数"
	}).$.find("widget-content").css("padding", "20px")
		.append('<div class="plot" style="width:100%;height:400px"></div>');
	$$("widget").css({
		width: "70%"
	}).find(".button-wrapper").css({
		float: "left",
		marginLeft: "40px"
	});

	$$("form").on("submit", function() {
		ajaxFormSubmit({
			url: "wechat/media/mediaAll.html",
			data: this,
			successAlert: false,
			success: function(map) {
				/*****BAR GRAPH*****/
				var mediaType = [
					[1, "image"],
					[2, "news"],
					[3, "video"],
					[4, "voice"]
				],
				max = [
					[1, 5000],
					[2, 1000],
					[3, 1000],
					[4, 5000]
				],
				foddernums = [
					[1, map.image_count],
					[2, map.news_count],
					[3, map.video_count],
					[4, map.voice_count]
				];
				//多组数据就需要设置label
				var dataset = [{
					label: "最大数量",
					data: max,
					color: "#cfddea"
				}, {
					label: "当前数量",
					data: foddernums,
					color: "#fc6"
				}];

				//图表的参数设置
				var options = {
					//图表参数
					series: {
						lines: {
							show: false,
							fill: true,
							steps: false
						},
						bars: {
							show: true,
							align: "center",
							barWidth: 0.8
						}
					},
					//x轴参数
					xaxis: {
						ticks: mediaType
					},
					yaxis: {
						minTickSize: 1,
						min: 0
					},
					//背景表格参数
					grid: {
						hoverable: true,
						clickable: true,
						borderColor: '#666',
						borderWidth: 2,
						labelMargin: 15
					},
				}
				//$.plot(生成图表的位置，数据，图表参数设置)
				$.plot($$(".plot"), dataset, options);
				var previousPoint = null;
				$$(".plot").on("plothover", function(event, pos, item) {
					$$("#x").text(pos.x.toFixed(2));
					$$("#y").text(pos.y.toFixed(2));
					if (item) {
						if (previousPoint != item.dataIndex) {
							previousPoint = item.dataIndex;
							$("#tooltip").remove();
							var y = item.datapoint[1].toFixed(0);
							showTooltip(item.pageX, item.pageY, y);
						}
					} else {
						$("#tooltip").remove();
						previousPoint = null;
					}
				});
			}
		});
	});
});