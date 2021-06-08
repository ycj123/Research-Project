// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.util.Map;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Closure;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Binding;

public class ConfigBinding extends Binding
{
    private Object callable;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205401;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public ConfigBinding(final Closure c) {
        $getCallSiteArray();
        this.callable = c;
    }
    
    public void setVariable(final String name, final Object value) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeClosure(this.callable, new Object[] { name, value });
    }
    
    static {
        ConfigBinding.__timeStamp__239_neverHappen1292524205401 = 0L;
        ConfigBinding.__timeStamp = 1292524205401L;
    }
    
    public Object getCallable() {
        return this.callable;
    }
    
    public void setCallable(final Object callable) {
        this.callable = callable;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$groovy$util$ConfigBinding(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigBinding.$callSiteArray == null || ($createCallSiteArray = ConfigBinding.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigBinding.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
