// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.system;

import uk.org.lidalia.sysoutslf4j.common.SLF4JPrintStream;
import java.io.PrintStream;
import uk.org.lidalia.sysoutslf4j.common.SystemOutput;
import uk.org.lidalia.sysoutslf4j.common.PrintStreamCoordinator;

public final class PrintStreamCoordinatorImpl implements PrintStreamCoordinator
{
    @Override
    public void replaceSystemOutputsWithSLF4JPrintStreams() {
        SystemOutput[] values;
        for (int length = (values = SystemOutput.values()).length, i = 0; i < length; ++i) {
            final SystemOutput systemOutput = values[i];
            replaceSystemOutputWithSLF4JPrintStream(systemOutput);
        }
    }
    
    private static void replaceSystemOutputWithSLF4JPrintStream(final SystemOutput systemOutput) {
        final SLF4JPrintStreamImpl slf4jPrintStream = buildSLF4JPrintStream(systemOutput.get());
        systemOutput.set(slf4jPrintStream);
    }
    
    private static SLF4JPrintStreamImpl buildSLF4JPrintStream(final PrintStream originalPrintStream) {
        final LoggerAppenderStore loggerAppenderStore = new LoggerAppenderStore();
        final SLF4JPrintStreamDelegate delegate = new SLF4JPrintStreamDelegate(originalPrintStream, loggerAppenderStore);
        return new SLF4JPrintStreamImpl(originalPrintStream, delegate);
    }
    
    @Override
    public void restoreOriginalSystemOutputs() {
        SystemOutput[] values;
        for (int length = (values = SystemOutput.values()).length, i = 0; i < length; ++i) {
            final SystemOutput systemOutput = values[i];
            restoreSystemOutput(systemOutput);
        }
    }
    
    private static void restoreSystemOutput(final SystemOutput systemOutput) {
        final SLF4JPrintStream slf4jPrintStream = (SLF4JPrintStream)systemOutput.get();
        systemOutput.set(slf4jPrintStream.getOriginalPrintStream());
    }
}
