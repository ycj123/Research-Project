// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import java.util.List;
import java.util.Properties;

public class PrefixedPropertiesValueSource implements QueryEnabledValueSource
{
    private final PrefixedValueSourceWrapper delegate;
    
    public PrefixedPropertiesValueSource(final String prefix, final Properties properties) {
        this.delegate = new PrefixedValueSourceWrapper(new PropertiesBasedValueSource(properties), prefix);
    }
    
    public PrefixedPropertiesValueSource(final List possiblePrefixes, final Properties properties, final boolean allowUnprefixedExpressions) {
        this.delegate = new PrefixedValueSourceWrapper(new PropertiesBasedValueSource(properties), possiblePrefixes, allowUnprefixedExpressions);
    }
    
    public void clearFeedback() {
        this.delegate.clearFeedback();
    }
    
    public List getFeedback() {
        return this.delegate.getFeedback();
    }
    
    public String getLastExpression() {
        return this.delegate.getLastExpression();
    }
    
    public Object getValue(final String expression) {
        return this.delegate.getValue(expression);
    }
}
