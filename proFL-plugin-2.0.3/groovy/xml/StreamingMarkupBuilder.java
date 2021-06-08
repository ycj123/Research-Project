// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.GStringImpl;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.xml.streamingmarkupsupport.AbstractStreamingBuilder;

public class StreamingMarkupBuilder extends AbstractStreamingBuilder implements GroovyObject
{
    private boolean useDoubleQuotes;
    private Object pendingStack;
    private Object commentClosure;
    private Object piClosure;
    private Object declarationClosure;
    private Object noopClosure;
    private Object unescapedClosure;
    private Object tagClosure;
    private Object builder;
    private Object encoding;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204641;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$xml$StreamingMarkupBuilder;
    private static /* synthetic */ Class $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
    
    public StreamingMarkupBuilder() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.useDoubleQuotes = DefaultTypeTransformation.booleanUnbox(Boolean.FALSE);
        this.pendingStack = ScriptBytecodeAdapter.createList(new Object[0]);
        this.commentClosure = new StreamingMarkupBuilder$_closure1(this, this);
        this.piClosure = new StreamingMarkupBuilder$_closure2(this, this);
        this.declarationClosure = new StreamingMarkupBuilder$_closure3(this, this);
        this.noopClosure = new StreamingMarkupBuilder$_closure4(this, this);
        this.unescapedClosure = new StreamingMarkupBuilder$_closure5(this, this);
        this.tagClosure = new StreamingMarkupBuilder$_closure6(this, this);
        this.builder = null;
        this.encoding = null;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        $getCallSiteArray[0].call($getCallSiteArray[1].callGroovyObjectGetProperty(this), ScriptBytecodeAdapter.createMap(new Object[] { "yield", this.noopClosure, "yieldUnescaped", this.unescapedClosure, "xmlDeclaration", this.declarationClosure, "comment", this.commentClosure, "pi", this.piClosure }));
        final Object nsSpecificTags = ScriptBytecodeAdapter.createMap(new Object[] { ":", ScriptBytecodeAdapter.createList(new Object[] { this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0]) }), "http://www.w3.org/XML/1998/namespace", ScriptBytecodeAdapter.createList(new Object[] { this.tagClosure, this.tagClosure, ScriptBytecodeAdapter.createMap(new Object[0]) }), "http://www.codehaus.org/Groovy/markup/keywords", ScriptBytecodeAdapter.createList(new Object[] { $getCallSiteArray[2].callGroovyObjectGetProperty(this), this.tagClosure, $getCallSiteArray[3].callGroovyObjectGetProperty(this) }) });
        this.builder = $getCallSiteArray[4].callConstructor($get$$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder(), nsSpecificTags);
    }
    
    public Object getQt() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.useDoubleQuotes)) ? "\"" : "'";
    }
    
    public Object bind(final Object closure) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object boundClosure = new Reference($getCallSiteArray[5].call(this.builder, closure));
        final Object enc = new Reference(this.encoding);
        return $getCallSiteArray[6].call(new StreamingMarkupBuilder$_bind_closure7(this, this, (Reference<Object>)boundClosure, (Reference<Object>)enc));
    }
    
    public Object bindNode(final Object node) {
        final Object node2 = new Reference(node);
        return $getCallSiteArray()[7].callCurrent(this, new StreamingMarkupBuilder$_bindNode_closure8(this, this, (Reference<Object>)node2));
    }
    
    @Override
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$xml$StreamingMarkupBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = StreamingMarkupBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (StreamingMarkupBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        StreamingMarkupBuilder.__timeStamp__239_neverHappen1292524204641 = 0L;
        StreamingMarkupBuilder.__timeStamp = 1292524204641L;
    }
    
    public boolean getUseDoubleQuotes() {
        return this.useDoubleQuotes;
    }
    
    public boolean isUseDoubleQuotes() {
        return this.useDoubleQuotes;
    }
    
    public void setUseDoubleQuotes(final boolean useDoubleQuotes) {
        this.useDoubleQuotes = useDoubleQuotes;
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
    
    public Object getDeclarationClosure() {
        return this.declarationClosure;
    }
    
    public void setDeclarationClosure(final Object declarationClosure) {
        this.declarationClosure = declarationClosure;
    }
    
    public Object getNoopClosure() {
        return this.noopClosure;
    }
    
    public void setNoopClosure(final Object noopClosure) {
        this.noopClosure = noopClosure;
    }
    
    public Object getUnescapedClosure() {
        return this.unescapedClosure;
    }
    
    public void setUnescapedClosure(final Object unescapedClosure) {
        this.unescapedClosure = unescapedClosure;
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
    
    public Object getEncoding() {
        return this.encoding;
    }
    
    public void setEncoding(final Object encoding) {
        this.encoding = encoding;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[8];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$StreamingMarkupBuilder(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (StreamingMarkupBuilder.$callSiteArray == null || ($createCallSiteArray = StreamingMarkupBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            StreamingMarkupBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = StreamingMarkupBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (StreamingMarkupBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$StreamingMarkupBuilder() {
        Class $class$groovy$xml$StreamingMarkupBuilder;
        if (($class$groovy$xml$StreamingMarkupBuilder = StreamingMarkupBuilder.$class$groovy$xml$StreamingMarkupBuilder) == null) {
            $class$groovy$xml$StreamingMarkupBuilder = (StreamingMarkupBuilder.$class$groovy$xml$StreamingMarkupBuilder = class$("groovy.xml.StreamingMarkupBuilder"));
        }
        return $class$groovy$xml$StreamingMarkupBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder() {
        Class $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
        if (($class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = StreamingMarkupBuilder.$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder) == null) {
            $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = (StreamingMarkupBuilder.$class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder = class$("groovy.xml.streamingmarkupsupport.BaseMarkupBuilder"));
        }
        return $class$groovy$xml$streamingmarkupsupport$BaseMarkupBuilder;
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
