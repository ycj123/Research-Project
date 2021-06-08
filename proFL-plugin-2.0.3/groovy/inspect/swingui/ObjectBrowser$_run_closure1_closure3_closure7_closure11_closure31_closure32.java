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

class ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Name", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure33(this, this.getThisObject()) }));
        $getCallSiteArray[1].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Params", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure34(this, this.getThisObject()) }));
        $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Type", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure35(this, this.getThisObject()) }));
        $getCallSiteArray[3].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Origin", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure36(this, this.getThisObject()) }));
        $getCallSiteArray[4].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Modifier", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure37(this, this.getThisObject()) }));
        $getCallSiteArray[5].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Declarer", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure38(this, this.getThisObject()) }));
        return $getCallSiteArray[6].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "header", "Exceptions", "read", new ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32_closure39(this, this.getThisObject()) }));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[7].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32.$callSiteArray == null || ($createCallSiteArray = ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ObjectBrowser$_run_closure1_closure3_closure7_closure11_closure31_closure32.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
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
