// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.HashMap;
import org.codehaus.groovy.groovydoc.GroovyPackageDoc;
import java.util.regex.Matcher;
import org.codehaus.groovy.groovydoc.GroovyAnnotationRef;
import org.codehaus.groovy.groovydoc.GroovyParameter;
import org.codehaus.groovy.groovydoc.GroovyType;
import java.util.Iterator;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Collection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.ArrayList;
import org.codehaus.groovy.groovydoc.GroovyRootDoc;
import org.codehaus.groovy.groovydoc.GroovyMethodDoc;
import org.codehaus.groovy.groovydoc.GroovyFieldDoc;
import org.codehaus.groovy.groovydoc.GroovyConstructorDoc;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;

public class SimpleGroovyClassDoc extends SimpleGroovyAbstractableElementDoc implements GroovyClassDoc
{
    public static final Pattern TAG_REGEX;
    public static final Pattern LINK_REGEX;
    public static final Pattern CODE_REGEX;
    public static final Pattern REF_LABEL_REGEX;
    public static final Pattern NAME_ARGS_REGEX;
    public static final Pattern SPLIT_ARGS_REGEX;
    private static final List<String> PRIMITIVES;
    private static final Map<String, String> TAG_TEXT;
    private final List<GroovyConstructorDoc> constructors;
    private final List<GroovyFieldDoc> fields;
    private final List<GroovyFieldDoc> properties;
    private final List<GroovyFieldDoc> enumConstants;
    private final List<GroovyMethodDoc> methods;
    private final List<String> importedClassesAndPackages;
    private final List<String> interfaceNames;
    private final List<GroovyClassDoc> interfaceClasses;
    private final List<GroovyClassDoc> nested;
    private final List<LinkArgument> links;
    private GroovyClassDoc superClass;
    private GroovyClassDoc outer;
    private String superClassName;
    private String fullPathName;
    private boolean isgroovy;
    private GroovyRootDoc savedRootDoc;
    
    public SimpleGroovyClassDoc(final List<String> importedClassesAndPackages, final String name, final List<LinkArgument> links) {
        super(name);
        this.savedRootDoc = null;
        this.importedClassesAndPackages = importedClassesAndPackages;
        this.links = links;
        this.constructors = new ArrayList<GroovyConstructorDoc>();
        this.fields = new ArrayList<GroovyFieldDoc>();
        this.properties = new ArrayList<GroovyFieldDoc>();
        this.enumConstants = new ArrayList<GroovyFieldDoc>();
        this.methods = new ArrayList<GroovyMethodDoc>();
        this.interfaceNames = new ArrayList<String>();
        this.interfaceClasses = new ArrayList<GroovyClassDoc>();
        this.nested = new ArrayList<GroovyClassDoc>();
    }
    
    public SimpleGroovyClassDoc(final List<String> importedClassesAndPackages, final String name) {
        this(importedClassesAndPackages, name, new ArrayList<LinkArgument>());
    }
    
    public GroovyConstructorDoc[] constructors() {
        Collections.sort(this.constructors);
        return this.constructors.toArray(new GroovyConstructorDoc[this.constructors.size()]);
    }
    
    public boolean add(final GroovyConstructorDoc constructor) {
        return this.constructors.add(constructor);
    }
    
    public GroovyClassDoc getOuter() {
        return this.outer;
    }
    
    public void setOuter(final GroovyClassDoc outer) {
        this.outer = outer;
    }
    
    public boolean isGroovy() {
        return this.isgroovy;
    }
    
    public void setGroovy(final boolean isgroovy) {
        this.isgroovy = isgroovy;
    }
    
    public GroovyClassDoc[] innerClasses() {
        Collections.sort(this.nested);
        return this.nested.toArray(new GroovyClassDoc[this.nested.size()]);
    }
    
    public boolean addNested(final GroovyClassDoc nestedClass) {
        return this.nested.add(nestedClass);
    }
    
    public GroovyFieldDoc[] fields() {
        Collections.sort(this.fields);
        return this.fields.toArray(new GroovyFieldDoc[this.fields.size()]);
    }
    
    public boolean add(final GroovyFieldDoc field) {
        return this.fields.add(field);
    }
    
    public GroovyFieldDoc[] properties() {
        Collections.sort(this.properties);
        return this.properties.toArray(new GroovyFieldDoc[this.properties.size()]);
    }
    
    public boolean addProperty(final GroovyFieldDoc property) {
        return this.properties.add(property);
    }
    
    public GroovyFieldDoc[] enumConstants() {
        Collections.sort(this.enumConstants);
        return this.enumConstants.toArray(new GroovyFieldDoc[this.enumConstants.size()]);
    }
    
    public boolean addEnumConstant(final GroovyFieldDoc field) {
        return this.enumConstants.add(field);
    }
    
    public GroovyMethodDoc[] methods() {
        Collections.sort(this.methods);
        return this.methods.toArray(new GroovyMethodDoc[this.methods.size()]);
    }
    
    public boolean add(final GroovyMethodDoc method) {
        return this.methods.add(method);
    }
    
    public String getSuperClassName() {
        return this.superClassName;
    }
    
    public void setSuperClassName(final String className) {
        this.superClassName = className;
    }
    
    public GroovyClassDoc superclass() {
        return this.superClass;
    }
    
    public void setSuperClass(final GroovyClassDoc doc) {
        this.superClass = doc;
    }
    
    public String getFullPathName() {
        return this.fullPathName;
    }
    
    public void setFullPathName(final String fullPathName) {
        this.fullPathName = fullPathName;
    }
    
    public String getRelativeRootPath() {
        final StringTokenizer tokenizer = new StringTokenizer(this.fullPathName, "/");
        final StringBuffer sb = new StringBuffer();
        if (tokenizer.hasMoreTokens()) {
            tokenizer.nextToken();
        }
        while (tokenizer.hasMoreTokens()) {
            tokenizer.nextToken();
            sb.append("../");
        }
        return sb.toString();
    }
    
    public List<GroovyClassDoc> getParentClasses() {
        final List<GroovyClassDoc> result = new LinkedList<GroovyClassDoc>();
        if (this.isInterface()) {
            return result;
        }
        result.add(0, this);
        GroovyClassDoc next = this;
        while (next.superclass() != null && !"java.lang.Object".equals(next.qualifiedTypeName())) {
            next = next.superclass();
            result.add(0, next);
        }
        GroovyClassDoc prev = next;
        Class nextClass = this.getClassOf(next.qualifiedTypeName());
        while (nextClass != null && nextClass.getSuperclass() != null && !Object.class.equals(nextClass)) {
            nextClass = nextClass.getSuperclass();
            final GroovyClassDoc nextDoc = new ExternalGroovyClassDoc(nextClass);
            if (prev instanceof SimpleGroovyClassDoc) {
                final SimpleGroovyClassDoc parent = (SimpleGroovyClassDoc)prev;
                parent.setSuperClass(nextDoc);
            }
            result.add(0, nextDoc);
            prev = nextDoc;
        }
        if (!result.get(0).qualifiedTypeName().equals("java.lang.Object")) {
            result.add(0, new ExternalGroovyClassDoc(Object.class));
        }
        return result;
    }
    
    public Set<GroovyClassDoc> getParentInterfaces() {
        final Set<GroovyClassDoc> result = new HashSet<GroovyClassDoc>();
        result.add(this);
        Set<GroovyClassDoc> next = new HashSet<GroovyClassDoc>();
        next.addAll(Arrays.asList(this.interfaces()));
        while (next.size() > 0) {
            final Set<GroovyClassDoc> temp = next;
            next = new HashSet<GroovyClassDoc>();
            for (final GroovyClassDoc t : temp) {
                if (t instanceof SimpleGroovyClassDoc) {
                    next.addAll(((SimpleGroovyClassDoc)t).getParentInterfaces());
                }
                else {
                    if (!(t instanceof ExternalGroovyClassDoc)) {
                        continue;
                    }
                    final ExternalGroovyClassDoc d = (ExternalGroovyClassDoc)t;
                    next.addAll(this.getJavaInterfaces(d));
                }
            }
            next = DefaultGroovyMethods.minus(next, result);
            result.addAll(next);
        }
        return result;
    }
    
    private Set<GroovyClassDoc> getJavaInterfaces(final ExternalGroovyClassDoc d) {
        final Set<GroovyClassDoc> result = new HashSet<GroovyClassDoc>();
        final Class[] interfaces = d.externalClass().getInterfaces();
        if (interfaces != null) {
            for (final Class i : interfaces) {
                final ExternalGroovyClassDoc doc = new ExternalGroovyClassDoc(i);
                result.add(doc);
                result.addAll(this.getJavaInterfaces(doc));
            }
        }
        return result;
    }
    
    private Class getClassOf(final String next) {
        try {
            return Class.forName(next.replace("/", "."));
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    void resolve(final GroovyRootDoc rootDoc) {
        this.savedRootDoc = rootDoc;
        final Map visibleClasses = rootDoc.getVisibleClasses(this.importedClassesAndPackages);
        for (final GroovyConstructorDoc constructor : this.constructors) {
            for (final GroovyParameter groovyParameter : constructor.parameters()) {
                final SimpleGroovyParameter param = (SimpleGroovyParameter)groovyParameter;
                final String paramTypeName = param.typeName();
                if (visibleClasses.containsKey(paramTypeName)) {
                    param.setType(visibleClasses.get(paramTypeName));
                }
                else {
                    final GroovyClassDoc doc = this.resolveClass(rootDoc, paramTypeName);
                    if (doc != null) {
                        param.setType(doc);
                    }
                }
            }
        }
        for (final GroovyFieldDoc field : this.fields) {
            final SimpleGroovyFieldDoc mutableField = (SimpleGroovyFieldDoc)field;
            final GroovyType fieldType = field.type();
            final String typeName = fieldType.typeName();
            if (visibleClasses.containsKey(typeName)) {
                mutableField.setType(visibleClasses.get(typeName));
            }
            else {
                final GroovyClassDoc doc2 = this.resolveClass(rootDoc, typeName);
                if (doc2 == null) {
                    continue;
                }
                mutableField.setType(doc2);
            }
        }
        for (final GroovyMethodDoc method : this.methods) {
            final GroovyType returnType = method.returnType();
            final String typeName2 = returnType.typeName();
            if (visibleClasses.containsKey(typeName2)) {
                method.setReturnType(visibleClasses.get(typeName2));
            }
            else {
                final GroovyClassDoc doc3 = this.resolveClass(rootDoc, typeName2);
                if (doc3 != null) {
                    method.setReturnType(doc3);
                }
            }
            for (final GroovyParameter groovyParameter2 : method.parameters()) {
                final SimpleGroovyParameter param2 = (SimpleGroovyParameter)groovyParameter2;
                final String paramTypeName2 = param2.typeName();
                if (visibleClasses.containsKey(paramTypeName2)) {
                    param2.setType(visibleClasses.get(paramTypeName2));
                }
                else {
                    final GroovyClassDoc doc4 = this.resolveClass(rootDoc, paramTypeName2);
                    if (doc4 != null) {
                        param2.setType(doc4);
                    }
                }
            }
        }
        if (this.superClassName != null && this.superClass == null) {
            this.superClass = this.resolveClass(rootDoc, this.superClassName);
        }
        for (final String name : this.interfaceNames) {
            this.interfaceClasses.add(this.resolveClass(rootDoc, name));
        }
        for (final GroovyAnnotationRef annotation : this.annotations()) {
            final SimpleGroovyAnnotationRef ref = (SimpleGroovyAnnotationRef)annotation;
            ref.setType(this.resolveClass(rootDoc, ref.name()));
        }
    }
    
    public String getDocUrl(final String type) {
        return this.getDocUrl(type, false);
    }
    
    public String getDocUrl(final String type, final boolean full) {
        return getDocUrl(type, full, this.links, this.getRelativeRootPath(), this.savedRootDoc, this);
    }
    
    private static String resolveMethodArgs(final GroovyRootDoc rootDoc, final SimpleGroovyClassDoc classDoc, final String type) {
        if (type.indexOf("(") < 0) {
            return type;
        }
        final Matcher m = SimpleGroovyClassDoc.NAME_ARGS_REGEX.matcher(type);
        if (m.matches()) {
            final String name = m.group(1);
            final String args = m.group(2);
            final StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append("(");
            final String[] argParts = SimpleGroovyClassDoc.SPLIT_ARGS_REGEX.split(args);
            boolean first = true;
            for (final String argPart : argParts) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(", ");
                }
                final GroovyClassDoc doc = classDoc.resolveClass(rootDoc, argPart);
                sb.append((doc == null) ? argPart : doc.qualifiedTypeName());
            }
            sb.append(")");
            return sb.toString();
        }
        return type;
    }
    
    public static String getDocUrl(String type, final boolean full, final List<LinkArgument> links, final String relativePath, final GroovyRootDoc rootDoc, final SimpleGroovyClassDoc classDoc) {
        if (type == null) {
            return type;
        }
        type = type.trim();
        if (isPrimitiveType(type)) {
            return type;
        }
        if (type.equals("def")) {
            type = "java.lang.Object def";
        }
        String label = null;
        final Matcher matcher = SimpleGroovyClassDoc.REF_LABEL_REGEX.matcher(type);
        if (matcher.find()) {
            type = matcher.group(1);
            label = matcher.group(4);
        }
        if (type.startsWith("#")) {
            return "<a href='" + resolveMethodArgs(rootDoc, classDoc, type) + "'>" + ((label == null) ? type.substring(1) : label) + "</a>";
        }
        if (type.endsWith("[]")) {
            if (label != null) {
                return getDocUrl(type.substring(0, type.length() - 2) + " " + label, full, links, relativePath, rootDoc, classDoc);
            }
            return getDocUrl(type.substring(0, type.length() - 2), full, links, relativePath, rootDoc, classDoc) + "[]";
        }
        else {
            if (type.indexOf(46) == -1 && classDoc != null) {
                final String[] pieces = type.split("#");
                final String candidate = pieces[0];
                final Class c = classDoc.resolveExternalClassFromImport(candidate);
                if (c != null) {
                    type = c.getName();
                }
                if (pieces.length > 1) {
                    type = type + "#" + pieces[1];
                }
                type = resolveMethodArgs(rootDoc, classDoc, type);
            }
            if (type.indexOf(46) == -1) {
                return type;
            }
            final String[] target = type.split("#");
            String shortClassName = target[0].replaceAll(".*\\.", "");
            shortClassName += ((target.length > 1) ? ("#" + target[1].split("\\(")[0]) : "");
            final String name = (full ? target[0] : shortClassName).replaceAll("#", ".");
            if (rootDoc != null) {
                final String slashedName = target[0].replaceAll("\\.", "/");
                final GroovyClassDoc doc = rootDoc.classNamed(slashedName);
                if (doc != null) {
                    return buildUrl(relativePath, target, (label == null) ? name : label);
                }
            }
            for (final LinkArgument link : links) {
                final StringTokenizer tokenizer = new StringTokenizer(link.getPackages(), ", ");
                while (tokenizer.hasMoreTokens()) {
                    final String token = tokenizer.nextToken();
                    if (type.startsWith(token)) {
                        return buildUrl(link.getHref(), target, (label == null) ? name : label);
                    }
                }
            }
            return type;
        }
    }
    
    private static String buildUrl(String relativeRoot, final String[] target, final String shortClassName) {
        if (!relativeRoot.endsWith("/")) {
            relativeRoot += "/";
        }
        final String url = relativeRoot + target[0].replace('.', '/') + ".html" + ((target.length > 1) ? ("#" + target[1]) : "");
        return "<a href='" + url + "' title='" + shortClassName + "'>" + shortClassName + "</a>";
    }
    
    private GroovyClassDoc resolveClass(final GroovyRootDoc rootDoc, final String name) {
        if (isPrimitiveType(name)) {
            return null;
        }
        GroovyClassDoc doc = ((SimpleGroovyRootDoc)rootDoc).classNamedExact(name);
        if (doc != null) {
            return doc;
        }
        final int slashIndex = name.lastIndexOf("/");
        if (slashIndex < 1) {
            doc = this.resolveInternalClassDocFromImport(rootDoc, name);
            if (doc != null) {
                return doc;
            }
            for (final GroovyClassDoc nestedDoc : this.nested) {
                if (nestedDoc.name().endsWith("." + name)) {
                    return nestedDoc;
                }
            }
            doc = rootDoc.classNamed(name);
            if (doc != null) {
                return doc;
            }
        }
        String shortname = name;
        Class c = null;
        if (slashIndex > 0) {
            shortname = name.substring(slashIndex + 1);
            c = this.resolveExternalFullyQualifiedClass(name);
        }
        else {
            c = this.resolveExternalClassFromImport(name);
        }
        if (c != null) {
            return new ExternalGroovyClassDoc(c);
        }
        final SimpleGroovyClassDoc placeholder = new SimpleGroovyClassDoc(null, shortname);
        placeholder.setFullPathName(name);
        return placeholder;
    }
    
    private static boolean isPrimitiveType(final String name) {
        String type = name;
        if (name.endsWith("[]")) {
            type = name.substring(0, name.length() - 2);
        }
        return SimpleGroovyClassDoc.PRIMITIVES.contains(type);
    }
    
    private GroovyClassDoc resolveInternalClassDocFromImport(final GroovyRootDoc rootDoc, final String baseName) {
        if (isPrimitiveType(baseName)) {
            return null;
        }
        for (final String importName : this.importedClassesAndPackages) {
            if (importName.endsWith("/" + baseName)) {
                final GroovyClassDoc doc = ((SimpleGroovyRootDoc)rootDoc).classNamedExact(importName);
                if (doc != null) {
                    return doc;
                }
                continue;
            }
            else {
                if (!importName.endsWith("/*")) {
                    continue;
                }
                final GroovyClassDoc doc = ((SimpleGroovyRootDoc)rootDoc).classNamedExact(importName.substring(0, importName.length() - 2) + baseName);
                if (doc != null) {
                    return doc;
                }
                continue;
            }
        }
        return null;
    }
    
    private Class resolveExternalClassFromImport(final String name) {
        if (isPrimitiveType(name)) {
            return null;
        }
        for (final String importName : this.importedClassesAndPackages) {
            String candidate = null;
            if (importName.endsWith("/" + name)) {
                candidate = importName.replaceAll("/", ".");
            }
            else if (importName.endsWith("/*")) {
                candidate = importName.substring(0, importName.length() - 2).replace('/', '.') + "." + name;
            }
            if (candidate != null) {
                try {
                    return Class.forName(candidate);
                }
                catch (NoClassDefFoundError e) {}
                catch (ClassNotFoundException ex) {}
            }
        }
        return null;
    }
    
    private Class resolveExternalFullyQualifiedClass(final String name) {
        final String candidate = name.replace('/', '.');
        try {
            return Class.forName(candidate);
        }
        catch (NoClassDefFoundError e) {}
        catch (ClassNotFoundException ex) {}
        return null;
    }
    
    public GroovyConstructorDoc[] constructors(final boolean filter) {
        return null;
    }
    
    public boolean definesSerializableFields() {
        return false;
    }
    
    public GroovyFieldDoc[] fields(final boolean filter) {
        return null;
    }
    
    public GroovyClassDoc findClass(final String className) {
        return null;
    }
    
    public GroovyClassDoc[] importedClasses() {
        return null;
    }
    
    public GroovyPackageDoc[] importedPackages() {
        return null;
    }
    
    public GroovyClassDoc[] innerClasses(final boolean filter) {
        return null;
    }
    
    public GroovyClassDoc[] interfaces() {
        Collections.sort(this.interfaceClasses);
        return this.interfaceClasses.toArray(new GroovyClassDoc[this.interfaceClasses.size()]);
    }
    
    public GroovyType[] interfaceTypes() {
        return null;
    }
    
    public boolean isExternalizable() {
        return false;
    }
    
    public boolean isSerializable() {
        return false;
    }
    
    public GroovyMethodDoc[] methods(final boolean filter) {
        return null;
    }
    
    public GroovyFieldDoc[] serializableFields() {
        return null;
    }
    
    public GroovyMethodDoc[] serializationMethods() {
        return null;
    }
    
    public boolean subclassOf(final GroovyClassDoc gcd) {
        return false;
    }
    
    public GroovyType superclassType() {
        return null;
    }
    
    public boolean isPrimitive() {
        return false;
    }
    
    public String qualifiedTypeName() {
        final String qtnWithSlashes = this.fullPathName.startsWith("DefaultPackage/") ? this.fullPathName.substring("DefaultPackage/".length()) : this.fullPathName;
        return qtnWithSlashes.replace('/', '.');
    }
    
    public String simpleTypeName() {
        final String typeName = this.qualifiedTypeName();
        final int lastDot = typeName.lastIndexOf(46);
        if (lastDot < 0) {
            return typeName;
        }
        return typeName.substring(lastDot + 1);
    }
    
    public String typeName() {
        return null;
    }
    
    public void addInterfaceName(final String className) {
        this.interfaceNames.add(className);
    }
    
    @Override
    public String firstSentenceCommentText() {
        if (super.firstSentenceCommentText() == null) {
            this.setFirstSentenceCommentText(this.replaceTags(SimpleGroovyDoc.calculateFirstSentence(this.getRawCommentText())));
        }
        return super.firstSentenceCommentText();
    }
    
    @Override
    public String commentText() {
        if (super.commentText() == null) {
            this.setCommentText(this.replaceTags(this.getRawCommentText()));
        }
        return super.commentText();
    }
    
    public String replaceTags(final String comment) {
        String result = comment.replaceAll("(?m)^\\s*\\*", "");
        result = this.replaceAllTags(result, "", "", SimpleGroovyClassDoc.LINK_REGEX);
        result = this.replaceAllTags(result, "<TT>", "</TT>", SimpleGroovyClassDoc.CODE_REGEX);
        result = this.replaceAllTagsCollated(result, "<DL><DT><B>", ":</B></DT><DD>", "</DD><DD>", "</DD></DL>", SimpleGroovyClassDoc.TAG_REGEX);
        return decodeSpecialSymbols(result);
    }
    
    public String replaceAllTags(final String self, final String s1, final String s2, final Pattern regex) {
        final Matcher matcher = regex.matcher(self);
        if (matcher.find()) {
            matcher.reset();
            final StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                final String tagname = matcher.group(1);
                if (!tagname.equals("interface")) {
                    String content = encodeSpecialSymbols(matcher.group(2));
                    if (tagname.equals("link")) {
                        content = this.getDocUrl(content);
                    }
                    matcher.appendReplacement(sb, s1 + content + s2);
                }
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return self;
    }
    
    public String replaceAllTagsCollated(final String self, final String preKey, final String postKey, final String valueSeparator, final String postValues, final Pattern regex) {
        final Matcher matcher = regex.matcher(self + "@endMarker");
        if (matcher.find()) {
            matcher.reset();
            final Map<String, List<String>> savedTags = new HashMap<String, List<String>>();
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                final String tagname = matcher.group(1);
                if (!tagname.equals("interface")) {
                    String content = encodeSpecialSymbols(matcher.group(2));
                    if ("see".equals(tagname)) {
                        content = this.getDocUrl(content);
                    }
                    else if ("param".equals(tagname)) {
                        final int index = content.indexOf(" ");
                        if (index >= 0) {
                            content = "<code>" + content.substring(0, index) + "</code> - " + content.substring(index);
                        }
                    }
                    if (SimpleGroovyClassDoc.TAG_TEXT.containsKey(tagname)) {
                        final String text = SimpleGroovyClassDoc.TAG_TEXT.get(tagname);
                        List<String> contents = savedTags.get(text);
                        if (contents == null) {
                            contents = new ArrayList<String>();
                            savedTags.put(text, contents);
                        }
                        contents.add(content);
                        matcher.appendReplacement(sb, "");
                    }
                    else {
                        matcher.appendReplacement(sb, preKey + tagname + postKey + content + postValues);
                    }
                }
            }
            matcher.appendTail(sb);
            sb = new StringBuffer(sb.substring(0, sb.length() - 10));
            for (final Map.Entry<String, List<String>> e : savedTags.entrySet()) {
                sb.append(preKey);
                sb.append(e.getKey());
                sb.append(postKey);
                sb.append(DefaultGroovyMethods.join(e.getValue(), valueSeparator));
                sb.append(postValues);
            }
            return sb.toString();
        }
        return self;
    }
    
    public static String encodeSpecialSymbols(final String text) {
        return Matcher.quoteReplacement(text.replaceAll("@", "&at;"));
    }
    
    public static String decodeSpecialSymbols(final String text) {
        return text.replaceAll("&at;", "@");
    }
    
    static {
        TAG_REGEX = Pattern.compile("(?sm)\\s*@([a-zA-Z.]+)\\s+(.*?)(?=\\s+@)");
        LINK_REGEX = Pattern.compile("(?m)[{]@(link)\\s+([^}]*)}");
        CODE_REGEX = Pattern.compile("(?m)[{]@(code)\\s+([^}]*)}");
        REF_LABEL_REGEX = Pattern.compile("([\\w.#]*(\\(.*\\))?)(\\s(.*))?");
        NAME_ARGS_REGEX = Pattern.compile("([^(]+)\\(([^)]*)\\)");
        SPLIT_ARGS_REGEX = Pattern.compile(",\\s*");
        PRIMITIVES = Arrays.asList("void", "boolean", "byte", "short", "char", "int", "long", "float", "double");
        (TAG_TEXT = new HashMap<String, String>()).put("see", "See Also");
        SimpleGroovyClassDoc.TAG_TEXT.put("param", "Parameters");
        SimpleGroovyClassDoc.TAG_TEXT.put("throw", "Throws");
        SimpleGroovyClassDoc.TAG_TEXT.put("exception", "Throws");
        SimpleGroovyClassDoc.TAG_TEXT.put("return", "Returns");
        SimpleGroovyClassDoc.TAG_TEXT.put("since", "Since");
        SimpleGroovyClassDoc.TAG_TEXT.put("author", "Authors");
        SimpleGroovyClassDoc.TAG_TEXT.put("version", "Version");
    }
}
