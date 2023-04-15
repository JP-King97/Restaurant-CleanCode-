package account;

public class MoneyAccount {

    private int currentBalance = 100000;

    public MoneyAccount (){}

    public int getCurrentBalance() {
        return currentBalance;
    }

    public int Withdrawal (int withdrawal){
        currentBalance = currentBalance - withdrawal;
        return currentBalance;
    }

    public int Deposit (int payment){
        currentBalance = currentBalance + payment;
        return currentBalance;
    }

}
