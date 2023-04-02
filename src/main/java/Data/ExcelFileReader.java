package Data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class ExcelFileReader {
    private Map<Integer, Integer> numericalReader; //numerical reader
    private Map<Integer, String> StringReader; //String reader
    public ExcelFileReader(Map<Integer, Integer> numericalReader,
                           Map<Integer, String> StringReader){
        this.numericalReader = numericalReader;
        this.StringReader = StringReader;
    }


    public XSSFSheet readSheet(){
        String filePath = "C:\\Users\\User\\IdeaProjects\\FamilyRestaurant\\Inventory.xlsx";
        FileInputStream fileInputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {throw new RuntimeException(e);}
        XSSFSheet inventorySheet = xssfWorkbook.getSheet("WarehouseQuantities");
        return inventorySheet;
    }
    public XSSFCell getCellValue(int rowNumber, int columNumber){
        XSSFRow row =readSheet().getRow(rowNumber+1);// rN+1 due to the location of the data on file
        return  row.getCell(columNumber);//0 = #column
    }

    public String getIngredientName(int id){
        return getCellValue(id,0).getStringCellValue();
    }
    public void setIngredientsName(int id, String name) {
        StringReader.put(id, name);
    }

    public Integer getIngredientAmount(int id){
        return Integer.valueOf(getCellValue(id,1).getRawValue());
    }
    public void setIngredientsAmount(int id,Integer amount) {
        numericalReader.put(id,amount);
    }
}
