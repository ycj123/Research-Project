// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.LinkedList;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.MetaMethod;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class ConfigSlurper$_parse_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> mc;
    private Reference<Object> prefix;
    private Reference<Object> assignName;
    private Reference<Object> config;
    private Reference<Object> stack;
    private Reference<Object> pushStack;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$util$ConfigObject;
    private static /* synthetic */ Class $class$groovy$util$ConfigSlurper$_parse_closure5;
    private static /* synthetic */ Class $class$groovy$lang$MissingMethodException;
    private static /* synthetic */ Class $class$groovy$lang$MetaMethod;
    private static /* synthetic */ Class $class$java$util$LinkedList;
    
    public ConfigSlurper$_parse_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> mc, final Reference<Object> prefix, final Reference<Object> assignName, final Reference<Object> config, final Reference<Object> stack, final Reference<Object> pushStack) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.mc = mc;
        this.prefix = prefix;
        this.assignName = assignName;
        this.config = config;
        this.stack = stack;
        this.pushStack = pushStack;
    }
    
    public Object doCall(final String name, final Object args) {
        final String name2 = (String)new Reference(name);
        final Object args2 = new Reference(args);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object result = new Reference(null);
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[0].callGetProperty(((Reference<Object>)args2).get()), ConfigSlurper$_parse_closure5.$const$0) && $getCallSiteArray[1].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$1) instanceof Closure) ? Boolean.TRUE : Boolean.FALSE)) {
            if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)name2).get(), $getCallSiteArray[2].callGroovyObjectGetProperty(this))) {
                try {
                    ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.TRUE, $get$$class$groovy$util$ConfigSlurper$_parse_closure5(), this, "envMode");
                    $getCallSiteArray[3].call($getCallSiteArray[4].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$1));
                    ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.FALSE, $get$$class$groovy$util$ConfigSlurper$_parse_closure5(), this, "envMode");
                }
                finally {
                    ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.FALSE, $get$$class$groovy$util$ConfigSlurper$_parse_closure5(), this, "envMode");
                }
            }
            else if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].callGroovyObjectGetProperty(this))) {
                if (ScriptBytecodeAdapter.compareEqual(((Reference<Object>)name2).get(), $getCallSiteArray[6].callGroovyObjectGetProperty(this))) {
                    final Object co = $getCallSiteArray[7].callConstructor($get$$class$groovy$util$ConfigObject());
                    $getCallSiteArray[8].call(this.config.get(), $getCallSiteArray[9].callGroovyObjectGetProperty(this), co);
                    $getCallSiteArray[10].call(this.pushStack.get(), co);
                    try {
                        ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.FALSE, $get$$class$groovy$util$ConfigSlurper$_parse_closure5(), this, "envMode");
                        $getCallSiteArray[11].call($getCallSiteArray[12].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$1));
                        ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.TRUE, $get$$class$groovy$util$ConfigSlurper$_parse_closure5(), this, "envMode");
                    }
                    finally {
                        ScriptBytecodeAdapter.setGroovyObjectProperty(Boolean.TRUE, $get$$class$groovy$util$ConfigSlurper$_parse_closure5(), this, "envMode");
                    }
                    $getCallSiteArray[13].call(this.stack.get());
                }
            }
            else {
                final Object co = new Reference(null);
                if ($getCallSiteArray[14].call($getCallSiteArray[15].callGetProperty($getCallSiteArray[16].callGetProperty(this.stack.get())), ((Reference<Object>)name2).get()) instanceof ConfigObject) {
                    ((Reference<Object>)co).set($getCallSiteArray[17].call($getCallSiteArray[18].callGetProperty($getCallSiteArray[19].callGetProperty(this.stack.get())), ((Reference<Object>)name2).get()));
                }
                else {
                    ((Reference<Object>)co).set($getCallSiteArray[20].callConstructor($get$$class$groovy$util$ConfigObject()));
                }
                $getCallSiteArray[21].call(this.assignName.get(), ((Reference<Object>)name2).get(), ((Reference<Object>)co).get());
                $getCallSiteArray[22].call(this.pushStack.get(), ((Reference<Object>)co).get());
                $getCallSiteArray[23].call($getCallSiteArray[24].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$1));
                $getCallSiteArray[25].call(this.stack.get());
            }
        }
        else if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareEqual($getCallSiteArray[26].callGetProperty(((Reference<Object>)args2).get()), ConfigSlurper$_parse_closure5.$const$2) && $getCallSiteArray[27].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$0) instanceof Closure) ? Boolean.TRUE : Boolean.FALSE)) {
            try {
                this.prefix.set($getCallSiteArray[28].call(((Reference<Object>)name2).get(), "."));
                $getCallSiteArray[29].call(this.assignName.get(), ((Reference<Object>)name2).get(), $getCallSiteArray[30].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$1));
                $getCallSiteArray[31].call($getCallSiteArray[32].call(((Reference<Object>)args2).get(), ConfigSlurper$_parse_closure5.$const$0));
                this.prefix.set("");
            }
            finally {
                this.prefix.set("");
            }
        }
        else {
            final MetaMethod mm = (MetaMethod)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[33].call(this.mc.get(), ((Reference<Object>)name2).get(), ((Reference<Object>)args2).get()), $get$$class$groovy$lang$MetaMethod()));
            if (!DefaultTypeTransformation.booleanUnbox(((Reference<Object>)mm).get())) {
                throw (Throwable)$getCallSiteArray[36].callConstructor($get$$class$groovy$lang$MissingMethodException(), ((Reference<Object>)name2).get(), $getCallSiteArray[37].callCurrent(this), ((Reference<Object>)args2).get());
            }
            ((Reference<Object>)result).set($getCallSiteArray[34].call(((Reference<Object>)mm).get(), $getCallSiteArray[35].callGroovyObjectGetProperty(this), ((Reference<Object>)args2).get()));
        }
        return ((Reference<Object>)result).get();
    }
    
    public Object call(final String name, final Object args) {
        final String name2 = (String)new Reference(name);
        final Object args2 = new Reference(args);
        return $getCallSiteArray()[38].callCurrent(this, ((Reference<Object>)name2).get(), ((Reference<Object>)args2).get());
    }
    
    public Object getMc() {
        $getCallSiteArray();
        return this.mc.get();
    }
    
    public Object getPrefix() {
        $getCallSiteArray();
        return this.prefix.get();
    }
    
    public Object getAssignName() {
        $getCallSiteArray();
        return this.assignName.get();
    }
    
    public Object getConfig() {
        $getCallSiteArray();
        return this.config.get();
    }
    
    public LinkedList getStack() {
        $getCallSiteArray();
        return (LinkedList)ScriptBytecodeAdapter.castToType(this.stack.get(), $get$$class$java$util$LinkedList());
    }
    
    public Object getPushStack() {
        $getCallSiteArray();
        return this.pushStack.get();
    }
    
    static {
        $const$2 = 2;
        $const$1 = 0;
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[39];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$ConfigSlurper$_parse_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (ConfigSlurper$_parse_closure5.$callSiteArray == null || ($createCallSiteArray = ConfigSlurper$_parse_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            ConfigSlurper$_parse_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigObject() {
        Class $class$groovy$util$ConfigObject;
        if (($class$groovy$util$ConfigObject = ConfigSlurper$_parse_closure5.$class$groovy$util$ConfigObject) == null) {
            $class$groovy$util$ConfigObject = (ConfigSlurper$_parse_closure5.$class$groovy$util$ConfigObject = class$("groovy.util.ConfigObject"));
        }
        return $class$groovy$util$ConfigObject;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$ConfigSlurper$_parse_closure5() {
        Class $class$groovy$util$ConfigSlurper$_parse_closure5;
        if (($class$groovy$util$ConfigSlurper$_parse_closure5 = ConfigSlurper$_parse_closure5.$class$groovy$util$ConfigSlurper$_parse_closure5) == null) {
            $class$groovy$util$ConfigSlurper$_parse_closure5 = (ConfigSlurper$_parse_closure5.$class$groovy$util$ConfigSlurper$_parse_closure5 = class$("groovy.util.ConfigSlurper$_parse_closure5"));
        }
        return $class$groovy$util$ConfigSlurper$_parse_closure5;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MissingMethodException() {
        Class $class$groovy$lang$MissingMethodException;
        if (($class$groovy$lang$MissingMethodException = ConfigSlurper$_parse_closure5.$class$groovy$lang$MissingMethodException) == null) {
            $class$groovy$lang$MissingMethodException = (ConfigSlurper$_parse_closure5.$class$groovy$lang$MissingMethodException = class$("groovy.lang.MissingMethodException"));
        }
        return $class$groovy$lang$MissingMethodException;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaMethod() {
        Class $class$groovy$lang$MetaMethod;
        if (($class$groovy$lang$MetaMethod = ConfigSlurper$_parse_closure5.$class$groovy$lang$MetaMethod) == null) {
            $class$groovy$lang$MetaMethod = (ConfigSlurper$_parse_closure5.$class$groovy$lang$MetaMethod = class$("groovy.lang.MetaMethod"));
        }
        return $class$groovy$lang$MetaMethod;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedList() {
        Class $class$java$util$LinkedList;
        if (($class$java$util$LinkedList = ConfigSlurper$_parse_closure5.$class$java$util$LinkedList) == null) {
            $class$java$util$LinkedList = (ConfigSlurper$_parse_closure5.$class$java$util$LinkedList = class$("java.util.LinkedList"));
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
