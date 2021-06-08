// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.mutationtest.SourceLocator;
import org.pitest.mutationtest.MutationResultListener;
import org.pitest.mutationtest.ListenerArguments;
import java.util.Properties;
import org.pitest.mutationtest.MutationResultListenerFactory;

public class HtmlReportFactory implements MutationResultListenerFactory
{
    @Override
    public MutationResultListener getListener(final Properties props, final ListenerArguments args) {
        return new MutationHtmlReportListener(args.getCoverage(), args.getOutputStrategy(), args.getEngine().getMutatorNames(), new SourceLocator[] { args.getLocator() });
    }
    
    @Override
    public String name() {
        return "HTML";
    }
    
    @Override
    public String description() {
        return "Default html report plugin";
    }
}
