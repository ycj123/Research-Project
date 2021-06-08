// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import org.pitest.functional.SideEffect1;
import org.pitest.functional.FunctionalList;
import org.pitest.functional.FCollection;
import org.pitest.functional.F;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.pitest.mutationtest.MutationResult;
import org.pitest.functional.FunctionalIterable;

public class MutationResultList implements FunctionalIterable<MutationResult>
{
    private final List<MutationResult> impl;
    
    public MutationResultList(final Collection<MutationResult> results) {
        (this.impl = new ArrayList<MutationResult>()).addAll(results);
    }
    
    public List<MutationGrouping> groupMutationsByLine() {
        this.sortMutationsIntoLineOrder();
        final List<MutationGrouping> groups = new ArrayList<MutationGrouping>();
        List<MutationResult> sublist = new ArrayList<MutationResult>();
        int lastLineNumber = -1;
        for (final MutationResult each : this.impl) {
            if (lastLineNumber != each.getDetails().getLineNumber() && !sublist.isEmpty()) {
                groups.add(new MutationGrouping(lastLineNumber, "Line " + lastLineNumber, sublist));
                sublist = new ArrayList<MutationResult>();
            }
            sublist.add(each);
            lastLineNumber = each.getDetails().getLineNumber();
        }
        if (!sublist.isEmpty()) {
            groups.add(new MutationGrouping(lastLineNumber, "Line " + lastLineNumber, sublist));
        }
        return groups;
    }
    
    private void sortMutationsIntoLineOrder() {
        final Comparator<MutationResult> c = new Comparator<MutationResult>() {
            @Override
            public int compare(final MutationResult o1, final MutationResult o2) {
                return o1.getDetails().getLineNumber() - o2.getDetails().getLineNumber();
            }
        };
        Collections.sort(this.impl, c);
    }
    
    @Override
    public boolean contains(final F<MutationResult, Boolean> predicate) {
        return FCollection.contains(this.impl, predicate);
    }
    
    @Override
    public FunctionalList<MutationResult> filter(final F<MutationResult, Boolean> predicate) {
        return FCollection.filter(this, predicate);
    }
    
    @Override
    public <B> FunctionalList<B> flatMap(final F<MutationResult, ? extends Iterable<B>> f) {
        return FCollection.flatMap((Iterable<? extends MutationResult>)this, f);
    }
    
    @Override
    public void forEach(final SideEffect1<MutationResult> e) {
        FCollection.forEach(this, e);
    }
    
    @Override
    public Iterator<MutationResult> iterator() {
        return this.impl.iterator();
    }
    
    @Override
    public <B> FunctionalList<B> map(final F<MutationResult, B> f) {
        return FCollection.map(this, f);
    }
    
    @Override
    public <B> void mapTo(final F<MutationResult, B> f, final Collection<? super B> bs) {
        FCollection.mapTo(this, f, bs);
    }
}
