// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import com.mks.api.response.InvalidCommandOptionException;
import com.mks.api.commands.SICommands;
import com.mks.api.response.WorkItem;
import com.mks.api.response.Response;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import com.mks.api.response.modifiable.ModifiableField;
import com.mks.api.response.impl.SimpleResponseFactory;
import java.util.NoSuchElementException;
import com.mks.api.response.Field;
import java.util.Enumeration;
import com.mks.api.response.APIException;
import com.mks.api.CmdRunnerCreator;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import com.mks.api.response.Item;

public final class SandboxInfo implements ISandboxInfo, Item
{
    private Map fields;
    private String typeInfo;
    
    private SandboxInfo(final String aSandboxName, final String host, final String port, final String aType, final String aSIProjectName, final String aTypeInfo, final String parent, final boolean isPending, final String configPath) {
        Boolean isVariant = Boolean.FALSE;
        Boolean isBuild = Boolean.FALSE;
        Boolean isNormal = Boolean.FALSE;
        if ("variant".equals(aType)) {
            isVariant = Boolean.TRUE;
        }
        else if ("build".equals(aType)) {
            isBuild = Boolean.TRUE;
        }
        else {
            isNormal = Boolean.TRUE;
        }
        (this.fields = new LinkedHashMap()).put("sandboxName", aSandboxName);
        this.fields.put("sandboxParent", parent);
        this.fields.put("server", host);
        this.fields.put("serverPort", port);
        this.fields.put("projectName", new File(aSIProjectName));
        this.fields.put("isVariant", isVariant);
        this.fields.put("isBuild", isBuild);
        this.fields.put("isNormal", isNormal);
        this.fields.put("isPending", new Boolean(isPending));
        this.fields.put("isSubsandbox", new Boolean(parent != null));
        this.fields.put("fullConfigSyntax", configPath);
        this.fields.put("developmentPath", this.isVariant() ? aTypeInfo : null);
        this.typeInfo = aTypeInfo;
    }
    
    public String getSandboxName() {
        return this.fields.get("sandboxName");
    }
    
    public File getSandboxFile() {
        return new File(this.getSandboxName());
    }
    
    public File getSandboxLocation() {
        return this.getSandboxFile().getParentFile();
    }
    
    public String getHostname() {
        return this.fields.get("server");
    }
    
    public String getPort() {
        return this.fields.get("serverPort");
    }
    
    public boolean isVariant() {
        return this.fields.get("isVariant");
    }
    
    public boolean isBuild() {
        return this.fields.get("isBuild");
    }
    
    public File getProject() {
        return this.fields.get("projectName");
    }
    
    public String getTypeInfo() {
        return this.typeInfo;
    }
    
    public boolean isSubsandbox() {
        return this.fields.get("isSubsandbox");
    }
    
    public String getParentName() {
        return this.fields.get("sandboxParent");
    }
    
    public boolean isPending() {
        return this.fields.get("isPending");
    }
    
    public boolean isRelatedTo(final CmdRunnerCreator session, final File sandboxRoot) throws APIException {
        return this.getSandboxFile().equals(sandboxRoot) || (this.getParentName() != null && getSandboxInfo(session, new File(this.getParentName())).isRelatedTo(session, sandboxRoot));
    }
    
    public boolean equals(final Object obj) {
        if (!(obj instanceof SandboxInfo)) {
            return false;
        }
        final SandboxInfo sbx = (SandboxInfo)obj;
        if (!this.getSandboxName().equals(sbx.getSandboxName())) {
            return false;
        }
        if (!this.getHostname().equals(sbx.getHostname())) {
            return false;
        }
        if (!this.getPort().equals(sbx.getPort())) {
            return false;
        }
        if (!this.getProject().equals(sbx.getProject())) {
            return false;
        }
        if (this.typeInfo == null) {
            if (sbx.typeInfo != null) {
                return false;
            }
        }
        else if (!this.typeInfo.equals(sbx.typeInfo)) {
            return false;
        }
        if (this.getParentName() == null) {
            if (sbx.getParentName() != null) {
                return false;
            }
        }
        else if (!this.getParentName().equals(sbx.getParentName())) {
            return false;
        }
        return true;
    }
    
    public int hashCode() {
        return this.getSandboxName().hashCode();
    }
    
    public String getConfigPath() {
        return this.fields.get("fullConfigSyntax");
    }
    
    public String getDevPath() {
        return this.fields.get("developmentPath");
    }
    
    public String getContext() {
        return this.getParentName();
    }
    
    public String getContext(final String key) {
        return null;
    }
    
    public Enumeration getContextKeys() {
        return new Enumeration() {
            public boolean hasMoreElements() {
                return false;
            }
            
            public Object nextElement() {
                return null;
            }
        };
    }
    
    public String getDisplayId() {
        return null;
    }
    
    public String getId() {
        return this.getSandboxName();
    }
    
    public String getModelType() {
        return "si.SandboxInfo";
    }
    
    public boolean contains(final String id) {
        return this.fields.containsKey(id);
    }
    
    public Field getField(final String id) {
        if (!this.contains(id)) {
            throw new NoSuchElementException();
        }
        final SimpleResponseFactory factory = SimpleResponseFactory.getResponseFactory();
        final ModifiableField field = factory.createField(id);
        field.setValue(this.fields.get(id));
        return field;
    }
    
    public int getFieldListSize() {
        return this.fields.size();
    }
    
    public Iterator getFields() {
        if (this.fields.isEmpty()) {
            return Collections.EMPTY_LIST.iterator();
        }
        final List itemFields = new ArrayList(this.fields.size());
        final Iterator ids = this.fields.keySet().iterator();
        while (ids.hasNext()) {
            itemFields.add(this.getField(ids.next()));
        }
        return itemFields.iterator();
    }
    
    private static SandboxInfo construct(final Response r) throws APIException {
        final WorkItem currentWorkItem = r.getWorkItems().next();
        final String sandboxName = currentWorkItem.getField("sandboxName").getValueAsString().replace('\\', '/');
        final String server = currentWorkItem.getField("server").getValueAsString();
        final String serverPort = currentWorkItem.getField("serverPort").getValueAsString();
        final String siProjectName = currentWorkItem.getField("projectName").getValueAsString();
        final String parentSandbox = currentWorkItem.getField("sandboxParent").getValueAsString();
        final String configPath = currentWorkItem.getField("fullConfigSyntax").getValueAsString();
        final String pendingStatus = currentWorkItem.getField("pendingType").getValueAsString();
        boolean isPending = false;
        if (pendingStatus != null && pendingStatus.indexOf("pending") >= 0) {
            isPending = true;
        }
        String type = "normal";
        String typeInfo = "";
        final String projectType = currentWorkItem.getField("projectType").getValueAsString();
        if (projectType.equals("Variant")) {
            type = "variant";
            typeInfo = currentWorkItem.getField("developmentPath").getItem().getId();
        }
        else if (projectType.equals("Build")) {
            type = "build";
            typeInfo = currentWorkItem.getField("revision").getItem().getId();
        }
        return new SandboxInfo(sandboxName, server, serverPort, type, siProjectName, typeInfo, parentSandbox, isPending, configPath);
    }
    
    public static SandboxInfo getSandboxInfo(final CmdRunnerCreator session, final File sandboxPath) throws APIException {
        return getSandboxInfo(new SICommands(session, false), sandboxPath);
    }
    
    static SandboxInfo getSandboxInfo(final SICommands si, final File sandboxPath) throws APIException {
        try {
            Response sandboxInfo = null;
            if (sandboxPath.isFile()) {
                sandboxInfo = si.getSandboxInfoFromSandbox(sandboxPath.getAbsolutePath());
            }
            else {
                sandboxInfo = si.getSandboxInfo(sandboxPath.getAbsolutePath());
            }
            return construct(sandboxInfo);
        }
        catch (InvalidCommandOptionException ex) {
            boolean missingSandbox = false;
            try {
                missingSandbox = "sandbox".equals(ex.getField("command-option").getValue());
            }
            catch (NoSuchElementException ex2) {}
            if (missingSandbox) {
                return null;
            }
            throw ex;
        }
    }
}
