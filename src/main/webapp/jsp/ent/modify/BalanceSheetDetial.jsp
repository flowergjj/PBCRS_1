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
<script type="text/javascript" src="${ctx}/js/ent/modify/BalanceSheetDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业基资产负债记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>资产负债表段</span></a></li>
			
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="display: none"  id="d1" >
				<form id="formBalanceSheetBsSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="sgmtId">
						
						<tr>
							<td class='queryTitle'>&nbsp;企业名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..80_AM" type='text'  name='entName' id='EntName' /></td>
							<td class='queryTitle'>&nbsp;企业身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='entCertType' id='EntCertType' /></td>
					
						</tr>
					
						<tr>
						<td class='queryTitle'>&nbsp;企业身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..40_AM_A:{EntCertType:EntCertType}" type='text'  name='entCertNum' id='EntCertNum' /></td>
						<td class='queryTitle'>&nbsp;信息报告日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM' type='text'  name='rptDate' id='RptDate' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;报表年份：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId='Year_AM'  name='sheetYear' id='SheetYear' /></td>
							<td class='queryTitle'>&nbsp;报表类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  type='text'  name='sheetType' id='SheetType' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;报表类型细分：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  type='text'  name='sheetTypeDivide' id='SheetTypeDivide' /></td>
							<td class='queryTitle'>&nbsp;审计事务所名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..80_AO" name='auditFirmName' id='AuditFirmName' /></td>

						</tr>
						
												<tr>
							<td class='queryTitle'>&nbsp;审计人员名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='auditorName' checkId="ANC..30_AO" id='AuditorName' /></td>
							<td class='queryTitle'>&nbsp;审计时间：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" type='text'  name='auditTime' id='AuditTime' /></td>
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;客户资料维护机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN14_AM" type='text'  name='cimoc' id='Cimoc' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>

						</tr>
						<tr style="text-align: center;">
		<!-- 					<td class='queryContent'><input type="button" value="提交" onclick="submitformBalanceSheetBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformBalanceSheetBsSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
						
						</tr>
											
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformBalanceSheetBsSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformBalanceSheetBsSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="display: none;height: 100%"  id="d2">
				<form id="formBalanceSheet2007Sgmt" method="post">
					<table class="queryTable" style="width: 100%" id="balanId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;货币资金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="Float(17,2)_AO" data-options="min:0,precision:2" type='text'  name='currencyFunds' id='CurrencyFunds' /></td>
							<td class='queryTitle'>&nbsp;交易性金融资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="Float(17,2)_AO" data-options="min:0,precision:2" type='text'  name='financialAssetsHeldForTrading' id='FinancialAssetsHeldForTrading' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;应收票据：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='notesReceivable' id='NotesReceivable' /></td>
<td class='queryTitle'>&nbsp;应收账款：</td><td class='queryContent'><input class='easyui-numberbox' data-options="min:0,precision:2" style="width: 145px;" type='text' checkId="Float(17,2)_AO"  name='accountsReceivable' id='accountsReceivable' /></td>
					
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;预付账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="Float(17,2)_AO" data-options="min:0,precision:2" type='text'  name='prepayments' id='Prepayments' /></td>
<td class='queryTitle'>&nbsp;应收利息：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='interestReceivable' id='interestReceivable' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;应收股利：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="Float(17,2)_AO" data-options="min:0,precision:2" type='text'  name='dividendsReceivable' id='DividendsReceivable' /></td>
<td class='queryTitle'>&nbsp;其他应收款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text' checkId="Float(17,2)_AO"  name='otherReceivables' id='otherReceivables' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;存货：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='inventories' id='Inventories' /></td>
<td class='queryTitle'>&nbsp;一年内到期的非流动资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text' checkId="Float(17,2)_AO"  name='curPorOfNonCurAss' id='CurPorOfNonCurAss' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;其他流动资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherCurrentAssets' id='OtherCurrentAssets' /></td>
<td class='queryTitle'>&nbsp;流动资产合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text' checkId="Float(17,2)_AO"  name='totalCurrentAssets' id='totalCurrentAssets' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;可供出售的金融资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='finAssAvaForSal' id='FinAssAvaForSal' /></td>
<td class='queryTitle'>&nbsp;持有至到期投资：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text' checkId="Float(17,2)_AO"  name='heldToMaturityInvestments' id='heldToMaturityInvestments' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;长期股权投资：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="Float(17,2)_AO" data-options="min:0,precision:2" type='text'  name='longTermEquityInvestment' id='LongTermEquityInvestment' /></td>
<td class='queryTitle'>&nbsp;长期应收款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='longTermReceivables' id='longTermReceivables' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;投资性房地产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="Float(17,2)_AO" data-options="min:0,precision:2" type='text'  name='investmentProperties' id='InvestmentProperties' /></td>
<td class='queryTitle'>&nbsp;固定资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='fixedAssets' id='fixedAssets' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;在建工程：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='constructionInProgress' id='ConstructionInProgress' /></td>
<td class='queryTitle'>&nbsp;工程物资：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='constructionMaterials' id='ConstructionMaterials' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;固定资产清理：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='fixedAssetsPendingForDisposal' id='FixedAssetsPendingForDisposal' /></td>
<td class='queryTitle'>&nbsp;生产性生物资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonCurrentBiologicalAssets' id='NonCurrentBiologicalAssets' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;油气资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='oilAndGasAssets' id='OilAndGasAssets' /></td>
<td class='queryTitle'>&nbsp;无形资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='intangibleAssets' id='intangibleAssets' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;开发支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='developmentDisbursements' id='DevelopmentDisbursements' /></td>
<td class='queryTitle'>&nbsp;商誉：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='goodwill' id='Goodwill' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;长期待摊费用：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='longTermDeferredExpenses' id='LongTermDeferredExpenses' /></td>
<td class='queryTitle'>&nbsp;递延所得税资产：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" class='easyui-numberbox' data-options="min:0,precision:2" checkId="Float(17,2)_AO"  name='deferredTaxAssets' id='DeferredTaxAssets' /></td>

						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;其他非流动资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherNonCurrentAssets' id='OtherNonCurrentAssets' /></td>
<td class='queryTitle'>&nbsp;非流动资产合计：</td><td class='queryContent'><input class='easyui-textbox' class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO"  name='totalNonCurrentAssets' id='TotalNonCurrentAssets' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;资产总计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalAssets' id='TotalAssets' /></td>
<td class='queryTitle'>&nbsp;短期借款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='shortTermBorrowings' id='ShortTermBorrowings' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;交易性金融负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='finLiaHelForTra' id='FinLiaHelForTra' /></td>
<td class='queryTitle'>&nbsp;应付票据：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='notesPayable' id='NotesPayable' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;应付账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='accountsPayable' id='AccountsPayable' /></td>
<td class='queryTitle'>&nbsp;预收账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='receiptsInAdvance' id='ReceiptsInAdvance' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;应付利息：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='interestPayable' id='InterestPayable' /></td>
<td class='queryTitle'>&nbsp;应付职工薪酬：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='employeeBenefitsPayable' id='EmployeeBenefitsPayable' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;应交税费：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='taxesPayable' id='TaxesPayable' /></td>
<td class='queryTitle'>&nbsp;应付股利：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='dividendsPayable' id='DividendsPayable' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他应付款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherPayable' id='OtherPayable' /></td>
<td class='queryTitle'>&nbsp;一年内到期的非流动负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='curPorOfLonTerLia' id='CurPorOfLonTerLia' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他流动负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherCurrentLiabilities' id='OtherCurrentLiabilities' /></td>
<td class='queryTitle'>&nbsp;流动负债合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalCurrentLiabilities' id='TotalCurrentLiabilities' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;长期借款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='longTermBorrowings' id='LongTermBorrowings' /></td>
<td class='queryTitle'>&nbsp;应付债券：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='bondsPayable' id='BondsPayable' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;长期应付款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='longTermPayables' id='LongTermPayables' /></td>
<td class='queryTitle'>&nbsp;专项应付款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='grantsPayable' id='GrantsPayable' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;预计负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='provisions' id='Provisions' /></td>
<td class='queryTitle'>&nbsp;递延所得税负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='deferredTaxLiabilities' id='DeferredTaxLiabilities' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他非流动负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherNonCurrentLiabilities' id='OtherNonCurrentLiabilities' /></td>
<td class='queryTitle'>&nbsp;非流动负债合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalNonCurrentLiabilities' id='TotalNonCurrentLiabilities' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;负债合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalLiabilities' id='TotalLiabilities' /></td>
<td class='queryTitle'>&nbsp;实收资本（或股本）：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='paidInCapitalOrShareCapital' id='PaidInCapitalOrShareCapital' /></td>

						</tr>
						<tr>
						<td class='queryTitle'>&nbsp;资本公积：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='capitalrRserve' id='CapitalrRserve' /></td>
<td class='queryTitle'>&nbsp;减：库存股：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='lessTreasuryStocks' id='LessTreasuryStocks' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;盈余公积：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='surplusReserve' id='SurplusReserve' /></td>
<td class='queryTitle'>&nbsp;未分配利润：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='unappropriatedProfit' id='UnappropriatedProfit' /></td>

						</tr>
						<tr>
						<td class='queryTitle'>&nbsp;所有者权益合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalEquity' id='TotalEquity' /></td>
<td class='queryTitle'>&nbsp;负债和所有者权益总计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalEquityAndLiabilities' id='TotalEquityAndLiabilities' /></td>

						</tr>
						
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformBalanceSheet2007Sgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformBalanceSheet2007Sgmt('updSub');"/> </td> -->
							<td class='queryContent'></td>
						
						</tr>
					</table>
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformBalanceSheet2007Sgmt('sub','balanId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformBalanceSheet2007Sgmt('updSub','balanId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
				
		</div>
		
		
	</div>
</body>
</html>