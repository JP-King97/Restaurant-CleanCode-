package menu;

import connection.DatabaseConnection;

public class Drink extends Dish{

    public Drink(DatabaseConnection dbConnection, int recipeID) {
        super(dbConnection, recipeID);
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
