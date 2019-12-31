
/*下拉框data数据*/
var data1 = [
    {
        "id":0,
        "text":"全部",
        "selected":true
    },{
        "id":1,
        "text":"大于"
    },{
        "id":2,
        "text":"小于"
    }
];
var datagridData;
var provinceCode="";
var cityCode="";
var cityAreaTownInitValue=[
                           {
                               "id":0,
                               "code":"",
                               "name":"请选择",
                               "parentId":"",
                               "selected":true
                           }
 ];
/* 表格分页data数据 */
$(function(){
    /* 下拉框 */
    $('#cc2,#cc3').combobox({
        limitToList:true,
        data:data1,
        valueField:'id',
        textField:'text',
        editable:false,
        panelHeight:"auto"
    });
    
    $('#city_selt,#area_selt').combobox({
        limitToList:true,
        data:cityAreaTownInitValue,
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:"auto"
    });
    
    
    //省份的数据初始化
    $('#province_selt').combobox({
        url:'communityAction!initProvince.html',
        method:'post',
        value : '请选择',
        valueField:'code',
        textField:'name',
        editable:false,
        onSelect:function(newValue){
        	provinceCode = newValue.code;
        	$("#provinceCode").val(provinceCode);
        	$("#cityCode").val("");
        	$("#areaCode").val("");
            $('#city_selt').combobox({
                url:'communityAction!getChildSelectData.html?code='+provinceCode,
                method:'post',
                value : '请选择',
                valueField:'code',
                textField:'name',
                onSelect:function(newValue){
                	cityCode = newValue.code;
                	$("#cityCode").val(cityCode);
                	$("#areaCode").val("");
                	 $('#area_selt').combobox({
                	        url:'communityAction!getChildSelectData.html?code='+cityCode,
                	        method:'post',
                	        value : '请选择',
                	        valueField:'code',
                	        textField:'name',
                	        onSelect:function(newValue){
                	        	areaCode = newValue.code;
                	        	$("#areaCode").val(areaCode);
                	        }
                	    });
                }
            });
        }
    });

    
   
    
    //	表格数据渲染
    datagridData=$('#dg').datagrid({    
	scrollbarSize : 0,
	nowrap : false,// 允许换行
//						url : 'communityAction!list.html',
	method : 'get',
	louomsg: '正在加载中，请稍等... ',
	fitColumns : true,// 宽度自适应
	checkOnSelect : false,// 点击该复选框的时候才会选中或取消
    emptyMsg: '<span>无记录</span>',
	onLoadSuccess : function(data) {// Fires when data is
		loadSpan();
		$('#pp').pagination('refresh', {
			total : data.total,
			pageNumber : data.page
		});
        toopTip(".seePic","查看");
        toopTip(".editPic","编辑");
        $('#dg').datagrid('resize');
		boxCheck();
		boxUnCheck();
	},
	onCheckAll:function(){
		boxCheck();
	},
	onCheck:function(){
		boxCheck();
	},
	onUncheckAll: function () {
		boxUnCheck();
	},
	onUncheck: function () {
		boxUnCheck();
	},
	columns : [ [
		{
			field : 'id',
			title : '编号',
			checkbox : true,
			width : 60,
			align : 'center'
		},
		{
			field : 'communityName',
			title : '小区名称',
			width : 135,
			align : 'center'
		},
	//								{
	//									field : 'country',
	//									title : '区域',
	//									width : 60,
	//									align : 'center'
	//								},
		{
			field : 'address',
			title : '地址',
			width : 225,
			align : 'center'
		},
		{
			field : 'familyCount',
			title : '入住户数',
			width : 50,
			align : 'center'
		},
		{
			field : 'peopleCount',
			title : '入住人数',
			width : 50,
			align : 'center'
		},
		{
			field : 'terminalCount',
			title : '媒体终端数量',
			width : 70,
			align : 'center'
		},
		{
			field : 'grandCardCount',
			title : '门禁卡数量',
			width : 50,
			align : 'center'
		},
		{
			field : 'handle',
			title : '操作',
			width : 70,
			align : 'center',
			formatter : function(value, row, index) {
				var id = row.id;
				var see = '<span class="handlePic seePic" href="#" onclick="godetail('
						+ "'" + id + "'" + ')"></span>';
				var edit = '<span class="handlePic editPic" href="#" onclick="goEdit('
						+ "'" + id + "'" + ')"></span>';
				return see + edit;
	//										var del = '<span class="handlePic delPic" href="#" onclick="removeCommunity('
	//												+ "'" + id + "'" + ')"></span>';
	//										return see + edit + del;
			}
		} 
	] ]
});
    //分页
    $('#pp').pagination({ 
		layout:['first','prev','links','next','last','manual'],
		pageSize:7,
		onSelectPage:function (pageNo, pageSize) {
	        var start = (pageNo - 1) * pageSize; 
	        var end = start + pageSize; 
	        var cName = $("#communityName").val(); //小区名称
	    	var familyCount =$("#familyCount").val();
	    	var terminalCount=$("#terminalCount").val(); 
	    	var familyCountKey=$("#cc2").val();
	    	var terminalKey=$("#cc3").val();
	    	var provinceCode=$("#provinceCode").val();
	    	var cityCode=$("#cityCode").val();
	    	var areaCode=$("#areaCode").val();
	        $("#dg").datagrid("reload", 
                {
                    pageNo:pageNo,
                    communityName:cName,
                    familyCount:familyCount,
                    terminalCount:terminalCount,
                    peopleKey:familyCountKey,
                    terminalKey:terminalKey,
                    province:provinceCode,
                    city:cityCode,
                    country:areaCode
                }
            );
            toopTip(".seePic","查看");
            toopTip(".editPic","编辑");

        }
	}); 
    
});

window.onload=function(){
 $("#areaCode").val("");
 $("#provinceCode").val("");
 $("#cityCode").val("");
 $("#familyCount").val("");
 $("#terminalCount").val("");
 $("#communityName").val("");
//	var cName = $("#communityName").val(); //小区名称
//	var familyCount =$("#familyCount").val();
//	var terminalCount=$("#terminalCount").val(); 
//	var familyCountKey=$("#peopleKey").val();
//	var terminalKey=$("#terminalKey").val();
//	var provinceCode=$("#provinceCode").val();
//	var cityCode=$("#cityCode").val();
//	var areaCode=$("#areaCode").val();
//	if(terminalKey!=""&&terminalKey!=undefined){
//		$("#cc3").combobox("select",terminalKey);
//	}
//	if(familyCountKey!=""&&familyCountKey!=undefined){
//		$("#cc2").combobox("select",familyCountKey);
//	}

	$('#dg').datagrid({url :'communityAction!list.html'});
//		queryParams:{
//			communityName:cName,
//	  		familyCount:familyCount,
//	  		terminalCount:terminalCount,
//	  		peopleKey:familyCountKey,
//	  		terminalKey:terminalKey,
//	  		province:provinceCode,
//	  		city:cityCode,
//	  		country:areaCode
//		}
			
}


//查询数据
function serch(){
	var cName = $("#communityName").val(); //小区名称
	var familyCount =$("#familyCount").val();
	var terminalCount=$("#terminalCount").val(); 
	var familyCountKey=$("#cc2").val();
	var terminalKey=$("#cc3").val();
	var provinceCode=$("#provinceCode").val();
	var cityCode=$("#cityCode").val();
	var areaCode=$("#areaCode").val();
	$("#terminalKey").val(terminalKey);
	$("#peopleKey").val(familyCountKey);
	$("#dg").datagrid('load',
			  {
		  		communityName:cName,
		  		familyCount:familyCount,
		  		terminalCount:terminalCount,
		  		peopleKey:familyCountKey,
		  		terminalKey:terminalKey,
		  		province:provinceCode,
		  		city:cityCode,
		  		country:areaCode
		  		}); 
}

//查看小区信息
function godetail(id){
	window.location.href="communityAction!communityView.html?id="+id;
}

//编辑小区
function goEdit(id){
	window.location.href="communityAction!communityEdit.html?id="+id;
}

//删除小区信息
function removeCommunity(id){
	parent.$.messager.confirm('','您确认想要删除该小区信息吗？',function(r){
	    if(r){    
	       $.ajax({
	    	   type: "post",
	    	   url:'communityAction!communityDelete.html',
	    	   data:'id='+id,
	    	   dataType:'JSON',
	    	   cache:false,
	    	   success:function(msg){
	    		   if(msg.result=="success"){
	    		   		infoMask(msg.success);
	    		   }else{
	    		   		infoMask(msg.error);
	    		   }
	    	   }
	       });    
	    }else{
        }
	});  
}


//导出小区数据
function exportData(){

	  var hasDataValue = $("#dg").datagrid("getRows");
    if(hasDataValue.length<=0){
    	infoMask('没有数据！');
    }else{
        window.location.href="communityAction!exportToExcel.html";
    }
}
