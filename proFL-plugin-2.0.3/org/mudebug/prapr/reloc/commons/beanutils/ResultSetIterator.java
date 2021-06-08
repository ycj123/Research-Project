// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.beanutils;

import java.util.NoSuchElementException;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultSetIterator implements DynaBean, Iterator
{
    protected boolean current;
    protected ResultSetDynaClass dynaClass;
    protected boolean eof;
    
    ResultSetIterator(final ResultSetDynaClass dynaClass) {
        this.current = false;
        this.dynaClass = null;
        this.eof = false;
        this.dynaClass = dynaClass;
    }
    
    public boolean contains(final String name, final String key) {
        throw new UnsupportedOperationException("FIXME - mapped properties not currently supported");
    }
    
    public Object get(final String name) {
        if (this.dynaClass.getDynaProperty(name) == null) {
            throw new IllegalArgumentException(name);
        }
        try {
            return this.dynaClass.getResultSet().getObject(name);
        }
        catch (SQLException e) {
            throw new RuntimeException("get(" + name + "): SQLException: " + e);
        }
    }
    
    public Object get(final String name, final int index) {
        throw new UnsupportedOperationException("FIXME - indexed properties not currently supported");
    }
    
    public Object get(final String name, final String key) {
        throw new UnsupportedOperationException("FIXME - mapped properties not currently supported");
    }
    
    public DynaClass getDynaClass() {
        return this.dynaClass;
    }
    
    public void remove(final String name, final String key) {
        throw new UnsupportedOperationException("FIXME - mapped operations not currently supported");
    }
    
    public void set(final String name, final Object value) {
        if (this.dynaClass.getDynaProperty(name) == null) {
            throw new IllegalArgumentException(name);
        }
        try {
            this.dynaClass.getResultSet().updateObject(name, value);
        }
        catch (SQLException e) {
            throw new RuntimeException("set(" + name + "): SQLException: " + e);
        }
    }
    
    public void set(final String name, final int index, final Object value) {
        throw new UnsupportedOperationException("FIXME - indexed properties not currently supported");
    }
    
    public void set(final String name, final String key, final Object value) {
        throw new UnsupportedOperationException("FIXME - mapped properties not currently supported");
    }
    
    public boolean hasNext() {
        try {
            this.advance();
            return !this.eof;
        }
        catch (SQLException e) {
            throw new RuntimeException("hasNext():  SQLException:  " + e);
        }
    }
    
    public Object next() {
        try {
            this.advance();
            if (this.eof) {
                throw new NoSuchElementException();
            }
            this.current = false;
            return this;
        }
        catch (SQLException e) {
            throw new RuntimeException("next():  SQLException:  " + e);
        }
    }
    
    public void remove() {
        throw new UnsupportedOperationException("remove()");
    }
    
    protected void advance() throws SQLException {
        if (!this.current && !this.eof) {
            if (this.dynaClass.getResultSet().next()) {
                this.current = true;
                this.eof = false;
            }
            else {
                this.current = false;
                this.eof = true;
            }
        }
    }
}
