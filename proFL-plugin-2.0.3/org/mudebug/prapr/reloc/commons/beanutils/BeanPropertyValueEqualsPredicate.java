// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.collections.Predicate;

public class BeanPropertyValueEqualsPredicate implements Predicate
{
    private final Log log;
    private String propertyName;
    private Object propertyValue;
    private boolean ignoreNull;
    
    public BeanPropertyValueEqualsPredicate(final String propertyName, final Object propertyValue) {
        this(propertyName, propertyValue, false);
    }
    
    public BeanPropertyValueEqualsPredicate(final String propertyName, final Object propertyValue, final boolean ignoreNull) {
        this.log = LogFactory.getLog(this.getClass());
        if (propertyName != null && propertyName.length() > 0) {
            this.propertyName = propertyName;
            this.propertyValue = propertyValue;
            this.ignoreNull = ignoreNull;
            return;
        }
        throw new IllegalArgumentException("propertyName cannot be null or empty");
    }
    
    public boolean evaluate(final Object object) {
        boolean evaluation = false;
        try {
            evaluation = this.evaluateValue(this.propertyValue, PropertyUtils.getProperty(object, this.propertyName));
        }
        catch (IllegalArgumentException e) {
            final String errorMsg = "Problem during evaluation. Null value encountered in property path...";
            if (!this.ignoreNull) {
                this.log.error("ERROR: Problem during evaluation. Null value encountered in property path...", e);
                throw e;
            }
            this.log.warn("WARNING: Problem during evaluation. Null value encountered in property path...", e);
        }
        catch (IllegalAccessException e2) {
            final String errorMsg2 = "Unable to access the property provided.";
            this.log.error("Unable to access the property provided.", e2);
            throw new IllegalArgumentException("Unable to access the property provided.");
        }
        catch (InvocationTargetException e3) {
            final String errorMsg3 = "Exception occurred in property's getter";
            this.log.error("Exception occurred in property's getter", e3);
            throw new IllegalArgumentException("Exception occurred in property's getter");
        }
        catch (NoSuchMethodException e4) {
            final String errorMsg4 = "Property not found.";
            this.log.error("Property not found.", e4);
            throw new IllegalArgumentException("Property not found.");
        }
        return evaluation;
    }
    
    private boolean evaluateValue(final Object expected, final Object actual) {
        return expected == actual || (expected != null && expected.equals(actual));
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public Object getPropertyValue() {
        return this.propertyValue;
    }
    
    public boolean isIgnoreNull() {
        return this.ignoreNull;
    }
}
