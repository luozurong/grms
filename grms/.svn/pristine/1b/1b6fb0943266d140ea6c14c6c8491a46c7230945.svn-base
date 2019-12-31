
//初始化按钮
$(function(){
	getDetailList();
});

//可登记标记
var flag = true;

//获取收款登记列表
function getDetailList(){
	var contractCode = $("#contractCode").val();
	var backMoneyPlanCode = $("#backMoneyPlanCode").val();
	$.ajax({  
        type : "GET",  //提交方式  
        url : "/grms/backMoneyPlan/getBackMoneyPlanDetail",//路径  
        data:{"backMoneyPlanCode":backMoneyPlanCode},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var sss = result.backList;
            	var dataList = [];
            	var $eleS = $("#planList");
            	for(var i = 0; i < sss.length; i++) {
            		dataList.push({
            			id:sss[i].id,
            			backMoneyPlanCode: sss[i].backMoneyPlanCode,
            			type: sss[i].type,
            			planBackTime: sss[i].planBackTime,
            			planBackMoney: sss[i].planBackMoney,
            			remark: sss[i].remark,
            			realBackMoney: sss[i].realBackMoney,
            			realBackTime: sss[i].realBackTime,
            			realBackRemark: sss[i].realBackRemark,
            			status: sss[i].status
            		})
            	}
            	$eleS.datagrid({
            		data: dataList,
            		fitColumns: true,
            		nowrap: false,
            		scrollbarSize: 0,
            		onLoadSuccess: function(data) {
            			$(".datagrid-cell-check").addClass('dataCheck');
            			$(".datagrid-header-check").addClass('dataCheck');
            			$eleS.parent().find('td[field=projectProductId] .datagrid-cell').each(function() {
            				$(this).attr('title', $(this).html());
            			});
            			setTimeout(function(){
            				$eleS.datagrid('resize');
            			},30)
            		},
            		columns: [
            			[ //相应调整显示栏
            			  	{
	          					field: 'id',
	          					title: '子计划ID',
	          					width: 0,
	          					align: "center",
	          					hidden: true
          					},
            				{
            					field: 'backMoneyPlanCode',
            					title: '计划ID',
            					width: 100,
            					align: "center"
            				},
            				{
            					field: 'planBackTime',
            					title: '计划收款日期',
            					width: 80,
            					align: "center"
            				},
            				{
            					field: 'type',
            					title: '款项类型',
            					width: 80,
            					align: "center",
            					formatter: function(value,row,index){
            						if(row.type=='3'){
            							return "扣款";
            						}else{
            							return "收款";
            						};		
            					}
            				},
            				{
            					field: 'backType',
            					title: '收款款项属性',
            					width: 80,
            					align: "center",
            					formatter: function(value,row,index){
            						if(row.type=='1'){
            							return "进度款";
            						}else if(row.type=='2'){
            							return "结算款";
            						};		
            					}
            				},
            				{
            					field: 'planBackMoney',
            					title: '计划收/扣款金额',
            					width: 80,
            					align: "center"
            				},
            				{
            					field: 'realBackMoney',
            					title: '实收/扣金额',
            					width: 100,
            					align: "center",
            					formatter: function(value,row,index){
            						if(row.realBackMoney!=undefined){//实际收款不为0
            							var realBackMoney = row.realBackMoney;
            						}else{
            							var realBackMoney = 0;
            						}
            						
            						return realBackMoney;
            					}
            				},
            				{
            					field: 'unCollectedMoney',
            					title: '未收/扣金额',
            					width: 80,
            					align: "center",
            					formatter: function(value,row,index){
            						if(row.realBackMoney!=undefined){//实际收款不为0
            							var unCollectedMoney = row.planBackMoney - row.realBackMoney;
            						}else{
            							var unCollectedMoney = row.planBackMoney;
            						}
            						
            						return unCollectedMoney.toFixed(2);
            					}
            				},
            				{
            					field: 'status',
            					title: '收款状态',
            					width: 80,
            					align: "center",
            					formatter: function(value,row,index){
            						if(row.status==0){
            							return "待收款";
            						}else if(row.status==1){
            							return "已收款";
            						}else if(row.status==2){
            							return "收款异常";
            						};		
            					}
            				},
            				{
            					field: 'remark',
            					title: '备注',
            					width: 80,
            					align: "center"
            				},
            				{
            					field: 'handle',
            					title: '操作',
            					width: 80,
            					align: "center",
            					formatter: function(value,row,index){
            						var end = $("#end").val();
            						var register = '';
            						var detail = '';
            						var roleType = $("#roleType").val();
            						if(roleType==0||roleType==1){//业务员、业务管理员没有登记权限
                						detail='<a href="javascript:;" style="color: #0066FF" onclick="detail(\''+row.backMoneyPlanCode+'\',\''+contractCode+'\')"> 详情</a>';
            						}else if(roleType==3||roleType==-1){//超级管理员、财务管理员有权限
            							if(row.status==0){//未收款才可以登记
            								if(end=="1" && row.type==3){//如果结算款为已收款，扣款记录可登记
                								register='<a href="javascript:;" style="color: #0066FF" onclick="registerPlan(\''+row.id+'\',\''+row.type+'\')">登记</a>';
            								}else{//结算款未收款，
            									if(flag){
                    								register='<a href="javascript:;" style="color: #0066FF" onclick="registerPlan(\''+row.id+'\',\''+row.type+'\')">登记</a>';
                    								flag = false;
            									}
            								}
            							}else if(row.status==2){
            								flag = false;
            							}
                						detail='<a href="javascript:;" style="color: #0066FF" onclick="detail(\''+row.backMoneyPlanCode+'\',\''+contractCode+'\')"> 详情</a>';
            						}
            						return register + detail;
            					}
            				}
            				
            			]
            		]
            	});
            } else {  
            	//infoMask('删除失败');
            }  
        }  
    }); 
}

//跳转详情页
function detail(backMoneyPlanCode,contractCode){
	window.location.href="/grms/backMoneyPlan/jumpToPlanRegister?backMoneyPlanCode="+backMoneyPlanCode+"&contractCode="+contractCode;
}


var saveFlag = true;
//收款登记
function registerPlan(id,type){
	var contractCode = $("#contractCode").val();
	//查询计划收款时间
	$.ajax({  
        type : "GET",  //提交方式  
        url : "/grms/backMoneyPlan/getPlanBackTime",//路径  
        data:{"id":id},
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        success : function(result) {//返回数据根据结果进行相应的处理  
        	if ( result.success) {  
        		//回显计划收款时间
        		
        		var time = result.planBackTime;
            	$("#realBackTime").datebox('setValue', time);
        	
        		console.log(11);	
        	}
        }
	});
	
	
	if(type=='3'){//扣款
		$("#realeReduce").show();
		$("#realeBack").hide();
		var title = "";
	    var rowId;
	    $("#realbacktime").hide();
	    $("#button").css({"display":"block"});
	    $(".detail-wrap").css({"height":"358"});
        title = "登记扣款";
        type="add";
        $('#roleName1').val("");
        $('#note1').val("");
	    wrapMaskShow();//父级遮罩显示
	    $('body').css('overflow','hidden');//禁止滚动
	    $('#roleWin').dialog({
	        width:480,
	        height:380,
	        modal:true,
	        collapsible:false,
	        minimizable:false,
	        maximizable:false,
	        cache : false ,
	        closable:true,
	        draggable:false,
	        resizable:false,
	        inline:false,
	        title:title,
	        onClose : function(){
	            wrapMaskHide();//父级遮罩隐藏
	            $('body').css('overflow','auto');//恢复滚动
	        },buttons:[{
				text:'取消',
				handler:function(){
					 wrapMaskHide();//父级遮罩隐藏
					$('#roleWin').dialog('close');
				}
			},{
				text:'确定',
				handler:function(){
					if(!saveFlag){
						return;
					}
					saveFlag = false;
					var type = "2";
					var realMoney = $("#realBackMoney").val();
					var realBackRemark = $("#realBackRemark").val();
					if(realBackRemark==""){
						infoMask('审批意见必填');
						return;
					}
					$.ajax({  
				        type : "POST",  //提交方式  
				        url : "/grms/backMoneyPlan/enregisterMoney",//路径  
				        data:{"type":type,"id":id,"realMoney":realMoney,"realBackRemark":realBackRemark,"contractCode":contractCode},
				        dataType : "json",//数据，这里使用的是Json格式进行传输  
				        success : function(result) {//返回数据根据结果进行相应的处理  
				        	//扣款登记
				        	if ( result.success) {  
				        		saveFlag = true;
				        		infoMask("登记成功");
				        		location.reload();
				        	}else{
				        		saveFlag = true;
				        		infoMask("登记失败");
				        	}
				        	wrapMaskHide();//父级遮罩隐藏
				        	$('#roleWin').dialog('close');
				        }
					});
				}
			}]
	    });
	    winHiSelfAdaptation($('#roleWin'));//弹窗定位
	}else{//收款
		$("#realeReduce").hide();
		$("#realeBack").show();
		var title = "";
	    var rowId;
	    $("#realbacktime").show();
	    $("#button").css({"display":"block"});
	    $(".detail-wrap").css({"height":"358"});
        title = "登记收款";
        type="add";
        $('#roleName1').val("");
        $('#note1').val("");
	    wrapMaskShow();//父级遮罩显示
	    $('body').css('overflow','hidden');//禁止滚动
	    $('#roleWin').dialog({
	        width:480,
	        height:380,
	        modal:true,
	        collapsible:false,
	        minimizable:false,
	        maximizable:false,
	        cache : false ,
	        closable:true,
	        draggable:false,
	        resizable:false,
	        inline:false,
	        title:title,
	        onClose : function(){
	            wrapMaskHide();//父级遮罩隐藏
	            $('body').css('overflow','auto');//恢复滚动
	        },buttons:[{
				text:'取消',
				handler:function(){
					  wrapMaskHide();//父级遮罩隐藏
					$('#roleWin').dialog('close');
				}
			},{
				text:'确定',
				handler:function(){
					if(!saveFlag){
						return;
					}
					saveFlag = false;
					var type = "1";
					var realMoney = $("#realBackMoney").val();
					var realBackTime = $("#realBackTime").datebox('getValue')
					var realBackRemark = $("#realBackRemark").val();
					if(realBackRemark==""||realBackTime==""){
						infoMask('审批意见和到账时间必填');
						return;
					}
					$.ajax({  
				        type : "POST",  //提交方式  
				        url : "/grms/backMoneyPlan/enregisterMoney",//路径  
				        data:{"type":type,"id":id,"realMoney":realMoney,"realBackRemark":realBackRemark,"realBackTime":realBackTime,"contractCode":contractCode},
				        dataType : "json",//数据，这里使用的是Json格式进行传输  
				        success : function(result) {//返回数据根据结果进行相应的处理  
				        	//扣款登记
				        	if ( result.success) {  
				        		saveFlag = true;
				        		infoMask("登记成功");
				        		location.reload();
				        	}else{
				        		saveFlag = true;
				        		infoMask("登记失败");
				        	}
				        	wrapMaskHide();//父级遮罩隐藏
				        	$('#roleWin').dialog('close');
				        }
					});
				}
			}]
	    });
	    winHiSelfAdaptation($('#roleWin'));//弹窗定位

	}
}

