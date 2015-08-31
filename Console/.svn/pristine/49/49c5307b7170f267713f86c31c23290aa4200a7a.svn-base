//红包-红包发放
$(document).on("wechat-redPacket-add", ".container", function() {
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		optionsAJAX: list_ajax.mcht,
		required: true
	}, {
		type: "text",
		label: "付款金额",
		name: "totalAmount",
		required: true,
	}, {
		type: "text",
		label: "最小红包金额",
		name: "minValue",
		required: true,
		verify: formVerify.number(0, 4000)
	}, {
		type: "text",
		label: "最大红包金额",
		name: "maxValue",
		required: true,
		verify: formVerify.number(0, 4000)
	}, {
		type: "text",
		label: "红包发放总人数",
		name: "totalNum",
		required: true,
		verify: formVerify.num
	}, {
		type: "text",
		label: "红包祝福语",
		name: "wishing",
		required: true,
		verify: formVerify.maxLength(32)
	}, {
		type: "text",
		label: "活动名称",
		name: "actName",
		required: true,
		verify: formVerify.maxLength(32)
	}, {
		type: "text",
		label: "备注",
		name: "remark",
		verify: formVerify.maxLength(256)
	}], [{
		type: "submit",
		text: "添加",
		icon: "plus-circle"
	}]);
	new Widget({
		title: "新增红包",
		template: form
	});

	form.$.on("submit", function() {
		if (!formVerify({
			obj: $(this).find(".required")
		}) || !formVerify({
			obj: form.$input_wrapper.maxValue,
			func: function() {
				return form.$input.minValue.val() - form.$input.maxValue.val() <= 0;
			},
			info: "最大值不能小于最小值"
		}) || !formVerify({
			obj: form.$input_wrapper.minValue,
			func: function() {
				return form.$input.minValue.val() * form.$input.totalNum.val() <= form.$input.totalAmount.val()
			},
			info: "总数大于总金额！"
		}) || !formVerify({
			obj: form.$input_wrapper.maxValue,
			func: function() {
				return form.$input.maxValue.val() * form.$input.totalNum.val() >= form.$input.totalAmount.val()
			},
			info: "总数大于总金额！"
		})) return;
		var data = {};
		var $inputs = $(this).find("[name]:not(:disabled)");
		$inputs.each(function() {
			data[$(this).attr("name")] = $(this).val();
		});
		data.sendName = form.$input.mchtId.children(":selected").text().slice(16);
		ajaxFormSubmit({
			url: "wechat/redpacket/add.html",
			data: data
		});
	});
});


//红包-红包管理
$(document).on("wechat-redPacket-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "商户编号",
		name: "mchtId",
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
		title: "付款金额(分)",
		index: "totalAmount",
	}, {
		title: "最小红包金额(分)",
		index: "minValue",
	}, {
		title: "最大红包金额(分)",
		index: "maxValue"
	}, {
		title: "红包发放总人数",
		index: "totalNum"
	}, {
		title: "祝福语",
		index: "wishing"
	}, {
		title: "活动名称",
		index: "actName"
	}, {
		title: "状态",
		map_func: function(data) {
			return ["未发放", "已发放"][data.status];
		},
		width: "50px"
	}, {
		title: "备注",
		index: "remark"
	}, {
		title: "相关操作",
		width: "200px",
		map_func: IPY_templates["table-button-money"] + " " + IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/redpacket/query.html",
		table_data: "redpacketList",
		"delete": {
			url: "wechat/redpacket/del.html",
			data: function(data) {
				return {
					mchtId: data.id.mchtId,
					nonceStr: data.id.nonceStr
				};
			}
		}
	});
	new Widget({
		title: "红包信息列表",
		template: table
	});
	table.refresh();
	table.$.on("click", ".btn.btn-money", function() {
		var trData = $(this).closest("tr").data("data");
		if (trData.status == 0) {
			trData.status = 1;
			$(this).parent("td").siblings('td:nth-child(8)').text("已发放");
			jAlert("发放成功~", "信息提示");
		} else {
			$.jGrowl("该红包已经发放！");
		}

	}).on("modify", "tr", function() {
		var trData = $(this).data("data");
		if (trData.status != 0) {
			$.jGrowl("该红包已经发放！");
			return;
		}
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
			label: "付款金额(分)",
			name: "totalAmount",
			value: trData.totalAmount,
			required: true
		}, {
			type: "text",
			label: "最小红包金额(分)",
			name: "minValue",
			required: true,
			value: trData.minValue,
			verify: formVerify.number(0, 4000)
		}, {
			type: "text",
			label: "最大红包金额(分)",
			name: "maxValue",
			required: true,
			value: trData.maxValue,
			verify: formVerify.number(0, 4000)
		}, {
			type: "text",
			label: "红包发放总人数",
			name: "totalNum",
			required: true,
			value: trData.totalNum,
			verify: formVerify.num
		}, {
			type: "text",
			label: "红包祝福语",
			name: "wishing",
			required: true,
			value: trData.wishing,
			verify: formVerify.maxLength(32)
		}, {
			type: "text",
			label: "活动名称",
			name: "actName",
			required: true,
			value: trData.actName,
			verify: formVerify.maxLength(20)
		}, {
			type: "text",
			label: "备注",
			value: trData.remark,
			name: "remark",
			verify: formVerify.maxLength(256)
		}, {
			type: "text",
			readonly: true,
			name: "sendName",
			value: trData.sendName,
			display: "none"
		}, {
			type: "text",
			readonly: true,
			name: "nonceStr",
			value: trData.id.nonceStr,
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
		form.$.on("submit", function() {
			if (!formVerify({
				obj: $(this).find(".required")
			}) || !formVerify({
				obj: form.$input_wrapper.maxValue,
				func: function() {
					return form.$input.minValue.val() - form.$input.maxValue.val() <= 0
				},
				info: "最大值不能小于最小值"
			}) || !formVerify({
				obj: form.$input_wrapper.minValue,
				func: function() {
					return form.$input.minValue.val() * form.$input.totalNum.val() <= form.$input.totalAmount.val()
				},
				info: "总数大于总金额！"
			}) || !formVerify({
				obj: form.$input_wrapper.maxValue,
				func: function() {
					return form.$input.maxValue.val() * form.$input.totalNum.val() >= form.$input.totalAmount.val()
				},
				info: "总数大于总金额！"
			})) return;
			ajaxFormSubmit({
				url: "wechat/redpacket/upd.html",
				data: this,
				success: function() {
					widget.close();
					table.refresh();
				}
			})
		});
	});
});