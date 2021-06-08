// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import groovy.lang.GroovyObject;

public class StrictExpectation implements GroovyObject
{
    private Demand fDemand;
    private int fCallSpecIdx;
    private List fCalls;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202973;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$StrictExpectation;
    private static /* synthetic */ Class $class$junit$framework$AssertionFailedError;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$Demand;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public StrictExpectation(final Demand demand) {
        $getCallSiteArray();
        this.fDemand = (Demand)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$mock$interceptor$Demand()), $get$$class$groovy$mock$interceptor$Demand());
        this.fCallSpecIdx = DefaultTypeTransformation.intUnbox(StrictExpectation.$const$0);
        this.fCalls = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.fDemand = (Demand)ScriptBytecodeAdapter.castToType(demand, $get$$class$groovy$mock$interceptor$Demand());
    }
    
    public Closure match(final String name) {
        final String name2 = (String)new Reference(name);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object filter = $getCallSiteArray[0].call($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this.fDemand)), new StrictExpectation$_match_closure1(this, this, (Reference<Object>)name2));
        if (DefaultTypeTransformation.booleanUnbox(filter)) {
            return (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this.fDemand), filter), $get$$class$groovy$lang$Closure());
        }
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(this.fCalls, DefaultTypeTransformation.box(this.fCallSpecIdx)))) {
            $getCallSiteArray[6].call(this.fCalls, DefaultTypeTransformation.box(this.fCallSpecIdx), StrictExpectation.$const$0);
        }
        if (ScriptBytecodeAdapter.compareGreaterThanEqual(DefaultTypeTransformation.box(this.fCallSpecIdx), $getCallSiteArray[7].call($getCallSiteArray[8].callGroovyObjectGetProperty(this.fDemand)))) {
            throw (Throwable)$getCallSiteArray[9].callConstructor($get$$class$junit$framework$AssertionFailedError(), new GStringImpl(new Object[] { ((Reference<Object>)name2).get() }, new String[] { "No more calls to '", "' expected at this point. End of demands." }));
        }
        final Object call = $getCallSiteArray[10].call($getCallSiteArray[11].callGroovyObjectGetProperty(this.fDemand), DefaultTypeTransformation.box(this.fCallSpecIdx));
        if (!ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)name2).get(), $getCallSiteArray[12].callGetProperty(call))) {
            final CallSite callSite = $getCallSiteArray[22];
            final CallSite callSite2 = $getCallSiteArray[23];
            final CallSite callSite3 = $getCallSiteArray[24];
            final List fCalls = this.fCalls;
            final Object box = DefaultTypeTransformation.box(this.fCallSpecIdx);
            callSite.call(fCalls, box, callSite2.call(callSite3.call(fCalls, box), StrictExpectation.$const$1));
            final Object result = $getCallSiteArray[25].callGetProperty(call);
            if (ScriptBytecodeAdapter.compareGreaterThanEqual($getCallSiteArray[26].call(this.fCalls, DefaultTypeTransformation.box(this.fCallSpecIdx)), $getCallSiteArray[27].callGetProperty($getCallSiteArray[28].callGetProperty(call)))) {
                DefaultTypeTransformation.box(this.fCallSpecIdx);
                this.fCallSpecIdx = DefaultTypeTransformation.intUnbox($getCallSiteArray[29].call(DefaultTypeTransformation.box(this.fCallSpecIdx)));
            }
            return (Closure)ScriptBytecodeAdapter.castToType(result, $get$$class$groovy$lang$Closure());
        }
        final Object open = $getCallSiteArray[13].call($getCallSiteArray[14].callGetProperty($getCallSiteArray[15].callGetProperty(call)), $getCallSiteArray[16].call(this.fCalls, DefaultTypeTransformation.box(this.fCallSpecIdx)));
        if (ScriptBytecodeAdapter.compareGreaterThan(open, StrictExpectation.$const$0)) {
            throw (Throwable)$getCallSiteArray[17].callConstructor($get$$class$junit$framework$AssertionFailedError(), $getCallSiteArray[18].call(new GStringImpl(new Object[] { ((Reference<Object>)name2).get() }, new String[] { "No call to '", "' expected at this point. " }), new GStringImpl(new Object[] { open, $getCallSiteArray[19].callGetProperty(call) }, new String[] { "Still ", " call(s) to '", "' expected." })));
        }
        DefaultTypeTransformation.box(this.fCallSpecIdx);
        this.fCallSpecIdx = DefaultTypeTransformation.intUnbox($getCallSiteArray[20].call(DefaultTypeTransformation.box(this.fCallSpecIdx)));
        return (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[21].callCurrent(this, ((Reference<Object>)name2).get()), $get$$class$groovy$lang$Closure());
    }
    
    public void verify() {
        $getCallSiteArray()[30].call(this.fDemand, this.fCalls);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$StrictExpectation()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = StrictExpectation.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (StrictExpectation.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        StrictExpectation.__timeStamp__239_neverHappen1292524202973 = 0L;
        StrictExpectation.__timeStamp = 1292524202973L;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public Demand getfDemand() {
        return this.fDemand;
    }
    
    public void setfDemand(final Demand fDemand) {
        this.fDemand = fDemand;
    }
    
    public int getfCallSpecIdx() {
        return this.fCallSpecIdx;
    }
    
    public void setfCallSpecIdx(final int fCallSpecIdx) {
        this.fCallSpecIdx = fCallSpecIdx;
    }
    
    public List getfCalls() {
        return this.fCalls;
    }
    
    public void setfCalls(final List fCalls) {
        this.fCalls = fCalls;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[31];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$StrictExpectation(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StrictExpectation.$callSiteArray == null || ($createCallSiteArray = StrictExpectation.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StrictExpectation.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$StrictExpectation() {
        Class $class$groovy$mock$interceptor$StrictExpectation;
        if (($class$groovy$mock$interceptor$StrictExpectation = StrictExpectation.$class$groovy$mock$interceptor$StrictExpectation) == null) {
            $class$groovy$mock$interceptor$StrictExpectation = (StrictExpectation.$class$groovy$mock$interceptor$StrictExpectation = class$("groovy.mock.interceptor.StrictExpectation"));
        }
        return $class$groovy$mock$interceptor$StrictExpectation;
    }
    
    private static /* synthetic */ Class $get$$class$junit$framework$AssertionFailedError() {
        Class $class$junit$framework$AssertionFailedError;
        if (($class$junit$framework$AssertionFailedError = StrictExpectation.$class$junit$framework$AssertionFailedError) == null) {
            $class$junit$framework$AssertionFailedError = (StrictExpectation.$class$junit$framework$AssertionFailedError = class$("junit.framework.AssertionFailedError"));
        }
        return $class$junit$framework$AssertionFailedError;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = StrictExpectation.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (StrictExpectation.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$Demand() {
        Class $class$groovy$mock$interceptor$Demand;
        if (($class$groovy$mock$interceptor$Demand = StrictExpectation.$class$groovy$mock$interceptor$Demand) == null) {
            $class$groovy$mock$interceptor$Demand = (StrictExpectation.$class$groovy$mock$interceptor$Demand = class$("groovy.mock.interceptor.Demand"));
        }
        return $class$groovy$mock$interceptor$Demand;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = StrictExpectation.$class$java$util$List) == null) {
            $class$java$util$List = (StrictExpectation.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = StrictExpectation.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (StrictExpectation.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
        }
        return $class$groovy$lang$Closure;
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
