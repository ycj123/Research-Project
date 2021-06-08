// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class CliBuilder$_expandArgumentFile_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> args;
    private Reference<Object> charAsInt;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$io$StreamTokenizer;
    
    public CliBuilder$_expandArgumentFile_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> args, final Reference<Object> charAsInt) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.args = args;
        this.charAsInt = charAsInt;
    }
    
    public Object doCall(final Object r) {
        final Object r2 = new Reference(r);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[0];
        final Object callConstructor = $getCallSiteArray[1].callConstructor($get$$class$java$io$StreamTokenizer(), ((Reference<Object>)r2).get());
        final Object thisObject = this.getThisObject();
        final Reference<Object> args2;
        final Reference args = args2 = this.args;
        final Reference charAsInt = this.charAsInt;
        return callSite.call(callConstructor, new CliBuilder$_expandArgumentFile_closure3_closure4(this, thisObject, args2, charAsInt));
    }
    
    public Object getArgs() {
        $getCallSiteArray();
        return this.args.get();
    }
    
    public Object getCharAsInt() {
        $getCallSiteArray();
        return this.charAsInt.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$CliBuilder$_expandArgumentFile_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CliBuilder$_expandArgumentFile_closure3.$callSiteArray == null || ($createCallSiteArray = CliBuilder$_expandArgumentFile_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CliBuilder$_expandArgumentFile_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$StreamTokenizer() {
        Class $class$java$io$StreamTokenizer;
        if (($class$java$io$StreamTokenizer = CliBuilder$_expandArgumentFile_closure3.$class$java$io$StreamTokenizer) == null) {
            $class$java$io$StreamTokenizer = (CliBuilder$_expandArgumentFile_closure3.$class$java$io$StreamTokenizer = class$("java.io.StreamTokenizer"));
        }
        return $class$java$io$StreamTokenizer;
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
