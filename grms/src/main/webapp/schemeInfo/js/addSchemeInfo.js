
function goback(){
	window.location.href="../schemeInfo/list.html";
}

//同步上传附件
function uploadFile(fileId){
	loadingShow("正在上传中，请勿进行其他操作");
	wrapMaskShow();
	var file = $("#" + fileId);
//	var	patrn = /^.+(xlsx|XLSX|xls|XLS|doc|docx|DOC|pdf|PDF|txt|TXT)$/i;
//	if(!patrn.exec(file.val())){
//		loadingShow(false);
//		infoMask('请上传Excel、doc、txt或pdf文件');
//		$("#" + fileId).val('');
//		return;
//  	}
	var val = file.val();
	if(!val || val == ''){
		$('#fileName').val('');
		loadingShow(false);
		wrapMaskHide();
		return;
	}
	if(file[0].files[0].size > 123289600){
		loadingShow(false);
		wrapMaskHide();
		infoMask('文件大小不能超过100M');
		$("#" + fileId).val('');
		return;
	}
	$('#fileName').val(file.val().substring(file.val().lastIndexOf('\\') + 1));
	loadingShow(false);
	wrapMaskHide();
}

function saveSchemeInfo(){
	loadingShow("正在保存，请勿进行其他操作");
	$('#nameNotNull').hide();
	 //对表单数据进行验证
	var name = $('#name').val();
	if(!name){
		loadingShow(false);
		$('#nameNotNull').show();
		return;
	}
	var file = $('#file');
	if(!file || file.val() == ''){
		infoMask('请上传附件');
		loadingShow(false);
		return;
	}
	var formData = new FormData();
	formData.append("file", file[0].files[0]); 
	formData.append("name",name);
	$.ajax({
		url: "../schemeInfo/save",  
		type: "POST",  
		data: formData,  
		contentType: false,
		processData: false,
		success: function(data){
			var code = data.code;
			if(code == 0){
				loadingShow(false);
				infoMask('操作成功');
				window.location.href = "../schemeInfo/list.html";
				
			}else if(code == 1){
				loadingShow(false);
				infoMask('提案名称已经存在');
			}else {
				loadingShow(false);
				infoMask('保存失败:服务器响应异常');
				
			}
		},  
		error: function(){
			loadingShow(false);
			infoMask('网络异常，请稍后重试！');
		}
	});
}