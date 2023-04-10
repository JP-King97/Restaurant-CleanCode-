package menu;

public class Food extends Dish {
    private int recipeID;
    public Food(int recipeID){
        super(recipeID);
        this.recipeID = recipeID;
    }

    /**
     * Calculate the production cost for a food dish
     *
     * @return the production cost
     */
    @Override
    public int getProductionCost() {

        switch (recipeID){
            case 0:
                productionCost = 400;
                break;
            case 1:
                productionCost = (int) (getIngredientsTotalPrice()*0.1);
                break;
            case 2:
                productionCost = (int) (getIngredientsTotalPrice()*0.2);
                break;
            default:
                System.out.println("Food dish not founded");
        }
        return productionCost;
    }



}
