// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.coverage;

import org.pitest.functional.F;
import org.pitest.classinfo.ClassName;
import org.pitest.functional.Option;
import java.io.Serializable;

public final class TestInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    private final String name;
    private final String definingClass;
    private final int time;
    private final int blocks;
    private final Option<ClassName> testee;
    
    public TestInfo(final String definingClass, final String name, final int time, final Option<ClassName> testee, final int blocksCovered) {
        this.definingClass = internIfNotNull(definingClass);
        this.name = name;
        this.time = time;
        this.testee = testee;
        this.blocks = blocksCovered;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public int getNumberOfBlocksCovered() {
        return this.blocks;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    public static F<TestInfo, String> toName() {
        return new F<TestInfo, String>() {
            @Override
            public String apply(final TestInfo a) {
                return a.getName();
            }
        };
    }
    
    public static F<TestInfo, ClassName> toDefiningClassName() {
        return new F<TestInfo, ClassName>() {
            @Override
            public ClassName apply(final TestInfo a) {
                return ClassName.fromString(a.definingClass);
            }
        };
    }
    
    public boolean directlyHits(final ClassName targetClass) {
        return this.testee.hasSome() && this.testee.value().equals(targetClass);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.definingClass == null) ? 0 : this.definingClass.hashCode());
        result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final TestInfo other = (TestInfo)obj;
        if (this.definingClass == null) {
            if (other.definingClass != null) {
                return false;
            }
        }
        else if (!this.definingClass.equals(other.definingClass)) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        }
        else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    private static String internIfNotNull(final String string) {
        if (string == null) {
            return null;
        }
        return string.intern();
    }
}
