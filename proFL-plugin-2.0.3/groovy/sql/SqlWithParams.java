// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import java.util.List;

public class SqlWithParams
{
    private String sql;
    private List<Object> params;
    
    public SqlWithParams(final String sql, final List<Object> params) {
        this.sql = sql;
        this.params = params;
    }
    
    public String getSql() {
        return this.sql;
    }
    
    public List<Object> getParams() {
        return this.params;
    }
}
