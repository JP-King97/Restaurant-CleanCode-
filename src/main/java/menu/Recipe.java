package menu;

import connection.DatabaseConnection;
import file.reader.ExcelFileReader;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Recipe {

    protected int recipeID;
    private DatabaseConnection dbConnection;
    public Recipe(DatabaseConnection dbConnection,int recipeID){
        this.dbConnection = dbConnection;
        this.recipeID = recipeID;
    }

    public Recipe(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public int getNumberOfRecipes(){
        int recipes = 0;
        try{
            ResultSet rs = dbConnection.executeQuery("SELECT RECIPE_ID FROM Recipes");
            rs.last();
            recipes = rs.getRow();
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return recipes;
    }

    public double getRecipeCalories(){
        double recipeTotalCal=0;
        try{
            ResultSet rsName = dbConnection.executeQuery("SELECT recipe_name FROM recipes WHERE recipe_ID = "+recipeID+";");
            rsName.next();
            ResultSet rsCalories = dbConnection.executeQuery("SELECT ingredient_id, ingredient_calories FROM ingredients;");
            String recipeName = rsName.getString("recipe_name");
            ResultSet rsID = getIngredientsIDs();

            while(rsID.next()){
                int ingredientID = rsID.getInt("ingredient_ID");
                rsCalories.next();
                double caloriesPerGr = rsCalories.getDouble("ingredient_calories");
                int ingredientAmount = getIngredientAmount(ingredientID);
                double ingredientTotalCal = (caloriesPerGr * ingredientAmount);
                recipeTotalCal=recipeTotalCal+ingredientTotalCal;
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return recipeTotalCal;
    }
    //////////////////////////////////////////
    public int getNumberOfIngredients(){
        int ingredients=0;
        try{
            ResultSet rs = dbConnection.executeQuery(
                    "SELECT NUMBER_OF_INGREDIENTS FROM Recipes WHERE Recipe_ID = "+recipeID+";");
            rs.next();
            ingredients =rs.getInt("NUMBER_OF_INGREDIENTS");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return ingredients;
    }

    public String getRecipeName(){
        String name= null;
        try{
            ResultSet rs = dbConnection.executeQuery("SELECT RECIPE_NAME FROM Recipes WHERE RECIPE_ID = "+recipeID+";");
            rs.next();
            name = rs.getString("RECIPE_NAME");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return name;
    }

    public String getDishType(){
        String type = null;
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT Dish_Type FROM recipes WHERE RECIPE_ID = "+recipeID+";");
            rs.next();
            type = rs.getString("Dish_Type");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return type;
    }
///////////////////////////////////////////
    public ResultSet getIngredientsIDs(){
        ResultSet rs = null;
        String recipeName = getRecipeName();
        double o =0;
        try{
            rs = dbConnection.executeQuery("SELECT INGREDIENT_ID FROM "+recipeName+" ;");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return rs;
    }

   public int getIngredientAmount(int ingredientID){
        ResultSet rs = null;
        int ingredientAmount =0;
        String recipeName = getRecipeName();
        try{
            rs = dbConnection.executeQuery("SELECT Ingredient_Amount FROM "+recipeName+" WHERE INGREDIENT_ID = "+ingredientID+";");
            rs.next();
            ingredientAmount = rs.getInt("Ingredient_Amount");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return ingredientAmount;
   }
////////////////////////////////////////////
   public Map<Integer,Double> getIngredientUnitPrices(){
        ResultSet rs = getIngredientsIDs();
       Map<Integer,Double> UnitPrices = new HashMap<>();

        try{
            int i =0;
            while(rs.next()){
                int ingredientId = rs.getInt("INGREDIENT_ID");
                ResultSet rs2 = dbConnection.executeQuery("SELECT INGREDIENT_UNIT_PRICE FROM Ingredients WHERE INGREDIENT_ID = "+ingredientId+";");
                rs2.next();
                double ingredientUnitPrice = rs2.getDouble("INGREDIENT_UNIT_PRICE");
                UnitPrices.put(i, ingredientUnitPrice);
                i++;
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return UnitPrices;

   }

    public void updateNumberOfIngredients(int newNumberOfIngredients){
        try{
            dbConnection.executeUpdate("UPDATE Recipes" +
                    "SET NUMBER_OF_INGREDIENTS = "+newNumberOfIngredients+"," +
                    "WHERE RECIPE_ID = "+recipeID+";");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void addNewRecipeTable(String recipeName, int numberOfIngredients, int recipe_ID, String dishType){
        try {
            dbConnection.executeUpdate(
                    "CREATE TABLE "+recipeName+" (\n" +
                            "    Ingredient_ID int NOT NULL,\n" +
                            "    Ingredient_Name varchar(40),\n" +
                            "    Ingredient_Amount int);");


            dbConnection.executeUpdate("INSERT INTO Recipes (Recipe_ID, Recipe_Name, Number_Of_Ingredients, Dish_Type) " +
                                             "VALUES ("+recipe_ID+" ,'"+recipeName+"', "+numberOfIngredients+",'"+dishType+"');"
            );
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }

    public void insertNewData(String recipeName){
        ExcelFileReader reader = new ExcelFileReader("Recipes");
        for (int i = 0; i < getNumberOfIngredients(); i++) {
            try {
                dbConnection.executeUpdate(
                        "INSERT INTO "+recipeName+" (Ingredient_ID, Ingredient_Name, Ingredient_Amount) " +
                                "VALUES (" + reader.getNumericalValueFromExcelFile(i+3, (4*recipeID)-3).intValue() + ",'" +
                                reader.getStringValueFromExcelFile(i+3, (4*recipeID)-1) + "',"+
                                reader.getNumericalValueFromExcelFile(i+3, (4*recipeID)-2) + " );");
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
        }
    }


}

