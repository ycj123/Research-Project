// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import java.util.Iterator;
import java.io.PrintWriter;
import org.codehaus.groovy.control.messages.SyntaxErrorMessage;
import org.codehaus.groovy.control.messages.WarningMessage;
import java.util.List;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.codehaus.groovy.control.messages.LocatedMessage;
import org.codehaus.groovy.syntax.CSTNode;
import org.codehaus.groovy.syntax.SyntaxException;
import org.codehaus.groovy.control.messages.Message;
import java.util.Collection;
import java.util.LinkedList;

public class ErrorCollector
{
    protected LinkedList warnings;
    protected LinkedList errors;
    protected CompilerConfiguration configuration;
    
    public ErrorCollector(final CompilerConfiguration configuration) {
        this.warnings = null;
        this.errors = null;
        this.configuration = configuration;
    }
    
    public void addCollectorContents(final ErrorCollector er) {
        if (er.errors != null) {
            if (this.errors == null) {
                this.errors = er.errors;
            }
            else {
                this.errors.addAll(er.errors);
            }
        }
        if (er.warnings != null) {
            if (this.warnings == null) {
                this.warnings = er.warnings;
            }
            else {
                this.warnings.addAll(er.warnings);
            }
        }
    }
    
    public void addErrorAndContinue(final Message message) {
        if (this.errors == null) {
            this.errors = new LinkedList();
        }
        this.errors.add(message);
    }
    
    public void addError(final Message message) throws CompilationFailedException {
        this.addErrorAndContinue(message);
        if (this.errors != null && this.errors.size() >= this.configuration.getTolerance()) {
            this.failIfErrors();
        }
    }
    
    public void addError(final Message message, final boolean fatal) throws CompilationFailedException {
        if (fatal) {
            this.addFatalError(message);
        }
        else {
            this.addError(message);
        }
    }
    
    public void addError(final SyntaxException error, final SourceUnit source) throws CompilationFailedException {
        this.addError(Message.create(error, source), error.isFatal());
    }
    
    public void addError(final String text, final CSTNode context, final SourceUnit source) throws CompilationFailedException {
        this.addError(new LocatedMessage(text, context, source));
    }
    
    public void addFatalError(final Message message) throws CompilationFailedException {
        this.addError(message);
        this.failIfErrors();
    }
    
    public void addException(final Exception cause, final SourceUnit source) throws CompilationFailedException {
        this.addError(new ExceptionMessage(cause, this.configuration.getDebug(), source));
        this.failIfErrors();
    }
    
    public boolean hasErrors() {
        return this.errors != null;
    }
    
    public boolean hasWarnings() {
        return this.warnings != null;
    }
    
    public List getWarnings() {
        return this.warnings;
    }
    
    public List getErrors() {
        return this.errors;
    }
    
    public int getWarningCount() {
        return (this.warnings == null) ? 0 : this.warnings.size();
    }
    
    public int getErrorCount() {
        return (this.errors == null) ? 0 : this.errors.size();
    }
    
    public WarningMessage getWarning(final int index) {
        if (index < this.getWarningCount()) {
            return this.warnings.get(index);
        }
        return null;
    }
    
    public Message getError(final int index) {
        if (index < this.getErrorCount()) {
            return this.errors.get(index);
        }
        return null;
    }
    
    public Message getLastError() {
        return this.errors.getLast();
    }
    
    public SyntaxException getSyntaxError(final int index) {
        SyntaxException exception = null;
        final Message message = this.getError(index);
        if (message != null && message instanceof SyntaxErrorMessage) {
            exception = ((SyntaxErrorMessage)message).getCause();
        }
        return exception;
    }
    
    public Exception getException(final int index) {
        Exception exception = null;
        final Message message = this.getError(index);
        if (message != null) {
            if (message instanceof ExceptionMessage) {
                exception = ((ExceptionMessage)message).getCause();
            }
            else if (message instanceof SyntaxErrorMessage) {
                exception = ((SyntaxErrorMessage)message).getCause();
            }
        }
        return exception;
    }
    
    public void addWarning(final WarningMessage message) {
        if (message.isRelevant(this.configuration.getWarningLevel())) {
            if (this.warnings == null) {
                this.warnings = new LinkedList();
            }
            this.warnings.add(message);
        }
    }
    
    public void addWarning(final int importance, final String text, final CSTNode context, final SourceUnit source) {
        if (WarningMessage.isRelevant(importance, this.configuration.getWarningLevel())) {
            this.addWarning(new WarningMessage(importance, text, context, source));
        }
    }
    
    public void addWarning(final int importance, final String text, final Object data, final CSTNode context, final SourceUnit source) {
        if (WarningMessage.isRelevant(importance, this.configuration.getWarningLevel())) {
            this.addWarning(new WarningMessage(importance, text, data, context, source));
        }
    }
    
    protected void failIfErrors() throws CompilationFailedException {
        if (this.hasErrors()) {
            throw new MultipleCompilationErrorsException(this);
        }
    }
    
    private void write(final PrintWriter writer, final Janitor janitor, final List messages, final String txt) {
        if (messages == null || messages.size() == 0) {
            return;
        }
        for (final Message message : messages) {
            message.write(writer, janitor);
            if (this.configuration.getDebug() && message instanceof SyntaxErrorMessage) {
                final SyntaxErrorMessage sem = (SyntaxErrorMessage)message;
                sem.getCause().printStackTrace(writer);
            }
            writer.println();
        }
        writer.print(messages.size());
        writer.print(" " + txt);
        if (messages.size() > 1) {
            writer.print("s");
        }
        writer.println();
    }
    
    public void write(final PrintWriter writer, final Janitor janitor) {
        this.write(writer, janitor, this.warnings, "warning");
        this.write(writer, janitor, this.errors, "error");
    }
}
