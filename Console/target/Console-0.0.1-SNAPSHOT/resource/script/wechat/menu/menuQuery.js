//菜单管理--微信自定义菜单
$(document).on("wechat-menu-add", ".container", function() {
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		required: true
	}, {
		type: "text",
		label: "排序号",
		name: "orderNo",
		required: true,
		verify: {
			func: formVerify.regex(/[1-9]\d{0,3}/).func,
			info: "必须为小于10000的正整数"
		}
	}, {
		type: "text",
		label: "菜单标题",
		name: "menuName",
		required: true,
		verify: formVerify.maxLength(40)
	}, {
		type: "select",
		label: "上级菜单",
		name: "parentId",
		options: {
			"-": "-"
		},
		required: true
	}, {
		type: "select",
		label: "事件类型",
		name: "type",
		required: true,
		options: {
			"1": "Click",
			"2": "View"
		}
	}, {
		type: "text",
		label: "菜单Key值",
		name: "menuKey",
		required: true,
		verify: formVerify.maxLength(128)
	}, {
		type: "text",
		label: "网页链接",
		name: "url",
		disabled: true,
		required: true,
		verify: formVerify.maxLength(256)
	}, {
		type: "select",
		label: "组别ID",
		name: "groupId",
		required: true
	}], [{
		type: "submit",
		text: "添加",
		icon: "plus-circle"
	}], {
		autosubmit: {
			url: "wechat/menu/add.html"
		}
	});
	new Widget({
		//设定标题
		title: "微信自定义菜单添加",
		//创建表单模板
		template: form
	});

	var get_parent = function() {
		list_ajax.insertSelect(form.$input.parentId, list_ajax.menuUp(form.$input.mchtId.val(), form.$input.groupId.val())).done(function() {
			form.$input.parentId.val("-");
		});
	};
	var get_group = function() {
		list_ajax.insertSelect(form.$input.groupId, list_ajax.group(form.$input.mchtId.val())).done(get_parent);
	};
	list_ajax.insertSelect(form.$input.mchtId, list_ajax.mcht).done(get_group);
	form.$input.mchtId.on("change", get_group);
	form.$input.groupId.on("change", get_parent);

	form.$input.type.on("change", function() {
		form.$input.menuKey.prop("disabled", $(this).val() != 1);
		form.$input.url.prop("disabled", $(this).val() == 1);
	});
});

//菜单管理-微信菜单管理
$(document).on("wechat-menu-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "商户编号",
		name: "mchtId"
	}, {
		type: "text",
		label: "组别ID",
		name: "groupId"
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
		},
		width: "12%"
	}, {
		title: "组别ID",
		index: "groupId"
	}, {
		title: "菜单ID",
		index: "menuId"
	}, {
		title: "上级菜单ID",
		index: "parentId"
	}, {
		title: "排序号",
		index: "orderNo"
	}, {
		title: "菜单标题",
		index: "menuName"
	}, {
		title: "菜单KEY值",
		index: "menuKey"
	}, {
		title: "网页链接",
		index: "url"
	}, {
		title: "事件类型",
		map_func: function(data) {
			return ["", "Click", "View"][data.type];
		},
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/menu/query.html",
		table_data: "mchtList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/menu/del.html",
			data: function(data) {
				return {
					menuId: data.menuId
				};
			}
		}
	});
	new Widget({
		title: "微信菜单列表",
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
			label: "组别ID",
			name: "groupId",
			value: trData.groupId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "菜单ID",
			name: "menuId",
			value: trData.menuId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "上级菜单ID",
			name: "parentId",
			value: trData.parentId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "排序号",
			name: "orderNo",
			value: trData.orderNo,
			required: true,
			verify: formVerify.maxLength(4)
		}, {
			type: "text",
			label: "菜单标题",
			name: "menuName",
			value: trData.menuName,
			required: true,
			verify: formVerify.maxLength(40)
		}, {
			type: "select",
			label: "事件类型",
			name: "type",
			id: "type",
			options: {
				"1": "Click",
				"2": "View"
			},
			value: trData.type,
			required: true
		}, {
			type: "text",
			label: "菜单KEY值",
			name: "menuKey",
			id: "menuKey",
			value: trData.menuKey,
			required: true,
			verify: formVerify.maxLength(128)
		}, {
			type: "text",
			label: "网页链接",
			name: "url",
			id: "url",
			value: trData.url,
			required: true,
			verify: formVerify.maxLength(256)
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		var widget = new Widget({
			title: "微信菜单修改",
			template: form,
			animate: "animate6"
		});
		form.$input.type.on("change", function() {
			form.$input.menuKey.prop("disabled", $(this).val() != 1);
			form.$input.url.prop("disabled", $(this).val() == 1);
		}).trigger("change");
		form.$.on("submit", function() {
			ajaxFormSubmit({
				data: this,
				url: "wechat/menu/upd.html",
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});