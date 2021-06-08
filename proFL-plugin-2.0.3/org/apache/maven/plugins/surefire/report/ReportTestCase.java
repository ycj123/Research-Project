// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugins.surefire.report;

import java.util.HashMap;
import java.util.Map;

public class ReportTestCase
{
    private String fullClassName;
    private String className;
    private String fullName;
    private String name;
    private float time;
    private Map<String, Object> failure;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getFullClassName() {
        return this.fullClassName;
    }
    
    public void setFullClassName(final String name) {
        this.fullClassName = name;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public void setClassName(final String name) {
        this.className = name;
    }
    
    public float getTime() {
        return this.time;
    }
    
    public void setTime(final float time) {
        this.time = time;
    }
    
    public Map<String, Object> getFailure() {
        return this.failure;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
    
    public void addFailure(final String message, final String type) {
        (this.failure = new HashMap<String, Object>()).put("message", message);
        this.failure.put("type", type);
    }
    
    @Override
    public String toString() {
        return this.fullName;
    }
}
