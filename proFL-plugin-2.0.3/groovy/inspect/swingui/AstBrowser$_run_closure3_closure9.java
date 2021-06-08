// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import java.math.BigDecimal;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure3_closure9 extends Closure implements GeneratedClosure
{
    private Reference<Object> phasePicker;
    private Reference<Object> splitterPane;
    private Reference<Object> mainSplitter;
    private Reference<Object> script;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ BigDecimal $const$3;
    private static final /* synthetic */ Integer $const$4;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$CompilePhaseAdapter;
    private static /* synthetic */ Class $class$java$awt$GridBagConstraints;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$JSplitPane;
    private static /* synthetic */ Class $class$groovy$lang$Closure;
    
    public AstBrowser$_run_closure3_closure9(final Object _outerInstance, final Object _thisObject, final Reference<Object> phasePicker, final Reference<Object> splitterPane, final Reference<Object> mainSplitter, final Reference<Object> script) {
        final Reference splitterPane2 = new Reference((T)splitterPane);
        final Reference mainSplitter2 = new Reference((T)mainSplitter);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.phasePicker = phasePicker;
        this.splitterPane = splitterPane2.get();
        this.mainSplitter = mainSplitter2.get();
        this.script = script;
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this);
        $getCallSiteArray[1].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", "At end of Phase: ", "constraints", $getCallSiteArray[2].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "gridx", AstBrowser$_run_closure3_closure9.$const$0, "gridy", AstBrowser$_run_closure3_closure9.$const$0, "gridwidth", AstBrowser$_run_closure3_closure9.$const$1, "gridheight", AstBrowser$_run_closure3_closure9.$const$1, "weightx", AstBrowser$_run_closure3_closure9.$const$0, "weighty", AstBrowser$_run_closure3_closure9.$const$0, "anchor", $getCallSiteArray[3].callGetProperty($get$$class$java$awt$GridBagConstraints()), "fill", $getCallSiteArray[4].callGetProperty($get$$class$java$awt$GridBagConstraints()), "insets", ScriptBytecodeAdapter.createList(new Object[] { AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2 }) })) }));
        final CallSite callSite = $getCallSiteArray[5];
        final Object[] values = { "items", $getCallSiteArray[6].call($get$$class$groovy$inspect$swingui$CompilePhaseAdapter()), "selectedItem", $getCallSiteArray[7].callGetProperty($getCallSiteArray[8].callGroovyObjectGetProperty(this)), "actionPerformed", null, null, null };
        final int n = 5;
        final Object thisObject = this.getThisObject();
        final Reference<Object> phasePicker2;
        final Reference phasePicker = phasePicker2 = this.phasePicker;
        final Reference script = this.script;
        values[n] = new AstBrowser$_run_closure3_closure9_closure20(this, thisObject, phasePicker2, script);
        values[6] = "constraints";
        values[7] = $getCallSiteArray[9].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "gridx", AstBrowser$_run_closure3_closure9.$const$1, "gridy", AstBrowser$_run_closure3_closure9.$const$0, "gridwidth", AstBrowser$_run_closure3_closure9.$const$1, "gridheight", AstBrowser$_run_closure3_closure9.$const$1, "weightx", AstBrowser$_run_closure3_closure9.$const$3, "weighty", AstBrowser$_run_closure3_closure9.$const$0, "anchor", $getCallSiteArray[10].callGetProperty($get$$class$java$awt$GridBagConstraints()), "fill", $getCallSiteArray[11].callGetProperty($get$$class$java$awt$GridBagConstraints()), "insets", ScriptBytecodeAdapter.createList(new Object[] { AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2 }) }));
        phasePicker.set(callSite.callCurrent(this, ScriptBytecodeAdapter.createMap(values)));
        $getCallSiteArray[12].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "text", "Refresh", "actionPerformed", new AstBrowser$_run_closure3_closure9_closure21(this, this.getThisObject(), phasePicker, script), "constraints", $getCallSiteArray[13].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "gridx", AstBrowser$_run_closure3_closure9.$const$2, "gridy", AstBrowser$_run_closure3_closure9.$const$0, "gridwidth", AstBrowser$_run_closure3_closure9.$const$1, "gridheight", AstBrowser$_run_closure3_closure9.$const$1, "weightx", AstBrowser$_run_closure3_closure9.$const$0, "weighty", AstBrowser$_run_closure3_closure9.$const$0, "anchor", $getCallSiteArray[14].callGetProperty($get$$class$java$awt$GridBagConstraints()), "fill", $getCallSiteArray[15].callGetProperty($get$$class$java$awt$GridBagConstraints()), "insets", ScriptBytecodeAdapter.createList(new Object[] { AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$4 }) })) }));
        this.splitterPane.set($getCallSiteArray[16].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "leftComponent", $getCallSiteArray[17].callCurrent(this, new AstBrowser$_run_closure3_closure9_closure22(this, this.getThisObject())), "rightComponent", $getCallSiteArray[18].callCurrent(this, new AstBrowser$_run_closure3_closure9_closure23(this, this.getThisObject())) }), new AstBrowser$_run_closure3_closure9_closure24(this, this.getThisObject())));
        final Object callCurrent = $getCallSiteArray[19].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[20].callGetProperty($get$$class$javax$swing$JSplitPane()), "topComponent", this.splitterPane.get(), "bottomComponent", $getCallSiteArray[21].callCurrent(this, new AstBrowser$_run_closure3_closure9_closure25(this, this.getThisObject())), "constraints", $getCallSiteArray[22].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "gridx", AstBrowser$_run_closure3_closure9.$const$0, "gridy", AstBrowser$_run_closure3_closure9.$const$2, "gridwidth", AstBrowser$_run_closure3_closure9.$const$4, "gridheight", AstBrowser$_run_closure3_closure9.$const$1, "weightx", AstBrowser$_run_closure3_closure9.$const$3, "weighty", AstBrowser$_run_closure3_closure9.$const$3, "anchor", $getCallSiteArray[23].callGetProperty($get$$class$java$awt$GridBagConstraints()), "fill", $getCallSiteArray[24].callGetProperty($get$$class$java$awt$GridBagConstraints()), "insets", ScriptBytecodeAdapter.createList(new Object[] { AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2, AstBrowser$_run_closure3_closure9.$const$2 }) })) }), new AstBrowser$_run_closure3_closure9_closure26(this, this.getThisObject()));
        this.mainSplitter.set(callCurrent);
        return callCurrent;
    }
    
    public Object getPhasePicker() {
        $getCallSiteArray();
        return this.phasePicker.get();
    }
    
    public Object getSplitterPane() {
        $getCallSiteArray();
        return this.splitterPane.get();
    }
    
    public Object getMainSplitter() {
        $getCallSiteArray();
        return this.mainSplitter.get();
    }
    
    public Closure getScript() {
        $getCallSiteArray();
        return (Closure)ScriptBytecodeAdapter.castToType(this.script.get(), $get$$class$groovy$lang$Closure());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[25].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$4 = 3;
        $const$3 = new BigDecimal("1.0");
        $const$2 = 2;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[26];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure3_closure9(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure3_closure9.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure3_closure9.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure3_closure9.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$CompilePhaseAdapter() {
        Class $class$groovy$inspect$swingui$CompilePhaseAdapter;
        if (($class$groovy$inspect$swingui$CompilePhaseAdapter = AstBrowser$_run_closure3_closure9.$class$groovy$inspect$swingui$CompilePhaseAdapter) == null) {
            $class$groovy$inspect$swingui$CompilePhaseAdapter = (AstBrowser$_run_closure3_closure9.$class$groovy$inspect$swingui$CompilePhaseAdapter = class$("groovy.inspect.swingui.CompilePhaseAdapter"));
        }
        return $class$groovy$inspect$swingui$CompilePhaseAdapter;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GridBagConstraints() {
        Class $class$java$awt$GridBagConstraints;
        if (($class$java$awt$GridBagConstraints = AstBrowser$_run_closure3_closure9.$class$java$awt$GridBagConstraints) == null) {
            $class$java$awt$GridBagConstraints = (AstBrowser$_run_closure3_closure9.$class$java$awt$GridBagConstraints = class$("java.awt.GridBagConstraints"));
        }
        return $class$java$awt$GridBagConstraints;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowser$_run_closure3_closure9.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowser$_run_closure3_closure9.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$JSplitPane() {
        Class $class$javax$swing$JSplitPane;
        if (($class$javax$swing$JSplitPane = AstBrowser$_run_closure3_closure9.$class$javax$swing$JSplitPane) == null) {
            $class$javax$swing$JSplitPane = (AstBrowser$_run_closure3_closure9.$class$javax$swing$JSplitPane = class$("javax.swing.JSplitPane"));
        }
        return $class$javax$swing$JSplitPane;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$Closure() {
        Class $class$groovy$lang$Closure;
        if (($class$groovy$lang$Closure = AstBrowser$_run_closure3_closure9.$class$groovy$lang$Closure) == null) {
            $class$groovy$lang$Closure = (AstBrowser$_run_closure3_closure9.$class$groovy$lang$Closure = class$("groovy.lang.Closure"));
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
