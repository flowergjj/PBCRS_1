package com.hkbank.pbcrs.dao.system;

import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleGroupDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;
	
	public Map<String, Object> getAllRoleGroup(Object params, int pageNo, int pageSize) {
		return baseDaoImpl.pageQuery("RoleGroup.findAllRoleGroupCounts", "RoleGroup.findAllRoleGroup", params, pageNo, pageSize);
	}
	
	public List<Map<String,Object>> findRoleGroupStatusCode(){
		return baseDaoImpl.selectList("RoleGroup.findRoleGroupStatusCode");
	}
	
	public List<Map<String,Object>> findRoleList(){
		return baseDaoImpl.selectList("RoleGroup.findRoleList");
	}
	
	public String getGroupSeqNo(){
		Map<String,Object> map = baseDaoImpl.selectOne("RoleGroup.getGroupSeqNo");
		String seqNo = String.valueOf(map.get("NEXTVAL"));
		return seqNo;
	}
	
	public int saveRoleGroupInfo(Map<String, Object> roleGroupInfo) {
		String sqlStr = "RoleGroup.saveRoleGroupInfo";
		int i = baseDaoImpl.save(sqlStr, roleGroupInfo);
		return i;
	}
	
	public int saveRoleGroupInfoLog(Map<String, Object> roleGroupInfo) {
		String sqlStr = "RoleGroup.saveRoleGroupInfoLog";
		int i = baseDaoImpl.save(sqlStr, roleGroupInfo);
		return i;
	}
	
	public int saveRoleGroup(Map<String, Object> roleGroup) {
		String sqlStr = "RoleGroup.saveRoleGroup";
		int i = baseDaoImpl.save(sqlStr, roleGroup);
		return i;
	}
	
	public int saveRoleGroupLog(Map<String, Object> roleGroup) {
		String sqlStr = "RoleGroup.saveRoleGroupLog";
		int i = baseDaoImpl.save(sqlStr, roleGroup);
		return i;
	}
	
	public List<Map<String,Object>> findRoleListByGroupId(Map<String, Object> group){
		return baseDaoImpl.selectList("RoleGroup.findRoleListByGroupId",group);
	}
	
	public int updateRoleGroupInfo(Map<String, Object> roleGroup){
		int i = baseDaoImpl.update("RoleGroup.updateRoleGroupInfo", roleGroup);
		return i;
	}
	
    public int delRoleGroup(Map<String, Object> roleGroup){
    	int i = baseDaoImpl.delete("RoleGroup.deleteRoleGroup", roleGroup);
    	return i;
    } 
    
    public int deleteRoleGroupInfo(Map<String, Object> roleGroup){
    	int i = baseDaoImpl.delete("RoleGroup.deleteRoleGroupInfo", roleGroup);
    	return i;
    }
    
    public Map<String,Object> findRoleInfoByGroupId(Map<String, Object> roleGroup){
    	return baseDaoImpl.selectOne("RoleGroup.findRoleInfoByGroupId", roleGroup);
    }
}
