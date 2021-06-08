// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConfigObject$_writeConfig_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public ConfigObject$_writeConfig_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object entry) {
        final Object entry2 = new Reference(entry);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(((Reference<Object>)entry2).get()), "."), ConfigObject$_writeConfig_closure1.$const$0) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    static {
        $const$0 = -1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigObject$_writeConfig_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigObject$_writeConfig_closure1.$callSiteArray == null || ($createCallSiteArray = ConfigObject$_writeConfig_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigObject$_writeConfig_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
