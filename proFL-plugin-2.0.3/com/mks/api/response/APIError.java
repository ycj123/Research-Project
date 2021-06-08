// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

public class APIError extends Error implements FieldContainer
{
    protected String message;
    protected List fields;
    protected boolean showStackTrace;
    protected Throwable cause;
    
    public APIError() {
        this((Throwable)null);
    }
    
    public APIError(final String msg) {
        this((Throwable)null);
        this.setMessage(msg);
    }
    
    public APIError(final Throwable cause) {
        this.cause = cause;
        this.fields = new ArrayList();
        this.showStackTrace = true;
    }
    
    public String getExceptionId() {
        if (this.contains("exception-name")) {
            return this.getField("exception-name").getValueAsString();
        }
        return null;
    }
    
    public String getMessage() {
        if (this.cause != null) {
            return this.cause.getMessage();
        }
        return this.message;
    }
    
    public void setMessage(final String message) {
        if (this.cause != null && this.cause instanceof APIError) {
            ((APIError)this.cause).setMessage(message);
        }
        else {
            this.message = message;
        }
    }
    
    public void add(final Field field) {
        this.fields.add(field);
    }
    
    public Field getField(final String name) {
        for (final Field f : this.fields) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        throw new NoSuchElementException(name);
    }
    
    public Field getField(final int idx) {
        return this.fields.get(idx);
    }
    
    public Iterator getFields() {
        return this.fields.iterator();
    }
    
    public int getFieldListSize() {
        return this.fields.size();
    }
    
    public boolean contains(final String id) {
        for (final Field f : this.fields) {
            if (f.getName().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    public void printStackTrace() {
        if (this.cause != null) {
            if (this.cause instanceof APIException || this.showStackTrace) {
                this.cause.printStackTrace();
            }
        }
        else if (this.showStackTrace) {
            super.printStackTrace();
        }
    }
    
    public void printStackTrace(final PrintStream s) {
        if (this.cause != null) {
            if (this.cause instanceof APIException || this.showStackTrace) {
                this.cause.printStackTrace(s);
            }
        }
        else if (this.showStackTrace) {
            super.printStackTrace(s);
        }
    }
    
    public void printStackTrace(final PrintWriter s) {
        if (this.cause != null) {
            if (this.cause instanceof APIException || this.showStackTrace) {
                this.cause.printStackTrace(s);
            }
        }
        else if (this.showStackTrace) {
            super.printStackTrace(s);
        }
    }
    
    protected void setShowStackTrace(final boolean showStackTrace) {
        if (this.cause != null && this.cause instanceof APIError) {
            ((APIError)this.cause).setShowStackTrace(showStackTrace);
        }
        else {
            this.showStackTrace = showStackTrace;
        }
    }
}
