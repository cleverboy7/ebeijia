//刮刮卡
//配置模板
$(document).on("wechat-scratchCard", ".container", function() {
	var form = new SimpleForm([{
		type: "select",
		label: "商户编号",
		name: "mchtId",
		optionsAJAX: list_ajax.mcht,
		required: true
	}, {
		type: "text",
		label: "模板名称",
		name: "modName",
		required: true,
		verify: formVerify.maxLength(64)
	}], [{
		type: "button",
		text: "添加奖品",
		id: "add",
		icon: "plus-circle"
	}, {
		type: "button",
		text: "查看效果",
		id: "display",
		icon: "play"
	}, {
		type: "submit",
		text: "提交",
		icon: "check"
	}])
	var widget = new Widget({
		title: "配置刮刮卡",
		template: form
	});
	//新增行
	var add_row = function() {
		$$(".form-input-wrapper").append(IPY_templates["luckydraw-form"]);
	};
	add_row();
	add_row();

	$$("widget").css("margin", "0 20px 20px 0");

	$$(".container-inner").append("<div class='scratch-card-box animate5'><div class='scratch-card'></div></div>");
	//添加奖品
	$$("#add").on("click", add_row);
	//删除行
	$$(".form-input-wrapper").on("click", "button:has(.fa-minus-circle)", function(event) {
		$(this).parent().remove();
		if ($$(".luckydraw-input-group").length < 2) {
			add_row();
		}
	});
	//查看效果
	$$("#display").on("click", function() {
		var $scratch = $$(".luckydraw-input-group");
		var $probability = $scratch.find('[name="probability"]');
		//验证部分
		if (veri()) return;
		//显示刮刮卡容器
		$(".scratch-card-box").css("display", "block").addClass('fadeInUpBig');
		//给刮刮卡随机出一个奖项
		var total1 = 0,
			total2 = 0;
		$probability.each(function() {
			total1 += parseInt($(this).val());
		});
		var current = Math.floor(Math.random() * total1) + 1;
		for (var i = 0; i < $probability.length; i++) {
			var t = total2;
			total2 += parseInt($($probability[i]).val());
			if (current > t && current <= total2) {
				$$(".scratch-card").text($($$("[name='prName']")[i]).val());
			}
		};
		//调用刮刮卡
		$(".scratch-card").wScratchPad({
			scratchMove: function(e, percent) {
				if (percent > 60)
					this.clear();
			}
		});
	});

	//提交
	form.$.on("submit", function() {
		if (!formVerify({
			obj: $.combine(form.$input_wrapper.mchtId, form.$input_wrapper.modName)
		})) {
			return;
		}
		if (veri()) return;
		var data = {
			mchtId: form.$input.mchtId.val(),
			modName: form.$input.modName.val()
		};
		var prizes = [];
		$(this).find(".luckydraw-input-group").each(function() {
			prizes.push({
				prName: $(this).find('[name="prName"]').val(),
				prize: $(this).find('[name="prize"]').val(),
				probability: $(this).find('[name="probability"]').val(),
				winNum: $(this).find('[name="winNum"]').val()
			});
		});
		data.prizeJson = JSON.stringify({
			prizes: prizes
		});
		ajaxFormSubmit({
			data: data,
			url: "wechat/win/add.html",
			resetForm: false
		});
	});

	function veri() {
		var $scratch = $$(".luckydraw-input-group");
		var $probability = $scratch.find('[name="probability"]');
		return (!formVerify({
			obj: $scratch.find(".required"),
			method: [formVerify.required]
		}) || !formVerify({
			obj: $probability.closest(".input-wrapper"),
			func: formVerify.regex(/^(0|[1-9]\d*)$/).func,
			info: "必须为非负整数"
		}) || !formVerify({
			obj: $probability.closest(".input-wrapper"),
			func: function() {
				return Array.prototype.some.call($probability, function(input) {
					return $(input).val() !== "0";
				});
			},
			info: "不能全部为0"
		}))
	}
})