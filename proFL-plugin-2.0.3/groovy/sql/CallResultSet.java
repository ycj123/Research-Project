// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.CallableStatement;

class CallResultSet extends GroovyResultSetExtension
{
    int indx;
    CallableStatement call;
    ResultSet resultSet;
    boolean firstCall;
    
    CallResultSet(final CallableStatement call, final int indx) {
        super(null);
        this.firstCall = true;
        this.call = call;
        this.indx = indx;
    }
    
    @Override
    protected ResultSet getResultSet() throws SQLException {
        if (this.firstCall) {
            this.resultSet = (ResultSet)this.call.getObject(this.indx + 1);
            this.firstCall = false;
        }
        return this.resultSet;
    }
    
    protected static GroovyResultSet getImpl(final CallableStatement call, final int idx) {
        final GroovyResultSetProxy proxy = new GroovyResultSetProxy(new CallResultSet(call, idx));
        return proxy.getImpl();
    }
}
