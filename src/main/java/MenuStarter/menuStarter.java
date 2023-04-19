package MenuStarter;

import account.MoneyAccount;
import inventory.Inventory;
import orders.Order;
import printer.Print;
import printer.Printer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class menuStarter {
    public static void main(String[] args) {
        MoneyAccount account = new MoneyAccount();
        Map<Integer, Integer> ingredientsAmounts = new HashMap<>();
        Map<Integer, String> ingredientsNames = new HashMap<>();
        Map<Integer, Integer> ingredientsUnitPrices =  new HashMap<>();
        Inventory inventory = new Inventory(ingredientsAmounts, ingredientsNames, ingredientsUnitPrices);
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Order order = new Order();
        inventory.setInitialAmounts();
        inventory.setIngredientsUnitPrices();
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
                    printer.currentIngredientInventory(ingredientsAmounts, ingredientsNames, ingredientsUnitPrices);
                    break;
                case 3:
                    try {
                        inventory.buyIngredients(scanner, account);
                    } catch (Exception e) {
                        printSomething(() -> System.out.println("Ingredient no founded"));
                    }
                    break;
                case 4:
                    printer.currentRecipeList();
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

