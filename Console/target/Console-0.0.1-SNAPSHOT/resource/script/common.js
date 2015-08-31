jQuery(document).ready(function($) {
	if (window.console) {
		var generate_css = function(obj) {
			var css = [];
			for (var key in obj) {
				if (obj.hasOwnProperty(key)) {
					css.push(key + ":" + obj[key]);
				}
			}
			return css.join(";");
		};
		var text = "欢迎使用微信管理平台";
		if (/webkit/i.test(navigator.userAgent)) {
			console.log("%c" + text, generate_css({
				"-webkit-background-clip": "text",
				"background-image": "-webkit-linear-gradient(180deg, #f00, #ff0, #0f0, #0ff, #00f, #f0f, #f00)",
				"font-family": '"Microsoft YaHei", sans-serif',
				"font-size": "3em",
				color: "transparent"
			}));
		} else if (/Firefox/i.test(navigator.userAgent)) {
			console.log("%c" + text, "font-size:3em");
		} else {
			console.log(text);
		}
	}
	$(document.body)
		.on("click", "a", function(event) {
			var href = $(this).attr("href");
			if (!href || href[0] == "#") {
				event.preventDefault();
			}
		})
		.on("submit", "form", function(event) {
			event.preventDefault();
		})
		.addClass("color-scheme-" + (localStorage.getItem("color-scheme") || "default"))
		.toggleClass("nav-expand", $(window).width() >= 1100);

	window.IPY_templates = {};
	$("ipy-template").each(function() {
		IPY_templates[$(this).attr("ipy-id")] = $(this).html();
	}).remove();

	try {
		Object.freeze(IPY_templates);
	} catch (e) {}

	$("body > header").html(IPY_templates.header).find(".time").digitTime();

	$.ajax({
		url: resourceURL + "template/nav.json",
		dataType: "json",
		cache: false,
		success: function(data) {
			var nav_map = {};
			var stack = data.slice();
			while (stack.length) {
				var current = stack.pop();
				if (current.subnav) {
					current.subnav.forEach(function(subnav) {
						subnav.parentnav = current;
					});
					stack = stack.concat(current.subnav);
				} else {
					nav_map[current.title] = current;
				}
			};
			authority.split(",").forEach(function(item) {
				for (var nav = nav_map[item]; nav && !nav.enabled; nav = nav.parentnav) {
					nav.enabled = true;
				}
			});

			var $nav = $("body > nav > .nav");
			if (!data.length) {
				$nav.remove();
				return;
			}
			var nav_template = IPY_templates.nav;
			var subnav_template = IPY_templates.subnav;
			var animation_count = 0;
			$nav
				.parent().addClass("pre-animation").end()
				.on("click", ".nav-item", expand_nav)
				.on("click", "li > a", function() {
					var $parent = $(this).parent();
					var $dropdown_list = $parent.children("ul");
					if ($dropdown_list.length) {
						$parent.toggleClass("expand");
						$dropdown_list.slideToggle("fast");
					} else {
						$.switch_page($(this));
					}
				})
				.on("animationend webkitAnimationEnd MSAnimationEnd", "> li", function(event) {
					var $this = $(this);
					if (event.originalEvent.animationName == "nav-bounce") {
						$this.removeClass("animation-nav-bounce").addClass("nav-item-shadow");
						setTimeout(function() {
							$this.removeClass("nav-item-shadow");
						}, 200);
						if (--animation_count == 0) {
							$nav.parent().removeClass("pre-animation");
						}
					}
				});

			var add_sub_nav = function(nav, $nav) {
				if (!nav.subnav) {
					$nav.children("ul").remove();
					return;
				}
				$nav.addClass("dropdown");
				nav.subnav.filter(function(item) {
					return item.enabled;
				}).forEach(function(subnav_item) {
					var $subnav = $(subnav_template)
						.children("a").attr("href", subnav_item.href || "#")
							.children(".title").text(subnav_item.title).end()
						.end()
						.appendTo($nav.children("ul"));
					add_sub_nav(subnav_item, $subnav);
				});
			};

			if (Modernizr.cssanimations) {
				data.filter(function(item) {
					return item.enabled;
				}).forEach(function(nav_item, index) {
					setTimeout(function() {
						var $nav_item = $(nav_template)
							.children("a").attr("href", nav_item.href || "#")
								.children(".fa").addClass("fa-" + nav_item.icon).end()
								.children(".title").text(nav_item.title).end()
							.end()
							.appendTo($nav);
						add_sub_nav(nav_item, $nav_item);
						$nav_item.addClass("animation-nav-bounce");
						++animation_count;
					}, index * 100);
				});
			} else {
				data.filter(function(item) {
					return item.enabled;
				}).forEach(function(nav_item) {
					var $nav_item = $(nav_template)
						.children("a").attr("href", nav_item.href || "#")
							.children(".fa").addClass("fa-" + nav_item.icon).end()
							.children(".title").text(nav_item.title).end()
						.end()
						.appendTo($nav);
					add_sub_nav(nav_item, $nav_item);
				});
				$nav.parent().removeClass("pre-animation");
			}
		}
	});

	$(".breadcrumb-wrapper").html(IPY_templates.breadcrumb)
		.children(".nav-switcher-wrapper").on("click", function() {
			if ($(document.body).hasClass("nav-expand")) {
				close_nav();
			} else {
				expand_nav();
			}
		}).end()
		.children(".fullscreen-wrapper").on("click", function() {
			if (document.fullscreenEnabled) {
				if (document.fullscreenElement) {
					document.exitFullscreen();
				} else {
					document.body.requestFullscreen();
				}
			}
		}).end()
		.children(".color-scheme-switcher")
			.children("a").on("click", function() {
				$(this).parent().toggleClass("open");
			}).end()
			.on("focusout", function() {
				$(this).removeClass("open");
			})
			.on("mouseover", "> ul a", function() {
				var color_scheme = $(this).attr("href").slice(1);
				document.body.className = document.body.className.replace(/color-scheme-\S+/,
					"color-scheme-" + color_scheme);
				localStorage.setItem("color-scheme", color_scheme);
			})
			.on("click", "> ul a", function(event) {
				$(".breadcrumb-wrapper > .color-scheme-switcher").trigger("focusout");
			});

	$("body > main").on("transitionend webkitTransitionEnd MSTransitionEnd", ".container", function(event) {
		if ($(event.target).is(".container")) {
			$("body > main").removeClass("switching");
			if ($(this).hasClass("old")) {
				$(this).remove();
			}
		}
	});

	var expand_nav = function() {
		$(document.body).addClass("nav-expand");
	};
	var close_nav = function() {
		$(document.body).removeClass("nav-expand");
		$("body > nav .dropdown.expand").removeClass("expand").children("ul").slideUp("fast");
	};
	var original_window_width = $(window).width();
	$(window).on("resize", function() {
		var width = $(window).width();
		if (width < 1100 && original_window_width >= 1100) {
			close_nav();
		}
		if (width >= 1100 && original_window_width < 1100) {
			expand_nav();
		}
		original_window_width = width;
	});

	$.switch_page = function($item) {
		if ($("body > main").hasClass("switching")) {
			return;
		}
		$("#jGrowlClose").trigger("click");
		var $breadcrumb = $(".breadcrumb");
		if (arguments.length === 0) {
			$("body > nav .nav-item.active").removeClass("active");
		} else {
			$item.closest(".nav-item").addClass("active").siblings().removeClass("active");
			var href_string = 'href="' + $item.attr("href") + '"';
			$breadcrumb.children("[" + href_string + "]").prev().addBack().remove();
			$breadcrumb
				.append('<li class="separator"></li>')
				.append("<li " + href_string + ">" + $item.children(".title").text() + "</li>");
			var length = $breadcrumb.children().length;
			if (length > 11) {
				$breadcrumb.children().slice(1, length - 10).remove();
			}
		}
		if (Modernizr.csstransitions) {
			$("body > main").addClass("switching");
		}
		$("body > main").append('<div class="container current"></div>');
		setTimeout(function() {
			$(".container:not(.current)").addClass("old");
			var $container = $(".container.current").removeClass("current");
			if (typeof $item === "undefined") {
				$container.html(IPY_templates.container)
					.find(".container-inner").html(IPY_templates.home).end()
					.trigger("home");
			} else {
				var href = $item.attr("href");
				if (href === "#") {
					$container.html(IPY_templates[404]).trigger("404");
				} else {
					$container.html(IPY_templates.container).trigger(href.slice(1));
				}
			}
			if (!Modernizr.csstransitions) {
				$("body > main").removeClass("switching");
				$(".container.old").remove();
			}
		}, 0);
	};

	window.$$ = function(selector) {
		return $(".container:not(.old)").find(selector);
	};
	$(".container").html(IPY_templates.container)
		.find(".container-inner").html(IPY_templates.home).end()
		.trigger("home");
	$("body > header > .logo, .breadcrumb > .fa-home").on("click", function() {
		$.switch_page();
	});
	$(".breadcrumb").on("click", "li[href]", function() {
		$.switch_page($('body > nav a[href="' + $(this).attr("href") + '"]'));
	});
	$(document).on("click", ".notfound > button", function() {
		$.switch_page();
	});
});
