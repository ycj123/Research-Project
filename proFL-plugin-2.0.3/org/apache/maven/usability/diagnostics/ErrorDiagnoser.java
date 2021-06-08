// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.usability.diagnostics;

public interface ErrorDiagnoser
{
    public static final String ROLE = ((ErrorDiagnoser$1.class$org$apache$maven$usability$diagnostics$ErrorDiagnoser == null) ? (ErrorDiagnoser$1.class$org$apache$maven$usability$diagnostics$ErrorDiagnoser = ErrorDiagnoser$1.class$("org.apache.maven.usability.diagnostics.ErrorDiagnoser")) : ErrorDiagnoser$1.class$org$apache$maven$usability$diagnostics$ErrorDiagnoser).getName();
    
    boolean canDiagnose(final Throwable p0);
    
    String diagnose(final Throwable p0);
}
