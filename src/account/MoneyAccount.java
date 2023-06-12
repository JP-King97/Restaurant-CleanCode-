package account;

import java.util.HashMap;
import java.util.Map;

public class MoneyAccount {
    private Map<Integer, Double> currentBalance = new HashMap<>();// = {100000.0};

    public MoneyAccount (){
        currentBalance.put(0,100000.0);
    }

    private int getMoneyAccountLength(){
        return currentBalance.size();
    }
    public Double getCurrentBalance() {
        return currentBalance.get(getMoneyAccountLength()-1);
    }

    public Double Withdrawal (Double withdrawal){
        currentBalance.put(getMoneyAccountLength(),currentBalance.get(getMoneyAccountLength()-1) - withdrawal);
        return currentBalance.get(getMoneyAccountLength());
    }

    public Double Deposit (Double payment){
        currentBalance.put(getMoneyAccountLength(), currentBalance.get(getMoneyAccountLength()-1) + payment);
        return currentBalance.get(getMoneyAccountLength());
    }
}
