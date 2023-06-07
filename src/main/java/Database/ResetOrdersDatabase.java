package Database;

import connection.DatabaseConnection;

import java.sql.ResultSet;

public class ResetOrdersDatabase {

    private DatabaseConnection dbConnection;
    public ResetOrdersDatabase(DatabaseConnection dbConnection){this.dbConnection=dbConnection;}

    public void resetDatabase(){
        erasePreviousInformation();
        createTable();
        System.out.println("Order's history reset");
    }

    private void createTable(){
        try {
            ResultSet rs = dbConnection.executeQuery("SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'orders');");
            rs.next();
            String tableExists = rs.getString(1);
            if(tableExists.equals("f")){
                dbConnection.executeUpdate("CREATE TABLE Orders(" +
                        "Order_ID smallserial NOT NULL," +
                        "Recipe_name varchar(40) NOT NULL," +
                        "Type_of_dish varchar(20) NOT NULL," +
                        "State varchar(15) NOT NULL);");
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void erasePreviousInformation(){
        try{
            dbConnection.executeUpdate("DROP TABLE orders;");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }



}
