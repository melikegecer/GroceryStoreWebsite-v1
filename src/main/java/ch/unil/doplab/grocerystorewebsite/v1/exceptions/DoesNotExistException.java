package ch.unil.doplab.grocerystorewebsite.v1.exceptions;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class DoesNotExistException extends Exception {

    private String message;

    public DoesNotExistException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
