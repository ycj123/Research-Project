// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.mudebug.prapr.reloc.commons.cli.Option;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class CliBuilder$_option_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> option;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$commons$cli$Option;
    
    public CliBuilder$_option_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> option) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.option = option;
    }
    
    public Object doCall(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        final CallSite callSite = $getCallSiteArray()[0];
        final Object value3 = this.option.get();
        final Object value4 = ((Reference<Object>)key2).get();
        final Object value5 = ((Reference<Object>)value2).get();
        callSite.call(value3, value4, value5);
        return value5;
    }
    
    public Object call(final Object key, final Object value) {
        final Object key2 = new Reference(key);
        final Object value2 = new Reference(value);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)key2).get(), ((Reference<Object>)value2).get());
    }
    
    public Option getOption() {
        $getCallSiteArray();
        return (Option)ScriptBytecodeAdapter.castToType(this.option.get(), $get$$class$org$apache$commons$cli$Option());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$CliBuilder$_option_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CliBuilder$_option_closure1.$callSiteArray == null || ($createCallSiteArray = CliBuilder$_option_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CliBuilder$_option_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$Option() {
        Class $class$org$apache$commons$cli$Option;
        if (($class$org$apache$commons$cli$Option = CliBuilder$_option_closure1.$class$org$apache$commons$cli$Option) == null) {
            $class$org$apache$commons$cli$Option = (CliBuilder$_option_closure1.$class$org$apache$commons$cli$Option = class$("org.mudebug.prapr.reloc.commons.cli.Option"));
        }
        return $class$org$apache$commons$cli$Option;
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
