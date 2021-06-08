// 
// Decompiled by Procyon v0.5.36
// 

package uk.org.lidalia.sysoutslf4j.system;

final class CallOrigin
{
    private final boolean printingStackTrace;
    private final String className;
    
    private CallOrigin(final boolean isStacktrace, final String className) {
        this.printingStackTrace = isStacktrace;
        this.className = className;
    }
    
    boolean isPrintingStackTrace() {
        return this.printingStackTrace;
    }
    
    String getClassName() {
        return this.className;
    }
    
    static CallOrigin getCallOrigin(final StackTraceElement[] stackTraceElements, final String libraryPackageName) {
        boolean isStackTrace = false;
        for (final StackTraceElement stackTraceElement : stackTraceElements) {
            String className = stackTraceElement.getClassName();
            if (className.equals(Throwable.class.getName())) {
                isStackTrace = true;
            }
            else if (outsideThisLibrary(className, libraryPackageName)) {
                className = getOuterClassName(className);
                return new CallOrigin(isStackTrace, className);
            }
        }
        throw new IllegalStateException("Nothing in the stack originated from outside package name " + libraryPackageName);
    }
    
    private static boolean outsideThisLibrary(final String className, final String libraryPackageName) {
        return !className.equals(Thread.class.getName()) && !className.startsWith(libraryPackageName);
    }
    
    private static String getOuterClassName(final String className) {
        final int startOfInnerClassName = className.indexOf(36);
        String outerClassName;
        if (startOfInnerClassName == -1) {
            outerClassName = className;
        }
        else {
            outerClassName = className.substring(0, startOfInnerClassName);
        }
        return outerClassName;
    }
}
