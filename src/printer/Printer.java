package printer;

import account.MoneyAccount;
import inventory.Inventory;
import menu.Recipe;
import orders.Order;

import java.util.Map;
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
     * @param account
     */
    public void currentMoneyAmount(MoneyAccount account ){
        System.out.println("Current money account: $"+account.getCurrentBalance());
    }

    /**
     * Print all the listed ingredients in the inventory and their amount as well.
     *
     * @param inventory
     */
    public void currentIngredientInventory(Inventory inventory){
        System.out.printf("%15s||%15s%n","INGREDIENTS","QUANTITIES");
        for (int i=0;i<inventory.getIngredientsAmounts().size();i++){
            System.out.printf("%15s%15s%n",inventory.getIngredientsNames().get(i),inventory.getIngredientsAmounts().get(i).intValue());
        }
    }

    /**
     * Print all the listed recipes and their IDs as well.
     */
    public void currentRecipeList(Inventory inventory){
        Recipe recipe1 = new Recipe(1);
        int numberOfRecipes = recipe1.getNumberOfRecipes();
        Map<Integer,String> availableReceipts = recipe1.getAvailableRecipes();
        System.out.printf("%10s || %30s || %15s%n", "ID", "RECIPE", "CALORIES");
        for (int i = 0; i< numberOfRecipes; i++){
            Recipe recipe2 = new Recipe(i+1);
            System.out.printf("%10s || %30s || %15.2f%n",i+1, availableReceipts.get(i),recipe2.getRecipeCalories(inventory));
        }
    }

    public void currentRequestedDishes(Order order){
        Recipe recipe = new Recipe(1);

        Map<Integer,Integer> requestedDishes = order.getRequestedDishes();
        Map<Integer,String> recipeList = recipe.getAvailableRecipes();
        System.out.printf("%10s || %30s%n", "ORDER ID", "RECIPE");
        for(Map.Entry<Integer,Integer> entry: order.getRequestedDishes().entrySet()){
            int recipeID = entry.getValue();
            System.out.printf("%10s || %30s%n", entry.getKey(), recipeList.get(recipeID-1) );
        }
    }

}
