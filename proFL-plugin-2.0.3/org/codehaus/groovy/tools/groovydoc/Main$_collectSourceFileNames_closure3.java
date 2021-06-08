// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.groovydoc;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.List;
import groovy.lang.GroovyObject;
import java.io.File;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Main$_collectSourceFileNames_closure3 extends Closure implements GeneratedClosure
{
    private Reference<Object> sourceDirs;
    private Reference<Object> exclusions;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class array$$class$java$lang$String;
    
    public Main$_collectSourceFileNames_closure3(final Object _outerInstance, final Object _thisObject, final Reference<Object> sourceDirs, final Reference<Object> exclusions) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.sourceDirs = sourceDirs;
        this.exclusions = exclusions;
    }
    
    public Object doCall(final String pkgOrFile) {
        final String pkgOrFile2 = (String)new Reference(pkgOrFile);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.isCase(((Reference<Object>)pkgOrFile2).get(), this.exclusions.get())) {
            return null;
        }
        final File srcFile = (File)$getCallSiteArray[0].callConstructor($get$$class$java$io$File(), ((Reference<Object>)pkgOrFile2).get());
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[1].call(srcFile)) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[2].call(srcFile))) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[3].call($getCallSiteArray[4].callGroovyObjectGetProperty(this), ((Reference<Object>)pkgOrFile2).get());
            return null;
        }
        return $getCallSiteArray[5].call(this.sourceDirs.get(), new Main$_collectSourceFileNames_closure3_closure4(this, this.getThisObject(), (Reference<Object>)pkgOrFile2));
    }
    
    public Object call(final String pkgOrFile) {
        final String pkgOrFile2 = (String)new Reference(pkgOrFile);
        return $getCallSiteArray()[6].callCurrent(this, ((Reference<Object>)pkgOrFile2).get());
    }
    
    public String[] getSourceDirs() {
        $getCallSiteArray();
        return (String[])ScriptBytecodeAdapter.castToType(this.sourceDirs.get(), $get$array$$class$java$lang$String());
    }
    
    public List<String> getExclusions() {
        $getCallSiteArray();
        return (List<String>)ScriptBytecodeAdapter.castToType(this.exclusions.get(), $get$$class$java$util$List());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[7];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$groovydoc$Main$_collectSourceFileNames_closure3(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Main$_collectSourceFileNames_closure3.$callSiteArray == null || ($createCallSiteArray = Main$_collectSourceFileNames_closure3.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Main$_collectSourceFileNames_closure3.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = Main$_collectSourceFileNames_closure3.$class$java$util$List) == null) {
            $class$java$util$List = (Main$_collectSourceFileNames_closure3.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = Main$_collectSourceFileNames_closure3.$class$java$io$File) == null) {
            $class$java$io$File = (Main$_collectSourceFileNames_closure3.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$String() {
        Class array$$class$java$lang$String;
        if ((array$$class$java$lang$String = Main$_collectSourceFileNames_closure3.array$$class$java$lang$String) == null) {
            array$$class$java$lang$String = (Main$_collectSourceFileNames_closure3.array$$class$java$lang$String = class$("[Ljava.lang.String;"));
        }
        return array$$class$java$lang$String;
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
