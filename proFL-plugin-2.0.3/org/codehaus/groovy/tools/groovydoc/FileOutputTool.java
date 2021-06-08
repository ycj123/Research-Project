// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.io.File;

public class FileOutputTool implements OutputTool
{
    public void makeOutputArea(final String filename) {
        final File dir = new File(filename);
        dir.mkdirs();
    }
    
    public void writeToOutput(final String fileName, final String text) throws Exception {
        final File file = new File(fileName);
        file.getParentFile().mkdirs();
        DefaultGroovyMethods.write(file, text);
    }
}
