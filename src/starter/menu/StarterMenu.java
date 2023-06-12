package starter.menu;


import account.MoneyAccount;
import inventory.Inventory;
import orders.Order;
import printer.Print;
import printer.Printer;

import java.util.Scanner;

public class StarterMenu {


    public static void main(String[] args) {
        MoneyAccount account = new MoneyAccount();
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Order order = new Order();
        printSomething(() -> System.out.println("Please select the method to set the initial ingredient amount: \n" +
                "1. Set the Excel values\n" +
                "2. Set random values\n" +
                "3. Set the values manually\n"));

        inventory.setInitialAmounts(scanner);
        //inventory.setIngredientsUnitPricesFromExcelFile();
        int optionSelected;
        do {
            try {
                optionSelected = printer.mainMenu(scanner);
            } catch (Exception e) {
                printSomething(() -> System.out.println("Input not valid, please try it again"));
                //System.out.println("Input not valid, please try it again");
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
                    printer.currentRecipeList(inventory);
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
                        account.Deposit(order.sellADish(recipeID));
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

}
