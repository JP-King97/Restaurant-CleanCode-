package menu;

import java.util.Map;

public abstract class Dish extends Recipe{
    protected double productionCost;
    protected double sellingPrice;
    protected double ingredientsTotalPrice;
    public Dish(int recipeID) {
        super(recipeID);
    }

    /**
     * Abstract method that should return the production cost which in
     *  every child class will be defined with a different formula
     * @return production cost
     */
    public abstract double getProductionCost();

    /**
     * Calculate the selected ingredients total price
     *
     * @return the selected ingredients total priced
     */
    public double getIngredientsTotalPrice(){
        Map<Integer,Double> ingredientUnitPrice = getRecipeIngredientsUnitPrices();
        for ( int i=0; i<getNumberOfIngredients();i++){
            ingredientsTotalPrice = ingredientsTotalPrice + ingredientUnitPrice.get(i)* getRecipeIngredientsAmounts().get(i);
        }
        return ingredientsTotalPrice;
    }

    /**
     * Calculated the selling price of the selected dish
     *
     * @return Selling price of the selected dish
     */
    public Double getSellingPrice(){
        sellingPrice=productionCost+getIngredientsTotalPrice()+1000;
        return sellingPrice;
    }
}
