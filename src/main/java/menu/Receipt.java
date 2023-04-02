package menu;

import Data.ExcelFileReader;

import java.util.HashMap;
import java.util.Map;

public class Receipt {


    private String name;
    private String dishType;
    private int receiptID;
    private int ingredientID;
    // private int numberOfIngredients;
    private int[] firstCell = new int[2]; //firstCell[0]= row; firstCell[1]= column ,
    private Map<Integer, String> receiptIngredients = new HashMap<>();
    private Map<Integer, Integer> receiptAmounts = new HashMap<>();;
    private String sheetName = "Receipts";
    private ExcelFileReader reader = new ExcelFileReader(receiptAmounts, receiptIngredients, sheetName);

    public Receipt(String name, int receiptID) {
        this.name = name;
        this.receiptID = receiptID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    // public void getIngredients(Map<Integer, Integer> numericalReader,
    //                            Map<Integer, String> StringReader){
    //     ExcelFileReader reader = new ExcelFileReader(numericalReader, StringReader,sheetName);
    //     reader.
    // }
    public void selectReceipt(int receiptID) {
        switch (receiptID) {
            case 1: {
                firstCell[0] = 1;
                firstCell[1] = 3;
                break;
            }
            case 2: {
                firstCell[0] = 1;
                firstCell[1] = 7;
                break;
            }
            case 3: {
                firstCell[0] = 1;
                firstCell[1] = 11;
                break;
            }
            case 4: {
                firstCell[0] = 20;
                firstCell[1] = 3;
                break;
            }
            case 5: {
                firstCell[0] = 20;
                firstCell[1] = 7;
                break;
            }
            case 6: {
                firstCell[0] = 20;
                firstCell[1] = 11;
                break;
            }
            case 7: {
                firstCell[0] = 20;
                firstCell[1] = 15;
                break;
            }
            case 8: {
                firstCell[0] = 20;
                firstCell[1] = 19;
                break;
            }
            case 9: {
                firstCell[0] = 33;
                firstCell[1] = 3;
                break;
            }
            case 10: {
                firstCell[0] = 33;
                firstCell[1] = 7;
                break;
            }
            case 11: {
                firstCell[0] = 33;
                firstCell[1] = 11;
                break;
            }
        }
    }

    public int getNumberOfIngredients(int receiptID) {
        selectReceipt(receiptID);
        //System.out.println(reader.getNumericalValue(firstCell[0]+1, firstCell[1]));
        return (reader.getNumericalValue(firstCell[0]+1, firstCell[1])).intValue();
    }

    public Map<Integer, String> getReceiptsIngredients() {
        for (int i = 0; i < getNumberOfIngredients(receiptID); i++){
            receiptIngredients.put(i, reader.getStringValue(firstCell[0] + 4 + i, firstCell[1] - 1));
            System.out.println("id: "+i+" -- Ingredient: "+(reader.getStringValue(firstCell[0] + 4 + i, firstCell[1] - 1)));
        }
        return receiptIngredients;
    }

    public Map<Integer, Integer> getReceiptsAmounts() {
        for (int i = 0; i < getNumberOfIngredients(receiptID); i++) {
            receiptAmounts.put(i, reader.getNumericalValue(firstCell[0] + 4 + i, firstCell[1]));
            System.out.println("id: "+i+" -- Amount: "+(reader.getNumericalValue(firstCell[0] + 4 + i, firstCell[1])));
        }
        return receiptAmounts;
    }

  // public static void main(String[] args) {
  //     Receipt receipt= new Receipt("Mashed Potatoes, Chicken and Broccoli",2);
  //     receipt.getReceiptsIngredients();
  //     receipt.getReceiptsAmounts();
  // }

}
