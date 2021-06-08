// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure2 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$grape$Grape;
    
    public GrapeMain$_run_closure2(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object arg, final Object cmd) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, "");
        final Integer moduleCount = (Integer)new Reference(GrapeMain$_run_closure2.$const$0);
        final Integer versionCount = (Integer)new Reference(GrapeMain$_run_closure2.$const$0);
        $getCallSiteArray[1].call($get$$class$groovy$grape$Grape());
        $getCallSiteArray[2].callCurrent(this);
        $getCallSiteArray[3].call($getCallSiteArray[4].call($get$$class$groovy$grape$Grape()), new GrapeMain$_run_closure2_closure7(this, this.getThisObject(), (Reference<Object>)versionCount, (Reference<Object>)moduleCount));
        $getCallSiteArray[5].callCurrent(this, "");
        $getCallSiteArray[6].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)moduleCount).get() }, new String[] { "", " Grape modules cached" }));
        return $getCallSiteArray[7].callCurrent(this, new GStringImpl(new Object[] { ((Reference<Object>)versionCount).get() }, new String[] { "", " Grape module versions cached" }));
    }
    
    public Object call(final Object arg, final Object cmd) {
        return $getCallSiteArray()[8].callCurrent(this, arg, cmd);
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure2.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$Grape() {
        Class $class$groovy$grape$Grape;
        if (($class$groovy$grape$Grape = GrapeMain$_run_closure2.$class$groovy$grape$Grape) == null) {
            $class$groovy$grape$Grape = (GrapeMain$_run_closure2.$class$groovy$grape$Grape = class$("groovy.grape.Grape"));
        }
        return $class$groovy$grape$Grape;
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
