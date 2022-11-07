package com.hkbank.pbcrs.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.mapper.PbcrsReportInspcevtdscinfMapper;
import com.hkbank.pbcrs.model.PbcrsReportInspcevtdscinf;

@Service
public class InSpcInfService {
  @Autowired
  private PbcrsReportInspcevtdscinfMapper pbcrsReportInspcevtdscinfMapper;
  
  public Map<String,Object> selectPageList(Map<String,Object> params){
	    List<PbcrsReportInspcevtdscinf> list = null;
		params = getStartAndEnd(params);
		// 查询总数
		int total = pbcrsReportInspcevtdscinfMapper.selectPageCount(params);
		// 判断total有没有值，或者total小于要查询开始的index
		if (total <= 0 || total < MapUtils.getInteger(params, "startindex")) {
			list = new ArrayList<PbcrsReportInspcevtdscinf>();
		} else {
			// 查询列表
			list = pbcrsReportInspcevtdscinfMapper.selectPageList(params);
			if (list == null) {
				list = new ArrayList<PbcrsReportInspcevtdscinf>();
			}
		}
	  Map<String,Object> rslt = new HashMap<String, Object>();
	  rslt.put("total", total);
	  rslt.put("rows", list);
	  return rslt;
  }
  
  public int updateInfo(Map<String,Object> params) throws ParseException{
	  PbcrsReportInspcevtdscinf inSpcInf = new PbcrsReportInspcevtdscinf();
	  inSpcInf.setInSpcEvtDscInfSeqNo(params.get("inSpcEvtDscInfSeqNo").toString());
	  inSpcInf.setReportFlag(params.get("reportFlag").toString());
	  inSpcInf.setSourceSys(params.get("sourceSys").toString());
	  inSpcInf.setInfRecType(params.get("infRecType").toString());
	  inSpcInf.setEtl_Date(DateUtils.parseDate(params.get("rptDate").toString(), "yyyy-MM-dd"));
	  inSpcInf.setMonth(params.get("month").toString());
	  inSpcInf.setOpetnType(params.get("opetnType").toString());
	  inSpcInf.setRptDate(params.get("rptDate").toString());
	  inSpcInf.setAcctCode(params.get("acctCode").toString());
	  inSpcInf.setFlag(params.get("flag").toString());
	  int updateRows = pbcrsReportInspcevtdscinfMapper.updateByPrimaryKey(inSpcInf);
	  return updateRows;
  }
  
  
  public int deleteInfo(Map<String,Object> params){
	  String keys = params.get("keys").toString();
	  String[] keyArr = keys.split(";");
	  int deleteRows=1 ;
	  for (String key : keyArr) {
		  deleteRows = pbcrsReportInspcevtdscinfMapper.deleteByPrimaryKey(key);
	  }
	 
	  return deleteRows;
  }
  
  public int insertInfo(Map<String,Object> params) throws ParseException{
	  PbcrsReportInspcevtdscinf inSpcInf = new PbcrsReportInspcevtdscinf();
	  inSpcInf.setInSpcEvtDscInfSeqNo(params.get("acctCode")+params.get("rptDate").toString().replace("-", ""));
	  inSpcInf.setInfRecType("215");
	  
	  String orgCode = pbcrsReportInspcevtdscinfMapper.selectOrgCode();
	  
	  String acctCode =params.get("acctCode").toString();
	  if(StringUtils.isEmpty(acctCode)){
		  acctCode ="D10025210H0005";
	  }
	  boolean startsWith = acctCode.startsWith(orgCode);
	  if(startsWith){
		  inSpcInf.setAcctCode(acctCode);
	  }else{
		  inSpcInf.setAcctCode(orgCode+acctCode);
	  }
	  inSpcInf.setOpetnType(params.get("opetnType").toString());
	  inSpcInf.setMonth(params.get("month").toString());
	  inSpcInf.setFlag(params.get("flag").toString());
	  inSpcInf.setRptDate(params.get("rptDate").toString());
	  inSpcInf.setEtl_Date(DateUtils.parseDate(params.get("rptDate").toString(), "yyyy-MM-dd"));
	  inSpcInf.setSourceSys(params.get("sourceSys").toString());
	  inSpcInf.setReportFlag("0");
	  int insertRows = pbcrsReportInspcevtdscinfMapper.insert(inSpcInf);
	  return insertRows;
  }
//分页参数封装
	public Map<String, Object> getStartAndEnd(Map<String, Object> param) {
		int pageNo = MapUtils.getIntValue(param, "page");
		int pageSize = MapUtils.getIntValue(param, "rows");
		int skip = (pageNo - 1) * pageSize + 1;
		int endindex = skip + pageSize;
		param.put("endindex", endindex);
		param.put("startindex", skip);
		return param;
	}
}
