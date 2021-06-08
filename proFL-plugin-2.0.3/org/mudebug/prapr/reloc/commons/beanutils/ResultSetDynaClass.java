// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.Iterator;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ResultSetDynaClass extends JDBCDynaClass implements DynaClass
{
    protected ResultSet resultSet;
    
    public ResultSetDynaClass(final ResultSet resultSet) throws SQLException {
        this(resultSet, true);
    }
    
    public ResultSetDynaClass(final ResultSet resultSet, final boolean lowerCase) throws SQLException {
        this.resultSet = null;
        if (resultSet == null) {
            throw new NullPointerException();
        }
        this.resultSet = resultSet;
        this.lowerCase = lowerCase;
        this.introspect(resultSet);
    }
    
    public Iterator iterator() {
        return new ResultSetIterator(this);
    }
    
    ResultSet getResultSet() {
        return this.resultSet;
    }
    
    protected Class loadClass(final String className) throws SQLException {
        try {
            return this.getClass().getClassLoader().loadClass(className);
        }
        catch (Exception e) {
            throw new SQLException("Cannot load column class '" + className + "': " + e);
        }
    }
}
