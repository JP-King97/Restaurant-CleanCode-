package menu;

import Data.ExcelFileReader;

import java.util.HashMap;
import java.util.Map;

public class Dish {

    private Map<Integer, Integer> ingredientPrice = new HashMap<>();
    private Map<Integer, Integer> ingredientQuantity = new HashMap<>();
    private String sheetName ="Prices";
    private int receiptID;
    ExcelFileReader reader;
    Receipt receipt;
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
    public int numberOfIngredients(){//Request from Receipts class
        receipt = new Receipt(receiptID);
        receipt.getReceiptsIngredients();
        return receipt.getNumberOfIngredients(receiptID);
    }
    public Map<Integer,Integer> getIngredientsQuantities(){
        receipt = new Receipt(receiptID);
        return receipt.getReceiptsAmounts();
    }

    public Map<Integer,Integer>  getIngredientPrice(){
        receipt = new Receipt(receiptID);
        return receipt.getIngredientPrice();
    }

  //  public static void main(String[] args) {
  //      Dish dish = new Dish(3);
  //      //System.out.println(dish.numberOfIngredients());
  //      //System.out.println(dish.getIngredientsQuantities());
  //      dish.getIngredientPrice();
  //  }
//

}
