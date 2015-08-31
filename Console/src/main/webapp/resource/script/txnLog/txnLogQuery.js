//操作流水管理--查询操作流水
$(document).on("wechat-txnLog-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "select",
		label: "操作渠道",
		name: "txnChl",
		options: {
			"": "所有渠道",
			"0": "接口",
			"1": "后台"
		},
		value: ""
	}, {
		type: "select",
		label: "操作状态",
		name: "status",
		options: {
			"": "所有状态",
			"0": "成功",
			"1": "失败"
		},
		value: ""
	}], [{
		type: "submit",
		text: "查询"
	}, {
		type: "reset",
		text: "重置"
	}]);
	new Widget({
		title: "查询条件",
		template: query_form
	});
	var table = new DynamicTable([{
		title: "流水号",
		index: "txnNo"
	}, {
		title: "操作日期",
		map_func: function(data) {
			return data.txnDate.parseDate();
		}
	}, {
		title: "操作时间",
		map_func: function(data) {
			return data.txnTime.parseDate();
		}
	}, {
		title: "操作员",
		index: "operator"
	}, {
		title: "操作用时(ms)",
		index: "operaTime"
	}, {
		title: "操作状态",
		map_func: function(data) {
			return ["成功", "失败"][data.txnStatus];
		}
	}, {
		title: "功能名称",
		index: "txnName"
	}, {
		title: "操作渠道",
		map_func: function(data) {
			return ["接口", "后台"][data.txnChl];
		}
	}], {
		url: "wechat/txnLog/query.html",
		table_data: "txnLogList",
		$query_form: query_form.$
	});
	new Widget({
		title: "流水列表",
		template: table
	});
	table.refresh();
});