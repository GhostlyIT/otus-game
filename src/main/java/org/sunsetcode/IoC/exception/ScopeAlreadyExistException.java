package org.sunsetcode.IoC.exception;

public class ScopeAlreadyExistException extends Exception
{
    @Override
    public String getMessage() {
        return "Scope with that name already exists";
    }
}
