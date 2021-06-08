// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import java.util.Map;
import groovy.util.BuilderSupport;

public class NamespaceBuilder
{
    private BuilderSupport builder;
    
    public static NamespaceBuilderSupport newInstance(final BuilderSupport builder, final String uri) {
        return new NamespaceBuilder(builder).namespace(uri);
    }
    
    public static NamespaceBuilderSupport newInstance(final BuilderSupport builder) {
        return new NamespaceBuilderSupport(builder);
    }
    
    public static NamespaceBuilderSupport newInstance(final BuilderSupport builder, final String uri, final String prefix) {
        return new NamespaceBuilder(builder).namespace(uri, prefix);
    }
    
    public static NamespaceBuilderSupport newInstance(final Map nsMap, final BuilderSupport builder) {
        return new NamespaceBuilder(builder).declareNamespace(nsMap);
    }
    
    public NamespaceBuilder(final BuilderSupport builder) {
        this.builder = builder;
    }
    
    public NamespaceBuilderSupport namespace(final String uri) {
        return this.namespace(uri, "");
    }
    
    public NamespaceBuilderSupport namespace(final String uri, final String prefix) {
        return new NamespaceBuilderSupport(this.builder, uri, prefix);
    }
    
    public NamespaceBuilderSupport declareNamespace(final Map ns) {
        return new NamespaceBuilderSupport(this.builder, ns);
    }
}
