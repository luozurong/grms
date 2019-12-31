//var localhost = 'http://192.168.51.4:8080/';//本地调试
var localhost = '/';//正式
var myNodes = [];//右边的点
var checkLeafs = []; //选中的叶数据
var flag = false; //预防1.4版本首次加载调用beforeCheck
var districtsUrl = '';//区域接口地址

$(function() {
	var areaParams = {};
	var dataList = [];
	$('#tree').tree({
		method: 'post',
		animate: false,
		checkbox: true,
		onCheck: treeCheck,
		onBeforeCheck: beforeCheck,//勾选前调用
		onLoadSuccess: loadSuccess//加载成功调用
	});
//	获取树数据
	districtsUrl = localhost+'grms/common/districts';
	treeInit();

});
function treeInit(){
	$.ajax({
		type: "get",
		url:districtsUrl,
//		url: "../dialog/tree_data1.json",//lly本地测试数据
		dataType: 'json',
		contentType: 'application/json;charset=UTF-8',
		async: false,
		success: function(data) {
//			console.log(data)
			if(data.code == 0) {
				var data = data.data;
				if(data.length != 0){
					var treeData = [];
	//				console.log(data);
					for(var i = 0; i < data.length; i++) {
						var treeDataChildren = [];
						var tree = data[i];
						for(var j = 0; j < tree.citys.length; j++) {//处理树子节点数据格式
							var treeChildren = tree.citys[j];
							treeDataChildren.push({
								id: treeChildren.code,
								text: treeChildren.name
							})
						}
						treeData.push({//处理数据格式
							state: 'closed',
							id: tree.province.code,
							text: tree.province.name,
							children: treeDataChildren
						})
					}
//					console.log(treeData)
					$('#tree').tree('loadData', treeData);//树填入数据
				}
				
			} else {
				bd.alert('提示', '服务器响应异常！')
			}
		},
		error:function(xhr,txt,e){
			bd.alert('提示','服务器响应异常！');
		}
	});
}

//加载成功 选中已选
function loadSuccess(node, data) {
//	console.log("loadSuccess:arealists======" + arealists);
	for(var i = 0; i < arealists.length; i++) {
		var a = $('#tree').tree('find', arealists[i]);
		$('#tree').tree('check', a.target)
	}

};

function beforeCheck(node, checked) {
	if(flag) {
		var isLeaf = $('#tree').tree('isLeaf', node.target);
		//			console.log(node)
		if(isLeaf) { //是叶节点
			if(!node.checked) { //未勾选
				var html = '<span class="add_area clearfix" id=' + node.id + '><span class="area fl">' + node.text + '</span><span class="delPic fl" onclick="delRight(this)"></span></span>';
				$('.add_top').append(html); //加到右边
				checkLeafs.push(node); //选中的叶数据
//				console.log("beforeCheck+isLeaf+isNotCheck" + checkLeafs);
			} else {
				$('.add_top').find('#' + node.id).remove(); //清空
				checkLeafs.splice($.inArray(node, checkLeafs), 1); //删掉未选中的叶数据
//				console.log("beforeCheck+isLeaf+isCheck" + checkLeafs)
			}
		} else { //否则 拿该节点下面所有的 未选的 叶节点

			//				console.log(node)
			var childNodes = $('#tree').tree('getChildren', node.target);
			//			var uncheckeds = $('#tree').tree('getChecked', 'unchecked');
//			console.log("该节点下面所有的节点==")
//			console.log(childNodes.length);
			myNodes = [];
			for(var i = 0; i < childNodes.length; i++) {
				var childLeaf = $('#tree').tree('isLeaf', childNodes[i].target)
					//					console.log("allLeaf"+childLeaf)
				if(childLeaf) { //叶节点
					//					console.log(childNodes[i].children)
					if(!childNodes[i].checked) { //未勾选的
						//						console.log(childNodes[i]);
						myNodes.push(childNodes[i].text);
						var html = '<span class="add_area clearfix" id=' + childNodes[i].id + '><span class="area fl">' + childNodes[i].text + '</span><span class="delPic fl" onclick="delRight(this)"></span></span>';
						$('.add_top').append(html); //加到右边
						checkLeafs.push(childNodes[i]); //选中的叶数据
						//							console.log("选中的叶=====")
						//							console.log(checkLeafs)
					}
				}
			}
//			console.log('选中的 叶节点==')
//			console.log(checkLeafs)
//			console.log(myNodes)
			if(myNodes.length == 0) { //父节点取消勾选
				for(var j = 0; j < childNodes.length; j++) {
					if($('#tree').tree('isLeaf', childNodes[j].target)) {
						var id = childNodes[j].id; //该节点下所有子节点
//						console.log('该节点下 所有叶节点==')
//						console.log(childNodes[j])
						$('.add_top').find('#' + id).remove(); //清空
						checkLeafs.splice($.inArray(childNodes[j], checkLeafs), 1); //删掉未选中的叶数据
					}
				}
//				console.log('父节点取消勾选之后 所有的 被勾选的 叶节点==')
//				console.log(checkLeafs)
			}

		}
	}
	flag=true;
	//		console.log(flag)
}

function delRight(e) {
	var id = $(e).parent('.add_area').attr('id');
	var treeNode = $('#tree').tree('find', id);
//	console.log(treeNode)
	$(e).parent('.add_area').remove();
	$('#tree').tree('uncheck', treeNode.target);
}

function treeCheck(node) {
	//		console.log(node.checkState)
	//		console.log(self)
	//		var isLeaf = $('#tree').tree('isLeaf',self.target);
	//		console.log(isLeaf)
	//		if(isLeaf){
	//			
	//		}else{
	//			var nodes = $('#tree').tree('getChecked', 'unchecked');
	////			console.log(nodes)
	//		}
	//		var node = $('#tree').tree('getChecked');
	//		for(var i = 0;i<node.length;i++){
	//			if($('#tree').tree('isLeaf',node[i].target)){
	//				console.log(node[i])
	//			}
	//		}
}
//选中区域确定  需判断删除了哪些区域来删除对应小区

//	var checkParents = [];
function areaSure() {
//	console.log(checkLeafs);
	if(checkLeafs.length == 0) {
		bd.alert('提示', '请选择区域');
	} else {

		var myarealists = arealists; //一级界面选中区域的id
//		console.log(myarealists)
		arealists = []; //一级界面的选择的区域id置空
		var html = '';
//		console.log(checkLeafs)
		for(var i = 0; i < checkLeafs.length; i++) {
			html += '<li class="fl clearfix" data="' + checkLeafs[i].id + '">' +
				'<span class="area fl">' + checkLeafs[i].text + '</span>' +
				'<span class="delPic fl" id="' + checkLeafs[i].id + '" onclick="delSelf(this)"></span>' +
				'</li>';
			arealists.push(checkLeafs[i].id); //重新填入选中的小区id
		}
//		console.log(arealists)
			//开始判断删除区域
		for(var j = 0; j < myarealists.length; j++) {
			var b = $.inArray(myarealists[j], arealists); //-1表示元素不存在数组中
			if(b < 0) {
				//					console.log(myarealists[j]);
				//					delendaarea.push(myarealists[j]);
//				console.log(communitys)
				for(var k = communitys.length - 1; k >= 0; k--) { //用逆向循环 一次性删除数组中的多个数据
					if(communitys[k].city === myarealists[j]) {
						communitys.splice(k, 1); //数组删除对应区域的小区
					}
				}
				var communitylistli = $('.communitylist li');
				for(var l = 0; l < communitylistli.length; l++) {
					if(communitylistli.eq(l).attr('cityid') == myarealists[j]) {
						communitylistli.eq(l).remove(); //页面删除对应区域的小区
					}
				}
			}
		}

		provinces = [];
		//省
		for(var i = 0; i < checkLeafs.length; i++) {
			var checkParents = $('#tree').tree('getParent', checkLeafs[i].target);
			provinces.push({
				id: checkParents.id,
				text: checkParents.text,
				citys: {
					id: checkLeafs[i].id,
					text: checkLeafs[i].text
				}
			})
		}
		var hash = {};
		var i = 0;
		var res = [];
		provinces.forEach(function(item) {//去重合并
			var id = item.id;
			hash[id] ? res[hash[id] - 1].citys.push(item.citys) : hash[id] = ++i && res.push({
				citys: [item.citys],
				id: id,
				text: item.text
			})

		});
		provinces = res;
//		console.log(provinces);
		
		$('.arealist li').remove();
		$('.arealist').append(html)
		$('#areaWindow').window('close');
	}
}
//区域搜索
//var mychecks = [];
function searchArea(value) {
	
//	flag = false; 
//	console.log(value)
//	if(!value){
//		console.log(111)
//		districtsUrl = localhost+'horiBigData/common/districts';
//	}else{
//		console.log(222)
//		districtsUrl = localhost+'horiBigData/common/districts/'+value;
//	}
//	console.log(arealists)
//	treeInit();
	$('.tree-title').css('color','#222222');
	if(!value) return;
	$('#tree').tree('collapseAll');
	var nodes = $('#tree').tree('getChildren'); // get checked nodes
	var roots = $('#tree').tree('getRoots');
//	console.log(nodes)
//	console.log(roots)
	var find = false;
	$.each(nodes, function(key, val) {
		var isLeaf = $('#tree').tree('isLeaf', val.target);
		if(isLeaf && val.text) {
			if(val.text.match(value)) {
				find = true;
				$('#tree').tree('expandTo', val.target);
				$(val.target).eq(0).find('.tree-title').css('color','#6495ed')
			}
		}
	});
	$.each(roots, function(key,val) {
		var isRoot = !$('#tree').tree('isLeaf', val.target);
		if(isRoot && val.text){
			if(val.text.match(value)){
				find = true;
//				$('#tree').tree('select',val.target);
				$('#tree').tree('expandTo', val.target);
				$(val.target).eq(0).find('.tree-title').css('color','#6495ed')
			}
		}
	});
	if(!find) {
		$('#searchInput').searchbox('setValue','')
		bd.alert('提示', '未搜索到区域!');
	}
}
//删除区域
function delSelf(e) {
	var id = $(e).attr('id'); //区域id
	var index = $('.arealist li').index($(e).parent('li'));
	$(e).parent('li').remove(); //页面删除
	arealists.splice(index, 1); //区域数组删除
	checkLeafs.splice(index, 1);
//	console.log(checkLeafs);
	
	provinces = [];
	//省
	for(var i = 0; i < checkLeafs.length; i++) {
		var checkParents = $('#tree').tree('getParent', checkLeafs[i].target);
		provinces.push({
			id: checkParents.id,
			text: checkParents.text,
			citys: {
				id: checkLeafs[i].id,
				text: checkLeafs[i].text
			}
		})
	}
	var hash = {};
	var i = 0;
	var res = [];
	provinces.forEach(function(item) {//省 去重+合并
		var id = item.id;
		hash[id] ? res[hash[id] - 1].citys.push(item.citys) : hash[id] = ++i && res.push({
			citys: [item.citys],
			id: id,
			text: item.text
		})

	});
	provinces = res;
//	console.log(provinces);
	
	if(communitys.length != 0) { //有选中小区才执行
		for(var i = communitys.length - 1; i >= 0; i--) { //用逆向循环 一次性删除数组中的多个数据
			if(communitys[i].city === id) {
				communitys.splice(i, 1);
			}
		}
		var communitylistli = $('.communitylist li');
		for(var j = 0; j < communitylistli.length; j++) {
			if(communitylistli.eq(j).attr('cityid') == id) { //页面删除
				communitylistli.eq(j).remove();
			}
		}
	}

}
//窗口大小改变
$(window).resize(function() {
	$('#areaWindow').window('center'); //弹窗居中
})