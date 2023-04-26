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
            case 3 -> productionCost = (int) (getIngredientsTotalPrice() * 0.15);
            case 4, 5 -> productionCost = (int) (getIngredientsTotalPrice() * 0.10);
            case 6, 7 -> productionCost = 400;
            default -> System.out.println("Drink not founded");
        }
        return productionCost;
    }
}
