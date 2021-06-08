// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.wrappers.PojoWrapper;
import groovy.lang.MissingMethodException;
import org.codehaus.groovy.runtime.metaclass.MissingMethodExecutionFailed;
import groovy.lang.GroovyInterceptable;
import groovy.lang.GString;
import groovy.xml.XmlUtil;
import org.w3c.dom.Element;
import groovy.lang.Range;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.InputStream;
import groovy.lang.Writable;
import java.io.Writer;
import org.codehaus.groovy.util.ReleaseInfo;
import groovy.lang.MetaClass;
import groovy.lang.MissingPropertyException;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.Script;
import groovy.lang.Binding;
import org.codehaus.groovy.transform.powerassert.PowerAssertionError;
import java.util.LinkedHashMap;
import groovy.lang.SpreadMapEvaluatingException;
import groovy.lang.SpreadMap;
import groovy.lang.Tuple;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Iterator;
import java.math.BigDecimal;
import java.math.BigInteger;
import groovy.lang.Closure;
import groovy.lang.GroovySystem;
import org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl;
import groovy.lang.GroovyObject;
import java.util.Map;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.beans.Introspector;
import groovy.lang.MetaClassRegistry;

public class InvokerHelper
{
    private static final Object[] EMPTY_MAIN_ARGS;
    public static final Object[] EMPTY_ARGS;
    protected static final Object[] EMPTY_ARGUMENTS;
    protected static final Class[] EMPTY_TYPES;
    public static final MetaClassRegistry metaRegistry;
    
    public static void removeClass(final Class clazz) {
        InvokerHelper.metaRegistry.removeMetaClass(clazz);
        Introspector.flushFromCaches(clazz);
    }
    
    public static Object invokeMethodSafe(final Object object, final String methodName, final Object arguments) {
        if (object != null) {
            return invokeMethod(object, methodName, arguments);
        }
        return null;
    }
    
    public static Object invokeStaticMethod(final String klass, final String methodName, final Object arguments) throws ClassNotFoundException {
        final Class type = Class.forName(klass);
        return invokeStaticMethod(type, methodName, arguments);
    }
    
    public static Object invokeStaticNoArgumentsMethod(final Class type, final String methodName) {
        return invokeStaticMethod(type, methodName, InvokerHelper.EMPTY_ARGS);
    }
    
    public static Object invokeConstructorOf(final String klass, final Object arguments) throws ClassNotFoundException {
        final Class type = Class.forName(klass);
        return invokeConstructorOf(type, arguments);
    }
    
    public static Object invokeNoArgumentsConstructorOf(final Class type) {
        return invokeConstructorOf(type, InvokerHelper.EMPTY_ARGS);
    }
    
    public static Object invokeClosure(final Object closure, final Object arguments) {
        return invokeMethod(closure, "doCall", arguments);
    }
    
    public static List asList(final Object value) {
        if (value == null) {
            return Collections.EMPTY_LIST;
        }
        if (value instanceof List) {
            return (List)value;
        }
        if (value.getClass().isArray()) {
            return Arrays.asList((Object[])value);
        }
        if (value instanceof Enumeration) {
            final List answer = new ArrayList();
            final Enumeration e = (Enumeration)value;
            while (e.hasMoreElements()) {
                answer.add(e.nextElement());
            }
            return answer;
        }
        return Collections.singletonList(value);
    }
    
    public static String toString(final Object arguments) {
        if (arguments instanceof Object[]) {
            return toArrayString((Object[])arguments);
        }
        if (arguments instanceof Collection) {
            return toListString((Collection)arguments);
        }
        if (arguments instanceof Map) {
            return toMapString((Map)arguments);
        }
        return format(arguments, false);
    }
    
    public static String inspect(final Object self) {
        return format(self, true);
    }
    
    public static Object getAttribute(Object object, final String attribute) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof Class) {
            return InvokerHelper.metaRegistry.getMetaClass((Class)object).getAttribute(object, attribute);
        }
        if (object instanceof GroovyObject) {
            return ((GroovyObject)object).getMetaClass().getAttribute(object, attribute);
        }
        return InvokerHelper.metaRegistry.getMetaClass(object.getClass()).getAttribute(object, attribute);
    }
    
    public static void setAttribute(Object object, final String attribute, final Object newValue) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof Class) {
            InvokerHelper.metaRegistry.getMetaClass((Class)object).setAttribute(object, attribute, newValue);
        }
        else if (object instanceof GroovyObject) {
            ((GroovyObject)object).getMetaClass().setAttribute(object, attribute, newValue);
        }
        else {
            InvokerHelper.metaRegistry.getMetaClass(object.getClass()).setAttribute(object, attribute, newValue);
        }
    }
    
    public static Object getProperty(Object object, final String property) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof GroovyObject) {
            final GroovyObject pogo = (GroovyObject)object;
            return pogo.getProperty(property);
        }
        if (object instanceof Class) {
            final Class c = (Class)object;
            return InvokerHelper.metaRegistry.getMetaClass(c).getProperty(object, property);
        }
        return ((MetaClassRegistryImpl)InvokerHelper.metaRegistry).getMetaClass(object).getProperty(object, property);
    }
    
    public static Object getPropertySafe(final Object object, final String property) {
        if (object != null) {
            return getProperty(object, property);
        }
        return null;
    }
    
    public static void setProperty(Object object, final String property, final Object newValue) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof GroovyObject) {
            final GroovyObject pogo = (GroovyObject)object;
            pogo.setProperty(property, newValue);
        }
        else if (object instanceof Class) {
            InvokerHelper.metaRegistry.getMetaClass((Class)object).setProperty(object, property, newValue);
        }
        else {
            ((MetaClassRegistryImpl)GroovySystem.getMetaClassRegistry()).getMetaClass(object).setProperty(object, property, newValue);
        }
    }
    
    public static void setProperty2(final Object newValue, final Object object, final String property) {
        setProperty(object, property, newValue);
    }
    
    public static void setGroovyObjectProperty(final Object newValue, final GroovyObject object, final String property) {
        object.setProperty(property, newValue);
    }
    
    public static Object getGroovyObjectProperty(final GroovyObject object, final String property) {
        return object.getProperty(property);
    }
    
    public static void setPropertySafe2(final Object newValue, final Object object, final String property) {
        if (object != null) {
            setProperty2(newValue, object, property);
        }
    }
    
    public static Closure getMethodPointer(final Object object, final String methodName) {
        if (object == null) {
            throw new NullPointerException("Cannot access method pointer for '" + methodName + "' on null object");
        }
        return new MethodClosure(object, methodName);
    }
    
    public static Object unaryMinus(final Object value) {
        if (value instanceof Integer) {
            final Integer number = (Integer)value;
            return -number;
        }
        if (value instanceof Long) {
            final Long number2 = (Long)value;
            return -number2;
        }
        if (value instanceof BigInteger) {
            return ((BigInteger)value).negate();
        }
        if (value instanceof BigDecimal) {
            return ((BigDecimal)value).negate();
        }
        if (value instanceof Double) {
            final Double number3 = (Double)value;
            return -number3;
        }
        if (value instanceof Float) {
            final Float number4 = (Float)value;
            return -number4;
        }
        if (value instanceof Short) {
            final Short number5 = (Short)value;
            return -number5;
        }
        if (value instanceof Byte) {
            final Byte number6 = (Byte)value;
            return -number6;
        }
        if (value instanceof ArrayList) {
            final List newlist = new ArrayList();
            final Iterator it = ((ArrayList)value).iterator();
            while (it.hasNext()) {
                newlist.add(unaryMinus(it.next()));
            }
            return newlist;
        }
        return invokeMethod(value, "negative", InvokerHelper.EMPTY_ARGS);
    }
    
    public static Object unaryPlus(final Object value) {
        if (value instanceof Integer || value instanceof Long || value instanceof BigInteger || value instanceof BigDecimal || value instanceof Double || value instanceof Float || value instanceof Short || value instanceof Byte) {
            return value;
        }
        if (value instanceof ArrayList) {
            final List newlist = new ArrayList();
            final Iterator it = ((ArrayList)value).iterator();
            while (it.hasNext()) {
                newlist.add(unaryPlus(it.next()));
            }
            return newlist;
        }
        return invokeMethod(value, "positive", InvokerHelper.EMPTY_ARGS);
    }
    
    public static Matcher findRegex(final Object left, final Object right) {
        String stringToCompare;
        if (left instanceof String) {
            stringToCompare = (String)left;
        }
        else {
            stringToCompare = toString(left);
        }
        String regexToCompareTo;
        if (right instanceof String) {
            regexToCompareTo = (String)right;
        }
        else {
            if (right instanceof Pattern) {
                final Pattern pattern = (Pattern)right;
                return pattern.matcher(stringToCompare);
            }
            regexToCompareTo = toString(right);
        }
        return Pattern.compile(regexToCompareTo).matcher(stringToCompare);
    }
    
    public static boolean matchRegex(final Object left, final Object right) {
        if (left == null || right == null) {
            return false;
        }
        Pattern pattern;
        if (right instanceof Pattern) {
            pattern = (Pattern)right;
        }
        else {
            pattern = Pattern.compile(toString(right));
        }
        final String stringToCompare = toString(left);
        final Matcher matcher = pattern.matcher(stringToCompare);
        RegexSupport.setLastMatcher(matcher);
        return matcher.matches();
    }
    
    public static Tuple createTuple(final Object[] array) {
        return new Tuple(array);
    }
    
    public static SpreadMap spreadMap(final Object value) {
        if (value instanceof Map) {
            final Object[] values = new Object[((Map)value).keySet().size() * 2];
            int index = 0;
            for (final Object key : ((Map)value).keySet()) {
                values[index++] = key;
                values[index++] = ((Map)value).get(key);
            }
            return new SpreadMap(values);
        }
        throw new SpreadMapEvaluatingException("Cannot spread the map " + value.getClass().getName() + ", value " + value);
    }
    
    public static List createList(final Object[] values) {
        final List answer = new ArrayList(values.length);
        answer.addAll(Arrays.asList(values));
        return answer;
    }
    
    public static Map createMap(final Object[] values) {
        final Map answer = new LinkedHashMap(values.length / 2);
        int i = 0;
        while (i < values.length - 1) {
            if (values[i] instanceof SpreadMap && values[i + 1] instanceof Map) {
                final Map smap = (Map)values[i + 1];
                for (final Object key : smap.keySet()) {
                    answer.put(key, smap.get(key));
                }
                i += 2;
            }
            else {
                answer.put(values[i++], values[i++]);
            }
        }
        return answer;
    }
    
    public static void assertFailed(final Object expression, final Object message) {
        if (message == null || "".equals(message)) {
            throw new PowerAssertionError(expression.toString());
        }
        throw new AssertionError((Object)(String.valueOf(message) + ". Expression: " + expression));
    }
    
    public static Object runScript(final Class scriptClass, final String[] args) {
        final Binding context = new Binding(args);
        final Script script = createScript(scriptClass, context);
        return invokeMethod(script, "run", InvokerHelper.EMPTY_ARGS);
    }
    
    public static Script createScript(final Class scriptClass, final Binding context) {
        Script script = null;
        if (scriptClass == null) {
            script = new Script() {
                @Override
                public Object run() {
                    return null;
                }
            };
        }
        else {
            try {
                final GroovyObject object = scriptClass.newInstance();
                if (object instanceof Script) {
                    script = (Script)object;
                }
                else {
                    script = new Script() {
                        @Override
                        public Object run() {
                            final Object args = this.getBinding().getVariables().get("args");
                            Object argsToPass = InvokerHelper.EMPTY_MAIN_ARGS;
                            if (args != null && args instanceof String[]) {
                                argsToPass = args;
                            }
                            object.invokeMethod("main", argsToPass);
                            return null;
                        }
                    };
                    setProperties(object, context.getVariables());
                }
            }
            catch (Exception e) {
                throw new GroovyRuntimeException("Failed to create Script instance for class: " + scriptClass + ". Reason: " + e, e);
            }
        }
        script.setBinding(context);
        return script;
    }
    
    public static void setProperties(final Object object, final Map map) {
        final MetaClass mc = getMetaClass(object);
        for (final Object o : map.entrySet()) {
            final Map.Entry entry = (Map.Entry)o;
            final String key = entry.getKey().toString();
            final Object value = entry.getValue();
            try {
                mc.setProperty(object, key, value);
            }
            catch (MissingPropertyException ex) {}
        }
    }
    
    @Deprecated
    public static String getVersion() {
        return ReleaseInfo.getVersion();
    }
    
    public static void write(final Writer out, final Object object) throws IOException {
        if (object instanceof String) {
            out.write((String)object);
        }
        else if (object instanceof Object[]) {
            out.write(toArrayString((Object[])object));
        }
        else if (object instanceof Map) {
            out.write(toMapString((Map)object));
        }
        else if (object instanceof Collection) {
            out.write(toListString((Collection)object));
        }
        else if (object instanceof Writable) {
            final Writable writable = (Writable)object;
            writable.writeTo(out);
        }
        else if (object instanceof InputStream || object instanceof Reader) {
            Reader reader;
            if (object instanceof InputStream) {
                reader = new InputStreamReader((InputStream)object);
            }
            else {
                reader = (Reader)object;
            }
            final char[] chars = new char[8192];
            int i;
            while ((i = reader.read(chars)) != -1) {
                out.write(chars, 0, i);
            }
            reader.close();
        }
        else {
            out.write(toString(object));
        }
    }
    
    public static Iterator<Object> asIterator(final Object o) {
        return (Iterator<Object>)invokeMethod(o, "iterator", InvokerHelper.EMPTY_ARGS);
    }
    
    protected static String format(final Object arguments, final boolean verbose) {
        return format(arguments, verbose, -1);
    }
    
    protected static String format(final Object arguments, final boolean verbose, final int maxSize) {
        if (arguments == null) {
            final NullObject nullObject = NullObject.getNullObject();
            return (String)nullObject.getMetaClass().invokeMethod(nullObject, "toString", InvokerHelper.EMPTY_ARGS);
        }
        if (arguments.getClass().isArray()) {
            if (arguments instanceof char[]) {
                return new String((char[])arguments);
            }
            return format(DefaultTypeTransformation.asCollection(arguments), verbose);
        }
        else if (arguments instanceof Range) {
            final Range range = (Range)arguments;
            if (verbose) {
                return range.inspect();
            }
            return range.toString();
        }
        else {
            if (arguments instanceof Collection) {
                return formatList((Collection)arguments, verbose, maxSize);
            }
            if (arguments instanceof Map) {
                return formatMap((Map)arguments, verbose, maxSize);
            }
            if (arguments instanceof Element) {
                return XmlUtil.serialize((Element)arguments);
            }
            if (!(arguments instanceof String)) {
                return arguments.toString();
            }
            if (verbose) {
                String arg = ((String)arguments).replaceAll("\\n", "\\\\n");
                arg = arg.replaceAll("\\r", "\\\\r");
                arg = arg.replaceAll("\\t", "\\\\t");
                arg = arg.replaceAll("\\f", "\\\\f");
                arg = arg.replaceAll("\\\"", "\\\\\"");
                arg = arg.replaceAll("\\\\", "\\\\");
                return "\"" + arg + "\"";
            }
            return (String)arguments;
        }
    }
    
    private static String formatMap(final Map map, final boolean verbose, final int maxSize) {
        if (map.isEmpty()) {
            return "[:]";
        }
        final StringBuffer buffer = new StringBuffer("[");
        boolean first = true;
        for (final Object o : map.entrySet()) {
            if (first) {
                first = false;
            }
            else {
                buffer.append(", ");
            }
            if (maxSize != -1 && buffer.length() > maxSize) {
                buffer.append("...");
                break;
            }
            final Map.Entry entry = (Map.Entry)o;
            buffer.append(format(entry.getKey(), verbose));
            buffer.append(":");
            if (entry.getValue() == map) {
                buffer.append("(this Map)");
            }
            else {
                buffer.append(format(entry.getValue(), verbose, sizeLeft(maxSize, buffer)));
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    private static int sizeLeft(final int maxSize, final StringBuffer buffer) {
        return (maxSize == -1) ? maxSize : Math.max(0, maxSize - buffer.length());
    }
    
    private static String formatList(final Collection collection, final boolean verbose, final int maxSize) {
        final StringBuffer buffer = new StringBuffer("[");
        boolean first = true;
        for (final Object item : collection) {
            if (first) {
                first = false;
            }
            else {
                buffer.append(", ");
            }
            if (maxSize != -1 && buffer.length() > maxSize) {
                buffer.append("...");
                break;
            }
            if (item == collection) {
                buffer.append("(this Collection)");
            }
            else {
                buffer.append(format(item, verbose, sizeLeft(maxSize, buffer)));
            }
        }
        buffer.append("]");
        return buffer.toString();
    }
    
    public static String toTypeString(final Object[] arguments) {
        if (arguments == null) {
            return "null";
        }
        final StringBuffer argBuf = new StringBuffer();
        for (int i = 0; i < arguments.length; ++i) {
            if (i > 0) {
                argBuf.append(", ");
            }
            argBuf.append((arguments[i] != null) ? arguments[i].getClass().getName() : "null");
        }
        return argBuf.toString();
    }
    
    public static String toMapString(final Map arg) {
        return toMapString(arg, -1);
    }
    
    public static String toMapString(final Map arg, final int maxSize) {
        return formatMap(arg, false, maxSize);
    }
    
    public static String toListString(final Collection arg) {
        return toListString(arg, -1);
    }
    
    public static String toListString(final Collection arg, final int maxSize) {
        return formatList(arg, false, maxSize);
    }
    
    public static String toArrayString(final Object[] arguments) {
        if (arguments == null) {
            return "null";
        }
        final String sbdry = "[";
        final String ebdry = "]";
        final StringBuffer argBuf = new StringBuffer(sbdry);
        for (int i = 0; i < arguments.length; ++i) {
            if (i > 0) {
                argBuf.append(", ");
            }
            argBuf.append(format(arguments[i], false));
        }
        argBuf.append(ebdry);
        return argBuf.toString();
    }
    
    public static List createRange(final Object from, final Object to, final boolean inclusive) {
        try {
            return ScriptBytecodeAdapter.createRange(from, to, inclusive);
        }
        catch (RuntimeException re) {
            throw re;
        }
        catch (Error e) {
            throw e;
        }
        catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
    public static Object bitwiseNegate(final Object value) {
        if (value instanceof Integer) {
            final Integer number = (Integer)value;
            return ~number;
        }
        if (value instanceof Long) {
            final Long number2 = (Long)value;
            return ~(long)number2;
        }
        if (value instanceof BigInteger) {
            return ((BigInteger)value).not();
        }
        if (value instanceof String) {
            return DefaultGroovyMethods.bitwiseNegate(value.toString());
        }
        if (value instanceof GString) {
            return DefaultGroovyMethods.bitwiseNegate(value.toString());
        }
        if (value instanceof ArrayList) {
            final List newlist = new ArrayList();
            final Iterator it = ((ArrayList)value).iterator();
            while (it.hasNext()) {
                newlist.add(bitwiseNegate(it.next()));
            }
            return newlist;
        }
        return invokeMethod(value, "bitwiseNegate", InvokerHelper.EMPTY_ARGS);
    }
    
    public static MetaClassRegistry getMetaRegistry() {
        return InvokerHelper.metaRegistry;
    }
    
    public static MetaClass getMetaClass(final Object object) {
        if (object instanceof GroovyObject) {
            return ((GroovyObject)object).getMetaClass();
        }
        return ((MetaClassRegistryImpl)GroovySystem.getMetaClassRegistry()).getMetaClass(object);
    }
    
    public static MetaClass getMetaClass(final Class cls) {
        return InvokerHelper.metaRegistry.getMetaClass(cls);
    }
    
    public static Object invokeMethod(Object object, final String methodName, final Object arguments) {
        if (object == null) {
            object = NullObject.getNullObject();
        }
        if (object instanceof Class) {
            final Class theClass = (Class)object;
            final MetaClass metaClass = InvokerHelper.metaRegistry.getMetaClass(theClass);
            return metaClass.invokeStaticMethod(object, methodName, asArray(arguments));
        }
        if (!(object instanceof GroovyObject)) {
            return invokePojoMethod(object, methodName, arguments);
        }
        return invokePogoMethod(object, methodName, arguments);
    }
    
    static Object invokePojoMethod(final Object object, final String methodName, final Object arguments) {
        final MetaClass metaClass = getMetaClass(object);
        return metaClass.invokeMethod(object, methodName, asArray(arguments));
    }
    
    static Object invokePogoMethod(final Object object, final String methodName, final Object arguments) {
        final GroovyObject groovy = (GroovyObject)object;
        final boolean intercepting = groovy instanceof GroovyInterceptable;
        try {
            if (intercepting) {
                return groovy.invokeMethod(methodName, asUnwrappedArray(arguments));
            }
            return groovy.getMetaClass().invokeMethod(object, methodName, asArray(arguments));
        }
        catch (MissingMethodException e) {
            if (e instanceof MissingMethodExecutionFailed) {
                throw (MissingMethodException)e.getCause();
            }
            if (!intercepting && e.getMethod().equals(methodName) && object.getClass() == e.getType()) {
                return groovy.invokeMethod(methodName, asUnwrappedArray(arguments));
            }
            throw e;
        }
    }
    
    public static Object invokeSuperMethod(final Object object, final String methodName, final Object arguments) {
        if (object == null) {
            throw new NullPointerException("Cannot invoke method " + methodName + "() on null object");
        }
        final Class theClass = object.getClass();
        final MetaClass metaClass = InvokerHelper.metaRegistry.getMetaClass(theClass.getSuperclass());
        return metaClass.invokeMethod(object, methodName, asArray(arguments));
    }
    
    public static Object invokeStaticMethod(final Class type, final String method, final Object arguments) {
        final MetaClass metaClass = InvokerHelper.metaRegistry.getMetaClass(type);
        return metaClass.invokeStaticMethod(type, method, asArray(arguments));
    }
    
    public static Object invokeConstructorOf(final Class type, final Object arguments) {
        final MetaClass metaClass = InvokerHelper.metaRegistry.getMetaClass(type);
        return metaClass.invokeConstructor(asArray(arguments));
    }
    
    public static Object[] asArray(final Object arguments) {
        if (arguments == null) {
            return InvokerHelper.EMPTY_ARGUMENTS;
        }
        if (arguments instanceof Object[]) {
            return (Object[])arguments;
        }
        return new Object[] { arguments };
    }
    
    public static Object[] asUnwrappedArray(final Object arguments) {
        final Object[] args = asArray(arguments);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] instanceof PojoWrapper) {
                args[i] = ((PojoWrapper)args[i]).unwrap();
            }
        }
        return args;
    }
    
    static {
        EMPTY_MAIN_ARGS = new Object[] { new String[0] };
        EMPTY_ARGS = new Object[0];
        EMPTY_ARGUMENTS = InvokerHelper.EMPTY_ARGS;
        EMPTY_TYPES = new Class[0];
        metaRegistry = GroovySystem.getMetaClassRegistry();
    }
}
