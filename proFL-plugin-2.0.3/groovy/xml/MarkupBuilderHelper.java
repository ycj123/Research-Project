// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import java.util.HashMap;
import java.util.Map;

public class MarkupBuilderHelper
{
    private MarkupBuilder builder;
    
    public MarkupBuilderHelper(final MarkupBuilder builder) {
        this.builder = builder;
    }
    
    public void yield(final Object value) {
        this.yield(value.toString());
    }
    
    public void yield(final String value) {
        this.builder.yield(value, true);
    }
    
    public void yieldUnescaped(final Object value) {
        this.yieldUnescaped(value.toString());
    }
    
    public void yieldUnescaped(final String value) {
        this.builder.yield(value, false);
    }
    
    public void comment(final String value) {
        this.yieldUnescaped("<!-- " + value + " -->");
    }
    
    public void xmlDeclaration(final Map<String, Object> args) {
        final Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        map.put("xml", args);
        this.pi(map);
    }
    
    public void pi(final Map<String, Map<String, Object>> args) {
        this.builder.pi(args);
    }
}
