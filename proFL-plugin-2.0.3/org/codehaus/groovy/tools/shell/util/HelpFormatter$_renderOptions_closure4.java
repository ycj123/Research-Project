// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class HelpFormatter$_renderOptions_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> opts;
    private Reference<Object> descPad;
    private Reference<Object> dpad;
    private Reference<Object> width;
    private Reference<Object> sb;
    private Reference<Object> maxPrefix;
    private Reference<Object> prefixes;
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$StringBuffer;
    
    public HelpFormatter$_renderOptions_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> opts, final Reference<Object> descPad, final Reference<Object> dpad, final Reference<Object> width, final Reference<Object> sb, final Reference<Object> maxPrefix, final Reference<Object> prefixes) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.opts = opts;
        this.descPad = descPad;
        this.dpad = dpad;
        this.width = width;
        this.sb = sb;
        this.maxPrefix = maxPrefix;
        this.prefixes = prefixes;
    }
    
    public Object doCall(final Object option, final Object i) {
        final Object option2 = new Reference(option);
        final Object j = new Reference(i);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object buff = new Reference($getCallSiteArray[0].callConstructor($get$$class$java$lang$StringBuffer(), $getCallSiteArray[1].call($getCallSiteArray[2].call(this.prefixes.get(), ((Reference<Object>)j).get()))));
        if (ScriptBytecodeAdapter.compareLessThan($getCallSiteArray[3].call(((Reference<Object>)buff).get()), this.maxPrefix.get())) {
            $getCallSiteArray[4].call(((Reference<Object>)buff).get(), $getCallSiteArray[5].call(" ", $getCallSiteArray[6].call(this.maxPrefix.get(), $getCallSiteArray[7].call(((Reference<Object>)buff).get()))));
        }
        $getCallSiteArray[8].call(((Reference<Object>)buff).get(), this.dpad.get());
        final Integer nextLineTabStop = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[9].call(this.maxPrefix.get(), this.descPad.get()), $get$$class$java$lang$Integer());
        final String text = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[10].call(((Reference<Object>)buff).get(), $getCallSiteArray[11].callGetProperty(((Reference<Object>)option2).get())), $get$$class$java$lang$String());
        $getCallSiteArray[12].callCurrent(this, this.sb.get(), this.width.get(), nextLineTabStop, text);
        if (ScriptBytecodeAdapter.compareLessThan(((Reference<Object>)j).get(), $getCallSiteArray[13].call($getCallSiteArray[14].call(this.opts.get()), HelpFormatter$_renderOptions_closure4.$const$0))) {
            return $getCallSiteArray[15].call(this.sb.get(), $getCallSiteArray[16].callGroovyObjectGetProperty(this));
        }
        return null;
    }
    
    public Object call(final Object option, final Object i) {
        final Object option2 = new Reference(option);
        final Object j = new Reference(i);
        return $getCallSiteArray()[17].callCurrent(this, ((Reference<Object>)option2).get(), ((Reference<Object>)j).get());
    }
    
    public List getOpts() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.opts.get(), $get$$class$java$util$List());
    }
    
    public int getDescPad() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.descPad.get(), $get$$class$java$lang$Integer()));
    }
    
    public String getDpad() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.dpad.get(), $get$$class$java$lang$String());
    }
    
    public int getWidth() {
        $getCallSiteArray();
        return DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(this.width.get(), $get$$class$java$lang$Integer()));
    }
    
    public StringBuffer getSb() {
        $getCallSiteArray();
        return (StringBuffer)ScriptBytecodeAdapter.castToType(this.sb.get(), $get$$class$java$lang$StringBuffer());
    }
    
    public Integer getMaxPrefix() {
        $getCallSiteArray();
        return (Integer)ScriptBytecodeAdapter.castToType(this.maxPrefix.get(), $get$$class$java$lang$Integer());
    }
    
    public List getPrefixes() {
        $getCallSiteArray();
        return (List)ScriptBytecodeAdapter.castToType(this.prefixes.get(), $get$$class$java$util$List());
    }
    
    static {
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[18];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$util$HelpFormatter$_renderOptions_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (HelpFormatter$_renderOptions_closure4.$callSiteArray == null || ($createCallSiteArray = HelpFormatter$_renderOptions_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            HelpFormatter$_renderOptions_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = HelpFormatter$_renderOptions_closure4.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (HelpFormatter$_renderOptions_closure4.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = HelpFormatter$_renderOptions_closure4.$class$java$util$List) == null) {
            $class$java$util$List = (HelpFormatter$_renderOptions_closure4.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = HelpFormatter$_renderOptions_closure4.$class$java$lang$String) == null) {
            $class$java$lang$String = (HelpFormatter$_renderOptions_closure4.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$StringBuffer() {
        Class $class$java$lang$StringBuffer;
        if (($class$java$lang$StringBuffer = HelpFormatter$_renderOptions_closure4.$class$java$lang$StringBuffer) == null) {
            $class$java$lang$StringBuffer = (HelpFormatter$_renderOptions_closure4.$class$java$lang$StringBuffer = class$("java.lang.StringBuffer"));
        }
        return $class$java$lang$StringBuffer;
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
