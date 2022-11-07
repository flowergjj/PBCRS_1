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
<script type="text/javascript"
	src="${ctx}/js/nat/modify/MotCltCtrInfDetail.js"></script>
<script type="text/javascript" src="${ctx}/js/FeedBack.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/common.js"></script>
<script type="text/javascript" src="${ctx}/js/checkData/InLinkage.js"></script>
<title>抵质押物修改详细</title>

</head>
<body>
	<div class="easyui-layout" fit="true">
		<div class="easyui-layout" style="width: 200px"
			data-options="region: 'west', border: true">
			<ul class='easyui-tree tree' data-options='animate:true'>
				<li><a href="javascript:void(0)"
					onclick="showDetial('motCltCtrBsSgmt');"><span>基础段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('motCltBsInfSgmt');"><span>基本信息段</span></a></li>
				<!-- <li><a href="javascript:void(0)"
					onclick="showDetial('comRecInfSgmt');"><span>其他债务人信息段</span></a></li> -->
				<li><a href="javascript:void(0)"
					onclick="showDetial('motgaProptInfSgmt');"><span>抵押物信息段</span></a></li>
				<li><a href="javascript:void(0)"
					onclick="showDetial('cltalInfSgmt');"><span>质物信息段</span></a></li>
			</ul>
		</div>
		<div class="easyui-layout"
			data-options="region: 'center',border: true">
			<!-- 基本信息段 -->
			<div class="easyui-layout" fit="true" id="motCltBsInfSgmt">
				<form id="motCltBsInfSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="motCltBsInfSgmtKey"
						name="motgaCltalBsInfSgmtSeqNo">


					<table style="width: 100%;" id='table1'>
						<tr height="40px">
							<td style="width: 13%;">&nbsp;合同类型:</td>
							<td style="width: 13%"><input checkId="Enum_AM" name="guarType" type="text" id="GUARTYPE"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;担保金额:</td>
							<td style="width: 12%"><input checkId='uInt..15_AM' name="ccAmt" type="text" id="CCAMT"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;币种:</td>
							<td style="width: 13%"><input checkId="Enum_AM" name="cy" type="text" id="CY"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;抵（质）押合同生效日期:</td>
							<td style="width: 13%"><input checkId="Enum_AM" name="ccValDate" type="text" id="CCVALDATE"
								class="easyui-datebox" data-options="editable:false"
								style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;抵（质）押合同到期日期:</td>
							<td><input checkId="Enum_AM" name="ccExpDate" type="text" id="CCEXPDATE"
								class="easyui-datebox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;最高额担保标志:</td>
							<td><input checkId="Enum_AM" name="maxGuar" type="text" id="MAXGUAR"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;抵质押合同状态:</td>
							<td><input checkId="Enum_AM" name="ccStatus" type="text" id="CCSTATUS"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td></td>
							<td></td>
						</tr>

					</table>

					<div style="margin-top: 100px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable1"
							style="width: 100px" onclick="submitform('motCltBsInfSgmt','sub','table1')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable1"
							style="width: 130px" onclick="submitform('motCltBsInfSgmt','updSub','table1')">提交并生成更正报文</a>
					</div>
				</form>
			</div>

			<!--基础段  -->
			<div class="easyui-layout" fit="true" id="motCltCtrBsSgmt">
				<form id="motCltCtrBsSgmtForm" method="POST"
					style="height: 100%; width: 100%">
					<!-- 主键，隐藏，做数据更新使用 -->
					<input type="hidden" id="motCltCtrBsSgmtKey"
						name="motgaCltalCtrctBsSgmtSeqNo" /> <input type="hidden"
						name="infRecType" />
					<input type="hidden" id="reportflag" name="reportflag">
					<table style="width: 100%;" id='table2'>
						<tr height="40px">
							<td style="width: 12%;">&nbsp;抵（质）押合同编号:</td>
							<td style="width: 12%"><input  id="CCCODE1" name="ccCode1"
								type="text" class="easyui-textbox" data-options=""
								style="width: 130px" readonly='true'/></td>
							<td style="width: 12%;">&nbsp;抵（质）押合同标识码:</td>
							<td style="width: 12%"><input checkId='AN..60_AM' id="CCCODE" name="ccCode"
								type="text" class="easyui-textbox" data-options=""
								style="width: 130px" /></td>
							
							<td style="width: 14%">&nbsp;报告时点说明代码:</td>
							<td style="width: 12%"><input checkId="Enum_AM" name="rptDateCode" type="text" id="RPTDATECODE"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td style="width: 12%">&nbsp;债务人身份类别:</td>
							<td style="width: 13%"><input checkId="Enum_AM" name="infoIDType" type="text" id="INFOIDTYPE"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
							<td>&nbsp;债务人姓名:</td>
							<td><input checkId='ANC..80_AM' name="name" type="text" class="easyui-textbox" id="NAME"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;债务人身份标识类型:</td>
							<td><input checkId='Enum_AM' name="certType" type="text" id="CERTTYPE"
								class="easyui-combobox" data-options="" style="width: 130px" /></td>
							<td>&nbsp;债务人身份标识号码:</td>
							<td><input checkId='ANC..40_AM' name="certNum" type="text" class="easyui-textbox" id="CERTNUM"
								data-options="" style="width: 130px" /></td>
							<td>&nbsp;业务管理机构代码:</td>
							<td><input checkId='AN14_AM' name="mngmtOrgCode" type="text" id="MNGMTORGCODE"
								class="easyui-textbox" data-options="" style="width: 130px" /></td>
						</tr>
						<tr height="40px">
						<td style="width: 12%">&nbsp;信息报告日期:</td>
							<td style="width: 12%"><input checkId="Date_AM" name="rptDate" type="text" id="RPTDATE"
								class="easyui-datebox" data-options="" style="width: 130px" /></td>
						</tr>
					</table>
					<div style="clear: both; padding: 20px" align="center">
						<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable2"
							style="width: 100px" onclick="submitform('motCltCtrBsSgmt','sub','table2')">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable2"
							style="width: 130px" onclick="submitform('motCltCtrBsSgmt','updSub','table2')">提交并生成更正报文</a>
					</div>
				</form>
			</div>


			<!-- 其他债务人信息段 -->
			<div class="easyui-layout" fit="true" id="comRecInfSgmt">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px">
					<div style="border-bottom: 1px solid rgb(149, 184, 231)">
						<div style="padding: 2px; display: inline-block;">&nbsp;其他债务人名称:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="idSgmtName" class="easyui-textbox"
								style="width: 100px;" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;其他债务人身份标识号码:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input id="idSgmtIdNum" class="easyui-textbox"
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
					<table id="comRecInfSgmtTable" style="height: 100%; width: 100%;"></table>
				</div>

				<!-- 修改项弹出框 -->
				<div id="dialog-comRecInfSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 300px; padding: 5px;">
					<form id="comRecInfSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="comRecInfSgmtKey"
							name="comRecInfSgmtSeqNo" />
						<!-- 自身主键 -->
						<input type="hidden" name="otrRecSeqNo">

						<table style="width: 100%;" id='table3'>

							<tr height="40px">
								<td>&nbsp;身份类别:</td>
								<td><input checkId='Enum_AM' name="infoIDType" type="text" id="INFOIDTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td>&nbsp;其他债务人名称:</td>
								<td><input checkId='ANC..80_AM' name="guarName" type="text" id="GUARNAME"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>
							</tr>
							<tr height="40px">

								<td>&nbsp;其他债务人身份标识类型:</td>
								<td><input checkId='Enum_AM' name="guarCertType" type="text" id="GUARCERTTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td>&nbsp;其他债务人身份标识号码:</td>
								<td><input checkId='ANC..40_AM' name="guarCertNum" type="text" id="GUARCERTNUM"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>
							</tr>

						</table>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 100px" onclick="submitform('comRecInfSgmt','sub','table3')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								style="width: 130px" onclick="submitform('comRecInfSgmt','updSub','table3')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>
			<!-- 抵押物信息段 -->
			<div class="easyui-layout" fit="true" id="motgaProptInfSgmt">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px">
					<div style="border-bottom: 1px solid rgb(149, 184, 231)">
						<div style="padding: 2px; display: inline-block;">&nbsp;抵押人名称:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="idSgmtName" class="easyui-textbox"
								style="width: 100px;" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;抵押人身份标识号码:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input id="idSgmtIdNum" class="easyui-textbox"
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
					<table id="motgaProptInfSgmtTable"
						style="height: 100%; width: 100%;"></table>
				</div>

				<!-- 修改项弹出框 -->
				<div id="dialog-motgaProptInfSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 400px; padding: 5px;">
					<form id="motgaProptInfSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="motgaProptInfSgmtKey"
							name="comRecInfSgmtSeqNo" />
						<!-- 自身主键 -->
						<input type="hidden" name="pleInfSeqNo">

						<table style="width: 100%;" id='table4'>
							<tr height="40px">
								<td style="width: 100px;">&nbsp;抵押物种类:</td>
								<td style="width: 100px"><input checkId='Enum_AM' name="pleType" type="text" id="PLETYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td style="width: 100px">&nbsp;抵押物评估日期:</td>
								<td style="width: 100px"><input name="valDate" type="text" id="VALDATE"
									class="easyui-datebox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;抵押物识别号类型:</td>
								<td><input name="motgaProptIDType" type="text" id="MOTGAPROPTIDTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td>&nbsp;抵押人身份类别:</td>
								<td><input  checkId='Enum_AM' name="pledgorType" type="text" id="PLEDGORTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;抵押物唯一识别号:</td>
								<td><input checkId='ANC..40_AO' name="pleCertID" type="text" id="PLECERTID"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>
								<td>&nbsp;抵押人名称:</td>
								<td><input checkId='ANC..80_AM' name="pledgorName" type="text" id="PLEDGORNAME"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;抵押物所在地行政区划:</td>
								<td><input name="pleDistr" type="text" id="PLEDISTR"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td>&nbsp;抵押人身份标识类型:</td>
								<td><input checkId='Enum_AM' name="pleorCertType" type="text" id="PLEORCERTTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;抵押物评估价值:</td>
								<td><input checkId='uInt..15_AO' name="pleValue" type="text" id="PLEVALUE"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>
								<td>&nbsp;抵押人身份标识号码:</td>
								<td><input checkId='ANC..40_AM' name="pleorCertNum" type="text" id="PLEORCERTNUM"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;币种:</td>
								<td><input name="pleCy" type="text" class="easyui-combobox" id="PLECY"
									data-options="" style="width: 130px" /></td>
								<td>&nbsp;抵押物说明:</td>
								<td><input checkId='ANC..200_AO' name="pleDesc" type="text" id="PLEDESC"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;评估机构类型:</td>
								<td><input name="valOrgType" type="text" id="VALORGTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td></td>
								<td></td>

							</tr>
						</table>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable4"
								style="width: 100px" onclick="submitform('motgaProptInfSgmt','sub','table4')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable4"
								style="width: 130px" onclick="submitform('motgaProptInfSgmt','updSub','table4')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>
			<!-- 质物信息段-->
			<div class="easyui-layout" fit="true" id="cltalInfSgmt">


				<div class="easyui-layout"
					data-options="region: 'north',border: false"
					style="padding-bottom: 1px">
					<div style="border-bottom: 1px solid rgb(149, 184, 231)">
						<div style="padding: 2px; display: inline-block;">&nbsp;出质人名称:</div>
						<div style="padding: 2px; display: inline-block;">
							<input id="idSgmtName" class="easyui-datebox"
								style="width: 100px;" />
						</div>
						<div style="padding: 2px; display: inline-block;">&nbsp;出质人身份标识号码:</div>
						<div
							style="padding: 2px; vertical-align: middle; display: inline-block;">
							<input id="idSgmtIdNum" class="easyui-textbox"
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
					<table id="cltalInfSgmtTable" style="height: 100%; width: 100%;"></table>
				</div>

				<!-- 修改项弹出框 -->
				<div id="dialog-cltalInfSgmt" class="easyui-dialog" title="修改"
					data-options="closed:true,modal:true"
					style="width: 600px; height: 300px; padding: 5px;">
					<form id="cltalInfSgmtForm" method="POST"
						style="height: 100%; width: 100%">
						<!-- 关联主键 -->
						<input type="hidden" id="cltalInfSgmtKey" name="cltalInfSgmtSeqNo" />
						<!-- 自身主键 -->
						<input type="hidden" name="impInfSeqNo">

						<table style="width: 100%;">
							<tr height="40px">
								<td style="width: 100px;">&nbsp;抵押物种类:</td>
								<td style="width: 100px"><input checkId='Enum_AM' name="impType" type="text" id="IMPTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>
								<td style="width: 100px">&nbsp;出质人名称:</td>
								<td style="width: 100px"><input checkId='ANC..80_AM' name="pawnName" type="text" id="PAWNNAME"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;质物价值:</td>
								<td><input checkId='uInt..15_AM' name="impVal" type="text" class="easyui-textbox" id="IMPVAL"
									data-options="" style="width: 130px" /></td>
								<td>&nbsp;出质人身份标识类型:</td>
								<td><input checkId='Enum_AM' name="pawnCertType" type="text" id="PAWNCERTTYPE"
									class="easyui-combobox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;币种:</td>
								<td><input checkId='Enum_AM' name="impCy" type="text" class="easyui-combobox" id="IMPCY"
									data-options="" style="width: 130px" /></td>
								<td>&nbsp;出质人身份标识号码:</td>
								<td><input checkId='ANC..40_AM' name="pawnCertNum" type="text" id="PAWNCERTNUM"
									class="easyui-textbox" data-options="" style="width: 130px" /></td>

							</tr>
							<tr height="40px">
								<td>&nbsp;出质人身份类别:</td>
								<td><input checkId='Enum_AM' name="ippc" type="text" class="easyui-combobox" id="IPPC"
									data-options="" style="width: 130px" /></td>
								<td></td>
								<td></td>

							</tr>

						</table>
						<div style="clear: both; padding: 20px" align="center">
							<a href="javascript:void(0)" class="easyui-linkbutton" id="subTable5"
								style="width: 100px" onclick="submitform('cltalInfSgmt','sub','table5')">提交</a>
							<a href="javascript:void(0)" class="easyui-linkbutton" id="updSubTable5"
								style="width: 130px" onclick="submitform('cltalInfSgmt','updSub','table5')">提交并生成更正报文</a>
						</div>
					</form>

				</div>

			</div>

		</div>
	</div>
</body>
</html>