package menu;

public class Dish {

    private String receiptName;
    private int receiptID;
    public Dish(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public void amountOfIngredients(){//Request from Receipts class
        Receipt receipt = new Receipt(receiptName, receiptID);
        receipt.getReceiptsAmounts();
        receipt.getReceiptsIngredients();
    }


}
