package menu;

import data.modifiers.NumericalDataModifier;
import data.modifiers.StringDataModifier;

import java.util.HashMap;
import java.util.Map;
public class Recipe {

    private final int[] firstCell = new int[2]; //firstCell[0]=row; firstCell[1]=column
    private Map<Integer,String> recipeIngredients;
    protected Map<Integer,Double> recipeIngredientsAmounts = new HashMap<>();
    protected Map<Integer,Double> recipeIngredientUnitPrices = new HashMap<>();
    protected Map<Integer,Double> recipeIngredientsIDs = new HashMap<>();
    protected Map<Integer,String> recipesList = new HashMap<>();
    private final String sheetName = "Recipes";

    public Recipe(int recipeID){
        selectRecipe(recipeID);
    }

    public Recipe(){}

    public void selectRecipe(int recipeID) {
        switch (recipeID) {
            case 0 -> {
                firstCell[0] = 1;
                firstCell[1] = 3;
            }
            case 1 -> {
                firstCell[0] = 1;
                firstCell[1] = 8;
            }
            case 2 -> {
                firstCell[0] = 1;
                firstCell[1] = 13;
            }
            case 3 -> {
                firstCell[0] = 20;
                firstCell[1] = 3;
            }
            case 4 -> {
                firstCell[0] = 20;
                firstCell[1] = 8;
            }
            case 5 -> {
                firstCell[0] = 20;
                firstCell[1] = 13;
            }
            case 6 -> {
                firstCell[0] = 20;
                firstCell[1] = 18;
            }
            case 7 -> {
                firstCell[0] = 20;
                firstCell[1] = 23;
            }
            case 8 -> {
                firstCell[0] = 32;
                firstCell[1] = 3;
            }
            case 9 -> {
                firstCell[0] = 32;
                firstCell[1] = 8;
            }
            case 10 -> {
                firstCell[0] = 32;
                firstCell[1] = 13;
            }
            default -> System.out.println("Recipe not founded");
        }
    }

    public int getNumberOfIngredients(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier(sheetName);
        return numericalDataModifier.getNumericalValueFromExcelFile(firstCell[0]+1,firstCell[1]).intValue();
    }

    public int getNumberOfRecipes(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier(sheetName);
        return numericalDataModifier.getNumericalValueFromExcelFile(44,4).intValue();
    }

    /**
     * get the ingredients' amounts for the recipe
     *
     * @return ingredients' amounts for the recipe
     */
    public Map<Integer,Double> getRecipeIngredientsAmounts(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier(sheetName,recipeIngredientsAmounts);
        for (int i = 0; i < getNumberOfIngredients(); i++) {
            recipeIngredientsAmounts.put(i, numericalDataModifier.getNumericalValueFromExcelFile(firstCell[0] + 4 + i, firstCell[1]));
        }
        return recipeIngredientsAmounts;
    }

    /**
     * get the unit price of each recipe ingredient
     *
     * @return unit price of each recipe ingredient
     */
    public Map<Integer, Double> getRecipeIngredientsUnitPrices(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier("Prices");
        setRecipeIngredientsIDs();
        for(int i = 0; i< getNumberOfIngredients(); i++){
            recipeIngredientUnitPrices.put(i,numericalDataModifier.getNumericalValueFromExcelFile((int) ((recipeIngredientsIDs.get(i))+1),1) );
        }
        return recipeIngredientUnitPrices;
    }

    /**
     * Set the ingredients IDs got from the Excel File
     *
     */
    public void setRecipeIngredientsIDs(){
        NumericalDataModifier numericalDataModifier = new NumericalDataModifier(sheetName);
        for( int i=0;i<getNumberOfIngredients();i++){
            recipeIngredientsIDs.put(i,numericalDataModifier.getNumericalValueFromExcelFile(firstCell[0]+4+i, firstCell[1]+1));
        }
    }

    public Map<Integer, Double> getRecipesIngredientsIDs() {
        return recipeIngredientsIDs;
    }

    /**
     * set the listed recipes in the Excel File
     *
     * @return the Map of recipes
     */
    public Map<Integer, String> getAvailableRecipes(){
        StringDataModifier stringDataModifier = new StringDataModifier(sheetName);
        for (int i = 0; i< getNumberOfRecipes(); i++){
            recipesList.put(i,stringDataModifier.getStringValueFromExcelFile(45+i,3));
        }
        return recipesList;
    }

}
