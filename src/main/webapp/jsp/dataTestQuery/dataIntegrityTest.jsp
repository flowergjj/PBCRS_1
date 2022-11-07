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
	src="${ctx}/js/dataTestQuery/dataIntegrityTest.js"></script> 
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>数据报送完整性</title>
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
			<h2 style="margin: 0px" align="center">借贷业务实际开展情况表</h2>
			<table id="DcTable" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">担保业务实际开展情况表</h2>
			<table id="GuTable" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">企业基本信息比对结果表</h2>
			<table id="BaseTable" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">财务报表信息比对结果表</h2>
			<table id="ReportTable" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">非循环贷账户（D1）业务量比对结果表</h2>
			<table id="D1Table" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">贴现账户（D2）业务量比对结果表</h2>
			<table id="D2Table" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">循环额度下分账户（R4）业务量比对结果表</h2>
			<table id="R4Table" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">授信协议业务量比对结果表</h2>
			<table id="ClTable" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">融资担保账户（G1）业务量比对结果表</h2>
			<table id="G1Table" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	  <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">非融资担保账户（G2）业务量比对结果表</h2>
			<table id="G2Table" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">支付担保账户（G3）业务量比对结果表</h2>
			<table id="G3Table" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
	   <div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">抵（质）押合同业务量比对结果表</h2>
			<table id="DzyTable" style="width: 100%;" data-options="nowrap:false">
			</table>
			
			
		</div>
		
		<div id="stockMgs">
		
		</div>

	</div>
	<!-- <div id="etlLog"></div> -->
</body>
</html>