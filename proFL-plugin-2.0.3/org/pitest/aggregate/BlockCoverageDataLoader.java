// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.aggregate;

import org.pitest.coverage.BlockLocation;
import org.pitest.mutationtest.engine.Location;
import org.pitest.mutationtest.engine.MethodName;
import org.pitest.classinfo.ClassName;
import java.util.Map;
import java.io.File;
import java.util.Collection;
import org.pitest.coverage.BlockCoverage;

class BlockCoverageDataLoader extends DataLoader<BlockCoverage>
{
    private static final String METHOD = "method";
    private static final String CLASSNAME = "classname";
    private static final String NUMBER = "number";
    private static final String TESTS = "tests";
    private static final String OPEN_PAREN = "(";
    
    BlockCoverageDataLoader(final Collection<File> filesToLoad) {
        super(filesToLoad);
    }
    
    @Override
    protected BlockCoverage mapToData(final Map<String, Object> map) {
        final String method = map.get("method");
        final Location location = new Location(ClassName.fromString(map.get("classname")), MethodName.fromString(method.substring(0, method.indexOf("("))), method.substring(method.indexOf("(")));
        final BlockLocation blockLocation = new BlockLocation(location, Integer.parseInt(map.get("number")));
        final Collection<String> tests = map.get("tests");
        return new BlockCoverage(blockLocation, tests);
    }
}
