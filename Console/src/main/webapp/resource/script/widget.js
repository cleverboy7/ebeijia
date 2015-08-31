(function($, undefined) {
	//创建新的主窗口部件（父元素，标题，需要使用的模板）
	window.Widget = function(widget) {
		widget.template = widget.template || {
			$: $()
		};
		var $parent = widget.$parent;
		if (!$parent) {
			if (widget.template.constructor === PopupForm || widget.template.constructor === MediaWidget) {
				$parent = $(document.body);
			} else {
				$parent = $$(".container-inner");
			}
		}
		this.$ = $(IPY_templates.widget).appendTo($parent)
			.data("widget", this)
			.find("widget-title").text(widget.title).end()
			.find("widget-content").append(widget.template.$).end();
		if (widget.template.constructor === SimpleForm || widget.template.constructor === PopupForm) {
			this.$.find("form").prepend('<input type="text" style="display:none"><input type="password" style="display:none">');
		}
		this.$.addClass("animate5 fadeInDownBig");
		if (widget.template.constructor === SimpleForm) {
			this.$.addClass("simple-form");
		} else if (widget.template.constructor === PopupForm) {
			this.$.addClass("simple-form modify-box");
		} else if (widget.template.constructor === DynamicTable) {
			this.$.addClass("dynamic-table");
		} else if (widget.template.constructor === QueryForm) {
			this.$.addClass("query-form");
		} else if (widget.template.constructor === RichEditForm) {
			this.$.addClass("rich-edit-form");
		} else if (widget.template.constructor === MediaWidget) {
			this.$.addClass("media-widget");
		}
		if (!("draggable" in widget) &&
			(widget.template.constructor === PopupForm || widget.template.constructor === MediaWidget)) {
			widget.draggable = true;
		}
		if (widget.draggable) {
			this.$.draggable({
				handler: this.$.find("widget-title"),
				cursor: "move"
			});
		}
		this.$.addClass(widget.animate);
		if (widget.animating) {
			this.$.removeClass("fadeInDownBig").addClass(widget.animating);
		}
		if (!("closeable" in widget) && 
			(widget.template.constructor === PopupForm || widget.template.constructor === MediaWidget)) {
			widget.closeable = true;
		}
		if (widget.closeable) {
			$(IPY_templates["widget-button"])
				.find("span").addClass("fa-close").end()
				.appendTo(this.$.find("widget-title"));
		}
		if (!("retractable" in widget) && widget.template.constructor === QueryForm) {
			widget.retractable = true;
		}
		if (widget.retractable) {
			$(IPY_templates["widget-button"])
				.find("span").addClass("fa-minus").end()
				.appendTo(this.$.find("widget-title"));
			this.$.find("widget-title").on("click", function() {
				$(this)
					.find(".sizeBtn > span").toggleClass("fa-plus fa-minus").end()
					.next().slideToggle("fast");
			}).trigger("click");
		}
		if (widget.fullscreen) {
			$(IPY_templates["widget-button"])
				.find("span").addClass("fa-arrows-alt").end()
				.appendTo(this.$.find("widget-title"));
			this.$.find("widget-title > .sizeBtn > .fa-arrows-alt").on("click", function() {
				if (document.fullscreenEnabled) {
					var element = document.fullscreenElement;
					if (element) {
						document.exitFullscreen();
					}
					if (element !== this) {
						this.requestFullscreen();
					}
				}
			}.bind(this.$[0]));
		}
	};
	
	Widget.prototype.close = function() {
		var $widget = this.$;
		if (Modernizr.cssanimations) {
			$widget.removeClass("fadeInDownBig").addClass("flipOutX");
			setTimeout(function() {
				popupClose();
				$widget.remove();
			}, 900);
		} else {
			popupClose();
			$widget.remove();
		}
	};

	//创建表单模板（input数组和button数组）
	window.SimpleForm = function(inputs, buttons, args) {
		this.$ = $(IPY_templates["simple-form"]);
		this.$input_wrapper = {};
		this.$input = {};
		var $input_wrapper = this.$.find(".form-input-wrapper");
		inputs && inputs.forEach(function(input) {
			var id = input.id || getRandomId();
			var $form_item, $input;
			if (input.type === "select") {
				$form_item = $(IPY_templates["simple-form-select"]).appendTo($input_wrapper);
				$input = $form_item.find("select");
				var select = $input[0];
				var select_options = select.options;
				if (input.options) {
					for (var value in input.options) {
						if (input.options.hasOwnProperty(value)) {
							select_options.add(new Option(input.options[value], value));
						}
					}
				}
				if (input.optionsAJAX) {
					list_ajax.insertSelect($input, input.optionsAJAX).done(function() {
						if ("value" in input) {
							$input.val(input.value);
						}
					});
				}
			} else if (input.type === "textarea") {
				$form_item = $(IPY_templates["simple-form-textarea"])
					.addClass("auto-height").appendTo($input_wrapper);
				$input = $form_item.find("textarea");
			} else if (input.type === "file") {
				$form_item = $(IPY_templates["simple-form-file"]).appendTo($input_wrapper);
				$input = $form_item.find("input");
				var filetype = input.filetype ? input.filetype + "/*" : "*";
				$form_item.find(".input-item")
					.on("dragenter dragover dragleave drop", function(event) {
						event.preventDefault();
					})
					.on("drop", function(event) {
						var file = event.originalEvent.dataTransfer.files[0];
						if (file && input.filetype && file.type.indexOf(input.filetype) === 0) {
							$(this).find("input").val(file);
						}
					})
					.children("button").on("click", function() {
						$(this).siblings("input").trigger("click");
					}).end();
				$input.attr("accept", filetype).on("change", function() {
					$(this).val(this.files[0] || null);
				});
			} else if (input.type === "dual-select") {
				$form_item = $(IPY_templates["simple-form-dual-select"])
					.addClass("auto-height").appendTo($input_wrapper);
				$input = $form_item.find("dual-select");
				new DualSelect($input);
				$input.toggleClass("paginate", !!input.paginate);
			} else {
				$form_item = $(IPY_templates["simple-form-input"].replace(/<input>/, '<input type="' + input.type + '">'))
					.appendTo($input_wrapper);
				$input = $form_item.find("input");
			}
			if (!/^(file|dual-select)$/.test(input.type)) {
				$form_item.find("label").attr("for", id);
				$input.attr({
					name: input.name,
					id: id
				});
			}
			if (/^(two-column|full|place-full)$/.test(input.size)) {
				$form_item.addClass("input-wrapper-full");
			}
			$form_item
				.toggleClass("required", !!input.required)
				.css("display", input.display)
				.find("label").text(input.label + "：").end()
				.find(".input-item").addClass("input-" + (input.size || "large"));
			$input
				.addClass(input.classes)
				.attr("name", input.name || "")
				.attr(input.attributes || {})
				.prop({
					disabled: input.disabled,
					readonly: input.readonly
				});
			if ("value" in input) {
				$input.val(input.value);
			}
			var verify = [];
			input.required && verify.push(formVerify.required);
			if (input.verify) {
				if (Array.isArray(input.verify)) {
					Array.prototype.push.apply(verify, input.verify);
				} else {
					verify.push(input.verify);
				}
			}
			$form_item.data("form-verify", verify);
			this.$input_wrapper[$input.attr("name")] = $form_item;
			this.$input[$input.attr("name")] = $input;
		}, this);
		var $button_wrapper = this.$.find(".button-wrapper");
		buttons && buttons.forEach(function(button) {
			var $button = $("<button>").addClass("btn btn-primary").addClass(button.classes).attr({
				type: button.type || "button",
				id: button.id
			}).attr(button.attributes || {}).appendTo($button_wrapper);
			if (button.icon) {
				$button.html('<span class="fa fa-' + button.icon + '"></span> ' + button.text);
			} else {
				$button.text(button.text);
			}
		});
		
		if (args && args.autosubmit) {
			this.$.on("submit", function() {
				ajaxFormSubmit($.extend({
					data: this
				}, args.autosubmit));
			});
		}
	};

	//创建查询表格模板
	window.QueryForm = function(inputs, buttons) {
		inputs && inputs.forEach(function(input) {
			input.size = input.size || "medium";
		});
		buttons && buttons.forEach(function(button) {
			if (button.type === "submit") {
				button.icon = button.icon || "search";
			}
			if (button.type === "reset") {
				button.icon = button.icon || "undo";
			}
		});
		SimpleForm.call(this, inputs, buttons);
	};

	//创建富文本表单模板
	window.RichEditForm = function(inputs, buttons) {
		SimpleForm.call(this, inputs, buttons);
	};
	
	window.PopupForm = function(inputs, buttons, args) {
		if (args && args.autosubmit) {
			args.autosubmit.success = args.autosubmit.success || function() {
				$(".modify-box .sizeBtn").trigger("click");
			};
		}
		SimpleForm.call(this, inputs, buttons, args);
	};
	
	window.MediaWidget = function() {
		this.$ = $();
	};

	//创建表格模板
	window.DynamicTable = function(columns, args) {
		this.page_index = 1;
		this.page_size = args.page_size || 10;
		this.total_rows = 0;
		this.url = args.url;
		this.table_data = args.table_data || Function.self;
		this.post_args = {};
		this.$query_form = (args.$query_form || $()).on("submit", function() {
			this.query();
		}.bind(this));
		this.autofix = ("autofix" in args) ? !!args.autofix : true;
		this.columns = columns;
		this.display = [];
		this.$ = $('<div class="table-wrapper"></div>');
		columns.forEach(function(column) {
			if (!("map_func" in column)) {
				column.map_func = function(data) {
					return data[column.index];
				};
			} else if (!Function.isFunction(column.map_func)) {
				var map = column.map_func;
				column.map_func = function() {
					return map;
				};
			}
		});
		var $table = $("<table>").appendTo(this.$);
		var $colgroup = $("<colgroup>").appendTo($table);
		var $thead = $("<thead>").appendTo($table);
		var $tbody = $("<tbody>").appendTo($table);
		var $thead_row = $("<tr>").appendTo($thead);
		columns.forEach(function(column) {
			$("<col>").css("width", column.width).appendTo($colgroup);
			$("<th>").css("display", column.display).text(column.title).appendTo($thead_row);
		}, this);
		this.display = columns.map(function(column) {
			return column.display || "";
		});
		if (this.table_data === undefined) {
			this.table_data = Function.self;
		} else if (!Function.isFunction(this.table_data)) {
			var index = this.table_data;
			this.table_data = function(data) {
				return data[index];
			};
		}
		this.generate_paginate();
		this.$
			.on("click", ".btn.modify", function() {
				$(this).closest("tr").trigger("modify");
			})
			.on("click", ".btn.delete", function() {
				$(this).closest("tr").trigger("delete");
			})
			.on("click", ".paginate-nav .first:not(.disabled)", function() {
				this.page_index = 1;
				this.refresh();
			}.bind(this))
			.on("click", ".paginate-nav .previous:not(.disabled)", function() {
				--this.page_index;
				this.refresh();
			}.bind(this))
			.on("click", ".paginate-nav .next:not(.disabled)", function() {
				++this.page_index;
				this.refresh();
			}.bind(this))
			.on("click", ".paginate-nav .last", function() {
				this.page_index = Math.floor((this.total_rows - 1) / this.page_size) + 1;
				this.refresh();
			}.bind(this))
			.on("click", ".paginate-nav .page-indexes > :not(.current)", function(event) {
				this.page_index = parseInt($(event.target).text());
				this.refresh();
			}.bind(this));
		if ("delete" in args) {
			var delete_args = args["delete"];
			var table = this;
			this.$.on("delete", "tr", function() {
				var $tr = $(this);
				jConfirm("是否确认删除此条信息", "删除信息确认", function(result) {
					if (result) {
						ajaxFormSubmit({
							url: delete_args.url,
							data: delete_args.data($tr.data("data")),
							success: function() {
								if (Modernizr.cssanimations) {
									$tr.addClass("animate7 hinge");
									setTimeout(function() {
										table.refresh();
									}, 1400);
								} else {
									table.refresh();
								}
							}
						});
					}
				});
			});
		}
	};

	DynamicTable.prototype.aoData = function() {
		return JSON.stringify([{
			name: "iDisplayStart",
			value: this.page_index
		}, {
			name: "iDisplayLength",
			value: this.page_size
		}]);
	};

	DynamicTable.prototype.refresh = function() {
		var data = $.extend({
			aoData: this.aoData()
		}, this.post_args);
		$.ajax2({
			url: this.url,
			method: "post",
			data: data,
			success: function(data) {
				if (data.respCode === "00") {
					if (this.autofix && !this.table_data(data.content).length) {
						var total_pages = Math.floor((data.content.total - 1) / this.page_size) + 1;
						if (total_pages < this.page_index && this.page_index != 1) {
							this.page_index = Math.max(total_pages, 1);
							this.refresh();
							return;
						}
					}
					var $tbody = this.$.find("tbody").empty();
					this.table_data(data.content).forEach(function(data) {
						var $row = $("<tr>").data("data", data).appendTo($tbody);
						this.columns.forEach(function(column, index) {
							$("<td>")
								.css("display", this.display[index])
								.html(column.map_func(data))
								.appendTo($row);
						}, this);
					}, this);
					this.total_rows = data.content.total;
					this.generate_paginate();
				} else {
					jAlert(data.errorInfo, "信息提示");
				}
			}.bind(this)
		});
	};

	DynamicTable.prototype.query = function() {
		var table = this;
		table.$query_form.find("[name]").each(function() {
			table.post_args[$(this).attr("name")] = $(this).val();
		});
		table.refresh();
	};

	DynamicTable.prototype.generate_paginate = function() {
		this.$.find(".paginate").remove();
		this.$.append(IPY_templates["dynamic-table-paginate"]);
		var total_pages = Math.floor((this.total_rows - 1) / this.page_size) + 1;
		this.page_index = Math.min(this.page_index - 1, total_pages) + 1;
		this.$.find(".total-rows").text(this.total_rows);
		this.$.find(".begin-row").text(this.page_size * (this.page_index - 1) + 1);
		this.$.find(".end-row").text(Math.min(this.total_rows, this.page_size * this.page_index));
		if (this.page_index == 1) {
			this.$.find(".first, .previous").addClass("disabled");
		}
		if (this.page_index >= total_pages) {
			this.$.find(".next, .last").addClass("disabled");
		}
		var $page_index = this.$.find(".page-indexes");
		$page_index.append('<span class="paginate-button current">' + this.page_index + '</span>');
		for (var i = 1; i <= 4; ++i) {
			if (this.page_index >= i + 1) {
				$page_index.prepend('<span class="paginate-button">' + (this.page_index - i) + '</span>');
			}
			if (this.page_index <= total_pages - i) {
				$page_index.append('<span class="paginate-button">' + (this.page_index + i) + '</span>');
			}
		}
	};

	window.DualSelect = function($select) {
		this.$ = $select;
		this.$unselected = $select.children(".unselected-list");
		this.$selected = $select.children(".selected-list");
		$select.data("dual-select", this);
		$select.on("click", "> ul > li:not(.disabled)", function() {
			$(this).toggleClass("active");
		});
		var dual_select = this;
		var remove_same = function($list, moving_length) {
			$list = $list.children();
			var original_length = $list.length - moving_length;
			for (var i = $list.length; --i >= original_length;) {
				for (var j = 0; j < original_length; ++j) {
					if ($list.eq(i).data("value") === $list.eq(j).data("value")) {
						$list.eq(i).remove();
						break;
					}
				}
			}
		};
		$select.children(".button-group").children()
			.eq(0).on("click", function() {
				var $moving = dual_select.$unselected.children(".active").removeClass("active");
				dual_select.$selected.append($moving);
				remove_same(dual_select.$selected, $moving.length);
			}).end()
			.eq(1).on("click", function() {
				var $moving = dual_select.$unselected.children().removeClass("active");
				dual_select.$selected.append($moving);
				remove_same(dual_select.$selected, $moving.length);
			}).end()
			.eq(2).on("click", function() {
				var $moving = dual_select.$selected.children(".active").removeClass("active");
				dual_select.$unselected.append($moving);
				remove_same(dual_select.$unselected, $moving.length);
			}).end()
			.eq(3).on("click", function() {
				var $moving = dual_select.$selected.children(":not(.disabled)").removeClass("active");
				dual_select.$unselected.append($moving);
				remove_same(dual_select.$unselected, $moving.length);
			});
	};
	DualSelect.prototype.add_to_unselected = function(array) {
		array.forEach(function(item) {
			$("<li>").data("value", item.value).append(item.html).appendTo(this);
		}, this.$unselected);
	};
	DualSelect.prototype.add_to_selected = function(array) {
		array.forEach(function(item) {
			$("<li>").data("value", item.value).append(item.html).appendTo(this);
		}, this.$selected);
	};
	DualSelect.prototype.clear_unselected = function() {
		this.$.children(".unselected-list").empty();
	};
	DualSelect.prototype.clear_selected = function() {
		this.$.children(".selected-list").empty();
	};
	
	$.extend($.valHooks, {
		"dual-select": {
			get: function(element) {
				return $(element).find("> .selected-list > li:not(.disabled)").map(function() {
					return $(this).data("value");
				}).get().toString();
			}
		},
		file: {
			get: function(element) {
				return $(element).data("file");
			},
			set: function(element, file) {
				if (!file) {
					element.value = "";
				}
				$(element).data("file", file || null);
				file_change.call(element, file);
				return file;
			}
		}
	});

	var file_change = function(file) {
		var file_name = file ? file.name : "";
		var last_dot = file_name.lastIndexOf(".");
		if (last_dot > 0) {
			var name = file_name.slice(0, last_dot);
			var extension = file_name.slice(last_dot);
		} else {
			var name = file_name;
			var extension = "";
		}
		$(this).siblings(".file-name")
			.children(".name").text(name).end()
			.children(".extension").text(extension).end()
			.trigger("resize")
		.end()
		.trigger("filechange");
	};

	$(document).on("resize", ".input-item.file-item > .file-name", function() {
		var $this = $(this);
		$this.children(".name").css("max-width", $this.width() - $this.children(".extension").width() - 5 + "px");
	}).on("click", ".sizeBtn:not(main .sizeBtn) > .fa-close", function() {
		$(this).closest("widget").data("widget").close();
	}).on("click", "#blackBg.removeable", function() {
		$(document.body).children("widget").data("widget").close();
	});
})(jQuery);