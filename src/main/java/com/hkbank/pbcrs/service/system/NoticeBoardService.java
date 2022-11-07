package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.NoticeBoradDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeBoardService {
	private Logger log = LogManager.getLogger(NoticeBoardService.class);

	public NoticeBoardService() {
	}

	@Autowired
	private NoticeBoradDao noticeBoradDao;

	/**
	 * 获取待办数量
	 * 
	 * @param 依照菜单编号获取相关数据
	 *            ，由前台上送
	 * @return 数量集合
	 * @throws ParseException
	 */
	public List<Map<String, Object>> getNotices(Map<String, Object> userinfo)
			throws ParseException {

		Map<String, Object> params = new HashMap<String, Object>();

		List<Map<String, Object>> rslt = noticeBoradDao.getNotices(params);
		log.debug("工作台的返回值：{}", rslt);
		return rslt;
	}

}
