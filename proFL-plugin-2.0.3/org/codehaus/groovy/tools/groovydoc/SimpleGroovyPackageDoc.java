// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.StringTokenizer;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import java.util.Map;
import org.codehaus.groovy.groovydoc.GroovyPackageDoc;

public class SimpleGroovyPackageDoc extends SimpleGroovyDoc implements GroovyPackageDoc
{
    private static final char FS = '/';
    final Map<String, GroovyClassDoc> classDocs;
    private String description;
    private String summary;
    
    public SimpleGroovyPackageDoc(final String name) {
        super(name);
        this.description = "";
        this.summary = "";
        this.classDocs = new TreeMap<String, GroovyClassDoc>();
    }
    
    public GroovyClassDoc[] allClasses() {
        return this.classDocs.values().toArray(new GroovyClassDoc[this.classDocs.values().size()]);
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public void setSummary(final String summary) {
        this.summary = summary;
    }
    
    public void putAll(final Map<String, GroovyClassDoc> classes) {
        for (final Map.Entry<String, GroovyClassDoc> docEntry : classes.entrySet()) {
            final GroovyClassDoc classDoc = docEntry.getValue();
            this.classDocs.put(docEntry.getKey(), classDoc);
            final SimpleGroovyProgramElementDoc programElement = (SimpleGroovyProgramElementDoc)classDoc;
            programElement.setContainingPackage(this);
        }
    }
    
    public String nameWithDots() {
        return this.name().replace('/', '.');
    }
    
    public GroovyClassDoc[] allClasses(final boolean arg0) {
        final List<GroovyClassDoc> classDocValues = new ArrayList<GroovyClassDoc>(this.classDocs.values());
        return classDocValues.toArray(new GroovyClassDoc[classDocValues.size()]);
    }
    
    public GroovyClassDoc[] enums() {
        final List<GroovyClassDoc> result = new ArrayList<GroovyClassDoc>(this.classDocs.values().size());
        for (final GroovyClassDoc doc : this.classDocs.values()) {
            if (doc.isEnum()) {
                result.add(doc);
            }
        }
        return result.toArray(new GroovyClassDoc[result.size()]);
    }
    
    public GroovyClassDoc[] errors() {
        final List<GroovyClassDoc> result = new ArrayList<GroovyClassDoc>(this.classDocs.values().size());
        for (final GroovyClassDoc doc : this.classDocs.values()) {
            if (doc.isError()) {
                result.add(doc);
            }
        }
        return result.toArray(new GroovyClassDoc[result.size()]);
    }
    
    public GroovyClassDoc[] exceptions() {
        final List<GroovyClassDoc> result = new ArrayList<GroovyClassDoc>(this.classDocs.values().size());
        for (final GroovyClassDoc doc : this.classDocs.values()) {
            if (doc.isException()) {
                result.add(doc);
            }
        }
        return result.toArray(new GroovyClassDoc[result.size()]);
    }
    
    public GroovyClassDoc findClass(final String arg0) {
        return null;
    }
    
    public GroovyClassDoc[] interfaces() {
        final List<GroovyClassDoc> result = new ArrayList<GroovyClassDoc>(this.classDocs.values().size());
        for (final GroovyClassDoc doc : this.classDocs.values()) {
            if (doc.isInterface()) {
                result.add(doc);
            }
        }
        return result.toArray(new GroovyClassDoc[result.size()]);
    }
    
    public GroovyClassDoc[] ordinaryClasses() {
        final List<GroovyClassDoc> result = new ArrayList<GroovyClassDoc>(this.classDocs.values().size());
        for (final GroovyClassDoc doc : this.classDocs.values()) {
            if (doc.isOrdinaryClass()) {
                result.add(doc);
            }
        }
        return result.toArray(new GroovyClassDoc[result.size()]);
    }
    
    public String description() {
        return this.description;
    }
    
    public String summary() {
        return this.summary;
    }
    
    public String getRelativeRootPath() {
        final StringTokenizer tokenizer = new StringTokenizer(this.name(), "/");
        final StringBuffer sb = new StringBuffer();
        while (tokenizer.hasMoreTokens()) {
            tokenizer.nextToken();
            sb.append("../");
        }
        return sb.toString();
    }
}
