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
            src="${ctx}/js/report/filter/filterData.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>过滤信息处理</title>
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

    <div id="dialog-add" class="easyui-dialog" title="添加"
         data-options="closed:true,modal:true"
         style="width: 80%; height: 60%; padding: 5px;">
        <div style="width: 90%;text-align: center">
            <div style="padding: 2px 0 ">
                <div style="display: inline-block;margin-right:10px">&nbsp;客户号:<input class="easyui-textbox" id="sourcecustId1" required="required" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;名称:<input class="easyui-textbox" id="name1"  required="required" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;证件号码:<input class="easyui-textbox" id="certNum1"  required="required" /></div>
                <div style="display: inline-block;margin-right:10px">&nbsp;过滤原因:<input class="easyui-textbox" id="reason"   /></div>
            </div>
            <div style="padding: 20px 0 ">
                <a
                        href="javascript:void(0)" class="easyui-linkbutton"
                        iconCls="icon-search" onclick="addClick()">确定</a>
                &nbsp;&nbsp; <a href="javascript:void(0)"
                                class="easyui-linkbutton" iconCls="icon-cancel"
                                onclick="addCancel()">取消</a>
            </div>
        </div>
    </div>
    <div id="dialog-importAdd" class="easyui-dialog" title="批量添加文件导入"
         data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
         style="width: 500px; height: 300px; padding: 5px;">
        <form id='dialog-importAddForm' method="POST" class="abms-form"
              enctype="multipart/form-data">
            <table>

                <tr>
                    <td colspan="6" style="width: 380px">选择上传文件:&nbsp;&nbsp;<input
                             type="file" name="addFile" id="addFile" size="35" accept=".xls,.xlsx" /></td>

                </tr>
                <tr>
                    <td style="width: 55px;"><a href="javascript:void(0)"
                                                class="easyui-linkbutton" iconCls="icon-save" onclick="addFileClick()">确定</a></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>

        </form>
    </div>
    <div id="dialog-importDel" class="easyui-dialog" title="批量删除文件导入"
         data-options="closed:true,modal:true,toolbar: '#dialog-toolbar-allocatableUserALL'"
         style="width: 500px; height: 300px; padding: 5px;">
        <form id='dialog-importDelForm' method="POST" class="abms-form"
              enctype="multipart/form-data">
            <table>

                <tr>
                    <td colspan="6" style="width: 380px">选择上传文件:&nbsp;&nbsp;<input
                            type="file" name="delFile" id="delFile"  size="35" accept=".xls,.xlsx" /></td>

                </tr>
                <tr>
                    <td style="width: 55px;"><a href="javascript:void(0)"
                                                class="easyui-linkbutton" iconCls="icon-save" onclick="delClick()">确定</a></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>

        </form>
    </div>
</div>
</body>
</html>
