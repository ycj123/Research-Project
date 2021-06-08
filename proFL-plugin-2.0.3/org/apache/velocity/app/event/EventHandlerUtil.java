// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event;

import org.apache.velocity.util.introspection.Info;
import java.util.Iterator;
import org.apache.velocity.util.ExceptionUtils;
import org.apache.velocity.context.Context;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.RuntimeServices;

public class EventHandlerUtil
{
    public static Object referenceInsert(final RuntimeServices rsvc, final InternalContextAdapter context, final String reference, final Object value) {
        final EventCartridge ev1 = rsvc.getApplicationEventCartridge();
        final Iterator applicationEventHandlerIterator = (ev1 == null) ? null : ev1.getReferenceInsertionEventHandlers();
        final EventCartridge ev2 = context.getEventCartridge();
        initializeEventCartridge(rsvc, ev2);
        final Iterator contextEventHandlerIterator = (ev2 == null) ? null : ev2.getReferenceInsertionEventHandlers();
        try {
            final EventHandlerMethodExecutor methodExecutor = new ReferenceInsertionEventHandler.referenceInsertExecutor(context, reference, value);
            callEventHandlers(applicationEventHandlerIterator, contextEventHandlerIterator, methodExecutor);
            return methodExecutor.getReturnValue();
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            throw ExceptionUtils.createRuntimeException("Exception in event handler.", e2);
        }
    }
    
    public static boolean shouldLogOnNullSet(final RuntimeServices rsvc, final InternalContextAdapter context, final String lhs, final String rhs) {
        final EventCartridge ev1 = rsvc.getApplicationEventCartridge();
        final Iterator applicationEventHandlerIterator = (ev1 == null) ? null : ev1.getNullSetEventHandlers();
        final EventCartridge ev2 = context.getEventCartridge();
        initializeEventCartridge(rsvc, ev2);
        final Iterator contextEventHandlerIterator = (ev2 == null) ? null : ev2.getNullSetEventHandlers();
        try {
            final EventHandlerMethodExecutor methodExecutor = new NullSetEventHandler.ShouldLogOnNullSetExecutor(context, lhs, rhs);
            callEventHandlers(applicationEventHandlerIterator, contextEventHandlerIterator, methodExecutor);
            return (boolean)methodExecutor.getReturnValue();
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            throw ExceptionUtils.createRuntimeException("Exception in event handler.", e2);
        }
    }
    
    public static Object methodException(final RuntimeServices rsvc, final InternalContextAdapter context, final Class claz, final String method, final Exception e) throws Exception {
        final EventCartridge ev1 = rsvc.getApplicationEventCartridge();
        final Iterator applicationEventHandlerIterator = (ev1 == null) ? null : ev1.getMethodExceptionEventHandlers();
        final EventCartridge ev2 = context.getEventCartridge();
        initializeEventCartridge(rsvc, ev2);
        final Iterator contextEventHandlerIterator = (ev2 == null) ? null : ev2.getMethodExceptionEventHandlers();
        final EventHandlerMethodExecutor methodExecutor = new MethodExceptionEventHandler.MethodExceptionExecutor(context, claz, method, e);
        if ((applicationEventHandlerIterator == null || !applicationEventHandlerIterator.hasNext()) && (contextEventHandlerIterator == null || !contextEventHandlerIterator.hasNext())) {
            throw e;
        }
        callEventHandlers(applicationEventHandlerIterator, contextEventHandlerIterator, methodExecutor);
        return methodExecutor.getReturnValue();
    }
    
    public static String includeEvent(final RuntimeServices rsvc, final InternalContextAdapter context, final String includeResourcePath, final String currentResourcePath, final String directiveName) {
        final EventCartridge ev1 = rsvc.getApplicationEventCartridge();
        final Iterator applicationEventHandlerIterator = (ev1 == null) ? null : ev1.getIncludeEventHandlers();
        final EventCartridge ev2 = context.getEventCartridge();
        initializeEventCartridge(rsvc, ev2);
        final Iterator contextEventHandlerIterator = (ev2 == null) ? null : ev2.getIncludeEventHandlers();
        try {
            final EventHandlerMethodExecutor methodExecutor = new IncludeEventHandler.IncludeEventExecutor(context, includeResourcePath, currentResourcePath, directiveName);
            callEventHandlers(applicationEventHandlerIterator, contextEventHandlerIterator, methodExecutor);
            return (String)methodExecutor.getReturnValue();
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            throw ExceptionUtils.createRuntimeException("Exception in event handler.", e2);
        }
    }
    
    public static Object invalidGetMethod(final RuntimeServices rsvc, final InternalContextAdapter context, final String reference, final Object object, final String property, final Info info) {
        return invalidReferenceHandlerCall(new InvalidReferenceEventHandler.InvalidGetMethodExecutor(context, reference, object, property, info), rsvc, context);
    }
    
    public static void invalidSetMethod(final RuntimeServices rsvc, final InternalContextAdapter context, final String leftreference, final String rightreference, final Info info) {
        invalidReferenceHandlerCall(new InvalidReferenceEventHandler.InvalidSetMethodExecutor(context, leftreference, rightreference, info), rsvc, context);
    }
    
    public static Object invalidMethod(final RuntimeServices rsvc, final InternalContextAdapter context, final String reference, final Object object, final String method, final Info info) {
        return invalidReferenceHandlerCall(new InvalidReferenceEventHandler.InvalidMethodExecutor(context, reference, object, method, info), rsvc, context);
    }
    
    public static Object invalidReferenceHandlerCall(final EventHandlerMethodExecutor methodExecutor, final RuntimeServices rsvc, final InternalContextAdapter context) {
        final EventCartridge ev1 = rsvc.getApplicationEventCartridge();
        final Iterator applicationEventHandlerIterator = (ev1 == null) ? null : ev1.getInvalidReferenceEventHandlers();
        final EventCartridge ev2 = context.getEventCartridge();
        initializeEventCartridge(rsvc, ev2);
        final Iterator contextEventHandlerIterator = (ev2 == null) ? null : ev2.getInvalidReferenceEventHandlers();
        try {
            callEventHandlers(applicationEventHandlerIterator, contextEventHandlerIterator, methodExecutor);
            return methodExecutor.getReturnValue();
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e2) {
            throw ExceptionUtils.createRuntimeException("Exception in event handler.", e2);
        }
    }
    
    private static void initializeEventCartridge(final RuntimeServices rsvc, final EventCartridge eventCartridge) {
        if (eventCartridge != null) {
            try {
                eventCartridge.initialize(rsvc);
            }
            catch (Exception e) {
                throw ExceptionUtils.createRuntimeException("Couldn't initialize event cartridge : ", e);
            }
        }
    }
    
    private static void callEventHandlers(final Iterator applicationEventHandlerIterator, final Iterator contextEventHandlerIterator, final EventHandlerMethodExecutor eventExecutor) throws Exception {
        iterateOverEventHandlers(applicationEventHandlerIterator, eventExecutor);
        iterateOverEventHandlers(contextEventHandlerIterator, eventExecutor);
    }
    
    private static void iterateOverEventHandlers(final Iterator handlerIterator, final EventHandlerMethodExecutor eventExecutor) throws Exception {
        if (handlerIterator != null) {
            final Iterator i = handlerIterator;
            while (i.hasNext()) {
                final EventHandler eventHandler = i.next();
                if (!eventExecutor.isDone()) {
                    eventExecutor.execute(eventHandler);
                }
            }
        }
    }
}
