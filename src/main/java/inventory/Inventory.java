package inventory;

import account.MoneyAccount;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    private Map<Integer, Integer> ingredientsAmounts;
    private Map<Integer, String> ingredientsNames;
    int ingredientID;
    //
    public Inventory(Map<Integer, Integer> ingredientsAmounts,
                     Map<Integer, String> ingredientsNames){
    this.ingredientsAmounts = ingredientsAmounts;
    this.ingredientsNames = ingredientsNames;
   }

   public Inventory (){
   }
    //add the list of ingredients and initialize the amount (Integer) in Zero.
  // public void setNames() {
  //     InitialValuesMethod method = new InitialValuesMethod(ingredientsAmounts,ingredientsNames);
  //     method.getRandomValues();
  // }

    public Map<Integer, Integer> getIngredientsAmounts() {
        return ingredientsAmounts;
    }

    public void setIngredientsAmounts(Map<Integer, Integer> ingredientsAmounts) {
        this.ingredientsAmounts = ingredientsAmounts;
    }

    public Map<Integer, String> getIngredientsNames() {
        return ingredientsNames;
    }

    public void setIngredientsNames(Map<Integer, String> ingredientsNames) {
        this.ingredientsNames = ingredientsNames;
    }

    public void setInitialAmounts(){
        InitialValuesMethod method = new InitialValuesMethod(ingredientsAmounts,ingredientsNames);
        method.getExcelFileValues();
    }

    public void buyIngredients(Scanner scanner, MoneyAccount account){
        //request the ingredient and the amount
        String auxExit="N";
        int paymentAmount = 0;
        do{
            System.out.println("Please introduce the ingredient ID and the amount in gr: ");
            System.out.print("ingredient ID:");
            int ingredientID = scanner.nextInt();
            System.out.print("amount(gr):");
            int amount_gr = scanner.nextInt();
            System.out.print("continue?:Y or N");
            auxExit = scanner.next();
            //update the inventory
            updatedAmounts(ingredientID,amount_gr);
            paymentAmount = paymentAmount + ((getIngredientsAmounts().get(ingredientID))*(amount_gr));
        }while(auxExit != "Y");
        //Update the money account
        account.Withdrawal(paymentAmount);
    }

    public void updatedAmounts(int ingredientID,int newAmount){
        ingredientsAmounts.put(ingredientID,newAmount);
    }

    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> ingredientsAmounts = new HashMap<>();
        Map<Integer, String> ingredientsNames = new HashMap<>();
        Inventory inventory = new Inventory(ingredientsAmounts,ingredientsNames);
        inventory.setInitialAmounts();
       // inventory.setNames();

    }







}
