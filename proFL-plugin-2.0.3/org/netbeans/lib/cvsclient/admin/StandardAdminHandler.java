// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.admin;

import java.util.TreeSet;
import java.util.Set;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import org.netbeans.lib.cvsclient.file.FileUtils;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import org.netbeans.lib.cvsclient.command.GlobalOptions;

public class StandardAdminHandler implements AdminHandler
{
    private static final Object ksEntries;
    private static Runnable t9yBeforeRename;
    
    public void updateAdminData(final String parent, String str, final Entry entry, final GlobalOptions globalOptions) throws IOException {
        final File parent2 = new File(parent, "CVS");
        parent2.mkdirs();
        final File file = new File(parent2, "Root");
        if (!file.exists()) {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            try {
                bufferedWriter.write(globalOptions.getCVSRoot());
            }
            finally {
                bufferedWriter.close();
            }
        }
        final File file2 = new File(parent2, "Repository");
        if (!file2.exists()) {
            final BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2));
            try {
                if (entry != null && !entry.isDirectory()) {
                    str = str.substring(0, str.length() - entry.getName().length());
                }
                if (str.endsWith("/")) {
                    str = str.substring(0, str.length() - 1);
                }
                if (str.length() == 0) {
                    str = ".";
                }
                bufferedWriter2.write(str);
            }
            finally {
                bufferedWriter2.close();
            }
        }
        final File file3 = new File(parent2, "Entries");
        if (file3.createNewFile()) {
            this.addDirectoryToParentEntriesFile(parent2);
            final BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter(file3));
            try {
                bufferedWriter3.write("D");
            }
            finally {
                bufferedWriter3.close();
            }
        }
        if (entry != null) {
            this.updateEntriesFile(file3, entry);
        }
    }
    
    public boolean exists(final File file) {
        return file.exists();
    }
    
    private void addDirectoryToParentEntriesFile(final File file) throws IOException {
        synchronized (StandardAdminHandler.ksEntries) {
            final File seekEntries = seekEntries(file.getParentFile().getParentFile());
            if (seekEntries != null) {
                final File file2 = new File(seekEntries.getParentFile(), "Entries.Backup");
                file2.createNewFile();
                BufferedReader bufferedReader = null;
                BufferedWriter bufferedWriter = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(seekEntries));
                    bufferedWriter = new BufferedWriter(new FileWriter(file2));
                    boolean b = false;
                    final Entry entry = new Entry();
                    entry.setName(file.getParentFile().getName());
                    entry.setDirectory(true);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (line.trim().equals("D")) {
                            continue;
                        }
                        final Entry entry2 = new Entry(line);
                        if (entry2.getName() != null && entry2.getName().equals(entry.getName())) {
                            bufferedWriter.write(entry.toString());
                            b = true;
                        }
                        else {
                            bufferedWriter.write(line);
                        }
                        bufferedWriter.newLine();
                    }
                    if (!b) {
                        bufferedWriter.write(entry.toString());
                        bufferedWriter.newLine();
                    }
                }
                finally {
                    try {
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    }
                    finally {
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                    }
                }
                if (StandardAdminHandler.t9yBeforeRename != null) {
                    StandardAdminHandler.t9yBeforeRename.run();
                }
                FileUtils.renameFile(file2, seekEntries);
            }
        }
    }
    
    private void updateEntriesFile(final File file, final Entry entry) throws IOException {
        synchronized (StandardAdminHandler.ksEntries) {
            final File file2 = new File(file.getParentFile(), "Entries.Backup");
            file2.createNewFile();
            BufferedReader bufferedReader = null;
            BufferedWriter bufferedWriter = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                bufferedWriter = new BufferedWriter(new FileWriter(file2));
                boolean b = false;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final Entry entry2 = new Entry(line);
                    if (entry2.getName() != null && entry2.getName().equals(entry.getName())) {
                        bufferedWriter.write(entry.toString());
                        b = true;
                    }
                    else {
                        bufferedWriter.write(line);
                    }
                    bufferedWriter.newLine();
                }
                if (!b) {
                    bufferedWriter.write(entry.toString());
                    bufferedWriter.newLine();
                }
            }
            finally {
                try {
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                }
                finally {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                }
            }
            if (StandardAdminHandler.t9yBeforeRename != null) {
                StandardAdminHandler.t9yBeforeRename.run();
            }
            FileUtils.renameFile(file2, file);
        }
    }
    
    public Entry getEntry(final File file) throws IOException {
        final File seekEntries = seekEntries(file.getParentFile());
        if (seekEntries == null) {
            return null;
        }
        this.processEntriesDotLog(new File(file.getParent(), "CVS"));
        BufferedReader bufferedReader = null;
        Entry entry = null;
        boolean equals = false;
        try {
            String line;
            for (bufferedReader = new BufferedReader(new FileReader(seekEntries)); !equals && (line = bufferedReader.readLine()) != null; equals = entry.getName().equals(file.getName())) {
                entry = new Entry(line);
                if (entry.getName() != null) {}
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        if (!equals) {
            return null;
        }
        return entry;
    }
    
    public Entry[] getEntriesAsArray(final File parent) throws IOException {
        final LinkedList list = new LinkedList<Entry>();
        final File seekEntries = seekEntries(parent);
        if (seekEntries == null) {
            return new Entry[0];
        }
        this.processEntriesDotLog(new File(parent, "CVS"));
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(seekEntries));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final Entry entry = new Entry(line);
                if (entry.getName() != null) {
                    list.add(entry);
                }
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return (Entry[])list.toArray(new Entry[list.size()]);
    }
    
    public Iterator getEntries(final File parent) throws IOException {
        final LinkedList<Entry> list = new LinkedList<Entry>();
        final File seekEntries = seekEntries(parent);
        if (seekEntries == null) {
            return list.iterator();
        }
        this.processEntriesDotLog(new File(parent, "CVS"));
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(seekEntries));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final Entry entry = new Entry(line);
                if (entry.getName() != null) {
                    list.add(entry);
                }
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return list.iterator();
    }
    
    public void setEntry(final File file, final Entry entry) throws IOException {
        final String parent = file.getParent();
        File seekEntries = seekEntries(parent);
        if (seekEntries == null) {
            seekEntries = new File(parent, "CVS/Entries");
        }
        this.processEntriesDotLog(new File(parent, "CVS"));
        this.updateEntriesFile(seekEntries, entry);
    }
    
    public void removeEntry(final File file) throws IOException {
        synchronized (StandardAdminHandler.ksEntries) {
            final File seekEntries = seekEntries(file.getParent());
            if (seekEntries == null) {
                return;
            }
            this.processEntriesDotLog(new File(file.getParent(), "CVS"));
            final File file2 = new File(file.getParentFile(), "Entries.Backup");
            file2.createNewFile();
            BufferedReader bufferedReader = null;
            Writer writer = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(seekEntries));
                writer = new BufferedWriter(new FileWriter(file2));
                boolean b = false;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final Entry entry = new Entry(line);
                    if (entry.getName() != null && !entry.getName().equals(file.getName())) {
                        writer.write(entry.toString());
                        ((BufferedWriter)writer).newLine();
                        b = (b || entry.isDirectory());
                    }
                }
                if (!b) {
                    writer.write("D");
                    ((BufferedWriter)writer).newLine();
                }
            }
            finally {
                try {
                    if (writer != null) {
                        ((BufferedWriter)writer).close();
                    }
                }
                finally {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                }
            }
            if (StandardAdminHandler.t9yBeforeRename != null) {
                StandardAdminHandler.t9yBeforeRename.run();
            }
            FileUtils.renameFile(file2, seekEntries);
        }
    }
    
    public String getRepositoryForDirectory(final String s, final String str) throws IOException {
        String string = "";
        File parentFile = new File(s);
        while (parentFile != null && parentFile.getName().length() != 0 && parentFile.exists()) {
            final File file = new File(parentFile, "CVS/Repository");
            if (file.exists()) {
                BufferedReader bufferedReader = null;
                String line = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    line = bufferedReader.readLine();
                }
                finally {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                }
                if (line == null) {
                    line = "";
                }
                String str2 = line + string;
                if (str2.startsWith("/")) {
                    return str2;
                }
                if (str2.startsWith("./")) {
                    str2 = str2.substring(2);
                }
                return str + '/' + str2;
            }
            else {
                string = '/' + parentFile.getName() + string;
                parentFile = parentFile.getParentFile();
            }
        }
        throw new FileNotFoundException("Repository file not found for directory " + s);
    }
    
    private void processEntriesDotLog(final File parent) throws IOException {
        synchronized (StandardAdminHandler.ksEntries) {
            final File file = new File(parent, "Entries.Log");
            if (!file.exists()) {
                return;
            }
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            final LinkedList list = new LinkedList<Entry>();
            final HashSet set = new HashSet<String>();
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.startsWith("A ")) {
                        list.add(new Entry(line.substring(2)));
                    }
                    else {
                        if (!line.startsWith("R ")) {
                            continue;
                        }
                        set.add(new Entry(line.substring(2)).getName());
                    }
                }
            }
            finally {
                bufferedReader.close();
            }
            if (list.size() > 0 || set.size() > 0) {
                final File file2 = new File(parent, "Entries.Backup");
                final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));
                final File file3 = new File(parent, "Entries");
                final BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file3));
                try {
                    int n = 0;
                    String line2;
                    while ((line2 = bufferedReader2.readLine()) != null) {
                        if (line2.trim().equals("D")) {
                            continue;
                        }
                        final Entry entry = new Entry(line2);
                        if (entry.isDirectory()) {
                            ++n;
                        }
                        if (set.contains(entry.getName())) {
                            continue;
                        }
                        bufferedWriter.write(entry.toString());
                        bufferedWriter.newLine();
                        if (!entry.isDirectory()) {
                            continue;
                        }
                        --n;
                    }
                    for (final Entry entry2 : list) {
                        if (entry2.isDirectory()) {
                            ++n;
                        }
                        bufferedWriter.write(entry2.toString());
                        bufferedWriter.newLine();
                    }
                    if (n == 0) {
                        bufferedWriter.write("D");
                        bufferedWriter.newLine();
                    }
                }
                finally {
                    try {
                        bufferedReader2.close();
                    }
                    finally {
                        bufferedWriter.close();
                    }
                }
                if (StandardAdminHandler.t9yBeforeRename != null) {
                    StandardAdminHandler.t9yBeforeRename.run();
                }
                FileUtils.renameFile(file2, file3);
            }
            file.delete();
        }
    }
    
    public Set getAllFiles(final File parent) throws IOException {
        final TreeSet<File> set = new TreeSet<File>();
        BufferedReader bufferedReader = null;
        try {
            final File seekEntries = seekEntries(parent);
            if (seekEntries != null) {
                return set;
            }
            bufferedReader = new BufferedReader(new FileReader(seekEntries));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final Entry entry = new Entry(line);
                if (entry.getName() != null) {
                    final File e = new File(parent, entry.getName());
                    if (!e.isFile()) {
                        continue;
                    }
                    set.add(e);
                }
            }
        }
        finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return set;
    }
    
    public String getStickyTagForDirectory(final File parent) {
        BufferedReader bufferedReader = null;
        final File file = new File(parent, "CVS/Tag");
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader.readLine();
        }
        catch (IOException ex) {}
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
        }
        return null;
    }
    
    private static File seekEntries(final File file) {
        synchronized (StandardAdminHandler.ksEntries) {
            final File file2 = new File(file, "CVS/Entries");
            if (file2.exists()) {
                return file2;
            }
            final File file3 = new File(file, "CVS/Entries.Backup");
            if (file3.exists()) {
                try {
                    if (StandardAdminHandler.t9yBeforeRename != null) {
                        StandardAdminHandler.t9yBeforeRename.run();
                    }
                    FileUtils.renameFile(file3, file2);
                    return file2;
                }
                catch (IOException ex) {}
            }
            return null;
        }
    }
    
    private static File seekEntries(final String pathname) {
        return seekEntries(new File(pathname));
    }
    
    static void t9yBeforeRenameSync(final Runnable t9yBeforeRename) {
        StandardAdminHandler.t9yBeforeRename = t9yBeforeRename;
    }
    
    static {
        ksEntries = new Object();
    }
}
