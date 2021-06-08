// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.profl.core;

import java.util.Comparator;

class ProFLComparator implements Comparator<Tuple>
{
    @Override
    public int compare(final Tuple t1, final Tuple t2) {
        final PatchCategory caty1 = t1.getCategory();
        final PatchCategory caty2 = t2.getCategory();
        if (caty1.compareTo(caty2) != 0) {
            return caty1.compareTo(caty2);
        }
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
}
