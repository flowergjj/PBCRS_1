package com.hkbank.pbcrs.dao.system;


import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuPowerDao {

	@Autowired
	private IBaseDao<Map<?, ?>> baseDaoImpl;

	public List<Map<?, ?>> findSubMenu(String userid, String menuid) {
		String sqlStr = "Power.findSubMenu";
		Map<String, String> params = new HashMap<String, String>();
		params.put("USERID", userid);
		params.put("MENU_ID", menuid);
		List<Map<?, ?>> result = baseDaoImpl.selectList(sqlStr, params);
		return result;
	}

	public List<SysMenu> findAllUserMenu(String userid) {
		String sqlStr = "Power.findAllSubMenu";
		Map<String, String> params = new HashMap<String, String>();
		params.put("USERID", userid);
		params.put("MENU_ID","10");
		List<SysMenu> result = baseDaoImpl.selectMenuList(sqlStr, params);
		return result;
	}
	
	public List<Map<?, ?>> findAllUserFunc(String userid) {
		String sqlStr = "Power.findAllUserFunc";
		Map<String, String> params = new HashMap<String, String>();
		params.put("USERID", userid);
		List<Map<?, ?>> result = baseDaoImpl.selectList(sqlStr, params);
		return result;
	}
	
	
	public List<Map<?, ?>> findUserRightFunc(Map<String, String> params) {
		String sqlStr = "Power.findUserRightFunc";

		List<Map<?, ?>> result = baseDaoImpl.selectList(sqlStr, params);
		return result;
	}
	
	
	
	
	

	// public Map<String, Object> findRole(String roleid) {
	// String sqlStr = "Power.findRole";
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("ROLEID", roleid);
	// Map<String, Object> role = baseDaoImpl.selectOne(sqlStr, params);
	//
	// sqlStr = "Power.findRolePower";
	// List<?> powers = baseDaoImpl.selectList(sqlStr, params);
	// role.put("POWERS", powers);
	//
	// return role;
	// }
	//
	// public List<Map<String, Object>> findRoleList(String roleid, String
	// rolename) {
	// String sqlStr = "Power.findRoleList";
	// Map<String, String> params = new HashMap<String, String>();
	// params.put("ROLEID", roleid);
	// params.put("ROLENAME", rolename);
	// List<Map<String, Object>> result = baseDaoImpl.selectList(sqlStr,
	// params);
	// return result;
	// }

	public void saveRole(String userid, String roleid, String rolename,
			String status, List<String> powers) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ROLEID", roleid);
		params.put("ROLENAME", rolename);
		params.put("STATUS", status);
		params.put("UPDATEUSER", userid);
		params.put("UPDATEDATE", new Date(System.currentTimeMillis()));
		// 保存角色
		String sqlStr = "Power.saveRole";
		this.baseDaoImpl.save(sqlStr, params);
		// 记录角色操作日志
		sqlStr = "Power.obtainSEQ_PBCRS_SYS_ROLE_INFO_LOG";
		Map<?, ?> result = baseDaoImpl.selectOne(sqlStr, null);
		String nextval = String.valueOf(result.get("NEXTVAL"));
		params.put("SERIALNO", nextval);
		params.put("DELFLAG", "0");
		sqlStr = "Power.createRoleLog";
		baseDaoImpl.save(sqlStr, params);
		// 保存角色权限
		// 记录角色权限操作日志

		// a) 清理原权限
		sqlStr = "Power.clearRolePower";
		this.baseDaoImpl.delete(sqlStr, params);
		// b) 新增新权限
		if (powers != null && powers.size() > 0) {
			for (String powerid : powers) {
				params.put("POWERID", powerid);

				sqlStr = "Power.createRolePower";
				this.baseDaoImpl.save(sqlStr, params);

				sqlStr = "Power.createRolePowerLog";
				this.baseDaoImpl.save(sqlStr, params);
			}
		}

	}

	public void deleteRole(String userid, String roleid) {

		Map<String, Object> params = new HashMap<String, Object>();

		String sqlStr = "Power.findRole";
		Map<?, ?> role = this.baseDaoImpl.selectOne(sqlStr, params);
		params.put("ROLEID", roleid);
		params.put("ROLENAME", role.get("ROLENAME"));
		params.put("STATUS", role.get("STATUS"));
		params.put("UPDATEUSER", userid);
		params.put("UPDATEDATE", new Date(System.currentTimeMillis()));
		// 删除角色
		sqlStr = "Power.clearRole";
		this.baseDaoImpl.delete(sqlStr, params);
		// 记录角色操作日志
		sqlStr = "Power.obtainSEQ_PBCRS_SYS_ROLE_INFO_LOG";
		Map<?, ?> result = baseDaoImpl.selectOne(sqlStr, null);
		String nextval = String.valueOf(result.get("NEXTVAL"));
		params.put("SERIALNO", nextval);
		params.put("DELFLAG", "1");
		sqlStr = "Power.createRoleLog";
		baseDaoImpl.save(sqlStr, params);
		// 清除角色权限
		sqlStr = "Power.clearRolePower";
		this.baseDaoImpl.delete(sqlStr, params);

	}
}