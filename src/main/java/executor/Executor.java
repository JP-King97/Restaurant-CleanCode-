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
                     //1.Print my current money amount
                     printer.currentMoneyAmount(account);
                     break;
                 case 2:
                     //2.Print my current ingredient inventory.
                     printer.currentIngredientInventory(ingredientsAmounts,ingredientsNames);
                     break;
                 case 3:
                     //3.Buy ingredients.
                     inventory.buyIngredients(scanner,account);
                     break;
                 case 4:
                     //4.Print my dishes inventory.
                     printer.currentRecipeList();
                     break;
                 case 5:
                     //5.Make a dish.
                     System.out.println("Please introduce the recipe ID:");
                     recipeID = scanner.nextInt();
                     order.makeADish(recipeID,inventory);
                     break;
                 case 6:
                     //6.Sell a dish.
                     System.out.println("Please introduce the recipe ID:");
                     recipeID = scanner.nextInt();
                     account.Deposit(order.sellADish(recipeID));
                     break;
                 case 7:
                     //7.EXIT.
                     break;
                 default:
                     System.out.println("Option not founded");
                     break;
             }
         }while (optionSelected !=7);




    }
}
