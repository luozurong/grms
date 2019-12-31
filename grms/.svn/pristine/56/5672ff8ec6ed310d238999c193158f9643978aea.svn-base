window.onresize = function(){
	myChart1.resize();
	myChart2.resize();
	myChart3.resize();
	myChart4.resize();
	myChart5.resize();
	myChart6.resize();
}
$(document).ready(function(){
       /* var data = $.parseJSON(construction);    
        $('#construction').datagrid('loadData', data);*/
		/*var data = $.parseJSON(region);    
        $('#region').datagrid('loadData', data);*/
	    getRegionDatagridData();
	    
		/*var data = $.parseJSON(activeUser);    
        $('#activeUser').datagrid('loadData', data);*/
        
	    getActiveUserDatagridData('','','','');
	    
	    //开门分析触发函数-----By laikunxin
	    getTalkOpenAnalysisDatagridData();
		//var data = $.parseJSON(openDoor);    
       //$('#openDoor').datagrid('loadData', data);
		/*var data = $.parseJSON(achievement);    
        $('#achievement').datagrid('loadData', data);
		var data = $.parseJSON(achievement1);    
        $('#achievement1').datagrid('loadData', data);
		$('#achievement2').datagrid('loadData', data);
		$('#achievement3').datagrid('loadData', data);
		$('#achievement4').datagrid('loadData', data);
		$('#achievement5').datagrid('loadData', data);*/
        
        getAchievementDatagridData('', '', '','selectDay');
        
        
		/*var data = $.parseJSON(orderIndicator);    
        $('#orderIndicator').datagrid('loadData', data);*/
        getUserOrderDatagridData('', '', '', '');
        
        getWarningDatagridData('', '', '', '');
		/*var data = $.parseJSON(warning);    
        $('#warning').datagrid('loadData', data);*/
        // 路径配置
        require.config({
            packages: [{
                name: 'echarts',
                location: 'echarts',
                main: 'echarts'
            }]
        });
  
        require(  
            [ 'echarts',   
              'echarts/chart/line',
              'echarts/chart/bar',
              'echarts/chart/map'  
            ], drawECharts //异步加载的回调函数绘制图表  
        );  
 });
        
        function drawECharts(ec) {
           //DrawEChart(ec);
           DrawEChart1(ec);
           DrawEChart2(ec);
           DrawEChart3(ec);
	       DrawEChart4(ec);
           DrawEChart5(ec);
           DrawEChart6(ec);
        }
        //创建ECharts图表方法  closed="true"
      /*  function DrawEChart(ec) {  
            var eCharts = ec;  
            var myChart = eCharts.init(document.getElementById('main'));  
            myChart.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
           var options =  {
		    title : {
			//text: '访问深度',
			//subtext: '人'
		    },
		    tooltip : {
			trigger: 'axis'
		    },
		    legend: {
			data:['待签','已签','设计中','施工中','已完工']
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
			    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'待签',
			    type:'bar',
			    data:[162, 135, 77, 25, 20, 7, 5, 2]			  
			},
                        {
			    name:'已签',
			    type:'bar',
			    data:[262, 15, 7, 5, 3, 0, 0, 0]			  
			},
                        {
			    name:'设计中',
			    type:'bar',
			    data:[50, 60, 20, 10, 5, 0, 0, 0]			  
			},
                        {
			    name:'施工中',
			    type:'bar',
			    data:[800, 600, 300, 20, 5, 0, 0, 0]			  
			},
                        {
			    name:'已完工',
			    type:'bar',
			    data:[1000, 800, 500, 20, 10, 0, 0, 0]			  
			}
		    ]
		};
                    
            myChart.setOption(options,true); //先把可选项注入myChart中  
            myChart.hideLoading();  
          //  getChartData();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        */
    //##################地域分布 start##########################################
        var myChart1 ;
//创建ECharts图表方法  closed="true"
        function DrawEChart1(ec) {  
            var eCharts1 = ec;  
            myChart1 = eCharts1.init(document.getElementById('main1'));  
            myChart1.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
        	var options1 ={
			    title : {
					text: '用户地域分布',
					subtext: '(人)',
					x:'center'
			    },
			    tooltip : {
					trigger: 'item'
			    },
			    legend: {
					orient: 'vertical',
					x:'left',
					data:['用户']
			    },
			    dataRange: {
					min: 0,
					max: 80000,
					x: 'left',
					y: 'bottom',
					text:['高','低'],           // 文本，默认为数值文本
					calculable : true
			    },
			    toolbox: {
					show: false,
					orient : 'vertical',
					x: 'right',
					y: 'center',
					feature : {
					    mark : {show: true},
					    dataView : {show: true, readOnly: false},
					    restore : {show: true},
					    saveAsImage : {show: true}
					}
			    },
			    roamController: {
					show: true,
					x: 'right',
					/*mapTypeControl: {
					    '广东': true
					}*/
					mapTypeControl: {
					    'china': true
					}
			    },
		    	series : [{
				    name: '用户',
				    type: 'map',
				    mapType: 'china',
				    roam: false,
				    itemStyle:{
						normal:{label:{show:true}},
						emphasis:{label:{show:true}}
				    },
				    data:[
						/*{name: '广州市',value: 80000},
						{name: '佛山市',value: 40000}*/
						{name: '北京',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '天津',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '上海',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '重庆',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '河北',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '河南',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '云南',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '辽宁',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '黑龙江',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '湖南',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '安徽',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '山东',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '新疆',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '江苏',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '浙江',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '江西',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '湖北',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '广西',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '甘肃',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '山西',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '内蒙古',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '陕西',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '吉林',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '福建',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '贵州',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '广东',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '青海',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '西藏',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '四川',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '宁夏',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '海南',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '台湾',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '香港',value: Math.round(Math.random()*1000),code:'123'},
		                {name: '澳门',value: Math.round(Math.random()*1000),code:'123'}
			    	]
				}]
			};
            myChart1.setOption(options1,true); //先把可选项注入myChart中  
            myChart1.hideLoading();  
            getChart1Data();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        var echarts1Data ;
        function getRegionDatagridData(){
        	$.ajaxSettings.async = false; //同步
        	var data = "";
        		var url = '/horiBigData/admin/regionController/showRegion';
        		/*$.getJSON(url,{
        			q : new Date().getTime()
        		},function(msg){
        			 if(msg!=null){
        				 data = msg;
        			 }else{
        				 alert("this is error");
        			 }
        		});*/
        		var params =  {
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
        		
        		 echarts1Data= data.provinceData;
                 $('#region').datagrid('loadData', data.ctiyData);  	
                 
        		 
        }


        function getAreaData(code){
        	$.ajaxSettings.async = false; //同步
        	var data = "";
        		var url = '/horiBigData/admin/regionController/showAreaDatagrid';
        		var params =  {
        				code:code,
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
        		
                 $('#region').datagrid('loadData', data.ctiyData);  	
        }
        /**
         * 封装echarts数据
         */
        function getChart1Data() {
        	var options = myChart1.getOption();
        	options.series[0].data = echarts1Data;
        	myChart1.setOption(options, true);
        	myChart1.on('click', function (params) {
        	    var city = params.name;
        	    var code = params.data.code;
        	    if(code==undefined){
        	    	$.messager.alert('Warning','非内地没有相应的数据，请点击其他省');
        	    	return ;
        	    }
        	    getAreaData(code);
        	});
        	myChart1.hideLoading();
        }
        //##################地域分布 end##########################################
        
       
       
        //###########################开门分析 start###############################
        var arrData2 = "";//全局变量
        function getTalkOpenAnalysisDatagridData(){
        	//alert("fff");
        	$.ajaxSettings.async = false; //同步
			   var data2 = "";
		       var myid = '10086';
		       var url = '/horiBigData/admin/talkOpenAnalysisController/showTalkOpenAnalysis';
		        $.getJSON(url,{
						myid:myid
					},function(msg){
						 if(msg!=null){
							 data2 = msg;
			        	 }else{
			        		 alert("this is error");
			        	 }
				});
		       var openDoordata = data2.viewMap;
		       arrData2 = data2;
		       //装载表格数据
		       $('#openDoor').datagrid('loadData', openDoordata);
        	
        }
        //创建ECharts图表方法  closed="true"
        var myChart2;
        function DrawEChart2(ec) {  
            var eCharts2 = ec;  
            myChart2 = eCharts2.init(document.getElementById('main2'));  
            myChart2.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
			var options2 = {
//      		title : {
//					text: '开门次数',	
//			               subtext: '（次）'	
//				    }, 
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
					data:['刷卡开门次数','IOS开门次数','Android开门次数','对讲开门次数','监控开门次数'],
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
					    magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					    restore : {show: true},
					    saveAsImage : {show: true}
					}
			    },
			    calculable : true,
			    xAxis : arrData2.xAxis,
			    /*xAxis : [
				{
				    type : 'category',
				    boundaryGap : false,
				    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08','2016/08/09','2016/08/10']
				}
			    ],*/
			    
			    yAxis : [
				{
				    type : 'value'
				}
			    ],
			    series : arrData2.series
			    /*series : [
				{
				    name:'刷卡开门次数',
				    type:'line',
				    smooth:true,
				    itemStyle: {normal: {areaStyle: {type: 'default'}}},
				    data:[800, 850, 1000, 1100, 1050, 900, 700,850,800,600]
				},
		                {
				    name:'IOS开门次数',
				    type:'line',
				    smooth:true,
				    itemStyle: {normal: {areaStyle: {type: 'default'}}},
				    data:[500, 550, 600, 800, 700, 750, 600,500,450,400]
				},
		                {
				    name:'Android开门次数',
				    type:'line',
				    smooth:true,
				    itemStyle: {normal: {areaStyle: {type: 'default'}}},
				    data:[50, 80, 100, 90, 60, 40, 75,85,95,60]
				},
		                {
				    name:'对讲开门次数',
				    type:'line',
				    smooth:true,
				    itemStyle: {normal: {areaStyle: {type: 'default'}}},
				    data:[10, 50, 40, 30, 20, 10, 50,40,30,10]
				},
		                {
				    name:'监控开门次数',
				    type:'line',
				    smooth:true,
				    itemStyle: {normal: {areaStyle: {type: 'default'}}},
				    data:[20, 30, 50, 40, 10, 40, 20,30,30,30]
				}
			    ]*/
		        };
		            myChart2.setOption(options2,true); //先把可选项注入myChart中  
		            myChart2.hideLoading();  
		           // getChart2Data();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        //########################开门分析 end########################################         
        
        /*##################成交金额 start##########################################*/
//创建ECharts图表方法  closed="true"
        var myChart3;
        function DrawEChart3(ec) {  
            var eCharts3 = ec;  
             myChart3 = eCharts3.init(document.getElementById('main3'));  
            myChart3.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
           var options3 = {
//          	title : {
//	       			text: '成交总额',	
//          		subtext: '（元）'	
//	    		},
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
			data:['商品超市','服务到家'],
			textStyle: {
    			color: '#222222', // 图例文字颜色
    			fontSize:14
			}
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
		    name:'商品超市',
		    type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[800, 850, 1000, 1100, 1050, 900, 700,850,800,600]
		},
                {
		    name:'服务到家',
		    type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[500, 550, 600, 800, 700, 750, 600,500,450,400]
		}
	    ]
           }; 
            myChart3.setOption(options3,true); //先把可选项注入myChart中  
            myChart3.hideLoading();  
            getChart3Data();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        
 var echarts3Data;     
        
        /**
         * 封装echarts数据
         */
        function getChart3Data() {
        	var options = myChart3.getOption();
        	options.series[0].data = echarts3Data.optionData.series[0].data;
        	options.series[1].data = echarts3Data.optionData.series[1].data;
        	/*options.series[2].data = echarts3Data.optionData.series[2].data;
        	options.series[3].data = echarts3Data.optionData.series[3].data;
        	options.series[4].data = echarts3Data.optionData.series[4].data;*/
        	options.xAxis[0].data = echarts3Data.optionData.xAxis[0].data;
        	myChart3.setOption(options, true);
        	myChart3.hideLoading();
        }

        function getAchievementDatagridData(districtrevVlues, areaValues, selectTime,seleteType){
        	$.ajaxSettings.async = false; //同步
        	var data = "";
        		var url = '/horiBigData/admin/achievementController/showAchievement';
        		var params =  {
        				cityId : districtrevVlues,
        				organizationSeq : areaValues,
        				selectTime : selectTime,
        				seleteType : seleteType,
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
        		 $('#achievement').datagrid('loadData', data.turnoverList);
        		 $('#achievement1').datagrid('loadData', data.spcsList);
        		 $('#achievement2').datagrid('loadData', data.fwdjList); 
                 	
        		 echarts3Data = data;
        		 
        }
        /**
         * 平均单价
         * @param value
         * @param rowData
         * @param rowIndex
         * @returns
         */
        function priceData(value, rowData, rowIndex){
        	var forderPercent = "0";
        	if (rowData["totalCounts"]!=0) {
        		forderPercent = decimal(rowData["totalTurnover"]
        		/ rowData["totalCounts"], 2);
        	}
           return forderPercent;
        }
        /**
         * 平均利润
         * @param value
         * @param rowData
         * @param rowIndex
         * @returns
         */
        function profitData(value, rowData, rowIndex){
        	var forderPercent = "0";
        	if (rowData["totalCounts"]!=0) {
        		forderPercent = decimal(rowData["totalProfit"]
        		/ rowData["totalCounts"], 2);
        	}
           return forderPercent;
        }
        
   /*##################成交金额 end##########################################*/
        
        /*##################订单总数 start##########################################*/ 
        var myChart4;
        //创建ECharts图表方法  closed="true"
        function DrawEChart4(ec) {  
            var eCharts4 = ec;  
             myChart4 = eCharts4.init(document.getElementById('main4'));  
            myChart4.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
           var options4 = {
//            title : {
//		text: '订单数',	
//              subtext: '（个）'	
//	    },
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
			data:['总订单数'],
			textStyle: {
				color: '#222222', // 图例文字颜色
				fontSize:14
			}
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
		    name:'总订单数',
		    type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[800, 850, 1000, 1100, 1050, 900, 700,850,800,600]
		}
	    ]
           }; 
            myChart4.setOption(options4,true); //先把可选项注入myChart中  
            myChart4.hideLoading();  
            getChart4Data();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        var echarts4Data;
        function getUserOrderDatagridData(districtrevVlues, areaValues, dateValues, selectTime) {
        	$.ajaxSettings.async = false; // 同步
        	var data = "";
        	var url = '/horiBigData/admin/userOrderIndicatorController/showUserOrderIndicator';
        	var params =  {
        			cityId : districtrevVlues,
        			organizationSeq : areaValues,
        			seleteType : dateValues,
        			selectTime : selectTime,
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

        	$('#orderIndicator').datagrid('loadData', data.totalOrderList);

        	echarts4Data = data;
        	// getChartData();
        }
        
        
        
        var echarts5Data;
    	function getWarningDatagridData(districtrevVlues, areaValues, dateValues, selectTime) {
    		$.ajaxSettings.async = false; // 同步
    		var data5 = "";
    		var url = '/horiBigData/waController/getData';
    		$.getJSON(url, {
    			cityId : districtrevVlues,
    			organizationSeq : areaValues,
    			seleteType:dateValues,
    			selectTime : selectTime
    			}, function(msg) {
    				//console.log(msg);

    			if (msg != null) {
    				data5 = msg;
    			} else {
    				alert("this is error");
    			}
    		});

    		$('#warning').datagrid('loadData', data5.totalWarningtList);

    		echarts5Data = data5;
    				
    	}
        
        

        /**
         * 百分比的转化率
         * 
         * @param value
         * @param rowData
         * @param rowIndex
         * @returns {String}
         */
        function changePercentData(value, rowData, rowIndex) {
        	var orderPercent;
        	if (rowData["completeOrders"]) {
        			forderPercent = decimal(rowData["completeOrders"]
        			/ rowData["totalOrders"], 2)
        			+ '%';
        			if(rowData["totalOrders"]==0){
        				forderPercent= "0%";
        			}
        	}
        	return forderPercent;
        }


        /**
         * 封装echarts数据
         */
        function getChart4Data() {
        	var options = myChart4.getOption();
        	options.series[0].data = echarts4Data.optionData.series[0].data;
        	options.xAxis[0].data = echarts4Data.optionData.xAxis[0].data;
        	myChart4.setOption(options, true);
        	myChart4.hideLoading();
        }

        
        /*##################订单总数 end##########################################*/ 

        
        function getChart5Data() {
            
        	var options = myChart5.getOption();
    		options.series=echarts5Data.series;
    		options.xAxis[0].data = echarts5Data.optionData.xAxis[0].data;
    		myChart5.setOption(options, true);
    		myChart5.hideLoading();
        }
      
        var myChart5;
	//创建ECharts图表方法  closed="true"
        function DrawEChart5(ec) {  
            var eCharts5 = ec;  
            myChart5 = eCharts5.init(document.getElementById('main5'));  
            myChart5.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
           var options5 =  {
//		    title : {
//			text: '报障情况',
//			subtext: '(次)'
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
			data:['平台类故障（自动）','平台类故障（人工）','APP类故障（人工）','门禁设备类故障（自动）','门禁设备类故障（人工）'],
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
			    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08']
			}
		    ],
		    yAxis : [
			{
			    type : 'value'
			}
		    ],
		    series : [
			{
			    name:'平台类故障（自动）',
			    type:'bar',
			    data:[0, 0, 0, 0, 0, 0, 0, 0]			  
			},
                        {
			    name:'平台类故障（人工）',
			    type:'bar',
			    data:[0, 1, 0, 0, 0, 0, 0, 0]			  
			},
                        {
			    name:'APP类故障（人工）',
			    type:'bar',
			    data:[5, 3, 2, 0, 0, 0, 0, 0]			  
			},
                        {
			    name:'门禁设备类故障（自动）',
			    type:'bar',
			    data:[50, 8, 0, 20, 5, 0, 0, 0]			  
			},
                        {
			    name:'门禁设备类故障（人工）',
			    type:'bar',
			    data:[3, 0, 0, 0, 0, 0, 0, 0]			  
			}
		    ]
		};
                    
            myChart5.setOption(options5,true); //先把可选项注入myChart中  
            myChart5.hideLoading();  
            getChart5Data();//aja后台交互   9/07/2016 - 09/14/2016
        } 

        /*##################日活跃度 start##########################################*/
        var myChart6;
        //创建ECharts图表方法  closed="true"
        function DrawEChart6(ec) {  
        	var eCharts6 = ec;  
        	myChart6 = eCharts6.init(document.getElementById('main6'));  
        	myChart6.showLoading({  
            	text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
        	var options6 = {
//          	title : {
//					text: '日活跃度',	
//             		subtext: '(人)'	
//			    },
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
					data:['日活跃度'],
					textStyle: {
            			color: '#222222', // 图例文字颜色
            			fontSize:14
        			}
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
	    		xAxis : [{
				    type : 'category',
				    boundaryGap : false,
				    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08','2016/08/09','2016/08/10']
				}],
			    yAxis : [{
				    type : 'value'
				}],
			    series : [{
				    name:'日活跃度',
				    type:'line',
				    smooth:true,
				    itemStyle: {normal: {areaStyle: {type: 'default'}}},
				    data:[0.03, 0.05, 0.15, 0.30, 0.18, 0.25, 0.20,0.17,0.09,0.19]
				}]
        	}; 
            myChart6.setOption(options6,true); //先把可选项注入myChart中  
            myChart6.hideLoading();  
            getChart6Data();//aja后台交互   9/07/2016 - 09/14/2016
        }
        
        /**
         * 封装echarts数据
         */
        function getChart6Data() {
        	var options = myChart6.getOption();
        	options.series[0].data = echarts6Data.series[0].data;
        	options.xAxis[0].data = echarts6Data.xAxis[0].data;
        	myChart6.setOption(options, true);
        	myChart6.hideLoading();
        }
        
        
        var echarts6Data ;
        
        function getActiveUserDatagridData(districtrevVlues, areaValues, selectTime,seleteType){
        	$.ajaxSettings.async = false; //同步
        	var data = "";
        		var url = '/horiBigData/admin/activeUserController/showActiveUser';
        		var params =  {
        				cityId : districtrevVlues,
        				organizationSeq : areaValues,
        				selectTime : selectTime,
        				seleteType : seleteType,
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
        	 
        		 echarts6Data= data.echartsData;
        		 ///前端处理数据
                 $('#activeUser').datagrid('loadData', data.activeUserList);  	
        		 
        }

        function activeData(value, rowData, rowIndex){
        	var forderPercent = "0%";
        	if (rowData["userTotal"]) {
        		forderPercent = decimal(rowData["activeTotal"]
        		/ rowData["userTotal"], 2)
        		+ '%';
        	}
        	return forderPercent;
        }
        function lostData(value, rowData, rowIndex){
        	var forderPercent = "0%";
        	if (rowData["userTotal"]) {
        		forderPercent = decimal(rowData["lostTotal"]
        		/ rowData["userTotal"], 2)
        		+ '%';
        	}
        	return forderPercent;
        }


   //##################日活跃度 end##########################################
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
