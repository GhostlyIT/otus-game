package org.sunsetcode.IoC.exception;

public class ScopeDoesntExistsException extends Exception
{
    @Override
    public String getMessage() {
        return "Scope with that name doesn't exists";
    }
}
