// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.logging.impl;

import org.mudebug.prapr.reloc.commons.logging.LogConfigurationException;
import org.apache.log4j.Logger;
import org.mudebug.prapr.reloc.commons.logging.Log;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;

public final class Log4jFactory extends LogFactory
{
    private Hashtable attributes;
    private Hashtable instances;
    
    public Log4jFactory() {
        this.attributes = new Hashtable();
        this.instances = new Hashtable();
    }
    
    public Object getAttribute(final String name) {
        return this.attributes.get(name);
    }
    
    public String[] getAttributeNames() {
        final Vector names = new Vector();
        final Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            names.addElement(keys.nextElement());
        }
        final String[] results = new String[names.size()];
        for (int i = 0; i < results.length; ++i) {
            results[i] = names.elementAt(i);
        }
        return results;
    }
    
    public Log getInstance(final Class clazz) throws LogConfigurationException {
        Log instance = this.instances.get(clazz);
        if (instance != null) {
            return instance;
        }
        instance = new Log4JLogger(Logger.getLogger(clazz));
        this.instances.put(clazz, instance);
        return instance;
    }
    
    public Log getInstance(final String name) throws LogConfigurationException {
        Log instance = this.instances.get(name);
        if (instance != null) {
            return instance;
        }
        instance = new Log4JLogger(Logger.getLogger(name));
        this.instances.put(name, instance);
        return instance;
    }
    
    public void release() {
        this.instances.clear();
    }
    
    public void removeAttribute(final String name) {
        this.attributes.remove(name);
    }
    
    public void setAttribute(final String name, final Object value) {
        if (value == null) {
            this.attributes.remove(name);
        }
        else {
            this.attributes.put(name, value);
        }
    }
}
