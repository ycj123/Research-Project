// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarcommonscli;

public class UnrecognizedOptionException extends ParseException
{
    private String option;
    
    public UnrecognizedOptionException(final String message) {
        super(message);
    }
    
    public UnrecognizedOptionException(final String message, final String option) {
        this(message);
        this.option = option;
    }
    
    public String getOption() {
        return this.option;
    }
}
