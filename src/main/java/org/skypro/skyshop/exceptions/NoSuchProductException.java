package org.skypro.skyshop.exceptions;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() { super(); }
    public NoSuchProductException(String message) { super(message); }
    public NoSuchProductException(Throwable t) { super(t); }
}
