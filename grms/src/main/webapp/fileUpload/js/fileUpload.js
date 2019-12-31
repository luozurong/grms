/**
 * 页面单按钮上传
 */
function upload(){
	var formData = new FormData($("#upfileForm")[0]);
	//formData.set('file', document.getElementById("upload").files[0]);
    var url = "upload";
	$.ajax({
        url: url,
        type: 'POST',
        cache: false,
        data:formData,
        processData: false,
        contentType: false,
        dataType: "json"
    }).done(function(data) {
        if(data.result=='true'){//文件上传成功
        	//文件url
        	var path=data.path;
        	$('#img1').attr('src',path);//显示图片，demo
        }else{
        	alert('文件上传失败');
        }
    });
}


/**
 * 页面中多个上传按钮上传
 * 注意：主要是确保表单中只有一个file文件域的name的名称是“file”,这个名称与后台controller接受的参数名对应
 */
function uploads(obj){
	//清空其他file域的name属性 
	$('.file_input').attr('name','');
	//当前操作的file域设置name属性
	$(obj).attr('name','file');
	var formData = new FormData($("#upfileForm")[0]);
	//formData.set('file', document.getElementById("upload").files[0]);
    var url = "upload";
	$.ajax({
        url: url,
        type: 'POST',
        cache: false,
        data:formData,
        processData: false,
        contentType: false,
        dataType: "json"
    }).done(function(data) {
        if(data.result=='true'){//文件上传成功
        	//文件url
        	var path=data.path;
        	//文件名
        	var fileName=data.fileName;
        	//父节点
        	var pNode=$(obj).parent();
        	//alert($(pNode).html());
        	//获取tr节点
        	var trNode=$(pNode).parent();
        	//alert($(trNode).html());
        	//获取tr下第一个节点
        	var td1=$(trNode).children()[0];
        	$(td1).append('<a target="_blank" href="'+path+'">'+fileName+'</a><br/>');////显示图片，demo
        }else{
        	alert('文件上传失败');
        }
    });
}