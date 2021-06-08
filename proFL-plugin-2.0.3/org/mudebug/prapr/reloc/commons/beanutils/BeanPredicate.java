// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.lang.reflect.InvocationTargetException;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.collections.Predicate;

public class BeanPredicate implements Predicate
{
    private final Log log;
    private String propertyName;
    private Predicate predicate;
    
    public BeanPredicate(final String propertyName, final Predicate predicate) {
        this.log = LogFactory.getLog(this.getClass());
        this.propertyName = propertyName;
        this.predicate = predicate;
    }
    
    public boolean evaluate(final Object object) {
        boolean evaluation = false;
        try {
            final Object propValue = PropertyUtils.getProperty(object, this.propertyName);
            evaluation = this.predicate.evaluate(propValue);
        }
        catch (IllegalArgumentException e) {
            final String errorMsg = "Problem during evaluation.";
            this.log.error("ERROR: Problem during evaluation.", e);
            throw e;
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
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public void setPropertyName(final String propertyName) {
        this.propertyName = propertyName;
    }
    
    public Predicate getPredicate() {
        return this.predicate;
    }
    
    public void setPredicate(final Predicate predicate) {
        this.predicate = predicate;
    }
}
