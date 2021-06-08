// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.MetaClass;
import java.util.Map;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class genArrayAccess extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205406;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$classgen$genArrayAccess;
    
    public genArrayAccess() {
        $getCallSiteArray();
    }
    
    public genArrayAccess(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$org$codehaus$groovy$classgen$genArrayAccess(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[1].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[2].callCurrent(this) }, new String[] { "\npackage org.codehaus.groovy.runtime.dgmimpl;\n\nimport groovy.lang.MetaClassImpl;\nimport groovy.lang.MetaMethod;\nimport org.codehaus.groovy.runtime.callsite.CallSite;\nimport org.codehaus.groovy.runtime.callsite.PojoMetaMethodSite;\nimport org.codehaus.groovy.reflection.CachedClass;\nimport org.codehaus.groovy.reflection.ReflectionCache;\n\npublic class ArrayOperations {\n  ", "\n}\n" }));
    }
    
    public Object genInners() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object res = new Reference("");
        final Map primitives = ScriptBytecodeAdapter.createMap(new Object[] { "boolean", "Boolean", "byte", "Byte", "char", "Character", "short", "Short", "int", "Integer", "long", "Long", "float", "Float", "double", "Double" });
        $getCallSiteArray[3].call(primitives, new genArrayAccess$_genInners_closure1(this, this, (Reference<Object>)res));
        return ((Reference<Object>)res).get();
    }
    
    static {
        genArrayAccess.__timeStamp__239_neverHappen1292524205406 = 0L;
        genArrayAccess.__timeStamp = 1292524205406L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genArrayAccess(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genArrayAccess.$callSiteArray == null || ($createCallSiteArray = genArrayAccess.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genArrayAccess.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = genArrayAccess.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (genArrayAccess.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = genArrayAccess.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (genArrayAccess.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$classgen$genArrayAccess() {
        Class $class$org$codehaus$groovy$classgen$genArrayAccess;
        if (($class$org$codehaus$groovy$classgen$genArrayAccess = genArrayAccess.$class$org$codehaus$groovy$classgen$genArrayAccess) == null) {
            $class$org$codehaus$groovy$classgen$genArrayAccess = (genArrayAccess.$class$org$codehaus$groovy$classgen$genArrayAccess = class$("org.codehaus.groovy.classgen.genArrayAccess"));
        }
        return $class$org$codehaus$groovy$classgen$genArrayAccess;
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
