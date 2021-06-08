// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import org.apache.velocity.Template;

public class ResourceFactory
{
    public static Resource getResource(final String resourceName, final int resourceType) {
        Resource resource = null;
        switch (resourceType) {
            case 1: {
                resource = new Template();
                break;
            }
            case 2: {
                resource = new ContentResource();
                break;
            }
        }
        return resource;
    }
}
