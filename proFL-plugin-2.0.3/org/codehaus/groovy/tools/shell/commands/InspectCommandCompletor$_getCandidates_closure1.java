// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class InspectCommandCompletor$_getCandidates_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> set;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    
    public InspectCommandCompletor$_getCandidates_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> set) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.set = set;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].call(this.set.get(), ((Reference<Object>)it2).get());
    }
    
    public Object getSet() {
        $getCallSiteArray();
        return this.set.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$InspectCommandCompletor$_getCandidates_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (InspectCommandCompletor$_getCandidates_closure1.$callSiteArray == null || ($createCallSiteArray = InspectCommandCompletor$_getCandidates_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            InspectCommandCompletor$_getCandidates_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = InspectCommandCompletor$_getCandidates_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (InspectCommandCompletor$_getCandidates_closure1.$class$java$lang$Object = class$("java.lang.Object"));
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
