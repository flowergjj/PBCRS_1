package com.hkbank.pbcrs.controller;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.service.FilterReportService;
import com.hkbank.pbcrs.service.ReportCmdService;
import org.apache.commons.collections.MapUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/filterReport")
public class FilterReportController {
    private static final Logger log = LogManager.getLogger(FilterReportController.class);

    @Autowired
    private FilterReportService filterReportService;

    @Autowired
    ReportCmdService reportCmdService;

    @RequestMapping("/getList")
    @ResponseBody
    public Map<String, Object> getList(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        try {
            return filterReportService.selectByPage(param);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("列表查询出现异常!", e);
        }
        return null;
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Map<String, Object> getOne(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        try {
            return filterReportService.selectOne(param);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("列表查询出现异常!", e);
        }
        return null;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Map<String, Object> update(HttpServletRequest request){
        Map<String, Object> param = Util.parseWebParas(request);
        Map<String,Object> rslt = new HashMap<>();
        try {
            int updCount =  filterReportService.update(param);
            rslt.put("RET_CODE","SUCCESS");
            rslt.put("RET_MSG","更新成功");
            return rslt;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更正信息出现异常!", e);
        }
        rslt.put("RET_CODE","FAIL");
        rslt.put("RET_MSG","更新失败");
        return rslt;
    }

    @RequestMapping("/report")
    @ResponseBody
    public Map<String, Object> report(@RequestBody List<Map<String,Object>> list, HttpServletRequest request){
       Map<String, Object> param = Util.parseWebParas(request);
        Map<String,Object> rslt = new HashMap<>();
        try {
            List<String> conditions = filterReportService.report(list);
            if (conditions != null && conditions.size()>0) {
                for (String condition : conditions) {
                    try {
                        reportCmdService.socket(condition+"REPORT_SOURCE_F");
                    } catch (Throwable e) {
                        e.printStackTrace();
                        throw new Exception("连接错误");
                    }

                }
            }
            rslt.put("RET_CODE","SUCCESS");
            rslt.put("RET_MSG","调用成功");
            return rslt;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更正信息出现异常!", e);
        }
        rslt.put("RET_CODE","FAIL");
        rslt.put("RET_MSG","调用失败");
        return rslt;
    }

}
