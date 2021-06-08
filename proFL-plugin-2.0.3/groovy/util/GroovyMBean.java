// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import groovy.lang.GroovyRuntimeException;
import java.util.Iterator;
import javax.management.MBeanAttributeInfo;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import javax.management.MBeanParameterInfo;
import javax.management.Attribute;
import javax.management.MBeanException;
import javax.management.MBeanOperationInfo;
import java.util.HashMap;
import java.io.IOException;
import javax.management.JMException;
import java.util.Map;
import javax.management.MBeanInfo;
import javax.management.ObjectName;
import javax.management.MBeanServerConnection;
import groovy.lang.GroovyObjectSupport;

public class GroovyMBean extends GroovyObjectSupport
{
    private final MBeanServerConnection server;
    private final ObjectName name;
    private MBeanInfo beanInfo;
    private final boolean ignoreErrors;
    private final Map operations;
    
    public GroovyMBean(final MBeanServerConnection server, final String objectName) throws JMException, IOException {
        this(server, objectName, false);
    }
    
    public GroovyMBean(final MBeanServerConnection server, final String objectName, final boolean ignoreErrors) throws JMException, IOException {
        this(server, new ObjectName(objectName), ignoreErrors);
    }
    
    public GroovyMBean(final MBeanServerConnection server, final ObjectName name) throws JMException, IOException {
        this(server, name, false);
    }
    
    public GroovyMBean(final MBeanServerConnection server, final ObjectName name, final boolean ignoreErrors) throws JMException, IOException {
        this.operations = new HashMap();
        this.server = server;
        this.name = name;
        this.ignoreErrors = ignoreErrors;
        this.beanInfo = server.getMBeanInfo(name);
        final MBeanOperationInfo[] operationInfos = this.beanInfo.getOperations();
        for (int i = 0; i < operationInfos.length; ++i) {
            final MBeanOperationInfo info = operationInfos[i];
            final String[] signature = this.createSignature(info);
            final String operationKey = this.createOperationKey(info.getName(), signature.length);
            this.operations.put(operationKey, signature);
        }
    }
    
    public MBeanServerConnection server() {
        return this.server;
    }
    
    public ObjectName name() {
        return this.name;
    }
    
    public MBeanInfo info() {
        return this.beanInfo;
    }
    
    @Override
    public Object getProperty(final String property) {
        try {
            return this.server.getAttribute(this.name, property);
        }
        catch (MBeanException e) {
            this.throwExceptionWithTarget("Could not access property: " + property + ". Reason: ", e);
        }
        catch (Exception e2) {
            if (!this.ignoreErrors) {
                this.throwException("Could not access property: " + property + ". Reason: ", e2);
            }
        }
        return null;
    }
    
    @Override
    public void setProperty(final String property, final Object value) {
        try {
            this.server.setAttribute(this.name, new Attribute(property, value));
        }
        catch (MBeanException e) {
            this.throwExceptionWithTarget("Could not set property: " + property + ". Reason: ", e);
        }
        catch (Exception e2) {
            this.throwException("Could not set property: " + property + ". Reason: ", e2);
        }
    }
    
    @Override
    public Object invokeMethod(final String method, final Object arguments) {
        Object[] argArray = null;
        if (arguments instanceof Object[]) {
            argArray = (Object[])arguments;
        }
        else {
            argArray = new Object[] { arguments };
        }
        final String operationKey = this.createOperationKey(method, argArray.length);
        final String[] signature = this.operations.get(operationKey);
        if (signature != null) {
            try {
                return this.server.invoke(this.name, method, argArray, signature);
            }
            catch (MBeanException e) {
                this.throwExceptionWithTarget("Could not invoke method: " + method + ". Reason: ", e);
            }
            catch (Exception e2) {
                this.throwException("Could not invoke method: " + method + ". Reason: ", e2);
            }
            return null;
        }
        return super.invokeMethod(method, arguments);
    }
    
    protected String[] createSignature(final MBeanOperationInfo info) {
        final MBeanParameterInfo[] params = info.getSignature();
        final String[] answer = new String[params.length];
        for (int i = 0; i < params.length; ++i) {
            answer[i] = params[i].getType();
        }
        return answer;
    }
    
    protected String createOperationKey(final String operation, final int params) {
        return operation + "_" + params;
    }
    
    public Collection listAttributeNames() {
        final List list = new ArrayList();
        try {
            final MBeanAttributeInfo[] attrs = this.beanInfo.getAttributes();
            for (int i = 0; i < attrs.length; ++i) {
                final MBeanAttributeInfo attr = attrs[i];
                list.add(attr.getName());
            }
        }
        catch (Exception e) {
            this.throwException("Could not list attribute names. Reason: ", e);
        }
        return list;
    }
    
    public List listAttributeValues() {
        final List list = new ArrayList();
        final Collection names = this.listAttributeNames();
        for (final String name : names) {
            try {
                final Object val = this.getProperty(name);
                if (val == null) {
                    continue;
                }
                list.add(name + " : " + val.toString());
            }
            catch (Exception e) {
                this.throwException("Could not list attribute values. Reason: ", e);
            }
        }
        return list;
    }
    
    public Collection listAttributeDescriptions() {
        final List list = new ArrayList();
        try {
            final MBeanAttributeInfo[] attrs = this.beanInfo.getAttributes();
            for (int i = 0; i < attrs.length; ++i) {
                final MBeanAttributeInfo attr = attrs[i];
                list.add(this.describeAttribute(attr));
            }
        }
        catch (Exception e) {
            this.throwException("Could not list attribute descriptions. Reason: ", e);
        }
        return list;
    }
    
    protected String describeAttribute(final MBeanAttributeInfo attr) {
        final StringBuffer buf = new StringBuffer();
        buf.append("(");
        if (attr.isReadable()) {
            buf.append("r");
        }
        if (attr.isWritable()) {
            buf.append("w");
        }
        buf.append(") ").append(attr.getType()).append(" ").append(attr.getName());
        return buf.toString();
    }
    
    public String describeAttribute(final String attributeName) {
        final String ret = "Attribute not found";
        try {
            final MBeanAttributeInfo[] attributes = this.beanInfo.getAttributes();
            for (int i = 0; i < attributes.length; ++i) {
                final MBeanAttributeInfo attribute = attributes[i];
                if (attribute.getName().equals(attributeName)) {
                    return this.describeAttribute(attribute);
                }
            }
        }
        catch (Exception e) {
            this.throwException("Could not describe attribute '" + attributeName + "'. Reason: ", e);
        }
        return ret;
    }
    
    public Collection listOperationNames() {
        final List list = new ArrayList();
        try {
            final MBeanOperationInfo[] operations = this.beanInfo.getOperations();
            for (int i = 0; i < operations.length; ++i) {
                final MBeanOperationInfo operation = operations[i];
                list.add(operation.getName());
            }
        }
        catch (Exception e) {
            this.throwException("Could not list operation names. Reason: ", e);
        }
        return list;
    }
    
    public Collection listOperationDescriptions() {
        final List list = new ArrayList();
        try {
            final MBeanOperationInfo[] operations = this.beanInfo.getOperations();
            for (int i = 0; i < operations.length; ++i) {
                final MBeanOperationInfo operation = operations[i];
                list.add(this.describeOperation(operation));
            }
        }
        catch (Exception e) {
            this.throwException("Could not list operation descriptions. Reason: ", e);
        }
        return list;
    }
    
    public List describeOperation(final String operationName) {
        final List list = new ArrayList();
        try {
            final MBeanOperationInfo[] operations = this.beanInfo.getOperations();
            for (int i = 0; i < operations.length; ++i) {
                final MBeanOperationInfo operation = operations[i];
                if (operation.getName().equals(operationName)) {
                    list.add(this.describeOperation(operation));
                }
            }
        }
        catch (Exception e) {
            this.throwException("Could not describe operations matching name '" + operationName + "'. Reason: ", e);
        }
        return list;
    }
    
    protected String describeOperation(final MBeanOperationInfo operation) {
        final StringBuffer buf = new StringBuffer();
        buf.append(operation.getReturnType()).append(" ").append(operation.getName()).append("(");
        final MBeanParameterInfo[] params = operation.getSignature();
        for (int j = 0; j < params.length; ++j) {
            final MBeanParameterInfo param = params[j];
            if (j != 0) {
                buf.append(", ");
            }
            buf.append(param.getType()).append(" ").append(param.getName());
        }
        buf.append(")");
        return buf.toString();
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("MBean Name:").append("\n  ").append(this.name.getCanonicalName()).append("\n  ");
        if (!this.listAttributeDescriptions().isEmpty()) {
            buf.append("\nAttributes:");
            final Iterator iterator = this.listAttributeDescriptions().iterator();
            while (iterator.hasNext()) {
                buf.append("\n  ").append(iterator.next());
            }
        }
        if (!this.listOperationDescriptions().isEmpty()) {
            buf.append("\nOperations:");
            final Iterator iterator = this.listOperationDescriptions().iterator();
            while (iterator.hasNext()) {
                buf.append("\n  ").append(iterator.next());
            }
        }
        return buf.toString();
    }
    
    private void throwException(final String m, final Exception e) {
        if (!this.ignoreErrors) {
            throw new GroovyRuntimeException(m + e, e);
        }
    }
    
    private void throwExceptionWithTarget(final String m, final MBeanException e) {
        if (!this.ignoreErrors) {
            throw new GroovyRuntimeException(m + e, e.getTargetException());
        }
    }
}
