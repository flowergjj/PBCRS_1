<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="userName" value="${user.userName}"/>
<c:set var="userId" value="${user.userId}"/>
<script type="text/javascript">
function getAppPath(){
	return "${ctx}";
}
function getUser(){
	return "${userName}";
}
function getUserId(){
	return "${userId}";
}
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/demo.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.loadmask.css">
<script type="text/javascript" src="${ctx}/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/json2.js"></script>
<script type="text/javascript" src="${ctx}/js/MaskUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/common/header.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_func.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_common.js"></script>
<script type="text/javascript" src="${ctx}/js/system/sys_code.js"></script>