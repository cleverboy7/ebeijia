//账户管理--绑定微信账户
$(document).on("wechat-mcht-add", ".container", function() {
	new Widget({
		title: "绑定微信账户",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			optionsAJAX: list_ajax.mcht,
			required: true
		}, {
			type: "text",
			label: "接入微信URL",
			name: "url",
			required: true,
			verify: formVerify.maxLength(256)
		}, {
			type: "text",
			label: "接入微信Token",
			name: "token",
			required: true,
			verify: formVerify.maxLength(64)
		}, {
			type: "text",
			label: "公众号AppId",
			name: "appid",
			required: true,
			verify: formVerify.maxLength(64)
		}, {
			type: "text",
			label: "微信AppSecret",
			name: "appsecret",
			required: true,
			verify: formVerify.maxLength(64)
		}, {
			type: "select",
			label: "微信号类型",
			name: "wechatType",
			required: true,
			options: {
				"1": "订阅号",
				"2": "服务号"
			}
		}, {
			type: "text",
			label: "微信账号",
			name: "nickName",
			required: true,
			verify: formVerify.maxLength(256)
		}], [{
			type: "submit",
			text: "添加",
			icon: "plus-circle"
		}], {
			autosubmit: {
				url: "wechat/mcht/add.html"
			}
		})
	});
});


//微信商户查询
$(document).on("wechat-mcht-query", ".container", function() {
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
		title: "微信URL",
		index: "url",
	}, {
		title: "微信TOKEN",
		index: "token",
	}, {
		title: "公众号APPID",
		index: "appid"
	}, {
		title: "微信APPSECRET",
		index: "appsecret"
	}, {
		title: "微信号类型",
		map_func: function(data) {
			return ["非公众号", "订阅号", "服务号"][data.wechatType];
		}
	}, {
		title: "微信账号",
		index: "nickname"
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/mcht/query.html",
		table_data: "mchtList",
		"delete": {
			url: "wechat/mcht/del.html",
			data: function(data) {
				return {
					mchtId: data.mchtId
				};
			}
		}
	});
	new Widget({
		title: "账户信息列表",
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
			value: trData.mchtId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "接入微信URL",
			name: "url",
			value: trData.url,
			required: true,
			verify: formVerify.maxLength(256)
		}, {
			type: "text",
			label: "接入微信Token",
			name: "token",
			value: trData.token,
			required: true,
			verify: formVerify.maxLength(64)
		}, {
			type: "text",
			label: "公众号AppId",
			name: "appid",
			value: trData.appid,
			required: true,
			verify: formVerify.maxLength(64)
		}, {
			type: "text",
			label: "微信AppSecret",
			name: "appsecret",
			value: trData.appsecret,
			required: true,
			verify: formVerify.maxLength(64)
		}, {
			type: "select",
			label: "微信号类型",
			name: "wechatType",
			required: true,
			options: {
				"1": "订阅号",
				"2": "服务号"
			},
			value: trData.wechatType
		}, {
			type: "text",
			label: "微信账号",
			name: "nickName",
			value: trData.nickname,
			required: true,
			verify: formVerify.maxLength(256)
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		var widget = new Widget({
			title: "微信账户修改",
			template: form,
			animate: "animate6"
		});
		form.$.on("submit", function() {
			ajaxFormSubmit({
				url: "wechat/mcht/upd.html",
				data: this,
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});