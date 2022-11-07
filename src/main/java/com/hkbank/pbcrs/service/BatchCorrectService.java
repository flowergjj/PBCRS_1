package com.hkbank.pbcrs.service;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.hkbank.pbcrs.util.ExeclReader3;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hkbank.pbcrs.util.Util;
import com.hkbank.pbcrs.mapper.BaseExternalSpi;
import com.hkbank.pbcrs.mapper.PbcrsReportBatchDelRecordInfoMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportBatchInfoMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportBatchMapper;
import com.hkbank.pbcrs.mapper.PbcrsReportFieldMappingMapper;
import com.hkbank.pbcrs.mapper.PbcrsSysReportconfigMapper;
import com.hkbank.pbcrs.model.PbcrsReportBatch;
import com.hkbank.pbcrs.model.PbcrsReportBatchDel;
import com.hkbank.pbcrs.model.PbcrsReportBatchDelInfo;
import com.hkbank.pbcrs.model.PbcrsReportBatchInfo;
import com.hkbank.pbcrs.model.PbcrsReportFieldMapping;
import com.hkbank.pbcrs.model.PbcrsSysReportconfig;

@Service
@SuppressWarnings("all")
@Slf4j
public class BatchCorrectService {
	@Autowired
	private PbcrsReportFieldMappingMapper fieldMappingMapper;

	@Autowired
	private PbcrsReportBatchMapper batchMapper;

	@Autowired
	private PbcrsReportBatchInfoMapper batchInfoMapper;
    @Autowired
    private ReportCmdService reportCmdService;
    @Autowired
    private PbcrsReportBatchMapper pbcrsReportBatchMapper;
	@Autowired
	private ApplicationContext ac;
	@Autowired
	private PbcrsSysReportconfigMapper reportMapper;
	@Autowired
	private PbcrsReportBatchDelRecordInfoMapper recordMapper;

	public List<Map<String, Object>> getFirstCombox(Map param) {
		return fieldMappingMapper.getFirstCombox(param);
	}

	public List<Map<String, Object>> getSecondCombox(Map params) {
		return fieldMappingMapper.getSecondCombox(params);
	}

	public void analyzeFile(Map<String, Object> params) throws Exception, ParseException {
		String filePath = params.get("filePath").toString();
		String userId = params.get("userid").toString();
		File excelFile = new File(filePath);
		FileInputStream in = new FileInputStream(excelFile);
		ExeclReader3.checkExcelVaild(excelFile);
		Workbook workbook = ExeclReader3.getWorkbok(in, excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		List<String> headList = new ArrayList<String>();
		int number = 0;
		int column = 0;
		
		boolean flag = true;
		String id=UUID.randomUUID().toString();
		for (Row row : sheet) {
			number++;
			if (number == 2) {
				// 取得一共有多少列
				column = row.getLastCellNum();
				for (int i = 0; i < column; i++) {
					if (row.getCell(i) != null && !row.getCell(i).toString().equals("")) {
						row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						String stringCellValue = row.getCell(i).getStringCellValue();
						headList.add(stringCellValue);
					} else {

						break;
					}
				}
			} else if(number > 2) {
				String markNo, dataDate, sourceSys, reportType, sgmtSeqNo = null,rptDate;
				if (row.getCell(0) != null && !row.getCell(0).toString().equals("")) {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					markNo = row.getCell(0).getStringCellValue();
				} else {
					throw new Exception("业务标识号不能为空");
				}
				if (row.getCell(1) != null && !row.getCell(1).toString().equals("")) {
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					dataDate = row.getCell(1).getStringCellValue();
					Date parseDate = DateUtils.parseDate(dataDate, "yyyyMMdd");
					rptDate = DateFormatUtils.format(parseDate, "yyyy-MM-dd");
				} else {
					throw new Exception("数据日期不能为空");
				}
				if (row.getCell(2) != null && !row.getCell(2).toString().equals("")) {
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					sourceSys = row.getCell(2).getStringCellValue();
				} else {
					throw new Exception("系统号不能为空");
				}
				if (row.getCell(3) != null && !row.getCell(3).toString().equals("")) {
					row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
					reportType = row.getCell(3).getStringCellValue();
				} else {
					throw new Exception("报文类型不能为空");
				}
				Map<String, String> param = new HashMap<String, String>();
				param.put("reportType", reportType);
				List<PbcrsReportFieldMapping> mapping = fieldMappingMapper.getMapping(param);
				if (mapping.size() == 0) {
					throw new Exception("无法找到对应的报文类型");
				}
				//企业基本信息报文类型
				String EnReportId = "310";
				//报表报文类型
				String reportIdS ="610,620,630,640,650";
				if(EnReportId.indexOf(mapping.get(0).getReportId()) >= 0){
					//报文类型为企业基本信息时主键拼接
					sgmtSeqNo = markNo + dataDate + sourceSys;
				}
				if(reportIdS.indexOf(mapping.get(0).getReportId()) >= 0){
					//报文类型为报表时主键拼接
					String sheetYear,sheetType,sheetTypeDivide;
					if (row.getCell(4) != null && !row.getCell(4).toString().equals("")) {
						row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
						sheetYear = row.getCell(4).getStringCellValue();
					} else {
						throw new Exception("报表年份不能为空");
					}
					if (row.getCell(5) != null && !row.getCell(5).toString().equals("")) {
						row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
						sheetType = row.getCell(5).getStringCellValue();
					} else {
						throw new Exception("报表类型不能为空");
					}
					if (row.getCell(6) != null && !row.getCell(6).toString().equals("")) {
						row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
						sheetTypeDivide = row.getCell(6).getStringCellValue();
					} else {
						throw new Exception("报表类型细分不能为空");
					}
					sgmtSeqNo = markNo + dataDate + sheetYear + sheetType + sheetTypeDivide;
				}
				//报文类型不属于上述类型主键拼接
				if(EnReportId.indexOf(mapping.get(0).getReportId()) < 0 && reportIdS.indexOf(mapping.get(0).getReportId()) < 0){
					sgmtSeqNo = markNo + dataDate;
				}
				//由于借贷主键带有时点，以上拼接主键逻辑无效，传入时标识号码为拼接好的主键
				sgmtSeqNo = markNo;
				if(flag){
					PbcrsReportBatch batch = new PbcrsReportBatch();
/*					batch.setMarkNo(markNo);
					batch.setDataDate(rptDate);*/
					batch.setOperateDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
					batch.setReportType(reportType);
/*					batch.setSourceSys(sourceSys);
					batch.setSgmtSeqNo(sgmtSeqNo);*/
					batch.setFileName(FilenameUtils.getName(filePath));
					batch.setReportId(mapping.get(0).getReportId());
					batch.setId(id);
					int insertRow = batchMapper.insert(batch);

					if (insertRow != 1) {
						throw new Exception("上传失败！");
					}
					flag = false;
				}

				List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
				for (int i = 4; i < column; i++) {
					if (row.getCell(i) != null && !row.getCell(i).toString().equals("")) {
						row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						String stringCellValue = row.getCell(i).getStringCellValue();
						PbcrsReportBatchInfo batchInfo = new PbcrsReportBatchInfo();
						batchInfo.setSgmtSeqNo(sgmtSeqNo);
						batchInfo.setFieldName(headList.get(i));
						batchInfo.setId(id);
						batchInfo.setMarkNo(markNo);
						batchInfo.setDataDate(rptDate);
						batchInfo.setSourceSys(sourceSys);
						batchInfo.setFieldValue(stringCellValue);
						int insertRow = batchInfoMapper.insert(batchInfo);
						Map<String, String> updateMap = new HashMap<String, String>();
						updateMap.put("fieldName", headList.get(i));
						updateMap.put("fieldValue", stringCellValue);
						updateList.add(updateMap);
						if (insertRow != 1) {
							throw new Exception("上传失败！");
						}
						
					}
				}
				if (updateList.size() > 0)
					modifyData(reportType, updateList, sgmtSeqNo, userId);
			}

		}
		workbook.close();

	}
	
	public int checkFile(Map<String, Object> param){
		int count = batchMapper.checkFileName(param);
		return count;
	}
	
	public int checkFileDel(Map<String, Object> param){
		int count = batchMapper.checkFileNameDel(param);
		return count;
	}
	

	/**
	 * 根据一行数据区对应的表结构中修改数据
	 * 
	 * @param reportType
	 *            报文类型
	 * @param fieldName
	 *            字段名称
	 * @param fieldValue
	 *            字段值
	 * @param sgmtSeqno
	 *            业务主键
	 * @return
	 * @throws Exception
	 */
	public boolean modifyData(String reportType, List<Map<String,String>> updateList, String sgmtSeqno,String userId) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("reportType", reportType);
		param.put("fieldName", updateList.get(0).get("fieldName"));
		List<PbcrsReportFieldMapping> mapping = fieldMappingMapper.getMapping(param);
		if (mapping.size() == 0) {
			throw new Exception("无法找到对应的报文类型");
		}
		PbcrsReportFieldMapping pbcrsReportFieldMapping = mapping.get(0);
		String mapperName = pbcrsReportFieldMapping.getMapperName();
		String serviceName = pbcrsReportFieldMapping.getServiceName();
		String methodName = pbcrsReportFieldMapping.getMethodName();
		BaseExternalSpi bean = (BaseExternalSpi)ac.getBean(mapperName);
		Object object = bean.selectByPrimaryKey(sgmtSeqno);
		if(object == null){
			log.info("批量修改数据未找到,业务主键为:"+sgmtSeqno);
			throw new Exception("批量修改数据数据未找到");
		}
		Object cloneBean = BeanUtils.cloneBean(object);
		Class<? extends Object> class1 = cloneBean.getClass();
		Field[] declaredFields = class1.getDeclaredFields();
		for (Map<String,String> data : updateList) {
			String fieldName = data.get("fieldName");
			String fieldValue=data.get("fieldValue");
			for (Field field : declaredFields) {
				if(field.getName().toUpperCase().equals(fieldName)){
					field.setAccessible(true);
					field.set(cloneBean, fieldValue);
					break;
				}
			}
			
		}
		Map<String, Object> obj2Map = Util.Obj2Map(cloneBean);
		obj2Map.put("updateType", "updSub");//个人
		obj2Map.put("type", "updSub");//企业
		obj2Map.put("userid", userId);
		Object bean2 = ac.getBean(serviceName);
		Class<? extends Object> serviceNameClass = bean2.getClass();
		Method declaredMethod = serviceNameClass.getDeclaredMethod(methodName, Map.class);
		declaredMethod.invoke(bean2, obj2Map);
		return true;
	}
	
	
		//列表
	public Map<String,Object> listPage(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportBatchMapper.selectListCount(params);

			/* 3. 计算记录数 , 避免记录数超过 */
			// while (total > 0 && total <= skip) {
			// skip = skip - pageSize;
			// pageNo = pageNo - 1;
			// }
			/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
			List<Map<String,Object>> list = null;
			if (total <= 0 || total <= skip) {
				list = new ArrayList<Map<String,Object>>();
			} else {
				int endindex= skip+pageSize;
				params.put("endindex", endindex);
				params.put("startindex", skip);
				list = pbcrsReportBatchMapper.selectListPage( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		}
	
	//列表详情
	public Map<String,Object> listPageInfo(Map<String,Object> params){
			
			int pageNo = MapUtils.getIntValue(params, "page");
			int pageSize = MapUtils.getIntValue(params, "rows");
			
			int skip = (pageNo - 1) * pageSize;

			String countKey;
			/* 2. 计算总记录数 */
			int total = pbcrsReportBatchMapper.selectListCountinfo(params);

			/* 3. 计算记录数 , 避免记录数超过 */
			// while (total > 0 && total <= skip) {
			// skip = skip - pageSize;
			// pageNo = pageNo - 1;
			// }
			/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
			List<Map<String,Object>> list = null;
			if (total <= 0 || total <= skip) {
				list = new ArrayList<Map<String,Object>>();
			} else {
				int endindex= skip+pageSize;
				params.put("endindex", endindex);
				params.put("startindex", skip);
				list = pbcrsReportBatchMapper.selectListPageinfo( params);
			}
			Map<String, Object> rslt = new HashMap<String, Object>();
			rslt.put("total", total);
			rslt.put("rows", list);

			return rslt;
			
		}
	
	public void reportBatch(Map<String,Object> params) throws Exception{
		String id = params.get("id").toString();
		//根据id查询文件记录根据dataDate分组
		List<PbcrsReportBatch> listBatchGroup = pbcrsReportBatchMapper.listBatchGroupByDataDate(params);
		if(listBatchGroup != null && listBatchGroup.size() > 0){
			for (int i = 0; i < listBatchGroup.size(); i++) {
				params.put("dataDate", listBatchGroup.get(i).getDataDate());
				//根据id以及报送日期查询一个批次文件下同一天的数据进行报送
				List<PbcrsReportBatch> listBatch = pbcrsReportBatchMapper.listBatch(params);
				if(listBatch != null && listBatch.size() > 0){
					String ids = "";
					for (int j = 0; j < listBatch.size(); j++) {
						if(ids==""){
							ids=ids+"'"+listBatch.get(j).getSgmtSeqNo()+"'";
						}else{
							ids=ids+",'"+listBatch.get(j).getSgmtSeqNo()+"'";
						}
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", ids);
					map.put("reportId", listBatch.get(0).getReportId());
					map.put("etl_date", listBatch.get(0).getDataDate());
					reportCmdService.reportGetFile(map);
				}
			}
		}
		
	}
	/**
	 * 批量删除上传
	 * @param params
	 * @throws Exception
	 * @throws ParseException
	 */
	public void analyzeFileDel(Map<String, Object> params) throws Exception, ParseException {
		String filePath = params.get("filePath").toString();
		String userId = params.get("userid").toString();
		File excelFile = new File(filePath);
		FileInputStream in = new FileInputStream(excelFile);
		ExeclReader3.checkExcelVaild(excelFile);
		Workbook workbook = ExeclReader3.getWorkbok(in, excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		List<String> headList = new ArrayList<String>();
		int number = 0;
		int column = 0;
		
		boolean flag = true;
		String id=UUID.randomUUID().toString();
		for (Row row : sheet) {
			number++;
			if (number == 2) {
				// 取得一共有多少列
				column = row.getLastCellNum();
				for (int i = 0; i < column; i++) {
					if (row.getCell(i) != null && !row.getCell(i).toString().equals("")) {
						row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
						String stringCellValue = row.getCell(i).getStringCellValue();
						headList.add(stringCellValue);
					} else {

						break;
					}
				}
			} else if(number > 2) {
				String markNo = null ,reportType = null, sgmtSeqNo = null;
				if (row.getCell(0) != null && !row.getCell(0).toString().equals("")) {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					markNo = row.getCell(0).getStringCellValue();
				} 
				if (row.getCell(1) != null && !row.getCell(1).toString().equals("")) {
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					reportType = row.getCell(1).getStringCellValue();
				} 
				if(markNo == null && reportType == null){
					break;
				}
				if(markNo == null){
					throw new Exception("主键标识号不能为空");
				}
				if(reportType == null){
					throw new Exception("报文类型不能为空");
				}
				//由于借贷主键带有时点，以上拼接主键逻辑无效，传入时标识号码为拼接好的主键
				sgmtSeqNo = markNo;
				
				PbcrsSysReportconfig config = reportMapper.selectByPrimaryKey(reportType);
				if(config == null){
					throw new Exception("无法找到对应的报文类型");
				}
				if(flag){
					PbcrsReportBatchDel batchDel = new PbcrsReportBatchDel();
					batchDel.setOperateDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
					batchDel.setReportType(config.getREPORTNAME());
					batchDel.setFileName(FilenameUtils.getName(filePath));
					batchDel.setReportId(reportType);
					batchDel.setId(id);
					int insertRow = batchMapper.insertDel(batchDel);

					if (insertRow != 1) {
						throw new Exception("上传失败！");
					}
					flag = false;
				}

				PbcrsReportBatchDelInfo batchDelInfo = new PbcrsReportBatchDelInfo();
				batchDelInfo.setSgmtSeqNo(sgmtSeqNo);
				batchDelInfo.setId(id);
				int insertRow = batchInfoMapper.insertDel(batchDelInfo);
				if (insertRow != 1) {
					throw new Exception("上传失败！");
				}
			    modifyDataDel(reportType, sgmtSeqNo, userId);
			}

		}

		workbook.close();

	}
	
	/**
	 * 根据一行数据区对应的表结构中修改数据
	 * 
	 * @param reportType
	 *            报文类型
	 * @param sgmtSeqno
	 *            业务主键
	 * @return
	 * @throws Exception
	 */
	public boolean modifyDataDel(String reportType,String sgmtSeqno,String userId) throws Exception {
		Map<String,Object> obj = new HashMap<String, Object>();		
		if(reportType.equals("654")){
			obj.put("IncomeAndESSeqNo", sgmtSeqno);
			excMethod("incAndExpStaService","updateByIsDel",obj);
		}else if(reportType.equals("224")){
			obj.put("infoKey", sgmtSeqno);
			excMethod("inCtrctInfService","delete",obj);
		}else if(reportType.equals("644")){
			obj.put("InstitutionBSSeqNo", sgmtSeqno);
			excMethod("insBalSheService","updateByIsDel",obj);
		}else if(reportType.equals("414")){
			obj.put("AcctBsSgmtSeqNo", sgmtSeqno);
			excMethod("enAcctInfService","updateByIsDel",obj);
		}else if(reportType.equals("634")){
			obj.put("CashFlowsSeqNo", sgmtSeqno);
			excMethod("cashFlowsService","updateByIsDel",obj);
		}else if(reportType.equals("624")){
			obj.put("IncomeStatementPASeqNo", sgmtSeqno);
			excMethod("incomesprofitapprService","updateByIsDel",obj);
		}else if(reportType.equals("314")){
			obj.put("BsSgmtSeqNo", sgmtSeqno);
			excMethod("enBasInfBsSgmtService","updateByIsDel",obj);
		}else if(reportType.equals("444")){
			obj.put("GuarAcctBsSgmtSeqNo", sgmtSeqno);
			excMethod("enSecAcctInfService","updateByIsDel",obj);
		}else if(reportType.equals("214")){
			obj.put("infoKey", sgmtSeqno);
			excMethod("inAcctInfService","delete",obj);
		}else if(reportType.equals("614")){
			obj.put("BalanceSheetSeqNo", sgmtSeqno);
			excMethod("balanceSheetService","updateByIsDel",obj);
		}else if(reportType.equals("114")){
			obj.put("infoKey", sgmtSeqno);
			excMethod("inBasInfoService","delete",obj);
		}else if(reportType.equals("514")){
			obj.put("infoKey", sgmtSeqno);
			excMethod("","delete",obj);
		}else if(reportType.equals("424")){
			obj.put("EnCtrctInfSeqNo", sgmtSeqno);
			excMethod("enCtrctInfService","updateByIsDel",obj);
		}
		return true;
	}
	
	public void excMethod(String ServiceName,String methodName,Map<String,Object> obj) throws Exception{
		Object bean2 = ac.getBean(ServiceName);
		Class<? extends Object> serviceNameClass = bean2.getClass();
		Method declaredMethod = serviceNameClass.getDeclaredMethod(methodName, Map.class);
		declaredMethod.invoke(bean2, obj);
	}
	
/**
 * 批量删除列表
 * @param params
 * @return
 */
public Map<String,Object> listPageDel(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportBatchMapper.selectListCountDel(params);

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportBatchMapper.selectListPageDel( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	}

/**
 * 批量删除列表详情查询
 * @param params
 * @return
 */
public Map<String,Object> listPageInfoDel(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = pbcrsReportBatchMapper.selectListCountinfoDel(params);

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = pbcrsReportBatchMapper.selectListPageinfoDel( params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	}
/**
 * 批量删除报送
 * @param params
 * @throws Exception
 */
public void reportBatchDel(Map<String,Object> params) throws Exception{
	String id = params.get("id").toString();
	//根据id查询文件记录根据dataDate分组
	//List<PbcrsReportBatch> listBatchGroup = pbcrsReportBatchMapper.listBatchGroupByDataDate(params);
	//if(listBatchGroup != null && listBatchGroup.size() > 0){
		//for (int i = 0; i < listBatchGroup.size(); i++) {
			//params.put("dataDate", listBatchGroup.get(i).getDataDate());
			//根据id以及报送日期查询一个批次文件下同一天的数据进行报送
			List<PbcrsReportBatchDel> listBatchDel = pbcrsReportBatchMapper.listBatchDel(params);
			if(listBatchDel != null && listBatchDel.size() > 0){
				String ids = "";
				for (int j = 0; j < listBatchDel.size(); j++) {
					if(ids==""){
						ids=ids+"'"+listBatchDel.get(j).getSgmtSeqNo()+"'";
					}else{
						ids=ids+",'"+listBatchDel.get(j).getSgmtSeqNo()+"'";
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", ids);
				map.put("reportId", listBatchDel.get(0).getReportId());
				map.put("etl_date", listBatchDel.get(0).getOperateDate());
				reportCmdService.reportGetFile(map);
			}
		//}
	//}
	
}

/**
 * 按日期和报文类型批量删除
 * @param params
 * @throws Exception
 */
public void reportDel(Map<String,Object> params) throws Exception{
	String queryDate = params.get("queryDate").toString();
	params.put("OperateDate", DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
	params.put("rptDate", queryDate);
	String reportId = params.get("reportId").toString();
	PbcrsSysReportconfig config = reportMapper.selectByPrimaryKey(reportId);
	if(config == null){
		throw new Exception("无法找到对应的报文类型");
	}else{
		params.put("reportType", config.getREPORTNAME());
	}
	int rs = recordMapper.insert(params);
	if(rs > 0){		
		String procName = null;
		queryDate = queryDate.replaceAll("-", "");
		if(reportId.equals("654")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENFINANCECFIN('"+queryDate+"')";
		}else if(reportId.equals("224")){
			procName = "call PKG_RPT_DEL_IND.PRO_PBCRS_RPT_DELINCREDITINFO('"+queryDate+"')";
		}else if(reportId.equals("644")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENFINANCEBSIN('"+queryDate+"')";
		}else if(reportId.equals("414")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENLOANINFO('"+queryDate+"')";
		}else if(reportId.equals("634")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENFINANCECF07('"+queryDate+"')";
		}else if(reportId.equals("624")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENFINANCEPS07('"+queryDate+"')";
		}else if(reportId.equals("314")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENBASEINFO('"+queryDate+"')";
		}else if(reportId.equals("444")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENGUARANTYINFO('"+queryDate+"')";
		}else if(reportId.equals("214")){
			procName = "call PKG_RPT_DEL_IND.PRO_PBCRS_RPT_DELINLOANINFO('"+queryDate+"')";
		}else if(reportId.equals("614")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENFINANCEBS07('"+queryDate+"')";
		}else if(reportId.equals("114")){
			procName = "call PKG_RPT_DEL_IND.PRO_PBCRS_RPT_DELINBASEINFO('"+queryDate+"')";
		}else if(reportId.equals("514")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENGUARANTYCONT('"+queryDate+"')";
		}else if(reportId.equals("424")){
			procName = "call PKG_RPT_DEL_ENT.PRO_PBCRS_RPT_ENCREDITINFO('"+queryDate+"')";
		}
		//执行批量删除的存储过程
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("procName", procName);
		recordMapper.execProc(res);
		//通过socket调用校验加压加密程序
		Map<String,String> map = new HashMap<String, String>();
		//{"ETLDATE":"p_etl_date","SGMTID":"650"}
		map.put("ETLDATE", queryDate);
		map.put("SGMTID", reportId);
		reportCmdService.genFile(map);
	}else{
		throw new Exception("批量删除失败！");
	}
}

/**
 * 批量删除列表
 * @param params
 * @return
 */
public Map<String,Object> listPageDelRpt(Map<String,Object> params){
		
		int pageNo = MapUtils.getIntValue(params, "page");
		int pageSize = MapUtils.getIntValue(params, "rows");
		
		int skip = (pageNo - 1) * pageSize;

		String countKey;
		/* 2. 计算总记录数 */
		int total = recordMapper.selectListCount(params);

		/* 3. 计算记录数 , 避免记录数超过 */
		// while (total > 0 && total <= skip) {
		// skip = skip - pageSize;
		// pageNo = pageNo - 1;
		// }
		/* 3.改：记录数超限以后，不查询，直接返回空结果集 */
		List<Map<String,Object>> list = null;
		if (total <= 0 || total <= skip) {
			list = new ArrayList<Map<String,Object>>();
		} else {
			int endindex= skip+pageSize;
			params.put("endindex", endindex);
			params.put("startindex", skip);
			list = recordMapper.selectListPage(params);
		}
		Map<String, Object> rslt = new HashMap<String, Object>();
		rslt.put("total", total);
		rslt.put("rows", list);

		return rslt;
		
	}

}
