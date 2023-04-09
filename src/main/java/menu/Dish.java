package menu;

import java.util.Map;

public abstract class Dish extends Recipe {

    protected int productionCostPercentage;
    protected int productionCost;
    protected int sellingPrice;
    private int ingredientTotalPrice;
    public Dish(int recipeID) {
        super(recipeID);
    }

    public int getIngredientsTotalPrice(){
        Map<Integer,Integer> ingredientUnitPrice = getIngredientUnitPrice();
        for ( int i=0; i<getNumberOfIngredients();i++){
            ingredientTotalPrice = ingredientTotalPrice + ingredientUnitPrice.get(i)* getRecipesAmounts().get(i);
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
