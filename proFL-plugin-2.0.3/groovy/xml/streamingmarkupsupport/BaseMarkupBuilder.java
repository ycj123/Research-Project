// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import groovy.lang.GroovyObjectSupport;
import java.util.Collections;
import java.util.HashMap;
import groovy.lang.GroovyInterceptable;
import groovy.lang.Closure;
import java.util.Map;

public class BaseMarkupBuilder extends Builder
{
    public BaseMarkupBuilder(final Map namespaceMethodMap) {
        super(namespaceMethodMap);
    }
    
    @Override
    public Object bind(final Closure root) {
        return new Document(root, this.namespaceMethodMap);
    }
    
    private static class Document extends Built implements GroovyInterceptable
    {
        private Object out;
        private final Map pendingNamespaces;
        private final Map namespaces;
        private final Map specialProperties;
        private String prefix;
        
        public Document(final Closure root, final Map namespaceMethodMap) {
            super(root, namespaceMethodMap);
            this.pendingNamespaces = new HashMap();
            this.namespaces = new HashMap();
            this.specialProperties = new HashMap();
            this.prefix = "";
            this.namespaces.put("xml", "http://www.w3.org/XML/1998/namespace");
            this.namespaces.put("mkp", "http://www.codehaus.org/Groovy/markup/keywords");
            this.specialProperties.put("out", new OutputSink("out") {
                @Override
                public Object leftShift(final Object value) {
                    return this.leftShift("yield", value);
                }
            });
            this.specialProperties.put("unescaped", new OutputSink("unescaped") {
                @Override
                public Object leftShift(final Object value) {
                    return this.leftShift("yieldUnescaped", value);
                }
            });
            this.specialProperties.put("namespaces", new OutputSink("namespaces") {
                @Override
                public Object leftShift(final Object value) {
                    return this.leftShift("declareNamespace", value);
                }
            });
            this.specialProperties.put("pi", new OutputSink("pi") {
                @Override
                public Object leftShift(final Object value) {
                    return this.leftShift("pi", value);
                }
            });
            this.specialProperties.put("comment", new OutputSink("comment") {
                @Override
                public Object leftShift(final Object value) {
                    return this.leftShift("comment", value);
                }
            });
        }
        
        @Override
        public Object invokeMethod(final String name, final Object args) {
            final Object[] arguments = (Object[])args;
            Map attrs = Collections.EMPTY_MAP;
            Object body = null;
            for (int i = 0; i != arguments.length; ++i) {
                final Object arg = arguments[i];
                if (arg instanceof Map) {
                    attrs = (Map)arg;
                }
                else if (arg instanceof Closure) {
                    final Closure c = (Closure)arg;
                    c.setDelegate(this);
                    body = c.asWritable();
                }
                else {
                    body = arg;
                }
            }
            Object uri;
            if (this.pendingNamespaces.containsKey(this.prefix)) {
                uri = this.pendingNamespaces.get(this.prefix);
            }
            else if (this.namespaces.containsKey(this.prefix)) {
                uri = this.namespaces.get(this.prefix);
            }
            else {
                uri = ":";
            }
            final Object[] info = this.namespaceSpecificTags.get(uri);
            final Map tagMap = (Map)info[2];
            final Closure defaultTagClosure = (Closure)info[0];
            final String prefix = this.prefix;
            this.prefix = "";
            if (tagMap.containsKey(name)) {
                return tagMap.get(name).call(new Object[] { this, this.pendingNamespaces, this.namespaces, this.namespaceSpecificTags, prefix, attrs, body, this.out });
            }
            return defaultTagClosure.call(new Object[] { name, this, this.pendingNamespaces, this.namespaces, this.namespaceSpecificTags, prefix, attrs, body, this.out });
        }
        
        @Override
        public Object getProperty(final String property) {
            final Object special = this.specialProperties.get(property);
            if (special == null) {
                this.prefix = property;
                return this;
            }
            return special;
        }
        
        @Override
        public void setProperty(final String property, final Object newValue) {
            if ("trigger".equals(property)) {
                this.out = newValue;
                this.root.call(this);
            }
            else {
                super.setProperty(property, newValue);
            }
        }
        
        private abstract class OutputSink extends GroovyObjectSupport
        {
            private final String name;
            
            public OutputSink(final String name) {
                this.name = name;
            }
            
            @Override
            public Object invokeMethod(final String name, final Object args) {
                Document.this.prefix = this.name;
                return Document.this.invokeMethod(name, args);
            }
            
            public abstract Object leftShift(final Object p0);
            
            protected Object leftShift(final String command, final Object value) {
                Document.this.getProperty("mkp");
                Document.this.invokeMethod(command, new Object[] { value });
                return this;
            }
        }
    }
}
