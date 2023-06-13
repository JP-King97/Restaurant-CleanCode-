package orders;

import inventory.Inventory;
import menu.*;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Integer,Integer> requestedDishes = new HashMap<>();
    private Map<Integer,Integer> deliveredDishes = new HashMap<>();
   // private Map<Integer,Integer> ordersIDs = new HashMap<>();
    public Order(){
    }

    public Map<Integer, Integer> getRequestedDishes() {
        return requestedDishes;
    }

    public Map<Integer, Integer> getDeliveredDishes() {
        return deliveredDishes;
    }

    /**
     * Make a dish reducing int the inventory the amount of ingredients needed for the recipe
     *
     * @param recipeID
     * @param inventory
     */
    public void makeADish(int recipeID, Inventory inventory) {
        Dish dish = selectTypeOfDish(recipeID);
        takeAllIngredientsAmountsForRecipe(dish, inventory);
        int amountRequested = requestedDishes.size();
        requestedDishes.put(amountRequested, recipeID);
    }

    /**
     *
     * @param orderID
     * @return
     */
    public double sellADish(int orderID){
        int recipeID = requestedDishes.get(orderID);
        requestedDishes.remove(orderID);
        int amountDelivered = deliveredDishes.size();
        deliveredDishes.put(amountDelivered,recipeID);
        Dish dish = selectTypeOfDish(recipeID);
        return dish.getSellingPrice();
    }

    /**
     * Initiate the dish object with the specific child class related with the
     * recipe and the recipe's ID
     *
     * @param recipeID
     * @return a Dish object instantiated with a specific child class
     */
    private Dish selectTypeOfDish(int recipeID){
        Recipe recipe = new Recipe(recipeID);
        Dish dish;
        String type = recipe.getRecipeType();
        switch (type) {
            case "FOOD" -> {
                //typeOfDish = "FOOD";
                dish = new Food(recipeID);
                return dish;
            }
            case "DRINK" -> {
                //typeOfDish = "DRINK";
                dish = new Drink(recipeID);
                return dish;
            }
            case "DESSERT" -> {
                //typeOfDish = "DESSERT";
                dish = new Dessert(recipeID);
                return dish;
            }
            default -> {
                System.out.println("Recipe not found");
                return null;
            }
        }
    }

    /**
     * Reduce the amount in the inventory for each ingredient required in the recipe
     *
     * @param dish
     * @param inventory
     */
    private void takeAllIngredientsAmountsForRecipe(Dish dish, Inventory inventory){
        dish.setRecipeIngredientsIDs();
        Map<Integer,Integer> ingredientsID = dish.getRecipesIngredientsIDs();
        Map<Integer,Double> recipeIngredientAmounts = dish.getRecipeIngredientsAmounts();
        Map<Integer,Double> ingredientsAmounts = inventory.getIngredientsAmounts();
        for(int i = 0; i< recipeIngredientAmounts.size(); i++){
            int ingredientAmountRequired = recipeIngredientAmounts.get(i).intValue();
            int IDs = ingredientsID.get(i).intValue();
            int inventoryIngredientAmount = ingredientsAmounts.get(IDs-1).intValue();
            enoughIngredientAmountCheck(IDs,ingredientAmountRequired,inventoryIngredientAmount,inventory);
        }
    }

    /**
     * Check if the amount of a specific ingredient in the inventory is enough
     * to supply the amount required for the recipe
     *
     * @param ingredientID ID of the required ingredient for the recipe
     * @param ingredientAmountRequired ingredient's amount required in the recipe
     * @param inventoryIngredientAmount ingredient's amount in the inventory
     * @param inventory
     */
    private void enoughIngredientAmountCheck(int ingredientID, int ingredientAmountRequired, int inventoryIngredientAmount, Inventory inventory){
        if( ingredientAmountRequired > inventoryIngredientAmount){
            System.out.println("you do not have enough ingredients for this recipe");
        } else {
            takeIngredientAmountFromInventory(ingredientID,(inventoryIngredientAmount - ingredientAmountRequired),inventory);
        }
    }

    /**
     * Reduce the amount of a specific ingredient from the inventory
     *
     * @param ingredientID
     * @param amount
     * @param inventory
     */
    private void takeIngredientAmountFromInventory(int ingredientID, int amount, Inventory inventory){
        inventory.updatedAmounts(ingredientID, (double) amount);
    }

    public static void main(String[] args) {
        Order order = new Order();
        Inventory inventory = new Inventory();
        double sellingPrice= order.sellADish(1);
    }
}
