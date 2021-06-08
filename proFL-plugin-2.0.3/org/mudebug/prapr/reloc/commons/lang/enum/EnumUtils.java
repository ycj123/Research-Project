// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.lang.enum;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EnumUtils
{
    public static Enum getEnum(final Class enumClass, final String name) {
        return Enum.getEnum(enumClass, name);
    }
    
    public static ValuedEnum getEnum(final Class enumClass, final int value) {
        return (ValuedEnum)ValuedEnum.getEnum(enumClass, value);
    }
    
    public static Map getEnumMap(final Class enumClass) {
        return Enum.getEnumMap(enumClass);
    }
    
    public static List getEnumList(final Class enumClass) {
        return Enum.getEnumList(enumClass);
    }
    
    public static Iterator iterator(final Class enumClass) {
        return Enum.getEnumList(enumClass).iterator();
    }
}
