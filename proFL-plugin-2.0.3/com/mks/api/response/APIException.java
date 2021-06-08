// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.response;

import java.io.PrintWriter;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import com.mks.api.response.modifiable.ModifiableField;
import java.util.ArrayList;
import com.mks.api.response.impl.SimpleResponseFactory;
import java.util.List;

public class APIException extends Exception implements FieldContainer
{
    protected String message;
    protected transient List fields;
    protected transient Response response;
    protected boolean showStackTrace;
    private transient SimpleResponseFactory factory;
    protected Throwable cause;
    
    public APIException() {
        this((Throwable)null);
    }
    
    public APIException(final String msg) {
        this((Throwable)null);
        this.setMessage(msg);
    }
    
    public APIException(final Throwable cause) {
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
        if (this.cause != null && this.cause instanceof APIException) {
            ((APIException)this.cause).setMessage(message);
        }
        else {
            this.message = message;
        }
    }
    
    public Response getResponse() {
        return this.response;
    }
    
    protected void setResponse(final Response response) {
        this.response = response;
    }
    
    public void add(final Field field) {
        this.fields.add(field);
    }
    
    public void addField(final String fieldname, final String fieldvalue) {
        synchronized (this) {
            if (this.factory == null) {
                this.factory = SimpleResponseFactory.getResponseFactory();
            }
        }
        final ModifiableField field = this.factory.createField(fieldname);
        field.setDataType("java.lang.String");
        field.setValue(fieldvalue);
        this.add(field);
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
        if (this.cause != null && this.cause instanceof APIException) {
            ((APIException)this.cause).setShowStackTrace(showStackTrace);
        }
        else {
            this.showStackTrace = showStackTrace;
        }
    }
}
