// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.RuntimeServicesAware;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.context.InternalEventContext;
import org.apache.velocity.context.Context;
import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class EventCartridge
{
    private List referenceHandlers;
    private List nullSetHandlers;
    private List methodExceptionHandlers;
    private List includeHandlers;
    private List invalidReferenceHandlers;
    Set initializedHandlers;
    
    public EventCartridge() {
        this.referenceHandlers = new ArrayList();
        this.nullSetHandlers = new ArrayList();
        this.methodExceptionHandlers = new ArrayList();
        this.includeHandlers = new ArrayList();
        this.invalidReferenceHandlers = new ArrayList();
        this.initializedHandlers = new HashSet();
    }
    
    public boolean addEventHandler(final EventHandler ev) {
        if (ev == null) {
            return false;
        }
        boolean found = false;
        if (ev instanceof ReferenceInsertionEventHandler) {
            this.addReferenceInsertionEventHandler((ReferenceInsertionEventHandler)ev);
            found = true;
        }
        if (ev instanceof NullSetEventHandler) {
            this.addNullSetEventHandler((NullSetEventHandler)ev);
            found = true;
        }
        if (ev instanceof MethodExceptionEventHandler) {
            this.addMethodExceptionHandler((MethodExceptionEventHandler)ev);
            found = true;
        }
        if (ev instanceof IncludeEventHandler) {
            this.addIncludeEventHandler((IncludeEventHandler)ev);
            found = true;
        }
        if (ev instanceof InvalidReferenceEventHandler) {
            this.addInvalidReferenceEventHandler((InvalidReferenceEventHandler)ev);
            found = true;
        }
        return found;
    }
    
    public void addReferenceInsertionEventHandler(final ReferenceInsertionEventHandler ev) {
        this.referenceHandlers.add(ev);
    }
    
    public void addNullSetEventHandler(final NullSetEventHandler ev) {
        this.nullSetHandlers.add(ev);
    }
    
    public void addMethodExceptionHandler(final MethodExceptionEventHandler ev) {
        this.methodExceptionHandlers.add(ev);
    }
    
    public void addIncludeEventHandler(final IncludeEventHandler ev) {
        this.includeHandlers.add(ev);
    }
    
    public void addInvalidReferenceEventHandler(final InvalidReferenceEventHandler ev) {
        this.invalidReferenceHandlers.add(ev);
    }
    
    public boolean removeEventHandler(final EventHandler ev) {
        if (ev == null) {
            return false;
        }
        final boolean found = false;
        if (ev instanceof ReferenceInsertionEventHandler) {
            return this.referenceHandlers.remove(ev);
        }
        if (ev instanceof NullSetEventHandler) {
            return this.nullSetHandlers.remove(ev);
        }
        if (ev instanceof MethodExceptionEventHandler) {
            return this.methodExceptionHandlers.remove(ev);
        }
        if (ev instanceof IncludeEventHandler) {
            return this.includeHandlers.remove(ev);
        }
        if (ev instanceof InvalidReferenceEventHandler) {
            return this.invalidReferenceHandlers.remove(ev);
        }
        return found;
    }
    
    public Iterator getReferenceInsertionEventHandlers() {
        return this.referenceHandlers.iterator();
    }
    
    public Iterator getNullSetEventHandlers() {
        return this.nullSetHandlers.iterator();
    }
    
    public Iterator getMethodExceptionEventHandlers() {
        return this.methodExceptionHandlers.iterator();
    }
    
    public Iterator getIncludeEventHandlers() {
        return this.includeHandlers.iterator();
    }
    
    public Iterator getInvalidReferenceEventHandlers() {
        return this.invalidReferenceHandlers.iterator();
    }
    
    public final boolean attachToContext(final Context context) {
        if (context instanceof InternalEventContext) {
            final InternalEventContext iec = (InternalEventContext)context;
            iec.attachEventCartridge(this);
            return true;
        }
        return false;
    }
    
    public void initialize(final RuntimeServices rs) throws Exception {
        for (final EventHandler eh : this.referenceHandlers) {
            if (eh instanceof RuntimeServicesAware && !this.initializedHandlers.contains(eh)) {
                ((RuntimeServicesAware)eh).setRuntimeServices(rs);
                this.initializedHandlers.add(eh);
            }
        }
        for (final EventHandler eh : this.nullSetHandlers) {
            if (eh instanceof RuntimeServicesAware && !this.initializedHandlers.contains(eh)) {
                ((RuntimeServicesAware)eh).setRuntimeServices(rs);
                this.initializedHandlers.add(eh);
            }
        }
        for (final EventHandler eh : this.methodExceptionHandlers) {
            if (eh instanceof RuntimeServicesAware && !this.initializedHandlers.contains(eh)) {
                ((RuntimeServicesAware)eh).setRuntimeServices(rs);
                this.initializedHandlers.add(eh);
            }
        }
        for (final EventHandler eh : this.includeHandlers) {
            if (eh instanceof RuntimeServicesAware && !this.initializedHandlers.contains(eh)) {
                ((RuntimeServicesAware)eh).setRuntimeServices(rs);
                this.initializedHandlers.add(eh);
            }
        }
        for (final EventHandler eh : this.invalidReferenceHandlers) {
            if (eh instanceof RuntimeServicesAware && !this.initializedHandlers.contains(eh)) {
                ((RuntimeServicesAware)eh).setRuntimeServices(rs);
                this.initializedHandlers.add(eh);
            }
        }
    }
}
