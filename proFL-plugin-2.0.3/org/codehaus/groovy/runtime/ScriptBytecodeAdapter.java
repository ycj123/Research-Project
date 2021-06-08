// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import groovy.lang.ObjectRange;
import groovy.lang.IntRange;
import groovy.lang.EmptyRange;
import java.util.Map;
import org.codehaus.groovy.runtime.wrappers.GroovyObjectWrapper;
import org.codehaus.groovy.runtime.wrappers.PojoWrapper;
import org.codehaus.groovy.runtime.wrappers.Wrapper;
import groovy.lang.Tuple;
import groovy.lang.Closure;
import groovy.lang.GroovySystem;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.MetaClass;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExecutionFailed;
import groovy.lang.GroovyInterceptable;
import groovy.lang.GroovyObject;
import groovy.lang.MissingMethodException;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExceptionNoStack;
import groovy.lang.MissingPropertyException;
import org.codehaus.groovy.runtime.metaclass.MissingPropertyExceptionNoStack;
import groovy.lang.GroovyRuntimeException;

public class ScriptBytecodeAdapter
{
    public static final Object[] EMPTY_ARGS;
    private static final Integer ZERO;
    private static final Integer MINUS_ONE;
    private static final Integer ONE;
    
    public static Throwable unwrap(final GroovyRuntimeException gre) {
        if (gre instanceof MissingPropertyExceptionNoStack) {
            final MissingPropertyExceptionNoStack noStack = (MissingPropertyExceptionNoStack)gre;
            return new MissingPropertyException(noStack.getProperty(), noStack.getType());
        }
        if (gre instanceof MissingMethodExceptionNoStack) {
            final MissingMethodExceptionNoStack noStack2 = (MissingMethodExceptionNoStack)gre;
            return new MissingMethodException(noStack2.getMethod(), noStack2.getType(), noStack2.getArguments(), noStack2.isStatic());
        }
        Throwable th = gre;
        if (th.getCause() != null && th.getCause() != gre) {
            th = th.getCause();
        }
        if (th != gre && th instanceof GroovyRuntimeException) {
            return unwrap((GroovyRuntimeException)th);
        }
        return th;
    }
    
    public static Object invokeMethodOnCurrentN(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        Object result = null;
        final boolean intercepting = receiver instanceof GroovyInterceptable;
        try {
            try {
                if (intercepting) {
                    result = receiver.invokeMethod(messageName, messageArguments);
                }
                else {
                    result = receiver.getMetaClass().invokeMethod(senderClass, receiver, messageName, messageArguments, false, true);
                }
            }
            catch (MissingMethodException e) {
                if (e instanceof MissingMethodExecutionFailed) {
                    throw (MissingMethodException)e.getCause();
                }
                if (intercepting || receiver.getClass() != e.getType() || !e.getMethod().equals(messageName)) {
                    throw e;
                }
                result = receiver.invokeMethod(messageName, messageArguments);
            }
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
        return result;
    }
    
    public static Object invokeMethodOnCurrentNSafe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        return invokeMethodOnCurrentN(senderClass, receiver, messageName, messageArguments);
    }
    
    public static Object invokeMethodOnCurrentNSpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(invokeMethodNSafe(senderClass, it.next(), messageName, messageArguments));
        }
        return answer;
    }
    
    public static Object invokeMethodOnCurrent0(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        return invokeMethodOnCurrentN(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethodOnCurrent0Safe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        return invokeMethodOnCurrentNSafe(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethodOnCurrent0SpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        return invokeMethodOnCurrentNSpreadSafe(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethodOnSuperN(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        final MetaClass metaClass = receiver.getMetaClass();
        Object result = null;
        try {
            result = metaClass.invokeMethod(senderClass, receiver, messageName, messageArguments, true, true);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
        return result;
    }
    
    public static Object invokeMethodOnSuperNSafe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        return invokeMethodOnSuperN(senderClass, receiver, messageName, messageArguments);
    }
    
    public static Object invokeMethodOnSuperNSpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(invokeMethodNSafe(senderClass, it.next(), messageName, messageArguments));
        }
        return answer;
    }
    
    public static Object invokeMethodOnSuper0(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        return invokeMethodOnSuperN(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethodOnSuper0Safe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        return invokeMethodOnSuperNSafe(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethodOnSuper0SpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        return invokeMethodOnSuperNSpreadSafe(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethodN(final Class senderClass, final Object receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        try {
            return InvokerHelper.invokeMethod(receiver, messageName, messageArguments);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object invokeMethodNSafe(final Class senderClass, final Object receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return invokeMethodN(senderClass, receiver, messageName, messageArguments);
    }
    
    public static Object invokeMethodNSpreadSafe(final Class senderClass, final Object receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        if (receiver == null) {
            return null;
        }
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(invokeMethodNSafe(senderClass, it.next(), messageName, messageArguments));
        }
        return answer;
    }
    
    private static Object[] getBoxedItems(final Object receiver) {
        return DefaultTypeTransformation.primitiveArrayToList(receiver).toArray();
    }
    
    public static Object invokeMethod0(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        return invokeMethodN(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethod0Safe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return invokeMethodNSafe(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeMethod0SpreadSafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        return invokeMethodNSpreadSafe(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeStaticMethodN(final Class senderClass, final Class receiver, final String messageName, final Object[] messageArguments) throws Throwable {
        try {
            return InvokerHelper.invokeStaticMethod(receiver, messageName, messageArguments);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object invokeStaticMethod0(final Class senderClass, final Class receiver, final String messageName) throws Throwable {
        return invokeStaticMethodN(senderClass, receiver, messageName, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static Object invokeNewN(final Class senderClass, final Class receiver, final Object arguments) throws Throwable {
        try {
            return InvokerHelper.invokeConstructorOf(receiver, arguments);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object invokeNew0(final Class senderClass, final Class receiver) throws Throwable {
        return invokeNewN(senderClass, receiver, ScriptBytecodeAdapter.EMPTY_ARGS);
    }
    
    public static int selectConstructorAndTransformArguments(final Object[] arguments, final int numberOfConstructors, final Class which) throws Throwable {
        final MetaClass metaClass = GroovySystem.getMetaClassRegistry().getMetaClass(which);
        try {
            return metaClass.selectConstructorAndTransformArguments(numberOfConstructors, arguments);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object getFieldOnSuper(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        try {
            if (receiver instanceof Class) {
                return InvokerHelper.getAttribute(receiver, messageName);
            }
            final MetaClass mc = ((GroovyObject)receiver).getMetaClass();
            return mc.getAttribute(senderClass, receiver, messageName, true);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object getFieldOnSuperSafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        return getFieldOnSuper(senderClass, receiver, messageName);
    }
    
    public static Object getFieldOnSuperSpreadSafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(getFieldOnSuper(senderClass, it.next(), messageName));
        }
        return answer;
    }
    
    public static void setFieldOnSuper(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        try {
            if (receiver instanceof Class) {
                InvokerHelper.setAttribute(receiver, messageName, messageArgument);
            }
            else {
                final MetaClass mc = ((GroovyObject)receiver).getMetaClass();
                mc.setAttribute(senderClass, receiver, messageName, messageArgument, true, true);
            }
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setFieldOnSuperSafe(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        setFieldOnSuper(messageArgument, senderClass, receiver, messageName);
    }
    
    public static void setFieldOnSuperSpreadSafe(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            setFieldOnSuper(messageArgument, senderClass, it.next(), messageName);
        }
    }
    
    public static Object getField(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        try {
            return InvokerHelper.getAttribute(receiver, messageName);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object getFieldSafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return getField(senderClass, receiver, messageName);
    }
    
    public static Object getFieldSpreadSafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(getFieldSafe(senderClass, it.next(), messageName));
        }
        return answer;
    }
    
    public static void setField(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        try {
            InvokerHelper.setAttribute(receiver, messageName, messageArgument);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setFieldSafe(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        setField(messageArgument, senderClass, receiver, messageName);
    }
    
    public static void setFieldSpreadSafe(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            setFieldSafe(messageArgument, senderClass, it.next(), messageName);
        }
    }
    
    public static Object getGroovyObjectField(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        try {
            return receiver.getMetaClass().getAttribute(receiver, messageName);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object getGroovyObjectFieldSafe(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        try {
            return receiver.getMetaClass().getAttribute(receiver, messageName);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object getGroovyObjectFieldSpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(getFieldSafe(senderClass, it.next(), messageName));
        }
        return answer;
    }
    
    public static void setGroovyObjectField(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        try {
            receiver.getMetaClass().setAttribute(receiver, messageName, messageArgument);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setGroovyObjectFieldSafe(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        try {
            receiver.getMetaClass().setAttribute(receiver, messageName, messageArgument);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setGroovyObjectFieldSpreadSafe(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            setFieldSafe(messageArgument, senderClass, it.next(), messageName);
        }
    }
    
    public static Object getPropertyOnSuper(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        return invokeMethodOnSuperN(senderClass, receiver, "getProperty", new Object[] { messageName });
    }
    
    public static Object getPropertyOnSuperSafe(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        return getPropertyOnSuper(senderClass, receiver, messageName);
    }
    
    public static Object getPropertyOnSuperSpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(getPropertySafe(senderClass, it.next(), messageName));
        }
        return answer;
    }
    
    public static void setPropertyOnSuper(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        try {
            InvokerHelper.setAttribute(receiver, messageName, messageArgument);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setPropertyOnSuperSafe(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        setPropertyOnSuper(messageArgument, senderClass, receiver, messageName);
    }
    
    public static void setPropertyOnSuperSpreadSafe(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            setPropertySafe(messageArgument, senderClass, it.next(), messageName);
        }
    }
    
    public static Object getProperty(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        try {
            return InvokerHelper.getProperty(receiver, messageName);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object getPropertySafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return getProperty(senderClass, receiver, messageName);
    }
    
    public static Object getPropertySpreadSafe(final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(getPropertySafe(senderClass, it.next(), messageName));
        }
        return answer;
    }
    
    public static void setProperty(final Object messageArgument, final Class senderClass, Object receiver, final String messageName) throws Throwable {
        try {
            if (receiver == null) {
                receiver = NullObject.getNullObject();
            }
            InvokerHelper.setProperty(receiver, messageName, messageArgument);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setPropertySafe(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        setProperty(messageArgument, senderClass, receiver, messageName);
    }
    
    public static void setPropertySpreadSafe(final Object messageArgument, final Class senderClass, final Object receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            setPropertySafe(messageArgument, senderClass, it.next(), messageName);
        }
    }
    
    public static Object getGroovyObjectProperty(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        return receiver.getProperty(messageName);
    }
    
    public static Object getGroovyObjectPropertySafe(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        return getGroovyObjectProperty(senderClass, receiver, messageName);
    }
    
    public static Object getGroovyObjectPropertySpreadSafe(final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return null;
        }
        final List answer = new ArrayList();
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            answer.add(getPropertySafe(senderClass, it.next(), messageName));
        }
        return answer;
    }
    
    public static void setGroovyObjectProperty(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        try {
            receiver.setProperty(messageName, messageArgument);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static void setGroovyObjectPropertySafe(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        receiver.setProperty(messageName, messageArgument);
    }
    
    public static void setGroovyObjectPropertySpreadSafe(final Object messageArgument, final Class senderClass, final GroovyObject receiver, final String messageName) throws Throwable {
        if (receiver == null) {
            return;
        }
        final Iterator it = InvokerHelper.asIterator(receiver);
        while (it.hasNext()) {
            setPropertySafe(messageArgument, senderClass, it.next(), messageName);
        }
    }
    
    public static Closure getMethodPointer(final Object object, final String methodName) {
        return InvokerHelper.getMethodPointer(object, methodName);
    }
    
    public static Object invokeClosure(final Object closure, final Object[] arguments) throws Throwable {
        return invokeMethodN(closure.getClass(), closure, "call", arguments);
    }
    
    public static Object asType(Object object, final Class type) throws Throwable {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        return invokeMethodN(object.getClass(), object, "asType", new Object[] { type });
    }
    
    public static Object castToType(final Object object, final Class type) throws Throwable {
        return DefaultTypeTransformation.castToType(object, type);
    }
    
    public static Tuple createTuple(final Object[] array) {
        return new Tuple(array);
    }
    
    public static List createList(final Object[] values) {
        return InvokerHelper.createList(values);
    }
    
    public static Wrapper createPojoWrapper(final Object val, final Class clazz) {
        return new PojoWrapper(val, clazz);
    }
    
    public static Wrapper createGroovyObjectWrapper(final GroovyObject val, final Class clazz) {
        return new GroovyObjectWrapper(val, clazz);
    }
    
    public static Map createMap(final Object[] values) {
        return InvokerHelper.createMap(values);
    }
    
    public static List createRange(final Object from, Object to, final boolean inclusive) throws Throwable {
        if (from instanceof Integer && to instanceof Integer) {
            int ito = (int)to;
            final int ifrom = (int)from;
            if (!inclusive) {
                if (ifrom == ito) {
                    return new EmptyRange((Comparable)from);
                }
                if (ifrom > ito) {
                    ++ito;
                }
                else {
                    --ito;
                }
            }
            return new IntRange(ifrom, ito);
        }
        if (!inclusive) {
            if (compareEqual(from, to)) {
                return new EmptyRange((Comparable)from);
            }
            if (compareGreaterThan(from, to)) {
                to = invokeMethod0(ScriptBytecodeAdapter.class, to, "next");
            }
            else {
                to = invokeMethod0(ScriptBytecodeAdapter.class, to, "previous");
            }
        }
        if (from instanceof Integer && to instanceof Integer) {
            return new IntRange(DefaultTypeTransformation.intUnbox(from), DefaultTypeTransformation.intUnbox(to));
        }
        return new ObjectRange((Comparable)from, (Comparable)to);
    }
    
    public static void assertFailed(final Object expression, final Object message) {
        InvokerHelper.assertFailed(expression, message);
    }
    
    public static boolean isCase(final Object switchValue, final Object caseExpression) throws Throwable {
        if (caseExpression == null) {
            return switchValue == null;
        }
        return DefaultTypeTransformation.castToBoolean(invokeMethodN(caseExpression.getClass(), caseExpression, "isCase", new Object[] { switchValue }));
    }
    
    public static boolean compareIdentical(final Object left, final Object right) {
        return left == right;
    }
    
    public static boolean compareNotIdentical(final Object left, final Object right) {
        return left != right;
    }
    
    public static boolean compareEqual(final Object left, final Object right) {
        return DefaultTypeTransformation.compareEqual(left, right);
    }
    
    public static boolean compareNotEqual(final Object left, final Object right) {
        return !compareEqual(left, right);
    }
    
    public static Integer compareTo(final Object left, final Object right) {
        final int answer = DefaultTypeTransformation.compareTo(left, right);
        if (answer == 0) {
            return ScriptBytecodeAdapter.ZERO;
        }
        return (answer > 0) ? ScriptBytecodeAdapter.ONE : ScriptBytecodeAdapter.MINUS_ONE;
    }
    
    public static boolean compareLessThan(final Object left, final Object right) {
        return compareTo(left, right) < 0;
    }
    
    public static boolean compareLessThanEqual(final Object left, final Object right) {
        return compareTo(left, right) <= 0;
    }
    
    public static boolean compareGreaterThan(final Object left, final Object right) {
        return compareTo(left, right) > 0;
    }
    
    public static boolean compareGreaterThanEqual(final Object left, final Object right) {
        return compareTo(left, right) >= 0;
    }
    
    public static Pattern regexPattern(final Object regex) {
        return DefaultGroovyMethods.bitwiseNegate(regex.toString());
    }
    
    public static Matcher findRegex(final Object left, final Object right) throws Throwable {
        return InvokerHelper.findRegex(left, right);
    }
    
    public static boolean matchRegex(final Object left, final Object right) {
        return InvokerHelper.matchRegex(left, right);
    }
    
    public static Object[] despreadList(final Object[] args, final Object[] spreads, final int[] positions) {
        final List ret = new ArrayList();
        int argsPos = 0;
        int spreadPos = 0;
        for (int pos = 0; pos < positions.length; ++pos) {
            while (argsPos < positions[pos]) {
                ret.add(args[argsPos]);
                ++argsPos;
            }
            final Object value = spreads[spreadPos];
            if (value == null) {
                ret.add(null);
            }
            else if (value instanceof List) {
                ret.addAll((Collection)value);
            }
            else {
                if (!value.getClass().isArray()) {
                    throw new IllegalArgumentException("cannot spread the type " + value.getClass().getName() + " with value " + value);
                }
                ret.addAll(DefaultTypeTransformation.primitiveArrayToList(value));
            }
            ++spreadPos;
        }
        while (argsPos < args.length) {
            ret.add(args[argsPos]);
            ++argsPos;
        }
        return ret.toArray();
    }
    
    public static Object spreadMap(final Object value) {
        return InvokerHelper.spreadMap(value);
    }
    
    public static Object unaryMinus(final Object value) throws Throwable {
        return InvokerHelper.unaryMinus(value);
    }
    
    public static Object unaryPlus(final Object value) throws Throwable {
        try {
            return InvokerHelper.unaryPlus(value);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static Object bitwiseNegate(final Object value) throws Throwable {
        try {
            return InvokerHelper.bitwiseNegate(value);
        }
        catch (GroovyRuntimeException gre) {
            throw unwrap(gre);
        }
    }
    
    public static MetaClass initMetaClass(final Object object) {
        return InvokerHelper.getMetaClass(object.getClass());
    }
    
    static {
        EMPTY_ARGS = new Object[0];
        ZERO = 0;
        MINUS_ONE = -1;
        ONE = 1;
    }
}
