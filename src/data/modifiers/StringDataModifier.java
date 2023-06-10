package data.modifiers;

import file.reader.ExcelFileReader;

import java.util.Map;

public class StringDataModifier {

    private Map<Integer,String> stringMap;
    private String sheetName;
    public StringDataModifier(String sheetName, Map<Integer,String> stringMap){
        this.sheetName = sheetName;
        this.stringMap = stringMap;
    }

    public StringDataModifier(String sheetName){
        this.sheetName = sheetName;
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
     *
     * @param ID the key value that identify the String value stored in the Map
     * @param stringValue the new String value that replace the previous value
     */
    public void setStringValue(int ID, String stringValue) {
        stringMap.put(ID, stringValue);
    }
}
