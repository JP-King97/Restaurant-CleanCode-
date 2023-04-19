package inventory;

import account.MoneyAccount;
import data.ExcelFileNumericalReader;
import data.ExcelFileReader;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {

    private final Map<Integer, Integer> INGREDIENTS_AMOUNTS;
    private final Map<Integer, String> INGREDIENTS_NAMES;
    private final Map<Integer, Integer> INGREDIENTS_UNIT_PRICES;
    private Map<Integer,Integer> ingredientsUnitPrices;

    public Inventory(Map<Integer, Integer> INGREDIENTS_AMOUNTS,
                     Map<Integer, String> INGREDIENTS_NAMES, Map<Integer, Integer> INGREDIENTS_UNIT_PRICES){
    this.INGREDIENTS_AMOUNTS = INGREDIENTS_AMOUNTS;
    this.INGREDIENTS_NAMES = INGREDIENTS_NAMES;
    this.INGREDIENTS_UNIT_PRICES = INGREDIENTS_UNIT_PRICES;
    }

    public Map<Integer, Integer> getINGREDIENTS_AMOUNTS() {
        return INGREDIENTS_AMOUNTS;
    }

    public Map<Integer, String> getINGREDIENTS_NAMES() {
        return INGREDIENTS_NAMES;
    }

    public Map<Integer, Integer> getINGREDIENTS_UNIT_PRICES() {
        return INGREDIENTS_UNIT_PRICES;
    }

    /**
     * Set the initial amount with the selected method
     */
    public void setInitialAmounts(){
        InitialValuesMethod method = new InitialValuesMethod(INGREDIENTS_AMOUNTS, INGREDIENTS_NAMES);
        method.setValuesFromExcel();
    }

    public Map<Integer,Integer> setIngredientsUnitPrices(){
        ExcelFileReader reader = new ExcelFileNumericalReader("Prices");
        int numberOfIngredients = 36;
        for(int i = 0; i< numberOfIngredients; i++){
            INGREDIENTS_UNIT_PRICES.put(i,reader.getNumericalValue(i,1));
        }
        return INGREDIENTS_UNIT_PRICES;
    }

    /**
     * reduce the money in the account by taking out the payment amount for the purchased ingredients
     *
     * @param scanner
     * @param account
     */
    public void buyIngredients(Scanner scanner, MoneyAccount account){
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
            int currentAmount = getINGREDIENTS_AMOUNTS().get(ingredientID);
            updatedAmounts(ingredientID,(amount_gr+currentAmount));
            paymentAmount = paymentAmount + ((getINGREDIENTS_UNIT_PRICES().get(ingredientID+1))*(amount_gr));
        }while(!Objects.equals(auxExit, "N"));
        return paymentAmount;
    }
    /**

     * Set new ingredients' amounts related to the ingredients' IDs
     * @param ingredientID
     * @param newAmount
     */
    public void updatedAmounts(int ingredientID,int newAmount){
        INGREDIENTS_AMOUNTS.put(ingredientID,newAmount);
    }

}
