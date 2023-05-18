package file.reader;

import connection.DatabaseConnection;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelFileReader {
    protected String sheetName;


    public ExcelFileReader(String sheetName){
        this.sheetName = sheetName;
    }

    /**
     * Find the information of the sheet in the Excel file and turn it into a Java object
     *
     * @return the sheet object that contain the desiree information
     */
    private XSSFSheet getExcelSheet(){
        FileInputStream fileInputStream;
        XSSFWorkbook xssfWorkbook;
        try{
            fileInputStream = new FileInputStream("Inventory.xlsx");
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        }catch (IOException e) {throw new RuntimeException(e);}
        return xssfWorkbook.getSheet(sheetName);
    }

    /**
     * Find the information of the cell in the sheet of the Excel file and turn it into a Java object
     *
     * @param rowNumber the position of the row where the information is located (starting on 0)
     * @param columnNumber the position of the column in the row where the information is located (starting on 0)
     * @return the cell object that contain the desiree information
     */
    private XSSFCell getCellValue(int rowNumber, int columnNumber){
        XSSFRow row = getExcelSheet().getRow(rowNumber);
        return row.getCell(columnNumber);
    }


    /**
     * Get the String value of the cell that contain the desiree information
     *
     * @param rowNumber the position of the row where the information is located (starting on 0)
     * @param columnNumber the position of the column in the row where the information is located (starting on 0)
     * @return the String value in the cell
     */
    public String getStringValueFromExcelFile(int rowNumber, int columnNumber){
        ExcelFileReader reader = new ExcelFileReader(sheetName);
        return reader.getCellValue(rowNumber,columnNumber).getStringCellValue();
    }

    /**
     * Get the numerical value of the cell that contain the desiree information
     *
     * @param rowNumber the position of the row where the information is located (starting on 0)
     * @param columnNumber the position of the column in the row where the information is located (starting on 0)
     * @return the numerical value in the cell
     */
    public Double getNumericalValueFromExcelFile(int rowNumber, int columnNumber){
        ExcelFileReader reader = new ExcelFileReader(sheetName);
        return Double.valueOf(reader.getCellValue(rowNumber, columnNumber).getRawValue());
    }


}
