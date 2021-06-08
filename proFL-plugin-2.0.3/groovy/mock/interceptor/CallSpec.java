// 
// Decompiled by Procyon v0.5.36
// 

package groovy.mock.interceptor;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Range;
import groovy.lang.Closure;
import groovy.lang.GroovyObject;

public class CallSpec implements GroovyObject
{
    private String name;
    private Closure behavior;
    private Range range;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202912;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$mock$interceptor$CallSpec;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    
    public CallSpec() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$mock$interceptor$CallSpec()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CallSpec.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CallSpec.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        CallSpec.__timeStamp__239_neverHappen1292524202912 = 0L;
        CallSpec.__timeStamp = 1292524202912L;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Closure getBehavior() {
        return this.behavior;
    }
    
    public void setBehavior(final Closure behavior) {
        this.behavior = behavior;
    }
    
    public Range getRange() {
        return this.range;
    }
    
    public void setRange(final Range range) {
        this.range = range;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$groovy$mock$interceptor$CallSpec(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CallSpec.$callSiteArray == null || ($createCallSiteArray = CallSpec.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CallSpec.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$mock$interceptor$CallSpec() {
        Class $class$groovy$mock$interceptor$CallSpec;
        if (($class$groovy$mock$interceptor$CallSpec = CallSpec.$class$groovy$mock$interceptor$CallSpec) == null) {
            $class$groovy$mock$interceptor$CallSpec = (CallSpec.$class$groovy$mock$interceptor$CallSpec = class$("groovy.mock.interceptor.CallSpec"));
        }
        return $class$groovy$mock$interceptor$CallSpec;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CallSpec.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CallSpec.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
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
