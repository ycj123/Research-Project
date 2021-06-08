// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.synergy.repository;

import java.util.StringTokenizer;
import java.net.UnknownHostException;
import java.net.URISyntaxException;
import java.net.MalformedURLException;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.scm.provider.ScmProviderRepository;

public class SynergyScmProviderRepository extends ScmProviderRepository
{
    private String projectSpec;
    private String projectName;
    private String projectVersion;
    private String projectRelease;
    private String projectPurpose;
    private String delimiter;
    private String instance;
    
    public SynergyScmProviderRepository(final String url) throws ScmRepositoryException {
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
        if (tokenizer.countTokens() == 5) {
            this.projectName = tokenizer.nextToken();
            this.delimiter = tokenizer.nextToken();
            this.projectVersion = tokenizer.nextToken();
            this.projectRelease = tokenizer.nextToken();
            this.projectPurpose = tokenizer.nextToken();
            this.instance = "1";
            this.projectSpec = this.projectName + this.delimiter + this.projectVersion + ":project:" + this.instance;
        }
        else {
            if (tokenizer.countTokens() != 6) {
                throw new MalformedURLException();
            }
            this.projectName = tokenizer.nextToken();
            this.delimiter = tokenizer.nextToken();
            this.projectVersion = tokenizer.nextToken();
            this.projectRelease = tokenizer.nextToken();
            this.projectPurpose = tokenizer.nextToken();
            this.instance = tokenizer.nextToken();
            this.projectSpec = this.projectName + this.delimiter + this.projectVersion + ":project:" + this.instance;
        }
    }
    
    public String getProjectSpec() {
        return this.projectSpec;
    }
    
    public String getProjectName() {
        return this.projectName;
    }
    
    public String getProjectVersion() {
        return this.projectVersion;
    }
    
    public String getProjectPurpose() {
        return this.projectPurpose;
    }
    
    public String getProjectRelease() {
        return this.projectRelease;
    }
    
    public String getInstance() {
        return this.instance;
    }
}
