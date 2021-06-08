// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public enum CompilePhaseAdapter implements GroovyObject
{
    public static final CompilePhaseAdapter INITIALIZATION;
    public static final CompilePhaseAdapter PARSING;
    public static final CompilePhaseAdapter CONVERSION;
    public static final CompilePhaseAdapter SEMANTIC_ANALYSIS;
    public static final CompilePhaseAdapter CANONICALIZATION;
    public static final CompilePhaseAdapter INSTRUCTION_SELECTION;
    public static final CompilePhaseAdapter CLASS_GENERATION;
    public static final CompilePhaseAdapter OUTPUT;
    public static final CompilePhaseAdapter FINALIZATION;
    private final int phaseId;
    private final String string;
    public static final CompilePhaseAdapter MIN_VALUE;
    public static final CompilePhaseAdapter MAX_VALUE;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204108;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$CompilePhaseAdapter;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Enum;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$Phases;
    
    public CompilePhaseAdapter(final String __str, final int __int, final Object phaseId, final Object string) {
        $getCallSiteArray();
        Object[] array;
        final Object[] arguments = array = (array2 = new Object[2]);
        arguments[0] = __str;
        arguments[1] = DefaultTypeTransformation.box(__int);
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$java$lang$Enum());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (Object[])arguments[0]);
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((String)array[0], DefaultTypeTransformation.intUnbox(array2[1]));
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
                this.phaseId = DefaultTypeTransformation.intUnbox(phaseId);
                this.string = (String)ScriptBytecodeAdapter.castToType(string, $get$$class$java$lang$String());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    @Override
    public String toString() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.string, $get$$class$java$lang$String());
    }
    
    static {
        CompilePhaseAdapter.__timeStamp__239_neverHappen1292524204108 = 0L;
        CompilePhaseAdapter.__timeStamp = 1292524204108L;
        $const$8 = 8;
        $const$7 = 7;
        $const$6 = 6;
        $const$5 = 5;
        $const$4 = 4;
        $const$3 = 3;
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
        INITIALIZATION = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[11].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "INITIALIZATION", 0, $getCallSiteArray()[12].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Initialization"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        PARSING = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[13].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "PARSING", 1, $getCallSiteArray()[14].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Parsing"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        CONVERSION = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[15].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "CONVERSION", 2, $getCallSiteArray()[16].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Conversion"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        SEMANTIC_ANALYSIS = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[17].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "SEMANTIC_ANALYSIS", 3, $getCallSiteArray()[18].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Semantic Analysis"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        CANONICALIZATION = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[19].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "CANONICALIZATION", 4, $getCallSiteArray()[20].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Canonicalization"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        INSTRUCTION_SELECTION = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[21].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "INSTRUCTION_SELECTION", 5, $getCallSiteArray()[22].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Instruction Selection"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        CLASS_GENERATION = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[23].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "CLASS_GENERATION", 6, $getCallSiteArray()[24].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Class Generation"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        OUTPUT = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[25].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "OUTPUT", 7, $getCallSiteArray()[26].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Output"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        FINALIZATION = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType($getCallSiteArray()[27].callStatic($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), "FINALIZATION", 8, $getCallSiteArray()[28].callGetProperty($get$$class$org$codehaus$groovy$control$Phases()), "Finalization"), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
        MIN_VALUE = CompilePhaseAdapter.INITIALIZATION;
        MAX_VALUE = CompilePhaseAdapter.FINALIZATION;
        $VALUES = new CompilePhaseAdapter[] { CompilePhaseAdapter.INITIALIZATION, CompilePhaseAdapter.PARSING, CompilePhaseAdapter.CONVERSION, CompilePhaseAdapter.SEMANTIC_ANALYSIS, CompilePhaseAdapter.CANONICALIZATION, CompilePhaseAdapter.INSTRUCTION_SELECTION, CompilePhaseAdapter.CLASS_GENERATION, CompilePhaseAdapter.OUTPUT, CompilePhaseAdapter.FINALIZATION };
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$CompilePhaseAdapter()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = CompilePhaseAdapter.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (CompilePhaseAdapter.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    public final int getPhaseId() {
        return this.phaseId;
    }
    
    public final String getString() {
        return this.string;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[29];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$CompilePhaseAdapter(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (CompilePhaseAdapter.$callSiteArray == null || ($createCallSiteArray = CompilePhaseAdapter.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            CompilePhaseAdapter.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$CompilePhaseAdapter() {
        Class $class$groovy$inspect$swingui$CompilePhaseAdapter;
        if (($class$groovy$inspect$swingui$CompilePhaseAdapter = CompilePhaseAdapter.$class$groovy$inspect$swingui$CompilePhaseAdapter) == null) {
            $class$groovy$inspect$swingui$CompilePhaseAdapter = (CompilePhaseAdapter.$class$groovy$inspect$swingui$CompilePhaseAdapter = class$("groovy.inspect.swingui.CompilePhaseAdapter"));
        }
        return $class$groovy$inspect$swingui$CompilePhaseAdapter;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = CompilePhaseAdapter.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (CompilePhaseAdapter.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Enum() {
        Class $class$java$lang$Enum;
        if (($class$java$lang$Enum = CompilePhaseAdapter.$class$java$lang$Enum) == null) {
            $class$java$lang$Enum = (CompilePhaseAdapter.$class$java$lang$Enum = class$("java.lang.Enum"));
        }
        return $class$java$lang$Enum;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = CompilePhaseAdapter.$class$java$lang$String) == null) {
            $class$java$lang$String = (CompilePhaseAdapter.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$Phases() {
        Class $class$org$codehaus$groovy$control$Phases;
        if (($class$org$codehaus$groovy$control$Phases = CompilePhaseAdapter.$class$org$codehaus$groovy$control$Phases) == null) {
            $class$org$codehaus$groovy$control$Phases = (CompilePhaseAdapter.$class$org$codehaus$groovy$control$Phases = class$("org.codehaus.groovy.control.Phases"));
        }
        return $class$org$codehaus$groovy$control$Phases;
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
