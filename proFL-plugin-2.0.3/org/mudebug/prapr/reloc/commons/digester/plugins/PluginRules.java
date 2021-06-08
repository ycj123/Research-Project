// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins;

import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.digester.Rule;
import java.util.List;
import org.mudebug.prapr.reloc.commons.digester.RulesBase;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.Rules;

public class PluginRules implements Rules
{
    protected Digester digester;
    private Rules decoratedRules;
    private PluginManager pluginManager;
    private String mountPoint;
    private PluginRules parent;
    private PluginContext pluginContext;
    
    public PluginRules() {
        this(new RulesBase());
    }
    
    public PluginRules(final Rules decoratedRules) {
        this.digester = null;
        this.mountPoint = null;
        this.parent = null;
        this.pluginContext = null;
        this.decoratedRules = decoratedRules;
        this.pluginContext = new PluginContext();
        this.pluginManager = new PluginManager(this.pluginContext);
    }
    
    PluginRules(final String mountPoint, final PluginRules parent) {
        this.digester = null;
        this.mountPoint = null;
        this.parent = null;
        this.pluginContext = null;
        this.decoratedRules = new RulesBase();
        this.pluginContext = parent.pluginContext;
        this.pluginManager = new PluginManager(parent.pluginManager);
        this.mountPoint = mountPoint;
        this.parent = parent;
    }
    
    public Rules getParent() {
        return this.parent;
    }
    
    public Digester getDigester() {
        return this.digester;
    }
    
    public void setDigester(final Digester digester) {
        this.digester = digester;
        this.decoratedRules.setDigester(digester);
    }
    
    public String getNamespaceURI() {
        return this.decoratedRules.getNamespaceURI();
    }
    
    public void setNamespaceURI(final String namespaceURI) {
        this.decoratedRules.setNamespaceURI(namespaceURI);
    }
    
    public PluginManager getPluginManager() {
        return this.pluginManager;
    }
    
    public List getRuleFinders() {
        return this.pluginContext.getRuleFinders();
    }
    
    public void setRuleFinders(final List ruleFinders) {
        this.pluginContext.setRuleFinders(ruleFinders);
    }
    
    Rules getDecoratedRules() {
        return this.decoratedRules;
    }
    
    public List rules() {
        return this.decoratedRules.rules();
    }
    
    public void add(String pattern, final Rule rule) {
        final Log log = LogUtils.getLogger(this.digester);
        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("add entry: mapping pattern [" + pattern + "]" + " to rule of type [" + rule.getClass().getName() + "]");
        }
        if (pattern.startsWith("/")) {
            pattern = pattern.substring(1);
        }
        if (this.mountPoint != null && !pattern.equals(this.mountPoint) && !pattern.startsWith(this.mountPoint + "/")) {
            log.warn("An attempt was made to add a rule with a pattern thatis not at or below the mountpoint of the current PluginRules object. Rule pattern: " + pattern + ", mountpoint: " + this.mountPoint + ", rule type: " + rule.getClass().getName());
            return;
        }
        this.decoratedRules.add(pattern, rule);
        if (rule instanceof InitializableRule) {
            try {
                ((InitializableRule)rule).postRegisterInit(pattern);
            }
            catch (PluginConfigurationException e) {
                if (debug) {
                    log.debug("Rule initialisation failed", e);
                }
                return;
            }
        }
        if (debug) {
            log.debug("add exit: mapped pattern [" + pattern + "]" + " to rule of type [" + rule.getClass().getName() + "]");
        }
    }
    
    public void clear() {
        this.decoratedRules.clear();
    }
    
    public List match(final String path) {
        return this.match(null, path);
    }
    
    public List match(final String namespaceURI, final String path) {
        final Log log = LogUtils.getLogger(this.digester);
        final boolean debug = log.isDebugEnabled();
        if (debug) {
            log.debug("Matching path [" + path + "] on rules object " + this.toString());
        }
        List matches;
        if (this.mountPoint != null && path.length() <= this.mountPoint.length()) {
            if (debug) {
                log.debug("Path [" + path + "] delegated to parent.");
            }
            matches = this.parent.match(namespaceURI, path);
        }
        else {
            matches = this.decoratedRules.match(namespaceURI, path);
        }
        return matches;
    }
    
    public void setPluginClassAttribute(final String namespaceUri, final String attrName) {
        this.pluginContext.setPluginClassAttribute(namespaceUri, attrName);
    }
    
    public void setPluginIdAttribute(final String namespaceUri, final String attrName) {
        this.pluginContext.setPluginIdAttribute(namespaceUri, attrName);
    }
    
    public String getPluginClassAttrNs() {
        return this.pluginContext.getPluginClassAttrNs();
    }
    
    public String getPluginClassAttr() {
        return this.pluginContext.getPluginClassAttr();
    }
    
    public String getPluginIdAttrNs() {
        return this.pluginContext.getPluginIdAttrNs();
    }
    
    public String getPluginIdAttr() {
        return this.pluginContext.getPluginIdAttr();
    }
}
