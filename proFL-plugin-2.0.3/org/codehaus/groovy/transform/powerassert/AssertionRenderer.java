// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.transform.powerassert;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class AssertionRenderer
{
    private final String text;
    private final ValueRecorder recorder;
    private final List<StringBuilder> lines;
    private final List<Integer> startColumns;
    
    private AssertionRenderer(final String text, final ValueRecorder recorder) {
        this.lines = new ArrayList<StringBuilder>();
        this.startColumns = new ArrayList<Integer>();
        if (text.contains("\n")) {
            throw new IllegalArgumentException("source text may not contain line breaks");
        }
        this.text = text;
        this.recorder = recorder;
    }
    
    public static String render(final String text, final ValueRecorder recorder) {
        return new AssertionRenderer(text, recorder).render();
    }
    
    private String render() {
        this.renderText();
        this.sortValues();
        this.renderValues();
        return this.linesToString();
    }
    
    private void renderText() {
        this.lines.add(new StringBuilder(this.text));
        this.startColumns.add(0);
        this.lines.add(new StringBuilder());
        this.startColumns.add(0);
    }
    
    private void sortValues() {
        Collections.sort(this.recorder.getValues(), new Comparator<Value>() {
            public int compare(final Value v1, final Value v2) {
                return v2.getColumn() - v1.getColumn();
            }
        });
    }
    
    private void renderValues() {
        final List<Value> values = this.recorder.getValues();
    Label_0355:
        for (int i = 0; i < values.size(); ++i) {
            final Value value = values.get(i);
            final int startColumn = value.getColumn();
            if (startColumn >= 1) {
                final Value next = (i + 1 < values.size()) ? values.get(i + 1) : null;
                if (next == null || next.getColumn() != value.getColumn()) {
                    final String str = valueToString(value.getValue());
                    if (str != null) {
                        final String[] strs = str.split("\r\n|\r|\n");
                        final int endColumn = (strs.length == 1) ? (value.getColumn() + str.length()) : Integer.MAX_VALUE;
                        for (int j = 1; j < this.lines.size(); ++j) {
                            if (endColumn < this.startColumns.get(j)) {
                                placeString(this.lines.get(j), str, startColumn);
                                this.startColumns.set(j, startColumn);
                                continue Label_0355;
                            }
                            placeString(this.lines.get(j), "|", startColumn);
                            if (j > 1) {
                                this.startColumns.set(j, startColumn + 1);
                            }
                        }
                        for (final String s : strs) {
                            final StringBuilder newLine = new StringBuilder();
                            this.lines.add(newLine);
                            placeString(newLine, s, startColumn);
                            this.startColumns.add(startColumn);
                        }
                    }
                }
            }
        }
    }
    
    private String linesToString() {
        final StringBuilder firstLine = this.lines.get(0);
        for (int i = 1; i < this.lines.size(); ++i) {
            firstLine.append('\n').append(this.lines.get(i).toString());
        }
        return firstLine.toString();
    }
    
    private static void placeString(final StringBuilder line, final String str, final int column) {
        while (line.length() < column) {
            line.append(' ');
        }
        line.replace(column - 1, column - 1 + str.length(), str);
    }
    
    private static String valueToString(final Object value) {
        String toString;
        try {
            toString = DefaultGroovyMethods.toString(value);
        }
        catch (Exception e) {
            return String.format("%s (toString() threw %s)", javaLangObjectToString(value), e.getClass().getName());
        }
        if (toString == null) {
            return String.format("%s (toString() == null)", javaLangObjectToString(value));
        }
        if (!toString.equals("")) {
            return toString;
        }
        if (hasStringLikeType(value)) {
            return "\"\"";
        }
        return String.format("%s (toString() == \"\")", javaLangObjectToString(value));
    }
    
    private static boolean hasStringLikeType(final Object value) {
        final Class<?> clazz = value.getClass();
        return clazz == String.class || clazz == StringBuffer.class || clazz == StringBuilder.class;
    }
    
    private static String javaLangObjectToString(final Object value) {
        final String hash = Integer.toHexString(System.identityHashCode(value));
        return value.getClass().getName() + "@" + hash;
    }
}
