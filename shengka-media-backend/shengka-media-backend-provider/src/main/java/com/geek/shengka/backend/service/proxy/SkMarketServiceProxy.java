package com.geek.shengka.backend.service.proxy;

import com.geek.shengka.backend.constant.CommonConstant;
import com.geek.shengka.backend.constant.ErrorCode;
import com.geek.shengka.backend.entity.InvalidFailMsg;
import com.geek.shengka.backend.exception.BaseException;
import com.geek.shengka.backend.params.req.SkModProfitAddReqParam;
import com.geek.shengka.backend.params.req.SklSdsrAddReqParam;
import com.geek.shengka.backend.params.req.SkspScCitedAddReqParam;
import com.geek.shengka.backend.service.SkModProfitService;
import com.geek.shengka.backend.service.SkSdsrService;
import com.geek.shengka.backend.service.SkspScCitedService;
import com.geek.shengka.backend.util.EmptyUtils;
import com.geek.shengka.backend.util.InvalidMsgUtils;
import com.geek.shengka.backend.util.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: zzy
 * @Date: 2019/8/14 17:49
 * @Version 1.0
 */
@Component
@Slf4j
public class SkMarketServiceProxy {

    private static final String PROFIT = "模块收入";

    private static final String SDSR = "收支总计";

    private static final String CAC = "市场支出";

    @Autowired
    private SkModProfitService skModProfitService;

    @Autowired
    private SkspScCitedService skspScCitedService;

    @Autowired
    private SkSdsrService skSdsrService;

    /**
     * 解析excel
     *
     * @param fis
     * @param userId
     * @return
     */
    public List<String> parseExcel(InputStream fis, String userId) throws BaseException {
        List<String> list = new ArrayList<>();
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(fis);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BaseException(ErrorCode.SYS_ERROR.getCode(), ErrorCode.SYS_ERROR.getMsg());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        //遍历3张sheet页
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while (sheetIterator.hasNext()) {
            List<Map<String, String>> data = new ArrayList<>();
            Sheet sheet = sheetIterator.next();
            String sheetName = sheet.getSheetName();
            //第一行序号
            int firstRow = sheet.getFirstRowNum();
            //最后一行序号
            int lastRow = sheet.getLastRowNum();
            //表头行
            Row titleRow = sheet.getRow(firstRow);
            for (int i = firstRow + 2; i < lastRow + 1; i++) {
                //获取第i行
                Row row = sheet.getRow(i);
                //获取第i行数据
                Map<String, String> map = getRowData(row, titleRow, list, sheetName, i);
                map.put("createBy", userId);
                data.add(map);
            }
            //当没有错误验证信息时继续执行
            if (EmptyUtils.isEmpty(list)) {
                log.warn(sheetName);
                switch (sheetName) {
                    case PROFIT:
                        if (!EmptyUtils.isEmpty(data)) {
                            List<SkModProfitAddReqParam> skModProfitList = MapUtils.mapToObject(data, SkModProfitAddReqParam.class);
                            if (EmptyUtils.isEmpty(skModProfitList)) {
                                throw new BaseException(ErrorCode.MAP_TO_BEAN_FAIL.getCode(), ErrorCode.MAP_TO_BEAN_FAIL.getMsg());
                            }
                            skModProfitService.uploadData(skModProfitList);
                        }
                        break;
                    case SDSR:
                        if (!EmptyUtils.isEmpty(data)) {
                            List<SklSdsrAddReqParam> sklSdsrs = MapUtils.mapToObject(data, SklSdsrAddReqParam.class);
                            if (EmptyUtils.isEmpty(sklSdsrs)) {
                                throw new BaseException(ErrorCode.MAP_TO_BEAN_FAIL.getCode(), ErrorCode.MAP_TO_BEAN_FAIL.getMsg());
                            }
                            skSdsrService.uploadData(sklSdsrs);
                        }
                        break;
                    case CAC:
                        if (!EmptyUtils.isEmpty(data)) {
                            List<SkspScCitedAddReqParam> scCitedList = MapUtils.mapToObject(data, SkspScCitedAddReqParam.class);
                            if (EmptyUtils.isEmpty(scCitedList)) {
                                throw new BaseException(ErrorCode.MAP_TO_BEAN_FAIL.getCode(), ErrorCode.MAP_TO_BEAN_FAIL.getMsg());
                            }
                            skspScCitedService.uploadData(scCitedList);
                        }
                        break;
                    default:
                        list.add(MessageFormat.format(CommonConstant.SHEET_INVALID_FAIL, sheetName));
                        break;

                }
            }
        }
        return list;
    }

    /**
     * 获取excel中一行的数据
     *
     * @param row
     * @param titleRow
     * @return
     */
    private Map<String, String> getRowData(Row row, Row titleRow, List<String> list, String sheetName, int i) {
        Map<String, String> map = new HashMap<>();
        //第一列序号
        int firstCell = row.getFirstCellNum();
        //最后一列序号
        int lastCell = row.getLastCellNum();
        for (int j = firstCell; j < lastCell; j++) {
            //当前列标题
            String key = titleRow.getCell(j).getStringCellValue();
            //当前单元格
            Cell cell = row.getCell(j);
            //当单元格是数字格式时，需要把它的cell type转成String，否则会出错
            //当前单元格的值
            String val = null;
            if (cell.getCellType() == CellType.NUMERIC) {
                short format = cell.getCellStyle().getDataFormat();
                //14 yyyy-MM-dd / 2017/01/01  , 31 yyyy年m月d日
                if (format == 14 || format == 31) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    val = simpleDateFormat.format(date);
                } else {
                    cell.setCellType(CellType.STRING);
                    val = cell.getStringCellValue();
                }
            } else {
                cell.setCellType(CellType.STRING);
                val = cell.getStringCellValue();
            }
            map.put(key, val);

            //验证参数
            InvalidFailMsg invalidFailMsg = new InvalidFailMsg();
            invalidFailMsg.setSheetName(sheetName);
            invalidFailMsg.setRowNum(i + 1);
            invalidFailMsg.setValue(val);
            invalidFailMsg.setCellENName(key);
            invalidFailMsg.setCellNum(j + 1);
            invalidList(list, invalidFailMsg);
        }
        return map;
    }

    /**
     * 验证excel参数正确性
     *
     * @param list
     */
    private void invalidList(List<String> list, InvalidFailMsg invalidFailMsg) {
        String key = invalidFailMsg.getCellENName();
        String value = invalidFailMsg.getValue();
        switch (key) {
            case "osSystem":
                if (StringUtils.isEmpty(value)) {
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.PARAM_REQUIRED.getMsg()));
                    break;
                }
                if (!value.equals(CommonConstant.ONE_STR)
                        && !value.equals(CommonConstant.TWO_STR)
                        && !value.equals(CommonConstant.THREE_STR)) {
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.SYSTEM_INVALID_FAIL.getMsg()));
                }
                break;
            case "date":
                if (StringUtils.isEmpty(value)) {
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.PARAM_REQUIRED.getMsg()));
                    break;
                }
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    format.parse(value);
                } catch (Exception e) {
                    log.error(e.toString());
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.DATE_INVALID_FAIL.getMsg()));
                }
                break;
            case "modProfit":
            case "adEcpm":
            case "cac":
            case "profit":
            case "bookCac":
            case "cashCac":
                try {
                    if (!StringUtils.isEmpty(value)) {
                        new BigDecimal(value);
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.DECIMAL_INVALID_FAIL.getMsg()));
                }
                break;
            case "profitType":
            case "profitMain":
            case "app":
            case "market":
            case "agentAccount":
            case "marketName":
                if (StringUtils.isEmpty(value)) {
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.PARAM_REQUIRED.getMsg()));
                }
                break;
            case "orders":
            case "ordersSuc":
            case "exposureCnt":
            case "clickCnt":
                try {
                    if (!StringUtils.isEmpty(value)) {
                        Long.valueOf(value);
                    }
                } catch (Exception e) {
                    log.error(e.toString());
                    list.add(InvalidMsgUtils.result(invalidFailMsg, ErrorCode.INT_INVALID_FAIL.getMsg()));
                }
                break;
            default:
                break;
        }
    }
}
