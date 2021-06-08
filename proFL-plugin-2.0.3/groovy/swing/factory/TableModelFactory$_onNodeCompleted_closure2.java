// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.beans.PropertyChangeListener;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TableModelFactory$_onNodeCompleted_closure2 extends Closure implements GeneratedClosure
{
    private Reference<Object> listener;
    private Reference<Object> parent;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$beans$PropertyChangeListener;
    
    public TableModelFactory$_onNodeCompleted_closure2(final Object _outerInstance, final Object _thisObject, final Reference<Object> listener, final Reference<Object> parent) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.listener = listener;
        this.parent = parent;
    }
    
    public Object doCall(final Object it) {
        return $getCallSiteArray()[0].call(this.parent.get(), "model", this.listener.get());
    }
    
    public PropertyChangeListener getListener() {
        $getCallSiteArray();
        return (PropertyChangeListener)ScriptBytecodeAdapter.castToType(this.listener.get(), $get$$class$java$beans$PropertyChangeListener());
    }
    
    public Object getParent() {
        $getCallSiteArray();
        return ScriptBytecodeAdapter.castToType(this.parent.get(), $get$$class$java$lang$Object());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure2(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TableModelFactory$_onNodeCompleted_closure2.$callSiteArray == null || ($createCallSiteArray = TableModelFactory$_onNodeCompleted_closure2.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TableModelFactory$_onNodeCompleted_closure2.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = TableModelFactory$_onNodeCompleted_closure2.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (TableModelFactory$_onNodeCompleted_closure2.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$beans$PropertyChangeListener() {
        Class $class$java$beans$PropertyChangeListener;
        if (($class$java$beans$PropertyChangeListener = TableModelFactory$_onNodeCompleted_closure2.$class$java$beans$PropertyChangeListener) == null) {
            $class$java$beans$PropertyChangeListener = (TableModelFactory$_onNodeCompleted_closure2.$class$java$beans$PropertyChangeListener = class$("java.beans.PropertyChangeListener"));
        }
        return $class$java$beans$PropertyChangeListener;
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
