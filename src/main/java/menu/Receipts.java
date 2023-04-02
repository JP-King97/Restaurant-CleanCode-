package menu;

import Data.ExcelFileReader;

import java.util.Map;

public class Receipts {


    private String name;
    private String dishType;
    private int receiptID;
    private int ingredientID;
   // private int numberOfIngredients;
    private int[] firstCell = new int[2]; //firstCell[0]= row; firstCell[1]= column ,
    private Map<Integer,String> receiptIngredients;
    private Map<Integer,Integer> receiptAmounts;
    private String sheetName ="Receipts";
    private ExcelFileReader reader = new ExcelFileReader(receiptAmounts,receiptIngredients,sheetName);
    public Receipts(String name,int receiptID){
        this.name = name;
        this.receiptID = receiptID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

  // public void getIngredients(Map<Integer, Integer> numericalReader,
  //                            Map<Integer, String> StringReader){
  //     ExcelFileReader reader = new ExcelFileReader(numericalReader, StringReader,sheetName);
  //     reader.
  // }
    public void selectReceipt(int receiptID){
        switch (receiptID){
            case  1: {firstCell[0]= 1;firstCell[1]= 3;break;}
            case  2: {firstCell[0]= 1;firstCell[1]= 7;break;}
            case  3: {firstCell[0]= 1;firstCell[1]=11;break;}
            case  4: {firstCell[0]=20;firstCell[1]= 3;break;}
            case  5: {firstCell[0]=20;firstCell[1]= 7;break;}
            case  6: {firstCell[0]=20;firstCell[1]=11;break;}
            case  7: {firstCell[0]=20;firstCell[1]=15;break;}
            case  8: {firstCell[0]=20;firstCell[1]=19;break;}
            case  9: {firstCell[0]=33;firstCell[1]= 3;break;}
            case 10: {firstCell[0]=33;firstCell[1]= 7;break;}
            case 11: {firstCell[0]=33;firstCell[1]=11;break;}
        }
    }

    public int getNumberOfIngredients(){
        selectReceipt(receiptID);
        int numberOfIngredients= reader.getNumericalValue(firstCell[0]+1,firstCell[1]);
        return numberOfIngredients ;
    }

   public void getReceiptsIngredients(){
        for ( int i=0; i < getNumberOfIngredients();i++)
        receiptIngredients.put(i,reader.getStringValue(firstCell[0]+4+i,firstCell[1]-1));
    }
    public void getReceiptsAmounts(){
        for ( int i=0; i < getNumberOfIngredients();i++)
            receiptIngredients.put(i,reader.getStringValue(firstCell[0]+4+i,firstCell[1]));
    }



}
