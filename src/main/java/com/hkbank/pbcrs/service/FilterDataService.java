package com.hkbank.pbcrs.service;

import com.hkbank.pbcrs.util.ExeclReader3;
import com.hkbank.pbcrs.mapper.FilterDataMapper;
import com.hkbank.pbcrs.model.FilterData;
import com.hkbank.pbcrs.model.PbcrsReportBatchDel;
import com.hkbank.pbcrs.model.PbcrsReportBatchDelInfo;
import com.hkbank.pbcrs.model.PbcrsSysReportconfig;
import com.sun.javafx.binding.StringFormatter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @description: 过滤信息处理业务类
 * @author: HS1112
 * @date: 2022-01-20 14:51
 */
@Service
public class FilterDataService {
    @Autowired
    private FilterDataMapper filterDataMapper;

    public Map<String, Object> selectByPage(Map<String, Object> param){
        List<FilterData> list;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        param = getStartAndEnd(param);
        int total = filterDataMapper.selectCount(param);
        if (total <= 0 || total < MapUtils.getInteger(param, "startindex")) {
            list = new ArrayList();
        } else {
            // 查询列表
            list = filterDataMapper.selectList(param);
            if (list == null) {
                list = new ArrayList();
            }
        }
        resultMap.put("total", total);
        resultMap.put("rows", list);
        return resultMap;
    }

    // 分页参数封装
    public Map<String, Object> getStartAndEnd(Map<String, Object> param) {
        int pageNo = MapUtils.getIntValue(param, "page");
        int pageSize = MapUtils.getIntValue(param, "rows");
        int skip = (pageNo - 1) * pageSize + 1;
        int endindex = skip + pageSize;
        param.put("endindex", endindex);
        param.put("startindex", skip);
        return param;
    }

    public int insert(Map<String, Object> param) {
        FilterData filterData = new FilterData();
        filterData.setName(param.get("name").toString());
        filterData.setId(param.get("certNum").toString());
        filterData.setSourcecustID(param.get("sourcecustId").toString());
        SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        filterData.setCreateDate(formatYMD.format(new Date()));
        filterData.setCreateTime(formatYMDHMS.format(new Date()));
        filterData.setIsEnable("Y");
        if(param.get("reason") != null){
            filterData.setReason(param.get("reason").toString());
        }
        int insert = filterDataMapper.insert(filterData);
        return insert;
    }

    public int delete(Map<String, Object> param) {
        String seqNo = param.get("seqNo").toString();
        int delete = filterDataMapper.delete(seqNo);
        return delete;
    }

    public int updateEnable(Map<String, Object> param) {
        String seqNo = param.get("seqNo").toString();
        String isEnable = param.get("isEnable").toString();
        int update = filterDataMapper.updateIsEnableBySeqno(seqNo, isEnable);
        return update;
    }

    public String analyzeFileAdd(Map<String, Object> params) throws Exception {
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
                String sourcecustID = null ,name = null, id = null,reason=null;
                if (row.getCell(0) != null && !row.getCell(0).toString().equals("")) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    sourcecustID = row.getCell(0).getStringCellValue();
                }
                if (row.getCell(1) != null && !row.getCell(1).toString().equals("")) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    name = row.getCell(1).getStringCellValue();
                }
                if (row.getCell(2) != null && !row.getCell(2).toString().equals("")) {
                    row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                    id = row.getCell(2).getStringCellValue();
                }
                if (row.getCell(3) != null && !row.getCell(3).toString().equals("")) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    reason = row.getCell(3).getStringCellValue();
                }
                if(sourcecustID == null || name == null || id == null){
                    return "客户号/名称/证件号码不能为空或空字符串";
                }

              /*  String str = "行数:{0},客户号:{1},名称:{2},证件号码:{3},原因:{4}";
                System.out.println(MessageFormat.format(str,number,sourcecustID,name,id,reason));*/
                FilterData filterData = new FilterData();
                filterData.setName(name);
                filterData.setId(id);
                filterData.setSourcecustID(sourcecustID);
                SimpleDateFormat formatYMD = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatYMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                filterData.setCreateDate(formatYMD.format(new Date()));
                filterData.setCreateTime(formatYMDHMS.format(new Date()));
                filterData.setIsEnable("Y");
                if(reason != null){
                    filterData.setReason(reason);
                }
                filterDataMapper.insert(filterData);
            }

        }

        workbook.close();
        return null;
    }

    public String analyzeFileDel(Map<String, Object> params) throws Exception {
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
                String sourcecustID = null ;
                if (row.getCell(0) != null && !row.getCell(0).toString().equals("")) {
                    row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    sourcecustID = row.getCell(0).getStringCellValue();
                }

                if(sourcecustID == null ){
                    return "客户号不能为空或空字符串";
                }

                filterDataMapper.deleteBysourcecustID(sourcecustID);
            }

        }

        workbook.close();
        return null;
    }

    public List<FilterData> getList() {
       return filterDataMapper.getList();
    }
}
