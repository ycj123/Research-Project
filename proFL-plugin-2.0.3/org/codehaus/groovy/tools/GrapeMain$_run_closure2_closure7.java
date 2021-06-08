// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.Map;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure2_closure7 extends Closure implements GeneratedClosure
{
    private Reference<Object> versionCount;
    private Reference<Object> moduleCount;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    
    public GrapeMain$_run_closure2_closure7(final Object _outerInstance, final Object _thisObject, final Reference<Object> versionCount, final Reference<Object> moduleCount) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.versionCount = versionCount;
        this.moduleCount = moduleCount;
    }
    
    public Object doCall(final String groupName, final Map group) {
        final String groupName2 = (String)new Reference(groupName);
        final Map group2 = (Map)new Reference(group);
        final CallSite callSite = $getCallSiteArray()[0];
        final Object value = ((Reference)group2).get();
        final Object thisObject = this.getThisObject();
        final String groupName3 = groupName2;
        final Reference<Object> versionCount2;
        final Reference versionCount = versionCount2 = this.versionCount;
        final Reference moduleCount = this.moduleCount;
        return callSite.call(value, new GrapeMain$_run_closure2_closure7_closure8(this, thisObject, (Reference<Object>)groupName3, versionCount2, moduleCount));
    }
    
    public Object call(final String groupName, final Map group) {
        final String groupName2 = (String)new Reference(groupName);
        final Map group2 = (Map)new Reference(group);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)groupName2).get(), ((Reference)group2).get());
    }
    
    public Integer getVersionCount() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.versionCount.get(), $get$$class$java$lang$Integer());
    }
    
    public Integer getModuleCount() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.moduleCount.get(), $get$$class$java$lang$Integer());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure2_closure7(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure2_closure7.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure2_closure7.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure2_closure7.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = GrapeMain$_run_closure2_closure7.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (GrapeMain$_run_closure2_closure7.$class$java$lang$Integer = class$("java.lang.Integer"));
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
