package menu;

import data.ExcelFileNumericalReader;
import data.ExcelFileReader;
import data.ExcelFileStringReader;

import java.util.HashMap;
import java.util.Map;

public class Recipe {

    private int receiptID;
    // private int numberOfIngredients;
    private int[] firstCell = new int[2]; //firstCell[0]= row; firstCell[1]= column ,
    private Map<Integer, String> recipeIngredients = new HashMap<>();
    protected Map<Integer, Integer> recipeAmounts = new HashMap<>();
    protected Map<Integer, Integer> ingredientUnitPrice = new HashMap<>();
    protected Map<Integer, Integer> ingredientsID = new HashMap<>();
    protected Map<Integer, String> recipesList = new HashMap<>();
    private String sheetName = "Receipts";

    public Recipe(int receiptID) {
        this.receiptID = receiptID;
        selectRecipe(receiptID);
    }

    public Recipe(){}

    public void selectRecipe(int recipeID) {
        switch (recipeID) {
            case 0: {
                firstCell[0] = 1;
                firstCell[1] = 3;
                break;
            }
            case 1: {
                firstCell[0] = 1;
                firstCell[1] = 8;
                break;
            }
            case 2: {
                firstCell[0] = 1;
                firstCell[1] = 13;
                break;
            }
            case 3: {
                firstCell[0] = 20;
                firstCell[1] = 3;
                break;
            }
            case 4: {
                firstCell[0] = 20;
                firstCell[1] = 8;
                break;
            }
            case 5: {
                firstCell[0] = 20;
                firstCell[1] = 13;
                break;
            }
            case 6: {
                firstCell[0] = 20;
                firstCell[1] = 18;
                break;
            }
            case 7: {
                firstCell[0] = 20;
                firstCell[1] = 23;
                break;
            }
            case 8: {
                firstCell[0] = 32;
                firstCell[1] = 3;
                break;
            }
            case 9: {
                firstCell[0] = 32;
                firstCell[1] = 8;
                break;
            }
            case 10: {
                firstCell[0] = 32;
                firstCell[1] = 13;
                break;
            }
        }
    }

    public int getNumberOfIngredients() {
        ExcelFileReader reader = new ExcelFileNumericalReader(recipeAmounts, sheetName);
        return (reader.getNumericalValue(firstCell[0]+1, firstCell[1]));
    }

    public int getNumberOfRecipes(){
        ExcelFileReader reader = new ExcelFileReader(sheetName);
        return (reader.getNumericalValue(44,4));

    }

    public Map<Integer, String> getRecipesIngredients() {
        ExcelFileReader reader = new ExcelFileReader(recipeAmounts, recipeIngredients, sheetName);
        for (int i = 0; i < getNumberOfIngredients(); i++){
            recipeIngredients.put(i, reader.getStringValue(firstCell[0] + 4 + i, firstCell[1] - 1));
        }
        return recipeIngredients;
    }

    /**
     * get the ingredients' amounts for the recipe
     *
     * @return ingredients' amounts for the recipe
     */
    public Map<Integer, Integer> getRecipeAmounts() {
        ExcelFileReader reader = new ExcelFileReader(recipeAmounts, recipeIngredients, sheetName);
        for (int i = 0; i < getNumberOfIngredients(); i++) {
            recipeAmounts.put(i, reader.getNumericalValue(firstCell[0] + 4 + i, firstCell[1]));
        }
        return recipeAmounts;
    }

    /**
     * get the ingredients' unit price
     *
     * @return the ingredients' unit price
     */
    public Map<Integer, Integer> getIngredientUnitPrice(){
        ExcelFileReader reader = new ExcelFileNumericalReader(ingredientUnitPrice,"Prices");
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
        ExcelFileReader reader = new ExcelFileNumericalReader(ingredientsID,sheetName);
        for( int i=0;i<getNumberOfIngredients();i++){
            ingredientsID.put(i,reader.getNumericalValue(firstCell[0]+4+i,firstCell[1]+1));
       }
    }

    public Map<Integer, Integer> getIngredientsID() {
        return ingredientsID;
    }

    /**
     * set the listed recipes in the Excel File
     *
     * @return the list of recipes
     */
     public Map<Integer, String> getAvailableRecipes(){
         ExcelFileReader reader = new ExcelFileStringReader(recipesList,sheetName);
         for (int i = 0; i< getNumberOfRecipes(); i++){
             recipesList.put(i,reader.getStringValue(45+i,3));
         }
         return recipesList;
     }



}
