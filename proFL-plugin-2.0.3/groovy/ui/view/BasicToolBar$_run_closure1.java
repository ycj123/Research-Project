// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicToolBar$_run_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$SwingConstants;
    
    public BasicToolBar$_run_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[1].callGroovyObjectGetProperty(this));
        $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[3].callGroovyObjectGetProperty(this));
        $getCallSiteArray[4].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[5].callGroovyObjectGetProperty(this));
        $getCallSiteArray[6].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[7].callGetProperty($get$$class$javax$swing$SwingConstants()) }));
        $getCallSiteArray[8].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[9].callGroovyObjectGetProperty(this));
        $getCallSiteArray[10].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[11].callGroovyObjectGetProperty(this));
        $getCallSiteArray[12].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[13].callGetProperty($get$$class$javax$swing$SwingConstants()) }));
        $getCallSiteArray[14].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[15].callGroovyObjectGetProperty(this));
        $getCallSiteArray[16].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[17].callGroovyObjectGetProperty(this));
        $getCallSiteArray[18].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[19].callGroovyObjectGetProperty(this));
        $getCallSiteArray[20].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[21].callGetProperty($get$$class$javax$swing$SwingConstants()) }));
        $getCallSiteArray[22].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[23].callGroovyObjectGetProperty(this));
        $getCallSiteArray[24].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[25].callGroovyObjectGetProperty(this));
        $getCallSiteArray[26].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[27].callGetProperty($get$$class$javax$swing$SwingConstants()) }));
        $getCallSiteArray[28].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[29].callGroovyObjectGetProperty(this));
        $getCallSiteArray[30].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[31].callGroovyObjectGetProperty(this));
        $getCallSiteArray[32].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[33].callGetProperty($get$$class$javax$swing$SwingConstants()) }));
        $getCallSiteArray[34].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[35].callGroovyObjectGetProperty(this));
        return $getCallSiteArray[36].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", null }), $getCallSiteArray[37].callGroovyObjectGetProperty(this));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[38].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[39];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicToolBar$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicToolBar$_run_closure1.$callSiteArray == null || ($createCallSiteArray = BasicToolBar$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicToolBar$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicToolBar$_run_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicToolBar$_run_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SwingConstants() {
        Class $class$javax$swing$SwingConstants;
        if (($class$javax$swing$SwingConstants = BasicToolBar$_run_closure1.$class$javax$swing$SwingConstants) == null) {
            $class$javax$swing$SwingConstants = (BasicToolBar$_run_closure1.$class$javax$swing$SwingConstants = class$("javax.swing.SwingConstants"));
        }
        return $class$javax$swing$SwingConstants;
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
