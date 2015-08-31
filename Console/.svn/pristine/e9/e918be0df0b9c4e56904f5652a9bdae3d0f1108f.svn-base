//图文管理--图文新增
$(document).on("wechat-news-add", ".container", function() {
	var form = new QueryForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		id: "mchtId",
		size: "large",
		required: true
	}, {
		type: "text",
		label: "名称",
		name: "name",
		required: true
	}, {
		type: "text",
		label: "描述",
		name: "dsc"
	}]);
	var widget = new Widget({
		title: "商户编号选择",
		template: form,
		retractable: false
	});
	widget.$.width(1050);

	var rich_edit_form = new RichEditForm([{
		type: "text",
		label: "标题",
		name: "title",
		required: true
	}, {
		type: "select",
		label: "封面",
		name: "show_cover_pic",
		required: true,
		options: {
			"0": "隐藏",
			"1": "显示"
		}
	}, {
		type: "text",
		label: "图文原地址",
		name: "content_source_url",
		required: true
	}, {
		type: "text",
		label: "作者",
		name: "author",
		required: true
	}, {
		type: "text",
		label: "单图文摘要",
		name: "digest"
	}, {
		type: "select",
		label: "封面图片",
		name: "thumb_media_id",
		size: "two-column"
	}, {
		type: "textarea",
		label: "具体内容",
		name: "content",
		size: "full",
		classes: "kindeditor",
		attributes: {
			rows: "15"
		}
	}], [{
		type: "button",
		text: "保存",
		icon: "save"
	}, {
		type: "submit",
		text: "上传",
		icon: "upload"
	}, {
		type: "button",
		text: "删除",
		icon: "trash-o",
		classes: "del"
	}]);
	//构造图文编辑部件
	var rich_edit_widget = new Widget({
		title: "图文上传",
		template: rich_edit_form
	});
	//设定图文编辑部件的宽度，并为其加上图片预览和富文本编辑器
	rich_edit_form.$.width(740);
	rich_edit_widget.$.width(1050).find("widget-content").append('<img class="digest-image">');
	rich_edit_form.$.find(".kindeditor").each(function() {
		//创建富文本编辑器，所用插件为KindEditor
		$(this).data("editor", KindEditor.create($(this), {
			items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'table', 'hr', 'emoticons','image', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink'],
			resizeType: 1,
			allowImageUpload: false
		}));
	});

	//获取商户编号
	list_ajax.insertSelect($$("#mchtId"), list_ajax.mcht).done(getMedia);
	//商户编号改变时获取该商户的媒体ID
	$$("#mchtId").on("change", getMedia);
	//创建result数组用来存储图文数据
	var result = [];
	//保存图文的方法
	var save_result = function(index) {
		//当该图文未定义时将一个空对象赋值给该图文
		if (!result[index]) result[index] = {};
		//把除了富文本编辑器外其他输入框的值加入到result中
		rich_edit_form.$.find("[name]:not(.kindeditor)").each(function() {
			result[index][$(this).attr("name")] = ($(this).val() || "").trim();
		});
		//将富文本编辑器的值加入到result
		rich_edit_form.$.find(".kindeditor").each(function() {
			result[index][$(this).attr("name")] = $(this).data("editor").html();
		});

		//当图文总数大于1时令单图文摘要不能填写
		if (rich_edit_widget.$.find(".form-nav li:visible").length > 1) {
			rich_edit_form.$.find("input[name='digest']").prop('disabled', true);
		}

		//为图文列表加上保存的图文的标题，若无标题则为默认标题
		var title = result[index].title || "无标题"
		$$(".form-nav li:nth-child(" + (index + 1) + ") a").text(title);
	};
	//读取图文的方法
	var load_result = function(index) {
		//重置图文部件的表单
		rich_edit_form.$.trigger("reset");
		//重置富文本编辑器
		rich_edit_form.$.find(".kindeditor").each(function() {
			$(this).data("editor").html("");
		});
		//循环读取result内存储的数据并将数据填入表单内
		for (var name in result[index]) {
			var $input = rich_edit_form.$.find('[name="' + name + '"]');
			if ($input.hasClass("kindeditor")) {
				$input.data("editor").html(result[index][name]);
			} else {
				$input.val(result[index][name]);
			}
		}
	}

	//新建一个图文标题列表并加入到图文编辑部件中，每个列都有一个data("index")属性来记录它的索引
	var $nav = $('<ul class="form-nav">').prependTo(rich_edit_widget.$.find("widget-content"));
	Array.prototype.forEach.call("一二三四", function(number, index) {
		$('<li><a href="#">新图文</a></li>').data("index", index).appendTo($nav);
		result.push({});
	});
	//给列表的第一列加上类，并为列表内的a元素加上点击事件用以切换、保存图文及加载图文
	$nav.find("li:first-child").addClass("current")
		.siblings('li').css("display", "none").parent()
		.on("click", "a", function(event, type) {
			if (!$(this).parent().hasClass("current")) {
				var $parent = $(this).parent().addClass("current");
				var $current = $parent.siblings(".current").removeClass("current");
				var index = $current.data("index");
				save_result($current.data("index"));
				load_result($parent.data("index"));
				//当该图文是被删除的图文时，不提示用户
				if (type != "del") {
					//当当前图文未填写完整就点其他图文时，提示用户
					if (!result[index].title || !result[index].content_source_url || !result[index].author) {
						$.jGrowl("该图文存在内容未填写完整！直接上传会失去相关内容，请注意！");
					}
				}
			}
		})

	//为container内的button绑定点击事件
	$$(".container-inner").on("click", "button", function() {
		//当button种类为submit时，保存当前图文、筛选掉不符合规格的图文并将数据AJAX提交到后台
		if ($(this).attr("type") == "submit") {
			save_result($$(".current").data("index"));
			result = result.filter(function(a) {
				return a.title && a.author && a.content_source_url
			});
			var data = {
				mchtId: $$("#mchtId").val(),
				name: $$("input[name='name']").val(),
				dsc: $$("input[name='dsc']").val(),
				articles: JSON.stringify({
					articles: result
				})
			};
			ajaxFormSubmit({
				verify: {
					obj: $$(".required")
				},
				url: "wechat/media/upNews.html",
				data: data,
				success: function() {
					$("form").trigger("reset");
					rich_edit_form.$.find(".kindeditor").each(function() {
						$(this).data("editor").html("");
					});
					result = [];
				},
				error: function() {
					popupClose();
					$(".loadingGif").remove();
				}
			});
			//当点击删除时删除掉该图文的标题列、result中的数据，并点击切换到第一个图文
		} else if ($(this).hasClass("del")) {
			var index = $$(".current").data("index"),
				delItem;
			//如果删除的是第一个图文且无其他图文直接清空表单
			if (index == 0 && rich_edit_widget.$.find(".form-nav li:visible").length == 1) {
				rich_edit_form.$.trigger("reset");
				rich_edit_form.$.find(".kindeditor").each(function() {
					$(this).data("editor").html("");
				});
			} else {
				//删除时自动点击其他图文的第一项，并传"del"参数过去防止提示用户删掉的图文不完整
				if (index != 0) {
					delItem = $(".form-nav li:first-child");
				} else {
					delItem = $(".form-nav li:nth-child(2)");
				}
				delItem.find("a").trigger('click', ["del"])
					.parent().siblings("li:nth-child(" + (index + 1) + ")")
				//被删除图文后面的图文索引减去一
				.nextAll().each(function() {
					$(this).data("index", $(this).data("index") - 1);
				})
					.end().remove();
				//新建一个隐藏的图文列到列尾
				$('<li><a href="#">新图文</a></li>').data("index", 3).css("display", "none").appendTo($$(".form-nav"));
				result[index] = {};
				//筛选掉被删除的图文
				result = result.filter(function(a) {
					return a.title
				});
			}
			//当图文总数为1时令单图文摘要可填写
			if (rich_edit_widget.$.find(".form-nav li:visible").length < 2) {
				rich_edit_form.$.find("input[name='digest']").prop('disabled', false);
			}
			//点击保存
		} else {
			//验证表单
			if (!formVerify({
				obj: rich_edit_form.$.find(".required")
			})) {
				return;
			}
			var index = $$(".current").data("index")
			//当保存的是最后一项图文，不需要切到下一个图文。其他则切换到下个图文
			if (index != 3) {
				$nav.find("li:nth-child(" + (index + 2) + ")").css("display", "block").find("a").click();
			} else {
				save_result(index);
			}
		}
	});

	//获取media的方法
	function getMedia() {
		list_ajax.insertSelect($$('select[name="thumb_media_id"]'), list_ajax.media($$("#mchtId").val(), "image"));
	}
});



//图文管理--图文查询
$(document).on("wechat-news-query", ".container", function() {
	var $container = $(this);
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
		"title": "商户编号",
		"index": "mchtId",
		"width": "12%"
	}, {
		"title": "媒体ID",
		"index": "media"
	}, {
		"title": "名称",
		"index": "name"
	}, {
		"title": "描述",
		"index": "dsc"
	}, {
		"title": "种类",
		"index": "type"
	}, {
		"title": "媒体种类",
		map_func: function(data) {
			return ["临时", "永久"][data.mediaType];
		}
	}, {
		"title": "上传时间",
		map_func: function(data) {
			return data.createTime.parseDate();
		}
	}, {
		title: "相关操作",
		width: "134px",
		map_func: IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/media/newsQuery.html",
		table_data: "newsList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/media/del.html",
			data: function(data) {
				return {
					mchtId: trData.mchtId,
					media: trData.media
				};
			}
		}
	});

	new Widget({
		title: "微信图文列表",
		template: table
	});
	table.refresh();
	$$(".container-inner").on("click", ".sizeBtn", function() {
		$$("widget:gt(1)").remove();
	})
	table.$.on("modify", "tr", function() {
		var trData = $(this).data("data");
		var result = [];
		$.ajax2({
			url: "wechat/media/newsGet.html",
			data: {
				mchtId: trData.mchtId,
				media: trData.media
			},
			type: "post",
			success: function(data) {
				if (data.respCode == "01") {
					jAlert(data.errorInfo, "错误信息");
					return;
				}
				data = JSON.parse(data.content);
				result = data.news_item;
				$$("widget:gt(1)").remove();
				var form = new QueryForm([{
					type: "text",
					label: "商户编号",
					name: "m-mchtId",
					id: "m-mchtId",
					size: "large",
					readonly: true
				}, {
					type: "text",
					label: "名称",
					name: "name",
					id: "m-name",
					required: true
				}, {
					type: "text",
					label: "描述",
					name: "dsc",
					id: "m-dsc"
				}]);
				var widget = new Widget({
					title: "商户编号选择",
					template: form,
					retractable: false,
					animate: "animate6",
					animating: "fadeInUpBig",
					closeable: true
				});
				widget.$.css({
					width: "1050px",
					marginTop: "30px"
				});
				var rich_edit_form = new RichEditForm([{
					type: "text",
					label: "标题",
					name: "title",
					required: true
				}, {
					type: "select",
					label: "封面",
					name: "show_cover_pic",
					options: {
						"0": "隐藏",
						"1": "显示"
					}
				}, {
					type: "text",
					label: "图文原地址",
					name: "content_source_url",
					required: true
				}, {
					type: "text",
					label: "作者",
					name: "author",
					required: true
				}, {
					type: "text",
					label: "单图文摘要",
					name: "digest"
				}, {
					type: "select",
					label: "封面图片",
					name: "thumb_media_id",
					size: "two-column"
				}, {
					type: "textarea",
					label: "具体内容",
					name: "content",
					size: "full",
					classes: "kindeditor",
					attributes: {
						rows: "15"
					}
				}], [{
					type: "button",
					text: "保存",
					icon: "save"
				}, {
					type: "submit",
					text: "上传",
					icon: "upload"
				}, {
					type: "button",
					text: "删除",
					icon: "trash-o",
					classes: "del"
				}]);
				var rich_edit_widget = new Widget({
					animate: "animate6",
					animating: "fadeInUpBig",
					title: "图文修改",
					template: rich_edit_form
				});
				$$("#m-mchtId").val(trData.mchtId);
				$$("#m-name").val(trData.name);
				$$("#m-dsc").val(trData.dsc);
				list_ajax.insertSelect($$('select[name="thumb_media_id"]'), list_ajax.media($$("#m-mchtId").val(), "image"));
				rich_edit_form.$.width(740);
				rich_edit_widget.$.width(1050).find("widget-content").append('<img class="digest-image">');
				rich_edit_form.$.find(".kindeditor").each(function() {
					$(this).data("editor", KindEditor.create($(this), {
						items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'table', 'hr', 'emoticons','image', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink'],
						resizeType: 1,
						allowImageUpload: false
					}));
				});
				var save_result = function(index) {
					if (!result[index]) result[index] = {};
					rich_edit_form.$.find("[name]:not(.kindeditor)").each(function() {
						result[index][$(this).attr("name")] = ($(this).val() || "").trim();
					});
					rich_edit_form.$.find(".kindeditor").each(function() {
						result[index][$(this).attr("name")] = $(this).data("editor").html();
					});
					if (rich_edit_widget.$.find(".form-nav li:visible").length > 1) {
						rich_edit_form.$.find("input[name='digest']").prop('disabled', true);
					}
					var title = result[index].title || "无标题"
					$(".form-nav li:nth-child(" + (index + 1) + ") a").text(title);
				},
					load_result = function(index) {
						rich_edit_form.$.trigger("reset");
						rich_edit_form.$.find(".kindeditor").each(function() {
							$(this).data("editor").html("");
						});
						for (var name in result[index]) {
							var $input = rich_edit_form.$.find('[name="' + name + '"]');
							if ($input.hasClass("kindeditor")) {
								$input.data("editor").html(result[index][name]);
							} else {
								$input.val(result[index][name]);
							}
						}
					}
				load_result(0);
				var $nav = $('<ul class="form-nav">').prependTo(rich_edit_widget.$.find("widget-content"));
				Array.prototype.forEach.call("一二三四", function(number, index) {
					var title = result[index].title || "新图文";
					var li = $('<li><a href="#">' + title + '</a></li>').data("index", index).appendTo($nav);
					result.push({});
					if (!result[index].title) {
						li.css("display", "none");
					}
				});
				$nav.find("li:first-child").addClass("current").end().on("click", "a", function(event, type) {
					if (!$(this).parent().hasClass("current")) {
						var $parent = $(this).parent().addClass("current");
						var $current = $parent.siblings(".current").removeClass("current");
						var index = $current.data("index");
						save_result($current.data("index"));
						load_result($parent.data("index"));
						if (type != "del") {
							if (!result[index].title || !result[index].content_source_url || !result[index].author) {
								$.jGrowl("该图文存在内容未填写完整！直接上传会失去相关内容，请注意！");
							}
						}
					}
				});
				$$(".container-inner").on("click", ".rich-edit-form button", function() {
					if ($(this).attr("type") == "submit") {
						save_result($$(".rich-edit-form .current").data("index"));
						result = result.filter(function(a) {
							return a.title && a.author && a.content_source_url;
						});
						var data = {
							mchtId: $$("#m-mchtId").val(),
							media: trData.media,
							name: $$("input[name='name']").val(),
							dsc: $$("input[name='dsc']").val(),
							articles: JSON.stringify({
								articles: result
							})
						};

						ajaxFormSubmit({
							verify: {
								obj: $(".required")
							},
							url: "wechat/media/updateNews.html",
							data: data,
							success: function() {
								$("form").trigger("reset");
								rich_edit_form.$.find(".kindeditor").each(function() {
									$(this).data("editor").html("");
								});
								result = [];
								$$("widget:gt(1)").remove();
							},
							error: function() {
								popupClose();
								$(".loadingGif").remove();
							}
						});
					} else if ($(this).hasClass('del')) {
						var index = $(".rich-edit-form .current").data("index"),
							delItem;
						if (index == 0 && rich_edit_widget.$.find(".form-nav li:visible").length == 1) {
							rich_edit_form.$.trigger("reset");
							rich_edit_form.$.find(".kindeditor").each(function() {
								$(this).data("editor").html("");
							});
						} else {
							if (index != 0) {
								delItem = $(".form-nav li:first-child");
							} else {
								delItem = $(".form-nav li:nth-child(2)");
							}
							delItem.find("a").trigger("click", ["del"])
								.parent().siblings("li:nth-child(" + (index + 1) + ")")
								.nextAll().each(function() {
									$(this).data("index", $(this).data("index") - 1);
								})
								.end().remove();
							$('<li><a href="#">新图文</a></li>').data("index", 3).css("display", "none").appendTo($(".form-nav"));
							result[index] = {};
							result = result.filter(function(a) {
								return a.title
							});
							if (rich_edit_widget.$.find(".form-nav li:visible").length < 2) {
								rich_edit_form.$.find("input[name='digest']").prop('disabled', false);
							}
						}
					} else {
						if (!formVerify({
							obj: rich_edit_form.$.find(".required")
						})) {
							return;
						}
						var index = $(".rich-edit-form .current").data("index");
						if (index != 3) {
							$(".form-nav li:nth-child(" + (index + 2) + ")").css("display", "block").find("a").click();
						} else {
							save_result(index);
						}
					}
				});
			}.bind($container)
		});
	});
});