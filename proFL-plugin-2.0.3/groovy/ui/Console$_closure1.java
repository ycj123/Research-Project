// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import java.util.Set;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class Console$_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> resolvedDependencies;
    private Reference<Object> downloadedArtifacts;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$ivy$core$event$resolve$StartResolveEvent;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$org$apache$ivy$core$event$download$PrepareDownloadEvent;
    
    public Console$_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> resolvedDependencies, final Reference<Object> downloadedArtifacts) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.resolvedDependencies = resolvedDependencies;
        this.downloadedArtifacts = downloadedArtifacts;
    }
    
    public Object doCall(final Object ivyEvent) {
        final Object ivyEvent2 = new Reference(ivyEvent);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object value = ((Reference<Object>)ivyEvent2).get();
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$org$apache$ivy$core$event$resolve$StartResolveEvent())) {
            final CallSite callSite = $getCallSiteArray[0];
            final Object callGetProperty = $getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGetProperty(((Reference<Object>)ivyEvent2).get()));
            final Object thisObject = this.getThisObject();
            final Reference resolvedDependencies = this.resolvedDependencies;
            return callSite.call(callGetProperty, new Console$_closure1_closure25(this, thisObject, resolvedDependencies));
        }
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$org$apache$ivy$core$event$download$PrepareDownloadEvent())) {
            final CallSite callSite2 = $getCallSiteArray[3];
            final Object callGetProperty2 = $getCallSiteArray[4].callGetProperty(((Reference<Object>)ivyEvent2).get());
            final Object thisObject2 = this.getThisObject();
            final Reference downloadedArtifacts = this.downloadedArtifacts;
            return callSite2.call(callGetProperty2, new Console$_closure1_closure26(this, thisObject2, downloadedArtifacts));
        }
        return null;
    }
    
    public Set<String> getResolvedDependencies() {
        $getCallSiteArray();
        return (Set<String>)ScriptBytecodeAdapter.castToType(this.resolvedDependencies.get(), $get$$class$java$util$Set());
    }
    
    public Set<String> getDownloadedArtifacts() {
        $getCallSiteArray();
        return (Set<String>)ScriptBytecodeAdapter.castToType(this.downloadedArtifacts.get(), $get$$class$java$util$Set());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$ui$Console$_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Console$_closure1.$callSiteArray == null || ($createCallSiteArray = Console$_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Console$_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$event$resolve$StartResolveEvent() {
        Class $class$org$apache$ivy$core$event$resolve$StartResolveEvent;
        if (($class$org$apache$ivy$core$event$resolve$StartResolveEvent = Console$_closure1.$class$org$apache$ivy$core$event$resolve$StartResolveEvent) == null) {
            $class$org$apache$ivy$core$event$resolve$StartResolveEvent = (Console$_closure1.$class$org$apache$ivy$core$event$resolve$StartResolveEvent = class$("org.apache.ivy.core.event.resolve.StartResolveEvent"));
        }
        return $class$org$apache$ivy$core$event$resolve$StartResolveEvent;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = Console$_closure1.$class$java$util$Set) == null) {
            $class$java$util$Set = (Console$_closure1.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$event$download$PrepareDownloadEvent() {
        Class $class$org$apache$ivy$core$event$download$PrepareDownloadEvent;
        if (($class$org$apache$ivy$core$event$download$PrepareDownloadEvent = Console$_closure1.$class$org$apache$ivy$core$event$download$PrepareDownloadEvent) == null) {
            $class$org$apache$ivy$core$event$download$PrepareDownloadEvent = (Console$_closure1.$class$org$apache$ivy$core$event$download$PrepareDownloadEvent = class$("org.apache.ivy.core.event.download.PrepareDownloadEvent"));
        }
        return $class$org$apache$ivy$core$event$download$PrepareDownloadEvent;
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
