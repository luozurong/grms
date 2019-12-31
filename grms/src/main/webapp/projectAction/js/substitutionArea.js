var pageNoAll=1;
var pageSizeAll=10;
var provinceCode = null;
var cityCode = null;
var countryCode = null;
$(function(){
	
	$('#searchBtn').linkbutton({  
		
	});
	$('#substitutionArea').linkbutton({  
		
	});
	$('#gobackBtn').linkbutton({  
		
	});
	
	var cityAreaTownInitValue=[
	                           {
	                               "id":0,
	                               "code":"",
	                               "name":"请选择",
	                               "parentId":"",
	                               "selected":true
	                           }
	 ];
	 $('#city,#country').combobox({
		 	width:100,
		 	height:30,
	        limitToList:true,
	        data:cityAreaTownInitValue,
	        valueField:'code',
	        textField:'name',
	        editable:false,
	        panelHeight:"auto"
	    });
	
	//省份的数据初始化
    $('#province').combobox({
        url:'/ums/communityAction!initProvince.html',
        method:'post',
        value : '请选择',
        valueField:'code',
        textField:'name',
        editable:false,
        onSelect:function(newValue){
        	provinceCode = newValue.code;
        	$("#provinceCode").val(provinceCode);
        	$("#cityCode").val("");
        	$("#countryCode").val("");
            $('#city').combobox({
                url:'/ums/communityAction!getChildSelectData.html?code='+provinceCode,
                method:'post',
                value : '请选择',
                valueField:'code',
                textField:'name',
                editable:false,
                onSelect:function(newValue){
                	cityCode = newValue.code;
                	$("#cityCode").val(cityCode);
                	$("#countryCode").val("");
                	$('#country').combobox({
            	        url:'/ums/communityAction!getChildSelectData.html?code='+cityCode,
            	        method:'post',
            	        value : '请选择',
            	        valueField:'code',
            	        textField:'name',
            	        editable:false,
            	        onSelect:function(newValue){
            	        	countryCode = newValue.code;
            	        	$("#countryCode").val(countryCode);
							var self = $(this);
							comboboxOnSelect(self,newValue);
            	        }
            	    });
					var self = $(this);
					comboboxOnSelect(self,newValue);
                }
            });
			var self = $(this);
			comboboxOnSelect(self,newValue);
        }
    });
	
    findMenu();
    
})


	
	



//查询数据
function findMenu(){
	var areaName=$('#areaName').val()?$('#areaName').val():"";
	var householdNumSign=$('#householdNum').val()?$('#householdNumSign').val():"";
	var householdNum=$('#householdNum').val()?$('#householdNum').val():"";
	var areaCategory = $('#areaCategory').val();
	var advertisingTerminalNumSign = $('#advertisingTerminalNum').val()?$('#advertisingTerminalNumSign').val():"";
	var advertisingTerminalNum = $('#advertisingTerminalNum').val()?$('#advertisingTerminalNum').val():"";
	var translateStatus = $('#translateStatus').val();
	var actionCode = $('#actionCode').val();
	
	var province=provinceCode?provinceCode:"";
	var city=cityCode?cityCode:"";	
	var country=countryCode?countryCode:"";
	
	var pageNo=pageNoAll;
	var pageSize=pageSizeAll;
	var areaDataReqVo='{"areaName":"'+areaName+'","householdNumSign":"'+householdNumSign
	+'","householdNum":"'+householdNum+'","areaCategory":"'+areaCategory
	+'","advertisingTerminalNumSign":"'+advertisingTerminalNumSign+'","advertisingTerminalNum":"'+advertisingTerminalNum
	+'","translateStatus":"'+translateStatus+'","country":"'+country
	+'","province":"'+province+'","city":"'+city
	+'","pageNum":"'+pageNo+'","pageSize":"'+pageSize+'"}';
	
	console.log(areaDataReqVo)
	
    $.ajax({  
        type : "POST",  //提交方式  
        url : "/grms/projectAction/getSubstitutionAreaDate.html",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"areaDataReqVo":areaDataReqVo,"actionCode":actionCode},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if (result.success) {  
            	var data2=result.rows;
            	var total=result.total;
            	refresh(data2);
            	paginationpage(total,data2);
            	
            } else {  
            	$.messager.alert('提示','获取置换小区列表数据失败！！');
            }  
        }  
    }); 
}

//刷新表格数据
function refresh(data){
//	表格数据渲染
  $('#dg').datagrid({
      border:true,
      scrollbarSize:0,
      nowrap:false,//允许换行
      data:data,
      singleSelect:true,
      emptyMsg:'<span>无记录</span>',
      onLoadSuccess:function(){ //dom操作
          $('#dg').datagrid('resize');
          
          
          inputStyle();
			 
			 setTimeout(function(){
				 inputStyle();
				 $(".datagrid-row,.datagrid-header-check").click(function(){
				    setTimeout(function(){
				        inputStyle();
				        console.log(11)
				    },100)
				});
			 },100);
          
      },
      columns:[[
          {
              field:'ck',
              title:'选择',
              checkbox:true,
              width:150,
              align:'left'
          },
          {
              field:'cityName',
              title:'城市',
              width:250,
              align:'left'
              
          },
          {
              field:'countryName',
              title:'区域',
              width:250,
              align:'left'
          },
          {
              field:'areaName',
              title:'楼盘名称',
              width:300,
              align:'left'
          },
          {
              field:'organizationSeq',
              title:'小区机构编号',
              width:450,
              align:'left'
          },
          {
              field:'areaCategory',
              title:'小区类型',
              width:200,
              align:'left',formatter:function(value){
            	  //小区类型，0：测试小区，1：云对讲项目  2：工程项目  3：样板演示小区  4：销售小区
            	  if(value==0){
            		  return "测试小区 "; 
            	  }else if(value==1){
            		  return "云对讲项目";
            	  }else if(value==2){
            		  return "工程项目";
            	  }else if(value==3){
            		  return "样板演示小区";
            	  }else if(value==4){
            		  return "销售小区";
            	  }
              }
          },
          {
              field:'translateStatus',
              title:'转化状态',
              width:200,
              align:'left',formatter:function(value){
            	  //转化状态: 0: 未转化 ， 1: 已完成转化  ,2 部分转化(试点转化)
            	  if(value==0){
            		  return "未转化 "; 
            	  }else if(value==1){
            		  return "已转化";
            	  }else if(value==2){
            		  return "部分转化(试点转化)";
            	  }
              }
          },
          {
              field:'householdNum',
              title:'实际户数',
              width:200,
              align:'left'
          },
          {
              field:'advertisingTerminalNum',
              title:'媒体终端',
              width:250,
              align:'left'
          },
          {
              field:'areaAddress',
              title:'小区地址',
              width:650,
              align:'left'
          }
      ]]
  });
	 
}


//返回
function goback(){
	window.location.href="/grms/projectAction/gobackPEList.html"
}

//产地置换
function substitutionArea(){
	
	var selected = $("#dg").datagrid("getSelected");
	
	var actionCode = $("#actionCode").val()
	var exceptionId = $("#exceptionId").val()
	
	if(!selected){
		
		$.messager.alert('提示','请选择要进行置换场地的小区！！');
		
		return ;
		
	}
	
	var areaName = selected.areaName
	var organizationSeq = selected.organizationSeq
	var province = selected.province
	var provinceName = selected.provinceName
	var city = selected.city
	var cityName = selected.cityName
	var countryName = selected.countryName
	var areaAddress = selected.areaAddress
	
	var jsonStr = '{"actionCode":"'+actionCode+'","organizationSeq":"'+organizationSeq
	+'","province":"'+province+'","provinceName":"'+provinceName+'","exceptionId":"'+exceptionId
	+'","countryName":"'+countryName+'","areaAddress":"'+areaAddress+'","areaName":"'+areaName
	+'","city":"'+city+'","cityName":"'+cityName+'"}';
	var json = JSON.parse(jsonStr)
	
	createConfirm("置换场地操作提示","确定进行置换场地吗？",true,false,"确定","取消",okCallbakFunciton,"","","");
	 function okCallbakFunciton(){
		 $.ajax({  
		        type : "POST",  //提交方式  
		        url : "/grms/projectAction/substitution.html",//路径  
		        dataType : "json",//数据，这里使用的是Json格式进行传输  
		        data:json,
		        success : function(result) {//返回数据根据结果进行相应的处理  
		            if (result.success) {  
		            	
		            	parent.$.messager.alert('提示','置换场地操作处理成功！！');
		            	window.location.href="/grms/projectAction/gobackPEList.html";
		            	
		            } else {  
		            	parent.$.messager.alert('提示','置换场地操作处理失败！！');
		            }  
		        }  
		    });
	  }
	
}


function paginationpage(total,data2){
//  分页
    $('#pp').pagination({
        total:total,
        layout:['list','first','prev','links','next','last','manual'],
        emptyMsg: '<span>无记录</span>',
        showRefresh:true,
        displayMsg:' ',
        pageList:[10,20,30],
        onSelectPage:function (pageNo, pageSize) {
        	console.log(pageNo+","+pageSize)
        	pageNoAll=pageNo;
        	pageSizeAll=pageSize;
        	findMenu()
        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+total+"条,每页显示： ");

}