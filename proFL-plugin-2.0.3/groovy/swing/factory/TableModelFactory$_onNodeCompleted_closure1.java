// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.model.DefaultTableModel;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class TableModelFactory$_onNodeCompleted_closure1 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1;
    
    public TableModelFactory$_onNodeCompleted_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object e) {
        final Object e2 = new Reference(e);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGetProperty(((Reference<Object>)e2).get()), "model") && $getCallSiteArray[1].callGetProperty(((Reference<Object>)e2).get()) instanceof DefaultTableModel) ? Boolean.TRUE : Boolean.FALSE)) {
            ScriptBytecodeAdapter.setProperty($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGetProperty(((Reference<Object>)e2).get())), $get$$class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1(), $getCallSiteArray[4].callGetProperty(((Reference<Object>)e2).get()), "columnModel");
            $getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(((Reference<Object>)e2).get()));
            return $getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty(((Reference<Object>)e2).get()));
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (TableModelFactory$_onNodeCompleted_closure1.$callSiteArray == null || ($createCallSiteArray = TableModelFactory$_onNodeCompleted_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            TableModelFactory$_onNodeCompleted_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1() {
        Class $class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1;
        if (($class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1 = TableModelFactory$_onNodeCompleted_closure1.$class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1) == null) {
            $class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1 = (TableModelFactory$_onNodeCompleted_closure1.$class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1 = class$("groovy.swing.factory.TableModelFactory$_onNodeCompleted_closure1"));
        }
        return $class$groovy$swing$factory$TableModelFactory$_onNodeCompleted_closure1;
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
