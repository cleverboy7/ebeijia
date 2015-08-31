//消息管理--微信接收消息查询
$(document).on("wechat-reqMsg-query", ".container", function() {
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
		title: "公众微信号",
		index: "toUsrName"
	}, {
		title: "发送账号",
		index: "fromUsrName"
	}, {
		title: "消息类型",
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
			}[data.msgType] || data.msgType;
		}
	}, {
		title: "类型参数一",
		width: "25%",
		map_func: function(data) {
			return data[{
				text: "content",
				image: "picUrl",
				location: "label",
				voice: "format",
				event: "eventType",
			}[data.msgType]] || "未知的消息类型";
		}
	}, {
		title: "类型参数二",
		map_func: function(data) {
			return data.msgType === "event" ? data.eventKey : "";
		}
	}], {
		url: "wechat/reqMsg/query.html",
		table_data: "reqMsgList",
		$query_form: query_form.$
	});
	new Widget({
		title: "接收消息列表",
		template: table
	});
	table.refresh();
})