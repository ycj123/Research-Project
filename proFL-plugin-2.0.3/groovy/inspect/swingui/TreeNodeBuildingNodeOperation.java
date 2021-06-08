// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import javax.swing.tree.DefaultMutableTreeNode;
import groovy.lang.Reference;
import java.util.List;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.classgen.GeneratorContext;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.control.CompilationUnit;

private class TreeNodeBuildingNodeOperation extends PrimaryClassNodeOperation implements GroovyObject
{
    private final Object root;
    private final Object sourceCollected;
    private final ScriptToTreeNodeAdapter adapter;
    private final Object showScriptFreeForm;
    private final Object showScriptClass;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204219;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter;
    private static /* synthetic */ Class $class$java$util$concurrent$atomic$AtomicBoolean;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$ast$ModuleNode;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
    
    public TreeNodeBuildingNodeOperation(final ScriptToTreeNodeAdapter adapter, final Object showScriptFreeForm, final Object showScriptClass) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.root = $getCallSiteArray[0].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "root");
        this.sourceCollected = $getCallSiteArray[1].callConstructor($get$$class$java$util$concurrent$atomic$AtomicBoolean(), Boolean.FALSE);
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        if (!DefaultTypeTransformation.booleanUnbox(adapter)) {
            throw (Throwable)$getCallSiteArray[2].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Null: adapter");
        }
        this.adapter = (ScriptToTreeNodeAdapter)ScriptBytecodeAdapter.castToType(adapter, $get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter());
        this.showScriptFreeForm = showScriptFreeForm;
        this.showScriptClass = showScriptClass;
    }
    
    public void call(final SourceUnit source, final GeneratorContext context, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(this.sourceCollected, Boolean.TRUE)) && DefaultTypeTransformation.booleanUnbox(this.showScriptFreeForm)) ? Boolean.TRUE : Boolean.FALSE)) {
            final ModuleNode ast = (ModuleNode)ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(source), $get$$class$org$codehaus$groovy$ast$ModuleNode());
            final TreeNodeBuildingVisitor visitor = (TreeNodeBuildingVisitor)$getCallSiteArray[5].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor(), this.adapter);
            $getCallSiteArray[6].call($getCallSiteArray[7].call(ast), visitor);
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGetProperty(visitor))) {
                $getCallSiteArray[9].call(this.root, $getCallSiteArray[10].callGetProperty(visitor));
            }
            $getCallSiteArray[11].callCurrent(this, "Methods", $getCallSiteArray[12].call(ast));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[13].call(classNode)) && !DefaultTypeTransformation.booleanUnbox(this.showScriptClass)) ? Boolean.TRUE : Boolean.FALSE)) {
            return;
        }
        final Object child = $getCallSiteArray[14].call(this.adapter, classNode);
        $getCallSiteArray[15].call(this.root, child);
        $getCallSiteArray[16].callCurrent(this, child, "Constructors", classNode);
        $getCallSiteArray[17].callCurrent(this, child, "Methods", classNode);
        $getCallSiteArray[18].callCurrent(this, child, "Fields", classNode);
        $getCallSiteArray[19].callCurrent(this, child, "Properties", classNode);
        $getCallSiteArray[20].callCurrent(this, child, "Annotations", classNode);
    }
    
    private List collectAnnotationData(final TreeNodeWithProperties parent, final String name, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object allAnnotations = new Reference($getCallSiteArray[21].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), name));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[22].callGetProperty(classNode))) {
            $getCallSiteArray[23].call(parent, ((Reference<Object>)allAnnotations).get());
        }
        return (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[24].callSafe($getCallSiteArray[25].callGetProperty(classNode), new TreeNodeBuildingNodeOperation$_collectAnnotationData_closure1(this, this, (Reference<Object>)allAnnotations)), $get$$class$java$util$List());
    }
    
    private Object collectPropertyData(final TreeNodeWithProperties parent, final String name, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object allProperties = new Reference($getCallSiteArray[26].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), name));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[27].callGetProperty(classNode))) {
            $getCallSiteArray[28].call(parent, ((Reference<Object>)allProperties).get());
        }
        return $getCallSiteArray[29].callSafe($getCallSiteArray[30].callGetProperty(classNode), new TreeNodeBuildingNodeOperation$_collectPropertyData_closure2(this, this, (Reference<Object>)allProperties));
    }
    
    private Object collectFieldData(final TreeNodeWithProperties parent, final String name, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object allFields = new Reference($getCallSiteArray[31].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), name));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[32].callGetProperty(classNode))) {
            $getCallSiteArray[33].call(parent, ((Reference<Object>)allFields).get());
        }
        return $getCallSiteArray[34].callSafe($getCallSiteArray[35].callGetProperty(classNode), new TreeNodeBuildingNodeOperation$_collectFieldData_closure3(this, this, (Reference<Object>)allFields));
    }
    
    private Object collectMethodData(final TreeNodeWithProperties parent, final String name, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object allMethods = $getCallSiteArray[36].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), name);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[37].callGetProperty(classNode))) {
            $getCallSiteArray[38].call(parent, allMethods);
        }
        return $getCallSiteArray[39].callCurrent(this, allMethods, $getCallSiteArray[40].callGetProperty(classNode));
    }
    
    private Object collectModuleNodeMethodData(final String name, final List methods) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(methods)) {
            return null;
        }
        final Object allMethods = $getCallSiteArray[41].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), name);
        $getCallSiteArray[42].call(this.root, allMethods);
        return $getCallSiteArray[43].callCurrent(this, allMethods, methods);
    }
    
    private Object doCollectMethodData(final DefaultMutableTreeNode allMethods, final List methods) {
        final DefaultMutableTreeNode allMethods2 = (DefaultMutableTreeNode)new Reference(allMethods);
        return $getCallSiteArray()[44].callSafe(methods, new TreeNodeBuildingNodeOperation$_doCollectMethodData_closure4(this, this, (Reference<Object>)allMethods2));
    }
    
    private Object collectConstructorData(final TreeNodeWithProperties parent, final String name, final ClassNode classNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object allCtors = new Reference($getCallSiteArray[45].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), name));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[46].callGetProperty(classNode))) {
            $getCallSiteArray[47].call(parent, ((Reference<Object>)allCtors).get());
        }
        return $getCallSiteArray[48].callSafe($getCallSiteArray[49].callGetProperty(classNode), new TreeNodeBuildingNodeOperation$_collectConstructorData_closure5(this, this, (Reference<Object>)allCtors));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TreeNodeBuildingNodeOperation.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TreeNodeBuildingNodeOperation.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TreeNodeBuildingNodeOperation.__timeStamp__239_neverHappen1292524204219 = 0L;
        TreeNodeBuildingNodeOperation.__timeStamp = 1292524204219L;
    }
    
    public final Object getRoot() {
        return this.root;
    }
    
    public final Object getSourceCollected() {
        return this.sourceCollected;
    }
    
    public final ScriptToTreeNodeAdapter getAdapter() {
        return this.adapter;
    }
    
    public final Object getShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public final Object getShowScriptClass() {
        return this.showScriptClass;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[50];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeBuildingNodeOperation.$callSiteArray == null || ($createCallSiteArray = TreeNodeBuildingNodeOperation.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeBuildingNodeOperation.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter() {
        Class $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter;
        if (($class$groovy$inspect$swingui$ScriptToTreeNodeAdapter = TreeNodeBuildingNodeOperation.$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter) == null) {
            $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter = (TreeNodeBuildingNodeOperation.$class$groovy$inspect$swingui$ScriptToTreeNodeAdapter = class$("groovy.inspect.swingui.ScriptToTreeNodeAdapter"));
        }
        return $class$groovy$inspect$swingui$ScriptToTreeNodeAdapter;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$concurrent$atomic$AtomicBoolean() {
        Class $class$java$util$concurrent$atomic$AtomicBoolean;
        if (($class$java$util$concurrent$atomic$AtomicBoolean = TreeNodeBuildingNodeOperation.$class$java$util$concurrent$atomic$AtomicBoolean) == null) {
            $class$java$util$concurrent$atomic$AtomicBoolean = (TreeNodeBuildingNodeOperation.$class$java$util$concurrent$atomic$AtomicBoolean = class$("java.util.concurrent.atomic.AtomicBoolean"));
        }
        return $class$java$util$concurrent$atomic$AtomicBoolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingVisitor() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingVisitor = TreeNodeBuildingNodeOperation.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingVisitor = (TreeNodeBuildingNodeOperation.$class$groovy$inspect$swingui$TreeNodeBuildingVisitor = class$("groovy.inspect.swingui.TreeNodeBuildingVisitor"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingVisitor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TreeNodeBuildingNodeOperation.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TreeNodeBuildingNodeOperation.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$ast$ModuleNode() {
        Class $class$org$codehaus$groovy$ast$ModuleNode;
        if (($class$org$codehaus$groovy$ast$ModuleNode = TreeNodeBuildingNodeOperation.$class$org$codehaus$groovy$ast$ModuleNode) == null) {
            $class$org$codehaus$groovy$ast$ModuleNode = (TreeNodeBuildingNodeOperation.$class$org$codehaus$groovy$ast$ModuleNode = class$("org.codehaus.groovy.ast.ModuleNode"));
        }
        return $class$org$codehaus$groovy$ast$ModuleNode;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = TreeNodeBuildingNodeOperation.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (TreeNodeBuildingNodeOperation.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = TreeNodeBuildingNodeOperation.$class$java$util$List) == null) {
            $class$java$util$List = (TreeNodeBuildingNodeOperation.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = TreeNodeBuildingNodeOperation.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (TreeNodeBuildingNodeOperation.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation() {
        Class $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
        if (($class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = TreeNodeBuildingNodeOperation.$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation) == null) {
            $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = (TreeNodeBuildingNodeOperation.$class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation = class$("groovy.inspect.swingui.TreeNodeBuildingNodeOperation"));
        }
        return $class$groovy$inspect$swingui$TreeNodeBuildingNodeOperation;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
