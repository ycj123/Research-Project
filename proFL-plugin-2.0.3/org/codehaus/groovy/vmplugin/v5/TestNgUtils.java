// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v5;

import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import groovy.lang.GroovyClassLoader;

public class TestNgUtils
{
    static Boolean realIsTestNgTest(final Class scriptClass, final GroovyClassLoader loader) {
        boolean isTest = false;
        try {
            try {
                final Class testAnnotationClass = loader.loadClass("org.testng.annotations.Test");
                Annotation annotation = scriptClass.getAnnotation(testAnnotationClass);
                if (annotation != null) {
                    isTest = true;
                }
                else {
                    final Method[] methods = scriptClass.getMethods();
                    for (int i = 0; i < methods.length; ++i) {
                        final Method method = methods[i];
                        annotation = method.getAnnotation((Class<Annotation>)testAnnotationClass);
                        if (annotation != null) {
                            isTest = true;
                            break;
                        }
                    }
                }
            }
            catch (ClassNotFoundException ex) {}
        }
        catch (Throwable t) {}
        return isTest ? Boolean.TRUE : Boolean.FALSE;
    }
    
    static Object realRunTestNgTest(final Class scriptClass, final GroovyClassLoader loader) {
        try {
            final Class testNGClass = loader.loadClass("org.testng.TestNG");
            final Object testng = InvokerHelper.invokeConstructorOf(testNGClass, new Object[0]);
            InvokerHelper.invokeMethod(testng, "setTestClasses", new Object[] { scriptClass });
            final Class listenerClass = loader.loadClass("org.testng.TestListenerAdapter");
            final Object listener = InvokerHelper.invokeConstructorOf(listenerClass, new Object[0]);
            InvokerHelper.invokeMethod(testng, "addListener", new Object[] { listener });
            final Object result = InvokerHelper.invokeMethod(testng, "run", new Object[0]);
            return result;
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Error running TestNG test.", e);
        }
    }
}
