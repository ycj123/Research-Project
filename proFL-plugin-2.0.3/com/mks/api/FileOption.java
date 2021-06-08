// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api;

import java.io.File;

public class FileOption extends Option
{
    private static final String REMOTE_PREFIX = "remote://";
    
    public FileOption(final String name, final File value) {
        super(name, "remote://" + value.getPath());
    }
    
    public FileOption(final String name, final String value) {
        super(name, "remote://" + value);
    }
}
