// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.util;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class StringUtil$_tr_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> replacement;
    private Reference<Object> source;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public StringUtil$_tr_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> replacement, final Reference<Object> source) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.replacement = replacement;
        this.source = source;
    }
    
    public Object doCall(final Object original) {
        final Object original2 = new Reference(original);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[0].call(this.source.get(), ((Reference<Object>)original2).get()))) {
            return $getCallSiteArray[1].call(this.replacement.get(), $getCallSiteArray[2].call(this.source.get(), ((Reference<Object>)original2).get()));
        }
        return ((Reference<Object>)original2).get();
    }
    
    public String getReplacement() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.replacement.get(), $get$$class$java$lang$String());
    }
    
    public String getSource() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.source.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[3];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$util$StringUtil$_tr_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StringUtil$_tr_closure1.$callSiteArray == null || ($createCallSiteArray = StringUtil$_tr_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StringUtil$_tr_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = StringUtil$_tr_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (StringUtil$_tr_closure1.$class$java$lang$String = class$("java.lang.String"));
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
