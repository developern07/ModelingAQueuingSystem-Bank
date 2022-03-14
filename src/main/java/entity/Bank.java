package entity;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Class that describes the operation of the bank.
 */
public class Bank {

    /**
     * Bank employees.
     */
    private final Employee employee1;
    private final Employee employee2;
    private final Employee employee3;

    /**
     * Constructor with parameters.
     * @param cashRegister Cash register
     */
    public Bank(CashRegister cashRegister){
        this.employee1 = new Employee(cashRegister);
        this.employee2 = new Employee(cashRegister);
        this.employee3 = new Employee(cashRegister);
        this.employee1.start();
        this.employee2.start();
        this.employee3.start();
    }

    /**
     * Selection of the queue with the smallest number of clients.
     * @param customer Customer
     */
    public void selectQueue (Customer customer){
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(employee1.getQueue().size());
        queue.addLast(employee2.getQueue().size());
        queue.addLast(employee3.getQueue().size());
        Collections.sort(queue);
        if (employee1.getQueue().size() == Integer.parseInt(queue.get(0).toString())){
            employee1.addToQueue(customer);
        } else if (employee2.getQueue().size() == Integer.parseInt(queue.get(0).toString())){
            employee2.addToQueue(customer);
        } else if (employee3.getQueue().size() == Integer.parseInt(queue.get(0).toString())){
            employee3.addToQueue(customer);
        }
    }

}
