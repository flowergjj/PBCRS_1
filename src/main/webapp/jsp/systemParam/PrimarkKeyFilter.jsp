<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript" src="${ctx}/js/systemParam/PrimarkKeyFilter.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>主键过滤</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;业务主键:<input type="text"
																					   id="QuerysourceCustId" name="QuerysourceCustId" class="easyui-textbox"/></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;状态:<select id="QueryState" class="easyui-combobox" name="QueryState" style="width:120px;">
					<option value="1">启用</option>
					<option value="0">停用</option>
				</select></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;系统:<input class="easyui-combobox" id="querySourceSys" /></div>
				<div style="display: inline-block;margin-right:10px">
					<a href="javascript:void(0)" class="easyui-linkbutton"
					   iconCls="icon-search" onclick="doQuery()">查询</a>
					&nbsp;&nbsp; <a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-cancel"
									onclick="doClear()">清空</a>

				</div>
			</div>

			
		</div>
		
		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="FilterTable" style="height: 100%; width: 100%;"></table>
		</div>
		
		
		
		
			<div id="addDlg" class="easyui-window" title="新增" modal="true"
		closed="true" iconCls="icon-save"
		style="width: 80%; height: 60%; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="addForm" method="POST"
					style="width: 100%; height: 70%; text-align: center;">
					<div style="padding: 2px 0 ">
						<div style="display: inline-block;margin-right:30px">&nbsp;系统类型:<input  id="sourceSys" name="sourceSys" class="easyui-combobox"
								data-options="required:true,missingMessage:'请选择系统类型'" /></div>
						<div style="display: inline-block;margin-right:30px">&nbsp;主键类型:<input  id="speType" name="speType" class="easyui-combobox"
								data-options="required:true,missingMessage:'请选择主键类型'" /></div>
						<div style="display: inline-block;margin-right:30px">&nbsp;业务主键:<input  id="sourceCustId" name="sourceCustId" class="easyui-textbox"
								data-options="required:true,missingMessage:'请填写业务主键'" /></div>
					</div>
					<div style="padding: 20px 0 ">
						<div style="display: inline-block;margin-right:10px">&nbsp;
							<a
									class="easyui-linkbutton" iconCls="icon-ok"
									href="javascript:void(0)" onclick="doSaveData()">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="easyui-linkbutton" iconCls="icon-cancel"
							   href="javascript:void(0)"
							   onclick="javascript:$('#addDlg').dialog('close')">取消</a>
						</div>

					</div>
				</form>
				<div style="color:red">
					<div>字段释义:</div>
					<div>系统类型:个贷代表个人白名单,信贷代表企业白名单</div>
					<div>主键类型:主键类型为客户号则过滤该客户基本信息、借贷信息、抵质押信息报文,为借贷账户标识码过滤借贷报文,为抵质押合同标识码过滤抵质押报文</div>
					<div>业务主键:对应主键类型唯一识别号(注意业务主键的值不包含14位信息维护机构码)</div>
				</div>
			</div>

		</div>
	</div>
	</div>
</body>
</html>