package com.hkbank.pbcrs.dao.system;


import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrgDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;
	
	public Map<String, Object> getAllOrg(Object params, int pageNo, int pageSize) {
		return baseDaoImpl.pageQuery("Org.getAllOrgCounts", "Org.findAllOrg", params, pageNo, pageSize);
	}
	
	public int getOrgCountsById(Map<String,Object> orgdata){
		return baseDaoImpl.selectCount("Org.getOrgCountsById", orgdata);
	}
	
	public int createOrg(Map<String, Object> insertorg) {
		String sqlStr = "Org.createOrg";
		int i = baseDaoImpl.save(sqlStr, insertorg);
		return i;
	}
	
	public int createOrgLog(Map<String, Object> orgInfo){
		String seqnoStr = "Org.getOrgLogSeqNo";
		int no = Integer.parseInt( String
				.valueOf(baseDaoImpl.selectOne(seqnoStr)));
		orgInfo.put("NEXTVAL", no);
		String sqlStr = "Org.createOrgLog";
		int i = baseDaoImpl.save(sqlStr, orgInfo);
		return i;
	}
	
	public int modifyOrg(Map<String, Object> orgInfo) {
		String sqlStr = "Org.modifyOrg";
		int i = baseDaoImpl.update(sqlStr, orgInfo);
		return i; 
	}
	
	public Map<?,?> getOrgInfoById(Map<String,Object> orgInfo){
		return baseDaoImpl.selectOne("Org.getOrgInfoById", orgInfo);
	}
	
	public int deleteOrg(Map<String, Object> delOrgInfo) {
		String sqlStr = "Org.deleteOrg";
		int i = baseDaoImpl.save(sqlStr, delOrgInfo);
		return i;
	}
	
	public List<Map<String, Object>> getOrgTree(Map<String, Object> params) {
		String sqlStr = "Org.getOrgTree";
		return baseDaoImpl.selectList(sqlStr, params);
	}
	
	public int getOrgTreeCount(Map<String, Object> params) {
		String sqlStr = "Org.getOrgTreeCount";
		return baseDaoImpl.selectCount(sqlStr, params);
	}

}
