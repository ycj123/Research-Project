// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Option
{
    private static final String FLAG_PREFIX = "-";
    private static final String OPTION_PREFIX = "--";
    private String name;
    private String canonicalName;
    private List values;
    private String separator;
    
    public Option(final String name) {
        this(name, (String)null);
    }
    
    public Option(final String name, final MultiValue mv) {
        this(name, mv.toString());
    }
    
    public Option(final String name, final String value) {
        if (name != null && name.startsWith("--")) {
            this.name = name.substring(2);
        }
        else if (name != null && name.startsWith("-")) {
            this.name = name.substring(1);
        }
        else {
            this.name = name;
        }
        this.canonicalName = ((name == null) ? null : name.toLowerCase());
        this.separator = ",";
        this.values = new LinkedList();
        if (value != null) {
            this.values.add(value);
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        if (this.values.size() < 1) {
            return null;
        }
        final StringBuffer value = new StringBuffer("");
        final Iterator it = this.values.iterator();
        if (it.hasNext()) {
            if (value.length() > 0) {
                value.append(this.separator);
            }
            value.append(it.next());
        }
        while (it.hasNext()) {
            value.append(this.separator);
            value.append(it.next());
        }
        return value.toString();
    }
    
    public void add(final String value) {
        this.values.add(value);
    }
    
    public void add(final Collection values) {
        this.values.addAll(values);
    }
    
    public void add(final MultiValue mv) {
        this.add(mv.toString());
    }
    
    public String getSeparator() {
        return this.separator;
    }
    
    public void setSeparator(final String separator) {
        if (!this.separator.equals(separator)) {
            this.separator = separator;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        boolean isFlag = false;
        if (this.name.length() == 1) {
            sb.append("-");
            sb.append(this.name);
            isFlag = true;
        }
        else {
            sb.append("--");
            sb.append(this.name);
        }
        final String value = this.getValue();
        if (value != null) {
            if (isFlag) {
                sb.append(" ");
            }
            else {
                sb.append("=");
            }
            sb.append(value);
        }
        return sb.toString();
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Option other = (Option)o;
        return (this.canonicalName == other.canonicalName || (this.canonicalName != null && this.canonicalName.equals(other.canonicalName))) && (this.separator == other.separator || (this.separator != null && this.separator.equals(other.separator))) && (this.values == other.values || (this.values != null && this.values.equals(other.values)));
    }
    
    public int hashCode() {
        return this.canonicalName.hashCode() ^ this.values.hashCode() ^ this.separator.hashCode();
    }
}
