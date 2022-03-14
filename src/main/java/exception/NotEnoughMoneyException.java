package exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
* Class for handling the exception when there is not enough money in the bank
*/
public class NotEnoughMoneyException extends Exception {

    Logger log = Logger.getLogger(String.valueOf(NotEnoughMoneyException.class));

    public NotEnoughMoneyException() {
        log.log(Level.WARNING,"Not enough money in the cash register!");
    }

}
