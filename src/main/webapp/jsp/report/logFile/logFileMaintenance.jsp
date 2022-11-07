<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/easyui/themes/demo.css">
<script type="text/javascript" src="${ctx}/js/common_utils.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.loadmask.js"></script>
<script type="text/javascript"
	src="${ctx}/js/report/logFile/logFileMaintenance.js"></script>
<script type="text/javascript"
	src="${ctx}/js/report/reportType.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<title>日志文件维护</title>
</head>
<body>
	<div class="easyui-layout" fit="true">
		<div id="cc" class="easyui-layout" style="padding: 0px;"
			data-options="region: 'north'">
			<div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;报文时间:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="reportDate" type="text" class="easyui-datebox"
						data-options="editable:false" style="width: 120px" />
				</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">&nbsp;报文类型:</div>
				<div style="padding: 2px; display: inline-block;_zoom:1;_display:inline">
					<input id="queryRptType" class="easyui-combobox" name="dept"
						style="width: 200px;"/>
						
					<!-- 	<option value=''></option>
				<option value='110'> 个人基本信息请求记录</option>
				<option value='114'>个人基本信息删除请求记录       </option>
				<option value='120'>家族关系信息记录               </option>
				<option value='130'>个人证件有效期信息记录         </option>
				<option value='134'>个人证件有效期信息删除请求记录 </option>
				<option value='140'>个人证件整合信息记录           </option>

				<option value='210'>个人借贷账户信息请求记录';
				<option value='211'>个人借贷账户标识变更请求记录</option>
				<option value='212'>个人借贷账户按段更正请求记录</option>
				<option value='213'>个人借贷账户按段删除请求记录</option>
				<option value='214'>个人借贷账户整笔删除请求记录</option>
				<option value='215'>个人借贷账户特殊事件说明记录</option>
				<option value='220'>个人授信协议信息请求记录';
				<option value='221'>个人授信协议标识变更请求记录</option>
				<option value='222'>个人授信协议按段更正请求记录</option>
				<option value='223'>个人授信协议按段删除请求记录</option>
				<option value='224'>个人授信协议整笔删除请求记录</option>

				<option value='230'>个人担保账户信息记录        </option>
				<option value='231'>个人担保账户标识变更请求记录</option>
				<option value='232'>个人担保账户更正请求记录    </option>
				<option value='233'>个人担保账户按段删除请求记录</option>
				<option value='234'>个人担保账户整笔删除请求记录</option>

				<option value='510'>抵质押物信息请求记录</option>
				<option value='511'>抵(质)押合同标识变更请求记录</option>
				<option value='514'>抵(质)押合同整笔删除请求记录</option>

				<option value='310'>企业基本信息请求记录';
				<option value='314'> 企业基本信息删除请求记录';
				<option value='340'> 企业身份标识整合信息记录';
				<option value='350'> 企业间关联关系信息记录 </option>

				<option value='410'>企业借贷账户信息请求记录';
				<option value='411'> 企业借贷账户标识变更请求记录      </option>
				<option value='412'> 企业借贷账户更正请求记录          </option>
				<option value='413'> 企业借贷账户按段删除请求记录      </option>
				<option value='414'> 企业借贷账户整笔删除请求记录      </option>
				<option value='420'>企业授信协议信息请求记录';
				<option value='421'> 企业授信协议标识变更请求记录      </option>
				<option value='422'> 企业授信协议更正请求记录          </option>
				<option value='423'> 企业授信协议按段删除请求记录      </option>
				<option value='424'> 企业授信协议整笔删除请求记录      </option>
				<option value='430'> 企业最高额保证合同信息记录        </option>
				<option value='431'> 企业最高额保证合同标识变更请求记录</option>
				<option value='432'> 企业最高额保证合同更正请求记录    </option>
				<option value='433'> 企业最高额保证合同按段删除请求记录</option>
				<option value='434'> 企业最高额保证合同整笔删除请求记录</option>

				<option value='440'>../jsp/ent/modify/EnSecAcctInfDetial.jsp';
				<option value='441'>企业担保账户标识变更请求记录</option>
				<option value='442'>企业担保账户更正请求记录    </option>
				<option value='443'>企业担保账户按段删除请求记录</option>
				<option value='444'> 企业担保账户整笔删除请求记录</option>

				<option value='610'>../jsp/ent/modify/BalanceSheetDetial.jsp';
				<option value='620'>../jsp/ent/modify/IncomeSProfitApprDetial.jsp';
				<option value='630'>../jsp/ent/modify/CashFlowsDetial.jsp';
				<option value='640'>../jsp/ent/modify/InsBalSheDetial.jsp';
				<option value='650'>../jsp/ent/modify/IncAndExpStaDetial.jsp';
				<option value='614'> 企业资产负债表整笔删除请求记录     </option>
				<option value='624'> 企业利润及利润分配表整笔删除请求记录';
				<option value='634'> 企业现金流量表整笔删除请求记录     </option>
				<option value='644'> 事业单位资产负债表整笔删除请求记录 </option>
				<option value='654'> 事业单位收入支出表整笔删除请求记录 </option>
					</select> -->
					<!-- <input id="queryName" class="easyui-textbox"  style="width: 100px;" /> -->
				</div>


				<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-search" onclick="doQuery()">查询</a>
					</div>
				</div>
				<div style="padding: 4px; display: inline-block;_zoom:1;_display:inline">

					<div>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							iconCls="icon-cancel" onclick="clearQuery()">清空</a>
					</div>
				</div>
			</div>

		</div>

		<!-- 中部表格元素-->
		<div class="easyui-layout "
			data-options="region:'center',border: false">
			<table id="DataListTable" style="height: 100%; width: 100%;"></table>
		</div>

	</div>
</body>
</html>