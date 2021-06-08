// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.util.introspection;

import java.lang.reflect.Method;
import org.apache.velocity.runtime.log.Log;

public class SecureIntrospectorImpl extends Introspector implements SecureIntrospectorControl
{
    private String[] badClasses;
    private String[] badPackages;
    
    public SecureIntrospectorImpl(final String[] badClasses, final String[] badPackages, final Log log) {
        super(log);
        this.badClasses = badClasses;
        this.badPackages = badPackages;
    }
    
    public Method getMethod(final Class clazz, final String methodName, final Object[] params) throws IllegalArgumentException {
        if (!this.checkObjectExecutePermission(clazz, methodName)) {
            this.log.warn("Cannot retrieve method " + methodName + " from object of class " + clazz.getName() + " due to security restrictions.");
            return null;
        }
        return super.getMethod(clazz, methodName, params);
    }
    
    public boolean checkObjectExecutePermission(final Class clazz, final String methodName) {
        if (methodName != null && (methodName.equals("wait") || methodName.equals("notify"))) {
            return false;
        }
        if (Number.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Boolean.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (String.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Class.class.isAssignableFrom(clazz) && methodName != null && methodName.equals("getName")) {
            return true;
        }
        String className = clazz.getName();
        if (className.startsWith("[L") && className.endsWith(";")) {
            className = className.substring(2, className.length() - 1);
        }
        final int dotPos = className.lastIndexOf(46);
        final String packageName = (dotPos == -1) ? "" : className.substring(0, dotPos);
        for (int sz = this.badPackages.length, i = 0; i < sz; ++i) {
            if (packageName.equals(this.badPackages[i])) {
                return false;
            }
        }
        for (int sz = this.badClasses.length, i = 0; i < sz; ++i) {
            if (className.equals(this.badClasses[i])) {
                return false;
            }
        }
        return true;
    }
}
