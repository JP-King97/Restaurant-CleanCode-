package menu;

public class Food extends Dishes{
    private boolean vegan = false;
    private boolean hot = false;
    //Constructor
    public Food(String Receipt, boolean vegan, boolean hot){
        super(Receipt);
        this.vegan = vegan;
        this.hot = hot;
    }

}
