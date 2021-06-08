// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Buildable;
import groovy.lang.Closure;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.xml.sax.helpers.AttributesImpl;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.xml.streamingmarkupsupport.AbstractStreamingBuilder;

public class StreamingSAXBuilder extends AbstractStreamingBuilder implements GroovyObject
{
    private Object pendingStack;
    private Object commentClosure;
    private Object piClosure;
    private Object noopClosure;
    private Object tagClosure;
    private Object builder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Integer $const$1;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204668;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$xml$StreamingSAXBuilder;
    private static /* synthetic */ Class $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
    private static /* synthetic */ Class $class$groovy$lang$GroovyRuntimeException;
    
    public StreamingSAXBuilder() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.pendingStack = ScriptBytecodeAdapter.createList(new Object[0]);
        this.commentClosure = new StreamingSAXBuilder$_closure1(this, this);
        this.piClosure = new StreamingSAXBuilder$_closure2(this, this);
        this.noopClosure = new StreamingSAXBuilder$_closure3(this, this);
        this.tagClosure = new StreamingSAXBuilder$_closure4(this, this);
        this.builder = null;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ScriptBytecodeAdapter.createMap(new Object[] { "yield", this.noopClosure, "yieldUnescaped", this.noopClosure, "comment", this.commentClosure, "pi", this.piClosure }));
        final Object nsSpecificTags = ScriptBytecodeAdapter.createMap(new Object[] { ":", ScriptBytecodeAdapter.createList(new Object[] { this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0]) }), "http://www.w3.org/XML/1998/namespace", ScriptBytecodeAdapter.createList(new Object[] { this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0]) }), "http://www.codehaus.org/Groovy/markup/keywords", ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[2].callGroovyObjectGetProperty(this), this.tagClosure, $getCallSiteArray[3].callGroovyObjectGetProperty(this) }) });
        this.builder = $getCallSiteArray[4].callConstructor($get$$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder(), nsSpecificTags);
    }
    
    private Object addAttributes(final AttributesImpl attributes, final Object key, final Object value, final Object namespaces) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[5].call(key, "$"))) {
            return $getCallSiteArray[16].call(attributes, ArrayUtil.createArray("", key, key, "CDATA", new GStringImpl(new Object[] { value }, new String[] { "", "" })));
        }
        final Object parts = $getCallSiteArray[6].call(key, "$");
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call(namespaces, $getCallSiteArray[8].call(parts, StreamingSAXBuilder.$const$0)))) {
            final Object namespaceUri = $getCallSiteArray[9].call(namespaces, $getCallSiteArray[10].call(parts, StreamingSAXBuilder.$const$0));
            return $getCallSiteArray[11].call(attributes, ArrayUtil.createArray(namespaceUri, $getCallSiteArray[12].call(parts, StreamingSAXBuilder.$const$1), new GStringImpl(new Object[] { $getCallSiteArray[13].call(parts, StreamingSAXBuilder.$const$0), $getCallSiteArray[14].call(parts, StreamingSAXBuilder.$const$1) }, new String[] { "", ":", "" }), "CDATA", new GStringImpl(new Object[] { value }, new String[] { "", "" })));
        }
        throw (Throwable)$getCallSiteArray[15].callConstructor($get$$class$groovy$lang$GroovyRuntimeException(), new GStringImpl(new Object[] { key }, new String[] { "bad attribute namespace tag in ", "" }));
    }
    
    private Object processBody(final Object body, final Object doc, final Object contentHandler) {
        final Object doc2 = new Reference(doc);
        final Object contentHandler2 = new Reference(contentHandler);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (body instanceof Closure) {
            final Object body2 = $getCallSiteArray[17].call(body);
            ScriptBytecodeAdapter.setProperty(((Reference<Object>)doc2).get(), $get$$class$groovy$xml$StreamingSAXBuilder(), body2, "delegate");
            return $getCallSiteArray[18].call(body2, ((Reference<Object>)doc2).get());
        }
        if (body instanceof Buildable) {
            return $getCallSiteArray[19].call(body, ((Reference<Object>)doc2).get());
        }
        return $getCallSiteArray[20].call(body, new StreamingSAXBuilder$_processBody_closure5(this, this, (Reference<Object>)contentHandler2, (Reference<Object>)doc2));
    }
    
    private Object processBodyPart(final Object part, final Object doc, final Object contentHandler) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (part instanceof Closure) {
            final Object body1 = $getCallSiteArray[21].call(part);
            ScriptBytecodeAdapter.setProperty(doc, $get$$class$groovy$xml$StreamingSAXBuilder(), body1, "delegate");
            return $getCallSiteArray[22].call(body1, doc);
        }
        if (part instanceof Buildable) {
            return $getCallSiteArray[23].call(part, doc);
        }
        final Object chars = $getCallSiteArray[24].call(part);
        return $getCallSiteArray[25].call(contentHandler, chars, StreamingSAXBuilder.$const$0, $getCallSiteArray[26].call(chars));
    }
    
    public Object bind(final Object closure) {
        final Object boundClosure = new Reference($getCallSiteArray()[27].call(this.builder, closure));
        return new StreamingSAXBuilder$_bind_closure6(this, this, (Reference<Object>)boundClosure);
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$xml$StreamingSAXBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = StreamingSAXBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (StreamingSAXBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        StreamingSAXBuilder.__timeStamp__239_neverHappen1292524204668 = 0L;
        StreamingSAXBuilder.__timeStamp = 1292524204668L;
        $const$1 = 1;
        $const$0 = 0;
    }
    
    public Object getPendingStack() {
        return this.pendingStack;
    }
    
    public void setPendingStack(final Object pendingStack) {
        this.pendingStack = pendingStack;
    }
    
    public Object getCommentClosure() {
        return this.commentClosure;
    }
    
    public void setCommentClosure(final Object commentClosure) {
        this.commentClosure = commentClosure;
    }
    
    public Object getPiClosure() {
        return this.piClosure;
    }
    
    public void setPiClosure(final Object piClosure) {
        this.piClosure = piClosure;
    }
    
    public Object getNoopClosure() {
        return this.noopClosure;
    }
    
    public void setNoopClosure(final Object noopClosure) {
        this.noopClosure = noopClosure;
    }
    
    public Object getTagClosure() {
        return this.tagClosure;
    }
    
    public void setTagClosure(final Object tagClosure) {
        this.tagClosure = tagClosure;
    }
    
    @Override
    public Object getBuilder() {
        return this.builder;
    }
    
    @Override
    public void setBuilder(final Object builder) {
        this.builder = builder;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[28];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingSAXBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingSAXBuilder.$callSiteArray == null || ($createCallSiteArray = StreamingSAXBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingSAXBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = StreamingSAXBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (StreamingSAXBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingSAXBuilder() {
        Class $class$groovy$xml$StreamingSAXBuilder;
        if (($class$groovy$xml$StreamingSAXBuilder = StreamingSAXBuilder.$class$groovy$xml$StreamingSAXBuilder) == null) {
            $class$groovy$xml$StreamingSAXBuilder = (StreamingSAXBuilder.$class$groovy$xml$StreamingSAXBuilder = class$("groovy.xml.StreamingSAXBuilder"));
        }
        return $class$groovy$xml$StreamingSAXBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder() {
        Class $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
        if (($class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = StreamingSAXBuilder.$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder) == null) {
            $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = (StreamingSAXBuilder.$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = class$("groovy.xml.streamingmarkupsupport.BaseMarkupBuilder"));
        }
        return $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovyRuntimeException() {
        Class $class$groovy$lang$GroovyRuntimeException;
        if (($class$groovy$lang$GroovyRuntimeException = StreamingSAXBuilder.$class$groovy$lang$GroovyRuntimeException) == null) {
            $class$groovy$lang$GroovyRuntimeException = (StreamingSAXBuilder.$class$groovy$lang$GroovyRuntimeException = class$("groovy.lang.GroovyRuntimeException"));
        }
        return $class$groovy$lang$GroovyRuntimeException;
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
