# Restaurant-CleanCode-

This is a software developed for a client with a restaurant and he/she needs to track the inventory and record the transactions. 
This restaurant's possible dishes are divided into 3 types: food, drinks, or desserts.
Each dish has a list of ingredients and a production cost is related to a percentage of the total ingredients cost that can be calculated with the following rules:

For the food:

     -10% if it's normal.
     -20% if it's hot.
     -$400 if it's vegan.
     
For the drinks:

     -10% if it's a juice.
     -15% if it's a milkshake.
     -$400 if it's alcoholic beverage.
    
For the desserts:

     -12% if it's cold.
     -20% if it's hot.
    
Each dish also contains a selling price calculated by the production cost, the cost of the ingredients, and $1000. The desserts also include the number of calories in the recipe.

The program first present the option of synchronize the internal storage of the program with the local Excel file:

![image](https://github.com/JP-King97/Restaurant-CleanCode-/assets/102275322/d406aae8-65b5-4433-a23a-0ddb36b0d0d8)

The initial balance account is set in $100.000, after that the software is able to display the following menu:

![image](https://user-images.githubusercontent.com/102275322/232967634-677577e9-9f12-44bf-9463-620020363f99.png)

Options 1, 2, and 4 only display information (account balance, ingredients inventory, and available dishes, in that order). On the other hand, options 3, 5, and 7 modify the information (add ingredients amounts, decrease ingredients amount, and add the payment for the dish). Last but not least option number 7 ends the program.

Also the option 6 shows the dishes requested in the option 5 and display them in order to show the user what dishes are available to delivered it. 

![image](https://github.com/JP-King97/Restaurant-CleanCode-/assets/102275322/feeba0b9-eeb5-4f89-a443-39f9d850a54f)

The format of the Excel File is new.

![image](https://github.com/JP-King97/Restaurant-CleanCode-/assets/102275322/6a3b455b-34b2-4674-bddc-a60551b1966e)

All the recipes must be placed with 1 column between each one. Also a new table was created and contains the recipes' IDs, names, numbers of ingredients and one cell with the total number of recipes.

![image](https://github.com/JP-King97/Restaurant-CleanCode-/assets/102275322/d17231a9-9efa-4197-b63a-f1bd0c4d606e)


