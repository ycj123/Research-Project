// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.Set;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_closure1_closure26 extends Closure implements GeneratedClosure
{
    private Reference<Object> downloadedArtifacts;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$groovy$ui$Console$_closure1_closure26;
    
    public Console$_closure1_closure26(final Object _outerInstance, final Object _thisObject, final Reference<Object> downloadedArtifacts) {
        final Reference downloadedArtifacts2 = new Reference((T)downloadedArtifacts);
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.downloadedArtifacts = downloadedArtifacts2.get();
    }
    
    public Object doCall(final Object it) {
        final Object it2 = new Reference(it);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = new Reference($getCallSiteArray[0].call(((Reference<Object>)it2).get()));
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(this.downloadedArtifacts.get(), ((Reference<Object>)name).get()))) {
            $getCallSiteArray[2].call(this.downloadedArtifacts.get(), ((Reference<Object>)name).get());
            final GStringImpl messageArgument = new GStringImpl(new Object[] { ((Reference<Object>)name).get() }, new String[] { "Downloading artifact ", " ..." });
            ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$ui$Console$_closure1_closure26(), $getCallSiteArray[3].callGroovyObjectGetProperty(this), "text");
            return messageArgument;
        }
        return null;
    }
    
    public Set<String> getDownloadedArtifacts() {
        $getCallSiteArray();
        return (Set<String>)ScriptBytecodeAdapter.castToType(this.downloadedArtifacts.get(), $get$$class$java$util$Set());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[4];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_closure1_closure26(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_closure1_closure26.$callSiteArray == null || ($createCallSiteArray = Console$_closure1_closure26.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_closure1_closure26.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = Console$_closure1_closure26.$class$java$util$Set) == null) {
            $class$java$util$Set = (Console$_closure1_closure26.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$ui$Console$_closure1_closure26() {
        Class $class$groovy$ui$Console$_closure1_closure26;
        if (($class$groovy$ui$Console$_closure1_closure26 = Console$_closure1_closure26.$class$groovy$ui$Console$_closure1_closure26) == null) {
            $class$groovy$ui$Console$_closure1_closure26 = (Console$_closure1_closure26.$class$groovy$ui$Console$_closure1_closure26 = class$("groovy.ui.Console$_closure1_closure26"));
        }
        return $class$groovy$ui$Console$_closure1_closure26;
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
