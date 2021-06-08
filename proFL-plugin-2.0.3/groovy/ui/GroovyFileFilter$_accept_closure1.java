// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import java.io.File;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GroovyFileFilter$_accept_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> f;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$ui$GroovyFileFilter;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$io$File;
    
    public GroovyFileFilter$_accept_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> f) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.f = f;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return ScriptBytecodeAdapter.compareEqual(((Reference<Object>)it2).get(), $getCallSiteArray()[0].callStatic($get$$class$groovy$ui$GroovyFileFilter(), this.f.get())) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public File getF() {
        $getCallSiteArray();
        return (File)ScriptBytecodeAdapter.castToType(this.f.get(), $get$$class$java$io$File());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$GroovyFileFilter$_accept_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GroovyFileFilter$_accept_closure1.$callSiteArray == null || ($createCallSiteArray = GroovyFileFilter$_accept_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GroovyFileFilter$_accept_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$GroovyFileFilter() {
        Class $class$groovy$ui$GroovyFileFilter;
        if (($class$groovy$ui$GroovyFileFilter = GroovyFileFilter$_accept_closure1.$class$groovy$ui$GroovyFileFilter) == null) {
            $class$groovy$ui$GroovyFileFilter = (GroovyFileFilter$_accept_closure1.$class$groovy$ui$GroovyFileFilter = class$("groovy.ui.GroovyFileFilter"));
        }
        return $class$groovy$ui$GroovyFileFilter;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = GroovyFileFilter$_accept_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (GroovyFileFilter$_accept_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = GroovyFileFilter$_accept_closure1.$class$java$io$File) == null) {
            $class$java$io$File = (GroovyFileFilter$_accept_closure1.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
