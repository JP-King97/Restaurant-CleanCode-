package inventory;

import account.MoneyAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {

    private Map<Integer, Integer> ingredientsAmounts;
    private Map<Integer, String> ingredientsNames;
    private final Map<Integer, String> ingredientIDs = new HashMap<>();
    int ingredientID;
    //
    public Inventory(Map<Integer, Integer> ingredientsAmounts,
                     Map<Integer, String> ingredientsNames){
    this.ingredientsAmounts = ingredientsAmounts;
    this.ingredientsNames = ingredientsNames;
    }

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

    /**
     * Set the initial amount with the selected method
     */
    public void setInitialAmounts(){
        InitialValuesMethod method = new InitialValuesMethod(ingredientsAmounts,ingredientsNames);
        method.setValuesFromExcel();
    }

    /**
     * reduce the money in the account by taking out the payment amount for the purchased ingredients
     *
     * @param scanner
     * @param account
     */
    public void buyIngredients(Scanner scanner, MoneyAccount account){
        //request the ingredient and the amount
        int paymentAmount = 0;
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
    private int addPurchaseAmount(Scanner scanner, int paymentAmount){
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
            int currentAmount = getIngredientsAmounts().get(ingredientID);
            updatedAmounts(ingredientID,(amount_gr+currentAmount));
            paymentAmount = paymentAmount + ((getIngredientsAmounts().get(ingredientID))*(amount_gr));
        }while(!Objects.equals(auxExit, "N"));
        return paymentAmount;
    }

    /**
     * Set new ingredients' amounts related to the ingredients' IDs
     * @param ingredientID
     * @param newAmount
     */
    public void updatedAmounts(int ingredientID,int newAmount){
        ingredientsAmounts.put(ingredientID,newAmount);
    }

}
