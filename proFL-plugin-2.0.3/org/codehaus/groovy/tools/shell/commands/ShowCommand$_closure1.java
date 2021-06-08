// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ShowCommand$_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ShowCommand$_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this)))) {
            return $getCallSiteArray[2].call($getCallSiteArray[3].callGetProperty($getCallSiteArray[4].callGroovyObjectGetProperty(this)), "No variables defined");
        }
        $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(this)), "Variables:");
        return $getCallSiteArray[8].call($getCallSiteArray[9].callGroovyObjectGetProperty(this), new ShowCommand$_closure1_closure5(this, this.getThisObject()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[10].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ShowCommand$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShowCommand$_closure1.$callSiteArray == null || ($createCallSiteArray = ShowCommand$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShowCommand$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ShowCommand$_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ShowCommand$_closure1.$class$java$lang$Object = class$("java.lang.Object"));
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
