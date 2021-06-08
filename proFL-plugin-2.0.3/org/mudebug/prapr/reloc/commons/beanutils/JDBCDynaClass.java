// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

abstract class JDBCDynaClass implements DynaClass, Serializable
{
    protected boolean lowerCase;
    protected DynaProperty[] properties;
    protected Map propertiesMap;
    
    JDBCDynaClass() {
        this.lowerCase = true;
        this.properties = null;
        this.propertiesMap = new HashMap();
    }
    
    public String getName() {
        return this.getClass().getName();
    }
    
    public DynaProperty getDynaProperty(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("No property name specified");
        }
        return this.propertiesMap.get(name);
    }
    
    public DynaProperty[] getDynaProperties() {
        return this.properties;
    }
    
    public DynaBean newInstance() throws IllegalAccessException, InstantiationException {
        throw new UnsupportedOperationException("newInstance() not supported");
    }
    
    protected Class loadClass(final String className) throws SQLException {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            if (cl == null) {
                cl = this.getClass().getClassLoader();
            }
            return cl.loadClass(className);
        }
        catch (Exception e) {
            throw new SQLException("Cannot load column class '" + className + "': " + e);
        }
    }
    
    protected DynaProperty createDynaProperty(final ResultSetMetaData metadata, final int i) throws SQLException {
        String name = null;
        if (this.lowerCase) {
            name = metadata.getColumnName(i).toLowerCase();
        }
        else {
            name = metadata.getColumnName(i);
        }
        String className = null;
        try {
            className = metadata.getColumnClassName(i);
        }
        catch (SQLException e) {}
        Class clazz = Object.class;
        if (className != null) {
            clazz = this.loadClass(className);
        }
        return new DynaProperty(name, clazz);
    }
    
    protected void introspect(final ResultSet resultSet) throws SQLException {
        final ArrayList list = new ArrayList();
        final ResultSetMetaData metadata = resultSet.getMetaData();
        for (int n = metadata.getColumnCount(), i = 1; i <= n; ++i) {
            final DynaProperty dynaProperty = this.createDynaProperty(metadata, i);
            if (dynaProperty != null) {
                list.add(dynaProperty);
            }
        }
        this.properties = list.toArray(new DynaProperty[list.size()]);
        for (int j = 0; j < this.properties.length; ++j) {
            this.propertiesMap.put(this.properties[j].getName(), this.properties[j]);
        }
    }
}
