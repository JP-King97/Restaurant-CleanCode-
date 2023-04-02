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

}
