// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.prefs.Preferences;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class AstBrowserUiPreferences implements GroovyObject
{
    private final Object frameLocation;
    private final Object frameSize;
    private final Object verticalDividerLocation;
    private final Object horizontalDividerLocation;
    private final boolean showScriptFreeForm;
    private final boolean showScriptClass;
    private int decompiledSourceFontSize;
    private final CompilePhaseAdapter selectedPhase;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ Integer $const$4;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202690;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$CompilePhaseAdapter;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowserUiPreferences;
    private static /* synthetic */ Class $class$java$util$prefs$Preferences;
    private static /* synthetic */ Class $class$org$codehaus$groovy$control$Phases;
    
    public AstBrowserUiPreferences() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final Preferences prefs = (Preferences)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$inspect$swingui$AstBrowserUiPreferences()), $get$$class$java$util$prefs$Preferences());
        this.frameLocation = ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[1].call(prefs, "frameX", AstBrowserUiPreferences.$const$0), $getCallSiteArray[2].call(prefs, "frameY", AstBrowserUiPreferences.$const$0) });
        this.frameSize = ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[3].call(prefs, "frameWidth", AstBrowserUiPreferences.$const$1), $getCallSiteArray[4].call(prefs, "frameHeight", AstBrowserUiPreferences.$const$2) });
        this.decompiledSourceFontSize = DefaultTypeTransformation.intUnbox($getCallSiteArray[5].call(prefs, "decompiledFontSize", AstBrowserUiPreferences.$const$3));
        this.verticalDividerLocation = $getCallSiteArray[6].call(prefs, "verticalSplitterLocation", AstBrowserUiPreferences.$const$4);
        this.horizontalDividerLocation = $getCallSiteArray[7].call(prefs, "horizontalSplitterLocation", AstBrowserUiPreferences.$const$4);
        this.showScriptFreeForm = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call(prefs, "showScriptFreeForm", Boolean.FALSE));
        this.showScriptClass = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(prefs, "showScriptClass", Boolean.TRUE));
        final Integer phase = (Integer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(prefs, "compilerPhase", $getCallSiteArray[11].callGetProperty($get$$class$org$codehaus$groovy$control$Phases())), $get$$class$java$lang$Integer()));
        this.selectedPhase = (CompilePhaseAdapter)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[12].call($getCallSiteArray[13].call($get$$class$groovy$inspect$swingui$CompilePhaseAdapter()), new AstBrowserUiPreferences$_closure1(this, this, (Reference<Object>)phase)), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter()), $get$$class$groovy$inspect$swingui$CompilePhaseAdapter());
    }
    
    public Object save(final Object frame, final Object vSplitter, final Object hSplitter, final Object scriptFreeFormPref, final Object scriptClassPref, final CompilePhaseAdapter phase) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Preferences prefs = (Preferences)ScriptBytecodeAdapter.castToType($getCallSiteArray[14].call($get$$class$java$util$prefs$Preferences(), $get$$class$groovy$inspect$swingui$AstBrowserUiPreferences()), $get$$class$java$util$prefs$Preferences());
        $getCallSiteArray[15].call(prefs, "decompiledFontSize", ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(DefaultTypeTransformation.box(this.decompiledSourceFontSize), $get$$class$java$lang$Integer()), Integer.TYPE));
        $getCallSiteArray[16].call(prefs, "frameX", ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[17].callGetProperty($getCallSiteArray[18].callGetProperty(frame)), $get$$class$java$lang$Integer()), Integer.TYPE));
        $getCallSiteArray[19].call(prefs, "frameY", ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[20].callGetProperty($getCallSiteArray[21].callGetProperty(frame)), $get$$class$java$lang$Integer()), Integer.TYPE));
        $getCallSiteArray[22].call(prefs, "frameWidth", ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[23].callGetProperty($getCallSiteArray[24].callGetProperty(frame)), $get$$class$java$lang$Integer()), Integer.TYPE));
        $getCallSiteArray[25].call(prefs, "frameHeight", ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[26].callGetProperty($getCallSiteArray[27].callGetProperty(frame)), $get$$class$java$lang$Integer()), Integer.TYPE));
        $getCallSiteArray[28].call(prefs, "verticalSplitterLocation", $getCallSiteArray[29].callGetProperty(vSplitter));
        $getCallSiteArray[30].call(prefs, "horizontalSplitterLocation", $getCallSiteArray[31].callGetProperty(hSplitter));
        $getCallSiteArray[32].call(prefs, "showScriptFreeForm", scriptFreeFormPref);
        $getCallSiteArray[33].call(prefs, "showScriptClass", scriptClassPref);
        return $getCallSiteArray[34].call(prefs, "compilerPhase", $getCallSiteArray[35].callGroovyObjectGetProperty(phase));
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$inspect$swingui$AstBrowserUiPreferences()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AstBrowserUiPreferences.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AstBrowserUiPreferences.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AstBrowserUiPreferences.__timeStamp__239_neverHappen1292524202690 = 0L;
        AstBrowserUiPreferences.__timeStamp = 1292524202690L;
        $const$4 = 100;
        $const$3 = 12;
        $const$2 = 600;
        $const$1 = 800;
        $const$0 = 200;
    }
    
    public final Object getFrameLocation() {
        return this.frameLocation;
    }
    
    public final Object getFrameSize() {
        return this.frameSize;
    }
    
    public final Object getVerticalDividerLocation() {
        return this.verticalDividerLocation;
    }
    
    public final Object getHorizontalDividerLocation() {
        return this.horizontalDividerLocation;
    }
    
    public final boolean getShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public final boolean isShowScriptFreeForm() {
        return this.showScriptFreeForm;
    }
    
    public final boolean getShowScriptClass() {
        return this.showScriptClass;
    }
    
    public final boolean isShowScriptClass() {
        return this.showScriptClass;
    }
    
    public int getDecompiledSourceFontSize() {
        return this.decompiledSourceFontSize;
    }
    
    public void setDecompiledSourceFontSize(final int decompiledSourceFontSize) {
        this.decompiledSourceFontSize = decompiledSourceFontSize;
    }
    
    public final CompilePhaseAdapter getSelectedPhase() {
        return this.selectedPhase;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[36];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowserUiPreferences(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowserUiPreferences.$callSiteArray == null || ($createCallSiteArray = AstBrowserUiPreferences.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowserUiPreferences.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$CompilePhaseAdapter() {
        Class $class$groovy$inspect$swingui$CompilePhaseAdapter;
        if (($class$groovy$inspect$swingui$CompilePhaseAdapter = AstBrowserUiPreferences.$class$groovy$inspect$swingui$CompilePhaseAdapter) == null) {
            $class$groovy$inspect$swingui$CompilePhaseAdapter = (AstBrowserUiPreferences.$class$groovy$inspect$swingui$CompilePhaseAdapter = class$("groovy.inspect.swingui.CompilePhaseAdapter"));
        }
        return $class$groovy$inspect$swingui$CompilePhaseAdapter;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = AstBrowserUiPreferences.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (AstBrowserUiPreferences.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AstBrowserUiPreferences.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AstBrowserUiPreferences.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowserUiPreferences() {
        Class $class$groovy$inspect$swingui$AstBrowserUiPreferences;
        if (($class$groovy$inspect$swingui$AstBrowserUiPreferences = AstBrowserUiPreferences.$class$groovy$inspect$swingui$AstBrowserUiPreferences) == null) {
            $class$groovy$inspect$swingui$AstBrowserUiPreferences = (AstBrowserUiPreferences.$class$groovy$inspect$swingui$AstBrowserUiPreferences = class$("groovy.inspect.swingui.AstBrowserUiPreferences"));
        }
        return $class$groovy$inspect$swingui$AstBrowserUiPreferences;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$prefs$Preferences() {
        Class $class$java$util$prefs$Preferences;
        if (($class$java$util$prefs$Preferences = AstBrowserUiPreferences.$class$java$util$prefs$Preferences) == null) {
            $class$java$util$prefs$Preferences = (AstBrowserUiPreferences.$class$java$util$prefs$Preferences = class$("java.util.prefs.Preferences"));
        }
        return $class$java$util$prefs$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$control$Phases() {
        Class $class$org$codehaus$groovy$control$Phases;
        if (($class$org$codehaus$groovy$control$Phases = AstBrowserUiPreferences.$class$org$codehaus$groovy$control$Phases) == null) {
            $class$org$codehaus$groovy$control$Phases = (AstBrowserUiPreferences.$class$org$codehaus$groovy$control$Phases = class$("org.codehaus.groovy.control.Phases"));
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
