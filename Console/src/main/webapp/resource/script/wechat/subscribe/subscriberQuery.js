//账号管理--微信用户查询
$(document).on("wechat-usr-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "商户编号",
		name: "mchtId"
	}, {
		type: "text",
		label: "用户标识",
		name: "openId"
	}], [{
		type: "submit",
		text: "查询"
	}, {
		type: "reset",
		text: "重置"
	}, {
		text: "同步",
		icon: "refresh",
		id: "sync"
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
		title: "用户头像",
		map_func: function(data) {
			return '<img class="tableImg" src=' + data.headimgurl + '>';
		},
		width: "60px"
	}, {
		title: "用户标识",
		index: "openid",
		width: "15%"
	}, {
		title: "用户昵称",
		index: "nickname"
	}, {
		title: "性别",
		map_func: function(data) {
			return ["未知", "男", "女"][data.sex];
		},
		width: "40px"
	}, {
		title: "国家",
		index: "country",
		width: "40px"
	}, {
		title: "省份",
		index: "province",
		width: "40px"
	}, {
		title: "城市",
		index: "city",
		width: "40px"
	}, {
		title: "语言",
		index: "language",
		width: "60px"
	}, {
		title: "状态",
		map_func: function(data) {
			return ["不关注", "已关注"][data.subscribeTiny];
		},
		width: "60px"
	}, {
		title: "用户关注时间",
		map_func: function(data) {
			return data.subscribeTime.parseDate();
		}
	}, {
		title: "组别",
		map_func: function(data) {
			data.groupId = data.groupId || "";
			var $select = $('<select class="dyn-groupId"><option value="0">未分组</option></select>');
			list_ajax.insertSelect($select, list_ajax.subGroup(data.mchtId)).done(function() {
				$select.val(data.groupId);
			});
			return $select;
		},
		width: "100px"
	}, {
		title: "备注",
		map_func: function(data) {
			data.remarks = data.remarks || "";
			return '<input class="dyn-remarks" type="text" value="' + data.remarks + '">';
		},
		width: "100px"
	}], {
		url: "wechat/usr/query.html",
		table_data: "usrList",
		$query_form: query_form.$
	});

	new Widget({
		title: "微信关注者列表",
		template: table
	});
	table.refresh();
	$$(".query-form #sync").on("click", function() {
		ajaxFormSubmit({
			url: "wechat/usr/syn.html",
			success: function() {
				table.refresh();
			}
		});
	});
	table.$.on("change", ".dyn-groupId", function() {
		var $this = $(this);
		var trData = $this.closest("tr").data("data");
		var groupId = $this.val();
		if (groupId != trData.groupId) {
			ajaxFormSubmit({
				url: "wechat/usr/mv.html",
				data: {
					subcribeId: trData.subcribeId,
					groupId: groupId
				},
				success: function() {
					trData.groupId = groupId;
				},
				respCode01: function() {
					$this.val(trData.groupId);
				},
				error: function() {
					$this.val(trData.groupId)
				}
			});
		}
	}).on("blur", ".dyn-remarks", function() {
		var $this = $(this);
		var trData = $this.closest("tr").data("data");
		var remark = $this.val().trim();
		if (trData.remarks != remark) {
			ajaxFormSubmit({
				data: {
					remark: remark,
					subcribeId: trData.subcribeId
				},
				url: "wechat/usr/upRemark.html",
				success: function() {
					$this.val(remark);
					trData.remarks = remark;
				},
				respCode01: function() {
					$this.val(trData.remarks);
				},
				error: function() {
					$this.val(trData.remarks);
				}
			});
		}
	});
});