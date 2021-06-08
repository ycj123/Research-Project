// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import org.apache.velocity.util.ClassUtils;
import java.util.HashMap;

public class FieldMethodizer
{
    private HashMap fieldHash;
    private HashMap classHash;
    
    public FieldMethodizer() {
        this.fieldHash = new HashMap();
        this.classHash = new HashMap();
    }
    
    public FieldMethodizer(final String s) {
        this.fieldHash = new HashMap();
        this.classHash = new HashMap();
        try {
            this.addObject(s);
        }
        catch (Exception e) {
            System.err.println("Could not add " + s + " for field methodizing: " + e.getMessage());
        }
    }
    
    public FieldMethodizer(final Object o) {
        this.fieldHash = new HashMap();
        this.classHash = new HashMap();
        try {
            this.addObject(o);
        }
        catch (Exception e) {
            System.err.println("Could not add " + o + " for field methodizing: " + e.getMessage());
        }
    }
    
    public void addObject(final String s) throws Exception {
        this.inspect(ClassUtils.getClass(s));
    }
    
    public void addObject(final Object o) throws Exception {
        this.inspect(o.getClass());
    }
    
    public Object get(final String fieldName) {
        Object value = null;
        try {
            final Field f = this.fieldHash.get(fieldName);
            if (f != null) {
                value = f.get(this.classHash.get(fieldName));
            }
        }
        catch (IllegalAccessException e) {
            System.err.println("IllegalAccessException while trying to access " + fieldName + ": " + e.getMessage());
        }
        return value;
    }
    
    private void inspect(final Class clas) {
        final Field[] fields = clas.getFields();
        for (int i = 0; i < fields.length; ++i) {
            final int mod = fields[i].getModifiers();
            if (Modifier.isStatic(mod) && Modifier.isPublic(mod)) {
                this.fieldHash.put(fields[i].getName(), fields[i]);
                this.classHash.put(fields[i].getName(), clas);
            }
        }
    }
}
