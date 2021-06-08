// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Closure;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import java.util.Map;
import groovy.util.FactoryBuilderSupport;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import java.lang.reflect.Constructor;
import groovy.lang.GroovyObject;
import groovy.util.AbstractFactory;

public class RichActionWidgetFactory extends AbstractFactory implements GroovyObject
{
    private static final Class[] ACTION_ARGS;
    private static final Class[] ICON_ARGS;
    private static final Class[] STRING_ARGS;
    private final Constructor actionCtor;
    private final Constructor iconCtor;
    private final Constructor stringCtor;
    private final Class klass;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204498;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$swing$factory$RichActionWidgetFactory;
    private static /* synthetic */ Class $class$java$lang$reflect$Constructor;
    private static /* synthetic */ Class $class$javax$swing$Action;
    private static /* synthetic */ Class $class$java$util$logging$Logger;
    private static /* synthetic */ Class $class$javax$swing$Icon;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class array$$class$java$lang$Class;
    private static /* synthetic */ Class $class$java$lang$Class;
    private static /* synthetic */ Class $class$java$util$logging$Level;
    
    public RichActionWidgetFactory(final Class klass) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        try {
            this.actionCtor = (Constructor)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[0].call(klass, (Object)RichActionWidgetFactory.ACTION_ARGS), $get$$class$java$lang$reflect$Constructor()), $get$$class$java$lang$reflect$Constructor());
            this.iconCtor = (Constructor)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[1].call(klass, (Object)RichActionWidgetFactory.ICON_ARGS), $get$$class$java$lang$reflect$Constructor()), $get$$class$java$lang$reflect$Constructor());
            this.stringCtor = (Constructor)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[2].call(klass, (Object)RichActionWidgetFactory.STRING_ARGS), $get$$class$java$lang$reflect$Constructor()), $get$$class$java$lang$reflect$Constructor());
            this.klass = (Class)ScriptBytecodeAdapter.castToType(klass, $get$$class$java$lang$Class());
        }
        catch (NoSuchMethodException ex) {
            $getCallSiteArray[3].call($getCallSiteArray[4].call($get$$class$java$util$logging$Logger(), "global"), $getCallSiteArray[5].callGetProperty($get$$class$java$util$logging$Level()), null, ex);
        }
        catch (SecurityException ex2) {
            $getCallSiteArray[6].call($getCallSiteArray[7].call($get$$class$java$util$logging$Logger(), "global"), $getCallSiteArray[8].callGetProperty($get$$class$java$util$logging$Level()), null, ex2);
        }
    }
    
    public Object newInstance(final FactoryBuilderSupport builder, final Object name, final Object value, final Map attributes) throws InstantiationException, IllegalAccessException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore          5
        //     5: aload_3         /* value */
        //     6: instanceof      Lgroovy/lang/GString;
        //     9: ifeq            28
        //    12: aload_3         /* value */
        //    13: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$String:()Ljava/lang/Class;
        //    16: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.asType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    19: checkcast       Ljava/lang/String;
        //    22: dup            
        //    23: astore_3        /* value */
        //    24: pop            
        //    25: goto            28
        //    28: aload_3         /* value */
        //    29: aconst_null    
        //    30: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.compareEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    33: ifeq            69
        //    36: aload           5
        //    38: ldc             9
        //    40: aaload         
        //    41: aload_0         /* this */
        //    42: getfield        groovy/swing/factory/RichActionWidgetFactory.klass:Ljava/lang/Class;
        //    45: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    50: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //    53: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    56: checkcast       Ljava/lang/Object;
        //    59: astore          6
        //    61: nop            
        //    62: nop            
        //    63: aload           6
        //    65: areturn        
        //    66: goto            311
        //    69: aload_3         /* value */
        //    70: instanceof      Ljavax/swing/Action;
        //    73: ifeq            110
        //    76: aload           5
        //    78: ldc             10
        //    80: aaload         
        //    81: aload_0         /* this */
        //    82: getfield        groovy/swing/factory/RichActionWidgetFactory.actionCtor:Ljava/lang/reflect/Constructor;
        //    85: aload_3         /* value */
        //    86: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    91: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //    94: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    97: checkcast       Ljava/lang/Object;
        //   100: astore          6
        //   102: nop            
        //   103: nop            
        //   104: aload           6
        //   106: areturn        
        //   107: goto            311
        //   110: aload_3         /* value */
        //   111: instanceof      Ljavax/swing/Icon;
        //   114: ifeq            151
        //   117: aload           5
        //   119: ldc             11
        //   121: aaload         
        //   122: aload_0         /* this */
        //   123: getfield        groovy/swing/factory/RichActionWidgetFactory.iconCtor:Ljava/lang/reflect/Constructor;
        //   126: aload_3         /* value */
        //   127: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   132: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //   135: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   138: checkcast       Ljava/lang/Object;
        //   141: astore          6
        //   143: nop            
        //   144: nop            
        //   145: aload           6
        //   147: areturn        
        //   148: goto            311
        //   151: aload_3         /* value */
        //   152: instanceof      Ljava/lang/String;
        //   155: ifeq            192
        //   158: aload           5
        //   160: ldc             12
        //   162: aaload         
        //   163: aload_0         /* this */
        //   164: getfield        groovy/swing/factory/RichActionWidgetFactory.stringCtor:Ljava/lang/reflect/Constructor;
        //   167: aload_3         /* value */
        //   168: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   173: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //   176: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   179: checkcast       Ljava/lang/Object;
        //   182: astore          6
        //   184: nop            
        //   185: nop            
        //   186: aload           6
        //   188: areturn        
        //   189: goto            311
        //   192: aload           5
        //   194: ldc             13
        //   196: aaload         
        //   197: aload_0         /* this */
        //   198: getfield        groovy/swing/factory/RichActionWidgetFactory.klass:Ljava/lang/Class;
        //   201: aload           5
        //   203: ldc             14
        //   205: aaload         
        //   206: aload_3         /* value */
        //   207: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   212: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   217: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   220: ifeq            243
        //   223: aload_3         /* value */
        //   224: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //   227: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   230: checkcast       Ljava/lang/Object;
        //   233: astore          6
        //   235: nop            
        //   236: nop            
        //   237: aload           6
        //   239: areturn        
        //   240: goto            311
        //   243: aload           5
        //   245: ldc             15
        //   247: aaload         
        //   248: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$RuntimeException:()Ljava/lang/Class;
        //   251: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   254: dup            
        //   255: iconst_2       
        //   256: anewarray       Ljava/lang/Object;
        //   259: dup            
        //   260: iconst_0       
        //   261: aload_2         /* name */
        //   262: aastore        
        //   263: dup            
        //   264: iconst_1       
        //   265: aload           5
        //   267: ldc             16
        //   269: aaload         
        //   270: aload_0         /* this */
        //   271: getfield        groovy/swing/factory/RichActionWidgetFactory.klass:Ljava/lang/Class;
        //   274: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   279: aastore        
        //   280: iconst_3       
        //   281: anewarray       Ljava/lang/String;
        //   284: dup            
        //   285: iconst_0       
        //   286: ldc             ""
        //   288: aastore        
        //   289: dup            
        //   290: iconst_1       
        //   291: ldc             " can only have a value argument of type javax.swing.Action, javax.swing.Icon, java.lang.String, or "
        //   293: aastore        
        //   294: dup            
        //   295: iconst_2       
        //   296: ldc             ""
        //   298: aastore        
        //   299: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   302: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   307: checkcast       Ljava/lang/Throwable;
        //   310: athrow         
        //   311: goto            442
        //   314: astore          e
        //   316: aload           5
        //   318: ldc             17
        //   320: aaload         
        //   321: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$RuntimeException:()Ljava/lang/Class;
        //   324: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   327: dup            
        //   328: iconst_2       
        //   329: anewarray       Ljava/lang/Object;
        //   332: dup            
        //   333: iconst_0       
        //   334: aload_2         /* name */
        //   335: aastore        
        //   336: dup            
        //   337: iconst_1       
        //   338: aload           e
        //   340: aastore        
        //   341: iconst_3       
        //   342: anewarray       Ljava/lang/String;
        //   345: dup            
        //   346: iconst_0       
        //   347: ldc             "Failed to create component for '"
        //   349: aastore        
        //   350: dup            
        //   351: iconst_1       
        //   352: ldc             "' reason: "
        //   354: aastore        
        //   355: dup            
        //   356: iconst_2       
        //   357: ldc             ""
        //   359: aastore        
        //   360: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   363: aload           e
        //   365: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   370: checkcast       Ljava/lang/Throwable;
        //   373: athrow         
        //   374: nop            
        //   375: goto            442
        //   378: astore          e
        //   380: aload           5
        //   382: ldc             18
        //   384: aaload         
        //   385: invokestatic    groovy/swing/factory/RichActionWidgetFactory.$get$$class$java$lang$RuntimeException:()Ljava/lang/Class;
        //   388: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   391: dup            
        //   392: iconst_2       
        //   393: anewarray       Ljava/lang/Object;
        //   396: dup            
        //   397: iconst_0       
        //   398: aload_2         /* name */
        //   399: aastore        
        //   400: dup            
        //   401: iconst_1       
        //   402: aload           e
        //   404: aastore        
        //   405: iconst_3       
        //   406: anewarray       Ljava/lang/String;
        //   409: dup            
        //   410: iconst_0       
        //   411: ldc             "Failed to create component for '"
        //   413: aastore        
        //   414: dup            
        //   415: iconst_1       
        //   416: ldc             "' reason: "
        //   418: aastore        
        //   419: dup            
        //   420: iconst_2       
        //   421: ldc             ""
        //   423: aastore        
        //   424: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   427: aload           e
        //   429: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callConstructor:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   434: checkcast       Ljava/lang/Throwable;
        //   437: athrow         
        //   438: nop            
        //   439: goto            442
        //   442: nop            
        //   443: goto            451
        //   446: astore          6
        //   448: aload           6
        //   450: athrow         
        //   451: nop            
        //    Exceptions:
        //  throws java.lang.InstantiationException
        //  throws java.lang.IllegalAccessException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                         
        //  -----  -----  -----  -----  ---------------------------------------------
        //  5      62     314    374    Ljava/lang/IllegalArgumentException;
        //  63     103    314    374    Ljava/lang/IllegalArgumentException;
        //  104    144    314    374    Ljava/lang/IllegalArgumentException;
        //  145    185    314    374    Ljava/lang/IllegalArgumentException;
        //  186    236    314    374    Ljava/lang/IllegalArgumentException;
        //  237    314    314    374    Ljava/lang/IllegalArgumentException;
        //  5      62     378    438    Ljava/lang/reflect/InvocationTargetException;
        //  63     103    378    438    Ljava/lang/reflect/InvocationTargetException;
        //  104    144    378    438    Ljava/lang/reflect/InvocationTargetException;
        //  145    185    378    438    Ljava/lang/reflect/InvocationTargetException;
        //  186    236    378    438    Ljava/lang/reflect/InvocationTargetException;
        //  237    314    378    438    Ljava/lang/reflect/InvocationTargetException;
        //  5      62     446    451    Any
        //  63     103    446    451    Any
        //  104    144    446    451    Any
        //  145    185    446    451    Any
        //  186    236    446    451    Any
        //  237    314    446    451    Any
        //  314    375    446    451    Any
        //  378    439    446    451    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$swing$factory$RichActionWidgetFactory()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = RichActionWidgetFactory.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (RichActionWidgetFactory.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        RichActionWidgetFactory.__timeStamp__239_neverHappen1292524204498 = 0L;
        RichActionWidgetFactory.__timeStamp = 1292524204498L;
        ACTION_ARGS = (Class[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $get$$class$javax$swing$Action() }), $get$array$$class$java$lang$Class());
        ICON_ARGS = (Class[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $get$$class$javax$swing$Icon() }), $get$array$$class$java$lang$Class());
        STRING_ARGS = (Class[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[] { $get$$class$java$lang$String() }), $get$array$$class$java$lang$Class());
    }
    
    public static final Class[] getACTION_ARGS() {
        return RichActionWidgetFactory.ACTION_ARGS;
    }
    
    public static final Class[] getICON_ARGS() {
        return RichActionWidgetFactory.ICON_ARGS;
    }
    
    public static final Class[] getSTRING_ARGS() {
        return RichActionWidgetFactory.STRING_ARGS;
    }
    
    public final Constructor getActionCtor() {
        return this.actionCtor;
    }
    
    public final Constructor getIconCtor() {
        return this.iconCtor;
    }
    
    public final Constructor getStringCtor() {
        return this.stringCtor;
    }
    
    public final Class getKlass() {
        return this.klass;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[19];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$RichActionWidgetFactory(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (RichActionWidgetFactory.$callSiteArray == null || ($createCallSiteArray = RichActionWidgetFactory.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            RichActionWidgetFactory.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = RichActionWidgetFactory.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (RichActionWidgetFactory.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$RichActionWidgetFactory() {
        Class $class$groovy$swing$factory$RichActionWidgetFactory;
        if (($class$groovy$swing$factory$RichActionWidgetFactory = RichActionWidgetFactory.$class$groovy$swing$factory$RichActionWidgetFactory) == null) {
            $class$groovy$swing$factory$RichActionWidgetFactory = (RichActionWidgetFactory.$class$groovy$swing$factory$RichActionWidgetFactory = class$("groovy.swing.factory.RichActionWidgetFactory"));
        }
        return $class$groovy$swing$factory$RichActionWidgetFactory;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$reflect$Constructor() {
        Class $class$java$lang$reflect$Constructor;
        if (($class$java$lang$reflect$Constructor = RichActionWidgetFactory.$class$java$lang$reflect$Constructor) == null) {
            $class$java$lang$reflect$Constructor = (RichActionWidgetFactory.$class$java$lang$reflect$Constructor = class$("java.lang.reflect.Constructor"));
        }
        return $class$java$lang$reflect$Constructor;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Action() {
        Class $class$javax$swing$Action;
        if (($class$javax$swing$Action = RichActionWidgetFactory.$class$javax$swing$Action) == null) {
            $class$javax$swing$Action = (RichActionWidgetFactory.$class$javax$swing$Action = class$("javax.swing.Action"));
        }
        return $class$javax$swing$Action;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Logger() {
        Class $class$java$util$logging$Logger;
        if (($class$java$util$logging$Logger = RichActionWidgetFactory.$class$java$util$logging$Logger) == null) {
            $class$java$util$logging$Logger = (RichActionWidgetFactory.$class$java$util$logging$Logger = class$("java.util.logging.Logger"));
        }
        return $class$java$util$logging$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$Icon() {
        Class $class$javax$swing$Icon;
        if (($class$javax$swing$Icon = RichActionWidgetFactory.$class$javax$swing$Icon) == null) {
            $class$javax$swing$Icon = (RichActionWidgetFactory.$class$javax$swing$Icon = class$("javax.swing.Icon"));
        }
        return $class$javax$swing$Icon;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = RichActionWidgetFactory.$class$java$lang$String) == null) {
            $class$java$lang$String = (RichActionWidgetFactory.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$Class() {
        Class array$$class$java$lang$Class;
        if ((array$$class$java$lang$Class = RichActionWidgetFactory.array$$class$java$lang$Class) == null) {
            array$$class$java$lang$Class = (RichActionWidgetFactory.array$$class$java$lang$Class = class$("[Ljava.lang.Class;"));
        }
        return array$$class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Class() {
        Class $class$java$lang$Class;
        if (($class$java$lang$Class = RichActionWidgetFactory.$class$java$lang$Class) == null) {
            $class$java$lang$Class = (RichActionWidgetFactory.$class$java$lang$Class = class$("java.lang.Class"));
        }
        return $class$java$lang$Class;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$logging$Level() {
        Class $class$java$util$logging$Level;
        if (($class$java$util$logging$Level = RichActionWidgetFactory.$class$java$util$logging$Level) == null) {
            $class$java$util$logging$Level = (RichActionWidgetFactory.$class$java$util$logging$Level = class$("java.util.logging.Level"));
        }
        return $class$java$util$logging$Level;
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
