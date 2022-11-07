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
<script type="text/javascript" src="${ctx}/js/ent/modify/IncAndExpStaDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>事业单位收入支出表信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>事业单位收入支出表段</span></a></li>
			
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formIncAndExpStBsSgmt" method="post">
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
							<td class='queryTitle'>&nbsp;报表类型细分：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" type='text' checkId='Enum_AM'   name='sheetTypeDivide' id='SheetTypeDivide' /></td>
							<td class='queryTitle'>&nbsp;审计事务所名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..80_AO"  name='auditFirmName' id='AuditFirmName' /></td>

						</tr>
						
												<tr>
							<td class='queryTitle'>&nbsp;审计人员名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..30_AO"  name='auditorName' id='AuditorName' /></td>
							<td class='queryTitle'>&nbsp;审计时间：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" type='text'  name='auditTime' id='AuditTime' /></td>
						</tr>
						
						<tr>
							<td class='queryTitle'>&nbsp;客户资料维护机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN14_AM" type='text'  name='cimoc' id='Cimoc' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>

						</tr>
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformIncAndExpStBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformIncAndExpStBsSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
						
						</tr>
											
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformIncAndExpStBsSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformIncAndExpStBsSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				<form id="formIncAndExpStaSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="incId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;本期财政补助结转结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='curFinSubCarOveBal' id='CurFinSubCarOveBal' /></td>
							<td class='queryTitle'>&nbsp;财政补助收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='financialSubsidyRevenue' id='FinancialSubsidyRevenue' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;事业支出（财政补助支出）：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='undExpFinSubExp' id='UndExpFinSubExp' /></td>
							<td class='queryTitle'>&nbsp;本期事业结转结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='curUndCarOveBal' id='CurUndCarOveBal' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;事业类收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='undertakingsClassRevenue' id='UndertakingsClassRevenue' /></td>
							<td class='queryTitle'>&nbsp;事业收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='undertakingsRevenue' id='UndertakingsRevenue' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;上级补助收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='superiorSubsidyRevenue' id='SuperiorSubsidyRevenue' /></td>
							<td class='queryTitle'>&nbsp;附属单位上缴收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='revTurOveBySubUni' id='RevTurOveBySubUni' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;其他收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherRevenue' id='OtherRevenue' /></td>
							<td class='queryTitle'>&nbsp;（其他收入科目下）捐赠收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='donationIncome' id='DonationIncome' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;事业类支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='undertakingsClassExpenditure' id='UndertakingsClassExpenditure' /></td>
							<td class='queryTitle'>&nbsp;事业支出（非财政补助支出）：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='undExpNonFinSubExp' id='UndExpNonFinSubExp' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;上缴上级支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='paymentToTheHigherAuthority' id='PaymentToTheHigherAuthority' /></td>
							<td class='queryTitle'>&nbsp;对附属单位补助支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='graToTheAuxOrg' id='GraToTheAuxOrg' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;其他支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='otherExpenditure' id='OtherExpenditure' /></td>
							<td class='queryTitle'>&nbsp;本期经营结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='currentOperatingBalance' id='CurrentOperatingBalance' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;经营收入：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='operatingRevenue' id='OperatingRevenue' /></td>
							<td class='queryTitle'>&nbsp;经营支出：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='operatingExpenditure' id='OperatingExpenditure' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;弥补以前年度亏损后的经营结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='opeBalMadUpForOfThePreYeaOpeLo' id='OpeBalMadUpForOfThePreYeaOpeLo' /></td>
							<td class='queryTitle'>&nbsp;本年非财政补助结转结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonFinSubCarOveBalThiYea' id='NonFinSubCarOveBalThiYea' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;非财政补助结转：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonFinancialAidCarriedOver' id='NonFinancialAidCarriedOver' /></td>
							<td class='queryTitle'>&nbsp;本年非财政补助结余：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='nonFinancialAidBalanceThisYear' id='NonFinancialAidBalanceThisYear' /></td>
							
						</tr>
								
												<tr>
							<td class='queryTitle'>&nbsp;应缴企业所得税：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='enterpriseIncomeTaxPayable' id='EnterpriseIncomeTaxPayable' /></td>
							<td class='queryTitle'>&nbsp;提取专用基金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='specialFundsToExtract' id='SpecialFundsToExtract' /></td>
							
						</tr>
												<tr>
							<td class='queryTitle'>&nbsp;转入事业基金：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" data-options="min:0,precision:2" checkId="Float(17,2)_AO" type='text'  name='publicFundTurnedInto' id='PublicFundTurnedInto' /></td>

						</tr>
												
						
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformIncAndExpStaSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformIncAndExpStaSgmt('updSub');"/> </td> -->
							<td class='queryContent'></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformIncAndExpStaSgmt('sub','incId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformIncAndExpStaSgmt('updSub','incId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
				
		</div>
		
		
	</div>
</body>
</html>