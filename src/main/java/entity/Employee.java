package entity;

import exception.NotEnoughMoneyException;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static enumeration.TypeOfOperation.deposit;
import static enumeration.TypeOfOperation.withdraw;

/**
 * A class that describes the work of a bank employee.
 */
public class Employee extends Thread {

    Logger log = Logger.getLogger(String.valueOf(Employee.class));

    /**
     * Cash register.
     */
    private final CashRegister cashRegister;

    /**
     * Queue of clients.
     */
    private final LinkedList<Customer> queue;

    /**
     * Constructor with a parameter.
     * @param cashRegister Cash register.
     */
    public Employee(CashRegister cashRegister) {
        this.cashRegister = cashRegister;
        this.queue = new LinkedList<>();
    }

    /**
     * Adding a client to the queue.
     * @param customer Customer
     */
    public void addToQueue(Customer customer) {
        synchronized(queue) {
            queue.addLast(customer);
            queue.notify();
        }
    }

    public LinkedList<Customer> getQueue() {
        return queue;
    }

    public CashRegister getCashRegister() {
        return cashRegister;
    }

    /**
     * Flow function.
     * If there is no one in the queue, then the bank employee waits until someone appears there.
     * If a client has appeared, then the bank employee serves him for as long as the client has in the "service time" property.
     * Operations: withdraw money / deposit money.
     */
    @Override
    public void run() {
        Customer customer;

        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    }
                    catch (InterruptedException ignored){}
                }
                customer = queue.removeFirst();
            }

            try {
                if (customer.isTypeOfOperation() == withdraw) {
                    this.getCashRegister().depositMoney(customer.getTransactionAmount());
                    log.log(Level.INFO,"The customer deposited " + customer.getTransactionAmount() + " money.");
                    log.log(Level.INFO,"Service time " + customer.getServiceTime());
                } else if (customer.isTypeOfOperation() == deposit) {
                    this.getCashRegister().withdrawMoney(customer.getTransactionAmount());
                    log.log(Level.INFO,"The client took " + customer.getTransactionAmount() + " money.");
                    log.log(Level.INFO,"Service time " + customer.getServiceTime());
                }
                log.log(Level.INFO,"The total amount of money in the bank: " + this.getCashRegister().getAmountOfMoney());
                Thread.sleep(customer.getServiceTime());
            }
            catch (RuntimeException | InterruptedException | NotEnoughMoneyException e) {
                e.printStackTrace();
            }
        }
    }
}
