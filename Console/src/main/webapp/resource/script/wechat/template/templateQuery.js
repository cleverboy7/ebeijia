//模板消息--设置模板行业
$(document).on("wechat-template-add", ".container", function() {
	new Widget({
		title: "设置模板行业",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			optionsAJAX: list_ajax.mcht,
			required: true
		}, {
			type: "select",
			label: "主行业",
			name: "id1",
			optionsAJAX: list_ajax.template,
			required: true
		}, {
			type: "select",
			label: "副行业",
			name: "id2",
			optionsAJAX: list_ajax.template,
			required: true
		}], [{
			type: "submit",
			text: "添加",
			icon: "plus-circle"
		}], {
			autosubmit: {
				url: "wechat/template/add.html"
			}
		})
	});
});

//模板消息--模板管理
$(document).on("wechat-template-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "商户编号",
		name: "mchtId"
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
		title: "商户编号",
		map_func: function(data) {
			var $div = $("<span>").text(data.mchtId);
			list_ajax.replaceText($div, data.mchtId, list_ajax.mcht);
			return $div;
		}
	}, {
		title: "主行业",
		index: "template1"
	}, {
		title: "副行业",
		index: "template2"
	}], {
		url: "wechat/template/query.html",
		table_data: "mchtList",
	});
	new Widget({
		title: "模板列表",
		template: table,
		$query_form: query_form.$
	});
	table.refresh();
})