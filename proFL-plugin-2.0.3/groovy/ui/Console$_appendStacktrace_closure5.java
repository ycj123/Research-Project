// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_appendStacktrace_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> doc;
    private Reference<Object> stacktracePattern;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$javax$swing$text$SimpleAttributeSet;
    private static /* synthetic */ Class $class$javax$swing$text$html$HTML$Tag;
    private static /* synthetic */ Class $class$javax$swing$text$html$HTML$Attribute;
    
    public Console$_appendStacktrace_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> doc, final Reference<Object> stacktracePattern) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.doc = doc;
        this.stacktracePattern = stacktracePattern;
    }
    
    public Object doCall(final Object line) {
        final Object line2 = new Reference(line);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Integer initialLength = (Integer)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callGetProperty(this.doc.get()), $get$$class$java$lang$Integer()));
        final Object matcher = new Reference(ScriptBytecodeAdapter.findRegex(((Reference<Object>)line2).get(), this.stacktracePattern.get()));
        final Object fileName = DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(((Reference<Object>)matcher).get())) ? $getCallSiteArray[2].call($getCallSiteArray[3].call(((Reference<Object>)matcher).get(), Console$_appendStacktrace_closure5.$const$0), Console$_appendStacktrace_closure5.$const$1) : "";
        if (DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual(fileName, $getCallSiteArray[4].callGetPropertySafe($getCallSiteArray[5].callGroovyObjectGetProperty(this))) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[6].call(fileName, $getCallSiteArray[7].callGroovyObjectGetProperty(this)))) ? Boolean.FALSE : Boolean.TRUE)) {
            final Object fileNameAndLineNumber = $getCallSiteArray[8].call($getCallSiteArray[9].call(((Reference<Object>)matcher).get(), Console$_appendStacktrace_closure5.$const$0), Console$_appendStacktrace_closure5.$const$2);
            final Object length = $getCallSiteArray[10].call(fileNameAndLineNumber);
            final Object index = $getCallSiteArray[11].call(((Reference<Object>)line2).get(), fileNameAndLineNumber);
            final Object style = $getCallSiteArray[12].callGroovyObjectGetProperty(this);
            final Object hrefAttr = $getCallSiteArray[13].callConstructor($get$$class$javax$swing$text$SimpleAttributeSet());
            $getCallSiteArray[14].call(hrefAttr, $getCallSiteArray[15].callGetProperty($get$$class$javax$swing$text$html$HTML$Attribute()), $getCallSiteArray[16].call("file://", fileNameAndLineNumber));
            $getCallSiteArray[17].call(style, $getCallSiteArray[18].callGetProperty($get$$class$javax$swing$text$html$HTML$Tag()), hrefAttr);
            $getCallSiteArray[19].call(this.doc.get(), ((Reference<Object>)initialLength).get(), $getCallSiteArray[20].call(((Reference<Object>)line2).get(), ScriptBytecodeAdapter.createRange(Console$_appendStacktrace_closure5.$const$0, index, false)), $getCallSiteArray[21].callGroovyObjectGetProperty(this));
            $getCallSiteArray[22].call(this.doc.get(), $getCallSiteArray[23].call(((Reference<Object>)initialLength).get(), index), $getCallSiteArray[24].call(((Reference<Object>)line2).get(), ScriptBytecodeAdapter.createRange(index, $getCallSiteArray[25].call(index, length), false)), style);
            return $getCallSiteArray[26].call(this.doc.get(), $getCallSiteArray[27].call($getCallSiteArray[28].call(((Reference<Object>)initialLength).get(), index), length), $getCallSiteArray[29].call($getCallSiteArray[30].call(((Reference<Object>)line2).get(), ScriptBytecodeAdapter.createRange($getCallSiteArray[31].call(index, length), Console$_appendStacktrace_closure5.$const$3, true)), "\n"), $getCallSiteArray[32].callGroovyObjectGetProperty(this));
        }
        return $getCallSiteArray[33].call(this.doc.get(), ((Reference<Object>)initialLength).get(), $getCallSiteArray[34].call(((Reference<Object>)line2).get(), "\n"), $getCallSiteArray[35].callGroovyObjectGetProperty(this));
    }
    
    public Object getDoc() {
        $getCallSiteArray();
        return this.doc.get();
    }
    
    public Object getStacktracePattern() {
        $getCallSiteArray();
        return this.stacktracePattern.get();
    }
    
    static {
        $const$3 = -1;
        $const$2 = -6;
        $const$1 = -5;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[36];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_appendStacktrace_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_appendStacktrace_closure5.$callSiteArray == null || ($createCallSiteArray = Console$_appendStacktrace_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_appendStacktrace_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = Console$_appendStacktrace_closure5.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (Console$_appendStacktrace_closure5.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$SimpleAttributeSet() {
        Class $class$javax$swing$text$SimpleAttributeSet;
        if (($class$javax$swing$text$SimpleAttributeSet = Console$_appendStacktrace_closure5.$class$javax$swing$text$SimpleAttributeSet) == null) {
            $class$javax$swing$text$SimpleAttributeSet = (Console$_appendStacktrace_closure5.$class$javax$swing$text$SimpleAttributeSet = class$("javax.swing.text.SimpleAttributeSet"));
        }
        return $class$javax$swing$text$SimpleAttributeSet;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$html$HTML$Tag() {
        Class $class$javax$swing$text$html$HTML$Tag;
        if (($class$javax$swing$text$html$HTML$Tag = Console$_appendStacktrace_closure5.$class$javax$swing$text$html$HTML$Tag) == null) {
            $class$javax$swing$text$html$HTML$Tag = (Console$_appendStacktrace_closure5.$class$javax$swing$text$html$HTML$Tag = class$("javax.swing.text.html.HTML$Tag"));
        }
        return $class$javax$swing$text$html$HTML$Tag;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$html$HTML$Attribute() {
        Class $class$javax$swing$text$html$HTML$Attribute;
        if (($class$javax$swing$text$html$HTML$Attribute = Console$_appendStacktrace_closure5.$class$javax$swing$text$html$HTML$Attribute) == null) {
            $class$javax$swing$text$html$HTML$Attribute = (Console$_appendStacktrace_closure5.$class$javax$swing$text$html$HTML$Attribute = class$("javax.swing.text.html.HTML$Attribute"));
        }
        return $class$javax$swing$text$html$HTML$Attribute;
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
