<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<jsp:include page="/jsp/common/header.jsp" />
<style id="style1">


</style>

<html>
<head>
<title>工商客户信息查询</title>
<meta http-equiv="Content-Type" content="text/html;
	 charset=utf-8">

<script type="text/javascript" src="${ctx}/js/workbench/gssjcxDetail.js"></script>


<body >

<button value="打印" onclick="doPrint();">打印</button>
<!-- startprint -->
<form id="form1">
<span >${user.userName}</span>
<span >${user.userId}</span> 
<span id="nowDate"></span>
<br>
<table border="2"   style='border-color:black; border-collapse:collapse;table-layout:fixed;width:100%;word-break:break-all;word-wrap:break-word;' id="reportTable">
 
 <tr height=18 style='height:13.5pt'>
  <td style='width:20pt;height:20pt;border-color:black;'></td>
  <td colspan=12 style="text-align: center;font-size:12px;;border-color:black;"><span style="text-align: center;font-size:20px;">主体基本信息</span></td>

 </tr>
 <tr height=18 style='height:13.5pt'>
  <td style='width:20pt;height:20pt;border-color:black;'>类型</td>
  <td style='width:120pt;border-color:black;'>名称</td>
  <td style='width:40pt;border-color:black;'>成立日期</td>
  <td style='width:40pt;border-color:black;'>注册资本</td>
  <td style='width:35pt;border-color:black;'>注册币种</td>
  <td style='width:20pt;border-color:black;'>企业状态</td>
  <td style='width:80pt;border-color:black;'>统一社会信用代码</td>
  <td style='width:40pt;border-color:black;'>行业</td>
  <td style='width:40pt;border-color:black;'>注册号 </td>
  <td style='width:20pt;border-color:black;'>是否上市</span></td>
  <td style='width:20pt;border-color:black;'>是否受益人</td>
  <td style='width:20pt;border-color:black;'>受益人类型</td>
  <td style='width:20pt;border-color:black;'>持股占比</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19  style='height:20pt;border-color:black;'><span id="NODE_TYPEDESC"></span></td>
  <td style='height:20pt;border-color:black;'><span id="PROP_NAME"></span></td>
  <td style='height:20pt;border-color:black;'><span id="ESDATE"></span></td>
  <td style='height:20pt;border-color:black;'><span id="REGCAP"></span></td>
  <td style='height:20pt;border-color:black;'><span id="REGCAPCUR_DESC"></span></td>
  <td style='height:20pt;border-color:black;'><span id="ENTSTATUSDESC"></span></td>
  <td style='height:20pt;border-color:black;'><span id="CREDITCODE"></span></td>
  <td style='height:20pt;border-color:black;'><span id="INDUSTRYPHY_DESC"></span></td>
  <td style='height:20pt;border-color:black;'><span id="REGNO"></span></td>
  <td style='height:20pt;border-color:black;'><span id="ISLISTDESC"></span></td>
  <td style='height:20pt;border-color:black;'><span id="ISSYRDESC"></span></td>
  <td style='height:20pt;border-color:black;'><span id="SYRTYPE"></span></td>
  <td style='height:20pt;border-color:black;'><span id="CGZB"></span></td>
 </tr>
 <tr height=18 style='height:20pt'>
  <td height=18 colspan=13 style='height:20pt;mso-ignore:colspan;border-color:black;'></td>
 </tr>
 


</table>

<br>
<br>
<br>
<br>
<table border="0"   style='ztable-layout:fixed;width:100%;word-break:break-all;word-wrap:break-word;' >
 <tr height=18 style='height:20pt'>
  <td height=18  style='height:20pt;mso-ignore:colspan;'><span style='font-size:15px'>开户企业公章:___________</span></td>
   <td height=18  style='height:20pt;mso-ignore:colspan;'><span style='font-size:15px'>网点公章:___________</span></td>
 </tr>
<tr></tr>
 <tr height=18 style='height:20pt'>
  <td height=18  style='height:20pt;mso-ignore:colspan;'><span style='font-size:15px'>法定代表人或授权代理人签字:______</span></td>
   <td height=18  style='height:20pt;mso-ignore:colspan;'><span style='font-size:15px'>网点经办人员签章:___________</span></td>
 </tr>
<tr></tr>
 <tr height=18 style='height:20pt'>
  <td height=18  style='height:20pt;mso-ignore:colspan;'><span style='font-size:15px'>日期:___________</span></td>
   <td height=18  style='height:20pt;mso-ignore:colspan;'><span style='font-size:15px'>日期:___________</span></td>
 </tr>
<tr></tr>
 
</table>
</form>
<!-- endprint -->

</body>

</html>
