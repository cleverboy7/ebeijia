$(document).on("wechat-luckydraw", ".container", function() {
	var BigWheel = function(args) {
		this.$parent = args.$parent || $$(".container-inner");
		this.content = args.content;
		this.frequency = args.frequency;
		var $draw = $(IPY_templates.bigWheel).appendTo(this.$parent);
		var $body = $draw.find(".bigWheel-body").addClass("resetting");

		var sectors = this.content.length;
		var sum_frequency = this.frequency.reduce(function(x, y) {
			return x + y;
		});

		var original_color = ["red", "green", "blue"];
		var color = [];
		for (var i = 0; i < sectors; ++i) {
			color.push(original_color[i % 3]);
		}
		if (sectors % 3 == 1) {
			color[sectors - 2] = original_color[0];
			color[sectors - 1] = original_color[2];
		}

		color.forEach(function(c, i) {
			var $sector = $(IPY_templates["bigWheel-sector"]).appendTo($body);
			$sector.css("transform", "rotate(" + -(360 / sectors * i + 180 / sectors) + "deg)")
				.find(".bigWheel-pie").css({
					"background-color": c,
					background: "radial-gradient(yellow, " + c + ")",
					transform: "rotate(" + (360 / sectors - 180) + "deg)"
				});
		});
		this.content.forEach(function(text, i) {
			$('<div class="bigWheel-wrapper"></div>')
				.css("transform", "rotate(" + -(360 / sectors * i) + "deg)")
				.text(text)
				.appendTo($body);
		});

		this.animating = false;
		var that = this;
		$body.on("transitionend webkitTransitionEnd MSTransitionEnd", function() {
			$body.addClass("resetting");
			that.animating = false;
		});

		$draw.on("click", function() {
			if (that.animating) {
				return;
			}
			that.animating = true;
			$body.css("transform", "rotate(0)");
			setTimeout(function() {
				$body.removeClass("resetting");
				setTimeout(function() {
					var choice = (function() {
						var x = Math.floor(sum_frequency * Math.random());
						for (var i = 0; i < sectors; ++i) {
							if (x < that.frequency[i]) {
								return i;
							} else {
								x -= that.frequency[i];
							}
						}
					})();
					var degree = 5400 + 360 / sectors * choice;
					$body.css("transform", "rotate(" + degree + "deg)");
				}, 0);
			}, 0);
		});
	};

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
	}]);
	var widget = new Widget({
		title: "配置大转盘",
		template: form
	});

	var add_row = function() {
		$$(".form-input-wrapper").append(IPY_templates["luckydraw-form"]);
	};

	add_row();
	add_row();

	$$("#add").on("click", add_row);
	$$(".form-input-wrapper").on("click", "button:has(.fa-minus-circle)", function(event) {
		$(this).parent().remove();
		if ($$(".luckydraw-input-group").length < 2) {
			add_row();
		}
	});
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
			url: "wechat/act/luckydrawAdd.html",
			resetForm: false
		});
	});
	$$("#display").on("click", function() {
		var $luckydraw = $$(".luckydraw-input-group");
		var $probability = $luckydraw.find('[name="probability"]');
		if (veri()) return;
		var content = $luckydraw.find('[name="prName"]').map(function() {
			return $(this).val();
		}).get();
		var frequency = $probability.map(function() {
			return parseInt($(this).val());
		}).get();
		$$(".luckydraw-place").remove();
		new BigWheel({
			$parent: $('<div class="luckydraw-place"></div>').css({
				position: "relative",
				"float": "left",
				width: "320px",
				height: "320px",
				"margin-left": "40px",
				"margin-top": "20px"
			}).appendTo($$(".container-inner")),
			content: content,
			frequency: frequency
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
});