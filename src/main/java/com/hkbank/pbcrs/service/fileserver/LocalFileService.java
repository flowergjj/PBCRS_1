package com.hkbank.pbcrs.service.fileserver;


import com.hkbank.pbcrs.cache.CacheManager;
import com.hkbank.pbcrs.dao.system.EcssDao;

import com.hkbank.pbcrs.exception.EismException;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class LocalFileService {

	@Autowired
	private EcssDao ecssDao;

	private Logger log = LogManager.getLogger(LocalFileService.class);

	public LocalFileService() {
		Properties ecss = (Properties) CacheManager
				.getCache("local_file_service.properties");

		if (ecss == null) {

			String ecss_properties = new String(
					"config/common/local_file_service.properties");

			try {
				ecss = Resources.getResourceAsProperties(ecss_properties);
			} catch (FileNotFoundException e) {
				log.error(e);
			} catch (IOException e) {
				log.error(e);
			}
			CacheManager.putCache("local_file_service.properties", ecss);
			WORK_DIR = ecss.getProperty("local_storage");
			WORK_DIR = WORK_DIR.trim();
			log.debug("WORK_DIR=={}", WORK_DIR);
		}

	}

	private static String WORK_DIR;

	private String obtainBussinessID() {
		log.entry("obtainBussinessID()");
		int seqno = ecssDao.obtainCONSUMER_SEQ_NO();

		DecimalFormat df = new DecimalFormat("00000000000000");

		String seq = df.format(seqno);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date(System.currentTimeMillis()));

		String bussinessid = "zh.EIS_LOC." + date + "." + seq;
		return log.exit(bussinessid);
	}

	public String obtainBussinessCode() {
		log.entry("obtainBussinessCode()");
		int seqno = ecssDao.obtainCONSUMER_SEQ_NO();

		DecimalFormat df = new DecimalFormat("00000000000000");

		String seq = df.format(seqno);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date(System.currentTimeMillis()));

		String bussinessCode = "EIS.LC." + date + "." + seq;
		return log.exit(bussinessCode);
	}

	/**
	 * @param bussinessCode
	 *            影像交易流水
	 * @param bussinessId
	 *            影像流水
	 * @param ac_type
	 *            影像类型
	 * @param filename
	 *            文件名（寻址用完整路径）
	 * @param ecm_author
	 *            影像上传人（系统用户ID）
	 * @param ecss_params
	 *            CERT_TYPE - 个人征信上传，其他交易为null：证件类型<br>
	 *            CERT_NUMBER - 个人征信上传，其他交易为null：证件号码 <br>
	 *            LOAN_CARD_NUM - 企业征信上传，其他交易为null：中征码<br>
	 * @return
	 * @throws IllegalDNPatternException
	 * @throws AppBizFault
	 * @throws EismException
	 */
	public String uploadImage(String bussinessCode, String bussinessId,
			String ac_type, String filename, String ecm_author,
			Map<String, String> ecss_params) throws EismException {
		log.entry("uploadImage()", bussinessCode, filename);

		if (filename == null || "".equals(filename.trim())) {
			return null;
		}

		// 将待上传文件保存至数据库
		Map<String, Object> params = new HashMap<String, Object>();
		if (bussinessId == null || "".equals(bussinessId.trim())) {
			bussinessId = this.obtainBussinessID();
		}

		String oldfilename = this.obtainFilename(filename);
		String filepath = this.getLocalStorage(bussinessCode, bussinessId,
				oldfilename);

		params.put("AC_IMG_ID", bussinessId);
		params.put("AC_SEQ", "0"); // 单个文件上传，始终在第一页
		params.put("AC_TYPE", ac_type);
		params.put("AC_OLD_NAME", oldfilename);
		params.put("AC_STUTAS", "1");
		params.put("WHO_CREATE", ecm_author);
		params.put("WHEN_CREATE", new Date(System.currentTimeMillis()));
		params.put("BUSSINESS_CODE", bussinessCode);
		params.put("FILE_STORAGE", "0"); // 存储位置：0=本地
		params.put("LOCAL_PATH", filepath); // 存储位置：0=本地
		params.put("UPLOAD_FLAG", "0");
		log.debug("params=={}", params);
		try {
			this.ecssDao.saveArchives(params);
		} catch (Exception e) {
			log.error("", e);
			throw new EismException(e);
		}

		try {
			this.storagefile(filename, filepath);
		} catch (IOException e) {
			log.error("", e);
			throw new EismException("保存上传文件失败，可能是磁盘空间不足，请联系管理员");
		} catch (Throwable t) {
			log.error("", t);
			throw new EismException("保存上传文件时，遇到未知错误，请联系管理员");
		}

		log.debug("影像已成功保存,bussinessId={}", bussinessId);

		return log.exit(bussinessId);
	}

	private void storagefile(String filename, String filepath)
			throws IOException {
		File fromfile = new File(filename);
		File tofile = new File(filepath);

		if (tofile.exists()) {
			tofile.delete();
		}

		String todirpath = tofile.getParent();
		if (todirpath != null) {
			File todir = new File(todirpath);
			if (!todir.exists()) {
				todir.mkdirs();
			}
		}

		InputStream read = new FileInputStream(fromfile);
		OutputStream write = new FileOutputStream(tofile);

		byte[] readbuff = new byte[512];
		int readcount = -1;
		while ((readcount = read.read(readbuff)) > 0) {
			write.write(readbuff, 0, readcount);
		}

		read.close();
		write.flush();
		write.close();

	}

	private String getLocalStorage(String bussinessCode, String bussinessId,
			String oldfilename) {
		StringBuffer filepathbuff = new StringBuffer();
		filepathbuff.append(WORK_DIR);
		filepathbuff.append('/');
		filepathbuff.append(bussinessCode);
		filepathbuff.append('/');
		filepathbuff.append(bussinessId);
		filepathbuff.append('/');
		filepathbuff.append(oldfilename);
		return filepathbuff.toString();
	}

	private String obtainFilename(String filename) {
		if (filename == null) {
			return null;
		}
		int ssi = -1;
		int si = filename.lastIndexOf('/');
		int bsi = filename.lastIndexOf('\\');

		ssi = ((si > bsi) ? si : bsi);

		String rslt = null;
		if (ssi < 0 || ssi >= filename.length()) {
			rslt = filename;
		} else {
			rslt = filename.substring(ssi + 1);
		}

		return rslt;
	}

	public String[] downloadImage(String bussinessId) throws EismException {
		log.entry("downloadImage()", bussinessId);

		if (bussinessId == null) {
			return null;
		}

		List<Map<String, Object>> files = this.ecssDao
				.findAchivesByBusinessID(bussinessId);

		String[] filePathes = null;
		if (files != null && (!files.isEmpty())) {
			filePathes = new String[files.size()];
			int i = 0;
			for (Map<String, Object> file : files) {
				String filename = String.valueOf(file.get("LOCAL_PATH"));
				filePathes[i++] = filename;
			}
		}

		return log.exit(filePathes);
	}

	/**
	 * @param args
	 * @throws AppBizFault
	 * @throws IllegalDNPatternException
	 */
	public void deleteImageByBussinessId(String bussinessId)
			throws EismException {

		log.entry("deleteImageByBussinessId()", bussinessId);

		if (bussinessId == null) {
			return;
		}

		List<Map<String, Object>> files = this.ecssDao
				.findAchivesByBusinessID(bussinessId);

		String[] filePathes = null;
		if (files != null && (!files.isEmpty())) {
			filePathes = new String[files.size()];
			int i = 0;
			for (Map<String, Object> file : files) {
				String filename = String.valueOf(file.get("LOCAL_PATH"));
				filePathes[i++] = filename;
			}
		}

		if (filePathes != null) {

			for (int i = 0; i < filePathes.length; i++) {
				File file = new File(filePathes[i]);
				if (file.exists()) {
					File parentdir = file.getParentFile();
					// 删除文件
					file.delete();
					this.delParentEmptyDir(WORK_DIR, parentdir);
				}
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("AC_IMG_ID", bussinessId);
			params.put("AC_SEQ", "0");
			this.ecssDao.deleteArchiveByBussinessId(params);

		}

		log.exit();
	}

	/**
	 * @param args
	 * @throws AppBizFault
	 * @throws IllegalDNPatternException
	 */
	public void deleteImageByBussinessCode(String bussinessCode)
			throws EismException {
		log.entry("deleteImageByBussinessCode()");

		if (bussinessCode == null) {
			return;
		}

		List<Map<String, Object>> files = this.ecssDao
				.findAchivesByBusinessCode(bussinessCode);

		String[] filePathes = null;
		if (files != null && (!files.isEmpty())) {
			filePathes = new String[files.size()];
			int i = 0;
			for (Map<String, Object> file : files) {
				String filename = String.valueOf(file.get("LOCAL_PATH"));
				filePathes[i++] = filename;
			}
		}

		if (filePathes != null) {

			for (int i = 0; i < filePathes.length; i++) {
				File file = new File(filePathes[i]);
				if (file.exists()) {
					this.delParentEmptyDir(WORK_DIR, file);
				}
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("BUSSINESS_CODE", bussinessCode);
			this.ecssDao.deleteArchiveByBussinessCode(params);

		}

		log.exit();
	}

	public void delParentEmptyDir(String workdir, File file) {
		if (file == null || workdir == null) {
			return;
		}

		// 统一路径分隔符为:/
		String endpath = file.getAbsolutePath().replaceAll("\\\\", "/");
		String workpath = workdir.replaceAll("\\\\", "/");

		// 清理多余的目录分隔符
		while (endpath.endsWith("/")) {
			endpath = endpath.substring(0, endpath.length() - 1);
		}

		while (workpath.endsWith("/")) {
			workpath = workpath.substring(0, workpath.length() - 1);
		}

		while (workpath.indexOf("//") > 0) {
			workpath = workpath.replaceAll("//", "/");
		}

		if (endpath.equals(workpath)) {
			// 到了工作目录，不再往上删除
			return;
		}

		if (file.exists()) {
			File[] fs = file.listFiles();
			if (fs == null || fs.length <= 0) {
				File pfile = file.getParentFile();
				file.delete();
				delParentEmptyDir(workpath, pfile);
			}
		}

	}

	public void delParentEmptyDir(String workdir, String filename) {
		if (filename == null || workdir == null) {
			return;
		}

		File file = new File(filename);
		this.delParentEmptyDir(workdir, file);

	}

	public static String getWORK_DIR() {
		return WORK_DIR;
	}

}
