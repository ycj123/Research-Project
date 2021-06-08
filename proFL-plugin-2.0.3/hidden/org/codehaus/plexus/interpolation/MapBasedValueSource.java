// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import java.util.Map;

public class MapBasedValueSource implements ValueSource
{
    private final Map values;
    
    public MapBasedValueSource(final Map values) {
        this.values = values;
    }
    
    public Object getValue(final String expression) {
        return (this.values == null) ? null : this.values.get(expression);
    }
}
