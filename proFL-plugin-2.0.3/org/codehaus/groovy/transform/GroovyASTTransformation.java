// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform;

import org.codehaus.groovy.control.CompilePhase;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface GroovyASTTransformation {
    CompilePhase phase() default CompilePhase.CANONICALIZATION;
}
