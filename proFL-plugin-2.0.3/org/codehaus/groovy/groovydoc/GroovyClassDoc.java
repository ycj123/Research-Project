// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.groovydoc;

public interface GroovyClassDoc extends GroovyType, GroovyProgramElementDoc
{
    GroovyConstructorDoc[] constructors();
    
    GroovyConstructorDoc[] constructors(final boolean p0);
    
    boolean definesSerializableFields();
    
    GroovyFieldDoc[] enumConstants();
    
    GroovyFieldDoc[] fields();
    
    GroovyFieldDoc[] properties();
    
    GroovyFieldDoc[] fields(final boolean p0);
    
    GroovyClassDoc findClass(final String p0);
    
    GroovyClassDoc[] importedClasses();
    
    GroovyPackageDoc[] importedPackages();
    
    GroovyClassDoc[] innerClasses();
    
    GroovyClassDoc[] innerClasses(final boolean p0);
    
    GroovyClassDoc[] interfaces();
    
    GroovyType[] interfaceTypes();
    
    boolean isAbstract();
    
    boolean isExternalizable();
    
    boolean isSerializable();
    
    GroovyMethodDoc[] methods();
    
    GroovyMethodDoc[] methods(final boolean p0);
    
    GroovyFieldDoc[] serializableFields();
    
    GroovyMethodDoc[] serializationMethods();
    
    boolean subclassOf(final GroovyClassDoc p0);
    
    GroovyClassDoc superclass();
    
    GroovyType superclassType();
    
    String getFullPathName();
    
    String getRelativeRootPath();
}
