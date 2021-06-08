// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Reference;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class GrapeIvy$_getDependencies_closure4 extends Closure implements GeneratedClosure
{
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$ivy$core$event$resolve$StartResolveEvent;
    private static /* synthetic */ Class $class$org$apache$ivy$core$event$download$PrepareDownloadEvent;
    
    public GrapeIvy$_getDependencies_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final Object ivyEvent) {
        final Object ivyEvent2 = new Reference(ivyEvent);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object value = ((Reference<Object>)ivyEvent2).get();
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$org$apache$ivy$core$event$resolve$StartResolveEvent())) {
            return $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGetProperty(((Reference<Object>)ivyEvent2).get())), new GrapeIvy$_getDependencies_closure4_closure12(this, this.getThisObject()));
        }
        if (ScriptBytecodeAdapter.isCase(value, $get$$class$org$apache$ivy$core$event$download$PrepareDownloadEvent())) {
            return $getCallSiteArray[3].call($getCallSiteArray[4].callGetProperty(((Reference<Object>)ivyEvent2).get()), new GrapeIvy$_getDependencies_closure4_closure13(this, this.getThisObject()));
        }
        return null;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[5];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy$_getDependencies_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy$_getDependencies_closure4.$callSiteArray == null || ($createCallSiteArray = GrapeIvy$_getDependencies_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy$_getDependencies_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$event$resolve$StartResolveEvent() {
        Class $class$org$apache$ivy$core$event$resolve$StartResolveEvent;
        if (($class$org$apache$ivy$core$event$resolve$StartResolveEvent = GrapeIvy$_getDependencies_closure4.$class$org$apache$ivy$core$event$resolve$StartResolveEvent) == null) {
            $class$org$apache$ivy$core$event$resolve$StartResolveEvent = (GrapeIvy$_getDependencies_closure4.$class$org$apache$ivy$core$event$resolve$StartResolveEvent = class$("org.apache.ivy.core.event.resolve.StartResolveEvent"));
        }
        return $class$org$apache$ivy$core$event$resolve$StartResolveEvent;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$event$download$PrepareDownloadEvent() {
        Class $class$org$apache$ivy$core$event$download$PrepareDownloadEvent;
        if (($class$org$apache$ivy$core$event$download$PrepareDownloadEvent = GrapeIvy$_getDependencies_closure4.$class$org$apache$ivy$core$event$download$PrepareDownloadEvent) == null) {
            $class$org$apache$ivy$core$event$download$PrepareDownloadEvent = (GrapeIvy$_getDependencies_closure4.$class$org$apache$ivy$core$event$download$PrepareDownloadEvent = class$("org.apache.ivy.core.event.download.PrepareDownloadEvent"));
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
