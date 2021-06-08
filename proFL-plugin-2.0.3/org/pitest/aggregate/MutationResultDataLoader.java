// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.aggregate;

import org.pitest.mutationtest.MutationStatusTestPair;
import org.pitest.mutationtest.DetectionStatus;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.mutationtest.engine.MutationIdentifier;
import java.util.Arrays;
import org.pitest.mutationtest.engine.Location;
import org.pitest.mutationtest.engine.MethodName;
import org.pitest.classinfo.ClassName;
import java.util.Map;
import java.io.File;
import java.util.Collection;
import org.pitest.mutationtest.MutationResult;

class MutationResultDataLoader extends DataLoader<MutationResult>
{
    private static final String MUTATED_CLASS = "mutatedClass";
    private static final String MUTATED_METHOD = "mutatedMethod";
    private static final String METHOD_DESCRIPTION = "methodDescription";
    private static final String INDEX = "index";
    private static final String MUTATOR = "mutator";
    private static final String SOURCE_FILE = "sourceFile";
    private static final String DESCRIPTION = "description";
    private static final String LINE_NUMBER = "lineNumber";
    private static final String BLOCK = "block";
    private static final String NUMBER_OF_TESTS_RUN = "numberOfTestsRun";
    private static final String STATUS = "status";
    private static final String KILLING_TEST = "killingTest";
    
    MutationResultDataLoader(final Collection<File> filesToLoad) {
        super(filesToLoad);
    }
    
    @Override
    protected MutationResult mapToData(final Map<String, Object> map) {
        final Location location = new Location(ClassName.fromString(map.get("mutatedClass")), MethodName.fromString(map.get("mutatedMethod")), map.get("methodDescription"));
        final MutationIdentifier id = new MutationIdentifier(location, Arrays.asList(new Integer(map.get("index"))), map.get("mutator"));
        final MutationDetails md = new MutationDetails(id, map.get("sourceFile"), map.get("description"), Integer.parseInt(map.get("lineNumber")), Integer.parseInt(map.get("block")));
        final MutationStatusTestPair status = new MutationStatusTestPair(Integer.parseInt(map.get("numberOfTestsRun")), DetectionStatus.valueOf(map.get("status")), map.get("killingTest"));
        return new MutationResult(md, status);
    }
}
