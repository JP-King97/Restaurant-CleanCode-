package menu;

import connection.DatabaseConnection;

import java.sql.ResultSet;
import java.util.Map;

public abstract class Dish extends Recipe {
    protected double productionCost = 0;
    public Dish (DatabaseConnection dbConnection,int recipeID){
        super(dbConnection,recipeID);
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
        Map<Integer,Double> ingredientsUnitPrices = getIngredientUnitPrices();
        ResultSet rs = getIngredientsIDs();
        double totalPrice = 0;
        int ingredientID=0;
        for(int i=0;i<getNumberOfIngredients();i++){
            try {
                rs.next();
                ingredientID =rs.getInt("INGREDIENT_ID");
            }catch(Exception e){
                System.out.println("Error "+e);
            }
            totalPrice = totalPrice + ingredientsUnitPrices.get(i)*getIngredientAmount(ingredientID);
        }
        return totalPrice;
    }

    /**
     * Calculated the selling price of the selected dish
     *
     * @return Selling price of the selected dish
     */
    public double getSellingPrice() {
        return productionCost + getIngredientsTotalPrice() + 1000;
    }





}
