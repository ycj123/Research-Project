// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import java.util.List;

public class PrefixedObjectValueSource implements FeedbackEnabledValueSource, QueryEnabledValueSource
{
    private final PrefixedValueSourceWrapper delegate;
    
    public PrefixedObjectValueSource(final String prefix, final Object root) {
        this.delegate = new PrefixedValueSourceWrapper(new ObjectBasedValueSource(root), prefix);
    }
    
    public PrefixedObjectValueSource(final List possiblePrefixes, final Object root, final boolean allowUnprefixedExpressions) {
        this.delegate = new PrefixedValueSourceWrapper(new ObjectBasedValueSource(root), possiblePrefixes, allowUnprefixedExpressions);
    }
    
    public Object getValue(final String expression) {
        return this.delegate.getValue(expression);
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
}
