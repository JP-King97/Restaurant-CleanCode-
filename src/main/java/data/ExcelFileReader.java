package data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ExcelFileReader {
    private String sheetName;
    private Map<Integer, Integer> numericalReader; //numerical reader
    private Map<Integer, String> StringReader; //String reader



    public ExcelFileReader(Map<Integer, Integer> numericalReader,
                           Map<Integer, String> StringReader, String sheetName){
        this.numericalReader = numericalReader;
        this.StringReader = StringReader;
        this.sheetName = sheetName;
    }

    public ExcelFileReader(String sheetName){
        this.sheetName = sheetName;
    }

  //  public ExcelFileReader(Map<Integer, String> StringReader,String sheetName){
  //      this.StringReader = StringReader;
  //      this.sheetName = sheetName;
  //  }

    public XSSFSheet readSheet(){
        String filePath = "C:\\Users\\User\\IdeaProjects\\FamilyRestaurant\\Inventory.xlsx";
        FileInputStream fileInputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        try {  // Review the concept "Fail fast"
            fileInputStream = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {throw new RuntimeException(e);}
        XSSFSheet inventorySheet = xssfWorkbook.getSheet(sheetName);
        return inventorySheet;
    }
    public XSSFCell getCellValue(int rowNumber, int columNumber){
        XSSFRow row =readSheet().getRow(rowNumber);// rN+1 due to the location of the data on file
        return  row.getCell(columNumber);//0 = #column
    }



    public Integer getNumericalValue(int rowNumber, int columNumber){
        return Integer.valueOf(getCellValue(rowNumber,columNumber).getRawValue());
    }
    public void setNumericalValue(int ID, Integer amount) {
        numericalReader.put(ID,amount);
    }

    public String getStringValue(int rowNumber, int columNumber){
        return getCellValue(rowNumber,columNumber).getStringCellValue();
    }
    public void setStringValue(int ID, String name) {
        StringReader.put(ID, name);
    }
}
