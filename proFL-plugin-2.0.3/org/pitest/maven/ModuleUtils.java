// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.apache.maven.artifact.Artifact;
import java.util.Set;
import java.util.Collection;
import org.pitest.functional.FCollection;
import org.pitest.classpath.DirectoryClassPathRoot;
import org.pitest.classinfo.ClassName;
import java.util.HashSet;
import org.pitest.functional.F;
import java.util.ArrayList;
import java.io.File;
import java.util.Iterator;
import org.apache.maven.project.MavenProject;
import java.util.List;

public class ModuleUtils
{
    static List<MavenProject> modules;
    
    public static void addToList(final List<String> targetList, final List<String> sourceList) {
        if (sourceList == null) {
            return;
        }
        for (final String cur : sourceList) {
            if (!targetList.contains(cur)) {
                targetList.add(cur);
            }
        }
    }
    
    public static List<File> namesToFiles(final List<String> nameList) {
        final List<File> files = new ArrayList<File>();
        final F<String, File> map = new F<String, File>() {
            @Override
            public File apply(final String s) {
                final File f = new File(s);
                if (f.exists()) {
                    return f;
                }
                return null;
            }
        };
        for (final String name : nameList) {
            final File file = map.apply(name);
            if (file != null) {
                files.add(file);
            }
        }
        return files;
    }
    
    public static Boolean exists(final List<String> fileNameList) {
        for (final String name : fileNameList) {
            final File file = new File(name);
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }
    
    public static List<String> getSrcClasses(final MavenProject project) {
        return getClassPatternsFromDir(project.getBuild().getOutputDirectory());
    }
    
    public static ArrayList<String> getTestClasses(final MavenProject project) {
        return getClassPatternsFromDir(project.getBuild().getTestOutputDirectory());
    }
    
    private static ArrayList<String> getClassPatternsFromDir(final String buildOutputDirectory) {
        final Set<String> classPatterns = new HashSet<String>();
        final F<String, String> classToPatterns = new F<String, String>() {
            @Override
            public String apply(final String clazz) {
                return ClassName.fromString(clazz).getPackage().asJavaName() + ".*";
            }
        };
        final File outputDir = new File(buildOutputDirectory);
        if (outputDir.exists()) {
            final DirectoryClassPathRoot classRoot = new DirectoryClassPathRoot(outputDir);
            final Collection<String> classes = classRoot.classNames();
            FCollection.mapTo(classes, classToPatterns, classPatterns);
        }
        return new ArrayList<String>(classPatterns);
    }
    
    public static void initialize(final AbstractPitMojo mojo) {
        final List<MavenProject> moduleList = (List<MavenProject>)mojo.getProject().getCollectedProjects();
        if (ModuleUtils.modules == null) {
            ModuleUtils.modules = moduleList;
        }
    }
    
    public static List<MavenProject> getDependingModules(final MavenProject project) {
        final List<MavenProject> modules = new ArrayList<MavenProject>();
        final Set<Artifact> dependencies = (Set<Artifact>)project.getArtifacts();
        for (final Artifact dependency : dependencies) {
            final MavenProject moduleProject = getMavenProjectFromName(dependency.getArtifactId());
            if (moduleProject != null) {
                modules.add(moduleProject);
            }
        }
        return modules;
    }
    
    public static boolean isProjectModule(final String name) {
        for (final MavenProject module : ModuleUtils.modules) {
            if (module.getArtifactId().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    public static MavenProject getMavenProjectFromName(final String name) {
        for (final MavenProject module : ModuleUtils.modules) {
            if (module.getArtifactId().equals(name)) {
                return module;
            }
        }
        return null;
    }
}
