// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.view;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.GroovyObject;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import java.math.BigDecimal;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BasicStatusBar$_run_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ BigDecimal $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$awt$GridBagConstraints;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$swing$SwingConstants;
    private static /* synthetic */ Class $class$groovy$ui$view$BasicStatusBar$_run_closure1;
    
    public BasicStatusBar$_run_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].callCurrent(this);
        $getCallSiteArray[1].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "gridwidth", $getCallSiteArray[2].callGetProperty($get$$class$java$awt$GridBagConstraints()), "fill", $getCallSiteArray[3].callGetProperty($get$$class$java$awt$GridBagConstraints()) }));
        ScriptBytecodeAdapter.setGroovyObjectProperty($getCallSiteArray[4].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "weightx", BasicStatusBar$_run_closure1.$const$0, "anchor", $getCallSiteArray[5].callGetProperty($get$$class$java$awt$GridBagConstraints()), "fill", $getCallSiteArray[6].callGetProperty($get$$class$java$awt$GridBagConstraints()), "insets", ScriptBytecodeAdapter.createList(new Object[] { BasicStatusBar$_run_closure1.$const$1, BasicStatusBar$_run_closure1.$const$2, BasicStatusBar$_run_closure1.$const$1, BasicStatusBar$_run_closure1.$const$2 }) }), "Welcome to Groovy."), $get$$class$groovy$ui$view$BasicStatusBar$_run_closure1(), this, "status");
        $getCallSiteArray[7].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "orientation", $getCallSiteArray[8].callGetProperty($get$$class$javax$swing$SwingConstants()), "fill", $getCallSiteArray[9].callGetProperty($get$$class$java$awt$GridBagConstraints()) }));
        final Object callCurrent = $getCallSiteArray[10].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "insets", ScriptBytecodeAdapter.createList(new Object[] { BasicStatusBar$_run_closure1.$const$1, BasicStatusBar$_run_closure1.$const$2, BasicStatusBar$_run_closure1.$const$1, BasicStatusBar$_run_closure1.$const$2 }) }), "1:1");
        ScriptBytecodeAdapter.setGroovyObjectProperty(callCurrent, $get$$class$groovy$ui$view$BasicStatusBar$_run_closure1(), this, "rowNumAndColNum");
        return callCurrent;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[11].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    static {
        $const$2 = 3;
        $const$1 = 1;
        $const$0 = new BigDecimal("1.0");
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$view$BasicStatusBar$_run_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BasicStatusBar$_run_closure1.$callSiteArray == null || ($createCallSiteArray = BasicStatusBar$_run_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BasicStatusBar$_run_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$awt$GridBagConstraints() {
        Class $class$java$awt$GridBagConstraints;
        if (($class$java$awt$GridBagConstraints = BasicStatusBar$_run_closure1.$class$java$awt$GridBagConstraints) == null) {
            $class$java$awt$GridBagConstraints = (BasicStatusBar$_run_closure1.$class$java$awt$GridBagConstraints = class$("java.awt.GridBagConstraints"));
        }
        return $class$java$awt$GridBagConstraints;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = BasicStatusBar$_run_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (BasicStatusBar$_run_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$SwingConstants() {
        Class $class$javax$swing$SwingConstants;
        if (($class$javax$swing$SwingConstants = BasicStatusBar$_run_closure1.$class$javax$swing$SwingConstants) == null) {
            $class$javax$swing$SwingConstants = (BasicStatusBar$_run_closure1.$class$javax$swing$SwingConstants = class$("javax.swing.SwingConstants"));
        }
        return $class$javax$swing$SwingConstants;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$view$BasicStatusBar$_run_closure1() {
        Class $class$groovy$ui$view$BasicStatusBar$_run_closure1;
        if (($class$groovy$ui$view$BasicStatusBar$_run_closure1 = BasicStatusBar$_run_closure1.$class$groovy$ui$view$BasicStatusBar$_run_closure1) == null) {
            $class$groovy$ui$view$BasicStatusBar$_run_closure1 = (BasicStatusBar$_run_closure1.$class$groovy$ui$view$BasicStatusBar$_run_closure1 = class$("groovy.ui.view.BasicStatusBar$_run_closure1"));
        }
        return $class$groovy$ui$view$BasicStatusBar$_run_closure1;
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
