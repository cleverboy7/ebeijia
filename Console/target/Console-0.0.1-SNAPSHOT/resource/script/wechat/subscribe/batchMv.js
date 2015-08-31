//关注者管理--批量修改分组
$(document).on("wechat-usr-batch", ".container", function() {
	list_ajax.removeCache("subGroup");
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
	}, {
		type: "select",
		label: "选择组别",
		name: "groupId",
		options: {
			"0": "未分组"
		}
	}, {
		type: "dual-select",
		label: "分组设置",
		size: "full",
		paginate: true
	}], [{
		type: "submit",
		text: "修改",
		icon: "pencil-square"
	}]);
	new Widget({
		title: "分组管理",
		template: form
	});
	var dual_select = form.$.find("dual-select").data("dual-select");

	var original_subscriber = [];
	var page_size = 20;
	var page_index = 1;
	var total_pages = 0;
	var add_user = function(user) {
		return {
			html: $("<div>").append($('<img>')
					.attr("src", user.headimgurl)
					.css({
						width: "20px",
						height: "20px",
						"margin-right": "20px",
						"border-radius": "50%",
						"vertical-align": "middle"
					}))
				.append($("<span>").text(user.nickname)),
			value: user.openid
		};
	};
	var refresh_data = function(new_group) {
		$.ajax2({
			url: "wechat/usr/queryBatch.html",
			method: "post",
			data: {
				mchtId: form.$input.mchtId.val(),
				groupId: form.$input.groupId.val(),
				aoData: DynamicTable.prototype.aoData.call({
					page_size: page_size,
					page_index: page_index
				})
			},
			success: function(data) {
				total_pages = Math.floor((data.content.total - 1) / page_size) + 1;
				$$(".prev-page")
					.toggleClass("btn-primary", page_index > 1)
					.prop("disabled", page_index <= 1);
				$$(".next-page")
					.toggleClass("btn-primary", page_index < total_pages)
					.prop("disabled", page_index >= total_pages)
				dual_select.clear_unselected();
				dual_select.add_to_unselected(data.content.usrList.map(add_user));
				if (new_group) {
					dual_select.clear_selected();
					dual_select.add_to_selected(data.content.usrInList.map(add_user));
					original_subscriber = data.content.usrInList.map(function(user) {
						return user.openid;
					});
					if (form.$input.groupId.val() == "0") {
						dual_select.$selected.children().addClass("disabled");
					}
				}
			}
		});
	};
	form.$input.groupId.on("change", function() {
		page_index = 1;
		refresh_data(true);
	});
	form.$input.mchtId.on("change", function() {
		list_ajax.insertSelect(form.$input.groupId, list_ajax.subGroup($(this).val())).done(function() {
			form.$input.groupId.trigger("change");
		});
	});
	list_ajax.insertSelect(form.$input.mchtId, list_ajax.mcht).done(function() {
		form.$input.mchtId.trigger("change");
	});

	$$(".prev-page").on("click", function() {
		--page_index;
		refresh_data();
	});
	$$(".next-page").on("click", function() {
		++page_index;
		refresh_data();
	});

	$$("form").on("submit", function() {
		var list = dual_select.$selected.children().map(function() {
			return $(this).data("value");
		}).get();
		var subscriber_list = list.filter(function(openid) {
			return original_subscriber.indexOf(openid) == -1;
		});
		var out_list = original_subscriber.filter(function(openid) {
			return list.indexOf(openid) == -1;
		});
		if (!subscriber_list.length && !out_list.length) {
			jAlert("分组情况没有变化", "信息提示");
			return;
		}
		ajaxFormSubmit({
			url: "wechat/usr/batchMv.html",
			data: {
				mchtId: form.$input.mchtId.val(),
				openList: subscriber_list.toString() || void 0,
				outList: out_list.toString() || void 0,
				groupId: form.$input.groupId.val(),
			},
			success: function(data) {
				subscriber_list = dual_select.$selected.children().removeClass("active").addClass("disabled").map(function() {
					return $(this).attr("openid");
				}).get();
				if (form.$input.groupId.val() == "0") {
					dual_select.$selected.children().addClass("disabled");
				}
			}
		});
	});
});