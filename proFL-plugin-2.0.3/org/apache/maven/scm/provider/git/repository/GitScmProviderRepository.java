// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.git.repository;

import org.apache.maven.scm.provider.ScmProviderRepository;
import java.util.regex.Matcher;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.maven.scm.ScmException;
import java.util.regex.Pattern;
import org.apache.maven.scm.provider.ScmProviderRepositoryWithHost;

public class GitScmProviderRepository extends ScmProviderRepositoryWithHost
{
    public static final String URL_DELIMITER_FETCH = "[fetch=]";
    public static final String URL_DELIMITER_PUSH = "[push=]";
    public static final String PROTOCOL_SEPARATOR = "://";
    public static final String PROTOCOL_FILE = "file";
    public static final String PROTOCOL_GIT = "git";
    public static final String PROTOCOL_SSH = "ssh";
    public static final String PROTOCOL_HTTP = "http";
    public static final String PROTOCOL_HTTPS = "https";
    public static final String PROTOCOL_RSYNC = "rsync";
    private static final Pattern HOST_AND_PORT_EXTRACTOR;
    public static final String PROTOCOL_NONE = "";
    private String provider;
    private RepositoryUrl fetchInfo;
    private RepositoryUrl pushInfo;
    
    public GitScmProviderRepository(final String url) throws ScmException {
        if (url == null) {
            throw new ScmException("url must not be null");
        }
        if (url.startsWith("[fetch=]")) {
            String fetch = url.substring("[fetch=]".length());
            final int indexPushDelimiter = fetch.indexOf("[push=]");
            if (indexPushDelimiter >= 0) {
                final String push = fetch.substring(indexPushDelimiter + "[push=]".length());
                this.pushInfo = this.parseUrl(push);
                fetch = fetch.substring(0, indexPushDelimiter);
            }
            this.fetchInfo = this.parseUrl(fetch);
            if (this.pushInfo == null) {
                this.pushInfo = this.fetchInfo;
            }
        }
        else if (url.startsWith("[push=]")) {
            String push2 = url.substring("[push=]".length());
            final int indexFetchDelimiter = push2.indexOf("[fetch=]");
            if (indexFetchDelimiter >= 0) {
                final String fetch2 = push2.substring(indexFetchDelimiter + "[fetch=]".length());
                this.fetchInfo = this.parseUrl(fetch2);
                push2 = push2.substring(0, indexFetchDelimiter);
            }
            this.pushInfo = this.parseUrl(push2);
            if (this.fetchInfo == null) {
                this.fetchInfo = this.pushInfo;
            }
        }
        else {
            final RepositoryUrl url2 = this.parseUrl(url);
            this.pushInfo = url2;
            this.fetchInfo = url2;
        }
        this.setUser(this.pushInfo.getUserName());
        this.setPassword(this.pushInfo.getPassword());
        this.setHost(this.pushInfo.getHost());
        if (this.pushInfo.getPort() != null && this.pushInfo.getPort().length() > 0) {
            this.setPort(Integer.parseInt(this.pushInfo.getPort()));
        }
    }
    
    public GitScmProviderRepository(final String url, final String user, final String password) throws ScmException {
        this(url);
        this.setUser(user);
        this.setPassword(password);
    }
    
    public String getProvider() {
        return this.provider;
    }
    
    public RepositoryUrl getFetchInfo() {
        return this.fetchInfo;
    }
    
    public RepositoryUrl getPushInfo() {
        return this.pushInfo;
    }
    
    public String getFetchUrl() {
        return this.getUrl(this.fetchInfo);
    }
    
    public String getPushUrl() {
        return this.getUrl(this.pushInfo);
    }
    
    private RepositoryUrl parseUrl(String url) throws ScmException {
        final RepositoryUrl repoUrl = new RepositoryUrl();
        url = this.parseProtocol(repoUrl, url);
        url = this.parseUserInfo(repoUrl, url);
        url = this.parseHostAndPort(repoUrl, url);
        repoUrl.setPath(url);
        return repoUrl;
    }
    
    private String getUrl(final RepositoryUrl repoUrl) {
        final StringBuilder urlSb = new StringBuilder(repoUrl.getProtocol());
        boolean urlSupportsUserInformation = false;
        if ("ssh".equals(repoUrl.getProtocol()) || "rsync".equals(repoUrl.getProtocol()) || "git".equals(repoUrl.getProtocol()) || "http".equals(repoUrl.getProtocol()) || "https".equals(repoUrl.getProtocol()) || "".equals(repoUrl.getProtocol())) {
            urlSupportsUserInformation = true;
        }
        if (repoUrl.getProtocol() != null && repoUrl.getProtocol().length() > 0) {
            urlSb.append("://");
        }
        if (urlSupportsUserInformation) {
            String userName = repoUrl.getUserName();
            if (this.getUser() != null && this.getUser().length() > 0) {
                userName = this.getUser();
            }
            String password = repoUrl.getPassword();
            if (this.getPassword() != null && this.getPassword().length() > 0) {
                password = this.getPassword();
            }
            if (userName != null && userName.length() > 0) {
                try {
                    urlSb.append(URLEncoder.encode(userName, "UTF-8"));
                }
                catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (password != null && password.length() > 0) {
                    urlSb.append(':');
                    try {
                        urlSb.append(URLEncoder.encode(password, "UTF-8"));
                    }
                    catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                urlSb.append('@');
            }
        }
        urlSb.append(repoUrl.getHost());
        if (repoUrl.getPort() != null && repoUrl.getPort().length() > 0) {
            urlSb.append(':').append(repoUrl.getPort());
        }
        urlSb.append(repoUrl.getPath());
        return urlSb.toString();
    }
    
    private String parseProtocol(final RepositoryUrl repoUrl, String url) throws ScmException {
        if (url.startsWith("file://")) {
            repoUrl.setProtocol("file");
        }
        else if (url.startsWith("https://")) {
            repoUrl.setProtocol("https");
        }
        else if (url.startsWith("http://")) {
            repoUrl.setProtocol("http");
        }
        else if (url.startsWith("ssh://")) {
            repoUrl.setProtocol("ssh");
        }
        else if (url.startsWith("git://")) {
            repoUrl.setProtocol("git");
        }
        else {
            if (!url.startsWith("rsync://")) {
                repoUrl.setProtocol("");
                return url;
            }
            repoUrl.setProtocol("rsync");
        }
        url = url.substring(repoUrl.getProtocol().length() + 3);
        return url;
    }
    
    private String parseUserInfo(final RepositoryUrl repoUrl, String url) throws ScmException {
        final int indexAt = url.indexOf(64);
        if (indexAt >= 0) {
            final String userInfo = url.substring(0, indexAt);
            final int indexPwdSep = userInfo.indexOf(58);
            if (indexPwdSep < 0) {
                repoUrl.setUserName(userInfo);
            }
            else {
                repoUrl.setUserName(userInfo.substring(0, indexPwdSep));
                repoUrl.setPassword(userInfo.substring(indexPwdSep + 1));
            }
            url = url.substring(indexAt + 1);
        }
        return url;
    }
    
    private String parseHostAndPort(final RepositoryUrl repoUrl, final String url) throws ScmException {
        repoUrl.setPort("");
        repoUrl.setHost("");
        if ("file".equals(repoUrl.getProtocol())) {
            return url;
        }
        final Matcher hostAndPortMatcher = GitScmProviderRepository.HOST_AND_PORT_EXTRACTOR.matcher(url);
        if (hostAndPortMatcher.matches()) {
            if (hostAndPortMatcher.groupCount() > 1 && hostAndPortMatcher.group(1) != null) {
                repoUrl.setHost(hostAndPortMatcher.group(1));
            }
            if (hostAndPortMatcher.groupCount() > 2 && hostAndPortMatcher.group(2) != null) {
                repoUrl.setPort(hostAndPortMatcher.group(2));
            }
            final StringBuilder computedUrl = new StringBuilder();
            if (hostAndPortMatcher.group(hostAndPortMatcher.groupCount() - 1) != null) {
                computedUrl.append(hostAndPortMatcher.group(hostAndPortMatcher.groupCount() - 1));
            }
            if (hostAndPortMatcher.group(hostAndPortMatcher.groupCount()) != null) {
                computedUrl.append(hostAndPortMatcher.group(hostAndPortMatcher.groupCount()));
            }
            return computedUrl.toString();
        }
        return url;
    }
    
    @Override
    public String getRelativePath(final ScmProviderRepository ancestor) {
        if (ancestor instanceof GitScmProviderRepository) {
            final GitScmProviderRepository gitAncestor = (GitScmProviderRepository)ancestor;
            final String url = this.getFetchUrl();
            final String path = url.replaceFirst(gitAncestor.getFetchUrl() + "/", "");
            if (!path.equals(url)) {
                return path;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        if (this.fetchInfo == this.pushInfo) {
            return this.getUrl(this.fetchInfo);
        }
        return "[fetch=]" + this.getUrl(this.fetchInfo) + "[push=]" + this.getUrl(this.pushInfo);
    }
    
    static {
        HOST_AND_PORT_EXTRACTOR = Pattern.compile("([^:/\\\\~]*)(?::(\\d*))?(?:([:/\\\\~])(.*))?");
    }
}
