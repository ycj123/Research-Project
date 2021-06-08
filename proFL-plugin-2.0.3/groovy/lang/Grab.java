// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE })
public @interface Grab {
    String group() default "";
    
    String module();
    
    String version();
    
    String classifier() default "";
    
    boolean transitive() default true;
    
    String conf() default "";
    
    String ext() default "";
    
    String value() default "";
    
    boolean initClass() default true;
}
