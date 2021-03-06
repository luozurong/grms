configMenuItem("收款管理","收款记录");

//需要隐藏的按钮
var mapButton = {};
mapButton['search'] = 1;
mapButton['export'] = 1;
var dataList;
//初始化按钮
$(function(){
	initButton();
	search();
});

function initButton(){
	var menuUrl="/grms/backMoneyPlanPeriods/list";
    $.ajax({  
        type : "POST",  //提交方式  
        url : "/grms/initButtonController/getButttonList",//路径  
        data : {"menuUrl":menuUrl},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        async : false,
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {
            	//比较需要隐藏的按钮
            	var buttonObject = result.button;
            	var admin = result.admin;
            	//如果是管理员
            	if(admin){
            		return;
            	}
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
            	if(mapButton['search']==0){
            	  $("#search").hide();
              	}
            	if(mapButton['export']==0){
              	  $("#export").hide();
               }
            }
        }  
    }); 
}

/*//select联动
function change(obj){
	var val = obj.value;
	
	if(val == 1){
		var sec = document.getElementById('"condition2"');
		sec.innerHTML = "<option>全部</option><option>收款</option><option>扣款</option>";
	}else if(val == 2){
		var sec = document.getElementById('"condition2"');
		sec.innerHTML = "<option>全部</option><option>待收款</option><option>待扣款</option><option>待收款</option><option>已扣款</option>";
	}else{
		var sec = document.getElementById('"condition2"');
		sec.innerHTML = "<option>全部</option>";
	}
}*/



//查询数据
function search(){
	var selectCondition =$('#selectCondition').val();
	var condition =$('#condition').val();
	var selectCondition2 =$('#selectCondition2').val();
	var condition2 = $('#condition2').val();
	
    $.ajax({  
        type : "GET",  //提交方式  
        url : "/grms/backMoneyPlanPeriods/getBackMoneyPlanPeriodsList",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        data:  {"selectCondition":selectCondition,"condition":condition,"selectCondition2":selectCondition2,"condition2":condition2},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	dataList = result.data;
				initDg(1,10);
				paginationpage();
            } else {  
            	infoMask('加载列表失败');
            }  
        }  
    }); 
}

//刷新表格数据
function initDg(pageNo,pageSize){
	var xL=(pageNo-1)*pageSize;
	var yL=pageNo*pageSize;
	var data1=dataList.slice(xL,yL);
//	表格数据渲染
    $('#dg').datagrid({
        border:true,
        scrollbarSize:0,
        nowrap:false,//允许换行
        data:data1,
        emptyMsg:'<span>无记录</span>',
        onLoadSuccess:function(){ //dom操作
            $('#dg').datagrid('resize');
        },
        columns:[[
      	        {
      	            field:'ck',
      	            title:'',
      	            checkbox:true,
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'backMoneyPlanCode',
      	            title:'收款计划ID',
      	            width:50,
      	            align:'center',
      	        },
      	        {
      	            field:'projectCode',
      	            title:'项目ID',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'contractCode',
      	            title:'合同ID',
      	            width:50,
      	            align:'center',
      	        },
      	        {
      	            field:'contractName',
      	            title:'合同名称',
      	            width:50,
      	            align:'center',
      	        },
      	        {
      	            field:'name',
      	            title:'公司名称',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'createrName',
      	            title:'业务员',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'planStatus',
      	            title:'合同收款状态',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'backMoneyType',
      	            title:'计划收款类型',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'planBackMoney',
      	            title:'计划收款金额',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'realBackMoney',
      	            title:'实收金额',
      	            width:50,
      	            align:'center'
      	        }, 
      	        {
      	            field:'planBackTime',
      	            title:'计划收款日期',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'realBackTime',
      	            title:'到账日期',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'registerAccount',
      	            title:'收款人账号',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'registerTime',
      	            title:'登记时间',
      	            width:50,
      	            align:'center'
      	        }
      	    ]]
    });
}

function paginationpage(){
//  分页
    $('#pp').pagination({
        total:dataList.length,
        layout:['list','first','prev','links','next','last','manual'],
        emptyMsg: '<span>无记录</span>',
        showRefresh:true,
        displayMsg:' ',
        pageList:[10,20,30],
        onSelectPage:function (pageNo, pageSize) {
        	initDg(pageNo,pageSize);
        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+dataList.length+"条,每页显示： ");

}



/***************导出*****************/
function exportStatistics(){
	wrapMaskShow();
	$('#exportStatistics').dialog({
	    title: "消息确认",
	    width: 380,
	    height: 290,
	    draggable:false,
	    closed: false,
	    modal: true,
	    onClose:function(){
	    	wrapMaskHide();
	    },
	    buttons:[{
	        text:'取消',
	        handler:function(){
	            $('#exportStatistics').dialog('close');
	            wrapMaskHide();
	        }
	    },{
	        text:'确认',
	        handler:function(){
	        	var hasDataValue = $("#dg").datagrid("getChecked");
	        	var orderNumArr = new Array();
	        	for(var i=0;i<hasDataValue.length;i++){
	        		var orderNum = hasDataValue[i].orderNum;
	        		orderNumArr[i]=orderNum;
	        	}
	        	if(hasDataValue.length<=0){
	            	infoMask('请选择需要导出的记录！');
	            	
	            }else{
	            	window.location.href="/uoms/maintenanceRecordAction!exportToExcel.html?orderNumArr="+orderNumArr;
	            }
	            $('#exportStatistics').dialog('close');
	            wrapMaskHide();
	        }
	    }]
	});
}

/***************导出全部（确认）**********************/
function exportAll(){
	wrapMaskShow();
	$('#exportStatistics').dialog({
	    title: "消息确认",
	    width: 380,
	    height: 290,
	    draggable:false,
	    closed: false,
	    modal: true,
	    onClose:function(){
	    	wrapMaskHide();
	    },
	    buttons:[{
	        text:'取消',
	        handler:function(){
	            $('#exportStatistics').dialog('close');
	            wrapMaskHide();
	        }
	    },{
	        text:'确认',
	        handler:function(){
	        	var areas = $("#areas").val();
	            var operationPer = $("#operationPer").val();
	            var terminalName = $("#terminalName").val();
	            var deviceID = $("#deviceID").val();
	            /*var provinceCode = $("#province").combobox('getValue'); 
	        	var cityCode = $("#city").combobox('getValue'); 
	        	var countryCode = $("#country").combobox('getValue'); 
	        	var townCode = $("#town").combobox('getValue');*/
	            var provinceVal = $("#provinceCode").val(); 
	        	var cityVal = $("#cityCode").val(); 
	        	var countryVal = $("#countryCode").val(); 
	        	var townVal = $("#townCode").val();
	        	var startTime = $('#startTime').datebox('getValue');
	        	var endTime = $('#endTime').datebox('getValue');
	        	var stoppageCategory = $("#stoppageCategory input[type='radio']:checked").val();
	        	var stoppageSublevelCategory = $("#stoppageSubCategory input[type='radio']:checked").val();
	    		var deviceType = $("#deviceType input[type='radio']:checked").val();
	    		var sublevelType = $("#sublevelType input[type='radio']:checked").val();
	    		
	    		if(typeof(sublevelType)=="undefined"){ 
	    		    sublevelType = "";
	    		}else{
	    			sublevelType = $("#sublevelType input[type='radio']:checked").val();
	    		}
	        	window.location.href="/uoms/maintenanceRecordAction!exportToExcelAll.html?areas="
	        							+areas+"&operationPer="+operationPer+"&terminalName="+terminalName+"&deviceID="+deviceID+"&startTime="+startTime+"&endTime="
	        							+endTime+"&stoppageCategory="+stoppageCategory+"&stoppageSublevelCategory="+stoppageSublevelCategory+"&deviceType="+deviceType+"&sublevelType="
	        							+sublevelType+"&queryBean.province="+provinceVal+"&queryBean.city="+cityVal+"&queryBean.country="+countryVal+"&queryBean.town="+townVal;
	            $('#exportStatistics').dialog('close');
	            wrapMaskHide();
	        }
	    }]

	});
	searchRecord();
}



/***************导出（确认）***************/
$('#hintStatistics').dialog({
    title: "消息确认",
    draggable:false,
    width: 520,
    height: 360,
    closed: true,
    modal: true
});


function exportBackMoneyPlan(){
	var selectCondition =$('#selectCondition').val();
	var condition =$('#condition').val();
	var selectCondition2 =$('#selectCondition2').val();
	var condition2 = $('#condition2').val();
	window.location.href="/grms/backMoneyPlanPeriods/exportBackMoneyPlanPeriods?selectCondition="+selectCondition+"&condition="+condition
							+"&selectCondition2="+selectCondition2+"&condition2="+condition2;
}


