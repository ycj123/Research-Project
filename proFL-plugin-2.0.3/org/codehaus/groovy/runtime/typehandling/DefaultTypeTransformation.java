// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime.typehandling;

import org.codehaus.groovy.reflection.ReflectionCache;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.IOException;
import groovy.lang.GroovyRuntimeException;
import java.io.File;
import groovy.lang.GString;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.codehaus.groovy.runtime.IteratorClosureAdapter;
import org.codehaus.groovy.runtime.MethodClosure;
import java.util.Map;
import java.util.Collections;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Array;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.math.BigInteger;

public class DefaultTypeTransformation
{
    protected static final Object[] EMPTY_ARGUMENTS;
    protected static final BigInteger ONE_NEG;
    
    public static byte byteUnbox(final Object value) {
        final Number n = castToNumber(value);
        return n.byteValue();
    }
    
    public static char charUnbox(final Object value) {
        return castToChar(value);
    }
    
    public static short shortUnbox(final Object value) {
        final Number n = castToNumber(value);
        return n.shortValue();
    }
    
    public static int intUnbox(final Object value) {
        final Number n = castToNumber(value);
        return n.intValue();
    }
    
    public static boolean booleanUnbox(final Object value) {
        return castToBoolean(value);
    }
    
    public static long longUnbox(final Object value) {
        final Number n = castToNumber(value);
        return n.longValue();
    }
    
    public static float floatUnbox(final Object value) {
        final Number n = castToNumber(value);
        return n.floatValue();
    }
    
    public static double doubleUnbox(final Object value) {
        final Number n = castToNumber(value);
        return n.doubleValue();
    }
    
    public static Object box(final boolean value) {
        return value ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public static Object box(final byte value) {
        return value;
    }
    
    public static Object box(final char value) {
        return value;
    }
    
    public static Object box(final short value) {
        return value;
    }
    
    public static Object box(final int value) {
        return value;
    }
    
    public static Object box(final long value) {
        return value;
    }
    
    public static Object box(final float value) {
        return value;
    }
    
    public static Object box(final double value) {
        return value;
    }
    
    public static Number castToNumber(final Object object) {
        return castToNumber(object, Number.class);
    }
    
    public static Number castToNumber(final Object object, final Class type) {
        if (object instanceof Number) {
            return (Number)object;
        }
        if (object instanceof Character) {
            return (int)(char)object;
        }
        if (!(object instanceof String)) {
            throw new GroovyCastException(object, type);
        }
        final String c = (String)object;
        if (c.length() == 1) {
            return (int)c.charAt(0);
        }
        throw new GroovyCastException(c, type);
    }
    
    public static boolean castToBoolean(final Object object) {
        return object != null && (boolean)InvokerHelper.invokeMethod(object, "asBoolean", InvokerHelper.EMPTY_ARGS);
    }
    
    public static char castToChar(final Object object) {
        if (object instanceof Character) {
            return (char)object;
        }
        if (object instanceof Number) {
            final Number value = (Number)object;
            return (char)value.intValue();
        }
        final String text = object.toString();
        if (text.length() == 1) {
            return text.charAt(0);
        }
        throw new GroovyCastException(text, Character.TYPE);
    }
    
    public static Object castToType(final Object object, final Class type) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: aload_1         /* type */
        //     7: ldc             Ljava/lang/Object;.class
        //     9: if_acmpne       14
        //    12: aload_0         /* object */
        //    13: areturn        
        //    14: aload_0         /* object */
        //    15: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    18: astore_2        /* aClass */
        //    19: aload_1         /* type */
        //    20: aload_2         /* aClass */
        //    21: if_acmpne       26
        //    24: aload_0         /* object */
        //    25: areturn        
        //    26: aload_1         /* type */
        //    27: invokestatic    org/codehaus/groovy/reflection/ReflectionCache.isArray:(Ljava/lang/Class;)Z
        //    30: ifeq            39
        //    33: aload_0         /* object */
        //    34: aload_1         /* type */
        //    35: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.asArray:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //    38: areturn        
        //    39: aload_1         /* type */
        //    40: aload_2         /* aClass */
        //    41: invokestatic    org/codehaus/groovy/reflection/ReflectionCache.isAssignableFrom:(Ljava/lang/Class;Ljava/lang/Class;)Z
        //    44: ifeq            49
        //    47: aload_0         /* object */
        //    48: areturn        
        //    49: ldc             Ljava/util/Collection;.class
        //    51: aload_1         /* type */
        //    52: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    55: ifeq            251
        //    58: aload_1         /* type */
        //    59: invokevirtual   java/lang/Class.getModifiers:()I
        //    62: istore_3        /* modifiers */
        //    63: aload_0         /* object */
        //    64: instanceof      Ljava/util/Collection;
        //    67: ifeq            111
        //    70: aload_1         /* type */
        //    71: ldc             Ljava/util/HashSet;.class
        //    73: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    76: ifeq            111
        //    79: aload_1         /* type */
        //    80: ldc             Ljava/util/HashSet;.class
        //    82: if_acmpeq       99
        //    85: iload_3         /* modifiers */
        //    86: invokestatic    java/lang/reflect/Modifier.isAbstract:(I)Z
        //    89: ifne            99
        //    92: iload_3         /* modifiers */
        //    93: invokestatic    java/lang/reflect/Modifier.isInterface:(I)Z
        //    96: ifeq            111
        //    99: new             Ljava/util/HashSet;
        //   102: dup            
        //   103: aload_0         /* object */
        //   104: checkcast       Ljava/util/Collection;
        //   107: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //   110: areturn        
        //   111: aload_2         /* aClass */
        //   112: invokevirtual   java/lang/Class.isArray:()Z
        //   115: ifeq            251
        //   118: aload_1         /* type */
        //   119: ldc             Ljava/util/ArrayList;.class
        //   121: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   124: ifeq            153
        //   127: iload_3         /* modifiers */
        //   128: invokestatic    java/lang/reflect/Modifier.isAbstract:(I)Z
        //   131: ifne            141
        //   134: iload_3         /* modifiers */
        //   135: invokestatic    java/lang/reflect/Modifier.isInterface:(I)Z
        //   138: ifeq            153
        //   141: new             Ljava/util/ArrayList;
        //   144: dup            
        //   145: invokespecial   java/util/ArrayList.<init>:()V
        //   148: astore          answer
        //   150: goto            208
        //   153: aload_1         /* type */
        //   154: invokevirtual   java/lang/Class.newInstance:()Ljava/lang/Object;
        //   157: checkcast       Ljava/util/Collection;
        //   160: astore          answer
        //   162: goto            208
        //   165: astore          e
        //   167: new             Lorg/codehaus/groovy/runtime/typehandling/GroovyCastException;
        //   170: dup            
        //   171: new             Ljava/lang/StringBuilder;
        //   174: dup            
        //   175: invokespecial   java/lang/StringBuilder.<init>:()V
        //   178: ldc             "Could not instantiate instance of: "
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: aload_1         /* type */
        //   184: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: ldc_w           ". Reason: "
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: aload           e
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   201: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   204: invokespecial   org/codehaus/groovy/runtime/typehandling/GroovyCastException.<init>:(Ljava/lang/String;)V
        //   207: athrow         
        //   208: aload_0         /* object */
        //   209: invokestatic    java/lang/reflect/Array.getLength:(Ljava/lang/Object;)I
        //   212: istore          length
        //   214: iconst_0       
        //   215: istore          i
        //   217: iload           i
        //   219: iload           length
        //   221: if_icmpge       248
        //   224: aload_0         /* object */
        //   225: iload           i
        //   227: invokestatic    java/lang/reflect/Array.get:(Ljava/lang/Object;I)Ljava/lang/Object;
        //   230: astore          element
        //   232: aload           answer
        //   234: aload           element
        //   236: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   241: pop            
        //   242: iinc            i, 1
        //   245: goto            217
        //   248: aload           answer
        //   250: areturn        
        //   251: aload_1         /* type */
        //   252: ldc             Ljava/lang/String;.class
        //   254: if_acmpne       262
        //   257: aload_0         /* object */
        //   258: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   261: areturn        
        //   262: aload_1         /* type */
        //   263: ldc             Ljava/lang/Character;.class
        //   265: if_acmpne       276
        //   268: aload_0         /* object */
        //   269: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.castToChar:(Ljava/lang/Object;)C
        //   272: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(C)Ljava/lang/Object;
        //   275: areturn        
        //   276: aload_1         /* type */
        //   277: ldc             Ljava/lang/Boolean;.class
        //   279: if_acmpne       290
        //   282: aload_0         /* object */
        //   283: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.castToBoolean:(Ljava/lang/Object;)Z
        //   286: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(Z)Ljava/lang/Object;
        //   289: areturn        
        //   290: aload_1         /* type */
        //   291: ldc             Ljava/lang/Class;.class
        //   293: if_acmpne       301
        //   296: aload_0         /* object */
        //   297: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.castToClass:(Ljava/lang/Object;)Ljava/lang/Class;
        //   300: areturn        
        //   301: ldc             Ljava/lang/Number;.class
        //   303: aload_1         /* type */
        //   304: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //   307: ifeq            639
        //   310: aload_0         /* object */
        //   311: aload_1         /* type */
        //   312: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.castToNumber:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Number;
        //   315: astore_3        /* n */
        //   316: aload_1         /* type */
        //   317: ldc             Ljava/lang/Byte;.class
        //   319: if_acmpne       334
        //   322: new             Ljava/lang/Byte;
        //   325: dup            
        //   326: aload_3         /* n */
        //   327: invokevirtual   java/lang/Number.byteValue:()B
        //   330: invokespecial   java/lang/Byte.<init>:(B)V
        //   333: areturn        
        //   334: aload_1         /* type */
        //   335: ldc             Ljava/lang/Character;.class
        //   337: if_acmpne       353
        //   340: new             Ljava/lang/Character;
        //   343: dup            
        //   344: aload_3         /* n */
        //   345: invokevirtual   java/lang/Number.intValue:()I
        //   348: i2c            
        //   349: invokespecial   java/lang/Character.<init>:(C)V
        //   352: areturn        
        //   353: aload_1         /* type */
        //   354: ldc             Ljava/lang/Short;.class
        //   356: if_acmpne       371
        //   359: new             Ljava/lang/Short;
        //   362: dup            
        //   363: aload_3         /* n */
        //   364: invokevirtual   java/lang/Number.shortValue:()S
        //   367: invokespecial   java/lang/Short.<init>:(S)V
        //   370: areturn        
        //   371: aload_1         /* type */
        //   372: ldc             Ljava/lang/Integer;.class
        //   374: if_acmpne       385
        //   377: aload_3         /* n */
        //   378: invokevirtual   java/lang/Number.intValue:()I
        //   381: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   384: areturn        
        //   385: aload_1         /* type */
        //   386: ldc             Ljava/lang/Long;.class
        //   388: if_acmpne       403
        //   391: new             Ljava/lang/Long;
        //   394: dup            
        //   395: aload_3         /* n */
        //   396: invokevirtual   java/lang/Number.longValue:()J
        //   399: invokespecial   java/lang/Long.<init>:(J)V
        //   402: areturn        
        //   403: aload_1         /* type */
        //   404: ldc             Ljava/lang/Float;.class
        //   406: if_acmpne       421
        //   409: new             Ljava/lang/Float;
        //   412: dup            
        //   413: aload_3         /* n */
        //   414: invokevirtual   java/lang/Number.floatValue:()F
        //   417: invokespecial   java/lang/Float.<init>:(F)V
        //   420: areturn        
        //   421: aload_1         /* type */
        //   422: ldc             Ljava/lang/Double;.class
        //   424: if_acmpne       524
        //   427: new             Ljava/lang/Double;
        //   430: dup            
        //   431: aload_3         /* n */
        //   432: invokevirtual   java/lang/Number.doubleValue:()D
        //   435: invokespecial   java/lang/Double.<init>:(D)V
        //   438: astore          answer
        //   440: aload_3         /* n */
        //   441: instanceof      Ljava/lang/Double;
        //   444: ifne            521
        //   447: aload           answer
        //   449: invokevirtual   java/lang/Double.doubleValue:()D
        //   452: ldc2_w          -Infinity
        //   455: dcmpl          
        //   456: ifeq            471
        //   459: aload           answer
        //   461: invokevirtual   java/lang/Double.doubleValue:()D
        //   464: ldc2_w          Infinity
        //   467: dcmpl          
        //   468: ifne            521
        //   471: new             Lgroovy/lang/GroovyRuntimeException;
        //   474: dup            
        //   475: new             Ljava/lang/StringBuilder;
        //   478: dup            
        //   479: invokespecial   java/lang/StringBuilder.<init>:()V
        //   482: ldc_w           "Automatic coercion of "
        //   485: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   488: aload_3         /* n */
        //   489: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   492: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   495: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   498: ldc_w           " value "
        //   501: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   504: aload_3         /* n */
        //   505: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   508: ldc_w           " to double failed.  Value is out of range."
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   517: invokespecial   groovy/lang/GroovyRuntimeException.<init>:(Ljava/lang/String;)V
        //   520: athrow         
        //   521: aload           answer
        //   523: areturn        
        //   524: aload_1         /* type */
        //   525: ldc_w           Ljava/math/BigDecimal;.class
        //   528: if_acmpne       569
        //   531: aload_3         /* n */
        //   532: instanceof      Ljava/lang/Float;
        //   535: ifne            545
        //   538: aload_3         /* n */
        //   539: instanceof      Ljava/lang/Double;
        //   542: ifeq            557
        //   545: new             Ljava/math/BigDecimal;
        //   548: dup            
        //   549: aload_3         /* n */
        //   550: invokevirtual   java/lang/Number.doubleValue:()D
        //   553: invokespecial   java/math/BigDecimal.<init>:(D)V
        //   556: areturn        
        //   557: new             Ljava/math/BigDecimal;
        //   560: dup            
        //   561: aload_3         /* n */
        //   562: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   565: invokespecial   java/math/BigDecimal.<init>:(Ljava/lang/String;)V
        //   568: areturn        
        //   569: aload_1         /* type */
        //   570: ldc_w           Ljava/math/BigInteger;.class
        //   573: if_acmpne       636
        //   576: aload_0         /* object */
        //   577: instanceof      Ljava/lang/Float;
        //   580: ifne            590
        //   583: aload_0         /* object */
        //   584: instanceof      Ljava/lang/Double;
        //   587: ifeq            609
        //   590: new             Ljava/math/BigDecimal;
        //   593: dup            
        //   594: aload_3         /* n */
        //   595: invokevirtual   java/lang/Number.doubleValue:()D
        //   598: invokespecial   java/math/BigDecimal.<init>:(D)V
        //   601: astore          bd
        //   603: aload           bd
        //   605: invokevirtual   java/math/BigDecimal.toBigInteger:()Ljava/math/BigInteger;
        //   608: areturn        
        //   609: aload_0         /* object */
        //   610: instanceof      Ljava/math/BigDecimal;
        //   613: ifeq            624
        //   616: aload_0         /* object */
        //   617: checkcast       Ljava/math/BigDecimal;
        //   620: invokevirtual   java/math/BigDecimal.toBigInteger:()Ljava/math/BigInteger;
        //   623: areturn        
        //   624: new             Ljava/math/BigInteger;
        //   627: dup            
        //   628: aload_3         /* n */
        //   629: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   632: invokespecial   java/math/BigInteger.<init>:(Ljava/lang/String;)V
        //   635: areturn        
        //   636: goto            894
        //   639: aload_1         /* type */
        //   640: invokevirtual   java/lang/Class.isPrimitive:()Z
        //   643: ifeq            848
        //   646: aload_1         /* type */
        //   647: getstatic       java/lang/Boolean.TYPE:Ljava/lang/Class;
        //   650: if_acmpne       661
        //   653: aload_0         /* object */
        //   654: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   657: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(Z)Ljava/lang/Object;
        //   660: areturn        
        //   661: aload_1         /* type */
        //   662: getstatic       java/lang/Byte.TYPE:Ljava/lang/Class;
        //   665: if_acmpne       676
        //   668: aload_0         /* object */
        //   669: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.byteUnbox:(Ljava/lang/Object;)B
        //   672: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(B)Ljava/lang/Object;
        //   675: areturn        
        //   676: aload_1         /* type */
        //   677: getstatic       java/lang/Character.TYPE:Ljava/lang/Class;
        //   680: if_acmpne       691
        //   683: aload_0         /* object */
        //   684: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.charUnbox:(Ljava/lang/Object;)C
        //   687: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(C)Ljava/lang/Object;
        //   690: areturn        
        //   691: aload_1         /* type */
        //   692: getstatic       java/lang/Short.TYPE:Ljava/lang/Class;
        //   695: if_acmpne       706
        //   698: aload_0         /* object */
        //   699: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.shortUnbox:(Ljava/lang/Object;)S
        //   702: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(S)Ljava/lang/Object;
        //   705: areturn        
        //   706: aload_1         /* type */
        //   707: getstatic       java/lang/Integer.TYPE:Ljava/lang/Class;
        //   710: if_acmpne       721
        //   713: aload_0         /* object */
        //   714: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.intUnbox:(Ljava/lang/Object;)I
        //   717: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(I)Ljava/lang/Object;
        //   720: areturn        
        //   721: aload_1         /* type */
        //   722: getstatic       java/lang/Long.TYPE:Ljava/lang/Class;
        //   725: if_acmpne       736
        //   728: aload_0         /* object */
        //   729: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.longUnbox:(Ljava/lang/Object;)J
        //   732: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(J)Ljava/lang/Object;
        //   735: areturn        
        //   736: aload_1         /* type */
        //   737: getstatic       java/lang/Float.TYPE:Ljava/lang/Class;
        //   740: if_acmpne       751
        //   743: aload_0         /* object */
        //   744: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.floatUnbox:(Ljava/lang/Object;)F
        //   747: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(F)Ljava/lang/Object;
        //   750: areturn        
        //   751: aload_1         /* type */
        //   752: getstatic       java/lang/Double.TYPE:Ljava/lang/Class;
        //   755: if_acmpne       894
        //   758: new             Ljava/lang/Double;
        //   761: dup            
        //   762: aload_0         /* object */
        //   763: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.doubleUnbox:(Ljava/lang/Object;)D
        //   766: invokespecial   java/lang/Double.<init>:(D)V
        //   769: astore_3        /* answer */
        //   770: aload_0         /* object */
        //   771: instanceof      Ljava/lang/Double;
        //   774: ifne            846
        //   777: aload_3         /* answer */
        //   778: invokevirtual   java/lang/Double.doubleValue:()D
        //   781: ldc2_w          -Infinity
        //   784: dcmpl          
        //   785: ifeq            799
        //   788: aload_3         /* answer */
        //   789: invokevirtual   java/lang/Double.doubleValue:()D
        //   792: ldc2_w          Infinity
        //   795: dcmpl          
        //   796: ifne            846
        //   799: new             Lgroovy/lang/GroovyRuntimeException;
        //   802: dup            
        //   803: new             Ljava/lang/StringBuilder;
        //   806: dup            
        //   807: invokespecial   java/lang/StringBuilder.<init>:()V
        //   810: ldc_w           "Automatic coercion of "
        //   813: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   816: aload_2         /* aClass */
        //   817: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   820: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   823: ldc_w           " value "
        //   826: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   829: aload_0         /* object */
        //   830: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   833: ldc_w           " to double failed.  Value is out of range."
        //   836: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   842: invokespecial   groovy/lang/GroovyRuntimeException.<init>:(Ljava/lang/String;)V
        //   845: athrow         
        //   846: aload_3         /* answer */
        //   847: areturn        
        //   848: aload_0         /* object */
        //   849: instanceof      Ljava/lang/String;
        //   852: ifeq            871
        //   855: aload_1         /* type */
        //   856: invokevirtual   java/lang/Class.isEnum:()Z
        //   859: ifeq            871
        //   862: aload_1         /* type */
        //   863: aload_0         /* object */
        //   864: checkcast       Ljava/lang/String;
        //   867: invokestatic    java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
        //   870: areturn        
        //   871: aload_0         /* object */
        //   872: instanceof      Lgroovy/lang/GString;
        //   875: ifeq            894
        //   878: aload_1         /* type */
        //   879: invokevirtual   java/lang/Class.isEnum:()Z
        //   882: ifeq            894
        //   885: aload_1         /* type */
        //   886: aload_0         /* object */
        //   887: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   890: invokestatic    java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
        //   893: areturn        
        //   894: aconst_null    
        //   895: astore_3        /* args */
        //   896: aload_0         /* object */
        //   897: instanceof      Ljava/util/Collection;
        //   900: ifeq            920
        //   903: aload_0         /* object */
        //   904: checkcast       Ljava/util/Collection;
        //   907: astore          collection
        //   909: aload           collection
        //   911: invokeinterface java/util/Collection.toArray:()[Ljava/lang/Object;
        //   916: astore_3        /* args */
        //   917: goto            954
        //   920: aload_0         /* object */
        //   921: instanceof      [Ljava/lang/Object;
        //   924: ifeq            938
        //   927: aload_0         /* object */
        //   928: checkcast       [Ljava/lang/Object;
        //   931: checkcast       [Ljava/lang/Object;
        //   934: astore_3        /* args */
        //   935: goto            954
        //   938: aload_0         /* object */
        //   939: instanceof      Ljava/util/Map;
        //   942: ifeq            954
        //   945: iconst_1       
        //   946: anewarray       Ljava/lang/Object;
        //   949: astore_3        /* args */
        //   950: aload_3         /* args */
        //   951: iconst_0       
        //   952: aload_0         /* object */
        //   953: aastore        
        //   954: aconst_null    
        //   955: astore          nested
        //   957: aload_3         /* args */
        //   958: ifnull          978
        //   961: aload_1         /* type */
        //   962: aload_3         /* args */
        //   963: invokestatic    org/codehaus/groovy/runtime/InvokerHelper.invokeConstructorOf:(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/Object;
        //   966: areturn        
        //   967: astore          iie
        //   969: aload           iie
        //   971: athrow         
        //   972: astore          e
        //   974: aload           e
        //   976: astore          nested
        //   978: aload           nested
        //   980: ifnull          999
        //   983: new             Lorg/codehaus/groovy/runtime/typehandling/GroovyCastException;
        //   986: dup            
        //   987: aload_0         /* object */
        //   988: aload_1         /* type */
        //   989: aload           nested
        //   991: invokespecial   org/codehaus/groovy/runtime/typehandling/GroovyCastException.<init>:(Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Exception;)V
        //   994: astore          gce
        //   996: goto            1010
        //   999: new             Lorg/codehaus/groovy/runtime/typehandling/GroovyCastException;
        //  1002: dup            
        //  1003: aload_0         /* object */
        //  1004: aload_1         /* type */
        //  1005: invokespecial   org/codehaus/groovy/runtime/typehandling/GroovyCastException.<init>:(Ljava/lang/Object;Ljava/lang/Class;)V
        //  1008: astore          gce
        //  1010: aload           gce
        //  1012: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                    
        //  -----  -----  -----  -----  --------------------------------------------------------
        //  153    162    165    208    Ljava/lang/Exception;
        //  961    966    967    972    Lorg/codehaus/groovy/runtime/InvokerInvocationException;
        //  961    966    972    978    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.accept(CoreMetadataFactory.java:653)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.MetadataHelper.substituteGenericArguments(MetadataHelper.java:1100)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2676)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:881)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2669)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryArguments(TypeAnalysis.java:2834)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2195)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryArguments(TypeAnalysis.java:2834)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2195)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryArguments(TypeAnalysis.java:2834)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2195)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryArguments(TypeAnalysis.java:2834)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2195)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryArguments(TypeAnalysis.java:2834)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2195)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.invalidateDependentExpressions(TypeAnalysis.java:759)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1011)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryArguments(TypeAnalysis.java:2796)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2195)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static Class castToClass(final Object object) {
        try {
            return Class.forName(object.toString());
        }
        catch (Exception e) {
            throw new GroovyCastException(object, Class.class, e);
        }
    }
    
    public static Object asArray(final Object object, final Class type) {
        if (type.isAssignableFrom(object.getClass())) {
            return object;
        }
        final Collection list = asCollection(object);
        final int size = list.size();
        final Class elementType = type.getComponentType();
        final Object array = Array.newInstance(elementType, size);
        int idx = 0;
        if (Boolean.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setBoolean(array, idx, booleanUnbox(element));
                ++idx;
            }
        }
        else if (Byte.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setByte(array, idx, byteUnbox(element));
                ++idx;
            }
        }
        else if (Character.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setChar(array, idx, charUnbox(element));
                ++idx;
            }
        }
        else if (Double.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setDouble(array, idx, doubleUnbox(element));
                ++idx;
            }
        }
        else if (Float.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setFloat(array, idx, floatUnbox(element));
                ++idx;
            }
        }
        else if (Integer.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setInt(array, idx, intUnbox(element));
                ++idx;
            }
        }
        else if (Long.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setLong(array, idx, longUnbox(element));
                ++idx;
            }
        }
        else if (Short.TYPE.equals(elementType)) {
            for (final Object element : list) {
                Array.setShort(array, idx, shortUnbox(element));
                ++idx;
            }
        }
        else {
            for (final Object element : list) {
                final Object coercedElement = castToType(element, elementType);
                Array.set(array, idx, coercedElement);
                ++idx;
            }
        }
        return array;
    }
    
    public static <T> Collection<T> asCollection(final T[] value) {
        return (Collection<T>)arrayAsCollection((Object[])value);
    }
    
    public static Collection asCollection(final Object value) {
        if (value == null) {
            return Collections.EMPTY_LIST;
        }
        if (value instanceof Collection) {
            return (Collection)value;
        }
        if (value instanceof Map) {
            final Map map = (Map)value;
            return map.entrySet();
        }
        if (value.getClass().isArray()) {
            return arrayAsCollection(value);
        }
        if (value instanceof MethodClosure) {
            final MethodClosure method = (MethodClosure)value;
            final IteratorClosureAdapter adapter = new IteratorClosureAdapter(method.getDelegate());
            method.call(adapter);
            return adapter.asList();
        }
        if (value instanceof String) {
            return DefaultGroovyMethods.toList((String)value);
        }
        if (value instanceof GString) {
            return DefaultGroovyMethods.toList(value.toString());
        }
        if (value instanceof File) {
            try {
                return DefaultGroovyMethods.readLines((File)value);
            }
            catch (IOException e) {
                throw new GroovyRuntimeException("Error reading file: " + value, e);
            }
        }
        if (isEnumSubclass(value)) {
            final Object[] values = (Object[])InvokerHelper.invokeMethod(value, "values", new Object[0]);
            return Arrays.asList(values);
        }
        return Collections.singletonList(value);
    }
    
    public static Collection arrayAsCollection(final Object value) {
        if (value.getClass().getComponentType().isPrimitive()) {
            return primitiveArrayToList(value);
        }
        return arrayAsCollection((Object[])value);
    }
    
    public static <T> Collection<T> arrayAsCollection(final T[] value) {
        return Arrays.asList((T[])value);
    }
    
    public static boolean isEnumSubclass(final Object value) {
        if (value instanceof Class) {
            for (Class superclass = ((Class)value).getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
                if (superclass.getName().equals("java.lang.Enum")) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static List primitiveArrayToList(final Object array) {
        final int size = Array.getLength(array);
        final List list = new ArrayList(size);
        for (int i = 0; i < size; ++i) {
            Object item = Array.get(array, i);
            if (item != null && item.getClass().isArray() && item.getClass().getComponentType().isPrimitive()) {
                item = primitiveArrayToList(item);
            }
            list.add(item);
        }
        return list;
    }
    
    public static Object[] primitiveArrayBox(final Object array) {
        final int size = Array.getLength(array);
        final Object[] ret = (Object[])Array.newInstance(ReflectionCache.autoboxType(array.getClass().getComponentType()), size);
        for (int i = 0; i < size; ++i) {
            ret[i] = Array.get(array, i);
        }
        return ret;
    }
    
    public static int compareTo(final Object left, final Object right) {
        return compareToWithEqualityCheck(left, right, false);
    }
    
    private static int compareToWithEqualityCheck(final Object left, final Object right, final boolean equalityCheckOnly) {
        if (left == right) {
            return 0;
        }
        if (left == null) {
            return -1;
        }
        if (right == null) {
            return 1;
        }
        if (left instanceof Comparable) {
            if (left instanceof Number) {
                if (isValidCharacterString(right)) {
                    return DefaultGroovyMethods.compareTo((Number)left, (Character)box(castToChar(right)));
                }
                if (right instanceof Character || right instanceof Number) {
                    return DefaultGroovyMethods.compareTo((Number)left, castToNumber(right));
                }
            }
            else if (left instanceof Character) {
                if (isValidCharacterString(right)) {
                    return DefaultGroovyMethods.compareTo((Character)left, (Character)box(castToChar(right)));
                }
                if (right instanceof Number) {
                    return DefaultGroovyMethods.compareTo((Character)left, (Number)right);
                }
            }
            else if (right instanceof Number) {
                if (isValidCharacterString(left)) {
                    return DefaultGroovyMethods.compareTo((Character)box(castToChar(left)), (Number)right);
                }
            }
            else {
                if (left instanceof String && right instanceof Character) {
                    return ((String)left).compareTo(right.toString());
                }
                if (left instanceof String && right instanceof GString) {
                    return ((String)left).compareTo(right.toString());
                }
            }
            if (!equalityCheckOnly || left.getClass().isAssignableFrom(right.getClass()) || (right.getClass() != Object.class && right.getClass().isAssignableFrom(left.getClass())) || (left instanceof GString && right instanceof String)) {
                final Comparable comparable = (Comparable)left;
                return comparable.compareTo(right);
            }
        }
        if (equalityCheckOnly) {
            return -1;
        }
        throw new GroovyRuntimeException("Cannot compare " + left.getClass().getName() + " with value '" + left + "' and " + right.getClass().getName() + " with value '" + right + "'");
    }
    
    public static boolean compareEqual(Object left, Object right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left instanceof Comparable) {
            return compareToWithEqualityCheck(left, right, true) == 0;
        }
        final Class leftClass = left.getClass();
        final Class rightClass = right.getClass();
        if (leftClass.isArray() && rightClass.isArray()) {
            return compareArrayEqual(left, right);
        }
        if (leftClass.isArray() && leftClass.getComponentType().isPrimitive()) {
            left = primitiveArrayToList(left);
        }
        if (rightClass.isArray() && rightClass.getComponentType().isPrimitive()) {
            right = primitiveArrayToList(right);
        }
        if (left instanceof Object[] && right instanceof List) {
            return DefaultGroovyMethods.equals((Object[])left, (List)right);
        }
        if (left instanceof List && right instanceof Object[]) {
            return DefaultGroovyMethods.equals((List)left, (Object[])right);
        }
        if (left instanceof List && right instanceof List) {
            return DefaultGroovyMethods.equals((List)left, (List)right);
        }
        if (left instanceof Map.Entry && right instanceof Map.Entry) {
            final Object k1 = ((Map.Entry)left).getKey();
            final Object k2 = ((Map.Entry)right).getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                final Object v1 = ((Map.Entry)left).getValue();
                final Object v2 = ((Map.Entry)right).getValue();
                if (v1 == v2 || (v1 != null && compareEqual(v1, v2))) {
                    return true;
                }
            }
            return false;
        }
        return (boolean)InvokerHelper.invokeMethod(left, "equals", right);
    }
    
    public static boolean compareArrayEqual(final Object left, final Object right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (Array.getLength(left) != Array.getLength(right)) {
            return false;
        }
        for (int i = 0; i < Array.getLength(left); ++i) {
            final Object l = Array.get(left, i);
            final Object r = Array.get(right, i);
            if (!compareEqual(l, r)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValidCharacterString(final Object value) {
        if (value instanceof String) {
            final String s = (String)value;
            if (s.length() == 1) {
                return true;
            }
        }
        return false;
    }
    
    public static int[] convertToIntArray(final Object a) {
        int[] ans = null;
        if (a.getClass().getName().equals("[I")) {
            ans = (int[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new int[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = ((Number)ia[i]).intValue();
                }
            }
        }
        return ans;
    }
    
    public static boolean[] convertToBooleanArray(final Object a) {
        boolean[] ans = null;
        if (a instanceof boolean[]) {
            ans = (boolean[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new boolean[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = (boolean)ia[i];
                }
            }
        }
        return ans;
    }
    
    public static byte[] convertToByteArray(final Object a) {
        byte[] ans = null;
        if (a instanceof byte[]) {
            ans = (byte[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new byte[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = ((Number)ia[i]).byteValue();
                }
            }
        }
        return ans;
    }
    
    public static short[] convertToShortArray(final Object a) {
        short[] ans = null;
        if (a instanceof short[]) {
            ans = (short[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new short[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                ans[i] = ((Number)ia[i]).shortValue();
            }
        }
        return ans;
    }
    
    public static char[] convertToCharArray(final Object a) {
        char[] ans = null;
        if (a instanceof char[]) {
            ans = (char[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new char[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = (char)ia[i];
                }
            }
        }
        return ans;
    }
    
    public static long[] convertToLongArray(final Object a) {
        long[] ans = null;
        if (a instanceof long[]) {
            ans = (long[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new long[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = ((Number)ia[i]).longValue();
                }
            }
        }
        return ans;
    }
    
    public static float[] convertToFloatArray(final Object a) {
        float[] ans = null;
        if (a instanceof float[]) {
            ans = (float[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new float[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = ((Number)ia[i]).floatValue();
                }
            }
        }
        return ans;
    }
    
    public static double[] convertToDoubleArray(final Object a) {
        double[] ans = null;
        if (a instanceof double[]) {
            ans = (double[])a;
        }
        else {
            final Object[] ia = (Object[])a;
            ans = new double[ia.length];
            for (int i = 0; i < ia.length; ++i) {
                if (ia[i] != null) {
                    ans[i] = ((Number)ia[i]).doubleValue();
                }
            }
        }
        return ans;
    }
    
    public static Object convertToPrimitiveArray(final Object a, final Class type) {
        if (type == Byte.TYPE) {
            return convertToByteArray(a);
        }
        if (type == Boolean.TYPE) {
            return convertToBooleanArray(a);
        }
        if (type == Short.TYPE) {
            return convertToShortArray(a);
        }
        if (type == Character.TYPE) {
            return convertToCharArray(a);
        }
        if (type == Integer.TYPE) {
            return convertToIntArray(a);
        }
        if (type == Long.TYPE) {
            return convertToLongArray(a);
        }
        if (type == Float.TYPE) {
            return convertToFloatArray(a);
        }
        if (type == Double.TYPE) {
            return convertToDoubleArray(a);
        }
        return a;
    }
    
    public static Character getCharFromSizeOneString(Object value) {
        if (value instanceof GString) {
            value = value.toString();
        }
        if (!(value instanceof String)) {
            return (Character)value;
        }
        final String s = (String)value;
        if (s.length() != 1) {
            throw new IllegalArgumentException("String of length 1 expected but got a bigger one");
        }
        return new Character(s.charAt(0));
    }
    
    static {
        EMPTY_ARGUMENTS = new Object[0];
        ONE_NEG = new BigInteger("-1");
    }
}
