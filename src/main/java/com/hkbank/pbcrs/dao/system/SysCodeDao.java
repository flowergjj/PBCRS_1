package com.hkbank.pbcrs.dao.system;

import com.hkbank.pbcrs.dao.IBaseDao;
import com.hkbank.pbcrs.dao.impl.BaseSessionDaoSupportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SysCodeDao extends BaseSessionDaoSupportImpl {

	@Autowired
	private IBaseDao<Map<String, Object>> baseDaoImpl;

	public List<Map<String, Object>> findSysCode(Object data) {
		return baseDaoImpl.selectList("SystemCode.findSysCode", data);

	}

}
