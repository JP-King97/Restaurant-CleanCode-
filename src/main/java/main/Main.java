package main;

import account.MoneyAccount;
import inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MoneyAccount account = new MoneyAccount();
        Map<Integer, Integer> ingredientsAmounts=new HashMap<>();
        Map<Integer, String> ingredientsNames=new HashMap<>();
        Inventory inventory = new Inventory(ingredientsAmounts,ingredientsNames);
        inventory.setAmounts();





    }
}
