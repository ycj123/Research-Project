// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import java.util.List;
import org.codehaus.groovy.groovydoc.GroovyPackageDoc;
import java.util.Map;
import org.codehaus.groovy.groovydoc.GroovyRootDoc;

public class SimpleGroovyRootDoc extends SimpleGroovyDoc implements GroovyRootDoc
{
    private Map<String, GroovyPackageDoc> packageDocs;
    private List<GroovyPackageDoc> packageDocValues;
    private Map<String, GroovyClassDoc> classDocs;
    private List<GroovyClassDoc> classDocValues;
    private String description;
    
    public SimpleGroovyRootDoc(final String name) {
        super(name);
        this.packageDocValues = null;
        this.classDocValues = null;
        this.description = "";
        this.packageDocs = new HashMap<String, GroovyPackageDoc>();
        this.classDocs = new HashMap<String, GroovyClassDoc>();
    }
    
    public GroovyClassDoc classNamed(final String name) {
        for (final String key : this.classDocs.keySet()) {
            if (key.equals(name)) {
                return this.classDocs.get(key);
            }
            final int lastSlashIdx = key.lastIndexOf(47);
            if (lastSlashIdx <= 0) {
                continue;
            }
            final String shortKey = key.substring(lastSlashIdx + 1);
            if (shortKey.equals(name)) {
                return this.classDocs.get(key);
            }
        }
        return null;
    }
    
    public GroovyClassDoc classNamedExact(final String name) {
        for (final String key : this.classDocs.keySet()) {
            if (key.equals(name)) {
                return this.classDocs.get(key);
            }
        }
        return null;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String description() {
        return this.description;
    }
    
    public String summary() {
        return SimpleGroovyDoc.calculateFirstSentence(this.description);
    }
    
    public GroovyClassDoc[] classes() {
        if (this.classDocValues == null) {
            Collections.sort(this.classDocValues = new ArrayList<GroovyClassDoc>(this.classDocs.values()));
        }
        return this.classDocValues.toArray(new GroovyClassDoc[this.classDocValues.size()]);
    }
    
    public String[][] options() {
        return null;
    }
    
    public GroovyPackageDoc packageNamed(final String packageName) {
        return this.packageDocs.get(packageName);
    }
    
    public void putAllClasses(final Map<String, GroovyClassDoc> classes) {
        this.classDocs.putAll(classes);
        this.classDocValues = null;
    }
    
    public void put(final String packageName, final GroovyPackageDoc packageDoc) {
        this.packageDocs.put(packageName, packageDoc);
        this.packageDocValues = null;
    }
    
    public GroovyClassDoc[] specifiedClasses() {
        return null;
    }
    
    public GroovyPackageDoc[] specifiedPackages() {
        if (this.packageDocValues == null) {
            Collections.sort(this.packageDocValues = new ArrayList<GroovyPackageDoc>(this.packageDocs.values()));
        }
        return this.packageDocValues.toArray(new GroovyPackageDoc[this.packageDocValues.size()]);
    }
    
    public Map<String, GroovyClassDoc> getVisibleClasses(final List importedClassesAndPackages) {
        final Map<String, GroovyClassDoc> visibleClasses = new HashMap<String, GroovyClassDoc>();
        for (final String fullClassName : this.classDocs.keySet()) {
            final String equivalentPackageImport = fullClassName.replaceAll("[^/]+$", "*");
            if (importedClassesAndPackages.contains(fullClassName) || importedClassesAndPackages.contains(equivalentPackageImport)) {
                final GroovyClassDoc classDoc = this.classDocs.get(fullClassName);
                visibleClasses.put(classDoc.name(), classDoc);
            }
        }
        return visibleClasses;
    }
    
    public void printError(final String arg0) {
    }
    
    public void printNotice(final String arg0) {
    }
    
    public void printWarning(final String arg0) {
    }
    
    public void resolve() {
        for (final GroovyClassDoc groovyClassDoc : this.classDocs.values()) {
            final SimpleGroovyClassDoc classDoc = (SimpleGroovyClassDoc)groovyClassDoc;
            classDoc.resolve(this);
        }
    }
}
