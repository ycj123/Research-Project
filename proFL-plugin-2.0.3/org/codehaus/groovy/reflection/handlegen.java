// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import groovy.lang.Script;

public class handlegen extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205453;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$reflection$handlegen;
    
    public handlegen() {
        $getCallSiteArray();
    }
    
    public handlegen(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$org$codehaus$groovy$reflection$handlegen(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object types = new Reference(ScriptBytecodeAdapter.createList(new Object[] { "boolean", "char", "byte", "short", "int", "long", "float", "double", "Object" }));
        return $getCallSiteArray[1].call(((Reference<Object>)types).get(), new handlegen$_run_closure1(this, this, (Reference<Object>)types));
    }
    
    static {
        handlegen.__timeStamp__239_neverHappen1292524205453 = 0L;
        handlegen.__timeStamp = 1292524205453L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$reflection$handlegen(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (handlegen.$callSiteArray == null || ($createCallSiteArray = handlegen.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            handlegen.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = handlegen.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (handlegen.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = handlegen.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (handlegen.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$reflection$handlegen() {
        Class $class$org$codehaus$groovy$reflection$handlegen;
        if (($class$org$codehaus$groovy$reflection$handlegen = handlegen.$class$org$codehaus$groovy$reflection$handlegen) == null) {
            $class$org$codehaus$groovy$reflection$handlegen = (handlegen.$class$org$codehaus$groovy$reflection$handlegen = class$("org.codehaus.groovy.reflection.handlegen"));
        }
        return $class$org$codehaus$groovy$reflection$handlegen;
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
