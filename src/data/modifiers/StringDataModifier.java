package data.modifiers;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

public class StringDataModifier {

    private String path = "C:/Users/User/IdeaProjects/FamilyRestaurant_JavaNoDependencies/Inventory.csv";
    private String line;
    private Map<Integer,String> stringMap;
    public StringDataModifier( Map<Integer,String> stringMap){
        this.stringMap = stringMap;
    }

    public StringDataModifier(){

    }

   // /**
   //  * Get the String value of the cell that contain the desiree information
   //  *
   //  * @param rowNumber the position of the row where the information is located (starting on 0)
   //  * @param columnNumber the position of the column in the row where the information is located (starting on 0)
   //  * @return the String value in the cell
   //  */
   // public String getStringValueFromExcelFile(int rowNumber, int columnNumber){
   //     ExcelFileReader reader = new ExcelFileReader(sheetName);
   //     return reader.getCellValue(rowNumber,columnNumber).getStringCellValue();
   // }

    /**
     *
     * @param row the counting start at 1
     * @param column the counting start at 1
     * @return the value of the cell as a String variable
     */
    public String getStringValueFromExcel(int row, int column) {
        BufferedReader bReader;
        String[] values = new String[28];
        {
            try {
                bReader = new BufferedReader(new FileReader(path));
                for(int i=0; i<=row;i++){
                    line = bReader.readLine();
                    values = line.split(";");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (values[column-1]);
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
