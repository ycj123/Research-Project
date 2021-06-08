// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.io.File;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.apache.maven.project.MavenProject;
import org.pitest.functional.predicate.Predicate;

public class NonEmptyProjectCheck implements Predicate<MavenProject>
{
    @Override
    public Boolean apply(final MavenProject project) {
        return FCollection.contains(project.getTestCompileSourceRoots(), (F<Object, Boolean>)this.exists()) && FCollection.contains(project.getCompileSourceRoots(), (F<Object, Boolean>)this.exists());
    }
    
    private Predicate<String> exists() {
        return new Predicate<String>() {
            @Override
            public Boolean apply(final String root) {
                return new File(root).exists();
            }
        };
    }
}
