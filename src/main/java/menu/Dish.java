package menu;

import java.util.Map;

public abstract class Dish extends Recipe {

    int recipeID;
    protected int productionCost;
    protected int sellingPrice;
    private int ingredientTotalPrice;
    public Dish(int recipeID) {
        super(recipeID);
        this.recipeID = recipeID;

    }

    public abstract int getProductionCost();

    public int getIngredientsTotalPrice(){
        Map<Integer,Integer> ingredientUnitPrice = getIngredientUnitPrice();
        for ( int i=0; i<getNumberOfIngredients();i++){
            ingredientTotalPrice = ingredientTotalPrice + ingredientUnitPrice.get(i)* getRecipesAmounts().get(i);
        }
        return ingredientTotalPrice;
    }

    public int getSellingPrice(){
        sellingPrice=productionCost+getIngredientsTotalPrice()+1000;
        return sellingPrice;
    }

}
