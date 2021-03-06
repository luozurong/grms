var pageNoAll = '1';
var pageSizeAll = '10';
var mapButton = {};
mapButton['search'] = 1;
mapButton['upload'] = 1;
mapButton['download'] = 1;
mapButton['delete'] = 1;
mapButton['batchDelete'] = 1;
$(function() {
	configMenuItem("提案管理", "提案列表");
	initButton();
	findMenu('', '');
});

function selectAll() {
	pageNoAll = '1';
	$('#pp').pagination({
		pageNumber : 1
	});
	findMenu('', '');
}

// 时间格式化函数
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()// millisecond  
    }  
    if (/(y+)/.test(format)) {
    	format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    } 
    for (var k in o){
    	if (new RegExp("(" + k + ")").test(format)){
    		format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    	}
    }
    return format;  
}

function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)  
}  

function initButton(){
	var menuUrl="/grms/schemeInfo/list.html";
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
              	if(mapButton['upload']==0){
              		$("#upload").hide();
              	}
              	if(mapButton['batchDelete']==0){
            	  	$("#batchDelete").hide();
              	}
            }
        }  
    }); 
}

// 查询
function findMenu(pageSize, pageNo) {
	var name = $('#name').val();
	pageNo = pageNoAll;
	pageSize = pageSizeAll;
	$.ajax({
		type : "GET", // 提交方式
		url : "/grms/schemeInfo",// 路径
		dataType : "json",// 数据，这里使用的是Json格式进行传输
		data : {keyword : name,pageNumber:pageNo,pageSize:pageSize},
		success : function(result) {// 返回数据根据结果进行相应的处理
			if (result.code == 0) {
				var data = result.data;
				var total = result.total;
				refresh(data);
				paginationpage(total);
			} else {
				infoMask('加载列表失败');
			}
		}
	});
}

function refresh(data) {
	// 表格数据渲染
	$('#dg').datagrid({
		border : true,
		scrollbarSize : 0,
		nowrap : false,// 允许换行
		data : data,
		fitColumns : true,// 宽度自适应
		emptyMsg : '<span>无记录</span>',
		onLoadSuccess : function() { // dom操作
			$('#dg').datagrid('resize');
			var dataHeight = $(".datagrid-view").height() - 19;
			$(".datagrid-view").css("height", dataHeight);
		},
		columns : [ 
				[{
	                field:'ck',
	                title:'',
	                checkbox:true,
	                width:5,
	                align:'left'
	            },
				{
					field : 'createTime',
					title : '上传时间',
					width : 40,
					align : 'center',
					formatter : formatDatebox
				},
				{
					field : 'name',
					title : '提案名称',
					width : 40,
					align : 'center'
				},
				{
					field : 'handle',
					title : '操作',
					width : 15,
					align : 'center',
					formatter : function(value, row, index) {
						var download = '<a href="javascript:;" onclick="down(\'' + index + '\')">下载</a>';
						var drop = '<a href="javascript:;" onclick="dealDeleteOne(\'' + index + '\')">删除</a>';
						if(mapButton['download'] == 0){
							download = "";
						}
						if(mapButton['delete'] == 0){
							drop = "";
						}
						return download + drop;
				}
				}] 
		]
	});
}

function paginationpage(total) {
	// 分页
	$('#pp').pagination({
		total : total,
		layout : [ 'list', 'first', 'prev', 'links', 'next', 'last', 'manual' ],
		emptyMsg : '<span>无记录</span>',
		showRefresh : true,
		displayMsg : ' ',
		onSelectPage : function(pageNo, pageSize) {
			if(pageNo == 0 || pageNo == '0'){
				pageNo = '1';
			}
			pageNoAll = pageNo;
			pageSizeAll = pageSize;
			findMenu('', '')
		}
	});
	$(".pagination-page-list").parent().append("条");
	$(".pagination-page-list").parent().prepend("共计" + total + "条,每页显示： ");
}

function upload(id) {
	var url = "../schemeInfo/add.html";
	window.location.href = url;
}

function down(index) {
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	var url = "../schemeInfo/download?id=" + checkedItem.id;
	window.location.href = url;
}

function dealDeleteMany() {
	var checkedItems = $('#dg').datagrid('getChecked');
	if (checkedItems.length) {
		createConfirm("消息确认", "请确认是否删除？", true, false, "删除", "取消", drop,
				"", checkedItems, "");
	} else {
		infoMask("请选择要删除的项目");
	}
}

function dealDeleteOne(index) {
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	createConfirm("消息确认", "确认删除吗？", true, false, "删除",
			"取消", drop, "", checkedItem, "");
}

function drop(checkedItems){
	var ids = "";
	if(checkedItems instanceof Array){
		for(var i = 0;i < checkedItems.length; i++){
			if(i == 0){
				ids = checkedItems[i].id;
			}else{
				ids = ids + "," + checkedItems[i].id;
			}
		}
	}else{
		ids = checkedItems.id;
	}
	$.ajax({  
	    type : "POST",  //提交方式  
	    url : "../schemeInfo/delete",//路径  
	    dataType : "json",//数据，这里使用的是Json格式进行传输  
	    data:  {ids:ids},  
	    success : function(result) {//返回数据根据结果进行相应的处理  
	        if (result.code == 0) { 
	        	infoMask("删除成功");
	        	findMenu('','');
	        	return;
	        } else {  
	        	infoMask("删除失败:服务器响应异常");
	        }  
	    },
		error: function(){
			infoMask('网络异常，请稍后重试！');
		}
	}); 
}
