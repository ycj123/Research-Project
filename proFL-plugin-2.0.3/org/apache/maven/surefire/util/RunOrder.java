// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.surefire.util;

import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class RunOrder
{
    public static final RunOrder ALPHABETICAL;
    public static final RunOrder FILESYSTEM;
    public static final RunOrder HOURLY;
    public static final RunOrder RANDOM;
    public static final RunOrder REVERSE_ALPHABETICAL;
    public static final RunOrder BALANCED;
    public static final RunOrder FAILEDFIRST;
    public static final RunOrder[] DEFAULT;
    private final String name;
    
    public static RunOrder[] valueOfMulti(final String values) {
        final List<RunOrder> result = new ArrayList<RunOrder>();
        if (values != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(values, ",");
            while (stringTokenizer.hasMoreTokens()) {
                result.add(valueOf(stringTokenizer.nextToken()));
            }
        }
        return result.toArray(new RunOrder[result.size()]);
    }
    
    public static RunOrder valueOf(final String name) {
        if (name == null) {
            return null;
        }
        final RunOrder[] arr$;
        final RunOrder[] runOrders = arr$ = values();
        for (final RunOrder runOrder : arr$) {
            if (runOrder.matches(name)) {
                return runOrder;
            }
        }
        final StringBuffer errorMessage = createMessageForMissingRunOrder(name);
        throw new IllegalArgumentException(errorMessage.toString());
    }
    
    private static StringBuffer createMessageForMissingRunOrder(final String name) {
        final RunOrder[] runOrders = values();
        final StringBuffer message = new StringBuffer();
        message.append("There's no RunOrder with the name ");
        message.append(name);
        message.append(". Please use one of the following RunOrders: ");
        for (int i = 0; i < runOrders.length; ++i) {
            if (i != 0) {
                message.append(", ");
            }
            message.append(runOrders[i]);
        }
        message.append(".");
        return message;
    }
    
    private static RunOrder[] values() {
        return new RunOrder[] { RunOrder.ALPHABETICAL, RunOrder.FILESYSTEM, RunOrder.HOURLY, RunOrder.RANDOM, RunOrder.REVERSE_ALPHABETICAL, RunOrder.BALANCED, RunOrder.FAILEDFIRST };
    }
    
    public static String asString(final RunOrder[] runOrder) {
        final StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < runOrder.length; ++i) {
            stringBuffer.append(runOrder[i].name);
            if (i < runOrder.length - 1) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }
    
    private RunOrder(final String name) {
        this.name = name;
    }
    
    private boolean matches(final String anotherName) {
        return this.name.equalsIgnoreCase(anotherName);
    }
    
    public String name() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
    static {
        ALPHABETICAL = new RunOrder("alphabetical");
        FILESYSTEM = new RunOrder("filesystem");
        HOURLY = new RunOrder("hourly");
        RANDOM = new RunOrder("random");
        REVERSE_ALPHABETICAL = new RunOrder("reversealphabetical");
        BALANCED = new RunOrder("balanced");
        FAILEDFIRST = new RunOrder("failedfirst");
        DEFAULT = new RunOrder[] { RunOrder.FILESYSTEM };
    }
}
