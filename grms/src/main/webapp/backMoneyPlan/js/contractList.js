
//需要隐藏的按钮
var dataList;
//初始化按钮
$(function(){
	 search();
});


//查询数据
function search(){
	//var condition =$('#condition').val();
	
    $.ajax({  
        type : "GET",  //提交方式  
        url : "/grms/backMoneyPlan/getContractList",//路径  
        dataType : "json",//数据，这里使用的是Json格式进行传输  
        //data:  {"condition":condition},  
        success : function(result) {//返回数据根据结果进行相应的处理  
            if ( result.success) {  
            	var data2=result.rows;
            	var total=result.total;
            	refreshFunc(data2);
            	paginationpage(total);
            } else {  
            	infoMask('加载列表失败');
            }  
        }  
    }); 
}

//刷新表格数据
function initDg(pageNo,pageSize){
	var xL=(pageNo-1)*pageSize;
	var yL=pageNo*pageSize;
	var data1=dataList.slice(xL,yL);
//	表格数据渲染
    $('#dg').datagrid({
        border:true,
        scrollbarSize:0,
        nowrap:false,//允许换行
        data:data1,
        emptyMsg:'<span>无记录</span>',
        onLoadSuccess:function(){ //dom操作
            $('#dg').datagrid('resize');
        },
        columns:[[
      	        {
      	            field:'ck',
      	            title:'',
      	            checkbox:true,
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'backMoneyPlanCode',
      	            title:'收款计划ID',
      	            width:50,
      	            align:'center',
      	        },
      	        {
      	            field:'projectCode',
      	            title:'项目ID',
      	            width:50,
      	            align:'center'
      	        },
      	        {
      	            field:'contractCode',
      	            title:'合同ID',
      	            width:50,
      	            align:'center',
      	        },
      	        {
      	            field:'contractName',
      	            title:'合同名称',
      	            width:50,
      	            align:'center',
      	        },
      	        {
      	            field:'money',
      	            title:'合同总金额',
      	            width:50,
      	            align:'center'
      	        }
      	    ]]
    });
}

function pagenationPage(){
//  分页
    $('#pp').pagination({
        total:dataList.length,
        layout:['list','first','prev','links','next','last','manual'],
        emptyMsg: '<span>无记录</span>',
        showRefresh:true,
        displayMsg:' ',
        pageList:[10,20,30],
        onSelectPage:function (pageNo, pageSize) {
        	initDg(pageNo,pageSize);
        }
    });
    $(".pagination-page-list").parent().append("条");
    $(".pagination-page-list").parent().prepend("共计"+dataList.length+"条,每页显示： ");

}


