package menu;

import data.ExcelFileNumericalReader;
import data.ExcelFileReader;
import data.ExcelFileStringReader;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

    private final int[] FIRST_CELL = new int[2]; //firstCell[0]= row; firstCell[1]= column ,
    private final Map<Integer, String> RECIPE_INGREDIENTS = new HashMap<>();
    protected Map<Integer, Integer> recipeAmounts = new HashMap<>();
    protected Map<Integer, Integer> ingredientUnitPrice = new HashMap<>();
    protected Map<Integer, Integer> ingredientsID = new HashMap<>();
    protected Map<Integer, String> recipesList = new HashMap<>();
    private final String SHEET_NAME = "Receipts";

    public Recipe(int receiptID) {
        selectRecipe(receiptID);
    }

    public Recipe(){}

    public void selectRecipe(int recipeID) {
        switch (recipeID) {
            case 0 -> {
                FIRST_CELL[0] = 1;
                FIRST_CELL[1] = 3;
            }
            case 1 -> {
                FIRST_CELL[0] = 1;
                FIRST_CELL[1] = 8;
            }
            case 2 -> {
                FIRST_CELL[0] = 1;
                FIRST_CELL[1] = 13;
            }
            case 3 -> {
                FIRST_CELL[0] = 20;
                FIRST_CELL[1] = 3;
            }
            case 4 -> {
                FIRST_CELL[0] = 20;
                FIRST_CELL[1] = 8;
            }
            case 5 -> {
                FIRST_CELL[0] = 20;
                FIRST_CELL[1] = 13;
            }
            case 6 -> {
                FIRST_CELL[0] = 20;
                FIRST_CELL[1] = 18;
            }
            case 7 -> {
                FIRST_CELL[0] = 20;
                FIRST_CELL[1] = 23;
            }
            case 8 -> {
                FIRST_CELL[0] = 32;
                FIRST_CELL[1] = 3;
            }
            case 9 -> {
                FIRST_CELL[0] = 32;
                FIRST_CELL[1] = 8;
            }
            case 10 -> {
                FIRST_CELL[0] = 32;
                FIRST_CELL[1] = 13;
            }
            default -> System.out.println("Recipe not founded");
        }
    }

    public int getNumberOfIngredients() {
        ExcelFileReader reader = new ExcelFileNumericalReader(SHEET_NAME);
        return (reader.getNumericalValue(FIRST_CELL[0]+1, FIRST_CELL[1]));
    }

    public int getNumberOfRecipes(){
        ExcelFileReader reader = new ExcelFileReader(SHEET_NAME);
        return (reader.getNumericalValue(44,4));

    }

    public Map<Integer, String> getRecipesIngredients() {
        ExcelFileReader reader = new ExcelFileReader(recipeAmounts, RECIPE_INGREDIENTS, SHEET_NAME);
        for (int i = 0; i < getNumberOfIngredients(); i++){
            RECIPE_INGREDIENTS.put(i, reader.getStringValue(FIRST_CELL[0] + 4 + i, FIRST_CELL[1] - 1));
        }
        return RECIPE_INGREDIENTS;
    }

    /**
     * get the ingredients' amounts for the recipe
     *
     * @return ingredients' amounts for the recipe
     */
    public Map<Integer, Integer> getRecipeAmounts() {
        ExcelFileReader reader = new ExcelFileReader(recipeAmounts, RECIPE_INGREDIENTS, SHEET_NAME);
        for (int i = 0; i < getNumberOfIngredients(); i++) {
            recipeAmounts.put(i, reader.getNumericalValue(FIRST_CELL[0] + 4 + i, FIRST_CELL[1]));
        }
        return recipeAmounts;
    }

    /**
     * get the ingredients' unit price
     *
     * @return the ingredients' unit price
     */
    public Map<Integer, Integer> getIngredientsUnitPrices(){
        ExcelFileReader reader = new ExcelFileNumericalReader("Prices");
        setIngredientID();
        for(int i = 0; i< getNumberOfIngredients(); i++){
                ingredientUnitPrice.put(i,reader.getNumericalValue((ingredientsID.get(i))+1,1) );
      }
        return ingredientUnitPrice;
    }

    /**
     * Set the ingredients IDs got from the Excel File
     *
     */
    public void setIngredientID(){
        ExcelFileReader reader = new ExcelFileNumericalReader( SHEET_NAME);
        for( int i=0;i<getNumberOfIngredients();i++){
            ingredientsID.put(i,reader.getNumericalValue(FIRST_CELL[0]+4+i, FIRST_CELL[1]+1));
       }
    }

    public Map<Integer, Integer> getIngredientsID() {
        return ingredientsID;
    }

    /**
     * set the listed recipes in the Excel File
     *
     * @return the Map of recipes
     */
     public Map<Integer, String> getAvailableRecipes(){
         ExcelFileReader reader = new ExcelFileStringReader(SHEET_NAME);
         for (int i = 0; i< getNumberOfRecipes(); i++){
             recipesList.put(i,reader.getStringValue(45+i,3));
         }
         return recipesList;
     }



}
