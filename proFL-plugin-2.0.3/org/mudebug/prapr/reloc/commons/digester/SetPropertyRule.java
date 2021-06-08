// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import java.beans.PropertyDescriptor;
import org.mudebug.prapr.reloc.commons.beanutils.DynaProperty;
import org.mudebug.prapr.reloc.commons.beanutils.BeanUtils;
import org.mudebug.prapr.reloc.commons.beanutils.PropertyUtils;
import org.mudebug.prapr.reloc.commons.beanutils.DynaBean;
import org.xml.sax.Attributes;

public class SetPropertyRule extends Rule
{
    protected String name;
    protected String value;
    
    public SetPropertyRule(final Digester digester, final String name, final String value) {
        this(name, value);
    }
    
    public SetPropertyRule(final String name, final String value) {
        this.name = null;
        this.value = null;
        this.name = name;
        this.value = value;
    }
    
    public void begin(final Attributes attributes) throws Exception {
        String actualName = null;
        String actualValue = null;
        for (int i = 0; i < attributes.getLength(); ++i) {
            String name = attributes.getLocalName(i);
            if ("".equals(name)) {
                name = attributes.getQName(i);
            }
            final String value = attributes.getValue(i);
            if (name.equals(this.name)) {
                actualName = value;
            }
            else if (name.equals(this.value)) {
                actualValue = value;
            }
        }
        final Object top = this.digester.peek();
        if (this.digester.log.isDebugEnabled()) {
            this.digester.log.debug("[SetPropertyRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " property " + actualName + " to " + actualValue);
        }
        if (top instanceof DynaBean) {
            final DynaProperty desc = ((DynaBean)top).getDynaClass().getDynaProperty(actualName);
            if (desc == null) {
                throw new NoSuchMethodException("Bean has no property named " + actualName);
            }
        }
        else {
            final PropertyDescriptor desc2 = PropertyUtils.getPropertyDescriptor(top, actualName);
            if (desc2 == null) {
                throw new NoSuchMethodException("Bean has no property named " + actualName);
            }
        }
        BeanUtils.setProperty(top, actualName, actualValue);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("SetPropertyRule[");
        sb.append("name=");
        sb.append(this.name);
        sb.append(", value=");
        sb.append(this.value);
        sb.append("]");
        return sb.toString();
    }
}
