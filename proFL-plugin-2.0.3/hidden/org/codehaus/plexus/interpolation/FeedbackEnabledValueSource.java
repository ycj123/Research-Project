// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import java.util.List;

public interface FeedbackEnabledValueSource extends ValueSource
{
    List getFeedback();
    
    void clearFeedback();
}
