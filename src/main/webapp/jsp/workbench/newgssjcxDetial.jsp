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
<%-- <link rel="stylesheet" type="text/css"
	href="${ctx}/css/stylesheet.css"> --%>
<script type="text/javascript" src="${ctx}/js/workbench/newgssjcxDetail.js"></script>
<style>
 ul.tree, ul.tree ul {
     list-style-type: none;
     background: url(../../images/vline.png) repeat-y;
     margin: 0;
     padding: 0;
   }
   
   ul.tree ul {
     margin-left: 10px;
   }

    ul.tree li {
     margin: 0;
     padding: 0 12px;
     line-height: 20px;
     background: url(../../images/node.png) no-repeat;
     color: #369;
     font-weight: bold;
   }
   /*ul.tree li.last {
     background: #fff url(images/lastnode.png) no-repeat;
   }*/
    ul.tree li:last-child {
     background: #fff url(../../images/lastnode.png) no-repeat;
   }
</style>
<%-- <script type="text/javascript" src="${ctx}/js/LodopFuncs.js"></script>	 --%>
</head>

<body link=blue vlink=purple>
<!-- <object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0>  
	       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed> 
</object>  -->
<button value="打印" onclick="doPrint();">打印</button>
<!-- startprint -->
<form id="form1">
<span >${user.userName}</span>
<span >${user.userId}</span> 
<span id="nowDate"></span>
<br>
<table border=1 cellpadding=0 cellspacing=0  style='border-collapse:collapse;table-layout:fixed;width:100%;word-break:break-all;word-wrap:break-word;' id="reportTable">
 
 <tr height=18 style='height:13.5pt'>
  <td style='width:20pt;height:20pt'>主体基本信息</td>
  <td style='width:120pt'></td>
  <td style='width:40pt'></td>
  <td style='width:40pt'></td>
  <td style='width:35pt'></td>
  <td style='width:20pt'></td>
  <td style='width:80pt'></td>
  <td style='width:40pt'></td>
  <td style='width:40pt'></td>
  <td style='width:20pt'></td>
  <td style='width:20pt'></td>
  <td style='width:20pt'></td>
  <td style='width:20pt'></td>
 </tr>
 <tr height=18 style='height:13.5pt'>
  <td height=18 class=xl65 style='height:20pt'>类型</td>
  <td >名称</td>
  <td >成立日期</td>
  <td >注册资本</td>
  <td >注册币种</td>
  <td >企业状态</td>
  <td >统一社会信用代码</td>
  <td >行业</td>
  <td >注册号 </td>
  <td >是否上市</span></td>
  <td >是否受益人</td>
  <td >受益人类型</td>
  <td >持股占比</td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl66 style='height:20pt;'><span id="NODE_TYPEDESC"></span></td>
  <td ><span id="PROP_NAME"></span></td>
  <td ><span id="ESDATE"></span></td>
  <td ><span id="REGCAP"></span></td>
  <td ><span id="REGCAPCUR_DESC"></span></td>
  <td ><span id="ENTSTATUSDESC"></span></td>
  <td ><span id="CREDITCODE"></span></td>
  <td ><span id="INDUSTRYPHY_DESC"></span></td>
  <td ><span id="REGNO"></span></td>
  <td ><span id="ISLISTDESC"></span></td>
  <td ><span id="ISSYRDESC"></span></td>
  <td ><span id="SYRTYPE"></span></td>
  <td ><span id="CGZB"></span></td>
 </tr>
 <tr height=18 style='height:20pt'>
  <td height=18 colspan=13 style='height:20pt;mso-ignore:colspan'></td>
 </tr>
 <tr height=18 style='height:20pt'>
  <td height=18 style='height:20pt'>关联关系</td>
  <td colspan=12 style='mso-ignore:colspan'></td>
 </tr>


</table>
<div class="col-md-offset-5 col-sm-12">
                    <ul id="treeList" class="tree">
                        <li>研发中心
                            <ul>
                                <li>北京研发部</li>
                                <li>深圳研发部
                                    <ul>
                                        <li>产品1组</li>
                                        <li>产品2组</li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>销售部
                            <ul>
                                <li>售前</li>
                                <li>售后</li>
                                <li>代理分区
                                    <ul>
                                        <li>东北区</li>
                                        <li>华北区</li>
                                        <li>华南区</li>
                                        <li>华中区</li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>财务部</li>
                        <li>人事部</li>
                    </ul>
                </div>
<br>
<br>
<br>
<br>
<br>
<br><span style='font-size:20px'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;网点业务公章:___________</span>

</form>
<!-- endprint -->

</body>

</html>
