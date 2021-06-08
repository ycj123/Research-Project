// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin.descriptor;

public class DuplicateMojoDescriptorException extends InvalidPluginDescriptorException
{
    public DuplicateMojoDescriptorException(final String goalPrefix, final String goal, final String existingImplementation, final String newImplementation) {
        super("Goal: " + goal + " already exists in the plugin descriptor for prefix: " + goalPrefix + "\nExisting implementation is: " + existingImplementation + "\nConflicting implementation is: " + newImplementation);
    }
}
