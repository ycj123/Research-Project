// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.clearcase.repository;

import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.StringTokenizer;
import java.net.UnknownHostException;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.providers.clearcase.settings.Settings;
import java.io.File;
import org.apache.maven.scm.log.ScmLogger;
import org.apache.maven.scm.provider.ScmProviderRepository;

public class ClearCaseScmProviderRepository extends ScmProviderRepository
{
    private ScmLogger logger;
    private boolean viewNameGivenByUser;
    private String viewName;
    private File configSpec;
    private String loadDirectory;
    private String streamName;
    private String vobName;
    private Settings settings;
    private String elementName;
    public static final String CLEARCASE_LT = "LT";
    public static final String CLEARCASE_UCM = "UCM";
    public static final String CLEARCASE_DEFAULT;
    
    public ClearCaseScmProviderRepository(final ScmLogger logger, final String url, final Settings settings) throws ScmRepositoryException {
        this.viewNameGivenByUser = false;
        this.logger = logger;
        this.settings = settings;
        try {
            this.parseUrl(url);
        }
        catch (MalformedURLException e) {
            throw new ScmRepositoryException("Illegal URL: " + url + "(" + e.getMessage() + ")");
        }
        catch (URISyntaxException e2) {
            throw new ScmRepositoryException("Illegal URL: " + url + "(" + e2.getMessage() + ")");
        }
        catch (UnknownHostException e3) {
            throw new ScmRepositoryException("Illegal URL: " + url + "(" + e3.getMessage() + ")");
        }
    }
    
    private void parseUrl(final String url) throws MalformedURLException, URISyntaxException, UnknownHostException {
        if (url.indexOf(124) != -1) {
            final StringTokenizer tokenizer = new StringTokenizer(url, "|");
            this.fillInProperties(tokenizer);
        }
        else {
            final StringTokenizer tokenizer = new StringTokenizer(url, ":");
            this.fillInProperties(tokenizer);
        }
    }
    
    private void fillInProperties(final StringTokenizer tokenizer) throws UnknownHostException, URISyntaxException, MalformedURLException {
        String configSpecString = null;
        if ("UCM".equals(this.settings.getClearcaseType())) {
            configSpecString = this.fillUCMProperties(tokenizer);
        }
        else {
            configSpecString = this.fillDefaultProperties(tokenizer);
        }
        if (!configSpecString.startsWith("load ")) {
            this.configSpec = this.createConfigSpecFile(configSpecString);
            this.loadDirectory = null;
        }
        else {
            this.configSpec = null;
            this.loadDirectory = configSpecString.substring(5);
        }
    }
    
    private String fillDefaultProperties(final StringTokenizer tokenizer) throws UnknownHostException {
        final int tokenNumber = tokenizer.countTokens();
        String configSpecString;
        if (tokenNumber == 1) {
            this.viewName = this.getDefaultViewName();
            configSpecString = tokenizer.nextToken();
        }
        else {
            configSpecString = this.checkViewName(tokenizer);
            this.checkUnexpectedParameter(tokenizer, tokenNumber, 2);
        }
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("viewName = '" + this.viewName + "' ; configSpec = '" + configSpecString + "'");
        }
        return configSpecString;
    }
    
    private String fillUCMProperties(final StringTokenizer tokenizer) throws UnknownHostException, MalformedURLException {
        final int tokenNumber = tokenizer.countTokens();
        if (tokenNumber <= 2) {
            throw new MalformedURLException("ClearCaseUCM need more parameters. Expected url format : [view_name]|[configspec]|[vob_name]|[stream_name]");
        }
        String configSpecString;
        if (tokenNumber == 3) {
            this.viewName = this.getDefaultViewName();
            configSpecString = tokenizer.nextToken();
            this.vobName = tokenizer.nextToken();
            this.streamName = tokenizer.nextToken();
        }
        else if (tokenNumber == 4) {
            final String[] tokens = { tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken() };
            if (tokens[3].startsWith("/main/")) {
                this.viewName = this.getDefaultViewName();
                configSpecString = tokens[0];
                this.vobName = tokens[1];
                this.streamName = tokens[2];
                this.elementName = tokens[3];
            }
            else {
                this.viewName = tokens[0];
                this.viewNameGivenByUser = true;
                configSpecString = tokens[1];
                this.vobName = tokens[2];
                this.streamName = tokens[3];
            }
        }
        else {
            configSpecString = this.checkViewName(tokenizer);
            this.vobName = tokenizer.nextToken();
            this.streamName = tokenizer.nextToken();
            this.elementName = tokenizer.nextToken();
            this.checkUnexpectedParameter(tokenizer, tokenNumber, 5);
        }
        if (this.logger.isInfoEnabled()) {
            this.logger.info("viewName = '" + this.viewName + "' ; configSpec = '" + configSpecString + "' ; vobName = '" + this.vobName + "' ; streamName = '" + this.streamName + "' ; elementName = '" + this.elementName + "'");
        }
        return configSpecString;
    }
    
    private String checkViewName(final StringTokenizer tokenizer) throws UnknownHostException {
        this.viewName = tokenizer.nextToken();
        if (this.viewName.length() > 0) {
            this.viewNameGivenByUser = true;
        }
        else {
            this.viewName = this.getDefaultViewName();
        }
        return tokenizer.nextToken();
    }
    
    private void checkUnexpectedParameter(final StringTokenizer tokenizer, final int tokenNumber, final int maxTokenNumber) {
        if (tokenNumber > maxTokenNumber) {
            final String unexpectedToken = tokenizer.nextToken();
            if (this.logger.isInfoEnabled()) {
                this.logger.info("The SCM URL contains unused parameter : " + unexpectedToken);
            }
        }
    }
    
    private File createConfigSpecFile(final String spec) throws URISyntaxException, MalformedURLException {
        File result;
        if (spec.indexOf(58) == -1) {
            result = new File(spec);
        }
        else {
            result = new File(new URI(new URL(spec).toString()));
        }
        return result;
    }
    
    private String getDefaultViewName() throws UnknownHostException {
        final String username = System.getProperty("user.name", "nouser");
        final String hostname = this.getHostName();
        return username + "-" + hostname + "-maven";
    }
    
    private String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
    
    public String getViewName(final String uniqueId) {
        String result;
        if (this.viewNameGivenByUser) {
            result = this.viewName;
        }
        else {
            result = this.viewName + "-" + uniqueId;
        }
        return result;
    }
    
    public File getConfigSpec() {
        return this.configSpec;
    }
    
    public boolean isAutoConfigSpec() {
        return this.configSpec == null;
    }
    
    public String getLoadDirectory() {
        return this.loadDirectory;
    }
    
    public String getStreamName() {
        return this.streamName;
    }
    
    public String getVobName() {
        return this.vobName;
    }
    
    public String getElementName() {
        return this.elementName;
    }
    
    public boolean hasElements() {
        return this.elementName != null;
    }
    
    static {
        CLEARCASE_DEFAULT = null;
    }
}
