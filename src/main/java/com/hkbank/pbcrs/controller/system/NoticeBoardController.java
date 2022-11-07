package com.hkbank.pbcrs.controller.system;

import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.service.system.NoticeBoardService;
import com.hkbank.pbcrs.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/noticeBoardController")
public class NoticeBoardController {

	private Logger log = LogManager.getLogger(NoticeBoardController.class);

	@Autowired
	private NoticeBoardService noticeBoard;

	/**
	 * 系统公告栏
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getNotices")
	@ResponseBody
	public List<Map<String, Object>> getNotices(HttpServletRequest req,
			HttpServletResponse response) throws ParseException {
		Map<String, Object> params = Util.parseWebParas(req);
		User user = new User();
		user = (User) req.getSession().getAttribute("user");
		params.put("userId", user.getUserId());

		List<Map<String, Object>> rsl = noticeBoard.getNotices(params);

		if (rsl != null && (!rsl.isEmpty())) {
			for (Map<String, Object> rslt : rsl) {
				String title = String.valueOf(rslt.get("TITLE"));
				String oper_time = String.valueOf(rslt.get("OPER_TIME"));

				StringBuffer titlebuff = new StringBuffer();

				titlebuff.append("[").append(oper_time).append("]");

//				if (title.length() > 12) {
//					titlebuff.append(title.substring(0, 10)).append("...");
//				} else {
					titlebuff.append(title);
//				}

				rslt.put("TITLE", titlebuff.toString());
			}
		}

		return log.exit(rsl);
	}
}
