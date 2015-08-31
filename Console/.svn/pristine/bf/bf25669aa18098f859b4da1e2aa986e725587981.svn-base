$(document).on("wechat-menu-syn", ".container", function() {
	var form = new QueryForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		size: "large"
	}], []);
	var widget = new Widget({
		title: "菜单查询",
		template: form,
		retractable: false
	});
	widget.$.addClass("menuList").css({
		width: "40%",
		"min-width": "440px"
	}).find("widget-content").append("<ul><li><span>菜单ID</span><span>菜单名称</span><span>相关操作</span></li></ul>");
	new Widget({
		title: "菜单预览"
	}).$.addClass("wechatView").find("widget-content").append('<div class="wechatBar"><ul></ul></div></div>');
	form.$.find(".form-input-wrapper").css("width", "100%");
	var get_menu = function() {
		$.ajax2({
			url: "wechat/menu/groupQuery.html",
			data: {
				mchtId: form.$input.mchtId.val()
			},
			method: "post",
			success: function(data) {
				if (data.respCode == "00") {
					var info = data.content.info;
					var $menuList = $$(".menuList ul").html('<li><span>菜单ID</span><span>菜单名称</span><span>相关操作</span></li>');
					if (info.length == 0) {
						jAlert("该商户没有菜单", "信息提示");
					} else {
						info.forEach(function(groupList) {
							$menuList.append('<li><span>' + groupList.id + '</span><span>' + groupList.name + '</span><span><button type="button" class="btn btn-primary menuSyn"><span class="fa fa-refresh"></span> 同步</button></span></li>');
						});
					}
				}
			}
		});
	};
	list_ajax.insertSelect(form.$input.mchtId, list_ajax.mcht).done(get_menu);
	form.$input.mchtId.on("change", get_menu);
	$(this).on("click", ".menuSyn", function() {
		ajaxFormSubmit({
			url: "wechat/menu/menuSyn.html",
			data: {
				mchtId: form.$input.mchtId.val(),
				groupId: $(this).parent().siblings("span:first").text()
			},
			success: function(menu) {
				var wechatBar = $$(".wechatBar > ul").empty();
				menu.button.forEach(function(m) {
					var san = "";
					var childMenu = "";
					if (Array.isArray(m.sub_button)) {
						m.sub_button.forEach(function(ms) {
							childMenu += "<li>" + ms.name + "</li>";
						});
						san = '<span class="fa fa-bars"></span>';
					}
					wechatBar.append('<li><ul class="child-menu">' + childMenu + '</ul>' + san + m.name + '</li>');
				});
				wechatBar.children("li")
					.css("width", 270 / wechatBar.children("li").length + "px").on("click", function() {
						$(this).children("ul").toggle(200);
					});
			}
		});
	});
});