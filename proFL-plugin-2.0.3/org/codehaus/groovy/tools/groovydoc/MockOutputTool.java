// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MockOutputTool implements OutputTool
{
    Set outputAreas;
    Map output;
    
    public MockOutputTool() {
        this.outputAreas = new HashSet();
        this.output = new HashMap();
    }
    
    public void makeOutputArea(final String filename) {
        this.outputAreas.add(filename);
    }
    
    public void writeToOutput(final String fileName, final String text) throws Exception {
        this.output.put(fileName, text);
    }
    
    public boolean isValidOutputArea(final String fileName) {
        return this.outputAreas.contains(fileName);
    }
    
    public String getText(final String fileName) {
        return this.output.get(fileName);
    }
    
    @Override
    public String toString() {
        return "dirs:" + this.outputAreas + ", files:" + this.output.keySet();
    }
}
