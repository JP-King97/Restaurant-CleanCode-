package menu;

public class Food extends Dish {
    private boolean vegan = false;
    private boolean hot = false;
    //Constructor
    public Food(int receiptID, boolean vegan, boolean hot){
        super(receiptID);
        this.vegan = vegan;
        this.hot = hot;
    }

    @Override
    public int getProductionCost() {
        productionCost = (int) (getIngredientsTotalPrice()*0.1);
        if (vegan){
            productionCost = 400;
        } else if (hot) {
            productionCost = (int) (getIngredientsTotalPrice()*0.2);
        }
        return productionCost;
    }



}
