// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import javax.swing.tree.MutableTreeNode;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import groovy.lang.GroovyObject;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeNodeWithProperties extends DefaultMutableTreeNode implements GroovyObject
{
    private List<List<String>> properties;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204111;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$TreeNodeWithProperties;
    
    public TreeNodeWithProperties(final Object userObject, final List<List<String>> properties) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[] { null }));
        arguments[0] = userObject;
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 3, $get$$class$javax$swing$tree$DefaultMutableTreeNode());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                break;
            }
            case 1: {
                super(array[0]);
                break;
            }
            case 2: {
                super(array2[0], DefaultTypeTransformation.booleanUnbox(array3[1]));
                break;
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.properties = (List<List<String>>)ScriptBytecodeAdapter.castToType(properties, $get$$class$java$util$List());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$TreeNodeWithProperties()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = TreeNodeWithProperties.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (TreeNodeWithProperties.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        TreeNodeWithProperties.__timeStamp__239_neverHappen1292524204111 = 0L;
        TreeNodeWithProperties.__timeStamp = 1292524204111L;
    }
    
    public List<List<String>> getProperties() {
        return this.properties;
    }
    
    public void setProperties(final List<List<String>> properties) {
        this.properties = properties;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$groovy$inspect$swingui$TreeNodeWithProperties(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TreeNodeWithProperties.$callSiteArray == null || ($createCallSiteArray = TreeNodeWithProperties.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TreeNodeWithProperties.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = TreeNodeWithProperties.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (TreeNodeWithProperties.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = TreeNodeWithProperties.$class$java$util$List) == null) {
            $class$java$util$List = (TreeNodeWithProperties.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = TreeNodeWithProperties.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (TreeNodeWithProperties.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$TreeNodeWithProperties() {
        Class $class$groovy$inspect$swingui$TreeNodeWithProperties;
        if (($class$groovy$inspect$swingui$TreeNodeWithProperties = TreeNodeWithProperties.$class$groovy$inspect$swingui$TreeNodeWithProperties) == null) {
            $class$groovy$inspect$swingui$TreeNodeWithProperties = (TreeNodeWithProperties.$class$groovy$inspect$swingui$TreeNodeWithProperties = class$("groovy.inspect.swingui.TreeNodeWithProperties"));
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
