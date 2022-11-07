package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.MenuPowerDao;
import com.hkbank.pbcrs.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MenuPower {

	public MenuPower() {
	}

	@Autowired
	private MenuPowerDao menuPowerDao;

	@Autowired
	private MenuPowerDao loginLogDao;

	public List<Map<?, ?>> getSubMenu(String userid, String menuid) {
		List<Map<?, ?>> result = menuPowerDao.findSubMenu(userid, menuid);

		return result;
	}

	public List<SysMenu> getUserMenu(String userid) {
		List<SysMenu> result = menuPowerDao.findAllUserMenu(userid);

		return result;
	}
	
	
	public List<Map<?, ?>> getUserFunc(String userid) {
		List<Map<?, ?>> result = menuPowerDao.findAllUserFunc(userid);

		return result;
	}
	
	public List<Map<?, ?>> findUserRightFunc(Map<String, String> params) {
		List<Map<?, ?>> result = menuPowerDao.findUserRightFunc(params);

		return result;
	}
	
	

	public void createNewRole(String userid, String roleid, String rolename,
			List<String> powers) {

		String status = "1"; // 创建和修改均为有效状态
		menuPowerDao.saveRole(userid, roleid, rolename, status, powers);

	}

	public void deleteRole(String userid, String roleid) {
		this.menuPowerDao.deleteRole(userid, roleid);
	}

}
