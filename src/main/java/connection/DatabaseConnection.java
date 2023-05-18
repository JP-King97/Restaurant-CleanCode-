package connection;

import java.sql.*;

public class DatabaseConnection {
    private Connection dbConnection;
    public DatabaseConnection(){}
    public Connection connect(){
        dbConnection=null;
        try{
            Class.forName("org.h2.Driver");
            dbConnection = DriverManager.getConnection("jdbc:h2:C:/Users/User/IdeaProjects/Familyrestaurant4/RestaurantDB","Admin","");
            System.out.println("Online");
        }catch(Exception e){
            System.out.println("Error "+e);
        }
        return dbConnection;
    }

    public ResultSet executeQuery(String query) {
        ResultSet result=null;
        try {
            Statement statement = dbConnection.createStatement();
            result = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error "+e);;
        }
        return result;
    }

    public void executeUpdate(String query){
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }

}
