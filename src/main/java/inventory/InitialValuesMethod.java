package inventory;

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

    public InitialValuesMethod(Map<Integer, Integer> ingredientsAmounts, Map<Integer, String> ingredientsNames){
        this.ingredientsAmounts=ingredientsAmounts;
        this.ingredientsNames=ingredientsNames;}




    public XSSFSheet readFile(){
        String filePath = "C:\\Users\\User\\IdeaProjects\\FamilyRestaurant\\Inventory.xlsx";
        FileInputStream fileInputStream = null;
        XSSFWorkbook xssfWorkbook = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            xssfWorkbook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {throw new RuntimeException(e);}
        XSSFSheet inventorySheet = xssfWorkbook.getSheet("WarehouseQuantities");
        return inventorySheet;
    }

    public void getExcelFileValues(){
        for (int i = 0; i < 36;i++){
            setIngredientsName(i,getIngredientName(i));
            setIngredientsAmount(i,getIngredientAmount(i));
            System.out.println("Ingredient: "+getIngredientName(i)+" -- Quantity: "+getIngredientAmount(i));
        }
    }

    public void getRandomValues(){
        Random random = new Random();
        for(int i = 0; i < 36;i++){
            setIngredientsName(i,getIngredientName(i));
            Integer randomNumber = random.nextInt(5000);
            setIngredientsAmount(i,randomNumber);
            System.out.println("Ingredient: "+getIngredientName(i)+" -- Quantity: "+randomNumber);
        }
    }

    public void enterValuesManually(){
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 36; i++){
            System.out.println("Please introduce the ingredient #"+(i+1)+":");
            ingredientsNames.put(i,scanner.nextLine());
            System.out.println("Please introduce the amount(gr) #"+(i+1)+":");
            ingredientsNames.put(i,scanner.nextLine());
        }
    }

    public String getIngredientName(int id){
        return readFile().getRow(id+1).getCell(0).getStringCellValue();
    }
    public void setIngredientsName(int id, String name) {
        ingredientsNames.put(id, name);
    }

    public Integer getIngredientAmount(int id){
        return Integer.valueOf(readFile().getRow(id+1).getCell(1).getRawValue());
    }
    public void setIngredientsAmount(int id,Integer amount) {
        ingredientsAmounts.put(id,amount);
    }
}
