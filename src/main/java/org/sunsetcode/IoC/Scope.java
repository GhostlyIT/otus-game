package org.sunsetcode.IoC;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Scope
{
    private final Map<String, Function<Object[], Object>> strategies = new HashMap<>();

    private Scope parentScope = null;

    public Scope()
    {
    }

    public Scope(Scope parentScope)
    {
        this.parentScope = parentScope;
    }

    public void addStrategy(String strategyName, Function<Object[], Object> strategy)
    {
        strategies.put(strategyName, strategy);
    }

    public Function<Object[], Object> getStrategy(String strategyName)
    {
         var strategy = strategies.get(strategyName);

         if (strategy == null && parentScope != null) {
             return parentScope.getStrategy(strategyName);
         }

         return strategy;
    }
}
