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
import groovy.lang.Reference;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class genDgmMath extends Script
{
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205441;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$classgen$genDgmMath;
    
    public genDgmMath() {
        $getCallSiteArray();
    }
    
    public genDgmMath(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$org$codehaus$groovy$classgen$genDgmMath(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object types = new Reference(ScriptBytecodeAdapter.createList(new Object[] { "Integer", "Long", "Float", "Double" }));
        $getCallSiteArray[1].callCurrent(this, "\npublic CallSite createPojoCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object receiver, Object[] args) {\n    NumberMath m = NumberMath.getMath((Number)receiver, (Number)args[0]);\n");
        $getCallSiteArray[2].call(((Reference<Object>)types).get(), new genDgmMath$_run_closure1(this, this, (Reference<Object>)types));
        $getCallSiteArray[3].callCurrent(this, "\n    return new NumberNumberCallSite (site, metaClass, metaMethod, params, (Number)receiver, (Number)args[0]){\n        public final Object invoke(Object receiver, Object[] args) {\n            return math.addImpl((Number)receiver,(Number)args[0]);\n        }\n\n        public final Object invokeBinop(Object receiver, Object arg) {\n            return math.addImpl((Number)receiver,(Number)arg);\n        }\n}\n");
        Object i = null;
        final Object call = $getCallSiteArray[4].call(ScriptBytecodeAdapter.createRange(genDgmMath.$const$0, genDgmMath.$const$1, true));
        while (((Iterator)call).hasNext()) {
            i = ((Iterator<Object>)call).next();
            $getCallSiteArray[5].callCurrent(this, new GStringImpl(new Object[] { i }, new String[] { "public Object invoke", " (Object receiver, " }));
            Object j = null;
            final Object call2 = $getCallSiteArray[6].call(ScriptBytecodeAdapter.createRange(genDgmMath.$const$2, $getCallSiteArray[7].call(i, genDgmMath.$const$2), true));
            while (((Iterator)call2).hasNext()) {
                j = ((Iterator<Object>)call2).next();
                $getCallSiteArray[8].callCurrent(this, new GStringImpl(new Object[] { j }, new String[] { "Object a", ", " }));
            }
            $getCallSiteArray[9].callCurrent(this, new GStringImpl(new Object[] { i }, new String[] { "Object a", ") {" }));
            $getCallSiteArray[10].callCurrent(this, "  return invoke (receiver, new Object[] {");
            j = null;
            final Object call3 = $getCallSiteArray[11].call(ScriptBytecodeAdapter.createRange(genDgmMath.$const$2, $getCallSiteArray[12].call(i, genDgmMath.$const$2), true));
            while (((Iterator)call3).hasNext()) {
                j = ((Iterator<Object>)call3).next();
                $getCallSiteArray[13].callCurrent(this, new GStringImpl(new Object[] { j }, new String[] { "a", ", " }));
            }
            $getCallSiteArray[14].callCurrent(this, new GStringImpl(new Object[] { i }, new String[] { "a", "} );" }));
            $getCallSiteArray[15].callCurrent(this, "}");
        }
        return null;
    }
    
    public Object getMath(final Object a, final Object b) {
        $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(a, "Double") && !ScriptBytecodeAdapter.compareEqual(b, "Double")) ? Boolean.FALSE : Boolean.TRUE) && !ScriptBytecodeAdapter.compareEqual(a, "Float")) ? Boolean.FALSE : Boolean.TRUE) && !ScriptBytecodeAdapter.compareEqual(b, "Float")) ? Boolean.FALSE : Boolean.TRUE)) {
            return "FloatingPointMath";
        }
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(a, "Long") && !ScriptBytecodeAdapter.compareEqual(b, "Long")) ? Boolean.FALSE : Boolean.TRUE)) {
            return "LongMath";
        }
        return "IntegerMath";
    }
    
    static {
        genDgmMath.__timeStamp__239_neverHappen1292524205441 = 0L;
        genDgmMath.__timeStamp = 1292524205441L;
        $const$2 = 1;
        $const$1 = 256;
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[16];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genDgmMath(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genDgmMath.$callSiteArray == null || ($createCallSiteArray = genDgmMath.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genDgmMath.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = genDgmMath.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (genDgmMath.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = genDgmMath.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (genDgmMath.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$classgen$genDgmMath() {
        Class $class$org$codehaus$groovy$classgen$genDgmMath;
        if (($class$org$codehaus$groovy$classgen$genDgmMath = genDgmMath.$class$org$codehaus$groovy$classgen$genDgmMath) == null) {
            $class$org$codehaus$groovy$classgen$genDgmMath = (genDgmMath.$class$org$codehaus$groovy$classgen$genDgmMath = class$("org.codehaus.groovy.classgen.genDgmMath"));
        }
        return $class$org$codehaus$groovy$classgen$genDgmMath;
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
