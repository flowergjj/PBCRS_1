<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="uname" value="${user.userName}" />
<script type="text/javascript">
	function getUserName() {
		return "${uname}";
	}
</script>
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<script type="text/javascript" src="${ctx}/js/jQuery.md5.js"></script>
<script type="text/javascript" src="${ctx}/js/system/main.js"></script>
<script type="text/javascript" src="${ctx}/js/system/menu.js"></script>
<title>江苏金农征信报送系统</title>
</head>
<body style="visibility: hidden;">
	<div id="alldiv" class="easyui-layout" fit="true">
		<div data-options="region:'north',border:false"
			style="height: 50px; background: #B3DFDA;">
			<img src="${ctx}/images/common/banner_title_1.png" height="100%"
				style="float: left; border: 0; margin-top: 0px; margin-left: 0px">
			<span style="float: right; padding-right: 20px; margin-top: 30px;"
				class="head"> 欢迎&nbsp;&nbsp;${user.userName},
				您的角色为&nbsp;&nbsp;${user.userRole}&nbsp;&nbsp;,所属机构为&nbsp;&nbsp;${user.orgName} <a
				href="javascript:void(0)" id="editpass" style="color: #000"
				onclick="OpenChgPwdDlg()"> 修改密码</a>&nbsp; <a
				href="javascript:void(0)" id="logout" style="color: #000"
				onclick="logOut()"> 退出</a>&nbsp;
			</span>
		</div>
		<div id="chgPwddlg" class="easyui-dialog" closed="true" title=" 修改密码 "
			style="width: 300px; height: 240px; padding: 10px 20px;">
			<form id="fm" method="post">
				<div class="fitem">
					<label>旧&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码:</label> <input
						name="oldPwd" id="oldPwd" type="password" required="true" /> <input
						name="holdPwd" id="holdPwd" type="hidden" required="true" />
				</div>
				<div class="fitem">
					<label>新&nbsp;&nbsp;&nbsp;密&nbsp;&nbsp;&nbsp;码:</label> <input
						name="newPwd" id="newPwd" type="password" required="true" /> <input
						name="hnewPwd" id="hnewPwd" type="hidden" required="true" />
				</div>
				<div class="fitem">
					<label>确认新密码:</label> <input name="confirmPwd" id="confirmPwd"
						type="password" required="true" />
				</div>
			</form>
			<div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="SavePwd()" iconcls="icon-save">保存</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:$('#chgPwddlg').dialog('close')"
					iconcls="icon-cancel">取消</a>
			</div>
		</div>
		<div id="MenuTree"
			data-options="region:'west',split:true,title:'导航菜单'"
			style="width: 180px;">
			<div id="nav" class="easyui-accordion" style="height: 100%"></div>
		</div>
		<div id="workspace" region="center" style="overflow-y: hidden;">
			<div id="center_tab" class="easyui-tabs"
				data-options="fit:true,border:false,plain:true">
				<div region="center" id="test" style="padding: 0px;" title="欢迎使用">
					<div class="easyui-layout" data-options="fit:true">
						<div region="center" id="test" style="padding: 20px;">
							${user.userName} ,欢迎您进入江苏金农征信报送系统;<br /> <br />
							<span>待办事项</span>
							<div>
							 <div id="checkList"></div>
							</div>
							
						</div>
						<div
							data-options="region:'east',split:true,hideCollapsedContent:false,collapsible:false"
							title="系统公告" style="width: 265px;">
							<table id="noticetable"
								style="width: 100%; padding-left: 5px; height: 32px;" border="0">
							</table>
							<table style="width: 100%; padding-left: 5px;" border="0">
								<tr>
									<td valign="top"><div id="notice_board" class="showtext"></div></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-options="region:'south',border:false"
			style="height: 25px; background: #B3DFDA;">
			<span
				style="width: 100%; text-align: center; padding: 7px 0px 0px 0px;">
				武汉汉思信息技术有限责任公司 版权所有 </span>
		</div>
	</div>
</body>
</html>