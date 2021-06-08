// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_finishException_closure10 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$javax$swing$text$SimpleAttributeSet;
    private static /* synthetic */ Class $class$org$codehaus$groovy$syntax$SyntaxException;
    private static /* synthetic */ Class $class$javax$swing$text$html$HTML$Tag;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$javax$swing$text$html$HTML$Attribute;
    
    public Console$_finishException_closure10(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object error) {
        final Object error2 = new Reference(error);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (((Reference<Object>)error2).get() instanceof SyntaxErrorMessage) {
            final SyntaxException se = (SyntaxException)ScriptBytecodeAdapter.castToType($getCallSiteArray[0].callGetProperty(((Reference<Object>)error2).get()), $get$$class$org$codehaus$groovy$syntax$SyntaxException());
            final Integer errorLine = (Integer)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callGetProperty(se), $get$$class$java$lang$Integer());
            final String message = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callGetProperty(se), $get$$class$java$lang$String());
            Object object;
            if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[3].callGetPropertySafe($getCallSiteArray[4].callGroovyObjectGetProperty(this)))) {
                object = $getCallSiteArray[5].callGroovyObjectGetProperty(this);
            }
            final String scriptFileName = (String)ScriptBytecodeAdapter.castToType(object, $get$$class$java$lang$String());
            final Object doc = $getCallSiteArray[6].callGetProperty($getCallSiteArray[7].callGroovyObjectGetProperty(this));
            final Object style = $getCallSiteArray[8].callGroovyObjectGetProperty(this);
            final Object hrefAttr = $getCallSiteArray[9].callConstructor($get$$class$javax$swing$text$SimpleAttributeSet());
            $getCallSiteArray[10].call(hrefAttr, $getCallSiteArray[11].callGetProperty($get$$class$javax$swing$text$html$HTML$Attribute()), $getCallSiteArray[12].call($getCallSiteArray[13].call($getCallSiteArray[14].call("file://", scriptFileName), ":"), errorLine));
            $getCallSiteArray[15].call(style, $getCallSiteArray[16].callGetProperty($get$$class$javax$swing$text$html$HTML$Tag()), hrefAttr);
            $getCallSiteArray[17].call(doc, $getCallSiteArray[18].callGetProperty(doc), $getCallSiteArray[19].call(message, " at "), $getCallSiteArray[20].callGroovyObjectGetProperty(this));
            return $getCallSiteArray[21].call(doc, $getCallSiteArray[22].callGetProperty(doc), new GStringImpl(new Object[] { $getCallSiteArray[23].callGetProperty(se), $getCallSiteArray[24].callGetProperty(se) }, new String[] { "line: ", ", column: ", "\n\n" }), style);
        }
        if (((Reference<Object>)error2).get() instanceof Throwable) {
            return $getCallSiteArray[25].callCurrent(this, ((Reference<Object>)error2).get());
        }
        if (((Reference<Object>)error2).get() instanceof ExceptionMessage) {
            return $getCallSiteArray[26].callCurrent(this, $getCallSiteArray[27].callGetProperty(((Reference<Object>)error2).get()));
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[28];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_finishException_closure10(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_finishException_closure10.$callSiteArray == null || ($createCallSiteArray = Console$_finishException_closure10.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_finishException_closure10.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = Console$_finishException_closure10.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (Console$_finishException_closure10.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$SimpleAttributeSet() {
        Class $class$javax$swing$text$SimpleAttributeSet;
        if (($class$javax$swing$text$SimpleAttributeSet = Console$_finishException_closure10.$class$javax$swing$text$SimpleAttributeSet) == null) {
            $class$javax$swing$text$SimpleAttributeSet = (Console$_finishException_closure10.$class$javax$swing$text$SimpleAttributeSet = class$("javax.swing.text.SimpleAttributeSet"));
        }
        return $class$javax$swing$text$SimpleAttributeSet;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$syntax$SyntaxException() {
        Class $class$org$codehaus$groovy$syntax$SyntaxException;
        if (($class$org$codehaus$groovy$syntax$SyntaxException = Console$_finishException_closure10.$class$org$codehaus$groovy$syntax$SyntaxException) == null) {
            $class$org$codehaus$groovy$syntax$SyntaxException = (Console$_finishException_closure10.$class$org$codehaus$groovy$syntax$SyntaxException = class$("org.codehaus.groovy.syntax.SyntaxException"));
        }
        return $class$org$codehaus$groovy$syntax$SyntaxException;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$html$HTML$Tag() {
        Class $class$javax$swing$text$html$HTML$Tag;
        if (($class$javax$swing$text$html$HTML$Tag = Console$_finishException_closure10.$class$javax$swing$text$html$HTML$Tag) == null) {
            $class$javax$swing$text$html$HTML$Tag = (Console$_finishException_closure10.$class$javax$swing$text$html$HTML$Tag = class$("javax.swing.text.html.HTML$Tag"));
        }
        return $class$javax$swing$text$html$HTML$Tag;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Console$_finishException_closure10.$class$java$lang$String) == null) {
            $class$java$lang$String = (Console$_finishException_closure10.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$text$html$HTML$Attribute() {
        Class $class$javax$swing$text$html$HTML$Attribute;
        if (($class$javax$swing$text$html$HTML$Attribute = Console$_finishException_closure10.$class$javax$swing$text$html$HTML$Attribute) == null) {
            $class$javax$swing$text$html$HTML$Attribute = (Console$_finishException_closure10.$class$javax$swing$text$html$HTML$Attribute = class$("javax.swing.text.html.HTML$Attribute"));
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
