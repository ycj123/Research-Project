// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import hidden.org.codehaus.plexus.interpolation.os.OperatingSystemUtils;
import java.io.IOException;
import java.util.Properties;

public class EnvarBasedValueSource implements ValueSource
{
    private Properties envars;
    private final boolean caseSensitive;
    
    public EnvarBasedValueSource() throws IOException {
        this(true);
    }
    
    public EnvarBasedValueSource(final boolean caseSensitive) throws IOException {
        this.caseSensitive = caseSensitive;
        this.envars = OperatingSystemUtils.getSystemEnvVars(caseSensitive);
    }
    
    public Object getValue(final String expression) {
        String expr = expression;
        if (expr.startsWith("env.")) {
            expr = expr.substring("env.".length());
        }
        if (!this.caseSensitive) {
            expr = expr.toUpperCase();
        }
        return this.envars.getProperty(expr);
    }
}
