//活动--新增活动
$(document).on("wechat-activity-add", ".container", function() {
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		optionsAJAX: list_ajax.mcht,
		required: true
	}, {
		type: "text",
		label: "活动名称",
		name: "actName",
		required: true,
		verify: formVerify.maxLength(32)
	}, {
		type: "text",
		label: "活动描述",
		name: "dsc",
		required: true,
		verify: formVerify.maxLength(128)
	}, {
		type: "select",
		label: "活动类型",
		name: "actType",
		required: true,
		options: {
			"0": "大转盘",
			"1": "刮刮卡",
			"2": "老虎机"
		}
	}, {
		type: "select",
		label: "每日标识",
		name: "dayFlag",
		required: true,
		options: {
			"0": "否",
			"1": "是"
		}
	}, {
		type: "text",
		label: "抽奖次数",
		name: "lotNum",
		required: true,
		verify: formVerify.number(0, 100)
	}, {
		type: "text",
		label: "活动开始日期",
		name: "beginDate",
		required: true,
		verify: formVerify.date
	}, {
		type: "text",
		label: "活动结束日期",
		name: "endDate",
		required: true,
		verify: formVerify.date
	}, {
		type: "select",
		label: "模板ID",
		name: "modId",
		required: true
	}, {
		type: "text",
		label: "活动链接",
		name: "url",
		readonly: true,
		required: true,
		size: "two-column",
		verify: formVerify.maxLength(256)
	}], [{
		type: "submit",
		text: "添加",
		icon: "plus-circle"
	}]);
	new Widget({
		title: "新增活动",
		template: form
	});

	$.combine(form.$input.beginDate, form.$input.endDate).datepicker({
		minDate: new Date()
	});
	form.$input.modId.on("change", function() {
		form.$input.url.val($(this).children(":selected").data("url"));
	});
	form.$input.actType.on("change", function() {
		list_ajax.insertSelect(form.$input.modId, list_ajax.mod(form.$input.mchtId.val(), form.$input.actType.val())).done(function(arg) {
			for (var i in arg) {
				form.$input.modId.children("[value='" + arg[i].value + "']").data("url", arg[i].url);
			}
			form.$input.modId.trigger("change");
		});
	}).trigger("change");
	form.$.on("submit", function() {
		var beginDate = form.$input.beginDate.val().replace(/-/g, "");
		var endDate = form.$input.endDate.val().replace(/-/g, "");
		if (!formVerify({
			obj: form.$.find(".required")
		}) || !formVerify({
			obj: form.$input_wrapper.beginDate,
			func: function() {
				return beginDate - endDate <= 0
			},
			info: "不能在结束日期之后！"
		})) return;
		ajaxFormSubmit({
			url: "wechat/act/add.html",
			data: this,
			success: function() {
				form.$input.actType.trigger("change");
			}
		});
	});
});

//活动--活动管理
$(document).on("wechat-activity-query", ".container", function() {
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
		title: "活动ID",
		index: "actId",
	}, {
		title: "活动名称",
		index: "actName",
	}, {
		title: "活动描述",
		index: "dsc"
	}, {
		title: "活动类型",
		map_func: function(data) {
			return ["大转盘", "刮刮卡", "老虎机"][data.actType];
		},
		width: "60px"
	}, {
		title: "活动模板ID",
		index: "modId",
		width: "50px"
	}, {
		title: "每日标识",
		map_func: function(data) {
			return ["否", "是"][data.dayFlag];
		},
		width: "60px"
	}, {
		title: "抽奖次数",
		index: "lotNum",
		width: "60px"
	}, {
		title: "活动开始日期",
		map_func: function(data) {
			return data.beginDate.parseDate();
		}
	}, {
		title: "活动结束日期",
		map_func: function(data) {
			return data.endDate.parseDate();
		}
	}, {
		title: "状态",
		map_func: function(data) {
			return ["未开启", "开启"][data.status];
		},
		width: "60px"
	}, {
		title: "活动链接",
		index: "url",
		width: "100px"
	}, {
		title: "相关操作",
		width: "200px",
		map_func: function(data) {
			if (data.status == 0) {
				return IPY_templates["table-button-play"] + " " + IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"];
			} else {
				return IPY_templates["table-button-stop"];
			}
		}
	}], {
		url: "wechat/act/query.html",
		table_data: "actList",
		"delete": {
			url: "wechat/act/del.html",
			data: function(data) {
				return {
					mchtId: data.mchtId,
					actId: data.actId
				};
			}
		}
	});
	new Widget({
		title: "活动信息列表",
		template: table
	});
	table.refresh();
	table.$.on("click", ".btn.play", function() {
		jConfirm("是否开启此活动？", "活动开启提醒", function() {
			var trData = $(this).closest("tr").data("data");
			ajaxFormSubmit({
				url: "wechat/act/sta.html",
				data: {
					actId: trData.actId,
					status: trData.status
				},
				success: function() {
					table.refresh();
				}
			})
		}.bind(this))
	}).on("click", ".btn.stop", function() {
		jConfirm("是否关闭此活动？", "活动关闭提醒", function() {
			var trData = $(this).closest("tr").data("data");
			ajaxFormSubmit({
				url: "wechat/act/sta.html",
				data: {
					actId: trData.actId,
					status: trData.status
				},
				success: function() {
					table.refresh();
				}
			})
		}.bind(this));
	}).on("modify", "tr", function() {
		var trData = $(this).data("data");
		if (trData.status == 1) return;
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
			label: "活动ID",
			name: "actId",
			value: trData.actId,
			readonly: true,
			required: true
		}, {
			type: "text",
			label: "活动名称",
			name: "actName",
			required: true,
			value: trData.actName,
			verify: formVerify.maxLength(32)
		}, {
			type: "text",
			label: "活动描述",
			name: "dsc",
			required: true,
			value: trData.dsc,
			verify: formVerify.maxLength(128)
		}, {
			type: "text",
			label: "抽奖次数",
			name: "lotNum",
			value: trData.lotNum,
			required: true,
			verify: formVerify.number(0, 100)
		}, {
			type: "select",
			label: "活动类型",
			name: "actType",
			value: trData.actType,
			required: true,
			options: {
				"0": "大转盘",
				"1": "刮刮卡",
				"2": "老虎机"
			}
		}, {
			type: "select",
			label: "模板ID",
			name: "modId",
			required: true
		}, {
			type: "select",
			label: "每日标识",
			value: trData.dayFlag,
			name: "dayFlag",
			required: true,
			options: {
				"0": "否",
				"1": "是"
			}
		}, {
			type: "text",
			label: "活动开始日期",
			name: "beginDate",
			value: trData.beginDate,
			required: true
		}, {
			type: "text",
			label: "活动结束日期",
			name: "endDate",
			value: trData.endDate,
			required: true
		}, {
			type: "text",
			label: "活动链接",
			name: "url",
			value: trData.url,
			required: true,
			readonly: true,
			size: "two-column",
			verify: formVerify.maxLength(256)
		}, {
            type: "text",
            label: "活动状态",
            name: "status",
            value: trData.status,
            required: true,
            readonly: true,
            display: "none"
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
		(function() {
			var begin = form.$input.beginDate, end = form.$input.endDate;
			begin.val(begin.val().slice(0, 4) + "-" + begin.val().slice(4, 6) + "-" + begin.val().slice(6));
			end.val(end.val().slice(0, 4) + "-" + end.val().slice(4, 6) + "-" + end.val().slice(6));
		})();
		$.combine(form.$input.beginDate, form.$input.endDate).datepicker({
			minDate: new Date()
		});
		form.$input.modId.on("change", function() {
			form.$input.url.val($(this).children(":selected").data("url"));
		});
		var mark = 0;
		form.$input.actType.on("change", function() {
			var $modId = form.$input.modId;
			list_ajax.insertSelect($modId, list_ajax.mod(trData.mchtId, form.$input.actType.val())).done(function(arg) {
				for (var i in arg) {
					$modId.children("[value='" + arg[i].value + "']").data("url", arg[i].url);
				};
				if (mark == 0) {
					$modId.val(trData.modId);
					mark += 1;
				}
				$modId.trigger("change");
			});
		}).trigger("change");
		form.$.on("submit", function() {
			var beginDate = form.$input.beginDate.val().replace(/-/g, "");
			var endDate = form.$input.endDate.val().replace(/-/g, "");
			if (!formVerify({
				obj: form.$.find(".required")
			}) || !formVerify({
				obj: form.$input_wrapper.beginDate,
				func: function() {
					return beginDate - endDate <= 0
				},
				info: "不能在结束日期之后！"
			})) return;
			ajaxFormSubmit({
				url: "wechat/act/upd.html",
				data: this,
				success: function() {
					widget.close();
					table.refresh();
				}
			});
		});
	});
});