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

class AstBrowser$_compile_closure7_closure37 extends Closure implements GeneratedClosure
{
    private Reference<Object> jTree;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Cursor;
    
    public AstBrowser$_compile_closure7_closure37(final Object _outerInstance, final Object _thisObject, final Reference<Object> jTree) {
        final Reference jTree2 = new Reference((T)jTree);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.jTree = jTree2.get();
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call(this.jTree.get(), $getCallSiteArray[1].callGetProperty($get$$class$java$awt$Cursor()));
    }
    
    public Object getjTree() {
        $getCallSiteArray();
        return this.jTree.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_compile_closure7_closure37(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_compile_closure7_closure37.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_compile_closure7_closure37.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_compile_closure7_closure37.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_compile_closure7_closure37.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_compile_closure7_closure37.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Cursor() {
        Class $class$java$awt$Cursor;
        if (($class$java$awt$Cursor = AstBrowser$_compile_closure7_closure37.$class$java$awt$Cursor) == null) {
            $class$java$awt$Cursor = (AstBrowser$_compile_closure7_closure37.$class$java$awt$Cursor = class$("java.awt.Cursor"));
        }
        return $class$java$awt$Cursor;
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
