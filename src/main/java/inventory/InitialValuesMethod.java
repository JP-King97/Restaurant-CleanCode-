package inventory;

import Data.ExcelFileReader;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class InitialValuesMethod {

    private Map<Integer, Integer> ingredientsAmounts;
    private Map<Integer, String> ingredientsNames;

    public InitialValuesMethod(Map<Integer, Integer> ingredientsAmounts,
                               Map<Integer, String> ingredientsNames){
        this.ingredientsAmounts=ingredientsAmounts;
        this.ingredientsNames=ingredientsNames;
    }



    public void getExcelFileValues(){
        ExcelFileReader reader = new ExcelFileReader(ingredientsAmounts,ingredientsNames);
        for (int i = 0; i < 36;i++){
            reader.setIngredientsName(i,reader.getIngredientName(i));
            reader.setIngredientsAmount(i,reader.getIngredientAmount(i));
            System.out.println("Ingredient: "+reader.getIngredientName(i)+" -- Quantity: "+reader.getIngredientAmount(i));
        }
    }

    public void getRandomValues(){
        Random random = new Random();
        ExcelFileReader reader = new ExcelFileReader(ingredientsAmounts, ingredientsNames);
        for(int i = 0; i < 36;i++){
            reader.setIngredientsName(i,reader.getIngredientName(i));
            Integer randomNumber = random.nextInt(5000);
            reader.setIngredientsAmount(i,reader.getIngredientAmount(i));
            System.out.println("Ingredient: "+reader.getIngredientName(i)+" -- Quantity: "+randomNumber);
        }
    }

    public void enterValuesManually(){
        Scanner scanner = new Scanner(System.in);
        ExcelFileReader reader = new ExcelFileReader(ingredientsAmounts,ingredientsNames)
        for (int i = 0; i < 36; i++){
            System.out.println("Please introduce the ingredient #"+(i+1)+":");
            reader.setIngredientsName(i, scanner.nextLine());
            System.out.println("Please introduce the amount(gr) #"+(i+1)+":");
            reader.setIngredientsAmount(i,scanner.nextInt());
        }
    }


