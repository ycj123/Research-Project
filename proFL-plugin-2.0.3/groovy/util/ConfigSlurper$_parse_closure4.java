// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.util.LinkedList;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConfigSlurper$_parse_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> assignName;
    private Reference<Object> stack;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$util$ConfigObject;
    private static /* synthetic */ Class $class$org$codehaus$groovy$runtime$InvokerHelper;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    
    public ConfigSlurper$_parse_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> assignName, final Reference<Object> stack) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.assignName = assignName;
        this.stack = stack;
    }
    
    public Object doCall(final String name) {
        final String name2 = (String)new Reference(name);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object current = new Reference($getCallSiteArray[0].callGetProperty(this.stack.get()));
        final Object result = new Reference(null);
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call($getCallSiteArray[2].callGetProperty(((Reference<Object>)current).get()), ((Reference<Object>)name2).get()))) {
            ((Reference<Object>)result).set($getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty(((Reference<Object>)current).get()), ((Reference<Object>)name2).get()));
        }
        else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call($getCallSiteArray[6].callGetProperty(((Reference<Object>)current).get()), ((Reference<Object>)name2).get()))) {
            ((Reference<Object>)result).set($getCallSiteArray[7].call($getCallSiteArray[8].callGetProperty(((Reference<Object>)current).get()), ((Reference<Object>)name2).get()));
        }
        else {
            try {
                ((Reference<Object>)result).set($getCallSiteArray[9].call($get$$class$org$codehaus$groovy$runtime$InvokerHelper(), this.getThisObject(), ((Reference<Object>)name2).get()));
            }
            catch (GroovyRuntimeException e) {
                ((Reference<Object>)result).set($getCallSiteArray[10].callConstructor($get$$class$groovy$util$ConfigObject()));
                $getCallSiteArray[11].call(this.assignName.get(), ((Reference<Object>)name2).get(), ((Reference<Object>)result).get());
            }
        }
        return ((Reference<Object>)result).get();
    }
    
    public Object call(final String name) {
        final String name2 = (String)new Reference(name);
        return $getCallSiteArray()[12].callCurrent(this, ((Reference<Object>)name2).get());
    }
    
    public Object getAssignName() {
        $getCallSiteArray();
        return this.assignName.get();
    }
    
    public LinkedList getStack() {
        $getCallSiteArray();
        return (LinkedList)ScriptBytecodeAdapter.castToType(this.stack.get(), $get$$class$java$util$LinkedList());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[13];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigSlurper$_parse_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigSlurper$_parse_closure4.$callSiteArray == null || ($createCallSiteArray = ConfigSlurper$_parse_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigSlurper$_parse_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigObject() {
        Class $class$groovy$util$ConfigObject;
        if (($class$groovy$util$ConfigObject = ConfigSlurper$_parse_closure4.$class$groovy$util$ConfigObject) == null) {
            $class$groovy$util$ConfigObject = (ConfigSlurper$_parse_closure4.$class$groovy$util$ConfigObject = class$("groovy.util.ConfigObject"));
        }
        return $class$groovy$util$ConfigObject;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$runtime$InvokerHelper() {
        Class $class$org$codehaus$groovy$runtime$InvokerHelper;
        if (($class$org$codehaus$groovy$runtime$InvokerHelper = ConfigSlurper$_parse_closure4.$class$org$codehaus$groovy$runtime$InvokerHelper) == null) {
            $class$org$codehaus$groovy$runtime$InvokerHelper = (ConfigSlurper$_parse_closure4.$class$org$codehaus$groovy$runtime$InvokerHelper = class$("org.codehaus.groovy.runtime.InvokerHelper"));
        }
        return $class$org$codehaus$groovy$runtime$InvokerHelper;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = ConfigSlurper$_parse_closure4.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (ConfigSlurper$_parse_closure4.$class$java$util$LinkedList = class$("java.util.LinkedList"));
        }
        return $class$java$util$LinkedList;
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
