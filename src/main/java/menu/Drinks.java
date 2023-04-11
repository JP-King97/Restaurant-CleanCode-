package menu;

public class Drinks extends Dish {

    private final int recipeID;
    public Drinks (int recipeID){
        super(recipeID);
        this.recipeID = recipeID;
    }

    /**
     * Calculate the production cost for a drink depending on if is a juice, milkshake or as alcoholic beverage
     *
     * @return the production cost
     */
    @Override
    public int getProductionCost() {

        switch (recipeID) {
            case 3 -> productionCost = (int) (getIngredientsTotalPrice() * 0.15);
            case 4, 5 -> productionCost = (int) (getIngredientsTotalPrice() * 0.10);
            case 6, 7 -> productionCost = 400;
            default -> System.out.println("Drink not founded");
        }
        return productionCost;
    }


}
