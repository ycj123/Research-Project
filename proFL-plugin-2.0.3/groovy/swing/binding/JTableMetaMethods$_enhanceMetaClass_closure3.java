// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JTableMetaMethods$_enhanceMetaClass_closure3 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public JTableMetaMethods$_enhanceMetaClass_closure3(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object myTable = new Reference($getCallSiteArray[0].callGroovyObjectGetProperty(this));
        return $getCallSiteArray[1].call($getCallSiteArray[2].call(((Reference<Object>)myTable).get()), new JTableMetaMethods$_enhanceMetaClass_closure3_closure4(this, this.getThisObject(), (Reference<Object>)myTable));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JTableMetaMethods$_enhanceMetaClass_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JTableMetaMethods$_enhanceMetaClass_closure3.$callSiteArray == null || ($createCallSiteArray = JTableMetaMethods$_enhanceMetaClass_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JTableMetaMethods$_enhanceMetaClass_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
