package menu;

import data.ExcelFileNumericalReader;
import data.ExcelFileReader;

import java.util.HashMap;
import java.util.Map;

public class Desserts extends Dish {

    private int recipeID;
    private Map<Integer,Integer> ingredientsCalories = new HashMap<>();


    public Desserts(int recipeID){
        super(recipeID);
        this.recipeID = recipeID;

    }

    @Override
    public int getProductionCost() {

        switch (recipeID){
            case 8,9,10:
                productionCost = (int) (getIngredientsTotalPrice()*0.12);
                break;
            default:
                System.out.println("Dessert not founded");
        }
        return productionCost;
    }

    public int getCalories(){
        ExcelFileReader reader = new ExcelFileNumericalReader(ingredientsCalories, "Calories");
        setIngredientID();
        int calories=0;
        for (int i=0;i<getNumberOfIngredients();i++){
            calories = calories + reader.getNumericalValue(ingredientsID.get(i)+1,1);
        }
        return calories;
    }

    public static void main(String[] args) {
        Desserts dessert = new Desserts(9);
        dessert.getIngredientsTotalPrice();
    }


}
