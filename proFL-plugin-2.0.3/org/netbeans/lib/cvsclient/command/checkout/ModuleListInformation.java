// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.command.checkout;

import java.io.File;
import org.netbeans.lib.cvsclient.command.FileInfoContainer;

public class ModuleListInformation extends FileInfoContainer
{
    private String moduleName;
    private String moduleStatus;
    private final StringBuffer paths;
    private String type;
    
    public ModuleListInformation() {
        this.paths = new StringBuffer();
    }
    
    public String getModuleName() {
        return this.moduleName;
    }
    
    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }
    
    public String getModuleStatus() {
        return this.moduleStatus;
    }
    
    public void setModuleStatus(final String moduleStatus) {
        this.moduleStatus = moduleStatus;
    }
    
    public String getPaths() {
        return this.paths.toString();
    }
    
    public void addPath(final String str) {
        if (this.paths.length() > 0) {
            this.paths.append(' ');
        }
        this.paths.append(str);
    }
    
    public File getFile() {
        return null;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(final String type) {
        this.type = type;
    }
}
