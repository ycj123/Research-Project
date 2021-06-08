// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.util.BuilderSupport;

public class StaxBuilder extends BuilderSupport
{
    private Object writer;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524205403;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public StaxBuilder(final Object xmlStreamWriter) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.writer = xmlStreamWriter;
        $getCallSiteArray[0].call(this.writer);
    }
    
    protected Object createNode(final Object name) {
        return $getCallSiteArray()[1].callCurrent(this, name, null, null);
    }
    
    protected Object createNode(final Object name, final Object value) {
        return $getCallSiteArray()[2].callCurrent(this, name, null, value);
    }
    
    protected Object createNode(final Object name, final Map attributes) {
        return $getCallSiteArray()[3].callCurrent(this, name, attributes, null);
    }
    
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[4].call(this.writer, $getCallSiteArray[5].call(name));
        if (DefaultTypeTransformation.booleanUnbox(attributes)) {
            $getCallSiteArray[6].call(attributes, new StaxBuilder$_createNode_closure1(this, this));
        }
        if (DefaultTypeTransformation.booleanUnbox(value)) {
            $getCallSiteArray[7].call(this.writer, $getCallSiteArray[8].call(value));
        }
        return name;
    }
    
    protected void nodeCompleted(final Object parent, final Object node) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[9].call(this.writer);
        if (!DefaultTypeTransformation.booleanUnbox(parent)) {
            $getCallSiteArray[10].call(this.writer);
            $getCallSiteArray[11].call(this.writer);
        }
    }
    
    protected void setParent(final Object parent, final Object child) {
        $getCallSiteArray();
    }
    
    static {
        StaxBuilder.__timeStamp__239_neverHappen1292524205403 = 0L;
        StaxBuilder.__timeStamp = 1292524205403L;
    }
    
    public Object getWriter() {
        return this.writer;
    }
    
    public void setWriter(final Object writer) {
        this.writer = writer;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[12];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StaxBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StaxBuilder.$callSiteArray == null || ($createCallSiteArray = StaxBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StaxBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
