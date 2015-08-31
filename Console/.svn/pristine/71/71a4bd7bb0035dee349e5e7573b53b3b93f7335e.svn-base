var list_ajax = function(args) {
	if ("cache" in args && cacheStorage.hasItem(args.cache)) {
		return $.Deferred().resolve(cacheStorage.getItem(args.cache));
	} else {
		var deferred = $.Deferred();
		$.ajax2({
			url: args.url,
			data: args.data,
			method: "post",
			success: function(data) {
				var cacheData = {};
				data.info.forEach(function(info) {
					cacheData[info.value] = info;
				});
				if ("cache" in args) {
					cacheStorage.setItem(args.cache, cacheData);
				}
				deferred.resolve(cacheData);
			},
			fail: function() {
				deferred.reject.call(null, arguments);
			}
		});
		return deferred;
	}
};

list_ajax.insertSelect = function($select, args) {
	return list_ajax(args).done(function(data) {
		var select = $select[0];
		(args.beforeInsert || function() {
			$(this).empty();
		}).call(select);
		var select_options = select.options;
		for (var value in data) {
			if (data.hasOwnProperty(value)) {
				var option = new Option(data[value].key, value);
				$.data(option, "info", data[value]);
				select_options.add(option);
			}
		}
	});
};

list_ajax.replaceText = function($div, index, args) {
	return list_ajax(args).done(function(data) {
		for (var value in data) {
			if (data.hasOwnProperty(value) && value === index) {
				$div.text(data[value].key);
				return;
			}
		}
	});
};

list_ajax.removeCache = function(type) {
	cacheStorage.removeItems("list/" + type);
};

$.extend(list_ajax, {
	group: function(mchtId) {
		return {
			url: "list/groupId.html",
			data: {
				mchtId: mchtId
			},
			cache: "list/group(" + mchtId + ")"
		};
	},
	mcht: {
		url: "list/mchtAll.html",
		cache: "list/mcht"
	},
	media: function(mchtId, msgType) {
		return {
			url: "list/typeMedia.html",
			data: {
				mchtId: mchtId,
				msgType: msgType
			}
		};
	},
	menuUp: function(mchtId, groupId) {
		return {
			url: "list/menuUpId.html",
			data: {
				mchtId: mchtId,
				groupId: groupId
			},
			beforeInsert: function() {
				$(this).children(":gt(0)").remove();
			}
		};
	},
	mod: function(mchtId, type) {
		return {
			url: "list/modList.html",
			data: {
				mchtId: mchtId,
				type: type
			}
		};

	},
	role: {
		url: "list/role.html",
		cache: "list/role"
	},
	subGroup: function(mchtId) {
		return {
			url: "list/subGroup.html",
			data: {
				mchtId: mchtId
			},
			beforeInsert: function() {
				$(this).children(":gt(0)").remove();
			},
			cache: "list/subGroup(" + mchtId + ")"
		};
	},
	template: {
		url: "list/dictType.html",
		data: {
			type: 9
		},
		cache: "list-template"
	}
});


var ajaxFormSubmit = function(args) {
	if (args.data instanceof HTMLFormElement) {
		args.verify = args.verify || {
			obj: $(args.data).find(".input-wrapper")
		};
	}
	if ("verify" in args && !formVerify(args.verify)) {
		return;
	}
	if (args.data instanceof HTMLFormElement) {
		var $form = $(args.data);
		var $inputs = $form.find("[name]:not(:disabled)");
		if ($inputs.filter('[type="file"]').length) {
			args.data = new FormData();
			$inputs.each(function() {
				if ($(this).hasClass("hasDatepicker")) {
					args.data.append($(this).attr("name"), $(this).val().replace(/-/g, ""));
				} else {
					args.data.append($(this).attr("name"), $(this).val());
				}
			});
		} else {
			args.data = {};
			$inputs.each(function() {
				if ($(this).hasClass("hasDatepicker")) {
					args.data[$(this).attr("name")] = $(this).val().replace(/-/g, "");
				} else {
					args.data[$(this).attr("name")] = $(this).val();
				}
			});
		}
	}
	var adminId = $(".user-info").attr("user-id");
	return $.ajax2({
		url: args.url,
		method: "post",
		data: args.data,
		beforeSend: function() {
			lockScreen(false);
			$(document.body).append('<img class="loadingGif" src="' + resourceURL + '/image/loading.gif">');
		},
		complete: function() {
			popupClose();
			$(".loadingGif").remove();
		},
		success: function(obj) {
			if (obj.respCode !== "00") {
				jAlert(obj.errorInfo, "信息提示", function() {
					args.respCode01 && args.respCode01(obj.errorInfo);
				});
			} else {
				if (!("successAlert" in args)) {
					args.successAlert = true;
				}
				if (args.successAlert) {
					jAlert("操作成功~！", "信息提示", function() {
						args.success && args.success(obj.content);
					});
				} else {
					args.success && args.success(obj.content);
				}
				var resetForm = ("resetForm" in args) ? args.resetForm : true;
				if (resetForm) {
					if (args.data instanceof HTMLFormElement) {
						$(args.data).trigger("reset");
					} else {
						$$(".simple-form form").trigger("reset");
					}
				}
			}
		},
		error: function() {
			args.error && args.error.apply(null, arguments);
			jAlert("网络故障，请联系管理员", "信息提示");
		}
	});
};

$.jGrowl.defaults.closerTemplate = "<div>[ 全 部 关 闭 ]</div>";
var $jGrowl = null;
//验证表单的方法
var formVerify = function(args_array) {
	if (!Array.isArray(args_array)) {
		args_array = [args_array];
	}
	var success = true;
	if ($jGrowl && $jGrowl.data("jGrowl.instance")) {
		$jGrowl.data("jGrowl.instance").shutdown();
		$jGrowl.remove();
	}
	$jGrowl = $('<div id="jGrowl"></div>').appendTo($(document.body));
	args_array.forEach(function(args) {
		args.obj.has("[name]:not(:disabled)").each(function() {
			var method_list;
			if (args.method) {
				method_list = args.method;
			} else if ("func" in args) {
				method_list = [{
					func: args.func,
					info: args.info
				}];
			} else {
				method_list = $(this).data("form-verify");
			}
			method_list.some(function(method) {
				if (!method.func.call(this, ($(this).find("[name]").val() || "").toString())) {
					$(this).addClass("animate5 shake");
					$jGrowl.jGrowl($(this).find("label").text().slice(0, -1) + method.info);
					success = false;
					return true;
				}
			}, this);
		});
	});
	setTimeout(function() {
		args_array.forEach(function(args) {
			args.obj.removeClass("animate5 shake");
		});
	}, 900);
	return success;
};
formVerify.stringLength = function(min, max) {
	return {
		func: function(string) {
			var length = string.utf8Length();
			return length >= min && length <= max;
		},
		info: "必须为" + min + "到" + max + "位"
	};
};
formVerify.regex = function(regex) {
	return {
		func: function(string) {
			return regex.test(string);
		},
		info: ""
	};
};
formVerify.required = {
	func: formVerify.stringLength(1, Infinity).func,
	info: "不能为空"
};
formVerify.minLength = function(min) {
	return {
		func: formVerify.stringLength(min, Infinity).func,
		info: "不能少于" + min + "位"
	};
};
formVerify.maxLength = function(max) {
	return {
		func: formVerify.stringLength(0, max).func,
		info: "不能多于" + max + "位"
	};
};
formVerify.number = function(min, max) {
	return {
		func: function(money) {
			return (money > min) && (money < max);
		},
		info: "必须大于" + min + "小于" + max
	};
};
formVerify.num = {
	func: formVerify.regex(/^[1-9]\d{0,3}$/).func,
	info: "必须为小于一万的正整数"
};
formVerify.password = {
	func: formVerify.regex(/^\w{6,32}$/).func,
	info: "必须为6到32位的数字或字母"
};
formVerify.date = {
	func: function(date) {
		if (!/^\d{4}-\d{2}-\d{2}$/.test(date)) {
			return false;
		}
		var d = new Date(date);
		if (isNaN(d.valueOf())) {
			return false;
		}
		var arr = date.split("-");
		return arr[0] == d.getFullYear() && arr[1] == d.getMonth() + 1 && arr[2] == d.getDate();
	},
	info: "格式错误"
}