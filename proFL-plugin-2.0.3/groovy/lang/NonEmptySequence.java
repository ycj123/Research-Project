// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.List;

public class NonEmptySequence extends Sequence
{
    public NonEmptySequence() {
        super((Class)null);
    }
    
    public NonEmptySequence(final Class type) {
        super(type);
    }
    
    public NonEmptySequence(final Class type, final List content) {
        super(type, content);
    }
    
    @Override
    public int minimumSize() {
        return 1;
    }
}
