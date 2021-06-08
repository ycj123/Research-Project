// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowserProperties$_run_closure1_closure2_closure3_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4;
    
    public AstBrowserProperties$_run_closure1_closure2_closure3_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object it) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        ScriptBytecodeAdapter.setGroovyObjectProperty("ClassNode - $expression.name", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "ClassNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("InnerClassNode - $expression.name", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "InnerClassNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("ConstructorNode - $expression.name", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "ConstructorNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("MethodNode - $expression.name", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "MethodNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("FieldNode - $expression.name : $expression.type", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "FieldNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("PropertyNode - ${expression.field?.name} : ${expression.field?.type}", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "PropertyNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("AnnotationNode - ${expression.classNode?.name}", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "AnnotationNode");
        ScriptBytecodeAdapter.setGroovyObjectProperty("Parameter - $expression.name", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "Parameter");
        ScriptBytecodeAdapter.setGroovyObjectProperty("DynamicVariable - $expression.name", $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), this, "DynamicVariable");
        $getCallSiteArray[0].callCurrent(this, new AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure5(this, this.getThisObject()));
        return $getCallSiteArray[1].callCurrent(this, new AstBrowserProperties$_run_closure1_closure2_closure3_closure4_closure6(this, this.getThisObject()));
    }
    
    public Object doCall() {
        return $getCallSiteArray()[2].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$callSiteArray == null || ($createCallSiteArray = AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4() {
        Class $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4;
        if (($class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4 = AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4) == null) {
            $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4 = (AstBrowserProperties$_run_closure1_closure2_closure3_closure4.$class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4 = class$("groovy.inspect.swingui.AstBrowserProperties$_run_closure1_closure2_closure3_closure4"));
        }
        return $class$groovy$inspect$swingui$AstBrowserProperties$_run_closure1_closure2_closure3_closure4;
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
