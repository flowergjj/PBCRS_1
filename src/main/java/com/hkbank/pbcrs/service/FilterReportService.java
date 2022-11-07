package com.hkbank.pbcrs.service;

import com.hkbank.pbcrs.mapper.FilterReportMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportConditionMapper;
import com.hkbank.pbcrs.model.FilterData;
import com.hkbank.pbcrs.model.PbcrsReportCondition;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterReportService {

    @Autowired
    private FilterReportMapper filterReportMapper;

    @Autowired
    private PbcrsReportConditionMapper pbcrsReportConditionMapper;

    public Map<String, Object> selectByPage(Map<String, Object> param) {
        List<Map<String,Object>> list;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        param = getStartAndEnd(param);
        int total = filterReportMapper.selectCount(param);
        if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
            list = new ArrayList();
        } else {
            // 查询列表
            list = filterReportMapper.selectList(param);
            if (list == null) {
                list = new ArrayList();
            }
        }
        resultMap.put("total", total);
        resultMap.put("rows", list);
        return resultMap;
    }

    public Map<String, Object> selectOne(Map<String, Object> param) {
        List<Map<String, Object>> maps = filterReportMapper.selectOne(param);
        return maps.get(0);
    }

    // 分页参数封装
    public Map<String, Object> getStartAndEnd(Map<String, Object> param) {
        int pageNo = MapUtils.getIntValue(param, "page");
        int pageSize = MapUtils.getIntValue(param, "rows");
        int skip = (pageNo - 1) * pageSize + 1;
        int endindex = skip + pageSize;
        param.put("endindex", endindex);
        param.put("startindex", skip);
        return param;
    }


    public int update(Map<String, Object> param) {
        List<Map<String, Object>> maps = filterReportMapper.selectOne(param);
        Map<String, Object> filterReport = maps.get(0);
        param.put("OLDENTCERTTYPE",filterReport.get("ENTCERTTYPE"));
        param.put("OLDENTCERTNUM",filterReport.get("ENTCERTNUM"));
        int baseCount = filterReportMapper.updateEnBasinfbBssgmt(param);
        int acctCount = filterReportMapper.updateEnacctBssgmt(param);
        int motCount = filterReportMapper.updateMotcltctrBssgmt(param);
        return baseCount+acctCount+motCount;
    }

    public List<String> report(List<Map<String,Object>> list) {

        Set<String> orgList = new HashSet<>(16);
        for (Map<String, Object> item : list) {
            String org_code = item.get("ORG_CODE").toString();
            orgList.add(org_code);
        }
        List<Map<String,Object>> params = new ArrayList<>(16);
        for (String orgCode : orgList) {
            Map<String,Object> info = new HashMap<>(16);
            info.put("ORG_CODE",orgCode);
            for (Map<String, Object> item : list) {
                info.put("INFRECTYPE",item.get("INFRECTYPE"));
                info.put("SOURCESYS",item.get("SOURCESYS"));
                String org_code = item.get("ORG_CODE").toString();
                String BSSGMTSEQNO =  info.get("BSSGMTSEQNO") == null?"": info.get("BSSGMTSEQNO").toString();
                //如果机构相同则合并业务号
                if (org_code.equals(orgCode)) {
                    info.put("BSSGMTSEQNO",BSSGMTSEQNO.equals("")?item.get("BSSGMTSEQNO").toString():BSSGMTSEQNO+","+item.get("BSSGMTSEQNO").toString()) ;
                }
            }
            params.add(info);
        }
        List<String> conditions = new ArrayList<>();
        for (Map<String, Object> param : params) {
            String id = UUID.randomUUID().toString();
            PbcrsReportCondition condition = new PbcrsReportCondition();
            condition.setConditionId(id);
            condition.setConditionSeqNo(param.get("BSSGMTSEQNO").toString());
            condition.setSgmtid(param.get("INFRECTYPE").toString());
            condition.setEtlDate(new Date());
            condition.setSysid(param.get("SOURCESYS").toString());
            //设置为前端调用类型
            condition.setReportType("2");
            //设置状态为未开始
            condition.setReportStaut("1");
            condition.setCreateTime(new Date());
            condition.setOrgCode(param.get("ORG_CODE").toString());
            //设置reportflag为2-过滤信息重新调用生成
            condition.setReportFlag("2");
            int count = pbcrsReportConditionMapper.insert(condition);
            //将插入的主键收集起来
            conditions.add(id);
        }
        return conditions;
    }
}
