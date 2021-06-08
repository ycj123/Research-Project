// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import hidden.org.codehaus.plexus.interpolation.reflection.ReflectionValueExtractor;
import java.util.ArrayList;
import java.util.List;

public class ObjectBasedValueSource implements FeedbackEnabledValueSource
{
    private final Object root;
    private List feedback;
    
    public ObjectBasedValueSource(final Object root) {
        this.feedback = new ArrayList();
        this.root = root;
    }
    
    public Object getValue(final String expression) {
        try {
            return ReflectionValueExtractor.evaluate(expression, this.root, false);
        }
        catch (Exception e) {
            this.feedback.add("Failed to extract '" + expression + "' from: " + this.root);
            this.feedback.add(e);
            return null;
        }
    }
    
    public List getFeedback() {
        return this.feedback;
    }
    
    public void clearFeedback() {
        this.feedback.clear();
    }
}
