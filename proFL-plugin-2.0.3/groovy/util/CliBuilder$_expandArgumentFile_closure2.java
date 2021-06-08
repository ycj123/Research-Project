// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class CliBuilder$_expandArgumentFile_closure2 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    
    public CliBuilder$_expandArgumentFile_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final String s) {
        final String s2 = (String)new Reference(s);
        return ScriptBytecodeAdapter.asType($getCallSiteArray()[0].call(((Reference<Object>)s2).get()), $get$$class$java$lang$Integer());
    }
    
    public Object call(final String s) {
        final String s2 = (String)new Reference(s);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)s2).get());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$CliBuilder$_expandArgumentFile_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CliBuilder$_expandArgumentFile_closure2.$callSiteArray == null || ($createCallSiteArray = CliBuilder$_expandArgumentFile_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CliBuilder$_expandArgumentFile_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = CliBuilder$_expandArgumentFile_closure2.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (CliBuilder$_expandArgumentFile_closure2.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
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
