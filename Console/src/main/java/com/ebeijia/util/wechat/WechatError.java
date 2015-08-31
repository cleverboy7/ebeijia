package com.ebeijia.util.wechat;



/**
 * 微信工具类
 * 
 * 
 */
public class WechatError {
	
	public static String checkCode(String code){
		String result = null;
		switch (code) {
			case "-1":result = "系统繁忙，请确认是否开通此功能";break;
			case "40001":result="获取access_token时AppSecret错误";break;
			case "40002":result="不合法的凭证类型";break;
			case "40003":result="不合法的OpenID";break;
			case "40004":result="不合法的媒体文件类型";break;
			case "40005":result="不合法的文件类型";break;
			case "40006":result="不合法的文件大小";break;
			case "40007":result="不合法的媒体文件id";break;
			case "40008":result="不合法的消息类型";break;
			case "40009":result="不合法的图片文件大小";break;
			case "40010":result="不合法的语音文件大小";break;
			case "40011":result="不合法的视频文件大小";break;
			case "40012":result="不合法的缩略图文件大小";break;
			case "40013":result="不合法的AppID";break;
			case "40014":result="不合法的access_token   ";break;     
			case "40015":result="不合法的菜单类型";break;
			case "40016":result="不合法的按钮个数";break;
			case "40017":result="不合法的按钮个数";break;
			case "40018":result="不合法的按钮名字长度";break;
			case "40019":result="不合法的按钮KEY长度";break;
			case "40020":result="不合法的按钮URL长度";break;
			case "40021":result="不合法的菜单版本号";break;
			case "40022":result="不合法的子菜单级数";break;
			case "40023":result="不合法的子菜单按钮个数";break;
			case "40024":result="不合法的子菜单按钮类型";break;
			case "40025":result="不合法的子菜单按钮名字长度";break;
			case "40026":result="不合法的子菜单按钮KEY长度";break;
			case "40027":result="不合法的子菜单按钮URL长度";break;
			case "40028":result="不合法的自定义菜单使用用户";break;
			case "40029":result="不合法的oauth_code";break;
			case "40030":result="不合法的refresh_token";break;
			case "40031":result="不合法的openid列表";break;
			case "40032":result="不合法的openid列表长度";break;
			case "40033":result="不合法的请求字符，不能包含\"uxxxx格式的字符";break;
			case "40035":result="不合法的参数";break;
			case "40038":result="不合法的请求格式";break;
			case "40039":result="不合法的URL长度";break;
			case "40050":result="不合法的分组id";break;
			case "40051":result="分组名字不合法";break;
			case "41001":result="缺少access_token参数";break;
			case "41002":result="缺少appid参数";break;
			case "41003":result="缺少refresh_token参数";break;
			case "41004":result="缺少secret参数";break;
			case "41005":result="缺少多媒体文件数据";break;
			case "41006":result="缺少media_id参数";break;
			case "41007":result="缺少子菜单数据";break;
			case "41008":result="缺少oauth code";break;
			case "41009":result="缺少openid";break;
			case "42001":result="access_token超时，请检查access_token的有效期";break;
			case "42002":result="refresh_token超时";break;
			case "42003":result="oauth_code超时";break;
			case "43001":result="需要GET请求";break;
			case "43002":result="需要POST请求";break;
			case "43003":result="需要HTTPS请求";break;
			case "43004":result="需要接收者关注";break;
			case "43005":result="需要好友关系";break;
			case "44001":result="多媒体文件为空";break;
			case "44002":result="POST的数据包为空";break;
			case "44003":result="图文消息内容为空";break;
			case "44004":result="文本消息内容为空";break;
			case "45001":result="多媒体文件大小超过限制";break;
			case "45002":result="消息内容超过限制";break;
			case "45003":result="标题字段超过限制";break;
			case "45004":result="描述字段超过限制";break;
			case "45005":result="链接字段超过限制";break;
			case "45006":result="图片链接字段超过限制";break;
			case "45007":result="语音播放时间超过限制";break;
			case "45008":result="图文消息超过限制";break;
			case "45009":result="接口调用超过限制";break;
			case "45010":result="创建菜单个数超过限制";break;
			case "45015":result="回复时间超过限制";break;
			case "45016":result="系统分组，不允许修改";break;
			case "45017":result="分组名字过长";break;
			case "45018":result="分组数量超过上限";break;
			case "45028":result="群发数量数量超过上限";break;
			case "46001":result="不存在媒体数据";break;
			case "46002":result="不存在的菜单版本";break;
			case "46003":result="不存在的菜单数据";break;
			case "46004":result="不存在的用户";break;
			case "47001":result="解析JSON/XML内容错误";break;
			case "48001":result="api功能未授权，请确认公众号已获得该接口";break;
			case "50001":result="用户未授权该api";break;
			case "61451":result="参数错误(invalid parameter)";break;
			case "61452":result="无效客服账号(invalid kf_account)";break;
			case "61453":result="客服帐号已存在(kf_account exsited)";break;
			case "61454":result="客服帐号名长度超过限制";break;
			case "61455":result="客服帐号名包含非法字符";break;
			case "61456":result="客服帐号个数超过限制(10个客服账号)";break;
			case "61457":result="无效头像文件类型";break;
			case "61450":result="系统错误";break;
			case "61500":result="日期格式错误";break;
			case "61501":result="日期范围错误";break;
			case "9001001":result="POST数据参数不合法";break;
			case "9001002":result="远端服务不可用";break;
			case "9001003":result="Ticket不合法";break;
			case "9001004":result="获取摇周边用户信息失败";break;
			case "9001005":result="获取商户信息失败";break;
			case "9001006":result="获取OpenID失败";break;
			case "9001007":result="上传文件缺失";break;
			case "9001008":result="上传素材的文件类型不合法";break;
			case "9001009":result="上传素材的文件尺寸不合法";break;
			case "9001010":result="上传失败";break;
			case "9001020":result="帐号不合法";break;
			case "9001021":result="已有设备激活率低于50%，不能新增设备";break;
			case "9001022":result="设备申请数不合法，必须为大于0的数字";break;
			case "9001023":result="已存在审核中的设备ID申请";break;
			case "9001024":result="一次查询设备ID数量不能超过50";break;
			case "9001025":result="设备ID不合法";break;
			case "9001026":result="页面ID不合法";break;
			case "9001027":result="页面参数不合法";break;
			case "9001028":result="一次删除页面ID数量不能超过10";break;
			case "9001029":result="页面已应用在设备中，请先解除应用关系再删除";break;
			case "9001030":result="一次查询页面ID数量不能超过50";break;
			case "9001031":result="时间区间不合法";break;
			case "9001032":result="保存设备与页面的绑定关系参数错误";break;
			case "9001033":result="门店ID不合法";break;
			case "9001034":result="设备备注信息过长";break;
			case "9001035":result="设备申请参数不合法";break;
			case "9001036":result="查询起始值begin不合法";break;
			//自定义
			case "8888888":result="未知错误";break;
			case "9999999":result="获取token失败";break;
		}
		return result;
	}
}
