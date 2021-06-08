// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class CliBuilder$_expandArgumentFile_closure3_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> args;
    private Reference<Object> charAsInt;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$io$StreamTokenizer;
    
    public CliBuilder$_expandArgumentFile_closure3_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> args, final Reference<Object> charAsInt) {
        final Reference args2 = new Reference((T)args);
        final Reference charAsInt2 = new Reference((T)charAsInt);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.args = args2.get();
        this.charAsInt = charAsInt2.get();
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this);
        $getCallSiteArray[1].callCurrent(this, $getCallSiteArray[2].call(this.charAsInt.get(), " "), CliBuilder$_expandArgumentFile_closure3_closure4.$const$0);
        $getCallSiteArray[3].callCurrent(this, CliBuilder$_expandArgumentFile_closure3_closure4.$const$1, $getCallSiteArray[4].call(this.charAsInt.get(), " "));
        $getCallSiteArray[5].callCurrent(this, $getCallSiteArray[6].call(this.charAsInt.get(), "#"));
        $getCallSiteArray[7].callCurrent(this, $getCallSiteArray[8].call(this.charAsInt.get(), "\""));
        $getCallSiteArray[9].callCurrent(this, $getCallSiteArray[10].call(this.charAsInt.get(), "'"));
        while (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[11].callCurrent(this), $getCallSiteArray[12].callGetProperty($get$$class$java$io$StreamTokenizer()))) {
            $getCallSiteArray[13].call(this.args.get(), $getCallSiteArray[14].callGroovyObjectGetProperty(this));
        }
        return null;
    }
    
    public Object getArgs() {
        $getCallSiteArray();
        return this.args.get();
    }
    
    public Object getCharAsInt() {
        $getCallSiteArray();
        return this.charAsInt.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[15].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$1 = 0;
        $const$0 = 255;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[16];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$CliBuilder$_expandArgumentFile_closure3_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CliBuilder$_expandArgumentFile_closure3_closure4.$callSiteArray == null || ($createCallSiteArray = CliBuilder$_expandArgumentFile_closure3_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CliBuilder$_expandArgumentFile_closure3_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = CliBuilder$_expandArgumentFile_closure3_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (CliBuilder$_expandArgumentFile_closure3_closure4.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$StreamTokenizer() {
        Class $class$java$io$StreamTokenizer;
        if (($class$java$io$StreamTokenizer = CliBuilder$_expandArgumentFile_closure3_closure4.$class$java$io$StreamTokenizer) == null) {
            $class$java$io$StreamTokenizer = (CliBuilder$_expandArgumentFile_closure3_closure4.$class$java$io$StreamTokenizer = class$("java.io.StreamTokenizer"));
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
