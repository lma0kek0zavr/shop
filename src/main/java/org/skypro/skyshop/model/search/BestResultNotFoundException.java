package org.skypro.skyshop.model.search;

public class BestResultNotFoundException extends Exception {
    public BestResultNotFoundException() { super(); }
    public BestResultNotFoundException(String message) { super(message); }
    public BestResultNotFoundException(Throwable cause) { super(cause); }
}
