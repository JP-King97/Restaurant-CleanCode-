package Database;

import connection.DatabaseConnection;
import file.reader.ExcelFileReader;
import inventory.Inventory;

import java.sql.ResultSet;
import java.util.Objects;

public class SyncInventoryDatabase {
    private DatabaseConnection dbConnection;
    public SyncInventoryDatabase(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void syncDatabaseWithExcel(){
        createTables();
        erasePreviousInventoryInformation();
        ExcelFileReader reader = new ExcelFileReader("Ingredients");
        int excelNumberOfIngredients = reader.getNumericalValueFromExcelFile(0,5).intValue();
        Inventory inventory = new Inventory(dbConnection);
        for (int i = 0; i < excelNumberOfIngredients; i++) {
            String excelName = reader.getStringValueFromExcelFile(i+1,1);
            if(!ingredientExists(excelName)){
                inventory.addNewIngredient(i);
            }
        }
        System.out.println("Inventory synchronized");
    }

    private void createTables(){
        try{
            dbConnection.executeQuery("CREATE TABLE Recipes(" +
                                        "Recipe_ID smallserial NOT NULL," +
                                        "Recipe_Name varchar(40) NOT NULL," +
                                        "Number_Of_Ingredients int NOT NULL," +
                                        "Dish_Type varchar(10) NOT NULL);");
            dbConnection.executeQuery("CREATE TABLE Ingredients(" +
                                        "Ingredient_ID smallserial NOT NULL," +
                                        "Ingredient_Name varchar(40) NOT NULL," +
                                        "Ingredient_Inventory_Amount_gr int NOT NULL," +
                                        "Ingredient_Unit_Price double precision NOT NULL);");
            dbConnection.executeQuery("CREATE TABLE Account_History(" +
                                        "Movement_ID smallserial NOT NULL," +
                                        "Current_Money_Account double precision NOT NULL);");
            dbConnection.executeUpdate("INSERT INTO Account_history (Current_Money_Account) VALUES (100000);");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void erasePreviousInventoryInformation(){
        try{
            dbConnection.executeUpdate("DELETE FROM Ingredients");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private boolean ingredientExists(String ingredientName){
        Inventory  inventory = new Inventory(dbConnection);
        boolean check = false;
        int aux = 0;
        for (int i = 1; i <= inventory.getNumberOfIngredients();i++){
            String dbName = null;
            try{
                ResultSet rs = dbConnection.executeQuery("SELECT Ingredient_Name FROM Ingredients WHERE Ingredient_ID = "+i+";");
                rs.next();
                dbName = rs.getString("Ingredient_Name");
            }catch(Exception e){
                System.out.println("Error "+e);
            }
            if(Objects.equals(ingredientName,dbName)){
                aux++;
            }
        }
        if(aux !=0){
            check =  true;
        }
        return check;
    }
}
