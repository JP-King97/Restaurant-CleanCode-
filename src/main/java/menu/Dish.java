package menu;

import java.util.Map;

public abstract class Dish extends Recipe {

    protected int recipeID;
    protected int productionCost;
    protected int sellingPrice;
    private int ingredientTotalPrice;
    public Dish(int recipeID) {
        super(recipeID);
        this.recipeID = recipeID;

    }
    public Dish(){}

    /**
     * Abstract method that should return the production cost which in
     *  every child class will be defined with a different formula
     * @return production cost
     */
    public abstract int getProductionCost();

    /**
     * Calculate the selected ingredients total price
     *
     * @return the selected ingredients total priced
     */
    public int getIngredientsTotalPrice(){
        Map<Integer,Integer> ingredientUnitPrice = getIngredientsUnitPrices();
        for ( int i=0; i<getNumberOfIngredients();i++){
            ingredientTotalPrice = ingredientTotalPrice + ingredientUnitPrice.get(i)* getRecipeAmounts().get(i);
        }
        return ingredientTotalPrice;
    }

    /**
     * Calculated the selling price of the selected dish
     *
     * @return Selling price of the selected dish
     */
    public int getSellingPrice(){
        sellingPrice=productionCost+getIngredientsTotalPrice()+1000;
        return sellingPrice;
    }

}
