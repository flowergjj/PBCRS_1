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
	src="${ctx}/js/nat/modify/InAcctInfDetail.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/InLinkage.js"></script>
<title>个人借贷信息修改详细</title>
<style>
</style>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width: 200px"
			data-options="region: 'west', border: true">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"
					onclick="showDetial('acctBsSgmt');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('acctBsInfSgmt');"><span>基本信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('rltRepymtInfSgmt');"><span>相关还款责任人段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('motCltCtrInfSgmt');"><span>抵质押物信息段</span></a></li>
				<%--<li><a href="javascript:void(0)" onclick="showDetial('acctCredSgmt');"><span>授信额度信息段</span></a></li>--%>
				<!-- <li><a href="javascript:void(0)"
					onclick="showDetial('oriCreInfSgmt');"><span>初始债权说明段</span></a></li> -->
				<li><a href="javascript:void(0)"
					onclick="showDetial('accMthBlgInfSgmt');"><span>月度表现信息段</span></a></li>
				<!-- <li><a href="javascript:void(0)"
					onclick="showDetial('specPrdSgmt');"><span>大额专项分期信息段</span></a></li> -->
				<li><a href="javascript:void(0)"
					onclick="showDetial('acctDbtInfSgmt');"><span>非月度表现信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('accSpeTrsDspSgmt');"><span>特殊交易说明段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout"
			data-options="region: 'center',border: true">
			<div  style="position:absolute;z-index:0;height:100%;width:100%;background-color:#ffffff">
			</div>
			<!-- 基本信息段 -->
			<div class="easyui-layout" fit="true" id="acctBsInfSgmt" style="position:absolute;z-index:-100">
				<form id="acctBsInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="acctBsInfSgmtKey"
						name="acctBsInfSgmtSeqNo">

					<table style="width: 100%;" id='table1'>
						<tr height="40px">
							<td style="width: 13%;">&nbsp;借贷业务大类:</td> 
							<td style="width: 13%"><input checkId="Enum_AM" name="busiLines" type="text" id="BUSILINES"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;借贷业务种类细分:</td>
							<td style="width: 12%"><input checkId='Enum_AM_A:{busiLines:BUSILINES}' name="busiDtlLines" id="BUSIDTLLINES"
								type="text" class="easyui-combobox" data-options=""
								style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;开户日期:</td>
							<td style="width: 13%"><input checkId="Date_AM" name="openDate" type="text" id="OPENDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;币种:</td>
							<td style="width: 13%"><input checkId="Enum_AM" name="cy" type="text" id="CY"
								class="easyui-combobox" data-options=""
								style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;信用额度:</td>
							<td><input checkId="uInt..15_SM_A:{accountType:ACCTCREDLINE}" name="acctCredLine" type="text" id="ACCTCREDLINE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;借款金额:</td>
							<td><input checkId="uInt..15_AM" name="loanAmt" type="text" class="easyui-textbox" id="LOANAMT"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;分次放款标志:</td>
							<td><input checkId="Enum_SM_S:{flag:FLAG}" name="flag" type="text" class="easyui-combobox" id="FLAG"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;到期日期:</td>
							<td><input checkId="Date_AM" name="dueDate" type="text" class="easyui-datebox" id="DUEDATE"
								data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;还款方式:</td>
							<td><input checkId="Enum_AM" name="repayMode" type="text" id="REPAYMODE"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;还款频率:</td>
							<td><input checkId="Enum_AM" name="repayFreqcy" type="text" id="REPAYFREQCY"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;还款期数:</td>
							<td><input checkId="uInt..3_AM" name="repayPrd" type="text" id="REPAYPRD" 
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;业务申请地行政区划代码:</td>
							<td><input name="applyBusiDist" type="text" id="APPLYBUSIDIST"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;担保方式:</td>
							<td><input checkId="Enum_AM" name="guarMode" type="text" id="GUARMODE"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;其他还款保证方式:</td>
							<td><input checkId="Enum_AM" name="othRepyGuarWay" type="text" id="OTHREPYGUARWAY"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;资产转让标志:</td>
							<td><input checkId="Enum_AM" name="assetTrandFlag" type="text" id="ASSETTRANDFLAG"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;业务经营类型:</td>
							<td><input checkId="Enum_AM" name="fundSou" type="text" class="easyui-combobox" id="FUNDSOU"
								data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;贷款发放形式:</td>
							<td><input checkId="Enum_AM" name="loanForm" type="text" id="LOANFORM"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;贷款合同编号:</td>
							<td><input checkId="ANC..200_SM_S:{acctType:BUSIDTLLINES}"  name="loanConCode" type="text" id="LOANCONCODE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;是否为首套住房贷款:</td>
							<td><input checkId="Enum_SM_S:{acctType:BUSIDTLLINES}"  name="firstHouLoanFlag" type="text" id="FIRSTHOULOANFLAG"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							
						</tr>
					</table>

					<div style="margin-top: 100px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="acctBsInfSgmtTable1"
							style="width: 100px" onclick="byParagraphDelete('acctBsInfSgmt','table1')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable1"
							style="width: 100px" onclick="submitform('acctBsInfSgmt','sub','table1')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable1"
							style="width: 120px" onclick="submitform('acctBsInfSgmt','updSub','table1')">提交并生成更正报文</a>
					</div>
				</form>
			</div>

			<!--基础段  -->
			<div class="easyui-layout" fit="true" id="acctBsSgmt" style="position:absolute;z-index:-100">
				<form id="acctBsSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="acctBsSgmtKey" name="acctBsSgmtSeqNo" />
					<input type="hidden" name="infRecType" />
					<!-- 是否已报送 -->
					<input type="hidden" id="reportflag" name="reportflag">
					<table style="width: 100%;" id='table2'>
						<tr height="40px">
							<td style="width: 12%">&nbsp;借据号:</td>
							<td style="width: 12%"><input  name="acctCode1" type="text" id="ACCTCODE1"
								class="easyui-textbox" data-options="" style="width: 120px" readonly='true'/></td>
							<td style="width: 12%;">&nbsp;账户类型:</td>
							<td style="width: 12%"><input checkId="Enum_AM" id="ACCTTYPE" name="acctType"
								type="text" class="easyui-combobox" data-options=""
								style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;个人账户标识码:</td>
							<td style="width: 12%"><input checkId="AN..60_AM" name="acctCode" type="text" id="ACCTCODE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							
							<td style="width: 12%">&nbsp;报告时点说明代码:</td>
							<td style="width: 13%"><input checkId="Enum_AM" id="RPTDATECODE"
								name="rptDateCode" type="text" class="easyui-combobox"
								data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;姓名:</td>
							<td><input checkId="ANC..30_AM" name="name" type="text" class="easyui-textbox" id="NAME"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;证件类型:</td>
							<td><input checkId="Enum_AM" id="IDTYPE" name="idtype" type="text"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;证件号码:</td>
							<td><input checkId="ANC..20_AM_A:{IDType:IDTYPE}" name="idnum" type="text" class="easyui-textbox" id="IDNUM"
								data-options="" style="width: 120px" /></td>
							<td>&nbsp;业务管理机构代码:</td>
							<td><input checkId="AN14_AM" name="mngmtOrgCode" type="text" id="MNGMTORGCODE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
						<td style="width: 14%">&nbsp;信息报告日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="rptDate" type="text" id="RPTDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable2"
							style="width: 100px" onclick="submitform('acctBsSgmt','sub','table2')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable2"
							style="width: 120px" onclick="submitform('acctBsSgmt','updSub','table2')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 授信额度信息段 -->
			<div class="easyui-layout" fit="true"  style="position:absolute;z-index:-100"  id="acctCredSgmt">
				<form id="acctCredSgmtForm" method="post">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="acctCredSgmtKey"
						name="acctCredSgmtSeqNo" />

					<table class="queryTable" style="width: 100%" id='table3'>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;授信协议标识码：</td><td class='queryContent'><input
							
							checkId="AN..60_AM" class='easyui-textbox' type='text'  name='mcc' 
							id='MCC' /></td>
							
						</tr>					
						
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('acctCredSgmt','sub','table3');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitform('acctCredSgmt','updSub','table3');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 初始债权说明段  -->
			<div class="easyui-layout" fit="true" id="oriCreInfSgmt" style="position:absolute;z-index:-100">
				<form id="oriCreInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="oriCreInfSgmtKey"
						name="origCreditorInfSgmtSeqNo" />

					<table style="width: 100%;" id='table4'>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;初始债权人名称:</td>
							<td style="width: 12%"><input checkId="ANC..18_AM"  name="initCredName" id="INITCREDNAME"
								type="text" class="easyui-textbox" data-options=""
								style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;初始债权人机构代码:</td>
							<td style="width: 12%"><input checkId="AN18_AO" name="initCredOrgNm" id="INITCREDORGNM"
								type="text" class="easyui-textbox" data-options=""
								style="width: 120px" /></td>
							<td style="width: 14%">&nbsp;原债务种类:</td>
							<td style="width: 12%"><input checkId="Enum_AM" name="origDbtCate" type="text" id="ORIGDBTCATE"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;债权转移时的还款状态:</td>
							<td style="width: 13%"><input checkId="Enum_AM" name="initRpySts" type="text" id="INITRPYSTS"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
						</tr>

					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('oriCreInfSgmt','sub',''table4'')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitform('oriCreInfSgmt','updSub',''table4'')">提交并生成更正报文</a>
					</div>
				</form>

			</div>
			<!-- 月度表现信息段  -->
			<div class="easyui-layout" fit="true" id="accMthBlgInfSgmt" style="position:absolute;z-index:-100">
				<form id="accMthBlgInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="accMthBlgInfSgmtKey"
						name="acctMthlyBlgInfSgmtSeqNo" />

					<table style="width: 100%;" id='table5'>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;月份:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="month" type="text" id="MONTH"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;结算/应还款日期:</td>
							<td style="width: 12%"><input name="settDate" type="text" id="SETTDATE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td style="width: 14%">&nbsp;账户状态:</td>
							<td style="width: 12%"><input checkId="Enum_AM" name="acctStatus" type="text" id="ACCTSTATUS"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;余额:</td>
							<td style="width: 13%"><input checkId="uInt..15_AM" name="acctBal" type="text" id="ACCTBAL"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;本期账单余额:</td>
							<td><input checkId="uInt..15_SM_A:{accountType:PRIDACCTBAL}" name="pridAcctBal" type="text" id="PRIDACCTBAL"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;已使用额度:</td>
							<td><input checkId="uInt..15_SM_A:{accountType:USEDAMT}" name="usedAmt" type="text" class="easyui-textbox" id="USEDAMT"
								data-options="" style="width: 120px" /></td> 
							<td>&nbsp;未出单的大额专项分期余额:</td>
							<td><input checkId="uInt..15_SM_A:{accountType:NOTISUBAL}" name="notIsuBal" type="text" id="NOTISUBAL"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;剩余还款期数:</td>
							<td><input checkId="uInt..3_AM" name="remRepPrd" type="text" id="REMREPPRD" 
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;五级分类:</td>
							<td><input checkId="Enum_AM" name="fiveCate" type="text" id="FIVECATE"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;五级分类认定日期:</td>
							<td><input checkId="Date_AM" name="fiveCateAdjDate" type="text" id="FIVECATEADJDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;当前还款状态:</td>
							<td><input checkId="Enum_AM" name="rpyStatus" type="text" id="RPYSTATUS"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;实际还款百分比:</td>
							<td><input checkId="uInt..3_SC_A:{accountType:RPYPRCT}" name="rpyPrct" type="text" class="easyui-textbox" id="RPYPRCT"
								data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;当前逾期期数:</td>
							<td><input checkId="uInt..3_AM" name="overdPrd" type="text" id="OVERDPRD"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;当前逾期总额:</td>
							<td><input checkId="uInt..15_AM" name="totOverd" type="text" id="TOTOVERD"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;当前逾期本金:</td>
							<td><input checkId="uInt..15_AM" name="overdPrinc" type="text" id="OVERDPRINC"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;逾期31-60天未归还本金:</td>
							<td><input checkId="uInt..15_AM" name="oved31_60Princ" type="text" id="OVED31_60PRINC"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;逾期61-90天未归还本金:</td>
							<td><input checkId="uInt..15_AM" name="oved61_90Princ" type="text" id="OVED61_90PRINC"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;逾期91-180天未归还本金:</td>
							<td><input checkId="uInt..15_AM" name="oved91_180Princ" type="text" id="OVED91_180PRINC"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;逾期180天以上未归还本金:</td>
							<td><input checkId="uInt..15_AM" name="ovedPrinc180" type="text" id="OVEDPRINC180"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;透支180天以上未还余额:</td>
							<td><input checkId="uInt..15_SM_A:{accountType:OVEDRAWBAOVE180}" name="ovedrawBaOve180" type="text" id="OVEDRAWBAOVE180"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;本月应还款金额:</td>
							<td><input checkId="uInt..15_AM" name="curRpyAmt" type="text" id="CURRPYAMT"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;本月实际还款金额:</td>
							<td><input checkId="uInt..15_AM" name="actRpyAmt" type="text" id="ACTRPYAMT"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;最近一次实际还款日期:</td>
							<td><input checkId="Enum_AM" name="latRpyDate" type="text" id="LATRPYDATE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;账户关闭日期:</td>
							<td><input checkId="Date_AC_A:{closeDate:ACCTSTATUS}" name="closeDate" type="text" id="CLOSEDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="accMthBlgInfSgmtTable5"
							style="width: 100px" onclick="byParagraphDelete('accMthBlgInfSgmt','table5')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable5"
							style="width: 100px" onclick="submitform('accMthBlgInfSgmt','sub','table5')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable5"
							style="width: 120px" onclick="submitform('accMthBlgInfSgmt','updSub','table5')">提交并生成更正报文</a>
					</div>
				</form>

			</div>
			<!-- 大额专项分期信息段  -->
			<div class="easyui-layout" fit="true" id="specPrdSgmt" style="position:absolute;z-index:-100">
				<form id="specPrdSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="specPrdSgmtKey" name="specPrdSgmtSeqNo" />

					<table style="width: 100%;" id='table6'>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;大额专项分期额度:</td>
							<td style="width: 12%"><input checkId="uInt..15_AM" name="specLine" type="text" id="SPECLINE"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;专项分期生效日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="specEfctDate" id="SPECEFCTDATE"
								type="text" class="easyui-datebox" data-options=""
								style="width: 120px" /></td>
							<td style="width: 14%">&nbsp;专项分期到期日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="specEndDate" type="text" id="SPECENDDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;已用分期金额:</td>
							<td style="width: 13%"><input checkId="uInt..15_AM" name="usedInstAmt" type="text" id="USEDINSTAMT"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
						</tr>

					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="byParagraphDelete('specPrdSgmt','table6')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitform('specPrdSgmt','sub','table6')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitform('specPrdSgmt','updSub','table6')">提交并生成更正报文</a>
					</div>
				</form>

			</div>
			<!-- 非月度表现信息段  -->
			<div class="easyui-layout" fit="true" id="acctDbtInfSgmt" style="position:absolute;z-index:-100">
				<form id="acctDbtInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="acctDbtInfSgmtKey"
						name="acctDbtInfSgmtSeqNo" />

					<table style="width: 100%;" id='table7'>
						<tr height="40px">
							<td style="width: 14%">&nbsp;账户状态:</td>
							<td style="width: 12%"><input checkId="Enum_AM" name="acctStatus" type="text" id="ACCTSTATUS"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;余额:</td>
							<td style="width: 13%"><input checkId="uInt..15_AM" name="acctBal" type="text" id="ACCTBAL"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%;">&nbsp;五级分类:</td>
							<td style="width: 12%"><input name="fiveCate" type="text" id="FIVECATE"
								class="easyui-combobox" data-options="" style="width: 120px" /></td>
							<td style="width: 12%">&nbsp;五级分类认定日期:</td>
							<td style="width: 12%"><input name="fiveCateAdjDate" id="FIVECATEADJDATE"
								type="text" class="easyui-datebox" data-options=""
								style="width: 120px" /></td>

						</tr>
						<tr height="40px">
							<td>&nbsp;剩余还款期数:</td>
							<td><input checkId="uInt..3_AM" name="remRepPrd" type="text" id="REMREPPRD"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;当前还款状态:</td>
							<td><input name="rpyStatus" type="text" id="RPYSTATUS"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;当前逾期期数:</td>
							<td><input checkId="uInt..3_AM" name="overdPrd" type="text" id="OVERDPRD"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;当前逾期总额:</td>
							<td><input checkId="uInt..15_AM" name="totOverd" type="text" id="TOTOVERD"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							


						</tr>
						<tr height="40px">
							<td>&nbsp;最近一次实际还款金额:</td>
							<td><input checkId="uInt..15_SM" name="latRpyAmt" type="text" id="LATRPYAMT"
								class="easyui-textbox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;最近一次实际还款日期:</td>
							<td><input checkId="Date_AM" name="latRpyDate" type="text" id="LATRPYDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td>&nbsp;账户关闭日期:</td>
							<td><input checkId="Date_AC_A:{notCloseDate:ACCTSTATUS}" name="closeDate" type="text" id="CLOSEDATE"
								class="easyui-datebox" data-options="" style="width: 120px" /></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</table>
					<div style="clear: both; padding: 20px" align="center">
					    <a href="javascript:void(0)" class="easyui-linkbutton" id="acctDbtInfSgmtTable7"
							style="width: 100px" onclick="byParagraphDelete('acctDbtInfSgmt','table7')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable7"
							style="width: 100px" onclick="submitform('acctDbtInfSgmt','sub','table7')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable7"
							style="width: 120px" onclick="submitform('acctDbtInfSgmt','updSub','table7')">提交并生成更正报文</a>
					</div>
				</form>

			</div>
			<!-- 相关还款责任人段 -->
			<div class="easyui-layout" fit="true" id="rltRepymtInfSgmt" style="position:absolute;z-index:-100">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px;min-height:35px">
					<div >
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
							<input id="idSgmtIdNum" class="easyui-textbox"
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
					<table id="rltRepymtInfSgmtTable"
						style="height: 100%; width: 100%;"></table>
				</div>

				<!-- 修改项弹出框 -->
				<div id="dialog-rltRepymtInfSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 300px; padding: 5px;">
					<form id="rltRepymtInfSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="rltRepymtInfSgmtKey"
							name="RltRepymtInfSgmtSeqNo" />
						<!-- 自身主键 -->
						<input type="hidden" name="rltRepymtInfSeqNo">

						<table style="width: 100%;" id='table8'>
							<tr height="40px">
								<td style="width: 100px;">&nbsp;身份类别:</td>
								<td style="width: 100px"><input checkId="Enum_AM" name="infoIDType" id="INFOIDTYPE"
									type="text" class="easyui-combobox" data-options=""
									style="width: 120px" /></td>
								<td style="width: 100px">&nbsp;还款责任人类型:</td>
								<td style="width: 100px"><input checkId="Enum_AM" name="arlpType" type="text" id="ARLPTYPE"
									class="easyui-combobox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;责任人名称:</td>
								<td><input checkId="ANC..80_AM" name="arlpName" type="text" id="ARLPNAME"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>
								<td>&nbsp;还款责任金额:</td>
								<td><input checkId="uInt..15_SC_A:{arlpAmt:ARLPTYPE}" name="arlpAmt" type="text" id="ARLPAMT"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;责任人身份标识类型:</td>
								<td><input checkId="Enum_AM" name="arlpCertType" type="text" id="ARLPCERTTYPE"
									class="easyui-combobox" data-options="" style="width: 120px" /></td>
								<td>&nbsp;联保标志:</td>
								<td><input checkId="Enum_SC_A:{wartySign:ARLPTYPE}" name="wartySign" type="text" id="WARTYSIGN"
									class="easyui-combobox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;责任人身份标识号码:</td>
								<td><input checkId="ANC..40_AM" name="arlpCertNum" type="text" id="ARLPCERTNUM"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>
								<td>&nbsp;保证合同编号:</td>
								<td><input checkId="AN..60_SC_A:{maxGuarMcc:ARLPTYPE}" name="maxGuarMcc" type="text" id="MAXGUARMCC"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>

							</tr>
						</table>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable8"
								style="width: 100px" onclick="submitform('rltRepymtInfSgmt','sub','table8')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable8"
								style="width: 120px" onclick="submitform('rltRepymtInfSgmt','updSub','table8')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>
			<!-- 特殊交易说明段 -->
			<div class="easyui-layout" fit="true" id="accSpeTrsDspSgmt" style="position:absolute;z-index:-100">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px;min-height:35px">
					<div><!-- style="border-bottom: 1px solid rgb(149, 184, 231)" -->
						<div style="padding: 2px; display: inline-block;">&nbsp;交易日期:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="idSgmtName" class="easyui-datebox"
								style="width: 100px;" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;交易金额:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input id="idSgmtIdNum" class="easyui-textbox"
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
					<table id="accSpeTrsDspSgmtTable"
						style="height: 100%; width: 100%;"></table>
				</div>

				<!-- 修改项弹出框 -->
				<div id="dialog-accSpeTrsDspSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 300px; padding: 5px;">
					<form id="accSpeTrsDspSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="accSpeTrsDspSgmtKey"
							name="acctSpecTrstDspnSgmtSeqNo" />
						<!-- 自身主键 -->
						<input type="hidden" name="cagOfTrdInfSeqNo">

						<table style="width: 100%;" id='table9'>
							<tr height="40px">
								<td style="width: 100px;">&nbsp;交易类型:</td>
								<td style="width: 100px"><input checkId="Enum_AM" checkId="Enum_AM" name="chanTranType" id="CHANTRANTYPE"
									type="text" class="easyui-combobox" data-options=""
									style="width: 120px" /></td>
								<td style="width: 100px">&nbsp;交易日期:</td>
								<td style="width: 100px"><input checkId="Date_AM" checkId="Date_AM" name="tranDate" type="text" id="TRANDATE"
									class="easyui-datebox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;交易金额:</td>
								<td><input checkId="uInt..15_AM" name="tranAmt" type="text" id="TRANAMT"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>
								<td>&nbsp;到期日期变更月数:</td>
								<td><input checkId="uInt..3_AM"  name="dueTranMon" type="text" id="DUETRANMON"
									class="easyui-combobox" data-options="" style="width: 120px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;交易明细信息:</td>
								<td><input checkId="ANC..200_AO" name="detInfo" type="text" id="DETINFO"
									class="easyui-textbox" data-options="" style="width: 120px" /></td>
								<td></td>
								<td></td>

							</tr>

						</table>
						<div style="clear: both; padding: 20px" align="center">
						 	<a href="javascript:void(0)" class="easyui-linkbutton" id="accSpeTrsDspSgmtTable9"
							style="width: 100px" onclick="byParagraphDelete('accSpeTrsDspSgmt','table9')">按段删除</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable9"
								style="width: 100px" onclick="submitform('accSpeTrsDspSgmt','sub','table9')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable9"
								style="width: 120px" onclick="submitform('accSpeTrsDspSgmt','updSub','table9')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>
			<!-- 抵质押物信息段 -->
			<div class="easyui-layout" fit="true" id="motCltCtrInfSgmt" style="position:absolute;z-index:-100">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px;min-height:35px">
					<div>
						<div style="padding: 2px; display: inline-block;">&nbsp;合同标示码:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="idSgmtName" class="easyui-textbox"
								style="width: 100px;" />
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
					<input type="hidden" id="motCltCtrInfSgmtKey" />
					<!-- 列表 -->
					<table id="motCltCtrInfSgmtTable"
						style="height: 100%; width: 100%;"></table>
				</div>


			</div>

		</div>
	</div>
</body>
</html>