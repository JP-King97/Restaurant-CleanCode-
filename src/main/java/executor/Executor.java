package executor;

import account.MoneyAccount;
import inventory.Inventory;
import orders.Order;
import printer.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Executor {
    public static void main(String[] args) {
        MoneyAccount account = new MoneyAccount();
        Map<Integer, Integer> ingredientsAmounts = new HashMap<>();
        Map<Integer, String> ingredientsNames = new HashMap<>();
        //would be good add the Map of the ingredientsIDs
        Inventory inventory = new Inventory(ingredientsAmounts, ingredientsNames);
        Scanner scanner = new Scanner(System.in);
        Print printer = new Print();
        Order order = new Order();
        inventory.setInitialAmounts();
        int recipeID;

         int optionSelected;
         do{
             optionSelected = printer.mainMenu(scanner);
             switch (optionSelected){
                 case 1:
                     printer.currentMoneyAmount(account);
                     break;
                 case 2:
                     printer.currentIngredientInventory(ingredientsAmounts,ingredientsNames);
                     break;
                 case 3:
                     inventory.buyIngredients(scanner,account);
                     break;
                 case 4:
                     printer.currentRecipeList();
                     break;
                 case 5:
                     System.out.println("Please introduce the recipe ID:");
                     recipeID = scanner.nextInt();
                     order.makeADish(recipeID,inventory);
                     break;
                 case 6:
                     System.out.println("Please introduce the recipe ID:");
                     recipeID = scanner.nextInt();
                     account.Deposit(order.sellADish(recipeID));
                     break;
                 case 7:
                     break;
                 default:
                     System.out.println("Option not founded");
                     break;
             }
         }while (optionSelected !=7);




    }
}
