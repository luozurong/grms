<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>

<head lang="en">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>编辑策略</title>
     <link rel="stylesheet" type="text/css" href="common/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="common/css/default.css" />
    <link rel="stylesheet" type="text/css" href="common/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="common/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="common/css/common.css" />
    <link rel="stylesheet" type="text/css" href="terminal/css/compile.css" />
</head>

<body>

<div class="header">
    <div class="goback"><a class="goback-a" onclick="javascript:history.back(-1)">返回</a></div>
    <div class="header-edit">编辑</div>
</div>
<form action="" id="ff">
<input type="hidden" id="bagId" name="id" value="${strategyBag.id}">

<div class="content">
    <div class="content1">
        <div class="title" id="121378124">
            <strong>*</strong>策略名称<span class="cl1-2">(20字以内)</span>
        </div>
        <c:if test="${strategyBag.type!=0}">
        <input class="easyui-validatebox"  type="text" onblur="Judgename()" name="name" id="rule_text" value="${strategyBag.name}" maxlength="20" />
    	</c:if>
        <c:if test="${strategyBag.type==0}">
        <input class="easyui-validatebox"  type="text" name="name" id="rule_text" value="${strategyBag.name}" maxlength="20" disabled/>
    	</c:if>
    </div>
    <div class="content2">
        <div class="title">
            <strong>*</strong>策略设置
        </div>
        <div class="content2_box" >
            <c:forEach items="${stratergyList}" var="st">
         <div id="${st.id}" class="rule-box">
                <div class="rule-delete"></div>
                <div class="op_time_box">
                    <div >每天开启时段:</div>
                    <div class="time_box">
                        <%--<div class="time_bt time-adjust1">--%>
                        <%--</div>--%>
                        <div class="time_bt time-select1" id="time-select1${st.id}">
                        </div>
                    </div>
                    <div>至</div>
                    <div class="time_box" >
                        <%--<div class="time_bt time-adjust2">--%>
                        <%--</div>--%>
                        <div class="time_bt time-select2" id="time-select2${st.id}">
                        </div>
                    </div>
                </div>

                <div class="set_brightness_box">
                    <div class="text_fl" >
                        亮度设置:
                    </div>
                    <div class="logo-1 fl"></div>
                    <div class="text_fl width_130" >
                        <div class="swip_box" >
                            <div class="ssa_add" id="ssa_add${st.id}" >
                            </div>
                            <div class="ssa" id="ssa${st.id}">
                            </div>
                        </div>
                    </div>
                    <div class="num_box clearfix" >
                        <div class="box1">
                            <input id="liangdu${st.id}" class="liangdu fl" type="text" value=""/>
                            <div class="num-deng fl">%</div>
                        </div>
                    </div>
                    <div class="text_fl" >
                        视频音量设置：
                    </div>
                    <div class="logo-2 fl" ></div>
                    <div class="text_fl width_130" >
                        <div class="swip_box"  >
                            <div class="music_add" id="music_add${st.id}">
                            </div>
                            <div class="music" id="music${st.id}">
                            </div>
                        </div>
                    </div>
                    <div class="num_box clearfix">
                        <div class="box1">
                            <input id="yinliang${st.id}" class="yinliang fl" type="text" value="" />
                            <div class="num-yin fl">%</div>
                        </div>
                    </div>

                    <div class="text_fl" >
                        音频广告音量：
                    </div>
                    <div class="logo-3 fl" ></div>
                    <div class="text_fl width_130" >
                        <div class="swip_box"  >
                            <div class="audiomusic_add" id="audiomusic_add${st.id}">
                            </div>
                            <div class="audiomusic" id="audiomusic${st.id}">
                            </div>
                        </div>
                    </div>
                    <div class="num_box clearfix">
                        <div class="box1">
                            <input id="audioyinliang${st.id}" class="audioyinliang fl" type="text" value="" />
                            <div class="num-yin fl">%</div>
                        </div>
                    </div>
                </div>
            </div>
			</c:forEach>

            <div class="add-rule">+添加</div>

        </div>
    </div>



</div>
<div class="save_box">
    <div class="save btnStyle1" onclick="dosave()">保存</div>
</div>
</form>

<script type="text/javascript" src="common/easyui/jquery.min.js"></script>
<script type="text/javascript" src="common/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="common/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="common/plugin/common.js"></script>
<script type="text/javascript" src="terminal/js/compile.js"></script>
</body>

</html>