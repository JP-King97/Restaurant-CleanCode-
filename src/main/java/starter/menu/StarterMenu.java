package starter.menu;

import account.MoneyAccount;
import connection.DatabaseConnection;
import inventory.Inventory;
import order.Order;
import printer.Print;
import printer.Printer;
import sync.SyncInventoryDatabase;
import sync.SyncRecipesDatabase;

import java.util.Scanner;

public class StarterMenu {

    public static void main(String[] args) {
        DatabaseConnection dbConnector = new DatabaseConnection();
        dbConnector.connect("family_restaurant_db", "postgres", "j3141592");
        MoneyAccount account = new MoneyAccount(dbConnector);
        Inventory inventory = new Inventory(dbConnector);
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Order order = new Order(dbConnector);
        
        printSomething(() -> System.out.println("Do you want to synchronize the Database? Yes/No (Y/N): "));
        String answer = scanner.nextLine();
        if(answer.equals("Y") || answer.equals("y")){
            SyncInventoryDatabase syncInventory = new SyncInventoryDatabase(dbConnector);
            SyncRecipesDatabase syncRecipes = new SyncRecipesDatabase(dbConnector);
            syncInventory.syncDatabaseWithExcel();
            System.out.println("Inventory synchronized");
            syncRecipes.syncDatabaseWithExcel();
            System.out.println("Recipes synchronized");

        }else{
            System.out.println("Inventory and Recipes NOT synchronized");
        }


        int optionSelected;
        do {
            try {
                optionSelected = printer.mainMenu(scanner);
            } catch (Exception e) {
                printSomething(() -> System.out.println("Input not valid, please try it again"));

                optionSelected = 0;
                scanner.nextLine();
            }

            switch (optionSelected) {
                case 0, 7:
                    break;
                case 1:
                    printer.currentMoneyAmount(account);
                    break;
                case 2:
                    printer.currentIngredientInventory(inventory);
                    break;
                case 3:
                    try {
                        inventory.buyIngredients(scanner, account);
                    } catch (Exception e) {
                        printSomething(() -> System.out.println("Ingredient no founded"));
                    }
                    break;
                case 4:
                    printer.currentRecipeList(dbConnector);
                    break;
                case 5:
                    printSomething(() -> System.out.println("Please introduce the recipe ID:"));
                    try {
                        int recipeID = scanner.nextInt();
                        order.makeADish(recipeID, inventory);
                    } catch (Exception e) {
                        printSomething(() -> System.out.println("invalid input, please try it again"));
                        scanner.nextLine();
                        break;
                    }
                    break;
                case 6:
                    printSomething(() -> System.out.println("Please introduce the recipe ID:"));
                    try {
                        int recipeID = scanner.nextInt();
                        account.deposit(order.sellADish(recipeID));
                    } catch (Exception e) {
                        printSomething(() -> System.out.println("invalid input, please try it again"));
                        scanner.nextLine();
                        break;
                    }
                    break;
                default:
                    printSomething(() -> System.out.println("Option not founded"));
                    break;
            }
        } while (optionSelected != 7);

    }
    private static void printSomething(Print object) {
        object.Print();
    }

    private static void setUpDatabase(){

    }
}




