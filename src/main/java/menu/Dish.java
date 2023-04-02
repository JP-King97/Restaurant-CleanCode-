package menu;

import Data.ExcelFileReader;

import java.util.HashMap;
import java.util.Map;

public class Dish {

    private Map<Integer, Integer> ingredientPrice = new HashMap<>();
    private String sheetName ="Prices";
    private int receiptID;
    public Dish(int receiptID) {
        this.receiptID = receiptID;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    /**
     *
     * @return
     */
    public int amountOfIngredients(){//Request from Receipts class
        Receipt receipt = new Receipt( receiptID);
        receipt.getReceiptsIngredients();
        return receipt.getNumberOfIngredients(receiptID);
    }

    public void getIngredientPrice(){
        ExcelFileReader reader = new ExcelFileReader(ingredientPrice,sheetName);
        for(int i = 0; i<amountOfIngredients();i++){
            reader.setNumericalValue(i, reader.getNumericalValue(i,1));
        }
    }

    public static void main(String[] args) {
        Dish dish = new Dish(3);
        int amountOfIngredients = dish.amountOfIngredients();
        System.out.println(amountOfIngredients);
    }


}
