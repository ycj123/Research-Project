// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.InvokerHelper;
import groovy.util.NodeBuilder;
import java.util.HashMap;
import java.util.Map;
import groovy.util.BuilderSupport;

public class NamespaceBuilderSupport extends BuilderSupport
{
    private boolean autoPrefix;
    private Map<String, String> nsMap;
    private BuilderSupport builder;
    
    public NamespaceBuilderSupport(final BuilderSupport builder) {
        super(builder);
        this.nsMap = new HashMap<String, String>();
        this.builder = builder;
    }
    
    public NamespaceBuilderSupport(final BuilderSupport builder, final String uri) {
        this(builder, uri, "");
    }
    
    public NamespaceBuilderSupport(final BuilderSupport builder, final String uri, final String prefix) {
        this(builder, uri, prefix, true);
    }
    
    public NamespaceBuilderSupport(final BuilderSupport builder, final String uri, final String prefix, final boolean autoPrefix) {
        this(builder);
        this.nsMap.put(prefix, uri);
        this.autoPrefix = autoPrefix;
    }
    
    public NamespaceBuilderSupport(final BuilderSupport builder, final Map nsMap) {
        this(builder);
        this.nsMap = (Map<String, String>)nsMap;
    }
    
    public NamespaceBuilderSupport namespace(final String namespaceURI) {
        this.nsMap.put("", namespaceURI);
        return this;
    }
    
    public NamespaceBuilderSupport namespace(final String namespaceURI, final String prefix) {
        this.nsMap.put(prefix, namespaceURI);
        return this;
    }
    
    public NamespaceBuilderSupport declareNamespace(final Map nsMap) {
        this.nsMap = (Map<String, String>)nsMap;
        return this;
    }
    
    @Override
    protected Object getCurrent() {
        if (this.builder instanceof NodeBuilder) {
            return InvokerHelper.invokeMethod(this.builder, "getCurrent", null);
        }
        return super.getCurrent();
    }
    
    @Override
    protected void setCurrent(final Object current) {
        if (this.builder instanceof NodeBuilder) {
            InvokerHelper.invokeMethod(this.builder, "setCurrent", current);
        }
        else {
            super.setCurrent(current);
        }
    }
    
    @Override
    protected void setParent(final Object parent, final Object child) {
    }
    
    @Override
    protected Object getName(final String methodName) {
        String prefix = this.autoPrefix ? this.nsMap.keySet().iterator().next() : "";
        String localPart = methodName;
        final int idx = methodName.indexOf(58);
        if (idx > 0) {
            prefix = methodName.substring(0, idx);
            localPart = methodName.substring(idx + 1);
        }
        String namespaceURI = this.nsMap.get(prefix);
        if (namespaceURI == null) {
            namespaceURI = "";
            prefix = "";
        }
        return new QName(namespaceURI, localPart, prefix);
    }
    
    @Override
    protected Object createNode(final Object name) {
        return name;
    }
    
    @Override
    protected Object createNode(final Object name, final Object value) {
        return name;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes) {
        return name;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        return name;
    }
}
