//var localhost = 'http://192.168.51.4:8080/';//本地调试
var localhost = '/';//正式
var selNum = 0;//小区选中数量
var total = 0;//小区总数
$(function() {
	var windowheight = $(window).height();
	var tableheight = 0;
	if(windowheight<500){
		tableheight = 255;
	}else{
		tableheight = 355;
	}
	//表格初始化
	$('#communityDg').datagrid({
		height:tableheight,
		singleSelect: false,
		collapsible: true,
		emptyMsg: '<span>无记录</span>',
		method: 'post',
		scrollbarSize: 0,
		onLoadSuccess: onLoadSuccess,
		onCheckAll: function() {
			bd.boxCheck();
			$('#selNum').text(selNum = total);
		},
		onCheck: function() {
			bd.boxCheck();
			$('#selNum').text(++selNum);
		},
		onUncheckAll: function() {
			bd.boxUnCheck();
			$('#selNum').text(selNum = 0);
		},
		onUncheck: function() {
			bd.boxUnCheck();
			$('#selNum').text(--selNum);
		}
	});
	var allCommunitysUrl = '';
	//获取所有小区
	var citys = bd.getIds('.arealist .delPic');
	var allCommunitysUrl = localhost+'grms/common/communitys?province=&citys='+citys+'&country=&keyword=';

	$.ajax({
		type: "get",
		url: allCommunitysUrl,
		dataType: 'json',
		contentType: 'application/json;charset=UTF-8',
		async: false,
		success: function(data) {
//			console.log(data);
			if(data.code == 0) {
				total = data.total;
				$('#total').text(total);
				$('#communityDg').datagrid('loadData', data.data);//填入表格
			}
		},
		error:function(xhr,txt,e){
			bd.alert('提示','服务器响应异常！');
		}
	});
	initSelt();//初始化下拉框
});
//  初始化下拉框
function initSelt() {
	var cityAreaTownInitValue = [{//市、区默认值
		"id": "",
		"text": "请选择",
		"selected": true
	}];
	$('#city_selt,#area_selt').combobox({
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
//	console.log(provinces)
//	console.log('111' + comboData)
	if(myProvinces.length == 0) { //没有选市，省空
		var provincesUrl = localhost+'grms/common/districts/provinces';
//		获取全部省
		$.ajax({
			type: "get",
			url: provincesUrl,
			dataType: 'json',
			contentType: 'application/json;charset=UTF-8',
			async: false,
			success: function(data) {
//				console.log(data)
				if(data.code == 0) {
					var shengdata = data.data;
					for(var i = 0; i < shengdata.length; i++) {//处理数据格式
						comboData.push({
							id: shengdata[i].code,
							text: shengdata[i].name
						})
					};
					if(comboData.length<=10){
						$('#province_selt').combobox({
							panelHeight: 'auto'
						})
					}
					$('#province_selt').combobox({
						data: comboData,//省数据填入下拉框
						onChange: function(newValue, oldValue) {
							var comboCitysData = [{
								text:'请选择',
								id:''
							}]; //市
							$('#city_selt').combobox('select','');
//							获取市
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
																		data: comboCountrysData//填入区数据
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
//		console.log(comboData);
		$('#province_selt').combobox({
			data: comboData,//填入省
			onChange: function(newValue, oldValue) {
//				console.log(newValue+'---'+oldValue)
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
//				console.log(comboCitysData);
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
//							console.log(newValue)
//							获取对应区
							$.ajax({
								type: "get",
								url: countrysUrl,
								dataType: 'json',
								contentType: 'application/json;charset=UTF-8',
								async: false,
								success: function(data) {
//										console.log(data)
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
											data: comboCountrysData//填入区数据
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
//  筛选
function filtrate(value) {
	var provinceVal = $('#province_selt').combobox('getValue');
	if(provinceVal == '请选择') {
		provinceVal = '';
	}
	var cityVal = $('#city_selt').combobox('getValue');
	if(cityVal == '请选择') {
		cityVal = '';
	}
	var areaVal = $('#area_selt').combobox('getValue');
	if(areaVal == '请选择') {
		areaVal = '';
	}
	var keyword = $('#keyword').searchbox('getValue');

	var filtUrl = localhost+'grms/common/communitys?province=' + provinceVal + '&citys=' + cityVal + '&country=' + areaVal + '&keyword=' + keyword;
	$.ajax({
		type: "get",
		url: filtUrl,
		dataType: 'json',
		contentType: 'application/json;charset=UTF-8',
		async: false,
		success: function(data) {
//			console.log(data);
			if(data.code == 0) {
				total = data.total;
				$('#total').text(total);
				$('#communityDg').datagrid('loadData', data.data);
			}
		},
		error:function(xhr,txt,e){
			bd.alert('提示','服务器响应异常！');
		}
	})
}
//表格加载成功
function onLoadSuccess() {
//	console.log('选中的小区=' + communitys);
//		var index = $('#communityDg').datagrid('getRowIndex',communitys[j])//不知道为啥这种拿不到row的index
	var allCommunitys = $('#communityDg').datagrid('getRows'); //所有小区
	$('#communityDg').datagrid('uncheckAll');
	for(var i = 0; i < communitys.length; i++) { //循环选中的小区
		for(var j = 0; j < allCommunitys.length; j++) { //循环所有小区
//			console.log(allCommunitys[j].organizationSeq+'--'+communitys[i])
			if(allCommunitys[j].organizationSeq == communitys[i]) { //选中的小区==所有小区中的某个
				var index = $('#communityDg').datagrid('getRowIndex', allCommunitys[j]); //只能这样拿index
				$('#communityDg').datagrid('selectRow', index); //勾选
//				console.log(index)
			}
		}
//			index.push($('#communityDg').datagrid('getRowIndex',communitys[i]));
	}
	
//另一线程延迟加载复选框样式
	setTimeout(function() {
		bd.loadSpan();
		bd.boxCheck();
		bd.boxUnCheck();
	//	全选按钮勾上
		var total = $('#total').text();
		var selNum = $('#selNum').text();
		if(total!=0&&total == selNum){
			$('#communityDg').datagrid('checkAll');
		}
	}, 0)
}

//	选中小区确定写到一级页面
function communitySure() {
	//		communityIds = [];//存放选中小区id
	//		communityNames = []; //存放选中小区名称
	var communityHtml = ''; //一级页面小区标签
	communitys = []; //选中的小区organizationSeq
	var selectNodes = $('#communityDg').datagrid('getSelections');
	if(selectNodes.length == 0) {
		bd.alert('提示', '请选择小区');
	} else {
		for(var i = 0; i < selectNodes.length; i++) {
			//				communityIds.push(communitys[i].itemid);
			//				communityNames.push(communitys[i].productname);
			communityHtml += '<li class="clearfix fl" organizationSeq="' + selectNodes[i].organizationSeq + '""><span class="community fl">' +
			selectNodes[i].areaName + '</span><span class="delPic fl" id="' + selectNodes[i].organizationSeq + '" onclick="communityDelSelf(this)"></span></li>';
			communitys.push(selectNodes[i].organizationSeq); //重新填入选中的小区organizationSeq
		}
		$('.communitylist').html('');
		$('.communitylist').append(communityHtml);
		$('#communityWindow').window('close');
	}

}

//删除小区
function communityDelSelf(e) {
	var id = $(e).attr('id');
	for(var i = 0; i < communitys.length; i++) {
		if(id == communitys[i]) {
			communitys.splice(i, 1); //数组删除
		}
	}
	$(e).parent('li').remove(); //页面删除
}
//窗口大小改变
$(window).resize(function() {
	$('#communityWindow').window('center'); //弹窗居中
})