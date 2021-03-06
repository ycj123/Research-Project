// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.execute;

import org.pitest.util.ExitCode;
import java.util.Iterator;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.mutationtest.MutationStatusMap;
import java.io.IOException;
import java.util.Map;
import org.pitest.mutationtest.MutationStatusTestPair;
import org.pitest.mutationtest.engine.MutationIdentifier;
import java.util.HashMap;
import org.pitest.process.ProcessArgs;
import java.net.ServerSocket;
import org.pitest.process.WrappingProcess;

public class MutationTestProcess
{
    private final WrappingProcess process;
    private final MutationTestCommunicationThread thread;
    
    public MutationTestProcess(final ServerSocket socket, final ProcessArgs processArgs, final MinionArguments arguments) {
        this.process = new WrappingProcess(socket.getLocalPort(), processArgs, MutationTestMinion.class);
        this.thread = new MutationTestCommunicationThread(socket, arguments, new HashMap<MutationIdentifier, MutationStatusTestPair>());
    }
    
    public void start() throws IOException, InterruptedException {
        this.thread.start();
        this.process.start();
    }
    
    public void results(final MutationStatusMap allmutations) throws IOException {
        for (final MutationDetails each : allmutations.allMutations()) {
            final MutationStatusTestPair status = this.thread.getStatus(each.getId());
            if (status != null) {
                allmutations.setStatusForMutation(each, status);
            }
        }
    }
    
    public ExitCode waitToDie() {
        try {
            return this.thread.waitToFinish();
        }
        finally {
            this.process.destroy();
        }
    }
}
