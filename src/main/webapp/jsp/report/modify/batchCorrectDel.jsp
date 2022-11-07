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
	src="${ctx}/js/report/modify/batchCorrectDel.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>批量更正</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="padding: 0px;"
			data-options="region: 'north'">
			<table class="queryTable" style="width: 100%;">
				<tr>
					<td style="width: 65px">&nbsp;导入时间：</td>
					<td style="width: 90px"><input class="easyui-datebox"
								id="queryRptDate" /></td>

					<td style="width: 65px">&nbsp;文件名称：</td>
					<td style="width: 110px;"><input class="easyui-textbox"
						id="queryfileName" style="width: 90px;" /></td>
					<td style="width: 70px;"><div i style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" onclick="doQuery()">查询</a>
						</div></td>
					<td><div style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel" "
						onclick="doClear()">清空</a>
						</div></td>
				</tr>
				<tr style="width: 90px;">
	<!-- 				<td><div style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-save" id="btn_imp">导入</a>
						</div></td>
					<td><div style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-save" id="btn_down">模板下载</a>
						</div></td> -->

					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr> 
			</table>
		</div>

		<!-- 中部表格元素-->
		<div class="easyui-layout "
			data-options="region:'center',border: false">
			<table id="DataListTable" style="height: 100%; width: 100%;"></table>
		</div>

	</div>
	<div id="dialog-importALL" class="easyui-dialog" title="批量更正文件导入"
		data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
		style="width: 500px; height: 300px; padding: 5px;">
		<form id='dialog-importdform' method="POST" class="abms-form"
			enctype="multipart/form-data">
			<table>

				<tr>

					<td colspan="6" style="width: 380px">选择上传文件:&nbsp;&nbsp;<input
						id="batchCorrectFile" name="batchCorrectFile" type="file" size="35" /></td>

				</tr>
				<tr>
					<td style="width: 55px;"><a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-save" id="btn_ok">确定</a></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>


			</table>

		</form>
	</div>

	<div id="dialog-templateDown" class="easyui-dialog" title="模板下载"
		data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
		style="width: 40%; height: 300px; padding: 5px;">



		<table align='center'>
			<tr>
				<td class="queryTitle">&nbsp;报文类型：</td>
				<td class="queryContent"><input class="easyui-combobox"
					id="queryRptType" /></td>
			</tr>
		</table>
		<div style='height:50px'></div>
		
		<div align='center'>
			<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="download1();">下载</a>
		</div>

	</div>
	
	<div id="dialog-BatchInfo" class="easyui-dialog" title="详情"
				data-options="closed:true,modal:true"
				style="width: 1000px; height: 800px; padding: 5px;">
				<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="batchInfoTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
		
	<div id="dialog-DelRpt" class="easyui-dialog" title="批量删除并报送"
		data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
		style="width: 1000px; height: 800px; padding: 5px;">

		<table>
			<tr>
				<td class="queryTitle">&nbsp;数据日期：</td>
				<td class="queryContent"><input class="easyui-datebox"
					id="queryDate" /></td>
				<td class="queryTitle">&nbsp;报文类型：</td>
				<td class="queryContent"><input class="easyui-combobox"
					id="queryRptTypeDel" /></td>
				<td class="queryTitle"></td>	
						<td style="width: 70px;"><div i style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 80px" onclick="delAndRpt();">删除并报送</a>
						</div></td>
					<td><div style="">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 80px" onclick="delAndRptClear();">清空</a>
						</div></td>
			</tr>
		</table>
		
				<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 85%; width: 100%;">
						<table id="DelRptTable" style="height: 100%; width: 100%;"></table>
					</div>

	</div>
</body>
</html>