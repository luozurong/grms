/* This function is used to change the style class of an element */

/* This function is used when press enter key in the textfield of page navigation bar. */
function getPageUrl(firstPageLink, givenPage, lastPage) {
	var pageIndex = 0;
	if (firstPageLink.indexOf('p=') != -1) {
		pageIndex = firstPageLink.indexOf('p=') + 2;
	} else if (firstPageLink.indexOf('page=') != -1) {
		pageIndex = firstPageLink.indexOf('page=') + 5;
	}
	var beforePageStr = firstPageLink.substring(0, pageIndex);
	var nextParaIndex = firstPageLink.indexOf('&', pageIndex);
	var afterPageStr = null;
	if (nextParaIndex == -1) {
		afterPageStr = '';
	} else {
		afterPageStr = firstPageLink.substring(nextParaIndex, firstPageLink.length);
	}
	lastPage = lastPage.replace(',', '');
	lastPage = parseInt(lastPage);
	if ((givenPage==null) || (givenPage.length==0) || isNaN(givenPage)) {
		givenPage = 1;
	} else {
		givenPage=parseInt(givenPage);
		if (givenPage < 1) {
			givenPage = 1;
		}
		if (givenPage > lastPage) {
			givenPage = lastPage;
		}
	}
	return beforePageStr + givenPage + afterPageStr;
}

//　　url 删除url ,新跳转url
function delChecked(url,newUrl){
	var msg="";
	$("input[type=checkbox]").each(function(){
		if($(this).attr("checked")){
			msg=msg+","+$(this).val();
		}
	});
	if(msg==""){
		msg="无选择";
		top.Dialog.alert(msg);		
		return false;
	}	
	top.Dialog.confirm("是否删除所选记录",function(){
		$.ajax({
			type: "POST",
			url: url,
			data: "ids="+msg,
			success: function(msg){
				if(msg="success"){
					top.Dialog.alert("删除成功！");
					window.location.href=newUrl;
				}else if(msg="fail"){
					top.Dialog.alert("系统正忙，稍后重试！");
				}
			}
		});		
	});
}

//url 删除url ,新跳转url
function singleDelChecked(url,id,newUrl){
	top.Dialog.confirm("是否删除所选记录",function(){
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data: "id="+id,
			success: function(msg){
				if("true"==msg.result){
					top.Dialog.alert(msg.success);
					window.location.href=newUrl;
				}else{
					top.Dialog.alert(msg.error);
				}				
			}
		});		
	});
}

function addObject(formName,url){
	var formTag = document.getElementById(formName);
	formTag.action = url;
	formTag.submit();
}

// check all the checkboxes in the list
function checkAll(theForm) {

	  for (var i=0;i<theForm.elements.length;i++) {
	    var e = theForm.elements[i];
	        var eName = e.name;
	        if (eName != 'allbox' && 
	            (e.type.indexOf("checkbox") == 0)) {
	            e.checked = theForm.allbox.checked;        
	        }
	    } 
}

//从后面取json对象
function getJson(action,params){
    var temp = "";
	jQuery
	.ajax({
		type : "POST",
		url : action,
		async:false,
		data : params,
		dataType : "text",
		success : function(json) {			
			temp = json;								
	    }
    });	
	return temp;	
}

/**
*信息提交
*/
function commit(formId ,url,newUrl){
	var commitResult=false;
	//验证结果
	var access=true;
	access=$("#"+formId).validationEngine({returnIsValid:true});
	if(access){
		$("#"+formId).mask("表单正在提交...");
		//ajax提交表单
		$.ajax({
	         type: "POST",
	         async:false,//同步
	         dataType: "json", 
	         url:url,
	         data:$("#"+formId).serialize(),// 要提交的表单
	         success: function(msg) {
	        	 if("true"==msg.result){
	        		alert(msg.success);
	        		window.location.href=newUrl;
	        		commitResult=true;
	        	 }else{
	        		 alert(msg.error);
	        	 }
	        	 $("#"+formId).unmask();
	         },
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
				 $("#"+formId).unmask();
                 alert(errorThrown);
	         } 
	     });
		
	}
	return commitResult;
}

/**
*提交页面在模态窗口
*/
function windowCommit(formId ,url,newUrl){
	var commitResult=false;
	//验证结果
	var access=true;
	access=$("#"+formId).validationEngine({returnIsValid:true});
	if(access){
		$("#"+formId).mask("表单正在提交...");
		//ajax提交表单
		$.ajax({
	         type: "POST",
	         async:false,//同步
	         dataType: "json", 
	         url:url,
	         data:$("#"+formId).serialize(),// 要提交的表单
	         success: function(msg) {
	        	 $("#"+formId).unmask();
	        	 if("true"==msg.result){
	        		alert(msg.success);
	        		top.frmright.window.location.href = newUrl;
	        	 }else{
	        		 alert(msg.error);
	        	 }
	         },
			 error: function (XMLHttpRequest, textStatus, errorThrown) {
				 $("#"+formId).unmask();
                 alert(errorThrown);
	         } 
	     });
		
	}
}