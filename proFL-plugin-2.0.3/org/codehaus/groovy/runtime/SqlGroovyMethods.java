// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import groovy.lang.GroovyRuntimeException;
import groovy.sql.ResultSetMetaDataWrapper;
import java.util.Iterator;
import groovy.sql.GroovyResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.Map;
import java.util.LinkedHashMap;
import groovy.sql.GroovyRowResult;
import java.sql.ResultSet;

public class SqlGroovyMethods
{
    public static GroovyRowResult toRowResult(final ResultSet rs) throws SQLException {
        final ResultSetMetaData metadata = rs.getMetaData();
        final Map<String, Object> lhm = new LinkedHashMap<String, Object>(metadata.getColumnCount(), 1.0f);
        for (int i = 1; i <= metadata.getColumnCount(); ++i) {
            lhm.put(metadata.getColumnLabel(i), rs.getObject(i));
        }
        return new GroovyRowResult(lhm);
    }
    
    public static Timestamp toTimestamp(final Date d) {
        return new Timestamp(d.getTime());
    }
    
    public static boolean asBoolean(final GroovyResultSet grs) {
        return true;
    }
    
    public static Iterator<ResultSetMetaDataWrapper> iterator(final ResultSetMetaData resultSetMetaData) {
        return new ResultSetMetaDataIterator(resultSetMetaData);
    }
    
    private static class ResultSetMetaDataIterator implements Iterator<ResultSetMetaDataWrapper>
    {
        private ResultSetMetaData target;
        private int index;
        
        public ResultSetMetaDataIterator(final ResultSetMetaData target) {
            this.index = 1;
            this.target = target;
        }
        
        public boolean hasNext() {
            try {
                return this.index <= this.target.getColumnCount();
            }
            catch (SQLException ex) {
                throw new GroovyRuntimeException("Unable to obtain column count from ResultSetMetaData", ex);
            }
        }
        
        public ResultSetMetaDataWrapper next() {
            return new ResultSetMetaDataWrapper(this.target, this.index++);
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove from ResultSetMetaData");
        }
    }
}
