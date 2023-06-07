package Database;

import connection.DatabaseConnection;

public class ResetOrdersDatabase {

    private DatabaseConnection dbConnection;
    public ResetOrdersDatabase(DatabaseConnection dbConnection){this.dbConnection=dbConnection;}

    public void resetDatabase(){
        erasePreviousInformation();
        createTable();
        System.out.println("Order's history reset");
    }

    private void createTable(){
        try{
            dbConnection.executeQuery("CREATE TABLE orders(" +
                                         "orders_ID smallserial NOT NULL," +
                                         "recipe_name varchar(40) NOT NULL," +
                                         "type_of_dish varchar(10) NOT NULL," +
                                         "state varchar(15) NOT NULL);");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void erasePreviousInformation(){
        try{
            dbConnection.executeQuery("DROP TABLE orders;");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }



}
