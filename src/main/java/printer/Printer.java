package printer;

import account.MoneyAccount;
import connection.DatabaseConnection;
import inventory.Inventory;
import menu.Recipe;

import java.sql.ResultSet;
import java.util.Scanner;

public class Printer {

    public Printer(){
    }

    /**
     *
     * @param scanner that receive
     * @return the option selected in the main menu.
     */
    public int mainMenu(Scanner scanner){
        System.out.println("////////////////////MAIN MENU////////////////////\n" +
                "            Please select an option\n"+
                "1.Print my current money amount.\n" +
                "2.Print my current ingredient inventory.\n" +
                "3.Buy ingredients.\n" +
                "4.Print my dishes inventory.\n" +
                "5.Make a dish.\n" +
                "6.Sell a dish.\n" +
                "7.EXIT.\n" +
                "/////////////////////////////////////////////////");
        return scanner.nextInt();
    }

    /**
     *
     * @param account object from the class MoneyAccount
     */
    public void currentMoneyAmount(MoneyAccount account ){
        System.out.println("Current money account: $"+account.getCurrentBalance());
    }

    /**
     * Print all the listed ingredients in the inventory and their amount as well.
     *
     * @param inventory object from the class Inventory
     */
     public void currentIngredientInventory(Inventory inventory){
         System.out.printf("%5s||%20s||%15s%n","IDs","INGREDIENTS","QUANTITIES");
         int numberOfIngredients = inventory.getNumberOfIngredients();

         try {
             for( int i = 1; i<=numberOfIngredients;i++){
                 ResultSet ingredientInfo = inventory.getIngredientInfo(i);
                 ingredientInfo.next();
                 System.out.printf("%5s%20s%15s%n",ingredientInfo.getInt("Ingredient_ID"),ingredientInfo.getString("INGREDIENT_NAME"),ingredientInfo.getInt("INGREDIENT_INVENTORY_AMOUNT_GR"));

             }
         }catch(Exception e){
             System.out.println("Error "+e);
         }
     }

    /**
     * Print all the listed recipes and their IDs as well.
     */
    public void currentRecipeList(DatabaseConnection dbConnection){
        System.out.printf("%10s||%20s%n","ID","RECEIPT");
        try{
            Recipe numberOfRecipes = new Recipe(dbConnection);
            for(int i=1; i <= numberOfRecipes.getNumberOfRecipes() ; i++){
                Recipe recipe = new Recipe(dbConnection,i);
                System.out.printf("%10s||%20s%n",i, recipe.getRecipeName());
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

}
