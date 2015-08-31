jQuery(document).ready(function($) {
	$(".topmenu a").on("click", function() {
		$.switch_page($('body > nav a[href="' + $(this).attr("href") + '"]'));
	});

	$("body > header .changePwd").on("click", function() {
		lockScreen();
		var form = new PopupForm([{
			type: "password",
			label: "原密码",
			name: "oldPwd"
		}, {
			type: "password",
			label: "新密码",
			name: "newPwd"
		}, {
			type: "password",
			label: "确认密码",
			name: "checkPwd"
		}], [{
			type: "submit",
			text: "修改",
			icon: "pencil-square"
		}]);
		var widget = new Widget({
			title: "修改密码",
			template: form
		});
		widget.$.addClass("modify-password animate6");
		form.$.on("submit", function() {
			var oldPwd = form.$input.oldPwd.val();
			var newPwd = form.$input.newPwd.val();
			var checkPwd = form.$input.checkPwd.val();
			if (newPwd != checkPwd) {
				jAlert("请确认两次输入的密码是否一致！", "信息提示");
				return;
			}
			if (newPwd.length < 6) {
				jAlert("密码必须不小于6位！", "信息提示");
				return;
			}
			$.ajax2({
				url: "wechat/admin/passUpd.html",
				method: "post",
				data: {
					adminId: $("body > header .user-info").attr("user-id"),
					oldPwd: oldPwd,
					newPwd: newPwd
				},
				success: function(msg) {
					if (msg.respCode != "00") {
						jAlert(msg.errorInfo, "信息提示");
					} else {
						jAlert("修改成功！请重新登录", "信息提示", function() {
							window.location.href = ".";
						});
					}
				},
				error: function() {
					jAlert("网络错误！", "信息提示");
				}
			});
		});
	});
	$("body > header .logout").on("click", function() {
		jConfirm("确定要退出？", "信息提示", function(result) {
			if (result) {
				$.ajax2({
					url: "logout.html",
					method: "post",
					success: function(data) {
						if (data.respCode === "00") {
							location.href = ".";
						}
					}
				});
			}
		});
	});
});