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
        Dish dish = selectTypeOfDish(recipeID);
        takeIngredientsForRecipe(dish, inventory);
    }

    public int sellADish(int recipeID){
        Dish dish = selectTypeOfDish(recipeID);
        return dish.getSellingPrice();
    }

    private Dish selectTypeOfDish(int recipeID){
        Dish dish;
        switch (recipeID) {
            case 0, 1, 2 -> {
                typeOfDish = "FOOD";
                dish = new Food(recipeID);
                return dish;
            }
            case 3, 4, 5, 6, 7 -> {
                typeOfDish = "DRINK";
                dish = new Drinks(recipeID);
                return dish;
            }
            case 8, 9, 10 -> {
                typeOfDish = "DESSERT";
                dish = new Desserts(recipeID);
                return dish;
            }
            default -> {
                System.out.println("Recipe not found");
                return dish = null;
            }
        }
    }


    private void takeIngredientsForRecipe(Dish dish, Inventory inventory){
        dish.setIngredientID();
        Map<Integer,Integer> ingredientsID = dish.getIngredientsID();
        Map<Integer,Integer> recipeIngredientAmounts = dish.getRecipesAmounts();
        Map<Integer,Integer> ingredientsAmounts = inventory.getIngredientsAmounts();
        int numberOfIngredients = dish.getNumberOfIngredients();
        for(int i = 0; i< numberOfIngredients; i++){
            int ingredientAmountRequired = recipeIngredientAmounts.get(i);
            int IDs = ingredientsID.get(i);
            int inventoryIngredientAmount = ingredientsAmounts.get(IDs);
            enoughIngredientsCheck(IDs,ingredientAmountRequired,inventoryIngredientAmount,inventory);
        }
    }

    private void enoughIngredientsCheck(int ingredientIDs, int ingredientAmountRequired, int inventoryIngredientAmount, Inventory inventory){
        if( ingredientAmountRequired > inventoryIngredientAmount){
            System.out.println("you do not have enough ingredients for this recipe");
        } else {
            takeIngredientFromInventory(ingredientIDs,(inventoryIngredientAmount - ingredientAmountRequired),inventory);
        }
    }

    private void takeIngredientFromInventory(int ingredientID, int amount, Inventory inventory){
        inventory.updatedAmounts(ingredientID,amount);
    }




}

