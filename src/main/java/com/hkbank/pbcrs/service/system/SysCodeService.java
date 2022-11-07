package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.SysCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysCodeService {

	@Autowired
	private SysCodeDao sysCodeDao;

	public List<Map<String, Object>> findSysCode(Map<String, String> data) {
		return sysCodeDao.findSysCode(data);
	}

}
