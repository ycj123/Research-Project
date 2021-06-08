// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import groovy.lang.Closure;
import java.util.Iterator;
import java.util.Map;
import groovy.lang.MissingPropertyException;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;
import groovy.lang.GroovyObjectSupport;

public class GroovyResultSetExtension extends GroovyObjectSupport
{
    private boolean updated;
    private final ResultSet resultSet;
    
    protected ResultSet getResultSet() throws SQLException {
        return this.resultSet;
    }
    
    public GroovyResultSetExtension(final ResultSet set) {
        this.updated = false;
        this.resultSet = set;
    }
    
    @Override
    public String toString() {
        try {
            final StringBuffer sb = new StringBuffer("[");
            final ResultSetMetaData metaData = this.resultSet.getMetaData();
            for (int count = metaData.getColumnCount(), i = 1; i <= count; ++i) {
                sb.append(metaData.getColumnName(i));
                sb.append(":");
                final Object object = this.resultSet.getObject(i);
                if (object != null) {
                    sb.append(object.toString());
                }
                else {
                    sb.append("[null]");
                }
                if (i < count) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
        catch (SQLException e) {
            return super.toString();
        }
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        try {
            return InvokerHelper.invokeMethod(this.getResultSet(), name, args);
        }
        catch (SQLException se) {
            throw new InvokerInvocationException(se);
        }
    }
    
    @Override
    public Object getProperty(final String columnName) {
        try {
            return this.getResultSet().getObject(columnName);
        }
        catch (SQLException e) {
            throw new MissingPropertyException(columnName, GroovyResultSetProxy.class, e);
        }
    }
    
    @Override
    public void setProperty(final String columnName, final Object newValue) {
        try {
            this.getResultSet().updateObject(columnName, newValue);
            this.updated = true;
        }
        catch (SQLException e) {
            throw new MissingPropertyException(columnName, GroovyResultSetProxy.class, e);
        }
    }
    
    public Object getAt(int index) throws SQLException {
        index = this.normalizeIndex(index);
        return this.getResultSet().getObject(index);
    }
    
    public void putAt(int index, final Object newValue) throws SQLException {
        index = this.normalizeIndex(index);
        this.getResultSet().updateObject(index, newValue);
    }
    
    public void add(final Map values) throws SQLException {
        this.getResultSet().moveToInsertRow();
        for (final Map.Entry entry : values.entrySet()) {
            this.getResultSet().updateObject(entry.getKey().toString(), entry.getValue());
        }
        this.getResultSet().insertRow();
    }
    
    protected int normalizeIndex(int index) throws SQLException {
        if (index < 0) {
            final int columnCount = this.getResultSet().getMetaData().getColumnCount();
            do {
                index += columnCount;
            } while (index < 0);
        }
        return index + 1;
    }
    
    public void eachRow(final Closure closure) throws SQLException {
        while (this.next()) {
            closure.call(this);
        }
    }
    
    public boolean next() throws SQLException {
        if (this.updated) {
            this.getResultSet().updateRow();
            this.updated = false;
        }
        return this.getResultSet().next();
    }
    
    public boolean previous() throws SQLException {
        if (this.updated) {
            this.getResultSet().updateRow();
            this.updated = false;
        }
        return this.getResultSet().previous();
    }
}
