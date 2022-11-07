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
<script type="text/javascript" src="${ctx}/js/ent/modify/EnBasInfDetial.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/EnLinkage.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<title>企业基本信息记录修改</title>
</head>
<body style="visibility: hidden;">
<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width:200px"
			data-options="region: 'west', border: false">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"  onclick="showDetial('d2');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"  onclick="showDetial('d1');"><span>基本概况段</span></a></li>

<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d3');"><span>其他标识段</span></a></li>-->				
                    <li><a href="javascript:void(0)"  onclick="showDetial('d4');"><span>主要组成人员段</span></a></li>
<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d5');"><span>注册资本及主要出资人段</span></a></li>
<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d6');"><span>实际控制人段</span></a></li>-->
<!-- 				<li><a href="javascript:void(0)"  onclick="showDetial('d7');"><span>上级机构段</span></a></li>
 -->				<li><a href="javascript:void(0)"  onclick="showDetial('d8');"><span>联系方式段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout" data-options="region: 'center',border: true">
		<span id="msg" style="color:red"></span>
			<div class="easyui-panel" style="display: none;" fit="true"  id="d1"  >
				<form id="formFcsInfSgmt" method="post">
					<table class="queryTable" style="width: 100%;" id="fcsinfId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;国别代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;"  type='text' name='NATIONALITY' id='NATIONALITY' /></td>
							<td class='queryTitle'>&nbsp;登记地址：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' name='REGADD'       id='REGADD' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;登记地行政区划代码：</td><td class='queryContent'><input class='easyui-combobox' style="width: 145px;" checkId='Enum_AO'  type='text'   name='ADMDIVOFREG'     id='ADMDIVOFREG' /></td>
							<td class='queryTitle'>&nbsp;成立日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"    name='ESTABLISHDATE'    id='ESTABLISHDATE' /></td>
												
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;营业许可到期日：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;" checkId='Date_AM'     name='BIZENDDATE'  id='BIZENDDATE' /></td>
							<td class='queryTitle'>&nbsp;业务范围：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..400_AO" name='BIZRANGE'    id='BIZRANGE' /></td>
						
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;行业分类代码：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;" checkId='Enum_AM'     name='ECOINDUSCATE'  id='ECOINDUSCATE' /></td>
							<td class='queryTitle'>&nbsp;经济类型代码：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  checkId='Enum_AM'    name='ECOTYPE'    id='ECOTYPE' /></td>
						
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;企业规模：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  checkId='Enum_AM'   name='ENTSCALE'  id='ENTSCALE' /></td>
							<td class='queryTitle'>&nbsp;信息更新日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  checkId='Date_AM'   name='FCSINFOUPDATE'    id='FCSINFOUPDATE' /></td>
						
						</tr>

						<tr style="text-align: center;">
						<!-- 	<td class='queryContent'><input type="button" value="提交" onclick="submitformFcsInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformFcsInfSgmt('updSub');"/></td> -->
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subFcsinfId"
							style="width: 100px" onclick="submitformFcsInfSgmt('sub','fcsinfId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubFcsinfId"
							style="width: 120px" onclick="submitformFcsInfSgmt('updSub','fcsinfId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>		
			<div class="easyui-panel" style="display: none;" fit="true" id="d2">
				<form id="formEnBasInfBsSgmt" method="post">
					<table class="queryTable" style="width: 100%;" id="baseId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;企业名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..80_AM" type='text'     name='ENTNAME'    id='ENTNAME' /></td>
							<td class='queryTitle'>&nbsp;企业身份标识类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"    name='ENTCERTTYPE'     id='EntCertType' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;企业身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..40_AM_A:{EntCertType:EntCertType}"   name='ENTCERTNUM'      id='EntCertNum' /></td>
							<td class='queryTitle'>&nbsp;信息来源编码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="AN..20_AM"       name='INFSURCCODE' id='INFSURCCODE'/></td>
												
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;信息报告日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  checkId='Date_AM'      name='RPTDATE'  id='RPTDATE'/></td>
							<td class='queryTitle'>&nbsp;报告时点说明代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'        name='RPTDATECODE' id='RPTDATECODE'/></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;客户资料维护机构代码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="AN14_AM" type='text'   name='CIMOC' id='CIMOC'/></td>
							<td class='queryTitle'>&nbsp;客户资料类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;" checkId='Enum_AM'       name='CUSTOMERTYPE' id='CUSTOMERTYPE'/></td>
							
						</tr>

						<tr>
							<td class='queryTitle'>&nbsp;存续状态：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  checkId='Enum_AM'       name='ETPSTS'  id='ETPSTS'/></td>
							<td class='queryTitle'>&nbsp;组织机构类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  checkId='Enum_AM'     name='ORGTYPE'  id='ORGTYPE'/></td>

						</tr>
						<input class="easyui-textbox" type="hidden"    id="REPORTFLAG"  name='REPORTFLAG'  />
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformEnBasInfBsSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformEnBasInfBsSgmt('updSub');"/></td> -->
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subBaseId"
							style="width: 100px" onclick="submitformEnBasInfBsSgmt('sub','baseId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubBaseId"
							style="width: 120px" onclick="submitformEnBasInfBsSgmt('updSub','baseId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
			<div class="easyui-panel" style="display: none;" fit="true" id="d3">
					<div class="easyui-layout" 
						data-options="region: 'north', border: false">
						<table class="queryTable" width="100%">
							<tr>
								
								<td class="queryTitle">&nbsp;企业身份标识码：</td>

								<td class="queryContent"><input class="inputText" type="text"
									id="queryEntCertNum" /></td>
								<td class="queryBtnTd" >&nbsp;&nbsp; <a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery()">查询</a>
									&nbsp;&nbsp; 
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClear()">清空</a>
								</td>
							</tr>
							
						</table>
						
					</div>
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="idTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-idmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 150px; padding: 5px;">
				<form id='dialog-idmodifyform' method="POST" >
					<table style="width: 100%;">
						<tr>
							<td class='queryTitle'>&nbsp;企业身份标识类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  name='OthEntCertType' id='OthEntCertType' /></td>
							<td class='queryTitle'>&nbsp;企业身份标识号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='OthEntCertNum' id='OthEntCertNum' /></td>

	
						</tr>
						<tr>
							<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformidsInfSgmt('sub');">确定</a></td>
								<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformidsInfSgmt('updSub');">提交并生成更正报文</a></td>
							<td><input type='hidden'  name='IDSgmtSeqNo' id='IDSgmtSeqNo' /></td>
							<td><input type='hidden' name='IDSgmtListSeqNo' id='IDSgmtListSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
	
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformidsInfSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformidsInfSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
			<div class="easyui-panel" style="display: none;height: 100%;" fit="true" id="d4">
				<div class="easyui-layout" 
						data-options="region: 'north', border: false" style="height: 10%;">
						<table class="queryTable" width="100%">
							<tr>
								
								<td class="queryTitle">&nbsp;主要组成人员姓名：</td>

								<td class="queryContent"><input class="easyui-textbox" style="width: 145px;" type="text"
									id="queryMmbAlias" /></td>
								<td class="queryTitle">&nbsp;主要组成人员证件号码：</td>

								<td class="queryContent"><input class="easyui-textbox" style="width: 145px;" type="text"
									id="queryMmbIDNum" /></td>
								<td class="queryBtnTd" >&nbsp;&nbsp; <a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery()">查询</a>
									&nbsp;&nbsp; 
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClear()">清空</a>
								</td>
							</tr>
							
						</table>
						
					</div>
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 90%; width: 100%;">
						<table id="MnMmbInfTable" style="height: 90%; width: 100%;"></table>
					</div>
			</div>
			
			<div id="dialog-MnMmbmodify" class="easyui-dialog" title="修改"
				data-options="closed:true,modal:true"
				style="width: 600px; height: 200px; padding: 5px;">
				<form id='dialog-MnMmbmodifyform' method="POST" >
					<table style="width: 100%;" id="mnmId">
						<tr>
							<td class='queryTitle'>&nbsp;主要组成人员姓名：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..30_AM" type='text'   name='MmbAlias'     id='MmbAlias' /></td>
							<td class='queryTitle'>&nbsp;主要组成人员证件类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;" checkId='Enum_AM'  name='MmbIDType'      id='MmbIDType' /></td>

						</tr>
						<tr>							
							<td class='queryTitle'>&nbsp;主要组成人员证件号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text' checkId="ANC..20_AM_A:{MmbIDType:MmbIDType}"   name='MmbIDNum'    id='MmbIDNum' /></td>
							<td class='queryTitle'>&nbsp;主要组成人员职位：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;" checkId='Enum_AM'    name='MmbPstn'    id='MmbPstn' /></td>
						</tr>

						<tr>
						<!-- 	<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformMnMmbInfSgmt('sub');">确定</a></td>
								<td style="width: 55px;"><a href="javascript:void(0)"
								class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformMnMmbInfSgmt('updSub');">提交并生成更正报文</a></td> -->
							<td><input type='hidden'  name='MnMmbInfSgmtSeqNo' id='MnMmbInfSgmtSeqNo' /></td>
							<td><input type='hidden' name='MmbInfSeqNo' id='MmbInfSeqNo' /></td>
							<td></td>
						</tr>
	
	
					</table>
						<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subMnmId"
							style="width: 100px" onclick="submitformMnMmbInfSgmt('sub','mnmId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubMnmId"
							style="width: 120px" onclick="submitformMnMmbInfSgmt('updSub','mnmId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
				
			<div class="easyui-panel" style="display: none;" fit="true" id="d5">
				<div class="easyui-layout" 
						data-options="region: 'north', border: false">
						<table class="queryTable" width="100%">
							<tr>
								
								<td class="queryTitle">&nbsp;出资人姓名：</td>

								<td class="queryContent"><input class="easyui-textbox" style="width: 145px;" type="text"
									id="querySharHodName" /></td>
								<td class="queryTitle">&nbsp;出资人身份标识号码：</td>

								<td class="queryContent"><input class="easyui-textbox" style="width: 145px;" type="text"
									id="querySharHodIDNum" /></td>
								<td class="queryBtnTd" >&nbsp;&nbsp; <a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery()">查询</a>
									&nbsp;&nbsp; 
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClear()">清空</a>
								</td>
							</tr>
							
						</table>
						
					</div>
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="MnShaHodInfSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			<div id="dialog-MnShaHodInfSgmtmodify" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 150px; padding: 5px;">
					<form id='dialog-MnShaHodInfSgmtmodifyform' method="POST" >
						<table style="width: 100%;">
							<tr>
								<td class='queryTitle'>&nbsp;出资人类型：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'   name='SharHodType'     id='SharHodType' /></td>
								<td class='queryTitle'>&nbsp;出资人身份类别：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"   name='SharHodCertType'  id='SharHodCertType' /></td>

							</tr>
							<tr>							
								<td class='queryTitle'>&nbsp;出资人姓名/名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='SharHodName'      id='SharHodName' /></td>
								<td class='queryTitle'>&nbsp;出资人身份标识类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"    name='SharHodIDType'   id='SharHodIDType' /></td>
							</tr>
							<tr>
								<td class='queryTitle'>&nbsp;出资人身份标识号码：</td><td class='queryContent'><input class='easyui-textbox'  style="width: 145px;" type='text'   name='SharHodIDNum'     id='SharHodIDNum' /></td>
								<td class='queryTitle'>&nbsp;出资比例：</td><td class='queryContent'><input class='easyui-textbox' type='text' style="width: 145px;"  name='InvRatio'     id='InvRatio' /></td>
					
							</tr>
							<tr>
			<!-- 					<td style="width: 55px;"><a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformMnShaHodInfSgmt('sub');">确定</a></td>
									<td style="width: 55px;"><a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformMnShaHodInfSgmt('updSub');">提交并生成更正报文</a></td> -->
								<td><input type='hidden'  name='MnShaHodInfSgmtSeqNo' id='MnShaHodInfSgmtSeqNo' /></td>
								<td><input type='hidden' name='SharHodInfSeqNo' id='SharHodInfSeqNo' /></td>
								<td></td>
							</tr>
		
		
						</table>
							<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformMnShaHodInfSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformMnShaHodInfSgmt('updSub');">提交并生成更正报文</a>
					</div>
					</form>
				</div>	
			
				
			<div class="easyui-panel" style="width: 100%;height:100%;display: none;" fit="true"  id="d6">
				<div class="easyui-layout" 
						data-options="region: 'north', border: false">
						<table class="queryTable" width="100%">
							<tr>
								
								<td class="queryTitle">&nbsp;实际控制人姓名：</td>

								<td class="queryContent"><input class="easyui-textbox" style="width: 145px;" type="text"
									id="queryActuCotrlName" /></td>
								<td class="queryTitle">&nbsp;实际控制人证件号码：</td>

								<td class="queryContent"><input class="easyui-textbox" style="width: 145px;" type="text"
									id="queryActuCotrlIDNum" /></td>
								<td class="queryBtnTd" >&nbsp;&nbsp; <a
									href="javascript:void(0)" class="easyui-linkbutton"
									iconCls="icon-search" onclick="doQuery()">查询</a>
									&nbsp;&nbsp; 
									<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="doClear()">清空</a>
								</td>
							</tr>
							
						</table>
						
					</div>
					
					<div class="easyui-layout"
						data-options="region: 'center', border: false" style="height: 95%; width: 100%;">
						<table id="ActuCotrlInfSgmtTable" style="height: 100%; width: 100%;"></table>
					</div>
			</div>
			<div id="dialog-ActuCotrlInfSgmtmodify" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 150px; padding: 5px;">
					<form id='dialog-ActuCotrlInfSgmtmodifyform' method="POST" >
						<table style="width: 100%;">
							<tr>
								<td class='queryTitle'>&nbsp;实际控制人身份类别：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"   name='ActuCotrlCertType'   id='ActuCotrlCertType' /></td>
								<td class='queryTitle'>&nbsp;实际控制人姓名/名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='ActuCotrlName'      id='ActuCotrlName' /></td>

							</tr>
							<tr>							
								<td class='queryTitle'>&nbsp;实际控制人证件类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;" name='ActuCotrlIDType'  id='ActuCotrlIDType' /></td>
								<td class='queryTitle'>&nbsp;实际控制人证件号码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'     name='ActuCotrlIDNum'    id='ActuCotrlIDNum' /></td>
							</tr>
							
							<tr>
	<!-- 							<td style="width: 55px;"><a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformActuCotrlInfSgmt('sub');">确定</a></td>
									<td style="width: 55px;"><a href="javascript:void(0)"
									class="easyui-linkbutton" iconCls="icon-save"  onclick="submitformActuCotrlInfSgmt('updSub');">提交并生成更正报文</a></td> -->
								<td><input type='hidden'  name='ActuCotrlInfSgmtSeqNo' id='ActuCotrlInfSgmtSeqNo' /></td>
								<td><input type='hidden' name='ActuCotrlInfSeqNo' id='ActuCotrlInfSeqNo' /></td>
								<td></td>
							</tr>
		
		
						</table>
							<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformActuCotrlInfSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformActuCotrlInfSgmt('updSub');">提交并生成更正报文</a>
					</div>
					</form>
				</div>	
				 
			<div class="easyui-panel" style="display: none;" fit="true"  id="d7">
				<form id="formSpvAthInfSgmt" method="post">
					<table class="queryTable" style="width: 100%;">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;上级机构类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  name='SUPORGTYPE' id='SupOrgType' /></td>
							<td class='queryTitle'>&nbsp;上级机构名称：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='SUPORGNAME' id='SupOrgName' /></td>
							
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;上级机构身份标识类型：</td><td class='queryContent'><input class="easyui-combobox" style="width: 145px;"  name='SUPORGCERTTYPE' id='SupOrgCertType' /></td>
							<td class='queryTitle'>&nbsp;上级机构身份标识码：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" type='text'  name='SUPORGCERTNUM' id='SupOrgCertNum' /></td>
						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;信息更新日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  name='SUPORGINFOUPDATE' id='SupOrgInfoUpDate' /></td>
							<td class='queryTitle'></td><td class='queryContent'></td>
												
						</tr>
						
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformSpvAthInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformSpvAthInfSgmt('updSub');"/> </td> -->
							<td class='queryTitle'><input type='hidden'  name='SPVSGATHRTYINFSGMTSEQNO' id='SPVSGATHRTYINFSGMTSEQNO' /></td><td class='queryContent'></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 100px" onclick="submitformSpvAthInfSgmt('sub');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							style="width: 120px" onclick="submitformSpvAthInfSgmt('updSub');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
			<div class="easyui-panel" style="display: none;" fit="true" id="d8">
				<form id="formCotaInfSgmt" method="post">
					<table class="queryTable" style="width: 100%;" id="cotainfId">
						<tr style="height:30px">							
							<td class='queryTitle'>&nbsp;联系地址行政区划代码：</td><td class='queryContent'><input  class="easyui-combobox" style="width: 145px;" checkId='Enum_AO'   name='CONADDDISTRICTCODE' id='ConAddDistrictCode' /></td>
							<td class='queryTitle'>&nbsp;联系地址：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..100_AO" type='text'  name='CONADD' id='CONADD' /></td>

						</tr>
						<tr>
							<td class='queryTitle'>&nbsp;联系电话：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;" checkId="ANC..25_AO" type='text'  name='CONPHONE' id='CONPHONE' /></td>
							<td class='queryTitle'>&nbsp;财务部门联系电话：</td><td class='queryContent'><input class='easyui-textbox' style="width: 145px;"  checkId="ANC..25_AO" type='text'  name='FINCONPHONE' id='FINCONPHONE' /></td>
					
						</tr>
						<tr>						
							<td class='queryTitle'>&nbsp;信息更新日期：</td><td class='queryContent'><input class="easyui-datebox" style="width: 145px;"  checkId='Date_AM' name='COTAINFOUPDATE' id='COTAINFOUPDATE' /></td>
							<td class='queryTitle'></td><td class='queryContent'></td>
							
						</tr>
					
						<tr style="text-align: center;">
<!-- 							<td class='queryContent'><input type="button" value="提交" onclick="submitformCotaInfSgmt('sub');"/> </td>
							<td class='queryContent'><input type="button" value="提交并生成更正报文" onclick="submitformCotaInfSgmt('updSub');"/> </td> -->
							<td class='queryTitle'><input type='hidden'  name='COTAINFSGMTSEQNO' id='COTAINFSGMTSEQNO' /></td><td class='queryContent'></td>
						
						</tr>
					</table>
					
					<div style="margin-top: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subCotainfId"
							style="width: 100px" onclick="submitformCotaInfSgmt('sub','cotainfId');">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubCotainfId"
							style="width: 120px" onclick="submitformCotaInfSgmt('updSub','cotainfId');">提交并生成更正报文</a>
					</div>
				</form>
			</div>	
		</div>
		
		
	</div>
</body>
</html>