// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect;

import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.io.PrintStream;
import org.codehaus.groovy.reflection.CachedClass;
import groovy.lang.PropertyValue;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import groovy.lang.MetaClass;
import groovy.lang.MetaMethod;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import groovy.lang.GroovyObject;
import java.lang.reflect.Modifier;

public class Inspector
{
    protected Object objectUnderInspection;
    public static final int CLASS_PACKAGE_IDX = 0;
    public static final int CLASS_CLASS_IDX = 1;
    public static final int CLASS_INTERFACE_IDX = 2;
    public static final int CLASS_SUPERCLASS_IDX = 3;
    public static final int CLASS_OTHER_IDX = 4;
    public static final int MEMBER_ORIGIN_IDX = 0;
    public static final int MEMBER_MODIFIER_IDX = 1;
    public static final int MEMBER_DECLARER_IDX = 2;
    public static final int MEMBER_TYPE_IDX = 3;
    public static final int MEMBER_NAME_IDX = 4;
    public static final int MEMBER_PARAMS_IDX = 5;
    public static final int MEMBER_VALUE_IDX = 5;
    public static final int MEMBER_EXCEPTIONS_IDX = 6;
    public static final String NOT_APPLICABLE = "n/a";
    public static final String GROOVY = "GROOVY";
    public static final String JAVA = "JAVA";
    
    public Inspector(final Object objectUnderInspection) {
        if (null == objectUnderInspection) {
            throw new IllegalArgumentException("argument must not be null");
        }
        this.objectUnderInspection = objectUnderInspection;
    }
    
    public String[] getClassProps() {
        final String[] result = new String[5];
        final Package pack = this.getClassUnderInspection().getPackage();
        result[0] = "package " + ((pack == null) ? "n/a" : pack.getName());
        final String modifiers = Modifier.toString(this.getClassUnderInspection().getModifiers());
        result[1] = modifiers + " class " + shortName(this.getClassUnderInspection());
        result[2] = "implements ";
        final Class[] arr$;
        final Class[] interfaces = arr$ = this.getClassUnderInspection().getInterfaces();
        for (final Class anInterface : arr$) {
            final StringBuilder sb = new StringBuilder();
            final String[] array = result;
            final int n = 2;
            array[n] = sb.append(array[n]).append(shortName(anInterface)).append(" ").toString();
        }
        result[3] = "extends " + shortName(this.getClassUnderInspection().getSuperclass());
        result[4] = "is Primitive: " + this.getClassUnderInspection().isPrimitive() + ", is Array: " + this.getClassUnderInspection().isArray() + ", is Groovy: " + this.isGroovy();
        return result;
    }
    
    public boolean isGroovy() {
        return GroovyObject.class.isAssignableFrom(this.getClassUnderInspection());
    }
    
    public Object getObject() {
        return this.objectUnderInspection;
    }
    
    public Object[] getMethods() {
        final Method[] methods = this.getClassUnderInspection().getMethods();
        final Constructor[] ctors = this.getClassUnderInspection().getConstructors();
        final Object[] result = new Object[methods.length + ctors.length];
        int resultIndex;
        for (resultIndex = 0; resultIndex < methods.length; ++resultIndex) {
            final Method method = methods[resultIndex];
            result[resultIndex] = this.methodInfo(method);
        }
        for (int i = 0; i < ctors.length; ++i, ++resultIndex) {
            final Constructor ctor = ctors[i];
            result[resultIndex] = this.methodInfo(ctor);
        }
        return result;
    }
    
    public Object[] getMetaMethods() {
        final MetaClass metaClass = InvokerHelper.getMetaClass(this.objectUnderInspection);
        final List metaMethods = metaClass.getMetaMethods();
        final Object[] result = new Object[metaMethods.size()];
        int i = 0;
        for (final MetaMethod metaMethod : metaMethods) {
            result[i] = this.methodInfo(metaMethod);
            ++i;
        }
        return result;
    }
    
    public Object[] getPublicFields() {
        final Field[] fields = this.getClassUnderInspection().getFields();
        final Object[] result = new Object[fields.length];
        for (int i = 0; i < fields.length; ++i) {
            final Field field = fields[i];
            result[i] = this.fieldInfo(field);
        }
        return result;
    }
    
    public Object[] getPropertyInfo() {
        final List props = DefaultGroovyMethods.getMetaPropertyValues(this.objectUnderInspection);
        final Object[] result = new Object[props.size()];
        int i = 0;
        for (final PropertyValue pv : props) {
            result[i] = this.fieldInfo(pv);
            ++i;
        }
        return result;
    }
    
    protected String[] fieldInfo(final Field field) {
        final String[] result = { "JAVA", Modifier.toString(field.getModifiers()), shortName(field.getDeclaringClass()), shortName(field.getType()), field.getName(), null };
        try {
            result[5] = InvokerHelper.inspect(field.get(this.objectUnderInspection));
        }
        catch (IllegalAccessException e) {
            result[5] = "n/a";
        }
        return this.withoutNulls(result);
    }
    
    protected String[] fieldInfo(final PropertyValue pv) {
        final String[] result = { "GROOVY", "public", "n/a", shortName(pv.getType()), pv.getName(), null };
        try {
            result[5] = InvokerHelper.inspect(pv.getValue());
        }
        catch (Exception e) {
            result[5] = "n/a";
        }
        return this.withoutNulls(result);
    }
    
    protected Class getClassUnderInspection() {
        return this.objectUnderInspection.getClass();
    }
    
    public static String shortName(final Class clazz) {
        if (null == clazz) {
            return "n/a";
        }
        String className = clazz.getName();
        if (null == clazz.getPackage()) {
            return className;
        }
        final String packageName = clazz.getPackage().getName();
        int offset = packageName.length();
        if (offset > 0) {
            ++offset;
        }
        className = className.substring(offset);
        return className;
    }
    
    protected String[] methodInfo(final Method method) {
        final String[] result = new String[7];
        final int mod = method.getModifiers();
        result[0] = "JAVA";
        result[2] = shortName(method.getDeclaringClass());
        result[1] = Modifier.toString(mod);
        result[4] = method.getName();
        result[3] = shortName(method.getReturnType());
        final Class[] params = method.getParameterTypes();
        final StringBuffer sb = new StringBuffer();
        for (int j = 0; j < params.length; ++j) {
            sb.append(shortName(params[j]));
            if (j < params.length - 1) {
                sb.append(", ");
            }
        }
        result[5] = sb.toString();
        sb.setLength(0);
        final Class[] exceptions = method.getExceptionTypes();
        for (int k = 0; k < exceptions.length; ++k) {
            sb.append(shortName(exceptions[k]));
            if (k < exceptions.length - 1) {
                sb.append(", ");
            }
        }
        result[6] = sb.toString();
        return this.withoutNulls(result);
    }
    
    protected String[] methodInfo(final Constructor ctor) {
        final String[] result = new String[7];
        final int mod = ctor.getModifiers();
        result[0] = "JAVA";
        result[1] = Modifier.toString(mod);
        result[2] = shortName(ctor.getDeclaringClass());
        result[3] = shortName(ctor.getDeclaringClass());
        result[4] = ctor.getName();
        final Class[] params = ctor.getParameterTypes();
        final StringBuffer sb = new StringBuffer();
        for (int j = 0; j < params.length; ++j) {
            sb.append(shortName(params[j]));
            if (j < params.length - 1) {
                sb.append(", ");
            }
        }
        result[5] = sb.toString();
        sb.setLength(0);
        final Class[] exceptions = ctor.getExceptionTypes();
        for (int k = 0; k < exceptions.length; ++k) {
            sb.append(shortName(exceptions[k]));
            if (k < exceptions.length - 1) {
                sb.append(", ");
            }
        }
        result[6] = sb.toString();
        return this.withoutNulls(result);
    }
    
    protected String[] methodInfo(final MetaMethod method) {
        final String[] result = new String[7];
        final int mod = method.getModifiers();
        result[0] = "GROOVY";
        result[1] = Modifier.toString(mod);
        result[2] = shortName(method.getDeclaringClass().getTheClass());
        result[3] = shortName(method.getReturnType());
        result[4] = method.getName();
        final CachedClass[] params = method.getParameterTypes();
        final StringBuffer sb = new StringBuffer();
        for (int j = 0; j < params.length; ++j) {
            sb.append(shortName(params[j].getTheClass()));
            if (j < params.length - 1) {
                sb.append(", ");
            }
        }
        result[5] = sb.toString();
        result[6] = "n/a";
        return this.withoutNulls(result);
    }
    
    protected String[] withoutNulls(final String[] toNormalize) {
        for (int i = 0; i < toNormalize.length; ++i) {
            final String s = toNormalize[i];
            if (null == s) {
                toNormalize[i] = "n/a";
            }
        }
        return toNormalize;
    }
    
    public static void print(final Object[] memberInfo) {
        print(System.out, memberInfo);
    }
    
    static void print(final PrintStream out, final Object[] memberInfo) {
        for (int i = 0; i < memberInfo.length; ++i) {
            final String[] metaMethod = (String[])memberInfo[i];
            out.print(i + ":\t");
            for (final String s : metaMethod) {
                out.print(s + " ");
            }
            out.println("");
        }
    }
    
    public static Collection sort(final List<Object> memberInfo) {
        Collections.sort(memberInfo, new MemberComparator());
        return memberInfo;
    }
    
    public static class MemberComparator implements Comparator<Object>
    {
        public int compare(final Object a, final Object b) {
            final String[] aStr = (String[])a;
            final String[] bStr = (String[])b;
            int result = aStr[4].compareTo(bStr[4]);
            if (0 != result) {
                return result;
            }
            result = aStr[3].compareTo(bStr[3]);
            if (0 != result) {
                return result;
            }
            result = aStr[5].compareTo(bStr[5]);
            if (0 != result) {
                return result;
            }
            result = aStr[2].compareTo(bStr[2]);
            if (0 != result) {
                return result;
            }
            result = aStr[1].compareTo(bStr[1]);
            if (0 != result) {
                return result;
            }
            result = aStr[0].compareTo(bStr[0]);
            return result;
        }
    }
}
