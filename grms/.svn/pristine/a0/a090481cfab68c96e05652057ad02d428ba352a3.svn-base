menuItemEachWelCome("首页");
var cityAreaTownInitValue1=[
       {
           "id":0,
           "code":"",
           "name":"请选择城市",
           "parentId":"",
           "selected":true
       }
 ];
var cityAreaTownInitValue2=[
    {
        "id":0,
        "code":"",
        "name":"请选择区/县",
        "parentId":"",
        "selected":true
    }
];
var cityAreaTownInitValue3=[
    {
        "id":0,
        "code":"",
        "name":"请选择",
        "parentId":"",
        "selected":true
    }
];
/***********地区下拉框选择后刷新页面*************/
$("#province").combobox({
	onChange: function () {
		selectProvinceChange();
	}
});
$("#city").combobox({
	onChange: function () {
		selectCityChange();
	}
});
$("#country").combobox({
	onChange: function () {
		selectCountryChange();
	}
});
$("#town").combobox({
	onChange: function () {
		selectTownChange();
	}
});

$(function(){
	/******首页显示项*******/
	$.ajax({
		url : "/uoms/startpageSetAction!getAll.html",
		type : "post",
		dataType : "json",
		async : true,
		success : function(data){
			if(data.deviceAll=='0'){
				$("#deviceAll").attr("style","display:none;");
			}else if(data.deviceAll=='1'){
				$("#deviceAll").attr("style","display:block;");
			}
			
			if(data.deviceOnline=='0'){
				$("#deviceOnline").attr("style","display:none;");
			}else if(data.deviceOnline=='1'){
				$("#deviceOnline").attr("style","display:block;");
			}
			
			if(data.deviceAlarm=='0'){
				$("#deviceAlarm").attr("style","display:none;");
			}else if(data.deviceAll=='1'){
				$("#deviceAlarm").attr("style","display:block;");
			}
			
			if(data.orderWfallocation=='0'){
				$("#orderWfallocation").attr("style","display:none;");
			}else if(data.orderWfallocation=='1'){
				$("#orderWfallocation").attr("style","display:block;");
			}
			
			if(data.orderWfdeal=='0'){
				$("#orderWfdeal").attr("style","display:none;");
			}else if(data.orderWfdeal=='1'){
				$("#orderWfdeal").attr("style","display:block;");
			}
			
			if(data.orderCapply=='0'){
				$("#orderCapply").attr("style","display:none;");
			}else if(data.orderCapply=='1'){
				$("#orderCapply").attr("style","display:block;");
			}
			
			if(data.orderDelay=='0'){
				$("#orderDelay").attr("style","display:none;");
			}else if(data.orderDelay=='1'){
				$("#orderDelay").attr("style","display:block;");
			}
			
			if(data.orderTimeoutwithoutdeal=='0'){
				$("#orderTimeoutwithoutdeal").attr("style","display:none;");
			}else if(data.orderTimeoutwithoutdeal=='1'){
				$("#orderTimeoutwithoutdeal").attr("style","display:block;");
			}
			
			if(data.orderTimeoutwithoutaccomplish=='0'){
				$("#orderTimeoutwithoutaccomplish").attr("style","display:none;");
			}else if(data.orderTimeoutwithoutaccomplish=='1'){
				$("#orderTimeoutwithoutaccomplish").attr("style","display:block;");
			}
			
			if(data.orderWfsummary=='0'){
				$("#orderWfsummary").attr("style","display:none;");
			}else if(data.orderWfsummary=='1'){
				$("#orderWfsummary").attr("style","display:block;");
			}
			
			if(data.orderTimeoutwithoutallocation=='0'){
				$("#orderTimeoutwithoutallocation").attr("style","display:none;");
			}else if(data.orderTimeoutwithoutallocation=='1'){
				$("#orderTimeoutwithoutallocation").attr("style","display:block;");
			}
		},
		error : function(){
			//infoMask('数据获取失败！');
		}
	});
	
	
	/****省份的数据初始化,4级联动****/
	$('#city').combobox({
        limitToList:true,
        data:cityAreaTownInitValue1,
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:"auto"
    });
    $('#country').combobox({
        limitToList:true,
        data:cityAreaTownInitValue2,
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:"auto"
    });
    $('#town').combobox({
        limitToList:true,
        data:cityAreaTownInitValue3,
        valueField:'code',
        textField:'name',
        editable:false,
        panelHeight:"auto"
    });
    $('#province').combobox({
        url:'/uoms/communityAction!initProvince.html',
        method:'post',
        value : '请选择省/市',
        valueField:'code',
        textField:'name',
        editable:false,
        onSelect:function(newValue){
        	provinceCode = newValue.code;
        	$("#provinceCode").val(provinceCode);
        	$("#cityCode").val("");
        	$("#countryCode").val("");
            $('#city').combobox({
                url:'/uoms/communityAction!getChildSelectData.html?code='+provinceCode,
                method:'post',
                value : '请选择城市',
                valueField:'code',
                textField:'name',
                onSelect:function(newValue){
                	cityCode = newValue.code;
                	$("#cityCode").val(cityCode);
                	$("#countryCode").val("");
                	 $('#country').combobox({
                	        url:'/uoms/communityAction!getChildSelectData.html?code='+cityCode,
                	        method:'post',
                	        value : '请选择区/县',
                	        valueField:'code',
                	        textField:'name',
                	        onSelect:function(newValue){
                	        	countryCode = newValue.code;
                	        	$("#countryCode").val(countryCode);
                	        	$('#town').combobox({
                        	        url:'/uoms/communityAction!getChildSelectData.html?code='+countryCode,
                        	        method:'post',
                        	        value : '请选择',
                        	        valueField:'code',
                        	        textField:'name',
                        	        onSelect:function(newValue){
                        	        	townCode = newValue.code;
                        	        	$("#townCode").val(townCode);
										var self = $(this);
										comboboxOnSelect(self,newValue);
                        	        }
                        	    });
								var self = $(this);
								comboboxOnSelect(self,newValue);
                	        }
                	    });
					var self = $(this);
					comboboxOnSelect(self,newValue); 
                }
            });
			var self = $(this);
			comboboxOnSelect(self,newValue);
        }
    });
    
    /*************工单数量*************/
    var province = $("#provinceCode").val(); 
    var city = $("#cityCode").val(); 
    var country = $("#countryCode").val();
    var town = $("#townCode").val();
    $.ajax({
		   url : "/uoms/workOrderManageAction!getWorkOrderNumByAddress.html",
		   type : "post", 
	       data : {"province":province,"city":city,"country":country,"town":town},
		   dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //待分配工单
			   $("#orderWfallocationVo").text(data.waitAllotNum);
			   //待处理
			   $("#orderWfdealVo").text(data.waitDealNum);
			   //变更申请
			   $("#orderCapplyVo").text(data.applyChangeNum);
			   //延时申请
		 	   $("#orderDelayVo").text(data.applyDelayNum);
			   //超时未处理
			   $("#orderTimeoutwithoutdealVo").text(data.overTimeDealNum);
			   //超时未完成
			   $("#orderTimeoutwithoutaccomplishVo").text(data.overTimeComplete);
			   //待归结
			   $("#orderWfsummaryVo").text(data.waitEndNum);
			   //超时未分配
			   $("#orderTimeoutwithoutallocationVo").text(data.overTimeAllotNum);
			   //告警设备数量 
			   $("#deviceAlarmVo").text(data.allNum);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
    
    /*************全部设备数量*************/
    $.ajax({
		   url : "/uoms/terminalAction!terminalNum.html",
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //全部设备
			   $("#deviceAllVo").text(data);
			   //在线设备
			  // $("#deviceOnlineVo").text(data);
		   },
		   error : function(){ // 请求失败
		     //infoMask('数据获取失败！');
		   }
		});
    /*************在线设备数量*************/
    $.ajax({
		   url : "/uoms/terminalAction!terminalOnlineNum.html",
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //在线设备
			   $("#deviceOnlineVo").text(data);
		   },
		   error : function(){ // 请求失败
		     //infoMask('数据获取失败！');
		   }
		});
});

/***********跳转至工单*************/
function goToOrder(status){
	var province = $("#province").combobox('getValue'); 
	var city = $("#city").combobox('getValue'); 
	var country = $("#country").combobox('getValue'); 
	var town = $("#town").combobox('getValue');
	if(province=="请选择省/市"){
		province="";
	}
	window.location.href="/uoms/workOrderManageAction!allWorkOrder.html?status="+status+"&province="+province+"&city="+city+"&country="+country+"&town="+town;
}

/***********跳转至设备管理*************/
function goToDevice(status){
	var province = $("#province").combobox('getValue'); 
	var city = $("#city").combobox('getValue'); 
	var country = $("#country").combobox('getValue'); 
	var town = $("#town").combobox('getValue');
	if(province=="请选择省/市"){
		province="";
	}
	if(status=='1'){
		//在线设备
		window.location.href="/uoms/talkSerialAction!talkSerialList.html?queryBean.status="
									+status+"&queryBean.provinceCode="+province+"&queryBean.cityCode="+city+"&queryBean.countryCode="+country+"&queryBean.townCode="+town;
	}else if(status=='all'){
		//全部设备
		window.location.href="/uoms/talkSerialAction!talkSerialList.html?queryBean.status="
									+status+"&queryBean.provinceCode="+province+"&queryBean.cityCode="+city+"&queryBean.countryCode="+country+"&queryBean.townCode="+town;
	}else if(status=='alarm'){
		//告警设备
		window.location.href="/uoms/terminalAlarmAction!terminalAlarmList.html?queryBean.status="
									+status+"&queryBean.provinceCode="+province+"&queryBean.cityCode="+city+"&queryBean.countryCode="+country+"&queryBean.townCode="+town;
	}
}


/***********区域选择*************/
function selectProvinceChange(){
	var province = $("#province").combobox('getValue'); 
	var city = $("#city").combobox('getValue'); 
	var country = $("#country").combobox('getValue'); 
	var town = $("#town").combobox('getValue'); 
	
	if(province=="请选择省/市"){
		province="";
	}
	if(city=="请选择城市"){
		city="";
	}
	if(country=="请选择区/县"){
		country="";
	}
	/**********工单数量***********/
	$.ajax({
		   url : "/uoms/workOrderManageAction!getWorkOrderNumByAddress.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //待分配工单
			   $("#orderWfallocationVo").text(data.waitAllotNum);
			   //待处理
			   $("#orderWfdealVo").text(data.waitDealNum);
			   //变更申请
			   $("#orderCapplyVo").text(data.applyChangeNum);
			   //延时申请
			   $("#orderDelayVo").text(data.applyDelayNum);
			   //超时未处理
			   $("#orderTimeoutwithoutdealVo").text(data.overTimeDealNum);
			   //超时未完成
			   $("#orderTimeoutwithoutaccomplishVo").text(data.overTimeComplete);
			   //待归结
			   $("#orderWfsummaryVo").text(data.waitEndNum);
			   //超时未分配
			   $("#orderTimeoutwithoutallocationVo").text(data.overTimeAllotNum);
			   //告警设备数量
			   $("#deviceAlarmVo").text(data.allNum);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	
	/**********设备数量***********/
	$.ajax({
		   url : "/uoms/terminalAction!terminalNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //全部设备
			   $("#deviceAllVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	$.ajax({
		   url : "/uoms/terminalAction!terminalOnlineNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //在线设备
			   $("#deviceOnlineVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
}


function selectCityChange(){
	var province = $("#province").combobox('getValue'); 
	var city = $("#city").combobox('getValue'); 
	var country = $("#country").combobox('getValue'); 
	var town = $("#town").combobox('getValue'); 
	if(province=="请选择省/市"){
		province="";
	}
	if(city=="请选择城市"){
		city="";
	}
	if(country=="请选择区/县"){
		country="";
	}
	$.ajax({
		   url : "/uoms/workOrderManageAction!getWorkOrderNumByAddress.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //待分配工单
			   $("#orderWfallocationVo").text(data.waitAllotNum);
			   //待处理
			   $("#orderWfdealVo").text(data.waitDealNum);
			   //变更申请
			   $("#orderCapplyVo").text(data.applyChangeNum);
			   //延时申请
			   $("#orderDelayVo").text(data.applyDelayNum);
			   //超时未处理
			   $("#orderTimeoutwithoutdealVo").text(data.overTimeDealNum);
			   //超时未完成
			   $("#orderTimeoutwithoutaccomplishVo").text(data.overTimeComplete);
			   //待归结
			   $("#orderWfsummaryVo").text(data.waitEndNum);
			   //超时未分配
			   $("#orderTimeoutwithoutallocationVo").text(data.overTimeAllotNum);
			   //告警设备数量
			   $("#deviceAlarmVo").text(data.allNum);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	/**********设备数量***********/
	$.ajax({
		   url : "/uoms/terminalAction!terminalNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //全部设备
			   $("#deviceAllVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	$.ajax({
		   url : "/uoms/terminalAction!terminalOnlineNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //在线设备
			   $("#deviceOnlineVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
}

function selectCountryChange(){
	var province = $("#province").combobox('getValue'); 
	var city = $("#city").combobox('getValue'); 
	var country = $("#country").combobox('getValue'); 
	var town = $("#town").combobox('getValue'); 
	if(province=="请选择省/市"){
		province="";
	}
	if(city=="请选择城市"){
		city="";
	}
	if(country=="请选择区/县"){
		country="";
	}
	$.ajax({
		   url : "/uoms/workOrderManageAction!getWorkOrderNumByAddress.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //待分配工单
			   $("#orderWfallocationVo").text(data.waitAllotNum);
			   //待处理
			   $("#orderWfdealVo").text(data.waitDealNum);
			   //变更申请
			   $("#orderCapplyVo").text(data.applyChangeNum);
			   //延时申请
			   $("#orderDelayVo").text(data.applyDelayNum);
			   //超时未处理
			   $("#orderTimeoutwithoutdealVo").text(data.overTimeDealNum);
			   //超时未完成
			   $("#orderTimeoutwithoutaccomplishVo").text(data.overTimeComplete);
			   //待归结
			   $("#orderWfsummaryVo").text(data.waitEndNum);
			   //超时未分配
			   $("#orderTimeoutwithoutallocationVo").text(data.overTimeAllotNum);
			   //告警设备数量
			   $("#deviceAlarmVo").text(data.allNum);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	/**********设备数量***********/
	$.ajax({
		   url : "/uoms/terminalAction!terminalNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //全部设备
			   $("#deviceAllVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	$.ajax({
		   url : "/uoms/terminalAction!terminalOnlineNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //在线设备
			   $("#deviceOnlineVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
}

function selectTownChange(){
	var province = $("#province").combobox('getValue'); 
	var city = $("#city").combobox('getValue'); 
	var country = $("#country").combobox('getValue'); 
	var town = $("#town").combobox('getValue'); 
	if(province=="请选择省/市"){
		province="";
	}
	if(city=="请选择城市"){
		city="";
	}
	if(country=="请选择区/县"){
		country="";
	}
	$.ajax({
		   url : "/uoms/workOrderManageAction!getWorkOrderNumByAddress.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //待分配工单
			   $("#orderWfallocationVo").text(data.waitAllotNum);
			   //待处理
			   $("#orderWfdealVo").text(data.waitDealNum);
			   //变更申请
			   $("#orderCapplyVo").text(data.applyChangeNum);
			   //延时申请
			   $("#orderDelayVo").text(data.applyDelayNum);
			   //超时未处理
			   $("#orderTimeoutwithoutdealVo").text(data.overTimeDealNum);
			   //超时未完成
			   $("#orderTimeoutwithoutaccomplishVo").text(data.overTimeComplete);
			   //待归结
			   $("#orderWfsummaryVo").text(data.waitEndNum);
			   //超时未分配
			   $("#orderTimeoutwithoutallocationVo").text(data.overTimeAllotNum);
			   //告警设备数量
			   $("#deviceAlarmVo").text(data.allNum);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	/**********设备数量***********/
	$.ajax({
		   url : "/uoms/terminalAction!terminalNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //全部设备
			   $("#deviceAllVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
	$.ajax({
		   url : "/uoms/terminalAction!terminalOnlineNum.html?province="+province+"&city="+city+"&country="+country+"&town="+town,
		   type : "post", 
	       dataType : "json",
	       async : true, 
		   success : function(data){ // 请求成功
			   //在线设备
			   $("#deviceOnlineVo").text(data);
		   },
		   error : function(){ // 请求失败
			   //infoMask('数据获取失败！');
		   }
		});
}

var boxFlag = 0;
$(".box1").click(function(){
	 $(".submenu").css("overflow","auto");
    if($(this).hasClass("equipMaintenance")){
        $(this).hasClass("allequipMaintenance")?boxFlag = 1:boxFlag = 0;
        $(this).hasClass("equipMaintenanceWarning")?boxFlag = 2:boxFlag = 1;
        menuItemEachWelCome("设备维护");
        $(parent.document).find(".menuItem").each(function(){
        	
            if($(this).text() == "设备维护"){
            	setTimeout(function(){
            		$(this).next(".submenu").css("height","100px");
            	},200);
            	$(this).next().css("min-height",100);
                $(this).next().find(".submenuItem").each(function(){
                    if(boxFlag == 1 && $(this).text() == "设备管理"){
                        $(this).addClass("submenuItemActive").siblings().removeClass("submenuItemActive");
                    }
                    if(boxFlag == 2 && $(this).text() == "设备告警"){
                        $(this).addClass("submenuItemActive").siblings().removeClass("submenuItemActive");                      
                    }
                });
            }
        });
    }else{
        menuItemEachWelCome("工单管理");
    }
});
function menuItemEachWelCome(msg){
    $(parent.document).find(".menuItem").each(function(){
        $(this).removeClass("open");
        hightPicWelCome();
        if($(this).text() == msg){
            $(this).addClass("open");
            hightPicWelCome();
        }
    });
}
function hightPicWelCome(){
    //隐藏下拉图标
    $(parent.document).find(".menuItem").each(function(){
        if($(this).siblings('.submenu').length == 0){
            $(this).siblings('.icon').hide();
        }
        if($(this).hasClass("open")){
            $(this).next().slideDown(120);//菜单显示
            $(this).siblings(".logo-img-black").hide();//高亮图片
            $(this).siblings(".logo-img-white").show();
            $(this).prev().addClass("left-border-check");//选择竖条
            $(this).siblings(".icon").css('background-image','url(common/images/nav_ic_down1.png)');//下拉上来icon
        }else{
            $(this).next().slideUp(120);//菜单收起
            $(this).siblings(".logo-img-black").show();
            $(this).siblings(".logo-img-white").hide();
            $(this).prev().removeClass("left-border-check");
            $(this).siblings(".icon").css('background-image','url(common/images/nav_ic_more1.png)');
        }
    });
}