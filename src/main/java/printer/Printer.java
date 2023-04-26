package printer;

import account.MoneyAccount;
import inventory.Inventory;
import menu.Recipe;

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
    public void currentRecipeList(){
        Recipe recipe = new Recipe();
        int numberOfRecipes = recipe.getNumberOfRecipes();
        Map<Integer,String> availableReceipts = recipe.getAvailableRecipes();
        System.out.printf("%10s||%20s%n","ID","RECEIPT");
        for (int i = 0; i< numberOfRecipes; i++){
            System.out.printf("%10s||%20s%n",i, availableReceipts.get(i));
        }
    }
}
