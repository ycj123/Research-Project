// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control.io;

import org.codehaus.groovy.control.HasCleanup;
import java.io.IOException;
import org.codehaus.groovy.control.Janitor;
import java.io.BufferedReader;
import org.codehaus.groovy.control.CompilerConfiguration;

public abstract class AbstractReaderSource implements ReaderSource
{
    protected CompilerConfiguration configuration;
    private BufferedReader lineSource;
    private String line;
    private int number;
    
    public AbstractReaderSource(final CompilerConfiguration configuration) {
        this.lineSource = null;
        this.line = null;
        this.number = 0;
        if (configuration == null) {
            throw new IllegalArgumentException("Compiler configuration must not be null!");
        }
        this.configuration = configuration;
    }
    
    public boolean canReopenSource() {
        return true;
    }
    
    public String getLine(final int lineNumber, final Janitor janitor) {
        if (this.lineSource != null && this.number > lineNumber) {
            this.cleanup();
        }
        if (this.lineSource == null) {
            try {
                this.lineSource = new BufferedReader(this.getReader());
            }
            catch (Exception ex) {}
            this.number = 0;
        }
        if (this.lineSource != null) {
            while (this.number < lineNumber) {
                try {
                    this.line = this.lineSource.readLine();
                    ++this.number;
                }
                catch (IOException e) {
                    this.cleanup();
                }
            }
            if (janitor == null) {
                final String result = this.line;
                this.cleanup();
                return result;
            }
            janitor.register(this);
        }
        return this.line;
    }
    
    public void cleanup() {
        if (this.lineSource != null) {
            try {
                this.lineSource.close();
            }
            catch (Exception ex) {}
        }
        this.lineSource = null;
        this.line = null;
        this.number = 0;
    }
}
