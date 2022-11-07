package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.OrgDao;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrgSrevice {

	private static final Logger log = LogManager.getLogger(OrgSrevice.class);
	
	@Autowired
	private OrgDao orgDao;

	public Map<String, Object> findAllOrg(Map<String, Object> params, int pageNo, int pageSize) {
		return orgDao.getAllOrg(params, pageNo, pageSize);
	}
	
	public int getOrgCountsById(Map<String,Object> insertdata){
		return orgDao.getOrgCountsById(insertdata);
	}
	
	public int saveOrg(Map<String,Object> insertdata){
		return orgDao.createOrg(insertdata);
	}
	
	public int saveOrgLog(Map<String,Object> insertdata){
		return orgDao.createOrgLog(insertdata);
	}
	
	public int updateOrgInfo(Map<String,Object> insertdata){
		return orgDao.modifyOrg(insertdata);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> getOrgInfoById(Map<String, Object> orgInfo){
		return (Map<String, Object>) orgDao.getOrgInfoById(orgInfo);
	}
	
	@SuppressWarnings("unchecked")
	public int deleteOrg(Map<String, Object> delOrgInfo) {
		int rt = 0;
		Map<String,Object> map = (Map<String, Object>) orgDao.getOrgInfoById(delOrgInfo);
		map.put("updateUser", delOrgInfo.get("updateUser"));
		map.put("updateDate", new Date(System.currentTimeMillis()));
		map.put("delFlag", "1");
		int i = orgDao.createOrgLog(map);
		if(0!=i){
			int j = orgDao.deleteOrg(delOrgInfo);
			rt=j;
		}
		return rt;
	}
	
	public String getOrgTree(Map<String, Object> params) throws Exception {
		List<Map<String, Object>> orgList = orgDao.getOrgTree(params);
		
		for(Map<String, Object> org : orgList) {
			int orgChildCount = orgDao.getOrgTreeCount(org);
			
			if(orgChildCount > 0) {
				org.put("state", "closed");
			} else {
				org.put("state", "open");
			}
		}
		
		JSONArray jsonObj = JSONArray.fromObject(orgList);
		log.debug("格式化后的OrgTree={}", jsonObj.toString());
		
		return jsonObj.toString();
	}


}
