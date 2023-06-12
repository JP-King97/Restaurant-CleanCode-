package file.reader;

import java.io.*;

public class ExcelFileReader {

    private String path = "C:/Users/User/IdeaProjects/FamilyRestaurant_JavaNoDependencies/Inventory.csv";

    private String line;

    public ExcelFileReader() {
    }

    public void getCellValues(int row, int column) {

        BufferedReader bReader;

        {
            try {
                bReader = new BufferedReader(new FileReader(path));
                for(int i=0; i<row;i++){
                    line = bReader.readLine();
                    String[] values = line.split(";");
                    System.out.println(values[column-1]);
                }
                while ((line = bReader.readLine()) != null) {
                    String[] values = line.split(";");
                    System.out.println(values[0]);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

   // private int tableLocation(String tableName){
   //     int[] cellCoordinate = new int[2];
   //     switch(tableName) {
   //         case "ID":
   //             cellCoordinate[0] = 2; //row
   //             cellCoordinate[1] = 1; //column
   //             break;
   //         case "IngredientName":
   //             cellCoordinate[0] = 2; //row
   //             cellCoordinate[1] = 2; //column
   //             break;
   //         case "IngredientQuantities":
   //             cellCoordinate[0] = 2; //row
   //             cellCoordinate[1] = 3; //column
   //             break;
   //         case "IngredientUnitPrice":
   //             cellCoordinate[0] = 2; //row
   //             cellCoordinate[1] = 4; //column
   //             break;
   //         case "IngredientCalories":
   //             cellCoordinate[0] = 2; //row
   //             cellCoordinate[1] = 5; //column
   //             break;
//
   //     }
//
   //     //inventory(ingredients)
   //     int[] IDs = {,};
//
   //     //recipes(list)
//
   //     //Vegan_Pasta
   //     //MashedPotatoes_Chicken_Broccoli
   //     //Hot_Spinach_Cream
   //     //Oreo_Milkshake
   //     //Juice_Fruit_Water
   //     //Juice_Fruit_Milk
   //     //Margarita
   //     //Tequila_Sunrise
   //     //Oreo_Cheesecake
   //     //Lemon_Cake
   //     //Passion_Fruit_Cake
//
   //     //*Orders' history
   //     //*money account
   // }

}


