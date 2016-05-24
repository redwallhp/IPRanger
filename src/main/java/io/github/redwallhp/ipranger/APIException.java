package io.github.redwallhp.ipranger;


public class APIException extends Exception {

    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }

    public APIException(Throwable t) {
        super(t);
    }

    public APIException(String message, Throwable t) {
        super(message, t);
    }
}
