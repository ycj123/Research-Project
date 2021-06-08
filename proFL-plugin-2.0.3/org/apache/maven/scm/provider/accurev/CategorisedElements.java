// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.accurev;

import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class CategorisedElements
{
    private List<File> memberElements;
    private List<File> nonMemberElements;
    
    public CategorisedElements() {
        this.memberElements = new ArrayList<File>();
        this.nonMemberElements = new ArrayList<File>();
    }
    
    public List<File> getMemberElements() {
        return this.memberElements;
    }
    
    public List<File> getNonMemberElements() {
        return this.nonMemberElements;
    }
}
