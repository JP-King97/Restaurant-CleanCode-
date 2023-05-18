package menu;

import connection.DatabaseConnection;

public class Dessert extends Dish{

    public Dessert(DatabaseConnection dbConnection, int recipeID){
        super(dbConnection,recipeID);


    }

    @Override
    public double getProductionCost() {
        switch (recipeID) {
            case 9, 10, 11 -> productionCost = (int) (getIngredientsTotalPrice() * 0.12);
            default -> System.out.println("Dessert not founded");
        }
        return productionCost;
    }
}
