package com.hkbank.pbcrs.controller;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.PbcrsSysConTrollerMapper;
import com.hkbank.pbcrs.model.FilterData;
import com.hkbank.pbcrs.service.FilterDataService;
import com.hkbank.pbcrs.service.ReportCmdService;
import com.hkbank.pbcrs.tool.List2Excel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 过滤信息处理类
 * @author: HS1112
 * @date: 2022-01-20 14:50
 */
@Controller
@RequestMapping("/filterData")
public class FilterDataController extends BaseController {
    private static final Logger log = LogManager.getLogger(FilterDataController.class);

    @Autowired
    private FilterDataService filterDataService;

    @Autowired
    private ReportCmdService reportCmdService;

    @Autowired
    private PbcrsSysConTrollerMapper sysConTrollerMapper;

    /**
     * 查询过滤信息
     * @param request
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        try {
            return filterDataService.selectByPage(param);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("列表查询出现异常!", e);
        }
        return null;

    }

    @RequestMapping("/report")
    @ResponseBody
    public Map<String,Object> report(HttpServletRequest request){
        Map<String, Object> rslt = new HashMap<String, Object>();
        Map<String, Object> params = Util.parseWebParas(request);
        Map<String,Object> con_info_params = new HashMap<String, Object>();
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                log.error("报文修改报送时session会话失效!");
                rslt.put("RET_CODE", "FAILED");
                return rslt;
            }
            params.put("CreateUser", user.getUserId());
            //controller层进行插入记录，防止事务回滚
            Map<String,Object> map = new HashMap<String, Object>();
            String web_proc_id = UUID.randomUUID().toString();
            con_info_params.put("infRecType", params.get("reportId").toString());
            con_info_params.put("insertUserName", user.getUserName());
            con_info_params.put("insertUserNo", user.getUserId());
            con_info_params.put("webProcId", web_proc_id);
            con_info_params.put("state", "0");
            con_info_params.put("etlDate", params.get("etl_date").toString().replaceAll("-", ""));
            sysConTrollerMapper.insertConInfo(con_info_params);
            params.put("webProcId", web_proc_id);
            params.put("reportFlag","2");
            List<String> conditions = reportCmdService.reportGetFile(params);
            //将socket调用提取到controller层防止事务的影响导致读取不到condition表信息
            if (conditions != null && conditions.size()>0) {
                for (String condition : conditions) {
                    try {
                        reportCmdService.socket(condition+"REPORT_SOURCE_S");
                    } catch (Throwable e) {
                        e.printStackTrace();
                        throw new Exception("连接错误");
                    }

                }
            }
            rslt.put("RET_CODE", "SUCCESS");
            con_info_params.put("state", "1");
            sysConTrollerMapper.updSysConInfo(con_info_params);
        } catch (Exception e) {
            log.error("报文修改报送异常!", e);
            rslt.put("RET_CODE", "FAILED");
            if (e.getMessage().contains("连接错误")) {
                rslt.put("ERR_MSG", "报送程序未正常启动,请联系管理员！");
            }
            if (e.getMessage().contains("未查询到指定报送日期数据")) {
                rslt.put("ERR_MSG", "未查询到指定报送日期数据！");
            }

            con_info_params.put("state", "2");
            sysConTrollerMapper.updSysConInfo(con_info_params);
            return rslt;
        }

        return rslt;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Map<String, Object> insert(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        Map<String,Object> result = new HashMap<>(16);
        try {
            int  count =  filterDataService.insert(param);
            result.put("RET_CODE","SUCCESS");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加出现异常!", e);
        }
        result.put("RET_CODE","FAIL");
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        Map<String,Object> result = new HashMap<>(16);
        try {
            int  count =  filterDataService.delete(param);
            result.put("RET_CODE","SUCCESS");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除出现异常!", e);
        }
        result.put("RET_CODE","FAIL");
        return result;
    }

    @RequestMapping("/updateEnable")
    @ResponseBody
    public Map<String, Object> updateEnable(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        Map<String,Object> result = new HashMap<>(16);
        try {
            int  count =  filterDataService.updateEnable(param);
            result.put("RET_CODE","SUCCESS");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新出现异常!", e);
        }
        result.put("RET_CODE","FAIL");
        return result;
    }

    /**
     * 批量添加模板下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/downloadAdd")
    @ResponseBody
    public Map<String,Object>  downloadAdd(HttpServletRequest request, HttpServletResponse response){


        String expDir = "excelFile\\批量添加"+
                String.valueOf(System.currentTimeMillis())  + ".xls";
        String ctxDir = request.getSession().getServletContext().getRealPath(
                String.valueOf(File.separatorChar));

        String saveDirectory = ctxDir + expDir;
        File file = new File(saveDirectory);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
        List2Excel export = new List2Excel();
        export.setSheetName("批量添加模板");
        export.setTitle("批量添加模板");
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("0", "客户号(必填)");
        data.put("1", "名称(必填)");
        data.put("2", "证件号码(必填)");
        data.put("3", "原因");
        map.add(data);

        try {
            String filepath=export.toExcel(map, saveDirectory);
            // url路径
            Properties pbcrsReportCfg = Resources
                    .getResourceAsProperties("config/common/report.properties");
            String path = pbcrsReportCfg.getProperty("batchUrl");
            //String url="http://localhost:";
            String destPath = path + request.getServerPort()
                    + request.getContextPath() + "/"
                    + expDir.replaceAll("\\\\", "/");

            System.out.println(" 导出Excel文件[成功] ");

            return fileDownload(filepath,response);
        } catch (Exception e) {
            System.out.println(" 导出Excel文件[失败] ");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量添加上传
     * @param request
     * @return
     * @throws Throwable
     */
    @ResponseBody
    @RequestMapping("/uploadFileAdd")
    public Map<String, Object> uploadFileAdd(HttpServletRequest request) throws Throwable {
        Map<String, Object> rslt = new HashMap<String, Object>();
        List<FileItem> fileItemList = Util.resolveFileItem(request);
        Map<String, Object> params = Util.parseWebParas(request);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        if (fileItemList != null && fileItemList.size() > 0) {
            /* 1.2. 将用户文件上传至临时目录 */
            List<Map<String, String>> fileList = generalFileUpload(fileItemList);
            /* 1.3. 判断用户上传是否成功 */
            if (fileList == null) {
                rslt.put("RET_CODE", "FAILED");
                rslt.put("RET_MSG", "文件上传出现异常, 请联系后台管理人员!");
                return rslt;
            }
            User user =	(User)request.getSession().getAttribute("user");
            params.put("userid", user.getUserId());
            params.put("filePath", fileList.get(0).get("UPLOAD_FILE_NAME"));
            try {
                String result = filterDataService.analyzeFileAdd(params);
                if(result != null){
                    rslt.put("RET_CODE", "FAILD");
                    rslt.put("RET_MSG",result);
                    return rslt;
                }
                rslt.put("RET_CODE", "SUCCESS");
                rslt.put("RET_MSG", "上传成功");
                return rslt;
            } catch (ParseException e) {
                log.debug("批量添加上传失败={}",e.getMessage());
                rslt.put("RET_CODE", "FAILD");
                rslt.put("RET_MSG", "文件数据日期格式不正确，请检查文件!");
                return rslt;
            }
            catch (Exception e) {
                e.printStackTrace();
                log.debug("批量添加上传失败={}",e.getMessage());
                rslt.put("RET_CODE", "FAILD");

                return rslt;
            }
        }
        return null;
    }

    /**
     * 批量删除模板下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/downloadDel")
    @ResponseBody
    public Map<String,Object>  downloadDel(HttpServletRequest request, HttpServletResponse response){


        String expDir = "excelFile\\批量删除"+
                String.valueOf(System.currentTimeMillis())  + ".xls";
        String ctxDir = request.getSession().getServletContext().getRealPath(
                String.valueOf(File.separatorChar));

        String saveDirectory = ctxDir + expDir;
        File file = new File(saveDirectory);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
        List2Excel export = new List2Excel();
        export.setSheetName("批量删除模板");
        export.setTitle("批量删除模板");
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("0", "客户号(必填)");
        data.put("1", "");
        map.add(data);
        try {
            String filepath=export.toExcel(map, saveDirectory);
            // url路径
            Properties pbcrsReportCfg = Resources
                    .getResourceAsProperties("config/common/report.properties");
            String path = pbcrsReportCfg.getProperty("batchUrl");
            //String url="http://localhost:";
            String destPath = path + request.getServerPort()
                    + request.getContextPath() + "/"
                    + expDir.replaceAll("\\\\", "/");

            System.out.println(" 导出Excel文件[成功] ");

            return fileDownload(filepath,response);
        } catch (Exception e) {
            System.out.println(" 导出Excel文件[失败] ");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量删除上传
     * @param request
     * @return
     * @throws Throwable
     */
    @ResponseBody
    @RequestMapping("/uploadFileDel")
    public Map<String, Object> uploadFileDel(HttpServletRequest request) throws Throwable {
        Map<String, Object> rslt = new HashMap<String, Object>();
        List<FileItem> fileItemList = Util.resolveFileItem(request);
        Map<String, Object> params = Util.parseWebParas(request);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        if (fileItemList != null && fileItemList.size() > 0) {
            /* 1.2. 将用户文件上传至临时目录 */
            List<Map<String, String>> fileList = generalFileUpload(fileItemList);
            /* 1.3. 判断用户上传是否成功 */
            if (fileList == null) {
                rslt.put("RET_CODE", "FAILED");
                rslt.put("RET_MSG", "文件上传出现异常, 请联系后台管理人员!");
                return rslt;
            }
            User user =	(User)request.getSession().getAttribute("user");
            params.put("userid", user.getUserId());
            params.put("filePath", fileList.get(0).get("UPLOAD_FILE_NAME"));
            try {
                String result = filterDataService.analyzeFileDel(params);
                if(result != null){
                    rslt.put("RET_CODE", "FAILD");
                    rslt.put("RET_MSG",result);
                    return rslt;
                }
                rslt.put("RET_CODE", "SUCCESS");
                rslt.put("RET_MSG", "上传成功");
                return rslt;
            } catch (ParseException e) {
                log.debug("批量删除上传失败={}",e.getMessage());
                rslt.put("RET_CODE", "FAILD");
                rslt.put("RET_MSG", "文件数据日期格式不正确，请检查文件!");
                return rslt;
            }
            catch (Exception e) {
                e.printStackTrace();
                log.debug("批量删除上传失败={}",e.getMessage());
                rslt.put("RET_CODE", "FAILD");

                return rslt;
            }
        }
        return null;
    }


    /**
     * 全量信息导出为excel
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/downloadAll")
    @ResponseBody
    public Map<String,Object>  downloadAll(HttpServletRequest request, HttpServletResponse response){


        String expDir = "excelFile\\全量信息"+
                String.valueOf(System.currentTimeMillis())  + ".xls";
        String ctxDir = request.getSession().getServletContext().getRealPath(
                String.valueOf(File.separatorChar));

        String saveDirectory = ctxDir + expDir;
        File file = new File(saveDirectory);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        List<Map<String, Object>> map = new ArrayList<Map<String,Object>>();
        List2Excel export = new List2Excel();
        export.setSheetName("全量信息");
        export.setTitle("全量信息");
        ArrayList<Integer> columnWidths = new ArrayList<>(16);
        columnWidths.add(20*256);
        columnWidths.add(50*256);
        columnWidths.add(25*256);
        columnWidths.add(15*256);
        columnWidths.add(20*256);
        columnWidths.add(30*256);
        columnWidths.add(10*256);
        export.setColumnWidths(columnWidths);
        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("0", "客户号");
        data.put("1", "名称");
        data.put("2", "证件号码");
        data.put("3", "录入日期");
        data.put("4", "录入时间");
        data.put("5", "原因");
        data.put("6", "是否启用");
        map.add(data);
        List<FilterData> list = filterDataService.getList();
        for (FilterData filterData : list) {
            Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
            dataMap.put("0", filterData.getSourcecustID());
            dataMap.put("1", filterData.getName());
            dataMap.put("2", filterData.getId());
            dataMap.put("3", filterData.getCreateDate());
            dataMap.put("4", filterData.getCreateTime());
            dataMap.put("5", filterData.getReason());
            dataMap.put("6", filterData.getIsEnable().equals("Y")?"是":"否");
            map.add(dataMap);
        }
        try {
            String filepath=export.toExcel(map, saveDirectory);
            // url路径
            Properties pbcrsReportCfg = Resources
                    .getResourceAsProperties("config/common/report.properties");
            String path = pbcrsReportCfg.getProperty("batchUrl");
            //String url="http://localhost:";
            String destPath = path + request.getServerPort()
                    + request.getContextPath() + "/"
                    + expDir.replaceAll("\\\\", "/");

            System.out.println(" 导出Excel文件[成功] ");

            return fileDownload(filepath,response);
        } catch (Exception e) {
            System.out.println(" 导出Excel文件[失败] ");
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, Object> fileDownload(String filePath,HttpServletResponse response){
        BufferedInputStream ins = null ;
        try {
            File file = new File(filePath);
            String filename = FilenameUtils.getName(filePath);
            filename = URLEncoder.encode(filename, "UTF-8");
            ins = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[ins.available()];
            ins.read(buffer);
            ins.close();

            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename="
                    + filename);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream ous = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/octet-stream");
            ous.write(buffer);
            ous.flush();
            ous.close();

        } catch (Exception e) {
            Map<String, Object> rslt = new HashMap<String, Object>();
            rslt.put("失败", "文件下载出错");
            return rslt;
        }
        return null;
    }



}
