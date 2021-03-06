// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.build.intercept.javafeatures;

import org.pitest.util.Log;
import java.util.Set;
import org.pitest.mutationtest.engine.MutationIdentifier;
import java.util.HashSet;
import org.pitest.functional.FunctionalList;
import org.pitest.functional.F;
import org.pitest.functional.prelude.Prelude;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.Map;
import org.pitest.functional.FCollection;
import java.util.ArrayList;
import org.pitest.mutationtest.engine.Mutater;
import org.pitest.mutationtest.engine.MutationDetails;
import java.util.Collection;
import org.pitest.bytecode.analysis.ClassTree;
import org.pitest.mutationtest.build.InterceptorType;
import java.util.logging.Logger;
import org.pitest.mutationtest.build.MutationInterceptor;

public class InlinedFinallyBlockFilter implements MutationInterceptor
{
    private static final Logger LOG;
    
    @Override
    public InterceptorType type() {
        return InterceptorType.FILTER;
    }
    
    @Override
    public void begin(final ClassTree clazz) {
    }
    
    @Override
    public Collection<MutationDetails> intercept(final Collection<MutationDetails> mutations, final Mutater m) {
        final List<MutationDetails> combined = new ArrayList<MutationDetails>(mutations.size());
        final Map<LineMutatorPair, Collection<MutationDetails>> mutatorLinebuckets = FCollection.bucket(mutations, toLineMutatorPair());
        for (final Map.Entry<LineMutatorPair, Collection<MutationDetails>> each : mutatorLinebuckets.entrySet()) {
            if (each.getValue().size() > 1) {
                this.checkForInlinedCode(combined, each);
            }
            else {
                combined.addAll(each.getValue());
            }
        }
        Collections.sort(combined, compareLineNumbers());
        return combined;
    }
    
    @Override
    public void end() {
    }
    
    private static Comparator<MutationDetails> compareLineNumbers() {
        return new Comparator<MutationDetails>() {
            @Override
            public int compare(final MutationDetails arg0, final MutationDetails arg1) {
                return arg0.getLineNumber() - arg1.getLineNumber();
            }
        };
    }
    
    private void checkForInlinedCode(final Collection<MutationDetails> combined, final Map.Entry<LineMutatorPair, Collection<MutationDetails>> each) {
        final FunctionalList<MutationDetails> mutationsInHandlerBlock = FCollection.filter(each.getValue(), isInFinallyHandler());
        if (!this.isPossibleToCorrectInlining(mutationsInHandlerBlock)) {
            combined.addAll(each.getValue());
            return;
        }
        final MutationDetails baseMutation = mutationsInHandlerBlock.get(0);
        final int firstBlock = baseMutation.getBlock();
        final FunctionalList<Integer> ids = FCollection.map(each.getValue(), mutationToBlock());
        if (ids.contains((F<Integer, Boolean>)Prelude.not((F<Object, Boolean>)Prelude.isEqualTo(firstBlock)))) {
            combined.add(makeCombinedMutant(each.getValue()));
        }
        else {
            combined.addAll(each.getValue());
        }
    }
    
    private boolean isPossibleToCorrectInlining(final List<MutationDetails> mutationsInHandlerBlock) {
        if (mutationsInHandlerBlock.size() > 1) {
            InlinedFinallyBlockFilter.LOG.warning("Found more than one mutation similar on same line in a finally block. Can't correct for inlining.");
            return false;
        }
        return !mutationsInHandlerBlock.isEmpty();
    }
    
    private static F<MutationDetails, Boolean> isInFinallyHandler() {
        return new F<MutationDetails, Boolean>() {
            @Override
            public Boolean apply(final MutationDetails a) {
                return a.isInFinallyBlock();
            }
        };
    }
    
    private static MutationDetails makeCombinedMutant(final Collection<MutationDetails> value) {
        final MutationDetails first = value.iterator().next();
        final Set<Integer> indexes = new HashSet<Integer>();
        FCollection.mapTo(value, mutationToIndex(), indexes);
        final MutationIdentifier id = new MutationIdentifier(first.getId().getLocation(), indexes, first.getId().getMutator());
        return new MutationDetails(id, first.getFilename(), first.getDescription(), first.getLineNumber(), first.getBlock());
    }
    
    private static F<MutationDetails, Integer> mutationToIndex() {
        return new F<MutationDetails, Integer>() {
            @Override
            public Integer apply(final MutationDetails a) {
                return a.getFirstIndex();
            }
        };
    }
    
    private static F<MutationDetails, Integer> mutationToBlock() {
        return new F<MutationDetails, Integer>() {
            @Override
            public Integer apply(final MutationDetails a) {
                return a.getBlock();
            }
        };
    }
    
    private static F<MutationDetails, LineMutatorPair> toLineMutatorPair() {
        return new F<MutationDetails, LineMutatorPair>() {
            @Override
            public LineMutatorPair apply(final MutationDetails a) {
                return new LineMutatorPair(a.getLineNumber(), a.getMutator());
            }
        };
    }
    
    static {
        LOG = Log.getLogger();
    }
}
