package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.service.system.SysCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysCodeManage")
public class SysCodeManageController {

	@Autowired
	private SysCodeService sysCodeService;

	@RequestMapping(value = "/sysCodeListByType")
	@ResponseBody
	public List<Map<String, Object>> findSexCode(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", req.getParameter("type"));
		List<Map<String, Object>> list = sysCodeService.findSysCode(map);
		return list;
	}

	@RequestMapping(value = "/selRoleStatusByType")
	@ResponseBody
	public List<Map<String, Object>> findRoleStatusCode(HttpServletRequest req,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", req.getParameter("type"));
		List<Map<String, Object>> list = sysCodeService.findSysCode(map);
		return list;
	}

}
