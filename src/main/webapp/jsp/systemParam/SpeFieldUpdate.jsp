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
<script type="text/javascript" src="${ctx}/js/systemParam/SpeFieldUpdate.js"></script>
	<style type="text/css">
		.textbox-icon-disabled{
			cursor: pointer;
		}

	</style>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>报文字段特殊处理</title>
</head>
<body>
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<div style="padding: 2px 0 ">
				<div style="display: inline-block;margin-right:10px">&nbsp;报文类型:<input type="text"
																					   id="report_type" name="report_type" class="easyui-textbox"/></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;报文信息段:<input type="text"
																						id="sgmt_name" name="sgmt_name" class="easyui-textbox"  /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;修改字段含义:<input type="text"
																						 id="comments" name="comments" class="easyui-textbox" /></div>
				<div style="display: inline-block;margin-right:10px">&nbsp;修改字段值:<input type="text"
																						id="spe_cloumn" name="spe_cloumn" class="easyui-textbox" /></div>
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
		style="width: 80%; height: 70%; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="addForm" method="POST"
					style="width: 900px; margin: 0 auto; height: 60%; ">
					<input type="hidden" id="updField" name = 'updField'  />
					<input type="hidden" id="tableName" name = 'tableName'  />
					<input type="hidden" id="conditionDesc" name = 'conditionDesc'  />

					<div style="padding: 10px 0 ">
						<div style="display: inline-block;margin-right:30px">
							&nbsp;待更正字段名称:<input type="text"
												 id="updFieldName" name="updFieldName" class="easyui-textbox"
												 data-options="required:true,
								prompt: '不可直接输入',icons:[{iconCls:'icon-search',handler: function(e){openCheckDlg();}}]"  readonly="true" />
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;待更正字段值:<input type="text"
												id="updFieldValue" name="updFieldValue" class="easyui-textbox"
												data-options="required:true" />
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;报文类型:<input type="text"
											  id="reportType" name="reportType" class="easyui-textbox"
											  data-options="required:true" readonly = 'true' />
						</div>
					</div>
					<div style="padding: 10px 0 ">
						<div style="display: inline-block;margin-right:30px">
							&nbsp;报文段:<input type="text"
											 id="sgmtName" name="sgmtName" class="easyui-textbox"
											 data-options="required:true" readonly = 'true' />
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;条件字段名称:<input type="text"
												id="conditionName" name="conditionName" class="easyui-combobox"
												data-options="required:true"
						/>
						</div>
						<div style="display: inline-block;margin-right:30px">
							&nbsp;条件字段值:<input type="text"
											   id="conditionValue" name="conditionValue" class="easyui-textbox"
											   data-options="required:true"
						/>
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
					<div>待更正字段名称:指所选择的的报文段落中需要更正的字段</div>
					<div>待更正字段值:指所选择的的报文段落中需要更正的字段对应的值</div>
					<div>报文类型:要更正的报文类型</div>
					<div>报文段:报文类型中具体的某一个报文段落</div>
					<div>条件字段名称:用来匹配那些字段对应的值需要做更正</div>
					<div>条件字段值:条件字段对应的值</div>
					<div>整个功能是一个update语句 update 表名(选择待更正字段名称后自动映射) set 待更正字段名称 = 待更正字段值 where 条件字段名称 = 条件字段值 </div>
					<div>该功能在报文数据跑批完成后自动做特殊字段更正</div>
				</div>
			</div>

		</div>
	</div>
	
	  <div id="checkDlg" class="easyui-window" title="参数查询" modal="true"
		closed="true" iconCls="icon-save"
		style="width: 60%; height: 80%; text-align: center; background: #fafafa;">
		
		<div class="easyui-layout" fit="true">
		<div class="easyui-layout" 
			data-options="region: 'north', border: false">
			<table class="queryTable" style="width: 100%">
				<tr>
				<td class="queryTitle" style="width: 10%">&nbsp;字段名称
							</td>
				<td class="queryContent" width="120px"><input type="text"
								id="fieldName" name="fieldName" class="easyui-textbox"/></td>
				
					<td class="queryBtnTd">&nbsp;&nbsp; <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-search" onclick="doFieldQuery()">查询</a>
						<!-- &nbsp;&nbsp; <a href="javascript:void(0)"
						class="easyui-linkbutton" iconCls="icon-cancel"
						onclick="doFieldClear()">清空</a> -->
					</td>
				</tr>
				
			</table>
			
		</div>
		
		<div class="easyui-layout"
			data-options="region: 'center', border: false">
			<table id="fieldInfoTable" style="height: 100%; width: 100%;"></table>
		</div>
		</div>
		
		</div>
	
	</div>
</body>
</html>