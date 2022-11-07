package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.LoginLogDao;
import com.hkbank.pbcrs.dao.system.RoleDao;
import com.hkbank.pbcrs.dao.system.RoleGroupDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

	private static Logger log = LogManager.getLogger(RoleService.class);

	@Autowired
	private RoleGroupDao roleGroupDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public Map<String, Object> findAllRole(Map<String, Object> params,
			int pageNo, int pageSize) {
		return roleDao.getAllRole(params, pageNo, pageSize);
	}

	public int getAllRoleCounts(Map<String, Object> querydata) {
		return roleDao.getAllRoleCounts(querydata);
	}

	public int getRoleCountsById(Map<String, Object> insertdata) {
		return roleDao.getRoleCountsById(insertdata);
	}

	public String getRoleLogSeqNo() {
		return roleDao.getRoleLogSeqNo();
	}

	public int findUserRoleCounts(Map<String, Object> RoleInfo) {
		return roleDao.findUserRoleCounts(RoleInfo);
	}

	// 角色新增保存
	public int saveRole(Map<String, Object> insertdata) throws Exception {
		// 保存角色信息
		int rltint = roleDao.createRole(insertdata);
		if (0 < rltint) {
			// 保存角色日志信息
			// int rolelog = roleDao.saveRoleLog(insertdata);
			// int createpower = 0, powerlog = 0;
			if ("".equals(insertdata.get("powerList").toString())) {
				// createpower = 1;
			} else {
				String[] powerList = insertdata.get("powerList").toString()
						.split(",");
				for (int i = 0; i < powerList.length; i++) {
					insertdata.put("powerID", powerList[i]);
					// 保存角色权限信息
					// createpower +=
					roleDao.createRolePower(insertdata);
					// 保存角色权限日志信息
					// powerlog +=
					insertdata.put("seqNo", insertdata.get("roleID").toString()+insertdata.get("powerID").toString());
					roleDao.saveRolePowerLog(insertdata);
				}
			}
		}
		return rltint;
	}

	// 角色修改保存
	public int updateRoleInfo(Map<String, Object> insertdata) {
		// int delint =
		roleDao.delPower(insertdata);
		// int svint = 0;
		int rltint = 0;
		// 保存更新后的角色表及角色日志表
		rltint = roleDao.modifyRole(insertdata);
		// svint =
		roleDao.saveRoleLog(insertdata);
		// 保存新的角色日志信息
		// int createpower = 0, powerlog = 0;
		String seqNo=insertdata.get("seqNo").toString();
		if ("".equals(insertdata.get("powerList").toString())) {
			// createpower = 1;
		} else {
			String[] powerList = insertdata.get("powerList").toString()
					.split(",");
			for (int i = 0; i < powerList.length; i++) {
				insertdata.put("powerID", powerList[i]);
				// 创建新的角色权限信息
				// createpower +=
				roleDao.createRolePower(insertdata);
				// 创建新的角色权限日志信息
				// powerlog +=
				/*String logseqNo=seqNo+insertdata.get("roleID").toString()+insertdata.get("powerID").toString();
				insertdata.put("seqNo", logseqNo);
				roleDao.saveRolePowerLog(insertdata);*/
			}
		}
		return rltint;
	}

	@SuppressWarnings("unchecked")
	public int deleteRole(Map<String, Object> delRoleInfo) {
		int rt = 0;
		Map<String, Object> map = (Map<String, Object>) roleDao
				.getRoleInfoById(delRoleInfo);
		map.put("updateUser", delRoleInfo.get("updateUser"));
		map.put("updateDate", new Date(System.currentTimeMillis()));
		map.put("delFlag", "1");
		map.put("seqNo", delRoleInfo.get("seqNo"));
		String seqNo = (String)delRoleInfo.get("seqNo");
		Map<String, Object> pl = new HashMap<String, Object>();
		pl.put("roleID", delRoleInfo.get("roleID"));
		String str = roleDao.getPowerListByRoleId(pl).toString();
		String[] p = str.substring(1, str.length() - 1).replace(" ", "")
				.split(",");

		int i = roleDao.saveRoleLog(map);
		if (0 != i) {
			int j = roleDao.deleteRole(delRoleInfo);
			j += roleDao.deleteRolePower(map);
			if (p.length > 0) {
				for (int k = 0; k < p.length; k++) {
					map.put("powerID", p[k]);
					String logseqNo=seqNo+map.get("roleID").toString()+map.get("powerID").toString();
					map.put("seqNo", logseqNo);
					roleDao.saveRolePowerLog(map);
				}
			}

			rt = j;
		}
		return rt;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getRoleInfoById(Map<String, Object> roleInfo) {
		return (Map<String, Object>) roleDao.getRoleInfoById(roleInfo);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getPowerByRoleId(
			Map<String, Object> powerInfo) {
		log.entry("getPowerByRoleId()", powerInfo);

		List<Map<?, ?>> menulist = roleDao.getPowerByRoleId(powerInfo);

		List<Map<String, Object>> menurslt = new ArrayList<Map<String, Object>>();

		// 根据页面控件要求设置所需参数：
		// id
		// text
		// children
		// checked
		// 遇到任何不满足要求的记录全部跳过

		if (menulist != null && (!menulist.isEmpty())) {

			for (Map<?, ?> menu_node : menulist) {
				int curr_menu_level = 0;
				Map<String, Object> last_menu_node = null;
				List<Map<String, Object>> submenu = menurslt;

				try {
					curr_menu_level = Integer.parseInt(String.valueOf(menu_node
							.get("menu_level")));
				} catch (Throwable t) {
					// menu_level配置错误或者为空时，跳过当前记录
					continue;
				}

				for (int i = 0; i < curr_menu_level + 1; i++) {

					// 在到达当前菜单节点所属级别之前，轮询获取子节点
					if (i < curr_menu_level) {

						if (submenu != null && (!submenu.isEmpty())) {
							last_menu_node = submenu.get(submenu.size() - 1);
							last_menu_node.put("checked", null);
							submenu = (List<Map<String, Object>>) last_menu_node
									.get("children");
						} else {
							// 如果在抵达目标级别之前，出现了空集合，说明配置存在问题，跳过当前记录
							break;
						}

					} else {
						// 抵达目标节点，处理
						Map<String, Object> cache_menu_node = new HashMap<String, Object>();

						cache_menu_node.put("id",
								String.valueOf(menu_node.get("powerID")));
						cache_menu_node.put("text",
								String.valueOf(menu_node.get("powerName")));

						if (menu_node.get("roleID") != null) {
							cache_menu_node.put("checked", "true");
						}

						if (submenu == null) {
							submenu = new ArrayList<Map<String, Object>>();
							last_menu_node.put("children", submenu);
						}
						submenu.add(cache_menu_node);
						// 处理完毕以后退出
						break;
					}
				}
			}
		}

		log.debug("menurslt=={}", menurslt);
		return menurslt;
	}

	public List<Map<?, ?>> getRoleLevel() {
		return roleDao.getRoleLevel();
	}

}
