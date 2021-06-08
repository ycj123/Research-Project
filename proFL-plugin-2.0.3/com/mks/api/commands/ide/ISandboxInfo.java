// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.io.File;

public interface ISandboxInfo
{
    public static final String VARIANT_SANDBOX = "variant";
    public static final String BUILD_SANDBOX = "build";
    public static final String NORMAL_SANDBOX = "normal";
    public static final String SANDBOX_FIELD = "sandboxName";
    public static final String PARENT_FIELD = "sandboxParent";
    public static final String HOST_FIELD = "server";
    public static final String PORT_FIELD = "serverPort";
    public static final String PROJECT_FIELD = "projectName";
    public static final String NORMAL_FIELD = "isNormal";
    public static final String VARIANT_FIELD = "isVariant";
    public static final String BUILD_FIELD = "isBuild";
    public static final String DEV_PATH_FIELD = "developmentPath";
    public static final String PENDING_FIELD = "isPending";
    public static final String SUB_SANDBOX_FIELD = "isSubsandbox";
    public static final String CONFIG_PATH_FIELD = "fullConfigSyntax";
    
    String getSandboxName();
    
    File getSandboxFile();
    
    File getSandboxLocation();
    
    String getHostname();
    
    String getPort();
    
    boolean isVariant();
    
    boolean isBuild();
    
    File getProject();
    
    String getTypeInfo();
    
    boolean isSubsandbox();
    
    String getParentName();
    
    boolean isPending();
    
    String getConfigPath();
    
    String getDevPath();
}
