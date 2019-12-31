/* 表格分页data数据 */
var dataALL=[];
var datagridData;

var sel=0; 
//保存选中的数据
var alldata=[];
var tser=[];
var flgC;
var lang;
$(function () {
	var id=$("#bagid").val();
	$('#box').tree({
    	url : "stratergyBagAction!initTress1.html",
        // data : treejson,
         animate : false,
         checkbox : true,
         cascadeCheck : true,
         onlyLeafCheck : false,
         lines : true,
         dnd : false,
         formatter:function(node){
             var s = node.text;
             if (node.children){
                 s += ' <span style=\'color:#222222\'></span>';
             }
             return s;
         },
         onCheck : function (node,checked) {
            // 遍历选中节点，高亮显示
             treeSelect();
           
             //选中的节点，显示在右边区域
             var node = node;

         },
         onClick : function (node) {
            // 遍历选中节点，高亮显示
             treeSelect();

         },
         onDblClick : function (node) {
            // 遍历选中节点，高亮显示
             treeSelect();

         },
         onLoadSuccess:function(node,data){
        	
        	 var arr=$("#areaIds").val();
        	 
        	 if(arr!=""&&arr!=undefined&&arr!=null){
        		 arr=arr.substr(1,arr.length-2);
        		 if(arr!=""&&arr!=undefined&&arr!=null){
        			 var s= arr.split(",")
                	 treeDefaul(s);  
        		 }
            	
            	 
        	 }
        
        	 $(".tree-checkbox").on("click",function(){
        		 flgC=$(this).hasClass("tree-checkbox1");
        		 var code= $(this).next().children().children();
        		 var codeID=code.attr("data-id");
        		 lang=code.attr("lang");
                 $.ajax({
                     type: "GET",
                     url: "stratergyBagAction!terminalList1.html",
                     data: {organizationSeq:codeID},
                     dataType: "json",
                     async:false,
                     success: function(msg){
                    	 dataALL=msg.rows; 
                    	
                     }
                 });
                

                 $('#dg').datagrid('loadData',dataALL.slice(0,10));
                 $('#pp').pagination('refresh', {
                     total:dataALL.length,
                     pageNumber:1
                 });
      
        	 });
         }

     });
     
	// 遍历选中节点
    treeSelect();
	
	//终端id
    var Tarr=$("#termialIds").val();
    if(Tarr!=""&&Tarr!=undefined){
	 Tarr=Tarr.substr(1,Tarr.length-2);
	 
	 var termialIds= Tarr.split(",")
	if(termialIds.length<=0){
		return ;
	}
	 for (var t = 0; t < termialIds.length; t++) {
		 if(termialIds[t].trim()!=''){
			 tser.push(termialIds[t].trim()); 
			 alldata.push(termialIds[t].trim());
		 }
		 
		 //保存原来的数据
		 
	}
    }
		

    //	表格数据渲染
    //$('#dg').datagrid('loadData',dataALL.slice(0,10));
    $('#dg').datagrid({    
  		scrollbarSize : 0,
  		nowrap : false,// 允许换行
 		url:'stratergyBagAction!terminalList.html',
  		//data:dataALL.slice(0,10),
//	    pagination:true,
        pageSize:10, 
  		method : 'post',
  		louomsg: '正在加载中，请稍等... ',
  		fitColumns : true,// 宽度自适应
  		checkOnSelect : false,// 点击该复选框的时候才会选中或取消
  		iconCls: 'icon-edit',
  		emptyMsg: '<span>无记录</span>',
  		onLoadSuccess : function(data) {// Fires when data is
  		
  			var rows = $('#dg').datagrid('getRows')//获取当前的数据行
  			
  			if(lang==null||lang==undefined){
  				$('.datagrid input').prop('disabled',true);//复选框置灰
  			}else{
  				$('.datagrid input').prop('disabled',false);//复选框置灰

  			}

  			if(flgC){
  				
  				
  				for (var i = 0; i < dataALL.length; i++) {
  					removeByValue(tser, dataALL[i].serial);
				}
  				for (var i = 0; i < rows.length; i++) {
 					 var check=tser.indexOf(rows[i].serial);      		          
     	           if(check!=-1){
     	        	   $('#dg').datagrid('checkRow', i);
     	           }
 				}
  				flgC=undefined;
  	
  			}else if(flgC==false){
  				for (var i = 0; i < dataALL.length; i++) {
  				  if(tser.indexOf(dataALL[i].serial)==-1){
  					  tser.push(dataALL[i].serial);
  				  }
				}
  				
  				for (var i = 0; i < rows.length; i++) {
  					 var check=tser.indexOf(rows[i].serial);      		          
        	           if(check!=-1){
        	        	   $('#dg').datagrid('checkRow', i);
        	           }
    	        }

  			}else{
  				for (var i = 0; i < rows.length; i++) {
      	           var check=tser.indexOf(rows[i].serial);      		          
      	           if(check!=-1){
      	        	   $('#dg').datagrid('checkRow', i);
      	           }
      	        }
  			}
  			
  				
            $('#dg').datagrid('resize');
              toopTip(".normal","正常");
              toopTip(".abnormal","异常");

           /*datagrid 表格复选框选中是否勾选判断*/
           datagridcheck();
         loadSpan();
         boxCheck();
         boxUnCheck();
         
         
  		},
  		
  		onCheck: function (index, row) {  //用于解决点击某行不会高亮
  		   var ar=[];
           if(tser.indexOf(row.serial)==-1){
           	tser.push(row.serial);

           }
           ar.push(row.organizationSeq)
           if(flgC==undefined){
        	   treeDefaul(ar);
           }
          
   			boxCheck();	
		},
		onCheckAll:function(){
   			boxCheck();	
		},
		onUncheckAll:function(){
   			boxUnCheck();	
		},
		onUncheck:function (index, row) {  //用于解决点击某行不会高亮
			if(tser.indexOf(row.serial)!=-1){
				removeByValue(tser, row.serial);
           }
			var t=true;
			var len=0;
			for (var i = 0; i < dataALL.length; i++) {
				//console.log(tser.indexOf(dataALL[i].serial)+"-------------");
				  if(tser.indexOf(dataALL[i].serial)==-1){
					  len++;
					 t=false;
				  }
				}
			//判断当前小区是否还存在勾选项
			if(t==false&&dataALL.length==len){
				var checkNode = $('#box').tree('find', row.organizationSeq);
		        $("#box").tree('uncheck', checkNode.target);	
				   flgC=undefined;  

			}
			

			
			boxUnCheck();
		},
  		columns : [ [
  		     {
  		     field:'id',
  		     title:'',
  		     // width:'5%',
  		     width:60,
  		     checkbox:true,
  		     align:'center',
  		     formatter: function (value, row, index){
  		    	//var a='<a href="javascript:void(0)" onclick="doselect()"></a>'
  		    	return "<a href='javascript:void(0)' onclick='doselect()'></a>";
  		    	}
  		     },
              {
                  field:'terminalName',
                  title:'终端机名称',
                  width:200,
                  align:'center',
              },
              {
                  field:'serial',
                  title:'终端序列号',
                  width:200,
                  align:'center'
              },
              {
                  field:'organizationSeq',
                  title:'',
                  width:200,
                  align:'center',
                  hidden:'true'
              }
  				] ]    
      });
//  	分页
      $('#pp').pagination({
          total:dataALL.length,
//  		beforePageText:'ashjsajshajshj ',
          layout:['first','prev','links','next','last','manual'],
          //	showPageList:true,
          pageSize:10,
          showRefresh:true,
          displayMsg:' ',
//        buttons: [{
////            iconCls:'icon-reload',
//            handler:function(){
//                var aa=$('.pagination-num').val();
//                $('#pp').pagination('select');	// 刷新当前页面
//                $('#pp').pagination('select',aa);
//            }
//        }],
          //pageList:[7,17,27],

          onSelectPage:function (pageNo, pageSize) {
              var start = (pageNo - 1) * pageSize;
              var end = start + pageSize;
              $("#dg").datagrid("loadData", dataALL.slice(start, end));

              $('#pp').pagination('refresh', {
                  total:dataALL.length,
                  pageNumber:pageNo
              });
          }
      });

});

window.onload=function(){/*
	var id=$("#bagid").val();
	//$('#dg').datagrid({url:'stratergyBagAction!terminalList1.html' });
	$('#dg').datagrid({data:datagridData });
	
*/};
//树形结构的模糊查找
function doSearch(value){
	var id=$("#bagid").val();
	$('#box').tree('collapseAll');

		$.ajax({
		 	   type: "post",
		 	   url:'stratergyBagAction!searchTree1.html',
		 	   data:'communityName='+value,
		 	   dataType:'JSON',
		 	   cache:false,
		 	   success:function(msg){
		 		   if(msg.result=="success"){
		 			   if(msg.opendId=="未搜索到小区!"){
		 			   		infoMask('未搜索到小区!');
		 			   }else{
		 				  var opendId =msg.opendId;
			 			  opendId = opendId.split(",");
			 			  for(var i=0;i<opendId.length;i++){
			 				 var node = $('#box').tree('find', opendId[i]);
				 			  $('#box').tree('expandTo', node.target).tree('select', node.target);
			 			  }  
		 			   }
		 		   }else{
		 			  editIndexNums = undefined;
		 			  editJson =undefined;
		 			  infoMask('请输入小区名称!');
		 		   }
		 	   }
		    }); 
	
	
	
	
	
}

function search(id){
	$("#communityCode").val(id);
	
	var rowcheck = $("#"+id).parent().parent().find(".tree-checkbox").hasClass("tree-checkbox1");//判断是否被勾选

	 var code= $("#"+id).children();
	 lang=code.attr("lang");
	//console.log(lang+".,.,..,..,."+code.html());
	 if(lang==null||lang==undefined){
				$('.datagrid input').prop('disabled',true);//复选框置灰
			}else{
				$('.datagrid input').prop('disabled',false);//复选框置灰

			}
	
	   $.ajax({
           type: "GET",
           url: "stratergyBagAction!terminalList1.html",
           data: {organizationSeq:id},
           dataType: "json",
           async:false,
           success: function(msg){
          	 dataALL=msg.rows; 
          	
           }
       });
	   
	   var ist=false;
	   for (var i = 0; i < dataALL.length; i++) {
		   var checkOne=tser.indexOf(dataALL[i].serial);
		   if(checkOne!=-1){
			   ist=true; 
		   }
	   }
	  // console.log(ist+"----------ist----------------------");
	   if(ist){
		   flgC=undefined;  
	   }else{
		  if(rowcheck){
			 flgC=false;  
		  }else{
			   flgC=undefined;  
 
		  }
	   }
	  
       $('#dg').datagrid('loadData',dataALL.slice(0,10));
       $('#pp').pagination('refresh', {
           total:dataALL.length,
           pageNumber:1
       });



}

function searchKey(fuzzyKey){
	var communityCode=$("#communityCode").val();


		$("#dg").datagrid('load',
	 	{
			 organizationSeq:communityCode,
			 fuzzyKey:fuzzyKey
  		}); 

}

$.extend($.fn.datagrid.methods, {
	editCell: function(jq,param){
		return jq.each(function(){
			var opts = $(this).datagrid('options');
			var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor1 = col.editor;
				if (fields[i] != param.field){
					col.editor = null;
				}
			}
			$(this).datagrid('beginEdit', param.index);
			for(var i=0; i<fields.length; i++){
				var col = $(this).datagrid('getColumnOption', fields[i]);
				col.editor = col.editor1;
			}
		});
	}
});


function accept(){
	if (endEditing()){
		var lineNums =undefined;
		if(editIndexNums==undefined){
			infoMask('未修改任何记录!');
		}else{
			if(String(editIndexNums).length>1){
				lineNums = editIndexNums.split(",");   //分割获取到改变值的行号
				for(var i=0;i<lineNums.length;i++){
					var rows=$('#dg').datagrid('getRows');
					var row =rows[lineNums[i]];
					if(i==0){
						editJson = [{
							"id":row.id,
							"terminalName":row.terminalName
						}]
					}else{
						var content={  
								"id":row.id,
								"terminalName":row.terminalName
		            	};
						editJson.push(content);
					}
				}
				edidName(JSON.stringify(editJson));
			}else{
				lineNums = editIndexNums;
				var rows=$('#dg').datagrid('getRows');
				var row =rows[lineNums];
				editJson = [{
					"id":row.id,
					"terminalName":row.terminalName
				}]
				edidName(JSON.stringify(editJson));
				
				
				
				
				
			}
			$('#dg').datagrid('acceptChanges');

            //解決某行編輯終端機名稱后，對應設備狀態的toopTip提示異常問題
            toopTip(".normal","正常");
            toopTip(".abnormal","异常");
		}
	}
}




function reject(){
	$('#dg').datagrid('rejectChanges');
}




/* 点击添加区域按钮，弹窗树默认选择 */
function treeDefaul(Arr){
    var checkArr = Arr;
    
    var checkArrLength = checkArr.length;
  for(var i=0;i<checkArrLength;i++){
    	
        var checkNode = $('#box').tree('find', checkArr[i].trim());
        $("#box").tree('check', checkNode.target);
    }

}


//取消按钮
function cancelArea(){
	sel=0;
	$('.edit').show();
	$('.saveCancel').hide();
	var id=$("#bagid").val();
	
	$('#box').tree({
        url : "stratergyBagAction!initTress.html?id="+id,
		checkbox:false
	});
	$('#dg').datagrid({url:'stratergyBagAction!terminalList.html?id='+id });
}



/*遍历选中节点，高亮显示*/
function treeSelect(){
    $(".tree-checkbox").each(function(){
        if($(this).hasClass("tree-checkbox1")){
            $(this).parent().addClass("tree-node-selected");
        }else{
            $(this).parent().removeClass("tree-node-selected");
        }
    });
}

//删除数组元素
function removeByValue(arr, val) {
	  for(var i=0; i<arr.length; i++) {
	    if(arr[i] == val) {
	      arr.splice(i, 1);
	      break;
	    }
	  }
	}


//保存
function dasave(){
	
	var id=$("#bagid").val();
	$.ajax({
	 	   type: "post",
	 	   url:'stratergyBagAction!updateTermina.html',
	 	   data:{"id":id,"termList":tser.join(",")},
	 	   dataType:'JSON',
	 	   cache:false,
	 	   success:function(msg){	 		  
	 		   if(msg.success){
	 			  parent.$.messager.alert({
						title:'',
						msg:'保存成功！',
						draggable:false,
						fn:function(){
							
      		  			 window.location.href ="stratergyBagAction!goUsableRange.html?id="+id;
						}
					});
					
	 		   }else{
	 			  infoMask("保存失败");     
	 		   }
	 	   }
	    }); 
	
}


