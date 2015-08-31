//角色管理--角色添加
$(document).on("role-add", ".container", function() {
	var form = new SimpleForm([{
		type: "text",
		label: "角色名称",
		name: "name",
		size: "place-full",
		required: true,
		verify: formVerify.maxLength(32)
	}, {
		type: "text",
		label: "角色描述",
		name: "dsc",
		size: "place-full",
		verify: formVerify.maxLength(64)
	}, {
		type: "dual-select",
		label: "权限设置",
		name: "roleList",
		size: "full",
		required: true
	}], [{
		type: "submit",
		text: "添加",
		icon: "plus-circle"
	}], {
		autosubmit: {
			url: "wechat/role/add.html",
			success: function() {
				list_ajax.removeCache("role");
				form.$input.roleList.find("> .button-group > button:last-child").trigger("click");
			}
		}
	});
	new Widget({
		title: "角色添加",
		template: form
	});
	var dual_select = form.$input.roleList.data("dual-select");

	$.ajax2({
		url: "list/funcInf.html",
		method: "post",
		success: function(list) {
			dual_select.add_to_unselected(list.info.map(function(item) {
				return {
					html: "<span>" + item.key + "</span>",
					value: item.value
				};
			}));
		}
	});
});

//角色管理--角色查询
$(document).on("role-query", ".container", function() {
	list_ajax.removeCache("role");
	var query_form = new QueryForm([{
		type: "text",
		label: "角色ID",
		name: "roleId"
	}, {
		type: "text",
		label: "角色名称",
		name: "roleName"
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
		title: "角色ID",
		index: "roleId"
	}, {
		title: "角色名称",
		index: "roleName"
	}, {
		title: "角色描述",
		index: "dsc"
	}], {
		url: "wechat/role/query.html",
		table_data: "roleList",
		$query_form: query_form.$
	});
	new Widget({
		title: "角色列表",
		template: table
	});
	table.refresh();
});

//角色管理--角色维护
$(document).on("role-upd", ".container", function() {
	list_ajax.removeCache("role");
	var query_form = new QueryForm([{
		type: "text",
		label: "角色ID",
		name: "roleId"
	}, {
		type: "text",
		label: "角色名称",
		name: "roleName"
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
		title: "角色ID",
		index: "roleId"
	}, {
		title: "角色名称",
		index: "roleName"
	}, {
		title: "角色描述",
		index: "dsc"
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/role/query.html",
		table_data: "roleList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/role/del.html",
			data: function(data) {
				return {
					roleId: data.roleId
				};
			}
		}
	});
	new Widget({
		title: "角色列表",
		template: table
	});
	table.refresh();
	table.$.on("modify", "tr", function() {
		var trData = $(this).data("data");
		lockScreen();
		var form = new PopupForm([{
			type: "text",
			label: "角色ID",
			name: "roleId",
			value: trData.roleId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "角色名称",
			name: "name",
			required: true,
			value: trData.roleName,
			verify: formVerify.maxLength(32)
		}, {
			type: "text",
			label: "角色描述",
			name: "dsc",
			value: trData.dsc,
			verify: formVerify.maxLength(64)
		}, {
			type: "dual-select",
			label: "修改权限",
			name: "roleList",
			size: "full",
			required: true
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		var widget = new Widget({
			title: "角色修改",
			template: form,
			animate: "animate6"
		});
		var dual_select = form.$input.roleList.data("dual-select");
		$.ajax2({
			url: "wechat/role/queryDsc.html",
			method: "post",
			data: {
				roleId: trData.roleId
			},
			success: function(list) {
				dual_select.add_to_unselected(list.content.noexis.map(function(item) {
					return {
						html: "<span>" + item[0] + "-" + item[1] + "</span>",
						value: item[0]
					};
				}));
				dual_select.add_to_selected(list.content.exis.map(function(item) {
					return {
						html: "<span>" + item[0] + "-" + item[1] + "</span>",
						value: item[0]
					};
				}));
			}
		});

		form.$.on("submit", function() {
			ajaxFormSubmit({
				data: this,
				url: "wechat/role/upd.html",
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});