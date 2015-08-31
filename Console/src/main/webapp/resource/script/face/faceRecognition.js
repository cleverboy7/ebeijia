$(document).on("face-recognition", ".container", function() {
	var form = new SimpleForm([{
		type: "file",
		label: "选择照片",
		name: "file",
		filetype: "image",
		required: true
	}], [{
		type: "submit",
		text: "识别",
		icon: "eye"
	}]);
	new Widget({
		title: "人脸识别",
		template: form
	});

	$$("widget-content").prepend('<div class="face"><div class="face-img"><div><img src="' + resourceURL + '/image/face.jpg" alt="" /></div></div><div class="face-content"></div></div>');
	var dl = $("<dl></dl>").append("<dt name='age'>年龄：</dt><dd></dd><dt name='person'>人种：</dt><dd></dd><dt name='sex'>性别：</dt><dd></dd><dt name='smile'>微笑：</dt><dd></dd><dt name='info'>信息：</dt><dd></dd>");
	$$(".face-content").append(dl);
	form.$input.file.on("filechange", function() {
		var file = $(this).val();
		if (!file) {
			$$(".face-img img").attr("src", resourceURL + "/image/face.jpg");
		} else {
			$$(".face-img img").attr("src", URL.createObjectURL(file));
		}
		$$(".face-content dd").text("");
	})

	$$(".face-img").on("click", function() {
		form.$input.file.trigger("click");
	}).on("dragenter dragover dragleave drop", function(event) {
		event.preventDefault();
	}).on("drop", function(event) {
		form.$input.file.closest(".input-item").trigger(event);
	});

	form.$.on("submit", function() {
		ajaxFormSubmit({
			url: "face.html",
			data: this,
			successAlert: false,
			success: function(obj) {
				$$(".face-content dt[name='age']+dd").text(obj.age);
				$$(".face-content dt[name='person']+dd").text(obj.person);
				$$(".face-content dt[name='sex']+dd").text(obj.sex);
				$$(".face-content dt[name='smile']+dd").text(obj.smile);
				$$(".face-content dt[name='info']+dd").text(obj.info);
			}
		});
	});
});