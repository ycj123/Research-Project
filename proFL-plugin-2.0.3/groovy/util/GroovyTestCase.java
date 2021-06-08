// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Method;
import groovy.lang.GroovyRuntimeException;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.Closure;
import groovy.lang.GroovyShell;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.logging.Logger;
import junit.framework.TestCase;

public class GroovyTestCase extends TestCase
{
    protected static Logger log;
    private static int counter;
    private static final int MAX_NESTED_EXCEPTIONS = 10;
    public static final String TEST_SCRIPT_NAME_PREFIX = "TestScript";
    private boolean useAgileDoxNaming;
    private static final ThreadLocal notYetImplementedFlag;
    
    public GroovyTestCase() {
        this.useAgileDoxNaming = false;
    }
    
    public String getName() {
        if (this.useAgileDoxNaming) {
            return super.getName().substring(4).replaceAll("([A-Z])", " $1").toLowerCase();
        }
        return super.getName();
    }
    
    public String getMethodName() {
        return super.getName();
    }
    
    protected void assertArrayEquals(final Object[] expected, final Object[] value) {
        final String message = "expected array: " + InvokerHelper.toString(expected) + " value array: " + InvokerHelper.toString(value);
        assertNotNull(message + ": expected should not be null", (Object)expected);
        assertNotNull(message + ": value should not be null", (Object)value);
        assertEquals(message, expected.length, value.length);
        for (int i = 0, size = expected.length; i < size; ++i) {
            assertEquals("value[" + i + "] when " + message, expected[i], value[i]);
        }
    }
    
    protected void assertLength(final int length, final char[] array) {
        assertEquals(length, array.length);
    }
    
    protected void assertLength(final int length, final int[] array) {
        assertEquals(length, array.length);
    }
    
    protected void assertLength(final int length, final Object[] array) {
        assertEquals(length, array.length);
    }
    
    protected void assertContains(final char expected, final char[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == expected) {
                return;
            }
        }
        final StringBuffer message = new StringBuffer();
        message.append(expected).append(" not in {");
        for (int j = 0; j < array.length; ++j) {
            message.append("'").append(array[j]).append("'");
            if (j < array.length - 1) {
                message.append(", ");
            }
        }
        message.append(" }");
        fail(message.toString());
    }
    
    protected void assertContains(final int expected, final int[] array) {
        for (final int anInt : array) {
            if (anInt == expected) {
                return;
            }
        }
        final StringBuffer message = new StringBuffer();
        message.append(expected).append(" not in {");
        for (int i = 0; i < array.length; ++i) {
            message.append("'").append(array[i]).append("'");
            if (i < array.length - 1) {
                message.append(", ");
            }
        }
        message.append(" }");
        fail(message.toString());
    }
    
    protected void assertToString(final Object value, final String expected) {
        final Object console = InvokerHelper.invokeMethod(value, "toString", null);
        assertEquals("toString() on value: " + value, expected, console);
    }
    
    protected void assertInspect(final Object value, final String expected) {
        final Object console = InvokerHelper.invokeMethod(value, "inspect", null);
        assertEquals("inspect() on value: " + value, expected, console);
    }
    
    protected void assertScript(final String script) throws Exception {
        final GroovyShell shell = new GroovyShell();
        shell.evaluate(script, this.getTestClassName());
    }
    
    protected String getTestClassName() {
        return "TestScript" + this.getMethodName() + GroovyTestCase.counter++ + ".groovy";
    }
    
    protected String shouldFail(final Closure code) {
        boolean failed = false;
        String result = null;
        try {
            code.call();
        }
        catch (GroovyRuntimeException gre) {
            failed = true;
            result = ScriptBytecodeAdapter.unwrap(gre).getMessage();
        }
        catch (Throwable e) {
            failed = true;
            result = e.getMessage();
        }
        assertTrue("Closure " + code + " should have failed", failed);
        return result;
    }
    
    protected String shouldFail(final Class clazz, final Closure code) {
        Throwable th = null;
        try {
            code.call();
        }
        catch (GroovyRuntimeException gre) {
            th = ScriptBytecodeAdapter.unwrap(gre);
        }
        catch (Throwable e) {
            th = e;
        }
        if (th == null) {
            fail("Closure " + code + " should have failed with an exception of type " + clazz.getName());
        }
        else if (!clazz.isInstance(th)) {
            fail("Closure " + code + " should have failed with an exception of type " + clazz.getName() + ", instead got Exception " + th);
        }
        return th.getMessage();
    }
    
    protected String shouldFailWithCause(final Class clazz, final Closure code) {
        Throwable th = null;
        Throwable orig = null;
        int level = 0;
        try {
            code.call();
        }
        catch (GroovyRuntimeException gre) {
            orig = ScriptBytecodeAdapter.unwrap(gre);
            th = orig.getCause();
        }
        catch (Throwable e) {
            orig = e;
            th = orig.getCause();
        }
        while (th != null && !clazz.isInstance(th) && th != th.getCause() && level < 10) {
            th = th.getCause();
            ++level;
        }
        if (orig == null) {
            fail("Closure " + code + " should have failed with an exception caused by type " + clazz.getName());
        }
        else if (th == null || !clazz.isInstance(th)) {
            fail("Closure " + code + " should have failed with an exception caused by type " + clazz.getName() + ", instead found these Exceptions:\n" + this.buildExceptionList(orig));
        }
        return th.getMessage();
    }
    
    private String buildExceptionList(Throwable th) {
        final StringBuilder sb = new StringBuilder();
        for (int level = 0; th != null; th = th.getCause(), ++level) {
            if (level > 1) {
                for (int i = 0; i < level - 1; ++i) {
                    sb.append("   ");
                }
            }
            if (level > 0) {
                sb.append("-> ");
            }
            if (level > 10) {
                sb.append("...");
                break;
            }
            sb.append(th.getClass().getName()).append(": ").append(th.getMessage()).append("\n");
            if (th == th.getCause()) {
                break;
            }
        }
        return sb.toString();
    }
    
    protected String fixEOLs(final String value) {
        return value.replaceAll("(\\r\\n?)|\n", "\n");
    }
    
    public static boolean notYetImplemented(final TestCase caller) {
        if (GroovyTestCase.notYetImplementedFlag.get() != null) {
            return false;
        }
        GroovyTestCase.notYetImplementedFlag.set(Boolean.TRUE);
        final Method testMethod = findRunningJUnitTestMethod(caller.getClass());
        try {
            GroovyTestCase.log.info("Running " + testMethod.getName() + " as not yet implemented");
            testMethod.invoke(caller, (Object[])new Class[0]);
            fail(testMethod.getName() + " is marked as not yet implemented but passes unexpectedly");
        }
        catch (Exception e) {
            GroovyTestCase.log.info(testMethod.getName() + " fails which is expected as it is not yet implemented");
        }
        finally {
            GroovyTestCase.notYetImplementedFlag.set(null);
        }
        return true;
    }
    
    public boolean notYetImplemented() {
        return notYetImplemented(this);
    }
    
    private static Method findRunningJUnitTestMethod(final Class caller) {
        final Class[] args = new Class[0];
        final Throwable t = new Exception();
        for (int i = t.getStackTrace().length - 1; i >= 0; --i) {
            final StackTraceElement element = t.getStackTrace()[i];
            if (element.getClassName().equals(caller.getName())) {
                try {
                    final Method m = caller.getMethod(element.getMethodName(), (Class[])args);
                    if (isPublicTestMethod(m)) {
                        return m;
                    }
                }
                catch (Exception ex) {}
            }
        }
        throw new RuntimeException("No JUnit test case method found in call stack");
    }
    
    private static boolean isPublicTestMethod(final Method method) {
        final String name = method.getName();
        final Class[] parameters = method.getParameterTypes();
        final Class returnType = method.getReturnType();
        return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE) && Modifier.isPublic(method.getModifiers());
    }
    
    public static void assertEquals(final String message, final Object expected, final Object actual) {
        if (expected == null && actual == null) {
            return;
        }
        if (expected != null && DefaultTypeTransformation.compareEqual(expected, actual)) {
            return;
        }
        failNotEquals(message, expected, actual);
    }
    
    public static void assertEquals(final Object expected, final Object actual) {
        assertEquals(null, expected, actual);
    }
    
    public static void assertEquals(final String expected, final String actual) {
        assertEquals((String)null, expected, actual);
    }
    
    static {
        GroovyTestCase.log = Logger.getLogger(GroovyTestCase.class.getName());
        notYetImplementedFlag = new ThreadLocal();
    }
}
