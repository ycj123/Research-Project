// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.validator;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import java.util.Iterator;
import java.util.Collections;
import java.util.Map;
import org.xml.sax.Attributes;
import org.mudebug.prapr.reloc.commons.digester.Rule;
import java.net.URL;
import org.mudebug.prapr.reloc.commons.digester.xmlrules.DigesterLoader;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.mudebug.prapr.reloc.commons.collections.FastHashMap;
import org.mudebug.prapr.reloc.commons.logging.Log;
import java.io.Serializable;

public class ValidatorResources implements Serializable
{
    private static final String[] registrations;
    private static final Log log;
    protected FastHashMap hFormSets;
    protected FastHashMap hConstants;
    protected FastHashMap hActions;
    protected static Locale defaultLocale;
    protected FormSet defaultFormSet;
    private static final String argsPattern = "form-validation/formset/form/field/arg";
    
    public ValidatorResources() {
        this.hFormSets = new FastHashMap();
        this.hConstants = new FastHashMap();
        this.hActions = new FastHashMap();
    }
    
    public ValidatorResources(final InputStream in) throws IOException, SAXException {
        this(new InputStream[] { in });
    }
    
    public ValidatorResources(final InputStream[] streams) throws IOException, SAXException {
        this.hFormSets = new FastHashMap();
        this.hConstants = new FastHashMap();
        this.hActions = new FastHashMap();
        final Digester digester = this.initDigester();
        for (int i = 0; i < streams.length; ++i) {
            digester.push(this);
            digester.parse(streams[i]);
        }
        this.process();
    }
    
    public ValidatorResources(final String uri) throws IOException, SAXException {
        this(new String[] { uri });
    }
    
    public ValidatorResources(final String[] uris) throws IOException, SAXException {
        this.hFormSets = new FastHashMap();
        this.hConstants = new FastHashMap();
        this.hActions = new FastHashMap();
        final Digester digester = this.initDigester();
        for (int i = 0; i < uris.length; ++i) {
            digester.push(this);
            digester.parse(uris[i]);
        }
        this.process();
    }
    
    private Digester initDigester() {
        final URL rulesUrl = this.getClass().getResource("digester-rules.xml");
        final Digester digester = DigesterLoader.createDigester(rulesUrl);
        digester.setNamespaceAware(true);
        digester.setValidating(true);
        digester.setUseContextClassLoader(true);
        this.addOldArgRules(digester);
        for (int i = 0; i < ValidatorResources.registrations.length; i += 2) {
            final URL url = this.getClass().getResource(ValidatorResources.registrations[i + 1]);
            if (url != null) {
                digester.register(ValidatorResources.registrations[i], url.toString());
            }
        }
        return digester;
    }
    
    private void addOldArgRules(final Digester digester) {
        final Rule rule = new Rule() {
            public void begin(final String namespace, final String name, final Attributes attributes) throws Exception {
                final Arg arg = new Arg();
                arg.setKey(attributes.getValue("key"));
                arg.setName(attributes.getValue("name"));
                try {
                    arg.setPosition(Integer.parseInt(name.substring(3)));
                }
                catch (Exception ex) {
                    ValidatorResources.log.error("Error parsing Arg position: " + name + " " + arg + " " + ex);
                }
                ((Field)this.getDigester().peek(0)).addArg(arg);
            }
        };
        digester.addRule("form-validation/formset/form/field/arg0", rule);
        digester.addRule("form-validation/formset/form/field/arg1", rule);
        digester.addRule("form-validation/formset/form/field/arg2", rule);
        digester.addRule("form-validation/formset/form/field/arg3", rule);
    }
    
    public void addFormSet(final FormSet fs) {
        final String key = this.buildKey(fs);
        if (key.length() == 0) {
            if (ValidatorResources.log.isWarnEnabled() && this.defaultFormSet != null) {
                ValidatorResources.log.warn("Overriding default FormSet definition.");
            }
            this.defaultFormSet = fs;
        }
        else {
            final FormSet formset = (FormSet)this.hFormSets.get(key);
            if (formset == null) {
                if (ValidatorResources.log.isDebugEnabled()) {
                    ValidatorResources.log.debug("Adding FormSet '" + fs.toString() + "'.");
                }
            }
            else if (ValidatorResources.log.isWarnEnabled()) {
                ValidatorResources.log.warn("Overriding FormSet definition. Duplicate for locale: " + key);
            }
            this.hFormSets.put(key, fs);
        }
    }
    
    public void addConstant(final String name, final String value) {
        if (ValidatorResources.log.isDebugEnabled()) {
            ValidatorResources.log.debug("Adding Global Constant: " + name + "," + value);
        }
        this.hConstants.put(name, value);
    }
    
    public void addValidatorAction(final ValidatorAction va) {
        va.init();
        this.hActions.put(va.getName(), va);
        if (ValidatorResources.log.isDebugEnabled()) {
            ValidatorResources.log.debug("Add ValidatorAction: " + va.getName() + "," + va.getClassname());
        }
    }
    
    public ValidatorAction getValidatorAction(final String key) {
        return (ValidatorAction)this.hActions.get(key);
    }
    
    public Map getValidatorActions() {
        return Collections.unmodifiableMap((Map<?, ?>)this.hActions);
    }
    
    protected String buildKey(final FormSet fs) {
        return this.buildLocale(fs.getLanguage(), fs.getCountry(), fs.getVariant());
    }
    
    private String buildLocale(final String lang, final String country, final String variant) {
        String key = (lang != null && lang.length() > 0) ? lang : "";
        key += ((country != null && country.length() > 0) ? ("_" + country) : "");
        key += ((variant != null && variant.length() > 0) ? ("_" + variant) : "");
        return key;
    }
    
    public Form getForm(final Locale locale, final String formKey) {
        return this.getForm(locale.getLanguage(), locale.getCountry(), locale.getVariant(), formKey);
    }
    
    public Form getForm(final String language, final String country, final String variant, final String formKey) {
        Form form = null;
        String key = this.buildLocale(language, country, variant);
        if (key.length() > 0) {
            final FormSet formSet = (FormSet)this.hFormSets.get(key);
            if (formSet != null) {
                form = formSet.getForm(formKey);
            }
        }
        final String localeKey = key;
        if (form == null) {
            key = this.buildLocale(language, country, null);
            if (key.length() > 0) {
                final FormSet formSet2 = (FormSet)this.hFormSets.get(key);
                if (formSet2 != null) {
                    form = formSet2.getForm(formKey);
                }
            }
        }
        if (form == null) {
            key = this.buildLocale(language, null, null);
            if (key.length() > 0) {
                final FormSet formSet2 = (FormSet)this.hFormSets.get(key);
                if (formSet2 != null) {
                    form = formSet2.getForm(formKey);
                }
            }
        }
        if (form == null) {
            form = this.defaultFormSet.getForm(formKey);
            key = "default";
        }
        if (form == null) {
            if (ValidatorResources.log.isWarnEnabled()) {
                ValidatorResources.log.warn("Form '" + formKey + "' not found for locale '" + localeKey + "'");
            }
        }
        else if (ValidatorResources.log.isDebugEnabled()) {
            ValidatorResources.log.debug("Form '" + formKey + "' found in formset '" + key + "' for locale '" + localeKey + "'");
        }
        return form;
    }
    
    public void process() {
        this.hFormSets.setFast(true);
        this.hConstants.setFast(true);
        this.hActions.setFast(true);
        this.processForms();
    }
    
    private void processForms() {
        if (this.defaultFormSet == null) {
            this.defaultFormSet = new FormSet();
        }
        this.defaultFormSet.process(this.hConstants);
        for (final String key : this.hFormSets.keySet()) {
            final FormSet fs = (FormSet)this.hFormSets.get(key);
            fs.merge(this.getParent(fs));
        }
        final Iterator j = this.hFormSets.values().iterator();
        while (j.hasNext()) {
            final FormSet fs = j.next();
            if (!fs.isProcessed()) {
                fs.process(this.hConstants);
            }
        }
    }
    
    private FormSet getParent(final FormSet fs) {
        FormSet parent = null;
        if (fs.getType() == 2) {
            parent = this.defaultFormSet;
        }
        else if (fs.getType() == 3) {
            parent = (FormSet)this.hFormSets.get(this.buildLocale(fs.getLanguage(), null, null));
            if (parent == null) {
                parent = this.defaultFormSet;
            }
        }
        else if (fs.getType() == 4) {
            parent = (FormSet)this.hFormSets.get(this.buildLocale(fs.getLanguage(), fs.getCountry(), null));
            if (parent == null) {
                parent = (FormSet)this.hFormSets.get(this.buildLocale(fs.getLanguage(), null, null));
                if (parent == null) {
                    parent = this.defaultFormSet;
                }
            }
        }
        return parent;
    }
    
    FormSet getFormSet(final String language, final String country, final String variant) {
        final String key = this.buildLocale(language, country, variant);
        if (key.length() == 0) {
            return this.defaultFormSet;
        }
        return (FormSet)this.hFormSets.get(key);
    }
    
    protected Map getFormSets() {
        return this.hFormSets;
    }
    
    protected Map getConstants() {
        return this.hConstants;
    }
    
    protected Map getActions() {
        return this.hActions;
    }
    
    static {
        registrations = new String[] { "-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.0//EN", "/org/mudebug/prapr/reloc/commons/validator/resources/validator_1_0.dtd", "-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.0.1//EN", "/org/mudebug/prapr/reloc/commons/validator/resources/validator_1_0_1.dtd", "-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.1//EN", "/org/mudebug/prapr/reloc/commons/validator/resources/validator_1_1.dtd", "-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.1.3//EN", "/org/mudebug/prapr/reloc/commons/validator/resources/validator_1_1_3.dtd", "-//Apache Software Foundation//DTD Commons Validator Rules Configuration 1.2.0//EN", "/org/mudebug/prapr/reloc/commons/validator/resources/validator_1_2_0.dtd" };
        log = LogFactory.getLog(ValidatorResources.class);
        ValidatorResources.defaultLocale = Locale.getDefault();
    }
}
