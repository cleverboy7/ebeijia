//素材管理--微信素材上传
$(document).on("wechat-media-upd", ".container", function() {
	new Widget({
		title: "微信素材上传",
		template: new SimpleForm([{
			type: "select",
			label: "商户编号",
			name: "mchtId",
			optionsAJAX: list_ajax.mcht,
			required: true
		}, {
			type: "select",
			label: "存放类型",
			name: "mediaType",
			required: true,
			options: {
				"0": "临时",
				"1": "永久"
			},
			value: "1"
		}, {
			type: "select",
			label: "素材类型",
			name: "type",
			required: true,
			options: {
				image: "image",
				voice: "voice",
				video: "video",
				thumb: "thumb"
			}
		}, {
			type: "text",
			label: "素材名称",
			name: "name",
			required: true,
			verify: formVerify.maxLength(30)
		}, {
			type: "text",
			label: "素材描述",
			name: "dsc",
			verify: formVerify.maxLength(64)
		}, {
			type: "file",
			label: "选择附件",
			name: "file",
			required: true
		}], [{
			type: "submit",
			text: "上传",
			icon: "upload"
		}], {
			autosubmit: {
				url: "wechat/media/upLoad.html"
			}
		})
	});
});

//素材管理--微信素材管理
$(document).on("wechat-media-query", ".container", function() {
	var query_form = new QueryForm([{
		type: "text",
		label: "商户编号",
		name: "mchtId"
	}, {
		type: "select",
		label: "存放类型",
		name: "mediaType",
		options: {
			"": "所有类型",
			"0": "临时",
			"1": "永久"
		},
		value: ""
	}, {
		type: "select",
		label: "素材类型",
		name: "type",
		options: {
			"": "所有类型",
			image: "image",
			voice: "voice",
			video: "video",
			thumb: "thumb"
		},
		value: ""
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
		title: "素材ID",
		index: "media",
		width: "20%"
	}, {
		title: "名称",
		index: "name"
	}, {
		title: "描述",
		index: "dsc"
	}, {
		title: "素材类型",
		index: "type"
	}, {
		title: "存放类型",
		map_func: function(data) {
			return ["临时", "永久"][data.mediaType];
		}
	}, {
		title: "URL",
		index: "url",
		width: "20%"
	}, {
		title: "上传时间",
		map_func: function(data) {
			return data.createTime.parseDate();
		}
	}, {
		title: "相关操作",
		width: "200px",
		map_func: IPY_templates["table-button-download"] + " " + IPY_templates["table-button-modify"] + " " + IPY_templates["table-button-delete"]
	}], {
		url: "wechat/media/query.html",
		table_data: "mediaList",
		$query_form: query_form.$,
		"delete": {
			url: "wechat/media/del.html",
			data: function(data) {
				return {
					mchtId: data.mchtId,
					media: data.media
				};
			}
		}
	});
	new Widget({
		title: "微信素材列表",
		template: table
	});
	table.refresh();

	table.$.on("modify", "tr", function() {
		var trData = $(this).data("data");
		lockScreen();
		new Widget({
			title: "微信素材信息修改",
			template: new PopupForm([{
				type: "text",
				label: "商户编号",
				name: "mchtId",
				value: trData.mchtId,
				disabled: true
			}, {
				type: "text",
				label: "素材ID",
				name: "media",
				value: trData.media,
				readonly: true,
				required: true
			}, {
				type: "text",
				label: "名称",
				name: "name",
				value: trData.name,
				required: true,
				verify: formVerify.maxLength(30)
			}, {
				type: "text",
				label: "描述",
				name: "dsc",
				value: trData.dsc,
				verify: formVerify.maxLength(64)
			}, {
				type: "text",
				label: "素材类型",
				name: "type",
				value: trData.type,
				disabled: true
			}, {
				type: "text",
				label: "存放类型",
				name: "mediaType",
				value: ["临时", "永久"][trData.mediaType],
				disabled: true
			}, {
				type: "text",
				label: "URL",
				name: "url",
				value: trData.url,
				disabled: true
			}, {
				type: "text",
				label: "上传时间",
				name: "createTime",
				value: trData.createTime.parseDate(),
				disabled: true
			}], [{
				type: "submit",
				text: "修改",
				icon: "pencil-square"
			}]),
			animate: "animate6"
		});
		$(".modify-box form").on("submit", function() {
			ajaxFormSubmit({
				data: this,
				url: "wechat/media/upd.html",
				success: function() {
					$(".modify-box .sizeBtn > .fa-close").trigger("click");
					table.refresh();
				}
			});
		});
	}).on("click", ".btn.download", function() {
		var trData = $(this).closest("tr").data("data");
		if (trData.mediaType == 1) {
			jAlert("暂不支持下载微信永久素材", "提示信息");
			return;
		}
		ajaxFormSubmit({
			url: "wechat/media/dowLoad.html",
			data: {
				mchtId: data.mchtId,
				media: data.media,
				mediaType: data.mediaType
			},
			success: function(media) {
				if (media.respCode === "00") {
					if (trData.mediaType == 0) {
						window.open(media.content, "_self");
					} else {
						var $iframe = $("<iframe>").hide().appendTo(document.body);
						var $form = $("<form>").attr("action", media.content)
							.appendTo($iframe[0].contentWindow.document.body);
						$('<input name="media_id">').val(data.media).appendTo($form);
						$form.attr({
							method: "post",
							enctype: "application/json"
						}).trigger("submit");
						$iframe.on("load", function() {
							jAlert("暂不支持下载微信永久素材", "提示信息");
							$iframe.remove();
						});
					}
				} else {
					jAlert(media.errorInfo, "错误提示");
				}
			},
			error: function() {
				jAlert("出错了，请重试", "出错了");
			}
		});
	});
});