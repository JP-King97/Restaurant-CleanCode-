package menu;

public class Drinks extends Dish {

    private boolean milkshake = false;
    private boolean juice = false;
    private boolean alcohol = false;
    public Drinks (int receiptID){
        super(receiptID);
    }

    @Override
    public int getProductionCost() {
        if(alcohol){
            productionCost= 400;
        }else {
            if (milkshake){
                productionCostPercentage = 15;
            } else{ productionCostPercentage = 10;
            }
            productionCost = getIngredientsTotalPrice()*productionCostPercentage/100;
        }
        return productionCost;
    }


}
