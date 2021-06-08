// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.IntRange;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.Map;
import java.util.List;
import groovy.lang.GroovyObject;

public class Demand implements GroovyObject
{
    private List recorded;
    private Map ignore;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202908;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$junit$framework$AssertionFailedError;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$CallSpec;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$Demand;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$util$Map;
    
    public Demand() {
        $getCallSiteArray();
        this.recorded = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.ignore = (Map)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createMap(new Object[0]), $get$$class$java$util$Map());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object invokeMethod(final String methodName, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object range = ScriptBytecodeAdapter.createRange(Demand.$const$0, Demand.$const$0, true);
        if ($getCallSiteArray[0].call(args, Demand.$const$1) instanceof IntRange) {
            range = $getCallSiteArray[1].call(args, Demand.$const$1);
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].callGetProperty(range))) {
                throw (Throwable)$getCallSiteArray[3].callConstructor($get$$class$java$lang$IllegalArgumentException(), "Reverse ranges not supported.");
            }
        }
        else if ($getCallSiteArray[4].call(args, Demand.$const$1) instanceof Integer) {
            range = ScriptBytecodeAdapter.createRange($getCallSiteArray[5].call(args, Demand.$const$1), $getCallSiteArray[6].call(args, Demand.$const$1), true);
        }
        if ($getCallSiteArray[7].call(args, Demand.$const$2) instanceof Closure) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[8].call(this.recorded, $getCallSiteArray[9].callConstructor($get$$class$groovy$mock$interceptor$CallSpec(), ScriptBytecodeAdapter.createMap(new Object[] { "name", methodName, "behavior", $getCallSiteArray[10].call(args, Demand.$const$2), "range", range }))), $get$$class$java$lang$Object());
        }
        return ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$Object());
    }
    
    public Object verify(final List calls) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object i = null;
        final Object call2 = $getCallSiteArray[11].call(ScriptBytecodeAdapter.createRange(Demand.$const$1, $getCallSiteArray[12].call(this.recorded), false));
        while (((Iterator)call2).hasNext()) {
            i = ((Iterator<Object>)call2).next();
            final Object call = $getCallSiteArray[13].call(this.recorded, i);
            final Object callCounter = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[14].call(calls, i)) ? $getCallSiteArray[15].call(calls, i) : Demand.$const$1;
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[16].call($getCallSiteArray[17].callGetProperty(call), callCounter))) {
                final Object msg = new GStringImpl(new Object[] { i, $getCallSiteArray[18].call($getCallSiteArray[19].callGetProperty(call)), $getCallSiteArray[20].callGetProperty(call) }, new String[] { "verify[", "]: expected ", " call(s) to '", "' but was " });
                throw (Throwable)$getCallSiteArray[21].callConstructor($get$$class$junit$framework$AssertionFailedError(), $getCallSiteArray[22].call(msg, new GStringImpl(new Object[] { callCounter }, new String[] { "called ", " time(s)." })));
            }
        }
        return null;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$Demand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Demand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Demand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Demand.__timeStamp__239_neverHappen1292524202908 = 0L;
        Demand.__timeStamp = 1292524202908L;
        $const$2 = -1;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    public List getRecorded() {
        return this.recorded;
    }
    
    public void setRecorded(final List recorded) {
        this.recorded = recorded;
    }
    
    public Map getIgnore() {
        return this.ignore;
    }
    
    public void setIgnore(final Map ignore) {
        this.ignore = ignore;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[23];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$Demand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Demand.$callSiteArray == null || ($createCallSiteArray = Demand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Demand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$junit$framework$AssertionFailedError() {
        Class $class$junit$framework$AssertionFailedError;
        if (($class$junit$framework$AssertionFailedError = Demand.$class$junit$framework$AssertionFailedError) == null) {
            $class$junit$framework$AssertionFailedError = (Demand.$class$junit$framework$AssertionFailedError = class$("junit.framework.AssertionFailedError"));
        }
        return $class$junit$framework$AssertionFailedError;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$CallSpec() {
        Class $class$groovy$mock$interceptor$CallSpec;
        if (($class$groovy$mock$interceptor$CallSpec = Demand.$class$groovy$mock$interceptor$CallSpec) == null) {
            $class$groovy$mock$interceptor$CallSpec = (Demand.$class$groovy$mock$interceptor$CallSpec = class$("groovy.mock.interceptor.CallSpec"));
        }
        return $class$groovy$mock$interceptor$CallSpec;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Demand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Demand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Demand.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Demand.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$Demand() {
        Class $class$groovy$mock$interceptor$Demand;
        if (($class$groovy$mock$interceptor$Demand = Demand.$class$groovy$mock$interceptor$Demand) == null) {
            $class$groovy$mock$interceptor$Demand = (Demand.$class$groovy$mock$interceptor$Demand = class$("groovy.mock.interceptor.Demand"));
        }
        return $class$groovy$mock$interceptor$Demand;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = Demand.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (Demand.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = Demand.$class$java$util$List) == null) {
            $class$java$util$List = (Demand.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = Demand.$class$java$util$Map) == null) {
            $class$java$util$Map = (Demand.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
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
