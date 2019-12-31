/**
 * 用户运营/媒管/电商部门专用js
 */
var pageNoAll = '1';
var pageSizeAll = '10';
$(function() {
	configMenuItem("执行管理", "执行清单列表");
	
	var jumpStr = $("#jumpStr").val();
	if (jumpStr != null && jumpStr != '') {
		var projectActionQueryBean = JSON.parse(decodeURIComponent(jumpStr))
		$('#projectName').val(projectActionQueryBean.projectName);
		$('#startTime').datebox('setValue', projectActionQueryBean.startTime);	
		$('#endTime').datebox('setValue', projectActionQueryBean.endTime);	
		$('#actionStatus').combobox('setValue', projectActionQueryBean.actionStatus);
		pageNoAll = projectActionQueryBean.pageNumber;
		pageSizeAll = projectActionQueryBean.pageSize;
	}
	findMenu('', '');
});

function selectAll() {
	pageNoAll = '1';
	$('#pp').pagination({
		pageNumber : 1

	});
	// 修改是否点击过搜索按钮状态
	var projectName = $('#projectName').val();
	var actionCode = $('#projectActionCode').val();
	var startTime = $('#startTime').datebox('getValue');// 投放开始日期(执行开始时间)
	var endTime = $('#endTime').datebox('getValue');// 投放结束日期(投放结束时间)
	var actionStatus = $('#actionStatus').combobox('getValue');// 执行清单状态
	findMenu('', '');
}

//------------------------------时间格式化函数   start--------------------------------------------------------
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
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
}  
// ------------------------------时间格式化函数   end--------------------------------------------------------
// 比较时间大小
function checkEndTime(){
	var startTime = $("#startTime").val();
	var start = new Date(startTime.replace("-", "/").replace("-", "/"));
	var endTime = $("#endTime").val();
	var end = new Date(endTime.replace("-", "/").replace("-", "/"));
	
	if(end < start) {
	 	return false;
	}
	return true;
}


// 查询
function findMenu(pageSize, pageNo) {
	var endFlag = checkEndTime();
	if (!endFlag) {
		$.messager.alert('消息提醒','结束时间不能小于开始时间！！！');
		return;
	}
	var projectName = $('#projectName').val();
	var actionCode = $('#projectActionCode').val();
	var startTime = $('#startTime').datebox('getValue');// 投放开始日期(执行开始时间)
	var endTime = $('#endTime').datebox('getValue');// 投放结束日期(投放结束时间)
	var actionStatus = $('#actionStatus').combobox('getValue');// 执行清单状态
	
	pageNo = pageNoAll;
	pageSize = pageSizeAll;
	var projectActionQueryBean = '{"projectName":"' + projectName + '","actionCode":"' + actionCode 
			+ '","startTime":"' + startTime + '","endTime":"' + endTime + '","actionStatus":"' + actionStatus 
			+'","pageNumber":"' + pageNo + '","pageSize":"' + pageSize + '"}';
	
	//console.debug(projectActionQueryBean);
	$.ajax({
		type : "POST", // 提交方式
		url : "/grms/zhongheDepart/umbListNew.html",// 路径
		dataType : "json",// 数据，这里使用的是Json格式进行传输
		data : {"projectActionQueryBean" : projectActionQueryBean},
		success : function(result) {// 返回数据根据结果进行相应的处理
			if (result.success) {
				var data2 = result.rows;
				var total = result.total;
				refresh(data2);
				paginationpage(total, data2);
			} else {
				
			}
		}
	});
}
function refresh(data2) {
	// 表格数据渲染
	$('#dg').datagrid({
		border : true,
		scrollbarSize : 0,
		nowrap : false,// 允许换行
		data : data2,
		fitColumns : true,// 宽度不自适应
		emptyMsg : '<span>无记录</span>',
		onLoadSuccess : function() { // dom操作
			$('#dg').datagrid('resize');
			var dataHeight = $(".datagrid-view").height() - 19;
			$(".datagrid-view").css("height", dataHeight);
		},
		columns : [ [
				{
					field : 'projectProductId',
					title : '项目清单主键id',
					width : 220,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'beginTime',
					title : '投放开始日期',
					width : 190,
					align : 'center',
					formatter : formatDatebox
				},
				{
					field : 'endTime',
					title : '投放结束日期',
					width : 190,
					align : 'center',
					formatter : formatDatebox
				},
				{
					field : 'businessType',
					title : '执行组织',
					width : 190,
					align : 'center',
					formatter : function(value) {
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
				},
				{
					field : 'projectName',
					title : '项目名称',
					width : 280,
					align : 'center'

				},
				{
					field : 'projectActionId',
					title : '执行清单主键id',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'actionCode',
					title : '执行清单ID',
					width : 190,
					align : 'center'
				},
				{
					field : 'areaNum',
					title : '小区数量',
					width : 250,
					align : 'center'
				},
				{
					field : 'actionStatus',
					title : '执行状态',
					width : 180,
					align : 'center',
					formatter : function(value) {
						if (value == 1) {
							return "待确认";
						} else if (value == 2) {
							return "策划中";
						} else if (value == 3) {
							return "待执行";
						} else if (value == 4) {
							return "执行中";
						} else if (value == 5) {
							return "已完成";
						}
					}
				},
				{
					field : 'exceptionStatus',
					title : '是否异常',
					width : 180,
					align : 'center',
					formatter : function(value) {
						if (value == 0) {
							return "<span style='color:red;'>异常</span>";
						} else if (value == 1) {
							return "正常";
						}
					}
				},
				{
					field : 'handle',
					title : '操作',
					width : 574,
					align : 'center',
					formatter : function(value, row, index) {
						var actionCode = row.actionCode;// 执行清单code
						var hrefUrl = "/grms/zhongheDepart/actionDetail.do?actionCode="+actionCode;
					    var str = "";
					    str += '<a href=\'' + hrefUrl + '\' style="color: blue;">查看详情</a>';
					    return str;
					}
				} ] ]
	});
	
}

function paginationpage(total, data2) {
	// 分页
	$('#pp').pagination(
			{
				total : total,
				layout : [ 'list', 'first', 'prev', 'links', 'next', 'last', 'manual' ],
				emptyMsg : '<span>无记录</span>',
				showRefresh : true,
				displayMsg : ' ',
				onSelectPage : function(pageNo, pageSize) {
					pageNoAll = pageNo;
					pageSizeAll = pageSize;
					findMenu('', '')
				}
			});
	$(".pagination-page-list").parent().append("10条");
	$(".pagination-page-list").parent().prepend("共计" + total + "条,每页显示： ");
	//$(".pagination-page-list").remove();
}


function LessThan(oTextArea){
    //获得textarea的maxlength属性
    var MaxLength = oTextArea.getAttribute("maxlength");
    var num = oTextArea.value.length;  
    $('#txtNum').html(num+"/500");
    //返回文本框字符个数是否符号要求的boolean值
    return oTextArea.value.length < oTextArea.getAttribute("maxlength");
}
