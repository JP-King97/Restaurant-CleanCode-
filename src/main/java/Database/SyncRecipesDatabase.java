package Database;

import connection.DatabaseConnection;
import file.reader.ExcelFileReader;
import menu.Recipe;

import java.sql.ResultSet;
import java.util.Objects;

public class SyncRecipesDatabase {
    private DatabaseConnection dbConnection;
    public SyncRecipesDatabase(DatabaseConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    public void syncDatabaseWithExcel(){
        erasePreviousRecipeInformation();
        createRecipesTable();
        //add new information
        ExcelFileReader reader = new ExcelFileReader("Recipes");
        int excelNumberOfRecipes = reader.getNumericalValueFromExcelFile(30,4).intValue();

        for(int i=0;i<excelNumberOfRecipes;i++){
            String excelName = reader.getStringValueFromExcelFile(i+30,2);
            Recipe recipe = new Recipe(dbConnection,1+i);
            if(!tableExists(excelName)){
                recipe.addNewRecipeTable(excelName,
                        reader.getNumericalValueFromExcelFile(i+30,3).intValue(),
                        reader.getNumericalValueFromExcelFile(i+30,1).intValue(),
                        reader.getStringValueFromExcelFile(1,((i+1)*4)-1)
                );
                recipe.insertNewData(excelName);
            }
        }
        System.out.println("Recipes synchronized");
    }

    private void erasePreviousRecipeInformation(){
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT Recipe_Name FROM Recipes;");
            while(rs.next()){
                dbConnection.executeUpdate("DROP TABLE "+rs.getString("Recipe_Name")+";");
            }
            dbConnection.executeUpdate("DROP TABLE Recipes;");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void createRecipesTable(){
        try{
            dbConnection.executeUpdate("CREATE TABLE Recipes(" +
                    "Recipe_ID smallserial NOT NULL," +
                    "Recipe_Name varchar(40) NOT NULL," +
                    "Number_Of_Ingredients int NOT NULL," +
                    "Dish_Type varchar(10) NOT NULL);");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private boolean tableExists(String tableName) {
        Recipe recipe = new Recipe(dbConnection);
        boolean check = false;
        int aux = 0;
        int nRecipes = recipe.getNumberOfRecipes();
        for (int i = 1; i <= nRecipes; i++) {
            String dbName = null;
            try {
                ResultSet rs = dbConnection.executeQuery("SELECT RECIPE_NAME FROM Recipes WHERE RECIPE_ID = " + i + ";");
                rs.next();
                dbName = rs.getString("RECIPE_NAME");
            } catch (Exception e) {
                System.out.println("Error " + e);
            }
            if (Objects.equals(tableName, dbName)) {
                aux++;
            }
        }
        if (aux !=0){
            check = true;
        }
        return check;
    }


   // public static void main(String[] args) {
   //     DatabaseConnection dbConnection = new DatabaseConnection();
   //     dbConnection.connect();
   //     SyncRecipesDatabase syncDB = new SyncRecipesDatabase(dbConnection);
   //     syncDB.syncDatabaseWithExcel();
   // }
}
