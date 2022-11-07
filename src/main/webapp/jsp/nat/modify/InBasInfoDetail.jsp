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
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/InLinkage.js"></script>
<script type="text/javascript"
	src="${ctx}/js/nat/modify/InBasInfoDetail.js"></script>
<title>个人基本信息修改详细</title>
<style>
p {
	display: block;
}
</style>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width: 200px"
			data-options="region: 'west', border: true">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"
					onclick="showDetial('fcsInfSgmt');"><span>基本概况段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('bsSgmt');"><span>基础段</span></a></li>
				<!-- <li><a href="javascript:void(0)"
					onclick="showDetial('idSgmt');"><span>其他标识段</span></a></li> -->
				<li><a href="javascript:void(0)"
					onclick="showDetial('spsInfSgmt');"><span>婚姻信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('eduInfSgmt');"><span>教育信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('octpnInfSgmt');"><span>职业信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('redncInfSgmt');"><span>居住地址段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('mlgInfSgmt');"><span>通讯地址段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('incInfSgmt');"><span>收入信息段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout"
			data-options="region: 'center',border: true">
			<span id="msg" style="color:red"></span>
			<!-- 基本概况段 -->
			<div class="easyui-layout" fit="true" id="fcsInfSgmt"
				style="display: none;">
				<form id="fcsInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="fcsInfSgmtKey" name="fcsInfSgmtSeqNo">

					<table style="width: 100%;" id="table1">
						<tr height="40px">
							<td style="width: 13%;">&nbsp;性别:</td>
							<td style="width: 13%"><input checkId="Enum_AM"  id="SEX" name="sex"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;出生日期:</td>
							<td style="width: 12%"><input  name="dob" type="text" id="DOB"
								class="easyui-datebox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;国籍:</td>
							<td style="width: 13%"><input checkId="Enum_AM" id="NATION" name="nation"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;户籍地址:</td>
							<td style="width: 16%"><input checkId="ANC..100_AO" name="houseAdd" type="text" id="HOUSEADD"
								class="easyui-textbox" data-options="width:200,multiline:true,height:40"  /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;户籍所在地行政区划:</td>
							<td><input id="HHDIST" name="hhDist" type="text"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;手机号码:</td>
							<td><input checkId="N11_AO"  name="cellPhone"  type="text" id="CELLPHONE"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;电子邮箱:</td>
							<td><input checkId="ANC..60_AO_A:{Email:EMAIL}"  name="email" type="text" class="easyui-textbox" id="EMAIL"
								data-options="width:150" style="width: 150px" /></td>
							<td>&nbsp;信息更新日期:</td>
							<td><input checkId="Date_AM" name="fcsInfoUpDate" type="text" id="FCSINFOUPDATE"
								class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
						</tr>
					</table>

					<div style="margin-top: 100px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton"  id="subTable1"
							style="width: 100px" onclick="submitformFcsInfSgmt('fcsInfSgmt','sub','table1')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"  id="updSubTable1"
							style="width: 130px" onclick="submitformFcsInfSgmt('fcsInfSgmt','updSub','table1')">提交并生成更正报文</a>
					</div>
				</form>
			</div>

			<!--个人基本信息基础  -->
			<div class="easyui-layout" fit="true" id="bsSgmt">
				<form id="bsSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="bsSgmtKey" name="bsSgmtSeqNo" />
					<!-- 信息记录类型-->
					<input type="hidden" id="infRecTypeKey" name="infRecType" />
					<!-- 是否已报送 -->
					<input type="hidden" id="reportflag" name="reportflag" >
					<table style="width: 100%;" id="table2">
						<tr height="40px">
						    <td style="width: 12%;">&nbsp;客户号:</td>
							<td style="width: 12%"><input  name="sourceCustid" type="text" id="SOURCECUSTID"
								class="easyui-textbox" data-options="" style="width: 130px" readonly='true' /></td>
							<td style="width: 12%;">&nbsp;姓名:</td>
							<td style="width: 12%"><input checkId="ANC..30_AM" name="name" type="text" id="NAME"
								class="easyui-textbox" data-options="" style="width: 130px;border-left:10px solid red" /></td>
							<td style="width: 12%">&nbsp;证件类型:</td>
							<td style="width: 12%"><input checkId="Enum_AM" id="IDTYPE" name="idtype"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;证件号码:</td>
							<td style="width: 12%"><input checkId="ANC..20_AM_A:{IDType:IDTYPE}" name="idnum" type="text" id="IDNUM"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							
						</tr>
						<tr height="40px">
							<td>&nbsp;信息报告日期:</td>
							<td><input checkId="Date_AM" name="rptDate" type="text" class="easyui-datebox" id="RPTDATE"
								data-options="editable:false" style="width: 130px" /></td>
							<td>&nbsp;报告时点说明代码:</td>
							<td><input checkId="Enum_AM" id="RPTDATECODE" name="rptDateCode" type="text"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;客户资料维护机构代码:</td>
							<td><input checkId="AN14_AM" name="cimoc" type="text" class="easyui-textbox" id="CIMOC"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;客户资料类型:</td>
							<td><input checkId="Enum_AM" id="CUSTOMERTYPE" name="customerType" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
						<td style="width: 12%">&nbsp;信息来源编码:</td>
							<td style="width: 13%"><input checkId=AN..20_AM"" name="infSurcCode" type="text" id="INFSURCCODE"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
						</tr>
					</table>







					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable2"
							style="width: 100px" onclick="submitformFcsInfSgmt('bsSgmt','sub','table2')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable2"
							style="width: 130px" onclick="submitformFcsInfSgmt('bsSgmt','updSub','table2')">提交并生成更正报文</a>
						
					</div>
				</form>
			</div>
			<!-- 个人基本信息其他标示 -->
			<div class="easyui-layout" fit="true" id="idSgmt">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px">
					<div style="border-bottom: 1px solid rgb(149, 184, 231)">
						<div style="padding: 2px; display: inline-block;">&nbsp;姓名:</div>
						<div style="padding: 2px; display: inline-block;">
							<input  id="IDSGMTNAME" class="easyui-textbox" name="idSgmtName"
								style="width: 100px;" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;证件号码:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input  id="IDSGMTIDNUM" class="easyui-textbox" name="idSgmtIdNum"
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
					<table id="idSgmtTable" style="height: 100%; width: 100%;"></table>
				</div>

				<!-- 修改项弹出框 -->
				<div id="dialog-idSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 150px; padding: 5px;">
					<form id="idSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="idSgmtKey" name="idsgmtSeqNo">
						<!-- 自身主键 -->
						<input type="hidden" name="idsgmtListSeqNo">


						<div style="padding: 2px; display: inline-block;">&nbsp;姓名:</div>
						<div style="padding: 2px; display: inline-block;">
							<input checkId="ANC..30_AM" name="alias" type="text" class="easyui-textbox" id="ALIAS"
								data-options="" style="width: 130px" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;证件类型:</div>
						<div style="padding: 2px; display: inline-block;">
							<input checkId="Enum_AM"  id="OTHIDTYPE" name="othIDType" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;证件号码:</div>
						<div style="padding: 2px; display: inline-block;">
							<input checkId="ANC..20_AM" name="othIDNum" type="text" class="easyui-textbox" id="OTHIDNUM"
								data-options="" style="width: 130px" />
						</div>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 100px" onclick="submitformFcsInfSgmt('idSgmt','sub','idSgmtForm')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 100px" onclick="submitformFcsInfSgmt('idSgmt','updSub','idSgmtForm')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>
			<!-- 婚姻信息段 -->
			<div class="easyui-layout" fit="true" id="spsInfSgmt">
				<form id="spsInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="spsInfSgmtKey" name="spsInfSgmtSeqNo" />


					<table style="width: 100%;" id="table3">
						<tr height="40px">
							<td style="width: 12%;">&nbsp;婚姻状况:</td>
							<td style="width: 12%"><input  checkId="Enum_AM"
								name="mariStatus" type="text" class="easyui-combobox" id="MARISTATUS"
								data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;配偶姓名:</td>
							<td style="width: 12%"><input checkId="ANC..30_SO_S:{mariStatus:MARISTATUS}" name="spoName" type="text" id="SPONAME"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;配偶证件类型:</td>
							<td style="width: 12%"><input checkId="Enum_SC_S:{mariStatus:MARISTATUS}" id="SPOIDTYPE"
								name="spoIDType" type="text" class="easyui-combobox"
								data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;配偶证件号码:</td>
							<td style="width: 13%"><input checkId="ANC..20_SC_S:{mariStatus:MARISTATUS},A:{IDType:SPOIDTYPE;sameStatus:SPOIDTYPE}" name="spoIDNum" type="text" id="SPOIDNUM"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;配偶联系电话:</td>
							<td><input checkId="ANC..25_SO_S:{mariStatus:MARISTATUS}" name="spoTel" type="text" class="easyui-textbox" id="SPOTEL"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;配偶工作单位:</td>
							<td><input checkId="ANC..80_SO_S:{mariStatus:MARISTATUS}" name="spsCmpyNm" type="text" id="SPSCMPYNM"
								class="easyui-textbox" data-options="" style="width: 170px" /></td>
							<td>&nbsp;信息更新日期:</td>
							<td><input checkId="Date_AM" name="spsInfoUpDate" type="text" id="SPSINFOUPDATE"
								class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable3"
							style="width: 100px" onclick="submitformFcsInfSgmt('spsInfSgmt','sub','table3')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable3"
							style="width: 130px" onclick="submitformFcsInfSgmt('spsInfSgmt','updSub','table3')">提交并生成更正报文</a>
					</div>
				</form>

			</div>
			<!-- 教育信息段 -->
			<div class="easyui-layout" fit="true" id="eduInfSgmt">

				<form id="eduInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="eduInfSgmtKey" name="eduInfSgmtSeqNo" />

					<table style="width: 100%;" id="table4">
						<tr height="40px">
							<td style="width: 12%;">&nbsp;学历:</td>
							<td style="width: 12%"><input checkId="Enum_AM" id="EDULEVEL" name="eduLevel"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;学位:</td>
							<td style="width: 12%"><input checkId="Enum_AM_A:{eduLevel:EDULEVEL}" id="ACADEGREE"
								name="acaDegree" type="text" class="easyui-combobox"
								data-options="" style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;信息更新日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="eduInfoUpDate" id="EDUINFOUPDATE"
								type="text" class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
							<td style="width: 12%"></td>
							<td style="width: 13%"></td>
						</tr>

					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable4"
							style="width: 100px" onclick="submitformFcsInfSgmt('eduInfSgmt','sub','table4')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable4"
							style="width: 130px" onclick="submitformFcsInfSgmt('eduInfSgmt','updSub','table4')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 职业信息段 -->
			<div class="easyui-layout" fit="true" id="octpnInfSgmt">
				<form id="octpnInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="octpnInfSgmtKey" name="octpnInfSgmtSeqNo" />


					<table style="width: 100%;" id='table5'>
						<tr height="40px">
							<td style="width: 9%;">&nbsp;就业状况:</td>
							<td style="width: 14%"><input checkId="Enum_AM" id="EMPSTATUS"
								name="empStatus" type="text" class="easyui-combobox"
								data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;单位名称:</td>
							<td style="width: 12%"><input checkId="ANC..80_SM_S:{empStatus:EMPSTATUS}" name="cpnName" type="text" id="CPNNAME"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;单位性质:</td>
							<td style="width: 12%"><input checkId="Enum_SM_S:{empStatus:EMPSTATUS}" id="CPNTYPE" name="cpnType"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;单位所属行业:</td>
							<td style="width: 13%"><input checkId="Enum_SM_S:{empStatus:EMPSTATUS}" id="INDUSTRY" name="industry"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;单位详细地址:</td>
							<td><input checkId="ANC..100_SO_S:{empStatus:EMPSTATUS}" name="cpnAddr" type="text" class="easyui-textbox" id="CPNADDR"
								data-options="width:180,multiline:true,height:40"  /></td>
							<td>&nbsp;单位所在地邮编:</td>
							<td><input checkId="N6_SO_S:{empStatus:EMPSTATUS}" name="cpnPc" type="text" class="easyui-textbox" id="CPNPC"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;单位所在地行政区划:</td>
							<td><input checkId="Enum_SO_S:{empStatus:EMPSTATUS}" id="CPNDIST" name="cpnDist" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;单位电话:</td>
							<td><input checkId="ANC..25_SO_S:{empStatus:EMPSTATUS}" name="cpnTEL" type="text" class="easyui-textbox" id="CPNTEL"
								data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;职业:</td>
							<td><input checkId="Enum_SM_S:{empStatus:EMPSTATUS}" id="OCCUPATION" name="occupation" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;职务:</td>
							<td><input checkId="Enum_SM_S:{empStatus:EMPSTATUS}" id="TITLE" name="title" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;职称:</td>
							<td><input checkId="Enum_SM_S:{empStatus:EMPSTATUS}" id="TECHTITLE" name="techTitle" type="text"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;本单位工作起始年份:</td>
							<td><input checkId="Year_SO_S:{empStatus:EMPSTATUS}" name="workStartDate" type="text" id="WORKSTARTDATE"
								class="easyui-combobox"  style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;信息更新日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="octpnInfoUpDate" id="OCTPNINFOUPDATE"
								type="text" class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable5"
							style="width: 100px"
							onclick="submitformFcsInfSgmt('octpnInfSgmt','sub','table5')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable5"
							style="width: 130px"
							onclick="submitformFcsInfSgmt('octpnInfSgmt','updSub','table5')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 居住地址段 -->
			<div class="easyui-layout" fit="true" id="redncInfSgmt">
				<form id="redncInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="redncInfSgmtKey" name="redncInfSgmtSeqNo" />

					<table style="width: 100%;" id="table6">
						<tr height="40px">
							<td style="width: 12%;">&nbsp;居住状况:</td>
							<td style="width: 12%"><input checkId="Enum_AM" id="RESISTATUS"
								name="resiStatus" type="text" class="easyui-combobox"
								data-options="" style="width: 130px" /></td>
							<td style="width: 9%">&nbsp;居住地详细地址:</td>
							<td style="width: 16%"><input checkId="ANC..100_AO" name="resiAddr" type="text" id="RESIADDR"
								class="easyui-textbox" data-options="width:180,multiline:true,height:40"  /></td>
							<td style="width: 14%">&nbsp;居住地邮编:</td>
							<td style="width: 12%"><input checkId="N6_AO" name="resiPc" type="text" id="RESIPC"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;居住地行政区划:</td>
							<td style="width: 13%"><input id="RESIDIST" name="resiDist"
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;住宅电话:</td>
							<td><input checkId="ANC..25_AO" name="homeTel" type="text" class="easyui-textbox" id="HOMETEL"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;信息更新日期:</td>
							<td><input checkId="Date_AM" name="resiInfoUpDate" type="text" id="RESIINFOUPDATE"
								class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable6"
							style="width: 100px"
							onclick="submitformFcsInfSgmt('redncInfSgmt','sub','table6')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable6"
							style="width: 130px"
							onclick="submitformFcsInfSgmt('redncInfSgmt','updSub','table6')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 通讯地址段 -->
			<div class="easyui-layout" fit="true" id="mlgInfSgmt">
				<form id="mlgInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="mlgInfSgmtKey" name="mlgInfSgmtSeqNo" />

					<table style="width: 100%;" id='table7'>
						<tr height="40px">
							<td style="width: 9%;">&nbsp;通讯地址:</td>
							<td style="width: 16%"><input checkId="ANC..100_AM" name="mailAddr" type="text" id="MAILADDR"
								class="easyui-textbox" data-options="width:180,multiline:true,height:40"  /></td>
							<td style="width: 12%">&nbsp;通讯地邮编:</td>
							<td style="width: 12%"><input checkId="N6_AM" name="mailPc" type="text" id="MAILPC"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;通讯地行政区划:</td>
							<td style="width: 12%"><input id="MAILDIST" name="mailDist" 
								type="text" class="easyui-combobox" data-options=""
								style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;信息更新日期:</td>
							<td style="width: 13%"><input checkId="Date_AM" name="mlgInfoUpDate" id="MLGINFOUPDATE"
								type="text" class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
						</tr>

					</table>

					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable7"
							style="width: 100px" onclick="submitformFcsInfSgmt('mlgInfSgmt','sub','table7')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable7"
							style="width: 130px" onclick="submitformFcsInfSgmt('mlgInfSgmt','updSub','table7')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
			<!-- 收入信息段 -->
			<div class="easyui-layout" fit="true" id="incInfSgmt">
				<form id="incInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="incInfSgmtKey" name="incInfSgmtSeqNo" />

					<table style="width: 100%;" id='table8'>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;自报年收入:</td>
							<td style="width: 12%"><input checkId="uInt..15_AM" name="annlInc" type="text" id="ANNLINC"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;纳税年收入:</td>
							<td style="width: 12%"><input checkId="uInt..15_AM" name="taxIncome" type="text" id="TAXINCOME"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 14%">&nbsp;信息更新日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="incInfoUpDate" id="INCINFOUPDATE"
								type="text" class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
							<td style="width: 12%"></td>
							<td style="width: 13%"></td>
						</tr>

					</table>

					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable8"
							style="width: 100px" onclick="submitformFcsInfSgmt('incInfSgmt','sub','table8')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable8"
							style="width: 130px" onclick="submitformFcsInfSgmt('incInfSgmt','updSub','table8')">提交并生成更正报文</a>
					</div>
				</form>
			</div>
		</div>


	</div>
</body>
</html>