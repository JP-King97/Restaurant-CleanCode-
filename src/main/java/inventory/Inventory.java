package inventory;

import account.MoneyAccount;
import connection.DatabaseConnection;
import file.reader.ExcelFileReader;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.Scanner;

public class Inventory {

    private DatabaseConnection dbConnection;
    public Inventory(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public ResultSet getIngredientInfo(int ingredientID){
        ResultSet ingredientInfo = null;
        try{
            ingredientInfo = dbConnection.executeQuery("SELECT * FROM Ingredients WHERE Ingredient_ID = "+ingredientID+";");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return ingredientInfo;
    }
    public ResultSet getIngredientsIDs(){
        ResultSet ingredientsIDs = null;
        try{
            ingredientsIDs = dbConnection.executeQuery("SELECT Ingredient_ID FROM Ingredients");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return ingredientsIDs;
    }
    public int getNumberOfIngredients(){
        int ingredients = 0;
        try{
            ResultSet rs = dbConnection.executeQuery("SELECT Ingredient_ID FROM Ingredients");
            rs.last();
            ingredients = rs.getRow();
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        return ingredients;
    }

    //these methods show the information of each column in the table
    public ResultSet getIngredientsAmounts(){
        ResultSet ingredientsAmounts = null;
        try {
            ingredientsAmounts = dbConnection.executeQuery("SELECT INGREDIENT_INVENTORY_AMOUNT_GR FROM Ingredients");
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        return ingredientsAmounts;
    }

    public ResultSet getIngredientsNames(){
        ResultSet ingredientsNames = null;
        try{
            ingredientsNames = dbConnection.executeQuery("SELECT INGREDIENT_NAME FROM Ingredients");
        }catch (Exception e){
            System.out.println("Error "+e);
        }
        return ingredientsNames;
    }

    public ResultSet getIngredientUnitPrices(){
        ResultSet ingredientsUnitPrices = null;
        try{
            ingredientsUnitPrices = dbConnection.executeQuery("SELECT INGREDIENT_UNIT_PRICE FROM Ingredients");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return ingredientsUnitPrices;
    }


    public void updateIngredientName(int ingredientID,String newIngredientName){
        ExcelFileReader stringReader = new ExcelFileReader("Ingredients");
        updateIngredientDatabaseValue("INGREDIENT_NAME",
                "'"+newIngredientName+"'",
                ingredientID);
    }

    public void updateIngredientAmount(int ingredientID,int newIngredientAmount){
        ExcelFileReader numReader = new ExcelFileReader("Ingredients");
        updateIngredientDatabaseValue("INGREDIENT_INVENTORY_AMOUNT_GR",
                ""+newIngredientAmount+"",
                             ingredientID);
    }

    public void updateIngredientUnitPrice(int ingredientID, double newIngredientUnitPrice){
        ExcelFileReader numReader = new ExcelFileReader("Ingredients");
        updateIngredientDatabaseValue("INGREDIENT_UNIT_PRICE",
                ""+newIngredientUnitPrice+"",
                ingredientID);
    }

    private void updateIngredientDatabaseValue(String nameOfTheColumn, String valueOfTheCell, int ingredientID ){
        try {
            dbConnection.executeUpdate(
                    "UPDATE Ingredients " +
                            "SET "+nameOfTheColumn+" = " + valueOfTheCell  +" "+
                            "WHERE INGREDIENT_ID = "+ingredientID+";");
        } catch (Exception e) {
            System.out.println("Error "+e);
        }
    }

    public void addNewIngredient(int ingredient_ID){
        ExcelFileReader reader = new ExcelFileReader("Ingredients");
        try{
                dbConnection.executeUpdate("INSERT INTO Ingredients (Ingredient_ID, Ingredient_Name, Ingredient_Inventory_Amount_gr, Ingredient_Unit_Price) " +
                        "VALUES ("+reader.getNumericalValueFromExcelFile(ingredient_ID+1,0)+"," +
                        " '"+reader.getStringValueFromExcelFile(ingredient_ID+1,1)+"'," +
                        " "+reader.getNumericalValueFromExcelFile(ingredient_ID+1,2).intValue()+"," +
                        " '"+reader.getNumericalValueFromExcelFile(ingredient_ID+1,3)+"');");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    public void buyIngredients(Scanner scanner, MoneyAccount account){
        double paymentAmount=0;
        paymentAmount = addPurchasedAmount(scanner,paymentAmount);
        account.withdrawal(paymentAmount);
    }

    private double addPurchasedAmount(Scanner scanner, double paymentAmount){
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
            int currentAmount = 0;
            try{
                ResultSet ingredientsAmounts = getIngredientsAmounts();
                ingredientsAmounts.absolute(ingredientID);
                currentAmount = ingredientsAmounts.getInt(ingredientID);
                updateIngredientDatabaseValue("INGREDIENT_INVENTORY_AMOUNT_GR","'"+(amount_gr+currentAmount)+"'",ingredientID);
                ResultSet ingredientUnitPrices = getIngredientUnitPrices();
                ingredientUnitPrices.absolute(ingredientID);
                paymentAmount = paymentAmount + ((ingredientUnitPrices).getDouble("INGREDIENT_UNIT_PRICE")*(amount_gr));
            }catch(Exception e){
                System.out.println("Error " + e);
            }
        }while(!Objects.equals(auxExit, "N") && !Objects.equals(auxExit, "n") );
        return paymentAmount;
    }


}

