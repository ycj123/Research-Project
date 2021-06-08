// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6;
    
    public AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty("ConstructorCall - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "ConstructorCallExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Spread - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "SpreadExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("ArgumentList - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "ArgumentListExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("MethodCall - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "MethodCallExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("GString - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "GStringExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Attribute - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "AttributeExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Declaration - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "DeclarationExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Variable - $expression.name : $expression.type", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "VariableExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Constant - $expression.value : $expression.type", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "ConstantExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Binary - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "BinaryExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Class - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "ClassExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Boolean - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "BooleanExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Array - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "ArrayExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("List - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "ListExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Tuple - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "TupleExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Field - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "FieldExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Property - $expression.propertyAsString", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "PropertyExpression");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Not - $expression.text", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "NotExpression");
        final String messageArgument = "Cast - $expression.text";
        ScriptBytecodeAdapter.setGroovyObjectProperty(messageArgument, $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), this, "CastExpression");
        return messageArgument;
    }
    
    public Object doCall() {
        return $getCallSiteArray()[0].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$callSiteArray == null || ($createCallSiteArray = AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6() {
        Class $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6;
        if (($class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6 = AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6) == null) {
            $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6 = (AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6.$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6 = class$("groovy.inspect.swingui.AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6"));
        }
        return $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6;
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
