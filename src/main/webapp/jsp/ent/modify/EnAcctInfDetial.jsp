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
<script type="text/javascript" src="${ctx}/js/ent/modify/EnAcctInfDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业借贷交易记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>账户基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>账户基本信息段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d3');"><span>相关还款责任人段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d4');"><span>抵质押物信息段</span></a></li>
				<%--<li><a href="javascript:void(0)"  onclick="showDetial('d5');"><span>授信额度信息段</span></a></li>--%>
<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d6');"><span>初始债权说明段</span></a></li>
 -->				<li><a href="javascript:void(0)"  onclick="showDetial('d7');"><span>还款表现信息段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d8');"><span>特定交易说明段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formEnAcctBsSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="acctId">
						<tr style="height:30px" >	
						<td class='queryTitle'>&nbsp;借据号：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;"  type='text'  name='codes' id='codes' readonly="readonly"/></td>						
							<td class='queryTitle'>&nbsp;账户类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='acctType' id='AcctType' /></td>
							<td class='queryTitle'>&nbsp;企业账户标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN..60_AM"type='text'  name='acctCode' id='AcctCode' /></td>
							
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;信息报告日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM'  type='text'  name='rptDate' id='RptDate' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;借款人名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..80_AM" type='text'  name='name' id='Name' /></td>
							<td class='queryTitle'>&nbsp;借款人身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='idtype' id='IDType' /></td>
							
						</tr>
						<tr>						
												<td class='queryTitle'>&nbsp;借款人身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..40_AM_A:{IDType:IDType}" type='text'  name='idnum' id='IDNum' /></td>
							<td class='queryTitle'>&nbsp;业务管理机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN14_AM" type='text'  name='mngmtOrgCode' id='MngmtOrgCode' /></td>
		
						</tr>
				
						<tr >
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='acctBsSgmtSeqNo' id='AcctBsSgmtSeqNo' /></td>
					
						</tr>
						<input type='hidden'  name='reportFlag' id='reportFlag' />
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformEnAcctBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnAcctBsSgmt('updSub');"/> </td> -->
							
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subAcctId"
							style="width: 100px" onclick="submitformEnAcctBsSgmt('sub','acctId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubAcctId"
							style="width: 120px" onclick="submitformEnAcctBsSgmt('updSub','acctId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				<form id="formEnAcctBsInfSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="sgmtId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;借贷业务大类：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='busiLines' id='BusiLines' /></td>
							<td class='queryTitle'>&nbsp;借贷业务种类细分：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='busiDtlLines' id='BusiDtlLines' /></td>
						
						</tr>
						<tr>
								<td class='queryTitle'>&nbsp;开户日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  type='text' checkId='Date_AM'  name='openDate' id='OpenDate' /></td>
							<td class='queryTitle'>&nbsp;币种：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='cy' id='Cy' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;信用额度(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_SM_S:{AcctCredLine:AcctCredLine}"  data-options="min:0" type='text'  name='acctCredLine' id='AcctCredLine' /></td>
							<td class='queryTitle'>&nbsp;借款金额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_SM_S:{LoanAmt:LoanAmt}" data-options="min:0" type='text'  name='loanAmt' id='LoanAmt' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;分次放款标志：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId="Enum_SM_S:{Flag:Flag}"  name='flag' id='Flag' /></td>
							<td class='queryTitle'>&nbsp;到期日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId="Date_SM_S:{DueDate:DueDate}"  type='text'  name='dueDate' id='DueDate' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;还款方式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId="Enum_SM_S:{RepayMode:RepayMode}" name='repayMode' id='RepayMode' /></td>
							<td class='queryTitle'>&nbsp;还款频率：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId="Enum_SM_S:{RepayFreqcy:RepayFreqcy}" name='repayFreqcy' id='RepayFreqcy' /></td>
							
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;业务申请地行政区划代码：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"  name='applyBusiDist' id='ApplyBusiDist' /></td>
							<td class='queryTitle'>&nbsp;担保方式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  name='guarMode' id='GuarMode' /></td>
							
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;其他还款保证方式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM' name='othRepyGuarWay' id='OthRepyGuarWay' /></td>
							<td class='queryTitle'>&nbsp;借款期限分类：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId="Enum_SM_S:{LoanTimeLimCat:LoanTimeLimCat}"  name='loanTimeLimCat' id='LoanTimeLimCat' /></td>
							
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;贷款发放形式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  name='loaFrm' id='LoaFrm' /></td>
							<td class='queryTitle'>&nbsp;贷款实际投向：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId="Enum_SM_S:{ActInvest:ActInvest}"  name='actInvest' id='ActInvest' /></td>
							
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;业务经营类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  name='fundSou' id='FundSou' /></td>
							<td class='queryTitle'>&nbsp;资产转让标识：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId="Enum_SM_S:{AssetTrandFlag:AssetTrandFlag}"  name='assetTrandFlag' id='AssetTrandFlag' /></td>
					
						</tr>
						
					
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformEnAcctBsInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnAcctBsInfSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden'  name='acctBsInfSgmtSeqNo' id='AcctBsInfSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="d2EnAcctBsInfSgmt"
								style="width: 100px" onclick="byParagraphDelete('d2','EnAcctBsInfSgmt')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subSgmtId"
							style="width: 100px" onclick="submitformEnAcctBsInfSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubSgmtId"
							style="width: 120px" onclick="submitformEnAcctBsInfSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d3">
					
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="EnRltRepInfSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-EnRltRepInfSgmtmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 450px; padding: 5px;">
				<form id='dialog-EnRltRepInfSgmtmodifyform' method="POST" >
					<table style="width: 100%;" id="eltrepId">
						<tr>
							<td class='queryTitle'>&nbsp;身份类别：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='arlpIDType' id='ArlpIDType' /></td>
							<td class='queryTitle'>&nbsp;责任人名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..80_AM" type='text'  name='arlpName' id='ArlpName' /></td>
							
	
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;责任人身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='arlpCertType' id='ArlpCertType' /></td>
							<td class='queryTitle'>&nbsp;责任人身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..40_AM_A:{ArlpCertType:ArlpCertType}" type='text'  name='arlpCertNum' id='ArlpCertNum' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;还款责任人类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"   name='arlpType' id='ArlpType' /></td>
							<td class='queryTitle'>&nbsp;还款责任金额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0" type='text'  name='arlpAmt' id='ArlpAmt' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;联保标志：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"   name='wartySign' id='WartySign' /></td>
							<td class='queryTitle'>&nbsp;保证合同编号：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='maxGuarMcc' id='MaxGuarMcc' /></td>
						</tr>
						<tr>
						<!-- 	<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformEnRltRepInf('sub');">确定</a></td>
							<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformEnRltRepInf('updSub');">提交并生成更正报文</a></td> -->
							<td><input type='hidden'  name='rltRepymtInfSgmtSeqNo' id='RltRepymtInfSgmtSeqNo' /></td>
							<td><input type='hidden' name='rltRepymtInfSeqNo' id='RltRepymtInfSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subEltrepId"
							style="width: 100px" onclick="submitformEnRltRepInf('sub','eltrepId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubEltrepId"
							style="width: 120px" onclick="submitformEnRltRepInf('updSub','eltrepId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d4">
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="MotCltCtrInfSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-MotCltCtrInfSgmtmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 450px; padding: 5px;">
				<form id='dialog-MotCltCtrInfSgmtmodifyform' method="POST" >
					<table style="width: 100%;">
						<tr>
							<td class='queryTitle'>&nbsp;抵（质）押合同标识码：</td><td class='queryContent'><input class='inputText' style="width: 145px;" type='text'   name='ccc' id='Ccc' /></td>

	
						</tr>
						
						<tr>
				<!-- 			<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformMotCltCtrInfSgmt('sub');">确定</a></td>
								<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformMotCltCtrInfSgmt('updSub');">提交并生成更正报文</a></td> -->
							<td><input type='hidden'  name='motgaCltalCtrctInfSgmtSeqNo' id='MotgaCltalCtrctInfSgmtSeqNo' /></td>
							<td><input type='hidden' name='cccInfSeqNo' id='CccInfSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
	
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subMotCltCtrInf"
							style="width: 100px" onclick="submitformMotCltCtrInfSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubMotCltCtrInf"
							style="width: 120px" onclick="submitformMotCltCtrInfSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
				
			
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d5">
				<form id="formEnAcctCredSgmt" method="post">
					<table class="queryTable" style="width: 100%">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;授信协议标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN..60_AM" type='text'  name='mcc' id='Mcc' /></td>
							
						</tr>					
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformEnAcctCredSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnAcctCredSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden' name='acctCredSgmtSeqNo' id='AcctCredSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformEnAcctCredSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformEnAcctCredSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d6">
				<form id="formEnOriCreInfSgmt" method="post">
					<table class="queryTable" style="width: 100%" >
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;初始债权人名称：</td><td class='queryContent'><input class='inputText' type='text' style="width: 145px;"  name='initCredName' id='InitCredName' /></td>
							<td class='queryTitle'>&nbsp;初始债权人机构代码：</td><td class='queryContent'><input class='inputText' type='text' style="width: 145px;"  name='initCedOrgCode' id='InitCedOrgCode' /></td>
						
						</tr>
						<tr>
								<td class='queryTitle'>&nbsp;原债务种类：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"  name='origDbtCate' id='OrigDbtCate' /></td>
							<td class='queryTitle'>&nbsp;债权转移时的还款状态：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"  name='initRpySts' id='InitRpySts' /></td>

						</tr>
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformEnOriCreInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnOriCreInfSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden' name='origCreditorInfSgmtSeqNo' id='OrigCreditorInfSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformEnOriCreInfSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformEnOriCreInfSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d7">
				<form id="formActLbltyInfSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="actId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;账户状态：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='acctStatus' id='AcctStatus' /></td>
							<td class='queryTitle'>&nbsp;余额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='acctBal' id='AcctBal' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;余额变化日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM' type='text'  name='balChgDate' id='BalChgDate' /></td>
							<td class='queryTitle'>&nbsp;五级分类：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='fiveCate' id='FiveCate' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;五级分类认定日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM'  type='text'  name='fiveCateAdjDate' id='FiveCateAdjDate' /></td>
							<td class='queryTitle'>&nbsp;剩余还款月数：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0" checkId="uInt_SM_S:{PymtPrd:PymtPrd}" type='text'  name='pymtPrd' id='PymtPrd' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;当前逾期总额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0" type='text'  name='totOverd' id='TotOverd' /></td>
							<td class='queryTitle'>&nbsp;当前逾期本金(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0" type='text'  name='overdPrinc' id='OverdPrinc' /></td>
							
						</tr>
							<tr style="height:30px">	
							<td class='queryTitle'>&nbsp;当前逾期天数(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0" type='text'  name='overdDy' id='OverdDy' /></td>													
							<td class='queryTitle'>&nbsp;最近一次实际还款日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM' type='text'  name='latRpyDate' id='LatRpyDate' /></td>
							
						</tr>
							<tr style="height:30px">	
							<td class='queryTitle'>&nbsp;最近一次实际还款金额(正整数)：</td><td class='queryContent'><input class="easyui-numberbox" style="width: 145px;" checkId="uInt..15_AM" data-options="min:0"  type='text'  name='latRpyAmt' id='LatRpyAmt' /></td>													
							<td class='queryTitle'>&nbsp;最近一次实际归还本金(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='latRpyPrincAmt' id='LatRpyPrincAmt' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;最近一次约定还款日：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  type='text'  name='latAgrrRpyDate' id='LatAgrrRpyDate' /></td>
							<td class='queryTitle'>&nbsp;最近一次约定还款金额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0" type='text'  name='latAgrrRpyAmt' id='LatAgrrRpyAmt' /></td>
							
						</tr>
							<tr style="height:30px">
							<td class='queryTitle'>&nbsp;账户关闭日期：</td><td class='queryContent'><input class="easyui-datebox"  style="width: 145px;" type='text'  name='closeDate' id='CloseDate' /></td>									
							<td class='queryTitle'>&nbsp;还款形式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='rpmtType' id='RpmtType' /></td>												
						</tr>
							<tr style="height:30px">
							<td class='queryTitle'>&nbsp;下一次约定还款日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  type='text'  name='nxtAgrrRpyDate' id='NxtAgrrRpyDate' /></td>
						</tr>
							
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformActLbltyInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformActLbltyInfSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden' name='actLbltyInfSgmtSeqNo' id='ActLbltyInfSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="d7ActLbltyInfSgmt"
									style="width: 100px" onclick="byParagraphDelete('d7','ActLbltyInfSgmt')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subActId"
							style="width: 100px" onclick="submitformActLbltyInfSgmt('sub','actId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubActId"
							style="width: 120px" onclick="submitformActLbltyInfSgmt('updSub','actId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d8">
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="EnAccSpeTrsSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-EnAccSpeTrsSgmtmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 450px; padding: 5px;">
				<form id='dialog-EnAccSpeTrsSgmtmodifyform' method="POST" >
					<table style="width: 100%;" id="speId">
						<tr>
							<td class='queryTitle'>&nbsp;交易类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='chanTranType' id='ChanTranType' /></td>
							<td class='queryTitle'>&nbsp;交易日期：</td><td class='queryContent'><input class="easyui-datebox"  style="width: 145px;" type='text' checkId='Date_AM' name='tranDate' id='TranDate' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;交易金额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='tranAmt' id='TranAmt' /></td>
							<td class='queryTitle'>&nbsp;到期日期变更月数：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="uInt..3_AM"  name='dueTranMon' id='DueTranMon' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;交易明细信息：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..200_AO" type='text'  name='detInfo' id='DetInfo' /></td>

						</tr>
						
						<tr>
							<!-- <td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitEnAccSpeTrsSgmtmodifyform('sub');">确定</a></td>
								<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitEnAccSpeTrsSgmtmodifyform('updSub');">提交并生成更正报文</a></td> -->
							<td><input type='hidden'  name='acctSpecTrstDspnSgmtSeqNo' id='AcctSpecTrstDspnSgmtSeqNo' /></td>
							<td><input type='hidden' name='cagOfTrdInfSeqNo' id='CagOfTrdInfSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="d8EnAccSpeTrsSgmtm"
									style="width: 100px" onclick="byParagraphDelete('d8','EnAccSpeTrsSgmtm')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subSpeId"
							style="width: 100px" onclick="submitEnAccSpeTrsSgmtmodifyform('sub','speId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubSpeId"
							style="width: 120px" onclick="submitEnAccSpeTrsSgmtmodifyform('updSub','speId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
			
		</div>
		
		
	</div>
</body>
</html>