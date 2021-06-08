// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import groovy.lang.Closure;
import java.util.Map;
import java.sql.SQLException;
import java.sql.ResultSet;
import groovy.lang.GroovyObject;

public interface GroovyResultSet extends GroovyObject, ResultSet
{
    Object getAt(final int p0) throws SQLException;
    
    Object getAt(final String p0);
    
    void putAt(final int p0, final Object p1) throws SQLException;
    
    void putAt(final String p0, final Object p1);
    
    void add(final Map p0) throws SQLException;
    
    void eachRow(final Closure p0) throws SQLException;
}
