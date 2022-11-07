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
<script type="text/javascript" src="${ctx}/js/ent/modify/CashFlowsDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业现金流量表信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>现金流量表段</span></a></li>
			
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formCashFlowsBsSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="sgmtId">
						
						<tr>
							<td class='queryTitle'>&nbsp;企业名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..80_AM"  name='entName' id='EntName' /></td>
							<td class='queryTitle'>&nbsp;企业身份标识类型：</td><td class='queryContent'><input class='easyui-combobox'  style="width: 145px;" checkId='Enum_AM'   name='entCertType' id='EntCertType' /></td>
					
						</tr>
					
						<tr>
						<td class='queryTitle'>&nbsp;企业身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..40_AM_A:{EntCertType:EntCertType}"  name='entCertNum' id='EntCertNum' /></td>
						<td class='queryTitle'>&nbsp;信息报告日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" type='text' checkId='Date_AM'  name='rptDate' id='RptDate' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;报表年份：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId='Year_AM'  name='sheetYear' id='SheetYear' /></td>
							<td class='queryTitle'>&nbsp;报表类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" type='text' checkId='Enum_AM'   name='sheetType' id='SheetType' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;报表类型细分：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  type='text'  name='sheetTypeDivide' id='SheetTypeDivide' /></td>
							<td class='queryTitle'>&nbsp;审计事务所名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='auditFirmName' checkId="ANC..80_AO" id='AuditFirmName' /></td>

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
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformCashFlowsBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformCashFlowsBsSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
						
						</tr>
											
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformCashFlowsBsSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformCashFlowsBsSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				<form id="formCashFlows2007Sgmt" method="post">
					<table class="queryTable" style="width: 100%" id="cashId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;销售商品和提供劳务收到的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casRecFroSalOfGooOrRenOfSer' id='CasRecFroSalOfGooOrRenOfSer' /></td>
							<td class='queryTitle'>&nbsp;收到的税费返还：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='taxRefunds' id='TaxRefunds' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;收到的其他与经营活动有关的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='othCasRecRelToOpeAct' id='OthCasRecRelToOpeAct' /></td>
							<td class='queryTitle'>&nbsp;经营活动现金流入小计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totCasInfFroOpeAct' id='TotCasInfFroOpeAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;购买商品、接受劳务支付的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashPaidForGoodsAndServices' id='CashPaidForGoodsAndServices' /></td>
							<td class='queryTitle'>&nbsp;支付给职工以及为职工支付的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casPaiToAndOnBehOfEmp' id='CasPaiToAndOnBehOfEmp' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;支付的各项税费：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='paymentsOfAllTypesOfTaxes' id='PaymentsOfAllTypesOfTaxes' /></td>
							<td class='queryTitle'>&nbsp;支付其他与经营活动有关的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='othCasPayFroOpeAct' id='OthCasPayFroOpeAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;经营活动现金流出小计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totCasOutFroOpeAct' id='TotCasOutFroOpeAct' /></td>
							<td class='queryTitle'>&nbsp;经营活动产生的现金流量净额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasFloFroOpeAct' id='NetCasFloFroOpeAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;收回投资所收到的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casRecFroRetOfInv' id='CasRecFroRetOfInv' /></td>
							<td class='queryTitle'>&nbsp;取得投资收益所收到的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashReceivedFromOnvestments' id='CashReceivedFromOnvestments' /></td>
							
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;处置定资产无形资产和其他长期资产所收回的现金净额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasRecFroDisOfFixAssIntAss' id='NetCasRecFroDisOfFixAssIntAss' /></td>
							<td class='queryTitle'>&nbsp;处置子公司及其他营业单位收到的现金净额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasInfOfDisOfSubAndOthBusEn' id='NetCasInfOfDisOfSubAndOthBusEn' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;收到的其他与投资活动有关的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casRecRelToOthInvAct' id='CasRecRelToOthInvAct' /></td>
							<td class='queryTitle'>&nbsp;投资活动现金流入小计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totCasInfFroInvAct' id='TotCasInfFroInvAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;购建固定资产无形资产和其他长期资产所支付的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casPaiToAcqFixAssIntAssAndOth' id='CasPaiToAcqFixAssIntAssAndOth' /></td>
							<td class='queryTitle'>&nbsp;投资所支付的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashPaymentsForInvestments' id='CashPaymentsForInvestments' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;取得子公司及其他营业单位支付的现金净额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasOutOfProOfSubAndOthBusUn' id='NetCasOutOfProOfSubAndOthBusUn' /></td>
							<td class='queryTitle'>&nbsp;支付的其他与投资活动有关的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casPayRelToOthInvAct' id='CasPayRelToOthInvAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;投资活动现金流出小计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='subTotalOfCashOutflows' id='SubTotalOfCashOutflows' /></td>
							<td class='queryTitle'>&nbsp;投资活动产生的现金流量净额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasFlFroInvAct' id='NetCasFlFroInvAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;吸收投资收到的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashReceivedFromInvestors' id='CashReceivedFromInvestors' /></td>
							<td class='queryTitle'>&nbsp;取得借款收到的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashFromBorrowings' id='CashFromBorrowings' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;收到的其他与筹资活动有关的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='othCasRecRelToFinAct' id='OthCasRecRelToFinAct' /></td>
							<td class='queryTitle'>&nbsp;筹资活动现金流入小计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totCasInfFroFinAct' id='TotCasInfFroFinAct' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;偿还债务所支付的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='cashRepaymentsForDebts' id='CashRepaymentsForDebts' /></td>
							<td class='queryTitle'>&nbsp;分配股利、利润或偿付利息所支付的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='casPayForDisOfDivOrProAndIntEx' id='CasPayForDisOfDivOrProAndIntEx' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;支付的其他与筹资活动有关的现金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casPayRelToOthFinAct' id='CasPayRelToOthFinAct' /></td>
							<td class='queryTitle'>&nbsp;筹资活动现金流出小计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totCasOutFroFinAct' id='TotCasOutFroFinAct' /></td>
							

						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;筹集活动产生的现金流量净额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasFloFroFinAct' id='NetCasFloFroFinAct' /></td>
							<td class='queryTitle'>&nbsp;汇率变动对现金的影响：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='effOfExcRatChaOnCas' id='EffOfExcRatChaOnCas' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;现金及现金等价物净增加额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netIncInCasAndCasEqu' id='NetIncInCasAndCasEqu' /></td>
							<td class='queryTitle'>&nbsp;期初现金及现金等价物余额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='iniCasAndCasEquBal' id='IniCasAndCasEquBal' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;期末现金及现金等价物余额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='theFinCasAndCasEquBal' id='TheFinCasAndCasEquBal' /></td>
							<td class='queryTitle'>&nbsp;净利润：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netProfit' id='netProfit' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;资产减值准备：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='provisionForAssetImpairment' id='ProvisionForAssetImpairment' /></td>
							<td class='queryTitle'>&nbsp;固定资产折旧、油气资产折耗、生产性生物资产折旧：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='depreciationOfFixedAssets' id='DepreciationOfFixedAssets' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;无形资产摊销：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='amortisationOfIntangibleAssets' id='AmortisationOfIntangibleAssets' /></td>
							<td class='queryTitle'>&nbsp;长期待摊费用摊销：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='amoOfLonTerDefExp' id='AmoOfLonTerDefExp' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;待摊费用减少：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='decreaseOfDefferedExpenses' id='DecreaseOfDefferedExpenses' /></td>
							<td class='queryTitle'>&nbsp;预提费用增加：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='additionOfAccuedExpense' id='AdditionOfAccuedExpense' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;处置固定资产无形资产和其他长期资产的损失：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='losOnDisOfFixAssIntAssAndOthLo' id='LosOnDisOfFixAssIntAssAndOthLo' /></td>
							<td class='queryTitle'>&nbsp;固定资产报废损失：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='lossesOnScrappingOfFixedAssets' id='LossesOnScrappingOfFixedAssets' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;公允价值变动损失：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='proOrLosFroChaInFaiVal' id='ProOrLosFroChaInFaiVal' /></td>
							<td class='queryTitle'>&nbsp;财务费用：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='financeExpense' id='FinanceExpense' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;投资损失：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='lossesArsingFromInvestment' id='LossesArsingFromInvestment' /></td>
							<td class='queryTitle'>&nbsp;递延所得税资产减少：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='deferredIncomeTaxAssets' id='DeferredIncomeTaxAssets' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;递延所得税负债增加：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='deferredIncomeTaxLiabilities' id='DeferredIncomeTaxLiabilities' /></td>
							<td class='queryTitle'>&nbsp;存货的减少：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='decreaseInInventories' id='DecreaseInInventories' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;经营性应收项目的减少：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='decreaseInOperatingReceivables' id='DecreaseInOperatingReceivables' /></td>
							<td class='queryTitle'>&nbsp;经营性应付项目的增加：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='increaseInOperatingReceivables' id='IncreaseInOperatingReceivables' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;（净利润调节为经营活动现金流量科目下）其他：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='others' id='Others' /></td>
							<td class='queryTitle'>&nbsp;经营活动产生的现金流量净额2：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netCasFloFroOpeActMi' id='NetCasFloFroOpeActMi' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;债务转为资本：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='debtsTransferToCapital' id='DebtsTransferToCapital' /></td>
							<td class='queryTitle'>&nbsp;一年内到期的可转换公司债券：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='oneYearDueConvertibleBonds' id='OneYearDueConvertibleBonds' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;融资租入固定资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='financingRentToTheFixedAsset' id='FinancingRentToTheFixedAsset' /></td>
							<td class='queryTitle'>&nbsp;（不涉及现金收支的投资和筹资活动科目下）其他：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonCashOthers' id='NonCashOthers' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;现金的期末余额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashAtTheEndOfPeriod' id='CashAtTheEndOfPeriod' /></td>
							<td class='queryTitle'>&nbsp;现金的期初余额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='cashAtTheBeginningOfThePeriod' id='CashAtTheBeginningOfThePeriod' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;现金等价物的期末余额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casEquAtTheEndOfThePer' id='CasEquAtTheEndOfThePer' /></td>
							<td class='queryTitle'>&nbsp;现金等价物的期初余额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='casRquAtTheBegOfThePer' id='CasRquAtTheBegOfThePer' /></td>
							
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;现金及现金等价物净增加额2：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='netIncInCasAndCasEquMi' id='NetIncInCasAndCasEquMi' /></td>
							<td class='queryTitle'></td><td class='queryContent'></td>
						</tr>
							
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformCashFlows2007Sgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformCashFlows2007Sgmt('updSub');"/> </td> -->
							<td class='queryContent'></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformCashFlows2007Sgmt('sub','cashId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformCashFlows2007Sgmt('updSub','cashId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
				
		</div>
		
		
	</div>
</body>
</html>