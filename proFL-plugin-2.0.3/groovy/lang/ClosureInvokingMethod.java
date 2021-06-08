// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

public interface ClosureInvokingMethod
{
    Closure getClosure();
    
    boolean isStatic();
    
    String getName();
}
