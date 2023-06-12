package inventory;

import data.modifiers.NumericalDataModifier;
import data.modifiers.StringDataModifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class InitialValues {
    private Map<Integer, Double> ingredientsAmounts = new HashMap<>();
    private Map<Integer, Double> ingredientsUnitPrices = new HashMap<>();
    private Map<Integer, Double> ingredientsCalories = new HashMap<>();
    private Map<Integer,String> ingredientsNames= new HashMap<>();
    private int totalNumberOfIngredients;

    public Map<Integer, Double> getIngredientsAmounts() {
        return ingredientsAmounts;
    }

    public Map<Integer, Double> getIngredientsUnitPrices() {
        return ingredientsUnitPrices;
    }

    public Map<Integer, Double> getIngredientsCalories() {
        return ingredientsCalories;
    }

    public Map<Integer, String> getIngredientsNames() {
        return ingredientsNames;
    }

    //private String sheetName = "WarehouseQuantities";


    public InitialValues (Map<Integer, Double> ingredientsAmounts,
                          Map<Integer,String> ingredientsNames,
                          Map<Integer, Double> ingredientsUnitPrices,
                          Map<Integer, Double> ingredientsCalories){
        this.ingredientsAmounts = ingredientsAmounts;
        this.ingredientsNames = ingredientsNames;
        this.ingredientsUnitPrices = ingredientsUnitPrices;
        this.ingredientsCalories = ingredientsCalories;
    }

    public void getTotalNumberOfIngredients(){
        NumericalDataModifier numericalReader = new NumericalDataModifier();
        totalNumberOfIngredients = (int) numericalReader.getNumericalValueFromExcel(0,6);
    }

    /**
     * set the initial values from an Excel File
     */
    public void setValuesFromExcel() {
        StringDataModifier stringReader = new StringDataModifier(ingredientsNames);
        NumericalDataModifier numericalReader1 = new NumericalDataModifier(ingredientsAmounts);
        NumericalDataModifier numericalReader2 = new NumericalDataModifier(ingredientsUnitPrices);
        NumericalDataModifier numericalReader3 = new NumericalDataModifier(ingredientsCalories);
        getTotalNumberOfIngredients();
        for (int i = 0; i < totalNumberOfIngredients; i++) {
            stringReader.setStringValue(i, stringReader.getStringValueFromExcel(i+1,2));
            numericalReader1.setNumericalValue(i, numericalReader1.getNumericalValueFromExcel(i+1,3));
            numericalReader2.setNumericalValue(i, numericalReader2.getNumericalValueFromExcel(i+1,4));
            numericalReader3.setNumericalValue(i, numericalReader3.getNumericalValueFromExcel(i+1,5));
        }
    }
    /**
     * Set initial random values
     */
    public void setRandomValues() {
        Random random = new Random();
        StringDataModifier stringReader = new StringDataModifier(ingredientsNames);
        NumericalDataModifier numericalReader1 = new NumericalDataModifier(ingredientsAmounts);
        NumericalDataModifier numericalReader2 = new NumericalDataModifier(ingredientsUnitPrices);
        NumericalDataModifier numericalReader3 = new NumericalDataModifier(ingredientsCalories);
        for (int i = 0; i < totalNumberOfIngredients; i++) {
            stringReader.setStringValue(i, stringReader.getStringValueFromExcel(i+1,2));
            double randomNumber = random.nextDouble(5000);
            numericalReader1.setNumericalValue(i, randomNumber);
            randomNumber = random.nextDouble(15);
            numericalReader2.setNumericalValue(i, randomNumber);
            numericalReader3.setNumericalValue(i, numericalReader3.getNumericalValueFromExcel(i+1,5));
        }
    }

    /**
     * Set initial values manually
     */
    public void enterValuesManually() {
        Scanner scanner = new Scanner(System.in);
        StringDataModifier stringReader = new StringDataModifier(ingredientsNames);
        NumericalDataModifier numericalReader1 = new NumericalDataModifier(ingredientsAmounts);
        NumericalDataModifier numericalReader2 = new NumericalDataModifier(ingredientsUnitPrices);
        NumericalDataModifier numericalReader3 = new NumericalDataModifier(ingredientsCalories);
        for (int i = 0; i < totalNumberOfIngredients; i++) {
            System.out.println("Please introduce the ingredient name #" + (i + 1) + ":");
            stringReader.setStringValue(i, scanner.nextLine());
            System.out.println("Please introduce the amount(gr) #" + (i + 1) + ":");
            numericalReader1.setNumericalValue(i, scanner.nextDouble());
            System.out.println("Please introduce the unit price($/gr) #" + (i + 1) + ":");
            numericalReader2.setNumericalValue(i, scanner.nextDouble());
            System.out.println("Please introduce the calories(kcal/gr) #" + (i + 1) + ":");
            numericalReader3.setNumericalValue(i, scanner.nextDouble());
        }
    }
}
