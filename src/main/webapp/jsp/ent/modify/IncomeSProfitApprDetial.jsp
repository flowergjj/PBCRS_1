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
<script type="text/javascript" src="${ctx}/js/ent/modify/IncomeSProfitApprDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业利润及利润分配信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>企业利润及利润分配表段</span></a></li>
			
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formIncomeSBsSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="sgmtId">
						
						<tr>
							<td class='queryTitle'>&nbsp;企业名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..80_AM"  name='entName' id='EntName' /></td>
							<td class='queryTitle'>&nbsp;企业身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='entCertType' id='EntCertType' /></td>
					
						</tr>
					
						<tr>
						<td class='queryTitle'>&nbsp;企业身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..40_AM_A:{EntCertType:EntCertType}"  type='text'  name='entCertNum' id='EntCertNum' /></td>
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
							<td class='queryTitle'>&nbsp;审计人员名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='auditorName'   checkId="ANC..30_AO" id='AuditorName' /></td>
							<td class='queryTitle'>&nbsp;审计时间：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" type='text'  name='auditTime' id='AuditTime' /></td>
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;客户资料维护机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN14_AM" type='text'  name='cimoc' id='Cimoc' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>

						</tr>
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformIncomeSBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformIncomeSBsSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
						
						</tr>
											
					</table>
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformIncomeSBsSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformIncomeSBsSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				<form id="formIncomeStatement07" method="post">
					<table class="queryTable" style="width: 100%" id="incomeId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;营业收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='revenueOfSales' id='RevenueOfSales' /></td>
							<td class='queryTitle'>&nbsp;营业成本：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='costOfSales' id='CostOfSales' /></td>
						
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;营业税金及附加：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='businessAndOtherTaxes' id='BusinessAndOtherTaxes' /></td>
							<td class='queryTitle'>&nbsp;销售费用：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='sellingExpenses' id='SellingExpenses' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;管理费用：</td><td class='queryContent'><input  class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='generalAndAdministrativeExpenses' id='GeneralAndAdministrativeExpenses' /></td>
							<td class='queryTitle'>&nbsp;财务费用：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='financialExpense' id='FinancialExpense' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;资产减值损失：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='impairmentLossOfAssets' id='ImpairmentLossOfAssets' /></td>
							<td class='queryTitle'>&nbsp;公允价值变动净收益：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='proOrLosAriFroChaInFaiVal' id='ProOrLosAriFroChaInFaiVal' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;投资净收益：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='investmentIncome' id='InvestmentIncome' /></td>
							<td class='queryTitle'>&nbsp;对联营企业和合营企业的投资收益：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='invFroAffBusAndCooEnt' id='InvFroAffBusAndCooEnt' /></td>
						
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;营业利润：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='operatingProfits' id='OperatingProfits' /></td>
							<td class='queryTitle'>&nbsp;营业外收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO"  type='text'  name='nonOperatingIncome' id='NonOperatingIncome' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;营业外支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonOperatingExpenses' id='NonOperatingExpenses' /></td>
							<td class='queryTitle'>&nbsp;非流动资产损失（其中：非流动资产处置损失）：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonCurrentAssets' id='NonCurrentAssets' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;利润总额：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='profitBeforeTax' id='ProfitBeforeTax' /></td>
							<td class='queryTitle'>&nbsp;所得税费用：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='incomeTaxExpense' id='IncomeTaxExpense' /></td>
							
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;净利润：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='netProfit' id='NetProfit' /></td>
							<td class='queryTitle'>&nbsp;基本每股收益：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" type='text'  name='basicEarningsPerShare' id='BasicEarningsPerShare' /></td>
							
						</tr>
						<tr style="height:30px">							
								<td class='queryTitle'>&nbsp;稀释每股收益：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='dilutedEarningsPerShare' id='DilutedEarningsPerShare' /></td>
							
						</tr>
						
						<tr style="text-align: center;">
				<!-- 			<td class='queryContent'><input type="button" value="提交" onclick="submitformIncomeStatement07('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformIncomeStatement07('updSub');"/> </td> -->
							<td class='queryContent'></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformIncomeStatement07('sub','incomeId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformIncomeStatement07('updSub','incomeId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
				
		</div>
		
		
	</div>
</body>
</html>