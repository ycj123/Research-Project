// 
// Decompiled by Procyon v0.5.36
// 

package groovyjarjarcommonscli;

public interface CommandLineParser
{
    CommandLine parse(final Options p0, final String[] p1) throws ParseException;
    
    CommandLine parse(final Options p0, final String[] p1, final boolean p2) throws ParseException;
}
