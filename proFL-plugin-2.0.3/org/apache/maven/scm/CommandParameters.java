// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class CommandParameters implements Serializable
{
    private static final long serialVersionUID = -7346070735958137283L;
    private Map<String, Object> parameters;
    
    public CommandParameters() {
        this.parameters = new HashMap<String, Object>();
    }
    
    public String getString(final CommandParameter parameter) throws ScmException {
        final Object object = this.getObject(String.class, parameter);
        if (object == null) {
            throw new ScmException("Missing parameter: '" + parameter.getName() + "'.");
        }
        return object.toString();
    }
    
    public String getString(final CommandParameter parameter, final String defaultValue) throws ScmException {
        final Object object = this.getObject(String.class, parameter, null);
        if (object == null) {
            return defaultValue;
        }
        return object.toString();
    }
    
    public void setString(final CommandParameter parameter, final String value) throws ScmException {
        this.setObject(parameter, value);
    }
    
    public int getInt(final CommandParameter parameter) throws ScmException {
        return (int)this.getObject(Integer.class, parameter);
    }
    
    public int getInt(final CommandParameter parameter, final int defaultValue) throws ScmException {
        final Integer value = (Integer)this.getObject(Integer.class, parameter, null);
        if (value == null) {
            return defaultValue;
        }
        return value;
    }
    
    public void setInt(final CommandParameter parameter, final int value) throws ScmException {
        this.setObject(parameter, value);
    }
    
    public Date getDate(final CommandParameter parameter) throws ScmException {
        return (Date)this.getObject(Date.class, parameter);
    }
    
    public Date getDate(final CommandParameter parameter, final Date defaultValue) throws ScmException {
        return (Date)this.getObject(Date.class, parameter, defaultValue);
    }
    
    public void setDate(final CommandParameter parameter, final Date date) throws ScmException {
        this.setObject(parameter, date);
    }
    
    public boolean getBoolean(final CommandParameter parameter) throws ScmException {
        return Boolean.valueOf(this.getString(parameter));
    }
    
    public boolean getBoolean(final CommandParameter parameter, final boolean defaultValue) throws ScmException {
        return Boolean.valueOf(this.getString(parameter, Boolean.toString(defaultValue)));
    }
    
    public ScmVersion getScmVersion(final CommandParameter parameter) throws ScmException {
        return (ScmVersion)this.getObject(ScmVersion.class, parameter);
    }
    
    public ScmVersion getScmVersion(final CommandParameter parameter, final ScmVersion defaultValue) throws ScmException {
        return (ScmVersion)this.getObject(ScmVersion.class, parameter, defaultValue);
    }
    
    public void setScmVersion(final CommandParameter parameter, final ScmVersion scmVersion) throws ScmException {
        this.setObject(parameter, scmVersion);
    }
    
    public File[] getFileArray(final CommandParameter parameter) throws ScmException {
        return (File[])this.getObject(File[].class, parameter);
    }
    
    public File[] getFileArray(final CommandParameter parameter, final File[] defaultValue) throws ScmException {
        return (File[])this.getObject(File[].class, parameter, defaultValue);
    }
    
    public ScmTagParameters getScmTagParameters(final CommandParameter parameter) throws ScmException {
        return (ScmTagParameters)this.getObject(ScmTagParameters.class, parameter, new ScmTagParameters());
    }
    
    public void setScmTagParameters(final CommandParameter parameter, final ScmTagParameters scmTagParameters) throws ScmException {
        this.setObject(parameter, scmTagParameters);
    }
    
    public void setScmBranchParameters(final CommandParameter parameter, final ScmBranchParameters scmBranchParameters) throws ScmException {
        this.setObject(parameter, scmBranchParameters);
    }
    
    public ScmBranchParameters getScmBranchParameters(final CommandParameter parameter) throws ScmException {
        return (ScmBranchParameters)this.getObject(ScmBranchParameters.class, parameter, new ScmBranchParameters());
    }
    
    private Object getObject(final Class<?> clazz, final CommandParameter parameter) throws ScmException {
        final Object object = this.getObject(clazz, parameter, null);
        if (object == null) {
            throw new ScmException("Missing parameter: '" + parameter.getName() + "'.");
        }
        return object;
    }
    
    private Object getObject(final Class<?> clazz, final CommandParameter parameter, final Object defaultValue) throws ScmException {
        final Object object = this.parameters.get(parameter.getName());
        if (object == null) {
            return defaultValue;
        }
        if (clazz != null && !clazz.isAssignableFrom(object.getClass())) {
            throw new ScmException("Wrong parameter type for '" + parameter.getName() + ". " + "Expected: " + clazz.getName() + ", got: " + object.getClass().getName());
        }
        return object;
    }
    
    private void setObject(final CommandParameter parameter, final Object value) throws ScmException {
        final Object object = this.getObject(null, parameter, null);
        if (object != null) {
            throw new ScmException("The parameter is already set: " + parameter.getName());
        }
        this.parameters.put(parameter.getName(), value);
    }
    
    public void remove(final CommandParameter parameter) {
        this.parameters.remove(parameter);
    }
}
