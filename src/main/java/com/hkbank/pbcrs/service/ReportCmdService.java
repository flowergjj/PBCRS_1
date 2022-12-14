package com.hkbank.pbcrs.service;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import com.hkbank.pbcrs.util.Constants;
import com.hkbank.pbcrs.util.DateUtils;
import com.hkbank.pbcrs.util.StringUtil;
import com.hkbank.pbcrs.mapper.PbcrsReportBalancesheetbssgmMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportConditionMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInfalmmbsinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportInidefctinfMapper;
import com.hkbank.pbcrs.mapper.PbcrsSysReportconfigMapper;
import com.hkbank.pbcrs.model.PbcrsReportActlbltyinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportCondition;
import com.hkbank.pbcrs.model.PbcrsReportEnaccspetrssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbsinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctbssgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctcredsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnacctinf;
import com.hkbank.pbcrs.model.PbcrsReportEncagoftrdinf;
import com.hkbank.pbcrs.model.PbcrsReportEnoricreinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepinfsgmt;
import com.hkbank.pbcrs.model.PbcrsReportEnrltrepymtinf;
import com.hkbank.pbcrs.model.PbcrsSysReportconfig;
import com.hkbank.pbcrs.controller.ReportCmdController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ReportCmdService {
    //@Autowired
    //ReportEnacctinfXmlGen reportEnacctinfXmlGen;

    @Autowired
    PbcrsSysReportconfigMapper pbcrsSysReportconfigMapper;
    @Autowired
    PbcrsReportConditionMapper pbcrsReportConditionMapper;
    @Autowired
    BalanceSheetService balanceService;
    @Autowired
    CashFlowsService cashService;
    @Autowired
    EnAcctInfService enAcctService;
    @Autowired
    EnBasInfBsSgmtService enBaseService;
    @Autowired
    EnCtrctInfService enCtrctService;
    @Autowired
    EnSecAcctInfService enSecService;
    @Autowired
    InAcctInfService inAcctService;
    @Autowired
    InBasInfoService inBaseService;
    @Autowired
    IncAndExpStaService incAndService;
    @Autowired
    IncomesprofitapprService inComeService;
    @Autowired
    InCtrctInfService inCtrctService;
    @Autowired
    InsBalSheService inBalSheService;
    @Autowired
    MotCltCtrInfService motcltService;
    @Autowired
    PbcrsReportInfalmmbsinfMapper pbcrsReportInfalmmbsinfMapper;
    @Autowired
    PbcrsReportInidefctinfMapper pbcrsReportInidefctinfMapper;


    private static final Logger log = LogManager
            .getLogger(ReportCmdService.class);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)//???????????????????????????
    public List<String> genFile(Map<String, String> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        String etl_dateStr = params.get("ETLDATE").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        log.info("ReportCmdService etl_dateStr:" + etl_dateStr);
        Date etl_date = sdf.parse(etl_dateStr);
        if (!params.containsKey("SGMTID")) {
            throw new Exception("????????????????????????");
        }
        String sgmtid = params.get("SGMTID").toString().trim();
        String reportFlag = params.get("reportFlag").toString();
        log.info("ReportCmdService sgmtid:" + sgmtid);
        PbcrsReportCondition condition = new PbcrsReportCondition();
        condition.setReportType(Constants.REPORT_SOURCE_P);
        condition.setReportStaut(Constants.REPORT_STATE_S);
        condition.setEtlDate(etl_date);
        condition.setSgmtid(sgmtid);
        condition.setReportFlag(reportFlag);
        condition.setWebProcId(params.get("webProcId"));
        if (params.containsKey("SYSID")) {// ?????????????????????????????????????????????????????????????????????partition
            String sysid = params.get("SYSID").toString();
            condition.setSysid(sysid);
        }
        condition.setCreateTime(DateUtils.getCurrDateTime());
        Map<String, Object> orgPar = new HashMap<String, Object>();
        orgPar.put("etlDate", condition.getEtlDate());
        orgPar.put("sysId", condition.getSysid());
        orgPar.put("sgmtId", condition.getSgmtid());
        List<Map<String, Object>> tableInfoList = pbcrsReportConditionMapper.getTableInfo(condition.getSgmtid());
        String tableName = null;
        if (tableInfoList.size() > 0) {
            tableName = tableInfoList.get(0).get("TABLENAME").toString();
        } else {
            log.info("???????????????PBCRS_SYS_REPORTCONFIG??????");
            throw new Exception("?????????????????????!");
        }
        if (condition.getSysid() != null && condition.getSysid().equals("IND")) {
            orgPar.put("sysId", "PLN");
        } else if (condition.getSysid() != null && condition.getSysid().equals("ENT")) {
            orgPar.put("sysId", "CRMS");
        }
        orgPar.put("tableName", tableName);
        List<Map<String, Object>> orgList = pbcrsReportConditionMapper.getOrgInfo(orgPar);
        if (orgList == null || orgList.size() == 0) {
            String reportInfo = tableInfoList.get(0).get("REPORTNAME").toString();
            //???????????????????????????????????????--??????????????????
            throw new NullPointerException(reportInfo);
        }
        List<String> conditions = new ArrayList<>();
        for (Map<String, Object> orgMap : orgList) {
            String id = UUID.randomUUID().toString();
            condition.setConditionId(id);
            condition.setOrgCode(orgMap.get("ORGCODE").toString());
            int count = pbcrsReportConditionMapper.insert(condition);
            log.info("ReportCmdService insert(condition)");
            if (count > 0) {
                conditions.add(id);
            }
        }
        return conditions;

    }

    public List<String> reportGetFile(Map<String, Object> map) throws Exception {
        // ??????????????????????????????????????????
        String reportId = map.get("reportId").toString();
        String etl_dateStr = map.get("etl_date").toString();
        String ids = map.get("id").toString();
        String reportFlag= map.get("reportFlag").toString();
        //??????
        String type = map.get("type").toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date etl_date = sdf.parse(etl_dateStr);
        PbcrsReportCondition condition = new PbcrsReportCondition();
        condition.setReportType(Constants.REPORT_SOURCE_P);
        condition.setReportStaut(Constants.REPORT_STATE_S);
        condition.setEtlDate(etl_date);
        condition.setSgmtid(reportId);
        condition.setReportFlag(reportFlag);
        condition.setWebProcId(map.get("webProcId").toString());
        // ?????????????????????????????????????????????????????????????????????partition
        if (map.containsKey("SYSID") && !StringUtil.isEmpty(map.get("SYSID").toString())) {
            String sysid = map.get("SYSID").toString();
            condition.setSysid(sysid);
        }
        if (type.equals("updSub")) {
            //??????????????????????????????
            condition.setConditionSeqNo(ids);
        } else {
            //?????????????????????????????????????????????????????????etl_date??????reportflag=0
            updReportFlag(reportId, etl_date, map.get("SYSID").toString());

        }
        Map<String, Object> orgPar = new HashMap<String, Object>(16);
        orgPar.put("etlDate", condition.getEtlDate());
        orgPar.put("sysId", condition.getSysid());
        orgPar.put("sgmtId", condition.getSgmtid());
        List<Map<String, Object>> tableInfoList = pbcrsReportConditionMapper.getTableInfo(condition.getSgmtid());
        String tableName = null;
        String tableSeqno = null;
        if (tableInfoList.size() > 0 && tableInfoList.get(0).get("TABLENAME") != null && tableInfoList.get(0).get("TABLESEQNO") != null) {
            tableName = tableInfoList.get(0).get("TABLENAME").toString();
            tableSeqno = tableInfoList.get(0).get("TABLESEQNO").toString();
        } else {
            log.info("???????????????PBCRS_SYS_REPORTCONFIG??????");
            throw new Exception("?????????????????????!");
        }
        if (condition.getSysid() != null && condition.getSysid().equals("IND")) {
            orgPar.put("sysId", "PLN");
        } else if (condition.getSysid() != null && condition.getSysid().equals("ENT")) {
            orgPar.put("sysId", "CRMS");
        }
        orgPar.put("tableName", tableName);
        orgPar.put("tableSeqno", tableSeqno);
        List<Map<String, Object>> orgList = null;
        if (condition.getConditionSeqNo() != null && !condition.getConditionSeqNo().equals("")) {
            orgPar.put("seqNoS", condition.getConditionSeqNo());
            orgList = pbcrsReportConditionMapper.getOrgInfoBySeqno(orgPar);
        } else {
            orgList = pbcrsReportConditionMapper.getOrgInfo(orgPar);
        }
        if (orgList == null || orgList.size() == 0) {
            throw new Exception("????????????????????????????????????");
        }
        List<String> conditions = new ArrayList<>();
        for (Map<String, Object> orgMap : orgList) {
            //????????????????????????????????????????????????????????????????????????(???????????????????????????)
            if (orgMap.get("SEQNOS") != null) {
                condition.setConditionSeqNo(orgMap.get("SEQNOS").toString());
            }
            condition.setOrgCode(orgMap.get("ORGCODE").toString());
            String id = UUID.randomUUID().toString();
            condition.setConditionId(id);
            int count = pbcrsReportConditionMapper.insert(condition);
            if (count > 0) {
                conditions.add(id);
            }

        }
        return conditions;

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void socket(String reportTypeAndReport) throws Throwable {
        Properties pbcrsReportCfg = Resources
                .getResourceAsProperties("config/common/report.properties");
        String url = pbcrsReportCfg.getProperty("socket.url");
        int port = Integer.parseInt(pbcrsReportCfg.getProperty("socket.port"));
        log.info("ReportCmdService socket url???" + url + " port:" + port);
        Socket s = new Socket(url, port);

        OutputStream out = s.getOutputStream();
        out.write(reportTypeAndReport.getBytes("UTF-8"));
        out.flush();
        out.close();
        s.close();

    }

    public void updReportFlag(String reportId, Date etl_date, String sysId) throws Exception {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("etl_Date", etl_date);
        obj.put("flagRes", "0");
        obj.put("REPORTFLAG", "1");
        if (reportId.equals("110")) {
            inBaseService.updateByMap(obj);
        } else if (reportId.equals("120")) {
            pbcrsReportInfalmmbsinfMapper.updateByMap(obj);
        } else if (reportId.equals("130")) {
            pbcrsReportInidefctinfMapper.updateByMap(obj);
        } else if (reportId.equals("210")) {
            inAcctService.updateByMap(obj);
        } else if (reportId.equals("220")) {
            inCtrctService.updateByMap(obj);
        } else if (reportId.equals("310")) {
            enBaseService.updateByMap(obj);
        } else if (reportId.equals("410")) {
            enAcctService.updateByMap(obj);
        } else if (reportId.equals("420")) {
            enCtrctService.updateByMap(obj);
        } else if (reportId.equals("440")) {
            enSecService.updateByMap(obj);
        } else if (reportId.equals("610")) {
            balanceService.updateByMap(obj);
        } else if (reportId.equals("620")) {
            inComeService.updateByMap(obj);
        } else if (reportId.equals("630")) {
            cashService.updateByMap(obj);
        } else if (reportId.equals("640")) {
            inBalSheService.updateByMap(obj);
        } else if (reportId.equals("650")) {
            incAndService.updateByMap(obj);
        } else if (reportId.equals("510")) {
            obj.put("sysid", sysId);
            motcltService.updateByMap(obj);
        }
    }

}
