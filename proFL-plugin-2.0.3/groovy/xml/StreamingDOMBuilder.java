// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.xml.streamingmarkupsupport.AbstractStreamingBuilder;

public class StreamingDOMBuilder extends AbstractStreamingBuilder implements GroovyObject
{
    private Object pendingStack;
    private Object defaultNamespaceStack;
    private Object commentClosure;
    private Object piClosure;
    private Object noopClosure;
    private Object tagClosure;
    private Object builder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204623;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
    private static /* synthetic */ Class $class$groovy$xml$StreamingDOMBuilder;
    
    public StreamingDOMBuilder() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.pendingStack = ScriptBytecodeAdapter.createList(new Object[0]);
        this.defaultNamespaceStack = ScriptBytecodeAdapter.createList(new Object[] { "" });
        this.commentClosure = new StreamingDOMBuilder$_closure1(this, this);
        this.piClosure = new StreamingDOMBuilder$_closure2(this, this);
        this.noopClosure = new StreamingDOMBuilder$_closure3(this, this);
        this.tagClosure = new StreamingDOMBuilder$_closure4(this, this);
        this.builder = null;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ScriptBytecodeAdapter.createMap(new Object[] { "yield", this.noopClosure, "yieldUnescaped", this.noopClosure, "comment", this.commentClosure, "pi", this.piClosure }));
        final Object nsSpecificTags = ScriptBytecodeAdapter.createMap(new Object[] { ":", ScriptBytecodeAdapter.createList(new Object[] { this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0]) }), "http://www.w3.org/2000/xmlns/", ScriptBytecodeAdapter.createList(new Object[] { this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0]) }), "http://www.codehaus.org/Groovy/markup/keywords", ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[2].callGroovyObjectGetProperty(this), this.tagClosure, $getCallSiteArray[3].callGroovyObjectGetProperty(this) }) });
        this.builder = $getCallSiteArray[4].callConstructor($get$$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder(), nsSpecificTags);
    }
    
    public Object bind(final Object closure) {
        final Object boundClosure = new Reference($getCallSiteArray()[5].call(this.builder, closure));
        return new StreamingDOMBuilder$_bind_closure5(this, this, (Reference<Object>)boundClosure);
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$xml$StreamingDOMBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = StreamingDOMBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (StreamingDOMBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        StreamingDOMBuilder.__timeStamp__239_neverHappen1292524204623 = 0L;
        StreamingDOMBuilder.__timeStamp = 1292524204623L;
    }
    
    public Object getPendingStack() {
        return this.pendingStack;
    }
    
    public void setPendingStack(final Object pendingStack) {
        this.pendingStack = pendingStack;
    }
    
    public Object getDefaultNamespaceStack() {
        return this.defaultNamespaceStack;
    }
    
    public void setDefaultNamespaceStack(final Object defaultNamespaceStack) {
        this.defaultNamespaceStack = defaultNamespaceStack;
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
        final String[] names = new String[6];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingDOMBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingDOMBuilder.$callSiteArray == null || ($createCallSiteArray = StreamingDOMBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingDOMBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = StreamingDOMBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (StreamingDOMBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder() {
        Class $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
        if (($class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = StreamingDOMBuilder.$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder) == null) {
            $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = (StreamingDOMBuilder.$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = class$("groovy.xml.streamingmarkupsupport.BaseMarkupBuilder"));
        }
        return $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingDOMBuilder() {
        Class $class$groovy$xml$StreamingDOMBuilder;
        if (($class$groovy$xml$StreamingDOMBuilder = StreamingDOMBuilder.$class$groovy$xml$StreamingDOMBuilder) == null) {
            $class$groovy$xml$StreamingDOMBuilder = (StreamingDOMBuilder.$class$groovy$xml$StreamingDOMBuilder = class$("groovy.xml.StreamingDOMBuilder"));
        }
        return $class$groovy$xml$StreamingDOMBuilder;
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
