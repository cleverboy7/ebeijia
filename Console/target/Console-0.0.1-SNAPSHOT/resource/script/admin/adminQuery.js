//管理员管理--管理员添加
$(document).on("admin-add", ".container", function() {
	new Widget({
		title: "管理员添加",
		template: new SimpleForm([{
			type: "text",
			label: "管理员名称",
			name: "adminName",
			required: true,
			verify: formVerify.stringLength(4, 32)
		}, {
			type: "text",
			label: "管理员描述",
			name: "adminDsc",
			verify: formVerify.maxLength(128)
		}, {
			type: "select",
			label: "角色",
			name: "roleId",
			optionsAJAX: list_ajax.role,
			required: true
		}, {
			type: "password",
			label: "管理员密码",
			name: "adminPwd",
			required: true,
			verify: formVerify.password
		}, {
			type: "file",
			label: "上传头像",
			name: "file",
			filetype: "image"
		}], [{
			type: "submit",
			text: "添加",
			icon: "plus-circle"
		}], {
			autosubmit: {
				url: "wechat/admin/add.html"
			}
		})
	});
});

//管理员管理--管理员查询
$(document).on("admin-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "管理员Id",
		name: "adminId"
	}, {
		type: "text",
		label: "管理员名称",
		name: "adminName"
	}, {
		type: "text",
		label: "管理员状态",
		name: "adminStats"
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
		title: "头像",
		map_func: function(data) {
			return '<img class="tableImg" src=' + data[0] + '>';
		}
	}, {
		title: "管理员ID",
		index: 1
	}, {
		title: "管理员名称",
		index: 2
	}, {
		title: "角色",
		map_func: function(data) {
			var $div = $("<span>").text(data[3]);
			list_ajax.replaceText($div, data[3], list_ajax.role);
			return $div;
		}
	}, {
		title: "管理员描述",
		index: 4
	}, {
		title: "管理员状态",
		map_func: function(data) {
			return ["正常", "锁定"][data[5]];
		}
	}], {
		url: "wechat/admin/query.html",
		table_data: "adminList",
		$query_form: query_form.$
	});
	new Widget({
		title: "管理员列表",
		template: table
	});
	table.refresh();
});

//管理员管理--管理员修改
$(document).on("admin-upd", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "管理员Id",
		name: "adminId"
	}, {
		type: "text",
		label: "管理员名称",
		name: "adminName"
	}, {
		type: "text",
		label: "管理员状态",
		name: "adminStat"
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
		title: "头像",
		map_func: function(data) {
			return '<img class="tableImg" src=' + data[0] + '>';
		}
	}, {
		title: "管理员ID",
		index: 1
	}, {
		title: "管理员名称",
		index: 2
	}, {
		title: "角色",
		map_func: function(data) {
			var $div = $("<span>").text(data[3]);
			list_ajax.replaceText($div, data[3], list_ajax.role);
			return $div;
		}
	}, {
		title: "管理员描述",
		index: 4
	}, {
		title: "管理员状态",
		map_func: function(data) {
			return ["正常", "锁定"][data[5]];
		}
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/admin/query.html",
		table_data: "adminList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/admin/del.html",
			data: function(data) {
				return {
					adminId: data[1]
				};
			}
		}
	});
	new Widget({
		title: "管理员列表",
		template: table
	});
	table.refresh();
	table.$.on("modify", "tr", function() {
		var trData = $(this).data("data");
		lockScreen();
		var form = new PopupForm([{
			type: "text",
			label: "管理员ID",
			name: "adminId",
			readonly: true,
			value: trData[1],
			required: true
		}, {
			type: "text",
			label: "管理员名称",
			name: "adminName",
			value: trData[2],
			required: true,
			verify: formVerify.stringLength(4, 32)
		}, {
			type: "text",
			label: "管理员描述",
			name: "dsc",
			value: trData[4],
			verify: formVerify.maxLength(128)
		}, {
			type: "select",
			label: "角色",
			name: "roleId",
			value: trData[3],
			optionsAJAX: list_ajax.role,
			required: true
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		var widget = new Widget({
			title: "管理员修改",
			template: form,
			animate: "animate6"
		});
		form.$.on("submit", function() {
			ajaxFormSubmit({
				data: this,
				url: "wechat/admin/upd.html",
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});