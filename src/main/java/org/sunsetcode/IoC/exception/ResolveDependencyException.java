package org.sunsetcode.IoC.exception;

public class ResolveDependencyException extends RuntimeException
{
    public ResolveDependencyException() {
        super();
    }

    public ResolveDependencyException(String message) {
        super(message);
    }

    public ResolveDependencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
