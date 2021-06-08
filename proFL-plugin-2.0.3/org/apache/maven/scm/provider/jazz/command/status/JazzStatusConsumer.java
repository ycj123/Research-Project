// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.jazz.command.status;

import org.apache.maven.scm.ScmFileStatus;
import java.util.regex.Matcher;
import org.apache.maven.scm.provider.jazz.repository.JazzScmProviderRepository;
import java.util.ArrayList;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.ScmFile;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.jazz.command.consumer.AbstractRepositoryConsumer;

public class JazzStatusConsumer extends AbstractRepositoryConsumer
{
    private static final Pattern WORKSPACE_PATTERN;
    private static final Pattern COMPONENT_PATTERN1;
    private static final Pattern COMPONENT_PATTERN2;
    private static final Pattern BASELINE_PATTERN;
    public static final String STATUS_CMD_WORKSPACE = "Workspace:";
    public static final String STATUS_CMD_COMPONENT = "Component:";
    public static final String STATUS_CMD_BASELINE = "Baseline:";
    public static final String STATUS_CMD_ADD_FLAG = "a-";
    public static final String STATUS_CMD_CHANGE_FLAG = "-c";
    public static final String STATUS_CMD_DELETE_FLAG = "d-";
    public static final String STATUS_CMD_MOVED_FLAG = "m-";
    private List<ScmFile> fChangedFiles;
    
    public JazzStatusConsumer(final ScmProviderRepository repo, final ScmLogger logger) {
        super(repo, logger);
        this.fChangedFiles = new ArrayList<ScmFile>();
    }
    
    @Override
    public void consumeLine(final String line) {
        super.consumeLine(line);
        if (this.containsWorkspace(line)) {
            this.extractWorkspace(line);
        }
        if (this.containsComponent(line)) {
            this.extractComponent(line);
        }
        if (this.containsBaseline(line)) {
            this.extractBaseline(line);
        }
        if (this.containsStatusFlag(line)) {
            this.extractChangedFile(line);
        }
    }
    
    private boolean containsWorkspace(final String line) {
        return line.trim().startsWith("Workspace:");
    }
    
    private void extractWorkspace(final String line) {
        final Matcher matcher = JazzStatusConsumer.WORKSPACE_PATTERN.matcher(line);
        if (matcher.find()) {
            final JazzScmProviderRepository jazzRepository = (JazzScmProviderRepository)this.getRepository();
            final int workspaceAlias = Integer.parseInt(matcher.group(1));
            final String workspace = matcher.group(2);
            final int streamAlias = Integer.parseInt(matcher.group(3));
            final String stream = matcher.group(4);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Successfully parsed \"Workspace:\" line:");
                this.getLogger().debug("  workspaceAlias = " + workspaceAlias);
                this.getLogger().debug("  workspace      = " + workspace);
                this.getLogger().debug("  streamAlias    = " + streamAlias);
                this.getLogger().debug("  stream         = " + stream);
            }
            jazzRepository.setWorkspaceAlias(workspaceAlias);
            jazzRepository.setWorkspace(workspace);
            jazzRepository.setFlowTargetAlias(streamAlias);
            jazzRepository.setFlowTarget(stream);
        }
    }
    
    private boolean containsComponent(final String line) {
        return line.trim().startsWith("Component:");
    }
    
    private void extractComponent(final String line) {
        Matcher matcher = JazzStatusConsumer.COMPONENT_PATTERN1.matcher(line);
        if (matcher.find()) {
            final JazzScmProviderRepository jazzRepository = (JazzScmProviderRepository)this.getRepository();
            final int componentAlias = Integer.parseInt(matcher.group(1));
            final String component = matcher.group(2);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Successfully parsed \"Component:\" line:");
                this.getLogger().debug("  componentAlias = " + componentAlias);
                this.getLogger().debug("  component      = " + component);
            }
            jazzRepository.setComponent(component);
        }
        matcher = JazzStatusConsumer.COMPONENT_PATTERN2.matcher(line);
        if (matcher.find()) {
            final JazzScmProviderRepository jazzRepository = (JazzScmProviderRepository)this.getRepository();
            final int componentAlias = Integer.parseInt(matcher.group(1));
            final String component = matcher.group(2);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Successfully parsed \"Component:\" line:");
                this.getLogger().debug("  componentAlias = " + componentAlias);
                this.getLogger().debug("  component      = " + component);
            }
            jazzRepository.setComponent(component);
        }
    }
    
    private boolean containsBaseline(final String line) {
        return line.trim().startsWith("Baseline:");
    }
    
    private void extractBaseline(final String line) {
        final Matcher matcher = JazzStatusConsumer.BASELINE_PATTERN.matcher(line);
        if (matcher.find()) {
            final JazzScmProviderRepository jazzRepository = (JazzScmProviderRepository)this.getRepository();
            final int baselineAlias = Integer.parseInt(matcher.group(1));
            final int baselineId = Integer.parseInt(matcher.group(2));
            final String baseline = matcher.group(3);
            if (this.getLogger().isDebugEnabled()) {
                this.getLogger().debug("Successfully parsed \"Baseline:\" line:");
                this.getLogger().debug("  baselineAlias = " + baselineAlias);
                this.getLogger().debug("  baselineId    = " + baselineId);
                this.getLogger().debug("  baseline      = " + baseline);
            }
            jazzRepository.setBaseline(baseline);
        }
    }
    
    private boolean containsStatusFlag(final String line) {
        boolean containsStatusFlag = false;
        if (line.trim().length() > 2) {
            final String flag = line.trim().substring(0, 2);
            if ("a-".equals(flag) || "-c".equals(flag) || "d-".equals(flag)) {
                containsStatusFlag = true;
            }
        }
        return containsStatusFlag;
    }
    
    private void extractChangedFile(final String line) {
        final String flag = line.trim().substring(0, 2);
        final String filePath = line.trim().substring(3).trim();
        ScmFileStatus status = ScmFileStatus.UNKNOWN;
        if ("a-".equals(flag)) {
            status = ScmFileStatus.ADDED;
        }
        if ("-c".equals(flag)) {
            status = ScmFileStatus.MODIFIED;
        }
        if ("d-".equals(flag)) {
            status = ScmFileStatus.DELETED;
        }
        if (this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(" Line               : '" + line + "'");
            this.getLogger().debug(" Extracted filePath : '" + filePath + "'");
            this.getLogger().debug(" Extracted     flag : '" + flag + "'");
            this.getLogger().debug(" Extracted   status : '" + status + "'");
        }
        this.fChangedFiles.add(new ScmFile(filePath, status));
    }
    
    public List<ScmFile> getChangedFiles() {
        return this.fChangedFiles;
    }
    
    static {
        WORKSPACE_PATTERN = Pattern.compile("\\((\\d+)\\) \"(.*)\" <-> \\((\\d+)\\) \"(.*)\"");
        COMPONENT_PATTERN1 = Pattern.compile("\\((\\d+)\\) \"(.*)\"");
        COMPONENT_PATTERN2 = Pattern.compile("\\((\\d+)\\) \"(.*)\" <.*>");
        BASELINE_PATTERN = Pattern.compile("\\((\\d+)\\) (\\d+) \"(.*)\"");
    }
}
