var myChart;
var eCharts;
var arrData;
var echartsData;
var myChartOption;

// 创建ECharts图表方法 closed="true"
function DrawEChart(ec) {
	eCharts = ec;
	myChart = eCharts.init(document.getElementById('main'));
	myChart.showLoading({
		text : "图表数据正在努力加载..."
	});
	// 定义图表options
	var options = {
		title : {
		// text: '活跃用户情况',
		// subtext: '（％）'
		},
		grid:{
		  x:55,
		  y:35,
		  x2:55,
		  y2:35
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ '注册次数', '激活次数', '登录次数', '登出次数' ],
			textStyle: {
				color: '#333',
				fontSize:14
			}
		},
		toolbox : {
			show : false,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar', 'stack', 'tiled' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		
		xAxis : arrData.xAxis,
		/*xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '2016/08/01', '2016/08/02', '2016/08/03', '2016/08/04',
					'2016/08/05', '2016/08/06', '2016/08/07', '2016/08/08',
					'2016/08/09', '2016/08/10' ]
		} ],
		*/
		
		yAxis : [ {
			type : 'value'
		} ],
		
		series : arrData.series
		/*series : [ {
			name : '注册次数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 800, 850, 1000, 1100, 1050, 900, 700, 850, 800, 600 ]
		}, {
			name : '激活次数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 500, 550, 600, 800, 700, 750, 600, 500, 450, 400 ]
		}, {
			name : '登录次数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 50, 80, 100, 90, 60, 40, 75, 85, 95, 60 ]
		}, {
			name : '登出次数',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 10, 50, 40, 30, 20, 10, 50, 40, 30, 10 ]
		}

		]*/
	};
	myChart.setOption(options); // 先把可选项注入myChart中
	myChart.hideLoading();
	//装载全局变量
    myChartOption = myChart.getOption();
}

$(document).ready(function() {
//	bd.radioNotCheck();//单选框可不选
	bd.radioCss();//单选框样式重置
	$.ajaxSettings.async = false; //同步
	var data1 = "";
    var myid = '10086';
    var url = '/horiBigData/app/appConditionController/showAppCondition';
     $.getJSON(url,{
				myid:myid
			},function(msg){
				 if(msg!=null){
					 data1 = msg;
	        	 }else{
	        		 alert("this is error");
	        	 }
		});
    var data = data1.viewMap;
    arrData = data1;
	
	$('#appCondition').datagrid('loadData', data);
	// 路径配置
	require.config({
		packages : [ {
			name : 'echarts',
			location : '../echarts',
			main : 'echarts'
		} ]
	});

	require([ 'echarts', 'echarts/chart/line' ], DrawEChart // 异步加载的回调函数绘制图表
	);

});


function changeRadio(changeValue){
	var vals = $("#"+changeValue+"").val();
	$("#radiaSelectedValue").val(vals);
}

/**
 * 点击查找
 */
function find() {
	echartsData = "";
	// 获取地域的值
	var districtrevVlues = bd.getIds('.arealist .delPic');
	//console.log("districtrevVlues:"+districtrevVlues);
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

/**
 * 获取封装成列表的数据
 */
 function  getDatagridData(districtrevVlues,areaValues,dateValues,selectTime){
	    $.ajaxSettings.async = false; //同步
	    var data = "";
        var url = '/horiBigData/app/appConditionController/searchAppCondition';
        $.getJSON(url,{
        	  selectVal:dateValues,
        	  selectTime:selectTime,
        	  selectCity:districtrevVlues,
        	  selectArea:areaValues
		},function(msg){
			 if(msg.series!=null){
				 data = msg;
	       	 }else if(dateValues=="selectWeek"){
	       		 $.messager.alert("警告框","尚未有类型为'周'的APP状况信息,系统将自动切换成'日'类型的APP状况信息","warning",function(){
						//重新设回日
	       			    $("#dayId").replaceWith("<input id = 'dayId' type='radio' name='lang' value='selectDay' checked />");
	       		 });
	       	 }else{
	       		 $.messager.alert("警告框","尚未有类型为'月'的APP状况信息,系统将自动切换成'日'类型的APP状况信息","warning",function(){
	 					//重新设回日
	       			     $("#dayId").replaceWith("<input id = 'dayId' type='radio' name='lang' value='selectDay' checked />");
	        		 });
	       	 }
		});
        //复制数据
        echartsData = data;
        
       //转载datagrid
        $('#appCondition').datagrid('loadData', data.viewMap);
        
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
     location.href="/horiBigData/app/appConditionController/export?selectVal="+radioValues+"&selectTime="+selectTime+"&selectCity="+districtrevVlues+"&selectArea="+areaValues;
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
 
 //图表自适应
window.onresize = function(){
	myChart.resize();
}