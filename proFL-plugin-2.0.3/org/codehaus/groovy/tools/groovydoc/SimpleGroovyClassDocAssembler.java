// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import java.util.regex.Matcher;
import java.util.StringTokenizer;
import org.codehaus.groovy.groovydoc.GroovyAnnotationRef;
import org.codehaus.groovy.groovydoc.GroovyFieldDoc;
import org.codehaus.groovy.groovydoc.GroovyParameter;
import org.codehaus.groovy.groovydoc.GroovyMethodDoc;
import org.codehaus.groovy.groovydoc.GroovyType;
import org.codehaus.groovy.groovydoc.GroovyConstructorDoc;
import groovyjarjarantlr.collections.AST;
import java.util.Iterator;
import org.codehaus.groovy.control.ResolveVisitor;
import java.util.ArrayList;
import java.util.HashMap;
import org.codehaus.groovy.antlr.LineColumn;
import org.codehaus.groovy.antlr.SourceBuffer;
import java.util.Properties;
import java.util.List;
import org.codehaus.groovy.groovydoc.GroovyClassDoc;
import java.util.Map;
import org.codehaus.groovy.antlr.GroovySourceAST;
import java.util.Stack;
import java.util.regex.Pattern;
import org.codehaus.groovy.antlr.parser.GroovyTokenTypes;
import org.codehaus.groovy.antlr.treewalker.VisitorAdapter;

public class SimpleGroovyClassDocAssembler extends VisitorAdapter implements GroovyTokenTypes
{
    private static final String FS = "/";
    private static final Pattern PREV_JAVADOC_COMMENT_PATTERN;
    private final Stack<GroovySourceAST> stack;
    private Map<String, GroovyClassDoc> classDocs;
    private List<String> importedClassesAndPackages;
    private List<LinkArgument> links;
    private Properties properties;
    private SimpleGroovyFieldDoc currentFieldDoc;
    private SourceBuffer sourceBuffer;
    private String packagePath;
    private LineColumn lastLineCol;
    private boolean insideEnum;
    private Map<String, SimpleGroovyClassDoc> foundClasses;
    private boolean isGroovy;
    private boolean deferSetup;
    private String className;
    
    public SimpleGroovyClassDocAssembler(final String packagePath, final String file, final SourceBuffer sourceBuffer, final List<LinkArgument> links, final Properties properties, final boolean isGroovy) {
        this.sourceBuffer = sourceBuffer;
        this.packagePath = packagePath;
        this.links = links;
        this.properties = properties;
        this.isGroovy = isGroovy;
        this.stack = new Stack<GroovySourceAST>();
        this.classDocs = new HashMap<String, GroovyClassDoc>();
        this.className = file;
        if (file != null) {
            final int idx = file.lastIndexOf(".");
            this.className = file.substring(0, idx);
        }
        this.deferSetup = packagePath.equals("DefaultPackage");
        this.importedClassesAndPackages = new ArrayList<String>();
        if (!this.deferSetup) {
            this.setUpImports(packagePath, links, isGroovy, this.className);
        }
        this.lastLineCol = new LineColumn(1, 1);
    }
    
    private void setUpImports(final String packagePath, final List<LinkArgument> links, final boolean isGroovy, final String className) {
        this.importedClassesAndPackages.add(packagePath + "/*");
        if (isGroovy) {
            for (final String pkg : ResolveVisitor.DEFAULT_IMPORTS) {
                this.importedClassesAndPackages.add(pkg.replace('.', '/') + "*");
            }
        }
        else {
            this.importedClassesAndPackages.add("java/lang/*");
        }
        final SimpleGroovyClassDoc currentClassDoc = new SimpleGroovyClassDoc(this.importedClassesAndPackages, className, links);
        currentClassDoc.setFullPathName(packagePath + "/" + className);
        currentClassDoc.setGroovy(isGroovy);
        this.classDocs.put(currentClassDoc.getFullPathName(), currentClassDoc);
    }
    
    public Map<String, GroovyClassDoc> getGroovyClassDocs() {
        this.postProcessClassDocs();
        return this.classDocs;
    }
    
    @Override
    public void visitInterfaceDef(final GroovySourceAST t, final int visit) {
        this.visitClassDef(t, visit);
    }
    
    @Override
    public void visitEnumDef(final GroovySourceAST t, final int visit) {
        this.visitClassDef(t, visit);
        final SimpleGroovyClassDoc currentClassDoc = this.getCurrentOrTopLevelClassDoc(t);
        if (visit == 4 && currentClassDoc != null) {
            this.adjustForAutomaticEnumMethods(currentClassDoc);
        }
    }
    
    @Override
    public void visitAnnotationDef(final GroovySourceAST t, final int visit) {
        this.visitClassDef(t, visit);
    }
    
    @Override
    public void visitClassDef(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            final SimpleGroovyClassDoc parent = this.getCurrentClassDoc();
            String className;
            final String shortName = className = this.getIdentFor(t);
            if (parent != null && this.isNested() && !this.insideAnonymousInnerClass()) {
                className = parent.name() + "." + className;
            }
            else {
                this.foundClasses = new HashMap<String, SimpleGroovyClassDoc>();
            }
            SimpleGroovyClassDoc current = this.classDocs.get(this.packagePath + "/" + className);
            if (current == null) {
                current = new SimpleGroovyClassDoc(this.importedClassesAndPackages, className, this.links);
                current.setGroovy(this.isGroovy);
            }
            current.setRawCommentText(this.getJavaDocCommentsBeforeNode(t));
            current.setFullPathName(this.packagePath + "/" + current.name());
            current.setTokenType(t.getType());
            this.processAnnotations(t, current);
            this.processModifiers(t, current);
            this.classDocs.put(current.getFullPathName(), current);
            this.foundClasses.put(shortName, current);
            if (parent != null) {
                parent.addNested(current);
                current.setOuter(parent);
            }
        }
    }
    
    @Override
    public void visitPackageDef(final GroovySourceAST t, final int visit) {
        if (visit == 1 && this.deferSetup) {
            final String packageWithSlashes = this.extractImportPath(t);
            this.setUpImports(packageWithSlashes, this.links, this.isGroovy, this.className);
        }
    }
    
    @Override
    public void visitImport(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            final String importTextWithSlashesInsteadOfDots = this.extractImportPath(t);
            this.importedClassesAndPackages.add(importTextWithSlashesInsteadOfDots);
        }
    }
    
    @Override
    public void visitExtendsClause(final GroovySourceAST t, final int visit) {
        final SimpleGroovyClassDoc currentClassDoc = this.getCurrentClassDoc();
        if (visit == 1) {
            for (final GroovySourceAST superClassNode : this.findTypeNames(t)) {
                final String superClassName = this.extractName(superClassNode);
                if (currentClassDoc.isInterface()) {
                    currentClassDoc.addInterfaceName(superClassName);
                }
                else {
                    currentClassDoc.setSuperClassName(superClassName);
                }
            }
        }
    }
    
    @Override
    public void visitImplementsClause(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            for (final GroovySourceAST classNode : this.findTypeNames(t)) {
                this.getCurrentClassDoc().addInterfaceName(this.extractName(classNode));
            }
        }
    }
    
    private List<GroovySourceAST> findTypeNames(final GroovySourceAST t) {
        final List<GroovySourceAST> types = new ArrayList<GroovySourceAST>();
        for (AST child = t.getFirstChild(); child != null; child = child.getNextSibling()) {
            final GroovySourceAST groovySourceAST = (GroovySourceAST)child;
            if (groovySourceAST.getType() == 12) {
                types.add((GroovySourceAST)groovySourceAST.getFirstChild());
            }
            else {
                types.add(groovySourceAST);
            }
        }
        return types;
    }
    
    @Override
    public void visitCtorIdent(final GroovySourceAST t, final int visit) {
        if (visit == 1 && !this.insideEnum && !this.insideAnonymousInnerClass()) {
            final SimpleGroovyClassDoc currentClassDoc = this.getCurrentClassDoc();
            final SimpleGroovyConstructorDoc currentConstructorDoc = new SimpleGroovyConstructorDoc(currentClassDoc.name(), currentClassDoc);
            currentConstructorDoc.setRawCommentText(this.getJavaDocCommentsBeforeNode(t));
            this.processModifiers(t, currentConstructorDoc);
            this.addParametersTo(t, currentConstructorDoc);
            this.processAnnotations(t, currentConstructorDoc);
            currentClassDoc.add(currentConstructorDoc);
        }
    }
    
    @Override
    public void visitMethodDef(final GroovySourceAST t, final int visit) {
        if (visit == 1 && !this.insideEnum && !this.insideAnonymousInnerClass()) {
            SimpleGroovyClassDoc currentClassDoc = this.getCurrentClassDoc();
            if (currentClassDoc == null) {
                if (!"true".equals(this.properties.getProperty("processScripts", "true"))) {
                    return;
                }
                currentClassDoc = new SimpleGroovyClassDoc(this.importedClassesAndPackages, this.className, this.links);
                currentClassDoc.setFullPathName(this.packagePath + "/" + this.className);
                currentClassDoc.setPublic(true);
                currentClassDoc.setScript(true);
                currentClassDoc.setGroovy(this.isGroovy);
                currentClassDoc.setSuperClassName("groovy/lang/Script");
                if ("true".equals(this.properties.getProperty("includeMainForScripts", "true"))) {
                    currentClassDoc.add(this.createMainMethod(currentClassDoc));
                }
                this.classDocs.put(currentClassDoc.getFullPathName(), currentClassDoc);
                if (this.foundClasses == null) {
                    this.foundClasses = new HashMap<String, SimpleGroovyClassDoc>();
                }
                this.foundClasses.put(this.className, currentClassDoc);
            }
            final String methodName = this.getIdentFor(t);
            final SimpleGroovyMethodDoc currentMethodDoc = new SimpleGroovyMethodDoc(methodName, currentClassDoc);
            currentMethodDoc.setRawCommentText(this.getJavaDocCommentsBeforeNode(t));
            this.processModifiers(t, currentMethodDoc);
            currentMethodDoc.setReturnType(new SimpleGroovyType(this.getTypeOrDefault(t)));
            this.addParametersTo(t, currentMethodDoc);
            this.processAnnotations(t, currentMethodDoc);
            currentClassDoc.add(currentMethodDoc);
        }
    }
    
    private GroovyMethodDoc createMainMethod(final SimpleGroovyClassDoc currentClassDoc) {
        final SimpleGroovyMethodDoc mainMethod = new SimpleGroovyMethodDoc("main", currentClassDoc);
        mainMethod.setPublic(true);
        mainMethod.setStatic(true);
        mainMethod.setCommentText("Implicit main method for Groovy Scripts");
        mainMethod.setFirstSentenceCommentText(mainMethod.commentText());
        final SimpleGroovyParameter args = new SimpleGroovyParameter("args");
        final GroovyType argsType = new SimpleGroovyType("java.lang.String[]");
        args.setType(argsType);
        mainMethod.add(args);
        final GroovyType returnType = new SimpleGroovyType("void");
        mainMethod.setReturnType(returnType);
        return mainMethod;
    }
    
    @Override
    public void visitAnnotationFieldDef(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            this.visitVariableDef(t, visit);
            final String defaultText = this.getDefaultValue(t);
            if (defaultText != null) {
                this.currentFieldDoc.setConstantValueExpression(defaultText);
                final String orig = this.currentFieldDoc.getRawCommentText();
                this.currentFieldDoc.setRawCommentText(orig + "\n* @default " + defaultText);
            }
        }
    }
    
    @Override
    public void visitEnumConstantDef(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            final SimpleGroovyClassDoc currentClassDoc = this.getCurrentClassDoc();
            this.insideEnum = true;
            final String enumConstantName = this.getIdentFor(t);
            final SimpleGroovyFieldDoc currentEnumConstantDoc = new SimpleGroovyFieldDoc(enumConstantName, currentClassDoc);
            currentEnumConstantDoc.setRawCommentText(this.getJavaDocCommentsBeforeNode(t));
            this.processModifiers(t, currentEnumConstantDoc);
            final String typeName = this.getTypeNodeAsText(t.childOfType(12), currentClassDoc.getTypeDescription());
            currentEnumConstantDoc.setType(new SimpleGroovyType(typeName));
            currentClassDoc.addEnumConstant(currentEnumConstantDoc);
        }
        else if (visit == 4) {
            this.insideEnum = false;
        }
    }
    
    @Override
    public void visitVariableDef(final GroovySourceAST t, final int visit) {
        if (visit == 1 && !this.insideAnonymousInnerClass() && this.isFieldDefinition()) {
            final SimpleGroovyClassDoc currentClassDoc = this.getCurrentClassDoc();
            if (currentClassDoc != null) {
                final String fieldName = this.getIdentFor(t);
                (this.currentFieldDoc = new SimpleGroovyFieldDoc(fieldName, currentClassDoc)).setRawCommentText(this.getJavaDocCommentsBeforeNode(t));
                final boolean isProp = this.processModifiers(t, this.currentFieldDoc);
                this.currentFieldDoc.setType(new SimpleGroovyType(this.getTypeOrDefault(t)));
                this.processAnnotations(t, this.currentFieldDoc);
                if (isProp) {
                    currentClassDoc.addProperty(this.currentFieldDoc);
                }
                else {
                    currentClassDoc.add(this.currentFieldDoc);
                }
            }
        }
    }
    
    @Override
    public void visitAssign(final GroovySourceAST t, final int visit) {
        this.gobbleComments(t, visit);
    }
    
    @Override
    public void visitMethodCall(final GroovySourceAST t, final int visit) {
        this.gobbleComments(t, visit);
    }
    
    private void gobbleComments(final GroovySourceAST t, final int visit) {
        if (visit == 1) {
            final SimpleGroovyClassDoc currentClassDoc = this.getCurrentClassDoc();
            if ((currentClassDoc == null || currentClassDoc.isScript()) && (t.getLine() > this.lastLineCol.getLine() || (t.getLine() == this.lastLineCol.getLine() && t.getColumn() > this.lastLineCol.getColumn()))) {
                this.getJavaDocCommentsBeforeNode(t);
                this.lastLineCol = new LineColumn(t.getLine(), t.getColumn());
            }
        }
    }
    
    private void postProcessClassDocs() {
        for (final GroovyClassDoc groovyClassDoc : this.classDocs.values()) {
            final SimpleGroovyClassDoc classDoc = (SimpleGroovyClassDoc)groovyClassDoc;
            if (classDoc.isClass()) {
                final GroovyConstructorDoc[] constructors = classDoc.constructors();
                if (constructors == null || constructors.length != 0) {
                    continue;
                }
                final GroovyConstructorDoc constructorDoc = new SimpleGroovyConstructorDoc(classDoc.name(), classDoc);
                classDoc.add(constructorDoc);
            }
        }
    }
    
    private boolean isNested() {
        return this.getCurrentClassDoc() != null;
    }
    
    private boolean isTopLevelConstruct(final GroovySourceAST node) {
        if (node == null) {
            return false;
        }
        final int type = node.getType();
        return type == 13 || type == 14 || type == 63 || type == 60;
    }
    
    private void adjustForAutomaticEnumMethods(final SimpleGroovyClassDoc currentClassDoc) {
        final SimpleGroovyMethodDoc valueOf = new SimpleGroovyMethodDoc("valueOf", currentClassDoc);
        valueOf.setRawCommentText("Returns the enum constant of this type with the specified name.");
        final SimpleGroovyParameter parameter = new SimpleGroovyParameter("name");
        parameter.setTypeName("String");
        valueOf.add(parameter);
        valueOf.setReturnType(new SimpleGroovyType(currentClassDoc.name()));
        currentClassDoc.add(valueOf);
        final SimpleGroovyMethodDoc values = new SimpleGroovyMethodDoc("values", currentClassDoc);
        values.setRawCommentText("Returns an array containing the constants of this enum type, in the order they are declared.");
        values.setReturnType(new SimpleGroovyType(currentClassDoc.name() + "[]"));
        currentClassDoc.add(values);
    }
    
    private String extractImportPath(final GroovySourceAST t) {
        GroovySourceAST child = t.childOfType(87);
        if (child == null) {
            child = t.childOfType(84);
        }
        return this.recurseDownImportBranch(child);
    }
    
    private String recurseDownImportBranch(final GroovySourceAST t) {
        if (t != null) {
            if (t.getType() == 87) {
                final GroovySourceAST firstChild = (GroovySourceAST)t.getFirstChild();
                final GroovySourceAST secondChild = (GroovySourceAST)firstChild.getNextSibling();
                return this.recurseDownImportBranch(firstChild) + "/" + this.recurseDownImportBranch(secondChild);
            }
            if (t.getType() == 84) {
                return t.getText();
            }
            if (t.getType() == 109) {
                return t.getText();
            }
        }
        return "";
    }
    
    private void addAnnotationRef(final SimpleGroovyProgramElementDoc node, final GroovySourceAST t) {
        final GroovySourceAST classNode = t.childOfType(84);
        if (classNode != null) {
            node.addAnnotationRef(new SimpleGroovyAnnotationRef(this.extractName(classNode), this.getChildTextFromSource(t).trim()));
        }
    }
    
    private void addAnnotationRef(final SimpleGroovyParameter node, final GroovySourceAST t) {
        final GroovySourceAST classNode = t.childOfType(84);
        if (classNode != null) {
            node.addAnnotationRef(new SimpleGroovyAnnotationRef(this.extractName(classNode), this.getChildTextFromSource(t).trim()));
        }
    }
    
    private void addAnnotationRefs(final SimpleGroovyProgramElementDoc node, final List<GroovySourceAST> nodes) {
        for (final GroovySourceAST t : nodes) {
            this.addAnnotationRef(node, t);
        }
    }
    
    private void processAnnotations(final GroovySourceAST t, final SimpleGroovyProgramElementDoc node) {
        final GroovySourceAST modifiers = t.childOfType(5);
        if (modifiers != null) {
            this.addAnnotationRefs(node, modifiers.childrenOfType(65));
        }
    }
    
    private String getDefaultValue(final GroovySourceAST t) {
        GroovySourceAST child = (GroovySourceAST)t.getFirstChild();
        if (t.getNumberOfChildren() != 4) {
            return null;
        }
        for (int i = 1; i < t.getNumberOfChildren(); ++i) {
            child = (GroovySourceAST)child.getNextSibling();
        }
        GroovySourceAST nodeToProcess = child;
        if (child.getNumberOfChildren() > 0) {
            nodeToProcess = (GroovySourceAST)child.getFirstChild();
        }
        return this.getChildTextFromSource(nodeToProcess, ";");
    }
    
    private String getChildTextFromSource(final GroovySourceAST child) {
        return this.sourceBuffer.getSnippet(new LineColumn(child.getLine(), child.getColumn()), new LineColumn(child.getLineLast(), child.getColumnLast()));
    }
    
    private String getChildTextFromSource(final GroovySourceAST child, final String tokens) {
        final String text = this.sourceBuffer.getSnippet(new LineColumn(child.getLine(), child.getColumn()), new LineColumn(child.getLine() + 1, 0));
        final StringTokenizer st = new StringTokenizer(text, tokens);
        return st.nextToken();
    }
    
    private boolean isFieldDefinition() {
        final GroovySourceAST parentNode = this.getParentNode();
        return parentNode != null && parentNode.getType() == 6;
    }
    
    private boolean insideAnonymousInnerClass() {
        final GroovySourceAST grandParentNode = this.getGrandParentNode();
        return grandParentNode != null && grandParentNode.getType() == 154;
    }
    
    private boolean processModifiers(final GroovySourceAST t, final SimpleGroovyAbstractableElementDoc memberOrClass) {
        final GroovySourceAST modifiers = t.childOfType(5);
        boolean hasNonPublicVisibility = false;
        boolean hasPublicVisibility = false;
        if (modifiers != null) {
            for (AST currentModifier = modifiers.getFirstChild(); currentModifier != null; currentModifier = currentModifier.getNextSibling()) {
                final int type = currentModifier.getType();
                switch (type) {
                    case 112: {
                        memberOrClass.setPublic(true);
                        hasPublicVisibility = true;
                        break;
                    }
                    case 113: {
                        memberOrClass.setProtected(true);
                        hasNonPublicVisibility = true;
                        break;
                    }
                    case 111: {
                        memberOrClass.setPrivate(true);
                        hasNonPublicVisibility = true;
                        break;
                    }
                    case 80: {
                        memberOrClass.setStatic(true);
                        break;
                    }
                    case 37: {
                        memberOrClass.setFinal(true);
                        break;
                    }
                    case 38: {
                        memberOrClass.setAbstract(true);
                        break;
                    }
                }
            }
            if (!hasNonPublicVisibility && this.isGroovy && !(memberOrClass instanceof GroovyFieldDoc)) {
                memberOrClass.setPublic(true);
            }
            else if (!hasNonPublicVisibility && !hasPublicVisibility && !this.isGroovy) {
                if (this.insideInterface(memberOrClass)) {
                    memberOrClass.setPublic(true);
                }
                else {
                    memberOrClass.setPackagePrivate(true);
                }
            }
            if (memberOrClass instanceof GroovyFieldDoc && !hasNonPublicVisibility && !hasPublicVisibility && this.isGroovy) {
                return true;
            }
        }
        else if (this.isGroovy && !(memberOrClass instanceof GroovyFieldDoc)) {
            memberOrClass.setPublic(true);
        }
        else if (!this.isGroovy) {
            if (this.insideInterface(memberOrClass)) {
                memberOrClass.setPublic(true);
            }
            else {
                memberOrClass.setPackagePrivate(true);
            }
        }
        return memberOrClass instanceof GroovyFieldDoc && this.isGroovy && (!hasNonPublicVisibility & !hasPublicVisibility);
    }
    
    private boolean insideInterface(final SimpleGroovyAbstractableElementDoc memberOrClass) {
        final SimpleGroovyClassDoc current = this.getCurrentClassDoc();
        return current != null && current != memberOrClass && current.isInterface();
    }
    
    private String getJavaDocCommentsBeforeNode(final GroovySourceAST t) {
        String result = "";
        final LineColumn thisLineCol = new LineColumn(t.getLine(), t.getColumn());
        final String text = this.sourceBuffer.getSnippet(this.lastLineCol, thisLineCol);
        if (text != null) {
            final Matcher m = SimpleGroovyClassDocAssembler.PREV_JAVADOC_COMMENT_PATTERN.matcher(text);
            if (m.find()) {
                result = m.group(1);
            }
        }
        if (this.isMajorType(t)) {
            this.lastLineCol = thisLineCol;
        }
        return result;
    }
    
    private boolean isMajorType(final GroovySourceAST t) {
        if (t == null) {
            return false;
        }
        final int tt = t.getType();
        return tt == 13 || tt == 14 || tt == 8 || tt == 63 || tt == 60 || tt == 9 || tt == 67 || tt == 61 || tt == 45;
    }
    
    private String getText(final GroovySourceAST node) {
        String returnValue = null;
        if (node != null) {
            returnValue = node.getText();
        }
        return returnValue;
    }
    
    private String extractName(final GroovySourceAST typeNode) {
        String typeName = this.buildName(typeNode);
        if (typeName.indexOf("/") == -1) {
            final String slashName = "/" + typeName;
            for (final String name : this.importedClassesAndPackages) {
                if (name.endsWith(slashName)) {
                    typeName = name;
                }
            }
        }
        return typeName;
    }
    
    private String buildName(final GroovySourceAST t) {
        if (t != null) {
            if (t.getType() == 87) {
                final GroovySourceAST firstChild = (GroovySourceAST)t.getFirstChild();
                final GroovySourceAST secondChild = (GroovySourceAST)firstChild.getNextSibling();
                return this.buildName(firstChild) + "/" + this.buildName(secondChild);
            }
            if (t.getType() == 84) {
                return t.getText();
            }
        }
        return "";
    }
    
    private String getTypeOrDefault(final GroovySourceAST t) {
        final GroovySourceAST typeNode = t.childOfType(12);
        return this.getTypeNodeAsText(typeNode, "def");
    }
    
    private String getTypeNodeAsText(final GroovySourceAST typeNode, final String defaultText) {
        if (typeNode != null && typeNode.getType() == 12 && typeNode.getNumberOfChildren() > 0) {
            return this.getAsText(typeNode, defaultText);
        }
        return defaultText;
    }
    
    private String getAsText(final GroovySourceAST typeNode, final String defaultText) {
        String returnValue = defaultText;
        final GroovySourceAST child = (GroovySourceAST)typeNode.getFirstChild();
        switch (child.getType()) {
            case 101: {
                returnValue = "boolean";
                break;
            }
            case 102: {
                returnValue = "byte";
                break;
            }
            case 103: {
                returnValue = "char";
                break;
            }
            case 108: {
                returnValue = "double";
                break;
            }
            case 106: {
                returnValue = "float";
                break;
            }
            case 105: {
                returnValue = "int";
                break;
            }
            case 107: {
                returnValue = "long";
                break;
            }
            case 104: {
                returnValue = "short";
                break;
            }
            case 100: {
                returnValue = "void";
                break;
            }
            case 16: {
                final String componentType = this.getAsText(child, defaultText);
                if (!componentType.equals("def")) {
                    returnValue = componentType + "[]";
                    break;
                }
                break;
            }
            case 84: {
                returnValue = child.getText();
                break;
            }
        }
        return returnValue;
    }
    
    private void addParametersTo(final GroovySourceAST t, final SimpleGroovyExecutableMemberDoc executableMemberDoc) {
        final GroovySourceAST parametersNode = t.childOfType(19);
        if (parametersNode != null && parametersNode.getNumberOfChildren() > 0) {
            for (GroovySourceAST currentNode = (GroovySourceAST)parametersNode.getFirstChild(); currentNode != null; currentNode = (GroovySourceAST)currentNode.getNextSibling()) {
                final String parameterTypeName = this.getTypeOrDefault(currentNode);
                final String parameterName = this.getText(currentNode.childOfType(84));
                final SimpleGroovyParameter parameter = new SimpleGroovyParameter(parameterName);
                parameter.setTypeName(parameterTypeName);
                final GroovySourceAST modifiers = currentNode.childOfType(5);
                if (modifiers != null) {
                    final List<GroovySourceAST> annotations = modifiers.childrenOfType(65);
                    for (final GroovySourceAST a : annotations) {
                        this.addAnnotationRef(parameter, a);
                    }
                }
                executableMemberDoc.add(parameter);
                if (currentNode.getNumberOfChildren() == 4) {
                    this.handleDefaultValue(currentNode, parameter);
                }
            }
        }
    }
    
    private void handleDefaultValue(final GroovySourceAST currentNode, final SimpleGroovyParameter parameter) {
        GroovySourceAST paramPart = (GroovySourceAST)currentNode.getFirstChild();
        for (int i = 1; i < currentNode.getNumberOfChildren(); ++i) {
            paramPart = (GroovySourceAST)paramPart.getNextSibling();
        }
        GroovySourceAST nodeToProcess = paramPart;
        if (paramPart.getNumberOfChildren() > 0) {
            nodeToProcess = (GroovySourceAST)paramPart.getFirstChild();
        }
        parameter.setDefaultValue(this.getChildTextFromSource(nodeToProcess, ",)"));
    }
    
    @Override
    public void push(final GroovySourceAST t) {
        this.stack.push(t);
    }
    
    @Override
    public GroovySourceAST pop() {
        if (!this.stack.empty()) {
            return this.stack.pop();
        }
        return null;
    }
    
    private GroovySourceAST getParentNode() {
        GroovySourceAST parentNode = null;
        final GroovySourceAST currentNode = this.stack.pop();
        if (!this.stack.empty()) {
            parentNode = this.stack.peek();
        }
        this.stack.push(currentNode);
        return parentNode;
    }
    
    private GroovySourceAST getGrandParentNode() {
        GroovySourceAST grandParentNode = null;
        final GroovySourceAST currentNode = this.stack.pop();
        if (!this.stack.empty()) {
            final GroovySourceAST parentNode = this.stack.pop();
            if (!this.stack.empty()) {
                grandParentNode = this.stack.peek();
            }
            this.stack.push(parentNode);
        }
        this.stack.push(currentNode);
        return grandParentNode;
    }
    
    private SimpleGroovyClassDoc getCurrentOrTopLevelClassDoc(final GroovySourceAST node) {
        final SimpleGroovyClassDoc current = this.getCurrentClassDoc();
        if (current != null) {
            return current;
        }
        return this.foundClasses.get(this.getIdentFor(node));
    }
    
    private SimpleGroovyClassDoc getCurrentClassDoc() {
        if (this.stack.isEmpty()) {
            return null;
        }
        final GroovySourceAST node = this.getParentNode();
        if (this.isTopLevelConstruct(node)) {
            return this.foundClasses.get(this.getIdentFor(node));
        }
        final GroovySourceAST saved = this.stack.pop();
        final SimpleGroovyClassDoc result = this.getCurrentClassDoc();
        this.stack.push(saved);
        return result;
    }
    
    private String getIdentFor(final GroovySourceAST gpn) {
        return gpn.childOfType(84).getText();
    }
    
    static {
        PREV_JAVADOC_COMMENT_PATTERN = Pattern.compile("(?s)/\\*\\*(.*?)\\*/");
    }
}
