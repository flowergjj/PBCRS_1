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
<script type="text/javascript" src="${ctx}/js/ent/modify/EnCtrctInfDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业授信信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基础段</span></a></li>
<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>共同受信人信息段</span></a></li>
 -->				<li><a href="javascript:void(0)"  onclick="showDetial('d3');"><span>额度信息段</span></a></li>
			
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d1" >
				<form id="formEnCtrctBsSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="ctrctId">
						<tr style="height:30px">
							<td class='queryTitle'>&nbsp;授信协议标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;"  type='text'  name='codes' id='codes'  readonly="readonly"/></td>						
							<td class='queryTitle'>&nbsp;征信授信协议标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN..60_AM" type='text'  name='contractCode' id='ContractCode' /></td>
							<td class='queryTitle'></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;信息报告日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  type='text' checkId='Date_AM'  name='rptDate' id='RptDate' /></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='rptDateCode' id='RptDateCode' /></td>
							
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;受信人名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..80_AM"  name='name' id='Name' /></td>
							<td class='queryTitle'>&nbsp;受信人身份标识类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'    name='idtype' id='IDType' /></td>
							
						</tr>
						<tr>						
												<td class='queryTitle'>&nbsp;受信人身份标识证件号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..40_AM_A:{IDType:IDType}" type='text'  name='idnum' id='IDNum' /></td>
							<td class='queryTitle'>&nbsp;业务管理机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN14_AM" type='text'  name='mngmtOrgCode' id='MngmtOrgCode' /></td>
		
						</tr>
				
						<tr>
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='infRecType' id='InfRecType' /></td>
							<td class='queryTitle'></td><td class='queryContent'><input type='hidden'  name='ctrctBsSgmtSeqNo' id='CtrctBsSgmtSeqNo' /></td>
					
						</tr>
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitEnCtrctBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitEnCtrctBsSgmt('updSub');"/></td> -->
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitEnCtrctBsSgmt('sub','ctrctId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitEnCtrctBsSgmt('updSub','ctrctId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d2">
				
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="EnCtrCerRelSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>	
			
			<div id="dialog-EnCtrCerRelSgmtmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 450px; padding: 5px;">
				<form id='dialog-EnCtrCerRelSgmtmodifyform' method="POST" >
					<table style="width: 100%;">
						<tr>
							<td class='queryTitle'>&nbsp;共同受信人身份类别：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;"  name='brerType' id='BrerType' /></td>
							<td class='queryTitle'>&nbsp;共同受信人名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='certRelName' id='CertRelName' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;共同受信人身份标识类型：</td><td class='queryContent'><input class='easyui-combobox'  style="width: 145px;" name='certRelIDType' id='CertRelIDType' /></td>
							<td class='queryTitle'>&nbsp;共同受信人身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='certRelIDNum' id='CertRelIDNum' /></td>

						</tr>
						
						<tr>
<!-- 							<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformEnCtrCerRelSgmt('sub');">确定</a></td>
								<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformEnCtrCerRelSgmt('updSub');">提交并生成更正报文</a></td> -->
							<td><input type='hidden'  name='ctrctCertRelSgmtSeqNo' id='CtrctCertRelSgmtSeqNo' /></td>
							<td><input type='hidden' name='ctrctCertRelSeqNo' id='CtrctCertRelSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
						<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformEnCtrCerRelSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformEnCtrCerRelSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<div class="easyui-panel" style="width: 100%;height:100%;display:none;"  id="d3">
				<form id="formEnCreditLimSgmt" method="post">
					<table class="queryTable" style="width: 100%" id="credtId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;授信额度类型：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='creditLimType' id='CreditLimType' /></td>
							<td class='queryTitle'>&nbsp;额度循环标志：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='limLoopFlg' id='LimLoopFlg' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;授信额度(正整数)：</td><td class='queryContent'><input class='easyui-numberbox' style="width: 145px;" checkId="uInt..15_AM" data-options="min:0" type='text'  name='creditLim' id='CreditLim' /></td>
							<td class='queryTitle'>&nbsp;币种：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='cy' id='Cy' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;额度生效日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  checkId='Date_AM' type='text'  name='conEffDate' id='ConEffDate' /></td>
							<td class='queryTitle'>&nbsp;额度到期日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM' type='text'  name='conExpDate' id='ConExpDate' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;额度状态：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AM'   name='conStatus' id='ConStatus' /></td>
							<td class='queryTitle'>&nbsp;授信限额编号：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='creditRestCode' id='CreditRestCode' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;授信限额：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='creditRest' id='CreditRest' /></td>
							<td class='queryTitle'></td><td class='queryContent'></td>
						</tr>
						
						<tr style="text-align: center;">
<!-- 							</td><td class='queryContent'><input type="button" value="提交" onclick="submitformEnCreditLimSgmt('sub');"/> </td>
							</td><td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnCreditLimSgmt('updSub');"/> </td> -->
							<td class='queryContent'><input type='hidden' name='creditLimSgmtSeqNo' id='CreditLimSgmtSeqNo' /></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
					  <a href="javascript:void(0)" id="del"  class="easyui-linkbutton"
							style="width: 100px"   onclick="deleteform()">按段删除</a>
						<a href="javascript:void(0)" id="sub" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformEnCreditLimSgmt('sub','credtId');">提交</a>
						<a href="javascript:void(0)" id="upd" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformEnCreditLimSgmt('updSub','credtId');">提交并生成更正报文</a>
					</div>
				</form>	
					
				
			</div>
			
		
				
			
			
		</div>
		
		
	</div>
</body>
</html>