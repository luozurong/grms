
var sss = [{
	ccmc: "王老吉卖场",
	cpyw: "社区运营",
	cpList: [{
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		},
		{
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		}, {
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		},
		{
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		}
	]
}, {
	ccmc: "/",
	cpyw: "媒管",
	cpList: [{
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		},
		{
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		}, {
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		},
		{
			typec: "地推",
			cpListc: "标准场地",
			cpSku: "标准场地",
			areaName: "天马小区",
			startTime: "2018年12月26日12:00",
			endTime: "2018年12月27日12:00",
			bugNum: "5"
		}
	]
}];

getResourceView(sss,$("#dg32"));

function getResourceView(sss,$eleS){
	var list1 = [];
	var list2 = [];
	var list3 = [];
	var dataList = [];
	var si = 0;
	for(var i = 0; i < sss.length; i++) {
		for(var j = 0; j < sss[i].cpList.length; j++) {
			if(sss[i].cpList.length > 1 && j == 0) {
				if(sss[i].cpyw == "社区运营") {//社区运营和其他类型合并规则区分
					list1.push({
						index: si,
						rowspan: sss[i].cpList.length
					});
					list2.push({
						index: si,
						rowspan: sss[i].cpList.length
					});
					list3.push({
						index: si,
						rowspan: sss[i].cpList.length
					});
				} else {
					list2.push({
						index: si,
						rowspan: sss[i].cpList.length
					});
				}
			}
			si++;
			dataList.push({
				ccmc: sss[i].ccmc,
				cpyw: sss[i].cpyw,
				typec: sss[i].cpList[j].typec,
				cpListc: sss[i].cpList[j].cpListc,
				cpSku: sss[i].cpList[j].cpSku,
				areaName: sss[i].cpList[j].areaName,
				startTime: sss[i].cpList[j].startTime,
				endTime: sss[i].cpList[j].endTime,
				bugNum: sss[i].cpList[j].bugNum
			})
		}
	}
	$eleS.datagrid({
		data: dataList,
		fitColumns: true,
		nowrap: false,
		scrollbarSize: 0,
		onLoadSuccess: function(data) {
			$(".datagrid-cell-check").addClass('dataCheck');
			$(".datagrid-header-check").addClass('dataCheck');
			for(var a = 0; a < list1.length; a++) {
				$eleS.datagrid('mergeCells', { //第一列合并
					index: list1[a].index,
					field: 'ccmc',
					rowspan: list1[a].rowspan
				});
			}
			for(var b = 0; b < list2.length; b++) {
				$eleS.datagrid('mergeCells', { //第一列合并
					index: list2[b].index,
					field: 'cpyw',
					rowspan: list2[b].rowspan
				});
			}
			for(var c = 0; c < list3.length; c++) {
				$eleS.datagrid('mergeCells', { //第一列合并
					index: list3[c].index,
					field: 'areaName',
					rowspan: list3[c].rowspan
				});
			}
			$eleS.parent().find('td[field=areaName] .datagrid-cell').each(function() {
				$(this).attr('title', $(this).html());
			});
			setTimeout(function(){
				$eleS.datagrid('resize');
			},30)
		},
		columns: [
			[ //相应调整显示栏
				{
					field: 'ccmc',
					title: '场次名称',
					width: 100,
					align: "center"
				},
				{
					field: 'cpyw',
					title: '业务',
					width: 110,
					align: "center"
				},
				{
					field: 'typec',
					title: '类型',
					width: 100,
					align: "center"
				},
				{
					field: 'cpListc',
					title: '产品清单',
					width: 80,
					align: "center"
				},
				{
					field: 'cpSku',
					title: '产品规格',
					width: 80,
					align: "center"
				},
				{
					field: 'bugNum',
					title: '购买数量',
					width: 80,
					align: "center"
				},
				{
					field: 'startTime',
					title: '执行开始时间',
					width: 120,
					align: "center"
				},
				{
					field: 'endTime',
					title: '执行结束时间',
					width: 120,
					align: "center"
				},
				{
					field: 'areaName',
					title: '已选小区',
					width: 230,
					align: "center"
				}
			]
		]
	});
}
