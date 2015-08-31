"use strict";

(function($, window, document, undefined) {
	Array.isArray = Array.isArray || function(array) {
		return Object.prototype.toString.call(array) === "[object Array]";
	};

	Array.prototype.indexOf = Array.prototype.indexOf || function(searchElement, fromIndex) {
		if (this == null) {
			throw new TypeError('"this" is null or not defined');
		}
		var O = Object(this);
		var len = O.length >>> 0;
		if (len === 0) {
			return -1;
		}
		var n = +fromIndex || 0;
		if (Math.abs(n) === Infinity) {
			n = 0;
		}
		if (n >= len) {
			return -1;
		}
		for (var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0); k < len; ++k) {
			if (k in O && O[k] === searchElement) {
				return k;
			}
		}
		return -1;
	};

	Array.prototype.forEach = Array.prototype.forEach || function(callback, thisArg) {
		var T;
		if (this == null) {
			throw new TypeError("this is null or not defined");
		}
		var O = Object(this);
		var len = O.length >>> 0;
		if (typeof callback !== "function") {
			throw new TypeError(callback + " is not a function");
		}
		if (arguments.length > 1) {
			T = thisArg;
		}
		for (var k = 0; k < len; ++k) {
			if (k in O) {
				callback.call(T, O[k], k, O);
			}
		}
	};

	Array.prototype.map = Array.prototype.map || function(callback, thisArg) {
		var T;
		if (this == null) {
			throw new TypeError(" this is null or not defined");
		}
		var O = Object(this);
		var len = O.length >>> 0;
		if (typeof callback !== "function") {
			throw new TypeError(callback + " is not a function");
		}
		if (arguments.length > 1) {
			T = thisArg;
		}
		var A = new Array(len);
		for (var k = 0; k < len; ++k) {
			var kValue, mappedValue;
			if (k in O) {
				A[k] = callback.call(T, O[k], k, O);
			}
		}
		return A;
	};

	Array.prototype.filter = Array.prototype.filter || function(callback, thisArg) {
		var T;
		if (this == null) {
			throw new TypeError(" this is null or not defined");
		}
		var O = Object(this);
		var len = O.length >>> 0;
		if (typeof callback !== "function") {
			throw new TypeError();
		}
		var A = [];
		if (arguments.length > 1) {
			T = thisArg;
		}
		for (var k = 0; k < len; ++k) {
			if (k in O) {
				var val = O[k]
				if (callback.call(T, val, k, O)) {
					A.push(val);
				}
			}
		}
		return A;
	};

	Array.prototype.reduce = Array.prototype.reduce || function(callback) {
		if (this == null) {
			throw new TypeError("Array.prototype.reduce called on null or undefined");
		}
		if (typeof callback !== "function") {
			throw new TypeError(callback + " is not a function");
		}
		var O = Object(this),
			len = O.length >>> 0,
			k = 0,
			value;
		if (arguments.length == 2) {
			value = arguments[1];
		} else {
			while (k < len && !(k in O)) {
				++k;
			}
			if (k >= len) {
				throw new TypeError("Reduce of empty array with no initial value");
			}
			value = O[k++];
		}
		for (; k < len; ++k) {
			if (k in O) {
				value = callback(value, O[k], k, O);
			}
		}
		return value;
	};

	Array.prototype.every = Array.prototype.every || function(callback, thisArg) {
		var T;
		if (this == null) {
			throw new TypeError("this is null or not defined");
		}
		var O = Object(this);
		var len = O.length >>> 0;
		if (typeof callback !== "function") {
			throw new TypeError(callback + " is not a function");
		}
		if (arguments.length > 1) {
			T = thisArg;
		}
		for (var k = 0; k < len; ++k) {
			if (k in O && !callback.call(T, O[k], k, O)) {
				return false;
			}
		}
		return true;
	};

	Array.prototype.some = Array.prototype.some || function(callback, thisArg) {
		var T;
		if (this == null) {
			throw new TypeError("this is null or not defined");
		}
		var O = Object(this);
		var len = O.length >>> 0;
		if (typeof callback !== "function") {
			throw new TypeError(callback + " is not a function");
		}
		if (arguments.length > 1) {
			T = thisArg;
		}
		for (var k = 0; k < len; ++k) {
			if (k in O && callback.call(T, O[k], k, O)) {
				return true;
			}
		}
		return false;
	};

	Array.prototype.binarySearch = function(value, compare) {
		compare = compare || function(x, y) {
			if (x < y) {
				return -1;
			} else if (x > y) {
				return 1;
			} else {
				return 0;
			}
		};
		var left = 0,
			right = this.length - 1;
		while (left <= right) {
			var mid = (left + right) >>> 1;
			var comparison = compare(this[mid], value);
			if (comparison < 0) {
				left = mid + 1;
			} else if (comparison > 0) {
				right = mid - 1;
			} else {
				return true;
			}
		}
		return false;
	};

	Array.merge = function(array1, array2, compare) {
		compare = compare || function(x, y) {
			if (x < y) {
				return -1;
			} else if (x > y) {
				return 1;
			} else {
				return 0;
			}
		};
		var result = [];
		var len1 = array1.length,
			len2 = array2.length;
		var i = 0,
			j = 0;
		while (i < len1 && j < len2) {
			var comparison = compare(array1[i], array2[j]);
			if (comparison <= 0) {
				result.push(array1[i++]);
			} else {
				result.push(array2[j++]);
			}
		}
		Array.prototype.push.apply(result, array1.slice(i));
		Array.prototype.push.apply(result, array2.slice(j));
		return result;
	};

	Function.isFunction = Function.isFunction || function(func) {
		return Object.prototype.toString.call(func) === "[object Function]";
	};

	Function.prototype.bind = Function.prototype.bind || function(oThis) {
		if (typeof this !== "function") {
			throw new TypeError("Function.prototype.bind - what is trying to be bound is not callable");
		}

		var aArgs = Array.prototype.slice.call(arguments, 1);
		var fToBind = this;
		var fNOP = function() {};
		var fBound = function() {
			return fToBind.apply(this instanceof fNOP ? this : oThis,
				aArgs.concat(Array.prototype.slice.call(arguments)));
		};

		fNOP.prototype = this.prototype;
		fBound.prototype = new fNOP();

		return fBound;
	};

	Function.nothing = function() {};
	Function.self = function(x) {
		return x;
	};

	String.prototype.utf8Length = function() {
		var length = 0;
		for (var i = 0, len = this.length; i < len; ++i) {
			var c = this.charCodeAt(i);
			if (c < 0x80) {
				++length;
			} else if (c < 0x800) {
				length += 2;
			} else if (c < 0x10000) {
				length += 3;
			} else {
				length += 4;
			}
		}
		return length;
	};

	//将字符串转换为日期
	String.prototype.parseDate = function() {
		if (this.length == 8) {
			return this.slice(0, 4) + "-" + this.slice(4, 6) + "-" + this.slice(6, 8);
		} else if (this.length == 6) {
			return this.slice(0, 2) + ":" + this.slice(2, 4) + ":" + this.slice(4, 6);
		} else if (this.length >= 12) {
			return this.slice(0, 4) + "-" + this.slice(4, 6) + "-" + this.slice(6, 8) + " " + this.slice(8, 10) + ":" + this.slice(10, 12);
		}
	};

	//全屏
	document.fullscreenEnabled = document.fullscreenEnabled || document.msFullscreenEnabled || document.mozFullScreenEnabled || document.webkitFullscreenEnabled || false;
	Element.prototype.requestFullscreen = Element.prototype.requestFullscreen || Element.prototype.msRequestFullscreen || Element.prototype.mozRequestFullScreen || Element.prototype.webkitRequestFullscreen || function() {};
	if (!("fullscreenElement" in document)) {
		Object.defineProperty(document, "fullscreenElement", {
			get: function() {
				return document.msFullscreenElement || document.mozFullScreenElement || document.webkitFullscreenElement || null;
			}
		})
	}
	document.exitFullscreen = document.exitFullscreen || document.msExitFullscreen || document.mozCancelFullScreen || document.webkitExitFullscreen || function() {};

	//锁屏
	window.lockScreen = function(removeable) {
		if (arguments.length === 0) {
			removeable = true;
		}
		$('<div id="blackBg"></div>').toggleClass("removeable", !!removeable).appendTo(document.body);
	};

	//清除锁屏  
	window.popupClose = function() {
		$("#blackBg:last").remove();
	};

	//图表--显示提示框
	window.showTooltip = function(x, y, contents) {
		$('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css({
			position: "fixed",
			display: "none",
			top: y + 5,
			left: x + 5
		}).appendTo(document.body).fadeIn(200);
	};

	//ajax2
	$.ajax2 = function(args) {
		args.url = projectURL + args.url;
		var original_success = args.success;
		var original_error = args.error;
		args.timeout = 10000;
		delete args.dataType;
		if (window.FormData && args.data instanceof FormData) { // 当类型为FormData时
			args.processData = false; // 告诉jQuery不要去处理发送的数据
			args.contentType = false; // 告诉jQuery不要去设置Content-Type请求头
		}
		args.success = function(data) {
			if (Object.prototype.toString.call(data) === "[object String]") {
				jAlert("会话超时，请重新登录！", "信息提示", function() {
					location.href = ".";
				});
			}
			original_success(data);
		};
		args.error = function() {
			jAlert("哎呀出错了！~！！请重试", "错误提示");
			original_error && original_error.apply(null, arguments);
		};
		return $.ajax(args);
	};
	
	$.combine = function() {
		return Array.prototype.reduce.call(arguments, function(x, y) {
			return x.add(y);
		});
	};
	
	window.getRandomId = function() {
		return "randomId-" + ++getRandomId.randomId;
	};
	getRandomId.randomId = 0;
	
	window.cacheStorage = {
		data: {},
		hasItem: function(key) {
			return key in this.data;
		},
		getItem: function(key) {
			return this.data[key];
		},
		setItem: function(key, value) {
			this.data[key] = value;
		},
		removeItem: function(key) {
			delete this.data[key];
		},
		removeItems: function(regex) {
			regex = new RegExp(regex);
			for (var key in this.data) {
				if (this.data.hasOwnProperty(key) && regex.test(key)) {
					delete this.data[key];
				}
			}
		}
	};
})(jQuery, window, document);