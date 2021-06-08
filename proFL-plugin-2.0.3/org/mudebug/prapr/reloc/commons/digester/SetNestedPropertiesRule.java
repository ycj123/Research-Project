// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import java.beans.PropertyDescriptor;
import org.mudebug.prapr.reloc.commons.beanutils.DynaProperty;
import org.mudebug.prapr.reloc.commons.beanutils.BeanUtils;
import org.mudebug.prapr.reloc.commons.beanutils.PropertyUtils;
import org.mudebug.prapr.reloc.commons.beanutils.DynaBean;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import java.util.HashMap;
import org.mudebug.prapr.reloc.commons.logging.Log;

public class SetNestedPropertiesRule extends Rule
{
    private static final String PROP_IGNORE = "ignore-me";
    private Log log;
    private AnyChildRule anyChildRule;
    private AnyChildRules newRules;
    private Rules oldRules;
    private boolean trimData;
    private boolean allowUnknownChildElements;
    private HashMap elementNames;
    
    public SetNestedPropertiesRule() {
        this.log = null;
        this.anyChildRule = new AnyChildRule();
        this.newRules = new AnyChildRules(this.anyChildRule);
        this.oldRules = null;
        this.trimData = true;
        this.allowUnknownChildElements = false;
        this.elementNames = new HashMap();
    }
    
    public SetNestedPropertiesRule(final String elementName, final String propertyName) {
        this.log = null;
        this.anyChildRule = new AnyChildRule();
        this.newRules = new AnyChildRules(this.anyChildRule);
        this.oldRules = null;
        this.trimData = true;
        this.allowUnknownChildElements = false;
        (this.elementNames = new HashMap()).put(elementName, propertyName);
    }
    
    public SetNestedPropertiesRule(final String[] elementNames, final String[] propertyNames) {
        this.log = null;
        this.anyChildRule = new AnyChildRule();
        this.newRules = new AnyChildRules(this.anyChildRule);
        this.oldRules = null;
        this.trimData = true;
        this.allowUnknownChildElements = false;
        this.elementNames = new HashMap();
        for (int i = 0, size = elementNames.length; i < size; ++i) {
            String propName = null;
            if (i < propertyNames.length) {
                propName = propertyNames[i];
            }
            if (propName == null) {
                this.elementNames.put(elementNames[i], "ignore-me");
            }
            else {
                this.elementNames.put(elementNames[i], propName);
            }
        }
    }
    
    public void setDigester(final Digester digester) {
        super.setDigester(digester);
        this.log = digester.getLogger();
        this.anyChildRule.setDigester(digester);
    }
    
    public void setTrimData(final boolean trimData) {
        this.trimData = trimData;
    }
    
    public boolean getTrimData() {
        return this.trimData;
    }
    
    public void setAllowUnknownChildElements(final boolean allowUnknownChildElements) {
        this.allowUnknownChildElements = allowUnknownChildElements;
    }
    
    public boolean getAllowUnknownChildElements() {
        return this.allowUnknownChildElements;
    }
    
    public void begin(final String namespace, final String name, final Attributes attributes) throws Exception {
        this.oldRules = this.digester.getRules();
        this.newRules.init(this.digester.getMatch() + "/", this.oldRules);
        this.digester.setRules(this.newRules);
    }
    
    public void body(final String bodyText) throws Exception {
        this.digester.setRules(this.oldRules);
    }
    
    public void addAlias(final String elementName, final String propertyName) {
        if (propertyName == null) {
            this.elementNames.put(elementName, "ignore-me");
        }
        else {
            this.elementNames.put(elementName, propertyName);
        }
    }
    
    public String toString() {
        return "SetNestedPropertiesRule";
    }
    
    private class AnyChildRules implements Rules
    {
        private String matchPrefix;
        private Rules decoratedRules;
        private ArrayList rules;
        private AnyChildRule rule;
        
        public AnyChildRules(final AnyChildRule rule) {
            this.matchPrefix = null;
            this.decoratedRules = null;
            this.rules = new ArrayList(1);
            this.rule = rule;
            this.rules.add(rule);
        }
        
        public Digester getDigester() {
            return null;
        }
        
        public void setDigester(final Digester digester) {
        }
        
        public String getNamespaceURI() {
            return null;
        }
        
        public void setNamespaceURI(final String namespaceURI) {
        }
        
        public void add(final String pattern, final Rule rule) {
        }
        
        public void clear() {
        }
        
        public List match(final String matchPath) {
            return this.match(null, matchPath);
        }
        
        public List match(final String namespaceURI, final String matchPath) {
            final List match = this.decoratedRules.match(namespaceURI, matchPath);
            if (!matchPath.startsWith(this.matchPrefix) || matchPath.indexOf(47, this.matchPrefix.length()) != -1) {
                return match;
            }
            if (match == null || match.size() == 0) {
                return this.rules;
            }
            final LinkedList newMatch = new LinkedList(match);
            newMatch.addLast(this.rule);
            return newMatch;
        }
        
        public List rules() {
            throw new RuntimeException("AnyChildRules.rules not implemented.");
        }
        
        public void init(final String prefix, final Rules rules) {
            this.matchPrefix = prefix;
            this.decoratedRules = rules;
        }
    }
    
    private class AnyChildRule extends Rule
    {
        private String currChildNamespaceURI;
        private String currChildElementName;
        
        private AnyChildRule() {
            this.currChildNamespaceURI = null;
            this.currChildElementName = null;
        }
        
        public void begin(final String namespaceURI, final String name, final Attributes attributes) throws Exception {
            this.currChildNamespaceURI = namespaceURI;
            this.currChildElementName = name;
        }
        
        public void body(String value) throws Exception {
            final boolean debug = SetNestedPropertiesRule.this.log.isDebugEnabled();
            String propName = SetNestedPropertiesRule.this.elementNames.get(this.currChildElementName);
            if (propName == "ignore-me") {
                return;
            }
            if (propName == null) {
                propName = this.currChildElementName;
            }
            if (this.digester.log.isDebugEnabled()) {
                this.digester.log.debug("[SetNestedPropertiesRule]{" + this.digester.match + "} Setting property '" + propName + "' to '" + value + "'");
            }
            final Object top = this.digester.peek();
            if (this.digester.log.isDebugEnabled()) {
                if (top != null) {
                    this.digester.log.debug("[SetNestedPropertiesRule]{" + this.digester.match + "} Set " + top.getClass().getName() + " properties");
                }
                else {
                    this.digester.log.debug("[SetPropertiesRule]{" + this.digester.match + "} Set NULL properties");
                }
            }
            if (SetNestedPropertiesRule.this.trimData) {
                value = value.trim();
            }
            if (!SetNestedPropertiesRule.this.allowUnknownChildElements) {
                if (top instanceof DynaBean) {
                    final DynaProperty desc = ((DynaBean)top).getDynaClass().getDynaProperty(propName);
                    if (desc == null) {
                        throw new NoSuchMethodException("Bean has no property named " + propName);
                    }
                }
                else {
                    final PropertyDescriptor desc2 = PropertyUtils.getPropertyDescriptor(top, propName);
                    if (desc2 == null) {
                        throw new NoSuchMethodException("Bean has no property named " + propName);
                    }
                }
            }
            BeanUtils.setProperty(top, propName, value);
        }
        
        public void end(final String namespace, final String name) throws Exception {
            this.currChildElementName = null;
        }
    }
}
