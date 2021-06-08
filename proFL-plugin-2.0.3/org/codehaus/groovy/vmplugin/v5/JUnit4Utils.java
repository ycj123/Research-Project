// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.vmplugin.v5;

import groovy.lang.GroovyRuntimeException;
import java.util.List;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.lang.reflect.Method;
import java.lang.annotation.Annotation;
import groovy.lang.GroovyClassLoader;

public class JUnit4Utils
{
    static Boolean realIsJUnit4Test(final Class scriptClass, final GroovyClassLoader loader) {
        boolean isTest = false;
        try {
            try {
                final Class runWithAnnotationClass = loader.loadClass("org.junit.runner.RunWith");
                Annotation annotation = scriptClass.getAnnotation(runWithAnnotationClass);
                if (annotation != null) {
                    isTest = true;
                }
                else {
                    final Class testAnnotationClass = loader.loadClass("org.junit.Test");
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
    
    static Object realRunJUnit4Test(final Class scriptClass, final GroovyClassLoader loader) {
        try {
            final Class junitCoreClass = loader.loadClass("org.junit.runner.JUnitCore");
            final Object result = InvokerHelper.invokeStaticMethod(junitCoreClass, "runClasses", new Object[] { scriptClass });
            System.out.print("JUnit 4 Runner, Tests: " + InvokerHelper.getProperty(result, "runCount"));
            System.out.print(", Failures: " + InvokerHelper.getProperty(result, "failureCount"));
            System.out.println(", Time: " + InvokerHelper.getProperty(result, "runTime"));
            final List failures = (List)InvokerHelper.getProperty(result, "failures");
            for (int i = 0; i < failures.size(); ++i) {
                final Object f = failures.get(i);
                System.out.println("Test Failure: " + InvokerHelper.getProperty(f, "description"));
                System.out.println(InvokerHelper.getProperty(f, "trace"));
            }
            return result;
        }
        catch (ClassNotFoundException e) {
            throw new GroovyRuntimeException("Error running JUnit 4 test.", e);
        }
    }
}
