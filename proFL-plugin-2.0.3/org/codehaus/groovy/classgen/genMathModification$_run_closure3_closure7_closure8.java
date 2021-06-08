// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genMathModification$_run_closure3_closure7_closure8 extends Closure implements GeneratedClosure
{
    private Reference<Object> op;
    private Reference<Object> wrappedType1;
    private Reference<Object> type1;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genMathModification$_run_closure3_closure7_closure8(final Object _outerInstance, final Object _thisObject, final Reference<Object> op, final Reference<Object> wrappedType1, final Reference<Object> type1) {
        final Reference op2 = new Reference((T)op);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.op = op2.get();
        this.wrappedType1 = wrappedType1;
        this.type1 = type1;
    }
    
    public Object doCall(final Object wrappedType2, final Object type2) {
        final Object wrappedType3 = new Reference(wrappedType2);
        final Object type3 = new Reference(type2);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object math = new Reference($getCallSiteArray[0].callCurrent(this, this.wrappedType1.get(), ((Reference<Object>)wrappedType3).get()));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(((Reference<Object>)math).get(), this.op.get()))) {
            $getCallSiteArray[2].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[3].callGetProperty(((Reference<Object>)math).get()), this.op.get(), this.type1.get(), ((Reference<Object>)type3).get(), this.type1.get(), this.op.get(), this.op.get(), ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[4].callGetProperty(((Reference<Object>)math).get()), this.type1.get()) ? $getCallSiteArray[5].call($getCallSiteArray[6].call("((", $getCallSiteArray[7].callGetProperty(((Reference<Object>)math).get())), ")op1)") : "op1", $getCallSiteArray[8].call(((Reference<Object>)math).get(), this.op.get()), ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[9].callGetProperty(((Reference<Object>)math).get()), ((Reference<Object>)type3).get()) ? $getCallSiteArray[10].call($getCallSiteArray[11].call("((", $getCallSiteArray[12].callGetProperty(((Reference<Object>)math).get())), ")op2)") : "op2" }, new String[] { "public static ", " ", "(", " op1, ", " op2) {\n                   if (instance.", "_", ") {\n                      return ", "Slow(op1, op2);\n                   }\n                   else {\n                      return ", " ", " ", ";\n                   }\n                }" }));
            return $getCallSiteArray[13].callCurrent(this, new GStringImpl(new Object[] { $getCallSiteArray[14].callGetProperty(((Reference<Object>)math).get()), this.op.get(), this.type1.get(), ((Reference<Object>)type3).get(), this.op.get(), $getCallSiteArray[15].callGetProperty(((Reference<Object>)math).get()) }, new String[] { "private static ", " ", "Slow(", " op1,", " op2) {\n                      return ((Number)InvokerHelper.invokeMethod(op1, \"", "\", op2)).", "Value();\n                }" }));
        }
        return null;
    }
    
    public Object call(final Object wrappedType2, final Object type2) {
        final Object wrappedType3 = new Reference(wrappedType2);
        final Object type3 = new Reference(type2);
        return $getCallSiteArray()[16].callCurrent(this, ((Reference<Object>)wrappedType3).get(), ((Reference<Object>)type3).get());
    }
    
    public Object getOp() {
        $getCallSiteArray();
        return this.op.get();
    }
    
    public Object getWrappedType1() {
        $getCallSiteArray();
        return this.wrappedType1.get();
    }
    
    public Object getType1() {
        $getCallSiteArray();
        return this.type1.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[17];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genMathModification$_run_closure3_closure7_closure8(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genMathModification$_run_closure3_closure7_closure8.$callSiteArray == null || ($createCallSiteArray = genMathModification$_run_closure3_closure7_closure8.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genMathModification$_run_closure3_closure7_closure8.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
