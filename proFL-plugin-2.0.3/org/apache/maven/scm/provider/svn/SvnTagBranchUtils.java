// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.svn;

import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmBranch;
import org.apache.maven.scm.ScmTag;
import org.apache.maven.scm.provider.svn.repository.SvnScmProviderRepository;
import org.codehaus.plexus.util.StringUtils;

public final class SvnTagBranchUtils
{
    public static final String[] REVISION_SPECIFIERS;
    public static final String SVN_TRUNK = "trunk";
    public static final String SVN_BRANCHES = "branches";
    public static final String SVN_TAGS = "tags";
    public static final String[] SVN_BASE_DIRS;
    
    private SvnTagBranchUtils() {
    }
    
    static String appendPath(String basePath, final String addlPath) {
        basePath = StringUtils.stripEnd(basePath, "/");
        if (StringUtils.isEmpty(addlPath)) {
            return basePath;
        }
        return basePath + "/" + StringUtils.stripStart(addlPath, "/");
    }
    
    public static String getProjectRoot(final String repoPath) {
        for (int i = 0; i < SvnTagBranchUtils.SVN_BASE_DIRS.length; ++i) {
            final String base = "/" + SvnTagBranchUtils.SVN_BASE_DIRS[i];
            final int pos = repoPath.lastIndexOf(base + "/");
            if (repoPath.endsWith(base)) {
                return repoPath.substring(0, repoPath.length() - base.length());
            }
            if (pos >= 0) {
                return repoPath.substring(0, pos);
            }
        }
        return appendPath(repoPath, null);
    }
    
    public static String resolveTagBase(final SvnScmProviderRepository repository) {
        return resolveTagBase(repository.getUrl());
    }
    
    public static String resolveTagBase(final String repositoryUrl) {
        return appendPath(getProjectRoot(repositoryUrl), "tags");
    }
    
    public static String resolveBranchBase(final SvnScmProviderRepository repository) {
        return resolveBranchBase(repository.getUrl());
    }
    
    public static String resolveBranchBase(final String repositoryUrl) {
        return appendPath(getProjectRoot(repositoryUrl), "branches");
    }
    
    public static String resolveTagUrl(final SvnScmProviderRepository repository, final ScmTag tag) {
        return resolveUrl(repository.getUrl(), repository.getTagBase(), "tags", tag);
    }
    
    public static String resolveTagUrl(final String repositoryUrl, final ScmTag tag) {
        return resolveUrl(repositoryUrl, null, "tags", tag);
    }
    
    public static String resolveBranchUrl(final SvnScmProviderRepository repository, final ScmBranch branch) {
        return resolveUrl(repository.getUrl(), repository.getBranchBase(), "branches", branch);
    }
    
    public static String resolveBranchUrl(final String repositoryUrl, final ScmBranch branch) {
        return resolveUrl(repositoryUrl, resolveBranchBase(repositoryUrl), "branches", branch);
    }
    
    private static String addSuffix(final String baseString, final String suffix) {
        return (suffix != null) ? (baseString + suffix) : baseString;
    }
    
    public static String resolveUrl(final String repositoryUrl, final String tagBase, final String subdir, final ScmBranch branchTag) {
        String branchTagName = branchTag.getName();
        String projectRoot = getProjectRoot(repositoryUrl);
        branchTagName = StringUtils.strip(branchTagName, "/");
        if (StringUtils.isEmpty(branchTagName)) {
            return null;
        }
        String queryString = null;
        if (repositoryUrl.indexOf(63) >= 0) {
            queryString = repositoryUrl.substring(repositoryUrl.indexOf(63));
            projectRoot = StringUtils.replace(projectRoot, queryString, "");
        }
        if (branchTagName.indexOf("://") >= 0) {
            return branchTagName;
        }
        if (StringUtils.isNotEmpty(tagBase) && !tagBase.equals(resolveTagBase(repositoryUrl)) && !tagBase.equals(resolveBranchBase(repositoryUrl))) {
            return appendPath(tagBase, branchTagName);
        }
        for (int i = 0; i < SvnTagBranchUtils.SVN_BASE_DIRS.length; ++i) {
            if (branchTagName.startsWith(SvnTagBranchUtils.SVN_BASE_DIRS[i] + "/")) {
                return addSuffix(appendPath(projectRoot, branchTagName), queryString);
            }
        }
        return addSuffix(appendPath(appendPath(projectRoot, subdir), branchTagName), queryString);
    }
    
    private static boolean checkRevisionArg(final String arg) {
        if (StringUtils.isNumeric(arg) || (arg.startsWith("{") && arg.endsWith("}"))) {
            return true;
        }
        for (int i = 0; i < SvnTagBranchUtils.REVISION_SPECIFIERS.length; ++i) {
            if (SvnTagBranchUtils.REVISION_SPECIFIERS[i].equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isRevisionSpecifier(final ScmVersion version) {
        if (version == null) {
            return false;
        }
        final String versionName = version.getName();
        if (StringUtils.isEmpty(versionName)) {
            return false;
        }
        if (checkRevisionArg(versionName)) {
            return true;
        }
        final String[] parts = StringUtils.split(versionName, ":");
        return parts.length == 2 && StringUtils.isNotEmpty(parts[0]) && StringUtils.isNotEmpty(parts[1]) && checkRevisionArg(parts[0]) && checkRevisionArg(parts[1]);
    }
    
    static {
        REVISION_SPECIFIERS = new String[] { "HEAD", "BASE", "COMMITTED", "PREV" };
        SVN_BASE_DIRS = new String[] { "trunk", "branches", "tags" };
    }
}
