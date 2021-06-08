// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import java.net.URI;
import java.util.List;
import java.util.Map;

public interface GrapeEngine
{
    Object grab(final String p0);
    
    Object grab(final Map p0);
    
    Object grab(final Map p0, final Map... p1);
    
    Map<String, Map<String, List<String>>> enumerateGrapes();
    
    URI[] resolve(final Map p0, final Map... p1);
    
    URI[] resolve(final Map p0, final List p1, final Map... p2);
    
    Map[] listDependencies(final ClassLoader p0);
    
    void addResolver(final Map<String, Object> p0);
}
