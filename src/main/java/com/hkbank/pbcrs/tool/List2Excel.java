package com.hkbank.pbcrs.tool;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 数据导出到excel
 * 
 * @author wujx
 * @since 1.0, Sep 12, 2012
 */
public class List2Excel {
    /**
     * 设置或获取工作本显示的名称
     */
     private String sheetName;
     /**
      * 设置或获取标题
      */
     private String title;
     /**
      * 设置或获取页眉
      */
     private String[][] header = null;
     /**
      * 设置或获取页脚
      */
     private String[][] footer = null;
     /**
      * 是否隐藏打印时间
      */
     private boolean hideDate = false;
     /**
      * 用于分页的最大记录数
      */
     private int recordNum = 50000;

     private List<Integer> columnWidths ;
      
      
     public String getSheetName() {
        return sheetName;
    }
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String[][] getHeader() {
        return header;
    }
    public void setHeader(String[][] header) {
        this.header = header;
    }
    public String[][] getFooter() {
        return footer;
    }
    public void setFooter(String[][] footer) {
        this.footer = footer;
    }
    public boolean isHideDate() {
        return hideDate;
    }
    public void setHideDate(boolean hideDate) {
        this.hideDate = hideDate;
    }
    public int getRecordNum() {
        return recordNum;
    }
    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    public List<Integer> getColumnWidths() {
        return columnWidths;
    }

    public void setColumnWidths(List<Integer> columnWidths) {
        this.columnWidths = columnWidths;
    }

    /**
     * 导出数据到Excel
     * @param dataSource 数据源
     * @param filePath 导出的路径
     * @return
     */
    public String toExcel(List<Map<String,Object>> dataSource, String filePath)  {
        if (dataSource == null){
            return null;
        }
        //创建一个工作簿
        HSSFWorkbook book = new HSSFWorkbook();
        //行数
        int rCount = dataSource.size() > 0 ? dataSource.size()-1 : 0;
        //列数
        int cCount = dataSource.size() == 0 ? 0 : dataSource.get(0).size();
        //获取分页工作表的个数
        int sheetCount = getSheetCount(rCount);
        if (sheetCount > 1){
            //初始化工作表的个数
            for (int i = 1; i <= sheetCount; i++){
                //添加一个工作表
                book.createSheet(isNullOrEmpty(sheetName)? "Sheet-" + i : sheetName + "-" + i);
            }
        } else {
            //添加一个工作表
            book.createSheet(isNullOrEmpty(sheetName)? "Sheet": sheetName );
        }
        HSSFCellStyle titleStyle=book.createCellStyle();
        //设置这些样式
        HSSFFont font = book.createFont();
        font.setBold(true);
        titleStyle.setFont(font);
        //标题居中对齐
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //生成标题样式
        for (int index = 0, rowIndex = 1; index < sheetCount; index++) {
            //获取每页的记录数
            int rows = getRows(rCount, index + 1);
            //获取工作簿的第一个工作表
            HSSFSheet sheet = book.getSheetAt(index);
            HSSFRow row = null;
            HSSFCell cell = null;
            Object value = null;
            //前置行数
            int frontRow = 0;
            //设置页眉和标题
            //设置标题
            if (!isNullOrEmpty(title)) {
                //合并标题行
                sheet.addMergedRegion(new CellRangeAddress(frontRow,0,frontRow,cCount-1));
                row = sheet.createRow(frontRow);
                cell = row.createCell(0);
                //设置行高
                /*row.setHeightInPoints(35);*/
                cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
                cell.setCellValue(title);
                cell.setCellStyle(titleStyle);
                frontRow++;
            }
             
            //页眉设置
            if (header != null) {
                int Rows = header[0].length;
                int Fields = header[1].length;
                for (int i = 0; i < Rows; i++) {
                    row = sheet.createRow(frontRow);
                    //设置行高
                    row.setHeightInPoints(17);
                    for (int j = 0; j < Fields; j++) {
                        cell = row.createCell(j);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        //设置数据部分
                        cell.setCellValue(header[i][j]);
                        //设置字体大小
                        //cell.Style.Font.Size = 11;
                    }
                    frontRow++;
                }
                frontRow++;
            }
            if (columnWidths != null) {
                for (int i = 0; i < columnWidths.size(); i++) {
                    //设置每一列的宽度
                    sheet.setColumnWidth(i,columnWidths.get(i));
                }
            }
            //数据写入
            if(dataSource.size() > 0 && cCount >0){
                //导出表头部分
                row = sheet.createRow(frontRow);
                //设置行高
                row.setHeightInPoints(17);
                //临时计数器
                int tally = 0;
                for (Entry<String,Object> entry : dataSource.get(0).entrySet()) {
                    cell = row.createCell(tally);                   
                    /* cell.setCellStyle(headStyle);*/
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
                    value = entry.getValue() == null ? "" : entry.getValue() + "";
                    //设置表头
                    cell.setCellValue(value + "");
                    //sheet.setColumnWidth(tally,(value+"").getBytes().length * 256*2);
                    tally++;
                }
                frontRow++;
                 
                // 导出数据部分
                for (int i = 0; i < rows; i++) {
                    row = sheet.createRow(frontRow);
                    //设置行高
                    row.setHeightInPoints(17);
                    tally = 0;
                    for (Entry<String,Object> entry : dataSource.get(0).entrySet()) {
                        value = dataSource.get(rowIndex).get(entry.getKey());
                        cell = row.createCell(tally);                        
                        //cell.setCellStyle(bodyStyle);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
                        //设置数据部分 
                        if (value!=null){
                            if(entry.getValue() instanceof Boolean)
                                cell.setCellValue((Boolean) value ? "是" : "否");
                            if(entry.getValue() instanceof Date)
                                cell.setCellValue(value+"");//DateUtils.format((Date) entry.getValue(), "yyyy-MM-dd"));
                            else
                                cell.setCellValue(value==null?"":value+"");
                        }
                        //sheet.setColumnWidth(tally,(value+"").getBytes().length * 256);
                        tally++;
                    }
                    frontRow++;
                    rowIndex++;
                }
                frontRow++;
            }
 
            //设置页脚
            //设置页脚数据
            if (footer != null) {
                int Rows = footer[0].length;
                int Fields = footer[1].length;
                for (int i = 0; i < Rows; i++) {
                    row = sheet.createRow(frontRow);
                    row.setHeightInPoints(17);//设置行高
                    for (int j = 0; j < Fields; j++) {
                        cell = row.createCell(j);
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
                        cell.setCellValue(footer[i][j]);//设置数据部分                       
                        //cell.Style.Font.Size = 11;//设置字体大小
                    }
                    frontRow++;
                }
            }
 
            if (!hideDate){//时间落款                
                row = sheet.createRow(frontRow);
                row.setHeightInPoints(17);//设置行高
                cell = row.createCell(cCount > 1 ? cCount - 2 : cCount);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
                //cell.setCellValue("制表时间：" + DateUtils.getCurrDateStr());//设置数据部分       
                //cell.Style.Font.Size = 11;//设置字体大小
                frontRow++;
            }
 
            if (sheetCount > 1) {
                row = sheet.createRow(frontRow);
                row.setHeightInPoints(17);//设置行高
                cell = row.createCell(cCount > 1 ? cCount - 2 : cCount);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("<共" + sheetCount + "页，第" + (index + 1) + "页>");//设置数据部分       
                //cell.Style.Font.Size = 11;//设置字体大小                    
            }
//            for(int i=0; i<cCount; i++){
//              sheet.autoSizeColumn(i);//自动分布列宽
//              sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
//            }
        }
        try  {
            filePath = filePath.replace("/", File.separator).replace("\\\\", File.separator);
            File destDir = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(filePath);
            book.write(fileOut);
            fileOut.close();
            return filePath;
         } catch (Exception e) {
            e.printStackTrace();
        }finally{
             
        }
        return null;
    }
         
     
    /**
     * 确定分页的个数
     * @param rCount 总得记录条数
     * @return
     */
    private int getSheetCount(int rCount){
        if (recordNum <= 0)
            return 1;
        if (rCount <= 0) return 1;
        int n = rCount % recordNum; //余数
        if (n == 0) {
            return rCount / recordNum;
        } else {
            return (int)(rCount / recordNum) + 1;
        }
    }
     
    /**
     * 确定每页的记录数
     * @param rCount 总得记录条数
     * @param page 当前页码
     * @return
     */
    private int getRows(int rCount, int page){
        if (recordNum <= 0)
            return rCount;
 
        if (rCount - page * recordNum >= 0)
            return recordNum;
        else
            return rCount % recordNum; //余数
    }
     
    private boolean isNullOrEmpty(Object value){
        if (value == null || value.toString().length() == 0) {
            return true;
        }
        return false;
    }
}
