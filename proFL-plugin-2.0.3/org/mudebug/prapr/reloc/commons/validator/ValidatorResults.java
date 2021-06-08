// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.validator;

import java.util.Iterator;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class ValidatorResults implements Serializable
{
    protected Map hResults;
    
    public ValidatorResults() {
        this.hResults = new HashMap();
    }
    
    public void merge(final ValidatorResults results) {
        this.hResults.putAll(results.hResults);
    }
    
    public void add(final Field field, final String validatorName, final boolean result) {
        this.add(field, validatorName, result, null);
    }
    
    public void add(final Field field, final String validatorName, final boolean result, final Object value) {
        ValidatorResult validatorResult = this.getValidatorResult(field.getKey());
        if (validatorResult == null) {
            validatorResult = new ValidatorResult(field);
            this.hResults.put(field.getKey(), validatorResult);
        }
        validatorResult.add(validatorName, result, value);
    }
    
    public void clear() {
        this.hResults.clear();
    }
    
    public boolean isEmpty() {
        return this.hResults.isEmpty();
    }
    
    public ValidatorResult getValidatorResult(final String key) {
        return this.hResults.get(key);
    }
    
    public Set getPropertyNames() {
        return Collections.unmodifiableSet(this.hResults.keySet());
    }
    
    public Map getResultValueMap() {
        final Map results = new HashMap();
        for (final String propertyKey : this.hResults.keySet()) {
            final ValidatorResult vr = this.getValidatorResult(propertyKey);
            final Map actions = vr.getActionMap();
            for (final String actionKey : actions.keySet()) {
                final ValidatorResult.ResultStatus rs = actions.get(actionKey);
                if (rs != null) {
                    final Object result = rs.getResult();
                    if (result == null || result instanceof Boolean) {
                        continue;
                    }
                    results.put(propertyKey, result);
                }
            }
        }
        return results;
    }
}
