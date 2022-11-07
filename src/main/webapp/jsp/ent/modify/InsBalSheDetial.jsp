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
<script type="text/javascript" src="${ctx}/js/ent/modify/InsBalSheDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>事业单位资产负载表信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>事业单位资产负债表段</span></a></li>
			
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formInsBalSheBsSgmt" method="post">
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
							<td class='queryTitle'>&nbsp;报表年份：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId='Year_AM' type='text'  name='sheetYear' id='SheetYear' /></td>
							<td class='queryTitle'>&nbsp;报表类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" type='text' checkId='Enum_AM'   name='sheetType' id='SheetType' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;报表类型细分：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" type='text' checkId='Enum_AM'   name='sheetTypeDivide' id='SheetTypeDivide' /></td>
							<td class='queryTitle'>&nbsp;审计事务所名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..80_AO"  name='auditFirmName' id='AuditFirmName' /></td>

						</tr>
						
												<tr>
							<td class='queryTitle'>&nbsp;审计人员名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='auditorName' checkId="ANC..30_AO" id='AuditorName' /></td>
							<td class='queryTitle'>&nbsp;审计时间：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" type='text'  name='auditTime' id='AuditTime' /></td>
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;客户资料维护机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="AN14_AM"  name='cimoc' id='Cimoc' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>

						</tr>
						<tr style="text-align: center;">
		<!-- 					<td class='queryContent'><input type="button" value="提交" onclick="submitformInsBalSheBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformInsBalSheBsSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
						
						</tr>
											
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformInsBalSheBsSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformInsBalSheBsSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				<form id="formInsBalSheSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="insId">
						<tr style="height:30px">							
						
							<td class='queryTitle'>&nbsp;货币资金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='currencyFunds' id='CurrencyFunds' /></td>
							<td class='queryTitle'>&nbsp;短期投资：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='shortTermInvestments' id='ShortTermInvestments' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;财政应返还额度：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='amountOfFinancialReturn' id='AmountOfFinancialReturn' /></td>
							<td class='queryTitle'>&nbsp;应收票据：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='notesReceivable' id='NotesReceivable' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;应收账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='accountsReceivable' id='AccountsReceivable' /></td>
							<td class='queryTitle'>&nbsp;预付账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='prepayments' id='Prepayments' /></td>
								
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他应收款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='otherReceivables' id='OtherReceivables' /></td>
							<td class='queryTitle'>&nbsp;存货：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='inventories' id='Inventories' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他流动资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='otherCurrentAssets' id='OtherCurrentAssets' /></td>
							<td class='queryTitle'>&nbsp;流动资产合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='totalCurrentAssets' id='TotalCurrentAssets' /></td>
							
						</tr>
												<tr>
						<td class='queryTitle'>&nbsp;长期投资：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='longTermInvestment' id='LongTermInvestment' /></td>
							<td class='queryTitle'>&nbsp;固定资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='fixedAssets' id='FixedAssets' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;固定资产原价：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='originalCostOfFixedAsset' id='OriginalCostOfFixedAsset' /></td>
							<td class='queryTitle'>&nbsp;累计折旧：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='fixAssAccDep' id='FixAssAccDep' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;在建工程：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='constructionInProcess' id='ConstructionInProcess' /></td>
							<td class='queryTitle'>&nbsp;无形资产：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='intangibleAssets' id='IntangibleAssets' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;无形资产原价：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='oriPriOfIntAss' id='OriPriOfIntAss' /></td>
							<td class='queryTitle'>&nbsp;累计摊销：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='accumulatedAmortization' id='AccumulatedAmortization' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;待处置资产损溢：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='asssToBeProPenThePro' id='AsssToBeProPenThePro' /></td>
							<td class='queryTitle'>&nbsp;非流动资产合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalNonCurrentAssets' id='TotalNonCurrentAssets' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;资产总计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='totalAssets' id='TotalAssets' /></td>
							<td class='queryTitle'>&nbsp;短期借款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='shortTermBorrowings' id='ShortTermBorrowings' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;应缴税费：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='taxPayable' id='TaxPayable' /></td>
							<td class='queryTitle'>&nbsp;应缴国库款：</td><td class='queryContent'><input class='easyui-numberbox'  style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='treasuryPayable' id='TreasuryPayable' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;应缴财政专户款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='payableFinancialSpecialAccount' id='PayableFinancialSpecialAccount' /></td>
							<td class='queryTitle'>&nbsp;应付职工薪酬：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='employeeBenefitsPayable' id='EmployeeBenefitsPayable' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;应付票据：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='notesPayable' id='NotesPayable' /></td>
							<td class='queryTitle'>&nbsp;应付账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='accountsPayable' id='AccountsPayable' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;预收账款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='receiptsInAdvance' id='ReceiptsInAdvance' /></td>
							<td class='queryTitle'>&nbsp;其他应付款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherPayables' id='OtherPayables' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他流动负债：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherCurrentLiabilities' id='OtherCurrentLiabilities' /></td>
							<td class='queryTitle'>&nbsp;流动负债合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalCurrentLiabilities' id='TotalCurrentLiabilities' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;长期借款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='longTermBorrowings' id='LongTermBorrowings' /></td>
							<td class='queryTitle'>&nbsp;长期应付款：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='longTermPayables' id='LongTermPayables' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;非流动负债合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalNonCurrentLiabilities' id='TotalNonCurrentLiabilities' /></td>
							<td class='queryTitle'>&nbsp;负债合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalLiabilities' id='TotalLiabilities' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;事业基金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='enterpriseFund' id='EnterpriseFund' /></td>
							<td class='queryTitle'>&nbsp;非流动资产基金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonCurrentAssetsFund' id='NonCurrentAssetsFund' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;专用基金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='specialPurposeFunds' id='SpecialPurposeFunds' /></td>
							<td class='queryTitle'>&nbsp;财政补助结转：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='financialAidCarriedOver' id='FinancialAidCarriedOver' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;财政补助结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='financialAidBalance' id='FinancialAidBalance' /></td>
							
							<td class='queryTitle'>&nbsp;非财政补助结转：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonFinancialAidCarriedOver' id='NonFinancialAidCarriedOver' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;非财政补助结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonFinancialAidBalance' id='NonFinancialAidBalance' /></td>
									<td class='queryTitle'>&nbsp;事业结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='undertakingsBalance' id='UndertakingsBalance' /></td>
							
						</tr>
						<tr>
						<td class='queryTitle'>&nbsp;经营结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='operatingBalance' id='OperatingBalance' /></td>
							
							<td class='queryTitle'>&nbsp;净资产合计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalNetAssets' id='TotalNetAssets' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;负债和净资产总计：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='totalLiabilitiesAndNetAssets' id='TotalLiabilitiesAndNetAssets' /></td>
							
						</tr>
						
						
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformInsBalSheSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformInsBalSheSgmt('updSub');"/> </td> -->
							<td class='queryContent'></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformInsBalSheSgmt('sub','insId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformInsBalSheSgmt('updSub','insId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
				
		</div>
		
		
	</div>
</body>
</html>