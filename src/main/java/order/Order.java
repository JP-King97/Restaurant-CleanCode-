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
        String recipeName=null;
        String dishType=null;
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT recipe_name, dish_type FROM Recipes WHERE recipe_ID = "+recipeID+";");
            rs.next();
            recipeName = rs.getString("recipe_name");
            dishType = rs.getString("dish_type");
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        updateOrderHistory("make",recipeName, recipeID, dishType);
    }

   public void updateOrderHistory(String action, String recipe_name, int recipe_ID, String Type_of_dish){
       try {
           if(action.equals("make")){
               dbConnection.executeUpdate("INSERT INTO Orders( Recipe_name, Recipe_ID, Type_of_dish, State) VALUES ('"+recipe_name+"','"+recipe_ID+"','"+Type_of_dish+"','Requested')");
           }
       }catch(Exception e){
           System.out.println("Error "+e);
       }
   }



    public double sellADish(int orderID){

        String recipeName = null;
        String dishType=null;
        int recipeID=0;
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT recipe_id FROM Orders WHERE order_ID = "+orderID+";");
            rs.next();
            recipeID = rs.getInt("recipe_id");
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        updateOrderHistory("sell", orderID);
        Dish dish = selectTypeOfDish(recipeID);
        return dish.getSellingPrice();
    }

    public void updateOrderHistory(String action, int orderID){
        try {

            if (action.equals("sell")){
                System.out.println(orderID);
                dbConnection.executeUpdate("UPDATE Orders " +
                        "SET State = 'Delivered'" +
                        "WHERE Order_ID = "+orderID+";");
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void takeAllIngredientsAmountsForRecipes(Dish dish, Inventory inventory){
        ResultSet IDs = dish.getIngredientsIDs();
        try{
            while(IDs.next()){
                int ID = IDs.getInt("INGREDIENT_ID");
                int recipeAmount = dish.getIngredientAmount(ID);
                int inventoryAmount = inventory.getIngredientAmountByID(ID);
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
//




    public ResultSet getNotDeliveredDishes(){
        ResultSet rs = null;
        try{
            rs = dbConnection.executeQuery("SELECT * FROM orders WHERE state='Requested';");

        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return rs;
    }


    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect("family_restaurant_db", "postgres", "j3141592");
        Order order = new Order(dbConnection);
       // order.updateOrderHistory("sell","Vegan Pasta","FOOD");
     //  try {
     //     // ResultSet rs = dbConnection.executeQuery("SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'orders');");
     //     // rs.next();
     //     // String tableExists = rs.getString(1);
     //          dbConnection.executeQuery()

     //      //System.out.println(tableExists);
     //  }catch(Exception e){
     //      System.out.println("Error "+e);
     //  }
    }
}
