/*时间下拉框data数据*/
var data1 = [
    {
        "id":25,
        "text":"请选择",
        "selected":true
    },
    {
        "id":0,
        "text":"00:00"/*,
     "selected":true*/
    },{
        "id":1,
        "text":"01:00"
    },{
        "id":2,
        "text":"02:00"
    },{
        "id":3,
        "text":"03:00"
    },{
        "id":4,
        "text":"04:00"
    },{
        "id":5,
        "text":"05:00"
    },{
        "id":6,
        "text":"06:00"
    },{
        "id":7,
        "text":"07:00"
    },{
        "id":8,
        "text":"08:00"
    },{
        "id":9,
        "text":"09:00"
    },{
        "id":10,
        "text":"10:00"
    },{
        "id":11,
        "text":"11:00"
    },{
        "id":12,
        "text":"12:00"
    },{
        "id":13,
        "text":"13:00"
    },{
        "id":14,
        "text":"14:00"
    }
    ,{
        "id":15,
        "text":"15:00"
    },{
        "id":16,
        "text":"16:00"
    },{
        "id":17,
        "text":"17:00"
    },{
        "id":18,
        "text":"18:00"
    },{
        "id":19,
        "text":"19:00"
    },{
        "id":20,
        "text":"20:00"
    },{
        "id":21,
        "text":"21:00"
    },{
        "id":22,
        "text":"22:00"
    },{
        "id":23,
        "text":"23:00"
    },{
        "id":24,
        "text":"24:00"
    }
];

/* ------ $(document).ready -----------   */
var bDate;
var eDate;
$(function () {

    var numadd = 1;
    /*点击添加，添加策略设置*/
    $(".add-rule").on("click",function(){
        numadd++;
        var ruleBoxId = "rule"+numadd;
        var ruleBoxId2 = "#"+ruleBoxId;

        var ruleBox = '<div id="'+ruleBoxId+'" class="rule-box">'+
            '<div class="rule-delete"></div>'+
            '<div class="op_time_box"><div >每天开启时段:</div><div class="time_box"><div class="time_bt time-select1"></div></div><div>至</div><div class="time_box" ><div class="time_bt time-select2"></div></div></div>'+
            '<div class="set_brightness_box">'+
                '<div class="text_fl" >亮度设置:</div><div class="logo-1 fl"></div>'+
                '<div class="text_fl width_130" ><div class="swip_box" ><div id="ssa_add"></div><div class="ssa"></div></div></div>'+
                '<div class="num_box clearfix" ><div class="box1"><input id="liangdu" class="fl" type="text" value="" /><div class="num-deng fl">%</div></div></div>'+
                '<div class="text_fl" >视频音量设置：</div><div class="logo-2 fl" ></div>'+
                '<div class="text_fl width_130" ><div class="swip_box"  ><div id="music_add"></div><div class="music" ></div></div></div>'+
                '<div class="num_box clearfix"><div class="box1"><input id="yinliang" class="fl" type="text" value="" /><div class="num-yin fl">%</div></div></div>'+
                '<div class="text_fl" >音频广告音量：</div><div class="logo-3 fl" ></div>'+
                '<div class="text_fl width_130" ><div class="swip_box"  ><div id="audiomusic_add"></div><div class="audiomusic" ></div></div></div>'+
                '<div class="num_box clearfix"><div class="box1"><input id="audioyinliang" class="fl" type="text" value="" /><div class="num-yin fl">%</div></div></div>'+
            '</div>'+
        '</div>';

        $(".add-rule").before(ruleBox);
        $(".rule-delete").show();
        $(".rule-delete:first").hide();//第一个策略没有删除叉号

        /*点击叉号，对应的策略设置消失*/
        $(".rule-delete").on("click",function(){
            $(this).parent().remove();
        });

        /*生成时间下拉框、亮度音量滑块*/
        sliderAndTime(ruleBoxId2);

    });

    /*点击叉号，对应的策略设置消失*/
    $(".rule-delete").on("click",function(){
        $(this).parent().remove();
    });

});


/*生成时间下拉框、亮度音量滑块*/
function sliderAndTime(id){
    var j1 = id +" .time-select1";
    var j2 = id +" .time-select2";

    var a1 = id +" .ssa";
    var a2 = id +" #ssa_add";
    var a3 = id +" #liangdu";

    var b1 = id +" .music";
    var b2 = id +" #music_add";
    var b3 = id +" #yinliang";

    var c1 = id +" .audiomusic";
    var c2 = id +" #audiomusic_add";
    var c3 = id +" #audioyinliang";

    /* 时间下拉框*/
    timefun(j1);
    timefun(j2);

    /*亮度滑块*/
    sliderfun(a1,a2,a3);

    /*视频音量滑块*/
    sliderfun(b1,b2,b3);

    /*音频音量滑块*/
    sliderfun(c1,c2,c3);

    $(j1).combobox({
    	onChange:function(){
        	var beginTime=$(j1).combobox('getText');
       	   	var endTime=$(j2).combobox('getText');
       		var t1="2017-03-27 "+beginTime;
        	var t2="2017-03-27 "+endTime;
        	//console.log(t1+"----------"+t2);
        	//console.log(beginTime+"----------"+endTime);
        	var f=CompareDate(t1,t2);
        	if(f){
        		$(j1).combobox('setValue',endTime);
        		infoMask("开始时间需小于结束时间");
        		return ;
        	}   
        }
    });

    
    $(j2).combobox({
    	onChange:function(){
        	var beginTime=$(j1).combobox('getText');
       	   	var endTime=$(j2).combobox('getText');
       		var t1="2017-03-27 "+beginTime;
        	var t2="2017-03-27 "+endTime;
        	//console.log(t1+"----------"+t2);
        	//console.log(beginTime+"----------"+endTime);
        	var f=CompareDate(t1,t2);
        	if(f){
        		$(j2).combobox('setValue',beginTime);
        		infoMask("结束时间需大于开始时间");
        		return ;
        	}   
        }
    });
    
}


/*
* 亮度、视频音量、语音音量滑块函数
*s1 小滑块 class
*s2 上面进度滑块 id
*s3 显示滑动数值input框 id
* */
function sliderfun(s1,s2,s3){
    var defaultValue = 80;//默认滑动位置
    var sliderLength = 130;//滑块长度
    $(s1).slider({
        mode: 'h',
        value : defaultValue,
        onChange:function(newValue,oldValue){
            $(s2).width(newValue*sliderLength/100);
            $(s3).val(newValue);
        }
    });

    $(s2).width(defaultValue*sliderLength/100);
    $(s3).empty().val(defaultValue);

    $(s3).on('keyup',function(){
        var val = $(this).val();
        if(val==''||val==null||val==undefined||isNaN(val)){
            val=0;
        }
        var num = parseInt(val);
        if(num>100){
            num=100;
        }
        $(this).empty().val(num);

        var width = num*sliderLength/100;
        $(s1).slider('setValue', num);
        $(s2).css('width', width);
    });
}

//比较时间大小
function CompareDate(d1,d2)
{

 //将所有的短横线替换为斜杠

  return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
}


window.onload=function(){ 
	var id=$("#bagId").val();
	 $.ajax({ 
	       type: "post", 
	       url: "stratergyBagAction!getStrategyBagJson.html", 
	       cache:false, 
	       async:false, 
	       data:{"id":id},
	        success: function(data){
	        	var json = eval('(' + data + ')');
	        	console.log(json.obj);
	        	if(json.success){
	        		var list=json.obj;
	        		for (var i = 0; i < list.length; i++) {
	        			var id=list[i].id;
	        			
	        		    var j1 ="#time-select1"+id;
	        		    var j2 ="#time-select2"+id;

	        		    var a1 ="#ssa"+id;
	        		    var a2 ="#ssa_add"+id;
	        		    var a3 ="#liangdu"+id;

	        		    var b1 ="#music"+id;
	        		    var b2 ="#music_add"+id;
	        		    var b3 ="#yinliang"+id;

	        		    var c1 ="#audiomusic"+id;
	        		    var c2 ="#audiomusic_add"+id;
	        		    var c3 ="#audioyinliang"+id;

	        		    /* 时间下拉框*/
	        		    timefun(j1);
	        		    timefun(j2);
	        		   // console.log(list[i].birghtness+"--"+list[i].videoVolume+"---"+list[i].audioVolume)
	        		    /*亮度滑块*/
	        		    sliderfun1(a1,a2,a3,list[i].birghtness);

	        		    /*视频音量滑块*/
	        		    sliderfun1(b1,b2,b3,list[i].videoVolume);

	        		    /*音频音量滑块*/
	        		    sliderfun1(c1,c2,c3,list[i].audioVolume);

	        		}
	        		
	        	}
	        	aa(list);
	        } 

	});
	 
        $(".rule-delete").show();
        $(".rule-delete:first").hide();//第一个策略没有删除叉号

        /*点击叉号，对应的策略设置消失*/
        $(".rule-delete").on("click",function(){
            $(this).parent().remove();
        });
	 
};

function aa(list){
	   $(".rule-box").each(function(i,value){
		
			var id=$(this).attr("id");
		    var j1 ="#"+id +" .time-select1";
		    var j2 ="#"+id +" .time-select2";
	
  		     var beginTime = list[i].beginTime;
  		     var endTime = list[i].endTime;
  	
		   $(j1).combobox({
			   value:beginTime,
			   onChange:function(){
		        	/*var beginTime=$(j1).combobox('getValue');
		       	   	var endTime=$(j2).combobox('getValue');*/
		    		/* beginTime=$(j1).combobox('getText');
		       	   	 endTime=$(j2).combobox('getText');*/
		    		var startTimes = $(j1).combobox('getText');
		    		var endTimes = $(j2).combobox('getText');
		    		if(endTimes=='00:00'){
		    			return ;
		    		}
		    		var t1="2017-03-27 "+startTimes;
		        	var t2="2017-03-27 "+endTimes;
		        	//console.log(t1+"----+++"+endTime+"+++---"+t2);
		        	  if(parseInt(endTimes.substr(0,2))==24){
			      	    	t2="2017-03-28 "+endTimes;
			      	    }else{
			      	    	t2="2017-03-27 "+endTimes;

			      	    }
		        	  var f=CompareDate(t1,t2);
		        	 // console.log(t1+"----+++++6666+"+f+"+6666++++---"+t2);
			        	if(f){
			        		$(j2).combobox('setValue',startTimes);
			        		infoMask("开始时间需小于结束时间");
			        		return ;
			        	} 
		       		/*var t1="2017-03-27 "+beginTime;
		        	var t2="2017-03-27 "+endTime;
		      	    if(parseInt(endTime.substr(0,2))==24){
		      	    	t2="2017-03-28 "+endTime;
		      	    }else{
		      	    	t2="2017-03-27 "+endTime;

		      	    }
		      	    console.log(t1+"-----===---"+t2);
		        	var f=CompareDate(t1,t2);
		        	
		        	if(f){
		        		$(j1).combobox('setValue',endTime);
		        		infoMask("开始时间不能大于截止时间");
		        		return ;
		        	}   */
		        },
		        onLoadSuccess: function () {
		        	$(j1).combobox('setValue',beginTime);
		        }
		    });

		    
		    $(j2).combobox({
		    	value:endTime,
		    	onChange:function(){
		        	/* beginTime=$(j1).combobox('getText');
		       	   	 endTime=$(j2).combobox('getText');*/
		    		var startTimes = $(j1).combobox('getText');
		    		var endTimes = $(j2).combobox('getText');
		    		if(endTimes=='00:00'){
		    			return ;
		    		}
		    		var t1="2017-03-27 "+startTimes;
		        	var t2="2017-03-27 "+endTimes;
		        	
		        	//console.log(t1+"----+++"+endTime+"+++---"+t2);
		        	  if(parseInt(endTimes.substr(0,2))==24){
			      	    	t2="2017-03-28 "+endTimes;
			      	    }else{
			      	    	t2="2017-03-27 "+endTimes;

			      	    }
		        	  var f=CompareDate(t1,t2);
		        	 // console.log(t1+"----+++++5555+"+f+"+55555++++---"+t2);
		        	  if(f){
			        		$(j1).combobox('setValue',endTimes);
			        		infoMask("结束时间需大于开始时间");
			        		return ;
			        	}
		        	 //console.log(t1+"----++++++---"+t2);
		        	//var f=CompareDate(t1,t2);
		        	/*if(f){
		        		//$(j2).combobox('setValue',beginTime);
		        		infoMask("截止时间不能小于开始时间");
		        		return ;
		        	}  */ 
		        },
		        onLoadSuccess: function () {
		        	$(j2).combobox('setValue',endTime);
		        }
		    });
		    
		    
		});
	 
}

/*
* 亮度、视频音量、语音音量滑块函数
*s1 小滑块 class
*s2 上面进度滑块 id
*s3 显示滑动数值input框 id
* */
function sliderfun1(s1,s2,s3,value){
    var defaultValue = value;//默认滑动位置
   // console.log(s1+"--"+s2+"--"+s3+value);
    var sliderLength = 130;//滑块长度
    $(s1).slider({
        mode: 'h',
        value : defaultValue,
        onChange:function(newValue,oldValue){
            $(s2).width(newValue*sliderLength/100);
            $(s3).val(newValue);
        }
    });

    $(s2).width(defaultValue*sliderLength/100);
    $(s3).empty().val(defaultValue);

    $(s3).on('keyup',function(){
        var val = $(this).val();
        if(val==''||val==null||val==undefined||isNaN(val)){
            val=0;
        }
        var num = parseInt(val);
        if(num>100){
            num=100;
        }
        $(this).empty().val(num);

        var width = num*sliderLength/100;
        $(s1).slider('setValue', num);
        $(s2).css('width', width);
    });
}

/* 时间下拉框函数 */
function timefun(idClass){
    $(idClass).combobox({
        limitToList:true,
        data:data1,
        valueField:'id',
        textField:'text',
        editable:false/*,
        panelHeight:"auto"*/
        

    });
}


var j=true;
function Judgename(){
	var name=$("#rule_text").val();
	var bagId=$("#bagId").val();
	if(name!=null){
		 $.ajax({
             type: "GET",
             url: "stratergyBagAction!isExitJudgename.html",
             data: {name:name,id:bagId},
             dataType: "json",
             success: function(msg){
            	  if(msg.success){
            		  $("#rule_text").val("");
            		  j=false;
            		  infoMask("已存在该策略名称");
            	  }else{
            		  j=true;
            	  }       	
             }
         });
        
	}
	
}


function dosave(){
	//console.log(j+",,,,,,,,,,,,,,,")
	if(j==false){
		return ;
	}
	var id=$("#bagId").val();

	
	var name =$("#rule_text").val();
	
	if($.trim(name)==''){
		
		infoMask("策略名称不能为空！");
		return ;
	}	
	var arr=[];
	//判断时间是否已选
	var flg=true;
	//判断开始和结束时间是否相同
	var flg1=true;
	var iscon='';
	//时间数组
	var bt1=[];
	var bt2=[];
	//计算时间段相差
	var num=[];
	$(".rule-box").each(function(){
		var arr1=[];
		var id=$(this).attr("id");
	  // console.log(id);
	    var j1 ="#"+id +" .time-select1";
	    var j2 ="#"+id +" .time-select2";
	    var a1 ="#"+id +" .ssa";
	   
	    var b1 ="#"+id +" .music";

	    var c1 ="#"+id +" .audiomusic";

	    var beginTime=$(j1).combobox('getText');
	    if(beginTime=='请选择'){
	    	flg=false;
	    }
	    var startTime=new Date(("2017-01-01 "+beginTime).replace(/-/g,"\/"));
	    bt1.push(startTime);
	    
	    var endTime=$(j2).combobox('getText');	  
	    if(endTime=='请选择'){
	    	flg=false;
	    }
	    
	    if(beginTime==endTime){
	    	flg1=false;
	    }
	    var overTime;
	    if(parseInt(endTime.substr(0,2))==24){
	    	overTime=new Date(("2017-01-02 "+endTime).replace(/-/g,"\/"));
	    }else{
	    	overTime=new Date(("2017-01-01 "+endTime).replace(/-/g,"\/"));

	    }
	    num.push(parseInt(endTime.substr(0,2))-parseInt(beginTime.substr(0,2)));
	   
	   
	    bt2.push(overTime);
	    
	   	var birghtness=$(a1).slider('getValue');
	    var videoVolume=$(b1).slider('getValue');
	    var audioVolume=$(c1).slider('getValue');
	    //console.log("-"+beginTime+"-"+endTime+"-"+birghtness+"-"+videoVolume+"-"+audioVolume);
	    arr1.push(beginTime);
	    arr1.push(endTime);
	    arr1.push(birghtness);
	    arr1.push(videoVolume);
	    arr1.push(audioVolume);
	    arr.push(arr1.join("-"));
	});
	//var list=JSON.stringify(arr);
	var list=arr.toString();
	
	// console.log("时间"+bt1+"----------------------"+bt2);
    var b=bt1.sort();  
    //console.log("b:::"+b+"----------------------"+b);
    var e=bt2.sort();  
    
    
   // console.log("数组："+num +" num.length:"+num.length);
    var sum=0;
    for(i=0;i<num.length;i++){
    	sum=sum+num[i];
    }
    //console.log("和："+sum);
 
    if(!flg){
    	infoMask("选择时间不能为空");
    	return ;
    }
    
    if(!flg1){
    	infoMask("开始时间和结束时间不能相同");
    	return ;
    }
    
    //判断时间重贴问题
    var su=compareDate(b,e);
	if(su){
		//判断时间是否包含了24个小时
		   if(sum!=24){
		    	infoMask("开启时段需覆盖24小时");
		    	return ;
		    }
		
		 $.ajax({
			 type: "post", 
			    url: "stratergyBagAction!editParameterstrategy.html", 
			    cache:false, 
			    async:false, 
			    data:{"id":id,"name":name,"strategyList":list},
			     success: function(data){
			     	var json = eval('(' + data + ')');
			     	if(json.success){
			     		
			     		 successMask('保存成功！','stratergyBagAction!goParameterstrategyList.html');
			     		
			     	}else{
			     		infoMask("网络出现异常");
			     	}
			     	
			     } 
	
	 
			 
		 });
	}
	
	
}

function compareDate(b,e){
    var begin =b;
    var over =e;

   
    for(i=1;i<begin.length;i++){
    	
        if (begin[i] < over[i-1]){
        	infoMask("存在重复开启时段");
            return false;
        }
    }

   // alert("时间没有重复！");
    return true;
}

