// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Iterator;
import javax.annotation.WillClose;
import java.nio.channels.FileChannel;
import java.nio.channels.Channel;
import java.nio.channels.ReadableByteChannel;
import java.net.URL;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.Os;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.File;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.StringUtils;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;

public class FileUtils
{
    private static final int ONE_KB = 1024;
    private static final int ONE_MB = 1048576;
    private static final int ONE_GB = 1073741824;
    private static final long FILE_COPY_BUFFER_SIZE = 31457280L;
    private static final String FS;
    private static final String[] INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME;
    
    protected FileUtils() {
    }
    
    @Nonnull
    public static String[] getDefaultExcludes() {
        return DirectoryScanner.DEFAULTEXCLUDES;
    }
    
    @Nonnull
    public static List<String> getDefaultExcludesAsList() {
        return Arrays.asList(getDefaultExcludes());
    }
    
    @Nonnull
    public static String getDefaultExcludesAsString() {
        return StringUtils.join(DirectoryScanner.DEFAULTEXCLUDES, ",");
    }
    
    @Nonnull
    public static String dirname(@Nonnull final String filename) {
        final int i = filename.lastIndexOf(File.separator);
        return (i >= 0) ? filename.substring(0, i) : "";
    }
    
    @Nonnull
    public static String filename(@Nonnull final String filename) {
        final int i = filename.lastIndexOf(File.separator);
        return (i >= 0) ? filename.substring(i + 1) : filename;
    }
    
    @Nonnull
    public static String extension(@Nonnull final String filename) {
        final int lastSep = filename.lastIndexOf(File.separatorChar);
        int lastDot;
        if (lastSep < 0) {
            lastDot = filename.lastIndexOf(46);
        }
        else {
            lastDot = filename.substring(lastSep + 1).lastIndexOf(46);
            if (lastDot >= 0) {
                lastDot += lastSep + 1;
            }
        }
        if (lastDot >= 0 && lastDot > lastSep) {
            return filename.substring(lastDot + 1);
        }
        return "";
    }
    
    public static boolean fileExists(@Nonnull final String fileName) {
        final File file = new File(fileName);
        return file.exists();
    }
    
    @Nonnull
    public static String fileRead(@Nonnull final String file) throws IOException {
        return fileRead(file, null);
    }
    
    @Nonnull
    private static String fileRead(@Nonnull final String file, @Nullable final String encoding) throws IOException {
        return fileRead(new File(file), encoding);
    }
    
    @Nonnull
    public static String fileRead(@Nonnull final File file) throws IOException {
        return fileRead(file, null);
    }
    
    @Nonnull
    public static String fileRead(@Nonnull final File file, @Nullable final String encoding) throws IOException {
        final StringBuilder buf = new StringBuilder();
        Reader reader = null;
        try {
            if (encoding != null) {
                reader = new InputStreamReader(new FileInputStream(file), encoding);
            }
            else {
                reader = new InputStreamReader(new FileInputStream(file));
            }
            final char[] b = new char[512];
            int count;
            while ((count = reader.read(b)) > 0) {
                buf.append(b, 0, count);
            }
        }
        finally {
            IOUtil.close(reader);
        }
        return buf.toString();
    }
    
    @Nonnull
    public static String[] fileReadArray(@Nonnull final File file) throws IOException {
        final List<String> files = loadFile(file);
        return files.toArray(new String[files.size()]);
    }
    
    public static void fileAppend(@Nonnull final String fileName, @Nonnull final String data) throws IOException {
        fileAppend(fileName, null, data);
    }
    
    public static void fileAppend(@Nonnull final String fileName, @Nullable final String encoding, @Nonnull final String data) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName, true);
            if (encoding != null) {
                out.write(data.getBytes(encoding));
            }
            else {
                out.write(data.getBytes());
            }
        }
        finally {
            IOUtil.close(out);
        }
    }
    
    public static void fileWrite(@Nonnull final String fileName, @Nonnull final String data) throws IOException {
        fileWrite(fileName, null, data);
    }
    
    public static void fileWrite(@Nonnull final String fileName, @Nullable final String encoding, @Nonnull final String data) throws IOException {
        final File file = new File(fileName);
        fileWrite(file, encoding, data);
    }
    
    public static void fileWrite(@Nonnull final File file, @Nullable final String encoding, @Nonnull final String data) throws IOException {
        Writer writer = null;
        try {
            final OutputStream out = new FileOutputStream(file);
            if (encoding != null) {
                writer = new OutputStreamWriter(out, encoding);
            }
            else {
                writer = new OutputStreamWriter(out);
            }
            writer.write(data);
        }
        finally {
            IOUtil.close(writer);
        }
    }
    
    public static void fileWriteArray(@Nonnull final File file, @Nullable final String... data) throws IOException {
        fileWriteArray(file, (String)null, data);
    }
    
    public static void fileWriteArray(@Nonnull final File file, @Nullable final String encoding, @Nullable final String... data) throws IOException {
        Writer writer = null;
        try {
            final OutputStream out = new FileOutputStream(file);
            if (encoding != null) {
                writer = new OutputStreamWriter(out, encoding);
            }
            else {
                writer = new OutputStreamWriter(out);
            }
            for (int i = 0; data != null && i < data.length; ++i) {
                writer.write(data[i]);
                if (i < data.length) {
                    writer.write("\n");
                }
            }
        }
        finally {
            IOUtil.close(writer);
        }
    }
    
    public static void fileDelete(@Nonnull final String fileName) {
        final File file = new File(fileName);
        file.delete();
    }
    
    public static String[] getFilesFromExtension(@Nonnull final String directory, @Nonnull final String... extensions) {
        List<String> files = new ArrayList<String>();
        final File currentDir = new File(directory);
        final String[] unknownFiles = currentDir.list();
        if (unknownFiles == null) {
            return new String[0];
        }
        for (final String unknownFile : unknownFiles) {
            final String currentFileName = directory + System.getProperty("file.separator") + unknownFile;
            final File currentFile = new File(currentFileName);
            if (currentFile.isDirectory()) {
                if (!currentFile.getName().equals("CVS")) {
                    final String[] fetchFiles = getFilesFromExtension(currentFileName, extensions);
                    files = blendFilesToList(files, fetchFiles);
                }
            }
            else {
                final String add = currentFile.getAbsolutePath();
                if (isValidFile(add, extensions)) {
                    files.add(add);
                }
            }
        }
        final String[] foundFiles = new String[files.size()];
        files.toArray(foundFiles);
        return foundFiles;
    }
    
    @Nonnull
    private static List<String> blendFilesToList(@Nonnull final List<String> v, @Nonnull final String... files) {
        Collections.addAll(v, files);
        return v;
    }
    
    private static boolean isValidFile(@Nonnull final String file, @Nonnull final String... extensions) {
        final String extension = extension(file);
        for (final String extension2 : extensions) {
            if (extension2.equals(extension)) {
                return true;
            }
        }
        return false;
    }
    
    public static void mkdir(@Nonnull final String dir) {
        final File file = new File(dir);
        if (Os.isFamily("windows") && !isValidWindowsFileName(file)) {
            throw new IllegalArgumentException("The file (" + dir + ") cannot contain any of the following characters: \n" + StringUtils.join(FileUtils.INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME, " "));
        }
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    
    public static boolean contentEquals(@Nonnull final File file1, @Nonnull final File file2) throws IOException {
        final boolean file1Exists = file1.exists();
        if (file1Exists != file2.exists()) {
            return false;
        }
        if (!file1Exists) {
            return true;
        }
        if (file1.isDirectory() || file2.isDirectory()) {
            return false;
        }
        InputStream input1 = null;
        InputStream input2 = null;
        try {
            input1 = new FileInputStream(file1);
            input2 = new FileInputStream(file2);
            return IOUtil.contentEquals(input1, input2);
        }
        finally {
            IOUtil.close(input1);
            IOUtil.close(input2);
        }
    }
    
    @Nullable
    public static File toFile(@Nullable final URL url) {
        if (url == null || !url.getProtocol().equalsIgnoreCase("file")) {
            return null;
        }
        String filename = url.getFile().replace('/', File.separatorChar);
        char ch = '\0';
        for (int pos = -1; (pos = filename.indexOf(37, pos + 1)) >= 0; filename = filename.substring(0, pos) + ch + filename.substring(pos + 3)) {
            if (pos + 2 < filename.length()) {
                final String hexStr = filename.substring(pos + 1, pos + 3);
                ch = (char)Integer.parseInt(hexStr, 16);
            }
        }
        return new File(filename);
    }
    
    @Nonnull
    public static URL[] toURLs(@Nonnull final File... files) throws IOException {
        final URL[] urls = new URL[files.length];
        for (int i = 0; i < urls.length; ++i) {
            urls[i] = files[i].toURL();
        }
        return urls;
    }
    
    @Nonnull
    public static String removeExtension(@Nonnull final String filename) {
        final String ext = extension(filename);
        if ("".equals(ext)) {
            return filename;
        }
        final int index = filename.lastIndexOf(ext) - 1;
        return filename.substring(0, index);
    }
    
    @Nonnull
    public static String getExtension(@Nonnull final String filename) {
        return extension(filename);
    }
    
    public static void copyFileToDirectory(@Nonnull final File source, @Nonnull final File destinationDirectory) throws IOException {
        if (destinationDirectory.exists() && !destinationDirectory.isDirectory()) {
            throw new IllegalArgumentException("Destination is not a directory");
        }
        copyFile(source, new File(destinationDirectory, source.getName()));
    }
    
    private static void copyFileToDirectoryIfModified(@Nonnull final File source, @Nonnull final File destinationDirectory) throws IOException {
        if (destinationDirectory.exists() && !destinationDirectory.isDirectory()) {
            throw new IllegalArgumentException("Destination is not a directory");
        }
        copyFileIfModified(source, new File(destinationDirectory, source.getName()));
    }
    
    public static void copyFile(@Nonnull final File source, @Nonnull final File destination) throws IOException {
        if (!source.exists()) {
            final String message = "File " + source + " does not exist";
            throw new IOException(message);
        }
        if (source.getCanonicalPath().equals(destination.getCanonicalPath())) {
            return;
        }
        mkdirsFor(destination);
        doCopyFile(source, destination);
        if (source.length() != destination.length()) {
            final String message = "Failed to copy full contents from " + source + " to " + destination;
            throw new IOException(message);
        }
    }
    
    private static void mkdirsFor(@Nonnull final File destination) {
        if (destination.getParentFile() != null && !destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }
    }
    
    private static void doCopyFile(@Nonnull final File source, @Nonnull final File destination) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(destination);
            input = fis.getChannel();
            output = fos.getChannel();
            long count;
            for (long size = input.size(), pos = 0L; pos < size; pos += output.transferFrom(input, pos, count)) {
                count = ((size - pos > 31457280L) ? 31457280L : (size - pos));
            }
        }
        finally {
            IOUtil.close(output);
            IOUtil.close(fos);
            IOUtil.close(input);
            IOUtil.close(fis);
        }
    }
    
    private static boolean copyFileIfModified(@Nonnull final File source, @Nonnull final File destination) throws IOException {
        if (destination.lastModified() < source.lastModified()) {
            copyFile(source, destination);
            return true;
        }
        return false;
    }
    
    public static void copyURLToFile(@Nonnull final URL source, @Nonnull final File destination) throws IOException {
        copyStreamToFile(source.openStream(), destination);
    }
    
    private static void copyStreamToFile(@Nonnull @WillClose final InputStream source, @Nonnull final File destination) throws IOException {
        FileOutputStream output = null;
        try {
            if (destination.getParentFile() != null && !destination.getParentFile().exists()) {
                destination.getParentFile().mkdirs();
            }
            if (destination.exists() && !destination.canWrite()) {
                final String message = "Unable to open file " + destination + " for writing.";
                throw new IOException(message);
            }
            output = new FileOutputStream(destination);
            IOUtil.copy(source, output);
        }
        finally {
            IOUtil.close(source);
            IOUtil.close(output);
        }
    }
    
    public static String normalize(final String path) {
        String normalized = path;
        while (true) {
            final int index = normalized.indexOf("//");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + normalized.substring(index + 1);
        }
        while (true) {
            final int index = normalized.indexOf("/./");
            if (index < 0) {
                break;
            }
            normalized = normalized.substring(0, index) + normalized.substring(index + 2);
        }
        while (true) {
            final int index = normalized.indexOf("/../");
            if (index < 0) {
                return normalized;
            }
            if (index == 0) {
                return null;
            }
            final int index2 = normalized.lastIndexOf(47, index - 1);
            normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
        }
    }
    
    public static File resolveFile(final File baseFile, final String filename) {
        String filenm = filename;
        if ('/' != File.separatorChar) {
            filenm = filename.replace('/', File.separatorChar);
        }
        if ('\\' != File.separatorChar) {
            filenm = filename.replace('\\', File.separatorChar);
        }
        if (filenm.startsWith(File.separator) || (Os.isFamily("windows") && filenm.indexOf(":") > 0)) {
            File file = new File(filenm);
            try {
                file = file.getCanonicalFile();
            }
            catch (IOException ex) {}
            return file;
        }
        final char[] chars = filename.toCharArray();
        final StringBuilder sb = new StringBuilder();
        int start = 0;
        if ('\\' == File.separatorChar) {
            sb.append(filenm.charAt(0));
            ++start;
        }
        for (int i = start; i < chars.length; ++i) {
            final boolean doubleSeparator = File.separatorChar == chars[i] && File.separatorChar == chars[i - 1];
            if (!doubleSeparator) {
                sb.append(chars[i]);
            }
        }
        filenm = sb.toString();
        File file2 = new File(baseFile, filenm).getAbsoluteFile();
        try {
            file2 = file2.getCanonicalFile();
        }
        catch (IOException ex2) {}
        return file2;
    }
    
    public static void forceDelete(final String file) throws IOException {
        forceDelete(new File(file));
    }
    
    public static void forceDelete(@Nonnull final File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        }
        else {
            final boolean filePresent = file.getCanonicalFile().exists();
            if (!deleteFile(file) && filePresent) {
                final String message = "File " + file + " unable to be deleted.";
                throw new IOException(message);
            }
        }
    }
    
    private static boolean deleteFile(@Nonnull File file) throws IOException {
        if (file.isDirectory()) {
            throw new IOException("File " + file + " isn't a file.");
        }
        if (!file.delete()) {
            if (Os.isFamily("windows")) {
                file = file.getCanonicalFile();
                System.gc();
            }
            try {
                Thread.sleep(10L);
                return file.delete();
            }
            catch (InterruptedException ex) {
                return file.delete();
            }
        }
        return true;
    }
    
    public static void forceMkdir(@Nonnull final File file) throws IOException {
        if (Os.isFamily("windows") && !isValidWindowsFileName(file)) {
            throw new IllegalArgumentException("The file (" + file.getAbsolutePath() + ") cannot contain any of the following characters: \n" + StringUtils.join(FileUtils.INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME, " "));
        }
        if (file.exists()) {
            if (file.isFile()) {
                final String message = "File " + file + " exists and is " + "not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        }
        else if (!file.mkdirs()) {
            final String message = "Unable to create directory " + file;
            throw new IOException(message);
        }
    }
    
    public static void deleteDirectory(@Nonnull final String directory) throws IOException {
        deleteDirectory(new File(directory));
    }
    
    public static void deleteDirectory(@Nonnull final File directory) throws IOException {
        if (!directory.exists()) {
            return;
        }
        if (directory.delete()) {
            return;
        }
        cleanDirectory(directory);
        if (!directory.delete()) {
            final String message = "Directory " + directory + " unable to be deleted.";
            throw new IOException(message);
        }
    }
    
    public static void cleanDirectory(@Nonnull final File directory) throws IOException {
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }
        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }
        IOException exception = null;
        final File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (final File file : files) {
            try {
                forceDelete(file);
            }
            catch (IOException ioe) {
                exception = ioe;
            }
        }
        if (null != exception) {
            throw exception;
        }
    }
    
    public static long sizeOfDirectory(@Nonnull final String directory) {
        return sizeOfDirectory(new File(directory));
    }
    
    public static long sizeOfDirectory(@Nonnull final File directory) {
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }
        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }
        long size = 0L;
        final File[] files = directory.listFiles();
        if (files == null) {
            throw new IllegalArgumentException("Problems reading directory");
        }
        for (final File file : files) {
            if (file.isDirectory()) {
                size += sizeOfDirectory(file);
            }
            else {
                size += file.length();
            }
        }
        return size;
    }
    
    @Nonnull
    public static List<File> getFiles(@Nonnull final File directory, @Nullable final String includes, @Nullable final String excludes) throws IOException {
        return getFiles(directory, includes, excludes, true);
    }
    
    @Nonnull
    public static List<File> getFiles(@Nonnull final File directory, @Nullable final String includes, @Nullable final String excludes, final boolean includeBasedir) throws IOException {
        final List<String> fileNames = getFileNames(directory, includes, excludes, includeBasedir);
        final List<File> files = new ArrayList<File>();
        for (final String filename : fileNames) {
            files.add(new File(filename));
        }
        return files;
    }
    
    @Nonnull
    public static List<String> getFileNames(@Nonnull final File directory, @Nullable final String includes, @Nullable final String excludes, final boolean includeBasedir) throws IOException {
        return getFileNames(directory, includes, excludes, includeBasedir, true);
    }
    
    @Nonnull
    private static List<String> getFileNames(@Nonnull final File directory, @Nullable final String includes, @Nullable final String excludes, final boolean includeBasedir, final boolean isCaseSensitive) throws IOException {
        return getFileAndDirectoryNames(directory, includes, excludes, includeBasedir, isCaseSensitive, true, false);
    }
    
    @Nonnull
    public static List<String> getDirectoryNames(@Nonnull final File directory, @Nullable final String includes, @Nullable final String excludes, final boolean includeBasedir) throws IOException {
        return getDirectoryNames(directory, includes, excludes, includeBasedir, true);
    }
    
    @Nonnull
    public static List<String> getDirectoryNames(@Nonnull final File directory, @Nullable final String includes, @Nullable final String excludes, final boolean includeBasedir, final boolean isCaseSensitive) throws IOException {
        return getFileAndDirectoryNames(directory, includes, excludes, includeBasedir, isCaseSensitive, false, true);
    }
    
    @Nonnull
    public static List<String> getFileAndDirectoryNames(final File directory, @Nullable final String includes, @Nullable final String excludes, final boolean includeBasedir, final boolean isCaseSensitive, final boolean getFiles, final boolean getDirectories) {
        final DirectoryScanner scanner = new DirectoryScanner();
        scanner.setBasedir(directory);
        if (includes != null) {
            scanner.setIncludes(StringUtils.split(includes, ","));
        }
        if (excludes != null) {
            scanner.setExcludes(StringUtils.split(excludes, ","));
        }
        scanner.setCaseSensitive(isCaseSensitive);
        scanner.scan();
        final List<String> list = new ArrayList<String>();
        if (getFiles) {
            final String[] arr$;
            final String[] files = arr$ = scanner.getIncludedFiles();
            for (final String file : arr$) {
                if (includeBasedir) {
                    list.add(directory + FileUtils.FS + file);
                }
                else {
                    list.add(file);
                }
            }
        }
        if (getDirectories) {
            final String[] arr$;
            final String[] directories = arr$ = scanner.getIncludedDirectories();
            for (final String directory2 : arr$) {
                if (includeBasedir) {
                    list.add(directory + FileUtils.FS + directory2);
                }
                else {
                    list.add(directory2);
                }
            }
        }
        return list;
    }
    
    public static void copyDirectory(final File sourceDirectory, final File destinationDirectory) throws IOException {
        copyDirectory(sourceDirectory, destinationDirectory, "**", null);
    }
    
    public static void copyDirectory(@Nonnull final File sourceDirectory, @Nonnull final File destinationDirectory, @Nullable final String includes, @Nullable final String excludes) throws IOException {
        if (!sourceDirectory.exists()) {
            return;
        }
        final List<File> files = getFiles(sourceDirectory, includes, excludes);
        for (final File file : files) {
            copyFileToDirectory(file, destinationDirectory);
        }
    }
    
    public static void copyDirectoryStructure(@Nonnull final File sourceDirectory, @Nonnull final File destinationDirectory) throws IOException {
        copyDirectoryStructure(sourceDirectory, destinationDirectory, destinationDirectory, false);
    }
    
    private static void copyDirectoryStructure(@Nonnull final File sourceDirectory, @Nonnull final File destinationDirectory, final File rootDestinationDirectory, final boolean onlyModifiedFiles) throws IOException {
        if (sourceDirectory == null) {
            throw new IOException("source directory can't be null.");
        }
        if (destinationDirectory == null) {
            throw new IOException("destination directory can't be null.");
        }
        if (sourceDirectory.equals(destinationDirectory)) {
            throw new IOException("source and destination are the same directory.");
        }
        if (!sourceDirectory.exists()) {
            throw new IOException("Source directory doesn't exists (" + sourceDirectory.getAbsolutePath() + ").");
        }
        final File[] files = sourceDirectory.listFiles();
        if (files == null) {
            return;
        }
        final String sourcePath = sourceDirectory.getAbsolutePath();
        for (final File file : files) {
            if (!file.equals(rootDestinationDirectory)) {
                String dest = file.getAbsolutePath();
                dest = dest.substring(sourcePath.length() + 1);
                File destination = new File(destinationDirectory, dest);
                if (file.isFile()) {
                    destination = destination.getParentFile();
                    if (onlyModifiedFiles) {
                        copyFileToDirectoryIfModified(file, destination);
                    }
                    else {
                        copyFileToDirectory(file, destination);
                    }
                }
                else {
                    if (!file.isDirectory()) {
                        throw new IOException("Unknown file type: " + file.getAbsolutePath());
                    }
                    if (!destination.exists() && !destination.mkdirs()) {
                        throw new IOException("Could not create destination directory '" + destination.getAbsolutePath() + "'.");
                    }
                    copyDirectoryStructure(file, destination, rootDestinationDirectory, onlyModifiedFiles);
                }
            }
        }
    }
    
    public static void rename(@Nonnull final File from, @Nonnull final File to) throws IOException {
        if (to.exists() && !to.delete()) {
            throw new IOException("Failed to delete " + to + " while trying to rename " + from);
        }
        final File parent = to.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("Failed to create directory " + parent + " while trying to rename " + from);
        }
        if (!from.renameTo(to)) {
            copyFile(from, to);
            if (!from.delete()) {
                throw new IOException("Failed to delete " + from + " while trying to rename it.");
            }
        }
    }
    
    public static File createTempFile(@Nonnull final String prefix, @Nonnull final String suffix, @Nullable final File parentDir) {
        String parent = System.getProperty("java.io.tmpdir");
        if (parentDir != null) {
            parent = parentDir.getPath();
        }
        final DecimalFormat fmt = new DecimalFormat("#####");
        final SecureRandom secureRandom = new SecureRandom();
        final long secureInitializer = secureRandom.nextLong();
        final Random rand = new Random(secureInitializer + Runtime.getRuntime().freeMemory());
        File result;
        do {
            result = new File(parent, prefix + fmt.format(positiveRandom(rand)) + suffix);
        } while (result.exists());
        return result;
    }
    
    private static int positiveRandom(final Random rand) {
        int a;
        for (a = rand.nextInt(); a == Integer.MIN_VALUE; a = rand.nextInt()) {}
        return Math.abs(a);
    }
    
    public static void copyFile(@Nonnull final File from, @Nonnull final File to, @Nullable final String encoding, @Nullable final FilterWrapper... wrappers) throws IOException {
        copyFile(from, to, encoding, wrappers, false);
    }
    
    public static void copyFile(@Nonnull final File from, @Nonnull final File to, @Nullable final String encoding, @Nullable final FilterWrapper[] wrappers, final boolean overwrite) throws IOException {
        if (wrappers != null && wrappers.length > 0) {
            Reader fileReader = null;
            Writer fileWriter = null;
            try {
                if (encoding == null || encoding.length() < 1) {
                    fileReader = new BufferedReader(new FileReader(from));
                    fileWriter = new FileWriter(to);
                }
                else {
                    final FileInputStream instream = new FileInputStream(from);
                    final FileOutputStream outstream = new FileOutputStream(to);
                    fileReader = new BufferedReader(new InputStreamReader(instream, encoding));
                    fileWriter = new OutputStreamWriter(outstream, encoding);
                }
                Reader reader = fileReader;
                for (final FilterWrapper wrapper : wrappers) {
                    reader = wrapper.getReader(reader);
                }
                IOUtil.copy(reader, fileWriter);
            }
            finally {
                IOUtil.close(fileReader);
                IOUtil.close(fileWriter);
            }
        }
        else if (to.lastModified() < from.lastModified() || overwrite) {
            copyFile(from, to);
        }
    }
    
    @Nonnull
    public static List<String> loadFile(@Nonnull final File file) throws IOException {
        final List<String> lines = new ArrayList<String>();
        if (file.exists()) {
            final FileReader fileReader = new FileReader(file);
            try {
                final BufferedReader reader = new BufferedReader(fileReader);
                for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                    line = line.trim();
                    if (!line.startsWith("#") && line.length() != 0) {
                        lines.add(line);
                    }
                }
                reader.close();
            }
            finally {
                fileReader.close();
            }
        }
        return lines;
    }
    
    private static boolean isValidWindowsFileName(@Nonnull final File f) {
        if (Os.isFamily("windows")) {
            if (StringUtils.indexOfAny(f.getName(), FileUtils.INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME) != -1) {
                return false;
            }
            if (f.getParentFile() != null) {
                return isValidWindowsFileName(f.getParentFile());
            }
        }
        return true;
    }
    
    static {
        FS = System.getProperty("file.separator");
        INVALID_CHARACTERS_FOR_WINDOWS_FILE_NAME = new String[] { ":", "*", "?", "\"", "<", ">", "|" };
    }
    
    public abstract static class FilterWrapper
    {
        public abstract Reader getReader(final Reader p0);
    }
}
