package com.example;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class JavaUtilClass {

    public Map<String, List<Map<String, String>>> readExcelFileAsJsonObject_RowWise(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = new HSSFWorkbook(inputStream);

        Map<String, List<Map<String, String>>> sheetsData = new HashMap<>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            List<Map<String, String>> rowsData = new ArrayList<>();
            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

            Row headerRow = sheet.getRow(0);
            List<String> headers = getHeaders(headerRow);

            for (int j = 1; j <= rowCount; j++) {
                Row row = sheet.getRow(j);
                Map<String, String> rowData = new HashMap<>();

                for (int k = 0; k < headers.size(); k++) {
                    String header = headers.get(k);
                    Cell cell = row.getCell(k);
                    String cellData = getCellData(cell);
                    rowData.put(header, cellData);
                }

                rowsData.add(rowData);
            }

            sheetsData.put(sheetName, rowsData);
        }

        return sheetsData;
    }

    private List<String> getHeaders(Row headerRow) {
        List<String> headers = new ArrayList<>();

        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }

        return headers;
    }

    private String getCellData(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}

