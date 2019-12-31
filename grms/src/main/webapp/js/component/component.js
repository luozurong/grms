//该文件主要封装easyui几个组件 
//@author：lly
//date：2017/11/17
var arealists =[];//选中的区域id
var provinces = [];//选中的区域对应省id
//console.log(provinces)
var communitys = [];//选中的小区机构编号
var bd = {
	alert:function(title,msg){
		parent.$.messager.alert({
			width:362,
//			height:203,
			title:title,
			msg:msg,
			shadow:false,
			draggable:false,
			border:false,
			fn:function(){
				console.log('回调函数')
			}
		})
	},
	//区域选择弹窗
	areaWindow:function(){
		var windowheight = $(window).height();
		var width = 0;
		var height = 0;
		if(windowheight<500){
			width = 602;
			height = 400;
		}else{
			width = 702;
			height = 500;
		}
		$('#areaWindow').window({
			title:'添加区域',
			width:width,
			height:height,
			modal:true,
			draggable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			border:false,
			resizable:false,
			href:'../dialog/addArea.html',
			onOpen:function(){
				$('#areaWindow').window('center');	
				parent.$('.mask,.tabsMask').show();
    			$('body').css('overflow','hidden');//禁止滚动
			},
			onClose:function(){
				parent.$('.mask,.tabsMask').hide();	
    			$('body').css('overflow','auto');//恢复滚动
			}
		})
	},
	communityWindow:function(){ //需要传选中的区域id
		var windowheight = $(window).height();
		var width = 0;
		var height = 0;
		if(windowheight<500){
			width = 802;
			height = 452;
		}else{
			width = 802;
			height = 552;
		}
		$('#communityWindow').window({
			title:'添加小区',
			width:width,
			height:height,
			modal:true,
			draggable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			border:false,
			resizable:false,
			href:'../dialog/addCommunity.html',
			onOpen:function(){
				$('#communityWindow').window('center');	
				parent.$('.mask,.tabsMask').show();
    			$('body').css('overflow','hidden');//禁止滚动				
			},
			onClose:function(){
				parent.$('.mask,.tabsMask').hide();	
    			$('body').css('overflow','auto');//恢复滚动	
			}
		})
	},
	communityRegionWindow:function(){
		$('body').append("<div id='communityRegionWindow'></div>");
		 //需要传选中的区域id
		var windowheight = $(window).height();
		var width = 0;
		var height = 0;
		if(windowheight<500){
			width = 902;
			height = 352;
		}else{
			width = 902;
			height = 352;
		}
		$('#communityRegionWindow').window({
			title:'选择区域',
			width:width,
			height:height,
			modal:true,
			draggable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			border:false,
			resizable:false,
			href:'../dialog/addRegionCommunity.html',
			onOpen:function(){
				$('#communityRegionWindow').window('center');	
				parent.$('.mask,.tabsMask').show();
   			$('body').css('overflow','hidden');//禁止滚动				
			},
			onClose:function(){
				parent.$('.mask,.tabsMask').hide();	
   			$('body').css('overflow','auto');//恢复滚动	
   			$('#communityRegionWindow').parent().remove();
			}
		})
	},
	communityCheckBoxPageWindow:function(){
		 //需要传选中的区域id
		$('body').append("<div id='communityCheckBoxPageWindow'></div>");
		var windowheight = $(window).height();
		var width = 0;
		var height = 0;
		if(windowheight<500){
			width = 902;
			height = 552;
		}else{
			width = 902;
			height = 552;
		}
		$('#communityCheckBoxPageWindow').window({
			title:'选择小区',
			width:width,
			height:height,
			modal:true,
			draggable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			border:false,
			resizable:false,
			href:'../dialog/addCheckboxCommunity.html',
			onOpen:function(){
				$('#communityCheckBoxPageWindow').window('center');	
				parent.$('.mask,.tabsMask').show();
    			$('body').css('overflow','hidden');//禁止滚动				
			},
			onClose:function(){
				parent.$('.mask,.tabsMask').hide();	
    			$('body').css('overflow','auto');//恢复滚动	
    			$('#communityCheckBoxPageWindow').parent().remove();
			}
		})
			
	},
	communityRadioPageWindow:function(){ //需要传选中的区域id
		var windowheight = $(window).height();
		var width = 0;
		var height = 0;
		if(windowheight<500){
			width = 902;
			height = 552;
		}else{
			width = 902;
			height = 552;
		}
		$('#communityRadioPageWindow').window({
			title:'选择小区',
			width:width,
			height:height,
			modal:true,
			draggable:false,
			collapsible:false,
			minimizable:false,
			maximizable:false,
			border:false,
			resizable:false,
			href:'../dialog/addRadioCommunity.html',
			onOpen:function(){
				$('#communityRadioPageWindow').window('center');	
				parent.$('.mask,.tabsMask').show();
    			$('body').css('overflow','hidden');//禁止滚动				
			},
			onClose:function(){
				parent.$('.mask,.tabsMask').hide();	
    			$('body').css('overflow','auto');//恢复滚动	
			}
		})
	},	
//	获取城市+小区id组成字符串
	getIds:function(span){
		var values = '' ;
	    $(span).each(function(index) {
	    	var value = $(this).attr('id');
	    	values += value +',';
		});
		values = values.substring(0,values.length-1);//去掉最后逗号
	    return values;
	},
	//格式化时间格式 2017/01/30 00:00:00 - 2017/01/30 00:00:00 格式成月日年 01/30/2017 00:00:00- 01/30/2017 00:00:00
	formatDateRange:function(time){
		var newTime;
		if(time){
			newTime = time.split(' - ');
			var time0 = newTime[0].split(/\s+/),
				time1 = newTime[1].split(/\s+/),
				time00 = time0[0].split('/'),
				time10 = time1[0].split('/');
			time10 = time10[1]+'/'+time10[2]+'/'+time10[0];
			time00 = time00[1]+'/'+time00[2]+'/'+time00[0];
			if(time1[1]){time1 = time10+' '+time1[1]}else{time1 = time10};
			if(time0[1]){time0 = time00+' '+time0[1]}else{time0 = time00};
			newTime = time0+' - '+time1;
		}
		return newTime;
	},
	//重写复选框默认样式
	checkboxCss:function(){
		var typeLabel = $('.typeLabel');
		var checkBorder = '<span class="checkBorder"></span>';
		if( typeLabel.children('.checkBorder').length == 0 ){
			typeLabel.append(checkBorder);
		};
		var check = $('input[type="checkbox"]:checked');
		var uncheck = $('input[type="checkbox"]').not("input:checked");
		uncheck.siblings('.checkBorder').removeClass('checkIcon');
		check.siblings('.checkBorder').addClass('checkIcon');
		typeLabel.click(function(){
			var check = $('input[type="checkbox"]:checked');
			var uncheck = $('input[type="checkbox"]').not("input:checked");
			check.siblings('.checkBorder').addClass('checkIcon');
			uncheck.siblings('.checkBorder').removeClass('checkIcon');
		});
		
	},
	//重写单选框默认样式
	radioCss:function(){
		var typeLabel = $('.typeLabel1');
		var checkBorder = '<span class="checkBorder1"></span>';
		if( typeLabel.children('.checkBorder1').length == 0 ){
			typeLabel.append(checkBorder);
		};
		var check = $('input[type="radio"]:checked');
		var uncheck = $('input[type="radio"]').not("input:checked");
		uncheck.siblings('.checkBorder1').removeClass('checkIcon1');
		check.siblings('.checkBorder1').addClass('checkIcon1');
		typeLabel.click(function(){
			var check = $('input[type="radio"]:checked');
			var uncheck = $('input[type="radio"]').not("input:checked");
			check.siblings('.checkBorder1').addClass('checkIcon1');
			uncheck.siblings('.checkBorder1').removeClass('checkIcon1');
		});
	},
	//单选可不选
	radioNotCheck:function(){
		var inputRadio = $("input[type='radio']");
		for(var i = 0;i<inputRadio.length;i++){
			if(inputRadio.eq(i).attr('checked')!='checked'){
				inputRadio.eq(i).attr('data','0');
			}else{
				inputRadio.eq(i).attr('data','1');
			}
		}
//		$("input[type='radio']").attr('data','0');
		$("input[type='radio']").unbind('click');
		$("input[type='radio']").bind('click',function(){
			if($(this).attr('data')==1){
				$(this).attr('data','0');
		        $(this).removeAttr('checked');
		    }else{
		    	var name = $(this).attr('name');
				$("input[type='radio'][name='"+name+"']").attr('data','0');
//				console.log(name)
				$(this).attr('data','1');
		    }
		});
	},
	//表格复选框样式配合css重写
	loadSpan:function(){//动态添加标签
		var checkParent = $('.datagrid-cell-check input[type="checkbox"]');
		var checkBorder = '<span class="checkBorder"></span>';
		checkParent.after(checkBorder);
		var headerCheck = $('.datagrid-header-check input[type="checkbox"]');
		for(var i=0;i<headerCheck.length;i++){
			if( headerCheck.eq(i).siblings('.checkBorder').length == 0 ){
				headerCheck.eq(i).after(checkBorder);
			}
		}
		var checkedDis = $('.datagrid input[type="checkbox"]:disabled');
		checkedDis.siblings('.checkBorder').addClass('checkDis');
	//	console.log(checkedDis.siblings('.checkBorder'))
	},
	boxCheck:function(){//勾选手动修改样式
		var checked = $('.datagrid input[type="checkbox"]:checked');
		var checkBorder = checked.siblings('.checkBorder');
		checkBorder.addClass('checkIcon'); 
	},
	boxUnCheck:function(){//取消勾选手动修改样式
		var unchecked = $('.datagrid input[type="checkbox"]').not("input:checked");
		var checkBorder = unchecked.siblings('.checkBorder');
		checkBorder.removeClass('checkIcon');
	}
}
