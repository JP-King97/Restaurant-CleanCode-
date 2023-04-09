package orders;

import inventory.Inventory;
import menu.Desserts;
import menu.Dish;
import menu.Drinks;
import menu.Food;

import java.util.Map;

public class Order {

    String typeOfDish;
    public Order(){

    }

    public void makeADish(int recipeID,Inventory inventory){
        //get the type of dish and production cost
        Dish dish;
        switch (recipeID) {
            case 0, 1, 2 -> {
                typeOfDish = "FOOD";
                dish = new Food(recipeID);
                dish.getProductionCost();
                //check if there are enough required ingredients
                enoughIngredients(dish, inventory);
            }
            case 3, 4, 5, 6, 7 -> {
                typeOfDish = "DRINK";
                dish = new Drinks(recipeID);
                dish.getProductionCost();
                enoughIngredients(dish, inventory);
            }
            case 8, 9, 10 -> {
                typeOfDish = "DESSERT";
                dish = new Desserts(recipeID);
                dish.getProductionCost();
                enoughIngredients(dish, inventory);
            }
        }
        //check if there are enough required ingredients




        //reduce the amount of ingredients
    }


    private void enoughIngredients(Dish dish, Inventory inventory){
        boolean enough = false;
        Map<Integer,Integer> ingredientsID ;
        dish.setIngredientID();
        //inventory.setInitialAmounts();
        ingredientsID = dish.getIngredientsID();
        Map<Integer,Integer> recipeIngredientAmounts = dish.getRecipesAmounts();
        for(int i = 0; i< dish.getNumberOfIngredients(); i++){
            int ingredientAmountRequired = dish.getRecipesAmounts().get(i);
            int IDs = ingredientsID.get(i);
            int inventoryIngredientAmount = inventory.getIngredientsAmounts().get(IDs);
            if( ingredientAmountRequired > inventoryIngredientAmount){
                System.out.println("you do not have enough ingredients for this recipe");
                enough = false;
            } else {
                enough =true;
                takeIngredientFromInventory(ingredientsID.get(i),(inventoryIngredientAmount - recipeIngredientAmounts.get(i)),inventory);
            }
        }
    }

    private void takeIngredientFromInventory(int ingredientID, int amount, Inventory inventory){
        inventory.updatedAmounts(ingredientID,amount);
    }



    public int sellADish(int recipeID, Inventory inventory){
        //receive the payment and add it to the
        Dish dish;
        int totalPrice=0;
        switch (recipeID){
            case 0,1,2:
                typeOfDish = "FOOD";
                dish = new Food(recipeID);
                dish.getProductionCost();
                //check if there are enough required ingredients
                enoughIngredients(dish,inventory);
                totalPrice = dish.getProductionCost() + dish.getIngredientsTotalPrice() + 1000;
                break;
            case 3,4,5,6,7:
                typeOfDish = "DRINK";
                dish = new Drinks(recipeID);
                dish.getProductionCost();
                enoughIngredients(dish,inventory);
                totalPrice = dish.getProductionCost() + dish.getIngredientsTotalPrice() + 1000;
                break;
            case 8,9,10:
                typeOfDish = "DESSERT";
                dish = new Desserts(recipeID);
                dish.getProductionCost();
                enoughIngredients(dish,inventory);
                totalPrice = dish.getProductionCost() + dish.getIngredientsTotalPrice() + 1000;
                break;
        }

     return totalPrice;

    }


    public static void main(String[] args) {
        Order order = new Order();
        Dish dish = new Food(2);
        Inventory inventory = new Inventory();
        order.enoughIngredients(dish,inventory);

    }

}

