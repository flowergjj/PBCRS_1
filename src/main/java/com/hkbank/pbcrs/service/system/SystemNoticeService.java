package com.hkbank.pbcrs.service.system;

import com.hkbank.pbcrs.dao.system.SystemNoticeDao;
import com.hkbank.pbcrs.domain.User;
import com.hkbank.pbcrs.exception.EismException;
import com.hkbank.pbcrs.service.fileserver.LocalFileService;
import com.hkbank.pbcrs.util.Util;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SystemNoticeService {

	private static Logger log = LogManager.getLogger(SystemNoticeService.class);

	@Autowired
	private SystemNoticeDao systemNoticeDao;

	@Autowired
	private LocalFileService archManager;

	public Map<String, Object> noticeList(HttpServletRequest request) {
		log.entry("noticeList()");
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> params = Util.parseWebParas(request);
		
		params.put("USERID", user.getUserId());

		int pageNo = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		return systemNoticeDao.noticeList(params, pageNo, pageSize);
	}

	public Map<String, Object> saveNotice(HttpServletRequest request)
			throws Throwable {
		log.entry("saveNotice()");

		List<FileItem> fileItemList = Util.resolveFileItem(request);
		Map<String, String> params = Util.resolveNormalField(fileItemList);
		User user = (User) request.getSession().getAttribute("user");

		log.debug("params={}", params);

		user = (User) request.getSession().getAttribute("user");
		String updateUser = user.getUserId();

		String title = params.get("title");
		String content = params.get("content");
		String status = params.get("status");

		if (title == null || "".equals(title)) {
			throw new EismException("请填写公告标题");
		}

		if (title.length() > 50) {
			throw new EismException("标题超长，请输入不超过50个字符！");
		}

		if (content == null || "".equals(content)) {
			throw new EismException("请填写公告内容");
		}

		if (content.length() > 500) {
			throw new EismException("内容超长，请输入不超过500个字符！");
		}

		Map<String, Object> insertdata = new HashMap<String, Object>();
		insertdata.put("TITLE", title);
		insertdata.put("CONTENT", content);
		insertdata.put("STATUS", status);
		insertdata.put("OPER_USER", updateUser);
		insertdata.put("OPER_TIME", new Date(System.currentTimeMillis()));
		String noticeid = this.getNoticeID();
		insertdata.put("NOTICE_ID", noticeid);

		log.debug("insertdata=={}", insertdata);

		systemNoticeDao.saveNotice(insertdata);

		//this.do_img(fileItemList, insertdata, null);

		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("RET_CODE", "SUCCESS");
		rslt.put("RET_MSG", "保存系统公告成功！");
		return rslt;
	}

	private String getNoticeID() {
		log.entry("getNoticeID()");
		Map<String, Object> seq_map = this.systemNoticeDao.getNoticeSeq();
		Object seq_obj = seq_map.get("SEQNO");
		String seq = null;
		if (seq_obj == null) {
			seq = "0";
		} else {
			seq = seq_obj.toString();
		}
		long seql = Long.parseLong(seq);
		DecimalFormat df = new DecimalFormat("00000000");
		seq = df.format(seql);

		Date today = new Date(System.currentTimeMillis());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String datestr = sdf.format(today);

		String noticeid = datestr + seq;

		return log.exit(noticeid);
	}

	public Map<String, Object> updateNotice(HttpServletRequest request)
			throws Throwable {
		log.entry("updateNotice()");

		List<FileItem> fileItemList = Util.resolveFileItem(request);
		Map<String, String> params = Util.resolveNormalField(fileItemList);
		User user = (User) request.getSession().getAttribute("user");

		log.debug("params={}", params);

		user = (User) request.getSession().getAttribute("user");
		String updateUser = user.getUserId();

		String noticeid = params.get("NOTICE_ID");
		String title = params.get("TITLE");
		String content = params.get("CONTENT");
		String status = params.get("STATUS");

		if (title == null || "".equals(title)) {
			throw new EismException("请填写公告标题");
		}

		if (title.length() > 50) {
			throw new EismException("标题超长，请输入不超过50个字符！");
		}

		if (content == null || "".equals(content)) {
			throw new EismException("请填写公告内容");
		}

		if (content.length() > 500) {
			throw new EismException("内容超长，请输入不超过500个字符！");
		}

		Map<String, Object> updatedata = new HashMap<String, Object>();
		updatedata.put("TITLE", title);
		updatedata.put("CONTENT", content);
		updatedata.put("STATUS", status);
		updatedata.put("OPER_USER", updateUser);
		updatedata.put("OPER_TIME", new Date(System.currentTimeMillis()));
		updatedata.put("NOTICE_ID", noticeid);

		log.debug("updatedata=={}", updatedata);

		Map<String, Object> oldNotice = this.systemNoticeDao
				.getNoticeByID(updatedata);
		Object o_operUser = oldNotice.get("OPER_USER");
		if (o_operUser == null
				|| (!updateUser.equalsIgnoreCase(o_operUser.toString()))) {
			throw new EismException("请勿修改他人创建的公告");
		}

		Object o_imgid = oldNotice.get("BUSSINESS_CODE");
		String imgid = null;
		if (o_imgid != null) {
			imgid = o_imgid.toString();
		}
		updatedata.put("BUSSINESS_CODE", imgid);

		systemNoticeDao.updateNotice(updatedata);

		//this.do_img(fileItemList, updatedata, imgid);

		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("RET_CODE", "SUCCESS");
		rslt.put("RET_MSG", "保存系统公告成功！");
		return rslt;
	}

	public Map<String, Object> deleteNotice(HttpServletRequest request)
			throws Exception {
		log.entry("deleteNotice()");

		User user = (User) request.getSession().getAttribute("user");
		String userid = user.getUserId();

		Map<String, Object> rslt = null;

		String list = request.getParameter("listdata");

		if (list != null && list.length() > 0) {

			String[] notice_ids = list.split(",");

			if (notice_ids != null && notice_ids.length > 0) {
				for (String notice_id : notice_ids) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("NOTICE_ID", notice_id);

					Map<String, Object> notice = this.systemNoticeDao
							.getNoticeByID(params);
					Object ou = notice.get("OPER_USER");

					if (ou == null || (!userid.equalsIgnoreCase(ou.toString()))) {
						throw new EismException("请勿删除他人创建的公告");
					}

					Object bc = notice.get("BUSSINESS_CODE");
					if (bc != null) {
						String bussCode = bc.toString();
						this.deleteImgs(bussCode);
					}

					systemNoticeDao.deleteNotice(params);
				}
			}
		}
		rslt = new HashMap<String, Object>();
		rslt.put("RET_CODE", "SUCCESS");
		rslt.put("RET_MSG", "删除系统公告成功！");
		return rslt;
	}

	public Map<String, Object> getNoticeByID(Map<String, Object> roleInfo) {
		return systemNoticeDao.getNoticeByID(roleInfo);
	}

	private Map<String, Object> do_img(List<FileItem> fileItemList,
			Map<String, Object> params, String imgId) throws Throwable {
		log.entry("do_img()", fileItemList, params);
		Map<String, Object> rslt = new HashMap<String, Object>();

		String userid = String.valueOf(params.get("OPER_USER"));

		/* 需要新上传文件的情况 */
		if (StringUtils.isEmpty(imgId)) {
			log.debug("该笔申请之前未上传影像文件, 需要上传!");
			String bussinessCode = archManager.obtainBussinessCode();
			List<Map<String, String>> fileList = Util.fileUpload(fileItemList,
					bussinessCode);

			/* 1.3. 判断用户上传是否成功, 要求至少上传一个文件 */
			if (fileList == null) {
				rslt.put("RET_CODE", "FAILED");
				rslt.put("RET_MSG", "文件上传出现异常, 请联系后台管理人员!");
				return rslt;
			}

			updateImg(bussinessCode, fileList, userid);
			params.put("BUSSINESS_CODE", bussinessCode);

			/* 更新影像流水号 */
			int updateCount = systemNoticeDao.updateNoticeImg(params);
			if (updateCount != 1) {
				throw new EismException("更新影像流水失败!");
			}

			// 删除上传的临时文件
			Util.deleteUploadFile(bussinessCode);
		}

		/* 更新原有影像 */
		else {
			log.debug("该笔申请之前上传过影像文件, 检查是否存在新上传文件!");

			/* 更新影像平台数据 */
			List<Map<String, String>> fileList = Util.fileUpload(fileItemList,
					imgId);
			log.debug("新上传文件List={}", fileList);
			if (fileList.size() > 0) {
				updateImg(imgId, fileList, userid);
			}

			// 删除上传的临时文件
			Util.deleteUploadFile(imgId);
		}

		/* 其它业务正常处理结束 */
		rslt.put("RET_CODE", "SUCCESS");
		rslt.put("RET_MSG", "更新信息成功信息成功,已重新发起申请!");
		log.debug("影像更新处理结束, rslt={}", rslt);
		return null; // 成功返回空，表示没有任何错误
	}

	public void updateImg(String bussinessCode,
			List<Map<String, String>> fileList, String userId) throws Exception {
		/*
		 * 2.3 保存档案数据 该方法内实现了档案dao层操作, 若出现并抛出异常, 应对之前的数据库操作进行回滚
		 */
		List<String> temp = new ArrayList<String>();
		for (Map<String, String> fileAttr : fileList) {
			String bussinessId = null;

			try {
				bussinessId = archManager.uploadImage(bussinessCode, null,
						fileAttr.get("AC_TYPE"), fileAttr.get("AC_OLD_NAME"),
						userId, null);
			} catch (Exception e) {

				/* 删除之前提交至影像的所有文件 */
				for (String imgId : temp) {
					try {
						deleteImg(imgId);
					} catch (Exception e2) {
						continue;
					}
				}

				throw new EismException("上传文件至影像平台出现异常!", e);
			}

			temp.add(bussinessId);
		}
	}

	public void deleteImg(String bussinessId) throws Exception {
		archManager.deleteImageByBussinessId(bussinessId);
	}

	public void deleteImgs(String bussinessCode) throws Exception {
		archManager.deleteImageByBussinessCode(bussinessCode);
	}

	public List<Map<String, Object>> findImgList(Map<String, Object> params) {
		List<Map<String, Object>> list = systemNoticeDao.findImgList(params);

		log.debug("影像LIST={}", list);
		return list;
	}

	public void doFileDownload(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		Map<String, Object> params = Util.parseWebParas(request);
		String imgId = ObjectUtils.toString(params.get("imgId"), "");
		log.debug("用户请求文件下载; 参数列表为{}", params);

		if (StringUtils.isEmpty(imgId)) {
			throw new EismException("该笔业务无相关影像文件!");
		}

		/* 从影像获取下载文件 */
		// 123
		String[] fileList = archManager.downloadImage(imgId);
		String localFilePath = fileList[0];

		/* 回传文件 */
		Util.fileDownload(localFilePath, response);

		log.debug("用户请求文件下载结束;");
	}

}
