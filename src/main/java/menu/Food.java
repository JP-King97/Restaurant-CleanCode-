package menu;

import connection.DatabaseConnection;

public class Food extends Dish {


    public Food(DatabaseConnection dbConnection, int recipeID) {
        super(dbConnection, recipeID);
    }

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
