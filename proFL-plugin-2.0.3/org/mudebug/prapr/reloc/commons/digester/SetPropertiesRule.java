// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import java.util.Map;
import org.mudebug.prapr.reloc.commons.beanutils.BeanUtils;
import java.util.HashMap;
import org.xml.sax.Attributes;

public class SetPropertiesRule extends Rule
{
    private String[] attributeNames;
    private String[] propertyNames;
    
    public SetPropertiesRule(final Digester digester) {
        this();
    }
    
    public SetPropertiesRule() {
    }
    
    public SetPropertiesRule(final String attributeName, final String propertyName) {
        (this.attributeNames = new String[1])[0] = attributeName;
        (this.propertyNames = new String[1])[0] = propertyName;
    }
    
    public SetPropertiesRule(final String[] attributeNames, final String[] propertyNames) {
        this.attributeNames = new String[attributeNames.length];
        for (int i = 0, size = attributeNames.length; i < size; ++i) {
            this.attributeNames[i] = attributeNames[i];
        }
        this.propertyNames = new String[propertyNames.length];
        for (int j = 0, size2 = propertyNames.length; j < size2; ++j) {
            this.propertyNames[j] = propertyNames[j];
        }
    }
    
    public void begin(final Attributes attributes) throws Exception {
        final HashMap values = new HashMap();
        int attNamesLength = 0;
        if (this.attributeNames != null) {
            attNamesLength = this.attributeNames.length;
        }
        int propNamesLength = 0;
        if (this.propertyNames != null) {
            propNamesLength = this.propertyNames.length;
        }
        for (int i = 0; i < attributes.getLength(); ++i) {
            String name = attributes.getLocalName(i);
            if ("".equals(name)) {
                name = attributes.getQName(i);
            }
            final String value = attributes.getValue(i);
            int n = 0;
            while (n < attNamesLength) {
                if (name.equals(this.attributeNames[n])) {
                    if (n < propNamesLength) {
                        name = this.propertyNames[n];
                        break;
                    }
                    name = null;
                    break;
                }
                else {
                    ++n;
                }
            }
            if (this.digester.log.isDebugEnabled()) {
                this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Setting property '" + name + "' to '" + value + "'");
            }
            if (name != null) {
                values.put(name, value);
            }
        }
        final Object top = this.digester.peek();
        if (this.digester.log.isDebugEnabled()) {
            if (top != null) {
                this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " properties");
            }
            else {
                this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Set NULL properties");
            }
        }
        BeanUtils.populate(top, values);
    }
    
    public void addAlias(final String attributeName, final String propertyName) {
        if (this.attributeNames == null) {
            (this.attributeNames = new String[1])[0] = attributeName;
            (this.propertyNames = new String[1])[0] = propertyName;
        }
        else {
            final int length = this.attributeNames.length;
            final String[] tempAttributes = new String[length + 1];
            for (int i = 0; i < length; ++i) {
                tempAttributes[i] = this.attributeNames[i];
            }
            tempAttributes[length] = attributeName;
            final String[] tempProperties = new String[length + 1];
            for (int j = 0; j < length && j < this.propertyNames.length; ++j) {
                tempProperties[j] = this.propertyNames[j];
            }
            tempProperties[length] = propertyName;
            this.propertyNames = tempProperties;
            this.attributeNames = tempAttributes;
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("SetPropertiesRule[");
        sb.append("]");
        return sb.toString();
    }
}
