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
	src="${ctx}/js/nat/modify/InSecAcctInfDetail.js"></script>
<title></title>
<style>
p {
	visibility: hidden;
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
					onclick="showDetial('guarAcctBsSgmt');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('guarAcctBsInfSgmt');"><span>基本信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('guaRltRepInfSgmt');"><span>在保责任信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('gsRltRepInfSgmt');"><span>相关还款责任人段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('guaMotCltCtrSgmt');"><span>抵质押物信息段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout"
			data-options="region: 'center',border: true">
			<!-- 基本信息段 -->
			<div id="guarAcctBsInfSgmt" style="width: 100%;">
				<form id="guarAcctBsInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="guarAcctBsInfSgmtKey"
						name="guarAcctBsInfSgmtSeqNo">
					<!-- 是否已报送 -->
					<input type="hidden" name="reportflag">
					<table style="width: 100%;">
						<tr height="40px">
							<td style="width: 13%;">&nbsp;担保业务大类:</td>
							<td style="width: 13%"><input id="busiLines"
								name="busiLines" type="text" class="easyui-combobox"
								data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;担保业务种类细分:</td>
							<td style="width: 12%"><input id="busiDtilLines"
								name="busiDtilLines" type="text" class="easyui-combobox"
								data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;开户日期:</td>
							<td style="width: 13%"><input name="openDate" type="text"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;担保金额:</td>
							<td style="width: 13%"><input name="acctCredLine"
								type="text" class="easyui-textbox" data-options="editable:false"
								style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;币种:</td>
							<td><input id="cy" name="cy" type="text"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;到期日期:</td>
							<td><input name="dueDate" type="text" class="easyui-datebox"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;反担保方式:</td>
							<td><input id="guarMode" name="guarMode" type="text"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;其他还款保证方式:</td>
							<td><input id="othRepyGuarWay" name="othRepyGuarWay"
								type="text" class="easyui-combobox" data-options=""
								style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;保证金百分比:</td>
							<td><input name="secDep" type="text" class="easyui-textbox"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;担保合同文本编号:</td>
							<td><input name="ctrctTxtCd" type="text"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>

					<div style="margin-top: 100px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('guarAcctBsInfSgmt','sub')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitform('guarAcctBsInfSgmt','updSub')">提交并生成更正报文</a>
					</div>
				</form>
			</div>

			<!--基础段  -->
			<div id="guarAcctBsSgmt" style="width: 100%;">
				<form id="guarAcctBsSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="guarAcctBsSgmtKey"
						name="guarAcctBsSgmtSeqNo" /> 
						<input type="hidden"
						name="infRecType" />
					<!-- 是否已报送 -->
					<input type="hidden" name="reportflag">
					<table style="width: 100%;">
						<tr height="40px">
							<td style="width: 12%;">&nbsp;账户类型:</td>
							<td style="width: 12%"><input id="acctType" name="acctType"
								type="text" class="easyui-combobox" data-options=""
								style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;账户标识码:</td>
							<td style="width: 12%"><input name="acctCode" type="text"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td style="width: 14%">&nbsp;信息报告日期:</td>
							<td style="width: 12%"><input name="rptDate" type="text"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;报告时点说明代码:</td>
							<td style="width: 13%"><input id="rptDateCode"
								name="rptDateCode" type="text" class="easyui-combobox"
								data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;债务人姓名:</td>
							<td><input name="name" type="text" class="easyui-textbox"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;债务人证件类型:</td>
							<td><input id="idtype" name="idtype" type="text"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;债务人证件号码:</td>
							<td><input name="idnum" type="text" class="easyui-textbox"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;业务管理机构代码:</td>
							<td><input name="mngmtOrgCode" type="text"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>
					</table>







					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('guarAcctBsSgmt','sub')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitform('guarAcctBsSgmt','updSub')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 在保责任信息段  -->
			<div id="guaRltRepInfSgmt" style="width: 100%;">
				<form id="guaRltRepInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="guaRltRepInfSgmtKey"
						name="guarRltRepymtInfSgmtSeqNo" />
					<!-- 是否已报送 -->
					<input type="hidden" name="reportflag">
					<table style="width: 100%;">
						<tr height="40px">
							<td style="width: 12%;">&nbsp;账户状态:</td>
							<td style="width: 12%"><input id="acctStatus"
								name="acctStatus" type="text" class="easyui-combobox"
								data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;在保余额:</td>
							<td style="width: 12%"><input name="loanAmt" type="text"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td style="width: 14%">&nbsp;余额变化日期:</td>
							<td style="width: 12%"><input name="repayPrd" type="text"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;五级分类:</td>
							<td style="width: 13%"><input id="fiveCate" name="fiveCate"
								type="text" class="easyui-combobox" data-options=""
								style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;五级分类认定日期:</td>
							<td><input name="fiveCateAdjDate" type="text"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;风险敞口:</td>
							<td><input name="riEx" type="text" class="easyui-textbox"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;代偿（垫款）标志:</td>
							<td><input id="compAdvFlag" name="compAdvFlag" type="text"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;账户关闭日期:</td>
							<td><input name="closeDate" type="text"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('guaRltRepInfSgmt','sub')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitform('guaRltRepInfSgmt','updSub')">提交并生成更正报文</a>
					</div>
				</form>

			</div>
			<!-- 相关还款责任人段 -->
			<div id="gsRltRepInfSgmt" style="height: 100%; width: 100%">


				<!--  <div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px"> -->
				<div style="height: 7%">
					<div style="border-bottom: 1px solid rgb(149, 184, 231)">
						<div style="padding: 2px; display: inline-block;">&nbsp;责任人名称:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="idSgmtName" class="easyui-textbox"
								style="width: 100px;" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;责任人身份标识号码:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input id="idSgmtIdNum" class="easyui-textbox"
								style="width: 150px" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;还款责任金额:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input id="arlpAmt" class="easyui-textbox" style="width: 150px" />
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
				<!-- <div class="easyui-layout "
					data-options="region:'center',border: false"> -->

				<!-- 列表 -->
				<table id="gsRltRepInfSgmtTable" class="easyui-datagrid"
					style="height: 93%; width: 100%;"></table>


				<!-- 修改项弹出框 -->
				<div id="dialog-gsRltRepInfSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 300px; padding: 5px;">
					<form id="gsRltRepInfSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="gsRltRepInfSgmtKey"
							name="rltRepymtInfSgmtSeqNo" readonly="readonly" />
						<!-- 自身主键 -->
						<input type="hidden" name="gsRltRepymtInfSeqNo">
						<!-- 是否已报送 -->
						<input type="hidden" name="reportflag">
						<table style="width: 100%;">
							<tr height="40px">
								<td style="width: 100px;">&nbsp;身份类别:</td>
								<td style="width: 100px"><input id="infoIDType"
									name="infoIDType" type="text" class="easyui-combobox"
									data-options="" style="width: 120px" /></td>
								<td style="width: 100px">&nbsp;还款责任人类型:</td>
								<td style="width: 100px"><input id="arlpType"
									name="arlpType" type="text" class="easyui-combobox"
									data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;责任人名称:</td>
								<td><input name="arlpName" type="text"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>
								<td>&nbsp;还款责任金额:</td>
								<td><input name="arlpAmt" type="text"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;责任人身份标识类型:</td>
								<td><input id="arlpCertType" name="arlpCertType"
									type="text" class="easyui-combobox" data-options=""
									style="width: 120px" /></td>
								<td>&nbsp;联保标志:</td>
								<td><input id="wartySign" name="wartySign" type="text"
									class="easyui-combobox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;责任人身份标识号码:</td>
								<td><input name="arlpCertNum" type="text"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>
								<td>&nbsp;保证合同编号:</td>
								<td><input name="maxGuarMcc" type="text"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>

							</tr>
						</table>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 100px" onclick="submitform('gsRltRepInfSgmt','sub')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 120px" onclick="submitform('gsRltRepInfSgmt','updSub')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>
			<!-- 抵质押物信息段 -->
			<div class="easyui-layout" fit="true" id="guaMotCltCtrSgmt">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px; height: 40px; width: 100%">
					<div style="border-bottom: 1px solid rgb(149, 184, 231);">
						<div style="padding: 2px; display: inline-block;">&nbsp;合同标示码:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="1" class="easyui-textbox" style="width: 100px;" />
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
					<!-- 主键 -->
					<input type="hidden" id="guaMotCltCtrSgmtKey"
						name="guarMotgaCltalCtrctInfSgmtSeqNo" />
					<!-- 列表 -->
					<table id="guaMotCltCtrSgmtTable" style="height: 100%; width: 100%"></table>
				</div>


			</div>

		</div>
	</div>
</body>
</html>
