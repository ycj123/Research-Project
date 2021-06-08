// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util.slurpersupport;

import java.util.List;
import java.io.IOException;
import java.io.Writer;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Iterator;
import java.util.Map;
import groovy.lang.Closure;

public class FilteredAttributes extends Attributes
{
    private final Closure closure;
    
    public FilteredAttributes(final GPathResult parent, final Closure closure, final Map<String, String> namespaceTagHints) {
        super(parent, parent.name, namespaceTagHints);
        this.closure = closure;
    }
    
    @Override
    public Iterator nodeIterator() {
        return new NodeIterator(this.parent.iterator()) {
            @Override
            protected Object getNextNode(final Iterator iter) {
                while (iter.hasNext()) {
                    final Object node = iter.next();
                    if (DefaultTypeTransformation.castToBoolean(FilteredAttributes.this.closure.call(new Object[] { node }))) {
                        return node;
                    }
                }
                return null;
            }
        };
    }
}
