// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class RecordCommand$_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public RecordCommand$_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].callGroovyObjectGetProperty(this))) {
            $getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this)), "Not recording");
            return null;
        }
        $getCallSiteArray[4].call($getCallSiteArray[5].callGetProperty($getCallSiteArray[6].callGroovyObjectGetProperty(this)), new GStringImpl(new Object[] { $getCallSiteArray[7].callGroovyObjectGetProperty(this), $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this)) }, new String[] { "Recording to file: ", " (", " bytes)" }));
        return $getCallSiteArray[10].callGroovyObjectGetProperty(this);
    }
    
    public Object doCall() {
        return $getCallSiteArray()[11].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$RecordCommand$_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RecordCommand$_closure3.$callSiteArray == null || ($createCallSiteArray = RecordCommand$_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RecordCommand$_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = RecordCommand$_closure3.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (RecordCommand$_closure3.$class$java$lang$Object = class$("java.lang.Object"));
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
