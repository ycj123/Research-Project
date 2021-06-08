// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Arrays;
import groovy.lang.GrabResolver;
import groovy.lang.Grapes;
import groovy.lang.GrabConfig;
import groovy.lang.GrabExclude;
import groovy.lang.Grab;
import org.codehaus.groovy.ast.AnnotatedNode;
import java.util.regex.Matcher;
import org.codehaus.groovy.ast.expr.MethodCallExpression;
import org.codehaus.groovy.ast.expr.VariableExpression;
import org.codehaus.groovy.ast.stmt.ExpressionStatement;
import org.codehaus.groovy.ast.expr.StaticMethodCallExpression;
import org.codehaus.groovy.ast.expr.ArgumentListExpression;
import org.codehaus.groovy.ast.expr.MapExpression;
import org.codehaus.groovy.ast.stmt.Statement;
import org.codehaus.groovy.ast.expr.Expression;
import java.util.Iterator;
import org.codehaus.groovy.transform.ASTTransformationVisitor;
import org.codehaus.groovy.ast.expr.ListExpression;
import org.codehaus.groovy.ast.expr.ConstantExpression;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import org.codehaus.groovy.ast.ImportNode;
import java.util.HashSet;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.ast.AnnotationNode;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.Collection;
import java.util.List;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

@GroovyASTTransformation(phase = CompilePhase.CONVERSION)
public class GrabAnnotationTransformation extends ClassCodeVisitorSupport implements ASTTransformation
{
    private static final String GRAB_CLASS_NAME;
    private static final String GRAB_DOT_NAME;
    private static final String GRAB_SHORT_NAME;
    private static final String GRABEXCLUDE_CLASS_NAME;
    private static final String GRABEXCLUDE_DOT_NAME;
    private static final String GRABEXCLUDE_SHORT_NAME;
    private static final String GRABCONFIG_CLASS_NAME;
    private static final String GRABCONFIG_DOT_NAME;
    private static final String GRABCONFIG_SHORT_NAME;
    private static final String GRAPES_CLASS_NAME;
    private static final String GRAPES_DOT_NAME;
    private static final String GRAPES_SHORT_NAME;
    private static final String GRABRESOLVER_CLASS_NAME;
    private static final String GRAPERESOLVER_DOT_NAME;
    private static final String GRABRESOLVER_SHORT_NAME;
    private static final ClassNode THREAD_CLASSNODE;
    private static final List<String> GRABEXCLUDE_REQUIRED;
    private static final List<String> GRAPERESOLVER_REQUIRED;
    private static final List<String> GRAB_REQUIRED;
    private static final List<String> GRAB_OPTIONAL;
    private static final Collection<String> GRAB_ALL;
    private static final Pattern IVY_PATTERN;
    boolean allowShortGrab;
    Set<String> grabAliases;
    List<AnnotationNode> grabAnnotations;
    boolean allowShortGrabExcludes;
    Set<String> grabExcludeAliases;
    List<AnnotationNode> grabExcludeAnnotations;
    boolean allowShortGrabConfig;
    Set<String> grabConfigAliases;
    List<AnnotationNode> grabConfigAnnotations;
    boolean allowShortGrapes;
    Set<String> grapesAliases;
    List<AnnotationNode> grapesAnnotations;
    boolean allowShortGrabResolver;
    Set<String> grabResolverAliases;
    List<AnnotationNode> grabResolverAnnotations;
    SourceUnit sourceUnit;
    ClassLoader loader;
    boolean initContextClassLoader;
    
    private static String dotName(final String className) {
        return className.substring(className.lastIndexOf("."));
    }
    
    private static String shortName(final String className) {
        return className.substring(1);
    }
    
    public SourceUnit getSourceUnit() {
        return this.sourceUnit;
    }
    
    public void visit(final ASTNode[] nodes, final SourceUnit source) {
        this.sourceUnit = source;
        this.loader = null;
        this.initContextClassLoader = false;
        final ModuleNode mn = (ModuleNode)nodes[0];
        this.allowShortGrab = true;
        this.allowShortGrabExcludes = true;
        this.allowShortGrabConfig = true;
        this.allowShortGrapes = true;
        this.allowShortGrabResolver = true;
        this.grabAliases = new HashSet<String>();
        this.grabExcludeAliases = new HashSet<String>();
        this.grabConfigAliases = new HashSet<String>();
        this.grapesAliases = new HashSet<String>();
        this.grabResolverAliases = new HashSet<String>();
        for (final ImportNode im : mn.getImports()) {
            final String alias = im.getAlias();
            final String className = im.getClassName();
            if ((className.endsWith(GrabAnnotationTransformation.GRAB_DOT_NAME) && (alias == null || alias.length() == 0)) || GrabAnnotationTransformation.GRAB_CLASS_NAME.equals(alias)) {
                this.allowShortGrab = false;
            }
            else if (GrabAnnotationTransformation.GRAB_CLASS_NAME.equals(className)) {
                this.grabAliases.add(im.getAlias());
            }
            if ((className.endsWith(GrabAnnotationTransformation.GRAPES_DOT_NAME) && (alias == null || alias.length() == 0)) || GrabAnnotationTransformation.GRAPES_CLASS_NAME.equals(alias)) {
                this.allowShortGrapes = false;
            }
            else if (GrabAnnotationTransformation.GRAPES_CLASS_NAME.equals(className)) {
                this.grapesAliases.add(im.getAlias());
            }
            if ((className.endsWith(GrabAnnotationTransformation.GRAPERESOLVER_DOT_NAME) && (alias == null || alias.length() == 0)) || GrabAnnotationTransformation.GRABRESOLVER_CLASS_NAME.equals(alias)) {
                this.allowShortGrabResolver = false;
            }
            else {
                if (!GrabAnnotationTransformation.GRABRESOLVER_CLASS_NAME.equals(className)) {
                    continue;
                }
                this.grabResolverAliases.add(im.getAlias());
            }
        }
        final List<Map<String, Object>> grabMaps = new ArrayList<Map<String, Object>>();
        final List<Map<String, Object>> grabExcludeMaps = new ArrayList<Map<String, Object>>();
        for (final ClassNode classNode : this.sourceUnit.getAST().getClasses()) {
            this.grabAnnotations = new ArrayList<AnnotationNode>();
            this.grabExcludeAnnotations = new ArrayList<AnnotationNode>();
            this.grabConfigAnnotations = new ArrayList<AnnotationNode>();
            this.grapesAnnotations = new ArrayList<AnnotationNode>();
            this.grabResolverAnnotations = new ArrayList<AnnotationNode>();
            this.visitClass(classNode);
            final ClassNode grapeClassNode = new ClassNode(Grape.class);
            if (!this.grabResolverAnnotations.isEmpty()) {
            Label_0511:
                for (final AnnotationNode node : this.grabResolverAnnotations) {
                    final Map<String, Object> grapeResolverMap = new HashMap<String, Object>();
                    final Expression value = node.getMember("value");
                    ConstantExpression ce = null;
                    if (value != null && value instanceof ConstantExpression) {
                        ce = (ConstantExpression)value;
                    }
                    String sval = null;
                    if (ce != null && ce.getValue() instanceof String) {
                        sval = (String)ce.getValue();
                    }
                    if (sval != null && sval.length() > 0) {
                        for (final String s : GrabAnnotationTransformation.GRAPERESOLVER_REQUIRED) {
                            final Expression member = node.getMember(s);
                            if (member != null) {
                                this.addError("The attribute \"" + s + "\" conflicts with attribute 'value' in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                                continue Label_0511;
                            }
                        }
                        grapeResolverMap.put("name", sval);
                        grapeResolverMap.put("root", sval);
                    }
                    else {
                        for (final String s : GrabAnnotationTransformation.GRAPERESOLVER_REQUIRED) {
                            final Expression member = node.getMember(s);
                            if (member == null) {
                                this.addError("The missing attribute \"" + s + "\" is required in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                                continue Label_0511;
                            }
                            if (member != null && !(member instanceof ConstantExpression)) {
                                this.addError("Attribute \"" + s + "\" has value " + member.getText() + " but should be an inline constant in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                                continue Label_0511;
                            }
                            grapeResolverMap.put(s, ((ConstantExpression)member).getValue());
                        }
                    }
                    Grape.addResolver(grapeResolverMap);
                }
            }
            if (!this.grapesAnnotations.isEmpty()) {
                for (final AnnotationNode node : this.grapesAnnotations) {
                    final Expression init = node.getMember("initClass");
                    final Expression value = node.getMember("value");
                    if (value instanceof ListExpression) {
                        for (final Object o : ((ListExpression)value).getExpressions()) {
                            if (o instanceof ConstantExpression) {
                                this.extractGrab(init, (ConstantExpression)o);
                            }
                        }
                    }
                    else {
                        if (!(value instanceof ConstantExpression)) {
                            continue;
                        }
                        this.extractGrab(init, (ConstantExpression)value);
                    }
                }
            }
            if (!this.grabConfigAnnotations.isEmpty()) {
                for (final AnnotationNode node : this.grabConfigAnnotations) {
                    this.checkForClassLoader(node);
                    this.checkForInitContextClassLoader(node);
                }
                this.addInitContextClassLoaderIfNeeded(classNode);
            }
            if (!this.grabExcludeAnnotations.isEmpty()) {
            Label_1195:
                for (final AnnotationNode node : this.grabExcludeAnnotations) {
                    final Map<String, Object> grabExcludeMap = new HashMap<String, Object>();
                    this.checkForConvenienceForm(node, true);
                    for (final String s2 : GrabAnnotationTransformation.GRABEXCLUDE_REQUIRED) {
                        final Expression member2 = node.getMember(s2);
                        if (member2 == null) {
                            this.addError("The missing attribute \"" + s2 + "\" is required in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                            continue Label_1195;
                        }
                        if (member2 != null && !(member2 instanceof ConstantExpression)) {
                            this.addError("Attribute \"" + s2 + "\" has value " + member2.getText() + " but should be an inline constant in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                            continue Label_1195;
                        }
                        grabExcludeMap.put(s2, ((ConstantExpression)member2).getValue());
                    }
                    grabExcludeMaps.add(grabExcludeMap);
                }
            }
            if (!this.grabAnnotations.isEmpty()) {
            Label_1469:
                for (final AnnotationNode node : this.grabAnnotations) {
                    final Map<String, Object> grabMap = new HashMap<String, Object>();
                    this.checkForConvenienceForm(node, false);
                    for (final String s2 : GrabAnnotationTransformation.GRAB_ALL) {
                        final Expression member2 = node.getMember(s2);
                        if (member2 == null && !GrabAnnotationTransformation.GRAB_OPTIONAL.contains(s2)) {
                            this.addError("The missing attribute \"" + s2 + "\" is required in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                            continue Label_1469;
                        }
                        if (member2 != null && !(member2 instanceof ConstantExpression)) {
                            this.addError("Attribute \"" + s2 + "\" has value " + member2.getText() + " but should be an inline constant in @" + node.getClassNode().getNameWithoutPackage() + " annotations", node);
                            continue Label_1469;
                        }
                        if (node.getMember(s2) == null) {
                            continue;
                        }
                        grabMap.put(s2, ((ConstantExpression)member2).getValue());
                    }
                    grabMaps.add(grabMap);
                    this.callGrabAsStaticInitIfNeeded(classNode, grapeClassNode, node, grabExcludeMaps);
                }
            }
        }
        if (!grabMaps.isEmpty()) {
            final Map<String, Object> basicArgs = new HashMap<String, Object>();
            basicArgs.put("classLoader", (this.loader != null) ? this.loader : this.sourceUnit.getClassLoader());
            if (!grabExcludeMaps.isEmpty()) {
                basicArgs.put("excludes", grabExcludeMaps);
            }
            try {
                Grape.grab(basicArgs, (Map[])grabMaps.toArray(new Map[grabMaps.size()]));
                ASTTransformationVisitor.addGlobalTransformsAfterGrab();
            }
            catch (RuntimeException re) {
                source.addException(re);
            }
        }
    }
    
    private void callGrabAsStaticInitIfNeeded(final ClassNode classNode, final ClassNode grapeClassNode, final AnnotationNode node, final List<Map<String, Object>> grabExcludeMaps) {
        if (node.getMember("initClass") == null || node.getMember("initClass") == ConstantExpression.TRUE) {
            final List<Statement> grabInitializers = new ArrayList<Statement>();
            final MapExpression me = new MapExpression();
            for (final String s : GrabAnnotationTransformation.GRAB_REQUIRED) {
                me.addMapEntryExpression(new ConstantExpression(s), node.getMember(s));
            }
            for (final String s : GrabAnnotationTransformation.GRAB_OPTIONAL) {
                if (node.getMember(s) != null) {
                    me.addMapEntryExpression(new ConstantExpression(s), node.getMember(s));
                }
            }
            ArgumentListExpression grabArgs;
            if (grabExcludeMaps.isEmpty()) {
                grabArgs = new ArgumentListExpression(me);
            }
            else {
                final MapExpression args = new MapExpression();
                final ListExpression list = new ListExpression();
                for (final Map<String, Object> map : grabExcludeMaps) {
                    final Set<Map.Entry<String, Object>> entries = map.entrySet();
                    final MapExpression inner = new MapExpression();
                    for (final Map.Entry<String, Object> entry : entries) {
                        inner.addMapEntryExpression(new ConstantExpression(entry.getKey()), new ConstantExpression(entry.getValue()));
                    }
                    list.addExpression(inner);
                }
                args.addMapEntryExpression(new ConstantExpression("excludes"), list);
                grabArgs = new ArgumentListExpression(args, me);
            }
            grabInitializers.add(new ExpressionStatement(new StaticMethodCallExpression(grapeClassNode, "grab", grabArgs)));
            classNode.addStaticInitializerStatements(grabInitializers, true);
        }
    }
    
    private void addInitContextClassLoaderIfNeeded(final ClassNode classNode) {
        if (this.initContextClassLoader) {
            final Statement initStatement = new ExpressionStatement(new MethodCallExpression(new StaticMethodCallExpression(GrabAnnotationTransformation.THREAD_CLASSNODE, "currentThread", ArgumentListExpression.EMPTY_ARGUMENTS), "setContextClassLoader", new MethodCallExpression(new MethodCallExpression(VariableExpression.THIS_EXPRESSION, "getClass", MethodCallExpression.NO_ARGUMENTS), "getClassLoader", ArgumentListExpression.EMPTY_ARGUMENTS)));
            classNode.addObjectInitializerStatements(initStatement);
        }
    }
    
    private void checkForClassLoader(final AnnotationNode node) {
        final Object val = node.getMember("systemClassLoader");
        if (val == null || !(val instanceof ConstantExpression)) {
            return;
        }
        final Object systemClassLoaderObject = ((ConstantExpression)val).getValue();
        if (!(systemClassLoaderObject instanceof Boolean)) {
            return;
        }
        final Boolean systemClassLoader = (Boolean)systemClassLoaderObject;
        if (systemClassLoader) {
            this.loader = ClassLoader.getSystemClassLoader();
        }
    }
    
    private void checkForInitContextClassLoader(final AnnotationNode node) {
        final Object val = node.getMember("initContextClassLoader");
        if (val == null || !(val instanceof ConstantExpression)) {
            return;
        }
        final Object initContextClassLoaderObject = ((ConstantExpression)val).getValue();
        if (!(initContextClassLoaderObject instanceof Boolean)) {
            return;
        }
        this.initContextClassLoader = (boolean)initContextClassLoaderObject;
    }
    
    private void checkForConvenienceForm(final AnnotationNode node, final boolean exclude) {
        final Object val = node.getMember("value");
        if (val == null || !(val instanceof ConstantExpression)) {
            return;
        }
        final Object allParts = ((ConstantExpression)val).getValue();
        if (!(allParts instanceof String)) {
            return;
        }
        String allstr = (String)allParts;
        if (allstr.contains("#")) {
            final Matcher m = GrabAnnotationTransformation.IVY_PATTERN.matcher(allstr);
            if (!m.find()) {
                return;
            }
            if (m.group(1) == null || m.group(2) == null) {
                return;
            }
            node.addMember("module", new ConstantExpression(m.group(2)));
            node.addMember("group", new ConstantExpression(m.group(1)));
            if (m.group(6) != null) {
                node.addMember("conf", new ConstantExpression(m.group(6)));
            }
            if (m.group(4) != null) {
                node.addMember("version", new ConstantExpression(m.group(4)));
            }
            else if (!exclude) {
                node.addMember("version", new ConstantExpression("*"));
            }
            node.getMembers().remove("value");
        }
        else if (allstr.contains(":")) {
            String ext = "";
            if (allstr.contains("@")) {
                final String[] parts = allstr.split("@");
                if (parts.length > 2) {
                    return;
                }
                allstr = parts[0];
                ext = parts[1];
            }
            final String[] parts = allstr.split(":");
            if (parts.length > 4) {
                return;
            }
            if (parts.length > 3) {
                node.addMember("classifier", new ConstantExpression(parts[3]));
            }
            if (parts.length > 2) {
                node.addMember("version", new ConstantExpression(parts[2]));
            }
            else if (!exclude) {
                node.addMember("version", new ConstantExpression("*"));
            }
            if (ext.length() > 0) {
                node.addMember("ext", new ConstantExpression(ext));
            }
            node.addMember("module", new ConstantExpression(parts[1]));
            node.addMember("group", new ConstantExpression(parts[0]));
            node.getMembers().remove("value");
        }
    }
    
    private void extractGrab(final Expression init, final ConstantExpression ce) {
        if (ce.getValue() instanceof AnnotationNode) {
            final AnnotationNode annotation = (AnnotationNode)ce.getValue();
            if (init != null && annotation.getMember("initClass") != null) {
                annotation.setMember("initClass", init);
            }
            final String name = annotation.getClassNode().getName();
            if (GrabAnnotationTransformation.GRAB_CLASS_NAME.equals(name) || (this.allowShortGrab && GrabAnnotationTransformation.GRAB_SHORT_NAME.equals(name)) || this.grabAliases.contains(name)) {
                this.grabAnnotations.add(annotation);
            }
            if (GrabAnnotationTransformation.GRABEXCLUDE_CLASS_NAME.equals(name) || (this.allowShortGrabExcludes && GrabAnnotationTransformation.GRABEXCLUDE_SHORT_NAME.equals(name)) || this.grabExcludeAliases.contains(name)) {
                this.grabExcludeAnnotations.add(annotation);
            }
            if (GrabAnnotationTransformation.GRABCONFIG_CLASS_NAME.equals(name) || (this.allowShortGrabConfig && GrabAnnotationTransformation.GRABCONFIG_SHORT_NAME.equals(name)) || this.grabConfigAliases.contains(name)) {
                this.grabConfigAnnotations.add(annotation);
            }
            if (GrabAnnotationTransformation.GRABRESOLVER_CLASS_NAME.equals(name) || (this.allowShortGrabResolver && GrabAnnotationTransformation.GRABRESOLVER_SHORT_NAME.equals(name)) || this.grabResolverAliases.contains(name)) {
                this.grabResolverAnnotations.add(annotation);
            }
        }
    }
    
    @Override
    public void visitAnnotations(final AnnotatedNode node) {
        super.visitAnnotations(node);
        for (final AnnotationNode an : node.getAnnotations()) {
            final String name = an.getClassNode().getName();
            if (GrabAnnotationTransformation.GRAB_CLASS_NAME.equals(name) || (this.allowShortGrab && GrabAnnotationTransformation.GRAB_SHORT_NAME.equals(name)) || this.grabAliases.contains(name)) {
                this.grabAnnotations.add(an);
            }
            if (GrabAnnotationTransformation.GRABEXCLUDE_CLASS_NAME.equals(name) || (this.allowShortGrabExcludes && GrabAnnotationTransformation.GRABEXCLUDE_SHORT_NAME.equals(name)) || this.grabExcludeAliases.contains(name)) {
                this.grabExcludeAnnotations.add(an);
            }
            if (GrabAnnotationTransformation.GRABCONFIG_CLASS_NAME.equals(name) || (this.allowShortGrabConfig && GrabAnnotationTransformation.GRABCONFIG_SHORT_NAME.equals(name)) || this.grabConfigAliases.contains(name)) {
                this.grabConfigAnnotations.add(an);
            }
            if (GrabAnnotationTransformation.GRAPES_CLASS_NAME.equals(name) || (this.allowShortGrapes && GrabAnnotationTransformation.GRAPES_SHORT_NAME.equals(name)) || this.grapesAliases.contains(name)) {
                this.grapesAnnotations.add(an);
            }
            if (GrabAnnotationTransformation.GRABRESOLVER_CLASS_NAME.equals(name) || (this.allowShortGrabResolver && GrabAnnotationTransformation.GRABRESOLVER_SHORT_NAME.equals(name)) || this.grabResolverAliases.contains(name)) {
                this.grabResolverAnnotations.add(an);
            }
        }
    }
    
    static {
        GRAB_CLASS_NAME = Grab.class.getName();
        GRAB_DOT_NAME = GrabAnnotationTransformation.GRAB_CLASS_NAME.substring(GrabAnnotationTransformation.GRAB_CLASS_NAME.lastIndexOf("."));
        GRAB_SHORT_NAME = GrabAnnotationTransformation.GRAB_DOT_NAME.substring(1);
        GRABEXCLUDE_CLASS_NAME = GrabExclude.class.getName();
        GRABEXCLUDE_DOT_NAME = dotName(GrabAnnotationTransformation.GRABEXCLUDE_CLASS_NAME);
        GRABEXCLUDE_SHORT_NAME = shortName(GrabAnnotationTransformation.GRABEXCLUDE_DOT_NAME);
        GRABCONFIG_CLASS_NAME = GrabConfig.class.getName();
        GRABCONFIG_DOT_NAME = dotName(GrabAnnotationTransformation.GRABCONFIG_CLASS_NAME);
        GRABCONFIG_SHORT_NAME = shortName(GrabAnnotationTransformation.GRABCONFIG_DOT_NAME);
        GRAPES_CLASS_NAME = Grapes.class.getName();
        GRAPES_DOT_NAME = dotName(GrabAnnotationTransformation.GRAPES_CLASS_NAME);
        GRAPES_SHORT_NAME = shortName(GrabAnnotationTransformation.GRAPES_DOT_NAME);
        GRABRESOLVER_CLASS_NAME = GrabResolver.class.getName();
        GRAPERESOLVER_DOT_NAME = dotName(GrabAnnotationTransformation.GRABRESOLVER_CLASS_NAME);
        GRABRESOLVER_SHORT_NAME = shortName(GrabAnnotationTransformation.GRAPERESOLVER_DOT_NAME);
        THREAD_CLASSNODE = new ClassNode(Thread.class);
        GRABEXCLUDE_REQUIRED = Arrays.asList("group", "module");
        GRAPERESOLVER_REQUIRED = Arrays.asList("name", "root");
        GRAB_REQUIRED = Arrays.asList("group", "module", "version");
        GRAB_OPTIONAL = Arrays.asList("classifier", "transitive", "conf", "ext");
        GRAB_ALL = DefaultGroovyMethods.plus(GrabAnnotationTransformation.GRAB_REQUIRED, GrabAnnotationTransformation.GRAB_OPTIONAL);
        IVY_PATTERN = Pattern.compile("([a-zA-Z0-9-/._+=]+)#([a-zA-Z0-9-/._+=]+)(;([a-zA-Z0-9-/.\\(\\)\\[\\]\\{\\}_+=,:@][a-zA-Z0-9-/.\\(\\)\\]\\{\\}_+=,:@]*))?(\\[([a-zA-Z0-9-/._+=,]*)\\])?");
    }
}
