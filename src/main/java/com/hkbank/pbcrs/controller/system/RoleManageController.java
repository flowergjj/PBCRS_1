package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/roleManage")
public class RoleManageController {

	private Logger log = LogManager.getLogger(RoleManageController.class);

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/roleList")
	@ResponseBody
	public Map<String, Object> getRoleList(HttpServletRequest req,
			HttpServletResponse response) {
		// String page = req.getParameter("page");
		// String rows = req.getParameter("rows");
		String roleID = req.getParameter("queryRoleId");
		String roleName = req.getParameter("queryRoleName");
		// String status = req.getParameter("queryStatus");

		// Map<String,Object> map = new HashMap<String,Object>();
		Map<String, Object> querydata = new HashMap<String, Object>();
		querydata.put("roleID", roleID);
		querydata.put("roleName", roleName);
		// querydata.put("status", status);
		// map.put("total", roleService.getAllRoleCounts(querydata));
		// map.put("rows", roleService.findAllRole(page,rows,querydata));

		int pageNo = Integer.parseInt(req.getParameter("page"));
		int pageSize = Integer.parseInt(req.getParameter("rows"));

		// Map<String, Object> params = Util.parseWebParas(req);
		return roleService.findAllRole(querydata, pageNo, pageSize);

		// return map;
	}
	
	private String handlepowerList(String powerList){
		String[] powers = powerList.split(",");
		HashMap<String,String> powerMap = new HashMap<String,String>();
		for (int i = 0; i < powers.length; i++) {
			if(!powerMap.containsKey(powers[i]))
				powerMap.put(powers[i], powers[i]);
		}
		HashMap<String,String> addpowerMap = new HashMap<String,String>();
		for(String key:powerMap.keySet()){
			if(key.length()>3){
				String pkey = key.substring(0, 3);
				if(!powerMap.containsKey(pkey))
				{
					if(!addpowerMap.containsKey(pkey))
						addpowerMap.put(pkey, pkey);
				}
					
			}
		}
		for(String key:addpowerMap.keySet()){
			powerList = powerList+","+key;
		}
		return powerList;

	}

	@RequestMapping(value = "/saveOrUpdateRole")
	@ResponseBody
	public Map<String, String> saveOrUpdateRoleInfo(HttpServletRequest req,
			HttpServletResponse response) {
		// int k = 0;
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		String updateUser = user.getUserId();

		String roleID = req.getParameter("ROLEID");
		String roleName = req.getParameter("ROLENAME");
		// String status = req.getParameter("STATUS");
		String roleLevel = req.getParameter("ROLELEVEL");
		String type = req.getParameter("UPDATETYPE");
		String powerList = req.getParameter("POWERLIST");
		powerList=handlepowerList(powerList);
		String valid_code_flag = req.getParameter("VALID_CODE_FLAG");

		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> insertdata = new HashMap<String, Object>();
		insertdata.put("roleID", roleID);
		insertdata.put("roleName", roleName);
		insertdata.put("status", "0");
		insertdata.put("roleLevel", roleLevel);
		insertdata.put("delFlag", "0");
		insertdata.put("updateUser", updateUser);
		insertdata.put("updateDate", new Date(System.currentTimeMillis()));
		insertdata.put("powerList", powerList);
		insertdata.put("VALID_CODE_FLAG", valid_code_flag);
		log.debug("insertdata数据={}", insertdata);
		log.debug("type={}", type);
		if ("save".equals(type)) {
			int rolecounts = roleService.getRoleCountsById(insertdata);

			if (0 == rolecounts) {

				int insertNum = 0;
				String sqno = roleService.getRoleLogSeqNo();
				insertdata.put("seqNo", sqno);
				log.debug("sqno={}", sqno);
				try {
					insertNum = roleService.saveRole(insertdata);
				} catch (Exception e) {
					log.error(e);
					map.put("success", "");
					map.put("error", "用户角色主表信息保存失败，请联系科技人员！");
				}
				if (1 != insertNum) {
					log.debug("角色信息插入失败={}", insertNum);
					map.put("success", "");
					map.put("error", "用户角色主表信息插入失败，请联系科技人员！");
				} else {
					map.put("success", "success");
					map.put("error", "");
				}
			} else {
				map.put("success", "");
				map.put("error", "该角色已存在，请检查！");
			}
		} else if ("update".equals(type)) {
			int insertNum = 0;
			String sqno = roleService.getRoleLogSeqNo();
			insertdata.put("seqNo", sqno);
			log.debug("insertdata={}", insertdata);
			try {
				insertNum = roleService.updateRoleInfo(insertdata);
			} catch (Exception e) {
				log.error(e);
				map.put("success", "");
				map.put("error", "用户角色主表信息更新失败，请联系科技人员！");
			}
			if (1 != insertNum) {
				map.put("success", "");
				map.put("error", "用户角色主表信息插入失败，请联系科技人员！");
			} else {
				map.put("success", "success");
				map.put("error", "");
			}
		}
		return map;
	}

	@RequestMapping(value = "/getRoleInfoById")
	@ResponseBody
	public Map<String, Object> getRoleInfoById(HttpServletRequest req,
			HttpServletResponse response) {
		String roleID = req.getParameter("roleID");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleID", roleID);
		return roleService.getRoleInfoById(map);
	}

	@RequestMapping(value = "/getPowerByRoleId")
	@ResponseBody
	public List<Map<String, Object>> getPowerByRoleId(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleID", req.getParameter("roleID"));
		return roleService.getPowerByRoleId(map);

	}

	@RequestMapping(value = "/delOrChangeRoleStatus")
	@ResponseBody
	public Map<String, String> delOrChangeRoleStatus(HttpServletRequest req,
			HttpServletResponse response) {
		int n = 0;
		Map<String, String> rt = new HashMap<String, String>();
		String type = req.getParameter("type");
		String a = req.getParameter("listdata");
		a = a.substring(0, a.length() - 1);
		String[] listdata = a.split(",");

		// String powerList = req.getParameter("POWERLIST");

		User user = new User();
		user = (User) req.getSession().getAttribute("user");

		if ("del".equals(type)) {
			Map<String, Object> RoleInfo = new HashMap<String, Object>();
			RoleInfo.put("rolelist", listdata);
			int usernum = roleService.findUserRoleCounts(RoleInfo);
			if (usernum > 0) {
				rt.put("success", "");
				rt.put("error", "角色正在被用户使用，请消除关联关系后再删除！");
			} else {
				for (int i = 0; i < listdata.length; i++) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("roleID", listdata[i]);
					map.put("updateUser", user.getUserId());
					String sqno = roleService.getRoleLogSeqNo();
					map.put("seqNo", sqno);
					log.debug("map={}", map);
					// 根据roleid获取powerid
					int dr = 0;
					try {
						dr = roleService.deleteRole(map);
					} catch (Exception e) {
						log.error(e);
						rt.put("success", "");
						rt.put("error", "删除角色失败，请联系科技人员！");
					}
					if (dr > 0) {
						n += 1;
					}
				}
				if (n != listdata.length) {
					rt.put("success", "");
					rt.put("error", "删除角色失败，请联系科技人员！");
				} else {
					rt.put("success", "success");
					rt.put("error", "");

				}
			}
		}
		return rt;
	}

	@RequestMapping(value = "/getRoleLevel")
	@ResponseBody
	public List<Map<?, ?>> getRoleLevel(HttpServletRequest req,
			HttpServletResponse response) {
		return roleService.getRoleLevel();
	}
}
