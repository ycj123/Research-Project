// 
// Decompiled by Procyon v0.5.36
// 

package hidden.org.codehaus.plexus.interpolation;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSearchInterpolator implements Interpolator
{
    private Map existingAnswers;
    private List valueSources;
    private List postProcessors;
    private boolean reusePatterns;
    private boolean cacheAnswers;
    
    public StringSearchInterpolator() {
        this.existingAnswers = new HashMap();
        this.valueSources = new ArrayList();
        this.postProcessors = new ArrayList();
        this.reusePatterns = false;
        this.cacheAnswers = false;
    }
    
    public void addValueSource(final ValueSource valueSource) {
        this.valueSources.add(valueSource);
    }
    
    public void removeValuesSource(final ValueSource valueSource) {
        this.valueSources.remove(valueSource);
    }
    
    public void addPostProcessor(final InterpolationPostProcessor postProcessor) {
        this.postProcessors.add(postProcessor);
    }
    
    public void removePostProcessor(final InterpolationPostProcessor postProcessor) {
        this.postProcessors.remove(postProcessor);
    }
    
    public String interpolate(final String input, final String thisPrefixPattern) throws InterpolationException {
        throw new UnsupportedOperationException("Regular expressions are not supported in this Interpolator implementation.");
    }
    
    public String interpolate(final String input, final String thisPrefixPattern, final RecursionInterceptor recursionInterceptor) throws InterpolationException {
        throw new UnsupportedOperationException("Regular expressions are not supported in this Interpolator implementation.");
    }
    
    public String interpolate(final String input) throws InterpolationException {
        return this.interpolate(input, new SimpleRecursionInterceptor());
    }
    
    public String interpolate(final String input, final RecursionInterceptor recursionInterceptor) throws InterpolationException {
        return this.interpolate(input, recursionInterceptor, new HashSet());
    }
    
    private String interpolate(final String input, final RecursionInterceptor recursionInterceptor, final Set unresolvable) throws InterpolationException {
        final StringBuffer result = new StringBuffer(input.length() * 2);
        int startIdx = -1;
        int endIdx = -1;
        while ((startIdx = input.indexOf("${", endIdx + 1)) > -1) {
            result.append(input.substring(endIdx + 1, startIdx));
            endIdx = input.indexOf("}", startIdx + 1);
            if (endIdx < 0) {
                break;
            }
            final String wholeExpr = input.substring(startIdx, endIdx + 1);
            String realExpr = wholeExpr.substring(2, wholeExpr.length() - 1);
            boolean resolved = false;
            if (!unresolvable.contains(wholeExpr)) {
                if (realExpr.startsWith(".")) {
                    realExpr = realExpr.substring(1);
                }
                if (recursionInterceptor.hasRecursiveExpression(realExpr)) {
                    throw new InterpolationException("Detected the following recursive expression cycle: " + recursionInterceptor.getExpressionCycle(realExpr), wholeExpr);
                }
                recursionInterceptor.expressionResolutionStarted(realExpr);
                Object value = this.existingAnswers.get(realExpr);
                ValueSource vs;
                for (Iterator it = this.valueSources.iterator(); it.hasNext() && value == null; value = vs.getValue(realExpr)) {
                    vs = it.next();
                }
                if (value != null) {
                    value = this.interpolate(String.valueOf(value), recursionInterceptor, unresolvable);
                    if (this.postProcessors != null && !this.postProcessors.isEmpty()) {
                        for (final InterpolationPostProcessor postProcessor : this.postProcessors) {
                            final Object newVal = postProcessor.execute(realExpr, value);
                            if (newVal != null) {
                                value = newVal;
                                break;
                            }
                        }
                    }
                    result.append(String.valueOf(value));
                    resolved = true;
                }
                else {
                    unresolvable.add(wholeExpr);
                }
                recursionInterceptor.expressionResolutionFinished(realExpr);
            }
            if (resolved) {
                continue;
            }
            result.append(wholeExpr);
        }
        if (endIdx == -1 && startIdx > -1) {
            result.append(input.substring(startIdx, input.length()));
        }
        else if (endIdx < input.length()) {
            result.append(input.substring(endIdx + 1, input.length()));
        }
        return result.toString();
    }
    
    public List getFeedback() {
        final List messages = new ArrayList();
        for (final ValueSource vs : this.valueSources) {
            if (vs instanceof FeedbackEnabledValueSource) {
                final List feedback = ((FeedbackEnabledValueSource)vs).getFeedback();
                if (feedback == null || feedback.isEmpty()) {
                    continue;
                }
                messages.addAll(feedback);
            }
        }
        return messages;
    }
    
    public void clearFeedback() {
        for (final ValueSource vs : this.valueSources) {
            if (vs instanceof FeedbackEnabledValueSource) {
                ((FeedbackEnabledValueSource)vs).clearFeedback();
            }
        }
    }
    
    public boolean isReusePatterns() {
        return this.reusePatterns;
    }
    
    public void setReusePatterns(final boolean reusePatterns) {
        this.reusePatterns = reusePatterns;
    }
    
    public boolean isCacheAnswers() {
        return this.cacheAnswers;
    }
    
    public void setCacheAnswers(final boolean cacheAnswers) {
        this.cacheAnswers = cacheAnswers;
    }
    
    public void clearAnswers() {
        this.existingAnswers.clear();
    }
}
