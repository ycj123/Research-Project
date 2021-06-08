// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven;

import java.util.LinkedList;
import org.pitest.util.Glob;
import org.pitest.functional.F;
import org.pitest.functional.predicate.Predicate;
import java.util.Collection;
import org.pitest.functional.FCollection;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import org.pitest.testapi.TestGroupConfig;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.pitest.mutationtest.config.ReportOptions;

public class SurefireConfigConverter
{
    public ReportOptions update(final ReportOptions option, final Xpp3Dom configuration) {
        if (configuration == null) {
            return option;
        }
        this.convertExcludes(option, configuration);
        this.convertGroups(option, configuration);
        return option;
    }
    
    private void convertGroups(final ReportOptions option, final Xpp3Dom configuration) {
        final TestGroupConfig existing = option.getGroupConfig();
        if (existing == null || (existing.getExcludedGroups().isEmpty() && existing.getIncludedGroups().isEmpty())) {
            final List<String> groups = this.extractStrings("groups", configuration);
            final List<String> excluded = this.extractStrings("excludedGroups", configuration);
            final TestGroupConfig gc = new TestGroupConfig(excluded, groups);
            option.setGroupConfig(gc);
        }
    }
    
    private List<String> extractStrings(final String element, final Xpp3Dom configuration) {
        final Xpp3Dom groups = configuration.getChild(element);
        if (groups != null) {
            final String[] parts = groups.getValue().split(" ");
            return Arrays.asList(parts);
        }
        return Collections.emptyList();
    }
    
    private void convertExcludes(final ReportOptions option, final Xpp3Dom configuration) {
        final List<Predicate<String>> excludes = FCollection.map(this.extract("excludes", configuration), this.filenameToClassFilter());
        excludes.addAll(option.getExcludedTestClasses());
        option.setExcludedTestClasses(excludes);
    }
    
    private F<String, Predicate<String>> filenameToClassFilter() {
        return new F<String, Predicate<String>>() {
            @Override
            public Predicate<String> apply(final String a) {
                return new Glob(a.replace(".java", "").replace("/", "."));
            }
        };
    }
    
    private List<String> extract(final String childname, final Xpp3Dom config) {
        final Xpp3Dom subelement = config.getChild(childname);
        if (subelement != null) {
            final List<String> result = new LinkedList<String>();
            final Xpp3Dom[] children2;
            final Xpp3Dom[] children = children2 = subelement.getChildren();
            for (final Xpp3Dom child : children2) {
                result.add(child.getValue());
            }
            return result;
        }
        return Collections.emptyList();
    }
}
