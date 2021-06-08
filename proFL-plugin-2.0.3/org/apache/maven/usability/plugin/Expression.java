// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.plugin;

import java.util.Properties;
import java.io.Serializable;

public class Expression implements Serializable
{
    private String syntax;
    private String description;
    private String configuration;
    private Properties cliOptions;
    private Properties apiMethods;
    private String deprecation;
    private String ban;
    private boolean editable;
    private String modelEncoding;
    
    public Expression() {
        this.editable = true;
        this.modelEncoding = "UTF-8";
    }
    
    public void addApiMethod(final String key, final String value) {
        this.getApiMethods().put(key, value);
    }
    
    public void addCliOption(final String key, final String value) {
        this.getCliOptions().put(key, value);
    }
    
    public Properties getApiMethods() {
        if (this.apiMethods == null) {
            this.apiMethods = new Properties();
        }
        return this.apiMethods;
    }
    
    public String getBan() {
        return this.ban;
    }
    
    public Properties getCliOptions() {
        if (this.cliOptions == null) {
            this.cliOptions = new Properties();
        }
        return this.cliOptions;
    }
    
    public String getConfiguration() {
        return this.configuration;
    }
    
    public String getDeprecation() {
        return this.deprecation;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getSyntax() {
        return this.syntax;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
    
    public void setApiMethods(final Properties apiMethods) {
        this.apiMethods = apiMethods;
    }
    
    public void setBan(final String ban) {
        this.ban = ban;
    }
    
    public void setCliOptions(final Properties cliOptions) {
        this.cliOptions = cliOptions;
    }
    
    public void setConfiguration(final String configuration) {
        this.configuration = configuration;
    }
    
    public void setDeprecation(final String deprecation) {
        this.deprecation = deprecation;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setEditable(final boolean editable) {
        this.editable = editable;
    }
    
    public void setSyntax(final String syntax) {
        this.syntax = syntax;
    }
    
    public void setModelEncoding(final String modelEncoding) {
        this.modelEncoding = modelEncoding;
    }
    
    public String getModelEncoding() {
        return this.modelEncoding;
    }
}
