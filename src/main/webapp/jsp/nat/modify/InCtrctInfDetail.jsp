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
<script type="text/javascript"
	src="${ctx}/js/nat/modify/InCtrctInfDetail.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/InLinkage.js"></script>
<title>个人授信信息修改详细</title>
<style>
p {
	display: block;
}
</style>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width: 200px"
			data-options="region: 'west', border: true">
			<ul class='easyui-tree tree' data-options='animate:true'>
				
				<li><a href="javascript:void(0)"
					onclick="showDetial('ctrctBsSgmt');"><span>基础段</span></a></li>
				<!-- <li><a href="javascript:void(0)"
					onclick="showDetial('ctrctCertRelSgmt');"><span>共同受信人信息段</span></a></li> -->
				<li><a href="javascript:void(0)"
					onclick="showDetial('creditLimSgmt');"><span>额度信息段</span></a></li>
				
			</ul>
		</div>
		<div class="easyui-layout"
			data-options="region: 'center',border: true">
			<!-- 基本段 -->
			<div class="easyui-layout" fit="true" id="ctrctBsSgmt"
				style="display: none;">
				<form id="ctrctBsSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="ctrctBsSgmtKey" name="ctrctBsSgmtSeqNo">
					<input type="hidden" name="infRecType">
					<!-- 是否已报送 -->
					<input type="hidden" name="reportflag">
					<table style="width: 100%;" id='table1'>
						<tr height="40px">
							<td style="width: 13%;">&nbsp;合同编号:</td>
							<td style="width: 13%"><input   name="contractCode1" type="text" id="CONTRACTCOD1E"
								class="easyui-textbox" data-options="" style="width: 130px" readonly = 'true'/></td>
							<td style="width: 13%;">&nbsp;授信协议标识码:</td>
							<td style="width: 13%"><input checkId='AN..60_AM'  name="contractCode" type="text" id="CONTRACTCODE"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							
							<td style="width: 12%">&nbsp;报告时点说明代码:</td>
							<td style="width: 13%"><input checkId='Enum_AM' id="RPTDATECODE" name="rptDateCode" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;姓名:</td>
							<td style="width: 13%"><input checkId='ANC..30_AM' name="name" type="text" id="NAME"
								class="easyui-textbox" data-options=""
								style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;证件类型:</td>
							<td><input checkId='Enum_AM' id="IDTYPE" name="idtype" type="text" 
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;证件号码:</td>
							<td><input checkId='ANC..20_AM_A:{IDType:IDTYPE}' name="idnum" type="text" id="IDNUM"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;业务管理机构代码:</td>
							<td><input checkId='AN14_AM' name="mngmtOrgCode" type="text" class="easyui-textbox" id="MNGMTORGCODE"
								data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;信息报告日期:</td>
							<td style="width: 12%"><input checkId='Date_AM' name="rptDate" type="text" id="RPTDATE"
								class="easyui-datebox" data-options="" style="width: 130px" /></td>
						</tr>
					</table>

					<div style="margin-top: 100px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('ctrctBsSgmt','sub','table1')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 130px" onclick="submitform('ctrctBsSgmt','updSub','table1')">提交并生成更正报文</a>
					</div>
				</form>
			</div>

			<!--额度信息段-->
			<div class="easyui-layout" fit="true" id="creditLimSgmt"
				>
				<form id="creditLimSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="creditLimSgmtKey" name="creditLimSgmtSeqNo" />
					<input type="hidden"  name="infRecType" />
					<!-- 是否已报送 -->
					<input type="hidden" name="reportflag">
					<table style="width: 100%;" id='table2'>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;授信额度类型:</td>
							<td style="width: 12%"><input checkId='Enum_AM' name="creditLimType" type="text" id="CREDITLIMTYPE"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;额度循环标志:</td>
							<td style="width: 12%"><input checkId='Enum_AM' id="LIMLOOPFLG" name="limLoopFlg" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;授信额度:</td>
							<td style="width: 12%"><input checkId='uInt..15_AM' name="creditLim" type="text" id="CREDITLIM"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;币种:</td>
							<td style="width: 13%"><input checkId='Enum_AM' id="CY" name="cy" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;额度生效日期:</td>
							<td><input checkId='Date_AM' name="conEffDate" type="text" class="easyui-datebox" id="CONEFFDATE"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;额度到期日期:</td>
							<td><input checkId='Date_AM' name="conExpDate" type="text" id="CONEXPDATE"
								class="easyui-datebox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;额度状态:</td>
							<td><input checkId='Enum_AM' id="CONSTATUS" name="conStatus" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;授信限额:</td>
							<td><input checkId='uInt..15_SM' name="creditRest" type="text" id="CREDITREST"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;授信额度编号:</td> 
							<td><input checkId='AN..60_SM_A:{sameStatus:CREDITREST}' name="creditRestCode" type="text" class="easyui-textbox" id="CREDITRESTCODE"
								data-options="" style="width: 130px" /></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
				        <a href="javascript:void(0)" id="del"  class="easyui-linkbutton"
							style="width: 100px"   onclick="deleteform()">按段删除</a>
						<a href="javascript:void(0)" id="upd" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('creditLimSgmt','sub','table2')">提交</a>
						<a href="javascript:void(0)" id="sub" class="easyui-linkbutton"
							style="width: 130px" onclick="submitform('creditLimSgmt','updSub','table2')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 共同受信人信息 -->
			<div class="easyui-layout" fit="true" id="ctrctCertRelSgmt">

				
					<div  class="easyui-layout" 
						data-options="region: 'north',border: false" style="padding-bottom:1px">
						<div style="border-bottom: 1px solid rgb(149, 184, 231)">
							<div style="padding: 2px; display: inline-block;">&nbsp;共同受信人名称:</div>
							<div style="padding: 2px; display: inline-block;">
								<input   class="easyui-textbox" id="idSgmtName"
									style="width: 100px;" />
							</div>
							<div style="padding: 2px; display: inline-block;">&nbsp;共同受信人身份标识号码:</div>
							<div
								style="padding: 2px; vertical-align: middle; display: inline-block;">
								<input  class="easyui-textbox" id="idSgmtIdNum"
									style="width: 150px" />
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
					</div>
					<div class="easyui-layout "
						data-options="region:'center',border: false">
						
						<!-- 列表 -->
						<table id="ctrctCertRelSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
				
				<!-- 修改项弹出框 -->
				<div id="dialog-ctrctCertRelSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 200px; padding: 5px;">
					<form id="ctrctCertRelSgmtForm" method="POST"
					style="height: 100%; width: 100%">
						<!-- 主键，隐藏，做数据更新使用 -->
						<input type="hidden" id="ctrctCertRelSgmtKey" name="ctrctCertRelSgmtSeqNo" />
						<!-- 自身主键 -->
						<input type="hidden" name="ctrctCertRelSeqNo">
						<!-- 是否已报送 -->
						<input type="hidden" name="reportflag" id='table3'>
						<table style="width: 100%;">
							<tr height="40px">
								<td style="width: 100px;">&nbsp;共同受信人身份类别:</td>
								<td style="width: 100px"><input id="BRERTYPE" name="brerType" type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
								<td style="width: 100px">&nbsp;共同受信人名称:</td>
								<td style="width: 100px"><input checkId='ANC..80_SM' name="certRelName" type="text" class="easyui-textbox" data-options="" id="CERTRELNAME"
								style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;共同受信人身份标识类型:</td>
								<td><input id="CERTRELIDTYPE" name="certRelIDType" type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
								<td>&nbsp;共同受信人身份标识号码:</td>
								<td><input name="certRelIDNum" checkId='ANC..40_SM' type="text" class="easyui-textbox" data-options="" id="CERTRELIDNUM"
								style="width: 130px" /></td>

							</tr>
							
						</table>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 100px" onclick="submitform('ctrctCertRelSgmt','sub','table3')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 130px" onclick="submitform('ctrctCertRelSgmt','updSub','table3')">提交并生成更正报文</a>
						</div>
					</form>
				
					
					</div>

			</div>
			
		
		</div>


	</div>
</body>
</html>