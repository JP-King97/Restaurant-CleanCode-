package Database;

import connection.DatabaseConnection;

public class ResetAccountHistoryDatabase {

    private DatabaseConnection dbConnection;

    public ResetAccountHistoryDatabase(DatabaseConnection dbConnection){this.dbConnection=dbConnection;}

    public void resetDatabase(){
        erasePreviousInformation();
        createTable();
        System.out.println("Money Account's history reset");
    }

    private void createTable(){
        try{
            dbConnection.executeQuery("CREATE TABLE account_history(" +
                                            "movement_id smallserial NOT NULL," +
                                            "current_money_account double precision NOT NULL);");
            dbConnection.executeUpdate("INSERT INTO Account_history (Current_Money_Account) VALUES (100000);");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }

    private void erasePreviousInformation(){
        try{
            dbConnection.executeQuery("DROP TABLE account_history;");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
    }
}
