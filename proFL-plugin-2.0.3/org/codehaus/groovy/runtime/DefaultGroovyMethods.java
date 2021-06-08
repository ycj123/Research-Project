// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import org.codehaus.groovy.runtime.dgmimpl.arrays.DoubleArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.DoubleArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.FloatArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.FloatArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.LongArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.LongArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ShortArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ShortArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.CharacterArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.CharacterArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ByteArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ByteArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.BooleanArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.BooleanArrayGetAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ObjectArrayPutAtMetaMethod;
import org.codehaus.groovy.runtime.dgmimpl.arrays.ObjectArrayGetAtMetaMethod;
import java.util.Calendar;
import java.util.Date;
import java.sql.SQLException;
import groovy.sql.GroovyRowResult;
import java.sql.ResultSet;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import groovy.lang.DeprecationException;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.MetaClassImpl;
import org.codehaus.groovy.runtime.metaclass.MetaClassRegistryImpl;
import groovy.lang.MetaClassRegistry;
import groovy.lang.DelegatingMetaClass;
import groovy.lang.GroovySystem;
import groovy.lang.ExpandoMetaClass;
import org.codehaus.groovy.reflection.ReflectionCache;
import org.codehaus.groovy.tools.RootLoader;
import java.net.ServerSocket;
import java.net.Socket;
import groovy.lang.StringWriterIOException;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import groovy.io.EncodingAwareBufferedWriter;
import java.io.FileWriter;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.TimerTask;
import java.util.Timer;
import java.util.HashMap;
import groovy.io.FileVisitResult;
import groovy.io.FileType;
import java.io.FileNotFoundException;
import java.io.BufferedOutputStream;
import groovy.util.CharsetToolkit;
import java.io.StringReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.ObjectStreamClass;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import org.codehaus.groovy.runtime.dgmimpl.NumberNumberDiv;
import java.math.BigDecimal;
import org.codehaus.groovy.runtime.dgmimpl.NumberNumberMultiply;
import org.codehaus.groovy.runtime.dgmimpl.NumberNumberMinus;
import org.codehaus.groovy.runtime.dgmimpl.NumberNumberPlus;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.StringTokenizer;
import java.util.BitSet;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import groovy.lang.Writable;
import java.io.StringWriter;
import java.util.concurrent.BlockingQueue;
import java.util.ListIterator;
import org.codehaus.groovy.runtime.typehandling.GroovyCastException;
import groovy.util.ProxyGenerator;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import groovy.sql.GroovyResultSet;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import groovy.lang.MapWithDefault;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.SpreadMap;
import java.util.SortedSet;
import java.util.SortedMap;
import org.codehaus.groovy.runtime.metaclass.MissingPropertyExceptionNoStack;
import groovy.lang.MissingPropertyException;
import groovy.lang.ObjectRange;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.MalformedURLException;
import java.net.URL;
import groovy.lang.EmptyRange;
import groovy.lang.IntRange;
import groovy.lang.Range;
import java.io.File;
import groovy.util.PermutationGenerator;
import java.util.HashSet;
import groovy.util.GroovyCollections;
import java.util.Enumeration;
import groovy.util.ClosureComparator;
import java.util.Comparator;
import groovy.util.OrderBy;
import java.util.Set;
import org.codehaus.groovy.runtime.typehandling.NumberMath;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collection;
import groovy.lang.GString;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.reflect.Array;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import groovy.io.GroovyPrintWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import org.codehaus.groovy.reflection.MixinInMetaClass;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;
import groovy.lang.MetaClass;
import groovy.lang.MetaProperty;
import java.util.ArrayList;
import groovy.lang.PropertyValue;
import java.util.List;
import java.security.AccessController;
import java.lang.reflect.Field;
import java.security.PrivilegedAction;
import groovy.lang.GroovyObject;
import groovy.lang.Closure;
import java.math.BigInteger;
import java.util.logging.Logger;

public class DefaultGroovyMethods extends DefaultGroovyMethodsSupport
{
    private static final Logger LOG;
    private static final Integer ONE;
    private static final BigInteger BI_INT_MAX;
    private static final BigInteger BI_INT_MIN;
    private static final BigInteger BI_LONG_MAX;
    private static final BigInteger BI_LONG_MIN;
    public static final Class[] additionals;
    private static int charBufferSize;
    private static int expectedLineLength;
    private static int EOF;
    static String lineSeparator;
    
    public static boolean is(final Object self, final Object other) {
        return self == other;
    }
    
    public static Object identity(final Object self, final Closure closure) {
        return with(self, closure);
    }
    
    public static Object with(final Object self, final Closure closure) {
        final Closure clonedClosure = (Closure)closure.clone();
        clonedClosure.setResolveStrategy(1);
        clonedClosure.setDelegate(self);
        return clonedClosure.call(self);
    }
    
    public static Object getAt(final Object self, final String property) {
        return InvokerHelper.getProperty(self, property);
    }
    
    public static void putAt(final Object self, final String property, final Object newValue) {
        InvokerHelper.setProperty(self, property, newValue);
    }
    
    public static String dump(final Object self) {
        if (self == null) {
            return "null";
        }
        final StringBuilder buffer = new StringBuilder("<");
        Class klass = self.getClass();
        buffer.append(klass.getName());
        buffer.append("@");
        buffer.append(Integer.toHexString(self.hashCode()));
        final boolean groovyObject = self instanceof GroovyObject;
        while (klass != null) {
            for (final Field field : klass.getDeclaredFields()) {
                if ((field.getModifiers() & 0x8) == 0x0) {
                    if (!groovyObject || !field.getName().equals("metaClass")) {
                        AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                            public Object run() {
                                field.setAccessible(true);
                                return null;
                            }
                        });
                        buffer.append(" ");
                        buffer.append(field.getName());
                        buffer.append("=");
                        try {
                            buffer.append(InvokerHelper.toString(field.get(self)));
                        }
                        catch (Exception e) {
                            buffer.append(e);
                        }
                    }
                }
            }
            klass = klass.getSuperclass();
        }
        buffer.append(">");
        return buffer.toString();
    }
    
    public static List<PropertyValue> getMetaPropertyValues(final Object self) {
        final MetaClass metaClass = InvokerHelper.getMetaClass(self);
        final List<MetaProperty> mps = metaClass.getProperties();
        final List<PropertyValue> props = new ArrayList<PropertyValue>(mps.size());
        for (final MetaProperty mp : mps) {
            props.add(new PropertyValue(self, mp));
        }
        return props;
    }
    
    public static Map getProperties(final Object self) {
        final List<PropertyValue> metaProps = getMetaPropertyValues(self);
        final Map<String, Object> props = new LinkedHashMap<String, Object>(metaProps.size());
        for (final PropertyValue mp : metaProps) {
            try {
                props.put(mp.getName(), mp.getValue());
            }
            catch (Exception e) {
                DefaultGroovyMethods.LOG.throwing(self.getClass().getName(), "getProperty(" + mp.getName() + ")", e);
            }
        }
        return props;
    }
    
    public static Object use(final Object self, final Class categoryClass, final Closure closure) {
        return GroovyCategorySupport.use(categoryClass, closure);
    }
    
    public static void mixin(final MetaClass self, final List<Class> categoryClasses) {
        MixinInMetaClass.mixinClassesToMetaClass(self, categoryClasses);
    }
    
    public static void mixin(final Class self, final List<Class> categoryClasses) {
        mixin(getMetaClass(self), categoryClasses);
    }
    
    public static void mixin(final Class self, final Class categoryClass) {
        mixin(getMetaClass(self), (List<Class>)Collections.singletonList(categoryClass));
    }
    
    public static void mixin(final Class self, final Class[] categoryClass) {
        mixin(getMetaClass(self), (List<Class>)Arrays.asList((Class[])categoryClass));
    }
    
    public static void mixin(final MetaClass self, final Class categoryClass) {
        mixin(self, (List<Class>)Collections.singletonList(categoryClass));
    }
    
    public static void mixin(final MetaClass self, final Class[] categoryClass) {
        mixin(self, (List<Class>)Arrays.asList((Class[])categoryClass));
    }
    
    public static Object use(final Object self, final List<Class> categoryClassList, final Closure closure) {
        return GroovyCategorySupport.use(categoryClassList, closure);
    }
    
    public static void addShutdownHook(final Object self, final Closure closure) {
        Runtime.getRuntime().addShutdownHook(new Thread(closure));
    }
    
    public static Object use(final Object self, final Object[] array) {
        if (array.length < 2) {
            throw new IllegalArgumentException("Expecting at least 2 arguments, a category class and a Closure");
        }
        Closure closure;
        try {
            closure = (Closure)array[array.length - 1];
        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException("Expecting a Closure to be the last argument");
        }
        final List<Class> list = new ArrayList<Class>(array.length - 1);
        for (int i = 0; i < array.length - 1; ++i) {
            Class categoryClass;
            try {
                categoryClass = (Class)array[i];
            }
            catch (ClassCastException e2) {
                throw new IllegalArgumentException("Expecting a Category Class for argument " + i);
            }
            list.add(categoryClass);
        }
        return GroovyCategorySupport.use(list, closure);
    }
    
    public static void print(final Object self, final Object value) {
        if (self instanceof Writer) {
            try {
                ((Writer)self).write(InvokerHelper.toString(value));
            }
            catch (IOException e) {}
        }
        else {
            System.out.print(InvokerHelper.toString(value));
        }
    }
    
    public static void print(final PrintWriter self, final Object value) {
        self.print(InvokerHelper.toString(value));
    }
    
    public static void print(final PrintStream self, final Object value) {
        self.print(InvokerHelper.toString(value));
    }
    
    public static void print(final Closure self, final Object value) {
        final Object owner = getClosureOwner(self);
        InvokerHelper.invokeMethod(owner, "print", new Object[] { value });
    }
    
    public static void println(final Object self) {
        if (self instanceof Writer) {
            final PrintWriter pw = new GroovyPrintWriter((Writer)self);
            pw.println();
        }
        else {
            System.out.println();
        }
    }
    
    public static void println(final Closure self) {
        final Object owner = getClosureOwner(self);
        InvokerHelper.invokeMethod(owner, "println", new Object[0]);
    }
    
    private static Object getClosureOwner(final Closure cls) {
        Object owner;
        for (owner = cls.getOwner(); owner instanceof GeneratedClosure; owner = ((Closure)owner).getOwner()) {}
        return owner;
    }
    
    public static void println(final Object self, final Object value) {
        if (self instanceof Writer) {
            final PrintWriter pw = new GroovyPrintWriter((Writer)self);
            pw.println(value);
        }
        else {
            System.out.println(InvokerHelper.toString(value));
        }
    }
    
    public static void println(final PrintWriter self, final Object value) {
        self.println(InvokerHelper.toString(value));
    }
    
    public static void println(final PrintStream self, final Object value) {
        self.println(InvokerHelper.toString(value));
    }
    
    public static void println(final Closure self, final Object value) {
        final Object owner = getClosureOwner(self);
        InvokerHelper.invokeMethod(owner, "println", new Object[] { value });
    }
    
    public static void printf(final Object self, final String format, final Object[] values) {
        if (self instanceof PrintStream) {
            ((PrintStream)self).printf(format, values);
        }
        else {
            System.out.printf(format, values);
        }
    }
    
    public static String sprintf(final Object self, final String format, final Object[] values) {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        final PrintStream out = new PrintStream(outputStream);
        out.printf(format, values);
        return outputStream.toString();
    }
    
    public static void printf(final Object self, final String format, final Object arg) {
        if (self instanceof PrintStream) {
            printf((PrintStream)self, format, arg);
        }
        else if (self instanceof Writer) {
            printf((Writer)self, format, arg);
        }
        else {
            printf(System.out, format, arg);
        }
    }
    
    private static void printf(final PrintStream self, final String format, final Object arg) {
        self.print(sprintf(self, format, arg));
    }
    
    private static void printf(final Writer self, final String format, final Object arg) {
        try {
            self.write(sprintf(self, format, arg));
        }
        catch (IOException e) {
            printf(System.out, format, arg);
        }
    }
    
    public static String sprintf(final Object self, final String format, final Object arg) {
        if (arg instanceof Object[]) {
            return sprintf(self, format, (Object[])arg);
        }
        if (arg instanceof List) {
            return sprintf(self, format, ((List)arg).toArray());
        }
        if (!arg.getClass().isArray()) {
            final Object[] o = (Object[])Array.newInstance(arg.getClass(), 1);
            o[0] = arg;
            return sprintf(self, format, o);
        }
        final String elemType = arg.getClass().getName();
        Object[] ans;
        if (elemType.equals("[I")) {
            final int[] ia = (int[])arg;
            ans = new Integer[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                ans[i] = ia[i];
            }
        }
        else if (elemType.equals("[C")) {
            final char[] ca = (char[])arg;
            ans = new Character[ca.length];
            for (int i = 0; i < ca.length; ++i) {
                ans[i] = ca[i];
            }
        }
        else if (elemType.equals("[Z")) {
            final boolean[] ba = (boolean[])arg;
            ans = new Boolean[ba.length];
            for (int i = 0; i < ba.length; ++i) {
                ans[i] = ba[i];
            }
        }
        else if (elemType.equals("[B")) {
            final byte[] ba2 = (byte[])arg;
            ans = new Byte[ba2.length];
            for (int i = 0; i < ba2.length; ++i) {
                ans[i] = ba2[i];
            }
        }
        else if (elemType.equals("[S")) {
            final short[] sa = (short[])arg;
            ans = new Short[sa.length];
            for (int i = 0; i < sa.length; ++i) {
                ans[i] = sa[i];
            }
        }
        else if (elemType.equals("[F")) {
            final float[] fa = (float[])arg;
            ans = new Float[fa.length];
            for (int i = 0; i < fa.length; ++i) {
                ans[i] = fa[i];
            }
        }
        else if (elemType.equals("[J")) {
            final long[] la = (long[])arg;
            ans = new Long[la.length];
            for (int i = 0; i < la.length; ++i) {
                ans[i] = la[i];
            }
        }
        else {
            if (!elemType.equals("[D")) {
                throw new RuntimeException("sprintf(String," + arg + ")");
            }
            final double[] da = (double[])arg;
            ans = new Double[da.length];
            for (int i = 0; i < da.length; ++i) {
                ans[i] = da[i];
            }
        }
        return sprintf(self, format, ans);
    }
    
    public static String inspect(final Object self) {
        return InvokerHelper.inspect(self);
    }
    
    public static void print(final Object self, PrintWriter out) {
        if (out == null) {
            out = new PrintWriter(System.out);
        }
        out.print(InvokerHelper.toString(self));
    }
    
    public static void println(final Object self, PrintWriter out) {
        if (out == null) {
            out = new PrintWriter(System.out);
        }
        out.println(InvokerHelper.toString(self));
    }
    
    public static Object invokeMethod(final Object object, final String method, final Object arguments) {
        return InvokerHelper.invokeMethod(object, method, arguments);
    }
    
    public static boolean isCase(final Object caseValue, final Object switchValue) {
        if (caseValue.getClass().isArray()) {
            return isCase(DefaultTypeTransformation.asCollection(caseValue), switchValue);
        }
        return caseValue.equals(switchValue);
    }
    
    public static boolean isCase(final String caseValue, final Object switchValue) {
        if (switchValue == null) {
            return caseValue == null;
        }
        return caseValue.equals(switchValue.toString());
    }
    
    public static boolean isCase(final GString caseValue, final Object switchValue) {
        return isCase(caseValue.toString(), switchValue);
    }
    
    public static boolean isCase(final Class caseValue, final Object switchValue) {
        if (switchValue instanceof Class) {
            final Class val = (Class)switchValue;
            return caseValue.isAssignableFrom(val);
        }
        return caseValue.isInstance(switchValue);
    }
    
    public static boolean isCase(final Collection caseValue, final Object switchValue) {
        return caseValue.contains(switchValue);
    }
    
    public static boolean isCase(final Map caseValue, final Object switchValue) {
        return DefaultTypeTransformation.castToBoolean(caseValue.get(switchValue));
    }
    
    public static boolean isCase(final Pattern caseValue, final Object switchValue) {
        if (switchValue == null) {
            return caseValue == null;
        }
        final Matcher matcher = caseValue.matcher(switchValue.toString());
        if (matcher.matches()) {
            RegexSupport.setLastMatcher(matcher);
            return true;
        }
        return false;
    }
    
    public static boolean isCase(final Number caseValue, final Number switchValue) {
        return NumberMath.compareTo(caseValue, switchValue) == 0;
    }
    
    public static <T> Iterator<T> unique(final Iterator<T> self) {
        return (Iterator<T>)toList((Collection<Object>)unique((Collection<T>)toList(self))).listIterator();
    }
    
    public static <T> Collection<T> unique(final Collection<T> self) {
        if (self instanceof Set) {
            return self;
        }
        final List<T> answer = new ArrayList<T>();
        final NumberAwareComparator<T> numberAwareComparator = new NumberAwareComparator<T>();
        for (final T t : self) {
            boolean duplicated = false;
            for (final T t2 : answer) {
                if (numberAwareComparator.compare(t, t2) == 0) {
                    duplicated = true;
                    break;
                }
            }
            if (!duplicated) {
                answer.add(t);
            }
        }
        self.clear();
        self.addAll((Collection<? extends T>)answer);
        return self;
    }
    
    public static int numberAwareCompareTo(final Comparable self, final Comparable other) {
        final NumberAwareComparator<Comparable> numberAwareComparator = new NumberAwareComparator<Comparable>();
        return numberAwareComparator.compare(self, other);
    }
    
    public static <T> Iterator<T> unique(final Iterator<T> self, final Closure closure) {
        return (Iterator<T>)toList((Collection<Object>)unique((Collection<T>)toList(self), closure)).listIterator();
    }
    
    public static <T> Collection<T> unique(final Collection<T> self, final Closure closure) {
        final int params = closure.getMaximumNumberOfParameters();
        if (params == 1) {
            unique(self, new OrderBy<T>(closure));
        }
        else {
            unique(self, new ClosureComparator<T>(closure));
        }
        return self;
    }
    
    public static <T> Iterator<T> unique(final Iterator<T> self, final Comparator<T> comparator) {
        return (Iterator<T>)toList((Collection<Object>)unique((Collection<T>)toList(self), (Comparator<T>)comparator)).listIterator();
    }
    
    public static <T> Collection<T> unique(final Collection<T> self, final Comparator<T> comparator) {
        final List<T> answer = new ArrayList<T>();
        for (final T t : self) {
            boolean duplicated = false;
            for (final T t2 : answer) {
                if (comparator.compare(t, t2) == 0) {
                    duplicated = true;
                    break;
                }
            }
            if (!duplicated) {
                answer.add(t);
            }
        }
        self.clear();
        self.addAll((Collection<? extends T>)answer);
        return self;
    }
    
    public static <T> T each(final T self, final Closure closure) {
        each(InvokerHelper.asIterator(self), closure);
        return self;
    }
    
    public static Object eachWithIndex(final Object self, final Closure closure) {
        int counter = 0;
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            closure.call(new Object[] { iter.next(), counter++ });
        }
        return self;
    }
    
    private static <T> Iterator<T> each(final Iterator<T> iter, final Closure closure) {
        while (iter.hasNext()) {
            closure.call(iter.next());
        }
        return iter;
    }
    
    public static <K, V> Map<K, V> each(final Map<K, V> self, final Closure closure) {
        for (final Map.Entry entry : self.entrySet()) {
            callClosureForMapEntry(closure, entry);
        }
        return self;
    }
    
    public static <K, V> Map<K, V> reverseEach(final Map<K, V> self, final Closure closure) {
        final Iterator<Map.Entry<K, V>> entries = reverse(self.entrySet().iterator());
        while (entries.hasNext()) {
            callClosureForMapEntry(closure, entries.next());
        }
        return self;
    }
    
    public static <K, V> Map<K, V> eachWithIndex(final Map<K, V> self, final Closure closure) {
        int counter = 0;
        for (final Map.Entry entry : self.entrySet()) {
            callClosureForMapEntryAndCounter(closure, entry, counter++);
        }
        return self;
    }
    
    public static <T> List<T> reverseEach(final List<T> self, final Closure closure) {
        each((Iterator<T>)new ReverseListIterator<T>(self), closure);
        return self;
    }
    
    public static <T> T[] reverseEach(final T[] self, final Closure closure) {
        each((Iterator<T>)new ReverseListIterator<T>(Arrays.asList(self)), closure);
        return self;
    }
    
    public static <T> T[] reverse(final T[] self) {
        return (T[])toList(new ReverseListIterator<T>(Arrays.asList(self))).toArray();
    }
    
    public static boolean every(final Object self, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            if (!DefaultTypeTransformation.castToBoolean(closure.call(iter.next()))) {
                return false;
            }
        }
        return true;
    }
    
    public static <K, V> boolean every(final Map<K, V> self, final Closure closure) {
        for (final Map.Entry entry : self.entrySet()) {
            if (!DefaultTypeTransformation.castToBoolean(callClosureForMapEntry(closure, entry))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean every(final Object self) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            if (!DefaultTypeTransformation.castToBoolean(iter.next())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean any(final Object self, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            if (DefaultTypeTransformation.castToBoolean(closure.call(iter.next()))) {
                return true;
            }
        }
        return false;
    }
    
    public static <K, V> boolean any(final Map<K, V> self, final Closure closure) {
        for (final Map.Entry entry : self.entrySet()) {
            if (DefaultTypeTransformation.castToBoolean(callClosureForMapEntry(closure, entry))) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean any(final Object self) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            if (DefaultTypeTransformation.castToBoolean(iter.next())) {
                return true;
            }
        }
        return false;
    }
    
    public static Collection grep(final Object self, final Object filter) {
        final Collection answer = DefaultGroovyMethodsSupport.createSimilarOrDefaultCollection(self);
        final MetaClass metaClass = InvokerHelper.getMetaClass(filter);
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            final Object object = iter.next();
            if (DefaultTypeTransformation.castToBoolean(metaClass.invokeMethod(filter, "isCase", object))) {
                answer.add(object);
            }
        }
        return answer;
    }
    
    public static Number count(final Iterator self, final Object value) {
        long answer = 0L;
        while (self.hasNext()) {
            if (DefaultTypeTransformation.compareEqual(self.next(), value)) {
                ++answer;
            }
        }
        if (answer <= 2147483647L) {
            return new Long(answer).intValue();
        }
        return answer;
    }
    
    public static Number count(final Collection self, final Object value) {
        return count(self.iterator(), value);
    }
    
    public static Number count(final Object[] self, final Object value) {
        return count(Arrays.asList(self), value);
    }
    
    public static Number count(final int[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final long[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final short[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final char[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final boolean[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final double[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final float[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static Number count(final byte[] self, final Object value) {
        return count(InvokerHelper.asIterator(self), value);
    }
    
    public static <T> List<T> toList(final Collection<T> self) {
        final List<T> answer = new ArrayList<T>(self.size());
        answer.addAll((Collection<? extends T>)self);
        return answer;
    }
    
    public static <T> List<T> toList(final Iterator<T> self) {
        final List<T> answer = new ArrayList<T>();
        while (self.hasNext()) {
            answer.add(self.next());
        }
        return answer;
    }
    
    public static <T> List<T> toList(final Enumeration<T> self) {
        final List<T> answer = new ArrayList<T>();
        while (self.hasMoreElements()) {
            answer.add(self.nextElement());
        }
        return answer;
    }
    
    public static List collect(final Object self, final Closure closure) {
        return (List)collect(self, new ArrayList(), closure);
    }
    
    public static Collection collect(final Object self, final Collection collection, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            collection.add(closure.call(iter.next()));
        }
        return collection;
    }
    
    public static List collect(final Collection self, final Closure closure) {
        return (List)collect(self, new ArrayList(self.size()), closure);
    }
    
    public static Collection collect(final Collection self, final Collection collection, final Closure closure) {
        final Iterator iter = self.iterator();
        while (iter.hasNext()) {
            collection.add(closure.call(iter.next()));
            if (closure.getDirective() == 1) {
                break;
            }
        }
        return collection;
    }
    
    public static List collectAll(final Collection self, final Closure closure) {
        return (List)collectAll(self, new ArrayList(self.size()), closure);
    }
    
    public static Collection collectAll(final Collection self, final Collection collection, final Closure closure) {
        for (final Object o : self) {
            if (o instanceof Collection) {
                final Collection c = (Collection)o;
                collection.add(collectAll(c, DefaultGroovyMethodsSupport.createSimilarCollection((Collection<Object>)collection, c.size()), closure));
            }
            else {
                collection.add(closure.call(o));
            }
            if (closure.getDirective() == 1) {
                break;
            }
        }
        return collection;
    }
    
    public static Collection collect(final Map self, final Collection collection, final Closure closure) {
        for (final Object entry : self.entrySet()) {
            collection.add(callClosureForMapEntry(closure, (Map.Entry)entry));
        }
        return collection;
    }
    
    public static List collect(final Map self, final Closure closure) {
        return (List)collect(self, new ArrayList(self.size()), closure);
    }
    
    public static Object find(final Object self, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            final Object value = iter.next();
            if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                return value;
            }
        }
        return null;
    }
    
    public static Object findResult(final Object self, final Object defaultResult, final Closure closure) {
        final Object result = findResult(self, closure);
        if (result == null) {
            return defaultResult;
        }
        return result;
    }
    
    public static Object findResult(final Object self, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            final Object value = iter.next();
            final Object result = closure.call(value);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    
    public static <T> T find(final Collection<T> self, final Closure closure) {
        for (final T value : self) {
            if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                return value;
            }
        }
        return null;
    }
    
    public static <T> Object findResult(final Collection<T> self, final Object defaultResult, final Closure closure) {
        final Object result = findResult(self, closure);
        if (result == null) {
            return defaultResult;
        }
        return result;
    }
    
    public static <T> Object findResult(final Collection<T> self, final Closure closure) {
        for (final T value : self) {
            final Object result = closure.call(value);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    
    public static <K, V> Map.Entry<K, V> find(final Map<K, V> self, final Closure closure) {
        for (final Map.Entry<K, V> entry : self.entrySet()) {
            if (DefaultTypeTransformation.castToBoolean(callClosureForMapEntry(closure, entry))) {
                return entry;
            }
        }
        return null;
    }
    
    public static <K, V> Object findResult(final Map<K, V> self, final Object defaultResult, final Closure closure) {
        final Object result = findResult(self, closure);
        if (result == null) {
            return defaultResult;
        }
        return result;
    }
    
    public static <K, V> Object findResult(final Map<K, V> self, final Closure closure) {
        for (final Map.Entry<K, V> entry : self.entrySet()) {
            final Object result = callClosureForMapEntry(closure, entry);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
    
    public static <T> Collection<T> findAll(final Collection<T> self, final Closure closure) {
        final Collection<T> answer = DefaultGroovyMethodsSupport.createSimilarCollection(self);
        final Iterator<T> iter = self.iterator();
        return findAll(closure, answer, iter);
    }
    
    public static Collection findAll(final Object self, final Closure closure) {
        final List answer = new ArrayList();
        final Iterator iter = InvokerHelper.asIterator(self);
        return findAll(closure, answer, (Iterator<Object>)iter);
    }
    
    private static <T> Collection<T> findAll(final Closure closure, final Collection<T> answer, final Iterator<T> iter) {
        while (iter.hasNext()) {
            final T value = iter.next();
            if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                answer.add(value);
            }
        }
        return answer;
    }
    
    public static boolean containsAll(final Collection self, final Object[] items) {
        return self.containsAll(Arrays.asList(items));
    }
    
    public static <T> boolean removeAll(final Collection<T> self, final T[] items) {
        return self.removeAll(Arrays.asList(items));
    }
    
    public static <T> boolean retainAll(final Collection<T> self, final T[] items) {
        return self.retainAll(Arrays.asList(items));
    }
    
    public static boolean retainAll(final Collection self, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        boolean result = false;
        while (iter.hasNext()) {
            final Object value = iter.next();
            if (!DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                iter.remove();
                result = true;
            }
        }
        return result;
    }
    
    public static boolean removeAll(final Collection self, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        boolean result = false;
        while (iter.hasNext()) {
            final Object value = iter.next();
            if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                iter.remove();
                result = true;
            }
        }
        return result;
    }
    
    public static <T> boolean addAll(final Collection<T> self, final T[] items) {
        return self.addAll((Collection<? extends T>)Arrays.asList(items));
    }
    
    public static <T> boolean addAll(final List<T> self, final int index, final T[] items) {
        return self.addAll(index, (Collection<? extends T>)Arrays.asList(items));
    }
    
    public static Collection split(final Object self, final Closure closure) {
        final List accept = new ArrayList();
        final List reject = new ArrayList();
        return split(closure, accept, reject, InvokerHelper.asIterator(self));
    }
    
    public static <T> Collection<Collection<T>> split(final Collection<T> self, final Closure closure) {
        final Collection<T> accept = DefaultGroovyMethodsSupport.createSimilarCollection(self);
        final Collection<T> reject = DefaultGroovyMethodsSupport.createSimilarCollection(self);
        final Iterator<T> iter = self.iterator();
        return split(closure, accept, reject, iter);
    }
    
    private static <T> Collection<Collection<T>> split(final Closure closure, final Collection<T> accept, final Collection<T> reject, final Iterator<T> iter) {
        final List<Collection<T>> answer = new ArrayList<Collection<T>>();
        while (iter.hasNext()) {
            final T value = iter.next();
            if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                accept.add(value);
            }
            else {
                reject.add(value);
            }
        }
        answer.add(accept);
        answer.add(reject);
        return answer;
    }
    
    public static List combinations(final Collection self) {
        return GroovyCollections.combinations(self);
    }
    
    public static <T> Set<List<T>> subsequences(final List<T> self) {
        return GroovyCollections.subsequences(self);
    }
    
    public static <T> Set<List<T>> permutations(final List<T> self) {
        final Set<List<T>> ans = new HashSet<List<T>>();
        final PermutationGenerator<T> generator = new PermutationGenerator<T>(self);
        while (generator.hasNext()) {
            ans.add(generator.next());
        }
        return ans;
    }
    
    public static <T> Iterator<List<T>> eachPermutation(final Collection<T> self, final Closure closure) {
        final Iterator<List<T>> generator = new PermutationGenerator<T>(self);
        while (generator.hasNext()) {
            closure.call(generator.next());
        }
        return generator;
    }
    
    public static List transpose(final List self) {
        return GroovyCollections.transpose(self);
    }
    
    public static <K, V> Map<K, V> findAll(final Map<K, V> self, final Closure closure) {
        final Map<K, V> answer = DefaultGroovyMethodsSupport.createSimilarMap(self);
        for (final Map.Entry<K, V> entry : self.entrySet()) {
            if (DefaultTypeTransformation.castToBoolean(callClosureForMapEntry(closure, entry))) {
                answer.put(entry.getKey(), entry.getValue());
            }
        }
        return answer;
    }
    
    public static <T> Map<Object, List<T>> groupBy(final Collection<T> self, final Closure closure) {
        final Map<Object, List<T>> answer = new LinkedHashMap<Object, List<T>>();
        for (final T element : self) {
            final Object value = closure.call(element);
            groupAnswer(answer, element, value);
        }
        return answer;
    }
    
    public static <K, V> Map<Object, List<Map.Entry<K, V>>> groupEntriesBy(final Map<K, V> self, final Closure closure) {
        final Map<Object, List<Map.Entry<K, V>>> answer = new LinkedHashMap<Object, List<Map.Entry<K, V>>>();
        for (final Map.Entry<K, V> entry : self.entrySet()) {
            final Object value = callClosureForMapEntry(closure, entry);
            groupAnswer(answer, entry, value);
        }
        return answer;
    }
    
    public static <K, V> Map<Object, Map<K, V>> groupBy(final Map<K, V> self, final Closure closure) {
        final Map<Object, List<Map.Entry<K, V>>> initial = (Map<Object, List<Map.Entry<K, V>>>)groupEntriesBy((Map<Object, Object>)self, closure);
        final Map<Object, Map<K, V>> answer = new LinkedHashMap<Object, Map<K, V>>();
        for (final Map.Entry<Object, List<Map.Entry<K, V>>> outer : initial.entrySet()) {
            final Object key = outer.getKey();
            final List<Map.Entry<K, V>> entries = outer.getValue();
            final Map<K, V> target = DefaultGroovyMethodsSupport.createSimilarMap(self);
            putAll(target, entries);
            answer.put(key, target);
        }
        return answer;
    }
    
    protected static <T> void groupAnswer(final Map<Object, List<T>> answer, final T element, final Object value) {
        if (answer.containsKey(value)) {
            answer.get(value).add(element);
        }
        else {
            final List<T> groupedElements = new ArrayList<T>();
            groupedElements.add(element);
            answer.put(value, groupedElements);
        }
    }
    
    protected static Object callClosureForMapEntry(final Closure closure, final Map.Entry entry) {
        if (closure.getMaximumNumberOfParameters() == 2) {
            return closure.call(new Object[] { entry.getKey(), entry.getValue() });
        }
        return closure.call(entry);
    }
    
    protected static Object callClosureForLine(final Closure closure, final String line, final int counter) {
        if (closure.getMaximumNumberOfParameters() == 2) {
            return closure.call(new Object[] { line, counter });
        }
        return closure.call(line);
    }
    
    protected static Object callClosureForMapEntryAndCounter(final Closure closure, final Map.Entry entry, final int counter) {
        if (closure.getMaximumNumberOfParameters() == 3) {
            return closure.call(new Object[] { entry.getKey(), entry.getValue(), counter });
        }
        if (closure.getMaximumNumberOfParameters() == 2) {
            return closure.call(new Object[] { entry, counter });
        }
        return closure.call(entry);
    }
    
    public static Object inject(final Collection self, final Object value, final Closure closure) {
        return inject(self.iterator(), value, closure);
    }
    
    public static Object inject(final Iterator self, Object value, final Closure closure) {
        final Object[] params = new Object[2];
        while (self.hasNext()) {
            final Object item = self.next();
            params[0] = value;
            params[1] = item;
            value = closure.call(params);
        }
        return value;
    }
    
    public static Object inject(final Object self, final Object value, final Closure closure) {
        final Iterator iter = InvokerHelper.asIterator(self);
        return inject(iter, value, closure);
    }
    
    public static Object inject(final Object[] self, final Object initialValue, final Closure closure) {
        final Object[] params = new Object[2];
        Object value = initialValue;
        for (final Object next : self) {
            params[0] = value;
            params[1] = next;
            value = closure.call(params);
        }
        return value;
    }
    
    public static Object sum(final Collection self) {
        return sum(self, null, true);
    }
    
    public static Object sum(final Object[] self) {
        return sum(toList(self), null, true);
    }
    
    public static Object sum(final Iterator<Object> self) {
        return sum(toList(self), null, true);
    }
    
    public static Object sum(final Collection self, final Object initialValue) {
        return sum(self, initialValue, false);
    }
    
    public static Object sum(final Object[] self, final Object initialValue) {
        return sum(toList(self), initialValue, false);
    }
    
    public static Object sum(final Iterator<Object> self, final Object initialValue) {
        return sum(toList(self), initialValue, false);
    }
    
    private static Object sum(final Collection self, final Object initialValue, boolean first) {
        Object result = initialValue;
        final Object[] param = { null };
        for (final Object next : self) {
            param[0] = next;
            if (first) {
                result = param[0];
                first = false;
            }
            else {
                final MetaClass metaClass = InvokerHelper.getMetaClass(result);
                result = metaClass.invokeMethod(result, "plus", param);
            }
        }
        return result;
    }
    
    public static Object sum(final Collection self, final Closure closure) {
        return sum(self, null, closure, true);
    }
    
    public static Object sum(final Object[] self, final Closure closure) {
        return sum(toList(self), null, closure, true);
    }
    
    public static Object sum(final Iterator<Object> self, final Closure closure) {
        return sum(toList(self), null, closure, true);
    }
    
    public static Object sum(final Collection self, final Object initialValue, final Closure closure) {
        return sum(self, initialValue, closure, false);
    }
    
    public static Object sum(final Object[] self, final Object initialValue, final Closure closure) {
        return sum(toList(self), initialValue, closure, false);
    }
    
    public static Object sum(final Iterator<Object> self, final Object initialValue, final Closure closure) {
        return sum(toList(self), initialValue, closure, false);
    }
    
    private static Object sum(final Collection self, final Object initialValue, final Closure closure, boolean first) {
        Object result = initialValue;
        final Object[] closureParam = { null };
        final Object[] plusParam = { null };
        for (final Object next : self) {
            closureParam[0] = next;
            plusParam[0] = closure.call(closureParam);
            if (first) {
                result = plusParam[0];
                first = false;
            }
            else {
                final MetaClass metaClass = InvokerHelper.getMetaClass(result);
                result = metaClass.invokeMethod(result, "plus", plusParam);
            }
        }
        return result;
    }
    
    public static String join(final Iterator<Object> self, final String separator) {
        return join(toList(self), separator);
    }
    
    public static String join(final Collection self, String separator) {
        final StringBuilder buffer = new StringBuilder();
        boolean first = true;
        if (separator == null) {
            separator = "";
        }
        for (final Object value : self) {
            if (first) {
                first = false;
            }
            else {
                buffer.append(separator);
            }
            buffer.append(InvokerHelper.toString(value));
        }
        return buffer.toString();
    }
    
    public static String join(final Object[] self, String separator) {
        final StringBuilder buffer = new StringBuilder();
        boolean first = true;
        if (separator == null) {
            separator = "";
        }
        for (final Object next : self) {
            final String value = InvokerHelper.toString(next);
            if (first) {
                first = false;
            }
            else {
                buffer.append(separator);
            }
            buffer.append(value);
        }
        return buffer.toString();
    }
    
    public static <T> T min(final Collection<T> self) {
        return GroovyCollections.min(self);
    }
    
    public static <T> T min(final Iterator<T> self) {
        return min(toList(self));
    }
    
    public static <T> T min(final T[] self) {
        return min(toList(self));
    }
    
    public static <T> T min(final Collection<T> self, final Comparator<T> comparator) {
        T answer = null;
        for (final T value : self) {
            if (answer == null || comparator.compare(value, answer) < 0) {
                answer = value;
            }
        }
        return answer;
    }
    
    public static <T> T min(final Iterator<T> self, final Comparator<T> comparator) {
        return min(toList(self), comparator);
    }
    
    public static <T> T min(final T[] self, final Comparator<T> comparator) {
        return min(toList(self), comparator);
    }
    
    public static <T> T min(final Collection<T> self, final Closure closure) {
        final int params = closure.getMaximumNumberOfParameters();
        if (params != 1) {
            return min(self, new ClosureComparator<T>(closure));
        }
        T answer = null;
        Object answer_value = null;
        for (final T item : self) {
            final Object value = closure.call(item);
            if (answer == null || ScriptBytecodeAdapter.compareLessThan(value, answer_value)) {
                answer = item;
                answer_value = value;
            }
        }
        return answer;
    }
    
    public static <K, V> Map.Entry<K, V> min(final Map<K, V> self, final Closure closure) {
        return min(self.entrySet(), closure);
    }
    
    public static <K, V> Map.Entry<K, V> max(final Map<K, V> self, final Closure closure) {
        return max(self.entrySet(), closure);
    }
    
    public static <T> T min(final Iterator<T> self, final Closure closure) {
        return min(toList(self), closure);
    }
    
    public static <T> T min(final T[] self, final Closure closure) {
        return min(toList(self), closure);
    }
    
    public static <T> T max(final Collection<T> self) {
        return GroovyCollections.max(self);
    }
    
    public static <T> T max(final Iterator<T> self) {
        return max(toList(self));
    }
    
    public static <T> T max(final T[] self) {
        return max(toList(self));
    }
    
    public static <T> T max(final Collection<T> self, final Closure closure) {
        final int params = closure.getMaximumNumberOfParameters();
        if (params != 1) {
            return max(self, new ClosureComparator<T>(closure));
        }
        T answer = null;
        Object answerValue = null;
        for (final T item : self) {
            final Object value = closure.call(item);
            if (answer == null || ScriptBytecodeAdapter.compareLessThan(answerValue, value)) {
                answer = item;
                answerValue = value;
            }
        }
        return answer;
    }
    
    public static <T> T max(final Iterator<T> self, final Closure closure) {
        return max(toList(self), closure);
    }
    
    public static <T> T max(final T[] self, final Closure closure) {
        return max(toList(self), closure);
    }
    
    public static <T> T max(final Collection<T> self, final Comparator<T> comparator) {
        T answer = null;
        for (final T value : self) {
            if (answer == null || comparator.compare(value, answer) > 0) {
                answer = value;
            }
        }
        return answer;
    }
    
    public static <T> T max(final Iterator<T> self, final Comparator<T> comparator) {
        return max(toList(self), comparator);
    }
    
    public static <T> T max(final T[] self, final Comparator<T> comparator) {
        return max(toList(self), comparator);
    }
    
    public static int size(final Iterator self) {
        int count = 0;
        while (self.hasNext()) {
            self.next();
            ++count;
        }
        return count;
    }
    
    public static int size(final String text) {
        return text.length();
    }
    
    public static int size(final StringBuffer buffer) {
        return buffer.length();
    }
    
    public static long size(final File self) {
        return self.length();
    }
    
    public static long size(final Matcher self) {
        return getCount(self);
    }
    
    public static int size(final Object[] self) {
        return self.length;
    }
    
    public static CharSequence getAt(final CharSequence text, int index) {
        index = DefaultGroovyMethodsSupport.normaliseIndex(index, text.length());
        return text.subSequence(index, index + 1);
    }
    
    public static String getAt(final String text, int index) {
        index = DefaultGroovyMethodsSupport.normaliseIndex(index, text.length());
        return text.substring(index, index + 1);
    }
    
    public static CharSequence getAt(final CharSequence text, final Range range) {
        int from = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), text.length());
        int to = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getTo()), text.length());
        boolean reverse = range.isReverse();
        if (from > to) {
            final int tmp = from;
            from = to;
            to = tmp;
            reverse = !reverse;
        }
        final CharSequence sequence = text.subSequence(from, to + 1);
        return reverse ? reverse((String)sequence) : sequence;
    }
    
    public static CharSequence getAt(final CharSequence text, final IntRange range) {
        return getAt(text, (Range)range);
    }
    
    public static CharSequence getAt(final CharSequence text, final EmptyRange range) {
        return "";
    }
    
    public static String getAt(final String text, final IntRange range) {
        return getAt(text, (Range)range);
    }
    
    public static String getAt(final String text, final EmptyRange range) {
        return "";
    }
    
    public static String getAt(final String text, final Range range) {
        int from = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getFrom()), text.length());
        int to = DefaultGroovyMethodsSupport.normaliseIndex(DefaultTypeTransformation.intUnbox(range.getTo()), text.length());
        boolean reverse = range.isReverse();
        if (from > to) {
            final int tmp = to;
            to = from;
            from = tmp;
            reverse = !reverse;
        }
        String answer = text.substring(from, to + 1);
        if (reverse) {
            answer = reverse(answer);
        }
        return answer;
    }
    
    public static String reverse(final String self) {
        return new StringBuilder(self).reverse().toString();
    }
    
    public static String stripMargin(final String self) {
        return stripMargin(self, '|');
    }
    
    public static String stripMargin(final String self, final String marginChar) {
        if (marginChar == null || marginChar.length() == 0) {
            return stripMargin(self, '|');
        }
        return stripMargin(self, marginChar.charAt(0));
    }
    
    public static String stripMargin(final String self, final char marginChar) {
        if (self.length() == 0) {
            return self;
        }
        try {
            final StringBuilder builder = new StringBuilder();
            for (final String line : readLines(self)) {
                builder.append(stripMarginFromLine(line, marginChar));
                builder.append("\n");
            }
            if (!self.endsWith("\n")) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
        catch (IOException e) {
            return self;
        }
    }
    
    private static String stripMarginFromLine(final String line, final char marginChar) {
        int length;
        int index;
        for (length = line.length(), index = 0; index < length && line.charAt(index) <= ' '; ++index) {}
        return (index < length && line.charAt(index) == marginChar) ? line.substring(index + 1) : line;
    }
    
    public static String stripIndent(final String self) {
        if (self.length() == 0) {
            return self;
        }
        int runningCount = -1;
        try {
            for (final String line : readLines(self)) {
                if (isAllWhitespace(line)) {
                    continue;
                }
                if (runningCount == -1) {
                    runningCount = line.length();
                }
                runningCount = findMinimumLeadingSpaces(line, runningCount);
                if (runningCount == 0) {
                    break;
                }
            }
        }
        catch (IOException ex) {}
        return stripIndent(self, (runningCount == -1) ? 0 : runningCount);
    }
    
    public static boolean isAllWhitespace(final String self) {
        for (int i = 0; i < self.length(); ++i) {
            if (!Character.isWhitespace(self.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private static int findMinimumLeadingSpaces(final String line, final int count) {
        int length;
        int index;
        for (length = line.length(), index = 0; index < length && index < count && Character.isWhitespace(line.charAt(index)); ++index) {}
        return index;
    }
    
    public static String stripIndent(final String self, final int numChars) {
        if (self.length() == 0 || numChars <= 0) {
            return self;
        }
        try {
            final StringBuilder builder = new StringBuilder();
            for (final String line : readLines(self)) {
                if (!isAllWhitespace(line)) {
                    builder.append(stripIndentFromLine(line, numChars));
                }
                builder.append("\n");
            }
            if (!self.endsWith("\n")) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
        catch (IOException e) {
            return self;
        }
    }
    
    private static String stripIndentFromLine(final String line, final int numChars) {
        final int length = line.length();
        return (numChars <= length) ? line.substring(numChars) : "";
    }
    
    public static URL toURL(final String self) throws MalformedURLException {
        return new URL(self);
    }
    
    public static URI toURI(final String self) throws URISyntaxException {
        return new URI(self);
    }
    
    public static Pattern bitwiseNegate(final String self) {
        return Pattern.compile(self);
    }
    
    public static String replaceFirst(final String self, final Pattern pattern, final String replacement) {
        return pattern.matcher(self).replaceFirst(replacement);
    }
    
    public static String replaceAll(final String self, final Pattern pattern, final String replacement) {
        return pattern.matcher(self).replaceAll(replacement);
    }
    
    public static String tr(final String self, final String sourceSet, final String replacementSet) throws ClassNotFoundException {
        return (String)InvokerHelper.invokeStaticMethod("org.codehaus.groovy.util.StringUtil", "tr", new Object[] { self, sourceSet, replacementSet });
    }
    
    public static boolean matches(final String self, final Pattern pattern) {
        return pattern.matcher(self).matches();
    }
    
    public static String find(final String self, final String regex) {
        return find(self, Pattern.compile(regex));
    }
    
    public static String find(final String self, final Pattern pattern) {
        final Matcher matcher = pattern.matcher(self);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }
    
    public static String find(final String self, final String regex, final Closure closure) {
        return find(self, Pattern.compile(regex), closure);
    }
    
    public static String find(final String self, final Pattern pattern, final Closure closure) {
        final Matcher matcher = pattern.matcher(self);
        if (!matcher.find()) {
            return null;
        }
        if (hasGroup(matcher)) {
            final int count = matcher.groupCount();
            final List groups = new ArrayList(count);
            for (int i = 0; i <= count; ++i) {
                groups.add(matcher.group(i));
            }
            return InvokerHelper.toString(closure.call(groups));
        }
        return InvokerHelper.toString(closure.call(matcher.group(0)));
    }
    
    public static List findAll(final String self, final String regex) {
        return findAll(self, Pattern.compile(regex));
    }
    
    public static List findAll(final String self, final Pattern pattern) {
        final Matcher matcher = pattern.matcher(self);
        final List list = new ArrayList();
        final Iterator iter = iterator(matcher);
        while (iter.hasNext()) {
            if (hasGroup(matcher)) {
                list.add(iter.next().get(0));
            }
            else {
                list.add(iter.next());
            }
        }
        return list;
    }
    
    public static List findAll(final String self, final String regex, final Closure closure) {
        return findAll(self, Pattern.compile(regex), closure);
    }
    
    public static List findAll(final String self, final Pattern pattern, final Closure closure) {
        final Matcher matcher = pattern.matcher(self);
        return collect(matcher, closure);
    }
    
    public static String replaceAll(final String self, final String regex, final Closure closure) {
        return replaceAll(self, Pattern.compile(regex), closure);
    }
    
    private static String getReplacement(final Matcher matcher, final Closure closure) {
        if (!hasGroup(matcher)) {
            return InvokerHelper.toString(closure.call(matcher.group()));
        }
        final int count = matcher.groupCount();
        final List<String> groups = new ArrayList<String>();
        for (int i = 0; i <= count; ++i) {
            groups.add(matcher.group(i));
        }
        if (closure.getParameterTypes().length == 1 && closure.getParameterTypes()[0] == Object[].class) {
            return InvokerHelper.toString(closure.call(groups.toArray()));
        }
        return InvokerHelper.toString(closure.call(groups));
    }
    
    public static String replaceAll(final String self, final Pattern pattern, final Closure closure) {
        final Matcher matcher = pattern.matcher(self);
        if (matcher.find()) {
            final StringBuffer sb = new StringBuffer(self.length() + 16);
            do {
                final String replacement = getReplacement(matcher, closure);
                matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
            } while (matcher.find());
            matcher.appendTail(sb);
            return sb.toString();
        }
        return self;
    }
    
    private static String getPadding(final String padding, final int length) {
        if (padding.length() < length) {
            return multiply(padding, length / padding.length() + 1).substring(0, length);
        }
        return padding.substring(0, length);
    }
    
    public static String padLeft(final String self, final Number numberOfChars, final String padding) {
        final int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self;
        }
        return getPadding(padding, numChars - self.length()) + self;
    }
    
    public static String padLeft(final String self, final Number numberOfChars) {
        return padLeft(self, numberOfChars, " ");
    }
    
    public static String padRight(final String self, final Number numberOfChars, final String padding) {
        final int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self;
        }
        return self + getPadding(padding, numChars - self.length());
    }
    
    public static String padRight(final String self, final Number numberOfChars) {
        return padRight(self, numberOfChars, " ");
    }
    
    public static String center(final String self, final Number numberOfChars, final String padding) {
        final int numChars = numberOfChars.intValue();
        if (numChars <= self.length()) {
            return self;
        }
        final int charsToAdd = numChars - self.length();
        final String semiPad = (charsToAdd % 2 == 1) ? getPadding(padding, charsToAdd / 2 + 1) : getPadding(padding, charsToAdd / 2);
        if (charsToAdd % 2 == 0) {
            return semiPad + self + semiPad;
        }
        return semiPad.substring(0, charsToAdd / 2) + self + semiPad;
    }
    
    public static String center(final String self, final Number numberOfChars) {
        return center(self, numberOfChars, " ");
    }
    
    public static Object getAt(final Matcher matcher, int idx) {
        try {
            final int count = getCount(matcher);
            if (idx < -count || idx >= count) {
                throw new IndexOutOfBoundsException("index is out of range " + -count + ".." + (count - 1) + " (index = " + idx + ")");
            }
            idx = DefaultGroovyMethodsSupport.normaliseIndex(idx, count);
            final Iterator iter = iterator(matcher);
            Object result = null;
            for (int i = 0; i <= idx; ++i) {
                result = iter.next();
            }
            return result;
        }
        catch (IllegalStateException ex) {
            return null;
        }
    }
    
    public static void setIndex(final Matcher matcher, int idx) {
        final int count = getCount(matcher);
        if (idx < -count || idx >= count) {
            throw new IndexOutOfBoundsException("index is out of range " + -count + ".." + (count - 1) + " (index = " + idx + ")");
        }
        if (idx == 0) {
            matcher.reset();
        }
        else if (idx > 0) {
            matcher.reset();
            for (int i = 0; i < idx; ++i) {
                matcher.find();
            }
        }
        else if (idx < 0) {
            matcher.reset();
            idx += getCount(matcher);
            for (int i = 0; i < idx; ++i) {
                matcher.find();
            }
        }
    }
    
    public static int getCount(final Matcher matcher) {
        int counter = 0;
        matcher.reset();
        while (matcher.find()) {
            ++counter;
        }
        return counter;
    }
    
    public static boolean hasGroup(final Matcher matcher) {
        return matcher.groupCount() > 0;
    }
    
    public static <T> List<T> getAt(final List<T> self, final Range range) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.size(), range);
        List<T> answer = self.subList(info.from, info.to);
        if (info.reverse) {
            answer = reverse(answer);
        }
        return answer;
    }
    
    public static <T> List<T> getAt(final List<T> self, final EmptyRange range) {
        return new ArrayList<T>();
    }
    
    public static <T> List<T> getAt(final List<T> self, final Collection indices) {
        final List<T> answer = new ArrayList<T>(indices.size());
        for (final Object value : indices) {
            if (value instanceof Range) {
                answer.addAll((Collection<? extends T>)getAt(self, (Range)value));
            }
            else if (value instanceof List) {
                answer.addAll((Collection<? extends T>)getAt((List<Object>)self, (Collection)value));
            }
            else {
                final int idx = DefaultTypeTransformation.intUnbox(value);
                answer.add(getAt(self, idx));
            }
        }
        return answer;
    }
    
    public static <T> List<T> getAt(final T[] self, final Collection indices) {
        final List<T> answer = new ArrayList<T>(indices.size());
        for (final Object value : indices) {
            if (value instanceof Range) {
                answer.addAll((Collection<? extends T>)getAt(self, (Range)value));
            }
            else if (value instanceof Collection) {
                answer.addAll((Collection<? extends T>)getAt((Object[])self, (Collection)value));
            }
            else {
                final int idx = DefaultTypeTransformation.intUnbox(value);
                answer.add(getAtImpl(self, idx));
            }
        }
        return answer;
    }
    
    public static CharSequence getAt(final CharSequence self, final Collection indices) {
        final StringBuilder answer = new StringBuilder();
        for (final Object value : indices) {
            if (value instanceof Range) {
                answer.append(getAt(self, (Range)value));
            }
            else if (value instanceof Collection) {
                answer.append(getAt(self, (Collection)value));
            }
            else {
                final int idx = DefaultTypeTransformation.intUnbox(value);
                answer.append(getAt(self, idx));
            }
        }
        return answer.toString();
    }
    
    public static String getAt(final String self, final Collection indices) {
        return (String)getAt((CharSequence)self, indices);
    }
    
    public static List getAt(final Matcher self, final Collection indices) {
        final List result = new ArrayList();
        for (final Object value : indices) {
            if (value instanceof Range) {
                result.addAll(getAt(self, (Collection)value));
            }
            else {
                final int idx = DefaultTypeTransformation.intUnbox(value);
                result.add(getAt(self, idx));
            }
        }
        return result;
    }
    
    public static <K, V> Map<K, V> subMap(final Map<K, V> map, final Collection<K> keys) {
        final Map<K, V> answer = new LinkedHashMap<K, V>(keys.size());
        for (final K key : keys) {
            answer.put(key, map.get(key));
        }
        return answer;
    }
    
    public static <K, V> V get(final Map<K, V> map, final K key, final V defaultValue) {
        if (!map.containsKey(key)) {
            map.put(key, defaultValue);
        }
        return map.get(key);
    }
    
    public static <T> List<T> getAt(final T[] array, final Range range) {
        final List<T> list = Arrays.asList(array);
        return getAt(list, range);
    }
    
    public static <T> List<T> getAt(final T[] array, final IntRange range) {
        final List<T> list = Arrays.asList(array);
        return getAt(list, range);
    }
    
    public static <T> List<T> getAt(final T[] array, final EmptyRange range) {
        return new ArrayList<T>();
    }
    
    public static <T> List<T> getAt(final T[] array, final ObjectRange range) {
        final List<T> list = Arrays.asList(array);
        return getAt(list, range);
    }
    
    private static <T> T getAtImpl(final T[] array, final int idx) {
        return array[DefaultGroovyMethodsSupport.normaliseIndex(idx, array.length)];
    }
    
    public static <T> List<T> toList(final T[] array) {
        return new ArrayList<T>((Collection<? extends T>)Arrays.asList(array));
    }
    
    public static <T> T getAt(final List<T> self, final int idx) {
        final int size = self.size();
        final int i = DefaultGroovyMethodsSupport.normaliseIndex(idx, size);
        if (i < size) {
            return self.get(i);
        }
        return null;
    }
    
    public static <T> T getAt(final Iterator<T> self, final int idx) {
        if (idx >= 0) {
            int count = 0;
            while (self.hasNext()) {
                if (count == idx) {
                    return self.next();
                }
                ++count;
                self.next();
            }
            return null;
        }
        final List<T> list = toList(self);
        final int adjustedIndex = idx + list.size();
        if (adjustedIndex < 0 || adjustedIndex >= list.size()) {
            return null;
        }
        return list.get(adjustedIndex);
    }
    
    public static <T> void putAt(final List<T> self, int idx, final T value) {
        int size = self.size();
        idx = DefaultGroovyMethodsSupport.normaliseIndex(idx, size);
        if (idx < size) {
            self.set(idx, value);
        }
        else {
            while (size < idx) {
                self.add(size++, null);
            }
            self.add(idx, value);
        }
    }
    
    public static void putAt(final StringBuffer self, final IntRange range, final Object value) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.length(), range);
        self.replace(info.from, info.to, value.toString());
    }
    
    public static void putAt(final StringBuffer self, final EmptyRange range, final Object value) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.length(), range);
        self.replace(info.from, info.to, value.toString());
    }
    
    public static void putAt(final List self, final EmptyRange range, final Object value) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.size(), range);
        final List sublist = self.subList(info.from, info.to);
        sublist.clear();
        if (value instanceof Collection) {
            final Collection col = (Collection)value;
            if (col.isEmpty()) {
                return;
            }
            sublist.addAll(col);
        }
        else {
            sublist.add(value);
        }
    }
    
    public static void putAt(final List self, final EmptyRange range, final Collection value) {
        putAt(self, range, (Object)value);
    }
    
    private static <T> List<T> resizeListWithRangeAndGetSublist(final List<T> self, final IntRange range) {
        final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.size(), range);
        int size = self.size();
        if (info.to >= size) {
            while (size < info.to) {
                self.add(size++, null);
            }
        }
        final List<T> sublist = self.subList(info.from, info.to);
        sublist.clear();
        return sublist;
    }
    
    public static void putAt(final List self, final IntRange range, final Collection col) {
        final List sublist = resizeListWithRangeAndGetSublist((List<Object>)self, range);
        if (col.isEmpty()) {
            return;
        }
        sublist.addAll(col);
    }
    
    public static void putAt(final List self, final IntRange range, final Object value) {
        final List sublist = resizeListWithRangeAndGetSublist((List<Object>)self, range);
        sublist.add(value);
    }
    
    public static void putAt(final List self, final List splice, final List values) {
        if (splice.isEmpty()) {
            if (!values.isEmpty()) {
                throw new IllegalArgumentException("Trying to replace 0 elements with " + values.size() + " elements");
            }
        }
        else {
            final Object first = splice.iterator().next();
            if (!(first instanceof Integer)) {
                throw new IllegalArgumentException("Can only index a List with another List of Integers, not a List of " + first.getClass().getName());
            }
            if (values.size() != splice.size()) {
                throw new IllegalArgumentException("Trying to replace " + splice.size() + " elements with " + values.size() + " elements");
            }
            final Iterator<?> valuesIter = values.iterator();
            for (final Object index : splice) {
                putAt(self, (int)index, valuesIter.next());
            }
        }
    }
    
    public static void putAt(final List self, final List splice, final Object value) {
        if (splice.isEmpty()) {
            return;
        }
        final Object first = splice.iterator().next();
        if (first instanceof Integer) {
            for (final Object index : splice) {
                self.set((int)index, value);
            }
            return;
        }
        throw new IllegalArgumentException("Can only index a List with another List of Integers, not a List of " + first.getClass().getName());
    }
    
    protected static List getSubList(final List self, final List splice) {
        int right = 0;
        boolean emptyRange = false;
        int left;
        if (splice.size() == 2) {
            left = DefaultTypeTransformation.intUnbox(splice.get(0));
            right = DefaultTypeTransformation.intUnbox(splice.get(1));
        }
        else if (splice instanceof IntRange) {
            final IntRange range = (IntRange)splice;
            left = range.getFromInt();
            right = range.getToInt();
        }
        else {
            if (!(splice instanceof EmptyRange)) {
                throw new IllegalArgumentException("You must specify a list of 2 indexes to create a sub-list");
            }
            final RangeInfo info = DefaultGroovyMethodsSupport.subListBorders(self.size(), (EmptyRange)splice);
            left = info.from;
            emptyRange = true;
        }
        final int size = self.size();
        left = DefaultGroovyMethodsSupport.normaliseIndex(left, size);
        right = DefaultGroovyMethodsSupport.normaliseIndex(right, size);
        List sublist;
        if (!emptyRange) {
            sublist = self.subList(left, right + 1);
        }
        else {
            sublist = self.subList(left, left);
        }
        return sublist;
    }
    
    public static <K, V> V getAt(final Map<K, V> self, final K key) {
        return self.get(key);
    }
    
    public static <K, V> Map<K, V> plus(final Map<K, V> left, final Map<K, V> right) {
        final Map<K, V> map = DefaultGroovyMethodsSupport.cloneSimilarMap(left);
        map.putAll((Map<? extends K, ? extends V>)right);
        return map;
    }
    
    public static <K, V> V putAt(final Map<K, V> self, final K key, final V value) {
        self.put(key, value);
        return value;
    }
    
    public static List getAt(final Collection coll, final String property) {
        final List answer = new ArrayList(coll.size());
        for (final Object item : coll) {
            if (item == null) {
                continue;
            }
            Object value;
            try {
                value = InvokerHelper.getProperty(item, property);
            }
            catch (MissingPropertyExceptionNoStack mpe) {
                final String causeString = new MissingPropertyException(mpe.getProperty(), mpe.getType()).toString();
                throw new MissingPropertyException("Exception evaluating property '" + property + "' for " + coll.getClass().getName() + ", Reason: " + causeString);
            }
            answer.add(value);
        }
        return answer;
    }
    
    public static <K, V> Map<K, V> asImmutable(final Map<? extends K, ? extends V> self) {
        return Collections.unmodifiableMap(self);
    }
    
    public static <K, V> SortedMap<K, V> asImmutable(final SortedMap<K, ? extends V> self) {
        return Collections.unmodifiableSortedMap(self);
    }
    
    public static <T> List<T> asImmutable(final List<? extends T> self) {
        return Collections.unmodifiableList(self);
    }
    
    public static <T> Set<T> asImmutable(final Set<? extends T> self) {
        return Collections.unmodifiableSet(self);
    }
    
    public static <T> SortedSet<T> asImmutable(final SortedSet<T> self) {
        return Collections.unmodifiableSortedSet(self);
    }
    
    public static <T> Collection<T> asImmutable(final Collection<? extends T> self) {
        return Collections.unmodifiableCollection(self);
    }
    
    public static <K, V> Map<K, V> asSynchronized(final Map<K, V> self) {
        return Collections.synchronizedMap(self);
    }
    
    public static <K, V> SortedMap<K, V> asSynchronized(final SortedMap<K, V> self) {
        return Collections.synchronizedSortedMap(self);
    }
    
    public static <T> Collection<T> asSynchronized(final Collection<T> self) {
        return Collections.synchronizedCollection(self);
    }
    
    public static <T> List<T> asSynchronized(final List<T> self) {
        return Collections.synchronizedList(self);
    }
    
    public static <T> Set<T> asSynchronized(final Set<T> self) {
        return Collections.synchronizedSet(self);
    }
    
    public static <T> SortedSet<T> asSynchronized(final SortedSet<T> self) {
        return Collections.synchronizedSortedSet(self);
    }
    
    public static SpreadMap spread(final Map self) {
        return toSpreadMap(self);
    }
    
    public static SpreadMap toSpreadMap(final Map self) {
        if (self == null) {
            throw new GroovyRuntimeException("Fail to convert Map to SpreadMap, because it is null.");
        }
        return new SpreadMap(self);
    }
    
    public static SpreadMap toSpreadMap(final Object[] self) {
        if (self == null) {
            throw new GroovyRuntimeException("Fail to convert Object[] to SpreadMap, because it is null.");
        }
        if (self.length % 2 != 0) {
            throw new GroovyRuntimeException("Fail to convert Object[] to SpreadMap, because it's size is not even.");
        }
        return new SpreadMap(self);
    }
    
    public static <K, V> Map<K, V> withDefault(final Map<K, V> self, final Closure init) {
        return MapWithDefault.newInstance(self, init);
    }
    
    public static <T> List<T> sort(final Collection<T> self) {
        final List<T> answer = (List<T>)asList((Collection<Object>)self);
        Collections.sort(answer, new NumberAwareComparator<Object>());
        return answer;
    }
    
    public static <K, V> Map<K, V> sort(final Map<K, V> self, final Closure closure) {
        final Map<K, V> result = new LinkedHashMap<K, V>();
        final List<Map.Entry<K, V>> entries = asList(self.entrySet());
        sort(entries, closure);
        for (final Map.Entry<K, V> entry : entries) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    public static <K, V> Map<K, V> sort(final Map<K, V> self, final Comparator<K> comparator) {
        final Map<K, V> result = new TreeMap<K, V>(comparator);
        result.putAll((Map<? extends K, ? extends V>)self);
        return result;
    }
    
    public static <K, V> Map<K, V> sort(final Map<K, V> self) {
        return new TreeMap<K, V>((Map<? extends K, ? extends V>)self);
    }
    
    public static <T> T[] sort(final T[] self) {
        Arrays.sort(self, new NumberAwareComparator<Object>());
        return self;
    }
    
    public static <T> Iterator<T> sort(final Iterator<T> self) {
        return sort(toList(self)).listIterator();
    }
    
    public static <T> Iterator<T> sort(final Iterator<T> self, final Comparator<T> comparator) {
        return sort(toList(self), comparator).listIterator();
    }
    
    public static <T> List<T> sort(final Collection<T> self, final Comparator<T> comparator) {
        final List<T> list = asList(self);
        Collections.sort(list, comparator);
        return list;
    }
    
    public static <T> T[] sort(final T[] self, final Comparator<T> comparator) {
        Arrays.sort(self, comparator);
        return self;
    }
    
    public static <T> Iterator<T> sort(final Iterator<T> self, final Closure closure) {
        return sort(toList(self), closure).listIterator();
    }
    
    public static <T> T[] sort(final T[] self, final Closure closure) {
        return (T[])sort(toList(self), closure).toArray();
    }
    
    public static <T> List<T> sort(final Collection<T> self, final Closure closure) {
        final List<T> list = asList(self);
        final int params = closure.getMaximumNumberOfParameters();
        if (params == 1) {
            Collections.sort(list, new OrderBy<Object>(closure));
        }
        else {
            Collections.sort(list, new ClosureComparator<Object>(closure));
        }
        return list;
    }
    
    public static <T> SortedSet<T> sort(final SortedSet<T> self) {
        return self;
    }
    
    public static <T> T pop(final List<T> self) {
        if (self.isEmpty()) {
            throw new NoSuchElementException("Cannot pop() an empty List");
        }
        return self.remove(self.size() - 1);
    }
    
    public static <K, V> Map<K, V> putAll(final Map<K, V> self, final Collection<Map.Entry<K, V>> entries) {
        for (final Map.Entry<K, V> entry : entries) {
            self.put(entry.getKey(), entry.getValue());
        }
        return self;
    }
    
    public static <K, V> Map<K, V> plus(final Map<K, V> self, final Collection<Map.Entry<K, V>> entries) {
        final Map<K, V> map = DefaultGroovyMethodsSupport.cloneSimilarMap(self);
        putAll((Map<Object, Object>)map, (Collection<Map.Entry<Object, Object>>)entries);
        return map;
    }
    
    public static <T> boolean push(final List<T> self, final T value) {
        return self.add(value);
    }
    
    public static <T> T last(final List<T> self) {
        if (self.isEmpty()) {
            throw new NoSuchElementException("Cannot access last() element from an empty List");
        }
        return self.get(self.size() - 1);
    }
    
    public static <T> T last(final T[] self) {
        if (self.length == 0) {
            throw new NoSuchElementException("Cannot access last() element from an empty Array");
        }
        return self[self.length - 1];
    }
    
    public static <T> T first(final List<T> self) {
        if (self.isEmpty()) {
            throw new NoSuchElementException("Cannot access first() element from an empty List");
        }
        return self.get(0);
    }
    
    public static <T> T first(final T[] self) {
        if (self.length == 0) {
            throw new NoSuchElementException("Cannot access first() element from an empty List");
        }
        return self[0];
    }
    
    public static <T> T head(final List<T> self) {
        return (T)first((List<Object>)self);
    }
    
    public static <T> T head(final T[] self) {
        return (T)first((Object[])self);
    }
    
    public static <T> List<T> tail(final List<T> self) {
        if (self.isEmpty()) {
            throw new NoSuchElementException("Cannot access tail() for an empty List");
        }
        final List<T> result = new ArrayList<T>((Collection<? extends T>)self);
        result.remove(0);
        return result;
    }
    
    public static <T> T[] tail(final T[] self) {
        if (self.length == 0) {
            throw new NoSuchElementException("Cannot access tail() for an empty Object array");
        }
        final Class<T> componentType = (Class<T>)self.getClass().getComponentType();
        final T[] result = (T[])Array.newInstance(componentType, self.length - 1);
        System.arraycopy(self, 1, result, 0, self.length - 1);
        return result;
    }
    
    public static <T> List<T> asList(final Collection<T> self) {
        if (self instanceof List) {
            return (List<T>)(List)self;
        }
        return (List<T>)toList((Collection<Object>)self);
    }
    
    public static boolean asBoolean(final Object object) {
        return object != null;
    }
    
    public static boolean asBoolean(final Boolean bool) {
        return bool;
    }
    
    public static boolean asBoolean(final Matcher matcher) {
        RegexSupport.setLastMatcher(matcher);
        return matcher.find();
    }
    
    public static boolean asBoolean(final Collection collection) {
        return !collection.isEmpty();
    }
    
    public static boolean asBoolean(final Map map) {
        return !map.isEmpty();
    }
    
    public static boolean asBoolean(final Iterator iterator) {
        return iterator.hasNext();
    }
    
    public static boolean asBoolean(final Enumeration enumeration) {
        return enumeration.hasMoreElements();
    }
    
    public static boolean asBoolean(final CharSequence string) {
        return string.length() > 0;
    }
    
    public static boolean asBoolean(final Object[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final byte[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final short[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final int[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final long[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final float[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final double[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final boolean[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final char[] array) {
        return array.length > 0;
    }
    
    public static boolean asBoolean(final Character character) {
        return character != '\0';
    }
    
    public static boolean asBoolean(final Number number) {
        return number.doubleValue() != 0.0;
    }
    
    @Deprecated
    public static boolean asBoolean(final GroovyResultSet grs) {
        return SqlGroovyMethods.asBoolean(grs);
    }
    
    public static Object asType(final Collection col, final Class clazz) {
        if (col.getClass() == clazz) {
            return col;
        }
        if (clazz == List.class) {
            return asList((Collection<Object>)col);
        }
        if (clazz == Set.class) {
            if (col instanceof Set) {
                return col;
            }
            return new HashSet(col);
        }
        else if (clazz == SortedSet.class) {
            if (col instanceof SortedSet) {
                return col;
            }
            return new TreeSet(col);
        }
        else if (clazz == Queue.class) {
            if (col instanceof Queue) {
                return col;
            }
            return new LinkedList(col);
        }
        else if (clazz == Stack.class) {
            if (col instanceof Stack) {
                return col;
            }
            final Stack stack = new Stack();
            stack.addAll(col);
            return stack;
        }
        else {
            final Object[] args = { col };
            try {
                return InvokerHelper.invokeConstructorOf(clazz, args);
            }
            catch (Exception e) {
                return asType((Object)col, clazz);
            }
        }
    }
    
    public static Object asType(final Object[] ary, final Class clazz) {
        if (clazz == List.class) {
            return new ArrayList(Arrays.asList(ary));
        }
        if (clazz == Set.class) {
            return new HashSet(Arrays.asList(ary));
        }
        if (clazz == SortedSet.class) {
            return new TreeSet(Arrays.asList(ary));
        }
        return asType((Object)ary, clazz);
    }
    
    public static Object asType(final Closure cl, final Class clazz) {
        if (clazz.isInterface() && !clazz.isInstance(cl)) {
            return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new ConvertedClosure(cl));
        }
        try {
            return asType((Object)cl, clazz);
        }
        catch (GroovyCastException ce) {
            try {
                return ProxyGenerator.INSTANCE.instantiateAggregateFromBaseClass(cl, clazz);
            }
            catch (GroovyRuntimeException cause) {
                throw new GroovyCastException("Error casting closure to " + clazz.getName() + ", Reason: " + cause.getMessage());
            }
        }
    }
    
    public static Object asType(final Map map, final Class clazz) {
        if (!clazz.isInstance(map) && clazz.isInterface()) {
            return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new ConvertedMap(map));
        }
        try {
            return asType((Object)map, clazz);
        }
        catch (GroovyCastException ce) {
            try {
                return ProxyGenerator.INSTANCE.instantiateAggregateFromBaseClass(map, clazz);
            }
            catch (GroovyRuntimeException cause) {
                throw new GroovyCastException("Error casting map to " + clazz.getName() + ", Reason: " + cause.getMessage());
            }
        }
    }
    
    public static <T> List<T> reverse(final List<T> self) {
        final int size = self.size();
        final List<T> answer = new ArrayList<T>(size);
        final ListIterator<T> iter = self.listIterator(size);
        while (iter.hasPrevious()) {
            answer.add(iter.previous());
        }
        return answer;
    }
    
    public static <T> Iterator<T> reverse(final Iterator<T> self) {
        return new ReverseListIterator<T>(toList(self));
    }
    
    public static <T> Collection<T> plus(final Collection<T> left, final Collection<T> right) {
        final Collection<T> answer = DefaultGroovyMethodsSupport.cloneSimilarCollection(left, left.size() + right.size());
        answer.addAll((Collection<? extends T>)right);
        return answer;
    }
    
    public static <T> Collection<T> plus(final Collection<T> left, final T right) {
        final Collection<T> answer = DefaultGroovyMethodsSupport.cloneSimilarCollection(left, left.size() + 1);
        answer.add(right);
        return answer;
    }
    
    public static <T> List<T> multiply(final Collection<T> self, final Number factor) {
        final int size = factor.intValue();
        final List<T> answer = new ArrayList<T>(self.size() * size);
        for (int i = 0; i < size; ++i) {
            answer.addAll((Collection<? extends T>)self);
        }
        return answer;
    }
    
    public static <T> Collection<T> intersect(Collection<T> left, Collection<T> right) {
        if (left.isEmpty()) {
            return DefaultGroovyMethodsSupport.createSimilarCollection(left, 0);
        }
        if (left.size() < right.size()) {
            final Collection<T> swaptemp = left;
            left = right;
            right = swaptemp;
        }
        final Collection<T> result = DefaultGroovyMethodsSupport.createSimilarCollection(left, left.size());
        final Collection<T> pickFrom = new TreeSet<T>(new NumberAwareComparator<Object>());
        pickFrom.addAll((Collection<? extends T>)left);
        for (final T t : right) {
            if (pickFrom.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }
    
    public static <K, V> Map<K, V> intersect(final Map<K, V> left, final Map<K, V> right) {
        final Map<K, V> ansMap = DefaultGroovyMethodsSupport.createSimilarMap(left);
        if (right != null && right.size() > 0) {
            for (final Map.Entry<K, V> e1 : left.entrySet()) {
                for (final Map.Entry<K, V> e2 : right.entrySet()) {
                    if (DefaultTypeTransformation.compareEqual(e1, e2)) {
                        ansMap.put(e1.getKey(), e1.getValue());
                    }
                }
            }
        }
        return ansMap;
    }
    
    public static boolean disjoint(final Collection left, final Collection right) {
        if (left.isEmpty() || right.isEmpty()) {
            return true;
        }
        final Collection pickFrom = new TreeSet(new NumberAwareComparator());
        pickFrom.addAll(right);
        for (final Object o : left) {
            if (pickFrom.contains(o)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean equals(final int[] left, final int[] right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.length != right.length) {
            return false;
        }
        for (int i = 0; i < left.length; ++i) {
            if (left[i] != right[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean equals(final Object[] left, final List right) {
        return coercedEquals(left, right);
    }
    
    public static boolean equals(final List left, final Object[] right) {
        return coercedEquals(right, left);
    }
    
    private static boolean coercedEquals(final Object[] left, final List right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.length != right.size()) {
            return false;
        }
        final NumberAwareComparator numberAwareComparator = new NumberAwareComparator();
        for (int i = left.length - 1; i >= 0; --i) {
            final Object o1 = left[i];
            final Object o2 = right.get(i);
            if (o1 == null) {
                if (o2 != null) {
                    return false;
                }
            }
            else if (o1 instanceof Number) {
                if (!(o2 instanceof Number) || numberAwareComparator.compare(o1, o2) != 0) {
                    return false;
                }
            }
            else if (!DefaultTypeTransformation.compareEqual(o1, o2)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean equals(final List left, final List right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.size() != right.size()) {
            return false;
        }
        final NumberAwareComparator numberAwareComparator = new NumberAwareComparator();
        final Iterator it1 = left.iterator();
        final Iterator it2 = right.iterator();
        while (it1.hasNext()) {
            final Object o1 = it1.next();
            final Object o2 = it2.next();
            if (o1 == null) {
                if (o2 != null) {
                    return false;
                }
                continue;
            }
            else if (o1 instanceof Number) {
                if (!(o2 instanceof Number) || numberAwareComparator.compare(o1, o2) != 0) {
                    return false;
                }
                continue;
            }
            else {
                if (!DefaultTypeTransformation.compareEqual(o1, o2)) {
                    return false;
                }
                continue;
            }
        }
        return true;
    }
    
    public static <T> Set<T> minus(final Set<T> self, final Collection operands) {
        final Set<T> ansSet = DefaultGroovyMethodsSupport.createSimilarSet(self);
        ansSet.addAll((Collection<? extends T>)self);
        if (self.size() > 0) {
            ansSet.removeAll(operands);
        }
        return ansSet;
    }
    
    public static <T> Set<T> minus(final Set<T> self, final Object operand) {
        final Set<T> ansSet = DefaultGroovyMethodsSupport.createSimilarSet(self);
        final Comparator numberComparator = new NumberAwareComparator();
        for (final T t : self) {
            if (numberComparator.compare(t, operand) != 0) {
                ansSet.add(t);
            }
        }
        return ansSet;
    }
    
    public static <T> T[] minus(final T[] self, final Collection<T> removeMe) {
        return (T[])minus((List<T>)toList((T[])self), removeMe).toArray();
    }
    
    public static <T> T[] minus(final T[] self, final T[] removeMe) {
        return (T[])minus((List<T>)toList((T[])self), toList(removeMe)).toArray();
    }
    
    public static <T> List<T> minus(final List<T> self, final Collection<T> removeMe) {
        if (self.size() == 0) {
            return new ArrayList<T>();
        }
        final boolean nlgnSort = DefaultGroovyMethodsSupport.sameType(new Collection[] { self, removeMe });
        final Comparator<T> numberComparator = new NumberAwareComparator<T>();
        if (nlgnSort && self.get(0) instanceof Comparable) {
            Set<T> answer;
            if (Number.class.isInstance(self.get(0))) {
                answer = new TreeSet<T>(numberComparator);
                answer.addAll((Collection<? extends T>)self);
                for (final T t : self) {
                    if (Number.class.isInstance(t)) {
                        for (final T t2 : removeMe) {
                            if (Number.class.isInstance(t2) && numberComparator.compare(t, t2) == 0) {
                                answer.remove(t);
                            }
                        }
                    }
                    else {
                        if (!removeMe.contains(t)) {
                            continue;
                        }
                        answer.remove(t);
                    }
                }
            }
            else {
                answer = new TreeSet<T>(numberComparator);
                answer.addAll((Collection<? extends T>)self);
                answer.removeAll(removeMe);
            }
            final List<T> ansList = new ArrayList<T>();
            for (final T o : self) {
                if (answer.contains(o)) {
                    ansList.add(o);
                }
            }
            return ansList;
        }
        final List<T> tmpAnswer = new LinkedList<T>((Collection<? extends T>)self);
        final Iterator<T> iter = tmpAnswer.iterator();
        while (iter.hasNext()) {
            final T element = iter.next();
            boolean elementRemoved = false;
            for (Iterator<T> iterator = removeMe.iterator(); iterator.hasNext() && !elementRemoved; elementRemoved = true) {
                final T elt = iterator.next();
                if (numberComparator.compare(element, elt) == 0) {
                    iter.remove();
                }
            }
        }
        return new ArrayList<T>((Collection<? extends T>)tmpAnswer);
    }
    
    public static <T> List<T> minus(final List<T> self, final Object operand) {
        final Comparator numberComparator = new NumberAwareComparator();
        final List<T> ansList = new ArrayList<T>();
        for (final T t : self) {
            if (numberComparator.compare(t, operand) != 0) {
                ansList.add(t);
            }
        }
        return ansList;
    }
    
    public static <T> T[] minus(final T[] self, final Object operand) {
        return (T[])minus((List<Object>)toList((T[])self), operand).toArray();
    }
    
    public static <K, V> Map<K, V> minus(final Map<K, V> self, final Map<K, V> operands) {
        final Map<K, V> ansMap = DefaultGroovyMethodsSupport.createSimilarMap(self);
        ansMap.putAll((Map<? extends K, ? extends V>)self);
        if (operands != null && operands.size() > 0) {
            for (final Map.Entry<K, V> e1 : self.entrySet()) {
                for (final Map.Entry<K, V> e2 : operands.entrySet()) {
                    if (DefaultTypeTransformation.compareEqual(e1, e2)) {
                        ansMap.remove(e1.getKey());
                    }
                }
            }
        }
        return ansMap;
    }
    
    public static Collection flatten(final Collection self) {
        return flatten(self, DefaultGroovyMethodsSupport.createSimilarCollection((Collection<Object>)self));
    }
    
    public static Collection flatten(final Object[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final boolean[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final byte[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final char[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final short[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final int[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final long[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final float[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    public static Collection flatten(final double[] self) {
        return flatten(toList(self), new ArrayList());
    }
    
    private static Collection flatten(final Collection elements, final Collection addTo) {
        for (final Object element : elements) {
            if (element instanceof Collection) {
                flatten((Collection)element, addTo);
            }
            else if (element != null && element.getClass().isArray()) {
                flatten(DefaultTypeTransformation.arrayAsCollection(element), addTo);
            }
            else {
                addTo.add(element);
            }
        }
        return addTo;
    }
    
    public static Collection flatten(final Collection self, final Closure flattenUsing) {
        return flatten(self, DefaultGroovyMethodsSupport.createSimilarCollection((Collection<Object>)self), flattenUsing);
    }
    
    private static Collection flatten(final Collection elements, final Collection addTo, final Closure flattenUsing) {
        for (final Object element : elements) {
            if (element instanceof Collection) {
                flatten((Collection)element, addTo, flattenUsing);
            }
            else if (element != null && element.getClass().isArray()) {
                flatten(DefaultTypeTransformation.arrayAsCollection(element), addTo, flattenUsing);
            }
            else {
                final Object flattened = flattenUsing.call(new Object[] { element });
                boolean returnedSelf = flattened == element;
                if (!returnedSelf && flattened instanceof Collection) {
                    final List list = toList((Collection<Object>)flattened);
                    if (list.size() == 1 && list.get(0) == element) {
                        returnedSelf = true;
                    }
                }
                if (flattened instanceof Collection && !returnedSelf) {
                    flatten((Collection)flattened, addTo, flattenUsing);
                }
                else {
                    addTo.add(flattened);
                }
            }
        }
        return addTo;
    }
    
    public static <T> Collection<T> leftShift(final Collection<T> self, final T value) {
        self.add(value);
        return self;
    }
    
    public static <T> BlockingQueue<T> leftShift(final BlockingQueue<T> self, final T value) throws InterruptedException {
        self.put(value);
        return self;
    }
    
    public static <K, V> Map<K, V> leftShift(final Map<K, V> self, final Map.Entry<K, V> entry) {
        self.put(entry.getKey(), entry.getValue());
        return self;
    }
    
    public static <K, V> Map<K, V> leftShift(final Map<K, V> self, final Map<K, V> other) {
        self.putAll((Map<? extends K, ? extends V>)other);
        return self;
    }
    
    public static StringBuffer leftShift(final String self, final Object value) {
        return new StringBuffer(self).append(value);
    }
    
    protected static StringWriter createStringWriter(final String self) {
        final StringWriter answer = new StringWriter();
        answer.write(self);
        return answer;
    }
    
    protected static StringBufferWriter createStringBufferWriter(final StringBuffer self) {
        return new StringBufferWriter(self);
    }
    
    public static StringBuffer leftShift(final StringBuffer self, final Object value) {
        self.append(value);
        return self;
    }
    
    public static Writer leftShift(final Writer self, final Object value) throws IOException {
        InvokerHelper.write(self, value);
        return self;
    }
    
    public static Number leftShift(final Number self, final Number operand) {
        return NumberMath.leftShift(self, operand);
    }
    
    public static Number rightShift(final Number self, final Number operand) {
        return NumberMath.rightShift(self, operand);
    }
    
    public static Number rightShiftUnsigned(final Number self, final Number operand) {
        return NumberMath.rightShiftUnsigned(self, operand);
    }
    
    public static void write(final Writer self, final Writable writable) throws IOException {
        writable.writeTo(self);
    }
    
    public static Writer leftShift(final OutputStream self, final Object value) throws IOException {
        final OutputStreamWriter writer = new FlushingStreamWriter(self);
        leftShift(writer, value);
        return writer;
    }
    
    public static void leftShift(final ObjectOutputStream self, final Object value) throws IOException {
        self.writeObject(value);
    }
    
    public static OutputStream leftShift(final OutputStream self, final InputStream in) throws IOException {
        final byte[] buf = new byte[1024];
        while (true) {
            final int count = in.read(buf, 0, buf.length);
            if (count == -1) {
                break;
            }
            if (count == 0) {
                Thread.yield();
            }
            else {
                self.write(buf, 0, count);
            }
        }
        self.flush();
        return self;
    }
    
    public static OutputStream leftShift(final OutputStream self, final byte[] value) throws IOException {
        self.write(value);
        self.flush();
        return self;
    }
    
    public static List<Byte> getAt(final byte[] array, final Range range) {
        return (List<Byte>)primitiveArrayGet(array, range);
    }
    
    public static List<Character> getAt(final char[] array, final Range range) {
        return (List<Character>)primitiveArrayGet(array, range);
    }
    
    public static List<Short> getAt(final short[] array, final Range range) {
        return (List<Short>)primitiveArrayGet(array, range);
    }
    
    public static List<Integer> getAt(final int[] array, final Range range) {
        return (List<Integer>)primitiveArrayGet(array, range);
    }
    
    public static List<Long> getAt(final long[] array, final Range range) {
        return (List<Long>)primitiveArrayGet(array, range);
    }
    
    public static List<Float> getAt(final float[] array, final Range range) {
        return (List<Float>)primitiveArrayGet(array, range);
    }
    
    public static List<Double> getAt(final double[] array, final Range range) {
        return (List<Double>)primitiveArrayGet(array, range);
    }
    
    public static List<Boolean> getAt(final boolean[] array, final Range range) {
        return (List<Boolean>)primitiveArrayGet(array, range);
    }
    
    public static List<Byte> getAt(final byte[] array, final IntRange range) {
        return (List<Byte>)primitiveArrayGet(array, range);
    }
    
    public static List<Character> getAt(final char[] array, final IntRange range) {
        return (List<Character>)primitiveArrayGet(array, range);
    }
    
    public static List<Short> getAt(final short[] array, final IntRange range) {
        return (List<Short>)primitiveArrayGet(array, range);
    }
    
    public static List<Integer> getAt(final int[] array, final IntRange range) {
        return (List<Integer>)primitiveArrayGet(array, range);
    }
    
    public static List<Long> getAt(final long[] array, final IntRange range) {
        return (List<Long>)primitiveArrayGet(array, range);
    }
    
    public static List<Float> getAt(final float[] array, final IntRange range) {
        return (List<Float>)primitiveArrayGet(array, range);
    }
    
    public static List<Double> getAt(final double[] array, final IntRange range) {
        return (List<Double>)primitiveArrayGet(array, range);
    }
    
    public static List<Boolean> getAt(final boolean[] array, final IntRange range) {
        return (List<Boolean>)primitiveArrayGet(array, range);
    }
    
    public static List<Byte> getAt(final byte[] array, final ObjectRange range) {
        return (List<Byte>)primitiveArrayGet(array, range);
    }
    
    public static List<Character> getAt(final char[] array, final ObjectRange range) {
        return (List<Character>)primitiveArrayGet(array, range);
    }
    
    public static List<Short> getAt(final short[] array, final ObjectRange range) {
        return (List<Short>)primitiveArrayGet(array, range);
    }
    
    public static List<Integer> getAt(final int[] array, final ObjectRange range) {
        return (List<Integer>)primitiveArrayGet(array, range);
    }
    
    public static List<Long> getAt(final long[] array, final ObjectRange range) {
        return (List<Long>)primitiveArrayGet(array, range);
    }
    
    public static List<Float> getAt(final float[] array, final ObjectRange range) {
        return (List<Float>)primitiveArrayGet(array, range);
    }
    
    public static List<Double> getAt(final double[] array, final ObjectRange range) {
        return (List<Double>)primitiveArrayGet(array, range);
    }
    
    public static List<Boolean> getAt(final boolean[] array, final ObjectRange range) {
        return (List<Boolean>)primitiveArrayGet(array, range);
    }
    
    public static List<Byte> getAt(final byte[] array, final Collection indices) {
        return (List<Byte>)primitiveArrayGet(array, indices);
    }
    
    public static List<Character> getAt(final char[] array, final Collection indices) {
        return (List<Character>)primitiveArrayGet(array, indices);
    }
    
    public static List<Short> getAt(final short[] array, final Collection indices) {
        return (List<Short>)primitiveArrayGet(array, indices);
    }
    
    public static List<Integer> getAt(final int[] array, final Collection indices) {
        return (List<Integer>)primitiveArrayGet(array, indices);
    }
    
    public static List<Long> getAt(final long[] array, final Collection indices) {
        return (List<Long>)primitiveArrayGet(array, indices);
    }
    
    public static List<Float> getAt(final float[] array, final Collection indices) {
        return (List<Float>)primitiveArrayGet(array, indices);
    }
    
    public static List<Double> getAt(final double[] array, final Collection indices) {
        return (List<Double>)primitiveArrayGet(array, indices);
    }
    
    public static List<Boolean> getAt(final boolean[] array, final Collection indices) {
        return (List<Boolean>)primitiveArrayGet(array, indices);
    }
    
    public static boolean getAt(final BitSet self, final int index) {
        return self.get(index);
    }
    
    public static BitSet getAt(final BitSet self, final IntRange range) {
        final int from = DefaultTypeTransformation.intUnbox(range.getFrom());
        final int to = DefaultTypeTransformation.intUnbox(range.getTo());
        final BitSet result = new BitSet();
        final int numberOfBits = to - from + 1;
        int adjuster = 1;
        int offset = from;
        if (range.isReverse()) {
            adjuster = -1;
            offset = to;
        }
        for (int i = 0; i < numberOfBits; ++i) {
            result.set(i, self.get(offset + adjuster * i));
        }
        return result;
    }
    
    public static void putAt(final BitSet self, final IntRange range, final boolean value) {
        int from = DefaultTypeTransformation.intUnbox(range.getFrom());
        int to = DefaultTypeTransformation.intUnbox(range.getTo());
        if (from > to) {
            final int tmp = to;
            to = from;
            from = tmp;
        }
        self.set(from, to + 1, value);
    }
    
    public static void putAt(final BitSet self, final int index, final boolean value) {
        self.set(index, value);
    }
    
    public static int size(final boolean[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final byte[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final char[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final short[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final int[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final long[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final float[] array) {
        return Array.getLength(array);
    }
    
    public static int size(final double[] array) {
        return Array.getLength(array);
    }
    
    public static List<Byte> toList(final byte[] array) {
        return (List<Byte>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Boolean> toList(final boolean[] array) {
        return (List<Boolean>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Character> toList(final char[] array) {
        return (List<Character>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Short> toList(final short[] array) {
        return (List<Short>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Integer> toList(final int[] array) {
        return (List<Integer>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Long> toList(final long[] array) {
        return (List<Long>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Float> toList(final float[] array) {
        return (List<Float>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    public static List<Double> toList(final double[] array) {
        return (List<Double>)DefaultTypeTransformation.primitiveArrayToList(array);
    }
    
    protected static Object primitiveArrayGet(final Object self, final int idx) {
        return Array.get(self, DefaultGroovyMethodsSupport.normaliseIndex(idx, Array.getLength(self)));
    }
    
    protected static List primitiveArrayGet(final Object self, final Range range) {
        final List answer = new ArrayList();
        for (final Object next : range) {
            final int idx = DefaultTypeTransformation.intUnbox(next);
            answer.add(primitiveArrayGet(self, idx));
        }
        return answer;
    }
    
    protected static List primitiveArrayGet(final Object self, final Collection indices) {
        final List answer = new ArrayList();
        for (final Object value : indices) {
            if (value instanceof Range) {
                answer.addAll(primitiveArrayGet(self, (Range)value));
            }
            else if (value instanceof List) {
                answer.addAll(primitiveArrayGet(self, (Collection)value));
            }
            else {
                final int idx = DefaultTypeTransformation.intUnbox(value);
                answer.add(primitiveArrayGet(self, idx));
            }
        }
        return answer;
    }
    
    protected static Object primitiveArrayPut(final Object self, final int idx, final Object newValue) {
        Array.set(self, DefaultGroovyMethodsSupport.normaliseIndex(idx, Array.getLength(self)), newValue);
        return newValue;
    }
    
    public static Character toCharacter(final String self) {
        return self.charAt(0);
    }
    
    public static Boolean toBoolean(final String self) {
        final String trimmed = self.trim();
        if ("true".equalsIgnoreCase(trimmed) || "y".equalsIgnoreCase(trimmed) || "1".equals(trimmed)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    
    public static Boolean toBoolean(final Boolean self) {
        return self;
    }
    
    public static String[] split(final String self) {
        final StringTokenizer st = new StringTokenizer(self);
        final String[] strings = new String[st.countTokens()];
        for (int i = 0; i < strings.length; ++i) {
            strings[i] = st.nextToken();
        }
        return strings;
    }
    
    public static String capitalize(final String self) {
        if (self == null || self.length() == 0) {
            return self;
        }
        return Character.toUpperCase(self.charAt(0)) + self.substring(1);
    }
    
    public static String expand(final String self) {
        return expand(self, 8);
    }
    
    public static String expand(final String self, final int tabStop) {
        if (self.length() == 0) {
            return self;
        }
        try {
            final StringBuilder builder = new StringBuilder();
            for (final String line : readLines(self)) {
                builder.append(expandLine(line, tabStop));
                builder.append("\n");
            }
            if (!self.endsWith("\n")) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
        catch (IOException e) {
            return self;
        }
    }
    
    public static String expandLine(String self, final int tabStop) {
        int index;
        while ((index = self.indexOf(9)) != -1) {
            final StringBuilder builder = new StringBuilder(self);
            final int count = tabStop - index % tabStop;
            builder.deleteCharAt(index);
            for (int i = 0; i < count; ++i) {
                builder.insert(index, " ");
            }
            self = builder.toString();
        }
        return self;
    }
    
    public static String unexpand(final String self) {
        return unexpand(self, 8);
    }
    
    public static String unexpand(final String self, final int tabStop) {
        if (self.length() == 0) {
            return self;
        }
        try {
            final StringBuilder builder = new StringBuilder();
            for (final String line : readLines(self)) {
                builder.append(unexpandLine(line, tabStop));
                builder.append("\n");
            }
            if (!self.endsWith("\n")) {
                builder.deleteCharAt(builder.length() - 1);
            }
            return builder.toString();
        }
        catch (IOException e) {
            return self;
        }
    }
    
    public static String unexpandLine(final String self, final int tabStop) {
        final StringBuilder builder = new StringBuilder(self);
        int index = 0;
        while (index + tabStop < builder.length()) {
            String piece;
            int count;
            for (piece = builder.substring(index, index + tabStop), count = 0; count < tabStop && Character.isWhitespace(piece.charAt(tabStop - (count + 1))); ++count) {}
            if (count > 0) {
                piece = piece.substring(0, tabStop - count) + '\t';
                builder.replace(index, index + tabStop, piece);
                index = index + tabStop - (count - 1);
            }
            else {
                index += tabStop;
            }
        }
        return builder.toString();
    }
    
    public static String[] split(final GString self) {
        return split(self.toString());
    }
    
    public static List tokenize(final String self, final String token) {
        return InvokerHelper.asList(new StringTokenizer(self, token));
    }
    
    public static List tokenize(final String self, final Character token) {
        return tokenize(self, token.toString());
    }
    
    public static List tokenize(final String self) {
        return InvokerHelper.asList(new StringTokenizer(self));
    }
    
    public static String plus(final String left, final Object value) {
        return left + toString(value);
    }
    
    public static String plus(final Number value, final String right) {
        return toString(value) + right;
    }
    
    public static String plus(final StringBuffer left, final String value) {
        return (Object)left + value;
    }
    
    public static String minus(final String self, final Object target) {
        if (target instanceof Pattern) {
            return ((Pattern)target).matcher(self).replaceFirst("");
        }
        final String text = toString(target);
        final int index = self.indexOf(text);
        if (index == -1) {
            return self;
        }
        final int end = index + text.length();
        if (self.length() > end) {
            return self.substring(0, index) + self.substring(end);
        }
        return self.substring(0, index);
    }
    
    public static boolean contains(final String self, final String text) {
        final int idx = self.indexOf(text);
        return idx >= 0;
    }
    
    public static int count(final String self, final String text) {
        int answer = 0;
        int idx = 0;
        while (true) {
            idx = self.indexOf(text, idx);
            if (idx < 0) {
                break;
            }
            ++answer;
            ++idx;
        }
        return answer;
    }
    
    public static String next(final String self) {
        final StringBuilder buffer = new StringBuilder(self);
        if (buffer.length() == 0) {
            buffer.append('\0');
        }
        else {
            final char last = buffer.charAt(buffer.length() - 1);
            if (last == '\uffff') {
                buffer.append('\0');
            }
            else {
                char next = last;
                ++next;
                buffer.setCharAt(buffer.length() - 1, next);
            }
        }
        return buffer.toString();
    }
    
    public static String previous(final String self) {
        final StringBuilder buffer = new StringBuilder(self);
        if (buffer.length() == 0) {
            throw new IllegalArgumentException("the string is empty");
        }
        final char last = buffer.charAt(buffer.length() - 1);
        if (last == '\0') {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        else {
            char next = last;
            --next;
            buffer.setCharAt(buffer.length() - 1, next);
        }
        return buffer.toString();
    }
    
    public static Process execute(final String self) throws IOException {
        return Runtime.getRuntime().exec(self);
    }
    
    public static Process execute(final String self, final String[] envp, final File dir) throws IOException {
        return Runtime.getRuntime().exec(self, envp, dir);
    }
    
    public static Process execute(final String self, final List envp, final File dir) throws IOException {
        return execute(self, stringify(envp), dir);
    }
    
    public static Process execute(final String[] commandArray) throws IOException {
        return Runtime.getRuntime().exec(commandArray);
    }
    
    public static Process execute(final String[] commandArray, final String[] envp, final File dir) throws IOException {
        return Runtime.getRuntime().exec(commandArray, envp, dir);
    }
    
    public static Process execute(final String[] commandArray, final List envp, final File dir) throws IOException {
        return Runtime.getRuntime().exec(commandArray, stringify(envp), dir);
    }
    
    public static Process execute(final List commands) throws IOException {
        return execute(stringify(commands));
    }
    
    public static Process execute(final List commands, final String[] envp, final File dir) throws IOException {
        return Runtime.getRuntime().exec(stringify(commands), envp, dir);
    }
    
    public static Process execute(final List commands, final List envp, final File dir) throws IOException {
        return Runtime.getRuntime().exec(stringify(commands), stringify(envp), dir);
    }
    
    private static String[] stringify(final List orig) {
        if (orig == null) {
            return null;
        }
        final String[] result = new String[orig.size()];
        for (int i = 0; i < orig.size(); ++i) {
            result[i] = orig.get(i).toString();
        }
        return result;
    }
    
    public static String multiply(final String self, final Number factor) {
        final int size = factor.intValue();
        if (size == 0) {
            return "";
        }
        if (size < 0) {
            throw new IllegalArgumentException("multiply() should be called with a number of 0 or greater not: " + size);
        }
        final StringBuilder answer = new StringBuilder(self);
        for (int i = 1; i < size; ++i) {
            answer.append(self);
        }
        return answer.toString();
    }
    
    public static String toString(final boolean[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final byte[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final char[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final short[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final int[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final long[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final float[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final double[] self) {
        return InvokerHelper.toString(self);
    }
    
    public static String toString(final AbstractMap self) {
        return toMapString(self);
    }
    
    public static String toMapString(final Map self) {
        return toMapString(self, -1);
    }
    
    public static String toMapString(final Map self, final int maxSize) {
        return (self == null) ? "null" : InvokerHelper.toMapString(self, maxSize);
    }
    
    public static String toString(final AbstractCollection self) {
        return toListString(self);
    }
    
    public static String toListString(final Collection self) {
        return toListString(self, -1);
    }
    
    public static String toListString(final Collection self, final int maxSize) {
        return (self == null) ? "null" : InvokerHelper.toListString(self, maxSize);
    }
    
    public static String toString(final Object[] self) {
        return toArrayString(self);
    }
    
    public static String toArrayString(final Object[] self) {
        return (self == null) ? "null" : InvokerHelper.toArrayString(self);
    }
    
    public static String toString(final Object value) {
        return InvokerHelper.toString(value);
    }
    
    public static Character next(final Character self) {
        return (char)((char)self + '\u0001');
    }
    
    public static Number next(final Number self) {
        return NumberNumberPlus.plus(self, DefaultGroovyMethods.ONE);
    }
    
    public static Character previous(final Character self) {
        return (char)((char)self - '\u0001');
    }
    
    public static Number previous(final Number self) {
        return NumberNumberMinus.minus(self, DefaultGroovyMethods.ONE);
    }
    
    public static Number plus(final Character left, final Number right) {
        return NumberNumberPlus.plus((int)left, right);
    }
    
    public static Number plus(final Number left, final Character right) {
        return NumberNumberPlus.plus(left, (int)right);
    }
    
    public static Number plus(final Character left, final Character right) {
        return plus((int)left, right);
    }
    
    public static int compareTo(final Character left, final Number right) {
        return compareTo((int)left, right);
    }
    
    public static int compareTo(final Number left, final Character right) {
        return compareTo(left, (int)right);
    }
    
    public static int compareTo(final Character left, final Character right) {
        return compareTo((int)left, right);
    }
    
    public static int compareTo(final Number left, final Number right) {
        return NumberMath.compareTo(left, right);
    }
    
    public static Number minus(final Character left, final Number right) {
        return NumberNumberMinus.minus((int)left, right);
    }
    
    public static Number minus(final Number left, final Character right) {
        return NumberNumberMinus.minus(left, (int)right);
    }
    
    public static Number minus(final Character left, final Character right) {
        return minus((int)left, right);
    }
    
    public static Number multiply(final Character left, final Number right) {
        return NumberNumberMultiply.multiply((int)left, right);
    }
    
    public static Number multiply(final Number left, final Character right) {
        return NumberNumberMultiply.multiply((int)right, left);
    }
    
    public static Number multiply(final Character left, final Character right) {
        return multiply((int)left, right);
    }
    
    public static Number multiply(final BigDecimal left, final Double right) {
        return NumberMath.multiply(left, right);
    }
    
    public static Number multiply(final BigDecimal left, final BigInteger right) {
        return NumberMath.multiply(left, right);
    }
    
    public static Number power(final Number self, final Number exponent) {
        final double base = self.doubleValue();
        final double exp = exponent.doubleValue();
        final double answer = Math.pow(base, exp);
        if ((int)answer == answer) {
            return (int)answer;
        }
        if ((long)answer == answer) {
            return (long)answer;
        }
        return answer;
    }
    
    public static Number power(final BigDecimal self, final Integer exponent) {
        if (exponent >= 0) {
            return self.pow(exponent);
        }
        return power(self, (double)exponent);
    }
    
    public static Number power(final BigInteger self, final Integer exponent) {
        if (exponent >= 0) {
            return self.pow(exponent);
        }
        return power(self, (double)exponent);
    }
    
    public static Number power(final Integer self, final Integer exponent) {
        if (exponent < 0) {
            return power(self, (double)exponent);
        }
        final BigInteger answer = BigInteger.valueOf(self).pow(exponent);
        if (answer.compareTo(DefaultGroovyMethods.BI_INT_MIN) >= 0 && answer.compareTo(DefaultGroovyMethods.BI_INT_MAX) <= 0) {
            return answer.intValue();
        }
        return answer;
    }
    
    public static Number power(final Long self, final Integer exponent) {
        if (exponent < 0) {
            return power(self, (double)exponent);
        }
        final BigInteger answer = BigInteger.valueOf(self).pow(exponent);
        if (answer.compareTo(DefaultGroovyMethods.BI_LONG_MIN) >= 0 && answer.compareTo(DefaultGroovyMethods.BI_LONG_MAX) <= 0) {
            return answer.longValue();
        }
        return answer;
    }
    
    public static Number div(final Character left, final Number right) {
        return NumberNumberDiv.div((int)left, right);
    }
    
    public static Number div(final Number left, final Character right) {
        return NumberNumberDiv.div(left, (int)right);
    }
    
    public static Number div(final Character left, final Character right) {
        return div((int)left, right);
    }
    
    public static Number intdiv(final Character left, final Number right) {
        return intdiv((int)left, right);
    }
    
    public static Number intdiv(final Number left, final Character right) {
        return intdiv(left, (int)right);
    }
    
    public static Number intdiv(final Character left, final Character right) {
        return intdiv((int)left, right);
    }
    
    public static Number intdiv(final Number left, final Number right) {
        return NumberMath.intdiv(left, right);
    }
    
    public static Number or(final Number left, final Number right) {
        return NumberMath.or(left, right);
    }
    
    public static Number and(final Number left, final Number right) {
        return NumberMath.and(left, right);
    }
    
    public static BitSet and(final BitSet left, final BitSet right) {
        final BitSet result = (BitSet)left.clone();
        result.and(right);
        return result;
    }
    
    public static BitSet xor(final BitSet left, final BitSet right) {
        final BitSet result = (BitSet)left.clone();
        result.xor(right);
        return result;
    }
    
    public static BitSet bitwiseNegate(final BitSet self) {
        final BitSet result = (BitSet)self.clone();
        result.flip(0, result.size() - 1);
        return result;
    }
    
    public static BitSet or(final BitSet left, final BitSet right) {
        final BitSet result = (BitSet)left.clone();
        result.or(right);
        return result;
    }
    
    public static Number xor(final Number left, final Number right) {
        return NumberMath.xor(left, right);
    }
    
    public static Number mod(final Number left, final Number right) {
        return NumberMath.mod(left, right);
    }
    
    public static Number unaryMinus(final Number left) {
        return NumberMath.unaryMinus(left);
    }
    
    public static void times(final Number self, final Closure closure) {
        for (int i = 0, size = self.intValue(); i < size; ++i) {
            closure.call(i);
            if (closure.getDirective() == 1) {
                break;
            }
        }
    }
    
    public static void upto(final Number self, final Number to, final Closure closure) {
        final int self2 = self.intValue();
        final int to2 = to.intValue();
        if (self2 <= to2) {
            for (int i = self2; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final long self, final Number to, final Closure closure) {
        final long to2 = to.longValue();
        if (self <= to2) {
            for (long i = self; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final Long self, final Number to, final Closure closure) {
        final long to2 = to.longValue();
        if (self <= to2) {
            for (long i = self; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final float self, final Number to, final Closure closure) {
        final float to2 = to.floatValue();
        if (self <= to2) {
            for (float i = self; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final Float self, final Number to, final Closure closure) {
        final float to2 = to.floatValue();
        if (self <= to2) {
            for (float i = self; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final double self, final Number to, final Closure closure) {
        final double to2 = to.doubleValue();
        if (self <= to2) {
            for (double i = self; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final Double self, final Number to, final Closure closure) {
        final double to2 = to.doubleValue();
        if (self <= to2) {
            for (double i = self; i <= to2; ++i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
    }
    
    public static void upto(final BigInteger self, final Number to, final Closure closure) {
        if (to instanceof BigDecimal) {
            final BigDecimal one = BigDecimal.valueOf(10L, 1);
            final BigDecimal self2 = new BigDecimal(self);
            final BigDecimal to2 = (BigDecimal)to;
            if (self2.compareTo(to2) > 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
            }
            for (BigDecimal i = self2; i.compareTo(to2) <= 0; i = i.add(one)) {
                closure.call(i);
            }
        }
        else if (to instanceof BigInteger) {
            final BigInteger one2 = BigInteger.valueOf(1L);
            final BigInteger to3 = (BigInteger)to;
            if (self.compareTo(to3) > 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
            }
            for (BigInteger j = self; j.compareTo(to3) <= 0; j = j.add(one2)) {
                closure.call(j);
            }
        }
        else {
            final BigInteger one2 = BigInteger.valueOf(1L);
            final BigInteger to3 = new BigInteger(to.toString());
            if (self.compareTo(to3) > 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
            }
            for (BigInteger j = self; j.compareTo(to3) <= 0; j = j.add(one2)) {
                closure.call(j);
            }
        }
    }
    
    public static void upto(final BigDecimal self, final Number to, final Closure closure) {
        final BigDecimal one = BigDecimal.valueOf(10L, 1);
        if (to instanceof BigDecimal) {
            final BigDecimal to2 = (BigDecimal)to;
            if (self.compareTo(to2) > 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
            }
            for (BigDecimal i = self; i.compareTo(to2) <= 0; i = i.add(one)) {
                closure.call(i);
            }
        }
        else if (to instanceof BigInteger) {
            final BigDecimal to2 = new BigDecimal((BigInteger)to);
            if (self.compareTo(to2) > 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
            }
            for (BigDecimal i = self; i.compareTo(to2) <= 0; i = i.add(one)) {
                closure.call(i);
            }
        }
        else {
            final BigDecimal to2 = new BigDecimal(to.toString());
            if (self.compareTo(to2) > 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".upto(" + to + ")");
            }
            for (BigDecimal i = self; i.compareTo(to2) <= 0; i = i.add(one)) {
                closure.call(i);
            }
        }
    }
    
    public static void downto(final Number self, final Number to, final Closure closure) {
        final int self2 = self.intValue();
        final int to2 = to.intValue();
        if (self2 >= to2) {
            for (int i = self2; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final long self, final Number to, final Closure closure) {
        final long to2 = to.longValue();
        if (self >= to2) {
            for (long i = self; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final Long self, final Number to, final Closure closure) {
        final long to2 = to.longValue();
        if (self >= to2) {
            for (long i = self; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final float self, final Number to, final Closure closure) {
        final float to2 = to.floatValue();
        if (self >= to2) {
            for (float i = self; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final Float self, final Number to, final Closure closure) {
        final float to2 = to.floatValue();
        if (self >= to2) {
            for (float i = self; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final double self, final Number to, final Closure closure) {
        final double to2 = to.doubleValue();
        if (self >= to2) {
            for (double i = self; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final Double self, final Number to, final Closure closure) {
        final double to2 = to.doubleValue();
        if (self >= to2) {
            for (double i = self; i >= to2; --i) {
                closure.call(i);
            }
            return;
        }
        throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
    }
    
    public static void downto(final BigInteger self, final Number to, final Closure closure) {
        if (to instanceof BigDecimal) {
            final BigDecimal one = BigDecimal.valueOf(10L, 1);
            final BigDecimal to2 = (BigDecimal)to;
            final BigDecimal selfD = new BigDecimal(self);
            if (selfD.compareTo(to2) < 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
            }
            for (BigDecimal i = selfD; i.compareTo(to2) >= 0; i = i.subtract(one)) {
                closure.call(i.toBigInteger());
            }
        }
        else if (to instanceof BigInteger) {
            final BigInteger one2 = BigInteger.valueOf(1L);
            final BigInteger to3 = (BigInteger)to;
            if (self.compareTo(to3) < 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
            }
            for (BigInteger j = self; j.compareTo(to3) >= 0; j = j.subtract(one2)) {
                closure.call(j);
            }
        }
        else {
            final BigInteger one2 = BigInteger.valueOf(1L);
            final BigInteger to3 = new BigInteger(to.toString());
            if (self.compareTo(to3) < 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
            }
            for (BigInteger j = self; j.compareTo(to3) >= 0; j = j.subtract(one2)) {
                closure.call(j);
            }
        }
    }
    
    public static void downto(final BigDecimal self, final Number to, final Closure closure) {
        final BigDecimal one = BigDecimal.valueOf(10L, 1);
        if (to instanceof BigDecimal) {
            final BigDecimal to2 = (BigDecimal)to;
            if (self.compareTo(to2) < 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
            }
            for (BigDecimal i = self; i.compareTo(to2) >= 0; i = i.subtract(one)) {
                closure.call(i);
            }
        }
        else if (to instanceof BigInteger) {
            final BigDecimal to2 = new BigDecimal((BigInteger)to);
            if (self.compareTo(to2) < 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
            }
            for (BigDecimal i = self; i.compareTo(to2) >= 0; i = i.subtract(one)) {
                closure.call(i);
            }
        }
        else {
            final BigDecimal to2 = new BigDecimal(to.toString());
            if (self.compareTo(to2) < 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self + ".downto(" + to + ")");
            }
            for (BigDecimal i = self; i.compareTo(to2) >= 0; i = i.subtract(one)) {
                closure.call(i);
            }
        }
    }
    
    public static void step(final Number self, final Number to, final Number stepNumber, final Closure closure) {
        if (self instanceof BigDecimal || to instanceof BigDecimal || stepNumber instanceof BigDecimal) {
            final BigDecimal zero = BigDecimal.valueOf(0L, 1);
            final BigDecimal self2 = (BigDecimal)((self instanceof BigDecimal) ? self : new BigDecimal(self.toString()));
            final BigDecimal to2 = (BigDecimal)((to instanceof BigDecimal) ? to : new BigDecimal(to.toString()));
            final BigDecimal stepNumber2 = (BigDecimal)((stepNumber instanceof BigDecimal) ? stepNumber : new BigDecimal(stepNumber.toString()));
            if (stepNumber2.compareTo(zero) > 0 && to2.compareTo(self2) > 0) {
                for (BigDecimal i = self2; i.compareTo(to2) < 0; i = i.add(stepNumber2)) {
                    closure.call(i);
                }
            }
            else if (stepNumber2.compareTo(zero) < 0 && to2.compareTo(self2) < 0) {
                for (BigDecimal i = self2; i.compareTo(to2) > 0; i = i.add(stepNumber2)) {
                    closure.call(i);
                }
            }
            else if (self2.compareTo(to2) != 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self2 + ".step(" + to2 + ", " + stepNumber2 + ")");
            }
        }
        else if (self instanceof BigInteger || to instanceof BigInteger || stepNumber instanceof BigInteger) {
            final BigInteger zero2 = BigInteger.valueOf(0L);
            final BigInteger self3 = (BigInteger)((self instanceof BigInteger) ? self : new BigInteger(self.toString()));
            final BigInteger to3 = (BigInteger)((to instanceof BigInteger) ? to : new BigInteger(to.toString()));
            final BigInteger stepNumber3 = (BigInteger)((stepNumber instanceof BigInteger) ? stepNumber : new BigInteger(stepNumber.toString()));
            if (stepNumber3.compareTo(zero2) > 0 && to3.compareTo(self3) > 0) {
                for (BigInteger j = self3; j.compareTo(to3) < 0; j = j.add(stepNumber3)) {
                    closure.call(j);
                }
            }
            else if (stepNumber3.compareTo(zero2) < 0 && to3.compareTo(self3) < 0) {
                for (BigInteger j = self3; j.compareTo(to3) > 0; j = j.add(stepNumber3)) {
                    closure.call(j);
                }
            }
            else if (self3.compareTo(to3) != 0) {
                throw new GroovyRuntimeException("Infinite loop in " + self3 + ".step(" + to3 + ", " + stepNumber3 + ")");
            }
        }
        else {
            final int self4 = self.intValue();
            final int to4 = to.intValue();
            final int stepNumber4 = stepNumber.intValue();
            if (stepNumber4 > 0 && to4 > self4) {
                for (int k = self4; k < to4; k += stepNumber4) {
                    closure.call(k);
                }
            }
            else if (stepNumber4 < 0 && to4 < self4) {
                for (int k = self4; k > to4; k += stepNumber4) {
                    closure.call(k);
                }
            }
            else if (self4 != to4) {
                throw new GroovyRuntimeException("Infinite loop in " + self4 + ".step(" + to4 + ", " + stepNumber4 + ")");
            }
        }
    }
    
    public static int abs(final Number number) {
        return Math.abs(number.intValue());
    }
    
    public static long abs(final Long number) {
        return Math.abs(number);
    }
    
    public static float abs(final Float number) {
        return Math.abs(number);
    }
    
    public static double abs(final Double number) {
        return Math.abs(number);
    }
    
    public static int round(final Float number) {
        return Math.round(number);
    }
    
    public static float round(final Float number, final int precision) {
        return (float)(Math.floor(number * Math.pow(10.0, precision) + 0.5) / Math.pow(10.0, precision));
    }
    
    public static float trunc(final Float number, final int precision) {
        return (float)(Math.floor(number * Math.pow(10.0, precision)) / Math.pow(10.0, precision));
    }
    
    public static float trunc(final Float number) {
        return (float)Math.floor(number);
    }
    
    public static long round(final Double number) {
        return Math.round(number);
    }
    
    public static double round(final Double number, final int precision) {
        return Math.floor(number * Math.pow(10.0, precision) + 0.5) / Math.pow(10.0, precision);
    }
    
    public static double trunc(final Double number) {
        return Math.floor(number);
    }
    
    public static double trunc(final Double number, final int precision) {
        return Math.floor(number * Math.pow(10.0, precision)) / Math.pow(10.0, precision);
    }
    
    public static Integer toInteger(final String self) {
        return Integer.valueOf(self.trim());
    }
    
    public static Long toLong(final String self) {
        return Long.valueOf(self.trim());
    }
    
    public static Short toShort(final String self) {
        return Short.valueOf(self.trim());
    }
    
    public static Float toFloat(final String self) {
        return Float.valueOf(self.trim());
    }
    
    public static Double toDouble(final String self) {
        return Double.valueOf(self.trim());
    }
    
    public static BigInteger toBigInteger(final String self) {
        return new BigInteger(self.trim());
    }
    
    public static BigDecimal toBigDecimal(final String self) {
        return new BigDecimal(self.trim());
    }
    
    public static boolean isInteger(final String self) {
        try {
            Integer.valueOf(self.trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isLong(final String self) {
        try {
            Long.valueOf(self.trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isFloat(final String self) {
        try {
            Float.valueOf(self.trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isDouble(final String self) {
        try {
            Double.valueOf(self.trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isBigInteger(final String self) {
        try {
            new BigInteger(self.trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isBigDecimal(final String self) {
        try {
            new BigDecimal(self.trim());
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static boolean isNumber(final String self) {
        return isBigDecimal(self);
    }
    
    public static boolean isUpperCase(final Character self) {
        return Character.isUpperCase(self);
    }
    
    public static boolean isLowerCase(final Character self) {
        return Character.isLowerCase(self);
    }
    
    public static boolean isLetter(final Character self) {
        return Character.isLetter(self);
    }
    
    public static boolean isDigit(final Character self) {
        return Character.isDigit(self);
    }
    
    public static boolean isLetterOrDigit(final Character self) {
        return Character.isLetterOrDigit(self);
    }
    
    public static boolean isWhitespace(final Character self) {
        return Character.isWhitespace(self);
    }
    
    public static char toUpperCase(final Character self) {
        return Character.toUpperCase(self);
    }
    
    public static char toLowerCase(final Character self) {
        return Character.toLowerCase(self);
    }
    
    public static Integer toInteger(final Number self) {
        return self.intValue();
    }
    
    public static Long toLong(final Number self) {
        return self.longValue();
    }
    
    public static Float toFloat(final Number self) {
        return self.floatValue();
    }
    
    public static Double toDouble(final Number self) {
        if (self instanceof Double || self instanceof Long || self instanceof Integer || self instanceof Short || self instanceof Byte) {
            return self.doubleValue();
        }
        return Double.valueOf(self.toString());
    }
    
    public static BigDecimal toBigDecimal(final Number self) {
        if (self instanceof Long || self instanceof Integer || self instanceof Short || self instanceof Byte) {
            return BigDecimal.valueOf(self.longValue());
        }
        return new BigDecimal(self.toString());
    }
    
    public static Object asType(final Number self, final Class c) {
        if (c == BigDecimal.class) {
            return toBigDecimal(self);
        }
        if (c == BigInteger.class) {
            return toBigInteger(self);
        }
        if (c == Double.class) {
            return toDouble(self);
        }
        if (c == Float.class) {
            return toFloat(self);
        }
        return asType((Object)self, c);
    }
    
    public static BigInteger toBigInteger(final Number self) {
        if (self instanceof BigInteger) {
            return (BigInteger)self;
        }
        if (self instanceof BigDecimal) {
            return ((BigDecimal)self).toBigInteger();
        }
        if (self instanceof Double) {
            return new BigDecimal((double)self).toBigInteger();
        }
        if (self instanceof Float) {
            return new BigDecimal((float)self).toBigInteger();
        }
        return new BigInteger(Long.toString(self.longValue()));
    }
    
    public static Boolean and(final Boolean left, final Boolean right) {
        return left && right;
    }
    
    public static Boolean or(final Boolean left, final Boolean right) {
        return left || right;
    }
    
    public static Boolean xor(final Boolean left, final Boolean right) {
        return left ^ right;
    }
    
    public static ObjectOutputStream newObjectOutputStream(final File file) throws IOException {
        return new ObjectOutputStream(new FileOutputStream(file));
    }
    
    public static ObjectOutputStream newObjectOutputStream(final OutputStream outputStream) throws IOException {
        return new ObjectOutputStream(outputStream);
    }
    
    public static Object withObjectOutputStream(final File file, final Closure closure) throws IOException {
        return withStream(newObjectOutputStream(file), closure);
    }
    
    public static Object withObjectOutputStream(final OutputStream outputStream, final Closure closure) throws IOException {
        return withStream(newObjectOutputStream(outputStream), closure);
    }
    
    public static ObjectInputStream newObjectInputStream(final File file) throws IOException {
        return new ObjectInputStream(new FileInputStream(file));
    }
    
    public static ObjectInputStream newObjectInputStream(final InputStream inputStream) throws IOException {
        return new ObjectInputStream(inputStream);
    }
    
    public static ObjectInputStream newObjectInputStream(final InputStream inputStream, final ClassLoader classLoader) throws IOException {
        return new ObjectInputStream(inputStream) {
            @Override
            protected Class<?> resolveClass(final ObjectStreamClass desc) throws IOException, ClassNotFoundException {
                return Class.forName(desc.getName(), true, classLoader);
            }
        };
    }
    
    public static ObjectInputStream newObjectInputStream(final File file, final ClassLoader classLoader) throws IOException {
        return newObjectInputStream(new FileInputStream(file), classLoader);
    }
    
    public static void eachObject(final File self, final Closure closure) throws IOException, ClassNotFoundException {
        eachObject(newObjectInputStream(self), closure);
    }
    
    public static void eachObject(ObjectInputStream ois, final Closure closure) throws IOException, ClassNotFoundException {
        try {
            while (true) {
                final Object obj = ois.readObject();
                closure.call(obj);
            }
        }
        catch (EOFException e) {
            final InputStream temp = ois;
            ois = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(ois);
        }
    }
    
    public static Object withObjectInputStream(final File file, final Closure closure) throws IOException {
        return withStream(newObjectInputStream(file), closure);
    }
    
    public static Object withObjectInputStream(final File file, final ClassLoader classLoader, final Closure closure) throws IOException {
        return withStream(newObjectInputStream(file, classLoader), closure);
    }
    
    public static Object withObjectInputStream(final InputStream inputStream, final Closure closure) throws IOException {
        return withStream(newObjectInputStream(inputStream), closure);
    }
    
    public static Object withObjectInputStream(final InputStream inputStream, final ClassLoader classLoader, final Closure closure) throws IOException {
        return withStream(newObjectInputStream(inputStream, classLoader), closure);
    }
    
    public static Object eachLine(final String self, final Closure closure) throws IOException {
        return eachLine(self, 0, closure);
    }
    
    public static Object eachLine(final String self, final int firstLine, final Closure closure) throws IOException {
        int count = firstLine;
        Object result = null;
        for (final String line : readLines(self)) {
            result = callClosureForLine(closure, line, count);
            ++count;
        }
        return result;
    }
    
    public static Object eachLine(final File self, final Closure closure) throws IOException {
        return eachLine(self, 1, closure);
    }
    
    public static Object eachLine(final File self, final String charset, final Closure closure) throws IOException {
        return eachLine(self, charset, 1, closure);
    }
    
    public static Object eachLine(final File self, final int firstLine, final Closure closure) throws IOException {
        return eachLine(newReader(self), firstLine, closure);
    }
    
    public static Object eachLine(final File self, final String charset, final int firstLine, final Closure closure) throws IOException {
        return eachLine(newReader(self, charset), firstLine, closure);
    }
    
    public static Object eachLine(final InputStream stream, final String charset, final Closure closure) throws IOException {
        return eachLine(stream, charset, 1, closure);
    }
    
    public static Object eachLine(final InputStream stream, final String charset, final int firstLine, final Closure closure) throws IOException {
        return eachLine(new InputStreamReader(stream, charset), firstLine, closure);
    }
    
    public static Object eachLine(final InputStream stream, final Closure closure) throws IOException {
        return eachLine(stream, 1, closure);
    }
    
    public static Object eachLine(final InputStream stream, final int firstLine, final Closure closure) throws IOException {
        return eachLine(new InputStreamReader(stream), firstLine, closure);
    }
    
    public static Object eachLine(final URL url, final Closure closure) throws IOException {
        return eachLine(url, 1, closure);
    }
    
    public static Object eachLine(final URL url, final int firstLine, final Closure closure) throws IOException {
        return eachLine(url.openConnection().getInputStream(), firstLine, closure);
    }
    
    public static Object eachLine(final URL url, final String charset, final Closure closure) throws IOException {
        return eachLine(url, charset, 1, closure);
    }
    
    public static Object eachLine(final URL url, final String charset, final int firstLine, final Closure closure) throws IOException {
        return eachLine(newReader(url, charset), firstLine, closure);
    }
    
    public static Object eachLine(final Reader self, final Closure closure) throws IOException {
        return eachLine(self, 1, closure);
    }
    
    public static Object eachLine(Reader self, final int firstLine, final Closure closure) throws IOException {
        int count = firstLine;
        Object result = null;
        Label_0031: {
            if (self instanceof BufferedReader) {
                final BufferedReader br = (BufferedReader)self;
                break Label_0031;
            }
            final BufferedReader br = new BufferedReader(self);
            try {
                while (true) {
                    final String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    result = callClosureForLine(closure, line, count);
                    ++count;
                }
                final Reader temp = self;
                self = null;
                temp.close();
                return result;
            }
            finally {
                DefaultGroovyMethodsSupport.closeWithWarning(self);
                DefaultGroovyMethodsSupport.closeWithWarning(br);
            }
        }
    }
    
    public static Object splitEachLine(final File self, final String regex, final Closure closure) throws IOException {
        return splitEachLine(newReader(self), regex, closure);
    }
    
    public static Object splitEachLine(final File self, final Pattern pattern, final Closure closure) throws IOException {
        return splitEachLine(newReader(self), pattern, closure);
    }
    
    public static Object splitEachLine(final File self, final String regex, final String charset, final Closure closure) throws IOException {
        return splitEachLine(newReader(self, charset), regex, closure);
    }
    
    public static Object splitEachLine(final File self, final Pattern pattern, final String charset, final Closure closure) throws IOException {
        return splitEachLine(newReader(self, charset), pattern, closure);
    }
    
    public static Object splitEachLine(final URL self, final String regex, final Closure closure) throws IOException {
        return splitEachLine(newReader(self), regex, closure);
    }
    
    public static Object splitEachLine(final URL self, final Pattern pattern, final Closure closure) throws IOException {
        return splitEachLine(newReader(self), pattern, closure);
    }
    
    public static Object splitEachLine(final URL self, final String regex, final String charset, final Closure closure) throws IOException {
        return splitEachLine(newReader(self, charset), regex, closure);
    }
    
    public static Object splitEachLine(final URL self, final Pattern pattern, final String charset, final Closure closure) throws IOException {
        return splitEachLine(newReader(self, charset), pattern, closure);
    }
    
    public static Object splitEachLine(final Reader self, final String regex, final Closure closure) throws IOException {
        return splitEachLine(self, Pattern.compile(regex), closure);
    }
    
    public static Object splitEachLine(Reader self, final Pattern pattern, final Closure closure) throws IOException {
        Object result = null;
        Label_0028: {
            if (self instanceof BufferedReader) {
                final BufferedReader br = (BufferedReader)self;
                break Label_0028;
            }
            final BufferedReader br = new BufferedReader(self);
            try {
                while (true) {
                    final String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    final List vals = Arrays.asList(pattern.split(line));
                    result = closure.call(vals);
                }
                final Reader temp = self;
                self = null;
                temp.close();
                return result;
            }
            finally {
                DefaultGroovyMethodsSupport.closeWithWarning(self);
                DefaultGroovyMethodsSupport.closeWithWarning(br);
            }
        }
    }
    
    public static Object splitEachLine(final InputStream stream, final String regex, final String charset, final Closure closure) throws IOException {
        return splitEachLine(new BufferedReader(new InputStreamReader(stream, charset)), regex, closure);
    }
    
    public static Object splitEachLine(final InputStream stream, final Pattern pattern, final String charset, final Closure closure) throws IOException {
        return splitEachLine(new BufferedReader(new InputStreamReader(stream, charset)), pattern, closure);
    }
    
    public static Object splitEachLine(final InputStream stream, final String regex, final Closure closure) throws IOException {
        return splitEachLine(new BufferedReader(new InputStreamReader(stream)), regex, closure);
    }
    
    public static Object splitEachLine(final InputStream stream, final Pattern pattern, final Closure closure) throws IOException {
        return splitEachLine(new BufferedReader(new InputStreamReader(stream)), pattern, closure);
    }
    
    public static Object splitEachLine(final String self, final String regex, final Closure closure) throws IOException {
        return splitEachLine(self, Pattern.compile(regex), closure);
    }
    
    public static Object splitEachLine(final String self, final Pattern pattern, final Closure closure) throws IOException {
        final List<String> list = readLines(self);
        Object result = null;
        for (final String line : list) {
            final List vals = Arrays.asList(pattern.split(line));
            result = closure.call(vals);
        }
        return result;
    }
    
    public static String readLine(final Reader self) throws IOException {
        if (self instanceof BufferedReader) {
            final BufferedReader br = (BufferedReader)self;
            return br.readLine();
        }
        if (self.markSupported()) {
            return readLineFromReaderWithMark(self);
        }
        return readLineFromReaderWithoutMark(self);
    }
    
    private static String readLineFromReaderWithMark(final Reader input) throws IOException {
        final char[] cbuf = new char[DefaultGroovyMethods.charBufferSize];
        try {
            input.mark(DefaultGroovyMethods.charBufferSize);
        }
        catch (IOException e) {
            DefaultGroovyMethods.LOG.warning("Caught exception setting mark on supporting reader: " + e);
            return readLineFromReaderWithoutMark(input);
        }
        int count = input.read(cbuf);
        if (count == DefaultGroovyMethods.EOF) {
            return null;
        }
        final StringBuffer line = new StringBuffer(DefaultGroovyMethods.expectedLineLength);
        int ls;
        for (ls = lineSeparatorIndex(cbuf, count); ls == -1; ls = lineSeparatorIndex(cbuf, count)) {
            line.append(cbuf, 0, count);
            count = input.read(cbuf);
            if (count == DefaultGroovyMethods.EOF) {
                return line.toString();
            }
        }
        line.append(cbuf, 0, ls);
        int skipLS = 1;
        if (ls + 1 < count) {
            if (cbuf[ls] == '\r' && cbuf[ls + 1] == '\n') {
                ++skipLS;
            }
        }
        else if (cbuf[ls] == '\r' && input.read() == 10) {
            ++skipLS;
        }
        input.reset();
        input.skip(line.length() + skipLS);
        return line.toString();
    }
    
    private static String readLineFromReaderWithoutMark(final Reader input) throws IOException {
        int c = input.read();
        if (c == -1) {
            return null;
        }
        final StringBuffer line = new StringBuffer(DefaultGroovyMethods.expectedLineLength);
        while (c != DefaultGroovyMethods.EOF && c != 10 && c != 13) {
            final char ch = (char)c;
            line.append(ch);
            c = input.read();
        }
        return line.toString();
    }
    
    private static int lineSeparatorIndex(final char[] array, final int length) {
        for (int k = 0; k < length; ++k) {
            if (isLineSeparator(array[k])) {
                return k;
            }
        }
        return -1;
    }
    
    private static boolean isLineSeparator(final char c) {
        return c == '\n' || c == '\r';
    }
    
    public static String denormalize(final String self) {
        if (DefaultGroovyMethods.lineSeparator == null) {
            final StringWriter sw = new StringWriter(2);
            try {
                final BufferedWriter bw = new BufferedWriter(sw);
                bw.newLine();
                bw.flush();
                DefaultGroovyMethods.lineSeparator = sw.toString();
            }
            catch (IOException ioe) {
                DefaultGroovyMethods.lineSeparator = "\n";
            }
        }
        final int len = self.length();
        if (len < 1) {
            return self;
        }
        final StringBuilder sb = new StringBuilder(110 * len / 100);
        int i = 0;
        while (i < len) {
            final char ch = self.charAt(i++);
            switch (ch) {
                case '\r': {
                    sb.append(DefaultGroovyMethods.lineSeparator);
                    if (i < len && self.charAt(i) == '\n') {
                        ++i;
                        continue;
                    }
                    continue;
                }
                case '\n': {
                    sb.append(DefaultGroovyMethods.lineSeparator);
                    continue;
                }
                default: {
                    sb.append(ch);
                    continue;
                }
            }
        }
        return sb.toString();
    }
    
    public static String normalize(final String self) {
        int nx = self.indexOf(13);
        if (nx < 0) {
            return self;
        }
        final int len = self.length();
        final StringBuilder sb = new StringBuilder(len);
        int i = 0;
        do {
            sb.append(self, i, nx);
            sb.append('\n');
            if ((i = nx + 1) >= len) {
                break;
            }
            if (self.charAt(i) == '\n' && ++i >= len) {
                break;
            }
            nx = self.indexOf(13, i);
        } while (nx > 0);
        sb.append(self, i, len);
        return sb.toString();
    }
    
    public static List<String> readLines(final String self) throws IOException {
        return readLines(new StringReader(self));
    }
    
    public static List<String> readLines(final File file) throws IOException {
        return readLines(newReader(file));
    }
    
    public static List<String> readLines(final File file, final String charset) throws IOException {
        return readLines(newReader(file, charset));
    }
    
    public static List<String> readLines(final InputStream stream) throws IOException {
        return readLines(newReader(stream));
    }
    
    public static List<String> readLines(final InputStream stream, final String charset) throws IOException {
        return readLines(newReader(stream, charset));
    }
    
    public static List<String> readLines(final URL self) throws IOException {
        return readLines(newReader(self));
    }
    
    public static List<String> readLines(final URL self, final String charset) throws IOException {
        return readLines(newReader(self, charset));
    }
    
    public static List<String> readLines(final Reader reader) throws IOException {
        final IteratorClosureAdapter closure = new IteratorClosureAdapter(reader);
        eachLine(reader, closure);
        return closure.asList();
    }
    
    public static String getText(final File file, final String charset) throws IOException {
        return getText(newReader(file, charset));
    }
    
    public static String getText(final File file) throws IOException {
        return getText(newReader(file));
    }
    
    public static String getText(final URL url) throws IOException {
        return getText(url, CharsetToolkit.getDefaultSystemCharset().toString());
    }
    
    public static String getText(final URL url, final String charset) throws IOException {
        final BufferedReader reader = newReader(url, charset);
        return getText(reader);
    }
    
    public static String getText(final InputStream is) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return getText(reader);
    }
    
    public static String getText(final InputStream is, final String charset) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        return getText(reader);
    }
    
    public static String getText(final Reader reader) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(reader);
        return getText(bufferedReader);
    }
    
    public static String getText(BufferedReader reader) throws IOException {
        final StringBuilder answer = new StringBuilder();
        final char[] charBuffer = new char[8192];
        try {
            int nbCharRead;
            while ((nbCharRead = reader.read(charBuffer)) != -1) {
                answer.append(charBuffer, 0, nbCharRead);
            }
            final Reader temp = reader;
            reader = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(reader);
        }
        return answer.toString();
    }
    
    public static byte[] getBytes(final File file) throws IOException {
        return getBytes(new FileInputStream(file));
    }
    
    public static byte[] getBytes(final URL url) throws IOException {
        return getBytes(url.openConnection().getInputStream());
    }
    
    public static byte[] getBytes(final InputStream is) throws IOException {
        final ByteArrayOutputStream answer = new ByteArrayOutputStream();
        final byte[] byteBuffer = new byte[8192];
        try {
            int nbByteRead;
            while ((nbByteRead = is.read(byteBuffer)) != -1) {
                answer.write(byteBuffer, 0, nbByteRead);
            }
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(is);
        }
        return answer.toByteArray();
    }
    
    public static void setBytes(final File file, final byte[] bytes) throws IOException {
        setBytes(new FileOutputStream(file), bytes);
    }
    
    public static void setBytes(final OutputStream os, final byte[] bytes) throws IOException {
        try {
            os.write(bytes);
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(os);
        }
    }
    
    public static void writeLine(final BufferedWriter writer, final String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }
    
    public static void write(final File file, final String text) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = newWriter(file);
            writer.write(text);
            writer.flush();
            final Writer temp = writer;
            writer = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static void setText(final File file, final String text) throws IOException {
        write(file, text);
    }
    
    public static void setText(final File file, final String text, final String charset) throws IOException {
        write(file, text, charset);
    }
    
    public static File leftShift(final File file, final Object text) throws IOException {
        append(file, text);
        return file;
    }
    
    public static File leftShift(final File file, final byte[] bytes) throws IOException {
        append(file, bytes);
        return file;
    }
    
    public static File leftShift(final File file, final InputStream data) throws IOException {
        append(file, data);
        return file;
    }
    
    public static void write(final File file, final String text, final String charset) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = newWriter(file, charset);
            writer.write(text);
            writer.flush();
            final Writer temp = writer;
            writer = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static void append(final File file, final Object text) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = newWriter(file, true);
            InvokerHelper.write(writer, text);
            writer.flush();
            final Writer temp = writer;
            writer = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static void append(final File file, final byte[] bytes) throws IOException {
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(file, true));
            stream.write(bytes, 0, bytes.length);
            stream.flush();
            final OutputStream temp = stream;
            stream = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(stream);
        }
    }
    
    public static void append(final File self, final InputStream stream) throws IOException {
        final OutputStream out = new FileOutputStream(self, true);
        try {
            leftShift(out, stream);
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(out);
        }
    }
    
    public static void append(final File file, final Object text, final String charset) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = newWriter(file, charset, true);
            InvokerHelper.write(writer, text);
            writer.flush();
            final Writer temp = writer;
            writer = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    private static void checkDir(final File dir) throws FileNotFoundException, IllegalArgumentException {
        if (!dir.exists()) {
            throw new FileNotFoundException(dir.getAbsolutePath());
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("The provided File object is not a directory: " + dir.getAbsolutePath());
        }
    }
    
    public static void eachFile(final File self, final FileType fileType, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        checkDir(self);
        final File[] files = self.listFiles();
        if (files == null) {
            return;
        }
        for (final File file : files) {
            if (fileType == FileType.ANY || (fileType != FileType.FILES && file.isDirectory()) || (fileType != FileType.DIRECTORIES && file.isFile())) {
                closure.call(file);
            }
        }
    }
    
    public static void eachFile(final File self, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        eachFile(self, FileType.ANY, closure);
    }
    
    public static void eachDir(final File self, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        eachFile(self, FileType.DIRECTORIES, closure);
    }
    
    public static void eachFileRecurse(final File self, final FileType fileType, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        checkDir(self);
        final File[] files = self.listFiles();
        if (files == null) {
            return;
        }
        for (final File file : files) {
            if (file.isDirectory()) {
                if (fileType != FileType.FILES) {
                    closure.call(file);
                }
                eachFileRecurse(file, fileType, closure);
            }
            else if (fileType != FileType.DIRECTORIES) {
                closure.call(file);
            }
        }
    }
    
    public static void traverse(final File self, final Map<String, Object> options, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        final Number maxDepthNumber = (Number)asType(options.remove("maxDepth"), Number.class);
        final int maxDepth = (maxDepthNumber == null) ? -1 : maxDepthNumber.intValue();
        final Boolean visitRoot = (Boolean)asType(get(options, "visitRoot", false), Boolean.class);
        final Boolean preRoot = (Boolean)asType(get(options, "preRoot", false), Boolean.class);
        final Boolean postRoot = (Boolean)asType(get(options, "postRoot", false), Boolean.class);
        final Closure pre = options.get("preDir");
        final Closure post = options.get("postDir");
        final FileType type = options.get("type");
        final Object filter = options.get("filter");
        final Object nameFilter = options.get("nameFilter");
        final Object excludeFilter = options.get("excludeFilter");
        final Object excludeNameFilter = options.get("excludeNameFilter");
        Object preResult = null;
        if (preRoot && pre != null) {
            preResult = pre.call(self);
        }
        if (preResult == FileVisitResult.TERMINATE || preResult == FileVisitResult.SKIP_SUBTREE) {
            return;
        }
        final FileVisitResult terminated = traverse(self, options, closure, maxDepth);
        if (type != FileType.FILES && visitRoot && closure != null && notFiltered(self, filter, nameFilter, excludeFilter, excludeNameFilter)) {
            final Object closureResult = closure.call(self);
            if (closureResult == FileVisitResult.TERMINATE) {
                return;
            }
        }
        if (postRoot && post != null && terminated != FileVisitResult.TERMINATE) {
            post.call(self);
        }
    }
    
    private static boolean notFiltered(final File file, final Object filter, final Object nameFilter, final Object excludeFilter, final Object excludeNameFilter) {
        if (filter == null && nameFilter == null && excludeFilter == null && excludeNameFilter == null) {
            return true;
        }
        if (filter != null && nameFilter != null) {
            throw new IllegalArgumentException("Can't set both 'filter' and 'nameFilter'");
        }
        if (excludeFilter != null && excludeNameFilter != null) {
            throw new IllegalArgumentException("Can't set both 'excludeFilter' and 'excludeNameFilter'");
        }
        Object filterToUse = null;
        Object filterParam = null;
        if (filter != null) {
            filterToUse = filter;
            filterParam = file;
        }
        else if (nameFilter != null) {
            filterToUse = nameFilter;
            filterParam = file.getName();
        }
        Object excludeFilterToUse = null;
        Object excludeParam = null;
        if (excludeFilter != null) {
            excludeFilterToUse = excludeFilter;
            excludeParam = file;
        }
        else if (excludeNameFilter != null) {
            excludeFilterToUse = excludeNameFilter;
            excludeParam = file.getName();
        }
        final MetaClass filterMC = (filterToUse == null) ? null : InvokerHelper.getMetaClass(filterToUse);
        final MetaClass excludeMC = (excludeFilterToUse == null) ? null : InvokerHelper.getMetaClass(excludeFilterToUse);
        final boolean included = filterToUse == null || (filterToUse != null && DefaultTypeTransformation.castToBoolean(filterMC.invokeMethod(filterToUse, "isCase", filterParam)));
        final boolean excluded = excludeFilterToUse != null && DefaultTypeTransformation.castToBoolean(excludeMC.invokeMethod(excludeFilterToUse, "isCase", excludeParam));
        return included && !excluded;
    }
    
    public static void traverse(final File self, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        traverse(self, new HashMap<String, Object>(), closure);
    }
    
    public static void traverse(final File self, final Map<String, Object> options) throws FileNotFoundException, IllegalArgumentException {
        final Closure visit = options.remove("visit");
        traverse(self, options, visit);
    }
    
    private static FileVisitResult traverse(final File self, final Map<String, Object> options, final Closure closure, final int maxDepth) throws FileNotFoundException, IllegalArgumentException {
        checkDir(self);
        final Closure pre = options.get("preDir");
        final Closure post = options.get("postDir");
        final FileType type = options.get("type");
        final Object filter = options.get("filter");
        final Object nameFilter = options.get("nameFilter");
        final Object excludeFilter = options.get("excludeFilter");
        final Object excludeNameFilter = options.get("excludeNameFilter");
        final Closure sort = options.get("sort");
        final File[] origFiles = self.listFiles();
        if (origFiles != null) {
            List<File> files = Arrays.asList(origFiles);
            if (sort != null) {
                files = sort(files, sort);
            }
            for (final File file : files) {
                if (file.isDirectory()) {
                    if (type != FileType.FILES && closure != null && notFiltered(file, filter, nameFilter, excludeFilter, excludeNameFilter)) {
                        final Object closureResult = closure.call(file);
                        if (closureResult == FileVisitResult.SKIP_SIBLINGS) {
                            break;
                        }
                        if (closureResult == FileVisitResult.TERMINATE) {
                            return FileVisitResult.TERMINATE;
                        }
                    }
                    if (maxDepth == 0) {
                        continue;
                    }
                    Object preResult = null;
                    if (pre != null) {
                        preResult = pre.call(file);
                    }
                    if (preResult == FileVisitResult.SKIP_SIBLINGS) {
                        break;
                    }
                    if (preResult == FileVisitResult.TERMINATE) {
                        return FileVisitResult.TERMINATE;
                    }
                    if (preResult != FileVisitResult.SKIP_SUBTREE) {
                        final FileVisitResult terminated = traverse(file, options, closure, maxDepth - 1);
                        if (terminated == FileVisitResult.TERMINATE) {
                            return terminated;
                        }
                    }
                    Object postResult = null;
                    if (post != null) {
                        postResult = post.call(file);
                    }
                    if (postResult == FileVisitResult.SKIP_SIBLINGS) {
                        break;
                    }
                    if (postResult == FileVisitResult.TERMINATE) {
                        return FileVisitResult.TERMINATE;
                    }
                    continue;
                }
                else {
                    if (type == FileType.DIRECTORIES || closure == null || !notFiltered(file, filter, nameFilter, excludeFilter, excludeNameFilter)) {
                        continue;
                    }
                    final Object closureResult = closure.call(file);
                    if (closureResult == FileVisitResult.SKIP_SIBLINGS) {
                        break;
                    }
                    if (closureResult == FileVisitResult.TERMINATE) {
                        return FileVisitResult.TERMINATE;
                    }
                    continue;
                }
            }
        }
        return FileVisitResult.CONTINUE;
    }
    
    public static void eachFileRecurse(final File self, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        eachFileRecurse(self, FileType.ANY, closure);
    }
    
    public static void eachDirRecurse(final File self, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        eachFileRecurse(self, FileType.DIRECTORIES, closure);
    }
    
    public static void eachFileMatch(final File self, final FileType fileType, final Object nameFilter, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        checkDir(self);
        final File[] files = self.listFiles();
        if (files == null) {
            return;
        }
        final MetaClass metaClass = InvokerHelper.getMetaClass(nameFilter);
        for (final File currentFile : files) {
            if (((fileType != FileType.FILES && currentFile.isDirectory()) || (fileType != FileType.DIRECTORIES && currentFile.isFile())) && DefaultTypeTransformation.castToBoolean(metaClass.invokeMethod(nameFilter, "isCase", currentFile.getName()))) {
                closure.call(currentFile);
            }
        }
    }
    
    public static void eachFileMatch(final File self, final Object nameFilter, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        eachFileMatch(self, FileType.ANY, nameFilter, closure);
    }
    
    public static void eachDirMatch(final File self, final Object nameFilter, final Closure closure) throws FileNotFoundException, IllegalArgumentException {
        eachFileMatch(self, FileType.DIRECTORIES, nameFilter, closure);
    }
    
    public static boolean deleteDir(final File self) {
        if (!self.exists()) {
            return true;
        }
        if (!self.isDirectory()) {
            return false;
        }
        final File[] files = self.listFiles();
        if (files == null) {
            return false;
        }
        boolean result = true;
        for (final File file : files) {
            if (file.isDirectory()) {
                if (!deleteDir(file)) {
                    result = false;
                }
            }
            else if (!file.delete()) {
                result = false;
            }
        }
        if (!self.delete()) {
            result = false;
        }
        return result;
    }
    
    public static boolean renameTo(final File self, final String newPathName) {
        return self.renameTo(new File(newPathName));
    }
    
    public static TimerTask runAfter(final Timer timer, final int delay, final Closure closure) {
        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                closure.call();
            }
        };
        timer.schedule(timerTask, delay);
        return timerTask;
    }
    
    public static BufferedReader newReader(final File file) throws IOException {
        final CharsetToolkit toolkit = new CharsetToolkit(file);
        return toolkit.getReader();
    }
    
    public static BufferedReader newReader(final File file, final String charset) throws FileNotFoundException, UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
    }
    
    public static BufferedReader newReader(final InputStream self) {
        return new BufferedReader(new InputStreamReader(self));
    }
    
    public static BufferedReader newReader(final InputStream self, final String charset) throws UnsupportedEncodingException {
        return new BufferedReader(new InputStreamReader(self, charset));
    }
    
    public static Object withReader(final File file, final Closure closure) throws IOException {
        return withReader(newReader(file), closure);
    }
    
    public static Object withReader(final File file, final String charset, final Closure closure) throws IOException {
        return withReader(newReader(file, charset), closure);
    }
    
    public static BufferedOutputStream newOutputStream(final File file) throws IOException {
        return new BufferedOutputStream(new FileOutputStream(file));
    }
    
    public static DataOutputStream newDataOutputStream(final File file) throws IOException {
        return new DataOutputStream(new FileOutputStream(file));
    }
    
    public static Object withOutputStream(final File file, final Closure closure) throws IOException {
        return withStream(newOutputStream(file), closure);
    }
    
    public static Object withInputStream(final File file, final Closure closure) throws IOException {
        return withStream(newInputStream(file), closure);
    }
    
    public static Object withInputStream(final URL url, final Closure closure) throws IOException {
        return withStream(newInputStream(url), closure);
    }
    
    public static Object withDataOutputStream(final File file, final Closure closure) throws IOException {
        return withStream(newDataOutputStream(file), closure);
    }
    
    public static Object withDataInputStream(final File file, final Closure closure) throws IOException {
        return withStream(newDataInputStream(file), closure);
    }
    
    public static BufferedWriter newWriter(final File file) throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }
    
    public static BufferedWriter newWriter(final File file, final boolean append) throws IOException {
        return new BufferedWriter(new FileWriter(file, append));
    }
    
    public static BufferedWriter newWriter(final File file, final String charset, final boolean append) throws IOException {
        if (append) {
            return new EncodingAwareBufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), charset));
        }
        final FileOutputStream stream = new FileOutputStream(file);
        if ("UTF-16BE".equals(charset)) {
            writeUtf16Bom(stream, true);
        }
        else if ("UTF-16LE".equals(charset)) {
            writeUtf16Bom(stream, false);
        }
        return new EncodingAwareBufferedWriter(new OutputStreamWriter(stream, charset));
    }
    
    public static BufferedWriter newWriter(final File file, final String charset) throws IOException {
        return newWriter(file, charset, false);
    }
    
    private static void writeUtf16Bom(final FileOutputStream stream, final boolean bigEndian) throws IOException {
        if (bigEndian) {
            stream.write(-2);
            stream.write(-1);
        }
        else {
            stream.write(-1);
            stream.write(-2);
        }
    }
    
    public static Object withWriter(final File file, final Closure closure) throws IOException {
        return withWriter(newWriter(file), closure);
    }
    
    public static Object withWriter(final File file, final String charset, final Closure closure) throws IOException {
        return withWriter(newWriter(file, charset), closure);
    }
    
    public static Object withWriterAppend(final File file, final String charset, final Closure closure) throws IOException {
        return withWriter(newWriter(file, charset, true), closure);
    }
    
    public static Object withWriterAppend(final File file, final Closure closure) throws IOException {
        return withWriter(newWriter(file, true), closure);
    }
    
    public static PrintWriter newPrintWriter(final File file) throws IOException {
        return new GroovyPrintWriter(newWriter(file));
    }
    
    public static PrintWriter newPrintWriter(final File file, final String charset) throws IOException {
        return new GroovyPrintWriter(newWriter(file, charset));
    }
    
    public static PrintWriter newPrintWriter(final Writer writer) {
        return new GroovyPrintWriter(writer);
    }
    
    public static Object withPrintWriter(final File file, final Closure closure) throws IOException {
        return withWriter(newPrintWriter(file), closure);
    }
    
    public static Object withPrintWriter(final File file, final String charset, final Closure closure) throws IOException {
        return withWriter(newPrintWriter(file, charset), closure);
    }
    
    public static Object withPrintWriter(final Writer writer, final Closure closure) throws IOException {
        return withWriter(newPrintWriter(writer), closure);
    }
    
    public static Object withWriter(Writer writer, final Closure closure) throws IOException {
        try {
            final Object result = closure.call(writer);
            try {
                writer.flush();
            }
            catch (IOException ex) {}
            final Writer temp = writer;
            writer = null;
            temp.close();
            return result;
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static Object withReader(Reader reader, final Closure closure) throws IOException {
        try {
            final Object result = closure.call(reader);
            final Reader temp = reader;
            reader = null;
            temp.close();
            return result;
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(reader);
        }
    }
    
    public static Object withStream(InputStream stream, final Closure closure) throws IOException {
        try {
            final Object result = closure.call(stream);
            final InputStream temp = stream;
            stream = null;
            temp.close();
            return result;
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(stream);
        }
    }
    
    public static Object withReader(final URL url, final Closure closure) throws IOException {
        return withReader(url.openConnection().getInputStream(), closure);
    }
    
    public static Object withReader(final URL url, final String charset, final Closure closure) throws IOException {
        return withReader(url.openConnection().getInputStream(), charset, closure);
    }
    
    public static Object withReader(final InputStream in, final Closure closure) throws IOException {
        return withReader(new InputStreamReader(in), closure);
    }
    
    public static Object withReader(final InputStream in, final String charset, final Closure closure) throws IOException {
        return withReader(new InputStreamReader(in, charset), closure);
    }
    
    public static Object withWriter(final OutputStream stream, final Closure closure) throws IOException {
        return withWriter(new OutputStreamWriter(stream), closure);
    }
    
    public static Object withWriter(final OutputStream stream, final String charset, final Closure closure) throws IOException {
        return withWriter(new OutputStreamWriter(stream, charset), closure);
    }
    
    public static Object withStream(OutputStream os, final Closure closure) throws IOException {
        try {
            final Object result = closure.call(os);
            os.flush();
            final OutputStream temp = os;
            os = null;
            temp.close();
            return result;
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(os);
        }
    }
    
    public static BufferedInputStream newInputStream(final File file) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file));
    }
    
    public static BufferedInputStream newInputStream(final URL url) throws MalformedURLException, IOException {
        return new BufferedInputStream(url.openConnection().getInputStream());
    }
    
    public static BufferedReader newReader(final URL url) throws MalformedURLException, IOException {
        return newReader(url.openConnection().getInputStream());
    }
    
    public static BufferedReader newReader(final URL url, final String charset) throws MalformedURLException, IOException {
        return new BufferedReader(new InputStreamReader(url.openConnection().getInputStream(), charset));
    }
    
    public static DataInputStream newDataInputStream(final File file) throws FileNotFoundException {
        return new DataInputStream(new FileInputStream(file));
    }
    
    public static void eachByte(final File self, final Closure closure) throws IOException {
        final BufferedInputStream is = newInputStream(self);
        eachByte(is, closure);
    }
    
    public static void eachByte(final File self, final int bufferLen, final Closure closure) throws IOException {
        final BufferedInputStream is = newInputStream(self);
        eachByte(is, bufferLen, closure);
    }
    
    public static void eachByte(final Byte[] self, final Closure closure) {
        each(self, closure);
    }
    
    public static void eachByte(final byte[] self, final Closure closure) {
        each(self, closure);
    }
    
    public static void eachByte(InputStream is, final Closure closure) throws IOException {
        try {
            while (true) {
                final int b = is.read();
                if (b == -1) {
                    break;
                }
                closure.call((byte)b);
            }
            final InputStream temp = is;
            is = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(is);
        }
    }
    
    public static void eachByte(InputStream is, final int bufferLen, final Closure closure) throws IOException {
        final byte[] buffer = new byte[bufferLen];
        int bytesRead = 0;
        try {
            while ((bytesRead = is.read(buffer, 0, bufferLen)) > 0) {
                closure.call(new Object[] { buffer, bytesRead });
            }
            final InputStream temp = is;
            is = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(is);
        }
    }
    
    public static void eachByte(final URL url, final Closure closure) throws IOException {
        final InputStream is = url.openConnection().getInputStream();
        eachByte(is, closure);
    }
    
    public static void eachByte(final URL url, final int bufferLen, final Closure closure) throws IOException {
        final InputStream is = url.openConnection().getInputStream();
        eachByte(is, bufferLen, closure);
    }
    
    public static void transformChar(Reader self, Writer writer, final Closure closure) throws IOException {
        try {
            final char[] chars = { '\0' };
            int c;
            while ((c = self.read()) != -1) {
                chars[0] = (char)c;
                writer.write((String)closure.call(new String(chars)));
            }
            writer.flush();
            final Writer temp2 = writer;
            writer = null;
            temp2.close();
            final Reader temp3 = self;
            self = null;
            temp3.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(self);
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static void transformLine(Reader reader, Writer writer, final Closure closure) throws IOException {
        final BufferedReader br = new BufferedReader(reader);
        final BufferedWriter bw = new BufferedWriter(writer);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                final Object o = closure.call(line);
                if (o != null) {
                    bw.write(o.toString());
                    bw.newLine();
                }
            }
            bw.flush();
            final Writer temp2 = writer;
            writer = null;
            temp2.close();
            final Reader temp3 = reader;
            reader = null;
            temp3.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(br);
            DefaultGroovyMethodsSupport.closeWithWarning(reader);
            DefaultGroovyMethodsSupport.closeWithWarning(bw);
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static void filterLine(Reader reader, Writer writer, final Closure closure) throws IOException {
        final BufferedReader br = new BufferedReader(reader);
        final BufferedWriter bw = new BufferedWriter(writer);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (DefaultTypeTransformation.castToBoolean(closure.call(line))) {
                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.flush();
            final Writer temp2 = writer;
            writer = null;
            temp2.close();
            final Reader temp3 = reader;
            reader = null;
            temp3.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(br);
            DefaultGroovyMethodsSupport.closeWithWarning(reader);
            DefaultGroovyMethodsSupport.closeWithWarning(bw);
            DefaultGroovyMethodsSupport.closeWithWarning(writer);
        }
    }
    
    public static Writable filterLine(final File self, final Closure closure) throws IOException {
        return filterLine(newReader(self), closure);
    }
    
    public static Writable filterLine(final File self, final String charset, final Closure closure) throws IOException {
        return filterLine(newReader(self, charset), closure);
    }
    
    public static void filterLine(final File self, final Writer writer, final Closure closure) throws IOException {
        filterLine(newReader(self), writer, closure);
    }
    
    public static void filterLine(final File self, final Writer writer, final String charset, final Closure closure) throws IOException {
        filterLine(newReader(self, charset), writer, closure);
    }
    
    public static Writable filterLine(final Reader reader, final Closure closure) {
        final BufferedReader br = new BufferedReader(reader);
        return new Writable() {
            public Writer writeTo(final Writer out) throws IOException {
                final BufferedWriter bw = new BufferedWriter(out);
                String line;
                while ((line = br.readLine()) != null) {
                    if (DefaultTypeTransformation.castToBoolean(closure.call(line))) {
                        bw.write(line);
                        bw.newLine();
                    }
                }
                bw.flush();
                return out;
            }
            
            @Override
            public String toString() {
                final StringWriter buffer = new StringWriter();
                try {
                    this.writeTo(buffer);
                }
                catch (IOException e) {
                    throw new StringWriterIOException(e);
                }
                return buffer.toString();
            }
        };
    }
    
    public static Writable filterLine(final InputStream self, final Closure predicate) {
        return filterLine(newReader(self), predicate);
    }
    
    public static Writable filterLine(final InputStream self, final String charset, final Closure predicate) throws UnsupportedEncodingException {
        return filterLine(newReader(self, charset), predicate);
    }
    
    public static void filterLine(final InputStream self, final Writer writer, final Closure predicate) throws IOException {
        filterLine(newReader(self), writer, predicate);
    }
    
    public static void filterLine(final InputStream self, final Writer writer, final String charset, final Closure predicate) throws IOException {
        filterLine(newReader(self, charset), writer, predicate);
    }
    
    public static Writable filterLine(final URL self, final Closure predicate) throws IOException {
        return filterLine(newReader(self), predicate);
    }
    
    public static Writable filterLine(final URL self, final String charset, final Closure predicate) throws IOException {
        return filterLine(newReader(self, charset), predicate);
    }
    
    public static void filterLine(final URL self, final Writer writer, final Closure predicate) throws IOException {
        filterLine(newReader(self), writer, predicate);
    }
    
    public static void filterLine(final URL self, final Writer writer, final String charset, final Closure predicate) throws IOException {
        filterLine(newReader(self, charset), writer, predicate);
    }
    
    public static byte[] readBytes(final File file) throws IOException {
        final byte[] bytes = new byte[(int)file.length()];
        final FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fileInputStream);
        try {
            dis.readFully(bytes);
            final InputStream temp = dis;
            dis = null;
            temp.close();
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(dis);
        }
        return bytes;
    }
    
    public static Object withStreams(final Socket socket, final Closure closure) throws IOException {
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        try {
            final Object result = closure.call(new Object[] { input, output });
            final InputStream temp1 = input;
            input = null;
            temp1.close();
            final OutputStream temp2 = output;
            output = null;
            temp2.close();
            return result;
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(input);
            DefaultGroovyMethodsSupport.closeWithWarning(output);
        }
    }
    
    public static Object withObjectStreams(final Socket socket, final Closure closure) throws IOException {
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(output);
        ObjectInputStream ois = new ObjectInputStream(input);
        try {
            final Object result = closure.call(new Object[] { ois, oos });
            InputStream temp1 = ois;
            ois = null;
            temp1.close();
            temp1 = input;
            input = null;
            temp1.close();
            OutputStream temp2 = oos;
            oos = null;
            temp2.close();
            temp2 = output;
            output = null;
            temp2.close();
            return result;
        }
        finally {
            DefaultGroovyMethodsSupport.closeWithWarning(ois);
            DefaultGroovyMethodsSupport.closeWithWarning(input);
            DefaultGroovyMethodsSupport.closeWithWarning(oos);
            DefaultGroovyMethodsSupport.closeWithWarning(output);
        }
    }
    
    public static Writer leftShift(final Socket self, final Object value) throws IOException {
        return leftShift(self.getOutputStream(), value);
    }
    
    public static OutputStream leftShift(final Socket self, final byte[] value) throws IOException {
        return leftShift(self.getOutputStream(), value);
    }
    
    public static Socket accept(final ServerSocket serverSocket, final Closure closure) throws IOException {
        return accept(serverSocket, true, closure);
    }
    
    public static Socket accept(final ServerSocket serverSocket, final boolean runInANewThread, final Closure closure) throws IOException {
        final Socket socket = serverSocket.accept();
        if (runInANewThread) {
            new Thread(new Runnable() {
                public void run() {
                    invokeClosureWithSocket(socket, closure);
                }
            }).start();
        }
        else {
            invokeClosureWithSocket(socket, closure);
        }
        return socket;
    }
    
    private static void invokeClosureWithSocket(final Socket socket, final Closure closure) {
        try {
            closure.call(socket);
        }
        finally {
            if (socket != null) {
                try {
                    socket.close();
                }
                catch (IOException e) {
                    DefaultGroovyMethods.LOG.warning("Caught exception closing socket: " + e);
                }
            }
        }
    }
    
    public static File asWritable(final File file) {
        return new WritableFile(file);
    }
    
    public static Object asType(final File f, final Class c) {
        if (c == Writable.class) {
            return asWritable(f);
        }
        return asType((Object)f, c);
    }
    
    public static File asWritable(final File file, final String encoding) {
        return new WritableFile(file, encoding);
    }
    
    public static List<String> toList(final String self) {
        final int size = self.length();
        final List<String> answer = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            answer.add(self.substring(i, i + 1));
        }
        return answer;
    }
    
    public static char[] getChars(final String self) {
        return self.toCharArray();
    }
    
    public static Object asType(final GString self, final Class c) {
        if (c == File.class) {
            return new File(self.toString());
        }
        if (Number.class.isAssignableFrom(c)) {
            return asType(self.toString(), c);
        }
        return asType((Object)self, c);
    }
    
    public static Object asType(final String self, final Class c) {
        if (c == List.class) {
            return toList(self);
        }
        if (c == BigDecimal.class) {
            return toBigDecimal(self);
        }
        if (c == BigInteger.class) {
            return toBigInteger(self);
        }
        if (c == Long.class || c == Long.TYPE) {
            return toLong(self);
        }
        if (c == Integer.class || c == Integer.TYPE) {
            return toInteger(self);
        }
        if (c == Short.class || c == Short.TYPE) {
            return toShort(self);
        }
        if (c == Byte.class || c == Byte.TYPE) {
            return Byte.valueOf(self.trim());
        }
        if (c == Character.class || c == Character.TYPE) {
            return toCharacter(self);
        }
        if (c == Double.class || c == Double.TYPE) {
            return toDouble(self);
        }
        if (c == Float.class || c == Float.TYPE) {
            return toFloat(self);
        }
        if (c == File.class) {
            return new File(self);
        }
        if (DefaultTypeTransformation.isEnumSubclass(c)) {
            return InvokerHelper.invokeMethod(c, "valueOf", new Object[] { self });
        }
        return asType((Object)self, c);
    }
    
    @Deprecated
    public static InputStream getIn(final Process self) {
        return ProcessGroovyMethods.getIn(self);
    }
    
    @Deprecated
    public static String getText(final Process self) throws IOException {
        return ProcessGroovyMethods.getText(self);
    }
    
    @Deprecated
    public static InputStream getErr(final Process self) {
        return ProcessGroovyMethods.getErr(self);
    }
    
    @Deprecated
    public static OutputStream getOut(final Process self) {
        return ProcessGroovyMethods.getOut(self);
    }
    
    @Deprecated
    public static Writer leftShift(final Process self, final Object value) throws IOException {
        return ProcessGroovyMethods.leftShift(self, value);
    }
    
    @Deprecated
    public static OutputStream leftShift(final Process self, final byte[] value) throws IOException {
        return ProcessGroovyMethods.leftShift(self, value);
    }
    
    @Deprecated
    public static void waitForOrKill(final Process self, final long numberOfMillis) {
        ProcessGroovyMethods.waitForOrKill(self, numberOfMillis);
    }
    
    @Deprecated
    public static void consumeProcessOutput(final Process self) {
        ProcessGroovyMethods.consumeProcessOutput(self);
    }
    
    @Deprecated
    public static void consumeProcessOutput(final Process self, final StringBuffer output, final StringBuffer error) {
        ProcessGroovyMethods.consumeProcessOutput(self, output, error);
    }
    
    @Deprecated
    public static void consumeProcessOutput(final Process self, final OutputStream output, final OutputStream error) {
        ProcessGroovyMethods.consumeProcessOutput(self, output, error);
    }
    
    @Deprecated
    public static void waitForProcessOutput(final Process self) {
        ProcessGroovyMethods.waitForProcessOutput(self);
    }
    
    @Deprecated
    public static void waitForProcessOutput(final Process self, final StringBuffer output, final StringBuffer error) {
        ProcessGroovyMethods.waitForProcessOutput(self, output, error);
    }
    
    @Deprecated
    public static void waitForProcessOutput(final Process self, final OutputStream output, final OutputStream error) {
        ProcessGroovyMethods.waitForProcessOutput(self, output, error);
    }
    
    @Deprecated
    public static Thread consumeProcessErrorStream(final Process self, final OutputStream err) {
        return ProcessGroovyMethods.consumeProcessErrorStream(self, err);
    }
    
    @Deprecated
    public static Thread consumeProcessErrorStream(final Process self, final StringBuffer error) {
        return ProcessGroovyMethods.consumeProcessErrorStream(self, error);
    }
    
    @Deprecated
    public static Thread consumeProcessErrorStream(final Process self, final Writer err) {
        return ProcessGroovyMethods.consumeProcessErrorStream(self, err);
    }
    
    @Deprecated
    public static Thread consumeProcessOutputStream(final Process self, final StringBuffer output) {
        return ProcessGroovyMethods.consumeProcessOutputStream(self, output);
    }
    
    @Deprecated
    public static Thread consumeProcessOutputStream(final Process self, final Writer output) {
        return ProcessGroovyMethods.consumeProcessOutputStream(self, output);
    }
    
    @Deprecated
    public static Thread consumeProcessOutputStream(final Process self, final OutputStream output) {
        return ProcessGroovyMethods.consumeProcessOutputStream(self, output);
    }
    
    @Deprecated
    public static void withWriter(final Process self, final Closure closure) {
        ProcessGroovyMethods.withWriter(self, closure);
    }
    
    @Deprecated
    public static void withOutputStream(final Process self, final Closure closure) {
        ProcessGroovyMethods.withOutputStream(self, closure);
    }
    
    @Deprecated
    public static Process pipeTo(final Process left, final Process right) throws IOException {
        return ProcessGroovyMethods.pipeTo(left, right);
    }
    
    @Deprecated
    public static Process or(final Process left, final Process right) throws IOException {
        return ProcessGroovyMethods.or(left, right);
    }
    
    public static String eachMatch(final String self, final String regex, final Closure closure) {
        return eachMatch(self, Pattern.compile(regex), closure);
    }
    
    public static String eachMatch(final String self, final Pattern pattern, final Closure closure) {
        final Matcher m = pattern.matcher(self);
        each(m, closure);
        return self;
    }
    
    public static int findIndexOf(final Object self, final Closure closure) {
        return findIndexOf(self, 0, closure);
    }
    
    public static int findIndexOf(final Object self, final int startIndex, final Closure closure) {
        int result = -1;
        int i = 0;
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            final Object value = iter.next();
            if (i >= startIndex) {
                if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                    result = i;
                    break;
                }
            }
            ++i;
        }
        return result;
    }
    
    public static int findLastIndexOf(final Object self, final Closure closure) {
        return findLastIndexOf(self, 0, closure);
    }
    
    public static int findLastIndexOf(final Object self, final int startIndex, final Closure closure) {
        int result = -1;
        int i = 0;
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            final Object value = iter.next();
            if (i >= startIndex) {
                if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                    result = i;
                }
            }
            ++i;
        }
        return result;
    }
    
    public static List<Number> findIndexValues(final Object self, final Closure closure) {
        return findIndexValues(self, 0, closure);
    }
    
    public static List<Number> findIndexValues(final Object self, final Number startIndex, final Closure closure) {
        final List<Number> result = new ArrayList<Number>();
        long count = 0L;
        final long startCount = startIndex.longValue();
        final Iterator iter = InvokerHelper.asIterator(self);
        while (iter.hasNext()) {
            final Object value = iter.next();
            if (count >= startCount) {
                if (DefaultTypeTransformation.castToBoolean(closure.call(value))) {
                    result.add(count);
                }
            }
            ++count;
        }
        return result;
    }
    
    public static ClassLoader getRootLoader(ClassLoader self) {
        while (self != null) {
            if (isRootLoaderClassOrSubClass(self)) {
                return self;
            }
            self = self.getParent();
        }
        return null;
    }
    
    private static boolean isRootLoaderClassOrSubClass(final ClassLoader self) {
        for (Class current = self.getClass(); !current.getName().equals(Object.class.getName()); current = current.getSuperclass()) {
            if (current.getName().equals(RootLoader.class.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public static Object asType(final Object obj, final Class type) {
        if (String.class == type) {
            return InvokerHelper.toString(obj);
        }
        try {
            if (ReflectionCache.isArray(type)) {
                return asArrayType(obj, type);
            }
        }
        catch (GroovyCastException ex) {}
        try {
            return DefaultTypeTransformation.castToType(obj, type);
        }
        catch (GroovyCastException e) {
            final MetaClass mc = InvokerHelper.getMetaClass(obj);
            if (mc instanceof ExpandoMetaClass) {
                final ExpandoMetaClass emc = (ExpandoMetaClass)mc;
                final Object mixedIn = emc.castToMixedType(obj, type);
                if (mixedIn != null) {
                    return mixedIn;
                }
            }
            if (type.isInterface()) {
                try {
                    final List<Class> interfaces = new ArrayList<Class>();
                    interfaces.add(type);
                    return ProxyGenerator.INSTANCE.instantiateDelegate(interfaces, obj);
                }
                catch (GroovyRuntimeException ex2) {}
            }
            throw e;
        }
    }
    
    private static Object asArrayType(final Object object, final Class type) {
        if (type.isAssignableFrom(object.getClass())) {
            return object;
        }
        final Collection list = DefaultTypeTransformation.asCollection(object);
        final int size = list.size();
        final Class elementType = type.getComponentType();
        final Object array = Array.newInstance(elementType, size);
        int idx = 0;
        if (Boolean.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setBoolean(array, idx, (boolean)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Boolean.TYPE }));
                ++idx;
            }
        }
        else if (Byte.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setByte(array, idx, (byte)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Byte.TYPE }));
                ++idx;
            }
        }
        else if (Character.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setChar(array, idx, (char)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Character.TYPE }));
                ++idx;
            }
        }
        else if (Double.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setDouble(array, idx, (double)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Double.TYPE }));
                ++idx;
            }
        }
        else if (Float.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setFloat(array, idx, (float)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Float.TYPE }));
                ++idx;
            }
        }
        else if (Integer.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setInt(array, idx, (int)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Integer.TYPE }));
                ++idx;
            }
        }
        else if (Long.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setLong(array, idx, (long)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Long.TYPE }));
                ++idx;
            }
        }
        else if (Short.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setShort(array, idx, (short)InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, Short.TYPE }));
                ++idx;
            }
        }
        else {
            for (final Object element : list) {
                Array.set(array, idx, InvokerHelper.invokeStaticMethod(DefaultGroovyMethods.class, "asType", new Object[] { element, elementType }));
                ++idx;
            }
        }
        return array;
    }
    
    public static <T> T newInstance(final Class<T> c) {
        return (T)InvokerHelper.invokeConstructorOf(c, null);
    }
    
    public static <T> T newInstance(final Class<T> c, Object[] args) {
        if (args == null) {
            args = new Object[] { null };
        }
        return (T)InvokerHelper.invokeConstructorOf(c, args);
    }
    
    public static MetaClass getMetaClass(final Class c) {
        final MetaClassRegistry metaClassRegistry = GroovySystem.getMetaClassRegistry();
        final MetaClass mc = metaClassRegistry.getMetaClass(c);
        if (mc instanceof ExpandoMetaClass || (mc instanceof DelegatingMetaClass && ((DelegatingMetaClass)mc).getAdaptee() instanceof ExpandoMetaClass)) {
            return mc;
        }
        return new HandleMetaClass(mc);
    }
    
    public static MetaClass getMetaClass(final Object obj) {
        final MetaClass mc = InvokerHelper.getMetaClass(obj);
        return new HandleMetaClass(mc, obj);
    }
    
    public static MetaClass getMetaClass(final GroovyObject obj) {
        return getMetaClass((Object)obj);
    }
    
    public static void setMetaClass(final Class self, final MetaClass metaClass) {
        final MetaClassRegistry metaClassRegistry = GroovySystem.getMetaClassRegistry();
        if (metaClass == null) {
            metaClassRegistry.removeMetaClass(self);
        }
        else {
            if (metaClass instanceof HandleMetaClass) {
                metaClassRegistry.setMetaClass(self, ((HandleMetaClass)metaClass).getAdaptee());
            }
            else {
                metaClassRegistry.setMetaClass(self, metaClass);
            }
            if (self == NullObject.class) {
                NullObject.getNullObject().setMetaClass(metaClass);
            }
        }
    }
    
    public static void setMetaClass(final Object self, MetaClass metaClass) {
        if (metaClass instanceof HandleMetaClass) {
            metaClass = ((HandleMetaClass)metaClass).getAdaptee();
        }
        if (self instanceof GroovyObject) {
            ((GroovyObject)self).setMetaClass(metaClass);
        }
        else if (self instanceof Class) {
            ((MetaClassRegistryImpl)GroovySystem.getMetaClassRegistry()).setMetaClass((Class)self, metaClass);
        }
        else {
            ((MetaClassRegistryImpl)GroovySystem.getMetaClassRegistry()).setMetaClass(self, metaClass);
        }
    }
    
    public static MetaClass metaClass(final Class self, final Closure closure) {
        final MetaClassRegistry metaClassRegistry = GroovySystem.getMetaClassRegistry();
        MetaClass mc = metaClassRegistry.getMetaClass(self);
        if (mc instanceof ExpandoMetaClass) {
            ((ExpandoMetaClass)mc).define(closure);
            return mc;
        }
        if (mc instanceof DelegatingMetaClass && ((DelegatingMetaClass)mc).getAdaptee() instanceof ExpandoMetaClass) {
            ((ExpandoMetaClass)((DelegatingMetaClass)mc).getAdaptee()).define(closure);
            return mc;
        }
        if (mc instanceof DelegatingMetaClass && ((DelegatingMetaClass)mc).getAdaptee().getClass() == MetaClassImpl.class) {
            final ExpandoMetaClass emc = new ExpandoMetaClass(self, false, true);
            emc.initialize();
            emc.define(closure);
            ((DelegatingMetaClass)mc).setAdaptee(emc);
            return mc;
        }
        if (mc.getClass() == MetaClassImpl.class) {
            mc = new ExpandoMetaClass(self, false, true);
            mc.initialize();
            ((ExpandoMetaClass)mc).define(closure);
            metaClassRegistry.setMetaClass(self, mc);
            return mc;
        }
        throw new GroovyRuntimeException("Can't add methods to custom meta class " + mc);
    }
    
    public static MetaClass metaClass(final Object self, final Closure closure) {
        final MetaClass emc = hasPerInstanceMetaClass(self);
        if (emc == null) {
            final ExpandoMetaClass metaClass = new ExpandoMetaClass(self.getClass(), false, true);
            metaClass.initialize();
            metaClass.define(closure);
            setMetaClass(self, metaClass);
            return metaClass;
        }
        if (emc instanceof ExpandoMetaClass) {
            ((ExpandoMetaClass)emc).define(closure);
            return emc;
        }
        if (emc instanceof DelegatingMetaClass && ((DelegatingMetaClass)emc).getAdaptee() instanceof ExpandoMetaClass) {
            ((ExpandoMetaClass)((DelegatingMetaClass)emc).getAdaptee()).define(closure);
            return emc;
        }
        throw new RuntimeException("Can't add methods to non-ExpandoMetaClass " + emc);
    }
    
    private static MetaClass hasPerInstanceMetaClass(final Object object) {
        if (object instanceof GroovyObject) {
            final MetaClass mc = ((GroovyObject)object).getMetaClass();
            if (mc == GroovySystem.getMetaClassRegistry().getMetaClass(object.getClass()) || mc.getClass() == MetaClassImpl.class) {
                return null;
            }
            return mc;
        }
        else {
            final ClassInfo info = ClassInfo.getClassInfo(object.getClass());
            info.lock();
            try {
                return info.getPerInstanceMetaClass(object);
            }
            finally {
                info.unlock();
            }
        }
    }
    
    public static <T> Iterator<T> iterator(final T[] a) {
        return DefaultTypeTransformation.asCollection(a).iterator();
    }
    
    public static Iterator iterator(final Object o) {
        return DefaultTypeTransformation.asCollection(o).iterator();
    }
    
    public static <T> Iterator<T> iterator(final Enumeration<T> enumeration) {
        return new Iterator<T>() {
            private T last;
            
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }
            
            public T next() {
                return this.last = enumeration.nextElement();
            }
            
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove() from an Enumeration");
            }
        };
    }
    
    public static Iterator iterator(final Matcher matcher) {
        matcher.reset();
        return new Iterator() {
            private boolean found;
            private boolean done;
            
            public boolean hasNext() {
                if (this.done) {
                    return false;
                }
                if (!this.found && !(this.found = matcher.find())) {
                    this.done = true;
                }
                return this.found;
            }
            
            public Object next() {
                if (!this.found && !this.hasNext()) {
                    throw new NoSuchElementException();
                }
                this.found = false;
                if (DefaultGroovyMethods.hasGroup(matcher)) {
                    final List list = new ArrayList(matcher.groupCount());
                    for (int i = 0; i <= matcher.groupCount(); ++i) {
                        list.add(matcher.group(i));
                    }
                    return list;
                }
                return matcher.group();
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    public static Iterator<String> iterator(final Reader self) {
        BufferedReader bufferedReader;
        if (self instanceof BufferedReader) {
            bufferedReader = (BufferedReader)self;
        }
        else {
            bufferedReader = new BufferedReader(self);
        }
        return new Iterator<String>() {
            String nextVal;
            boolean nextMustRead = true;
            boolean hasNext = true;
            
            public boolean hasNext() {
                if (this.nextMustRead && this.hasNext) {
                    try {
                        this.nextVal = this.readNext();
                        this.nextMustRead = false;
                    }
                    catch (IOException e) {
                        this.hasNext = false;
                    }
                }
                return this.hasNext;
            }
            
            public String next() {
                String retval = null;
                if (this.nextMustRead) {
                    try {
                        retval = this.readNext();
                    }
                    catch (IOException e) {
                        this.hasNext = false;
                    }
                }
                else {
                    retval = this.nextVal;
                }
                this.nextMustRead = true;
                return retval;
            }
            
            private String readNext() throws IOException {
                final String nv = bufferedReader.readLine();
                if (nv == null) {
                    this.hasNext = false;
                }
                return nv;
            }
            
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove() from a Reader Iterator");
            }
        };
    }
    
    public static Iterator<Byte> iterator(final InputStream self) {
        return iterator(new DataInputStream(self));
    }
    
    public static Iterator<Byte> iterator(final DataInputStream self) {
        return new Iterator<Byte>() {
            Byte nextVal;
            boolean nextMustRead = true;
            boolean hasNext = true;
            
            public boolean hasNext() {
                if (this.nextMustRead && this.hasNext) {
                    try {
                        this.nextVal = self.readByte();
                        this.nextMustRead = false;
                    }
                    catch (IOException e) {
                        this.hasNext = false;
                    }
                }
                return this.hasNext;
            }
            
            public Byte next() {
                Byte retval = null;
                if (this.nextMustRead) {
                    try {
                        retval = self.readByte();
                    }
                    catch (IOException e) {
                        this.hasNext = false;
                    }
                }
                else {
                    retval = this.nextVal;
                }
                this.nextMustRead = true;
                return retval;
            }
            
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove() from a DataInputStream Iterator");
            }
        };
    }
    
    @Deprecated
    public static Iterator iterator(final File self) throws IOException {
        throw new DeprecationException("Iterators on files are not supported any more. Use File.eachLine() instead. Alternatively you can use FileReader.iterator() and provide your own exception handling.");
    }
    
    public static <T> Iterator<T> iterator(final Iterator<T> self) {
        return self;
    }
    
    public static List respondsTo(final Object self, final String name, final Object[] argTypes) {
        return InvokerHelper.getMetaClass(self).respondsTo(self, name, argTypes);
    }
    
    public static List respondsTo(final Object self, final String name) {
        return InvokerHelper.getMetaClass(self).respondsTo(self, name);
    }
    
    public static MetaProperty hasProperty(final Object self, final String name) {
        return InvokerHelper.getMetaClass(self).hasProperty(self, name);
    }
    
    @Deprecated
    public static Iterator<Node> iterator(final NodeList nodeList) {
        return XmlGroovyMethods.iterator(nodeList);
    }
    
    @Deprecated
    public static GroovyRowResult toRowResult(final ResultSet rs) throws SQLException {
        return SqlGroovyMethods.toRowResult(rs);
    }
    
    @Deprecated
    public static Writable encodeBase64(final Byte[] data, final boolean chunked) {
        return EncodingGroovyMethods.encodeBase64(data, chunked);
    }
    
    @Deprecated
    public static Writable encodeBase64(final Byte[] data) {
        return EncodingGroovyMethods.encodeBase64(data);
    }
    
    @Deprecated
    public static Writable encodeBase64(final byte[] data, final boolean chunked) {
        return EncodingGroovyMethods.encodeBase64(data, chunked);
    }
    
    @Deprecated
    public static Writable encodeBase64(final byte[] data) {
        return EncodingGroovyMethods.encodeBase64(data);
    }
    
    @Deprecated
    public static byte[] decodeBase64(final String value) {
        return EncodingGroovyMethods.decodeBase64(value);
    }
    
    @Deprecated
    public static Date next(final Date self) {
        return DateGroovyMethods.next(self);
    }
    
    @Deprecated
    public static java.sql.Date next(final java.sql.Date self) {
        return DateGroovyMethods.next(self);
    }
    
    @Deprecated
    public static Date previous(final Date self) {
        return DateGroovyMethods.previous(self);
    }
    
    @Deprecated
    public static java.sql.Date previous(final java.sql.Date self) {
        return DateGroovyMethods.previous(self);
    }
    
    @Deprecated
    public static Date plus(final Date self, final int days) {
        return DateGroovyMethods.plus(self, days);
    }
    
    @Deprecated
    public static java.sql.Date plus(final java.sql.Date self, final int days) {
        return DateGroovyMethods.plus(self, days);
    }
    
    @Deprecated
    public static Date minus(final Date self, final int days) {
        return DateGroovyMethods.minus(self, days);
    }
    
    @Deprecated
    public static java.sql.Date minus(final java.sql.Date self, final int days) {
        return DateGroovyMethods.minus(self, days);
    }
    
    @Deprecated
    public static int minus(final Calendar self, final Calendar then) {
        return DateGroovyMethods.minus(self, then);
    }
    
    @Deprecated
    public static int minus(final Date self, final Date then) {
        return DateGroovyMethods.minus(self, then);
    }
    
    @Deprecated
    public static String format(final Date self, final String format) {
        return DateGroovyMethods.format(self, format);
    }
    
    @Deprecated
    public static String getDateString(final Date self) {
        return DateGroovyMethods.getDateString(self);
    }
    
    @Deprecated
    public static String getTimeString(final Date self) {
        return DateGroovyMethods.getTimeString(self);
    }
    
    @Deprecated
    public static String getDateTimeString(final Date self) {
        return DateGroovyMethods.getDateTimeString(self);
    }
    
    @Deprecated
    public static void clearTime(final Date self) {
        DateGroovyMethods.clearTime(self);
    }
    
    @Deprecated
    public static void clearTime(final java.sql.Date self) {
        DateGroovyMethods.clearTime(self);
    }
    
    @Deprecated
    public static void clearTime(final Calendar self) {
        DateGroovyMethods.clearTime(self);
    }
    
    @Deprecated
    public static String format(final Calendar self, final String pattern) {
        return DateGroovyMethods.format(self, pattern);
    }
    
    @Deprecated
    public static int getAt(final Date self, final int field) {
        return DateGroovyMethods.getAt(self, field);
    }
    
    static {
        LOG = Logger.getLogger(DefaultGroovyMethods.class.getName());
        ONE = 1;
        BI_INT_MAX = BigInteger.valueOf(2147483647L);
        BI_INT_MIN = BigInteger.valueOf(-2147483648L);
        BI_LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);
        BI_LONG_MIN = BigInteger.valueOf(Long.MIN_VALUE);
        additionals = new Class[] { NumberNumberPlus.class, NumberNumberMultiply.class, NumberNumberMinus.class, NumberNumberDiv.class, ObjectArrayGetAtMetaMethod.class, ObjectArrayPutAtMetaMethod.class, BooleanArrayGetAtMetaMethod.class, BooleanArrayPutAtMetaMethod.class, ByteArrayGetAtMetaMethod.class, ByteArrayPutAtMetaMethod.class, CharacterArrayGetAtMetaMethod.class, CharacterArrayPutAtMetaMethod.class, ShortArrayGetAtMetaMethod.class, ShortArrayPutAtMetaMethod.class, IntegerArrayGetAtMetaMethod.class, IntegerArrayPutAtMetaMethod.class, LongArrayGetAtMetaMethod.class, LongArrayPutAtMetaMethod.class, FloatArrayGetAtMetaMethod.class, FloatArrayPutAtMetaMethod.class, DoubleArrayGetAtMetaMethod.class, DoubleArrayPutAtMetaMethod.class };
        DefaultGroovyMethods.charBufferSize = 4096;
        DefaultGroovyMethods.expectedLineLength = 160;
        DefaultGroovyMethods.EOF = -1;
        DefaultGroovyMethods.lineSeparator = null;
    }
}
