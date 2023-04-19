package data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelFileReader {
    private final String SHEET_NAME;
    private Map<Integer, Integer> numericalReader;
    private Map<Integer, String> StringReader;



    public ExcelFileReader(Map<Integer, Integer> numericalReader,
                           Map<Integer, String> StringReader, String SHEET_NAME){
        this.numericalReader = numericalReader;
        this.StringReader = StringReader;
        this.SHEET_NAME = SHEET_NAME;
    }

    public ExcelFileReader(String SHEET_NAME){
        this.SHEET_NAME = SHEET_NAME;
    }

    /**
     * Find the sheet provided in the String "sheetName".
     *
     * @return the sheet selected in the Excel File
     */
    public XSSFSheet readSheet(){
        FileInputStream fileInputStream;
        XSSFWorkbook xssfWorkbook;
        try {  // Review the concept "Fail fast"
            fileInputStream = new FileInputStream("Inventory.xlsx");
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {throw new RuntimeException(e);}
        return xssfWorkbook.getSheet(SHEET_NAME);
    }

    /**
     * Find the cell located in the row and column provided
     *
     * @param rowNumber
     * @param columnNumber
     * @return the cell selected in the selected sheet in the Excel File
     */
    public XSSFCell getCellValue(int rowNumber, int columnNumber){
        XSSFRow row =readSheet().getRow(rowNumber);
        return  row.getCell(columnNumber);
    }


    /**
     *  Find the numerical value in the cell located in the row and column provided
     *
     * @param rowNumber
     * @param columNumber
     * @return the Integer value located in the row and column provided
     */
    public Integer getNumericalValue(int rowNumber, int columNumber){
        return Integer.valueOf(getCellValue(rowNumber,columNumber).getRawValue());
    }
    public void setNumericalValue(int ID, Integer amount) {
        numericalReader.put(ID,amount);
    }

    /**
     * Find the String value in the cell located in the row and column provided
     *
     * @param rowNumber
     * @param columNumber
     * @return the String value located in the row and column provided
     */
    public String getStringValue(int rowNumber, int columNumber){
        return getCellValue(rowNumber,columNumber).getStringCellValue();
    }
    public void setStringValue(int ID, String name) {
        StringReader.put(ID, name);
    }
}
