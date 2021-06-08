// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.collections.Transformer;

public class BeanToPropertyValueTransformer implements Transformer
{
    private final Log log;
    private String propertyName;
    private boolean ignoreNull;
    
    public BeanToPropertyValueTransformer(final String propertyName) {
        this(propertyName, false);
    }
    
    public BeanToPropertyValueTransformer(final String propertyName, final boolean ignoreNull) {
        this.log = LogFactory.getLog(this.getClass());
        if (propertyName != null && propertyName.length() > 0) {
            this.propertyName = propertyName;
            this.ignoreNull = ignoreNull;
            return;
        }
        throw new IllegalArgumentException("propertyName cannot be null or empty");
    }
    
    public Object transform(final Object object) {
        Object propertyValue = null;
        try {
            propertyValue = PropertyUtils.getProperty(object, this.propertyName);
        }
        catch (IllegalArgumentException e) {
            final String errorMsg = "Problem during transformation. Null value encountered in property path...";
            if (!this.ignoreNull) {
                this.log.error("ERROR: Problem during transformation. Null value encountered in property path...", e);
                throw e;
            }
            this.log.warn("WARNING: Problem during transformation. Null value encountered in property path...", e);
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
            final String errorMsg4 = "No property found for name [" + this.propertyName + "]";
            this.log.error(errorMsg4, e4);
            throw new IllegalArgumentException(errorMsg4);
        }
        return propertyValue;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public boolean isIgnoreNull() {
        return this.ignoreNull;
    }
}
