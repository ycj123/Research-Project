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

class AstBrowser$_compile_closure7_closure36 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private Reference<Object> model;
    private Reference<Object> jTree;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$awt$Cursor;
    
    public AstBrowser$_compile_closure7_closure36(final Object _outerInstance, final Object _thisObject, final Reference<Object> result, final Reference<Object> model, final Reference<Object> jTree) {
        final Reference model2 = new Reference((T)model);
        final Reference jTree2 = new Reference((T)jTree);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
        this.model = model2.get();
        this.jTree = jTree2.get();
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call(this.model.get(), this.result.get());
        $getCallSiteArray[1].call(this.model.get());
        return $getCallSiteArray[2].call(this.jTree.get(), $getCallSiteArray[3].callGetProperty($get$$class$java$awt$Cursor()));
    }
    
    public Object getResult() {
        $getCallSiteArray();
        return this.result.get();
    }
    
    public Object getModel() {
        $getCallSiteArray();
        return this.model.get();
    }
    
    public Object getjTree() {
        $getCallSiteArray();
        return this.jTree.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_compile_closure7_closure36(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_compile_closure7_closure36.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_compile_closure7_closure36.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_compile_closure7_closure36.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_compile_closure7_closure36.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_compile_closure7_closure36.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$Cursor() {
        Class $class$java$awt$Cursor;
        if (($class$java$awt$Cursor = AstBrowser$_compile_closure7_closure36.$class$java$awt$Cursor) == null) {
            $class$java$awt$Cursor = (AstBrowser$_compile_closure7_closure36.$class$java$awt$Cursor = class$("java.awt.Cursor"));
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
