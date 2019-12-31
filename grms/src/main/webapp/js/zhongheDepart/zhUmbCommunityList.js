
//自定义操作列
function formatOper(value,row,index) {
	console.log('进入。。。。。。');
	var actionCode = row.actionCode;// 执行清单code
	var hrefUrl = "/grms/zhongheDepart/actionDetail.do?actionCode="+actionCode;
    var str = "";
    str += '<a href=\'' + hrefUrl + '\' style="color: blue;">查看详情</a>';
    return str;
}
//编辑
function doEdit(id) {
    alert(id);
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
function actionOper(value,row,index){
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
$(function(){
	/*
	 * 抽取所有需要用得元素.
	 */
	var zhCommunityDatagrid,zhCommunityDialog,zhCommunityForm;
	zhCommunityDatagrid = $("#zhCommunity_datagrid");
	/*
	 * 初始化数据表格 
	 */
	zhCommunityDatagrid.datagrid({
		url:"/grms/zhongheDepart/umbList.do",
		fit:true,
		rownumbers:true,
		singleSelect:true,
		striped:true,
		pagination:true,
		fitColumns:true,
		toolbar:'#zhCommunity_datagrid_tb'
	});

	
});
function keyWordSearch(){
	var zhCommunityDatagrid = $("#zhCommunity_datagrid");
	var projectName = $("#projectName").val();
	var projectActionCode = $("#projectActionCode").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var actionStatus = $("#actionStatus").val();
	
	zhCommunityDatagrid.datagrid('load', {
		projectName : projectName,
		actionCode : projectActionCode,
		beginDate : startTime,
		endDate : endTime,
		actionStatus : actionStatus
	});
}


