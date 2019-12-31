<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>默认参数设置</title>
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <link rel="stylesheet" type="text/css" href="common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="terminal/css/defaultparameters.css" />
</head>
<body>
    <div class="header">
        <div class="goback"><a class="goback-a" onclick="javascript:history.back(-1)">返回</a></div>
        <div class="header-edit">默认策略</div>
    </div>
    <div class="wrap">
        <div class="content-wrap">
            <div class="content">
            <input type="hidden" id="birghtness" value="${parameters.birghtness}"/>
            <input type="hidden" id="videoVolume" value="${parameters.videoVolume}"/>
            <input type="hidden" id="audioVolume" value="${parameters.audioVolume}"/>
                <div class="brightness clearfix">
                    <div class="text-1 fl">默认亮度：</div>
                    <div class="logo-1 fl"></div>
                    <div class="slider-pattern1 fl">
                        <input class="slider1"/>
                        <div class="slider-run1"></div>
                    </div>
                    <div class="percent-1 fl clearfix">
                        <input class="input1 fl" type="text" value=""/>
                        <div class="fl">%</div>
                    </div>
                </div>
                <div class="volume clearfix">
                    <div class="text-2 fl">视频广告音量：</div>
                    <div class="logo-2 fl"></div>
                    <div class="slider-pattern2 fl">
                        <input class="slider2"/>
                        <div class="slider-run2"></div>
                    </div>
                    <div class="percent-2 fl clearfix">
                        <input class="input2 fl" type="text" value=""/>
                        <div class="fl">%</div>
                    </div>
                </div>
                <div class="volume clearfix">
                    <div class="text-2 fl">音频广告音量：</div>
                    <div class="logo-2 fl"></div>
                    <div class="slider-pattern2 fl">
                        <input class="slider3"/>
                        <div class="slider-run3"></div>
                    </div>
                    <div class="percent-2 fl clearfix">
                        <input class="input3 fl" type="text" value=""/>
                        <div class="fl">%</div>
                    </div>
                </div>
                <div class="save btnStyle1" onclick="dosave()">保存</div>
            </div>
        </div>
    </div>

    <script type="text/javascript"	src="common/easyui/jquery.min.js"></script>
    <script type="text/javascript"	src="common/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="common/easyui/locale/easyui-lang-zh_CN.js" ></script>
    <script type="text/javascript"	src="common/plugin/common.js"></script>
    <script type="text/javascript"	src="terminal/js/defaultparameters.js"></script>
</body>
</html>