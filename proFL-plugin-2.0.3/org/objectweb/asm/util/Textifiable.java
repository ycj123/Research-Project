// 
// Decompiled by Procyon v0.5.36
// 

package org.objectweb.asm.util;

import org.objectweb.asm.Label;
import java.util.Map;

public interface Textifiable
{
    void textify(final StringBuffer p0, final Map<Label, String> p1);
}
