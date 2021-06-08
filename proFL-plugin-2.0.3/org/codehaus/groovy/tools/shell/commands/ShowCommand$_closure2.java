// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ShowCommand$_closure2 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public ShowCommand$_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object classes = new Reference($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(this)));
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[2].call(((Reference<Object>)classes).get()), ShowCommand$_closure2.$const$0)) {
            return $getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(this)), "No classes have been loaded");
        }
        $getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGroovyObjectGetProperty(this)), "Classes:");
        return $getCallSiteArray[9].call(((Reference<Object>)classes).get(), new ShowCommand$_closure2_closure6(this, this.getThisObject()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[10].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$ShowCommand$_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ShowCommand$_closure2.$callSiteArray == null || ($createCallSiteArray = ShowCommand$_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ShowCommand$_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = ShowCommand$_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (ShowCommand$_closure2.$class$java$lang$Object = class$("java.lang.Object"));
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
