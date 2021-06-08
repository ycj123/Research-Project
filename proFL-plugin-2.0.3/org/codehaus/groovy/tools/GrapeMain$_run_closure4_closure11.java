// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure4_closure11 extends Closure implements GeneratedClosure
{
    private Reference<Object> spacesLen;
    private Reference<Object> spaces;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public GrapeMain$_run_closure4_closure11(final Object _outerInstance, final Object _thisObject, final Reference<Object> spacesLen, final Reference<Object> spaces) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.spacesLen = spacesLen;
        this.spaces = spaces;
    }
    
    public Object doCall(final String k, final Object v) {
        final String i = (String)new Reference(k);
        final Object v2 = new Reference(v);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[1].call($getCallSiteArray[2].call(((Reference<Object>)i).get(), this.spaces.get()), GrapeMain$_run_closure4_closure11.$const$0, this.spacesLen.get()), $getCallSiteArray[3].callGetProperty(((Reference<Object>)v2).get()) }, new String[] { "  ", " ", "" }));
    }
    
    public Object call(final String k, final Object v) {
        final String i = (String)new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public Integer getSpacesLen() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.spacesLen.get(), $get$$class$java$lang$Integer());
    }
    
    public String getSpaces() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.spaces.get(), $get$$class$java$lang$String());
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure4_closure11(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure4_closure11.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure4_closure11.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure4_closure11.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = GrapeMain$_run_closure4_closure11.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (GrapeMain$_run_closure4_closure11.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GrapeMain$_run_closure4_closure11.$class$java$lang$String) == null) {
            $class$java$lang$String = (GrapeMain$_run_closure4_closure11.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
