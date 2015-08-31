$(document).on("wechat-respMsg-add", ".container", function() {
	new Widget({
		title: "基本设置",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			id: "mchtId",
			optionsAJAX: list_ajax.mcht,
			required: true
		}, {
			type: "select",
			label: "收到消息类型",
			name: "respType",
			id: "respType",
			required: true,
			options: {
				text: "文本消息",
				image: "图片消息",
				video: "视频消息",
				music: "音乐消息",
				voice: "语音消息",
				subscribe: "关注回复",
				location: "地理消息",
				link: "链接消息"
			}
		}, {
			type: "select",
			label: "回复消息类型",
			name: "msgType",
			id: "msgType",
			required: true,
			options: {
				"": "请选择",
				text: "文本消息",
				image: "图片消息",
				voice: "语音消息",
				music: "音乐消息",
				video: "视频消息",
				news: "图文消息",
				transfer_customer_service: "客服接入"
			}
		}, {
			type: "text",
			label: "关键词",
			name: "keywords",
			id: "keywords",
			required: true
		}, {
			type: "select",
			label: "关键词类型",
			name: "keywordType",
			id: "keywordType",
			required: true,
			options: {
				"1": "完全匹配",
				"2": "包含匹配"
			}
		}], [])
	});
	var result = [];
	var save_result = function(index, form) {
		form.find("input").each(function() {
			result[index][$(this).attr("name")] = ($(this).val() || "").trim();
		});
	};
	var load_result = function(index, form) {
		form.find("form").trigger("reset");
		for (var name in result[index]) {
			var $input = form.find('[name="' + name + '"]');
			$input.val(result[index][name]);
		}
	};
	$$(".simple-form").css({
		marginBottom: "30px",
		marginRight: "100px"
	});
	$$("#respType").on("change", function() {
		if ($(this).val() != "text") {
			$$("#keywords").prop("disabled", true).val("");
			$$("#keywordType").prop("disabled", true);
		} else {
			$$("#keywords").prop("disabled", false);
			$$("#keywordType").prop("disabled", false);
		}
	})
	$$("#msgType").on("change", function() {
		$$(".simple-form:nth-child(2)").remove();
		var msgType = $(this).val();
		switch (msgType) {
			case "":
				break;
			case "text":
				new Widget({
					title: "文本内容设置",
					template: new SimpleForm([{
						type: "textarea",
						label: "文本内容",
						name: "content",
						id: "content",
						required: true,
						size: "two-column",
						attributes: {
							rows: "10",
							style: "resize:none;"
						}
					}], [{
						type: "submit",
						text: "添加",
						icon: "plus-circle"
					}]),
					animate: "animate6",
					animating: "fadeInUpBig"
				});
				break;
			case "image":
			case "voice":
			case "video":
				new Widget({
					title: "多媒体消息设置",
					template: new SimpleForm([{
						type: "select",
						label: "多媒体ID",
						name: "mediaId",
						id: "mediaId",
						required: true,
						size: "two-column",
					}], [{
						type: "submit",
						text: "添加",
						icon: "plus-circle"
					}]),
					animate: "animate6",
					animating: "fadeInUpBig"
				});
				$$("#mchtId").off("change").on("change", getMedia).trigger("change");
				break;
			case "music":
				new Widget({
					title: "音乐消息设置",
					template: new SimpleForm([{
						type: "title",
						label: "音乐标题",
						name: "title",
						id: "title",
						required: true
					}, {
						type: "text",
						label: "音乐描述",
						name: "dsc",
						id: "dsc"
					}, {
						type: "text",
						label: "音乐链接",
						name: "musicUrl",
						id: "musicUrl",
						required: true
					}, {
						type: "text",
						label: "高质量音乐链接",
						name: "hqMusicUrl",
						id: "hqMusicUrl"
					}, {
						type: "select",
						label: "选择缩略图",
						name: "mediaId",
						id: "mediaId"
					}], [{
						type: "submit",
						text: "添加",
						icon: "plus-circle"
					}]),
					animate: "animate6",
					animating: "fadeInUpBig"
				});
				$$("#mchtId").off("change").on("change", getThumb).trigger("change");
				break;
			case "transfer_customer_service":
				new Widget({
					title: "客服设置",
					template: new SimpleForm([], [{
						type: "submit",
						text: "接入",
						icon: "plus-circle"
					}]),
					animate: "animate6",
					animating: "fadeInUpBig"
				});
				break;
			case "news":
				new Widget({
					title: "图文消息设置",
					template: new SimpleForm([{
						type: "text",
						label: "图文标题",
						name: "title",
						id: "title",
						required: true
					}, {
						type: "text",
						label: "图文消息描述",
						name: "description",
						id: "description",
						required: true
					}, {
						type: "text",
						label: "图片链接",
						name: "picUrl",
						id: "picUrl",
						required: true
					}, {
						type: "text",
						label: "点击图文跳转链接",
						name: "url",
						id: "url",
						required: true
					}], [{
						type: "button",
						text: "保存",
						icon: "save"
					}, {
						type: "submit",
						text: "上传",
						icon: "upload"
					}]),
					animate: "animate6",
					animating: "fadeInUpBig"
				});
				var $simpleForm2 = $$(".simple-form:nth-child(2)");
				var $nav = $('<ul class="form-nav">').prependTo($simpleForm2.find("widget-content"));
				Array.prototype.forEach.call("一二三四五六七八九十", function(number, index) {
					$('<li><a href="#">图文' + number + '</a></li>').data("index", index).appendTo($nav);
					result.push({});
				});
				$nav.find("li").css({
					width: "20%",
					display: "none"
				}).end().find("li:first-child").css("display", "block").addClass("current").end().on("click", "a", function() {
					if (!$(this).parent().hasClass("current")) {
						var $parent = $(this).parent().addClass("current");
						var $current = $parent.siblings(".current").removeClass("current");
						save_result($current.data("index"), $simpleForm2);
						load_result($parent.data("index"), $simpleForm2);
					}
				})
				$simpleForm2.find("button:not([type='submit'])").on("click", function() {
					if (!formVerify({
						obj: $$(".simple-form:nth-child(2) .required")
					})) {
						return;
					}
					$nav.find("li:nth-child(" + ($(".current").data("index") + 2) + ")").css("display", "block").find("a").click();
				});
				break;
			default:
				break;
		}
	})
	$$(".container-inner").on("click", "button[type='submit']", function() {
		if ($$("#msgType").val() == "news") {
			save_result($$(".current").data("index"), $$(".simple-form:nth-child(2)"));
		}
		var articles = result.filter(function(a) {
			return a.title && a.picUrl && a.url
		});
		var respData = {
			mchtId: $$("#mchtId").val(),
			respType: $$("#respType").val(),
			msgType: $$("#msgType").val(),
			keywords: $$("#keywords").val(),
			keywordType: $$("#keywordType").val(),
			content: $$("#content").val(),
			mediaId: $$("#mediaId").val(),
			title: $$("#title").val(),
			dsc: $$("#dsc").val(),
			musicUrl: $$("#musicUrl").val(),
			hqMusicUrl: $$("#hqMusicUrl").val(),
			mediaId: $$("#mediaId").val(),
			articles: JSON.stringify({
				articles: articles
			})
		};
		ajaxFormSubmit({
			verify: {
				obj: $$(".required")
			},
			data: respData,
			url: "wechat/respMsg/add.html",
			success: function() {
				$$(".simple-form:nth-child(2)").remove();
				result = [];
			}
		});
	});

	var getMedia = function() {
		list_ajax.insertSelect($$("#mediaId"), list_ajax.media($$("#mchtId").val(), $$("#msgType").val()));
	};

	var getThumb = function() {
		list_ajax.insertSelect($$("#mediaId"), list_ajax.media($$("#mchtId").val(), "thumb")).done(function() {
			$$("#mediaId")[0].options.add(new Option("不使用缩略图", ""));
			$$("#mediaId").val("");
		});
	};
});

$(document).on("wechat-respMsg-query", ".container", function() {
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
		title: "关键词",
		index: "keywords"
	}, {
		title: "关键词类型",
		map_func: function(data) {
			return ["未知类型", "完全匹配", "包含匹配"][data.keywordType];
		},
		width: "70px"
	}, {
		title: "接收消息类型",
		map_func: function(data) {
			return {
				text: "文本消息",
				image: "图片消息",
				voice: "语音消息",
				video: "视频消息",
				shortvideo: "小视频消息",
				location: "地理位置消息",
				link: "链接消息",
				subscribe: "用户订阅",
				event: "事件消息"
			}[data.respType] || data.respType;
		}
	}, {
		title: "回复消息类型",
		map_func: function(data) {
			return {
				text: "文本消息",
				image: "图片消息",
				voice: "语音消息",
				music: "音乐消息",
				video: "视频消息",
				news: "图文消息",
				transfer_customer_service: "transfer"
			}[data.msgType] || data.msgType;
		}
	}, {
		title: "内容",
		index: "content"
	}, {
		title: "标题",
		index: "title"
	}, {
		title: "描述",
		index: "description"
	}, {
		title: "图片链接",
		map_func: function(data) {
			return $("<a>").css({
				overflow: "hidden",
				textOverflow: "ellipsis",
				width: "80px",
				whiteSpace: "nowrap",
				display: "block"
			}).html(data.picUrl).attr({
				href: data.picUrl,
				target: "_blank"
			});
		}
	}, {
		title: "链接",
		index: "url"
	}, {
		title: "媒体ID",
		index: "mediaId",
		width: "13%"
	}, {
		title: "音乐链接",
		map_func: function(data) {
			return $("<a>").css({
				overflow: "hidden",
				textOverflow: "ellipsis",
				width: "80px",
				whiteSpace: "nowrap",
				display: "block"
			}).html(data.musicUrl).attr({
				href: data.musicUrl,
				target: "_blank"
			});
		}
	}, {
		title: "高质量音乐链接",
		map_func: function(data) {
			return $("<a>").css({
				overflow: "hidden",
				textOverflow: "ellipsis",
				width: "80px",
				whiteSpace: "nowrap",
				display: "block"
			}).html(data.musicUrl).attr({
				href: data.musicUrl,
				target: "_blank"
			});
		}
	}, {
		title: "相关操作",
		width: "70px",
		map_func: IPY_templates["table-button-delete"]
	}], {
		url: "wechat/respMsg/query.html",
		table_data: "respMsgList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/respMsg/del.html",
			data: function(data) {
				respMsgId: data.respMsgId
			}
		}
	});
	new Widget({
		title: "自动消息答复列表",
		template: table
	});
	table.refresh();
});