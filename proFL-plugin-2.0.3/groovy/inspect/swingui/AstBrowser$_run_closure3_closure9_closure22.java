// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure3_closure9_closure22 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultTreeModel;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    
    public AstBrowser$_run_closure3_closure9_closure22(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object callCurrent = $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "name", "AstTreeView", "model", $getCallSiteArray[1].callConstructor($get$$class$javax$swing$tree$DefaultTreeModel(), $getCallSiteArray[2].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "Loading...")) }), new AstBrowser$_run_closure3_closure9_closure22_closure27(this, this.getThisObject()));
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22(), this, "jTree");
        return callCurrent;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[3].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure3_closure9_closure22.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure3_closure9_closure22.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure3_closure9_closure22.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_run_closure3_closure9_closure22.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_run_closure3_closure9_closure22.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22() {
        Class $class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22;
        if (($class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22 = AstBrowser$_run_closure3_closure9_closure22.$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22) == null) {
            $class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22 = (AstBrowser$_run_closure3_closure9_closure22.$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22 = class$("groovy.inspect.swingui.AstBrowser$_run_closure3_closure9_closure22"));
        }
        return $class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9_closure22;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultTreeModel() {
        Class $class$javax$swing$tree$DefaultTreeModel;
        if (($class$javax$swing$tree$DefaultTreeModel = AstBrowser$_run_closure3_closure9_closure22.$class$javax$swing$tree$DefaultTreeModel) == null) {
            $class$javax$swing$tree$DefaultTreeModel = (AstBrowser$_run_closure3_closure9_closure22.$class$javax$swing$tree$DefaultTreeModel = class$("javax.swing.tree.DefaultTreeModel"));
        }
        return $class$javax$swing$tree$DefaultTreeModel;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = AstBrowser$_run_closure3_closure9_closure22.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (AstBrowser$_run_closure3_closure9_closure22.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
        }
        return $class$javax$swing$tree$DefaultMutableTreeNode;
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
