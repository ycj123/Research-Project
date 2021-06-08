// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core;

public enum SuspStrategyImpl implements SuspStrategy
{
    OCHIAI {
        @Override
        public double computeSusp(final int ef, final int ep, final int nf, final int np) {
            final double denom = Math.sqrt((ef + ep) * (ef + nf));
            return (denom > 0.0) ? (ef / denom) : 0.0;
        }
    }, 
    TARANTULA {
        @Override
        public double computeSusp(final int ef, final int ep, final int nf, final int np) {
            final double denom1 = (ef + nf == 0) ? 0.0 : (ef / (double)(ef + nf));
            final double denom2 = (ep + np == 0) ? 0.0 : (ep / (double)(ep + np));
            final double denom3 = denom1 + denom2;
            return (denom3 > 0.0) ? (denom1 / denom3) : 0.0;
        }
    };
}
