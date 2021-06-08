// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.app.event.implement;

import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.util.RuntimeServicesAware;
import org.apache.velocity.app.event.MethodExceptionEventHandler;

public class PrintExceptions implements MethodExceptionEventHandler, RuntimeServicesAware
{
    private static String SHOW_MESSAGE;
    private static String SHOW_STACK_TRACE;
    private RuntimeServices rs;
    
    public PrintExceptions() {
        this.rs = null;
    }
    
    public Object methodException(final Class claz, final String method, final Exception e) throws Exception {
        final boolean showMessage = this.rs.getBoolean(PrintExceptions.SHOW_MESSAGE, false);
        final boolean showStackTrace = this.rs.getBoolean(PrintExceptions.SHOW_STACK_TRACE, false);
        StringBuffer st;
        if (showMessage && showStackTrace) {
            st = new StringBuffer(200);
            st.append(e.getClass().getName()).append("\n");
            st.append(e.getMessage()).append("\n");
            st.append(getStackTrace(e));
        }
        else if (showMessage) {
            st = new StringBuffer(50);
            st.append(e.getClass().getName()).append("\n");
            st.append(e.getMessage()).append("\n");
        }
        else if (showStackTrace) {
            st = new StringBuffer(200);
            st.append(e.getClass().getName()).append("\n");
            st.append(getStackTrace(e));
        }
        else {
            st = new StringBuffer(15);
            st.append(e.getClass().getName()).append("\n");
        }
        return st.toString();
    }
    
    private static String getStackTrace(final Throwable throwable) {
        PrintWriter printWriter = null;
        try {
            final StringWriter stackTraceWriter = new StringWriter();
            printWriter = new PrintWriter(stackTraceWriter);
            throwable.printStackTrace(printWriter);
            printWriter.flush();
            return stackTraceWriter.toString();
        }
        finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
    
    public void setRuntimeServices(final RuntimeServices rs) {
        this.rs = rs;
    }
    
    static {
        PrintExceptions.SHOW_MESSAGE = "eventhandler.methodexception.message";
        PrintExceptions.SHOW_STACK_TRACE = "eventhandler.methodexception.stacktrace";
    }
}
