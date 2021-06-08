// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

public class TracingInterceptor implements Interceptor
{
    protected Writer writer;
    private int indent;
    
    public TracingInterceptor() {
        this.writer = new PrintWriter(System.out);
        this.indent = 0;
    }
    
    public Writer getWriter() {
        return this.writer;
    }
    
    public void setWriter(final Writer writer) {
        this.writer = writer;
    }
    
    public Object beforeInvoke(final Object object, final String methodName, final Object[] arguments) {
        this.write(object, methodName, arguments, "before");
        ++this.indent;
        return null;
    }
    
    public Object afterInvoke(final Object object, final String methodName, final Object[] arguments, final Object result) {
        --this.indent;
        this.write(object, methodName, arguments, "after ");
        return result;
    }
    
    public boolean doInvoke() {
        return true;
    }
    
    private String indent() {
        final StringBuffer result = new StringBuffer();
        for (int i = 0; i < this.indent; ++i) {
            result.append("  ");
        }
        return result.toString();
    }
    
    protected void write(final Object object, final String methodName, final Object[] arguments, final String origin) {
        try {
            this.writer.write(this.indent());
            this.writer.write(origin);
            this.writer.write(" ");
            final Class theClass = (object instanceof Class) ? ((Class)object) : object.getClass();
            this.writeInfo(theClass, methodName, arguments);
            this.writer.write("\n");
            this.writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void writeInfo(final Class aClass, final String methodName, final Object[] arguments) throws IOException {
        this.writer.write(aClass.getName());
        this.writer.write(".");
        this.writer.write(methodName);
        this.writer.write("(");
        for (int i = 0; i < arguments.length; ++i) {
            if (i > 0) {
                this.writer.write(", ");
            }
            final Object argument = arguments[i];
            this.writer.write(argument.getClass().getName());
        }
        this.writer.write(")");
    }
}
