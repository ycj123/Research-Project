// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

public class Parameter
{
    private String alias;
    private String name;
    private String type;
    private boolean required;
    private boolean editable;
    private String description;
    private String expression;
    private String deprecated;
    private String defaultValue;
    private String implementation;
    private Requirement requirement;
    private String since;
    
    public Parameter() {
        this.editable = true;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
    
    public boolean isRequired() {
        return this.required;
    }
    
    public void setRequired(final boolean required) {
        this.required = required;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getExpression() {
        return this.expression;
    }
    
    public void setExpression(final String expression) {
        this.expression = expression;
    }
    
    public String getDeprecated() {
        return this.deprecated;
    }
    
    public void setDeprecated(final String deprecated) {
        this.deprecated = deprecated;
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    public boolean equals(final Object other) {
        return other instanceof Parameter && this.getName().equals(((Parameter)other).getName());
    }
    
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(final String alias) {
        this.alias = alias;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
    
    public void setEditable(final boolean editable) {
        this.editable = editable;
    }
    
    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public String toString() {
        return "Mojo parameter [name: '" + this.getName() + "'; alias: '" + this.getAlias() + "']";
    }
    
    public Requirement getRequirement() {
        return this.requirement;
    }
    
    public void setRequirement(final Requirement requirement) {
        this.requirement = requirement;
    }
    
    public String getImplementation() {
        return this.implementation;
    }
    
    public void setImplementation(final String implementation) {
        this.implementation = implementation;
    }
    
    public String getSince() {
        return this.since;
    }
    
    public void setSince(final String since) {
        this.since = since;
    }
}
