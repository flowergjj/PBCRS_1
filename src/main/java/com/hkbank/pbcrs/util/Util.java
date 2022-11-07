package com.hkbank.pbcrs.util;


import com.hkbank.pbcrs.cache.CacheManager;
import com.hkbank.pbcrs.exception.EismException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.imageio.ImageIO;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Util {

	private static final Logger log = LogManager.getLogger(Util.class);

	public static final String KEY_FILE_UPLOAD = "FILE_UPLOAD";
	public static final String KEY_REPORT = "__REPORT__";
	public static final String KEY_LOGIN = "__LOGIN__";
	private static final String CONFIG_FILE_UPLOAD = "config/common/file_upload.properties";
	private static final String CONFIG_REPORT = "config/common/report.properties";
	public static final String CONFIG_LOGIN = "config/common/login.properties";
	private static final String EXCEL_CSV = "csv";
	/* 初始化一些配置文件并放入缓存 */
	static {
		try {
			if (!CacheManager.hasCache(KEY_FILE_UPLOAD)) {
				CacheManager.putCache(KEY_FILE_UPLOAD,
						Resources.getResourceAsProperties(CONFIG_FILE_UPLOAD));
			}

			if (!CacheManager.hasCache(KEY_REPORT)) {
				CacheManager.putCache(KEY_REPORT,
						Resources.getResourceAsProperties(CONFIG_REPORT));
			}
			if (!CacheManager.hasCache(KEY_LOGIN)) {
				CacheManager.putCache(KEY_LOGIN,
						Resources.getResourceAsProperties(CONFIG_LOGIN));
			}

		} catch (IOException e) {
			log.error("加载配置文件出现异常", e);
		}
	}

	public String createCacheNO(String sqNO) {
		Date dateTime = new Date(System.currentTimeMillis());
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String bussNo = "CC" + simpleTime.format(dateTime) + sqNO;
		return bussNo;
	}

	public String createBussNO(String sqNO) {
		Date dateTime = new Date(System.currentTimeMillis());
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String bussNo = "FL" + simpleTime.format(dateTime) + sqNO;
		return bussNo;
	}

	public String createUserLogNO(String sqNO) {
		Date dateTime = new Date(System.currentTimeMillis());
		SimpleDateFormat simpleTime = new SimpleDateFormat("yyyyMMdd");
		String bussNo = simpleTime.format(dateTime) + "." + sqNO;
		return bussNo;
	}

	@SuppressWarnings("unchecked")
	public static List<FileItem> resolveFileItem(HttpServletRequest request)
			throws Throwable {
		Properties config = null;
		String fileEncoding = null;
		long fileSize = -1;
		List<FileItem> list = null;

		/* 本来此处存在try-catch, 按照黎叔的要求予以去掉 */
		config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
		fileEncoding = config.getProperty("file_header_encoding") == null ? "UTF-8"
				: config.getProperty("file_header_encoding");
		fileSize = config.getProperty("file_max_size") == null ? -1 : Long
				.parseLong(config.getProperty("file_max_size"));

		// 创建文件的临时存储控件
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		ServletFileUpload fileupload = new ServletFileUpload(diskFactory);

		// 设置文件最大大小
		//fileupload.setSizeMax(fileSize);

		// 设置编码
		fileupload.setHeaderEncoding(fileEncoding);
		log.debug("request=={}", request.getParameterMap());

		// 通过解析当前的request,获取上传的文件,放在一个集合中
		/* TODO 在某种情况下此处会出现NullPointerException 但是具体的场景未知, 此处需要额外注意 */
		try {
			list = fileupload.parseRequest(request);
		} catch (SizeLimitExceededException e) {
			long maxsize = e.getPermittedSize();
			StringBuffer errbuff = new StringBuffer();
			errbuff.append("上传的附件文件不能超过");
			errbuff.append(maxsize / 1024 / 1024);
			errbuff.append("M！");
			throw new EismException(errbuff.toString());
		} catch (Throwable e) {
			throw e;
		}

		return list;
	}

	public static Map<String, String> resolveNormalField(List<FileItem> list)
			throws Throwable {
		Map<String, String> params = new HashMap<String, String>();
		Properties config = null;
		String encoding = null;

		/* 本来此处存在try-catch, 按照黎叔的要求予以去掉 */
		config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
		encoding = config.getProperty("file_header_encoding") == null ? "UTF-8"
				: config.getProperty("file_header_encoding");

		Iterator<FileItem> it = list.iterator();
		while (it.hasNext()) {
			FileItem item = it.next();
			if (item.isFormField()) {
				String itemvalue = item.getString(encoding);
				if (itemvalue != null) {
					itemvalue = itemvalue.trim();
				}
				params.put(item.getFieldName(), itemvalue);
			}
		}

		return params;
	}

//	public static void clearUploadWorkDir(List<FileItem> fileList,
//			String bussinessCode) {
//		log.entry("clearUploadWorkDir()", fileList, bussinessCode);
//		Properties config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
//		// 首先删除fileList，然后递归删除空的父目录，直至saveBase为止，saveBase保留
//		String saveBase = config.getProperty("file_save_base");
//
//		if (fileList == null || fileList.isEmpty()) {
//			return;
//		}
//
//		LocalFileService localFileService = new LocalFileService();
//		for (FileItem fi : fileList) {
//			String filename = fi.getName();
//			log.debug("filename=={}", filename);
//			File tempfile = new File(filename);
//			localFileService.delParentEmptyDir(saveBase, tempfile);
//
//		}
//
//	}

	public static void deleteUploadFile(String bussinessCode) {
		try {

			Properties config = (Properties) CacheManager
					.getCache(KEY_FILE_UPLOAD);
			String saveBase = config.getProperty("file_save_base");

			String foldPath = saveBase + File.separator + bussinessCode;

			File workdir = new File(foldPath);
			cleandir(workdir);

		} catch (Throwable t) {
			log.error("清理上传文件临时目录时，发生异常", t);

		}

	}

	private static void cleandir(File foldPath) {
		if (foldPath.exists()) {
			if (foldPath.isDirectory()) {
				File[] files = foldPath.listFiles();
				if (files != null && files.length > 0) {
					for (File f : files) {
						cleandir(f);
					}
				} else {
					foldPath.delete();
				}
			} else {
				foldPath.delete();
			}
		}

	}

	/**
	 * 解析form表单中的数据并进行文件上传; 文件按照saveBase/bussinessId/filename方式进行存放, 其中:<br/>
	 * saveBase - config/file_upload.properties配置文件中file_save_base的值;<br/>
	 * bussinessId - 影像编号, 由ImageManager生成;<br/>
	 * filename - 前端上传文件文件名;<br/>
	 * 

	 *            影像流水;
	 * @return 文件存放的路径列表;<br/>
	 *         若为null则表示文件上传过程出现异常或者不符合规范;<br/>
	 *         否则返回文件存放的路径list;
	 */
	public static List<Map<String, String>> fileUpload(List<FileItem> list,
			String bussinessCode) throws Throwable {

		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();

		if (list == null || list.isEmpty()) {
			return fileList;
		}

		Properties config = null;
		String saveBase = null;

		/* 本来此处存在try-catch, 按照黎叔的要求予以去掉 */
		config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
		saveBase = config.getProperty("file_save_base");
		if (StringUtils.isEmpty(saveBase)) {
			throw new EismException("Base path should not be null!");
		}

		Iterator<FileItem> it = list.iterator();
		while (it.hasNext()) {
			FileItem item = it.next();

			/* 获取到文件域的情况 */
			if (!item.isFormField() && !"".equals(item.getName().trim())) {
				log.debug("获取到文件域, 准备进行上传! fieldName=[{}], fieldValue=[{}]",
						item.getFieldName(), item.getName());

				/* 为了适应黎叔影像接口, 此处放入该属性 */
				Map<String, String> fileMap = new HashMap<String, String>();
				if ("perAuthFile".equals(item.getFieldName())) {
					fileMap.put("AC_TYPE", "017002");
				} else if ("entAuthFile".equals(item.getFieldName())) {
					fileMap.put("AC_TYPE", "017001");
				} else {
					fileMap.put("AC_TYPE", "017001");
					// log.warn("未知的文件标签名字, 请检查代码!");
				}

				/* 存储的地址 */
				// String fileName = new File(item.getName()).getName();
				String fileName = FilenameUtils.getName(item.getName());
				log.debug("获取上传文件名为=[{}]", fileName);
				String savePath = saveBase + File.separator + bussinessCode
						+ File.separator + RandomUtils.generateString(6)
						+ File.separator + fileMap.get("AC_TYPE");
				File saveDir = new File(savePath);
				File saveFile = new File(savePath + File.separator + fileName);

				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}

				/* 如果没有文件存在则会出现FileNotFound异常, 因此先进行创建 */
				if (!saveFile.exists()) {
					saveFile.createNewFile();
				}

				log.debug("上传文件路径为=[{}]", saveFile.getAbsolutePath());
				fileMap.put("AC_OLD_NAME", saveFile.getAbsolutePath());
				item.write(saveFile);
				item.delete();
				fileList.add(fileMap);
				log.debug("文件[{}]上传成功!", saveFile.getAbsolutePath());
			}
		}

		return fileList;
	}

	/**
	 * 解析form表单中的数据并进行文件上传; 文件按照saveBase/bussinessId/filename方式进行存放, 其中:<br/>
	 * saveBase - config/file_upload.properties配置文件中file_save_base的值;<br/>
	 * bussinessId - 影像编号, 由ImageManager生成;<br/>
	 * filename - 前端上传文件文件名;<br/>
	 * 
	 * @param request
	 * @param response
	 * @param bussinessId
	 *            影像流水;
	 * @return 文件存放的路径列表;<br/>
	 *         若为null则表示文件上传过程出现异常或者不符合规范;<br/>
	 *         否则返回文件存放的路径list;
	 */
	public static List<Map<String, String>> generalFileUpload(
			List<FileItem> list, String bussinessCode) throws Throwable {
		Properties config = null;
		String saveBase = null;
		List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();

		config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
		saveBase = config.getProperty("file_save_base");
		if (StringUtils.isEmpty(saveBase))
			throw new Exception("Base path should not be null!");

		Iterator<FileItem> it = list.iterator();
		while (it.hasNext()) {
			FileItem item = it.next();

			/* 获取到文件域的情况 */
			if (!item.isFormField() && !"".equals(item.getName().trim())) {
				log.debug("获取到文件域, 准备进行上传! fieldName=[{}], fieldValue=[{}]",
						item.getFieldName(), item.getName());

				/* 存储的地址 */
				// String fileName = new File(item.getName()).getName();
				String fileName = FilenameUtils.getName(item.getName());
				log.debug("获取上传文件名为=[{}]", fileName);
				String savePath = "";
				synchronized (savePath) {
					savePath = saveBase + File.separator + bussinessCode
							+ File.separator + RandomUtils.generateString(6);
				}
				File saveDir = new File(savePath);
				File saveFile = new File(savePath + File.separator + fileName);

				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}

				/* 如果没有文件存在则会出现FileNotFound异常, 因此先进行创建 */
				if (!saveFile.exists()) {
					saveFile.createNewFile();
				}

				Map<String, String> fileMap = new HashMap<String, String>();
				log.debug("上传文件路径为=[{}]", saveFile.getAbsolutePath());
				fileMap.put("UPLOAD_FILE_NAME", saveFile.getAbsolutePath());
				item.write(saveFile);
				item.delete();
				fileList.add(fileMap);
				log.debug("文件[{}]上传成功!", saveFile.getAbsolutePath());
			}
		}

		return fileList;
	}

	/**
	 * 文件下载方法
	 * 
	 * 注意! 如果是IE8浏览器记得在"工具 -> SmartScreen筛选器"内选择关闭SmartScreen筛选器, 否则会导致下载文件卡住;
	 * 
	 * @param localFilePath
	 *            本地文件绝对路径;
	 * @param response
	 *            client端response;
	 * @throws Throwable
	 *             发生任意异常时
	 */
	public static void fileDownload(String localFilePath,
			HttpServletResponse response) throws Throwable {
		fileDownload(localFilePath, null, response);
	}

	/**
	 * 文件下载方法
	 * 
	 * 注意! 如果是IE8浏览器记得在"工具 -> SmartScreen筛选器"内选择关闭SmartScreen筛选器, 否则会导致下载文件卡住;
	 * 
	 * @param localFilePath
	 *            本地文件绝对路径;
	 * @param response
	 *            client端response;
	 * @throws Throwable
	 *             发生任意异常时
	 */
	public static void fileDownload(String localFilePath,
			String responseEncoding, HttpServletResponse response)
			throws Throwable {
		Properties config = null;
		String encoding = null;
		OutputStream output = null;
		InputStream input = null;

		log.debug("开始进行文件下载, 本地文件为={}", localFilePath);

		if (localFilePath == null) {
			throw new EismException("需要下载的文件[" + localFilePath + "]不存在!");
		}

		try {
			config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
			encoding = config.getProperty("file_header_encoding") == null ? "UTF-8"
					: config.getProperty("file_header_encoding");

			File localFile = new File(localFilePath);
			String fileName = localFile.getName();
			fileName = URLEncoder.encode(fileName, encoding);

			log.debug("download filename is: {}", fileName);

			if (!localFile.exists()) {
				// throw new EismException("需要下载的文件[" + localFilePath
				// + "]不存在，该文件可能已经归档。");
				throw new EismException("需要下载的文件不存在，该文件可能已经归档。");
			}

			/* 设置相关属性 */
			if (responseEncoding == null) {
				responseEncoding = encoding;
			}
			response.setCharacterEncoding(responseEncoding);
			response.setContentLength((int) localFile.length());
			if (isFileImage(localFile)) {
				log.debug("回传文件为image文件, 直接打开");
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition", "inline; filename="
						+ fileName);
			} else if (isFileHtml(localFile)) {
				log.debug("回传文件为html文件, 直接打开");
				response.setContentType("text/html");
			} else {
				log.debug("回传文件为其它文件, 提示下载");
				response.setContentType("application/octet-stream");
				response.addHeader("Content-Disposition",
						"attachment; filename=" + fileName);
			}

			/* 写回客户端 */
			output = response.getOutputStream();
			// input = new FileInputStream(localFile);
			input = iconvEncoding(responseEncoding, encoding, localFile);
			IOUtils.copy(input, output);

			log.debug("文件下载正常结束");
		} catch (Throwable e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}

	}

	private static InputStream iconvEncoding(String responseEncoding,
			String encoding, File localFile) throws IOException {
		log.entry("iconvEncoding()", responseEncoding, encoding);
		InputStream input = new FileInputStream(localFile);
		if (responseEncoding.equalsIgnoreCase(encoding)) {
			return input;
		} else {

			InputStreamReader isr = new InputStreamReader(input, encoding);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			StringBuffer converted = new StringBuffer();
			while ((line = br.readLine()) != null) {
				// log.debug("line=={}", line);
				String convstr = new String(line.getBytes(), responseEncoding);
				// log.debug("convstr=={}", convstr);
				converted.append(convstr);
				converted.append("\n");
			}
			IOUtils.closeQuietly(br);

			InputStream convertedStream = new ByteArrayInputStream(converted
					.toString().getBytes());

			return convertedStream;
		}
	}

	public static String translateChinese(HttpServletRequest request,
			String chinese) throws Throwable {
		return translateChinese(request, chinese, "UTF-8");
	}

	public static String translateChinese(HttpServletRequest request,
			String chinese, String encoding) throws Throwable {

		String agent = request.getHeader("User-Agent");
		String translate = null;
		boolean isChromeframe = (agent != null && agent.indexOf("chromeframe") != -1);
		boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);

		if (chinese == null) {
			return chinese;
		}

		if (isChromeframe || isMSIE) {
			translate = URLEncoder.encode(chinese, encoding);
		} else {
			translate = new String(chinese.getBytes(encoding), "ISO-8859-1");
		}

		return translate;
	}

	/**
	 * 文件下载方法
	 * 
	 * 注意! 如果是IE8浏览器记得在"工具 -> SmartScreen筛选器"内选择关闭SmartScreen筛选器, 否则会导致下载文件卡住;
	 * 
	 * @param localFilePath
	 *            本地文件绝对路径;
	 * @param response
	 *            client端response;
	 * @throws Throwable
	 *             发生任意异常时
	 */
	public static void stringDownload(String content,
			HttpServletResponse response) throws Throwable {
		Properties config = null;
		String encoding = null;
		OutputStream output = null;
		InputStream input = null;

		try {
			config = (Properties) CacheManager.getCache(KEY_FILE_UPLOAD);
			encoding = config.getProperty("file_header_encoding") == null ? "UTF-8"
					: config.getProperty("file_header_encoding");

			/* 设置相关属性 */
			response.setCharacterEncoding(encoding);
			response.setContentLength(content.length());

			response.setContentType("text/html");

			/* 写回客户端 */
			output = response.getOutputStream();
			// output.write(content.getBytes());

			input = new ByteArrayInputStream(content.getBytes());

			IOUtils.copy(input, output);

			log.debug("文件下载正常结束");
		} catch (Throwable e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}

	}

	/**
	 * 解析前台传递参数并获取参数列表<br/>
	 * HttpServletRequest.getParameterMap的方法返回map的rawtypes 为
	 * {@code Map<String, String[]>}, 不能为MyBatis所直接使用;<br/>
	 * 该方法用于处理该Map形成{@code Map<String, Object>}进行返回;
	 * 
	 * @param req
	 *            页面请求;
	 * @return 页面参数列表Map;<br/>
	 *         若前台页面参数map的size为0或者空, 则返回空Map;<br/>
	 *         若value数组的长度为1, 则将其转换为string[0];<br/>
	 *         若value数组的长度为0, 则将其转换为""; <br/>
	 *         若value数组的长度为其它, 则保持数组形式不变;
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseWebParas(HttpServletRequest req) {
		Map<String, String[]> paras = req.getParameterMap();
		Map<String, Object> rslt = new HashMap<String, Object>();

		if (paras == null || paras.isEmpty()) {
			return rslt;
		}

		for (Entry<String, String[]> entry : paras.entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();

			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					String v = values[i];
					if (v != null) {
						values[i] = v.trim();
					}
				}
				if (values.length == 1) {
					if (values[0] != null) {
						rslt.put(key, values[0]);
					}
				} else if (values.length > 0) {
					rslt.put(key, values);
				}
			}
		}

		return rslt;
	}

	public static void exportReport(Map<String, Object> params,
			HttpServletResponse response) throws Throwable {
		// String modulePath = null;
		// String reportName = null;
		// String reportFileName = null;
		// InputStream input = null;
		// OutputStream output = null;
		// Connection sqlConnection = null;
		// Properties reportConfig = null;
		//
		// try {
		// /* 初始化参数 */
		// reportConfig = (Properties) CacheManager.getCache(KEY_REPORT);
		// modulePath = reportConfig.getProperty("report.module.path");
		// reportName = ObjectUtils.toString(params.get("reportName"), "");
		//
		// /* 加载报表模板 */
		// input = Resources.getResourceAsStream(modulePath + File.separator
		// + reportName + ".brt");
		// BiosReportTemplate template = new BiosReportTemplate(input);
		//
		// /* 报表头必须为标题 */
		// reportFileName = template.getCellValue(1, 1) + ".xls";
		// log.debug(
		// "准备导出报表; modulePaht=[{}],reportName=[{}],reportFileName=[{}]; Params={}",
		// modulePath, reportName, reportFileName, params);
		//
		// /* 设置返回参数 */
		// response.setCharacterEncoding("UTF-8");
		// response.setContentType("application/octet-stream");
		// response.addHeader("Content-Disposition", "attachment; filename="
		// + URLEncoder.encode(reportFileName, "UTF-8"));
		// output = response.getOutputStream();
		//
		// /* 导出Excel */
		// log.debug("准备回写文件");
		// sqlConnection = getSqlConnection();
		// BiosReportManager reportManager = new BiosReportManager(template,
		// params, null);
		// reportManager.setConnection(sqlConnection);
		// reportManager.calc();
		// reportManager.toExcel(output, null);
		// log.debug("报表导出结束!");
		// } catch (Throwable e) {
		// throw e;
		// } finally {
		// IOUtils.closeQuietly(input);
		// IOUtils.closeQuietly(output);
		// }
	}

	// public static boolean verifyAD(String username, String password)
	// throws Throwable {
	//
	// // 经测试发现，当password为空时，AD认证一定通过
	// // 此处如发现password为空，则判断为不通过
	// if (password == null || ("".equals(password.trim()))) {
	// log.debug("password is null, denied.");
	// return false;
	// }
	//
	// Properties loginCfg = null;
	// String address = null;
	// int port = 389; // 行内AD认证默认389
	// // String domain = null;
	// // String context = null;
	// // LdapContext ldapCtx = null;
	//
	// loginCfg = (Properties) CacheManager.getCache(KEY_LOGIN);
	// address = loginCfg.getProperty("login.AD.address");
	// try {
	// port = Integer.parseInt(loginCfg.getProperty("login.AD.port"));
	// } catch (Throwable t) {
	// port = 389;
	// }
	// // domain = loginCfg.getProperty("login.AD.domain");
	// // context = loginCfg.getProperty("login.AD.context");
	//
	// // Hashtable<String, String> env = new Hashtable<String, String>();
	// // env.put(Context.INITIAL_CONTEXT_FACTORY, context);
	// // env.put(Context.PROVIDER_URL, address);
	// // env.put(Context.SECURITY_AUTHENTICATION, "simple");
	// // env.put(Context.SECURITY_PRINCIPAL, username + "@" + domain);
	// // env.put(Context.SECURITY_CREDENTIALS, password);
	//
	// LDAPConnection con = new LDAPConnection();
	// try {
	// log.debug("start AD auth.");
	// LDAPAttribute ldapAttribute = new LDAPAttribute("userPassword",
	// password.getBytes("UTF-16LE"));
	// con.connect(address, port);
	// con.bind(LDAPConnection.LDAP_V3, username, password);
	// log.debug("AD auth success.");
	// } catch (Throwable t) {
	// log.warn("AD域认证失败");
	// log.error("", t);
	// return false;
	// } finally {
	// try {
	// con.disconnect();
	// } catch (Throwable ex) {
	// ex.printStackTrace();
	// }
	// }
	//
	// // try {
	// // ldapCtx = new InitialLdapContext(env, null);
	// // } catch (AuthenticationException e) {
	// // log.warn("AD域认证失败");
	// // return false;
	// // } catch (Throwable e) {
	// // throw e;
	// // } finally {
	// // if (ldapCtx != null) {
	// // ldapCtx.close();
	// // }
	// // }
	//
	// return true;
	// }

	public static boolean verifyAD(String username, String password)
			throws Throwable {
		Properties loginCfg = null;
		String address = null;
		String domain = null;
		String context = null;
		LdapContext ldapCtx = null;

		loginCfg = (Properties) CacheManager.getCache(KEY_LOGIN);
		address = loginCfg.getProperty("login.AD.address");
		domain = loginCfg.getProperty("login.AD.domain");
		context = loginCfg.getProperty("login.AD.context");

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, context);
		env.put(Context.PROVIDER_URL, address);
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, username + "@" + domain);
		env.put(Context.SECURITY_CREDENTIALS, password);

		try {
			ldapCtx = new InitialLdapContext(env, null);
		} catch (AuthenticationException e) {
			log.warn("AD域认证失败");
			log.error("", e);
			return false;
		} catch (Throwable e) {
			throw e;
		} finally {
			if (ldapCtx != null) {
				ldapCtx.close();
			}
		}

		return true;
	}

	private static boolean isFileImage(File file) throws Throwable {
		if (ImageIO.read(file) == null) {
			return false;
		}

		return true;
	}

	private static boolean isFileHtml(File file) throws Throwable {
		return file.getName().endsWith(".html");
	}

	/**
	 * 计算相差天数<br/>
	 * 主要用于密码提醒
	 * 
	 * @param datebefore
	 *            之前时间 dateafter 之后时间
	 * @return days 相差天数
	 */
	public static int CalDays(Date datebefore, Date dateafter) {
		int days = 0;
		long l = dateafter.getTime() - datebefore.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		days = (int) day;
		return days;
	}
	/**
	 * 
	 * @param Number 阿拉伯数字字符串(不超过百位)
	 * @return 中文书写格式字符串
	 */
	static public String NumberToChinaNumber(String Number) {
		String tmpnewchar = "";
		String rslt = "";

		for (int i = (Number.length() - 1); i >= 0; i--) {
			char value = Number.charAt(i);
			switch (value) {
			case '0':
				tmpnewchar = "零";
				break;
			case '1':
				tmpnewchar = "一";
				break;
			case '2':
				tmpnewchar = "二";
				break;
			case '3':
				tmpnewchar = "三";
				break;
			case '4':
				tmpnewchar = "四";
				break;
			case '5':
				tmpnewchar = "五";
				break;
			case '6':
				tmpnewchar = "六";
				break;
			case '7':
				tmpnewchar = "七";
				break;
			case '8':
				tmpnewchar = "八";
				break;
			case '9':
				tmpnewchar = "九";
				break;
			}
			switch (Number.length() - 1 - i) {
			case 0:
				rslt = tmpnewchar;
				break;
			case 1:
				rslt = tmpnewchar + "十" + rslt;
				break;
			case 2:
				rslt = tmpnewchar + "百" + rslt;
				break;
			case 3:
				rslt = tmpnewchar + "千" + rslt;
				break;
			case 4:
				rslt = tmpnewchar + "万" + rslt;
				break;

			}
		}
		while (rslt.indexOf("十零") > 0 || rslt.indexOf("一十") > 0) {
			rslt = rslt.replaceAll("十零", "十");
			rslt = rslt.replaceAll("一十", "十");
		}
		return rslt;
	}
	/**
	 * /**
	 * csv格式文件转化为excel文件
	 * @param path
	 * @param list 需要将那些列转化为数字
	 * @return  生成的文件路径
	 * @throws Exception
	 */
	
/*	public static String csvToExcel(String path,List<Integer> list)throws Exception{
		//Workbook wb = null;
		String filepath=null;
		File file=new File(path);
		FileInputStream in = new FileInputStream(file);
		if (file.getName().toLowerCase().endsWith(EXCEL_CSV)) {
			BufferedReader br = null;
			HSSFWorkbook workbook = null;
			FileOutputStream os = null;
			int row = 0;
			try {

				String lineTxt = null;
				br = new BufferedReader(new InputStreamReader(in, "GBK"));
				workbook = new HSSFWorkbook();
				HSSFDataFormat format = workbook.createDataFormat();
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setDataFormat(format.getFormat("@"));
				HSSFSheet sheet = workbook.createSheet();
				String uuid = UUID.randomUUID().toString().replace("-", "").substring(0,6);

				Properties config = (Properties) CacheManager
						.getCache("FILE_UPLOAD");
				String saveBase = config.getProperty("file_save_base")
						+ File.separator + "csvToExcel"+ File.separator+uuid;
				File saveDir = new File(saveBase);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}
				 filepath = saveBase + File.separator
						+ getFileName(file.getName()) + ".xls";
				os = new FileOutputStream(filepath);
				
				HSSFRow createRow = null;
				HSSFCell createCell = null;
				while ((lineTxt = br.readLine()) != null) {
					createRow = sheet.createRow(row);
					String[] values = lineTxt.split(",\"");
					for (int i = 0; i < values.length; i++) {
						values[i]=values[i].replaceAll("\"", "");
						values[i]=values[i].replaceAll("\\?$|�$","");
						createCell = createRow.createCell(i);
						//判断是否是需要进行转化成数字的列
						if(list!=null&&list.contains(i+1)&&row!=0){
							createCell.setCellValue(Double.parseDouble(values[i]));
						}else{
						createCell.setCellStyle(cellStyle);
						createCell.setCellValue(values[i]);
						}
					}
					row++;

				}
				workbook.write(os);
				return filepath;
			} catch (Exception e) {
				throw new Exception("csv文件转化为excel文件出错!错误行数:"+(row+1));
			} finally {
				if (br != null) {
					br.close();
				}
				if (workbook != null) {
					workbook.close();
				}
				if (os != null) {
					os.close();
				}
			}
		}
		   //如果不是csv格式就return本身的文件按路径
			return path;
		
		
	}*/
	/**
	 * 获取文件名称
	 */
	public static String getFileName(String fileNameAll) {
		String fileName = null;
		if (StringUtils.isNotEmpty(fileNameAll)) {
			int index = fileNameAll.lastIndexOf(".");
			fileName = fileNameAll.substring(0, index);
		}
		return fileName;
	}
	
	public static Object map2Object(Map<String,Object> map ,Class<?> clz) throws Exception{
		Object obj = clz.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field:fields){
			int mod = field.getModifiers();
			if(Modifier.isStatic(mod)||Modifier.isFinal(mod)){
				continue;
			}
			field.setAccessible(true);
			field.set(obj, map.get(field.getName()));
		}
		return obj;
		
	}
	
	public static Map<String,Object> map2Lower(Map<String,Object> map){
		Map<String,Object> lowermap = new HashMap<String,Object>();
		
		for(Entry<String, Object> entry:map.entrySet()){
			lowermap.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		
		return lowermap;
	}
	
	public static Object map2Object2(Map<String,Object> map ,Class<?> clz) throws Exception{
		Object obj = clz.newInstance();
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String,Object> lowermap = map2Lower(map);
		for(Field field:fields){
			int mod = field.getModifiers();
			if(Modifier.isStatic(mod)||Modifier.isFinal(mod)){
				continue;
			}
			field.setAccessible(true);
			//String fileName = field.getName();
			//fileName = fileName.substring(0,1).toLowerCase()+fileName.substring(1);
			field.set(obj, lowermap.get(field.getName().toLowerCase()));
		}
		return obj;
		
	}
	
	public static Map<String,Object> Obj2Map(Object obj) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field:fields){
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

}
