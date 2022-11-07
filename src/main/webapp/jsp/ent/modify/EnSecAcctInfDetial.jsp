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
<script type="text/javascript" src="${ctx}/js/ent/modify/EnSecAcctInfDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业担保交易信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>账户基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>账户基本信息段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d3');"><span>相关还款责任人段</span></a></li>
<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d4');"><span>抵质押物信息段</span></a></li>-->				
<!--                  <li><a href="javascript:void(0)"  onclick="showDetial('d5');"><span>授信额度信息段</span></a></li>
 -->				<li><a href="javascript:void(0)"  onclick="showDetial('d6');"><span>在保责任信息段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formEnGuarAcctBsSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="sgmtId">
						<tr style="height:30px">	
							<td class='queryTitle'>&nbsp;企业账户标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='codes' id='codes'  readonly="readonly"/></td>												
							<td class='queryTitle'>&nbsp;账户类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='acctType' id='AcctType' /></td>
							<td class='queryTitle'>&nbsp;征信企业账户标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN..60_AM" type='text'  name='acctCode' id='AcctCode' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;信息报告日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM'  type='text'  name='rptDate' id='RptDate' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;债务人名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..80_AM" type='text'  name='name' id='Name' /></td>
							<td class='queryTitle'>&nbsp;债务人身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='idtype' id='IDType' /></td>
							
						</tr>
						<tr>						
												<td class='queryTitle'>&nbsp;债务人身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..40_AM_A:{IDType:IDType}" name='idnum' id='IDNum' /></td>
							<td class='queryTitle'>&nbsp;业务管理机构代码：</td><td class='queryContent'><input class='easyui-textbox' checkId="AN14_AM" style="width: 145px;" type='text'  name='mngmtOrgCode' id='MngmtOrgCode' /></td>
		
						</tr>
				
						<tr>
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='guarAcctBsSgmtSeqNo' id='GuarAcctBsSgmtSeqNo' /></td>
					
						</tr>
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformEnGuarAcctBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnGuarAcctBsSgmt('updSub');"/> </td> -->
							<td class='queryContent'></td>
						
						</tr>
					</table>
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformEnGuarAcctBsSgmt('sub','sgmtId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformEnGuarAcctBsSgmt('updSub','sgmtId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				<form id="formEnGuaAccBsInfSgmt" method="post">
					
					<table class="queryTable" style="width: 100%" id="guaaccId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;担保业务大类：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='busiLines' id='BusiLines' /></td>
							<td class='queryTitle'>&nbsp;担保业务细类：</td><td class='queryContent'><input class='easyui-combobox'  style="width: 145px;" checkId='Enum_AM'   name='busiDtilLines' id='BusiDtilLines' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;开户日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM'  type='text'  name='openDate' id='OpenDate' /></td>
							<td class='queryTitle'>&nbsp;担保金额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='guarAmt' id='GuarAmt' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;币种：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='cy' id='Cy' /></td>
							<td class='queryTitle'>&nbsp;到期日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM'  type='text'  name='dueDate' id='DueDate' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;反担保方式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='guarMode' id='GuarMode' /></td>
							<td class='queryTitle'>&nbsp;其他还款保证方式：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='othRepyGuarWay' id='OthRepyGuarWay' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;保证金百分比(正整数不足1填0)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..3_AM" data-options="min:0" type='text'  name='secDep' id='SecDep' /></td>
							<td class='queryTitle'>&nbsp;担保合同文本编号：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..60_AO"  name='ctrctTxtCode' id='CtrctTxtCode' /></td>

						</tr>
						
						
						
					
						<tr style="text-align: center;">
			<!-- 				<td class='queryContent'><input type="button" value="提交" onclick="submitformEnGuaAccBsInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnGuaAccBsInfSgmt('updSub');"/> </td> -->
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='guarAcctBsInfSgmtSeqNo' id='GuarAcctBsInfSgmtSeqNo' /></td>
						
						</tr>
					</table>
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 100px" onclick="byParagraphDelete('d2','EnGuaAccBsInfSgmt')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformEnGuaAccBsInfSgmt('sub','guaaccId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformEnGuaAccBsInfSgmt('updSub','guaaccId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d3">
					
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="GseRltRepInfSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-GseRltRepInfSgmtmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 450px; padding: 5px;">
				<form id='dialog-GseRltRepInfSgmtmodifyform' method="POST" >
					<table style="width: 100%;" id="gseId">
						<tr>
							<td class='queryTitle'>&nbsp;身份类别：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='infoIDType' id='InfoIDType' /></td>
							<td class='queryTitle'>&nbsp;责任人名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..80_AM" type='text'  name='arlpName' id='ArlpName' /></td>
							
	
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;责任人身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"  checkId='Enum_AM'   name='arlpCertType' id='ArlpCertType' /></td>
							<td class='queryTitle'>&nbsp;责任人身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..40_AM_A:{ArlpCertType:ArlpCertType}" type='text'  name='arlpCertNum' id='ArlpCertNum' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;还款责任人类型：</td><td class='queryContent'><input class='easyui-combobox'  style="width: 145px;"   name='arlpType' id='ArlpType' /></td>
							<td class='queryTitle'>&nbsp;还款责任金额(正整数)：</td><td class='queryContent'><input class='easyui-number' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='arlpAmt' id='ArlpAmt' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;联保标志：</td><td class='queryContent'><input class='easyui-combobox'  style="width: 145px;"  name='wartySign' id='WartySign' /></td>
							<td class='queryTitle'>&nbsp;保证合同编号：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='maxGuarMcc' id='MaxGuarMcc' /></td>
						</tr>
						<tr>
<!-- 							<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformGseRltRepInfSgmt('sub');">确定</a></td>
								<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformGseRltRepInfSgmt('updSub');">提交并生成更正报文</a></td> -->
							<td><input type='hidden'  name='rltRepymtInfSgmtSeqNo' id='RltRepymtInfSgmtSeqNo' /></td>
							<td><input type='hidden' name='rltRepymtInfSeqNo' id='RltRepymtInfSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
						<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformGseRltRepInfSgmt('sub','gseId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformGseRltRepInfSgmt('updSub','gseId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d4">
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="GuarMotCltCtrInfSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-GuaMotCltCtrSgmtmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 450px; padding: 5px;">
				<form id='dialog-GuaMotCltCtrSgmtmodifyform' method="POST" >
					<table style="width: 100%;">
						<tr>
							<td class='queryTitle'>&nbsp;抵（质）押合同标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='ccc' id='Ccc' /></td>

	
						</tr>
						
						<tr>
<!-- 							<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformGuaMotCltCtrSgmt();">确定</a></td>
							<td><input type='hidden'  name='guarmotgaCltalCtrctInfSgmtSeqNo' id='GuarMotgaCltalCtrctInfSgmtSeqNo' /></td> -->
							<td><input type='hidden' name='cccInfSeqNo' id='CccInfSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
						<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformGuaMotCltCtrSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformGuaMotCltCtrSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
				
			
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d5">
				<form id="formGuarAcctCredSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="credId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;授信协议标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN..60_AM" type='text'  name='mcc' id='Mcc' /></td>
							
						</tr>					
						<tr style="text-align: center;">
					<!-- 		<td class='queryContent'><input type="button" value="提交" onclick="submitformGuarAcctCredSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformGuarAcctCredSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden' name='guarAcctCredSgmtSeqNo' id='guarAcctCredSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformGuarAcctCredSgmt('sub','credId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformGuarAcctCredSgmt('updSub','credId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
				
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d6">
				<form id="formGuaRltRepInfSgmt" method="post">
				
					<table class="queryTable" style="width: 100%" id="guaId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;账户状态：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'  name='acctStatus' id='AcctStatus' /></td>
							<td class='queryTitle'>&nbsp;在保余额(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='loanAmt' id='LoanAmt' /></td>
						
						</tr>
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;余额变化日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  type='text' checkId='Date_AM'  name='repayPrd' id='RepayPrd' /></td>
						<td class='queryTitle'>&nbsp;五级分类：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='fiveCate' id='FiveCate' /></td>
						
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;五级分类认定日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM' type='text'  name='fiveCateAdjDate' id='FiveCateAdjDate' /></td>
						<td class='queryTitle'>&nbsp;风险敞口(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AO" data-options="min:0" type='text'  name='riEx' id='RiEx' /></td>
						
						</tr>
							<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;代偿（垫款）标志：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='compAdvFlag' id='CompAdvFlag' /></td>
							<td class='queryTitle'>&nbsp;账户关闭日期：</td><td class='queryContent'><input class="easyui-datebox"  style="width: 145px;" type='text'  name='closeDate' id='CloseDate' /></td>
						</tr>
						
							
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformGuaRltRepInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformGuaRltRepInfSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden' name='guarRltRepymtInfSgmtSeqNo' id='GuarRltRepymtInfSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
									style="width: 100px" onclick="byParagraphDelete('d6','GuaRltRepInfSgmt')">按段删除</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformGuaRltRepInfSgmt('sub','guaId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformGuaRltRepInfSgmt('updSub','guaId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			
				
			
		</div>
		
		
	</div>
</body>
</html>