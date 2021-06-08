// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.entry.report.compressedxml;

import org.mudebug.prapr.entry.mutationtest.AugmentedListenerArguments;
import org.pitest.mutationtest.MutationResultListener;
import org.pitest.mutationtest.ListenerArguments;
import java.util.Properties;
import org.pitest.mutationtest.MutationResultListenerFactory;

public class CompressedXMLFactory implements MutationResultListenerFactory
{
    @Override
    public MutationResultListener getListener(final Properties props, final ListenerArguments args) {
        final AugmentedListenerArguments arguments = (AugmentedListenerArguments)args;
        final CompressedDirectoryResultOutputStrategy cdros = CompressedDirectoryResultOutputStrategy.forResultOutputStrategy(arguments.getOutputStrategy());
        return new CompressedXMLReportListener(cdros, arguments.getSuspStrategy(), arguments.getFailingTests(), arguments.getAllTestsCount());
    }
    
    @Override
    public String name() {
        return "COMPRESSED-XML";
    }
    
    @Override
    public String description() {
        return "Compressed xml report plugin";
    }
}
