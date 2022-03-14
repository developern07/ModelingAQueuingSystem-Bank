import entity.Bank;
import entity.CashRegister;
import entity.Customer;

import java.util.Random;

public class Main {
    public static void main(String[] args){
        Random random = new Random();
        CashRegister cashRegister = new CashRegister(random.nextInt(1000000));
        Bank bank = new Bank(cashRegister);
        while (true) {
            if (random.nextInt(200000000) == 0) {
                Customer customer = new Customer();
                bank.selectQueue(customer);
            }
        }
    }
}
