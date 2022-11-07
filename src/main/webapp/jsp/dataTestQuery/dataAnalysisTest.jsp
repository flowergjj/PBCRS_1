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
<script type="text/javascript" src="${ctx}/js/dataTestQuery/dataAnalysisTest.js"></script>
<title>数据报送质量分析测试</title>
</head>
<body style="padding:0px;">
		<div class="easyui-layout" style="padding: 0px; border:1px solid rgb(149, 184, 231)"
			data-options="region: 'north'">
			<table class="queryTable" style="width: 100%;">
				<tr>
					<td style="width: 65px">&nbsp;日期：</td>
					<td style="width: 130px"><input id="rptDate"
						style="width: 110px"  class="easyui-datebox"
						editable="false"></input></td>

					<td style="width: 70px;">
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-search" onclick="doQuery()">查询</a>
						</td>
					<td>
							<a href="javascript:void(0)" class="easyui-linkbutton"
								iconCls="icon-cancel" 
						onclick="doClear()">清空</a>
						</td>
				</tr>
			</table>
		</div>

		<!-- 主页面table表 -->
		<div  class="easyui-layout"
			data-options="region: 'center', border: false" >
			<h2 style="margin: 0px" align="center">客户标识报送情况</h2>
			<table id="customerReport" style="width: 100%;" data-options="nowrap:false">
			</table>
			<h2 style="margin: 0px" align="center">基本信息指标分析</h2>
			<table id="baseInfoAnalysis" style="width: 100%;" data-options="nowrap:false">
			</table>
			<h2 style="margin: 0px" align="center">财务报表信息报送情况</h2>
			<table id="reportFromInfo" style="width: 100%;" data-options="nowrap:false">
			</table>
	   </div>
</body>

</html>