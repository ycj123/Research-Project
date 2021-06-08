// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine.command;

import java.util.ResourceBundle;
import org.netbeans.lib.cvsclient.commandLine.GetOpt;
import org.netbeans.lib.cvsclient.command.Builder;
import org.netbeans.lib.cvsclient.command.commit.CommitCommand;
import org.netbeans.lib.cvsclient.command.Command;
import org.netbeans.lib.cvsclient.command.GlobalOptions;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class commit extends AbstractCommandProvider
{
    public String[] getSynonyms() {
        return new String[] { "ci", "com", "put" };
    }
    
    private static String getEditorProcess(String property) {
        if (property == null) {
            if (System.getProperty("os.name").startsWith("Windows")) {
                property = "notepad.exe";
            }
            else {
                property = null;
            }
            property = System.getProperty("cvs.editor", property);
        }
        return property;
    }
    
    private static File createTempFile(final File[] array, final File directory) throws IOException {
        BufferedReader bufferedReader = null;
        Writer writer = null;
        try {
            final File tempFile = File.createTempFile("cvsTemplate", "txt", directory);
            writer = new BufferedWriter(new FileWriter(tempFile));
            if (array != null && array.length > 0) {
                final File file = new File(array[0].getParentFile(), "CVS" + File.separator + "Template");
                if (file.exists()) {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        writer.write(line);
                        ((BufferedWriter)writer).newLine();
                    }
                }
            }
            writer.write("CVS: ----------------------------------------------------------------------");
            ((BufferedWriter)writer).newLine();
            writer.write("CVS: Enter Log.  Lines beginning with `CVS:' are removed automatically");
            ((BufferedWriter)writer).newLine();
            writer.write("CVS: ");
            ((BufferedWriter)writer).newLine();
            writer.write("CVS: Committing in .");
            ((BufferedWriter)writer).newLine();
            writer.write("CVS: ");
            ((BufferedWriter)writer).newLine();
            writer.write("CVS: Modified Files:");
            ((BufferedWriter)writer).newLine();
            if (array != null) {
                for (int i = 0; i < array.length; ++i) {
                    writer.write("CVS:  " + array[i].getPath());
                }
            }
            writer.write("CVS: ----------------------------------------------------------------------");
            ((BufferedWriter)writer).newLine();
            return tempFile;
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (writer != null) {
                ((BufferedWriter)writer).close();
            }
        }
    }
    
    private static String createMessage(final File[] array, final GlobalOptions globalOptions) {
        File tempFile = null;
        BufferedReader bufferedReader = null;
        try {
            tempFile = createTempFile(array, globalOptions.getTempDir());
            final String editorProcess = getEditorProcess(globalOptions.getEditor());
            if (editorProcess == null) {
                return null;
            }
            final Process exec = Runtime.getRuntime().exec(new String[] { editorProcess, tempFile.getPath() });
            int wait = -1;
            try {
                wait = exec.waitFor();
            }
            catch (InterruptedException ex) {}
            if (wait != 0) {
                return null;
            }
            bufferedReader = new BufferedReader(new FileReader(tempFile));
            final StringBuffer sb = new StringBuffer((int)tempFile.length());
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("CVS:")) {
                    sb.append(line);
                    sb.append('\n');
                }
            }
            return sb.toString();
        }
        catch (IOException obj) {
            System.err.println("Error: " + obj);
            obj.printStackTrace();
            return null;
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (tempFile != null) {
                    tempFile.delete();
                }
            }
            catch (Exception obj2) {
                System.err.println("Fatal error: " + obj2);
                obj2.printStackTrace();
            }
        }
    }
    
    public Command createCommand(final String[] array, final int n, final GlobalOptions globalOptions, String property) {
        final CommitCommand commitCommand = new CommitCommand();
        commitCommand.setBuilder(null);
        final GetOpt getOpt = new GetOpt(array, commitCommand.getOptString());
        getOpt.optIndexSet(n);
        boolean b = false;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if (!commitCommand.setCVSCommand((char)getopt, getOpt.optArgGet())) {
                b = true;
            }
        }
        if (b) {
            throw new IllegalArgumentException(this.getUsage());
        }
        final int optIndexGet = getOpt.optIndexGet();
        File[] files = null;
        if (optIndexGet < array.length) {
            files = new File[array.length - optIndexGet];
            if (property == null) {
                property = System.getProperty("user.dir");
            }
            final File parent = new File(property);
            for (int i = optIndexGet; i < array.length; ++i) {
                files[i - optIndexGet] = new File(parent, array[i]);
            }
            commitCommand.setFiles(files);
        }
        if (commitCommand.getMessage() == null && commitCommand.getLogMessageFromFile() == null) {
            final String message = createMessage(files, globalOptions);
            if (message == null) {
                throw new IllegalArgumentException(ResourceBundle.getBundle(commit.class.getPackage().getName() + ".Bundle").getString("commit.messageNotSpecified"));
            }
            commitCommand.setMessage(message);
        }
        return commitCommand;
    }
}
