package inventory;

import data.ExcelFileReader;
import data.ExcelFileStringReader;


import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class InitialValuesMethod {

    private Map<Integer, Integer> ingredientsAmounts;
    private Map<Integer, String> ingredientsNames;
    private String sheetName = "WarehouseQuantities";

    public InitialValuesMethod(Map<Integer, Integer> ingredientsAmounts,
                               Map<Integer, String> ingredientsNames) {
        this.ingredientsAmounts = ingredientsAmounts;
        this.ingredientsNames = ingredientsNames;
    }


    public void getExcelFileValues() {
        ExcelFileReader reader = new ExcelFileReader(ingredientsAmounts, ingredientsNames,sheetName);
        for (int i = 0; i < 36; i++) {
            reader.setStringValue(i, reader.getStringValue(i+1,0));
            reader.setNumericalValue(i, reader.getNumericalValue(i+1,1));
            //System.out.println("Ingredient: " + reader.getStringValue(i+1,0) + " -- Quantity: " + reader.getNumericalValue(i+1,1));
        }
    }

    public void getRandomValues() {
        Random random = new Random();
        ExcelFileReader reader = new ExcelFileReader(ingredientsAmounts, ingredientsNames,sheetName);
        for (int i = 0; i < 36; i++) {
            reader.setStringValue(i, reader.getStringValue(i+1,0));
            Integer randomNumber = random.nextInt(5000);
            reader.setNumericalValue(i, reader.getNumericalValue(i+1,1));
            //System.out.println("Ingredient: " + reader.getStringValue(i+1,0) + " -- Quantity: " + randomNumber);
        }
    }

    public void enterValuesManually() {
        Scanner scanner = new Scanner(System.in);
        ExcelFileReader reader = new ExcelFileReader(ingredientsAmounts, ingredientsNames,sheetName);
        for (int i = 0; i < 36; i++) {
            System.out.println("Please introduce the ingredient #" + (i + 1) + ":");
            reader.setStringValue(i, scanner.nextLine());
            System.out.println("Please introduce the amount(gr) #" + (i + 1) + ":");
            reader.setNumericalValue(i, scanner.nextInt());
        }
    }
}


