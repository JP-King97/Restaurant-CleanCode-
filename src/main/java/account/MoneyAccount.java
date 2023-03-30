package account;

public class MoneyAccount {

    public MoneyAccount (){}

    //Due to owner request the initial amount in the account is $100.000
    int currentBalance = 100000;

    //withdrawal = use to make the payments for the ingredients
    public int Withdrawal (int withdrawal){
        currentBalance = currentBalance - withdrawal;
        return currentBalance;
    }

    public int Deposit (int payment){
        currentBalance = currentBalance + payment;
        return currentBalance;
    }

    public void PrintCurrentBalance (){
        System.out.println("Your current balance is: $"+currentBalance);
    }

}
