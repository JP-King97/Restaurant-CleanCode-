package menu;

import data.modifiers.NumericalDataModifier;

import java.util.HashMap;
import java.util.Map;

public class Dessert extends Dish{
    private final int recipeID;
   // private final Map<Integer,Double> ingredientsCalories = new HashMap<>();

    public Dessert(int recipeID){
        super(recipeID);
        this.recipeID = recipeID;

    }

    /**
     * Calculate the production cost for a dessert depending on if is a cold or hot
     *
     * @return the production cost
     */
    @Override
    public double getProductionCost() {
        switch (recipeID) {
            case 8, 9, 10 -> productionCost = (int) (getIngredientsTotalPrice() * 0.12);
            default -> System.out.println("Dessert not founded");
        }
        return productionCost;
    }

   // public int getCalories(){
   //     NumericalDataModifier numericalDataModifier = new NumericalDataModifier(ingredientsCalories);
   //     setRecipeIngredientsIDs();
   //     int calories=0;
   //     for (int i=0;i<getNumberOfIngredients();i++){
   //         calories = (int) (calories + numericalDataModifier.getNumericalValueFromExcel((recipeIngredientsIDs.get(i).intValue()+1),5));
   //     }
   //     return calories;
   // }
}
