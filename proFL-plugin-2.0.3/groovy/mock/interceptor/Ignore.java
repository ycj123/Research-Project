// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class Ignore implements GroovyObject
{
    private Object parent;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202914;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$Ignore;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$IllegalArgumentException;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public Ignore() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object invokeMethod(final String methodName, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(args) && ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[0].call(args), Ignore.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) {
            throw (Throwable)$getCallSiteArray[1].callConstructor($get$$class$java$lang$IllegalArgumentException(), new GStringImpl(new Object[] { methodName }, new String[] { "Ranges/repetitions not supported for ignored method '", "'." }));
        }
        if (!DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(args) && ScriptBytecodeAdapter.compareEqual($getCallSiteArray[2].call(args), Ignore.$const$0)) ? Boolean.TRUE : Boolean.FALSE)) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(this.parent, methodName, null), $get$$class$java$lang$Object());
        }
        if ($getCallSiteArray[3].call(args, Ignore.$const$1) instanceof Closure) {
            return ScriptBytecodeAdapter.castToType($getCallSiteArray[4].call(this.parent, methodName, ScriptBytecodeAdapter.createGroovyObjectWrapper((GroovyObject)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].call(args, Ignore.$const$1), $get$$class$groovy$lang$Closure()), $get$$class$groovy$lang$Closure())), $get$$class$java$lang$Object());
        }
        throw (Throwable)$getCallSiteArray[6].callConstructor($get$$class$java$lang$IllegalArgumentException(), new GStringImpl(new Object[] { methodName, $getCallSiteArray[7].callGetProperty($getCallSiteArray[8].call($getCallSiteArray[9].call(args, Ignore.$const$1))) }, new String[] { "Optional parameter to ignored method '", "' must be a Closure but instead found a ", "." }));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$Ignore()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Ignore.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Ignore.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Ignore.__timeStamp__239_neverHappen1292524202914 = 0L;
        Ignore.__timeStamp = 1292524202914L;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    public Object getParent() {
        return this.parent;
    }
    
    public void setParent(final Object parent) {
        this.parent = parent;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[11];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$mock$interceptor$Ignore(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Ignore.$callSiteArray == null || ($createCallSiteArray = Ignore.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Ignore.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Ignore.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Ignore.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$Ignore() {
        Class $class$groovy$mock$interceptor$Ignore;
        if (($class$groovy$mock$interceptor$Ignore = Ignore.$class$groovy$mock$interceptor$Ignore) == null) {
            $class$groovy$mock$interceptor$Ignore = (Ignore.$class$groovy$mock$interceptor$Ignore = class$("groovy.mock.interceptor.Ignore"));
        }
        return $class$groovy$mock$interceptor$Ignore;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = Ignore.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (Ignore.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$IllegalArgumentException() {
        Class $class$java$lang$IllegalArgumentException;
        if (($class$java$lang$IllegalArgumentException = Ignore.$class$java$lang$IllegalArgumentException) == null) {
            $class$java$lang$IllegalArgumentException = (Ignore.$class$java$lang$IllegalArgumentException = class$("java.lang.IllegalArgumentException"));
        }
        return $class$java$lang$IllegalArgumentException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = Ignore.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (Ignore.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
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
