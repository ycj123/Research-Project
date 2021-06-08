// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import javax.management.NotificationFilter;
import org.codehaus.groovy.runtime.ArrayUtil;
import javax.management.MBeanServer;
import groovy.lang.Reference;
import java.util.Date;
import javax.management.ObjectName;
import javax.management.timer.Timer;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class JmxTimerFactory extends AbstractFactory implements GroovyObject
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$0;
    private static final /* synthetic */ Long $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    private static final /* synthetic */ Integer $const$4;
    private static final /* synthetic */ Integer $const$5;
    private static final /* synthetic */ Integer $const$6;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204371;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$management$ObjectName;
    private static /* synthetic */ Class $class$groovy$util$GroovyMBean;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilderException;
    private static /* synthetic */ Class $class$javax$management$NotificationFilterSupport;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxBuilder;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$java$lang$Object;
    private static /* synthetic */ Class $class$javax$management$MBeanServer;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$javax$management$timer$Timer;
    private static /* synthetic */ Class $class$groovy$jmx$builder$JmxTimerFactory;
    private static /* synthetic */ Class $class$java$util$Date;
    private static /* synthetic */ Class $class$javax$management$NotificationFilter;
    
    public JmxTimerFactory() {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object nodeName, final Object nodeParam, final Map nodeAttribs) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox(nodeParam)) {
            throw (Throwable)$getCallSiteArray[0].callConstructor($get$$class$groovy$jmx$builder$JmxBuilderException(), new GStringImpl(new Object[] { nodeName }, new String[] { "Node '", "' only supports named attributes." }));
        }
        final JmxBuilder fsb = (JmxBuilder)ScriptBytecodeAdapter.castToType(builder, $get$$class$groovy$jmx$builder$JmxBuilder());
        final Timer timer = (Timer)$getCallSiteArray[1].callConstructor($get$$class$javax$management$timer$Timer());
        final Object metaMap = ScriptBytecodeAdapter.createMap(new Object[0]);
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[2].call(fsb), $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "server");
        ScriptBytecodeAdapter.setProperty(timer, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "timer");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[3].call(nodeAttribs, "name"), $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "name");
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[4].call(nodeAttribs, "event"))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[5].call(nodeAttribs, "type"))) {
                messageArgument = "jmx.builder.event";
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "event");
        Object messageArgument2;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument2 = $getCallSiteArray[6].call(nodeAttribs, "message"))) {
            messageArgument2 = $getCallSiteArray[7].call(nodeAttribs, "msg");
        }
        ScriptBytecodeAdapter.setProperty(messageArgument2, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "message");
        Object messageArgument3;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument3 = $getCallSiteArray[8].call(nodeAttribs, "data"))) {
            messageArgument3 = $getCallSiteArray[9].call(nodeAttribs, "userData");
        }
        ScriptBytecodeAdapter.setProperty(messageArgument3, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "data");
        Object messageArgument4;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument4 = $getCallSiteArray[10].call(nodeAttribs, "date"))) {
            messageArgument4 = $getCallSiteArray[11].call(nodeAttribs, "startDate");
        }
        ScriptBytecodeAdapter.setProperty(messageArgument4, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "date");
        Object messageArgument5;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument5 = $getCallSiteArray[12].call(nodeAttribs, "period"))) {
            messageArgument5 = $getCallSiteArray[13].call(nodeAttribs, "frequency");
        }
        ScriptBytecodeAdapter.setProperty(messageArgument5, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "period");
        Object messageArgument6;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument6 = $getCallSiteArray[14].call(nodeAttribs, "occurs"))) {
            if (!DefaultTypeTransformation.booleanUnbox(messageArgument6 = $getCallSiteArray[15].call(nodeAttribs, "occurences"))) {
                messageArgument6 = JmxTimerFactory.$const$0;
            }
        }
        ScriptBytecodeAdapter.setProperty(messageArgument6, $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "occurences");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[16].callCurrent(this, fsb, timer, $getCallSiteArray[17].callGetProperty(metaMap)), $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "name");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[18].callCurrent(this, $getCallSiteArray[19].callGetProperty(metaMap)), $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "date");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[20].callCurrent(this, $getCallSiteArray[21].callGetProperty(metaMap)), $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "period");
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[22].callCurrent(this, $getCallSiteArray[23].callGetProperty(metaMap)), $get$$class$groovy$jmx$builder$JmxTimerFactory(), metaMap, "listeners");
        final Object result = $getCallSiteArray[24].callCurrent(this, metaMap);
        return ScriptBytecodeAdapter.castToType(result, $get$$class$java$lang$Object());
    }
    
    private Object getNormalizedName(final Object fsb, final Object timer, final Object name) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object result = null;
        if (!DefaultTypeTransformation.booleanUnbox(name)) {
            result = $getCallSiteArray[25].callCurrent(this, fsb, timer);
        }
        else if (name instanceof String) {
            result = $getCallSiteArray[26].callConstructor($get$$class$javax$management$ObjectName(), name);
        }
        else if (name instanceof ObjectName) {
            result = name;
        }
        else {
            result = $getCallSiteArray[27].callCurrent(this, fsb, $getCallSiteArray[28].callGroovyObjectGetProperty(this));
        }
        return result;
    }
    
    private Object getDefaultName(final Object fsb, final Object timer) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object name = new GStringImpl(new Object[] { $getCallSiteArray[29].call(fsb), $getCallSiteArray[30].call(timer) }, new String[] { "", ":type=TimerService,name=Timer@", "" });
        return $getCallSiteArray[31].callConstructor($get$$class$javax$management$ObjectName(), name);
    }
    
    private Object getNormalizedDate(final Object date) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(date)) {
            return $getCallSiteArray[32].callConstructor($get$$class$java$util$Date());
        }
        if (date instanceof Date) {
            return date;
        }
        Object startDate = null;
        if (!ScriptBytecodeAdapter.isCase(date, null)) {
            if (ScriptBytecodeAdapter.isCase(date, "now")) {}
        }
        startDate = $getCallSiteArray[33].callConstructor($get$$class$java$util$Date());
        return startDate;
    }
    
    private Object getNormalizedPeriod(final Object period) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(period)) {
            return JmxTimerFactory.$const$1;
        }
        if (period instanceof Number) {
            return period;
        }
        Object result = JmxTimerFactory.$const$1;
        if (period instanceof String) {
            Object multiplier = $getCallSiteArray[34].call(period, JmxTimerFactory.$const$2);
            Object value = null;
            try {
                value = $getCallSiteArray[35].call($getCallSiteArray[36].call(period, ScriptBytecodeAdapter.createRange(JmxTimerFactory.$const$0, JmxTimerFactory.$const$3, true)));
            }
            catch (Exception e) {
                multiplier = "x";
            }
            final Object o = multiplier;
            if (ScriptBytecodeAdapter.isCase(o, "s")) {
                result = $getCallSiteArray[37].call(value, JmxTimerFactory.$const$4);
            }
            else if (ScriptBytecodeAdapter.isCase(o, "m")) {
                result = $getCallSiteArray[38].call($getCallSiteArray[39].call(value, JmxTimerFactory.$const$5), JmxTimerFactory.$const$4);
            }
            else if (ScriptBytecodeAdapter.isCase(o, "h")) {
                result = $getCallSiteArray[40].call($getCallSiteArray[41].call($getCallSiteArray[42].call(value, JmxTimerFactory.$const$5), JmxTimerFactory.$const$5), JmxTimerFactory.$const$4);
            }
            else if (ScriptBytecodeAdapter.isCase(o, "d")) {
                result = $getCallSiteArray[43].call($getCallSiteArray[44].call($getCallSiteArray[45].call($getCallSiteArray[46].call(value, JmxTimerFactory.$const$6), JmxTimerFactory.$const$5), JmxTimerFactory.$const$5), JmxTimerFactory.$const$4);
            }
            else {
                result = JmxTimerFactory.$const$1;
            }
        }
        return result;
    }
    
    private Object getNormalizedRecipientList(final Object list) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox(list)) {
            return null;
        }
        final Object result = new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
        $getCallSiteArray[47].call(list, new JmxTimerFactory$_getNormalizedRecipientList_closure1(this, this, (Reference<Object>)result));
        return ((Reference<Object>)result).get();
    }
    
    private Object registerTimer(final Object metaMap) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object server = ScriptBytecodeAdapter.castToType($getCallSiteArray[48].callGetProperty(metaMap), $get$$class$javax$management$MBeanServer());
        final Object timer = $getCallSiteArray[49].callGetProperty(metaMap);
        $getCallSiteArray[50].call(timer, ArrayUtil.createArray($getCallSiteArray[51].callGetProperty(metaMap), $getCallSiteArray[52].callGetProperty(metaMap), $getCallSiteArray[53].callGetProperty(metaMap), $getCallSiteArray[54].callGetProperty(metaMap), $getCallSiteArray[55].callGetProperty(metaMap), $getCallSiteArray[56].callGetProperty(metaMap)));
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[57].call(server, $getCallSiteArray[58].callGetProperty(metaMap)))) {
            $getCallSiteArray[59].call(server, $getCallSiteArray[60].callGetProperty(metaMap));
        }
        $getCallSiteArray[61].call(server, timer, $getCallSiteArray[62].callGetProperty(metaMap));
        return $getCallSiteArray[63].callConstructor($get$$class$groovy$util$GroovyMBean(), $getCallSiteArray[64].callGetProperty(metaMap), $getCallSiteArray[65].callGetProperty(metaMap));
    }
    
    private NotificationFilter getEventFilter(final Object type) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object noteFilter = $getCallSiteArray[66].callConstructor($get$$class$javax$management$NotificationFilterSupport());
        $getCallSiteArray[67].call(noteFilter, type);
        return (NotificationFilter)ScriptBytecodeAdapter.castToType(noteFilter, $get$$class$javax$management$NotificationFilter());
    }
    
    @Override
    public boolean onHandleNodeAttributes(final FactoryBuilderSupport builder, final Object node, final Map nodeAttribs) {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public boolean isLeaf() {
        $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType(Boolean.TRUE, $get$$class$java$lang$Boolean()));
    }
    
    @Override
    public void onNodeCompleted(final FactoryBuilderSupport builder, final Object parentNode, final Object thisNode) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (ScriptBytecodeAdapter.compareNotEqual(parentNode, null)) {
            $getCallSiteArray[68].call(parentNode, thisNode);
        }
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$jmx$builder$JmxTimerFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = JmxTimerFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (JmxTimerFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        JmxTimerFactory.__timeStamp__239_neverHappen1292524204371 = 0L;
        JmxTimerFactory.__timeStamp = 1292524204371L;
        $const$6 = 24;
        $const$5 = 60;
        $const$4 = 1000;
        $const$3 = -2;
        $const$2 = -1;
        $const$1 = 1000L;
        $const$0 = 0;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[69];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$jmx$builder$JmxTimerFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (JmxTimerFactory.$callSiteArray == null || ($createCallSiteArray = JmxTimerFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            JmxTimerFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$ObjectName() {
        Class $class$javax$management$ObjectName;
        if (($class$javax$management$ObjectName = JmxTimerFactory.$class$javax$management$ObjectName) == null) {
            $class$javax$management$ObjectName = (JmxTimerFactory.$class$javax$management$ObjectName = class$("javax.management.ObjectName"));
        }
        return $class$javax$management$ObjectName;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$util$GroovyMBean() {
        Class $class$groovy$util$GroovyMBean;
        if (($class$groovy$util$GroovyMBean = JmxTimerFactory.$class$groovy$util$GroovyMBean) == null) {
            $class$groovy$util$GroovyMBean = (JmxTimerFactory.$class$groovy$util$GroovyMBean = class$("groovy.util.GroovyMBean"));
        }
        return $class$groovy$util$GroovyMBean;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilderException() {
        Class $class$groovy$jmx$builder$JmxBuilderException;
        if (($class$groovy$jmx$builder$JmxBuilderException = JmxTimerFactory.$class$groovy$jmx$builder$JmxBuilderException) == null) {
            $class$groovy$jmx$builder$JmxBuilderException = (JmxTimerFactory.$class$groovy$jmx$builder$JmxBuilderException = class$("groovy.jmx.builder.JmxBuilderException"));
        }
        return $class$groovy$jmx$builder$JmxBuilderException;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$NotificationFilterSupport() {
        Class $class$javax$management$NotificationFilterSupport;
        if (($class$javax$management$NotificationFilterSupport = JmxTimerFactory.$class$javax$management$NotificationFilterSupport) == null) {
            $class$javax$management$NotificationFilterSupport = (JmxTimerFactory.$class$javax$management$NotificationFilterSupport = class$("javax.management.NotificationFilterSupport"));
        }
        return $class$javax$management$NotificationFilterSupport;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxBuilder() {
        Class $class$groovy$jmx$builder$JmxBuilder;
        if (($class$groovy$jmx$builder$JmxBuilder = JmxTimerFactory.$class$groovy$jmx$builder$JmxBuilder) == null) {
            $class$groovy$jmx$builder$JmxBuilder = (JmxTimerFactory.$class$groovy$jmx$builder$JmxBuilder = class$("groovy.jmx.builder.JmxBuilder"));
        }
        return $class$groovy$jmx$builder$JmxBuilder;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = JmxTimerFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (JmxTimerFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Object() {
        Class $class$java$lang$Object;
        if (($class$java$lang$Object = JmxTimerFactory.$class$java$lang$Object) == null) {
            $class$java$lang$Object = (JmxTimerFactory.$class$java$lang$Object = class$("java.lang.Object"));
        }
        return $class$java$lang$Object;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$MBeanServer() {
        Class $class$javax$management$MBeanServer;
        if (($class$javax$management$MBeanServer = JmxTimerFactory.$class$javax$management$MBeanServer) == null) {
            $class$javax$management$MBeanServer = (JmxTimerFactory.$class$javax$management$MBeanServer = class$("javax.management.MBeanServer"));
        }
        return $class$javax$management$MBeanServer;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = JmxTimerFactory.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (JmxTimerFactory.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$timer$Timer() {
        Class $class$javax$management$timer$Timer;
        if (($class$javax$management$timer$Timer = JmxTimerFactory.$class$javax$management$timer$Timer) == null) {
            $class$javax$management$timer$Timer = (JmxTimerFactory.$class$javax$management$timer$Timer = class$("javax.management.timer.Timer"));
        }
        return $class$javax$management$timer$Timer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$jmx$builder$JmxTimerFactory() {
        Class $class$groovy$jmx$builder$JmxTimerFactory;
        if (($class$groovy$jmx$builder$JmxTimerFactory = JmxTimerFactory.$class$groovy$jmx$builder$JmxTimerFactory) == null) {
            $class$groovy$jmx$builder$JmxTimerFactory = (JmxTimerFactory.$class$groovy$jmx$builder$JmxTimerFactory = class$("groovy.jmx.builder.JmxTimerFactory"));
        }
        return $class$groovy$jmx$builder$JmxTimerFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Date() {
        Class $class$java$util$Date;
        if (($class$java$util$Date = JmxTimerFactory.$class$java$util$Date) == null) {
            $class$java$util$Date = (JmxTimerFactory.$class$java$util$Date = class$("java.util.Date"));
        }
        return $class$java$util$Date;
    }
    
    private static /* synthetic */ Class $get$$class$javax$management$NotificationFilter() {
        Class $class$javax$management$NotificationFilter;
        if (($class$javax$management$NotificationFilter = JmxTimerFactory.$class$javax$management$NotificationFilter) == null) {
            $class$javax$management$NotificationFilter = (JmxTimerFactory.$class$javax$management$NotificationFilter = class$("javax.management.NotificationFilter"));
        }
        return $class$javax$management$NotificationFilter;
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
