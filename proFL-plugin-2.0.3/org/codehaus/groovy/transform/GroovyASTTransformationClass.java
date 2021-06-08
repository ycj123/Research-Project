// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE })
public @interface GroovyASTTransformationClass {
    String[] value() default {};
    
    Class[] classes() default {};
}
