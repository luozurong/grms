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

$(function () {

	var id=$("#id").val();
	var beginDate=$("#beginDate").val();
	var endDate=$("#endDate").val();
	var beginTime=$("#beginTime").val();
	var endTime=$("#endTime").val();
	//alert(beginTime+"--"+endTime);
	var birghtness=$("#birghtness").val();
	var volume=$("#volume").val();

    /* 时间下拉框 */
    /*$('.time-select1,.time-select2').combobox({
        limitToList:true,
        data:data1,
        valueField:'id',
        textField:'text',
        editable:false,
        panelHeight:"auto"
    });*/


});


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

	        		    var j1 = id +" .time-select1";
	        		    var j2 = id +" .time-select2";

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
	        		    /*timefun(j1);
	        		    timefun(j2);*/


//	        		    console.log(list[i].birghtness+"--"+list[i].videoVolume+"---"+list[i].audioVolume)
	        		    /*亮度滑块*/
	        		    sliderfun(a1,a2,a3,list[i].birghtness);

	        		    /*视频音量滑块*/
	        		    sliderfun(b1,b2,b3,list[i].videoVolume);

	        		    /*音频音量滑块*/
	        		    sliderfun(c1,c2,c3,list[i].audioVolume);



	        		   /* $("#time-select1"+list[i].id).combobox('setValue',list[i].beginTime);
	        			$("#time-select2"+list[i].id).combobox('setValue',list[i].endTime);*/

                        $("#time-select1"+list[i].id).combobox({
                            limitToList:true,
                            /*data:data1,*/
                            value : list[i].beginTime,
                            valueField:'id',
                            textField:'text',
                            editable:false

                        });
                        $("#time-select2"+list[i].id).combobox({
                            limitToList:true,
                            /*data:data1,*/
                            value:list[i].endTime,
                            valueField:'id',
                            textField:'text',
                            editable:false

                        });


	        		}

                    $(".time-select1").combobox({ disabled: true });
                    $(".time-select2").combobox({ disabled: true });
	        	}



            }

	});



};



/*
* 亮度、视频音量、语音音量滑块函数
*s1 小滑块 class
*s2 上面进度滑块 id
*s3 显示滑动数值input框 id
* */
function sliderfun(s1,s2,s3,value){
    var defaultValue = value;//默认滑动位置
   // console.log(s1+"--"+s2+"--"+s3+value);
    var sliderLength = 130;//滑块长度
    $(s1).slider({
        mode: 'h',
        value : defaultValue,
        onChange:function(newValue,oldValue){
            $(s2).width(newValue*sliderLength/100);
            $(s3).text(newValue);
        }
    });

    $(s2).width(defaultValue*sliderLength/100);
    $(s3).empty().text(defaultValue);

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

    $(s1).slider({
        disabled:true
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
        disabled:false,
        panelHeight:"auto"*/

    });

}
