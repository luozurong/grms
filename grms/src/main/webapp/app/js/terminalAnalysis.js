 var echartsData;
 var series_index=0;
$(document).ready(function(){  
//	bd.radioNotCheck();//单选框可不选
	bd.radioCss();//单选框样式重置
	
	$('#tt').tabs('resize');
	
	getDatagridData('', '', 0, '');

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
          	'echarts/chart/bar'  
        ], drawEcharts //异步加载的回调函数绘制图表  
    );  

      $('#tt').tabs({
			border:false,
			onSelect:function(title,index){
				//console.log(index+"------------------");
				getDatagridData('', '', index, '');
				series_index=index;
				selDrawEcharts(index);
				
			  	// 获取地域的值
		    	var districtrevVlues = getSpanIds('#area_seat span');

		    	// 获取小区的值
		    	var areaValues = getSpanIds('#village_seat span');
		    	var options = myChart.getOption();
		    	$.ajaxSettings.async = false; //同步
		    	var radioValues=$("input:radio:checked").val();
		    	
		    	var time1=$('.datebox-1').val();
		    	var time2=$('.datebox-2').val();
		    	// 获取时间的值
				var selectTime=$('#dateFrame .textbox-value').val();
		    	// alert('时间：：'+selectTime)
		    	getDatagridData(districtrevVlues,areaValues,index, selectTime);

		    	getChartData();
			}
		});

});

var eCharts;
var myChart;
//定义图表options  
var options =  [{
//		    title : {
			//text: '访问深度',
			//subtext: '人'
//		    },
	grid:{
	  x:55,
	  y:35,
	  x2:55,
	  y2:35
	},
	tooltip : {
		trigger: 'axis'
	},
	legend: {
		data:['累计启动次数(次)'],
		textStyle: {
			color: '#333',
			fontSize:14
		}
	},
	toolbox: {
		show : false,
		feature : {
			mark : {show: true},
			dataView : {show: true, readOnly: false},
			magicType : {show: true, type: ['line', 'bar']},
			restore : {show: true},
			saveAsImage : {show: true}
		}
	},
	calculable : true,
	xAxis : [
	{
		type : 'category',
		data : ['苹果','三星','华为','小米','OPPO','vivo','其他']
	}
	],
	yAxis : [
	{
		type : 'value'
	}
	],
	series : [
	{
		name:'累计启动次数(次)',
		type:'bar',
		barMaxWidth:50,//最大宽度
		data:[0, 0, 0, 1, 1, 1, 1],
		markPoint : {
			data : [
			{type : 'max', name: '最大值'},
			{type : 'min', name: '最小值'}
			]
		},
		markLine : {
			data : [
			{type : 'average', name: '平均值'}
			]
		}
	}
	]
	},{
//		    title : {
			//text: '访问深度',
			//subtext: '人'
//		    },
		    grid:{
	            x:55,
	            y:35,
	            x2:55,
	            y2:35
	        },
		    tooltip : {
			trigger: 'axis'
		    },
		    legend: {
					data:['累计启动次数(次)'],
	        textStyle: {
	            color: '#333',
	            fontSize:14
	        }
		    },
		    toolbox: {
			show : false,
			feature : {
			    mark : {show: true},
			    dataView : {show: true, readOnly: false},
			    magicType : {show: true, type: ['line', 'bar']},
			    restore : {show: true},
			    saveAsImage : {show: true}
			}
		    },
		    calculable : true,
		    xAxis : [
			{
			    type : 'category',
			    data : ['iPhone6s plus','iPhone6s','iPhone6','华为荣耀８','小米5S','oppoR9 plus','vivo x7']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'累计启动次数(次)',
			    type:'bar',
			    barMaxWidth:50,//最大宽度
			    data:[0, 0, 1, 1, 1, 1, 1],
			    markPoint : {
				data : [
				    {type : 'max', name: '最大值'},
				    {type : 'min', name: '最小值'}
				]
			    },
			    markLine : {
				data : [
				    {type : 'average', name: '平均值'}
				]
			    }
			}
		    ]
		},{
		    title : {
			//text: '访问深度',
			//subtext: '人'
		    },
		    grid:{
	            x:55,
	            y:35,
	            x2:55,
	            y2:35
	        },
		    tooltip : {
			trigger: 'axis'
		    },
		    legend: {
			data:['累计启动次数(次)'],
			        textStyle: {
			            color: '#333',
			            fontSize:14
			        }
		    },
		    toolbox: {
			show : false,
			feature : {
			    mark : {show: true},
			    dataView : {show: true, readOnly: false},
			    magicType : {show: true, type: ['line', 'bar']},
			    restore : {show: true},
			    saveAsImage : {show: true}
			}
		    },
		    calculable : true,
		    xAxis : [
			{
			    type : 'category',
			    data : ['ios10','ios9.3','ios8.3','ios7.0','android4.4.3','android4.3.1','android4.2.2']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'累计启动次数(次)',
			    type:'bar',
			    barMaxWidth:50,//最大宽度
			    data:[2, 2, 2, 1, 1, 2, 2],
			    markPoint : {
				data : [
				    {type : 'max', name: '最大值'},
				    {type : 'min', name: '最小值'}
				]
			    },
			    markLine : {
				data : [
				    {type : 'average', name: '平均值'}
				]
			    }
			}
		    ]
		},{
		    title : {
			//text: '访问深度',
			//subtext: '人'
		    },
		    grid:{
	            x:55,
	            y:35,
	            x2:55,
	            y2:35
	        },
		    tooltip : {
			trigger: 'axis'
		    },
		    legend: {
			data:['累计启动次数(次)'],
			        textStyle: {
			            color: '#333',
			            fontSize:14
			        }
		    },
		    toolbox: {
			show : false,
			feature : {
			    mark : {show: true},
			    dataView : {show: true, readOnly: false},
			    magicType : {show: true, type: ['line', 'bar']},
			    restore : {show: true},
			    saveAsImage : {show: true}
			}
		    },
		    calculable : true,
		    xAxis : [
			{
			    type : 'category',
			    data : ['640*1136','DVGA(640*960)','HVGA(320*480)','768*1024','1536*2048','552*900']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'累计启动次数(次)',
			    type:'bar',
			    barMaxWidth:50,//最大宽度
			    data:[2, 3, 4, 5, 6, 7],
			    markPoint : {
				data : [
				    {type : 'max', name: '最大值'},
				    {type : 'min', name: '最小值'}
				]
			    },
			    markLine : {
				data : [
				    {type : 'average', name: '平均值'}
				]
			    }
			}
		    ]
		},{
		    title : {
			//text: '访问深度',
			//subtext: '人'
		    },
		    grid:{
	            x:55,
	            y:35,
	            x2:55,
	            y2:35
	        },
		    tooltip : {
			trigger: 'axis'
		    },
		    legend: {
			data:['累计启动次数(次)'],
			        textStyle: {
			            color: '#333',
			            fontSize:14
			        }
		    },
		    toolbox: {
			show : false,
			feature : {
			    mark : {show: true},
			    dataView : {show: true, readOnly: false},
			    magicType : {show: true, type: ['line', 'bar']},
			    restore : {show: true},
			    saveAsImage : {show: true}
			}
		    },
		    calculable : true,
		    xAxis : [
			{
			    type : 'category',
			    data : ['中国移动','中国联通','中国电信']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'累计启动次数(次)',
			    type:'bar',
			    barMaxWidth:50,//最大宽度
			    data:[1, 2, 3],
			    markPoint : {
				data : [
				    {type : 'max', name: '最大值'},
				    {type : 'min', name: '最小值'}
				]
			    },
			    markLine : {
				data : [
				    {type : 'average', name: '平均值'}
				]
			    }
			}
		    ]
		},{
		    title : {
			//text: '访问深度',
			//subtext: '人'
		    },
		    grid:{
	            x:55,
	            y:35,
	            x2:55,
	            y2:35
	        },
		    tooltip : {
			trigger: 'axis'
		    },
		    legend: {
			data:['累计启动次数(次)'],
			        textStyle: {
			            color: '#333',
			            fontSize:14
			        }
		    },
		    toolbox: {
			show : false,
			feature : {
			    mark : {show: true},
			    dataView : {show: true, readOnly: false},
			    magicType : {show: true, type: ['line', 'bar']},
			    restore : {show: true},
			    saveAsImage : {show: true}
			}
		    },
		    calculable : true,
		    xAxis : [
			{
			    type : 'category',
			    data : ['WIFI','4G/3G']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'累计启动次数(次)',
			    type:'bar',
			    barMaxWidth:50,//最大宽度
			    data:[1, 2, 3],
			    markPoint : {
				data : [
				    {type : 'max', name: '最大值'},
				    {type : 'min', name: '最小值'}
				]
			    },
			    markLine : {
				data : [
				    {type : 'average', name: '平均值'}
				]
			    }
			}
		    ]
		}];

		function selDrawEcharts(num){
            myChart = eCharts.init(document.getElementById('main'+num));  
            myChart.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            myChart.setOption(options[num],true); //先把可选项注入myChart中  
            myChart.hideLoading(); 
		}
        function drawEcharts(ec){
        	DrawEChart(ec);
        }
        //创建ECharts图表方法  closed="true"
        function DrawEChart(ec) {  
            eCharts = ec;  
            myChart = eCharts.init(document.getElementById('main0'));  
            myChart.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            
                    
            myChart.setOption(options[0],true); //先把可选项注入myChart中  
            myChart.hideLoading();  
            getChartData();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        


/**
 * 封装echarts数据
 */
function getChartData() {
	if(echartsData.success==true){
		var options = myChart.getOption();
		options.xAxis[0].data=echartsData.xAxisData;
		options.series[0].data = echartsData.seriesData;
		myChart.setOption(options, true);
		myChart.hideLoading();
	}else{
		var options = myChart.getOption();
		options.xAxis[0].data=[""];
		options.series[0].data = [""];
		myChart.setOption(options, true);
		myChart.hideLoading();
	}

}

function getDatagridData(districtrevVlues, areaValues, selectTab, selectTime) {
	//console.log("----------------------------"+selectTab);
	series_index=selectTab;
	$.ajaxSettings.async = false; // 同步
	var data = "";
	var url = '/horiBigData/terminalAnalysisController/getData';
	$.getJSON(url, {
		selectTime : selectTime,
		selectTab:selectTab,
		cityId:districtrevVlues,
		organizationSeq:areaValues
		}, function(msg) {

			data = msg;
			$('#datagrid'+selectTab).datagrid('loadData', data.totalTerminalAnalysis);

			echartsData = data;

	});
}


	//查询
	function doselect(){
		// 获取地域的值
		var districtrevVlues = bd.getIds('.arealist .delPic');
		// 获取小区的值
		var areaValues =bd.getIds('.communitylist .delPic');
    	var options = myChart.getOption();
    	$.ajaxSettings.async = false; //同步
    	var radioValues=$("input:radio:checked").val();
    	
    	var time1=$('.datebox-1').val();
    	var time2=$('.datebox-2').val();
    	// 获取时间的值
		var selectTime=$('#dateFrame .textbox-value').val();
    	// alert('时间：：'+selectTime)
    	getDatagridData(districtrevVlues,areaValues,series_index, selectTime);

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
		location.href = "/horiBigData/terminalAnalysisController/export?selectTab="+series_index+"&selectTime="+selectTime+"&cityId="+districtrevVlues+"&organizationSeq" +
				"="+areaValues;
	}
	
//图标自适应
window.onresize = function(){
	$('#tt').tabs('resize');
	myChart.resize();
}