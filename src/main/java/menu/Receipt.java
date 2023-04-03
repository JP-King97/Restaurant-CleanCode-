package menu;

import data.ExcelFileReader;

import java.util.HashMap;
import java.util.Map;

public class Receipt {

    private int receiptID;
    // private int numberOfIngredients;
    private int[] firstCell = new int[2]; //firstCell[0]= row; firstCell[1]= column ,
    private Map<Integer, String> receiptIngredients = new HashMap<>();
    protected Map<Integer, Integer> receiptAmounts = new HashMap<>();
    protected Map<Integer, Integer> ingredientUnitPrice = new HashMap<>();
    protected Map<Integer, Integer> ingredientID = new HashMap<>();
    private String sheetName = "Receipts";

    public Receipt( int receiptID) {
        this.receiptID = receiptID;
        selectReceipt(receiptID);
    }

    public void selectReceipt(int receiptID) {
        switch (receiptID) {
            case 1: {
                firstCell[0] = 1;
                firstCell[1] = 3;
                break;
            }
            case 2: {
                firstCell[0] = 1;
                firstCell[1] = 8;
                break;
            }
            case 3: {
                firstCell[0] = 1;
                firstCell[1] = 13;
                break;
            }
            case 4: {
                firstCell[0] = 20;
                firstCell[1] = 3;
                break;
            }
            case 5: {
                firstCell[0] = 20;
                firstCell[1] = 8;
                break;
            }
            case 6: {
                firstCell[0] = 20;
                firstCell[1] = 13;
                break;
            }
            case 7: {
                firstCell[0] = 20;
                firstCell[1] = 18;
                break;
            }
            case 8: {
                firstCell[0] = 20;
                firstCell[1] = 23;
                break;
            }
            case 9: {
                firstCell[0] = 32;
                firstCell[1] = 3;
                break;
            }
            case 10: {
                firstCell[0] = 32;
                firstCell[1] = 8;
                break;
            }
            case 11: {
                firstCell[0] = 32;
                firstCell[1] = 13;
                break;
            }
        }
    }

    public int getNumberOfIngredients() {
        ExcelFileReader reader = new ExcelFileReader(receiptAmounts, receiptIngredients, sheetName);
        return (reader.getNumericalValue(firstCell[0]+1, firstCell[1]));
    }

    public Map<Integer, String> getReceiptsIngredients() {
        ExcelFileReader reader = new ExcelFileReader(receiptAmounts, receiptIngredients, sheetName);
        for (int i = 0; i < getNumberOfIngredients(); i++){
            receiptIngredients.put(i, reader.getStringValue(firstCell[0] + 4 + i, firstCell[1] - 1));
            //System.out.println("id: "+i+" -- Ingredient: "+(reader.getStringValue(firstCell[0] + 4 + i, firstCell[1] - 1)));
        }
        return receiptIngredients;
    }

    public Map<Integer, Integer> getReceiptsAmounts() {
        ExcelFileReader reader = new ExcelFileReader(receiptAmounts, receiptIngredients, sheetName);
        for (int i = 0; i < getNumberOfIngredients(); i++) {
            receiptAmounts.put(i, reader.getNumericalValue(firstCell[0] + 4 + i, firstCell[1]));
         //   System.out.println("id: "+i+
         //           " -- Amount: "+(reader.getNumericalValue(firstCell[0] + 4 + i, firstCell[1])));
        }
        return receiptAmounts;
    }

    public Map<Integer, Integer> getIngredientUnitPrice(){
        ExcelFileReader reader = new ExcelFileReader(ingredientUnitPrice,"Prices");
        setIngredientID();
        for(int i = 0; i< getNumberOfIngredients(); i++){
                reader.setNumericalValue(i,reader.getNumericalValue((ingredientID.get(i))+1,1) );
       //     System.out.println("Ingredient: " + getReceiptsIngredients().get(i) +
       //              " -- Unit Price: " + reader.getNumericalValue(ingredientID.get(i)+1,1));
        }
        return ingredientUnitPrice;
    }

    public void setIngredientID(){
        ExcelFileReader reader = new ExcelFileReader(ingredientID,sheetName);
        for( int i=0;i<getNumberOfIngredients();i++){
            reader.setNumericalValue(i,reader.getNumericalValue(firstCell[0]+4+i,firstCell[1]+1));
                System.out.println("Ingredient: " + getReceiptsIngredients().get(i) +
                        " -- ID: " + reader.getNumericalValue(firstCell[0]+4+i,firstCell[1]+1));
        }
    }

   public static void main(String[] args) {
        Receipt receipt= new Receipt(4);
  //     receipt.getReceiptsIngredients();
  //     receipt.getReceiptsAmounts();
  //     receipt.setIngredientID();
        receipt.getIngredientUnitPrice();
    }

}
