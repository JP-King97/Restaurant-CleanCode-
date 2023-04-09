package printer;

import account.MoneyAccount;
import inventory.Inventory;
import menu.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Print {
    private Map<Integer, Integer> numericalMap = new HashMap<>();
    private Map<Integer, String> stringMap = new HashMap<>();

    public Print(Map<Integer, Integer> numericalMap, Map<Integer, String> stringMap) {
        this.numericalMap = numericalMap;
        this.stringMap = stringMap;
    }

    public Print(){
    }

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
        int option = scanner.nextInt();
        return option;
    }

    public void currentMoneyAmount(MoneyAccount account ){
        System.out.println("Current money account: $"+account.getCurrentBalance());
    }

    public void currentIngredientInventory(Map<Integer, Integer> numericalMap, Map<Integer, String> stringMap){
        Inventory inventory = new Inventory(numericalMap,stringMap);
        System.out.printf("%15s||%15s%n","INGREDIENTS","QUANTITIES");
        for (int i=0;i<numericalMap.size();i++){
            System.out.printf("%15s%15s%n",inventory.getIngredientsNames().get(i),inventory.getIngredientsAmounts().get(i));
        }
    }

    public void currentRecipeList(){
        Recipe recipe = new Recipe();
        System.out.printf("%10s||%20s%n","ID","RECEIPT");
        for (int i = 0; i< recipe.getNumberOfRecipes(); i++){
            System.out.printf("%10s||%20s%n",i, recipe.getAvailableReceipts().get(i));;
        }

    }
    public void switchBetweenOptions(){


    }

      public static void main(String[] args) {
           Inventory inventory = new Inventory();
           inventory.setInitialAmounts();
           Print printer = new Print();
           Scanner scanner = new Scanner(System.in);
           int optionSelected = printer.mainMenu(scanner);
           printer.currentRecipeList();
//
      }


}