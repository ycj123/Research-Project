// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.binding;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.ListModel;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class JListMetaMethods$_enhanceMetaClass_closure1 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$javax$swing$ListModel;
    
    public JListMetaMethods$_enhanceMetaClass_closure1(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ListModel model = (ListModel)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callGetProperty($getCallSiteArray[1].callGroovyObjectGetProperty(this)), $get$$class$javax$swing$ListModel()));
        final Object results = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        final Integer size = (Integer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callGetProperty(((Reference)model).get()), $get$$class$java$lang$Integer()));
        final Integer i = (Integer)new Reference(JListMetaMethods$_enhanceMetaClass_closure1.$const$0);
        while (ScriptBytecodeAdapter.compareLessThan(((Reference<Object>)i).get(), ((Reference<Object>)size).get())) {
            ((Reference<Object>)results).set($getCallSiteArray[3].call(((Reference<Object>)results).get(), $getCallSiteArray[4].call(((Reference)model).get(), ((Reference<Object>)i).get())));
            ((Reference<Object>)i).get();
            ((Reference<Object>)i).set($getCallSiteArray[5].call(((Reference<Object>)i).get()));
        }
        return ((Reference<Object>)results).get();
    }
    
    static {
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$binding$JListMetaMethods$_enhanceMetaClass_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JListMetaMethods$_enhanceMetaClass_closure1.$callSiteArray == null || ($createCallSiteArray = JListMetaMethods$_enhanceMetaClass_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JListMetaMethods$_enhanceMetaClass_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = JListMetaMethods$_enhanceMetaClass_closure1.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (JListMetaMethods$_enhanceMetaClass_closure1.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$ListModel() {
        Class $class$javax$swing$ListModel;
        if (($class$javax$swing$ListModel = JListMetaMethods$_enhanceMetaClass_closure1.$class$javax$swing$ListModel) == null) {
            $class$javax$swing$ListModel = (JListMetaMethods$_enhanceMetaClass_closure1.$class$javax$swing$ListModel = class$("javax.swing.ListModel"));
        }
        return $class$javax$swing$ListModel;
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
