// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.connection;

import org.netbeans.lib.cvsclient.file.FileUtils;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Collection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public final class PasswordsFile
{
    public static String findPassword(final String str) {
        final File file = new File(System.getProperty("cvs.passfile", System.getProperty("user.home") + "/.cvspass"));
        BufferedReader bufferedReader = null;
        String substring = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String normalize = normalize(line);
                if (normalize.startsWith(str + " ")) {
                    substring = normalize.substring(str.length() + 1);
                    break;
                }
            }
        }
        catch (IOException ex) {
            return null;
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
        }
        return substring;
    }
    
    public static Collection listRoots(final String prefix) {
        final ArrayList<String> list = new ArrayList<String>();
        final File file = new File(System.getProperty("cvs.passfile", System.getProperty("user.home") + "/.cvspass"));
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] split = normalize(line).split(" ");
                if (split[0].startsWith(prefix)) {
                    list.add(split[0]);
                }
            }
        }
        catch (IOException ex) {
            return Collections.EMPTY_SET;
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
        }
        return list;
    }
    
    public static void storePassword(final String s, final String str) throws IOException {
        final File file = new File(System.getProperty("cvs.passfile", System.getProperty("user.home") + File.separatorChar + ".cvspass"));
        Writer writer = null;
        BufferedReader bufferedReader = null;
        try {
            final String property = System.getProperty("line.separator");
            if (file.createNewFile()) {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(s + " " + str + property);
                ((BufferedWriter)writer).close();
            }
            else {
                final File tempFile = File.createTempFile("cvs", "tmp");
                bufferedReader = new BufferedReader(new FileReader(file));
                writer = new BufferedWriter(new FileWriter(tempFile));
                int n = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (normalize(line).startsWith(s + " ")) {
                        if (n != 0) {
                            continue;
                        }
                        writer.write(s + " " + str + property);
                        n = 1;
                    }
                    else {
                        writer.write(line + property);
                    }
                }
                if (n == 0) {
                    writer.write(s + " " + str + property);
                }
                bufferedReader.close();
                ((BufferedWriter)writer).flush();
                ((BufferedWriter)writer).close();
                FileUtils.copyFile(tempFile, file);
                tempFile.delete();
            }
        }
        finally {
            try {
                if (writer != null) {
                    ((BufferedWriter)writer).close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private static String normalize(String substring) {
        if (substring.startsWith("/1 ")) {
            substring = substring.substring("/1 ".length());
        }
        return substring;
    }
}
