// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class RecordCommand$_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public RecordCommand$_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGroovyObjectGetProperty(this))) {
            return $getCallSiteArray[1].callCurrent(this);
        }
        return null;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RecordCommand$_closure4.$callSiteArray == null || ($createCallSiteArray = RecordCommand$_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RecordCommand$_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RecordCommand$_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RecordCommand$_closure4.$class$java$lang$Object = class$("java.lang.Object"));
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
