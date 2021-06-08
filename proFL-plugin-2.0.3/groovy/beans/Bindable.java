// 
// Decompiled by Procyon v0.5.36
// 

package groovy.beans;

import org.codehaus.groovy.transform.GroovyASTTransformationClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.FIELD, ElementType.TYPE })
@GroovyASTTransformationClass({ "groovy.beans.BindableASTTransformation" })
public @interface Bindable {
}
