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
	src="${ctx}/js/report/feedbackFile/feedbackFileImp.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>反馈文件导入</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout"
			data-options="region: 'north', border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;导入日期:<input  id="query_imp_date" style="width: 100px"   class= "easyui-datebox" editable="false" ></input></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;反馈文件名称:<input class="easyui-textbox"
																						 id="query_file_name" style="width: 200px;" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;系统:<input class="easyui-combobox" id="querySourceSys" /></div>
				<div style="display: inline-block;margin-right:10px">
					<a href="javascript:void(0)" class="easyui-linkbutton"
					   iconCls="icon-search" onclick="doQuery()">查询</a>
					&nbsp;&nbsp; <a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-cancel"
									onclick="clearQuery()">清空</a>

				</div>
				<div style="display: inline-block;margin-left:20px">
					<a href="javascript:void(0)" class="easyui-linkbutton"
					   iconCls="icon-save" id="btn_imp">导入反馈文件</a>
				</div>
			</div>
			<%--<table class="queryTable" style="width: 100%;">
				<tr>
					<td style="width: 65px">&nbsp;导入时间：</td>
					<td style="width: 90px" id="query_td"><input  id="query_imp_date" style="width: 100%"  type= "text" class= "easyui-datebox" editable="false" ></input></td>
					
					<!-- <td style="width: 90px"><input class="easyui-combobox"
						style="width: 100%;" id="qry_imp_year" /></td> -->
				<!-- 	<td style="width: 55px">&nbsp;导入月：</td>
					<td style="width: 90px"><input class="easyui-combobox"
						id="qry_imp_month" style="width: 100%;" /></td> -->
					<td style="width: 100px">&nbsp;反馈文件名称：</td>
					<td style="width:110px;"><input class="easyui-textbox"
						id="query_file_name" style="width: 90px;" /></td>
					<td style="width: 70px;"><div i style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" onclick="doQuery()">查询</a>
						</div></td>
					<td><div style=" ">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel" "
						onclick="clearQuery()">清空</a>
						</div></td>
				</tr>

				<tr style="width: 90px; height: 35px">
					<td><div 
							style="">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-save" id="btn_imp">导入</a>
						</div></td>
					<!-- <td style="padding: 0 0 0 10px"><div  style=" ">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-redo" id="btn_exp">导出</a>
						</div></td> -->
						<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>--%>
		</div>

		<!-- 中部表格元素-->
		<div class="easyui-layout "
			data-options="region:'center',border: false">
			<table id="DataListTable" style="height: 100%; width: 100%;"></table>
		</div>

	</div>
		<div id="dialog-importALL" class="easyui-dialog" title="反馈文件导入"
			data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
			style="width: 500px; height: 300px; padding: 5px;">
			<form id='dialog-importdform' method="POST" class="abms-form"
				enctype="multipart/form-data">
				<table>
					
					<tr>

						<td colspan="6" style="width: 380px">选择上传文件:&nbsp;&nbsp;<input
							id="stocklistFile" name="stocklistFile" type="file" size="35"
							 /></td>

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
		
		<div id="dialog-importDetail" class="easyui-dialog" title="反馈详情"
			data-options="closed:true,modal:true"
			style="width: 90%; height: 90%; padding: 0px;">
			
			
				<table id="detialTable" style="height: 560px; width: 100%;"></table>

		
		</div>
</body>
</html>