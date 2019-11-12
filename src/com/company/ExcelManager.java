package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

    public class ExcelManager {

        private static ExcelManager excelMng;

        public ExcelManager() {
// TODO Auto-generated constructor stub
        }

        public static ExcelManager getInstance() {
            if (excelMng == null)
                excelMng = new ExcelManager();
            return excelMng;
        }

        /**
         * 엑셀파일 파싱후 HashMap리스트를 반환
         *
         * @param file
         * @return
         * @throws Exception
         */
        public List<HashMap<String, String>> getListExcel(File file) throws Exception {
            List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("c:\\test.xls"));
//엑셀파일의 시트 존재 유무 확인
            if (workbook.getNumberOfSheets() < 1) return null;

//첫번째 시트를 읽음
            HSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                list.add(readCellData(sheet.getRow(i)));
            }
            return list;
        }

        private HashMap<String, String> readCellData(HSSFRow row) {
            HashMap<String, String> hMap = new HashMap<String, String>();
            int maxNum = row.getLastCellNum();
            for(int i = 0; i < maxNum; i++){
                hMap.put("attr"+i,getStringCellData(row.getCell(i)));
            }
            return hMap;
        }

        private String getStringCellData (HSSFCell cell) {
            DecimalFormat df = new DecimalFormat();
            FormulaEvaluator evaluator = new

                    HSSFWorkbook().getCreationHelper().createFormulaEvaluator();
            if (cell != null) {
                String data = null;
                switch (cell.getCellType()) {
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        boolean bdata = cell.getBooleanCellValue();
                        data = String.valueOf(bdata);
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            data = formatter.format(cell.getDateCellValue());
                        } else {
                            double ddata = cell.getNumericCellValue();
                            data = df.format(ddata);
                        }
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        data = cell.toString();
                        break;
                    case HSSFCell.CELL_TYPE_BLANK:
                    case HSSFCell.CELL_TYPE_ERROR:
                    case HSSFCell.CELL_TYPE_FORMULA:
                        if (!(cell.toString() == "")) {
                            if (evaluator.evaluateFormulaCell(cell) == HSSFCell.CELL_TYPE_NUMERIC) {

                                double fddata = cell.getNumericCellValue();
                                data = df.format(fddata);
                            } else if (evaluator.evaluateFormulaCell(cell) ==

                                    HSSFCell.CELL_TYPE_STRING) {
                                data = cell.getStringCellValue();
                            } else if (evaluator.evaluateFormulaCell(cell) ==

                                    HSSFCell.CELL_TYPE_BOOLEAN) {
                                boolean fbdata = cell.getBooleanCellValue();
                                data = String.valueOf(fbdata);
                            }
                            break;
                        }
                    default:
                        data = cell.toString();
                }
                return data;
            } else {
                return null;
            }
        }
    }
