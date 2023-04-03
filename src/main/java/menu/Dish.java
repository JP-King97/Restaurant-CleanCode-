package menu;

import java.util.Map;

public abstract class Dish extends Receipt{

    protected int productionCostPercentage;
    protected int productionCost;
    protected int sellingPrice;
    private int ingredientTotalPrice;
    public Dish(int receiptID) {
        super(receiptID);
    }

    public int getIngredientsTotalPrice(){
        Map<Integer,Integer> ingredientUnitPrice = getIngredientUnitPrice();
        for ( int i=0; i<getNumberOfIngredients();i++){
            ingredientTotalPrice = ingredientTotalPrice + ingredientUnitPrice.get(i)*getReceiptsAmounts().get(i);
        }
        return ingredientTotalPrice;
    }
     public abstract int getProductionCost();

    public int getSellingPrice(int productionCost){
        sellingPrice=productionCost+getIngredientsTotalPrice()+1000;
        return sellingPrice;
    }


 //  public static void main(String[] args) {
 //      int receiptID = 3;
 //      Dish dish = new Dish(receiptID);
 //      //dish.getIngredientID();
 //      dish.getIngredientsTotalPrice();

 //  }

}
