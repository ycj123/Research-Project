// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Main$_collectSourceFileNames_closure3_closure4 extends Closure implements GeneratedClosure
{
    private Reference<Object> pkgOrFile;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$groovy$io$FileType;
    private static /* synthetic */ Class $class$java$io$File;
    
    public Main$_collectSourceFileNames_closure3_closure4(final Object _outerInstance, final Object _thisObject, final Reference<Object> pkgOrFile) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.pkgOrFile = pkgOrFile;
    }
    
    public Object doCall(final Object dirStr) {
        final Object dirStr2 = new Reference(dirStr);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object dir = $getCallSiteArray[0].callConstructor($get$$class$java$io$File(), ((Reference<Object>)dirStr2).get());
        final Object pkgOrFileSlashes = new Reference($getCallSiteArray[1].call(this.pkgOrFile.get(), ".", "/"));
        final Object candidate = new Reference($getCallSiteArray[2].callConstructor($get$$class$java$io$File(), dir, this.pkgOrFile.get()));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[3].call(((Reference<Object>)candidate).get())) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[4].call(((Reference<Object>)candidate).get()))) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[5].call($getCallSiteArray[6].callGroovyObjectGetProperty(this), this.pkgOrFile.get());
        }
        ((Reference<Object>)candidate).set($getCallSiteArray[7].callConstructor($get$$class$java$io$File(), dir, ((Reference<Object>)pkgOrFileSlashes).get()));
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].call(((Reference<Object>)candidate).get())) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].call(((Reference<Object>)candidate).get()))) ? Boolean.TRUE : Boolean.FALSE)) {
            return $getCallSiteArray[10].call(((Reference<Object>)candidate).get(), $getCallSiteArray[11].callGetProperty($get$$class$groovy$io$FileType()), ScriptBytecodeAdapter.bitwiseNegate(".*\\.(?:groovy|java)"), new Main$_collectSourceFileNames_closure3_closure4_closure5(this, this.getThisObject(), (Reference<Object>)pkgOrFileSlashes));
        }
        return null;
    }
    
    public String getPkgOrFile() {
        $getCallSiteArray();
        return (String)ScriptBytecodeAdapter.castToType(this.pkgOrFile.get(), $get$$class$java$lang$String());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$groovydoc$Main$_collectSourceFileNames_closure3_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main$_collectSourceFileNames_closure3_closure4.$callSiteArray == null || ($createCallSiteArray = Main$_collectSourceFileNames_closure3_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main$_collectSourceFileNames_closure3_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Main$_collectSourceFileNames_closure3_closure4.$class$java$lang$String) == null) {
            $class$java$lang$String = (Main$_collectSourceFileNames_closure3_closure4.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$io$FileType() {
        Class $class$groovy$io$FileType;
        if (($class$groovy$io$FileType = Main$_collectSourceFileNames_closure3_closure4.$class$groovy$io$FileType) == null) {
            $class$groovy$io$FileType = (Main$_collectSourceFileNames_closure3_closure4.$class$groovy$io$FileType = class$("groovy.io.FileType"));
        }
        return $class$groovy$io$FileType;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Main$_collectSourceFileNames_closure3_closure4.$class$java$io$File) == null) {
            $class$java$io$File = (Main$_collectSourceFileNames_closure3_closure4.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
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
