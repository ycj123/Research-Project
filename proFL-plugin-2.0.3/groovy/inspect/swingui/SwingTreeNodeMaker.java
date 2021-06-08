// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import javax.swing.tree.DefaultMutableTreeNode;

public class SwingTreeNodeMaker implements AstBrowserNodeMaker<DefaultMutableTreeNode>, GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202697;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$SwingTreeNodeMaker;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeWithProperties;
    
    public SwingTreeNodeMaker() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public DefaultMutableTreeNode makeNode(final Object userObject) {
        return (DefaultMutableTreeNode)ScriptBytecodeAdapter.castToType($getCallSiteArray()[0].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), userObject), $get$$class$javax$swing$tree$DefaultMutableTreeNode());
    }
    
    public DefaultMutableTreeNode makeNodeWithProperties(final Object userObject, final List<List<String>> properties) {
        return (DefaultMutableTreeNode)ScriptBytecodeAdapter.castToType($getCallSiteArray()[1].callConstructor($get$$class$groovy$inspect$swingui$TreeNodeWithProperties(), userObject, properties), $get$$class$javax$swing$tree$DefaultMutableTreeNode());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$SwingTreeNodeMaker()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = SwingTreeNodeMaker.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (SwingTreeNodeMaker.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        SwingTreeNodeMaker.__timeStamp__239_neverHappen1292524202697 = 0L;
        SwingTreeNodeMaker.__timeStamp = 1292524202697L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$SwingTreeNodeMaker(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (SwingTreeNodeMaker.$callSiteArray == null || ($createCallSiteArray = SwingTreeNodeMaker.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            SwingTreeNodeMaker.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = SwingTreeNodeMaker.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (SwingTreeNodeMaker.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = SwingTreeNodeMaker.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (SwingTreeNodeMaker.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$SwingTreeNodeMaker() {
        Class $class$groovy$inspect$swingui$SwingTreeNodeMaker;
        if (($class$groovy$inspect$swingui$SwingTreeNodeMaker = SwingTreeNodeMaker.$class$groovy$inspect$swingui$SwingTreeNodeMaker) == null) {
            $class$groovy$inspect$swingui$SwingTreeNodeMaker = (SwingTreeNodeMaker.$class$groovy$inspect$swingui$SwingTreeNodeMaker = class$("groovy.inspect.swingui.SwingTreeNodeMaker"));
        }
        return $class$groovy$inspect$swingui$SwingTreeNodeMaker;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeWithProperties() {
        Class $class$groovy$inspect$swingui$TreeNodeWithProperties;
        if (($class$groovy$inspect$swingui$TreeNodeWithProperties = SwingTreeNodeMaker.$class$groovy$inspect$swingui$TreeNodeWithProperties) == null) {
            $class$groovy$inspect$swingui$TreeNodeWithProperties = (SwingTreeNodeMaker.$class$groovy$inspect$swingui$TreeNodeWithProperties = class$("groovy.inspect.swingui.TreeNodeWithProperties"));
        }
        return $class$groovy$inspect$swingui$TreeNodeWithProperties;
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
