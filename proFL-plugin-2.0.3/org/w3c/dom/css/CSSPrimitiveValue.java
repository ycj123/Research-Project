// 
// Decompiled by Procyon v0.5.36
// 

package org.w3c.dom.css;

import org.w3c.dom.DOMException;

public interface CSSPrimitiveValue extends CSSValue
{
    public static final short CSS_UNKNOWN = 0;
    public static final short CSS_NUMBER = 1;
    public static final short CSS_PERCENTAGE = 2;
    public static final short CSS_EMS = 3;
    public static final short CSS_EXS = 4;
    public static final short CSS_PX = 5;
    public static final short CSS_CM = 6;
    public static final short CSS_MM = 7;
    public static final short CSS_IN = 8;
    public static final short CSS_PT = 9;
    public static final short CSS_PC = 10;
    public static final short CSS_DEG = 11;
    public static final short CSS_RAD = 12;
    public static final short CSS_GRAD = 13;
    public static final short CSS_MS = 14;
    public static final short CSS_S = 15;
    public static final short CSS_HZ = 16;
    public static final short CSS_KHZ = 17;
    public static final short CSS_DIMENSION = 18;
    public static final short CSS_STRING = 19;
    public static final short CSS_URI = 20;
    public static final short CSS_IDENT = 21;
    public static final short CSS_ATTR = 22;
    public static final short CSS_COUNTER = 23;
    public static final short CSS_RECT = 24;
    public static final short CSS_RGBCOLOR = 25;
    
    short getPrimitiveType();
    
    void setFloatValue(final short p0, final float p1) throws DOMException;
    
    float getFloatValue(final short p0) throws DOMException;
    
    void setStringValue(final short p0, final String p1) throws DOMException;
    
    String getStringValue() throws DOMException;
    
    Counter getCounterValue() throws DOMException;
    
    Rect getRectValue() throws DOMException;
    
    RGBColor getRGBColorValue() throws DOMException;
}
