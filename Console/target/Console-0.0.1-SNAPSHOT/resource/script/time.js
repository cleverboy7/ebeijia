(function($, undefined) {
	$.fn.digitTime = function() {
		var get_time = function() {
			var date = new Date();
			var hour = date.getHours();
			var minute = date.getMinutes();
			var second = date.getSeconds();
			return [Math.floor(hour / 10), hour % 10, Math.floor(minute / 10), minute % 10, Math.floor(second / 10), second % 10];
		};

		var $digits = this
			.html('<li></li><li></li><li class="separator"></li><li></li><li></li><li class="separator"></li><li></li><li></li>')
			.find("li:not(.separator)")
			.on("animationend webkitAnimationEnd MSAnimationEnd", function(event) {
				if (event.originalEvent.animationName == "flip") {
					$(this).removeClass("animation-flip");
				}
			});
		var time = get_time();
		for (var i = 0; i < 6; ++i) {
			$digits.eq(i).text(time[i]);
		}

		setInterval(function() {
			var t = get_time();
			for (var i = 6; --i >= 0;) {
				if (t[i] == time[i]) {
					break;
				} else {
					$digits.eq(i).text(t[i]).addClass("animation-flip");
				}
			}
			time = t;
		}, 100);
	};
})(jQuery);