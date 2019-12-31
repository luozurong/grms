var myChart;
var eCharts;

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
			text : '累计新用户',
			subtext : '（人）'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : [ 'v2.6.01', 'v2.5.02', 'v2.4.01' ]
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
		xAxis : [ {
			type : 'category',
			boundaryGap : false,
			data : [ '2016/08/01', '2016/08/02', '2016/08/03', '2016/08/04',
					'2016/08/05', '2016/08/06', '2016/08/07', '2016/08/08',
					'2016/08/09', '2016/08/10' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : 'v2.6.01',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 400, 390, 380, 410, 350, 300, 280, 350, 320, 350 ]
		}, {
			name : 'v2.5.02',
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
			name : 'v2.4.01',
			type : 'line',
			smooth : true,
			itemStyle : {
				normal : {
					areaStyle : {
						type : 'default'
					}
				}
			},
			data : [ 350, 380, 400, 390, 360, 340, 375, 385, 395, 360 ]
		} ]
	};
	myChart.setOption(options); // 先把可选项注入myChart中
	myChart.hideLoading();
	getChartData();// aja后台交互 9/07/2016 - 09/14/2016
}

$(document).ready(function() {
//	bd.radioNotCheck();//单选框可不选
	bd.radioCss();//单选框样式重置
	getDatagridData('', '', '', '');
	// var data = $.parseJSON(jsonstr);
	// $('.easyui-datagrid').datagrid('loadData', data);
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

function getDatagridData(districtrevVlues, areaValues, radioValues, selectTime) {
	console.log("----------------------------" + radioValues);
	$.ajaxSettings.async = false; // 同步
	var data = "";
	var url = '/horiBigData/app/versionAnalysisController/getData';
	$.getJSON(url, {
		selectTime : selectTime,
		selectType : radioValues,
		cityId : districtrevVlues,
		organizationSeq : areaValues
	}, function(msg) {

			data = msg;
			console.log(data)
			$('#easyui-datagrid').datagrid('loadData',
					data.totalVersionAnalysis);

			echartsData = data;
	
	});

}
	
	/**
	 * 封装echarts数据
	 */
	function getChartData() {
		
		console.log(echartsData.legendDate.length+":::echartsData.legendDate.length")
		if(echartsData.legendDate.length>0){
			var options = myChart.getOption();
			//console.log(options.series+"''''''''''''''''options.series'")
			options.series = echartsData.dataList;
			options.xAxis[0].data = echartsData.sevenList;
			options.legend.data = echartsData.legendDate;
			myChart.setOption(options, true);
			myChart.hideLoading();
		}else{
			var options = myChart.getOption();
	
			options.series =[{name : '-',type : 'line',smooth : true,
				itemStyle : {normal : {areaStyle : {
				type : 'default'}}},
				data : ['']}];
			options.xAxis[0].data = echartsData.sevenList;
			options.legend.data = '';
			myChart.setOption(options, true);
		}
		
		
		
	}
	
	function dosearch() {
		// 获取地域的值
		var districtrevVlues = bd.getIds('.arealist .delPic');
		// 获取小区的值
		var areaValues =bd.getIds('.communitylist .delPic');
		var options = myChart.getOption();
		$.ajaxSettings.async = false; // 同步
		var radioValues = $("input:radio:checked").val();
	
		// 获取时间的值
		var selectTime=$('#dateFrame .textbox-value').val();
		// alert("查询炒作");
		console.log(radioValues + "---radioValues" + selectTime);
		getDatagridData(districtrevVlues,areaValues,radioValues,selectTime);
		getChartData();
	}

	
	function doExportPoi(){
		

		
		var options = myChart.getOption();
		$.ajaxSettings.async = false; // 同步
		// 获取地域的值
		var districtrevVlues = bd.getIds('.arealist .delPic');
		// 获取小区的值
		var areaValues =bd.getIds('.communitylist .delPic');
		var options = myChart.getOption();
		$.ajaxSettings.async = false; //同步
		var radioValues=$("input:radio:checked").val();
	
		// 获取时间的值
		var selectTime = $('.textbox-value').val()
		location.href = "/horiBigData/app/versionAnalysisController/export?seleteType="+radioValues+"&selectTime="+selectTime+"&cityId="+districtrevVlues+"&organizationSeq" +
				"="+areaValues;
	
		
	}
	