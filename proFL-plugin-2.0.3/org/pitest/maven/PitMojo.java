// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

@org.apache.maven.plugins.annotations.Mojo(name = "mutationCoverage", defaultPhase = LifecyclePhase.VERIFY, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
public class PitMojo extends AbstractPitMojo
{
}
