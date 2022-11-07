package com.hkbank.pbcrs.service;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.PbcrsReportTraceMapper;
import com.hkbank.pbcrs.model.PbcrsReportTrace;

@Service
public class PbcrsReportTraceService {
	@Autowired
	PbcrsReportTraceMapper pbcrsReportTraceMapper;
	
	public void insertTrace(Object oldValue,Map<String,Object> newparams,String sgmtName,String modifySeqno) throws Exception{
		Map<String,Object> newmap=Util.map2Lower(newparams);
		
		Map<String,Object> oldmap=Util.map2Lower(Util.Obj2Map(oldValue));
		
		String userid = newmap.get("userid").toString();
		for(Map.Entry<String, Object> entry:oldmap.entrySet()){
			
			if(newmap.containsKey(entry.getKey())){
				Object object = newmap.get(entry.getKey());
				
				if(entry.getValue()!=null && object!=null && !object.toString().equals("")){
				if(!object.equals(entry.getValue())){
					PbcrsReportTrace temptrace = new PbcrsReportTrace();
					temptrace.setModifyuser(userid);
					temptrace.setModifydate(new Date());
					temptrace.setModifysgmt(sgmtName);
					temptrace.setModifycol(entry.getKey());
					temptrace.setBeforevalue(entry.getValue()+"");
					temptrace.setAftervalue(object+"");
					temptrace.setModifySeqno(modifySeqno);
					pbcrsReportTraceMapper.insert(temptrace);
				}
			}
			}
		}
	}
}
