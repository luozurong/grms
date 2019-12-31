var data;
var datagrid;
function doSearch2(value){
	//alert(value);
	datagrid.datagrid('load', {
		name : ($.trim($('input[name="forwardParams"]').val())=='请输入搜素内容')?'':$.trim($('input[name="forwardParams"]').val()),
	});
}
$(function () {


	var volume=$("#volume").val();
	var birghtness=$("#birghtness").val();
    //	表格数据渲染
	datagrid=$('#dg').datagrid({
        //		width:,
//        height:540,
//				rownumbers:true,   //是否显示行号
        scrollbarSize:0,
        nowrap:true,//允许换行
//			    url:'datagrid_data1.json',
//			    method:'get',
        //url:"terminalStatergyAction!getParameterstrategyList.html",
        fitColumns:true,//宽度不自适应
//	    pagination:true,
        pageSize:10,   //表格中每页显示的行数
        //   pageList:[6,10,15],
        checkOnSelect:false,//点击该复选框的时候才会选中或取消
        emptyMsg: '<span>无记录</span>',
        onLoadSuccess: function (data) {//Fires when data is loaded successfully.
			$('#pp').pagination('refresh', {
				total: data.total,
				pageNumber: data.page
			});
            $('#dg').datagrid('resize');
            toopTip(".seePic","查看");
            toopTip(".editPic","编辑");
            toopTip(".delPic","删除");
            toopTip(".viewarea","小区范围");
		},
        columns:[[
            /*{
                field:'id',
                title:'',
                // width:'5%',
                width:60,
                checkbox:true,
                align:'center'
            },*/
            {
                field:'name',
                title:'策略名称',
                //width:'16%',
                width:180,
                align:'center'
            },
            {
                field:'parsList',
                title:'参数设置',
                //width:'26%',
                width:310,
                align:'center',
                formatter: function(value,row,index){
                	if(value!=null){
                		var list=value.split(",");
                    	var htm="";
                    	if(list.length>4){
                    		htm=list[0]+"<br>"+list[1]+"<br>"+list[2]+"<br>"+".......";
                    		return htm;
                    	}else{
                    		for(var i=0;i<list.length;i++){
                    			htm=htm+list[i]+"<br>";
                    		}
                    			
                    		return htm;
                    	}
                	}
                	
     			},
                	
                align:'left',
                halign:'center'
            },
           /* {
                field:'addressList',
                title:'适用区域',
                // width:'26%',
                width:300,
                align:'center',
                formatter: function(value,row,index){
                	if(value!=null){
                		var list=value.split(",");
                    	var htm="";
                    	if(list.length>4){
                    		htm=list[0]+"<br>"+list[1]+"<br>"+list[2]+"<br>"+"...";
                    		return htm;
                    	}else{
                    		for(var i=0;i<list.length;i++){
                    			htm=htm+list[i]+"<br>";
                    		}
                    			
                    		return htm;
                    	}
                	}
                	
     			}
               
            },*/
            /*{
                field:'enableDate',
                title:'全部状态',
                //width:'10%',
                width:110,
                align:'center',
                formatter: function(value,row,index){
         				if (value==1){
         					return '生效中';
         				} else{
         					return '已失效';
         				}
         			}
            },*/
            {
                field:'handle',
                title:'操作',
                // width:'18%',
                width:164,
                align:'center',
                formatter: function(value,row,index){
                	var del='';
                	var range='';
                	var id=row.id;
                    var see = '<span class="handlePic seePic" href="#" onclick="godetail('+"'"+id+"'" + ')"></span>';
                    var edit = '<span class="handlePic editPic" href="#" onclick="goedit('+"'"+id+"'" + ')"></span>';
                    if(row.type!=0){
                    	del= '<span class="handlePic delPic" href="javascript:void(0)" onclick="dodel('+"'"+id+"'" + ')"></span>';
                    	range='<span class="handlePic viewarea" href="#" onclick="gorange('+"'"+id+"'" + ')"></span>';
                    }

                    return see+edit+range+del;
                }

            }
        ]]
    });

	
	
//	分页
//	分页
	$('#pp').pagination({ 
		//total:data.length, 
//		beforePageText:'ashjsajshajshj ',
		layout:['first','prev','links','next','last','manual'],
//		showPageList:true,
		pageSize:10,
//		pageList:[7,17,27],
		
		onSelectPage:function (pageNo, pageSize) {
        	
            var start = (pageNo - 1) * pageSize;
            var end = start + pageSize;
            console.log("pageNo--"+pageNo);
            var forwardParams = ($.trim($('input[name="forwardParams"]').val())=='请输入搜素内容')?'':$.trim($('input[name="forwardParams"]').val())
            console.log("forwardParams--"+forwardParams);
            $("#dg").datagrid("load",{pageNumber:pageNo,name:forwardParams} );
            //$("#dg").datagrid("load",{page:pageNo} );
            $('#pp').pagination('refresh', {
                //total:data.length,
                pageNumber:pageNo
            });
            toopTip(".seePic","查看");
            toopTip(".editPic","编辑");
            toopTip(".delPic","删除");
            toopTip(".viewarea","小区范围");
        }
	}); 
		
		
		$('.delete').click(function(){
	        var row = $('#dg').datagrid('getSelections');
	        var ids = [];
	        if(row.length !== 0 ){
	        	parent.$.messager.confirm('','确定删除终端策略？',function(boolean){
	        		 if(boolean){//确定删除操作
	                      for (var i = 0; i < row.length; i++) {
	  						ids.push(row[i].id);
  						}
	                  	$.ajax({
	                  		url : 'terminalStatergyAction!deleteTerminal.html',
							data : {
								id : ids.join(',')
							},
							dataType : 'json',
							success : function(response) {
								successMask('删除成功！','terminalStatergyAction!goParameterstrategyList.html');
							}
	                  	});
	        		 }else{
	        			 $('#dg').datagrid('unselectAll');
							$('#dg').datagrid('reload');//重新加载行 
	        		 }
	        	});
	        
	        }else{
	        	infoMask('请选择要删除的项!');
	        }
	        
	});

//        warnMask("系统繁忙，请稍后再试！");
	
});


window.onload=function(){ 
	 $('#dg').datagrid({
			url : 'stratergyBagAction!getParameterstrategyList.html',
			pageSize : 10,
		});
} 



            
function godetail(id){
	//alert(id);
	window.location.href = "stratergyBagAction!goStrategyBagDetail.html?id="
		+ id;
}
function goedit(id){
	//alert(id);
	window.location.href = "stratergyBagAction!goStratergyBagEdit.html?id=" + id;
}

function gorange(id){
	//alert(id);
	window.location.href = "stratergyBagAction!goUsableRange.html?id=" + id;
}



function dodel(id){
	parent.$.messager.confirm('','确定删除终端策略？',function(boolean){
        if(boolean){
            $.ajax({
                url : 'stratergyBagAction!deleteStrategyBag.html',
                data : {
                    id : id
                },
                dataType : 'json',
                success : function(response) {
                    //$("#dg").datagrid('load');
                    successMask('删除成功！','stratergyBagAction!goParameterstrategyList.html');
                }
            });
        }else{
        	$('#dg').datagrid('unselectAll');
			$('#dg').datagrid('reload');//重新加载行
        }

	});
}
