package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.SysComboBoxDataDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SysComboBoxDataService {

	private static final Logger log = LogManager
			.getLogger(SysComboBoxDataService.class);

	@Autowired
	private SysComboBoxDataDao sysComboBoxDataDao;

	public List<Map<String, Object>> getComboboxDataList(
			Map<String, Object> params) {
		log.entry("getComboboxDataList()", params);

		List<Map<String, Object>> rslt = null;

		rslt = sysComboBoxDataDao.getComboboxDataList(params);
		return log.exit(rslt);
	}

	public List<Map<String, Object>> getBussTypeList(Map<String, Object> params) {
		log.entry("getBussTypeList()", params);

		List<Map<String, Object>> rslt = null;

		rslt = sysComboBoxDataDao.getBussTypeList(params);
		return log.exit(rslt);
	}
	
	public List<Map<String, Object>> getChannelList(Map<String, Object> params) {
		log.entry("getBussTypeList()", params);

		List<Map<String, Object>> rslt = null;

		rslt = sysComboBoxDataDao.getChannelList(params);
		return log.exit(rslt);
	}
	

}