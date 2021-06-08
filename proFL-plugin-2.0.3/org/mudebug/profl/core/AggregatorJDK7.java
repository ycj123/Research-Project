// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;
import java.util.Collection;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

public class AggregatorJDK7
{
    public static List<Tuple> aggregate(final List<Mutation> mutations, final boolean profl) {
        final Map<String, Tuple> grouped = new HashMap<String, Tuple>();
        for (final Mutation mutation : mutations) {
            final String methodId = mutation.getMethodId();
            final double susp = mutation.getSuspiciousnessValue();
            final PatchCategory caty = mutation.getCategorization();
            if (!grouped.containsKey(methodId)) {
                grouped.put(methodId, new Tuple(methodId, susp, caty));
            }
            else {
                if (susp > grouped.get(methodId).getSuspiciousnessValue()) {
                    grouped.get(methodId).setSuspiciousnessValue(susp);
                }
                if (caty.compareTo(grouped.get(methodId).getCategory()) >= 0) {
                    continue;
                }
                grouped.get(methodId).setCategory(caty);
            }
        }
        final List<Tuple> ranked = new LinkedList<Tuple>();
        ranked.addAll(grouped.values());
        if (profl) {
            Collections.sort(ranked, new ProFLComparator());
        }
        else {
            Collections.sort(ranked, new SBFLComparator());
        }
        return ranked;
    }
}
