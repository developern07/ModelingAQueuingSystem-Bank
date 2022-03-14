package entity;

import exception.NotEnoughMoneyException;

/**
 * A class that describes the cash register.
 */
public class CashRegister {

    /**
     * Amount of money.
     */
    private int amountOfMoney;

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    /**
     * Constructor with parameters.
     * @param amountOfMoney Amount of money.
     */
    public CashRegister(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    /**
     * Cash replenishment.
     * @param transactionAmount Transaction amount
     */
    public synchronized void depositMoney(int transactionAmount) {
        this.amountOfMoney += transactionAmount;
    }

    /**
     * Getting money from the bank.
     * @param transactionAmount Transaction amount
     * @throws NotEnoughMoneyException there is not enough money in the bank
     */
    public synchronized void withdrawMoney(int transactionAmount) throws NotEnoughMoneyException {
        if (transactionAmount > this.amountOfMoney) {
            throw new NotEnoughMoneyException();
        } else {
            this.amountOfMoney -= transactionAmount;
        }
    }

}
