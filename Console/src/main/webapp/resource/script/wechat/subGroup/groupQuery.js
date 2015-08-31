//关注者管理--新增组别
$(document).on("wechat-subGroup-add", ".container", function() {
	new Widget({
		title: "新增组别",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			optionsAJAX: list_ajax.mcht,
			required: true
		}, {
			type: "text",
			label: "组别名称",
			name: "name",
			id: "name",
			required: true,
			verify: formVerify.maxLength(64)
		}], [{
			type: "submit",
			text: "添加",
			icon: "plus-circle"
		}], {
			autosubmit: {
				url: "wechat/subGroup/add.html",
				success: function() {
					list_ajax.removeCache("subGroup");
				}
			}
		})
	});
});

//关注者管理--查询组别
$(document).on("wechat-subGroup-query", ".container", function() {
	list_ajax.removeCache("subGroup");
	var query_form = new QueryForm([{
		type: "text",
		label: "商户编号",
		name: "mchtId"
	}, {
		type: "text",
		label: "组别名称",
		name: "name"
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
			var $div = $("<span>").text(data.id.mchtId);
			list_ajax.replaceText($div, data.id.mchtId, list_ajax.mcht);
			return $div;
		}
	}, {
		title: "组别ID",
		map_func: function(data) {
			return data.id.groupId;
		}
	}, {
		title: "组别名称",
		index: "name"
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"]
	}], {
		url: "wechat/subGroup/query.html",
		table_data: "groupList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/subGroup/del.html",
			data: function(data) {
				return {
					mchtId: data.id.mchtId,
					groupId: data.id.groupId
				};
			}
		}
	});
	new Widget({
		title: "组别列表",
		template: table
	});
	table.refresh();
	table.$.on("modify", "tr", function() {
		var trData = $(this).data("data");
		lockScreen();
		var form = new PopupForm([{
			type: "text",
			label: "商户编号",
			name: "mchtId",
			value: trData.id.mchtId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "组别ID",
			name: "groupId",
			value: trData.id.groupId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "组别名称",
			name: "name",
			value: trData.name,
			required: true,
			verify: formVerify.maxLength(64)
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		new Widget({
			title: "组别修改",
			template: form,
			animate: "animate6"
		});
		form.$.on("submit", function() {
			ajaxFormSubmit({
				data: this,
				url: "wechat/subGroup/upd.html",
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});