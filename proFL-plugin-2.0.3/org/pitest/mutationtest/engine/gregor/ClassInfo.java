// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.engine.gregor;

import org.pitest.functional.F;
import org.pitest.functional.FArray;

public class ClassInfo
{
    private final int version;
    private final int access;
    private final String name;
    private final String signature;
    private final String superName;
    private final String[] interfaces;
    
    public ClassInfo(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
        this.version = version;
        this.access = access;
        this.name = name;
        this.signature = signature;
        this.superName = superName;
        this.interfaces = interfaces;
    }
    
    public boolean isEnum() {
        return this.superName.equals("java/lang/Enum");
    }
    
    public boolean isGroovyClass() {
        return FArray.contains(this.interfaces, isAGroovyClass());
    }
    
    private static F<String, Boolean> isAGroovyClass() {
        return new F<String, Boolean>() {
            @Override
            public Boolean apply(final String a) {
                return a.startsWith("groovy/lang/") || a.startsWith("org/codehaus/groovy/runtime");
            }
        };
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public int getAccess() {
        return this.access;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSignature() {
        return this.signature;
    }
    
    public String getSuperName() {
        return this.superName;
    }
    
    public String[] getInterfaces() {
        return this.interfaces;
    }
}
