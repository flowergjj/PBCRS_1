<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="uname" value="${user.userName}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctx}/js/system/main.js"></script>
<script type="text/javascript" src="${ctx}/js/nat/modify/InSpcInf.js"></script>
<title>个人信贷信息特殊事件</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div id="cc" class="easyui-layout" style="padding: 0px;"
			data-options="region: 'north'">
			
				<div  style="padding: 2px;width:85px; display: inline-block;">
					&nbsp;日期:</div>
				<div style="padding: 2px; display: inline-block;">
					<input id="queryRptDate" type="text" class="easyui-datebox" data-options="editable:false"
						style="width: 120px" />
				</div>
			
				<div  style="padding: 2px;width:85px; display: inline-block;">&nbsp;账户标识码:</div>
				<div style="padding: 2px; display: inline-block;">
					<input id="queryAcctCode" class="easyui-textbox"  style="width: 180px;" />
				</div>
				
				
				<div style="padding: 4px; display: inline-block;">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					</div>
				</div>
				<div style="padding: 4px; display: inline-block;">
					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel" onclick="clearQuery()">清空</a>
					</div>
				</div>
		</div>

		<!-- 中部表格元素-->
		<div  class="easyui-layout " data-options="region:'center',border: false">
			 <table id="DataListTable" style="height: 100%; width: 100%;"></table> 
		</div>

	</div>
	
	<div id="dialog-add" class="easyui-dialog" title="添加"
		data-options="closed:true,modal:true"
		style="width: 600px; height: 300px; padding: 5px;">
		<form id="add" method="POST"
			style="height: 100%; width: 100%">
			<!-- 主键 -->
			<!-- <input type="hidden" name="comRecInfSgmtSeqNo"> -->
			<!-- 报送标志 -->
			<!-- <input type="hidden" name="reportFlag"> -->
			<!-- 系统号 -->
			<!-- <input type="hidden" name="sourceSys"> -->
			<!-- 报送日期 -->
			<!-- <input type="hidden" name="etl_Date"> -->
			<!-- 信息记录类型 -->
			<!-- <input type="hidden" name="infRecType"> -->
			
			<table style="width: 100%;" id='addTable'>

				<tr height="40px">
					<td>&nbsp;账户标识码:</td>
					<td><input checkId='Enum_AM' name="acctCode"  id="acctCode"
						class="easyui-textbox" data-options="" style="width: 130px" /></td>
					<td>&nbsp;事件类型:</td>
					<td><input  name="opetnType"  id="opetnType"
						class="easyui-combobox" data-options="" style="width: 130px" /></td>
				</tr>
				<tr height="40px">

					<td>&nbsp;生效标志:</td>
					<td><input  name="flag"  id="flag"
						class="easyui-combobox" data-options="" style="width: 130px" /></td>
					<td>&nbsp;月份(yyyy-MM):</td>
					<td><input  name="month"  id="month"
						class="easyui-textbox" data-options="" style="width: 130px" /></td>
				</tr>
				<tr height="40px">

					<td>&nbsp;报送日期:</td>
					<td><input  name="rptDate"  id="rptDate"
						class="easyui-datebox" data-options="" style="width: 130px" /></td>
					<td>&nbsp;系统号:</td>
					<td><input  name="sourceSys"  id="sourceSys"
						class="easyui-combobox" data-options="" style="width: 130px" /></td>
				</tr>

			</table>
			<div style="clear: both; padding: 20px" align="center">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 100px" onclick="submit()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 130px" onclick="$('#dialog-add').dialog('close')">取消</a>
			</div>
		</form>

	</div>
	
	<div id="dialog-update" class="easyui-dialog" title="修改"
		data-options="closed:true,modal:true"
		style="width: 600px; height: 300px; padding: 5px;">
		<form id="update" method="POST"
			style="height: 100%; width: 100%">
			<!-- 主键 -->
			<input type="hidden" name="inSpcEvtDscInfSeqNo" >
			<!-- 报送标志 -->
			<input type="hidden" name="reportFlag">
			<!-- 系统号 -->
			<!-- <input type="hidden" name="sourceSys"> -->
			<!-- 报送日期 -->
			<input type="hidden" name="etl_Date">
			<!-- 信息记录类型 -->
			<input type="hidden" name="infRecType">
			
			<table style="width: 100%;" id='addTable'>

				<tr height="40px">
					<td>&nbsp;账户标识码:</td>
					<td><input checkId='Enum_AM' name="acctCode"  id="acctCode"
						class="easyui-textbox" data-options="" style="width: 130px" /></td>
					<td>&nbsp;事件类型:</td>
					<td><input  name="opetnType"  id="opetnTypeU"
						class="easyui-combobox" data-options="" style="width: 130px" /></td>
				</tr>
				<tr height="40px">

					<td>&nbsp;生效标志:</td>
					<td><input  name="flag"  id="flagU"
						class="easyui-textbox" data-options="" style="width: 130px" /></td>
					<td>&nbsp;月份(yyyy-MM):</td>
					<td><input  name="month"  id="month"
						class="easyui-textbox" data-options="" style="width: 130px" /></td>
				</tr>
				<tr height="40px">

					<td>&nbsp;报送日期:</td>
					<td><input  name="rptDate"  id="rptDate"
						class="easyui-datebox" data-options="" style="width: 130px" /></td>
					<td>&nbsp;系统号:</td>
					<td><input  name="sourceSys"  id="sourceSysU"
						class="easyui-combobox" data-options="" style="width: 130px" /></td>
				</tr>

			</table>
			<div style="clear: both; padding: 20px" align="center">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 100px" onclick="submitU()">确定</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					style="width: 130px" onclick="$('#dialog-update').dialog('close')">取消</a>
			</div>
		</form>

	</div>
</body>
</html>