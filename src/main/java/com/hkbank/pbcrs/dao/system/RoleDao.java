package com.hkbank.pbcrs.dao.system;

import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<?, ?>> baseDaoImpl;

	public int createRole(Map<String, Object> insertrole) {
		String sqlStr = "Power.createRole";
		int i = baseDaoImpl.save(sqlStr, insertrole);
		return i;
	}
	
	public int createRolePower(Map<String, Object> insertrole) {
		String sqlStr = "Power.createRolePower";
		int i = baseDaoImpl.save(sqlStr, insertrole);
		return i;
	}
	
	public Map<String, Object> getAllRole(Object params, int pageNo, int pageSize) {
		return baseDaoImpl.pageQuery("Power.getAllRoleCounts", "Power.findRoleList", params, pageNo, pageSize);
	}
	
	public int getAllRoleCounts(Map<String,Object> querydata){
		return baseDaoImpl.selectCount("Power.getAllRoleCounts", querydata);
	}
	
	public int getRoleCountsById(Map<String,Object> roledata){
		return baseDaoImpl.selectCount("Power.getRoleCountsById", roledata);
	}
	
	public Map<?,?> getRoleInfoById(Map<String,Object> roleInfo){
		return baseDaoImpl.selectOne("Power.getRoleInfoById", roleInfo);
	}
	
	public List<Map<?,?>> getPowerByRoleId(Map<String,Object> roleInfo){
		return baseDaoImpl.selectList("Power.getPowerByRoleId", roleInfo);
	}
	
	public List<Map<?,?>> getPowerListByRoleId(Map<String,Object> roleInfo){
		return baseDaoImpl.selectList("Power.getPowerListByRoleId", roleInfo);
	}
	
	public int modifyRole(Map<String, Object> newRoleInfo) {
		String sqlStr = "Power.modifyRole";
		int i = baseDaoImpl.update(sqlStr, newRoleInfo);
		return i; 
	}
	
	public int deleteRole(Map<String, Object> delRoleInfo) {
		String sqlStr = "Power.deleteRole";
		int i = baseDaoImpl.save(sqlStr, delRoleInfo);
		return i;
	}
	
	public int delPower(Map<String, Object> powerInfo) {
		String sqlStr = "Power.clearRolePower";
		int i = baseDaoImpl.save(sqlStr, powerInfo);
		return i;
	}
	
	public int deleteRolePower(Map<String, Object> delRolePower) {
		String sqlStr = "Power.clearRolePower";
		int i = baseDaoImpl.save(sqlStr, delRolePower);
		return i;
	}
	
	public String getRoleLogSeqNo(){
		String sqlStr = "Power.obtainSEQ_PBCRS_SYS_ROLE_INFO_LOG";
		String no = String.valueOf(baseDaoImpl.selectOne(sqlStr).get("NEXTVAL"));
		return no;
	}
	
	public int saveRoleLog(Map<String, Object> roleInfo){
		String sqlStr = "Power.createRoleLog";
		int i = baseDaoImpl.save(sqlStr, roleInfo);
		return i;
	}
	
	public int saveRolePowerLog(Map<String, Object> roleInfo){
		String sqlStr = "Power.createRolePowerLog";
		int i = baseDaoImpl.save(sqlStr, roleInfo);
		return i;
	}
	
	public List<Map<?,?>> getRoleLevel(){
		return baseDaoImpl.selectList("Power.getRoleLevel");
	}
	public int findUserRoleCounts(Map<String, Object> RoleInfo) {
		String sqlStr = "Power.findUserRoleCounts";
		int i = baseDaoImpl.selectCount(sqlStr, RoleInfo);
		return i;
	}
}
