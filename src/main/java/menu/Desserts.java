package menu;

import data.ExcelFileReader;

import java.util.HashMap;
import java.util.Map;

public class Desserts extends Dish {

    private int calories=0;
    private Map<Integer,Integer> ingredientsCalories = new HashMap<>();
    private boolean hot = false;

    public Desserts(int receiptID){
        super(receiptID);

    }

    @Override
    public int getProductionCost() {
        if (hot){productionCostPercentage = 20;
        }else {productionCostPercentage = 12;}
        productionCost = getIngredientsTotalPrice() * productionCostPercentage / 100;
        return productionCost;
    }

    public int getCalories(){
        ExcelFileReader reader = new ExcelFileReader(ingredientsCalories, "Calories");
        setIngredientID();
        for (int i=0;i<getNumberOfIngredients();i++){
            calories = calories + reader.getNumericalValue(ingredientID.get(i)+1,1);
        }
        return calories;
    }

    public static void main(String[] args) {
        Desserts dessert = new Desserts(9);
        dessert.getIngredientsTotalPrice();
    }


}
