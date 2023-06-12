package inventory;

import account.MoneyAccount;
import data.modifiers.NumericalDataModifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {
    private Map<Integer,Double> ingredientsAmounts = new HashMap<>();
    private Map<Integer,String> ingredientsNames = new HashMap<>();
    private Map<Integer,Double> ingredientsUnitPrices = new HashMap<>();
    private Map<Integer, Double> ingredientsCalories = new HashMap<>();

    public Inventory(){
    }

    public void setInitialAmounts(Scanner scanner){
        InitialValues method = new InitialValues(ingredientsAmounts, ingredientsNames,ingredientsUnitPrices,ingredientsCalories);
        int option=0;
        do{
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> method.setValuesFromExcel();
                    case 2 -> method.setRandomValues();
                    case 3 -> method.enterValuesManually();
                    default -> System.out.println("Option not valid, try it again");
                }
                ingredientsAmounts = method.getIngredientsAmounts();
                ingredientsNames = method.getIngredientsNames();
                ingredientsUnitPrices = method.getIngredientsUnitPrices();
                ingredientsCalories = method.getIngredientsCalories();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Option not valid, try it again");
                scanner.nextLine();
            }


        }while(option != 1 && option != 2 && option != 3);
    }

    public Map<Integer,String> getIngredientsNames(){ return ingredientsNames;}

    public Map<Integer,Double> getIngredientsAmounts(){ return ingredientsAmounts;}

    public Map<Integer,Double> getIngredientsUnitPrices(){ return ingredientsUnitPrices;}

    public Map<Integer,Double> getIngredientsCalories(){ return ingredientsCalories;}

  //  public Map<Integer,Double> setIngredientsUnitPricesFromExcelFile(){
  //      NumericalDataModifier numericalReader = new NumericalDataModifier(ingredientsUnitPrices);
  //      int numberOfIngredients = 36; //Make it dynamic
  //      for(int i = 0; i< numberOfIngredients; i++){
  //          ingredientsUnitPrices.put(i,numericalReader.getNumericalValueFromExcel(i,1));
  //      }
  //      return ingredientsUnitPrices;
  //  }

    /**
     * reduce the money in the account by taking out the payment amount for the purchased ingredients
     *
     * @param scanner
     * @param account
     */
    public void buyIngredients(Scanner scanner, MoneyAccount account){
        Double paymentAmount = 0.0;
        paymentAmount = addPurchaseAmount(scanner, paymentAmount);
        account.Withdrawal(paymentAmount);
    }
    /**
     * Calculate the total payment amount of the purchased ingredients
     *
     * @param scanner
     * @param paymentAmount related to the purchased ingredients
     * @return total payment amount
     */
    private Double addPurchaseAmount(Scanner scanner, Double paymentAmount){
        String auxExit = "Y";
        do{
            System.out.println("Please introduce the ingredient ID and the amount in gr: ");
            System.out.print("ingredient ID:");
            int ingredientID = scanner.nextInt();
            System.out.print("amount(gr):");
            int amount_gr = scanner.nextInt();
            System.out.print("continue?:Y or N\n");
            auxExit = scanner.next();
            //update the inventory
            Double currentAmount = getIngredientsAmounts().get(ingredientID-1);
            updatedAmounts(ingredientID,(amount_gr+currentAmount));
            paymentAmount = paymentAmount + ((getIngredientsUnitPrices().get(ingredientID+1))*(amount_gr));
        }while(!Objects.equals(auxExit, "N"));
        return paymentAmount;
    }

    public void updatedAmounts(int ingredientID,Double newAmount){
        ingredientsAmounts.put(ingredientID-1,newAmount);
    }

}
