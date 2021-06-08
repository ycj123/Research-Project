// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarcommonscli;

public class MissingArgumentException extends ParseException
{
    private Option option;
    
    public MissingArgumentException(final String message) {
        super(message);
    }
    
    public MissingArgumentException(final Option option) {
        this("Missing argument for option: " + option.getKey());
        this.option = option;
    }
    
    public Option getOption() {
        return this.option;
    }
}
