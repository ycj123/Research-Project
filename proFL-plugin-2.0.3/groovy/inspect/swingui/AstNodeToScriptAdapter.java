// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.control.CompilationUnit;
import groovy.lang.GroovyCodeSource;
import org.codehaus.groovy.control.CompilationFailedException;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class AstNodeToScriptAdapter implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202701;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$io$StringWriter;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstNodeToScriptAdapter;
    private static /* synthetic */ Class $class$groovy$lang$GroovyClassLoader;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$java$lang$ClassLoader;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilerConfiguration;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstNodeToScriptVisitor;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilePhase;
    private static /* synthetic */ Class $class$groovy$lang$GroovyCodeSource;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$CompilationUnit;
    
    public AstNodeToScriptAdapter() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public static void main(final String... args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(args) && !ScriptBytecodeAdapter.compareLessThan($getCallSiteArray[0].callGetProperty(args), AstNodeToScriptAdapter.$const$0)) ? Boolean.FALSE : Boolean.TRUE)) {
            $getCallSiteArray[1].callStatic($get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter(), "\nUsage: java groovy.inspect.swingui.AstNodeToScriptAdapter [filename] [compilephase]\nwhere [filename] is a Groovy script\nand [compilephase] is a valid Integer based org.codehaus.groovy.control.CompilePhase");
        }
        else {
            final Object file = $getCallSiteArray[2].callConstructor($get$$class$java$io$File(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType($getCallSiteArray[3].call(args, AstNodeToScriptAdapter.$const$1), $get$$class$java$lang$String()), $get$$class$java$lang$String()));
            final Object phase = $getCallSiteArray[4].call($get$$class$org$codehaus$groovy$control$CompilePhase(), $getCallSiteArray[5].call(args, AstNodeToScriptAdapter.$const$2));
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call(file))) {
                $getCallSiteArray[7].callStatic($get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter(), new GStringImpl(new Object[] { $getCallSiteArray[8].call(args, AstNodeToScriptAdapter.$const$1) }, new String[] { "File ", " cannot be found." }));
            }
            else if (ScriptBytecodeAdapter.compareEqual(phase, null)) {
                $getCallSiteArray[9].callStatic($get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter(), new GStringImpl(new Object[] { $getCallSiteArray[10].call(args, AstNodeToScriptAdapter.$const$2) }, new String[] { "Compile phase ", " cannot be mapped to a org.codehaus.groovy.control.CompilePhase." }));
            }
            else {
                $getCallSiteArray[11].callStatic($get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter(), $getCallSiteArray[12].call($getCallSiteArray[13].callConstructor($get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter()), $getCallSiteArray[14].callGetProperty(file), $getCallSiteArray[15].call(phase)));
            }
        }
    }
    
    public String compileToScript(final String script, final int compilePhase, ClassLoader classLoader, final boolean showScriptFreeForm, final boolean showScriptClass) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object writer = new Reference($getCallSiteArray[16].callConstructor($get$$class$java$io$StringWriter()));
        Object callConstructor;
        if (!DefaultTypeTransformation.booleanUnbox(callConstructor = classLoader)) {
            callConstructor = $getCallSiteArray[17].callConstructor($get$$class$groovy$lang$GroovyClassLoader(), $getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callCurrent(this)));
        }
        classLoader = (ClassLoader)ScriptBytecodeAdapter.castToType(callConstructor, $get$$class$java$lang$ClassLoader());
        final Object scriptName = $getCallSiteArray[20].call($getCallSiteArray[21].call("script", $getCallSiteArray[22].call($get$$class$java$lang$System())), ".groovy");
        final GroovyCodeSource codeSource = (GroovyCodeSource)$getCallSiteArray[23].callConstructor($get$$class$groovy$lang$GroovyCodeSource(), script, scriptName, "/groovy/script");
        final CompilationUnit cu = (CompilationUnit)$getCallSiteArray[24].callConstructor($get$$class$org$codehaus$groovy$control$CompilationUnit(), $getCallSiteArray[25].callGetProperty($get$$class$org$codehaus$groovy$control$CompilerConfiguration()), $getCallSiteArray[26].callGetProperty(codeSource), classLoader);
        $getCallSiteArray[27].call(cu, $getCallSiteArray[28].callConstructor($get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor(), ((Reference<Object>)writer).get(), DefaultTypeTransformation.box(showScriptFreeForm), DefaultTypeTransformation.box(showScriptClass)), DefaultTypeTransformation.box(compilePhase));
        $getCallSiteArray[29].call(cu, $getCallSiteArray[30].call(codeSource), script);
        try {
            $getCallSiteArray[31].call(cu, DefaultTypeTransformation.box(compilePhase));
        }
        catch (CompilationFailedException cfe) {
            $getCallSiteArray[32].call(((Reference<Object>)writer).get(), "Unable to produce AST for this phase due to earlier compilation error:");
            $getCallSiteArray[33].call($getCallSiteArray[34].callGetProperty(cfe), new AstNodeToScriptAdapter$_compileToScript_closure1(this, this, (Reference<Object>)writer));
            $getCallSiteArray[35].call(((Reference<Object>)writer).get(), "Fix the above error(s) and then press Refresh");
        }
        catch (Throwable t) {
            $getCallSiteArray[36].call(((Reference<Object>)writer).get(), "Unable to produce AST for this phase due to an error:");
            $getCallSiteArray[37].call(((Reference<Object>)writer).get(), $getCallSiteArray[38].call(t));
            $getCallSiteArray[39].call(((Reference<Object>)writer).get(), "Fix the above error(s) and then press Refresh");
        }
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[40].call(((Reference<Object>)writer).get()), $get$$class$java$lang$String());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstNodeToScriptAdapter.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstNodeToScriptAdapter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public String compileToScript(final String script, final int compilePhase, final ClassLoader classLoader, final boolean showScriptFreeForm) {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[41].callCurrent(this, ArrayUtil.createArray(ScriptBytecodeAdapter.createPojoWrapper(script, $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(compilePhase), $get$$class$java$lang$Integer()), Integer.TYPE), ScriptBytecodeAdapter.createPojoWrapper(classLoader, $get$$class$java$lang$ClassLoader()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(showScriptFreeForm), $get$$class$java$lang$Boolean()), Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE))), $get$$class$java$lang$String());
    }
    
    public String compileToScript(final String script, final int compilePhase, final ClassLoader classLoader) {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[42].callCurrent(this, ArrayUtil.createArray(ScriptBytecodeAdapter.createPojoWrapper(script, $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(compilePhase), $get$$class$java$lang$Integer()), Integer.TYPE), ScriptBytecodeAdapter.createPojoWrapper(classLoader, $get$$class$java$lang$ClassLoader()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE))), $get$$class$java$lang$String());
    }
    
    public String compileToScript(final String script, final int compilePhase) {
        return (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[43].callCurrent(this, ArrayUtil.createArray(ScriptBytecodeAdapter.createPojoWrapper(script, $get$$class$java$lang$String()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.box(compilePhase), $get$$class$java$lang$Integer()), Integer.TYPE), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.castToType(null, $get$$class$java$lang$ClassLoader()), $get$$class$java$lang$ClassLoader()), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE), ScriptBytecodeAdapter.createPojoWrapper(Boolean.TRUE, Boolean.TYPE))), $get$$class$java$lang$String());
    }
    
    static {
        AstNodeToScriptAdapter.__timeStamp__239_neverHappen1292524202701 = 0L;
        AstNodeToScriptAdapter.__timeStamp = 1292524202701L;
        $const$2 = 1;
        $const$1 = 0;
        $const$0 = 2;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[44];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstNodeToScriptAdapter.$callSiteArray == null || ($createCallSiteArray = AstNodeToScriptAdapter.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstNodeToScriptAdapter.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$StringWriter() {
        Class $class$java$io$StringWriter;
        if (($class$java$io$StringWriter = AstNodeToScriptAdapter.$class$java$io$StringWriter) == null) {
            $class$java$io$StringWriter = (AstNodeToScriptAdapter.$class$java$io$StringWriter = class$("java.io.StringWriter"));
        }
        return $class$java$io$StringWriter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstNodeToScriptAdapter() {
        Class $class$groovy$inspect$swingui$AstNodeToScriptAdapter;
        if (($class$groovy$inspect$swingui$AstNodeToScriptAdapter = AstNodeToScriptAdapter.$class$groovy$inspect$swingui$AstNodeToScriptAdapter) == null) {
            $class$groovy$inspect$swingui$AstNodeToScriptAdapter = (AstNodeToScriptAdapter.$class$groovy$inspect$swingui$AstNodeToScriptAdapter = class$("groovy.inspect.swingui.AstNodeToScriptAdapter"));
        }
        return $class$groovy$inspect$swingui$AstNodeToScriptAdapter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyClassLoader() {
        Class $class$groovy$lang$GroovyClassLoader;
        if (($class$groovy$lang$GroovyClassLoader = AstNodeToScriptAdapter.$class$groovy$lang$GroovyClassLoader) == null) {
            $class$groovy$lang$GroovyClassLoader = (AstNodeToScriptAdapter.$class$groovy$lang$GroovyClassLoader = class$("groovy.lang.GroovyClassLoader"));
        }
        return $class$groovy$lang$GroovyClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = AstNodeToScriptAdapter.$class$java$lang$String) == null) {
            $class$java$lang$String = (AstNodeToScriptAdapter.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = AstNodeToScriptAdapter.$class$java$io$File) == null) {
            $class$java$io$File = (AstNodeToScriptAdapter.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$ClassLoader() {
        Class $class$java$lang$ClassLoader;
        if (($class$java$lang$ClassLoader = AstNodeToScriptAdapter.$class$java$lang$ClassLoader) == null) {
            $class$java$lang$ClassLoader = (AstNodeToScriptAdapter.$class$java$lang$ClassLoader = class$("java.lang.ClassLoader"));
        }
        return $class$java$lang$ClassLoader;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstNodeToScriptAdapter.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstNodeToScriptAdapter.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstNodeToScriptAdapter.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstNodeToScriptAdapter.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilerConfiguration() {
        Class $class$org$codehaus$groovy$control$CompilerConfiguration;
        if (($class$org$codehaus$groovy$control$CompilerConfiguration = AstNodeToScriptAdapter.$class$org$codehaus$groovy$control$CompilerConfiguration) == null) {
            $class$org$codehaus$groovy$control$CompilerConfiguration = (AstNodeToScriptAdapter.$class$org$codehaus$groovy$control$CompilerConfiguration = class$("org.codehaus.groovy.control.CompilerConfiguration"));
        }
        return $class$org$codehaus$groovy$control$CompilerConfiguration;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstNodeToScriptVisitor() {
        Class $class$groovy$inspect$swingui$AstNodeToScriptVisitor;
        if (($class$groovy$inspect$swingui$AstNodeToScriptVisitor = AstNodeToScriptAdapter.$class$groovy$inspect$swingui$AstNodeToScriptVisitor) == null) {
            $class$groovy$inspect$swingui$AstNodeToScriptVisitor = (AstNodeToScriptAdapter.$class$groovy$inspect$swingui$AstNodeToScriptVisitor = class$("groovy.inspect.swingui.AstNodeToScriptVisitor"));
        }
        return $class$groovy$inspect$swingui$AstNodeToScriptVisitor;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = AstNodeToScriptAdapter.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (AstNodeToScriptAdapter.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = AstNodeToScriptAdapter.$class$java$lang$System) == null) {
            $class$java$lang$System = (AstNodeToScriptAdapter.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilePhase() {
        Class $class$org$codehaus$groovy$control$CompilePhase;
        if (($class$org$codehaus$groovy$control$CompilePhase = AstNodeToScriptAdapter.$class$org$codehaus$groovy$control$CompilePhase) == null) {
            $class$org$codehaus$groovy$control$CompilePhase = (AstNodeToScriptAdapter.$class$org$codehaus$groovy$control$CompilePhase = class$("org.codehaus.groovy.control.CompilePhase"));
        }
        return $class$org$codehaus$groovy$control$CompilePhase;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyCodeSource() {
        Class $class$groovy$lang$GroovyCodeSource;
        if (($class$groovy$lang$GroovyCodeSource = AstNodeToScriptAdapter.$class$groovy$lang$GroovyCodeSource) == null) {
            $class$groovy$lang$GroovyCodeSource = (AstNodeToScriptAdapter.$class$groovy$lang$GroovyCodeSource = class$("groovy.lang.GroovyCodeSource"));
        }
        return $class$groovy$lang$GroovyCodeSource;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$CompilationUnit() {
        Class $class$org$codehaus$groovy$control$CompilationUnit;
        if (($class$org$codehaus$groovy$control$CompilationUnit = AstNodeToScriptAdapter.$class$org$codehaus$groovy$control$CompilationUnit) == null) {
            $class$org$codehaus$groovy$control$CompilationUnit = (AstNodeToScriptAdapter.$class$org$codehaus$groovy$control$CompilationUnit = class$("org.codehaus.groovy.control.CompilationUnit"));
        }
        return $class$org$codehaus$groovy$control$CompilationUnit;
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
