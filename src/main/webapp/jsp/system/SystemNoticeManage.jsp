<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript"
	src="${ctx}/js/system/fileupload.js"></script>
<script type="text/javascript"
	src="${ctx}/js/system/sys_notice_manage.js"></script>

<title>系统公告管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body style="visibility: hidden;">

<img id="tempimg" dynsrc="" src="" style="display:none" />
	<div class="easyui-layout" style="padding: 5px;"
		data-options="region: 'north', border: false">
		<table class="queryTable" width="100%">
			<tr>
				<td class="queryTitle">&nbsp;公告编号：</td>
				<td class="queryContent"><input class="inputText" type="text"
					id="queryNoticeID" /></td>
				<td class="queryTitle">&nbsp;标题：</td>
				<td class="queryContent"><input class="inputText" type="text"
					id="queryTitle" /></td>
				<td class="queryBtnTd" rowspan="2">&nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="doQuery()">查询</a> &nbsp;&nbsp; <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-cancel" onclick="doClear()">清空</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="easyui-layout" fit="true">
		<table id="noticetable" style="height: 100%; width: 100%"></table>
	</div>

	<div id="addDlg" class="easyui-window" title="新增公告" modal="true"
		closed="true" iconCls="icon-save"
		style="width: 650px; height: 350px; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="addForm" method="post" enctype="multipart/form-data"
					style="text-align: center;">
					<table border="0"  width="100%"
						style="margin-bottom: 0px">
						<input type="hidden" id="status" name="status" value="1" />
						<tr>
							<td class="queryTitle" width="80px">标题 <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="120px"><input type="text"
								id="title" name="title" class="easyui-textbox" style="width: 350px" /></td>
						</tr>
						<tr>
							<td class="queryTitle" width="80px">内容 <span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="120px"><textarea
									id="content" name="content" cols="50" rows="10"></textarea>
							</td>
						</tr>
						<tr id="add_attachRow" style="display: none">
							<td class="queryTitle">附件</td>
							<td class="queryContent" id="attachArea"></td>
						</tr>
						<tr id="add_attchButtonRow" style="display: none">
							<td class="queryContent"></td>
							<td class="queryContent" colspan="2"><a
								class="easyui-linkbutton" href="javascript:void(0)"
								onclick="addAtta('add')">增加附件</a></td>
						</tr>
					</table>
				</form>
			</div>
			<div region="south" border="false"
				style="text-align: center; height: 30px; line-height: 30px;">
				<a class="easyui-linkbutton" iconCls="icon-ok"
					href="javascript:void(0)" onclick="doPostData()">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="easyui-linkbutton" iconCls="icon-cancel"
					href="javascript:void(0)"
					onclick="javascript:$('#addDlg').dialog('close')">取消</a>
			</div>
		</div>
	</div>

	<div id="editDlg" class="easyui-window" title="修改公告" closed="true"
		iconCls="icon-edit"
		style="width: 650px; height: 350px; text-align: center; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="background: #fff; border: 1px solid #ccc; padding-left: 30px;">
				<form id="editDlgForm" method="POST" enctype="multipart/form-data">
					<table border="0"  width="100%"
						style="margin-bottom: 0px">
						<input type="hidden" id="STATUS" name="STATUS" value="1" />
						<input type="hidden" id="NOTICE_ID" name="NOTICE_ID" />
						<tr>
							<td class="queryTitle" width="80px">标题<span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="120px"><input type="text"
								id="TITLE" name="TITLE" class="easyui-textbox" style="width: 350px" /></td>
						</tr>
						<tr>
							<td class="queryTitle" width="80px">内容<span
								style="color: #ff0000; font-size: 7pt;"> &nbsp;* </span>
							</td>
							<td class="queryContent" width="120px"><textarea
									id="CONTENT" name="CONTENT" cols="50" rows="10"></textarea></td>
						</tr>
						<tr id="update_attachFileRow" style="display: none">
							<td class="queryTitle" valign="center">附件</td>
							<td class="queryContent" id="updateAttachArea">
							</td>
						</tr>
						<tr id="update_attchButtonRow" style="display: none">
							<td class="queryContent"></td>
							<td class="queryContent" id="updateAttachButton">
								<a class="easyui-linkbutton" href="javascript:void(0)"
								onclick="addAtta('update')">增加附件</a>
							</td>
						</tr>

					</table>
				</form>
				<div region="south" border="false"
					style="text-align: center; height: 30px; line-height: 30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok"
						href="javascript:void(0)" onclick="doUpdatePostData()">提交</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="easyui-linkbutton" iconCls="icon-cancel"
						href="javascript:void(0)"
						onclick="javascript:$('#editDlg').dialog('close')">返回</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
