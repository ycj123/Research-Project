// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.toolchain.java;

import hidden.org.codehaus.plexus.util.Os;
import java.io.File;
import hidden.org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.logging.Logger;
import org.apache.maven.toolchain.model.ToolchainModel;
import org.apache.maven.toolchain.DefaultToolchain;

public class DefaultJavaToolChain extends DefaultToolchain implements JavaToolChain
{
    private String javaHome;
    public static final String KEY_JAVAHOME = "jdkHome";
    
    public DefaultJavaToolChain(final ToolchainModel model, final Logger logger) {
        super(model, "jdk", logger);
    }
    
    public String getJavaHome() {
        return this.javaHome;
    }
    
    public void setJavaHome(final String javaHome) {
        this.javaHome = javaHome;
    }
    
    @Override
    public String toString() {
        return "JDK[" + this.getJavaHome() + "]";
    }
    
    public String findTool(final String toolName) {
        final File toRet = findTool(toolName, new File(FileUtils.normalize(this.getJavaHome())));
        if (toRet != null) {
            return toRet.getAbsolutePath();
        }
        return null;
    }
    
    private static File findTool(final String toolName, final File installFolder) {
        final File bin = new File(installFolder, "bin");
        if (bin.exists()) {
            final File tool = new File(bin, toolName + (Os.isFamily("windows") ? ".exe" : ""));
            if (tool.exists()) {
                return tool;
            }
        }
        return null;
    }
}
