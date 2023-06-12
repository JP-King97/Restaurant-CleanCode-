package menu;

public class Drink extends Dish{
    private final int recipeID;
    public Drink (int recipeID){
        super(recipeID);
        this.recipeID = recipeID;
    }

    @Override
    public double getProductionCost() {
        switch (recipeID) {
            case 4 -> productionCost = (int) (getIngredientsTotalPrice() * 0.15);
            case 5, 6 -> productionCost = (int) (getIngredientsTotalPrice() * 0.10);
            case 7, 8 -> productionCost = 400;
            default -> System.out.println("Drink not founded");
        }
        return productionCost;
    }
}
