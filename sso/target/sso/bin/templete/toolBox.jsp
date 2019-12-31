<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--框架必需start-->
<script type="text/javascript" src="../js/jquery-1.4.js"></script>
<script type="text/javascript" src="../js/framework.js"></script>
<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
<link  rel="stylesheet" type="text/css" id="skin"/>
<!--框架必需end-->

<!--修正ie6支持png图片start-->
<script type="text/javascript" src="../js/method/pngFix/supersleight.js"></script>
<!--修正ie6支持png图片end-->

<!--鼠标移入图标变色start-->
<script>
$(function(){
	$(".navIconSmall").hover(function(){
		$(this).addClass("navIconSmall_hover");
	},function(){
		$(this).removeClass("navIconSmall_hover");
	})
})
</script>
<!--鼠标移入图标变色end-->
<body>
			<div class="navIconSmall" onclick="top.Dialog.alert('个人中心')">
        		<img src="../icons/png/01.png"/><br />
				个人中心
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('个人收藏夹')">
        		<img src="../icons/png/02.png"/><br />
				个人收藏夹
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('高级搜索')">
        		<img src="../icons/png/03.png"/><br />
				高级搜索
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('文档管理')">
        		<img src="../icons/png/04.png"/><br />
				文档管理
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('帮助中心')">
        		<img src="../icons/png/05.png"/><br />
				帮助中心
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('个人存储空间')">
        		<img src="../icons/png/06.png"/><br />
				个人存储空间
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('附件管理')">
        		<img src="../icons/png/07.png"/><br />
				附件管理
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('通知消息')">
        		<img src="../icons/png/08.png"/><br />
				通知消息
        	</div>
			<div class="navIconSmall" onclick="top.Dialog.alert('回收站')">
        		<img src="../icons/png/09.png"/><br />
				回收站
        	</div>
			<div class="clear"></div>
</body>
</html>