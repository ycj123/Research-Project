// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.testset;

public class TestArtifactInfo
{
    private final String version;
    private final String classifier;
    
    public TestArtifactInfo(final String version, final String classifier) {
        this.version = version;
        this.classifier = classifier;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public String getClassifier() {
        return this.classifier;
    }
}
