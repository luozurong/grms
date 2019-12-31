/**
*复选与单选框样式调整
*/
$("input[type='checkbox']").click(function(){
	if($(this).is(':checked')){
		$(this).parent().addClass("checkbox-bg");
	}else{
		$(this).parent().removeClass("checkbox-bg");
	}
})
$("input[type='radio']").click(function(){
    if($(this).attr("checked") == "checked") $(this).attr("checked",false)
    $("input[type='radio']").each(function(index,ele){ 
        if($(ele).is(":checked") == true) $(ele).attr("checked","checked");
        else $(this).attr("checked",false); 
    });
	$("input[name='"+ $(this).attr('name') +"']").parent().removeClass("radio-bg");
	if($(this).is(':checked')){
		$(this).parent().addClass("radio-bg");
	}
});
var data2 =[
    {hander:"125",starttime:"20150714",sex:"man",Mstatus:"ok",endtime:"20170254","time":"2017-05-19","productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"attr1":"Large","itemid":"EST-1"},
    {hander:"125",starttime:"20150714",sex:"man",Mstatus:"ok",endtime:"20170254","time":"2017-05-19","productid":"K9-DL-01","productname":"Dalmation","unitcost":12.00,"status":"P","listprice":18.50,"attr1":"Spotted Adult Female","itemid":"EST-10"},
    {hander:"125",starttime:"20150714",sex:"man",Mstatus:"ok",endtime:"20170254","time":"2017-05-19","productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":38.50,"attr1":"Venomless","itemid":"EST-11"},
    {hander:"125",starttime:"20150714",sex:"man",Mstatus:"ok",endtime:"20170254","time":"2017-05-19","productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":26.50,"attr1":"Rattleless","itemid":"EST-12"},
    {hander:"125",starttime:"20150714",sex:"man",Mstatus:"ok",endtime:"20170254","time":"2017-05-19","productid":"RP-LI-02","productname":"Iguana","unitcost":12.00,"status":"P","listprice":35.50,"attr1":"Green Adult","itemid":"EST-13"},

];




    $("#startTime").datebox({
        onSelect : function(beginDate){
            $('#endTime').datebox('calendar').calendar({ 
                validator: function(date){
                    return beginDate<=date;
                }
            });
        }
    });

    $("#endTime").datebox({
        onSelect : function(endDate){
            $('#startTime').datebox('calendar').calendar({
                validator: function(date){
                    return endDate>=date;
                }
            });
        }
    });






/**
*表格
*/
$('#dg').datagrid({    
    data:data2.slice(0,10),
    fitColumns :true,
    scrollbarSize:0, 
    onLoadSuccess:function(){
        $(".datagrid-cell-check").addClass('dataCheck');
        $(".datagrid-header-check").addClass('dataCheck');
    },
    columns:[[ 
         {
            field:'ck',
            title:'',
            checkbox:true,
            width:50,
        },   
        {
            field:'hander',
            title:'代码',
            width:100
        },    
        {
            field:'starttime',
            title:'名称',
            width:100
        },    
        {
            field:'time',
            title:'价格',
            width:100
        }    
    ]]    
});


/**
*表格中复选框样式
*/
function inputStyle(){
    $(".datagrid-cell-check,.datagrid-header-check").each(function(){
        if($(this).find("input").is(':checked')){
            $(this).addClass('dataChecked');
        }else{
            $(this).removeClass("dataChecked");
        }
    });
}
/*$(".datagrid-row input").click(function(){
    inputStyle();
});
$(".datagrid-header-check input").click(function(){
    if($(this).is(":checked")){
        $(this).parent().addClass("dataChecked");  
    }else{
        $(this).parent().removeClass("dataChecked");
    }
    setTimeout(function(){
        inputStyle();
    },100)
});*/
$(".datagrid-row").click(function(){
    setTimeout(function(){
        inputStyle();
    },100)
});


/**
*对话框
*/
function delayWorkOrder(){
    wrapMaskShow();
    $('#delayWorkOrder').dialog({
        title: "延时处理",
        width: 600,
        height: 400,
        closed: false,
        modal: true,
        buttons:[{
            text:'不同意',
            handler:function(){
                $('#delayWorkOrder').dialog('close');
                wrapMaskHide();
            }
        },{
            text:'同意',
            handler:function(){
                $('#delayWorkOrder').dialog('close');
                wrapMaskHide();
            }
        }]
    });
}


/**
*提示框封装
*/
function messagerConfirm(title,messager,callBack){
    wrapMaskShow();
    $.messager.confirm(title,messager,function(r){    
        if (r){    
            callBack(); //执行操作
            wrapMaskHide();    
        }else{
            wrapMaskHide();
        }    
    });
}
//提示框调用
function tip(){
    messagerConfirm("是否要删除","您确认想要删除记录吗？",function(){
        alert('确认删除') //执行操作
    });
}


//$.messager.alert('我的消息','这是一个提示信息！','info');


var datas =[
            {
                type:"地推",
                productName:"标准场地（3米*3米）",
                sku:"man",
                Mstatus:[
                         {activeName: '场次', num: 0},
                         {activeName: '帐篷数（必选）', num: 12}],
                endtime:"20170254"
            },
            {
                type:"地推",
                productName:"游戏/内容：",
                sku:"man",
                Mstatus:[
                         {activeName: '场次', num: 0},
                         {activeName: '帐篷数（必选）', num: 12}],
                endtime:"20170254"
            },
            {
                type:"地推",
                productName:"专场策划",
                sku:"man",
                Mstatus:[
                         {activeName: '场次', num: 0},
                         {activeName: '帐篷数（必选）', num: 12}],
                endtime:"20170254"
            },
            {
                type:"地推",
                productName:"基础引流",
                sku:"man",
                Mstatus:[
                         {activeName: '场次', num: 0},
                         {activeName: '帐篷数（必选）', num: 12}],
                endtime:"20170254"
            }
        ];

$('#shDataid').datagrid({    
    data:datas.slice(0,10),
    width: 835,
    fitColumns :false,
    scrollbarSize:0,
    nowrap: false,
    onLoadSuccess:function(){
        /*$('#shDataid').datagrid('mergeCells', { //第二列合并
            index: 0,
            field: 'type',
            rowspan: 4
        });*/
    },
    columns:[[    
        {
            field:'type',
            title:'类型',
            width: 200,
            align: 'center',
        },    
        {
            field:'productName',
            title:'产品清单',
            width: 200,
            align: 'center',
        },    
        {
            field:'sku',
            title:'产品规格',
            width: 200,
            formatter: function(value,row,index){
                console.log(index);
                if(index== 0){
                    var _html = 54;
                    //console.log(row.Mstatus);
                    var _html = '<div class="td-item">'+row.Mstatus[0].activeName+'</div><div class="td-item">'+row.Mstatus[1].activeName+'</div>';
                    return _html;
                }else{
                    return index;
                }
                
            }
        },
        {
            field:'endtime',
            title:'购买数量',
            width: 200, 
            formatter: function(value,row,index){
                console.log(index);
                if(index== 0){
                    var _html = 0;
                    var _html = '<div class="td-item">'+row.Mstatus[0].num+'</div><div class="td-item"><input value="'+row.Mstatus[1].num+'"></div>';
                    return _html;
                }else{
                    return index;
                }
                
            }
        }
    ]]    
});

//显示加载弹框（“自定义文字”）
function loadingShow(flag){
    var _html = '';
    _html += '<div id="newStatistics">'
    _html +=    '<div>'
    _html +=       '<img src="../common/images/loading-2.gif" alt="">'
    _html +=       '<span>'+flag+'</span>'
    _html +=   '</div>'
    _html +='</div>';
    if(flag !=  ""){
       $("body").append(_html);
    }else if(flag == false ){
         var _mask = document.getElementById('newStatistics');  
        _mask.parentNode.removeChild(_mask);  
    }
}
//loadingShow("正在加载中"); 
setTimeout(function(){
  loadingShow(false);         //关闭加载弹框 
},5000);
   //显示加载弹框（“自定义文字”）



