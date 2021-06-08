// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.site.decoration.inheritance;

import java.net.URL;
import org.codehaus.plexus.util.PathTool;
import java.net.MalformedURLException;

public abstract class PathUtils
{
    private PathUtils() {
    }
    
    public static final PathDescriptor convertPath(final PathDescriptor oldPath, final PathDescriptor newPath) throws MalformedURLException {
        final String relative = getRelativePath(oldPath, newPath);
        if (relative == null) {
            return oldPath;
        }
        return new PathDescriptor(relative);
    }
    
    public static final String getRelativePath(final PathDescriptor oldPathDescriptor, final PathDescriptor newPathDescriptor) {
        if (oldPathDescriptor.isFile() && !newPathDescriptor.isFile()) {
            if (oldPathDescriptor.isRelative()) {
                return oldPathDescriptor.getPath();
            }
            return null;
        }
        else if (!oldPathDescriptor.isFile()) {
            final URL oldUrl = oldPathDescriptor.getPathUrl();
            final URL newUrl = newPathDescriptor.getPathUrl();
            if (oldUrl == null || newUrl == null) {
                return null;
            }
            if (newUrl.getProtocol().equalsIgnoreCase(oldUrl.getProtocol()) && newUrl.getHost().equalsIgnoreCase(oldUrl.getHost()) && newUrl.getPort() == oldUrl.getPort()) {
                final String oldPath = oldPathDescriptor.getPath();
                final String newPath = newPathDescriptor.getPath();
                return PathTool.getRelativeWebPath(newPath, oldPath);
            }
            return null;
        }
        else {
            final String oldPath2 = oldPathDescriptor.getPath();
            final String newPath2 = newPathDescriptor.getPath();
            if (oldPath2 == null || newPath2 == null) {
                return null;
            }
            return PathTool.getRelativeFilePath(oldPath2, newPath2);
        }
    }
}
