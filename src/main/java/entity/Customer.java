package entity;

import enumeration.TypeOfOperation;

import java.util.Random;

/**
 * A class that describes the work of a bank customer.
 */
public class Customer {

    /**
     * Type of operation (withdraw money / deposit money)
     */
    private final TypeOfOperation typeOfOperation;

    /**
     * Transaction amount
     */
    private final int transactionAmount;

    /**
     * Service time
     */
    private final long serviceTime;

    public TypeOfOperation isTypeOfOperation() {
        return typeOfOperation;
    }

    public int getTransactionAmount() {
        return transactionAmount;
    }

    public long getServiceTime() {
        return serviceTime;
    }

    /**
     * Constructor without parameters.
     */
    public Customer() {
        Random random = new Random();
        this.serviceTime = random.nextInt(30000);
        this.transactionAmount = random.nextInt(1000000);
        this.typeOfOperation = TypeOfOperation.values()[random.nextInt(TypeOfOperation.values().length)];
    }
}
