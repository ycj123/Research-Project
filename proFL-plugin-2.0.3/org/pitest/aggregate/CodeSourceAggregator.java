// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.aggregate;

import org.pitest.classinfo.ClassName;
import java.util.Iterator;
import org.pitest.util.Glob;
import org.pitest.functional.FCollection;
import org.pitest.classpath.DirectoryClassPathRoot;
import java.util.HashSet;
import org.pitest.classpath.ClassPathRoot;
import org.pitest.functional.predicate.Predicate;
import org.pitest.classpath.PathFilter;
import org.pitest.functional.F;
import org.pitest.functional.prelude.Prelude;
import org.pitest.mutationtest.config.DefaultDependencyPathPredicate;
import org.pitest.classpath.ClassFilter;
import org.pitest.mutationtest.config.DefaultCodePathPredicate;
import org.pitest.classpath.ClassPath;
import org.pitest.classpath.ProjectClassPaths;
import org.pitest.classpath.CodeSource;
import java.util.Collections;
import java.io.File;
import java.util.Collection;

class CodeSourceAggregator
{
    private final Collection<File> compiledCodeDirectories;
    
    CodeSourceAggregator(final Collection<File> compiledCodeDirectories) {
        this.compiledCodeDirectories = Collections.unmodifiableCollection((Collection<? extends File>)compiledCodeDirectories);
    }
    
    public CodeSource createCodeSource() {
        return new CodeSource(this.createProjectClassPaths());
    }
    
    private ProjectClassPaths createProjectClassPaths() {
        final ClassPath classPath = new ClassPath(this.compiledCodeDirectories);
        final Predicate<String> classPredicate = this.createClassPredicate();
        final Predicate<ClassPathRoot> pathPredicate = new DefaultCodePathPredicate();
        return new ProjectClassPaths(classPath, new ClassFilter(classPredicate, classPredicate), new PathFilter(pathPredicate, (Predicate<ClassPathRoot>)Prelude.not((F<Object, Boolean>)new DefaultDependencyPathPredicate())));
    }
    
    private Predicate<String> createClassPredicate() {
        final Collection<String> classes = new HashSet<String>();
        for (final File buildOutputDirectory : this.compiledCodeDirectories) {
            if (buildOutputDirectory.exists()) {
                final DirectoryClassPathRoot dcRoot = new DirectoryClassPathRoot(buildOutputDirectory);
                classes.addAll(FCollection.map(dcRoot.classNames(), this.toPredicate()));
            }
        }
        return Prelude.or(FCollection.map(classes, Glob.toGlobPredicate()));
    }
    
    private F<String, String> toPredicate() {
        return new F<String, String>() {
            @Override
            public String apply(final String a) {
                return ClassName.fromString(a).getPackage().asJavaName() + ".*";
            }
        };
    }
}
