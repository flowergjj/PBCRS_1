<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
            src="${ctx}/js/report/filter/filterReport.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>过滤报文查询</title>
</head>
<body>
<div class="easyui-layout" fit="true">
    <div class="easyui-layout" style="padding: 0px;"
         data-options="region: 'north',border:false">
        <div style="padding: 2px 0 ">
            <div style="display: inline-block;margin-right:10px">&nbsp;录入日期:<input class="easyui-datebox" id="createDate" /></div>
            <div style="display: inline-block;margin-right:10px">&nbsp;客户号:<input class="easyui-textbox" id="sourcecustID" /></div>
            <div style="display: inline-block;margin-right:10px">&nbsp;名称:<input class="easyui-textbox" id="name" style="width: 90px;" /></div>
            <div style="display: inline-block;margin-right:10px">&nbsp;证件号码:<input class="easyui-textbox" id="certNum" style="width: 120px;" /></div>
            <div style="display: inline-block;margin-right:10px">&nbsp;是否已启用:<input class="easyui-combobox" id="enable" style="width: 120px;" /></div>

            <div style="display: inline-block;margin-right:10px">
                <a
                        href="javascript:void(0)" class="easyui-linkbutton"
                        iconCls="icon-search" onclick="doQuery()">查询</a>
                &nbsp;&nbsp; <a href="javascript:void(0)"
                                class="easyui-linkbutton" iconCls="icon-cancel"
                                onclick="doClear()">清空</a>
            </div>
        </div>

    </div>
    <div class="easyui-layout"
         data-options="region:'center',border: false">
        <table id="filterDataTable" style="height: 100%; width: 100%;"></table>
    </div>

    <div id="dialog-update" class="easyui-dialog" title="更正信息"
         data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
         style="width: 60%; height: 60%; padding: 5px;text-align: center">
        <form id='dialog-updateForm' method="POST" class="abms-form">
            <div style="padding: 10px 0 ">
                <div style="display: none;margin-right:10px">&nbsp;业务主键:<input class="easyui-textbox" readonly id="BSSGMTSEQNO" name="BSSGMTSEQNO" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;信息记录类型:<input class="easyui-textbox" readonly id="INFRECTYPE" name="INFRECTYPE" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;名称:<input class="easyui-textbox" id="ENTNAME" name="ENTNAME" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;证件类型:<input class="easyui-combobox" id="ENTCERTTYPE" name="ENTCERTTYPE"  /></div>

            </div>
            <div style="padding: 10px 0 ">
                <div style="display: inline-block;margin-right:10px">&nbsp;证件号码:<input class="easyui-textbox" id="ENTCERTNUM" name="ENTCERTNUM" style="width: 120px;" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;报送日期:<input class="easyui-textbox"  readonly id="RPTDATE" name="RPTDATE" style="width: 120px;" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;管理机构:<input class="easyui-textbox" readonly id="ORG_CODE" name="ORG_CODE" style="width: 120px;" /></div>
            </div>
           <div style="padding: 10px 0 ">
               <div style="display: inline-block;margin-right:10px">
                   <a
                           href="javascript:void(0)" class="easyui-linkbutton"
                           iconCls="icon-search" onclick="doSubmit()">确定</a>
                   &nbsp;&nbsp; <a href="javascript:void(0)"
                                   class="easyui-linkbutton" iconCls="icon-cancel"
                                   onclick="doCancel()">取消</a>
               </div>
            </div>

        </form>
    </div>

</div>
</body>
</html>
