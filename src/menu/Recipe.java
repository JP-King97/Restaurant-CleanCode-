package menu;

import data.modifiers.NumericalDataModifier;
import data.modifiers.StringDataModifier;
import inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
public class Recipe {

    private final int[] firstCell = new int[2]; //firstCell[0]=row; firstCell[1]=column
    //private Map<Integer,String> recipeIngredients;
    protected Map<Integer,Double> recipeIngredientsAmounts = new HashMap<>();
    protected Map<Integer,Double> recipeIngredientUnitPrices = new HashMap<>();
    protected Map<Integer,Integer> recipeIngredientsIDs = new HashMap<>();
    protected Map<Integer,String> recipesList = new HashMap<>();
   // private final String sheetName = "Recipes";
    private int recipeID;

    public Recipe(int recipeID){
        selectRecipe(recipeID);
        this.recipeID = recipeID;
    }


    public void selectRecipe(int recipeID) { //enhance
        if(recipeID>0 && recipeID<=getNumberOfRecipes()){
            firstCell[0] = 1;
            firstCell[1] =  9+(4*(recipeID-1));
        }else{
            System.out.println("Recipe not found");
        }
    }

    public int getNumberOfIngredients(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier();
        return (int) numericalDataModifier.getNumericalValueFromExcel(29+recipeID,11);
    }

    public int getNumberOfRecipes(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier();
        return (int) numericalDataModifier.getNumericalValueFromExcel(30,12);
    }

    /**
     * get the ingredients' amounts for the recipe
     *
     * @return ingredients' amounts for the recipe
     */
    public Map<Integer,Double> getRecipeIngredientsAmounts(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier(recipeIngredientsAmounts);
        for (int i = 0; i < getNumberOfIngredients(); i++) {
            recipeIngredientsAmounts.put(i, numericalDataModifier.getNumericalValueFromExcel(firstCell[0] + 2 + i, firstCell[1]+1));
        }
        return recipeIngredientsAmounts;
    }

    /**
     * get the unit price of each recipe ingredient
     *
     * @return unit price of each recipe ingredient
     */
    public Map<Integer, Double> getRecipeIngredientsUnitPrices(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier(recipeIngredientUnitPrices);
        setRecipeIngredientsIDs();
        for(int i = 0; i< getNumberOfIngredients(); i++){
            recipeIngredientUnitPrices.put(i,numericalDataModifier.getNumericalValueFromExcel((int) ((recipeIngredientsIDs.get(i))+1),4) );
        }
        return recipeIngredientUnitPrices;
    }

    /**
     * Set the ingredients IDs got from the Excel File
     *
     */
    public void setRecipeIngredientsIDs(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier();
        int numberOfIngredients = getNumberOfIngredients();
        for( int i=0;i<numberOfIngredients;i++) {
            int ID = (int) (numericalDataModifier.getNumericalValueFromExcel(firstCell[0] + 2 + i, firstCell[1]));
            recipeIngredientsIDs.put(i,ID);
        }
    }

    public Map<Integer, Integer> getRecipesIngredientsIDs() {
        return recipeIngredientsIDs;
    }

    /**
     * set the listed recipes in the Excel File
     *
     * @return the Map of recipes
     */
    public Map<Integer, String> getAvailableRecipes(){
        StringDataModifier stringDataModifier = new StringDataModifier();
        for (int i = 0; i< getNumberOfRecipes(); i++){
            recipesList.put(i,stringDataModifier.getStringValueFromExcel(30+i,10));
        }
        return recipesList;
    }

    public double getRecipeCalories(Inventory inventory){
        setRecipeIngredientsIDs();
        double calories=0;
        int NIngredients =getNumberOfIngredients();
        for(int i=0;i<NIngredients; i++){
            int ingredientID = getRecipesIngredientsIDs().get(i);
            double ingredientAmount = getRecipeIngredientsAmounts().get(i);
            double ingredientCalories = inventory.getIngredientsCalories().get(ingredientID-1);
            calories = calories + (ingredientCalories*ingredientAmount) ;
        }
        return calories;
    }

    public String getRecipeType(){
        StringDataModifier stringDataModifier = new StringDataModifier();
        return stringDataModifier.getStringValueFromExcel(firstCell[0]+1,firstCell[1]+2);
    }

    public static void main(String[] args) {
        Recipe recipe = new Recipe(1);
        recipe.setRecipeIngredientsIDs();
        Map<Integer,Integer> IDs = recipe.getRecipesIngredientsIDs();
    }

}
