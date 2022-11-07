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
<script type="text/javascript" src="${ctx}/js/systemParam/ParamManage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>借贷产品报送管理</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;借贷产品代码:<input  id="QueryprodId" name="QueryprodId" class="easyui-textbox"/></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;借贷产品代码含义:<input id="QueryprodDesc" name="QueryprodDesc" class="easyui-textbox"  /></div>
				<div style="display: none;margin-right:10px">&nbsp;业务类型:<input id="QueryBusiType" name="QueryBusiType" class="easyui-combobox" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;系统类型:<input class="easyui-combobox" id="querySourceSys" /></div>
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
			<table id="ParamTable" style="height: 100%; width: 100%;"></table>
		</div>
		
		
		
		
    <div id="addDlg" class="easyui-window" title="参数新增" modal="true"
		closed="true" iconCls="icon-save"
		style="width: 80%; height: 60%;  background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="addForm" method="POST"
					style="width: 900px; height: 60%;margin: 0 auto; ">
					<div style="padding: 10px 0 ">
						<div style="display: inline-block;margin-right:30px">
							&nbsp;系统类型:<input type="text"
											  id="sourceSys" name="sourceSys" class="easyui-combobox"
											  data-options="required:true,missingMessage:'请选择系统类型'" />
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;借贷产品代码:<input type="text"
											  id="prodId" name="prodId" class=" easyui-textbox"
											  data-options="required:true,missingMessage:'请填写借贷产品代码'" />
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;借贷产品代码含义:<input type="text"
												id="prodDesc" name="prodDesc" class="easyui-textbox"
												data-options="required:true,missingMessage:'请填写借贷产品代码含义'" />
						</div>
					</div>
					<div style="padding: 10px 0 ">
						<div style="display: inline-block;margin-right:30px">
							&nbsp;是否信用免担保:<input type="text"
												 id="noGuarFlag" name="noGuarFlag" class="easyui-combobox"/>
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;是否报送该借贷业务:<input type="text"
												   id="isSubmission" name="isSubmission" class="easyui-combobox"/>
						</div>
					</div>
					<div style="padding: 20px 0 ;text-align: center">
						<a
								class="easyui-linkbutton" iconCls="icon-ok"
								href="javascript:void(0)" onclick="doSaveData()">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="easyui-linkbutton" iconCls="icon-cancel"
						   href="javascript:void(0)"
						   onclick="javascript:$('#addDlg').dialog('close')">取消</a>
					</div>

				</form>
				<div style="color: red">
					<div>字段解释:</div>
					<div>系统类型:个贷代表个人,信贷代表企业</div>
					<div>借贷产品代码:客户发生贷款对应的业务品种代码</div>
					<div>借贷产品代码含义:业务品种代码中文含义</div>
					<div> 是否信用免担保:表示该类业务贷款客户担保方式是否为信用免担保,信用免担保选项一般为没有相关还款责任人的贷款业务设置</div>
					<div> 是否报送该借贷业务:表示该类业务贷款客户征信信息是否需要报送,默认为空-需要报送</div>
				</div>
			</div>

		</div>
	</div>
	</div>
</body>
</html>