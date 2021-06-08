// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.aggregate;

import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Method;
import java.lang.reflect.Array;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Map;

final class MutatorUtil
{
    private static Map<String, MethodMutatorFactory> factories;
    
    static MethodMutatorFactory loadMutator(final String className) {
        if (!MutatorUtil.factories.containsKey(className)) {
            try {
                final Class<MethodMutatorFactory> clazz = (Class<MethodMutatorFactory>)Class.forName(className);
                final Method values = clazz.getMethod("values", (Class<?>[])new Class[0]);
                final Object valuesArray = values.invoke(null, new Object[0]);
                final MethodMutatorFactory mutator = (MethodMutatorFactory)Array.get(valuesArray, 0);
                MutatorUtil.factories.put(className, mutator);
            }
            catch (Exception e) {
                throw new RuntimeException("Unable to load Mutator for class: " + className, e);
            }
        }
        return MutatorUtil.factories.get(className);
    }
    
    static {
        MutatorUtil.factories = new ConcurrentHashMap<String, MethodMutatorFactory>();
    }
}
