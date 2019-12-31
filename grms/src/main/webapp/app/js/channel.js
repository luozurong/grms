var myChart;  
var eCharts;  
var arrData;
var echartsData;
var myChartOption; 

  
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
				text: '新用户',	
		               subtext: '（人）'	
			    },
		    tooltip : {
				trigger: 'axis'
			    },
		    legend: {
				data:['苹果AppStore','豌豆夹','360手机助手','腾讯应用宝','百度应用','安卓应用市场']
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
		    /*xAxis : [
			{
			    type : 'category',
			    boundaryGap : false,
			    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08','2016/08/09','2016/08/10']
			}
		    ],*/
		    //获取服务器中的数据
		    xAxis :arrData.xAxis,
		    yAxis : [
				{
				    type : 'value'
				}
		    ],
		    
		    series : arrData.series,
		  
		    /*series : [
			{
			    name:'苹果AppStore',
			    type:'line',
			    smooth:true,
			    itemStyle: {normal: {areaStyle: {type: 'default'}}},
			    data:[400, 390, 380, 410, 350, 300, 280,350,320,350]
			},
	                {
			    name:'豌豆夹',
			    type:'line',
			    smooth:true,
			    itemStyle: {normal: {areaStyle: {type: 'default'}}},
			    data:[500, 550, 600, 800, 700, 750, 600,500,450,400]
			},
	                {
			    name:'360手机助手',
			    type:'line',
			    smooth:true,
			    itemStyle: {normal: {areaStyle: {type: 'default'}}},
			    data:[50, 80, 100, 90, 60, 40, 75,85,95,60]
			},
	                {
			    name:'腾讯应用宝',
			    type:'line',
			    smooth:true,
			    itemStyle: {normal: {areaStyle: {type: 'default'}}},
			    data:[150, 180, 200, 190, 160, 140, 175,185,195,160]
			},
	                {
			    name:'百度应用',
			    type:'line',
			    smooth:true,
			    itemStyle: {normal: {areaStyle: {type: 'default'}}},
			    data:[250, 280, 300, 290, 260, 240, 275,285,295,260]
			},
	                {
			    name:'安卓应用市场',
			    type:'line',
			    smooth:true,
			    itemStyle: {normal: {areaStyle: {type: 'default'}}},
			    data:[350, 380, 400, 390, 360, 340, 375,385,395,360]
			}
		    ]*/
		    
		    
           }; 
           /*console.log("--------------xAxis------------------");
		   console.log(options.xAxis);
		   console.log("--------------series------------------");
		   console.log(options.series[0].name);
		   console.log(options.series[0].data[0]);
		   console.log(arrData.series[0].name);
		   console.log(arrData.series[0].type);
		   console.log(arrData.series[0].smooth);
		   console.log(arrData.series[0].itemStyle);
		   console.log(arrData.series[0].data);*/
		    /*type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[400, 390, 380, 410, 350, 300, 280,350,320,350]*/
		   //alert(options.title.text);
           
            myChart.setOption(options); //先把可选项注入myChart中  
            myChart.hideLoading();  
            //装载全局变量
            myChartOption = myChart.getOption();
        }  
        
//载入页面时触发
$(document).ready(function(){   
//		bd.radioNotCheck();//单选框可不选
		bd.radioCss();//单选框样式重置
        $.ajaxSettings.async = false; //同步
	    var data1 = "";
        var myid = '10086';
        var url = '/horiBigData/app/channelAnalysisController/showChannelAnalysis';
        $.getJSON(url,{
				myid:myid
			},function(msg){
				 if(msg!=null){
					 data1 = msg;
	        	 }else{
	        		 alert("this is error");
	        	 }
		});
        
        arrData = data1;
        var data = data1.viewMap;
       //var data = data1.viewMap;
        $('#channelId').datagrid('loadData', data);
        
        
        
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
});

/**
 * 点击查找
 */
function find() {
	echartsData = "";
	// 获取地域的值
	var districtrevVlues = bd.getIds('.arealist .delPic');
	// 获取小区的值
	var areaValues =bd.getIds('.communitylist .delPic');
	//console.log("areaValues:"+areaValues);
	// 获取日 周 月的radio
	var radioValues = $("input[type='radio']:checked").val();
	//console.log("radioValues:"+radioValues);
	// 获取时间的值
	var selectTime=$('#dateFrame .textbox-value').val();
	//console.log("selectTime:"+selectTime);
	//载入表格数据
	getDatagridData(districtrevVlues, areaValues, radioValues, selectTime);
	//载入图表数据
	getChartData();

}

//点击导出
function exportPoi(){
	//alert("lai");
	// 获取时间的值
	var selectTime = $('.textbox-value').val();
	// 获取日 周 月的radio
	var radioValues = $("input[type='radio']:checked").val();
	// 获取地域的值
	var districtrevVlues = bd.getIds('.arealist .delPic');
	// 获取小区的值
	var areaValues =bd.getIds('.communitylist .delPic');
    //页面跳转
    location.href="/horiBigData/app/channelAnalysisController/export?selectVal="+radioValues+"&selectTime="+selectTime+"&selectCity="+districtrevVlues+"&selectArea="+areaValues;
}

/**
 * 获取封装成列表的数据
 */
 function  getDatagridData(districtrevVlues,areaValues,dateValues,selectTime){
	    $.ajaxSettings.async = false; //同步
	    var data = "";
        var url = '/horiBigData/app/channelAnalysisController/searchChannelAnalysis';
        $.getJSON(url,{
        	  selectVal:dateValues,
        	  selectTime:selectTime,
        	  selectCity:districtrevVlues,
        	  selectArea:areaValues
		},function(msg){

			 if(msg.series!=null){
				 data = msg;
        	 }else if(dateValues=="selectWeek"){
        		 $.messager.alert("警告框","尚未有类型为'周'的渠道分布信息,系统将自动切换成'日'类型的渠道分布信息","warning",function(){
 					//重新设回日
        			 $("#dayId").replaceWith("<input id = 'dayId' type='radio' name='lang' value='selectDay' checked />");
        		 });
        	 }else{
        		 $.messager.alert("警告框","尚未有类型为'月'的渠道分布信息,系统将自动切换成'日'类型的渠道分布信息","warning",function(){
  					//重新设回日
        			 $("#dayId").replaceWith("<input id = 'dayId' type='radio' name='lang' value='selectDay' checked />");
         		 });
        	 }
		});
        //复制数据
        echartsData = data;
        
       //转载datagrid
        $('#channelId').datagrid('loadData', data.viewMap);
}
 
 /**
  * 封装echarts数据
  */
 function getChartData() {
 	var options = myChartOption;
		options.series = echartsData.series;
 	options.xAxis = echartsData.xAxis;
 	myChart.setOption(options, true);
 	myChart.hideLoading();
 }




