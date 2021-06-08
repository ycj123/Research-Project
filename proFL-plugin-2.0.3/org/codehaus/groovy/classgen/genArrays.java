// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class genArrays extends Script
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205409;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$classgen$genArrays;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    
    public genArrays() {
        $getCallSiteArray();
    }
    
    public genArrays(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$org$codehaus$groovy$classgen$genArrays(), args);
    }
    
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[1].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[2].callCurrent(this) }, new String[] { "\n\npublic class ArrayUtil {\n   ", "\n}\n\n" }));
    }
    
    public Object genMethods() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object res = "";
        Object i = null;
        final Object call = $getCallSiteArray[3].call(ScriptBytecodeAdapter.createRange(genArrays.$const$0, genArrays.$const$1, true));
        while (((Iterator)call).hasNext()) {
            i = ((Iterator<Object>)call).next();
            res = $getCallSiteArray[4].call(res, $getCallSiteArray[5].call("\n\n", $getCallSiteArray[6].callCurrent(this, i)));
        }
        return res;
    }
    
    public Object genMethod(final int paramNum) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object res = "public static Object [] createArray (";
        Object k = null;
        final Object call = $getCallSiteArray[7].call(ScriptBytecodeAdapter.createRange(genArrays.$const$2, DefaultTypeTransformation.box(paramNum), false));
        while (((Iterator)call).hasNext()) {
            k = ((Iterator<Object>)call).next();
            res = $getCallSiteArray[8].call(res, $getCallSiteArray[9].call("Object arg", k));
            if (ScriptBytecodeAdapter.compareNotEqual(k, $getCallSiteArray[10].call(DefaultTypeTransformation.box(paramNum), genArrays.$const$0))) {
                res = $getCallSiteArray[11].call(res, ", ");
            }
        }
        res = $getCallSiteArray[12].call(res, ") {\n");
        res = $getCallSiteArray[13].call(res, "return new Object [] {\n");
        k = null;
        final Object call2 = $getCallSiteArray[14].call(ScriptBytecodeAdapter.createRange(genArrays.$const$2, DefaultTypeTransformation.box(paramNum), false));
        while (((Iterator)call2).hasNext()) {
            k = ((Iterator<Object>)call2).next();
            res = $getCallSiteArray[15].call(res, $getCallSiteArray[16].call("arg", k));
            if (ScriptBytecodeAdapter.compareNotEqual(k, $getCallSiteArray[17].call(DefaultTypeTransformation.box(paramNum), genArrays.$const$0))) {
                res = $getCallSiteArray[18].call(res, ", ");
            }
        }
        res = $getCallSiteArray[19].call(res, "};\n");
        res = $getCallSiteArray[20].call(res, "}");
        return res;
    }
    
    static {
        genArrays.__timeStamp__239_neverHappen1292524205409 = 0L;
        genArrays.__timeStamp = 1292524205409L;
        $const$2 = 0;
        $const$1 = 250;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[21];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genArrays(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genArrays.$callSiteArray == null || ($createCallSiteArray = genArrays.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genArrays.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = genArrays.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (genArrays.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$classgen$genArrays() {
        Class $class$org$codehaus$groovy$classgen$genArrays;
        if (($class$org$codehaus$groovy$classgen$genArrays = genArrays.$class$org$codehaus$groovy$classgen$genArrays) == null) {
            $class$org$codehaus$groovy$classgen$genArrays = (genArrays.$class$org$codehaus$groovy$classgen$genArrays = class$("org.codehaus.groovy.classgen.genArrays"));
        }
        return $class$org$codehaus$groovy$classgen$genArrays;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = genArrays.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (genArrays.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
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
