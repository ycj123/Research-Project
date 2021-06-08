// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.booter;

import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Properties;
import java.io.File;
import org.apache.maven.surefire.util.ReflectionUtils;

public class TypeEncodedValue
{
    String type;
    String value;
    
    public TypeEncodedValue(final String type, final String value) {
        this.type = type;
        this.value = value;
    }
    
    public boolean isTypeClass() {
        return Class.class.getName().equals(this.type);
    }
    
    public Object getDecodedValue() {
        return this.getDecodedValue(Thread.currentThread().getContextClassLoader());
    }
    
    public Object getDecodedValue(final ClassLoader classLoader) {
        if (this.type.trim().length() == 0) {
            return null;
        }
        if (this.type.equals(String.class.getName())) {
            return this.value;
        }
        if (this.isTypeClass()) {
            return ReflectionUtils.loadClass(classLoader, this.value);
        }
        if (this.type.equals(File.class.getName())) {
            return new File(this.value);
        }
        if (this.type.equals(Boolean.class.getName())) {
            return Boolean.valueOf(this.value);
        }
        if (this.type.equals(Integer.class.getName())) {
            return Integer.valueOf(this.value);
        }
        if (this.type.equals(Properties.class.getName())) {
            final Properties result = new Properties();
            try {
                final ByteArrayInputStream bais = new ByteArrayInputStream(this.value.getBytes("8859_1"));
                result.load(bais);
            }
            catch (Exception e) {
                throw new RuntimeException("bug in property conversion", e);
            }
            return result;
        }
        throw new IllegalArgumentException("Unknown parameter type: " + this.type);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final TypeEncodedValue that = (TypeEncodedValue)o;
        Label_0062: {
            if (this.type != null) {
                if (this.type.equals(that.type)) {
                    break Label_0062;
                }
            }
            else if (that.type == null) {
                break Label_0062;
            }
            return false;
        }
        if (this.value != null) {
            if (!this.value.equals(that.value)) {
                return false;
            }
        }
        else if (that.value != null) {
            return false;
        }
        return true;
        b = false;
        return b;
    }
    
    @Override
    public int hashCode() {
        int result = (this.type != null) ? this.type.hashCode() : 0;
        result = 31 * result + ((this.value != null) ? this.value.hashCode() : 0);
        return result;
    }
}
