package connection;

import java.sql.*;

public class DatabaseConnection {
    private Connection dbConnection;
    public DatabaseConnection(){}

    public Connection connect(String dbName,String user,String password){
        dbConnection = null;
        try{
                Class.forName("org.postgresql.Driver");
            dbConnection = DriverManager.getConnection("jdbc:postgresql://postgres:5432/"+dbName,user,password);
                if(dbConnection!=null){
                    System.out.println("Connection Established");
                }else{
                    System.out.println("Connection Failed");
                }
        }catch(Exception e){
            System.out.println(e);
        }
        return dbConnection;
    }

  // public Connection connect(){
  //     dbConnection=null;
  //     try{
  //         Class.forName("org.h2.Driver");
  //         dbConnection = DriverManager.getConnection("jdbc:h2:C:/Users/User/IdeaProjects/Familyrestaurant4/RestaurantDB","Admin","");
  //         System.out.println("Online");
  //     }catch(Exception e){
  //         System.out.println("Error "+e);
  //     }
  //     return dbConnection;
  // }

    public ResultSet executeQuery(String query) {
        ResultSet result=null;
        try {
            Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

    public static void main(String[] args) {
        DatabaseConnection dbConnector = new DatabaseConnection();
      // dbConnector.connectPostgres("family_restaurant", "postgres", "j3141592");
    }
}
