// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class OutputTransforms implements GroovyObject
{
    private static Object $localTransforms;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203533;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms;
    private static /* synthetic */ Class $class$groovy$ui$OutputTransforms;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$groovy$lang$GroovyShell;
    
    public OutputTransforms() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static Object loadOutputTransforms() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object transforms = ScriptBytecodeAdapter.createList(new Object[0]);
        final Object userHome = $getCallSiteArray[0].callConstructor($get$$class$java$io$File(), $getCallSiteArray[1].call($get$$class$java$lang$System(), "user.home"));
        final Object groovyDir = $getCallSiteArray[2].callConstructor($get$$class$java$io$File(), userHome, ".groovy");
        final Object userTransforms = $getCallSiteArray[3].callConstructor($get$$class$java$io$File(), groovyDir, "OutputTransforms.groovy");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(userTransforms))) {
            final GroovyShell shell = (GroovyShell)$getCallSiteArray[5].callConstructor($get$$class$groovy$lang$GroovyShell());
            $getCallSiteArray[6].call(shell, "transforms", transforms);
            $getCallSiteArray[7].call(shell, userTransforms);
        }
        $getCallSiteArray[8].call(transforms, new OutputTransforms$_loadOutputTransforms_closure1($get$$class$groovy$ui$OutputTransforms(), $get$$class$groovy$ui$OutputTransforms()));
        $getCallSiteArray[9].call(transforms, new OutputTransforms$_loadOutputTransforms_closure2($get$$class$groovy$ui$OutputTransforms(), $get$$class$groovy$ui$OutputTransforms()));
        $getCallSiteArray[10].call(transforms, new OutputTransforms$_loadOutputTransforms_closure3($get$$class$groovy$ui$OutputTransforms(), $get$$class$groovy$ui$OutputTransforms()));
        $getCallSiteArray[11].call(transforms, new OutputTransforms$_loadOutputTransforms_closure4($get$$class$groovy$ui$OutputTransforms(), $get$$class$groovy$ui$OutputTransforms()));
        $getCallSiteArray[12].call(transforms, new OutputTransforms$_loadOutputTransforms_closure5($get$$class$groovy$ui$OutputTransforms(), $get$$class$groovy$ui$OutputTransforms()));
        return transforms;
    }
    
    public static Object transformResult(final Object base, final Object transforms) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Closure c = null;
        final Object call = $getCallSiteArray[13].call(transforms);
        while (((Iterator)call).hasNext()) {
            c = ((Iterator<Closure>)call).next();
            final Object result = $getCallSiteArray[14].call(c, ScriptBytecodeAdapter.createPojoWrapper(base, $get$$class$java$lang$Object()));
            if (ScriptBytecodeAdapter.compareNotEqual(result, null)) {
                return result;
            }
        }
        return base;
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$ui$OutputTransforms()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = OutputTransforms.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (OutputTransforms.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public static Object getLocalTransforms() {
        return $getCallSiteArray()[15].callGetProperty($get$$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms());
    }
    
    public static Object transformResult(final Object base) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[16].callStatic($get$$class$groovy$ui$OutputTransforms(), ScriptBytecodeAdapter.createPojoWrapper(base, $get$$class$java$lang$Object()), ScriptBytecodeAdapter.createPojoWrapper($getCallSiteArray[17].callGetProperty($get$$class$groovy$ui$OutputTransforms()), $get$$class$java$lang$Object()));
    }
    
    static {
        OutputTransforms.__timeStamp__239_neverHappen1292524203533 = 0L;
        OutputTransforms.__timeStamp = 1292524203533L;
    }
    
    public static Object get$localTransforms() {
        return OutputTransforms.$localTransforms;
    }
    
    public static void set$localTransforms(final Object $localTransforms) {
        OutputTransforms.$localTransforms = $localTransforms;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$OutputTransforms(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (OutputTransforms.$callSiteArray == null || ($createCallSiteArray = OutputTransforms.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            OutputTransforms.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = OutputTransforms.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (OutputTransforms.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = OutputTransforms.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (OutputTransforms.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = OutputTransforms.$class$java$lang$System) == null) {
            $class$java$lang$System = (OutputTransforms.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms() {
        Class $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms;
        if (($class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms = OutputTransforms.$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms) == null) {
            $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms = (OutputTransforms.$class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms = class$("groovy.ui.OutputTransforms$ObjectHolder_localTransforms"));
        }
        return $class$groovy$ui$OutputTransforms$ObjectHolder_localTransforms;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$OutputTransforms() {
        Class $class$groovy$ui$OutputTransforms;
        if (($class$groovy$ui$OutputTransforms = OutputTransforms.$class$groovy$ui$OutputTransforms) == null) {
            $class$groovy$ui$OutputTransforms = (OutputTransforms.$class$groovy$ui$OutputTransforms = class$("groovy.ui.OutputTransforms"));
        }
        return $class$groovy$ui$OutputTransforms;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = OutputTransforms.$class$java$io$File) == null) {
            $class$java$io$File = (OutputTransforms.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyShell() {
        Class $class$groovy$lang$GroovyShell;
        if (($class$groovy$lang$GroovyShell = OutputTransforms.$class$groovy$lang$GroovyShell) == null) {
            $class$groovy$lang$GroovyShell = (OutputTransforms.$class$groovy$lang$GroovyShell = class$("groovy.lang.GroovyShell"));
        }
        return $class$groovy$lang$GroovyShell;
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
