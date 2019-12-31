/**
 * 用户运营/媒管/电商部门专用js
 */
var pageNoAll = '1';
var pageSizeAll = '10';
$(function() {
	configMenuItem("执行管理", "媒管/用户/电商执行清单列表");
	
	var jumpStr = $("#jumpStr").val();
	if (jumpStr != null && jumpStr != '') {
		var projectActionQueryBean = JSON.parse(decodeURIComponent(jumpStr))
		$('#projectName').val(projectActionQueryBean.projectName);
		$('#startTime').datebox('setValue', projectActionQueryBean.startTime);	
		$('#endTime').datebox('setValue', projectActionQueryBean.endTime);	
		$('#actionStatus').combobox('setValue', projectActionQueryBean.actionStatus);
		pageNoAll = projectActionQueryBean.pageNumber;
		pageSizeAll = projectActionQueryBean.pageSize;
		// 修改是否点击过搜索按钮状态
		$("#is_selectAll").val(projectActionQueryBean.isSelectAll);
		if (projectActionQueryBean.isSelectAll == "1") {
			$('#projectActionCode').val(projectActionQueryBean.projectActionCode);
		}
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
	
	if (projectName != '' || actionCode != '' || startTime != '' || endTime != '' || actionStatus != '') {
		$("#is_selectAll").val("1");
		console.debug("修改状态");
	}
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
	
	var is_selectAll = $("#is_selectAll").val();
	if (is_selectAll == '0') {
		projectName = '';
		actionCode = '';
		startTime = '';
		endTime = '';
		actionStatus = '';
	}
	
	pageNo = pageNoAll;
	pageSize = pageSizeAll;
	var projectActionQueryBean = '{"projectName":"' + projectName + '","actionCode":"' + actionCode 
			+ '","startTime":"' + startTime + '","endTime":"' + endTime + '","actionStatus":"' + actionStatus 
			+'","pageNumber":"' + pageNo + '","pageSize":"' + pageSize + '"}';
	
	//console.debug(projectActionQueryBean);
	$.ajax({
		type : "POST", // 提交方式
		url : "/grms/projectAction/getYMDPAListData.html",// 路径
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
			initButton();
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
					field : 'departmentId',
					title : '部门id',
					width : 220,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'departmentName',
					title : '部门名称',
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
					width : 220,
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
				/*{
					field : 'actionStatus2',
					title : '执行组织确认',
					width : 180,
					align : 'center',
					formatter : function(value,row,index) {
						value = row.actionStatus;
						if (value == 1) {
							return "未确认";
						} else {
							return "确认";
						}
					}
				},
				{
					field : 'exceptionResult',
					title : '异常处理结果',
					width : 200,
					align : 'center',
					formatter : function(value) {
						if (value == 1) {
							return "置换场地";
						} else if (value == 2) {
							return "扣款";
						} else if (value == 3) {
							return "暂停";
						} else if (value == 4) {
							return "完成";
						} else if (value == 5) {
							return "终止";
						}
					}
				},*/
				{
					field : 'handle',
					title : '操作',
					width : 574,
					align : 'center',
					formatter : function(value, row, index) {
						var actionStatus = row.actionStatus;// 执行状态
						var exceptionStatus = row.exceptionStatus;// 异常状态
						var receiveAccount = row.receiveAccount;// 领取人账号
						var account = $("#account").val();// session中的账号
						var departmentId = row.departmentId;// 执行部门id
						var departId = $("#departId").val();// 当前账号id
						
						var viewDetail = '<a href="javascript:;" style="display:none;" data-id="1" onclick="viewDetail(\'' + index + '\')">活动详情</a>';
						var reportExceptionRecord = '<a href="javascript:;" style="display:none;" data-id="4" onclick="reportExceptionRecord(\'' + index + '\')">异常上报记录</a>';
						if (departId != departmentId && account != "admin") {
							var reportExceptionRecord = '<a href="javascript:;" style="display:none;" data-id="4" onclick="reportExceptionRecord(\'' + index + '\')">异常上报记录</a>';
							return viewDetail + reportExceptionRecord;
						} else {
							if (actionStatus == 1) {// 待领取
								var changeStatus2;
								if (exceptionStatus == 1) {// 正常
									changeStatus2 = '<a href="javascript:;" style="display:none;" data-id="2" onclick="changeStatus(2,\'' + index + '\')">确认领取</a>';// 确认领取之后，状态变为"策划中"(2)
								} else if (exceptionStatus == 0) {// 异常
									changeStatus2 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">确认领取</a>';
								}
								return viewDetail + changeStatus2 + reportExceptionRecord;
							} else if (actionStatus == 2) {// 策划中
								var changeStatus3;
								var reportException;
								if (exceptionStatus == 1) {// 正常
									changeStatus3 = '<a href="javascript:;" style="display:none;" data-id="2" onclick="changeStatus(3,\'' + index + '\')">完成策划</a>';// 完成策划后，状态变为"待执行"(3)
									reportException = '<a href="javascript:;" style="display:none;" data-id="3" onclick="reportException(\'' + index + '\')">上报异常</a>';
								} else if (exceptionStatus == 0) {// 异常
									changeStatus3 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">完成策划</a>';// 完成策划后，状态变为"待执行"(3)
									reportException = '<a href="javascript:;" style="display:none;color:#666;" data-id="3">上报异常</a>';
								}
								if (account != receiveAccount) {
									changeStatus3 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">完成策划</a>';// 完成策划后，状态变为"待执行"(3)
								}
								return viewDetail + changeStatus3 + reportException + reportExceptionRecord;
							} else if (actionStatus == 3) {// 待执行
								var changeStatus4;
								var reportException;
								if (exceptionStatus == 1) {// 正常
									changeStatus4 = '<a href="javascript:;" style="display:none;" data-id="2" onclick="changeStatus(4,\'' + index + '\')">进入执行</a>';// 进入执行，状态变为"执行中"(4)
									reportException = '<a href="javascript:;" style="display:none;" data-id="3" onclick="reportException(\'' + index + '\')">上报异常</a>';
								} else if (exceptionStatus == 0) {// 异常
									changeStatus4 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">进入执行</a>';// 进入执行，状态变为"执行中"(4)
									reportException = '<a href="javascript:;"" style="display:none;color:#666;" data-id="3">上报异常</a>';
								}
								if (account != receiveAccount) {
									changeStatus4 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">进入执行</a>';// 进入执行，状态变为"执行中"(4)
								}
								return viewDetail + changeStatus4 + reportException + reportExceptionRecord;
							} else if (actionStatus == 4) {// 执行中
								var changeStatus5;
								var reportException;
								if (exceptionStatus == 1) {// 正常
									changeStatus5 = '<a href="javascript:;" style="display:none;" data-id="2" onclick="changeStatus(5,\'' + index + '\')">执行完成</a>';// 执行完成，状态变为"已完成"(5)
									reportException = '<a href="javascript:;" style="display:none;" data-id="3" onclick="reportException(\'' + index + '\')">上报异常</a>';
								} else if (exceptionStatus == 0) {// 异常
									changeStatus5 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">执行完成</a>';// 执行完成，状态变为"已完成"(5)
									reportException = '<a href="javascript:;" style="display:none;color:#666;" data-id="3">上报异常</a>';
								}
								if (account != receiveAccount) {
									changeStatus5 = '<a href="javascript:;" style="display:none;color:#666;" data-id="2">执行完成</a>';// 执行完成，状态变为"已完成"(5)
								}
								return viewDetail + changeStatus5 + reportException + reportExceptionRecord;
							} else if (actionStatus == 5) {// 已完成
								return viewDetail + reportExceptionRecord;
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

//执行清单详情
function viewDetail(index){
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	var actionCode = checkedItem.actionCode;
	window.location.href="/grms/zhongheDepart/actionDetail.do?actionCode="+actionCode;
}

// 确认领取、完成策划、进入执行、执行完成操作
function changeStatus(status, index){
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	var projectActionId = checkedItem.projectActionId;
	$.ajax({
		type : "POST",
		url : "/grms/projectAction/changeStatus.html",
		dataType : "json",
		data : {actionStatus:status, projectActionId: projectActionId},
		success : function (result){
			if (result.success) {
				$.messager.alert('消息提醒','操作成功！！！');
				findMenu('','');// 刷新页面
			} else {
				if (result.hasNoContract) {
					$.messager.alert('消息提醒','操作失败，没有已审核的执行合同！！！');
				} else if (result.hasNobackMoneyPlan) {
					$.messager.alert('消息提醒','操作失败，没有已审核的收款计划！！！');
				} else {
					$.messager.alert('消息提醒','操作失败！！！');
				}
			}
		}
	});
}

/**
 * 跳转异常上报记录
 * @returns
 */
function reportExceptionRecord(index){
	var projectName = $('#projectName').val();
	var startTime = $('#startTime').datebox('getValue');// 投放开始日期(执行开始时间)
	var endTime = $('#endTime').datebox('getValue');// 投放结束日期(执行结束时间)
	var actionStatus = $('#actionStatus').combobox('getValue');// 执行清单状态
	var isSelectAll = $("#is_selectAll").val();
	var pageFlag = $("#pageFlag").val();
	pageNo = pageNoAll;
	pageSize = pageSizeAll;
	
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	var projectActionCode = checkedItem.actionCode;// 执行清单编码
	
	var jumpStr = '{"projectName":"' + projectName + '","projectActionCode":"' + projectActionCode +'","startTime":"' + startTime + '","endTime":"' + endTime 
			+ '","actionStatus":"' + actionStatus +'","pageNumber":"' + pageNo + '","pageSize":"' + pageSize 
			+ '","isSelectAll":"' + isSelectAll + '","pageFlag":"' + pageFlag + '"}';
	
	//console.debug(jumpStr);
	
	window.location.href="/grms/projectAction/exceptionList.html?jumpStr="+encodeURIComponent(encodeURIComponent(jumpStr));
}

/**
 * 上报异常
 * @returns
 */
function reportException(index){
	var checkedItem = $('#dg').datagrid('getData').rows[index];
	var projectActionId = checkedItem.projectActionId;// 执行清单主键id
	//var actionCode = checkedItem.actionCode;// 执行清单编码
	//var projectProductId = checkedItem.projectProductId;// 项目清单id
	var businessType = checkedItem.businessType;// 业务类型
	
	$.ajax({
		type : "POST",
		url : "/grms/projectAction/reportException.html",
		dataType : "json",
		data : {projectActionId:projectActionId},
		success : function (result){
			if (result.success) {
				saveException(projectActionId);
			} else {
				if (result.hasNoContract) {
					$.messager.alert('消息提醒','操作失败，没有已审核的执行合同！！！');
				} else if (result.hasNobackMoneyPlan) {
					$.messager.alert('消息提醒','操作失败，没有已审核的收款计划！！！');
				} else {
					$.messager.alert('消息提醒','操作失败！！！');
				}
			}
		}
	});
}

function saveException(projectActionId){
	$("#dlg_re").dialog({
		title : "上报异常确认",
		width : 500,
        height : 400,
        resizable : false,
        modal : true,
        doSize : true,
        buttons:[{
        		id : 'btn_tijiao',
				text : '提交',
				iconCls : "icon-ok",
				handler : function(){
					var exRemark = $("#exRemark").val();
					//console.debug(exRemark);
					$.ajax({
						type : "POST",
						url : "/grms/projectAction/saveException.html",
						dataType : "json",
						data : {projectActionId:projectActionId,exRemark:exRemark},
						success : function (result){
							if (result.success) {
								$.messager.alert('消息提醒','操作成功！！！');
								$("#dlg_re").dialog("close");
								findMenu('','');// 刷新页面
							} else {
								/*if (result.hasNoContract) {
									$.messager.alert('消息提醒','操作失败，没有已审核的执行合同！！！');
								} else if (result.hasNobackMoneyPlan) {
									$.messager.alert('消息提醒','操作失败，没有已审核的收款计划！！！');
								} else {
									$.messager.alert('消息提醒','操作失败！！！');
								}*/
							}
						}
					});
				}
			}]
	});
}

function LessThan(oTextArea){
    //获得textarea的maxlength属性
    var MaxLength = oTextArea.getAttribute("maxlength");
    var num = oTextArea.value.length;  
    $('#txtNum').html(num+"/500");
    //返回文本框字符个数是否符号要求的boolean值
    return oTextArea.value.length < oTextArea.getAttribute("maxlength");
}

function exportMGData(){
	$.messager.confirm('导出Excel', '是否导出?', function(r){
		if (r){
			var projectName = $('#projectName').val();
			var actionCode = $('#projectActionCode').val();
			var startTime = $('#startTime').datebox('getValue');// 投放开始日期(执行开始时间)
			var endTime = $('#endTime').datebox('getValue');// 投放结束日期(投放结束时间)
			var actionStatus = $('#actionStatus').combobox('getValue');// 执行清单状态
			
			var is_selectAll = $("#is_selectAll").val();
			if (is_selectAll == '0') {
				projectName = '';
				actionCode = '';
				startTime = '';
				endTime = '';
				actionStatus = '';
			}
			
			pageNo = pageNoAll;
			pageSize = pageSizeAll;
			var projectActionQueryBean = '{"projectName":"' + projectName + '","actionCode":"' + actionCode 
					+ '","startTime":"' + startTime + '","endTime":"' + endTime + '","actionStatus":"' + actionStatus 
					+'","pageNumber":"' + pageNo + '","pageSize":"' + pageSize + '"}';
			
			window.location.href="/grms/projectAction/exportMGData.html?projectActionQueryBean="+encodeURIComponent(projectActionQueryBean);
		}
	});
}

//需要隐藏的按钮
var mapButton = {};
var userType;
function initButton(){
	mapButton['exportMGData'] = 1;
	mapButton['selectAll'] = 1;
	mapButton['viewDetail'] = 1;
	mapButton['changeStatus'] = 1;
	mapButton['reportException'] = 1;
	mapButton['reportExceptionRecord'] = 1;
	var menuUrl="/grms/projectAction/ymdActionList.html";
    $.ajax({  
        type : "POST",  //提交方式  
        url : "/grms/initButtonController/getButttonList.html",//路径  
        data:{"menuUrl":menuUrl},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if (result.success) {
            	//console.log(result)
            	//比较需要隐藏的按钮
            	var buttonObject= result.button;
            	var admin=result.admin;
            	//console.log(admin)
            	//console.log(buttonObject)
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
	            	if(mapButton['exportMGData']==1){
	            	  $("#allBack").show();
	              	}
	            	if(mapButton['selectAll']==1){
	            		$("#selectAll").show();
	            	}
	            	if(mapButton['viewDetail']==1){
	            		$("a[data-id='1']").show();
	            	}
	            	if(mapButton['changeStatus']==1){
	            		$("a[data-id='2']").show();
	            	}
	            	if(mapButton['reportException']==1){
	            		$("a[data-id='3']").show();
	            	}
	            	if(mapButton['reportExceptionRecord']==1){
	            		$("a[data-id='4']").show();
	            	}
	            }else{
	            	$("#allBack").show();
	            	$("#selectAll").show();
	             	$("a[data-id='1']").show();
	             	$("a[data-id='2']").show();
	             	$("a[data-id='3']").show();
	            	$("a[data-id='4']").show();
	            	userType=0;
	            }
            } else {  
            	
            }  
        }  
    }); 
}