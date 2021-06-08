// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class ContentResource extends Resource
{
    public boolean process() throws ResourceNotFoundException {
        BufferedReader reader = null;
        try {
            final StringWriter sw = new StringWriter();
            reader = new BufferedReader(new InputStreamReader(this.resourceLoader.getResourceStream(this.name), this.encoding));
            final char[] buf = new char[1024];
            int len = 0;
            while ((len = reader.read(buf, 0, 1024)) != -1) {
                sw.write(buf, 0, len);
            }
            this.setData(sw.toString());
            return true;
        }
        catch (ResourceNotFoundException e) {
            throw e;
        }
        catch (Exception e2) {
            this.rsvc.getLog().error("Cannot process content resource", e2);
            return false;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Exception ex) {}
            }
        }
    }
}
