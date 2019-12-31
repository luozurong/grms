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
           var options = {
              title : {
		//text: '活跃用户情况',	
               // subtext: '（％）'	
	    },
	    tooltip : {
		trigger: 'axis'
	    },
	    legend: {
		data:['留存用户','新用户']
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
		    data : ['2016/08/01','2016/08/02','2016/08/03','2016/08/04','2016/08/05','2016/08/06','2016/08/07','2016/08/08','2016/08/09']
		}
	    ],
	    yAxis : [
		{
		    type : 'value'
		}
	    ],
	    series : [
		{
		    name:'留存用户',
		    type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[100, 120, 125, 150, 300, 200, 300,400,250]
		},
                {
		    name:'新用户',
		    type:'line',
		    smooth:true,
		    itemStyle: {normal: {areaStyle: {type: 'default'}}},
		    data:[300,400, 400, 300, 500,300, 400,500,400]
		}
	    ]
           }; 
            myChart.setOption(options); //先把可选项注入myChart中  
            myChart.hideLoading();  
            getChartData();//aja后台交互   9/07/2016 - 09/14/2016
        }  
        

$(document).ready(function(){   
//	bd.radioNotCheck();//单选框可不选
	bd.radioCss();//单选框样式重置
    /*  var data = $.parseJSON(jsonstr);  */
	  //$('.easyui-datagrid').datagrid('loadData', data);
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
    });
var echartsData ;
function getDatagridData(districtrevVlues, areaValues, selectTime,seleteType){
	$.ajaxSettings.async = false; //同步
	var data = "";
		var url = '/horiBigData/admin/retainedUserController/showAchievement';
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
		 var retainedList = handleData(data.retainedUserVoList);
		 console.log(retainedList);
        $('.easyui-datagrid').datagrid('loadData', retainedList);  	
       // $('.easyui-datagrid').datagrid('loadData', data.retainedUserVoList);  	
		 
}

function handleData(json){
	var arr=[];
	var newUser;
	var retainUser;
	var retainUserPercent;
	var i;
	$.each(json, function(index, obj) {
		retainUser = json[index].retainTotal;
		newUser = json[index].newTotal;
		if(json.length-1>index){
			i = index+1;
			newUser = json[i].newTotal;
		}else{
			newUser = retainUser;
		}
		retainUserPercent = retainPercentData(retainUser, newUser);
		json[index].retainPercent=retainUserPercent;
		arr.push(json[index]);
	});
	
	return arr;
}

 function retainPercentData(retainUser, newUser){
	var forderPercent = "0%";
	if (newUser) {
		forderPercent = decimal(retainUser
		/ newUser, 2)
		+ '%';
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
	// alert('时间：：'+selectTime)
	getDatagridData(districtrevVlues, areaValues, selectTime,seleteType);
	getChartData() ;
} 

/**
 * 封装echarts数据
 */
function getChartData() {
	var options = myChart.getOption();
	options.series[0].data = echartsData.series[0].data;
	options.series[1].data = echartsData.series[1].data;
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
	location.href = "/horiBigData/admin/retainedUserController/export?selectType="+seleteType+"&selectTime="+selectTime+"&cityId="+districtrevVlues+"&organizationSeq" +
			"="+areaValues;
}