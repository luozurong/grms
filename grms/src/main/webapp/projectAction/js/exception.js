var pageNoAll = '1';
var pageSizeAll = '10';
$(function() {
	configMenuItem("执行管理", "执行清单列表");
	
	var jumpStr = $("#jumpStr").val();
	//console.debug(jumpStr);
	if (jumpStr != null && jumpStr != '') {
		var projectActionQueryBean = JSON.parse(decodeURIComponent(jumpStr))
		$('#projectActionCode').val(projectActionQueryBean.projectActionCode);
	}
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
/*Date.prototype.format = function (format) {  
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
}  */
// 时间格式化函数


// 查询
function findMenu(pageSize, pageNo) {
	var projectActionCode = $('#projectActionCode').val();// 清单id
	var status = $('#status').combobox('getValue');// 异常处理状态
	var createTime = $('#createTime').datebox('getValue');// 上报时间
	
	pageNo = pageNoAll;
	pageSize = pageSizeAll;
	var paExceptionQueryBean = '{"projectActionCode":"' + projectActionCode + '","status":"' + status + '","startTime":"' 
			+ createTime +'","pageNumber":"' + pageNo + '","pageSize":"' + pageSize + '"}';
	
	$.ajax({
		type : "POST", // 提交方式
		url : "/grms/projectAction/getExceptionListData.html",// 路径
		dataType : "json",// 数据，这里使用的是Json格式进行传输
		data : {"paExceptionQueryBean" : paExceptionQueryBean},
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
					field : 'departmentId',
					title : '上报部门id',
					width : 220,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'receiveAccount',
					title : '执行清单领取人',
					width : 220,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'departmentName',
					title : '上报部门',
					width : 190,
					align : 'center'
				},
				{
					field : 'businessType',
					title : '业务类型',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'exceptionId',
					title : '异常记录id',
					width : 220,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'projectActionCode',
					title : '清单ID',
					width : 220,
					align : 'center'
				},
				{
					field : 'createTime',
					title : '上报时间',
					width : 190,
					align : 'center',
					formatter : function(value,row,index){
						return value.substr(0,19);
					}
				},
				{
					field : 'exceptionRemark',
					title : '上报原因',
					width : 480,
					align : 'center'
				},
				{
					field : 'status',
					title : '处理状态',
					width : 190,
					align : 'center',
					formatter : function(value) {
						if (value == 0) {
							return "待处理";
						} else if (value == 1) {
							return "处理中";
						} else if (value == 2) {
							return "已处理";
						} 
					}
				},
				{
					field : 'confirmStatus',
					title : '确认处理结果',
					width : 190,
					align : 'center',
					formatter : function(value,row,index) {
						var status = row.status;
						if (status == 0) {
							return "/";
						} else if (value == 0) {
							return "未确认";
						} else if (value == 1) {
							return "已确认";
						}
					}
				},
				{
					field : 'result',
					title : '处理结果',
					width : 190,
					align : 'center',
					formatter : function(value,row,index) {
						var status = row.status;
						if (status == 0) {
							return "/";
						} else if (value == 1) {
							return "置换场地";
						} else if (value == 2) {
							return "扣款";
						} else if (value == 3) {
							return "继续";
						} else if (value == 4) {
							return "暂停";
						} else if (value == 5) {
							return "恢复";
						} else if (value == 6) {
							return "终止";
						}
					}
				},
				{
					field : 'handle',
					title : '操作',
					width : 300,
					align : 'center',
					formatter : function(value, row, index) {
						var confirmStatus = row.confirmStatus;
						var receiveAccount = row.receiveAccount; // 执行清单领取人
						var account = $("#account").val();// session中的账号
						
						if (account == receiveAccount || receiveAccount == null || account == "admin") {
							if (confirmStatus == 0) {// 未确认
								var confirm = '<a href="javascript:;" data-id="1" onclick="changeConfirmStatus(1,\'' + index + '\')">确认</a>';
								return confirm;
							} else if (confirmStatus == 1) {// 已确认
								var disableConfirm = '<a href="javascript:;" style="color:#666;">确认</a>';
								return disableConfirm;
							}
						}
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

// 确认领取、完成策划、进入执行、执行完成操作
function changeConfirmStatus(confirmStatus, index){
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	// console.debug(checkedItem);
	var id = checkedItem.exceptionId;// 异常记录id
	var projectActionCode = checkedItem.projectActionCode;// 执行清单编号
	var result = checkedItem.result;// 异常处理结果
	$.ajax({
		type : "POST",
		url : "/grms/projectAction/changeConfirmStatus.html",
		dataType : "json",
		data : {id:id, confirmStatus: confirmStatus,projectActionCode:projectActionCode,result:result},
		success : function (result){
			if (result.success) {
				$.messager.alert('消息提醒','操作成功！！！');
				findMenu('','');// 刷新页面
			} else {
				$.messager.alert('消息提醒','操作失败！！！');
			}
		}
	});
}

function goBack(){
	var jumpStr = $("#jumpStr").val();
	//console.debug(jumpStr);
	var projectActionQueryBean = JSON.parse(decodeURIComponent(jumpStr));
	//console.debug(projectActionQueryBean);
	var pageFlage = projectActionQueryBean.pageFlag;
	//console.debug(pageFlage);
	
	if (pageFlage == 'community') {
		window.location.href="/grms/projectAction/communityActionList.html?jumpStr="+encodeURIComponent(jumpStr);
	} else if (pageFlage == 'ymd') {
		window.location.href="/grms/projectAction/ymdActionList.html?jumpStr="+encodeURIComponent(jumpStr);
	}
	
}