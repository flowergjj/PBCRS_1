package com.hkbank.pbcrs.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.DateUtils;
import com.hkbank.pbcrs.util.StringUtil;
import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.PbcrsReportConditionMapper;
import com.hkbank.pbcrs.mapper.PbcrsSysReportconfigMapper;
import com.hkbank.pbcrs.model.PbcrsReportCondition;
import com.hkbank.pbcrs.service.BalanceSheetService;
import com.hkbank.pbcrs.service.ReportCmdService;

@Controller
@RequestMapping("/ReportCmd")
public class ReportCmdController {

    @Autowired
    private ReportCmdService service;
    private static final Logger log = LogManager.getLogger(ReportCmdController.class);

    @Autowired
    PbcrsSysReportconfigMapper pbcrsSysReportconfigMapper;
    @Autowired
    PbcrsReportConditionMapper pbcrsReportConditionMapper;

    @ResponseBody
    @RequestMapping("/start")
    public String start(HttpServletRequest request) {
        Map<String, Object> param = Util.parseWebParas(request);
        String p = param.get("P").toString();
        log.info("ReportCmdController Receive Msg:" + p);
        if (p != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                String decodeStr = new String(decoder.decodeBuffer(p), "UTF-8");
                Map<String, String> reportParams = new HashMap<String, String>();
                String[] paramArray = decodeStr.replace("\n", "").split("&");
                for (String paramStr : paramArray) {
                    String[] paramItem = paramStr.split("=");
                    if (paramItem.length > 1) {
                        reportParams.put(paramItem[0], paramItem[1]);
                    }
                }
                reportParams.put("reportFlag","0");
                //???socket????????????controller???????????????????????????????????????socket??????????????????conditon?????????
                List<String> conditions = service.genFile(reportParams);
                if (conditions != null && conditions.size()>0) {
                    for (String condition : conditions) {
                        try {
                            service.socket(condition+"REPORT_SOURCE_P");
                        } catch (Throwable e) {
                            e.printStackTrace();
                            log.error("", e);
                            throw new Exception("????????????");
                        }
                    }
                    return "SUCCESS " + reportParams;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.info("ReportCmdController ERROR:" + e.getMessage());
                e.printStackTrace();
                if (e.getMessage().contains("????????????")) {
                    return "???????????????????????????,?????????????????????";
                }
                if (e.getMessage().contains("????????????????????????????????????")) {
                    return "???????????????????????????????????????";
                }
                return "??????????????????!??????????????????";
            }
        } else {
            return "????????????????????????!";
        }
        return null;
    }







}
