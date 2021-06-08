// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeMain$_run_closure2_closure7_closure8 extends Closure implements GeneratedClosure
{
    private Reference<Object> groupName;
    private Reference<Object> versionCount;
    private Reference<Object> moduleCount;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public GrapeMain$_run_closure2_closure7_closure8(final Object _outerInstance, final Object _thisObject, final Reference<Object> groupName, final Reference<Object> versionCount, final Reference<Object> moduleCount) {
        final Reference versionCount2 = new Reference((T)versionCount);
        final Reference moduleCount2 = new Reference((T)moduleCount);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.groupName = groupName;
        this.versionCount = versionCount2.get();
        this.moduleCount = moduleCount2.get();
    }
    
    public Object doCall(final String moduleName, final List<String> versions) {
        final String moduleName2 = (String)new Reference(moduleName);
        final List versions2 = (List)new Reference(versions);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this, new GStringImpl(new Object[] { this.groupName.get(), ((Reference<Object>)moduleName2).get(), ((Reference)versions2).get() }, new String[] { "", " ", "  ", "" }));
        this.moduleCount.get();
        this.moduleCount.set($getCallSiteArray[1].call(this.moduleCount.get()));
        final Object call = $getCallSiteArray[2].call(this.versionCount.get(), $getCallSiteArray[3].call(((Reference)versions2).get()));
        this.versionCount.set(ScriptBytecodeAdapter.castToType(call, $get$$class$java$lang$Integer()));
        return call;
    }
    
    public Object call(final String moduleName, final List<String> versions) {
        final String moduleName2 = (String)new Reference(moduleName);
        final List versions2 = (List)new Reference(versions);
        return $getCallSiteArray()[4].callCurrent(this, ((Reference<Object>)moduleName2).get(), ((Reference)versions2).get());
    }
    
    public String getGroupName() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.groupName.get(), $get$$class$java$lang$String());
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
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$GrapeMain$_run_closure2_closure7_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeMain$_run_closure2_closure7_closure8.$callSiteArray == null || ($createCallSiteArray = GrapeMain$_run_closure2_closure7_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeMain$_run_closure2_closure7_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = GrapeMain$_run_closure2_closure7_closure8.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (GrapeMain$_run_closure2_closure7_closure8.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GrapeMain$_run_closure2_closure7_closure8.$class$java$lang$String) == null) {
            $class$java$lang$String = (GrapeMain$_run_closure2_closure7_closure8.$class$java$lang$String = class$("java.lang.String"));
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
