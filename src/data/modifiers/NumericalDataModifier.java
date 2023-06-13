package data.modifiers;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
public class NumericalDataModifier {
    private String path = "../Restaurant-CleanCode-/Inventory.csv";
    private String line;
    private Map<Integer,Double> numericalMap;
    public NumericalDataModifier( Map<Integer,Double> numericalMap){
        this.numericalMap = numericalMap;
    }

    public NumericalDataModifier(){}

   // /**
   //  * Get the numerical value of the cell that contain the desiree information
   //  *
   //  * @param rowNumber the position of the row where the information is located (starting on 0)
   //  * @param columnNumber the position of the column in the row where the information is located (starting on 0)
   //  * @return the numerical value in the cell
   //  */
   //   public Double getNumericalValueFromExcelFile(int rowNumber, int columnNumber){
   //       ExcelFileReader reader = new ExcelFileReader();
   //       return Double.valueOf(reader.getCellValue(rowNumber, columnNumber).getRawValue());
   //   }

    /**
     *
     * @param row the counting start at 1
     * @param column the counting start at 1
     * @return the value of the cell as a double variable
     */
    public double getNumericalValueFromExcel(int row, int column) {
        BufferedReader bReader;
        String[] values = new String[30];
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
        return Double.parseDouble(values[column-1]);
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
