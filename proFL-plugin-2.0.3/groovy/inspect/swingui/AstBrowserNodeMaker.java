// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import java.util.List;

public interface AstBrowserNodeMaker<T>
{
    T makeNode(final Object p0);
    
    T makeNodeWithProperties(final Object p0, final List<List<String>> p1);
}
