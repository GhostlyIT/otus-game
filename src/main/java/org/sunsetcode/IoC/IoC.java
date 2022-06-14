package org.sunsetcode.IoC;

import org.sunsetcode.command.Command;

import java.util.*;
import java.util.function.Function;

public class IoC
{
    private static Scope currentScope = new Scope();

    private final static Map<String, Scope> scopes = new HashMap<>(){{
        put("default", currentScope);
    }};

    @SuppressWarnings("unchecked")
    public static <T> T resolve(String key, Object... args)
    {
        if (key.equals("IoC.Registry")) {
            return (T) (new RegDependencyCommand((String) args[0], (Function<Object[], Object>) args[1]));
        }

        if (key.equals("Scopes.New")) {
            return (T) (new RegNewScope((String) args[0]));
        }

        if (key.equals("Scopes.Current")) {
            return (T) (new ChangeCurrentScope((String) args[0]));
        }

        if (currentScope == null) {
            throw new ResolveDependencyException("Scope is not set");
        }

        var strategy = currentScope.getStrategy(key);

        if (strategy != null) {
            return (T) strategy.apply(args);
        } else {
            throw new ResolveDependencyException("Can't resolve dependency: " + key);
        }
    }

    private static class RegDependencyCommand implements Command
    {
        private final String key;
        private final Function<Object[], Object> strategy;

        RegDependencyCommand(String key, Function<Object[], Object> strategy)
        {
            this.key = key;
            this.strategy = strategy;
        }

        @Override
        public void execute()
        {
            currentScope.addStrategy(key, strategy);
        }
    }

    private static class RegNewScope implements Command
    {
        private final String name;

        public RegNewScope(String name)
        {
            this.name = name;
        }

        @Override
        public void execute()
        {
            Scope newScope = new Scope(currentScope);
            currentScope = newScope;
            scopes.put(name, newScope);
        }
    }

    private static class ChangeCurrentScope implements Command
    {
        private final String name;

        public ChangeCurrentScope(String name)
        {
            this.name = name;
        }

        @Override
        public void execute()
        {
            currentScope = scopes.get(name);
        }
    }
}
