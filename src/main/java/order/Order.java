package order;

import connection.DatabaseConnection;
import inventory.Inventory;
import menu.*;

import java.sql.ResultSet;

public class Order {

    private DatabaseConnection dbConnection;
    public Order(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    private Dish selectTypeOfDish(int recipeID){
        Recipe recipe = new Recipe(dbConnection,recipeID);
        String type = recipe.getDishType();
        Dish dish;
        switch (type){
            case "FOOD" -> {
                dish = new Food(dbConnection,recipeID);
            }
            case "DRINK" -> {
                dish = new Drink(dbConnection,recipeID);
            }
            case "DESSERT" -> {
                dish = new Dessert(dbConnection,recipeID);
            }
            default -> {
                System.out.println("Dish type not found");
                dish = null;
            }
        }
        return dish;
    }
    public void makeADish(int recipeID, Inventory inventory){
        Dish dish = selectTypeOfDish(recipeID);
        takeAllIngredientsAmountsForRecipes(dish,inventory);

    }

    public double sellADish(int recipeID){
        Dish dish = selectTypeOfDish(recipeID);
        return dish.getSellingPrice();
    }

    private void takeAllIngredientsAmountsForRecipes(Dish dish, Inventory inventory){
        ResultSet IDs = dish.getIngredientsIDs();
        ResultSet inventoryAmounts = inventory.getIngredientsAmounts();

        try{
            while(IDs.next()){
                int ID = IDs.getInt("INGREDIENT_ID");
                int recipeAmount = dish.getIngredientAmount(ID);
                inventoryAmounts.next();
                int inventoryAmount = inventoryAmounts.getInt("INGREDIENT_INVENTORY_AMOUNT_GR");
                enoughIngredientAmountCheck(inventory,ID,recipeAmount,inventoryAmount);
            }
        }catch(Exception e){
                System.out.println("Error "+e);
        }
    }

    private void enoughIngredientAmountCheck(Inventory inventory, int ingredientID, int ingredientAmountRequired,int inventoryIngredientAmount){
        if(ingredientAmountRequired > inventoryIngredientAmount){
            System.out.println("you do not have enough ingredients for this recipe");
        }else{
            takeIngredientAmountFromInventory(inventory, ingredientID, (inventoryIngredientAmount - ingredientAmountRequired));
        }
    }

    private void takeIngredientAmountFromInventory( Inventory inventory, int ingredientID, int newIngredientAmount){
        inventory.updateIngredientAmount(ingredientID,newIngredientAmount);
    }
}
