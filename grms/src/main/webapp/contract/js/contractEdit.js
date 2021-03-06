//	表格数据渲染
var data2 ;

var systemId="1";
//需要隐藏的按钮
var mapButton = {};
var userType;
var pageNoAll='1';
var pageSizeAll='10';
var pageNoAllPro='1';
var pageSizeAllPro='10';
var contractType = [{"id":'',"text":"全部"},{"id":1,"text":"执行合同"},{"id":2,"text":"框架合同"}];
var contractStatus=[{"id":'',"text":"全部"},{"id":1,"text":"未提交"},{"id":2,"text":"待审批-业务管理"},
                    {"id":3,"text":"待审批-合同管理"},{"id":4,"text":"待审批-业务管理"},{"id":4,"text":"待审批-财务管理"},{"id":5,"text":"审批通过"},
                    {"id":6,"text":"审批不通过"},{"id":7,"text":"已完成"}];

$(function(){
	
	
    $(".go-back").click(function(){
        window.location.href="/grms/contract/contract.html";
    });
    
    $("#uploadFile").click(function(){
      	 upload();
      });
    //初始化信息
    initContract();
    
});

function divShow(){
	$('#divContract').show();
	$('#divProject').show();

}

function divHidden(){
	  //关联合同
    $('#fromContractCode').val('');
	$('#fromContractName').val('');
	$("#contract-name").text('');
	$("#project-name").text('');

	$('#contractName').val('');
		//关联项目编号 
	$('#productCode').val('');
	$('#projectName').val('');
	$('#customerResource').val('');
	$('#divContract').hide();
	$('#divProject').hide();
	$('#btnContractdel').hide();

}
function initContract(){
	 var args = new Object();
     args = GetUrlParms();//获取参数数组
     
     if(args["projectName"] != undefined&&args["projectCode"] !=undefined){
    	   $('#productCode').val(args["projectCode"]);
    	   $('#projectName').val(args["projectName"]);
		   var pageText2=args["projectName"]+'&nbsp;&nbsp;'+"项目ID:"+args["projectCode"]
		   $("#project-name").html(pageText2);
		   $("input[name='contractType'][value='1']").attr("checked",true); 
     }
     
     //如果要查找参数key:
     if (args["status"] != undefined) {
         //如果要查找参数key:
         var status = args["status"];
         if(status=='new'){
             $('#id').val('');
        		//合同类型             
             $("input[name='contractType'][value='1']").attr("checked",true);        
             //关联合同
             $('#fromContractCode').val('');
      	     $('#fromContractName').val('');
             $('#contractCode').val('');
        	 $('#contractName').val('');
        		//关联项目编号 
        	 $('#productCode').val('');
        	 $('#projectName').val('');
        	 $('#customerResource').val('');

        	 $('#money').val('');
             $('#url').val('');
         }else if(status=='edit'){
        	 var id=args["id"];
        	   $.ajax({
                   type : "GET",  //提交方式
                   url : "/grms/contract/getContractOne",//路径
                   async: false,
                   data:  {"id":id},
                   dataType : "json",//数据，这里使用的是Json格式进行传输
                   success : function(result) {//返回数据根据结果进行相应的处理
                       if ( result.success) {
                    	   $('#id').val(result.obj.id);
                    	   if(result.obj.contractType==1){
                   			//合同类型
                    		   $("input[name='contractType'][value='1']").attr("checked",true);  
                    		   //关联合同
                        	   $('#fromContractCode').val(result.obj.fromContractCode);
                        	   $('#fromContractName').val(result.obj.fromContractName);
                        	   if(result.obj.fromContractCode!=''&&result.obj.fromContractCode!="null"){
                        		   var pageText1=result.obj.fromContractName+'&nbsp;&nbsp;'+"合同ID:"+result.obj.fromContractCode
                        		   $("#contract-name").html(pageText1);
                        	   }
                        	   //关联项目编号
                        	   $('#productCode').val(result.obj.projectCode);
                        	   $('#projectName').val(result.obj.projectName);
                			   var pageText2=result.obj.projectName+'&nbsp;&nbsp;'+"项目ID:"+result.obj.projectCode
              				   $("#project-name").html(pageText2);
                    	   }else{
                    		    $('#divContract').hide();
                    			$('#divProject').hide();
                    		    $("input[name='contractType'][value='2']").attr("checked",true);        
                    	   }
                    	   //合同
                    	   $('#contractCode').val(result.obj.contractCode);
                    	   $('#contractName').val(result.obj.contractName);
                    	   
                    	
            			   
            			   $('#money').val(result.obj.money);
                    	   $('#url').val(result.obj.attachmentDetail);
                      	   $('#customerResource').val(result.obj.customerResource);

            			   var	tr='';
                    		if(result.obj.attachments!=''&&result.obj.attachments!=null){
                    			var urlArray=result.obj.attachments;
                    			for(var i=0;i<urlArray.length;i++){
                    			tr=tr+"<tr id='"+urlArray[i].fileName+"'><td>"+urlArray[i].fileName+"</td><td>"+
                          		   		'<a onclick="download2(\''+urlArray[i].fileUrl+'\',\''+urlArray[i].fileName+'\')">下载</a>'+
                          		   		'<a onclick="deleteAttchment(this,\''+urlArray[i].id+'\')">删除</a>'+
                          		   		"</td></tr>";
                    			}
                    	     }		
                    		$("#upload").val('');
           		     		$("#projectFileList").append(tr);

                    	   
                       } else {

                       }
                   }
               });

         }
     }
     checkedFunc();
}

/**
 * 页面单按钮上传
 */
function upload(){
	var formData = new FormData($("#upfile")[0]);
	if($("#upload").val()==''){
		infoMask('上传文件不能为空');
		return;
	}
	//formData.set('file', document.getElementById("upload").files[0]);
    var url = "/grms/attachment/uploadContractFile";
    showShade(true , "正在上传附件,请稍后......");
	$.ajax({
        url: url,
        type: 'POST',
        cache: false,
        data:formData,
        processData: false,
        contentType: false,
        dataType: "json",
	    complete : function (XHR, TS){
                showShade(false);
        }
    }).done(function(data) {
        if(data.success){//文件上传成功
        	//文件url
        	var attachment=data.obj;
        	var	tr="<tr id='"+attachment.fileName+"'><td>"+attachment.fileName+"</td><td>"+
		   		 '<a onclick="download2(\''+attachment.fileUrl+'\',\''+attachment.fileName+'\')">下载</a>'+
		   		 '<a onclick="deleteAttchment(this,\''+attachment.id+'\')">删除</a>'+
		   		 "</td></tr>";
        	
        	     		
            $("#upload").val('');
		   	$("#projectFileList").append(tr);
    		infoMask('文件上传成功');

        }else{
    		infoMask('文件上传失败');

        }
    });
}
/*删除*/
function deleteAttchment(obj,id){

    $.ajax({  
        type : "POST",  //提交方式  
        url : "/grms/attachment/deleteAttchmentContract",//路径  
        data:{"id":id},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {
            	
             } else {  
            	 infoMask('文件删除失败');
            }  
        }  
    });
		$(obj).parents("tr").remove();
		
}						
function downloadFile(url) {   
    try{ 
        var elemIF = document.createElement("iframe");   
        elemIF.src = url;   
        elemIF.style.display = "none";   
        document.body.appendChild(elemIF);   
    }catch(e){ 

    } 
}
function initButton(){
	//
	mapButton['select'] = 1;
	mapButton['create'] = 1;
	mapButton['view'] = 1;
	mapButton['alter'] = 1;
	mapButton['delete'] = 1;
	var menuUrl="/ums/roleAction!goRoleList.html";
    $.ajax({  
        type : "POST",  //提交方式  
        url : "/ums/initButtonAction!initMenuButton.html",//路径  
        data:{"menuUrl":menuUrl},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {
            	
            	//比较需要隐藏的按钮
            	var buttonObject= result.obj;
            	var admin=result.msg;
            	if(admin!="admin"){
            	var temp=1;
            	for(var prop in mapButton){
            		temp=1;
            		for(var j=0;j<buttonObject.length;j++){
            			if(prop==buttonObject[j].resourceCode){
            				temp=0;
            				break;
            				}
            			}
            			if(temp==1){
            				mapButton[prop]=0;
            			}
            		}
            	if(mapButton['create']==0){
            	  $("#create").hide();
              	}
              	if(mapButton['select']==0){
              		$("#select").hide();
              	}
             
            }else{
            	userType=0;
            }
            } else {  
            	
            }  
        }  
    }); 
  
}

function cancel2(){
    window.location.href="/grms/contract/contract.html";

}
function save(){
	var id =$('#id').val();
	//合同类型
	var contractType = $('input[name="contractType"]:checked').val();
	//关联框架合同
	var fromContractCode =$('#fromContractCode').val();
	
/*	if(contractType=='1'&&fromContractCode==''){
		infoMask("执行合同关联框架合同不能为空");
        return;
	}*/
	var fromContractName =$('#fromContractName').val();

	var contractCode =$('#contractCode').val();
	var contractName=$('#contractName').val();
	if(contractName==''||contractName== undefined||contractName==null){
		infoMask("合同名称不能为空");
        return;
	}
	//关联项目编号
	var projectCode=$('#productCode').val();
	var projectName=$('#projectName').val();

	if(contractType=='1'&&projectCode==''){
		infoMask("执行合同关联项目编号不能为空");
        return;
	}
	var customerResource=$('#customerResource').val()
	var money=$('#money').val();
	
	if(/^\d+(\.\d{1,2})?$/.test(money)){
		
	}else{
		infoMask("合同金额请输入精度为两位小数以内的正数！");
		return;
	}
	if(customerResource!=''){
		if(/^\d+(\.\d{1,2})?$/.test(customerResource)&&customerResource<=1){
		}else{
			infoMask("系数范围0-1，精确到小数点后两位！");
			return;
		}
	}
	var data=new Object();
	data.contractType=contractType;
	
	data.fromContractCode=fromContractCode;
	data.fromContractName=fromContractName;

	data.contractCode=contractCode;
	data.contractName=contractName;
	data.projectCode=projectCode;
	data.projectName=projectName;

	data.money=money;
	data.customerResource=customerResource;
/*	data.attachmentDetail=url;
*/	data.id=id;
    $.ajax({  
        type : "POST",  //提交方式  
        contentType : "application/json;charset=utf-8", 
        url : "/grms/contract/saveContract",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  JSON.stringify(data),  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
        		infoMask('保存成功');
        	    window.location.href="/grms/contract/contract.html";
            } else {  
        		infoMask(result.msg);

            }  
        }  
    }); 
}
/****************合同列表***********************/
function createNew(){

	searchMaintainer("6","1");
	//显示遮罩
	wrapMaskShow();
	$('#allotWorkOrder').dialog('open');
}

winHiSelfAdaptation($('#allotWorkOrder'));
$('#allotWorkOrder').dialog({
  title: "选择合同",
  width: 800,
  height: 500,
  draggable:false,
  closed: true,
  modal: true,
  buttons:[{
      text:'取消',
      handler:function(){
          $('#allotWorkOrder').dialog('close');
        //隐藏遮罩
      	wrapMaskHide();
      }
  },{
      text:'确定',
      handler:function(){
          var row = $('#allotWorkOrderTable').datagrid('getChecked');
  			if(row !== null&&row.length!=0){
    			//隐藏遮罩
    			wrapMaskHide();
				var fromContractCode=row[0].contractCode;
				var fromContractName=row[0].contractName;
				console.log(row);
				var pageText=fromContractName+'&nbsp;&nbsp;'+"合同ID:"+fromContractCode
				$("#contract-name").html(pageText);
				$("#btnContractdel").show();
			    $("#fromContractCode").val(fromContractCode);
			    $("#fromContractName").val(fromContractName);

/*			    $("#btnContract").text(row[0].contractName);
*/
		        $('#allotWorkOrder').dialog('close');
  			}else{
  				infoMask('请选择');
      		}
  		}
  }],
  onClose:function(){
  	wrapMaskHide();
  }
});
$(window).resize(function(){
	$('#dg').datagrid('resize');	
	setTimeout(function(){
		$("#allotWorkOrderTable .datagrid-view").css("height",$("#allotWorkOrderTable .datagrid-btable").height());
	},1000);
})

function delContractPage(){
	$("#contract-name").html('');
	$("#fromContractCode").val('');
	$("#fromContractName").val('');
	$("#btnContractdel").hide();

}
function refresh2(data2){
$('#allotWorkOrderTable').datagrid({
  nowrap:false,//允许换行
  data:data2,
  width: 700,
  fitColumns: false,
  emptyMsg:'<span>无记录</span>',
  checkOnSelect:false,//点击该复选框的时候才会选中或取消
  singleSelect:true,
  onLoadSuccess: function (data) {//Fires when data is loaded successfully.
	     //去掉表格底部多出的19px
      $('#dg').datagrid('resize');
      var dataHeight =  $("#allotWorkOrder .datagrid-view").height()-19;
      $("#allotWorkOrder .datagrid-view").css("height",dataHeight );
	},
  columns:[[
      {
          field:'ck',
          checkbox:true,
          width:100,
          align:'left'
      },
      {
          field:'contractCode',
          title:'合同编号',
          width:270,
          align:'left'
      },
      {
          field:'contractName',
          title:'合同名称',
          width:200,
          align:'left'
      },
      {
          field:'contractType',
          title:'合同类型',
          width:200,
          align:'left',
          formatter: function(value,row,index){
          	if(value==1){
          		return "执行合同";
          	}else{
          		return "框架合同";
          	}
          }
      }
  ]]
});
}
function paginationpage(total,data2){
	  $('#pp-allotWorkOrderTable').pagination({
	        total:total,
	        layout:['list','first','prev','links','next','last','manual'],
	        emptyMsg: '<span>无记录</span>',
	        showRefresh:true,
	        displayMsg:' ',
	        pageList:[10,20,30],
	        onSelectPage:function (pageNo, pageSize) {
	        	pageNoAll=pageNo;
	        	pageSizeAll=pageSize;
	        	searchMaintainer('','') ;   
	           
	        }
	    });
	    $(".pagination-page-list").parent().append("条");
	    $(".pagination-page-list").parent().prepend("共计"+total+"条,每页显示： ");

}

//搜索
function searchMaintainer(pageSize,pageNo){
	var contractOrAccount=$("#allot_maintainerName").val();
	var contractType='2'
	var contractStatus='7'

	pageSize=pageSizeAll;
	pageNo=pageNoAll;
	var data=new Object();
	data.startTime="";
	data.queryName=contractOrAccount;
	data.pageSize=pageSize;
	data.pageNumber=pageNo;
	data.contractType=contractType;
	data.contractStatus=contractStatus;
	data.pageSize=pageSize;
	data.pageNumber=pageNo;
    $.ajax({  
        type : "POST",  //提交方式  
        contentType : "application/json;charset=utf-8", 
        url : "/grms/contract/getContractListData",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  JSON.stringify(data),  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data2=result.obj.list;
            	var total=result.obj.total;
            	refresh2(data2);
            	paginationpage(total,data2);
            } else {  
            	
            }  
        }  
    }); 
	
}
///-----------项目列表
//搜索
function createNew2(){

	searchProject("5","1");
	//显示遮罩
	wrapMaskShow();
	$('#allotWorkOrder2').dialog('open');
}

winHiSelfAdaptation($('#allotWorkOrder2'));
$('#allotWorkOrder2').dialog({
  title: "选择项目",
  width: 800,
  height: 500,
  draggable:false,
  closed: true,
  modal: true,
  buttons:[{
      text:'取消',
      handler:function(){
          $('#allotWorkOrder2').dialog('close');
        //隐藏遮罩
      	wrapMaskHide();
      }
  },{
      text:'确定',
      handler:function(){
          var row = $('#allotWorkOrderTable2').datagrid('getChecked');
  			if(row !== null&&row.length!=0){
    			//隐藏遮罩
    			wrapMaskHide();
				var productCode=row[0].productCode;
				var name=row[0].name;
				var pageText=name+'&nbsp;&nbsp;'+"项目ID:"+productCode
				$("#project-name").html(pageText);
			    $("#productCode").val(productCode);
			    $("#projectName").val(name);
/*			    $("#btnProject").text(name);
*/		        $('#allotWorkOrder2').dialog('close');

  			}else{
  				infoMask('请选择');
      		}
  		}
  }],
  onClose:function(){
  	wrapMaskHide();
  }
});
$(window).resize(function(){
	$('#dg').datagrid('resize');	
	/*setTimeout(function(){
		$("#allotWorkOrderTable2.datagrid-view").css("height",$("#allotWorkOrderTable2.datagrid-btable").height());
	},1000);*/
})

function refresh3(data2){
$('#allotWorkOrderTable2').datagrid({
  nowrap:true,//允许换行
  data:data2,
  width: 700,
  fitColumns: false,
  emptyMsg:'<span>无记录</span>',
  checkOnSelect:false,//点击该复选框的时候才会选中或取消
  singleSelect:true,
  onLoadSuccess: function (data) {//Fires when data is loaded successfully.
	     //去掉表格底部多出的19px
      $('#dg').datagrid('resize');
      var dataHeight =  $("#allotWorkOrder2 .datagrid-view").height()-19;
      $("#allotWorkOrder2 .datagrid-view").css("height",dataHeight );
	},
  columns:[[
      {
          field:'ck',
          checkbox:true,
          width:100,
          align:'left'
      },
      {
          field:'productCode',
          title:'项目编号',
          width:270,
          align:'left'
      },
      {
          field:'name',
          title:'项目名称',
          width:200,
          align:'left'
      },
      {
          field:'customerName',
          title:'主要联系人',
          width:200,
          align:'left'
      }
  ]]
});
}
function paginationpage2(total,data2){
	  $('#pp-allotWorkOrderTable2').pagination({
	        total:total,
	        layout:['list','first','prev','links','next','last','manual'],
	        emptyMsg: '<span>无记录</span>',
	        showRefresh:true,
	        displayMsg:' ',
	        pageList:[10,20,30],
	        onSelectPage:function (pageNo, pageSize) {
	        	pageNoAllPro=pageNo;
	        	pageSizeAllPro=pageSize;
	           
	        }
	    });
   $(".pagination-page-list").parent().append("条");
   $(".pagination-page-list").parent().prepend("共计"+total+"条,每页显示： ");
}

//搜索
function searchProject(pageSize,pageNo){
	var name=$("#allot_projectName").val();
	pageSize=pageSizeAll;
	pageNo=pageNoAll;
	var data=new Object();
	data.name=name;
	data.pageSize=pageSize;
	data.pageNumber=pageNo;

    $.ajax({  
        type : "POST",  //提交方式  
        contentType : "application/json;charset=utf-8", 
        url : "/grms/contract/getPorjectEdit",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  JSON.stringify(data),  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data2=result.obj.list;
            	var total=result.obj.total;
            	refresh3(data2);
            	paginationpage2(total,data2);
            } else {  
            	
            }  
        }  
    }); 
	
}

// //获取URL所有参数
function GetUrlParms() {
    var args = new Object();
    var query = location.search.substring(1); //获取查询串   
    var pairs = query.split("&"); //在逗号处断开   
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('='); //查找name=value   
        if (pos == -1) continue; //如果没有找到就跳过   
        var argname = pairs[i].substring(0, pos); //提取name   
        var value = pairs[i].substring(pos + 1); //提取value   
        args[argname] = decodeURI(value); //存为属性   
    }
    return args;
}

function checkedFunc(){
	setTimeout(function(){
		$("label input").each(function(){
			console.log($(this).val());
			console.log($(this).attr("checked"));
			if($(this).is(":checked")){
				console.log(2);
				$(this).parent().addClass("radio-bg");
			}
		});
	},200);
}
/*
显示遮罩
*/
function showShade(show , text){
   if(true == show){
       if(text){
           $('.shadeBox .tipsInfo p').html(text);
       }     	
	    wrapMaskShow();//父级遮罩显示
	    $('body').css('overflow','hidden');//禁止滚动	    
        $('.shadeBox').show();
   }else{
       wrapMaskHide();//父级遮罩隐藏
       $('body').css('overflow','auto');//恢复滚动
       $('.shadeBox').hide();
   }

}

function download2(url,fileName) {
	//下载附件
	var urlall="/grms/attachment/downloadUrl?url="+url+"&fileName="+fileName;
	window.open(urlall,'_blank');
	}
function EncodeUtf8(s1)
{
      var s = escape(s1);
      var sa = s.split("%");
      var retV ="";
      if(sa[0] != "")
      {
         retV = sa[0];
      }
      for(var i = 1; i < sa.length; i ++)
      {
           if(sa[i].substring(0,1) == "u")
           {
               retV += Hex2Utf8(Str2Hex(sa[i].substring(1,5)));

           }
           else retV += "%" + sa[i];
      }

      return retV;
}
