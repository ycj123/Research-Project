// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_compile_closure6 extends Closure implements GeneratedClosure
{
    private Reference<Object> model;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$tree$DefaultMutableTreeNode;
    
    public AstBrowser$_compile_closure6(final Object _outerInstance, final Object _thisObject, final Reference<Object> model) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.model = model;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object root = $getCallSiteArray[0].call(this.model.get());
        $getCallSiteArray[1].call(root);
        $getCallSiteArray[2].call(root, $getCallSiteArray[3].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), $getCallSiteArray[4].callConstructor($get$$class$javax$swing$tree$DefaultMutableTreeNode(), "Loading...")));
        return $getCallSiteArray[5].call(this.model.get(), root);
    }
    
    public Object getModel() {
        $getCallSiteArray();
        return this.model.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[6].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_compile_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_compile_closure6.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_compile_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_compile_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_compile_closure6.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_compile_closure6.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$DefaultMutableTreeNode() {
        Class $class$javax$swing$tree$DefaultMutableTreeNode;
        if (($class$javax$swing$tree$DefaultMutableTreeNode = AstBrowser$_compile_closure6.$class$javax$swing$tree$DefaultMutableTreeNode) == null) {
            $class$javax$swing$tree$DefaultMutableTreeNode = (AstBrowser$_compile_closure6.$class$javax$swing$tree$DefaultMutableTreeNode = class$("javax.swing.tree.DefaultMutableTreeNode"));
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
