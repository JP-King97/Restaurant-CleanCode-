package account;

import connection.DatabaseConnection;

import java.sql.ResultSet;
import java.util.Scanner;

public class MoneyAccount {

    private DatabaseConnection dbConnection;
    private double currentBalance ;//= 100000.0;
    private ResultSet resultSet;

    public MoneyAccount (DatabaseConnection dbConnection){
        this.dbConnection=dbConnection;
    }

    public double getCurrentBalance() {
        try {
            resultSet = dbConnection.executeQuery("SELECT CURRENT_MONEY_ACCOUNT FROM Account_History");
            resultSet.last();
            currentBalance = resultSet.getDouble("CURRENT_MONEY_ACCOUNT");
        } catch (Exception e) {
            System.out.println("Error "+e);;
        }
        return currentBalance;
    }

    public void withdrawal (double withdrawal){
         double newBalance = (getCurrentBalance() - withdrawal);
        dbConnection.executeUpdate("INSERT INTO Account_History(CURRENT_MONEY_ACCOUNT) VALUES ('"+newBalance+"');");
    }

    public void deposit (double payment){
        double newBalance = (getCurrentBalance() + payment);
        dbConnection.executeUpdate("INSERT INTO Account_History(CURRENT_MONEY_ACCOUNT) VALUES ('"+newBalance+"');");
    }

}


