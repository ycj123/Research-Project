// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import junit.framework.TestResult;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.logging.Handler;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.util.GroovyTestCase;

public class GroovyLogTestCase extends GroovyTestCase implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205605;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$GroovyLogTestCase;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$util$logging$SimpleFormatter;
    private static /* synthetic */ Class $class$java$util$logging$StreamHandler;
    private static /* synthetic */ Class $class$java$util$logging$Logger;
    private static /* synthetic */ Class $class$java$io$ByteArrayOutputStream;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$util$logging$Level;
    
    public GroovyLogTestCase() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static String stringLog(final Level level, final String qualifier, final Closure yield) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Logger logger = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$java$util$logging$Logger(), qualifier), $get$$class$java$util$logging$Logger());
        final Object usesParentHandlers = $getCallSiteArray[1].callGetProperty(logger);
        ScriptBytecodeAdapter.setProperty(Boolean.FALSE, $get$$class$groovy$lang$GroovyLogTestCase(), logger, "useParentHandlers");
        final Object out = $getCallSiteArray[2].callConstructor($get$$class$java$io$ByteArrayOutputStream(), GroovyLogTestCase.$const$0);
        final Handler stringHandler = (Handler)$getCallSiteArray[3].callConstructor($get$$class$java$util$logging$StreamHandler(), out, $getCallSiteArray[4].callConstructor($get$$class$java$util$logging$SimpleFormatter()));
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[5].callGetProperty($get$$class$java$util$logging$Level()), $get$$class$groovy$lang$GroovyLogTestCase(), stringHandler, "level");
        $getCallSiteArray[6].call(logger, stringHandler);
        $getCallSiteArray[7].callStatic($get$$class$groovy$lang$GroovyLogTestCase(), level, qualifier, yield);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[8].callGetProperty($get$$class$java$util$logging$Level()), $get$$class$groovy$lang$GroovyLogTestCase(), logger, "level");
        $getCallSiteArray[9].call(stringHandler);
        $getCallSiteArray[10].call(out);
        $getCallSiteArray[11].call(logger, stringHandler);
        ScriptBytecodeAdapter.setProperty(usesParentHandlers, $get$$class$groovy$lang$GroovyLogTestCase(), logger, "useParentHandlers");
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call(out), $get$$class$java$lang$String());
    }
    
    public static Object withLevel(final Level level, final String qualifier, final Closure yield) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Logger logger = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray[13].call($get$$class$java$util$logging$Logger(), qualifier), $get$$class$java$util$logging$Logger());
        final Object loglevel = $getCallSiteArray[14].callGetProperty(logger);
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[15].call(logger, level))) {
            ScriptBytecodeAdapter.setProperty(level, $get$$class$groovy$lang$GroovyLogTestCase(), logger, "level");
        }
        final Object result = $getCallSiteArray[16].call(yield);
        ScriptBytecodeAdapter.setProperty(loglevel, $get$$class$groovy$lang$GroovyLogTestCase(), logger, "level");
        return result;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$lang$GroovyLogTestCase()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = GroovyLogTestCase.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (GroovyLogTestCase.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        GroovyLogTestCase.__timeStamp__239_neverHappen1292524205605 = 0L;
        GroovyLogTestCase.__timeStamp = 1292524205605L;
        $const$0 = 1024;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[17];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$lang$GroovyLogTestCase(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GroovyLogTestCase.$callSiteArray == null || ($createCallSiteArray = GroovyLogTestCase.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GroovyLogTestCase.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyLogTestCase() {
        Class $class$groovy$lang$GroovyLogTestCase;
        if (($class$groovy$lang$GroovyLogTestCase = GroovyLogTestCase.$class$groovy$lang$GroovyLogTestCase) == null) {
            $class$groovy$lang$GroovyLogTestCase = (GroovyLogTestCase.$class$groovy$lang$GroovyLogTestCase = class$("groovy.lang.GroovyLogTestCase"));
        }
        return $class$groovy$lang$GroovyLogTestCase;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = GroovyLogTestCase.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (GroovyLogTestCase.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$SimpleFormatter() {
        Class $class$java$util$logging$SimpleFormatter;
        if (($class$java$util$logging$SimpleFormatter = GroovyLogTestCase.$class$java$util$logging$SimpleFormatter) == null) {
            $class$java$util$logging$SimpleFormatter = (GroovyLogTestCase.$class$java$util$logging$SimpleFormatter = class$("java.util.logging.SimpleFormatter"));
        }
        return $class$java$util$logging$SimpleFormatter;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$StreamHandler() {
        Class $class$java$util$logging$StreamHandler;
        if (($class$java$util$logging$StreamHandler = GroovyLogTestCase.$class$java$util$logging$StreamHandler) == null) {
            $class$java$util$logging$StreamHandler = (GroovyLogTestCase.$class$java$util$logging$StreamHandler = class$("java.util.logging.StreamHandler"));
        }
        return $class$java$util$logging$StreamHandler;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Logger() {
        Class $class$java$util$logging$Logger;
        if (($class$java$util$logging$Logger = GroovyLogTestCase.$class$java$util$logging$Logger) == null) {
            $class$java$util$logging$Logger = (GroovyLogTestCase.$class$java$util$logging$Logger = class$("java.util.logging.Logger"));
        }
        return $class$java$util$logging$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$ByteArrayOutputStream() {
        Class $class$java$io$ByteArrayOutputStream;
        if (($class$java$io$ByteArrayOutputStream = GroovyLogTestCase.$class$java$io$ByteArrayOutputStream) == null) {
            $class$java$io$ByteArrayOutputStream = (GroovyLogTestCase.$class$java$io$ByteArrayOutputStream = class$("java.io.ByteArrayOutputStream"));
        }
        return $class$java$io$ByteArrayOutputStream;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GroovyLogTestCase.$class$java$lang$String) == null) {
            $class$java$lang$String = (GroovyLogTestCase.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Level() {
        Class $class$java$util$logging$Level;
        if (($class$java$util$logging$Level = GroovyLogTestCase.$class$java$util$logging$Level) == null) {
            $class$java$util$logging$Level = (GroovyLogTestCase.$class$java$util$logging$Level = class$("java.util.logging.Level"));
        }
        return $class$java$util$logging$Level;
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
