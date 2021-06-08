// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class FileNameByRegexFinder$_getFileNames_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> result;
    private Reference<Object> pattern;
    private Reference<Object> excludesPattern;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public FileNameByRegexFinder$_getFileNames_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> result, final Reference<Object> pattern, final Reference<Object> excludesPattern) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.result = result;
        this.pattern = pattern;
        this.excludesPattern = excludesPattern;
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.findRegex($getCallSiteArray[0].callGetProperty(((Reference<Object>)it2).get()), this.pattern.get())) && DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(this.excludesPattern.get()) && DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.findRegex($getCallSiteArray[1].callGetProperty(((Reference<Object>)it2).get()), this.excludesPattern.get()))) ? Boolean.FALSE : Boolean.TRUE)) ? Boolean.TRUE : Boolean.FALSE)) {
            return $getCallSiteArray[2].call(this.result.get(), $getCallSiteArray[3].callGetProperty(((Reference<Object>)it2).get()));
        }
        return null;
    }
    
    public Object getResult() {
        $getCallSiteArray();
        return this.result.get();
    }
    
    public String getPattern() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.pattern.get(), $get$$class$java$lang$String());
    }
    
    public String getExcludesPattern() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.excludesPattern.get(), $get$$class$java$lang$String());
    }
    
    public Object doCall() {
        return $getCallSiteArray()[4].callCurrent(this, ScriptBytecodeAdapter.createPojoWrapper(null, $get$$class$java$lang$Object()));
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$util$FileNameByRegexFinder$_getFileNames_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (FileNameByRegexFinder$_getFileNames_closure1.$callSiteArray == null || ($createCallSiteArray = FileNameByRegexFinder$_getFileNames_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            FileNameByRegexFinder$_getFileNames_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = FileNameByRegexFinder$_getFileNames_closure1.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (FileNameByRegexFinder$_getFileNames_closure1.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = FileNameByRegexFinder$_getFileNames_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (FileNameByRegexFinder$_getFileNames_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
