// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester.plugins.strategies;

import java.io.InputStream;
import java.io.IOException;
import org.mudebug.prapr.reloc.commons.digester.plugins.PluginException;
import java.io.FileInputStream;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleLoader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.digester.Digester;
import org.mudebug.prapr.reloc.commons.digester.plugins.RuleFinder;

public class FinderFromFile extends RuleFinder
{
    public static String DFLT_FILENAME_ATTR;
    private String filenameAttr;
    
    public FinderFromFile() {
        this(FinderFromFile.DFLT_FILENAME_ATTR);
    }
    
    public FinderFromFile(final String filenameAttr) {
        this.filenameAttr = filenameAttr;
    }
    
    public RuleLoader findLoader(final Digester d, final Class pluginClass, final Properties p) throws PluginException {
        final String rulesFileName = p.getProperty(this.filenameAttr);
        if (rulesFileName == null) {
            return null;
        }
        InputStream is = null;
        try {
            is = new FileInputStream(rulesFileName);
        }
        catch (IOException ioe) {
            throw new PluginException("Unable to process file [" + rulesFileName + "]", ioe);
        }
        try {
            final RuleLoader loader = new LoaderFromStream(is);
            return loader;
        }
        catch (Exception e) {
            throw new PluginException("Unable to load xmlrules from file [" + rulesFileName + "]", e);
        }
        finally {
            try {
                is.close();
            }
            catch (IOException ioe2) {
                throw new PluginException("Unable to close stream for file [" + rulesFileName + "]", ioe2);
            }
        }
    }
    
    static {
        FinderFromFile.DFLT_FILENAME_ATTR = "file";
    }
}
