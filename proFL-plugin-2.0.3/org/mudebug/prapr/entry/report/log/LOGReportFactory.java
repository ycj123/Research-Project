// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report.log;

import java.lang.reflect.Field;
import org.pitest.mutationtest.config.DirectoryResultOutputStrategy;
import org.pitest.util.ResultOutputStrategy;
import org.pitest.mutationtest.engine.Mutater;
import java.io.File;
import org.mudebug.prapr.entry.mutationtest.AugmentedListenerArguments;
import org.pitest.mutationtest.MutationResultListener;
import org.pitest.mutationtest.ListenerArguments;
import java.util.Properties;
import org.pitest.mutationtest.MutationResultListenerFactory;

public class LOGReportFactory implements MutationResultListenerFactory
{
    @Override
    public String description() {
        return "PraPR log report plugin";
    }
    
    @Override
    public MutationResultListener getListener(final Properties props, final ListenerArguments args) {
        final AugmentedListenerArguments arguments = (AugmentedListenerArguments)args;
        final Mutater mutater = arguments.getEngine().createMutator(arguments.getClassByteArraySource());
        final File poolDirectory = new File(this.getReportDirectory(arguments.getOutputStrategy()), "pool");
        return new LOGReportListener(arguments.getOutputStrategy(), poolDirectory, arguments.getSuspStrategy(), arguments.getFailingTests(), arguments.getAllTestsCount(), mutater, arguments.shouldDumpMutations());
    }
    
    private File getReportDirectory(final ResultOutputStrategy outputStrategy) {
        try {
            if (outputStrategy instanceof DirectoryResultOutputStrategy) {
                final Field reportDirField = outputStrategy.getClass().getDeclaredField("reportDir");
                reportDirField.setAccessible(true);
                return (File)reportDirField.get(outputStrategy);
            }
        }
        catch (Exception ex) {}
        throw new IllegalArgumentException();
    }
    
    @Override
    public String name() {
        return "LOG";
    }
}
