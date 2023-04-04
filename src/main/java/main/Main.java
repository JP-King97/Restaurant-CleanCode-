package main;

import account.MoneyAccount;
import inventory.Inventory;
import printer.Print;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoneyAccount account = new MoneyAccount();
        Map<Integer, Integer> ingredientsAmounts=new HashMap<>();
        Map<Integer, String> ingredientsNames=new HashMap<>();
        Inventory inventory = new Inventory(ingredientsAmounts,ingredientsNames);
        inventory.setInitialAmounts();

    }

    public void starterProgram(){
        MoneyAccount account = new MoneyAccount();
        Map<Integer, Integer> ingredientsAmounts=new HashMap<>();
        Map<Integer, String> ingredientsNames=new HashMap<>();
        Inventory inventory = new Inventory(ingredientsAmounts,ingredientsNames);
        inventory.setInitialAmounts();
        Scanner scanner = new Scanner(System.in);
        Print printer = new Print();

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
                    break;
                case 5:
                    //5.Make a dish.
                    break;
                case 6:
                    //6.Sell a dish.
                    break;
                case 7:
                    //7.EXIT.
                    break;
            }
        }while (optionSelected !=7);




    }
}
