//群发消息--分组群发
$(document).on("wechat-mass-group", ".container", function() {
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		required: true
	}, {
		type: "select",
		label: "关注者组别",
		name: "groupId",
		options: {
			"0": "未分组"
		},
		required: true
	}, {
		type: "select",
		label: "消息类型",
		name: "type",
		required: true,
		options: {
			text: "text",
			image: "image",
			voice: "voice",
			mpvideo: "video",
			mpnews: "news"
		}
	}, {
		type: "text",
		label: "消息内容",
		name: "content",
		required: true,
		verify: formVerify.maxLength(128)
	}, {
		type: "text",
		label: "多媒体ID",
		name: "media",
		size: "two-column",
		disabled: true,
		readonly: true,
		required: true
	}], [{
		type: "submit",
		text: "发送",
		icon: "send"
	}], {
		autosubmit: {
			url: "wechat/mass/sendAll.html"
		}
	});
	new Widget({
		title: "分组群发",
		template: form
	});

	form.$input.media.css("cursor", "pointer");
	form.$input.mchtId.on("change", function() {
		list_ajax.insertSelect(form.$input.groupId, list_ajax.subGroup($(this).val())).done(function() {
			form.$input.groupId.val("0");
		});
		form.$input.media.val("");
	});
	form.$input.type.on("change", function() {
		if ($(this).val() === "text") {
			form.$input.media.prop("disabled", true).val("");
			form.$input.content.prop("disabled", false);
		} else {
			form.$input.media.prop("disabled", false);
			form.$input.content.prop("disabled", true);
		}
	});
	list_ajax.insertSelect(form.$input.mchtId, list_ajax.mcht).done(function() {
		form.$input.mchtId.trigger("change");
	});

	form.$input.type.on("change", function() {
		if ($(this).val() == "text") {
			return;
		}
		lockScreen();
		new Widget({
			title: "选择文件",
			template: new MediaWidget()
		});
		$.ajax2({
			url: "list/typeMedia.html",
			data: {
				mchtId: form.$input.mchtId.val(),
				msgType: form.$input.type.val()
			},
			success: function(data) {
				if (data.info.length > 0) {
					var url, type = form.$input.type.val();
					data.info.forEach(function(a) {
						switch (type) {
							case "image":
								url = a.url;
								break;
							case "voice":
								url = "/Console/resource/image/audio.png";
								break;
							case "video":
								url = "/Console/resource/image/video.png";
								break;
							case "news":
								url = "/Console/resource/image/doc.png";
								break;
							default:
								url = "/Console/resource/image/doc.png";
								break;
						}
						$a = $("<div class='thumb'><img src='" + url + "' alt='facebox'/><div>" + a.key + "</div><div class='thumb-select'>选 择</div><div class='control'><span class='fa fa-search'></span></div></div>")
							.data('value', a.value);
						if (type == "image") {
							$a.appendTo('.media-widget widget-content').find("img,.control").facebox();
						} else if (type == "voice") {
							$a.append("<audio src='" + a.url + "'></audio>").find("span").removeClass("fa-search").addClass('fa-play');
							$a.appendTo('.media-widget widget-content');
						} else if (type == "video") {
							$a.prepend("<video src='" + a.url + "'></video>").find("span").removeClass("fa-search").addClass('fa-play');
							$a.find("img").remove();
							$a.appendTo('.media-widget widget-content');
						} else if (type == "news") {
							$a.find("span").remove();
						}
					});
					$(".media-widget .thumb").on("click", ".control", function() {
						var audio = $(this).siblings("audio,video")[0];
						if (audio.paused) {
							audio.play();
							$(audio).removeClass('fa-play').addClass('fa-pause');
						} else {
							audio.pause();
							$(audio).removeClass('fa-pause').addClass('fa-play');
						}
						$
					});
					$(".media-widget .thumb").hover(function() {
						$(this).find(".thumb-select,.control").slideToggle(80);
					}, function() {
						$(this).find(".thumb-select,.control").slideToggle(80);
					});
					$(".media-widget .thumb .thumb-select").on("click", function() {
						form.$input.media.val($(this).closest(".thumb").data("value"));
						$(this).closest(".media-widget").data("widget").close();
						if ($("audio").length) {
							$("audio")[0].pause();
						}
					});
				} else if (data.info.length == 0) {
					$(".media-widget widget-content").text("抱歉，该商户下并没有此类文件~！").css({
						padding: "10px",
						fontSize: "14px",
						fontStyle: "italic"
					});
				}
			}
		});
	});

	form.$input.media.on("click", function() {
		event.preventDefault();
		form.$input.type.change();
	})
});

//群发消息--筛选群发
$(document).on("wechat-mass-batch", ".container", function() {
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		required: true
	}, {
		type: "dual-select",
		label: "关注者筛选",
		name: "toUsr",
		size: "full",
		required: true,
		paginate: true
	}, {
		type: "select",
		label: "微信号类型",
		name: "type",
		options: {
			text: "text",
			image: "image",
			voice: "voice",
			mpvideo: "video",
			mpnews: "news"
		},
		required: true
	}, {
		type: "text",
		label: "消息内容",
		name: "content",
		required: true,
		verify: formVerify.maxLength(128)
	}, {
		type: "select",
		label: "多媒体ID",
		name: "media",
		size: "two-column",
		disabled: true,
		required: true
	}], [{
		type: "submit",
		text: "发送",
		icon: "send"
	}], {
		autosubmit: {
			url: "wechat/mass/sendUsr.html"
		}
	});
	new Widget({
		title: "筛选群发",
		template: form
	});
	var dual_select = form.$input.toUsr.data("dual-select");
	var page_size = 20;
	var page_index = 1;
	var total_pages = 0;
	var refresh_data = function(new_mcht) {
		$.ajax2({
			url: "wechat/usr/query.html",
			method: "post",
			data: {
				mchtId: form.$input.mchtId.val(),
				aoData: DynamicTable.prototype.aoData.call({
					page_size: page_size,
					page_index: page_index
				})
			},
			success: function(data) {
				total_pages = Math.floor((data.content.total - 1) / page_size) + 1;
				form.$input.toUsr.find(".prev-page").toggleClass("btn-primary", page_index > 1).prop("disabled", page_index <= 1);
				form.$input.toUsr.find(".next-page").toggleClass("btn-primary", page_index < total_pages).prop("disabled", page_index >= total_pages);
				dual_select.clear_unselected();
				if (new_mcht) {
					dual_select.clear_selected();
				}
				dual_select.add_to_unselected(data.content.usrList.map(function(user) {
					return {
						html: $("<div>").append($('<img>').attr("src", user.headimgurl).css({
							width: "20px",
							height: "20px",
							"margin-right": "20px",
							"border-radius": "50%",
							"vertical-align": "middle"
						})).append($('<span>').text(user.nickname)),
						value: user.openid
					};
				}));
			}
		});
	};
	form.$input.toUsr.find(".prev-page").on("click", function() {
		--page_index;
		refresh_data();
	});
	form.$input.toUsr.find(".next-page").on("click", function() {
		++page_index;
		refresh_data();
	});
	var get_media = function() {
		list_ajax.insertSelect(form.$input.media, list_ajax.media(form.$input.mchtId.val(), form.$input.type.val()));
	};
	form.$input.mchtId.on("change", function() {
		page_index = 1;
		refresh_data(true);
		get_media();
	});
	form.$input.type.on("change", function() {
		form.$input.media.prop("disabled", $(this).val() === "text");
		form.$input.content.prop("disabled", $(this).val() !== "text");
		get_media();
	});
	list_ajax.insertSelect(form.$input.mchtId, list_ajax.mcht).done(function() {
		form.$input.mchtId.trigger("change");
	});
});

//分组群发--新增多媒体
$(document).on("wechat-mass-media-add", ".container", function() {
	new Widget({
		title: "新增多媒体",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			id: "mchtId",
		}, {
			type: "select",
			label: "媒体种类",
			name: "type",
			id: "type",
			options: {
				video: "video",
				news: "news"
			}
		}, {
			type: "text",
			label: "标题",
			name: "title",
			id: "title",
			required: true,
			verify: formVerify.maxLength(30)
		}, {
			type: "text",
			label: "描述",
			name: "dsc",
			id: "dsc",
			required: true,
			verify: formVerify.maxLength(64)
		}], [])
	});
	var result = [];
	var save_result = function(index) {
		//当该图文未定义时将一个空对象赋值给该图文
		if (!result[index]) result[index] = {};
		//把除了富文本编辑器外其他输入框的值加入到result中
		$(".rich-edit-form").find("[name]:not(.kindeditor)").each(function() {
			result[index][$(this).attr("name")] = ($(this).val() || "").trim();
		});
		//将富文本编辑器的值加入到result
		$(".rich-edit-form").find(".kindeditor").each(function() {
			result[index][$(this).attr("name")] = $(this).data("editor").html();
		});
		if ($$(".rich-edit-form").find(".form-nav li:visible").length > 1) {
			$$(".rich-edit-form").find("input[name='digest']").prop('disabled', true);
		}

		//为图文列表加上保存的图文的标题，若无标题则为默认标题
		var title = result[index].title || "无标题"
		$$(".form-nav li:nth-child(" + (index + 1) + ") a").text(title);
	};
	//读取图文的方法
	var load_result = function(index) {
		//重置图文部件的表单
		$(".rich-edit-form").find("form").trigger("reset");
		//重置富文本编辑器
		$(".rich-edit-form").find(".kindeditor").each(function() {
			$(this).data("editor").html("");
		});
		//循环读取result内存储的数据并将数据填入表单内
		for (var name in result[index]) {
			var $input = $(".rich-edit-form").find('[name="' + name + '"]');
			if ($input.hasClass("kindeditor")) {
				$input.data("editor").html(result[index][name]);
			} else {
				$input.val(result[index][name]);
			}
		}
	}
	$$(".simple-form").css({
		marginBottom: "30px",
		marginRight: "100px"
	});
	$$("#type").on("change", function() {
		$$("widget:nth-child(2)").remove();
		switch ($(this).val()) {
			case "video":
				new Widget({
					title: "视频设置",
					template: new SimpleForm([{
						type: "select",
						label: "多媒体ID",
						name: "mediaId",
						id: "mediaId",
						size: "two-column",
						required: true
					}], [{
						type: "submit",
						text: "添加",
						icon: "plus-circle"
					}]),
					animating: "fadeInUpBig"
				});
				$$("#mchtId").off("change").on("change", get_media).trigger("change");
				break;
			case "news":
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
				var rich_edit_widget = new Widget({
					title: "图文上传",
					template: rich_edit_form,
					animating: "fadeInUpBig"
				});
				$$("#mchtId").off("change").on("change", get_news_pic).trigger("change");
				$(".rich-edit-form").find("form").width(740);
				$(".rich-edit-form").width(1050).find("widget-content").append('<img class="digest-image">');
				$(".rich-edit-form").find(".kindeditor").each(function() {
					$(this).data("editor", KindEditor.create($(this), {
						items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright', 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'table', 'hr', 'emoticons', 'image', 'baidumap', 'pagebreak', 'anchor', 'link', 'unlink'],
						resizeType: 1,
						allowImageUpload: false
					}));
				});
				var $nav = $('<ul class="form-nav">').prependTo($$(".rich-edit-form").find("widget-content"));
				Array.prototype.forEach.call("一二三四五六七八九十", function(number, index) {
					$('<li><a href="#">新图文</a></li>').data("index", index).appendTo($nav);
					result.push({});
				});
				$nav.find("li").css("width", "20%").end()
					.find("li:first-child").addClass("current")
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
				$(".rich-edit-form").on("click", "button", function() {
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
							url: "wechat/media/mpnews.html",
							data: data,
							success: function() {
								$("form").trigger("reset");
								$(".rich-edit-form").find(".kindeditor").each(function() {
									$(this).data("editor").html("");
								});
								result = [];
							}
						});
						//当点击删除时删除掉该图文的标题列、result中的数据，并点击切换到第一个图文
					} else if ($(this).hasClass("del")) {
						var index = $$(".current").data("index"),
							delItem;
						//如果删除的是第一个图文，直接清空表单
						if (index == 0 && $$(".rich-edit-form").find(".form-nav li:visible").length == 1) {
							$(".rich-edit-form").find("form").trigger("reset");
							$(".rich-edit-form").find(".kindeditor").each(function() {
								$(this).data("editor").html("");
							});
						} else {
							if (index != 0) {
								delItem = $(".form-nav li:first-child");
							} else {
								delItem = $(".form-nav li:nth-child(2)");
							}
							//删除时自动点击图文列的第一项，并传"del"参数过去防止提示用户删掉的图文不完整
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
								return a.title;
							});
						}
						if (rich_edit_widget.$.find(".form-nav li:visible").length < 2) {
							rich_edit_form.$.find("input[name='digest']").prop('disabled', false);
						}
						//点击保存
					} else {
						//验证表单
						if (!formVerify({
							obj: $(".rich-edit-form .required")
						})) {
							return;
						}
						var index = $$(".current").data("index")
						//当保存的是最后一项图文，不需要切到下一个图文。其他则切换到下个图文
						if (index != 9) {
							$nav.find("li:nth-child(" + (index + 2) + ")").css("display", "block").find("a").click();
						} else {
							save_result(index);
						}
					}
				});
				break;
			default:
				break;
		}
	}).trigger("change");

	$$(".container-inner").on("click", "button[type='submit']", function() {
		if (massData.type == "video") {
			ajaxFormSubmit({
				verify: {
					obj: $$(".required")
				},
				data: {
					mchtId: $$("#mchtId").val(),
					title: $$("#title").val(),
					description: $$("#dsc").val(),
					mediaId: $$("#mediaId").val()
				},
				url: "wechat/mass/mpvideo.html",
				success: function() {
					$$("widget:nth-child(2)").remove();
				}
			});
		}
	})

	var get_media = function() {
		list_ajax.insertSelect($$("#mediaId"), list_ajax.media($$("#mchtId").val(), $$("#type").val()));
	};
	var get_news_pic = function() {
		list_ajax.insertSelect($$('select[name="thumb_media_id"]'), list_ajax.media($$("#mchtId").val(), "thumb"));
	};
	list_ajax.insertSelect($$("#mchtId"), list_ajax.mcht).done(get_media);
})

//分组群发--群发查询
$(document).on("wechat-mass-query", ".container", function() {
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
			return data.id.mchtId;
		}
	}, {
		title: "消息ID",
		map_func: function(data) {
			return data.id.msgId;
		}
	}, {
		title: "消息种类",
		map_func: function(data) {
			return ["分组群发", "筛选群发"][data.type];
		}
	}, {
		title: "媒体种类",
		index: "msgType"
	}, {
		title: "内容",
		index: "content"
	}, {
		title: "媒体ID",
		index: "media",
		width: "20%"
	}, {
		title: "接收人数",
		map_func: function(data) {
			return data.toUsr || "";
		}
	}, {
		title: "群发时间",
		map_func: function(data) {
			return data.createTime.parseDate();
		}
	}], {
		url: "wechat/mass/query.html",
		table_data: "massList",
		$query_form: query_form.$
	});
	new Widget({
		title: "群发信息列表",
		template: table
	});
	table.refresh();
})