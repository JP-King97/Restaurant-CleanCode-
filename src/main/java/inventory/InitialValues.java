package inventory;

import data.modifiers.NumericalDataModifier;
import data.modifiers.StringDataModifier;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class InitialValues {

    private Map<Integer, Double> ingredientsAmounts;
    private Map<Integer,String> ingredientsNames;
    private String sheetName = "WarehouseQuantities";
    private Integer numberOfIntegers = 36;

    public InitialValues (Map<Integer, Double> ingredientsAmounts,
                          Map<Integer,String> ingredientsNames){
        this.ingredientsAmounts = ingredientsAmounts;
        this.ingredientsNames = ingredientsNames;
    }

    /**
     * set the initial values from an Excel File
     */
    public void setValuesFromExcel() {
        NumericalDataModifier numericalReader = new NumericalDataModifier(sheetName,ingredientsAmounts);
        StringDataModifier stringReader = new StringDataModifier(sheetName,ingredientsNames);
        for (int i = 0; i < numberOfIntegers; i++) {
            stringReader.setStringValue(i, stringReader.getStringValueFromExcelFile(i+1,0));
            numericalReader.setNumericalValue(i, numericalReader.getNumericalValueFromExcelFile(i+1,1));
        }
    }
    /**
     * Set initial random values
     */
    public void setRandomValues() {
        Random random = new Random();
        NumericalDataModifier numericalReader = new NumericalDataModifier(sheetName,ingredientsAmounts);
        StringDataModifier stringReader = new StringDataModifier(sheetName,ingredientsNames);
        for (int i = 0; i < numberOfIntegers; i++) {
            stringReader.setStringValue(i, stringReader.getStringValueFromExcelFile(i+1,0));
            Double randomNumber = random.nextDouble(5000);
            numericalReader.setNumericalValue(i, randomNumber);
        }
    }

    /**
     * Set initial values manually
     */
    public void enterValuesManually() {
        Scanner scanner = new Scanner(System.in);
        NumericalDataModifier numericalReader = new NumericalDataModifier(sheetName,ingredientsAmounts);
        StringDataModifier stringReader = new StringDataModifier(sheetName,ingredientsNames);
        for (int i = 0; i < numberOfIntegers; i++) {
            System.out.println("Please introduce the ingredient #" + (i + 1) + ":");
            stringReader.setStringValue(i, scanner.nextLine());
            System.out.println("Please introduce the amount(gr) #" + (i + 1) + ":");
            numericalReader.setNumericalValue(i, scanner.nextDouble());
        }
    }

}
