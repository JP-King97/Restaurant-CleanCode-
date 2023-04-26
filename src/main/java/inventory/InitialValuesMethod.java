package inventory;

import data.ExcelFileReader;


import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class InitialValuesMethod {
    private final Map<Integer, Integer> INGREDIENTS_AMOUNTS;
    private final Map<Integer, String> INGREDIENTS_NAMES;
    private final String SHEET_NAME = "WarehouseQuantities";
    private final Integer NUMBER_OF_INGREDIENTS = 36;

    public InitialValuesMethod(Map<Integer, Integer> INGREDIENTS_AMOUNTS,
                               Map<Integer, String> INGREDIENTS_NAMES) {
        this.INGREDIENTS_AMOUNTS = INGREDIENTS_AMOUNTS;
        this.INGREDIENTS_NAMES = INGREDIENTS_NAMES;
    }

    /**
     * set the initial values from an Excel File.
     */
    public void setValuesFromExcel() {
        ExcelFileReader reader = new ExcelFileReader(INGREDIENTS_AMOUNTS, INGREDIENTS_NAMES, SHEET_NAME);
        for (int i = 0; i < NUMBER_OF_INGREDIENTS; i++) {
            reader.setStringValue(i, reader.getStringValue(i+1,0));
            reader.setNumericalValue(i, reader.getNumericalValue(i+1,1));
            }
    }

    /**
     * Set initial random values
     */
    public void setRandomValues() {
        Random random = new Random();
        ExcelFileReader reader = new ExcelFileReader(INGREDIENTS_AMOUNTS, INGREDIENTS_NAMES, SHEET_NAME);
        for (int i = 0; i < NUMBER_OF_INGREDIENTS; i++) {
            reader.setStringValue(i, reader.getStringValue(i+1,0));
            Integer randomNumber = random.nextInt(5000);
            reader.setNumericalValue(i, randomNumber);

        }
    }

    /**
     * Set initial values manually
     */
    public void enterValuesManually() {
        Scanner scanner = new Scanner(System.in);
        ExcelFileReader reader = new ExcelFileReader(INGREDIENTS_AMOUNTS, INGREDIENTS_NAMES, SHEET_NAME);
        for (int i = 0; i < NUMBER_OF_INGREDIENTS; i++) {
            System.out.println("Please introduce the ingredient #" + (i + 1) + ":");
            reader.setStringValue(i, scanner.nextLine());
            System.out.println("Please introduce the amount(gr) #" + (i + 1) + ":");
            reader.setNumericalValue(i, scanner.nextInt());
        }
    }
}


