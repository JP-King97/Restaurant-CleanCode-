package Database;

import connection.DatabaseConnection;

import java.sql.ResultSet;

public class ResetAccountHistoryDatabase {

    private DatabaseConnection dbConnection;

    public ResetAccountHistoryDatabase(DatabaseConnection dbConnection){this.dbConnection=dbConnection;}

    public void resetDatabase(){
        erasePreviousInformation();
        createTable();
        System.out.println("Money Account's history reset");
    }

    private void createTable(){

        try {
            ResultSet rs = dbConnection.executeQuery("SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'money_account_history');");
            rs.next();
            String tableExists = rs.getString(1);
            if(tableExists.equals("f")){
                dbConnection.executeUpdate("CREATE TABLE money_account_history(" +
                        "movement_id smallserial NOT NULL," +
                        "current_money_account double precision NOT NULL);");
                dbConnection.executeUpdate("INSERT INTO money_account_history (Current_Money_Account) VALUES (100000);");
            }
        }catch(Exception e){
            System.out.println("Error "+e);
        }

    }

    private void erasePreviousInformation(){
        try{
            dbConnection.executeUpdate("DROP TABLE money_account_history;");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }
}
