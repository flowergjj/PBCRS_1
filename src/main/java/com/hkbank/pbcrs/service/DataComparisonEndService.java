package com.hkbank.pbcrs.service;

import com.hkbank.pbcrs.util.ExeclReader3;
import com.hkbank.pbcrs.util.StringUtil;
import com.hkbank.pbcrs.mapper.DataComparisonEndMapper;
import com.hkbank.pbcrs.model.DataComparisonEnd;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataComparisonEndService {
	private static final Logger log = LogManager.getLogger(DataComparisonEndService.class);
	@Autowired
	private DataComparisonEndMapper dataCompareMapper;

	public String getEntCompare(Map<String, Object> params) throws Exception {
		StringBuilder builder = null;
		try {
			List<DataComparisonEnd> list = this.dataCompareMapper.Dc(params);
			log.info("list:" + list.size());
			builder = new StringBuilder();
			if ((list != null) && (list.size() > 0)) {
				for (int i = 0; i < list.size(); i++) {
					DataComparisonEnd tmp = (DataComparisonEnd) list.get(i);
					String rowData = tmp.toString();
					if (!tmp.getField17().equals("0")) {
						rowData = rowData + toString(tmp.getArlpName(), tmp.getArlpCerttype(), tmp.getArlpCertnum(),
								tmp.getArlpAmt());
					}
					if (rowData != null) {
						builder.append(rowData);
						builder.append("\r\n");
					}
				}
			}
			List<DataComparisonEnd> listGu = this.dataCompareMapper.Gu(params);
			log.info("listGu:" + listGu.size());
			if ((listGu != null) && (listGu.size() > 0)) {
				for (int i = 0; i < listGu.size(); i++) {
					DataComparisonEnd tmp = (DataComparisonEnd) listGu.get(i);
					String rowData = tmp.toString();
					if (!tmp.getField17().equals("0")) {
						rowData = rowData + toString(tmp.getArlpName(), tmp.getArlpCerttype(), tmp.getArlpCertnum(),
								tmp.getArlpAmt());
					}
					if (rowData != null) {
						builder.append(rowData);
						builder.append("\r\n");
					}
				}
			}
			List<DataComparisonEnd> listSx = this.dataCompareMapper.Sx(params);
			log.info("listSx:" + listSx.size());
			if ((listSx != null) && (listSx.size() > 0)) {
				for (int i = 0; i < listSx.size(); i++) {
					DataComparisonEnd tmp = (DataComparisonEnd) listSx.get(i);
					String rowData = tmp.toString();
					if (!tmp.getField17().equals("0")) {
						rowData = rowData + toString(tmp.getArlpName(), tmp.getArlpCerttype(), tmp.getArlpCertnum(),
								tmp.getArlpAmt());
					}
					if (rowData != null) {
						builder.append(rowData);
						builder.append("\r\n");
					}
				}
			}
			List<DataComparisonEnd> listDzy = this.dataCompareMapper.Dzy(params);
			log.info("listDzy:" + listDzy.size());
			if ((listDzy != null) && (listDzy.size() > 0)) {
				for (int i = 0; i < listDzy.size(); i++) {
					DataComparisonEnd tmp = (DataComparisonEnd) listDzy.get(i);
					String rowData = tmp.toString();
					if (!tmp.getField17().equals("0")) {
						rowData = rowData + toString(tmp.getArlpName(), tmp.getArlpCerttype(), tmp.getArlpCertnum(),
								tmp.getArlpAmt());
					}
					if (rowData != null) {
						builder.append(rowData);
						builder.append("\r\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return builder.toString();
	}

	public String toString(String arlpName, String arlpCerttype, String arlpCertnum, String arlpAmt) {
		String str = "";
		String[] names = arlpName.split(",");
		String[] certTypes = arlpCerttype.split(",");
		String[] certNums = arlpCertnum.split(",");
		String[] Amts = arlpAmt.split(",");
		if (names.length > 0) {
			for (int i = 0; i < names.length; i++) {
				str = str + "|!" + names[i] + "|!" + certTypes[i] + "|!" + certNums[i] + "|!" + Amts[i];
			}
		}
		return str;
	}

	public Map<String, Object> listPage(Map<String, Object> params) {
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");

		int skip = (pageNo - 1) * pageSize;

		int total = this.dataCompareMapper.getCount(params);

		List<Map<String, Object>> list = null;
		if ((total <= 0) || (total <= skip)) {
			list = new ArrayList();
		} else {
			int endindex = skip + pageSize;
			params.put("endindex", Integer.valueOf(endindex));
			params.put("startindex", Integer.valueOf(skip));
			list = this.dataCompareMapper.dataList(params);
		}
		Map<String, Object> rslt = new HashMap();
		rslt.put("total", Integer.valueOf(total));
		rslt.put("rows", list);

		return rslt;
	}

	public Map<String, Object> listDetailPage(Map<String, Object> params) {
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");

		int skip = (pageNo - 1) * pageSize;
		
		int total = 0;
		if(params.containsKey("type")) {
			if(params.get("type").toString().equals("data")) {
				params.put("type1", "data");
				params.put("type2", "report");
			}else {
				params.put("type2", "data");
				params.put("type1", "report");
			}
			total = dataCompareMapper.getDetailCompareCount(params);
		}else {
			total = this.dataCompareMapper.getDetailCount(params);
		}
		

		List<Map<String, Object>> list = null;
		if ((total <= 0) || (total <= skip)) {
			list = new ArrayList();
		} else {
			int endindex = skip + pageSize;
			params.put("endindex", Integer.valueOf(endindex));
			params.put("startindex", Integer.valueOf(skip));
			if(params.containsKey("type")) {
				list = this.dataCompareMapper.dataDetailListCompare(params);
			}else {
				list = this.dataCompareMapper.dataDetailList(params);
			}
		}
		Map<String, Object> rslt = new HashMap();
		rslt.put("total", Integer.valueOf(total));
		rslt.put("rows", list);

		return rslt;
	}

	public String getEntByNo(Map<String, Object> params) throws Exception {
		StringBuilder builder = null;
		try {
			List<DataComparisonEnd> list = this.dataCompareMapper.getEntCompare(params);
			builder = new StringBuilder();
			if ((list != null) && (list.size() > 0)) {
				for (int i = 0; i < list.size(); i++) {
					DataComparisonEnd tmp = (DataComparisonEnd) list.get(i);
					String rowData = tmp.toString();
					if (rowData != null) {
						builder.append(rowData);
						builder.append("\r\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
		return builder.toString();
	}

	public int upd(Map<String, Object> params) throws Exception {
		String fieldValue = params.get("fieldValue").toString();
		String field = params.get("field").toString();
		String param = field + " = " + "'" + fieldValue + "'";
		params.put("param", param);
		return this.dataCompareMapper.upd(params);
	}

	public void analyzeFile(Map<String, Object> params) throws Exception, ParseException {
		String filePath = params.get("filePath").toString();
		String userId = params.get("userid").toString();
		File excelFile = new File(filePath);
		if (filePath.indexOf(".csv") > -1) {
			if (excelFile.exists()) {
				List<String> list = FileUtils.readLines(excelFile, "GBK");
				if ((list != null) && (list.size() > 0)) {
					for (int i = 0; i < list.size(); i++) {
						String line = (String) list.get(i);
						String[] row = line.split(",");
						for (int j = 0; j < row.length; j++) {
							System.out.println(row[j]);
						}
					}
				}
			}
		} else {
			FileInputStream in = new FileInputStream(excelFile);
			ExeclReader3.checkExcelVaild(excelFile);
			Workbook workbook = ExeclReader3.getWorkbok(in, excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			List<String> headList = new ArrayList();
			int number = 0;
			int column = 0;

			boolean flag = true;
			String id = UUID.randomUUID().toString();
			for (Row row : sheet) {
				number++;
				if (number == 2) {
					column = row.getLastCellNum();
					for (int i = 0; i < column; i++) {
						if ((row.getCell(i) == null) || (row.getCell(i).toString().equals(""))) {
							break;
						}
						row.getCell(i).setCellType(1);
						String stringCellValue = row.getCell(i).getStringCellValue();
						headList.add(stringCellValue);
					}
				} else if (number > 2) {
					String markNo = null;
					String reportType = null;
					String sgmtSeqNo = null;
					if ((row.getCell(0) != null) && (!row.getCell(0).toString().equals(""))) {
						row.getCell(0).setCellType(1);
						markNo = row.getCell(0).getStringCellValue();
					}
					if ((row.getCell(1) != null) && (!row.getCell(1).toString().equals(""))) {
						row.getCell(1).setCellType(1);
						reportType = row.getCell(1).getStringCellValue();
					}
					if ((markNo == null) && (reportType == null)) {
						break;
					}
					if (markNo == null) {
						throw new Exception("主键标示不能为空");
					}
					if (reportType == null) {
						throw new Exception("报文类型不能为空");
					}
					sgmtSeqNo = markNo;
				}
			}
			workbook.close();
		}
	}

	public String getEntCompareByInter(Map<String, Object> params) throws Exception {
		StringBuilder builder = null;
		try {
			List<DataComparisonEnd> list = this.dataCompareMapper.getEntCompare(params);
			log.info("list:" + list.size());
			builder = new StringBuilder();
			if ((list != null) && (list.size() > 0)) {
				for (int i = 0; i < list.size(); i++) {
					DataComparisonEnd tmp = (DataComparisonEnd) list.get(i);
					String rowData = tmp.toStringByStr();
					if (StringUtil.isNotEmpty(tmp.getArlpName())) {
						rowData = rowData + toStringByInter(tmp.getArlpName(), tmp.getArlpCerttype(),
								tmp.getArlpCertnum(), tmp.getArlpAmt());
					} else {
						rowData = tmp.toString();
					}
					if (rowData != null) {
						builder.append(rowData);
						builder.append("\r\n");
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return builder.toString();
	}

	public String toStringByInter(String arlpName, String arlpCerttype, String arlpCertnum, String arlpAmt) {
		String str = "";
		String[] names = arlpName.split(",");
		String[] certTypes = arlpCerttype.split(",");
		String[] certNums = arlpCertnum.split(",");
		String[] Amts = arlpAmt.split(",");
		if (names.length > 0) {
			str = str + "|!" + names.length;			
			for (int i = 0; i < names.length; i++) {
				str = str + "|!" + names[i] + "|!" + certTypes[i] + "|!" + certNums[i] + "|!" + Amts[i];
			}
		}
		return str;
	}
	
	//将企业两端比对集市层
	public void add(Map<String, Object> params) throws Exception {
		//新增需要创建分区
		params.put("rpt", params.get("queryRptDate").toString().replace("-", ""));
		dataCompareMapper.callPartition(params);
		//新增借贷报送层比对数据
		dataCompareMapper.addDc(params);
		log.info("借贷报送层比对数据新增成功："+params.get("queryRptDate"));
		dataCompareMapper.addGu(params);
		log.info("担保报送层比对数据新增成功："+params.get("queryRptDate"));
		dataCompareMapper.addSx(params);
		log.info("授信报送层比对数据新增成功："+params.get("queryRptDate"));
		dataCompareMapper.addDzy(params);
		log.info("抵质押报送层比对数据新增成功："+params.get("queryRptDate"));
		
		//新增集市层比对数据
		dataCompareMapper.addDcInter(params);
		log.info("借贷集市层比对数据新增成功："+params.get("queryRptDate"));
		dataCompareMapper.addGuInter(params);
		log.info("担保集市层比对数据新增成功："+params.get("queryRptDate"));
		dataCompareMapper.addSxInter(params);
		log.info("授信集市层比对数据新增成功："+params.get("queryRptDate"));
		dataCompareMapper.addDzyInter(params);
		log.info("抵质押集市层比对数据新增成功："+params.get("queryRptDate"));

	}

}
