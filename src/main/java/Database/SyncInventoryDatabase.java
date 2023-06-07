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
        erasePreviousInventoryInformation();
        createTable();
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

    private void createTable(){
        try{

            dbConnection.executeUpdate("CREATE TABLE Ingredients(" +
                                        "Ingredient_ID smallserial NOT NULL," +
                                        "Ingredient_Name varchar(40) NOT NULL," +
                                        "Ingredient_Inventory_Amount_gr int NOT NULL," +
                                        "Ingredient_Unit_Price double precision NOT NULL);");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void erasePreviousInventoryInformation(){
        try{
            dbConnection.executeUpdate("DROP TABLE Ingredients");
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
