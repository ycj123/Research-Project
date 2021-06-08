// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report;

import org.mudebug.prapr.reloc.commons.io.filefilter.AndFileFilter;
import java.util.Arrays;
import org.mudebug.prapr.reloc.commons.io.filefilter.CanWriteFileFilter;
import org.mudebug.prapr.reloc.commons.io.filefilter.RegexFileFilter;
import org.mudebug.prapr.reloc.commons.io.filefilter.DirectoryFileFilter;
import org.mudebug.prapr.reloc.commons.io.filefilter.IOFileFilter;
import org.mudebug.prapr.reloc.commons.io.comparator.LastModifiedFileComparator;
import org.pitest.util.PitError;
import org.apache.maven.plugin.logging.Log;
import java.io.File;
import java.io.FileFilter;

public class ReportSourceLocator
{
    static final FileFilter TIMESTAMPED_REPORTS_FILE_FILTER;
    
    public File locate(final File reportsDirectory, final Log log) {
        if (!reportsDirectory.exists()) {
            throw new PitError("could not find reports directory [" + reportsDirectory + "]");
        }
        if (!reportsDirectory.canRead()) {
            throw new PitError("reports directory [" + reportsDirectory + "] not readable");
        }
        if (!reportsDirectory.isDirectory()) {
            throw new PitError("reports directory [" + reportsDirectory + "] is actually a file, it must be a directory");
        }
        return this.executeLocator(reportsDirectory, log);
    }
    
    private File executeLocator(final File reportsDirectory, final Log log) {
        final File[] subdirectories = reportsDirectory.listFiles(ReportSourceLocator.TIMESTAMPED_REPORTS_FILE_FILTER);
        File latest = reportsDirectory;
        log.debug("ReportSourceLocator starting search in directory [" + reportsDirectory.getAbsolutePath() + "]");
        if (subdirectories != null) {
            final LastModifiedFileComparator c = new LastModifiedFileComparator();
            for (final File f : subdirectories) {
                log.debug("comparing directory [" + f.getAbsolutePath() + "] with the current latest directory of [" + latest.getAbsolutePath() + "]");
                if (c.compare((Object)latest, (Object)f) < 0) {
                    latest = f;
                    log.debug("directory [" + f.getAbsolutePath() + "] is now the latest");
                }
            }
            log.debug("ReportSourceLocator determined directory [" + latest.getAbsolutePath() + "] is the directory containing the latest pit reports");
            return latest;
        }
        throw new PitError("could not list files in directory [" + reportsDirectory.getAbsolutePath() + "] because of an unknown I/O error");
    }
    
    static {
        TIMESTAMPED_REPORTS_FILE_FILTER = new AndFileFilter(Arrays.asList(DirectoryFileFilter.DIRECTORY, new RegexFileFilter("^\\d+$"), CanWriteFileFilter.CAN_WRITE));
    }
}
