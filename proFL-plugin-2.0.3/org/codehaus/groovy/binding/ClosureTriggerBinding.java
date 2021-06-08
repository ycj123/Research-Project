// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.security.AccessController;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import groovy.lang.Reference;
import java.security.PrivilegedAction;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import groovy.lang.Closure;
import java.util.Map;

public class ClosureTriggerBinding implements TriggerBinding, SourceBinding
{
    Map<String, TriggerBinding> syntheticBindings;
    Closure closure;
    
    public ClosureTriggerBinding(final Map<String, TriggerBinding> syntheticBindings) {
        this.syntheticBindings = syntheticBindings;
    }
    
    public Closure getClosure() {
        return this.closure;
    }
    
    public void setClosure(final Closure closure) {
        this.closure = closure;
    }
    
    private BindPath createBindPath(final String propertyName, final BindPathSnooper snooper) {
        final BindPath bp = new BindPath();
        bp.propertyName = propertyName;
        bp.updateLocalSyntheticProperties(this.syntheticBindings);
        final List<BindPath> childPaths = new ArrayList<BindPath>();
        for (final Map.Entry<String, BindPathSnooper> entry : snooper.fields.entrySet()) {
            childPaths.add(this.createBindPath(entry.getKey(), entry.getValue()));
        }
        bp.children = childPaths.toArray(new BindPath[childPaths.size()]);
        return bp;
    }
    
    public FullBinding createBinding(final SourceBinding source, final TargetBinding target) {
        if (source != this) {
            throw new RuntimeException("Source binding must the Trigger Binding as well");
        }
        final BindPathSnooper delegate = new BindPathSnooper();
        try {
            final Class closureClass = this.closure.getClass();
            final Closure closureLocalCopy = AccessController.doPrivileged((PrivilegedAction<Closure>)new PrivilegedAction<Closure>() {
                public Closure run() {
                    final Constructor constructor = closureClass.getConstructors()[0];
                    final int paramCount = constructor.getParameterTypes().length;
                    final Object[] args = new Object[paramCount];
                    args[0] = delegate;
                    for (int i = 1; i < paramCount; ++i) {
                        args[i] = new Reference(new BindPathSnooper());
                    }
                    try {
                        boolean acc = constructor.isAccessible();
                        constructor.setAccessible(true);
                        final Closure localCopy = constructor.newInstance(args);
                        if (!acc) {
                            constructor.setAccessible(false);
                        }
                        localCopy.setResolveStrategy(3);
                        for (final Field f : closureClass.getDeclaredFields()) {
                            acc = f.isAccessible();
                            f.setAccessible(true);
                            if (f.getType() == Reference.class) {
                                delegate.fields.put(f.getName(), ((Reference)f.get(localCopy)).get());
                            }
                            if (!acc) {
                                f.setAccessible(false);
                            }
                        }
                        return localCopy;
                    }
                    catch (Exception e) {
                        throw new RuntimeException("Error snooping closure", e);
                    }
                }
            });
            try {
                closureLocalCopy.call();
            }
            catch (DeadEndException e) {
                throw e;
            }
            catch (Exception ex) {}
        }
        catch (Exception e2) {
            e2.printStackTrace(System.out);
            throw new RuntimeException("A closure expression binding could not be created because of " + e2.getClass().getName() + ":\n\t" + e2.getMessage());
        }
        final List<BindPath> rootPaths = new ArrayList<BindPath>();
        for (final Map.Entry<String, BindPathSnooper> entry : delegate.fields.entrySet()) {
            final BindPath bp = this.createBindPath(entry.getKey(), entry.getValue());
            bp.currentObject = this.closure;
            rootPaths.add(bp);
        }
        final PropertyPathFullBinding fb = new PropertyPathFullBinding();
        fb.setSourceBinding(new ClosureSourceBinding(this.closure));
        fb.setTargetBinding(target);
        fb.bindPaths = rootPaths.toArray(new BindPath[rootPaths.size()]);
        return fb;
    }
    
    public Object getSourceValue() {
        return this.closure.call();
    }
}
