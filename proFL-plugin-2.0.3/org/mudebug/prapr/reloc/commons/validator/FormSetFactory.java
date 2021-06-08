// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.validator;

import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.mudebug.prapr.reloc.commons.digester.AbstractObjectCreationFactory;

public class FormSetFactory extends AbstractObjectCreationFactory
{
    private static final Log log;
    
    public Object createObject(final Attributes attributes) throws Exception {
        final ValidatorResources resources = (ValidatorResources)this.digester.peek(0);
        final String language = attributes.getValue("language");
        final String country = attributes.getValue("country");
        final String variant = attributes.getValue("variant");
        return this.createFormSet(resources, language, country, variant);
    }
    
    private FormSet createFormSet(final ValidatorResources resources, final String language, final String country, final String variant) throws Exception {
        FormSet formSet = resources.getFormSet(language, country, variant);
        if (formSet != null) {
            if (FormSetFactory.log.isDebugEnabled()) {
                FormSetFactory.log.debug("FormSet[" + formSet.displayKey() + "] found - merging.");
            }
            return formSet;
        }
        formSet = new FormSet();
        formSet.setLanguage(language);
        formSet.setCountry(country);
        formSet.setVariant(variant);
        resources.addFormSet(formSet);
        if (FormSetFactory.log.isDebugEnabled()) {
            FormSetFactory.log.debug("FormSet[" + formSet.displayKey() + "] created.");
        }
        return formSet;
    }
    
    static {
        log = LogFactory.getLog(ValidatorResources.class);
    }
}
