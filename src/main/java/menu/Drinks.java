package menu;

public class Drinks extends Dish {

    private int recipeID;
    public Drinks (int recipeID){
        super(recipeID);
        this.recipeID = recipeID;
    }

    /**
     * Calculate the production cost for a drink
     *
     * @return the production cost
     */
    @Override
    public int getProductionCost() {

        switch (recipeID){
            case 3:
                productionCost = (int) (getIngredientsTotalPrice()*0.15);
                break;
            case 4,5:
                productionCost = (int) (getIngredientsTotalPrice()*0.10);
                break;
            case 6,7:
                productionCost = 400;
                break;
            default:
                System.out.println("Drink not founded");
        }
        return productionCost;
    }


}
