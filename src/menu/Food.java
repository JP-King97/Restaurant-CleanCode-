package menu;

public class Food extends Dish{

    private final int recipeID;
    public Food(int recipeID) {
        super(recipeID);
        this.recipeID=recipeID;
    }

    /**
     * Calculate the production cost for a food dish depending on if is vegan, hot or a normal dish
     *
     * @return the production cost
     */
    @Override
    public double getProductionCost() {
        switch (recipeID) {
            case 1 -> productionCost = 400;
            case 2 -> productionCost = (getIngredientsTotalPrice() * 0.1);
            case 3 -> productionCost = (getIngredientsTotalPrice() * 0.2);
            default -> System.out.println("Food dish not founded");
        }
        return productionCost;
    }
}
