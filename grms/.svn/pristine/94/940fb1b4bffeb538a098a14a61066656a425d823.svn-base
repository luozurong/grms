var localhost = '/';//正式
var selNum = 0;//小区选中数量
var total = 0;//小区总数
var pageSize=10;
var pageNumber=1;
function initAreaTypeSelect(){
	$('#areaTypeR').combobox({    
	    data:[
				{
					label: '请选择',
					value: ''
				},	          
				{
					label: '云对讲项目',
					value: '1'
				},
				{
					label: '工程项目',
					value: '2'
				},
				{
					label: '样板演示小区',
					value: '3'
				},
				{
					label: '测试小区',
					value: '0'
				}	 				
	    ],
	    valueField:'value',    
	    textField:'label',
	    select:'',
		editable: false,
		panelHeight: "auto"
	});  
}

function resetSearch(){
	$("#province_selt").combobox('setValue','');
	$("#city_selt").combobox('setValue','');
	$("#area_selt").combobox('setValue','');
	$("#town_selt").combobox('setValue','');
	$("#areaTypeR").combobox('setValue','');
	$("input[name='translateStatus']").attr("checked", false);
}

function getQueryParam(){
	var provinceVal = $('#province_selt').val()=='请选择'?"": $('#province_selt').val();
	var cityVal = $('#city_selt').val()=='请选择'?"":$('#city_selt').val() ;
	var areaVal = $('#area_selt').val()=='请选择'?"":$('#area_selt').val();
	var areaCategory =$('#areaTypeR').combobox('getValue');
	var townVal =$('#town_selt').val()=='请选择'?"":$('#town_selt').val();
	var translate = new Array($("input:checked").size());
	$("input[name='translateStatus']:checked").each(function(index,element){
		translate[index]=$(element).val();
	});
	var areaQueryBean = new Object();
	areaQueryBean.province =provinceVal;
	areaQueryBean.city=cityVal;
	areaQueryBean.country=areaVal;
	areaQueryBean.translateStatus=translate;
	areaQueryBean.areaCategory=areaCategory;
	areaQueryBean.town=townVal;
	return areaQueryBean;
}


//	选中小区确定写到一级页面
function communitySure() {
	var communityHtml = ''; //一级页面小区标签
	var queryBean = getQueryParam();
	if(queryBean.province
			|| queryBean.city
			|| queryBean.country
			|| queryBean.translateStatus
			|| queryBean.areaCategory
			|| queryBean.town){
		addRegionCallBackFunction(queryBean);
		$('#communityRegionWindow').window('close');
	}else{
		if(communitys.length==0){
			bd.alert('提示', '请选择小区');
			return;
		}
	}

}



//删除小区
function checkCommunityDelSelf(e,organizationSeq){
	var id = $(e).attr('id');
	for(var i = 0;i<communitys.length;i++){
		if(organizationSeq == communitys[i].organizationSeq){
			communitys.splice(i,1);//数组删除
		}
	}
	$(e).parent('li').remove();//页面删除
}


//初始化下拉框
function initSelt() {
var cityAreaTownInitValue = [{//市、区默认值
	"id": "",
	"text": "请选择",
	"selected": true
}];
$('#city_selt,#area_selt,#town_selt').combobox({
	height: 34,
	width: 120,
	limitToList: true, //输入的值只能是列表框中的内容
	data: cityAreaTownInitValue,
	valueField: 'id',
	textField: 'text',
	editable: false,
	panelHeight: "auto",
	value: '请选择'
});

$('#province_selt').combobox({
	height: 34,
	width: 120,
	editable: false,
	valueField: 'id',
	textField: 'text',
	//  		data:comboData,
	value: '请选择'

})
var comboData = [{
	text:'请选择',
	id:''
}]; //省
var myProvinces = [];
myProvinces = provinces
	//		comboData = provinces;
//console.log(provinces)
//console.log('111' + comboData)
if(myProvinces.length == 0) { //没有选市，省空
	var provincesUrl = localhost+'grms/common/districts/provinces';
//	获取全部省
	$.ajax({
		type: "get",
		url: provincesUrl,
		dataType: 'json',
		contentType: 'application/json;charset=UTF-8',
		async: false,
		success: function(data) {
//			console.log(data)
			if(data.code == 0) {
				var shengdata = data.data;
				for(var i = 0; i < shengdata.length; i++) {//处理数据格式
					comboData.push({
						id: shengdata[i].code,
						text: shengdata[i].name
					})
				};
				if(comboData.length<=10){$('#province_selt').combobox({panelHeight: 'auto'})}
				$('#province_selt').combobox({
					data: comboData,//省数据填入下拉框
					onChange: function(newValue, oldValue) {
						var comboCitysData = [{
							text:'请选择',
							id:''
						}]; //市
						$('#city_selt').combobox('select','');
//						获取市
						if(newValue!=''){
							var citysUrl = localhost+'grms/common/districts/' + newValue + '/citys';
							$.ajax({
								type: "get",
								url: citysUrl,
								dataType: 'json',
								contentType: 'application/json;charset=UTF-8',
								async: false,
								success: function(data) {
//									console.log(data)
									if(data.code == 0) {
										var shidata = data.data;
										for(var i = 0; i < shidata.length; i++) {//处理数据格式
											comboCitysData.push({
												id: shidata[i].code,
												text: shidata[i].name
											})
										}
										$('#city_selt').combobox({
											data: comboCitysData,//市数据填入下拉框
											onChange: function(newValue, oldValue) {
												var comboCountrysData = [{
													text:'请选择',
													id:''
												}]; //区
												if(newValue!=''){
													var countrysUrl = localhost+'grms/common/districts/' + newValue + '/countrys';
	//												console.log(newValue)
	//												获取区
													$.ajax({
														type: "get",
														url: countrysUrl,
					//									url: "../dialog/tree_data1.json",
														dataType: 'json',
														contentType: 'application/json;charset=UTF-8',
														async: false,
														success: function(data) {
	//														console.log(data)
															if(data.code == 0) {
																var data = data.data;
																for(var i = 0; i < data.length; i++) {//处理数据格式
																	comboCountrysData.push({
																		id: data[i].code,
																		text: data[i].name
																	})
																}
	//															console.log(comboCountrysData);
																	
																$('#area_selt').combobox({
																	data: comboCountrysData,//填入区数据
																	onChange: function(newValue, oldValue){
																			var comboTownsData = [{
																				text:'请选择',
																				id:''
																			}];
																		if(newValue!=''){
																			var townsUrl = localhost+'grms/common/districts/'+newValue+'/towns';
																			$.ajax({
																				type: "get",
																				url: townsUrl,
																				dataType: 'json',
																				contentType: 'application/json;charset=UTF-8',
																				async: false,
																				success: function(data){
																					var comboTownsData = [{
																						text:'请选择',
																						id:''
																					}];
																					if(data.code == 0) {
																						var data = data.data;
																						for(var i = 0; i < data.length; i++) {//处理数据格式
																							comboTownsData.push({
																								id: data[i].code,
																								text: data[i].name
																							})
																						}
																					}
																					$('#town_selt').combobox({
																						data: comboTownsData,//市数据填入下拉框
																					})
																				}
																			});
																		}	
																	}
																
																})

															}
														},
														error:function(xhr,txt,e){
															bd.alert('提示','服务器响应异常！');
														}
													});
												}else{
													var nullData = [{//联动下拉框默认空数据
														text:'请选择',
														id:''
													}];
													$('#area_selt').combobox({data:nullData});
												}

											}
										})
									}
								},
								error:function(xhr,txt,e){
									bd.alert('提示','服务器响应异常！');
								}
						
							})
						}else{
							var nullData = [{//联动下拉框默认空数据
								text:'请选择',
								id:''
							}];
							$('#city_selt').combobox({data:nullData});
							$('#area_selt').combobox({data:nullData});
						}
					}
				})
			}
		},
		error:function(xhr,txt,e){
			bd.alert('提示','服务器响应异常！');
		}
	});
} else {//如果有选城市，即省非空
	comboData = provinces;
	if(comboData[0].id!=''){
		comboData.unshift({
			text:'请选择',
			id:''
		})
	}
//	console.log(comboData);
	$('#province_selt').combobox({
		data: comboData,//填入省
		onChange: function(newValue, oldValue) {
//			console.log(newValue+'---'+oldValue)
			var comboCitysData = []; //市
			$('#city_selt').combobox('select','');
			if(newValue!=''){
				for(var i = 0; i < comboData.length; i++) {
					if(newValue == comboData[i].id) {//获取选中省对应的城市
						comboCitysData = comboData[i].citys;
						break;
					}
				}
			}
//			console.log(comboCitysData);
			if(comboCitysData.length != 0) {//市非空
				
				if(comboCitysData[0].id!=''){
					comboCitysData.unshift({
				    	text:'请选择',
				 		id:''
					});
				}
			}else{
				comboCitysData = [{
					text:'请选择',
				 	id:''
				}]
			}
			$('#city_selt').combobox({
				data: comboCitysData,//填入市数据
				onChange: function(newValue, oldValue) {
					var comboCountrysData = [{
						text:'请选择',
						id:''
					}]; //区
					if(newValue!=''){
						var countrysUrl = localhost+'grms/common/districts/' + newValue + '/countrys';
//						console.log(newValue)
//						获取对应区
						$.ajax({
							type: "get",
							url: countrysUrl,
							dataType: 'json',
							contentType: 'application/json;charset=UTF-8',
							async: false,
							success: function(data) {
//									console.log(data)
								if(data.code == 0) {
									var data = data.data;
									for(var i = 0; i < data.length; i++) {//处理区数据格式
										comboCountrysData.push({
											id: data[i].code,
											text: data[i].name
										})
									}
//										console.log(comboCountrysData);

									$('#area_selt').combobox({
										data: comboCountrysData,//填入区数据
										onChange: function(newValue, oldValue){
											var comboTownsData = [{
												text:'请选择',
												id:''
											}];
										if(newValue!=''){
											var townsUrl = localhost+'grms/common/districts/'+newValue+'/towns';
											$.ajax({
												type: "get",
												url: townsUrl,
												dataType: 'json',
												contentType: 'application/json;charset=UTF-8',
												async: false,
												success: function(data){
													var comboTownsData = [{
														text:'请选择',
														id:''
													}];
													if(data.code == 0) {
														var data = data.data;
														for(var i = 0; i < data.length; i++) {//处理数据格式
															comboTownsData.push({
																id: data[i].code,
																text: data[i].name
															})
														}
													}
													$('#town_selt').combobox({
														data: comboTownsData,//市数据填入下拉框
													})
												}
											});
										}	
									}
										
									})
								}
							},
							error:function(xhr,txt,e){
								bd.alert('提示','服务器响应异常！');
							}
						});
					}else{
						var nullData = [{//联动下拉框默认空数据
							text:'请选择',
							id:''
						}];
						$('#area_selt').combobox({data:nullData});
					}
				}
			})

		}
	})
}
}

	initSelt();//初始化下拉框
	initAreaTypeSelect();