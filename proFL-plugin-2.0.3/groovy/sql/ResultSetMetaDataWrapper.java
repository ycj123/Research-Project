// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import groovy.lang.ReadOnlyPropertyException;
import groovy.lang.MissingPropertyException;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.sql.ResultSetMetaData;
import groovy.lang.GroovyObjectSupport;

public class ResultSetMetaDataWrapper extends GroovyObjectSupport
{
    private ResultSetMetaData target;
    private int index;
    
    public ResultSetMetaDataWrapper(final ResultSetMetaData target, final int index) {
        this.target = target;
        this.index = index;
    }
    
    private Object[] getIndexedArgs(final Object[] originalArgs) {
        final Object[] result = new Object[originalArgs.length + 1];
        result[0] = this.index;
        for (int i = 0, originalArgsLength = originalArgs.length; i < originalArgsLength; ++i) {
            result[i + 1] = originalArgs[i];
        }
        return result;
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        final Object[] indexedArgs = this.getIndexedArgs((Object[])args);
        return InvokerHelper.invokeMethod(this.target, name, indexedArgs);
    }
    
    private String getPropertyGetterName(final String prop) {
        if (prop == null || prop.length() < 1) {
            throw new MissingPropertyException(prop, this.target.getClass());
        }
        return "get" + prop.substring(0, 1).toUpperCase() + prop.substring(1);
    }
    
    @Override
    public Object getProperty(final String property) {
        return this.invokeMethod(this.getPropertyGetterName(property), new Object[0]);
    }
    
    @Override
    public void setProperty(final String property, final Object newValue) {
        throw new ReadOnlyPropertyException(property, this.target.getClass());
    }
}
