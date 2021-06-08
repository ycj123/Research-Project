// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.io.File;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Main$_collectSourceFileNames_closure3_closure4_closure5 extends Closure implements GeneratedClosure
{
    private Reference<Object> pkgOrFileSlashes;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public Main$_collectSourceFileNames_closure3_closure4_closure5(final Object _outerInstance, final Object _thisObject, final Reference<Object> pkgOrFileSlashes) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.pkgOrFileSlashes = pkgOrFileSlashes;
    }
    
    public Object doCall(final File f) {
        final File f2 = (File)new Reference(f);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), $getCallSiteArray[2].call($getCallSiteArray[3].call(this.pkgOrFileSlashes.get(), "/"), $getCallSiteArray[4].call(((Reference<Object>)f2).get())));
    }
    
    public Object call(final File f) {
        final File f2 = (File)new Reference(f);
        return $getCallSiteArray()[5].callCurrent(this, ((Reference<Object>)f2).get());
    }
    
    public Object getPkgOrFileSlashes() {
        $getCallSiteArray();
        return this.pkgOrFileSlashes.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$groovydoc$Main$_collectSourceFileNames_closure3_closure4_closure5(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main$_collectSourceFileNames_closure3_closure4_closure5.$callSiteArray == null || ($createCallSiteArray = Main$_collectSourceFileNames_closure3_closure4_closure5.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main$_collectSourceFileNames_closure3_closure4_closure5.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
