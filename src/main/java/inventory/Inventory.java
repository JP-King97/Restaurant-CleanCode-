package inventory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Integer, Integer> ingredientsAmounts;
    private Map<Integer, String> ingredientsNames;
    //
    public Inventory(Map<Integer, Integer> ingredientsAmounts,
                     Map<Integer, String> ingredientsNames){
    this.ingredientsAmounts = ingredientsAmounts;
    this.ingredientsNames = ingredientsNames;
   }
    //add the list of ingredients and initialize the amount (Integer) in Zero.
    public void setNames() {
        InitialValuesMethod method = new InitialValuesMethod(ingredientsAmounts,ingredientsNames);
        method.getExcelFileValues();
    }
    public void setAmounts(){
        InitialValuesMethod method = new InitialValuesMethod(ingredientsAmounts,ingredientsNames);
        method.getExcelFileValues();
    }
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> ingredientsAmounts = new HashMap<>();
        Map<Integer, String> ingredientsNames = new HashMap<>();
        Inventory inventory = new Inventory(ingredientsAmounts,ingredientsNames);
        inventory.setAmounts();
        inventory.setNames();

    }







}
