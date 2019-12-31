/**
 * 社区运营部门专用js
 */

var pageNoAll = '1';
var pageSizeAll = '10';
$(function() {
	configMenuItem("执行管理", "执行清单列表");
	
	var jumpStr = $("#jumpStr").val();
	//console.debug(jumpStr);
	if (jumpStr != null && jumpStr != '') {
		var projectActionQueryBean = JSON.parse(decodeURIComponent(jumpStr))
		$('#provinceCode').val(projectActionQueryBean.province);
		$('#cityCode').val(projectActionQueryBean.city);
		$('#countryCode').val(projectActionQueryBean.country);
		$("#areaName").val(projectActionQueryBean.areaName);
		$('#startTime').datebox('setValue', projectActionQueryBean.startTime);	
		$('#actionStatus').combobox('setValue', projectActionQueryBean.actionStatus);
		pageNoAll = projectActionQueryBean.pageNumber;
		pageSizeAll = projectActionQueryBean.pageSize;
	}
	openSelectedAddress();
	findMenu('', '');
});

function selectAll() {
	pageNoAll = '1';
	$('#pp').pagination({
		pageNumber : 1

	});
	findMenu('', '');
}

//------------------------------时间格式化函数   start--------------------------------------------------------
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()// millisecond  
    }  
    if (/(y+)/.test(format)) {
    	format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    } 
    for (var k in o){
    	if (new RegExp("(" + k + ")").test(format)){
    		format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    	}
    }
    return format;  
}  
function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
}  
//------------------------------时间格式化函数   end--------------------------------------------------------


// 查询
function findMenu(pageSize, pageNo) {
	var provinceCode = $('#provinceCode').val();
	var cityCode = $('#cityCode').val();
	var countryCode = $('#countryCode').val();
	var areaName = $('#areaName').val();// 小区名称
	var startTime = $('#startTime').datebox('getValue');// 执行日期(执行开始时间)
	var actionStatus = $('#actionStatus').combobox('getValue');// 执行清单状态
	
	pageNo = pageNoAll;
	pageSize = pageSizeAll;
	var projectActionQueryBean = '{"province":"' + provinceCode + '","city":"'
	+ cityCode + '","country":"' + countryCode + '","areaName":"' + areaName + '","startTime":"' + startTime 
	+ '","actionStatus":"' + actionStatus +'","pageNumber":"' + pageNo + '","pageSize":"' + pageSize + '"}';
	
	$.ajax({
		type : "POST", // 提交方式
		url : "/grms/zhongheDepart/sqListNew.html",// 路径
		dataType : "json",// 数据，这里使用的是Json格式进行传输
		data : {"projectActionQueryBean" : projectActionQueryBean},
		success : function(result) {// 返回数据根据结果进行相应的处理
			if (result.success) {
				var data2 = result.rows;
				var total = result.total;
				refresh(data2);
				paginationpage(total, data2);
			} else {
				
			}
		}
	});
}
function refresh(data2) {
	// 表格数据渲染
	$('#dg').datagrid({
		border : true,
		scrollbarSize : 0,
		nowrap : false,// 允许换行
		data : data2,
		fitColumns : true,// 宽度不自适应
		emptyMsg : '<span>无记录</span>',
		onLoadSuccess : function() { // dom操作
			$('#dg').datagrid('resize');
			var dataHeight = $(".datagrid-view").height() - 19;
			$(".datagrid-view").css("height", dataHeight);
		},
		columns : [ [
				{
					field : 'projectProductId',
					title : '项目清单主键id',
					width : 220,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'beginTime',
					title : '执行时间',
					width : 190,
					align : 'center',
					formatter : formatDatebox
				},
				{
					field : 'areaName',
					title : '小区名称',
					width : 280,
					align : 'center'

				},
				{
					field : 'province',
					title : '省份编码',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'provinceName',
					title : '省份',
					width : 190,
					align : 'center'
				},
				{
					field : 'city',
					title : '城市编码',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'cityName',
					title : '城市',
					width : 190,
					align : 'center'
				},
				{
					field : 'country',
					title : '区（县）编码',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'countryName',
					title : '区（县）',
					width : 190,
					align : 'center'
				},
				{
					field : 'areaAddress',
					title : '详细地址',
					width : 280,
					align : 'center'
				},
				{
					field : 'businessType',
					title : '执行组织',
					width : 190,
					align : 'center',
					formatter : function(value) {
						if (value == 1) {
							return "社区运营";
						} else if (value == 2) {
							return "媒管";
						} else if (value == 3) {
							return "用户运营";
						} else if (value == 4) {
							return "电商运营";
						}
					}
				},
				{
					field : 'projectActionId',
					title : '执行清单主键id',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'actionCode',
					title : '执行清单编码',
					width : 190,
					align : 'center',
					hidden : 'true'
				},
				{
					field : 'projectName',
					title : '项目名称',
					width : 250,
					align : 'center'
				},
				{
					field : 'fieldName',
					title : '场次名称',
					width :250,
					align : 'center'
				},
				{
					field : 'actionStatus',
					title : '执行状态',
					width : 180,
					align : 'center',
					formatter : function(value) {
						if (value == 1) {
							return "待确认";
						} else if (value == 2) {
							return "策划中";
						} else if (value == 3) {
							return "待执行";
						} else if (value == 4) {
							return "执行中";
						} else if (value == 5) {
							return "已完成";
						}
					}
				},
				{
					field : 'exceptionStatus',
					title : '清单情况',
					width : 180,
					align : 'center',
					formatter : function(value) {
						if (value == 0) {
							return "<span style='color:red;'>异常</span>";
						} else if (value == 1) {
							return "正常";
						}
					}
				},
				{
					field : 'handle',
					title : '操作',
					width : 574,
					align : 'center',
					formatter : function(value, row, index) {
						var actionCode = row.actionCode;// 执行清单code
						var hrefUrl = "/grms/zhongheDepart/actionDetail.do?actionCode="+actionCode+"&optType=1";
					    var str = "";
					    str += '<a href=\'' + hrefUrl + '\' style="color: blue;">查看详情</a>';
					    return str;
					}
				} ] ]
	});
}

function paginationpage(total, data2) {
	// 分页
	$('#pp').pagination(
			{
				total : total,
				layout : [ 'list', 'first', 'prev', 'links', 'next', 'last', 'manual' ],
				emptyMsg : '<span>无记录</span>',
				showRefresh : true,
				displayMsg : ' ',
				onSelectPage : function(pageNo, pageSize) {
					pageNoAll = pageNo;
					pageSizeAll = pageSize;
					findMenu('', '')
				}
			});
	$(".pagination-page-list").parent().append("10条");
	$(".pagination-page-list").parent().prepend("共计" + total + "条,每页显示： ");
	//$(".pagination-page-list").remove();
}

function LessThan(oTextArea){
    //获得textarea的maxlength属性
    var MaxLength = oTextArea.getAttribute("maxlength");
    var num = oTextArea.value.length;  
    $('#txtNum').html(num+"/500");
    //返回文本框字符个数是否符号要求的boolean值
    return oTextArea.value.length < oTextArea.getAttribute("maxlength");
}

/**
 * 省市区级联
 * @returns
 */
function openSelectedAddress(){ //回显地址
	var provinceCode = $("#provinceCode").val();
	var cityCode = $("#cityCode").val();
	var countryCode = $("#countryCode").val();
	
    var url = '/ums/communityAction!initProvince.html';
    $.ajaxSettings.async = false;
    $.post(url, function(result) {
        $("#province").combobox({
            data : result,
            valueField:'code',
            textField:'name',
            cache:false,
            editable:false, //只读
            onLoadSuccess: function () { //加载完成后,设置选中的项
                $(this).combobox("setValue",provinceCode);
            },
            onSelect:function(record){
                var provinceId = record.code;
                $("#provinceCode").val(provinceId);
                $("#cityCode").val("");
                $("#countryCode").val("");
                var url = '/ums/communityAction!getChildSelectData.html?code='+provinceId;
                $.post(url, function(result1) { //二级联动
                    $("#city").combobox({
                        data : result1,
                        valueField:'code',
                        textField:'name',
                        cache:false,
                        editable:false,
                        onLoadSuccess: function (data) { //加载完成后,设置选中的项
                        	//console.debug(provinceId);
                        	//console.debug(provinceCode);
                        	if (provinceId != '' && provinceCode != '' && provinceId != provinceCode) {
                        		$(this).combobox("select","");
							} else {
								$(this).combobox("select",cityCode);
							}
                        },
                        onSelect:function(record){
                            var cityId = record.code;
                            $("#cityCode").val(cityId);
                            $("#countryCode").val("");
                            var url = '/ums/communityAction!getChildSelectData.html?code='+cityId;
                            $.post(url,function(result2) { //三级联动
                                $("#country").combobox({
                                    data : result2,
                                    valueField:'code',
                                    textField:'name',
                                    cache:false,
                                    editable:false,
                                    onLoadSuccess: function () { //加载完成后,设置选中的项
                                    	if (provinceId != '' && provinceCode != '' && provinceId != provinceCode) {
                                    		$(this).combobox("select","");
            							} else {
            								$(this).combobox("select",countryCode);
            							}
                                    },
                                    onSelect:function(record){
                                    	var countryCode = record.code;
                                        $("#countryCode").val(countryCode);
                                    }
                                });
                            },'json');
                        }
                    });
                },'json');
            }
        });
    },'json');
    $.ajaxSettings.async = true;
}
