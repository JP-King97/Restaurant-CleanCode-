package account;

public class MoneyAccount {
    private Double currentBalance = 100000.0;

    public MoneyAccount (){}

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public Double Withdrawal (Double withdrawal){
        currentBalance = currentBalance - withdrawal;
        return currentBalance;
    }

    public Double Deposit (Double payment){
        currentBalance = currentBalance + payment;
        return currentBalance;
    }
}
