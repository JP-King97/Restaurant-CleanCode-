package data.modifiers;

import file.reader.ExcelFileReader;

import java.util.Map;
public class NumericalDataModifier {

    private Map<Integer,Double> numericalMap;
    private String sheetName;
    public NumericalDataModifier(String sheetName, Map<Integer,Double> numericalMap){
        this.sheetName = sheetName;
        this.numericalMap = numericalMap;
    }

    public NumericalDataModifier(String sheetName){
        this.sheetName = sheetName;
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

    /**
     *
     * @param ID the key value that identify the numerical value stored in the Map
     * @param numericalValue the new numerical value that replace the previous value
     */
    public void setNumericalValue(int ID, Double numericalValue) {
        numericalMap.put(ID,numericalValue);
    }

}
