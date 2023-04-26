package orders;

import inventory.Inventory;
import menu.Dessert;
import menu.Dish;
import menu.Drink;
import menu.Food;

import java.util.Map;

public class Order {
    public Order(){
    }

    /**
     * Make a dish reducing int the inventory the amount of ingredients needed for the recipe
     *
     * @param recipeID
     * @param inventory
     */
    public void makeADish(int recipeID, Inventory inventory){
        Dish dish = selectTypeOfDish(recipeID);
        takeAllIngredientsAmountsForRecipe(dish, inventory);
    }

    /**
     * Calculate the payment for the dish (selling price)
     *
     * @param recipeID
     *
     * @return the selling price
     */
    public double sellADish(int recipeID){
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
        Dish dish;
        switch (recipeID) {
            case 0, 1, 2 -> {
                //typeOfDish = "FOOD";
                dish = new Food(recipeID);
                return dish;
            }
            case 3, 4, 5, 6, 7 -> {
                //typeOfDish = "DRINK";
                dish = new Drink(recipeID);
                return dish;
            }
            case 8, 9, 10 -> {
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
        Map<Integer,Double> ingredientsID = dish.getRecipesIngredientsIDs();
        Map<Integer,Double> recipeIngredientAmounts = dish.getRecipeIngredientsAmounts();
        Map<Integer,Double> ingredientsAmounts = inventory.getIngredientsAmounts();
        for(int i = 0; i< recipeIngredientAmounts.size(); i++){
            int ingredientAmountRequired = recipeIngredientAmounts.get(i).intValue();
            int IDs = ingredientsID.get(i).intValue();
            int inventoryIngredientAmount = ingredientsAmounts.get(IDs).intValue();
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

}
