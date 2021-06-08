// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;
import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.io.Serializable;

public abstract class GString extends GroovyObjectSupport implements Comparable, CharSequence, Writable, Buildable, Serializable
{
    static final long serialVersionUID = -2638020355892246323L;
    public static final GString EMPTY;
    private Object[] values;
    
    public GString(final Object values) {
        this.values = (Object[])values;
    }
    
    public GString(final Object[] values) {
        this.values = values;
    }
    
    public abstract String[] getStrings();
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        try {
            return super.invokeMethod(name, args);
        }
        catch (MissingMethodException e) {
            return InvokerHelper.invokeMethod(this.toString(), name, args);
        }
    }
    
    public Object[] getValues() {
        return this.values;
    }
    
    public GString plus(final GString that) {
        final List<String> stringList = new ArrayList<String>();
        final List<Object> valueList = new ArrayList<Object>();
        stringList.addAll(Arrays.asList(this.getStrings()));
        valueList.addAll(Arrays.asList(this.getValues()));
        List<String> thatStrings = Arrays.asList(that.getStrings());
        if (stringList.size() > valueList.size()) {
            thatStrings = new ArrayList<String>(thatStrings);
            String s = stringList.get(stringList.size() - 1);
            s += thatStrings.get(0);
            thatStrings.remove(0);
            stringList.set(stringList.size() - 1, s);
        }
        stringList.addAll(thatStrings);
        valueList.addAll(Arrays.asList(that.getValues()));
        final String[] newStrings = new String[stringList.size()];
        stringList.toArray(newStrings);
        final Object[] newValues = valueList.toArray();
        return new GString(newValues) {
            @Override
            public String[] getStrings() {
                return newStrings;
            }
        };
    }
    
    public GString plus(final String that) {
        final String[] currentStrings = this.getStrings();
        final boolean appendToLastString = currentStrings.length > this.getValues().length;
        String[] newStrings;
        if (appendToLastString) {
            newStrings = new String[currentStrings.length];
        }
        else {
            newStrings = new String[currentStrings.length + 1];
        }
        final Object[] newValues = new Object[this.getValues().length];
        final int lastIndex = currentStrings.length;
        System.arraycopy(currentStrings, 0, newStrings, 0, lastIndex);
        System.arraycopy(this.getValues(), 0, newValues, 0, this.getValues().length);
        if (appendToLastString) {
            final StringBuilder sb = new StringBuilder();
            final String[] array = newStrings;
            final int n = lastIndex - 1;
            array[n] = sb.append(array[n]).append(that).toString();
        }
        else {
            newStrings[lastIndex] = that;
        }
        final String[] finalStrings = newStrings;
        return new GString(newValues) {
            @Override
            public String[] getStrings() {
                return finalStrings;
            }
        };
    }
    
    public int getValueCount() {
        return this.values.length;
    }
    
    public Object getValue(final int idx) {
        return this.values[idx];
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
    
    public Writer writeTo(final Writer out) throws IOException {
        final String[] s = this.getStrings();
        final int numberOfValues = this.values.length;
        for (int i = 0, size = s.length; i < size; ++i) {
            out.write(s[i]);
            if (i < numberOfValues) {
                final Object value = this.values[i];
                if (value instanceof Closure) {
                    final Closure c = (Closure)value;
                    if (c.getMaximumNumberOfParameters() == 0) {
                        InvokerHelper.write(out, c.call(null));
                    }
                    else {
                        if (c.getMaximumNumberOfParameters() != 1) {
                            throw new GroovyRuntimeException("Trying to evaluate a GString containing a Closure taking " + c.getMaximumNumberOfParameters() + " parameters");
                        }
                        c.call(new Object[] { out });
                    }
                }
                else {
                    InvokerHelper.write(out, value);
                }
            }
        }
        return out;
    }
    
    public void build(final GroovyObject builder) {
        final String[] s = this.getStrings();
        final int numberOfValues = this.values.length;
        for (int i = 0, size = s.length; i < size; ++i) {
            builder.getProperty("mkp");
            builder.invokeMethod("yield", new Object[] { s[i] });
            if (i < numberOfValues) {
                builder.getProperty("mkp");
                builder.invokeMethod("yield", new Object[] { this.values[i] });
            }
        }
    }
    
    @Override
    public boolean equals(final Object that) {
        return that instanceof GString && this.equals((GString)that);
    }
    
    public boolean equals(final GString that) {
        return this.toString().equals(that.toString());
    }
    
    @Override
    public int hashCode() {
        return 37 + this.toString().hashCode();
    }
    
    public int compareTo(final Object that) {
        return this.toString().compareTo(that.toString());
    }
    
    public char charAt(final int index) {
        return this.toString().charAt(index);
    }
    
    public int length() {
        return this.toString().length();
    }
    
    public CharSequence subSequence(final int start, final int end) {
        return this.toString().subSequence(start, end);
    }
    
    public Pattern negate() {
        return DefaultGroovyMethods.bitwiseNegate(this.toString());
    }
    
    static {
        EMPTY = new GString(new Object[0]) {
            @Override
            public String[] getStrings() {
                return new String[] { "" };
            }
        };
    }
}
