package com.hkbank.pbcrs.util;

import com.hkbank.pbcrs.cache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

public class ExeclReader3 {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";
	private static final String EXCEL_CSV = "csv";
	public static void checkExcelVaild(File excelFile) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param
	 * @return
	 * @throws Throwable
	 */
	public static Workbook getWorkbok(InputStream in, File file)
			throws Exception {
		Workbook wb = null;
		if (file.getName().toLowerCase().endsWith(EXCEL_XLS)) { // Excel 2003
			try {
				wb = new HSSFWorkbook(in);
			} catch (Exception e) {
				throw new Exception("导入文件格式不正确,请将文件转化为2003版excel!");
			}

		} else if (file.getName().toLowerCase().endsWith(EXCEL_XLSX)) { // Excel
																		// 2007/2010
			try {
				wb = new XSSFWorkbook(in);
			} catch (Exception e) {
				throw new Exception("导入文件格式不正确,请将文件转化为2007版excel!");
			}
          //已废弃的判断
		} else if (file.getName().toLowerCase().endsWith(EXCEL_CSV)) {
			BufferedReader br = null;
			HSSFWorkbook workbook = null;
			FileOutputStream os = null;
			try {

				String lineTxt = null;
				br = new BufferedReader(new InputStreamReader(in, "GBK"));
				workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet();
				String uuid = UUID.randomUUID().toString().replace("-", "");

				Properties config = (Properties) CacheManager
						.getCache("FILE_UPLOAD");
				String saveBase = config.getProperty("file_save_base")
						+ File.separator + "csvToExcel";
				File saveDir = new File(saveBase);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}
				String filepath = saveBase + File.separator
						+ getFileName(file.getName()) + uuid + ".xls";
				os = new FileOutputStream(filepath);
				int row = 0;
				HSSFRow createRow = null;
				HSSFCell createCell = null;
				while ((lineTxt = br.readLine()) != null) {
					createRow = sheet.createRow(row);
					String[] values = lineTxt.split("\",\"|\\?,\"");
					for (int i = 0; i < values.length; i++) {
						values[i]=values[i].replaceAll("\"", "");
						createCell = createRow.createCell(i);
						createCell.setCellValue(values[i]);
					}
					row++;

				}
				workbook.write(os);
				wb = workbook;
			} catch (Exception e) {
				throw new Exception("csv文件转化为excel文件出错!");
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

		return wb;
	}

	/**
	 * 判断文件是否是excel
	 * 
	 * @throws Exception
	 
	public static void checkExcelVaild(File file) throws Exception {
		if (!file.exists()) {
			throw new Exception("文件不存在");
		}
		if (!(file.isFile() && (file.getName().toLowerCase()
				.endsWith(EXCEL_XLS)
				|| file.getName().toLowerCase().endsWith(EXCEL_XLSX) || file
				.getName().toLowerCase().endsWith(EXCEL_CSV)))) {
			throw new Exception("文件不是Excel");
		}
	}

	/**
	 * 读取Excel测试，兼容 Excel 2003/2007/2010
	 * 
	 * @throws Exception
	 *//*
	public static void main(String[] args) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 同时支持Excel 2003、2007
			File excelFile = new File(
					"F:\\Hans\\Project\\股权管理系统\\需求评审\\需求评审\\需求4.股东大会流程\\股东大会流程管理.xls"); // 创建文件对象
			FileInputStream in = new FileInputStream(excelFile); // 文件流
			checkExcelVaild(excelFile);
			Workbook workbook = getWorkbok(in, excelFile);
			// Workbook workbook = WorkbookFactory.create(is); // 这种方式
			// Excel2003/2007/2010都是可以处理的

			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			*//**
			 * 设置当前excel中sheet的下标：0开始
			 *//*
			Sheet sheet = workbook.getSheetAt(4); // 遍历第一个Sheet
			// Sheet sheet = workbook.getSheetAt(2); // 遍历第三个Sheet

			// 获取总行数
			// System.out.println(sheet.getLastRowNum());

			// 为跳过第一行目录设置count
			int count = 0;
			for (Row row : sheet) {
				try {
					// 跳过第一和第二行的目录
					
					 * if(count < 1 ) { count++; continue; }
					 
					// 如果当前行没有数据，跳出循环
					if (row.getCell(0).toString().equals("")) {
						return;
					}

					// 获取总列数(空格的不计算)
					int columnTotalNum = row.getPhysicalNumberOfCells();
					System.out.println("总列数：" + columnTotalNum);

					System.out.println("最大列数：" + row.getLastCellNum());

					// for循环的，不扫描空格的列
					// for (Cell cell : row) {
					// System.out.println(cell);
					// }
					int end = row.getLastCellNum();
					for (int i = 0; i < end; i++) {
						Cell cell = row.getCell(i);
						if (cell == null) {
							System.out.print("null" + "\t");
							continue;
						}

						Object obj = getValue(cell);
						System.out.print(obj + "\t");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getValue(Cell cell) {
		Object obj = null;
		switch (cell.getCellTypeEnum()) {
		case BOOLEAN:
			obj = cell.getBooleanCellValue();
			break;
		case ERROR:
			obj = cell.getErrorCellValue();
			break;
		case NUMERIC:
			obj = cell.getNumericCellValue();
			break;
		case STRING:
			obj = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return obj;
	}

	*//**
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

}
