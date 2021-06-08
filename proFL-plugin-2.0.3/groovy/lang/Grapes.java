// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.lang.annotation.Annotation;

public @interface Grapes {
    Grab[] value();
    
    boolean initClass() default true;
}
