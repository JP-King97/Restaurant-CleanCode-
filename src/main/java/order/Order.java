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
        updateOrderHistory("make",recipeName,dishType);

    }

    public double sellADish(int recipeID){
        Dish dish = selectTypeOfDish(recipeID);
        String recipeName = null;
        String dishType=null;
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT recipe_name, dish_type FROM Recipes");
            rs.next();
            recipeName = rs.getString("recipe_name");
            dishType = rs.getString("dish_type");
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        updateOrderHistory("sell",recipeName,dishType);
        return dish.getSellingPrice();
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

    private void createTable (){
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'orders');");
            rs.next();
            String tableExists = rs.getString(1);
            if(tableExists.equals("f")){
                dbConnection.executeQuery("CREATE TABLE Orders(" +
                                              "Order_ID smallserial NOT NULL," +
                                              "Recipe_name varchar(40) NOT NULL," +
                                              "Type_of_dish varchar(20) NOT NULL," +
                                              "State varchar(15) NOT NULL);");
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }
//

    public void updateOrderHistory(String action, String Recipe_name, String Type_of_dish){
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT MAX(Order_ID) FROM Orders;");
            rs.next();
            int orderID = rs.getInt(1);
            if (action.equals("sell")){
                System.out.println(orderID);
                dbConnection.executeUpdate("UPDATE Orders " +
                        "SET State = 'Delivered'" +
                        "WHERE Order_ID = "+orderID+";");
            } if(action.equals("make")){
                dbConnection.executeUpdate("INSERT INTO Orders( Recipe_name, Type_of_dish, State) VALUES ('"+Recipe_name+"','"+Type_of_dish+"','Requested')");
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    public void resetValues(){

    }


    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect("family_restaurant_db", "postgres", "j3141592");
        Order order = new Order(dbConnection);
        order.createTable();
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
