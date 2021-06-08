// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.integrity;

import com.mks.api.response.WorkItemIterator;
import java.util.Collections;
import com.mks.api.MultiValue;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;
import com.mks.api.response.APIException;
import com.mks.api.response.Field;
import com.mks.api.response.WorkItem;
import com.mks.api.response.Response;
import java.util.NoSuchElementException;
import java.util.Calendar;
import com.mks.api.Option;
import com.mks.api.Command;
import java.util.Comparator;
import java.util.Date;

public class Project
{
    public static final String NORMAL_PROJECT = "Normal";
    public static final String VARIANT_PROJECT = "Variant";
    public static final String BUILD_PROJECT = "Build";
    private String projectName;
    private String projectType;
    private String projectRevision;
    private String fullConfigSyntax;
    private Date lastCheckpoint;
    private APISession api;
    public static final Comparator<Member> FILES_ORDER;
    
    public static void validateTag(final String tagName) throws Exception {
        if (tagName == null || tagName.length() == 0) {
            throw new Exception("The checkpoint label string is empty!");
        }
        final char ch = tagName.charAt(0);
        if (('A' > ch || ch > 'Z') && ('a' > ch || ch > 'z')) {
            throw new Exception("The checkpoint label must start with an alpha character!");
        }
        for (final char invalid : "$,.:;/\\@".toCharArray()) {
            if (tagName.indexOf(invalid) >= 0) {
                throw new Exception("The checkpoint label may cannot contain one of the following characters: $ , . : ; / \\ @");
            }
        }
    }
    
    public Project(final APISession api, final String configPath) throws APIException {
        this.api = api;
        try {
            final Command siProjectInfoCmd = new Command("si", "projectinfo");
            siProjectInfoCmd.addOption(new Option("project", configPath));
            api.getLogger().info("Preparing to execute si projectinfo for " + configPath);
            final Response infoRes = api.runCommand(siProjectInfoCmd);
            final WorkItem wi = infoRes.getWorkItems().next();
            final Field pjNameFld = wi.getField("projectName");
            final Field pjTypeFld = wi.getField("projectType");
            final Field pjCfgPathFld = wi.getField("fullConfigSyntax");
            final Field pjChkptFld = wi.getField("lastCheckpoint");
            if (null != pjNameFld && null != pjNameFld.getValueAsString()) {
                this.projectName = pjNameFld.getValueAsString();
            }
            else {
                api.getLogger().warn("Project info did not provide a value for the 'projectName' field!");
                this.projectName = "";
            }
            if (null != pjTypeFld && null != pjTypeFld.getValueAsString()) {
                this.projectType = pjTypeFld.getValueAsString();
                if (this.isBuild()) {
                    final Field pjRevFld = wi.getField("revision");
                    if (null != pjRevFld && null != pjRevFld.getItem()) {
                        this.projectRevision = pjRevFld.getItem().getId();
                    }
                    else {
                        this.projectRevision = "";
                        api.getLogger().warn("Project info did not provide a vale for the 'revision' field!");
                    }
                }
            }
            else {
                api.getLogger().warn("Project info did not provide a value for the 'projectType' field!");
                this.projectType = "";
            }
            if (null != pjCfgPathFld && null != pjCfgPathFld.getValueAsString()) {
                this.fullConfigSyntax = pjCfgPathFld.getValueAsString();
            }
            else {
                api.getLogger().error("Project info did not provide a value for the 'fullConfigSyntax' field!");
                this.fullConfigSyntax = "";
            }
            if (null != pjChkptFld && null != pjChkptFld.getDateTime()) {
                this.lastCheckpoint = pjChkptFld.getDateTime();
            }
            else {
                api.getLogger().warn("Project info did not provide a value for the 'lastCheckpoint' field!");
                this.lastCheckpoint = Calendar.getInstance().getTime();
            }
        }
        catch (NoSuchElementException nsee) {
            api.getLogger().error("Project info did not provide a value for field " + nsee.getMessage());
        }
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public String getProjectRevision() {
        return this.projectRevision;
    }
    
    public boolean isNormal() {
        return this.projectType.equalsIgnoreCase("Normal");
    }
    
    public boolean isVariant() {
        return this.projectType.equalsIgnoreCase("Variant");
    }
    
    public boolean isBuild() {
        return this.projectType.equalsIgnoreCase("Build");
    }
    
    public String getConfigurationPath() {
        return this.fullConfigSyntax;
    }
    
    public Date getLastCheckpointDate() {
        return this.lastCheckpoint;
    }
    
    public List<Member> listFiles(final String workspaceDir) throws APIException {
        final List<Member> memberList = new ArrayList<Member>();
        final Hashtable<String, String> pjConfigHash = new Hashtable<String, String>();
        pjConfigHash.put(this.projectName, this.fullConfigSyntax);
        final String projectRoot = this.projectName.substring(0, this.projectName.lastIndexOf(47));
        final Command siViewProjectCmd = new Command("si", "viewproject");
        siViewProjectCmd.addOption(new Option("recurse"));
        siViewProjectCmd.addOption(new Option("project", this.fullConfigSyntax));
        final MultiValue mvFields = new MultiValue(",");
        mvFields.add("name");
        mvFields.add("context");
        mvFields.add("memberrev");
        mvFields.add("membertimestamp");
        mvFields.add("memberdescription");
        siViewProjectCmd.addOption(new Option("fields", mvFields));
        this.api.getLogger().info("Preparing to execute si viewproject for " + this.fullConfigSyntax);
        final Response viewRes = this.api.runCommand(siViewProjectCmd);
        final WorkItemIterator wit = viewRes.getWorkItems();
        while (wit.hasNext()) {
            final WorkItem wi = wit.next();
            if (wi.getModelType().equals("si.Subproject")) {
                pjConfigHash.put(wi.getField("name").getValueAsString(), wi.getId());
            }
            else if (wi.getModelType().equals("si.Member")) {
                final String parentProject = wi.getField("parent").getValueAsString();
                final Member iCMMember = new Member(wi, pjConfigHash.get(parentProject), projectRoot, workspaceDir);
                memberList.add(iCMMember);
            }
            else {
                this.api.getLogger().warn("View project output contains an invalid model type: " + wi.getModelType());
            }
        }
        Collections.sort(memberList, Project.FILES_ORDER);
        return memberList;
    }
    
    public Response checkpoint(final String message, final String tag) throws APIException {
        this.api.getLogger().debug("Checkpointing project " + this.fullConfigSyntax + " with label '" + tag + "'");
        final Command siCheckpoint = new Command("si", "checkpoint");
        siCheckpoint.addOption(new Option("recurse"));
        siCheckpoint.addOption(new Option("project", this.fullConfigSyntax));
        siCheckpoint.addOption(new Option("label", tag));
        if (null != message && message.length() > 0) {
            siCheckpoint.addOption(new Option("description", message));
        }
        return this.api.runCommand(siCheckpoint);
    }
    
    public Response createDevPath(final String devPath) throws APIException {
        String chkpt = this.projectRevision;
        if (!this.isBuild()) {
            final Response chkptRes = this.checkpoint("Pre-checkpoint for development path " + devPath, devPath + " Baseline");
            final WorkItem wi = chkptRes.getWorkItem(this.fullConfigSyntax);
            chkpt = wi.getResult().getField("resultant").getItem().getId();
        }
        this.api.getLogger().debug("Creating development path '" + devPath + "' for project " + this.projectName + " at revision '" + chkpt + "'");
        final Command siCreateDevPath = new Command("si", "createdevpath");
        siCreateDevPath.addOption(new Option("devpath", devPath));
        siCreateDevPath.addOption(new Option("project", this.projectName));
        siCreateDevPath.addOption(new Option("projectRevision", chkpt));
        return this.api.runCommand(siCreateDevPath);
    }
    
    static {
        FILES_ORDER = new Comparator<Member>() {
            public int compare(final Member cmm1, final Member cmm2) {
                return cmm1.getMemberName().compareToIgnoreCase(cmm2.getMemberName());
            }
        };
    }
}
