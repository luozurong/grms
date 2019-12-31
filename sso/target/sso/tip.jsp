<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>tip</title>
<style type="text/css">
.img-min {
	width: 64%;
	margin: 85px auto 0;
}

.img-min img {
	width: 100%;
	height: 100%;
}

.title {
	text-align: center;
	font-size: 26.7px;
	font-weight: 700;
	padding-top: 16px;
	color: rgb(106, 106, 106);
}

.describe {
	text-align: center;
	color: rgb(34, 34, 34);
	font-size: 17.3px;
	padding-top: 26px;
}

@media only screen and (min-width: 640px) {
	.img-min {
		width: 443px;
		height: 222px;
		margin: 85px auto 0;
		background: url(<%=request.getContextPath()%>/images/ic_404.png);
		background-size: cover;
	}
	.img-min img {
		display: none;
	}
	.title {
		color: rgb(76, 150, 234);
		font-size: 42px;
		padding-top: 18px;
	}
	.describe {
		padding-top: 18px;
		color: rgb(106, 106, 106);
		font-size: 18px;
	}
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<%
String[] addr=pageContext.getRequest().getParameterValues("code");
request.setAttribute("addr", addr[0]);
%>
<script type="text/javascript">
$(function(){
	closeProgress();
	var tip="${addr}";
	$("#title").text(tip);
});
</script>
</head>
<body>
	<div>
		<div class="img-min">
			<img src="<%=request.getContextPath()%>/images/ic_404.png" />
		</div>
		<div class="title" id="title"></div>
		<div class="describe">哎呀，页面出错啦！</div>
	</div>
</body>
</html>
