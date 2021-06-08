// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import java.io.File;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Binding;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Script;

public class genMathModification extends Script
{
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205445;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$Script;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$org$codehaus$groovy$classgen$genMathModification;
    
    public genMathModification() {
        $getCallSiteArray();
    }
    
    public genMathModification(final Binding context) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.invokeMethodOnSuperN($get$$class$groovy$lang$Script(), this, "setBinding", new Object[] { context });
    }
    
    public static void main(final String... args) {
        $getCallSiteArray()[0].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), $get$$class$org$codehaus$groovy$classgen$genMathModification(), args);
    }
    
    @Override
    public Object run() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object ops = ScriptBytecodeAdapter.createList(new Object[] { "plus", "minus", "multiply", "div", "or", "and", "xor", "intdiv", "mod", "leftShift", "rightShift", "rightShiftUnsigned" });
        final Object numbers = new Reference(ScriptBytecodeAdapter.createMap(new Object[] { "Byte", "byte", "Short", "short", "Integer", "int", "Long", "long", "Float", "float", "Double", "double" }));
        $getCallSiteArray[1].call(ops, new genMathModification$_run_closure1(this, this, (Reference<Object>)numbers));
        $getCallSiteArray[2].call(ops, new genMathModification$_run_closure2(this, this, (Reference<Object>)numbers));
        return $getCallSiteArray[3].call(ops, new genMathModification$_run_closure3(this, this, (Reference<Object>)numbers));
    }
    
    public Object isFloatingPoint(final Object number) {
        $getCallSiteArray();
        return (!ScriptBytecodeAdapter.compareEqual(number, "Double") && !ScriptBytecodeAdapter.compareEqual(number, "Float")) ? Boolean.FALSE : Boolean.TRUE;
    }
    
    public Object isLong(final Object number) {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.compareEqual(number, "Long") ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public Object getMath(final Object left, final Object right) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].callCurrent(this, left)) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callCurrent(this, right))) ? Boolean.FALSE : Boolean.TRUE)) {
            return ScriptBytecodeAdapter.createMap(new Object[] { "resType", "double", "plus", "+", "minus", "-", "multiply", "*", "div", "/" });
        }
        if (DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].callCurrent(this, left)) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].callCurrent(this, right))) ? Boolean.FALSE : Boolean.TRUE)) {
            return ScriptBytecodeAdapter.createMap(new Object[] { "resType", "long", "plus", "+", "minus", "-", "multiply", "*", "div", "/", "or", "|", "and", "&", "xor", "^", "intdiv", "/", "mod", "%", "leftShift", "<<", "rightShift", ">>", "rightShiftUnsigned", ">>>" });
        }
        return ScriptBytecodeAdapter.createMap(new Object[] { "resType", "int", "plus", "+", "minus", "-", "multiply", "*", "div", "/", "or", "|", "and", "&", "xor", "^", "intdiv", "/", "mod", "%", "leftShift", "<<", "rightShift", ">>", "rightShiftUnsigned", ">>>" });
    }
    
    static {
        genMathModification.__timeStamp__239_neverHappen1292524205445 = 0L;
        genMathModification.__timeStamp = 1292524205445L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification.$callSiteArray == null || ($createCallSiteArray = genMathModification.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Script() {
        Class $class$groovy$lang$Script;
        if (($class$groovy$lang$Script = genMathModification.$class$groovy$lang$Script) == null) {
            $class$groovy$lang$Script = (genMathModification.$class$groovy$lang$Script = class$("groovy.lang.Script"));
        }
        return $class$groovy$lang$Script;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = genMathModification.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (genMathModification.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$classgen$genMathModification() {
        Class $class$org$codehaus$groovy$classgen$genMathModification;
        if (($class$org$codehaus$groovy$classgen$genMathModification = genMathModification.$class$org$codehaus$groovy$classgen$genMathModification) == null) {
            $class$org$codehaus$groovy$classgen$genMathModification = (genMathModification.$class$org$codehaus$groovy$classgen$genMathModification = class$("org.codehaus.groovy.classgen.genMathModification"));
        }
        return $class$org$codehaus$groovy$classgen$genMathModification;
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
