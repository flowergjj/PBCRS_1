<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript"
	src="${ctx}/js/dataTestQuery/IndDataIntegrityTest.js"></script> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>个人数据报送完整性</title>
</head>
<body style="padding:0px">
	<div>
		<div   style="border:1px solid rgb(149, 184, 231);position:fixed;background-color:white;width:100%">
			<div>
<!-- 				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;系统:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="querySourceSys" class="easyui-combobox" name="dept"
						style="width: 100px;"/>
				</div> -->
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;调度日期:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="etlDate" type="text" class="easyui-datebox"
						data-options="editable:false" style="width: 120px" />
				</div>

				<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					</div>
				</div>
				<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel" onclick="clearQuery()">清空</a>
					</div>
				</div>
			</div>

		</div>
		<div style="height:50px"></div>
      <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">客户资料统一管理</h2>
			<table id="infoManager" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">客户资料分系统管理</h2>
			<p align="center" style="margin:0">个贷系统</p>
			<table id="PLN_infoManager" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">网贷系统</p>
			<table id="ILN_infoManager" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">小微系统</p>
			<table id="SLN_infoManager" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">个人基本信息记录增量数据完整性比对</h2>
			<table id="indBaseAddCheck" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">非循环贷账户（D1）业务量比对结果表</h2>
			<p align="center" style="margin:0">第一个月</p>
			<table id="d1FirstCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">第二个月</p>
			<table id="d1SecondCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">第三个月</p>
			<table id="d1ThirdCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">循环额度下分账户（R4）业务量比对结果</h2>
			<p align="center" style="margin:0">第一个月</p>
			<table id="r4FirstCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">第二个月</p>
			<table id="r4SecondCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">第三个月</p>
			<table id="r4ThirdCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">个人授信协议业务量比对结果</h2>
			<table id="indCtrCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">抵（质）押物信息业务量比对结果</h2>
			<table id="modCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">信息关联性比对结果</h2>
			<p align="center" style="margin:0">个人基本信息</p>
			<table id="baseInfoJoinCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">个人授信协议信息</p>
			<table id="ctrInfoJoinCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
			<p align="center" style="margin:0">抵（质）押物信息</p>
			<table id="modInfoJoinCheckResult" style="width: 100%;" data-options="nowrap:false">
			</table>
		</div>

		<div id="stockMgs">
		
		</div>

	</div>
	<!-- <div id="etlLog"></div> -->
</body>
</html>