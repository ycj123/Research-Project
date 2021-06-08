// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.util.List;
import groovy.lang.GroovyObject;

public class LooseExpectation implements GroovyObject
{
    private Demand fDemand;
    private List fCalls;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202917;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$junit$framework$AssertionFailedError;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$LooseExpectation;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$Demand;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public LooseExpectation(final Demand demand) {
        $getCallSiteArray();
        this.fDemand = (Demand)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(null, $get$$class$groovy$mock$interceptor$Demand()), $get$$class$groovy$mock$interceptor$Demand());
        this.fCalls = (List)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$List());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.fDemand = (Demand)ScriptBytecodeAdapter.castToType(demand, $get$$class$groovy$mock$interceptor$Demand());
    }
    
    public Closure match(final String name) {
        final String name2 = (String)new Reference(name);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object filter = $getCallSiteArray[0].call($getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this.fDemand)), new LooseExpectation$_match_closure1(this, this, (Reference<Object>)name2));
        if (DefaultTypeTransformation.booleanUnbox(filter)) {
            return (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this.fDemand), filter), $get$$class$groovy$lang$Closure());
        }
        Object callIndex;
        for (callIndex = LooseExpectation.$const$0; !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callCurrent(this, ((Reference<Object>)name2).get(), callIndex)); callIndex = $getCallSiteArray[6].call(callIndex)) {}
        final CallSite callSite = $getCallSiteArray[7];
        final CallSite callSite2 = $getCallSiteArray[8];
        final CallSite callSite3 = $getCallSiteArray[9];
        final List fCalls = this.fCalls;
        final Object o = callIndex;
        callSite.call(fCalls, o, callSite2.call(callSite3.call(fCalls, o), LooseExpectation.$const$1));
        return (Closure)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].callGetProperty($getCallSiteArray[11].call($getCallSiteArray[12].callGroovyObjectGetProperty(this.fDemand), callIndex)), $get$$class$groovy$lang$Closure());
    }
    
    public boolean isEligible(final String name, final int i) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object calls = $getCallSiteArray[13].callGroovyObjectGetProperty(this.fDemand);
        if (ScriptBytecodeAdapter.compareGreaterThanEqual(DefaultTypeTransformation.box(i), $getCallSiteArray[14].call(calls))) {
            throw (Throwable)$getCallSiteArray[15].callConstructor($get$$class$junit$framework$AssertionFailedError(), new GStringImpl(new Object[] { name }, new String[] { "No more calls to '", "' expected at this point. End of demands." }));
        }
        if (ScriptBytecodeAdapter.compareNotEqual($getCallSiteArray[16].callGetProperty($getCallSiteArray[17].call(calls, DefaultTypeTransformation.box(i))), name)) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
        }
        if (ScriptBytecodeAdapter.compareEqual(null, $getCallSiteArray[18].call(this.fCalls, DefaultTypeTransformation.box(i)))) {
            $getCallSiteArray[19].call(this.fCalls, DefaultTypeTransformation.box(i), LooseExpectation.$const$0);
        }
        if (ScriptBytecodeAdapter.compareGreaterThanEqual($getCallSiteArray[20].call(this.fCalls, DefaultTypeTransformation.box(i)), $getCallSiteArray[21].callGetProperty($getCallSiteArray[22].callGetProperty($getCallSiteArray[23].call(calls, DefaultTypeTransformation.box(i)))))) {
            return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
        }
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    public void verify() {
        $getCallSiteArray()[24].call(this.fDemand, this.fCalls);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$LooseExpectation()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = LooseExpectation.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (LooseExpectation.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        LooseExpectation.__timeStamp__239_neverHappen1292524202917 = 0L;
        LooseExpectation.__timeStamp = 1292524202917L;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public Demand getfDemand() {
        return this.fDemand;
    }
    
    public void setfDemand(final Demand fDemand) {
        this.fDemand = fDemand;
    }
    
    public List getfCalls() {
        return this.fCalls;
    }
    
    public void setfCalls(final List fCalls) {
        this.fCalls = fCalls;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[25];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$LooseExpectation(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (LooseExpectation.$callSiteArray == null || ($createCallSiteArray = LooseExpectation.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            LooseExpectation.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$junit$framework$AssertionFailedError() {
        Class $class$junit$framework$AssertionFailedError;
        if (($class$junit$framework$AssertionFailedError = LooseExpectation.$class$junit$framework$AssertionFailedError) == null) {
            $class$junit$framework$AssertionFailedError = (LooseExpectation.$class$junit$framework$AssertionFailedError = class$("junit.framework.AssertionFailedError"));
        }
        return $class$junit$framework$AssertionFailedError;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$LooseExpectation() {
        Class $class$groovy$mock$interceptor$LooseExpectation;
        if (($class$groovy$mock$interceptor$LooseExpectation = LooseExpectation.$class$groovy$mock$interceptor$LooseExpectation) == null) {
            $class$groovy$mock$interceptor$LooseExpectation = (LooseExpectation.$class$groovy$mock$interceptor$LooseExpectation = class$("groovy.mock.interceptor.LooseExpectation"));
        }
        return $class$groovy$mock$interceptor$LooseExpectation;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = LooseExpectation.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (LooseExpectation.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = LooseExpectation.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (LooseExpectation.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$Demand() {
        Class $class$groovy$mock$interceptor$Demand;
        if (($class$groovy$mock$interceptor$Demand = LooseExpectation.$class$groovy$mock$interceptor$Demand) == null) {
            $class$groovy$mock$interceptor$Demand = (LooseExpectation.$class$groovy$mock$interceptor$Demand = class$("groovy.mock.interceptor.Demand"));
        }
        return $class$groovy$mock$interceptor$Demand;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = LooseExpectation.$class$java$util$List) == null) {
            $class$java$util$List = (LooseExpectation.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = LooseExpectation.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (LooseExpectation.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
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
