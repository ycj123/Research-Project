// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml.streamingmarkupsupport;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;

public class AbstractStreamingBuilder implements GroovyObject
{
    private Object badTagClosure;
    private Object namespaceSetupClosure;
    private Object aliasSetupClosure;
    private Object getNamespaceClosure;
    private Object specialTags;
    private Object builder;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203685;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    
    public AbstractStreamingBuilder() {
        $getCallSiteArray();
        this.badTagClosure = new AbstractStreamingBuilder$_closure1(this, this);
        this.namespaceSetupClosure = new AbstractStreamingBuilder$_closure2(this, this);
        this.aliasSetupClosure = new AbstractStreamingBuilder$_closure3(this, this);
        this.getNamespaceClosure = new AbstractStreamingBuilder$_closure4(this, this);
        this.specialTags = ScriptBytecodeAdapter.createMap(new Object[] { "declareNamespace", this.namespaceSetupClosure, "declareAlias", this.aliasSetupClosure, "getNamespaces", this.getNamespaceClosure });
        this.builder = null;
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = AbstractStreamingBuilder.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (AbstractStreamingBuilder.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        AbstractStreamingBuilder.__timeStamp__239_neverHappen1292524203685 = 0L;
        AbstractStreamingBuilder.__timeStamp = 1292524203685L;
    }
    
    public Object getBadTagClosure() {
        return this.badTagClosure;
    }
    
    public void setBadTagClosure(final Object badTagClosure) {
        this.badTagClosure = badTagClosure;
    }
    
    public Object getNamespaceSetupClosure() {
        return this.namespaceSetupClosure;
    }
    
    public void setNamespaceSetupClosure(final Object namespaceSetupClosure) {
        this.namespaceSetupClosure = namespaceSetupClosure;
    }
    
    public Object getAliasSetupClosure() {
        return this.aliasSetupClosure;
    }
    
    public void setAliasSetupClosure(final Object aliasSetupClosure) {
        this.aliasSetupClosure = aliasSetupClosure;
    }
    
    public Object getGetNamespaceClosure() {
        return this.getNamespaceClosure;
    }
    
    public void setGetNamespaceClosure(final Object getNamespaceClosure) {
        this.getNamespaceClosure = getNamespaceClosure;
    }
    
    public Object getSpecialTags() {
        return this.specialTags;
    }
    
    public void setSpecialTags(final Object specialTags) {
        this.specialTags = specialTags;
    }
    
    public Object getBuilder() {
        return this.builder;
    }
    
    public void setBuilder(final Object builder) {
        this.builder = builder;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        return new CallSiteArray($get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder(), new String[0]);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AbstractStreamingBuilder.$callSiteArray == null || ($createCallSiteArray = AbstractStreamingBuilder.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AbstractStreamingBuilder.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder() {
        Class $class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder;
        if (($class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder = AbstractStreamingBuilder.$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder) == null) {
            $class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder = (AbstractStreamingBuilder.$class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder = class$("groovy.xml.streamingmarkupsupport.AbstractStreamingBuilder"));
        }
        return $class$groovy$xml$streamingmarkupsupport$AbstractStreamingBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = AbstractStreamingBuilder.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (AbstractStreamingBuilder.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
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
