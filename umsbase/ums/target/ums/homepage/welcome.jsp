<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%;">
	<head>
		<meta charset="UTF-8">
		<title>欢迎登录</title>
		<style type="text/css">
			html,body{
				padding: 0;
				margin: 0;
				border: 0;
			}
			body{
		        background-color:#d9d9d9;
			}
			.mainBox{
				position: relative;
				width: 100%;
				height: 100%;
				background-image: url(images/bg1.png);
				background-size: 100% ;
				background-repeat: no-repeat;
			}
			.welcomeP{
				position: absolute;
				font-size: 24px;
				font-family: "微软雅黑";
				left: 50%;
				margin-left: -120px;
				top:40%;
			}
			@media screen and (min-width: 1540px) {
				.mainBox{
					background-size: 1540px;
				}
			}
		</style>
	</head>
	<body style="height: 100%;">
		<div class="mainBox"></div>
	</body>
</html>
