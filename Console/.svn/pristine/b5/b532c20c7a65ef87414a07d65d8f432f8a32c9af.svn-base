//多客服--新增客服
$(document).on("wechat-kf-add", ".container", function() {
	new Widget({
		title: "新增客服",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			optionsAJAX: list_ajax.mcht,
			required: true
		}, {
			type: "text",
			label: "账号",
			name: "id",
			required: true,
			verify: formVerify.maxLength(32)
		}, {
			type: "text",
			label: "名称",
			name: "name",
			required: true,
			verify: formVerify.maxLength(12)
		}, {
			type: "password",
			label: "密码",
			name: "pwd",
			required: true,
			verify: formVerify.password
		}], [{
			type: "submit",
			text: "添加",
			icon: "plus-circle"
		}], {
			autosubmit: {
				url: "wechat/kf/add.html"
			}
		})
	});
});

//关注者管理--查询组别
$(document).on("wechat-kf-query", ".container", function() {
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
			var $div = $("<span>").text(data.id.mchtId);
			list_ajax.replaceText($div, data.id.mchtId, list_ajax.mcht);
			return $div;
		}
	}, {
		title: "账号",
		map_func: function(data) {
			return data.id.kfId;
		}
	}, {
		title: "名称",
		index: "kfNick"
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/kf/query.html",
		table_data: "kfList",
		"delete": {
			url: "wechat/kf/del.html",
			$query_form: query_form.$,
			data: function(data) {
				return {
					mchtId: data.id.mchtId,
					id: data.id.kfId,
					name: data.kfNick,
					pwd: data.kfPwd
				};
			}
		}
	});
	new Widget({
		title: "客服列表",
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
			required: true,
			readonly: true
		}, {
			type: "text",
			label: "账号id",
			name: "id",
			value: trData.id.kfId,
			required: true,
			readonly: true
		}, {
			type: "text",
			label: "账号名称",
			name: "name",
			value: trData.kfNick,
			required: true,
			verify: formVerify.maxLength(12)
		}, {
			type: "password",
			label: "密码",
			name: "pwd",
			required: true,
			verify: formVerify.password
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		var widget = new Widget({
			title: "客服修改",
			template: form,
			animate: "animate6"
		});
		form.$.on("submit", function() {
			ajaxFormSubmit({
				url:"wechat/kf/upd.html",
				data: this,
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});