var myChart;  
var eCharts;
$(function(){
//	bd.radioNotCheck();//单选框可不选
	bd.radioCss();//单选框样式重置
})	
 
        //创建ECharts图表方法  closed="true"
        function DrawEChart(ec) {  
            eCharts = ec;  
            myChart = eCharts.init(document.getElementById('main'));  
            myChart.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
           var options = {
              title : {
		//text: '活跃用户情况',	
               // subtext: '（％）'	
	    },
	    tooltip : {
		trigger: 'axis'
	    },
	    legend: {
		data:['活跃用户']
	    },
	    toolbox: {
		show : false,
		feature : {
		    mark : {show: true},
		    dataView : {show: true, readOnly: false},
		    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		    restore : {show: true},
		    saveAsImage : {show: true}
		}
	    },
	    calculable : true,
	    xAxis : [
		{
		    type : 'category',
		    boundaryGap : false,
		    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08','2016/08/09','2016/08/10']
		}
	    ],
	    yAxis : [
		{
		    type : 'value'
		}
	    ],
	    series : [
		{
		    name:'活跃用户',
		    type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[0.03, 0.05, 0.15, 0.30, 0.18, 0.25, 0.20,0.17,0.09,0.19]
		}
	    ]
           }; 
            myChart.setOption(options); //先把可选项注入myChart中  
            myChart.hideLoading();  
            getChartData();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        

$(document).ready(function(){  
    //var data = $.parseJSON(activeUser);    
    //$('#activeUser').datagrid('loadData', data);
    getDatagridData('','','','');
	// 路径配置
        require.config({
            packages: [{
                name: 'echarts',
                location: '../echarts',
                main: 'echarts'
            }]
        });
  
        require(  
            [ 'echarts',   
              'echarts/chart/line'  
            ], DrawEChart //异步加载的回调函数绘制图表  
        );  

      //顶部工具栏初始
//      topInitialize();
});
var echartsData ;

function getDatagridData(districtrevVlues, areaValues, selectTime,seleteType, selectUser, selectOS){
	$.ajaxSettings.async = false; //同步
	var data = "";
		var url = '/horiBigData/admin/activeUserController/showActiveUser';
		/*$.getJSON(url,{
			cityId : districtrevVlues,
			organizationSeq : areaValues,
			selectTime : selectTime,
			seleteType : seleteType,
			q : new Date().getTime()
		},function(msg){
			 if(msg!=null){
				 data = msg;
			 }else{
				 alert("this is error");
			 }
		});*/
		var params =  {
				cityId : districtrevVlues,
				organizationSeq : areaValues,
				selectTime : selectTime,
				seleteType : seleteType,
				userFormed : selectUser,
				OSId : selectOS,
				q : new Date().getTime()
    		}
		
		AJAXUtil.getData(url,false,params,function(msg){
    		if(msg.code==JsonResult[0].code){
    			data = msg.data;
    		}
    		if(msg.code==JsonResult[1].code){
    			window.location="/horiBigData/globalError.htm";
    		}
    	});
	 
		 echartsData= data.echartsData;
		 ///前端处理数据
         $('#activeUser').datagrid('loadData', data.activeUserList);  	
}



//活跃度
function activeData(value, rowData, rowIndex){
	var forderPercent = "0%";
	if (rowData["userTotal"]) {
		forderPercent = decimal(rowData["activeTotal"]
		/ rowData["userTotal"], 2)
		+ '%';
	}
	return forderPercent;
}

//流失率
function lostData(value, rowData, rowIndex){
	var seleteTypeId = $('input[name="selType"]:checked').val();
	if (seleteTypeId == ''||seleteTypeId == 'selectDay') {
		var forderPercent = "0%";
		if (rowData["userTotal"]) {
			forderPercent = decimal(rowData["lostTotal"]
			/ rowData["userTotal"], 2)
			+ '%';
		}
		$('#lostTotalId').show();
		$('#lostRatioId').show();
		$('#deathTotalId').show();
		$('#deathRatioId').show();
	}else{
		forderPercent = '';
		$('#lostTotalId').hide();
		$('#lostRatioId').hide();
		$('#deathTotalId').hide();
		$('#deathRatioId').hide();
	}
	return forderPercent;
}
//僵尸率
function deathData(value, rowData, rowIndex){
	var seleteTypeId = $('input[name="selType"]:checked').val();
	if (seleteTypeId == ''||seleteTypeId == 'selectDay') {
		var forderPercent = "0%";
		if (rowData["userTotal"]) {
			forderPercent = decimal(rowData["deathTotal"]
			/ rowData["userTotal"], 2)
			+ '%';
		}
		$('#lostTotalId').show();
		$('#lostRatioId').show();
		$('#deathTotalId').show();
		$('#deathRatioId').show();
	}else{
		forderPercent = '';
		$('#lostTotalId').hide();
		$('#lostRatioId').hide();
		$('#deathTotalId').hide();
		$('#deathRatioId').hide();
	}
	return forderPercent;
}

/**
 * 保留两位小数
 * 
 * @param num
 * @param v
 * @returns {Number}
 */
function decimal(num, v) {
	var vv = Math.pow(10, v);
	return Math.round(num * 100 * vv) / vv;
}


function find() {
	// 获取地域的值
	var districtrevVlues = bd.getIds('.arealist .delPic');
	// 获取小区的值
	var areaValues =bd.getIds('.communitylist .delPic');
	// 获取日 周 月的radio
	var seleteType = $("input[type='radio']:checked").val();
	// 获取时间的值
	var selectTime=$('#dateFrame .textbox-value').val();
	// 获取用户构成
	var selectUser = $("input[name='selUserformed']:checked").val();
	// 获取操作系统
	var selectOS = $("input[name='selOS']:checked").val();
	
	if (selectUser == "all") {
		selectUser = "";
	}
	
	var selectOS;
	if (selectOS == "all") {
		selectOS = "";
	}
	// alert('时间：：'+selectTime)
	getDatagridData(districtrevVlues, areaValues, selectTime,seleteType, selectUser, selectOS);
	getChartData() ;
} 

/**
 * 封装echarts数据
 */
function getChartData() {
	var options = myChart.getOption();
	options.series[0].data = echartsData.series[0].data;
	options.xAxis[0].data = echartsData.xAxis[0].data;
	myChart.setOption(options, true);
	myChart.hideLoading();
}
function doExportPoi(){
	// 获取地域的值
	var districtrevVlues = bd.getIds('.arealist .delPic');
	// 获取小区的值
	var areaValues =bd.getIds('.communitylist .delPic');
	// 获取日 周 月的radio
	var seleteType = $("input[type='radio']:checked").val();
	// 获取时间的值
	var selectTime = $('.textbox-value').val();
	// 获取用户组成
	var selectUser = $("input[name='selUserformed']:checked").val();
	// 获取操作系统
	var selectOS = $("input[name='selOS']:checked").val();
	
	if (selectUser == "all") {
		selectUser = "";
	}
	
	var selectOS;
	if (selectOS == "all") {
		selectOS = "";
	}
	
	location.href = "/horiBigData/admin/activeUserController/export?selectType="+seleteType+"&selectTime="+selectTime+"&cityId="+districtrevVlues+"&organizationSeq" +
			"="+areaValues+"&userFormed="+selectUser+"&OSId="+selectOS;
}