// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JTableMetaMethods$_enhanceMetaClass_closure3_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> myTable;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$swing$binding$JTableMetaMethods;
    
    public JTableMetaMethods$_enhanceMetaClass_closure3_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> myTable) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.myTable = myTable;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        return $getCallSiteArray()[0].callStatic($get$$class$groovy$swing$binding$JTableMetaMethods(), this.myTable.get(), ((Reference<Object>)it2).get());
    }
    
    public Object getMyTable() {
        $getCallSiteArray();
        return this.myTable.get();
    }
    
    public Object doCall() {
        return $getCallSiteArray()[1].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JTableMetaMethods$_enhanceMetaClass_closure3_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$callSiteArray == null || ($createCallSiteArray = JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$binding$JTableMetaMethods() {
        Class $class$groovy$swing$binding$JTableMetaMethods;
        if (($class$groovy$swing$binding$JTableMetaMethods = JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$class$groovy$swing$binding$JTableMetaMethods) == null) {
            $class$groovy$swing$binding$JTableMetaMethods = (JTableMetaMethods$_enhanceMetaClass_closure3_closure4.$class$groovy$swing$binding$JTableMetaMethods = class$("groovy.swing.binding.JTableMetaMethods"));
        }
        return $class$groovy$swing$binding$JTableMetaMethods;
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
