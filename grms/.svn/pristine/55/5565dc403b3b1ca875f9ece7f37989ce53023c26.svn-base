var myChart;  
        var eCharts;  

         
  
        //创建ECharts图表方法  closed="true"
        function DrawEChart(ec) {  
            eCharts = ec;  
            myChart = eCharts.init(document.getElementById('main'));  
            myChart.showLoading({  
                text : "图表数据正在努力加载..."  
            });  
            //定义图表options  
           var options ={
		    title : {
			text: '用户地域分布',
			subtext: '（人）',
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
			mapTypeControl: {
			    'china': true
			}
		    },
		    series : [
			{
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
			}
		    ]
		};
            myChart.setOption(options); //先把可选项注入myChart中  
            myChart.hideLoading();  
            getChartData();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        

$(document).ready(function(){ 
/*        var data = $.parseJSON(region);    
*/        getDatagridData();
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
              'echarts/chart/map'  
            ], DrawEChart //异步加载的回调函数绘制图表  
        ); 
    });

var echartsData ;
function getDatagridData(){
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
		
		 echartsData= data.provinceData;
         $('#region').datagrid('loadData', data.ctiyData);  	
         
		 
}


function getAreaData(code){
	$.ajaxSettings.async = false; //同步
	var data = "";
		var url = '/horiBigData/admin/regionController/showAreaDatagrid';
		/*$.getJSON(url,{
			code:code,
			q : new Date().getTime()
		},function(msg){
			 if(msg!=null){
				 data = msg;
			 }else{
				 alert("this is error");
			 }
		});*/
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
function getChartData() {
	var options = myChart.getOption();
	options.series[0].data = echartsData;
	myChart.setOption(options, true);
	myChart.on('click', function (params) {
	    var city = params.name;
	    var code = params.data.code;
	    if(code==undefined){
	    	$.messager.alert('Warning','非内地没有相应的数据，请点击其他省');
	    	return ;
	    }
	    getAreaData(code);
	});
	myChart.hideLoading();
}

