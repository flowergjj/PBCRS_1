package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.RoleGroupService;
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
@RequestMapping("/roleGroupManage")
public class RoleGroupManageController {

	private Logger log = LogManager.getLogger(RoleGroupManageController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleGroupService roleGroupService;

	@RequestMapping(value = "/roleGroupList")
	@ResponseBody
	public Map<String, Object> getRoleList(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, Object> querydata = new HashMap<String, Object>();
		int pageNo = Integer.parseInt(req.getParameter("page"));
		int pageSize = Integer.parseInt(req.getParameter("rows"));
		return roleGroupService.findAllRoleGroup(querydata, pageNo, pageSize);
	}

	@RequestMapping(value = "/roleGroupStatusCode")
	@ResponseBody
	public List<Map<String, Object>> findRoleGroupStatusCode(
			HttpServletRequest req, HttpServletResponse response) {
		return roleGroupService.findRoleGroupStatusCode();
	}

	@RequestMapping(value = "/roleList")
	@ResponseBody
	public List<Map<String, Object>> findRoleList(HttpServletRequest req,
			HttpServletResponse response) {
		return roleGroupService.findRoleList();
	}

	@RequestMapping(value = "/roleListByGroupId")
	@ResponseBody
	public List<Map<String, Object>> findRoleListByGroupId(
			HttpServletRequest req, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", req.getParameter("groupId"));
		return roleGroupService.findRoleListByGroupId(map);
	}

	@RequestMapping(value = "/saveOrUpdateRoleGroup")
	@ResponseBody
	public Map<String, String> saveOrUpdateRoleGroup(HttpServletRequest req,
			HttpServletResponse response) {
		// int k = 0;
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		String updateUser = user.getUserId();
		String groupName = req.getParameter("groupName");
		String status = req.getParameter("status");
		String type = req.getParameter("type");
		String powerList = req.getParameter("powerList");
		String groupId = req.getParameter("groupId");

		Map<String, String> map = new HashMap<String, String>();
		Map<String, Object> insertdata = new HashMap<String, Object>();
		insertdata.put("groupName", groupName);
		insertdata.put("status", status);
		insertdata.put("updateUser", updateUser);
		insertdata.put("updateDate", new Date(System.currentTimeMillis()));
		insertdata.put("powerList", powerList);
		log.debug("insertdata数据={}", insertdata);
		log.debug("type={}", type);
		if ("save".equals(type)) {
			int insertNum = 0;
			try {
				insertNum = roleGroupService.saveRoleGroup(insertdata);
			} catch (Exception e) {
				log.error(e);
				map.put("success", "");
				map.put("error", "信息保存失败，请联系科技人员！");
			}
			if (1 != insertNum) {
				log.debug("角色组信息插入失败={}", insertNum);
				map.put("success", "");
				map.put("error", "信息插入失败，请联系科技人员！");
			} else {
				map.put("success", "success");
				map.put("error", "");
			}
		} else if ("update".equals(type)) {
			int insertNum = 0;
			insertdata.put("groupId", groupId);
			log.debug("insertdata={}", insertdata);
			try {
				insertNum = roleGroupService.updateRoleGroup(insertdata);
			} catch (Exception e) {
				log.error(e);
				map.put("success", "");
				map.put("error", "角色组信息更新失败，请联系科技人员！");
			}
			if (1 != insertNum) {
				map.put("success", "");
				map.put("error", "角色组信息更新失败，请联系科技人员！");
			} else {
				map.put("success", "success");
				map.put("error", "");
			}
		}
		return map;
	}

	@RequestMapping(value = "/delRoleGroup")
	@ResponseBody
	public Map<String, String> delRoleGroup(HttpServletRequest req,
			HttpServletResponse response) {
		int n = 0;
		Map<String, String> rt = new HashMap<String, String>();
		String[] listdata = req.getParameter("listdata").split(",");
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		try {
			n = roleGroupService.deleteRoleGroup(listdata, user);
		} catch (Exception e) {
			log.error(e);
			rt.put("success", "");
			rt.put("error", "角色组信息删除失败，请联系科技人员！");
		}
		if (0 == n) {
			rt.put("success", "");
			rt.put("error", "角色组信息删除失败，请联系科技人员！");
		} else {
			rt.put("success", "success");
			rt.put("error", "");
		}
		return rt;
	}
}
