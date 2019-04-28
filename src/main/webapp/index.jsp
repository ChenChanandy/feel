<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="js/kindeditor/themes/default/default.css" />
<script src="js/kindeditor/kindeditor-all.js"></script>
<script src="js/kindeditor/lang/zh_CN.js"></script>
<script>
	KindEditor.ready(function(K) {
		var editor = K.editor({
			allowFileManager : true,
			uploadJson:'upload'
		});
		editor = K.create('textarea[name="feel"]', {
			uploadJson:'upload'
		});
		K('#J_selectImage').click(function() {
			editor.loadPlugin('multiimage', function() {
				editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						var div = K('#J_imageView');
						div.html('');
						K.each(urlList, function(i, data) {
							div.append('<img src="' + data.url + '" width="160" height="90">');
							div.append('<input type="hidden" name="imgs" value="'+ data.url +'"/>');
						});
						editor.hideDialog();
					}
				});
			});
		});
	});
</script>

</head>
<body>
	<form action="insert" method="post">
		标题：<input type="text" name=title /><br /> 
		图片： <input type="button" id="J_selectImage" value="批量上传" />
		<div id="J_imageView"></div>
		感受： <textarea name="feel" style="width:700px;height:200px;visibility:hidden;"></textarea>
		<input type="submit"value="发表" />
	</form>
</body>
</html>