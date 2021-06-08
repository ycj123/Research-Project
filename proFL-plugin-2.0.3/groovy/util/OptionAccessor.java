// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.List;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.mudebug.prapr.reloc.commons.cli.CommandLine;
import groovy.lang.GroovyObject;

public class OptionAccessor implements GroovyObject
{
    private CommandLine inner;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203579;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$commons$cli$CommandLine;
    private static /* synthetic */ Class $class$groovy$util$OptionAccessor;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Character;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public OptionAccessor(final CommandLine inner) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.inner = (CommandLine)ScriptBytecodeAdapter.castToType(inner, $get$$class$org$apache$commons$cli$CommandLine());
    }
    
    public Object invokeMethod(final String name, final Object args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), this.inner), this.inner, name, args);
    }
    
    public Object getProperty(String name) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object methodname = "getOptionValue";
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareGreaterThan($getCallSiteArray[2].call(name), OptionAccessor.$const$0) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(name, "s"))) ? Boolean.TRUE : Boolean.FALSE)) {
            final Object singularName = $getCallSiteArray[4].call(name, ScriptBytecodeAdapter.createRange(OptionAccessor.$const$1, OptionAccessor.$const$2, true));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callCurrent(this, singularName))) {
                name = (String)ScriptBytecodeAdapter.castToType(singularName, $get$$class$java$lang$String());
                methodname = $getCallSiteArray[6].call(methodname, "s");
            }
        }
        if (ScriptBytecodeAdapter.compareEqual($getCallSiteArray[7].call(name), OptionAccessor.$const$0)) {
            name = (String)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.asType(name, $get$$class$java$lang$Character()), $get$$class$java$lang$String());
        }
        Object result = $getCallSiteArray[8].call($getCallSiteArray[9].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), this.inner), this.inner, methodname, name);
        if (ScriptBytecodeAdapter.compareEqual(null, result)) {
            result = $getCallSiteArray[10].call(this.inner, name);
        }
        if (result instanceof String[]) {
            result = $getCallSiteArray[11].call(result);
        }
        return result;
    }
    
    public List<String> arguments() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return (List<String>)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].callGetProperty(this.inner)), $get$$class$java$util$List());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$util$OptionAccessor()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = OptionAccessor.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (OptionAccessor.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        OptionAccessor.__timeStamp__239_neverHappen1292524203579 = 0L;
        OptionAccessor.__timeStamp = 1292524203579L;
        $const$2 = -2;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    public CommandLine getInner() {
        return this.inner;
    }
    
    public void setInner(final CommandLine inner) {
        this.inner = inner;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[14];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$OptionAccessor(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (OptionAccessor.$callSiteArray == null || ($createCallSiteArray = OptionAccessor.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            OptionAccessor.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$commons$cli$CommandLine() {
        Class $class$org$apache$commons$cli$CommandLine;
        if (($class$org$apache$commons$cli$CommandLine = OptionAccessor.$class$org$apache$commons$cli$CommandLine) == null) {
            $class$org$apache$commons$cli$CommandLine = (OptionAccessor.$class$org$apache$commons$cli$CommandLine = class$("org.mudebug.prapr.reloc.commons.cli.CommandLine"));
        }
        return $class$org$apache$commons$cli$CommandLine;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$OptionAccessor() {
        Class $class$groovy$util$OptionAccessor;
        if (($class$groovy$util$OptionAccessor = OptionAccessor.$class$groovy$util$OptionAccessor) == null) {
            $class$groovy$util$OptionAccessor = (OptionAccessor.$class$groovy$util$OptionAccessor = class$("groovy.util.OptionAccessor"));
        }
        return $class$groovy$util$OptionAccessor;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = OptionAccessor.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (OptionAccessor.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Character() {
        Class $class$java$lang$Character;
        if (($class$java$lang$Character = OptionAccessor.$class$java$lang$Character) == null) {
            $class$java$lang$Character = (OptionAccessor.$class$java$lang$Character = class$("java.lang.Character"));
        }
        return $class$java$lang$Character;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = OptionAccessor.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (OptionAccessor.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = OptionAccessor.$class$java$util$List) == null) {
            $class$java$util$List = (OptionAccessor.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = OptionAccessor.$class$java$lang$String) == null) {
            $class$java$lang$String = (OptionAccessor.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
