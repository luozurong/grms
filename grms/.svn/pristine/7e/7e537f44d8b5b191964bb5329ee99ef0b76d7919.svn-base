var business_type=1; //指定到的页面

$(function(){

	$(".project3").next().next().next().next().find(".tb-color").click(function(){selectCustomerRow34($(this))});
	$(".project4").next().next().next().next().find(".tb-color").click(function(){selectCustomerRow34($(this))});
	//用户默认时间选择事件
	dateBoxInitDeault($("#startTime3"),$("#endTime3"),3);
	//电商默认时间选择事件
	dateBoxInitDeault($("#startTime4"),$("#endTime4"),4);
	//社区运营默认时间选择事件
	dateBoxInitDeault($("#startTime1"),$("#endTime1"),1);
	//煤管默认时间选择事件
	dateBoxInitDeault($("#startTime2"),$("#endTime2"),2);
	
	
	
	
	var num = $("#businessType").val();
	if(num == '') {
		num = 1;
		business_type=1;
	}else{
		business_type=num;
	}
	
	$(".workorder-tabs .workorder-tabs"+num).addClass("workorder-select").siblings().removeClass("workorder-select");
	$(".addProject-pop>div").eq(num-1).addClass("addProject-pop-active").siblings().removeClass("addProject-pop-active");
	
	
});

$(".workorder-tabs>div").click(function(){
	if($(this).hasClass("workorder-tabs1")){
		business_type=1;
	}
	if($(this).hasClass("workorder-tabs2")){
		business_type=2;
	}
	if($(this).hasClass("workorder-tabs3")){
		business_type=3;
	}
	if($(this).hasClass("workorder-tabs4")){
		business_type=4;
	}
	if($("#operate").val()==1){
		return false;
	}
	$(this).addClass("workorder-select").siblings().removeClass("workorder-select");
	$(".addProject-pop>div").eq($(this).index()).addClass("addProject-pop-active").siblings().removeClass("addProject-pop-active");
	setTimeout(function(){
		//dataFunc();
	},500);
});


// 1：社区运营=================================================================================================================================================
/**
 * 社区运营数据保存
 */
function saveProject1(){
	var project1={};
	project1.id=$("#project1Id").val();
	//场次名称
	project1.fieldName=$("#field_name").val();
	if($("#field_name").val().trim().length==0){
		infoMask("场次名称不能为空");
		return;
	}
	//开始时间
	project1.beginTime=$("#startTime1").val();
	if($("#startTime1").val().trim().length<=0){
		infoMask("开始时间不能为空");
		return;
	}
	//结束时间
	project1.endTime=$("#endTime1").val();
	if($("#endTime1").val().trim().length<=0){
		infoMask("结束时间不能为空");
		return;
	}
	//选择小区id	
	project1.organizationSeq=$("#project1AreaO").val();
	if($("#project1AreaO").val().trim().length<=0){
		infoMask("未选择小区");
		return;
	}
	//选择小区名称
	project1.areaName=$("#project1AreaName").val();
	//是否服从场次分配
	project1.isDistributionField=$("#isDistributionField").is(".checkbox-bg")?"1":"0";
	
	//是否服从时间调配
	project1.isDistributionTime=$("#isDistributionTime").is(".checkbox-bg")?"1":"0";
	
	//标准场地（3米*3米，必须加基础引流）
	var productList=[];
	var product1011={};
	
	product1011.id="1011";
	product1011.buyNum=$("#p1011").val();	
	
	productList[0]=product1011;
	var product1012={};
	
	/*product1012.id="1021";
	product1012.buyNum=$("#p1011").val();
	
	
	productList[1]=product1012;*/
	
	//单选	
	var ddbg=$(".radio-bg");
	if(ddbg.length>0){
		dd=ddbg.find('input');
		productdd={};
		productdd.id=dd.val();				
		productdd.buyNum=$("#ddNum").val();
		
		if($("#ddNum").val().trim().length<=0){
			infoMask("已选项数量不能为空");
			return;
		}
		productList.push(productdd);
	}
	
	project1.projectMenus=productList;
	project1.businessType="1";
	project1.businessName="社区运营";
	
	//追加游戏
	//$(".project1 .checkbox-bg").each(
	var project_1=$(".project1 .checkbox-bg");
	for(var i=0;i<project_1.length;i++){
		var num=$(project_1[i]).parent().next().find("input").val();
		//console.log("数据："+num);
		
		if(!(/^\+?[1-9][0-9]*$/.test(num))){
			infoMask("已选项数量必须为数字");
			return;
		}
		if(num.trim().length<=0){
			infoMask("已选项数量有误");
			return;
		}
		
		if(num<=0){
			//infoMask("已选项数量不能为0");
			return;
		}
		if(num>0){
			prodect={};
			prodect.id=$(project_1[i]).find("input").val();
			prodect.buyNum=$(project_1[i]).parent().next().find("input").val();
			
			productList.push(prodect);
		}
	}
			
	if(productList.length<=0){
		infoMask("您还未选中任何清单,无法保存");
		return;
	}
	
	showShade(true,"保存项目清单中,请稍后......");
	
	console.log(JSON.stringify(project1));
	
	$.ajax({
        url: "../projectProduct/saveProjectProdect.html",
        type:"POST",
        data:JSON.stringify(project1),
        dataType:"json",
        contentType:'application/json;charset=utf-8',
        success: function (data) {	        	        	
            if(true == data.success){                		                        	
            	successMask('保存成功',"/grms/projectProduct/backAddProject.html");                 
                
            }else{
            	infoMask('保存失败:' +data.msg);
            }
        },
        error: function () {
        	infoMask('保存失败:' +"服务器响应异常");
        },
        complete : function (XHR, TS){
            showShade(false);
        }
    });
	
}

//煤管选择默认小区触事件
function selectCustomer1(){
	if($("input[name='organizationSeq1']:checked").val()){	                	    	  
    	var area=$("#infoDg").datagrid("getSelected");
    	console.log(area.organizationSeq+":"+area.areaName);   	
    	$("#project1AreaO").val(area.organizationSeq);  
    	$("#project1AreaName").val(area.areaName);
    	$("#project1AreaSh").text("已选小区:"+area.areaName);
    }
}


$(function(){
	$("#p1011").blur(function(){
		if(!(/^\+?[1-9][0-9]*$/.test($("#p1011").val()))){	
				$("#p1011").val(1);
				infoMask("请输入大于0的数量");
				return;
		}
		if($("#p1011").val()<1){
			$("#p1011").val(1);
			infoMask("请输入大于0的数量");
		}
		
	});
	//对输入数量绑定失去焦点事件
	$("#ddNum").blur(function(){
		if($("#ddNum").val()==""){
			return;
		}
		
		if(!(/^\+?[1-9][0-9]*$/.test($("#ddNum").val()))||$("#ddNum").val()<=0){	
				$("#ddNum").val("");
				infoMask("请输入大于0的数量");				
		}
	});
	$(".project1").each(function(){
		
		var num=$(this).next().find(".input-num");
		
		num.blur(function(){
			if(num.val()==""){
				return;
			}
			
			if(!(/^\+?[1-9][0-9]*$/.test(num.val()))||num<=0 ){
				num.val("");
				infoMask("请输入大于0的数量");				
		 	}
		});
		
	});
});



// 2：煤管=====================================================================================================================================================

function selectCustomer2(areaDeault){
	
	//判断煤管不可勾选的帧位,以及默认小区后每行记录操作
	$.ajax({
        url: "../projectProduct/screen2Prodect.html",
        type:"POST",
        data:{
        	organizationSeqs:areaDeault.organizationSeqs,
        	beginTime:$("#startTime2").val(),
        	endTime:$("#endTime2").val()
        },
        success: function (data) {	 
        	var result=JSON.parse(data);
            if(true == result.success){ 
            	var listM=result.obj;  //删选小区处理结果
            	//筛选成功,对表格进行处理
            	$(".project2").each(function(index,element){
            		//开始时间
            		$(this).next().find(" .startTime").datebox('setValue', $("#startTime2").datebox('getValue'));
            		//结束时间
            		$(this).next().find(".endTime").datebox('setValue', $("#endTime2").datebox('getValue'));
            		//复选框置为未选
            		//$(this).find("input[type='checkbox']").parent().removeClass("checkbox-bg");
            		//console.log("哈哈哈哈1"+listM[0].result);
            		//判断复选框是否可操作
            		for(var i=0;i<listM.length;i++){
            			if(listM[i].meanId==$(this).next().find(".meanId").val()){
            				if(listM[i].result==false){
            					//console.log("哈哈哈哈");
            					//用户不可操作
            					if($(this).find("input[type='checkbox']").attr('readonly') =='readonly'){
            						$(this).find("input[type='checkbox']").parent().removeClass("checkbox-bg");
	            					$(this).find("input[type='checkbox']").parent().removeClass("checkbox-icon");									
									$(this).find("input[type='checkbox']").parent().addClass("readonly-bg");
            					}
            				}else{
            					//用户可操作
            					//按钮 待续..... 
            					if(!$(this).find("input[type='checkbox']").parent().is(".checkbox-icon")){
								$(this).find("input[type='checkbox']").parent().addClass("checkbox-icon");}
								//$(this).parent().removeClass("checkbox-bg");
								$(this).find("input[type='checkbox']").parent().removeClass("readonly-bg");
				            }            					
            			}            				                        				            			              				
            				
            			
            		}
            		//组织机构编号
            		$(this).next().find(".oreqS").val(areaDeault.organizationSeqs);           		
            		//小区名称
            		//console.log(areaDeault.areaNames);
            		$(this).next().next().next().text(areaDeault.areaNames);            		
            		
            	});
            	
            	
            }else{
            	infoMask('筛选有误:' +result.msg);
            }
        },
        error: function () {
        	infoMask('筛选有误:' +"服务器响应异常");
        },
        complete : function (XHR, TS){
            showShade(false);
        }
       });
}

$(function(){
	$(".project2").each(function(index,element){
		//console.log(3);
		//console.log($(this).next().find('input [name="startTime"]'));
		//选择时间绑定事件
		dateBoxInit($(this),2);
		var pp=$(this);
		//选择小区绑定事件
		$(this).next().next().next().next().find(".tb-color").click(function(){
        	//绑定小区弹出框事件
			showAreaByMeanId2(pp);
        });       
    
	});
	
	/**
	 * 其他失去焦点事件
	 */
	$(".input-inline-num").blur(function(){
		var numQ=$(".input-inline-num").val().trim();
		if(numQ.trim()=='') return;
		if(!(/^\+?[1-9][0-9]*$/.test(numQ))){
			$(".input-inline-num").val("");
			infoMask("请输入大于15，且为5的倍数的数字");
			return;
		}
		if(!(numQ%5==0&&numQ>15)){
			$(".input-inline-num").val("");
			infoMask("请输入大于15，且为5的倍数的数字");
			return;
		}
		  
	})
	
	/**
	 * 初始化数据校验
	 */
	var checks=$(".project2").find(".checkbox-bg");
	for(var i=0;i<checks.length;i++){		
		var pp2=$(checks[i]).parent();
		checkProduc2(pp2);
	}
	
	
});

function checkProduc2(pp2){
	$.ajax({
        url: "../projectProduct/checkProduc2tByMeadId.html",
        type:"POST",
        data:{
        	meanId:pp2.next().find(".meanId").val(),
        	startTime:pp2.next().find(".startTime").val() ,
        	endTime:pp2.next().find(".endTime").val() ,
        	orsQ:pp2.next().find(".oreqS").val() ,
        },	       
        success: function (data) {
        	//console.log(data+"的点点滴滴多多");
            if("false" == data){ 	            	
            	var check=pp2.find("input[type='checkbox']");
            	check.parent().removeClass("checkbox-icon");
        		check.parent().removeClass("checkbox-bg");
        		if(!check.parent().is(".readonly-bg"))
        		check.parent().addClass("readonly-bg");
            }	            
        },
        error: function () {
        	console.log('失败:' +"服务器响应异常");
        },
        complete : function (XHR, TS){
            showShade(false);
        }
    });
}		

/**
 * 针对煤管:单个清单选择小区时触发弹框
 * pp:class="project2" 对应的元素，jQuery元素
 */
function showAreaByMeanId2(pp){
	var startT=pp.next().find('.startTime').datebox('getValue');  
	var endT=pp.next().find('.endTime').datebox('getValue');
	//console.log("开始："+startT)
	if(!startT||startT.trim()==""){
		infoMask('开始日期不能为空!');
		return; 
	}
	if(!endT||endT.trim()==""){
		infoMask('结束日期不能为空!');
		return;
	}
	
	wrapMaskShow();//父级遮罩显示
	 $('body').css('overflow','hidden');//禁止滚动
	 
	 initAddress();
	 
	meadId=pp.next().find(".meanId").val();
	//console.log("meadId:"+meadId);
    beginTimeM=startT;
    endTimeM=endT;
    
    //TODO 设置回显的小区2 OK
    var  oreqS=pp.next().find(".oreqS").val().trim();//小区机构编号
    var areaNames=pp.next().next().next().text();
    if(oreqS){
    	//console.log("1:"+select_cell_list)
    	var org_list=oreqS.split(",");
    	var raea_Names=areaNames.split(",");
    	
    	for(var i=0;i<org_list.length;i++){
    		var area_info={};  //小区信息
    		area_info.oreq=org_list[i];
    		area_info.areaName=raea_Names[i];
    		select_cell_list.push(area_info);
    	}    
    	selectCellListDeWeight();   	
    }
       
	$('#infoDg').datagrid({
			collapsible:true,
			emptyMsg:'<span>无记录</span>',		    
			method:'get',
			scrollbarSize:0,
			url:'../projectProduct/findBAreaListByMeanId.html',	
			queryParams: {
				pageNo:1,
		  		id:meadId,
		  		beginTime:beginTimeM,
		  		endTime:endTimeM
			},
	        nowrap:false,//允许换行
	        fitColumns:true,//宽度自适应
	        singleSelect:business_type==1?true:false ,  //如果为true，则只允许选择一行
			onLoadSuccess : function(data) {// Fires when data is				
		    	//循环判断操作为新增的不能选择
            	for (var i = 0; i < data.rows.length; i++) {
	                //根据isFinanceExamine让某些行不可选
	                if (data.rows[i].result == false) {
	                	//console.log("额,怎么了");
	                    $("td[field='organization_seq_result']").find("input")[i+1].disabled = true;	                    	                    
	                    //干掉select_cell_list中不可选的
	                    for (var j=0;j<select_cell_list.length;j++) {
						     if(select_cell_list[j].oreq==data.rows[i].organizationSeq){  
						    	 if (data.rows[i].result == false) {						    		 
						    		 select_cell_list.splice(j,1);	
					             }				     	
						     }							
						}
	                }
            	}	
			 
				//TODO 对选中元素进行回显 OK
				if(business_type!=1){
					for(var j=0;j<data.rows.length;j++){
						for (var i=0;i<select_cell_list.length;i++) {
						     if(select_cell_list[i].oreq==data.rows[j].organizationSeq){  
						     	//console.log(data.rows.organizationSeq+"：哈哈");
						     	$("#infoDg").datagrid("selectRow",j);
						     	select_cell_list[i].areaName=data.rows[j].areaName;
						     	break;
						     }							
						}
				   }					
			    // console.log(select_cell_list)
				
				page_num=data.page;
	        	pageBtnShowOrHide('#infoPp',data);	        	
				$('#infoPp').pagination('refresh', {
					total : data.total,
					pageNumber : data.page
				});
				 $('#infoDg').datagrid('resize');
				 
				 inputStyle();
				 
				 setTimeout(function(){
					 $(".datagrid-row,.datagrid-header-check").click(function(){
					    setTimeout(function(){
					        inputStyle();
					    },100)
					});
				 },10);
				} 
			},
			onLoadError:function(){
				$('#infoDg').datagrid('resize');
	        },	       
	        onClickRow : function(rowIndex, rowData) {	        	
				//根据status值 单击单选行不可用
				if (rowData.result == false) {
					//console.log(rowData.result+":" +rowIndex);
					$('#infoDg').datagrid('unselectRow', rowIndex);
				} 
			},
			onDblClickRow : function(rowIndex, rowData) {
				//根据status值 双击单选行不可用
				if (rowData.result == false) {
					$('#infoDg').datagrid('unselectRow', rowIndex);
				} 
			},
			onSelectAll : function(rows) {
				//根据status值  全选时某些行不选中
				$.each(rows, function(i, n) {
					if (n.result == false) {						
						$('#infoDg').datagrid('unselectRow', i);
						//console.log("i值："+i)
						$($("input[name='organization_seq_result']")[i+1]).prop('checked',false);
					}
				});
				//TODO 回显时会用...... OK
				for(var i=0;i<rows.length;i++){
					if (rows[i].result==true){
						var area_info={};
		    			area_info.oreq=rows[i].organizationSeq;
		    			area_info.areaName=rows[i].areaName;
		 	        	select_cell_list.push(area_info);
					}				
				}
				selectCellListDeWeight();
			},
			onSelect:function(rowIndex, rowData){
    			//TODO ok
 	        	var area_info={};
    			area_info.oreq=rowData.organizationSeq;
    			area_info.areaName=rowData.areaName;
 	        	select_cell_list.push(area_info);
 	        	selectCellListDeWeight();	 
 	        },
 	        onUnselect:function(rowIndex, rowData){
 	        	selectCellListDelete(rowData.organizationSeq);
 	        },
 	        onUnselectAll:function(rows){
 	        	for(var i=0;i<rows.length;i++){
					selectCellListDelete(rows[i].organizationSeq);
				}
 	        },
			columns : [ [     
			{
				field : 'organization_seq_result',
				title:"选择",
				align : 'center',
				checkbox:true,
				width : 50,
			},
			{
				field : 'cityName',
				title : '城市',
				width : 100,
				halign : 'center',
				align : 'center'
			},              
			{
				field : 'countryName',
				title : '区域',
				width : 100,
				halign : 'center',
				align : 'center'
			}, {
				field : 'areaName',
				title : '楼盘名称',
				width : 100,
				halign : 'center',
				align : 'center'
			}, {
				field : 'town',
				title : '小区编码',
				width : 120,
				halign : 'center',
				align : 'center',
				formatter:function(value,row,index){
					return row.organizationSeq;
				}
			}, {
				field : 'areaCategory',
				title : '小区类型',
				width : 80,
				halign : 'center',
				align : 'center',
				formatter:function(value,row,index){
					
						switch (value) {
						case "0":							
							return "测试小区";
							break;
	                    case "1":	
	                    	return "云对讲项目";
							break;
						case "2":
							return "工程项目";
							break;
	                    case "3":	
	                    	return "样板演示小区";
							break;						
						default:
							break;
						}
					  }
			    }, {
				field : 'translateStatus',
				title : '转化情况',
				width : 80,
				halign : 'center',
				align : 'center',
				formatter:function(value,row,index){
					switch (value) {
					case "0":
						return "未转化";
						break;
                    case "1":	
                 	return "已完成转化";
						break;
					case "2":
						return "部分转化(试点转化)";
						break;                    					
					default:
						break;
					}
				  }
	        	},{
					field : 'householdNum',
					title : '实际户数',
					width : 80,
					halign : 'center',
					align : 'center'
		        },{
					field : 'areaAddress',
					title : '地址',
					width : 170,
					halign : 'center',
					align : 'center'
		        }
			 ] ]
		});		
	// 分页
	comPagination('#infoPp',10,infoPpSelectPage2);
	
	
		
	/* $("#infoDg").datagrid('load',
		{
		  		pageNo:1,
		  		id:meadId,
		  		beginTime:beginTimeM,
		  		endTime:endTimeM
		}); */		
		//弹框
		 $('#select_Area').dialog({
        width:1000,
        height:575,
        modal:true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:true,
        draggable:true,
        resizable:false,
        inline:false,
        title:"选择小区",
        ok:"确定",
        cancel:"取消",
        buttons:[{
            text:'取消',
            handler:function(){
                wrapMaskHide();
                
                select_cell_list=[];
                page_num=1;
                
                $("#provinceCode").val("");
            	$("#cityCode").val("");
            	$("#countryCode").val("");
            	$("#communityName").val("");	           
                //将三级联动至于 请选择
            	$("#province").combobox("select","");
                $('#select_Area').dialog('close');
                	                	               	                
            }
	    },{
            text:'确认',
            handler:function(){
                wrapMaskHide();
               // console.log(select_cell_list)
               var areaDeault={};	                
                areaDeault=getAreaDeault();
                
                pp.next().find(".oreqS")
                
                //console.log(areaDeault.organizationSeqs);
                //console.log(areaDeault.areaNames);
                pp.next().find('.oreqS').val(areaDeault.organizationSeqs);
				pp.next().next().next().text(areaDeault.areaNames);
				
				if(!pp.find("input[type='checkbox']").parent().is(".checkbox-icon")){
					pp.find("input[type='checkbox']").parent().addClass("checkbox-icon");
			    }						
	                //$(this).parent().removeClass("checkbox-bg");
	            pp.find("input[type='checkbox']").parent().removeClass("readonly-bg");
                
                //清空
                select_cell_list=[];
                page_num=1;
                
                $("#provinceCode").val("");
            	$("#cityCode").val("");
            	$("#countryCode").val("");
            	$("#communityName").val("");
                //将三级联动至于 请选择
            	$("#province").combobox("select","");
                $('#select_Area').dialog('close');
                	              
            }
        }],

        onClose : function(){
        	
        	select_cell_list=[];
            page_num=1;
            
            //将三级联动至于 请选择
        	$("#province").combobox("select","");
           
        	$("#provinceCode").val("");
        	$("#cityCode").val("");
        	$("#countryCode").val("");
        	$("#communityName").val("");
            wrapMaskHide();//父级遮罩隐藏
            $('body').css('overflow','auto');//恢复滚动
        }
    });
    winHiSelfAdaptation($('#select_Area'));//弹窗定位
}


var meadId="";
var beginTimeM;
var endTimeM;


//详情的表格分页==针对煤管单行选择小区
function infoPpSelectPage2(pageNo, pageSize){
	
	var provinceCode=$("#provinceCode").val();
	var cityCode=$("#cityCode").val()=="请选择"||$("#cityCode").val()=="0086"?"":$("#cityCode").val();
	var countryCode=$("#countryCode").val()=="请选择"||$("#countryCode").val()=="0086"?"":$("#countryCode").val();
	var communityName=$("#communityName").val();
	
	var start = (pageNo - 1) * pageSize; 
    var end = start + pageSize; 	
    $("#infoDg").datagrid("reload", {
		pageNo:pageNo,
		province:provinceCode,
		city:cityCode,
		country:countryCode,
		areaName:communityName,
		id:meadId,
		beginTime:beginTimeM,
		endTime:endTimeM
	}); 
}



// 3：用户运营=================================================================================================================================================
$(function(){
	//每行选择时间绑定
	$(".project3").each(function(index,element){
		//选择时间绑定事件
		dateBoxInit($(this),3);
		
		//输入数量添加事件
		number3And4($(this));
	});
});

// 4：电商运营=================================================================================================================================================
$(function(){
	//每行选择时间绑定
	$(".project4").each(function(index,element){
		//选择时间绑定事件
		dateBoxInit($(this),4);
		//输入数量添加事件
		number3And4($(this));
	});
});



//===========================================================================================================================================================
/**
 * 用户运营,电商运营选择默认小区
 */
function selectCustomer3And(areaDeault,bn_type){
	$(".project"+bn_type).each(function(){
		//组织机构编号
        $(this).next().find(".oreqS").val(areaDeault.organizationSeqs);           		
        //小区名称            		
        $(this).next().next().next().text(areaDeault.areaNames);           		
	});
}

/**
 * 3,4输入数量绑定事件
 * @param {Object} pp
 */
function number3And4(pp){
	pp.next().next().find(".input-num").attr("maxlength",6);
	pp.next().next().find(".input-num").blur(
		function(){
			if($(this).val()=="") return;
			var startT=pp.next().find('.startTime').datebox('getValue');  
			var endT=pp.next().find('.endTime').datebox('getValue');
			//console.log("开始："+startT)
			if(!startT||startT.trim()==""){
				infoMask('开始日期不能为空!');
				pp.next().next().find(".input-num").val("");
				return; 
			}
			if(!endT||endT.trim()==""){
				infoMask('结束日期不能为空!');
				pp.next().next().find(".input-num").val("");
				return;
			}
			
			
			var numQ=pp.next().next().find(".input-num").val().trim();
			
			if(numQ.trim()=='') return;
			var num=addNum[pp.next().find('.meanId').val()];
			if(num<=0){
				pp.next().next().find(".input-num").val("");
				infoMask("当前时间内库存量为0,请重新选择时间");
				return;
			}
			var resultNum=num;
									
			if(!(/^\+?[1-9][0-9]*$/.test(numQ))){	
				pp.next().next().find(".input-num").val("");
				infoMask("当前购买数量的库存量为"+num+",请重新输入小于等于"+resultNum+"的购买数量。");
				return;
			}
			if(numQ<=0){	
				pp.next().next().find(".input-num").val("");
				infoMask("当前购买数量的库存量为"+num+",请重新输入大于0且小于等于"+resultNum+"的购买数量。");
				return;
			}
			
			if(numQ>resultNum){
				pp.next().next().find(".input-num").val("");
				infoMask("当前购买数量的库存量为"+num+",请重新输入小于等于"+resultNum+"的购买数量。");
				return;
			}
			//addNum[pp.next().find('.meanId').val()]=numQ;
		}
	);
}


/**
 * 用户运营,电商   加载完数据后 ,设置不可选操作
 */
$(function(){
	$(".project3").each(function(){
		filterNotSelectable($(this));
	});
	$(".project4").each(function(){
		filterNotSelectable($(this));
	})
	
});

function filterNotSelectable(pp){
	//console.log(pp.next().find('.meanId').val()+"：哈哈");
	var num=addNum[pp.next().find('.meanId').val()];
	//console.log(addNum)
	//console.log(pp.next().find('.meanId').val()+"："+num);
	if(num<=0){
		//console.log(pp.next().find('.meanId').val()+"："+num);
		var check=pp.find("input[type='checkbox']");
		check.parent().removeClass("checkbox-icon");
		check.parent().removeClass("checkbox-bg");
		if(!check.parent().is(".readonly-bg"))
		check.parent().addClass("readonly-bg");
		pp.next().next().find(".input-num").val("");
		return;
	}
	if(pp.next().next().find(".input-num").val()&&num<pp.next().next().find(".input-num").val()){
		pp.next().next().find(".input-num").val("");
	}
}

//==============================================================================



//保存清单2,3,4 ==开始====================================================================================================
function saveProduct(b_type){
	var chceks=null;
	if(b_type==2){
		//console.log("哈哈哈");
		chceks=$(".project2").find('.checkbox-bg');
	}
	if(b_type==3){
		chceks=$(".project3").find('.checkbox-bg');
	}
	if(b_type==4){
		chceks=$(".project4").find('.checkbox-bg');
	}
	//console.log("哈哈哈："+chceks.length)
	var productList=[];
	for(var i=0;i<chceks.length;i++){
		var pp=$(chceks[i]).parent();
		//var pp=$(this).parent();
		
		var productMeau={};
		
		//产品清单id
		var id=pp.next().find('.meanId').val();
		productMeau.id=id;
		
		//开始执行时间
		var begin_time=pp.next().find('.startTime').datebox('getValue');
		if(!begin_time||begin_time.trim().length<=0){
			infoMask("有选项未选开始时间");
			return;
		}
		productMeau.beginTime=begin_time;
		
		//结束执行时间
		var end_time=pp.next().find('.endTime').datebox('getValue');
		if(!end_time||end_time.trim().length<=0){
			infoMask("有选项未选结束时间");
			return;
		}
		productMeau.endTime=end_time;
		
		//购买数量
		var buy_num=0;
		if(b_type==2)  buy_num=1;
		if(b_type==3||b_type==4){
			buy_num=pp.next().next().find('.input-num').val().trim();
		}
		if(buy_num<=0){
			infoMask("已选项数量必须大于0");
			return;
		}	
		productMeau.buyNum=buy_num;		
		
		//小区id字符串
		var area_ids=pp.next().find('.oreqS').val();
		if(area_ids.trim().length<=0){
			infoMask("有选项未选择小区");
			return;
		}
		productMeau.areaIds=area_ids;
		
		//小区名称字符串
		var area_names=pp.next().next().next().text();
		if(area_names.trim().length<=0){
			infoMask("有选项未选择小区");
			return;
		}
		productMeau.areaNames=area_names;
		
		//其他
		if(b_type==2&& id=='2014'){
			var other_num1=$(".input-inline-num").val().trim();
			if(other_num1.trim().length<=0){
				infoMask("其他项未填写时间");
				return;
		    }
			if(!(/^\+?[1-9][0-9]*$/.test(other_num1))){
				$(".input-inline-num").val("");
				infoMask("其他项输入信息有误,请输入大于15，且为5的倍数的数字");
				return;
			}
			if(!(other_num1%5==0&&other_num1>15)){
				$(".input-inline-num").val("");
				infoMask("其他项输入信息有误,请输入大于15，且为5的倍数的数字");
				return;
			}
			productMeau.otherNum1=other_num1;
		}
		
		productList.push(productMeau);
	};
	setTimeout(function(){
		if(productList.length==0){
			infoMask("您还未选中任何清单,无法保存");
			return;
		}
		
		
		var project_menu_vo={};
		project_menu_vo.projectMenus=productList;
	
		project_menu_vo.businessType=b_type;
		
		showShade(true,"保存项目清单中,请稍后......");
		
		$.ajax({
	        url: "../projectProduct/saveProjectProdect.html",
	        type:"POST",
	        data:JSON.stringify(project_menu_vo),
	        dataType:"json",
	        contentType:'application/json;charset=utf-8',
	        success: function (data) {	        	        	
	            if(true == data.success){                		                        	
	            	successMask('保存成功',"/grms/projectProduct/backAddProject.html");                 
	                
	            }else{
	            	infoMask('保存失败:' +data.msg);
	            }
	        },
	        error: function () {
	        	infoMask('保存失败:' +"服务器响应异常");
	        },
	        complete : function (XHR, TS){
	            showShade(false);
	        }
	    });
	
	},10)
	
	
}



//保存清单2,3,4 ==结束====================================================================================================


//
/**
 * 用户运营，电商运营每一行选择小区
 * areaA :点击小区标签
 */
function selectCustomerRow34(areaA){
	var pp=areaA.parent().prev().prev().prev().prev();
	wrapMaskShow();//父级遮罩显示
	 $('body').css('overflow','hidden');//禁止滚动
	 
	 initAddress();	
	 
	 //TODO 数据回显,获得需回显的小区 OK
	  var  oreqS=pp.next().find(".oreqS").val().trim();//小区机构编号
	  var areaNames=pp.next().next().next().text(); 
	  console.log(areaNames);
	    if(oreqS){
	    	var org_list=oreqS.split(",");
	    	var raea_Names=areaNames.split(",");
	    	for(var i=0;i<org_list.length;i++){
	    		var area_info={};  //小区信息
	    		area_info.oreq=org_list[i];
	    		area_info.areaName=raea_Names[i];
	    		select_cell_list.push(area_info);
	    	}
	    	selectCellListDeWeight();
	    }
	 
	 init();
	 
	 $("#infoDg").datagrid('load',
			  {
		  		pageNo:1
		  		}); 
	 
	 $('#select_Area').dialog({
	        width:1000,
	        height:575,
	        modal:true,
	        collapsible:false,
	        minimizable:false,
	        maximizable:false,
	        closable:true,
	        draggable:true,
	        resizable:false,
	        inline:false,
	        title:"选择小区",
	        ok:"确定",
	        cancel:"取消",
	        buttons:[{
	            text:'取消',
	            handler:function(){
	                wrapMaskHide();
	                
	                select_cell_list=[];
	                page_num=1;
	                
	                $("#provinceCode").val("");
	            	$("#cityCode").val("");
	            	$("#countryCode").val("");
	            	$("#communityName").val("");	           
	                //将三级联动至于 请选择
	            	$("#province").combobox("select","");
	                $('#select_Area').dialog('close');
	                	                	               	                
	            }
	        },{
	            text:'确认',
	            handler:function(){
	                wrapMaskHide();
	                var areaDeault={};	               
	                areaDeault=getAreaDeault();
	                
	                //组织机构编号
            		pp.next().find(".oreqS").val(areaDeault.organizationSeqs);           		
            		//小区名称
            		//console.log(areaDeault.areaNames);
            		pp.next().next().next().text(areaDeault.areaNames);
	                
	                //清空
	                select_cell_list=[];
	                page_num=1;
	                
	                $("#provinceCode").val("");
	            	$("#cityCode").val("");
	            	$("#countryCode").val("");
	            	$("#communityName").val("");
	                //将三级联动至于 请选择
	            	$("#province").combobox("select","");
	                $('#select_Area').dialog('close');
	                	              
	            }
	        }],

	        onClose : function(){
	        	
	        	select_cell_list=[];
                page_num=1;
                
                //将三级联动至于 请选择
            	$("#province").combobox("select","");
	        	
	        	$("#provinceCode").val("");
	        	$("#cityCode").val("");
	        	$("#countryCode").val("");
	        	$("#communityName").val("");
	            wrapMaskHide();//父级遮罩隐藏
	            $('body').css('overflow','auto');//恢复滚动
	        }
	    });
	    winHiSelfAdaptation($('#select_Area'));//弹窗定位
}

/**
 * 用户默认小区日期时间控件
 * startTimeD:开始时间
 * endTimeD:结束时间
 */
var windowTimeTip=false;
function dateBoxInitDeault(startTimeD,endTimeD,b_type){
	var now = new Date();
	var nextMonth = new Date();
	nextMonth.setMonth(now.getMonth() + 1);//初始化的时候，开始时间与结束时间相差一个月

	function formatDate(date){
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		
		//getHours() 	设置 Date 对象中的小时 (0 ~ 23)。
		//getMinutes() 	设置 Date 对象中的分钟 (0 ~ 59)。
		//getSeconds() 	设置 Date 对象中的秒钟 (0 ~ 59)。
		
		var hours=date.getHours() > 9 ? date.getHours() : "0" + date.getHours();
		var minutes=date.getMinutes() > 9 ? date.getMinutes() : "0" + date.getMinutes();
		var seconds=date.getSeconds() > 9 ? date.getSeconds() : "0" + date.getSeconds();
		if(b_type==1){
			return date.getFullYear() + '-' + month + '-' + day+" "+hours+":"+minutes+":"+seconds;
		}else{
			return date.getFullYear() + '-' + month + '-' + day;
		}		
	}

	function onChangeDateBox(newValue, oldValue){
		var beginTime;
		var endTime;
		if(b_type==1){
			beginTime = startTimeD.datetimebox('getValue');
		    endTime = endTimeD.datetimebox('getValue');
					   		    
		}else{
			beginTime = startTimeD.datebox('getValue');
		    endTime = endTimeD.datebox('getValue');
		}		
		/*console.log("前："+beginTime);
		console.log("后："+endTime);*/
		var o = $(this);
		if(beginTime && endTime && beginTime > endTime){	
			if(b_type==1){
				if(!windowTimeTip){
					windowTimeTip=true;
					infoMask('开始日期必须小于结束日期!');
					setTimeout('windowTimeTip=false',500);
				}
				$(this).datetimebox('setValue',oldValue);
				 
			}else{
				infoMask('开始日期必须小于等于结束日期!');
				 $(this).datebox('setValue',oldValue);	
			}			
			return;
		}else{
		    if(beginTime&&endTime&&beginTime.trim()!=""&&endTime.trim()!=""){
		    	//console.log("后："+b_type);
				//2.煤管
				if(b_type==2){
					//console.log("2："+b_type);
					
					$(".project2").each(function(){
						
						//按钮不选中
						$(this).find(".checkbox-icon").removeClass(".checkbox-bg");
						//按钮可操作   
						if(!$(this).find("input[type='checkbox']").parent().is(".checkbox-icon")){
							$(this).find("input[type='checkbox']").parent().addClass("checkbox-icon");
					    }						
			                //$(this).parent().removeClass("checkbox-bg");
			            $(this).find("input[type='checkbox']").parent().removeClass("readonly-bg");
						
						//时间变更
						$(this).next().find(".startTime").datebox("setValue",$("#startTime2").datebox("getValue"));
						$(this).next().find(".endTime").datebox("setValue",$("#endTime2").datebox("getValue"));
						//组织机构编号清空
						$(this).next().find(".oreqS").val("");
						//已选小区清空
						$(this).next().next().next().text("");
					});
				}
				if(b_type==3){
					$(".project3").each(function(){						
						//时间变更
						$(this).next().find(".startTime").datebox("setValue",$("#startTime3").datebox("getValue"));
						$(this).next().find(".endTime").datebox("setValue",$("#endTime3").datebox("getValue"));
					});
				}
				if(b_type==4){
					$(".project4").each(function(){						
						//时间变更
						$(this).next().find(".startTime").datebox("setValue",$("#startTime4").datebox("getValue"));
						$(this).next().find(".endTime").datebox("setValue",$("#endTime4").datebox("getValue"));
					});
				}
			}	
		}
		
		
	}

	function validatorDate(date){
		var now = new Date();
		var nowDate = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		return nowDate<=date;
	}



//	日起控件
	if(b_type==1){
		//格式化时间
		startTimeD.datetimebox({
		//value : formatDate(now),
			editable:false,
			formatter:formatDate,
			onChange: onChangeDateBox
		});

		//格式化时间
		endTimeD.datetimebox({
			//value : formatDate(nextMonth),
			editable:false,
			formatter:formatDate,
			onChange: onChangeDateBox
		});
	}else{
		//格式化时间
		startTimeD.datebox({
		//value : formatDate(now),
			editable:false,
			formatter:formatDate,
			onChange: onChangeDateBox
		});

		//格式化时间
		endTimeD.datebox({
			//value : formatDate(nextMonth),
			editable:false,
			formatter:formatDate,
			onChange: onChangeDateBox
		});
	}
	
	/*startTimeD.datebox('calendar').calendar({
		validator: function(date){
			return validatorDate(date);
		}
	});

	endTimeD.datebox('calendar').calendar({
		validator: function(date){
			return validatorDate(date);
		}
	});*/
}


/**
 * 这个控件只针对列表里的时间
 * 如：class="project2" 对应的元素，jQuery元素
 * b_type:类型 2-煤管.....
 */
//日期控件
function dateBoxInit(pp,b_type){
	var startT=pp.next().find('.startTime');  //开始时间插件
	var endT=pp.next().find('.endTime'); //结束时间插件
	
	var now = new Date();
	var nextMonth = new Date();
	nextMonth.setMonth(now.getMonth() + 1);//初始化的时候，开始时间与结束时间相差一个月

	function formatDate(date){
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
		+ (date.getMonth() + 1);
		return date.getFullYear() + '-' + month + '-' + day;
	}
  
	function onChangeDateBox(newValue, oldValue){
		
		var beginTime =startT.datebox('getValue');
		var endTime = endT.datebox('getValue');
		var o = $(this);
		
		if(beginTime && endTime && beginTime > endTime){
			infoMask('开始日期必须小于等于结束日期!');
			$(this).datebox('setValue',oldValue);
			return;
		} 
		if(beginTime&&endTime&&beginTime.trim()!=null&&endTime.trim()!=null){
			//2.煤管
			if(b_type==2){
				pp.find("input[type='checkbox']").parent().removeClass("readonly-bg");
				if(!pp.find("input[type='checkbox']").parent().is(".checkbox-icon")){
				  pp.find("input[type='checkbox']").parent().addClass("checkbox-icon");
				}
									
				//按钮设置为不选状态
				pp.find("input[type='checkbox']").parent().removeClass(".checkbox-bg");
				//清空选择的小区,组织机构编号
				pp.next().find('.oreqS').val("");
				pp.next().next().next().text("");
			}
			if(b_type==3||b_type==4){				
				$.ajax({
			        url: "../productMenu/findAddNumsByMeanIdAndBeginTime.html",
			        type:"POST",
			        data:{
			        	meanId:pp.next().find('.meanId').val(),
			        	beginTime:beginTime
			        },
			        success: function (data) {	 
			        	var result=JSON.parse(data);
			            if(true == result.success){ 
			            	//console.log("剩余次数:"+result.obj);   
			            	pp.next().find('.meanId').val();
			            	addNum[pp.next().find('.meanId').val()]=result.obj;
			            	if(result.obj<=0){
			            		//将按钮置为不可选			            	
			            		//设置不可用 		    
			            		pp.find("input[type='checkbox']").parent().removeClass("checkbox-icon");
			            		pp.find("input[type='checkbox']").parent().removeClass("checkbox-bg");
								if(!$(this).find("input[type='checkbox']").parent().is(".readonly-bg")){
									pp.find("input[type='checkbox']").parent().addClass("readonly-bg");
								}								  
				            }else{
				            	pp.find("input[type='checkbox']").parent().removeClass("readonly-bg");
				            	if(!pp.find("input[type='checkbox']").parent().is(".checkbox-icon")){
				            		pp.find("input[type='checkbox']").parent().addClass("checkbox-icon");
				            	}								
				            	//pp.find("input[type='checkbox']").parent().removeClass("checkbox-bg");						
				            }
				        }
			        },
			        error: function () {
			        	nfoMask('服务器异常' );
			        },
			        complete : function (XHR, TS){
			            showShade(false);
			        }
			       });
			}
		}
	}

	function validatorDate(date){
		var now = new Date();
		var nowDate = new Date(now.getFullYear(), now.getMonth(), now.getDate());
		return nowDate<=date;
	}

//	日起控件
	//格式化时间
	startT.datebox({
	//value : formatDate(now),
		editable:false,
		formatter:formatDate,
		onChange: onChangeDateBox
	});

	//格式化时间
	endT.datebox({
		//value : formatDate(nextMonth),
		editable:false,
		formatter:formatDate,
		onChange: onChangeDateBox
	});
	/*startT.datebox('calendar').calendar({
		validator: function(date){
			return validatorDate(date);
		}
	});
	console.log("44444444");
	endT.datebox('calendar').calendar({
		validator: function(date){
			return validatorDate(date);
		}
	});*/
}

// 添加小区


/**
 * 三级弹框联动
 */
function initAddress(){
	var cityAreaTownInitValue=[
	                           {
	                               "id":0,
	                               "code":"",
	                               "name":"请选择",
	                               "parentId":"",
	                               "selected":true
	                           }
	 ];
	 $('#city,#country').combobox({
		 	width:100,
		 	height:30,
	        limitToList:true,
	        data:cityAreaTownInitValue,
	        valueField:'code',
	        textField:'name',
	        editable:false,
	        panelHeight:"auto"
	    });
	
	//省份的数据初始化,4级联动
	$('#province').combobox({
        url:'/ums/communityAction!initProvince.html',
        method:'post',
        value : '请选择',
        valueField:'code',
        textField:'name',
        editable:false,
        onSelect:function(newValue){
        	provinceCode = newValue.code;
        	$("#provinceCode").val(provinceCode);
        	$("#cityCode").val("");
        	$("#countryCode").val("");
            $('#city').combobox({
                url:'/ums/communityAction!getChildSelectData.html?code='+provinceCode,
                method:'post',
                value : '请选择',
                valueField:'code',
                textField:'name',
                onSelect:function(newValue){
                	cityCode = newValue.code;
                	$("#cityCode").val(cityCode);
                	$("#countryCode").val("");
                	$('#country').combobox({
            	        url:'/ums/communityAction!getChildSelectData.html?code='+cityCode,
            	        method:'post',
            	        value : '请选择',
            	        valueField:'code',
            	        textField:'name',
            	        onSelect:function(newValue){
            	        	countryCode = newValue.code;
            	        	$("#countryCode").val(countryCode);           	        	
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
	
}


//公共分页方法 带元素ID、 页数、 selectPage方法、s按钮数防止弹窗宽度不够
function comPagination(Id,pagesize,selectPage,s){
//	按钮数不带默认10个
	var links = 0;
	if( s == undefined ){
		links = 10;
	}else{
		links = s;
	}
	$(Id).pagination({
		layout:['first','prev','links','next','last','manual'],
		pageSize:pagesize,
		links:links,
		onSelectPage:selectPage
	})
}

//分页按钮根据数据显示或隐藏 带分页选择器ID，返回的列表数据
function pageBtnShowOrHide(selector,data){
	if(data.total == 0 || data.total == undefined){
		$(selector).find('table').hide();
		$(selector).css('height','36px');
	}else{
		$(selector).find('table').show();
	}
}




//点击返回立项
function getBackProject(){
	window.location.href="../projectProduct/backAddProject.html";
}

/**
 * 默认小区
 */
function selectArea(){
	wrapMaskShow();//父级遮罩显示
	 $('body').css('overflow','hidden');//禁止滚动
	 
	 initAddress();	
	 
	 init();
	 
	//对于煤管选择时间
	 if(business_type==2){
		 if($("#startTime2").val().trim()==""||$("#endTime2").val().trim()==""){
			 infoMask("默认时间不能为空");
			 wrapMaskHide();//父级遮罩隐藏
	         $('body').css('overflow','auto');//恢复滚动
			 return;
		 }
	 }
	 
	 $("#infoDg").datagrid('load',
			  {
		  		pageNo:1
		  		}); 
	 
	 $('#select_Area').dialog({
	        width:1000,
	        height:575,
	        modal:true,
	        collapsible:false,
	        minimizable:false,
	        maximizable:false,
	        closable:true,
	        draggable:true,
	        resizable:false,
	        inline:false,
	        title:"选择小区",
	        ok:"确定",
	        cancel:"取消",
	        buttons:[{
	            text:'取消',
	            handler:function(){
	                wrapMaskHide();
	                
	                select_cell_list=[];
	                page_num=1;
	                
	                $("#provinceCode").val("");
	            	$("#cityCode").val("");
	            	$("#countryCode").val("");
	            	$("#communityName").val("");	           
	                //将三级联动至于 请选择
	            	$("#province").combobox("select","");
	                $('#select_Area').dialog('close');
	                	                	               	                
	            }
	        },{
	            text:'确认',
	            handler:function(){
	                wrapMaskHide();	
	               /* for(var i=0;i<select_cell_list.length;i++){
						console.log(select_cell_list[i].oreq+":"+select_cell_list[i].areaName);
					}*/
	                
	                var areaDeault={};
	                //获取选择的小区
	                if(business_type!=1){
	                	areaDeault=getAreaDeault();
	                }
	                
	                //1
	                
	               switch (business_type+"") {
					case '1':						
						selectCustomer1();
						break;
					case  '2':
						selectCustomer2(areaDeault);
						break;
					case '3':
						selectCustomer3And(areaDeault,3);
						break;
					case '4':
						selectCustomer3And(areaDeault,4);
						break;
					default:
						break;
					}
	                
	                
	                
	                //清空
	                select_cell_list=[];
	                page_num=1;
	                
	                $("#provinceCode").val("");
	            	$("#cityCode").val("");
	            	$("#countryCode").val("");
	            	$("#communityName").val("");
	                //将三级联动至于 请选择	
	            	$("#province").combobox("select","");
	                $('#select_Area').dialog('close');
	                	              
	            }
	        }],

	        onClose : function(){
	        	
	        	select_cell_list=[];
                page_num=1;
                
                //将三级联动至于 请选择
            	$("#province").combobox("select","");
	        	
	        	$("#provinceCode").val("");
	        	$("#cityCode").val("");
	        	$("#countryCode").val("");
	        	$("#communityName").val("");
	            wrapMaskHide();//父级遮罩隐藏
	            $('body').css('overflow','auto');//恢复滚动
	        }
	    });
	    winHiSelfAdaptation($('#select_Area'));//弹窗定位
}

/**
 * 点击(针对所有)选择小区按钮:获得小区机构编号,小区名称等
 */
function getAreaDeault(){
 	var organizationSeqs=[];
 	var areaNames=[];
 	
 	for(var i=0;i<select_cell_list.length;i++){
 		//TODO 保存 OK
 		organizationSeqs.push(select_cell_list[i].oreq);
 		areaNames.push(select_cell_list[i].areaName);
 	}
 	
 	var a={};
 	a.organizationSeqs=organizationSeqs.join();
 	a.areaNames=areaNames.join();
 	return a;
}

/**
 * 初始化表格:默认小区
 */
function init(){	
	console.log(business_type)
	 $('#infoDg').datagrid({
			collapsible:true,
			emptyMsg:'<span>无记录</span>',		    
			method:'get',
			scrollbarSize:0,
			url:"../projectProduct/getCommunityList.html",			
	        nowrap:false,//允许换行
	        fitColumns:true,//宽度自适应
	        singleSelect:business_type==1?true:false ,  //如果为true，则只允许选择一行
	        onBeforeLoad:function(){
	        	
	        },
	       onSelect:function(rowIndex, rowData){
    			//TODO OK
    			var area_info={};
    			area_info.oreq=rowData.organizationSeq;
    			area_info.areaName=rowData.areaName;
 	        	select_cell_list.push(area_info); 	    
 	        	selectCellListDeWeight(); 	 	        	
 	        },
 	        onUnselect:function(rowIndex, rowData){
 	        	selectCellListDelete(rowData.organizationSeq);
 	        },
 	        onUnselectAll:function(rows){
 	        	for(var i=0;i<rows.length;i++){
					selectCellListDelete(rows[i].organizationSeq);
				}
 	        },
	        onSelectAll:function(rows){
	        	for(var i=0;i<rows.length;i++){
					var area_info={};
	    			area_info.oreq=rows[i].organizationSeq;
	    			area_info.areaName=rows[i].areaName;
	 	        	select_cell_list.push(area_info);
				}
				selectCellListDeWeight();
	        },
			onLoadSuccess : function(data) {// Fires  when data is	
				for(var i=0;i<select_cell_list.length;i++){
					console.log(select_cell_list[i].oreq+":"+select_cell_list[i].areaName);
				}
				//对选中元素进行回显
				if(business_type!=1){
					for(var j=0;j<data.rows.length;j++){
						for (var i=0;i<select_cell_list.length;i++) {
						     if(select_cell_list[i].oreq==data.rows[j].organizationSeq){
						    	 console.log("哈哈哈")
						     	$("#infoDg").datagrid("selectRow",j);
						     	select_cell_list[i].areaName=data.rows[j].areaName;
						     	break;
						     }							
						}
					}	
				}
				
				page_num=data.page;
	        	pageBtnShowOrHide('#infoPp',data);	        	
				$('#infoPp').pagination('refresh', {
					total : data.total,
					pageNumber : data.page
				});
				 $('#infoDg').datagrid('resize');
				 
				 
				 inputStyle();
				 
				 setTimeout(function(){
					 $(".datagrid-row,.datagrid-header-check").click(function(){
					    setTimeout(function(){
					        inputStyle();
					    },100)
					});
				 },10);
				 
			},
			onLoadError:function(){
				$('#infoDg').datagrid('resize');
	        },	       
	        onClickRow: function(rowIndex, rowData){
	        	if(business_type==1){
		            //加载完毕后获取所有的checkbox遍历
		            var radio = $("input[name='organizationSeq1']")[rowIndex].disabled;
		            //如果当前的单选框不可选，则不让其选中
		            if (radio!= true) {
		                //让点击的行单选按钮选中
		                $("input[name='organizationSeq1']")[rowIndex].checked = true;
		            }
		            else {
		                $("input[name='organizationSeq1']")[rowIndex].checked = false;
		            }
	        	}
	        },
			columns : [ [
			{
				field : 'province',
				width : 50,	
				title:"",
				hidden:business_type==1?false:true,
				align : 'center',
				formatter: function (value, row, index) {  
	                  return "<input name='organizationSeq1' type='radio' value='"+row.organizationSeq+"'/> ";  
	            }  
			},         
			{
				field : 'organizationSeq',
				title:"选择",
				align : 'center',
				checkbox:business_type==1?false:true,
				hidden:business_type==1?true:false				
			},
			{
				field : 'cityName',
				title : '城市',
				width : 100,
				halign : 'center',
				align : 'center'
			},              
			{
				field : 'countryName',
				title : '区域',
				width : 100,
				halign : 'center',
				align : 'center'
			}, {
				field : 'areaName',
				title : '楼盘名称',
				width : 100,
				halign : 'center',
				align : 'center'
			}, {
				field : 'town',
				title : '小区编码',
				width : 120,
				halign : 'center',
				align : 'center',
				formatter:function(value,row,index){
					return row.organizationSeq;
				}
			}, {
				field : 'areaCategory',
				title : '小区类型',
				width : 80,
				halign : 'center',
				align : 'center',
				formatter:function(value,row,index){
					
						switch (value) {
						case "0":							
							return "测试小区";
							break;
	                    case "1":	
	                    	return "云对讲项目";
							break;
						case "2":
							return "工程项目";
							break;
	                    case "3":	
	                    	return "样板演示小区";
							break;						
						default:
							break;
						}
					  }
			    }, {
				field : 'translateStatus',
				title : '转化情况',
				width : 80,
				halign : 'center',
				align : 'center',
				formatter:function(value,row,index){
					switch (value) {
					case "0":
						return "未转化";
						break;
                    case "1":	
                 	return "已完成转化";
						break;
					case "2":
						return "部分转化(试点转化)";
						break;                    					
					default:
						break;
					}
				  }
	        	},{
					field : 'householdNum',
					title : '实际户数',
					width : 80,
					halign : 'center',
					align : 'center'
		        },{
					field : 'areaAddress',
					title : '地址',
					width : 170,
					halign : 'center',
					align : 'center'
		        }
			 ] ]
		});
		// 分页
		comPagination('#infoPp',10,infoPpSelectPage);
}

//搜索按钮
function detailSearch(){
    page_num=1;	
	var provinceCode=$("#provinceCode").val();
	var cityCode=$("#cityCode").val()=="请选择"||$("#cityCode").val()=="0086"?"":$("#cityCode").val();
	var countryCode=$("#countryCode").val()=="请选择"||$("#countryCode").val()=="0086"?"":$("#countryCode").val();
	var communityName=$("#communityName").val();
	$("#infoDg").datagrid("reload", {
		pageNo:1,
		province:provinceCode,
		city:cityCode,
		country:countryCode,
		areaName:communityName,
		id:meadId,
		beginTime:beginTimeM,
		endTime:endTimeM
	});
} 

/**
 * select_cell_list 变量去重
 */
//TODO OK
function selectCellListDeWeight(){
	var temp=[];
	var falgC=true;
	for(var i = 0; i < select_cell_list.length; i++){
        for(var j=0;j<temp.length;j++){
        	if(temp[j].oreq==select_cell_list[i].oreq){
        		falgC=false;
        		break;
        	}
        }
        if(falgC){
        	temp.push(select_cell_list[i]);
        }else{
        	falgC=true;
        }
    }
	select_cell_list=temp;
}
/**
 * select_cell_list 删除指定项
 * 参数:组织机构编号
 */
function selectCellListDelete(oreq){
	for(var i=0;i<select_cell_list.length;i++){
		if(select_cell_list[i].oreq==oreq){
			select_cell_list.splice(i,1); break;
		}
	}
}

var select_cell_list=[];  //保存组织机构编号,小区名称  里面的对象 属性 oreq：机构编号,areaName:小区名称
var page_num=1;
//详情的表格分页
function infoPpSelectPage(pageNo, pageSize){	
	var provinceCode=$("#provinceCode").val();
	var cityCode=$("#cityCode").val()=="请选择"||$("#cityCode").val()=="0086"?"":$("#cityCode").val();
	var countryCode=$("#countryCode").val()=="请选择"||$("#countryCode").val()=="0086"?"":$("#countryCode").val();
	var communityName=$("#communityName").val();
	//console.log("哈哈哈哈哈哈");
	var start = (pageNo - 1) * pageSize; 
    var end = start + pageSize; 	
    $("#infoDg").datagrid("reload", {
		pageNo:pageNo,
		province:provinceCode,
		city:cityCode,
		country:countryCode,
		areaName:communityName,
		id:meadId,
		beginTime:beginTimeM,
		endTime:endTimeM
	}); 
}


/*
显示遮罩
*/
function showShade(show , text){
   if(true == show){
       if(text){
           $('.shadeBox .tipsInfo p').html(text);
       }     	
	    wrapMaskShow();//父级遮罩显示
	    $('body').css('overflow','hidden');//禁止滚动	    
        $('.shadeBox').show();
   }else{
       wrapMaskHide();//父级遮罩隐藏
       $('body').css('overflow','auto');//恢复滚动
       $('.shadeBox').hide();
   }

}

