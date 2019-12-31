
//自定义操作列
function formatOper(value,row,index) {
	var hrefUrl = "/grms/zhongheDepart/showArea.do?projectProductMenuId="+row.projectProductMenuId;
    var str = "";
    str += '合计' + value + '个';
    str += '   ';
    str += '<a href="javascript:;" style="" onclick="viewArea(\'' + hrefUrl + '\')">查看</a>   ';
    return str;
}
function formatDate(value,row,index){
	if(row.businessType=='1'){
		return value;
	}else{
		return value.substr(0,11);
	}	
}
//自定义操作列
function formatHandle(value,row,index) {
	var hrefUrl = "/grms/project/download?id="+row.id;
	var hrefUrl2 = "/grms/zhongheDepart/deleteAttatment?id="+row.id;
	var str = "";
	var role = $("#roleType").val();
	str += '<a href="javascript:;" style="" onclick="downloadFile(\'' + hrefUrl + '\')">下载</a>   ';
	if(role==8 && (row.isExtra==null || row.isExtra==1 )){
		str += '<a href="javascript:;" style="" onclick="deleteFile(\'' + hrefUrl2 + '\')">删除</a>   ';	
	}
	return str;
}
//下载资源清单列表
function downloadData(){
	//wrapMaskShow();
	parent.$.messager.confirm('下载', '是否下载资源清单?', function(r){
		var actionCode = $("#actionCode").val();
		//wrapMaskHide();
		if (r){
		
         location.href="/grms/zhongheDepart/exportMGData?actionCode="+actionCode
		}
	});
}
/**
 * 弹框显示小区
 */
function viewArea(hrefUrl){
	$.ajax({
		url: hrefUrl,
		type: 'GET',
		dataType : "json",// 数据，这里使用的是Json格式进行传输
		success: function(data){
			if(data.success){
				//$("#win_form").form("load",data);
				var areaList = data.areaList;
				var tagb ='';
				for(var i=0;i<areaList.length;i++){
					tagb += '<h2><span>'+areaList[i].provinceName+'</span>'+'>'+'<span>'+areaList[i].cityName+'</span>'+'>'+'<span>'+areaList[i].countryName+'</span></h2>';
					for(var j=0;j<areaList[i].ppmas.length;j++){
						tagb +='<td>'+areaList[i].ppmas[j]+'</td>&nbsp;&nbsp;';
						if(j%4==0&&j>4){tagb +='<br>'}
					}      
					
					
				}
				$('#areas').html(tagb);
				
			}
		}
	
	});
	 wrapMaskShow();//父级遮罩显示
	 //$("#win").show();
	 $('#win').dialog({
		title: '查看小区', 
	    width: 400,    
	    height: 300,    
        modal:true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:true,
        draggable:false,
        resizable:false,
        inline:false,
        cancel:"取消",
        buttons:[{
            text:'取消',
            handler:function(){
            	wrapMaskHide();		
             	$('#win').dialog('close');
            }
        }],
        onClose : function(){
            wrapMaskHide();//父级遮罩隐藏
        }
        
	 });
	
} 

function downloadFile(hrefUrl){
	window.location.href=hrefUrl;
}
function deleteFile(hrefUrl){
	var fj_datagrid = $("#fj_datagrid");
	$.ajax({
		 url: hrefUrl,
	     type: 'GET',
	     dataType : "json",// 数据，这里使用的是Json格式进行传输
	     success: function(result){
	    	 if (result.success) {
	    		 	fj_datagrid.datagrid("reload");
				} 
	    	 alert(result.msg);
	     }

	});
}

//删除
function doDelete(id) {
    alert(id);
}
function oganiOper(value,row,index){
	if (value == 1) {
		return "社区运营";
	} else if (value == 2) {
		return "媒管";
	} else if (value == 3) {
		return "用户运营";
	} else if (value == 4) {
		return "电商运营";
	}
}
var rowspanIndex=0;
$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var zhCommunityDatagrid,actionCode,fj_datagrid,SQdatagrid;
	zhCommunityDatagrid = $("#zhCommunity_datagrid");
	fj_datagrid = $("#fj_datagrid");
	SQdatagrid = $("#SQ_datagrid");//社区
	actionCode = $("#actionCode").val();
	/*
	 * 初始化数据表格 
	 */
	zhCommunityDatagrid.datagrid({
		url:"/grms/zhongheDepart/getActionData.do?actionCode="+actionCode,//媒管、用户、电商
		fit:true,
		rownumbers:false,
		scrollbarSize:0,
		nowrap:false,//允许换行
		singleSelect:true,
		striped:true,
		pagination:false,
		fitColumns:true
	});
	SQdatagrid.datagrid({
		url:"/grms/zhongheDepart/getActionData.do?actionCode="+actionCode+"&businessType=1",//社区
		fit:true,
		rownumbers:false,
		scrollbarSize:0,
		nowrap:false,//允许换行
		singleSelect:true,
		striped:true,
		pagination:false,
		fitColumns:true,
		onLoadSuccess: function(data) {
			var mark = 1;
			var mark2 = 1;
			var mark3 = 1;
			for(var i=1;i<data.rows.length;i++){
					if(data.rows[i]["actionCode"]==data.rows[i-1]["actionCode"]){
						//指定列进行合并操作
						if(i==data.rows.length-1){							
							$(this).datagrid("mergeCells", {
								index:rowspanIndex,
								field:'fieldName',
								rowspan:i-rowspanIndex+1
							});
							$(this).datagrid("mergeCells", {
								index:rowspanIndex,
								field:'areaName',
								rowspan:i-rowspanIndex+1
							});
							$(this).datagrid("mergeCells", {
								index:rowspanIndex,
								field:'businessType',
								rowspan:i-rowspanIndex+1
							});
						}
					}else{	
						$(this).datagrid("mergeCells", {
							index:rowspanIndex,
							field:'fieldName',
							rowspan:i-rowspanIndex
						});
						$(this).datagrid("mergeCells", {
							index:rowspanIndex,
							field:'areaName',
							rowspan:i-rowspanIndex
						});
						$(this).datagrid("mergeCells", {
							index:rowspanIndex,
							field:'businessType',
							rowspan:i-rowspanIndex
						});
						rowspanIndex=i;
					}
				
			/*	if(data.rows[i]["fieldName"]==data.rows[i-1]["fieldName"]){
					mark +=1;
					//指定列进行合并操作
					$(this).datagrid("mergeCells", {
						index:i+1-mark,
						field:'fieldName',
						rowspan:mark
					});
				}
				if(data.rows[i]["businessType"]==data.rows[i-1]["businessType"]){
					mark2 +=1;
					//指定列进行合并操作
					$(this).datagrid("mergeCells", {
						index:i+1-mark2,
						field:'businessType',
						rowspan:mark2
					});
				}
				if(data.rows[i]["areaName"]==data.rows[i-1]["areaName"]){
					mark3 +=1;
					//指定列进行合并操作
					$(this).datagrid("mergeCells", {
						index:i+1-mark3,
						field:'areaName',
						rowspan:mark3
					});
				}*/
				
			}
	    }
	});
	fj_datagrid.datagrid({
		url:"/grms/zhongheDepart/getActionAttachmentData.do?actionCode="+actionCode,//附件
		fit:true,
		rownumbers:false,
		scrollbarSize:0,
		nowrap:false,//允许换行
		singleSelect:true,
		striped:true,
		pagination:false,
		fitColumns:true,
		toolbar:'#zhCommunity_datagrid_tb'
	});

	
});

function uploadFile(){
	loadingShow("正在上传中，请勿进行其他操作");
	
	var formData = new FormData();
    formData.append("file", $("#projectFile")[0].files[0]); 
    var url = "/grms/fileUpload/upload";
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
        	var fj_datagrid = $("#fj_datagrid");
        	//清除 查看附件按钮的内容
        	$("#projectFile").val("");
        	//文件url
        	var fileUrl=data.path;
        	var fileName=data.fileName;
        	var correlationId = $("#actionCode").val();
        	var type = 3;
        	var params = "fileUrl="+fileUrl+"&fileName="+fileName+"&correlationId="+correlationId+"&type="+type;
        	$.ajax({
        		 url: "/grms/zhongheDepart/uploadAttachment.do",
        	     type: 'GET',
        	     dataType : "json",// 数据，这里使用的是Json格式进行传输
        	     data: {"fileUrl":fileUrl,"fileName":fileName,"correlationId":correlationId,"type":type},
        	     success: function(result){
        	    	 if (result.success) {
        	    		 	fj_datagrid.datagrid("reload");
        				} 
        	    	 loadingShow(false);
        	    	 alert(result.msg);
        	     }

        	});
        }else{
        	$("#projectFile").val("");
        	loadingShow(false);
        	alert('文件上传失败');
        }
    });
}


