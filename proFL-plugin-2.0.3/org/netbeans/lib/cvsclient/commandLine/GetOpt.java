// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient.commandLine;

public class GetOpt
{
    private String[] theArgs;
    private int argCount;
    private String optString;
    public boolean optErr;
    public static final int optEOF = -1;
    private int optIndex;
    private String optArg;
    private int optPosition;
    
    public GetOpt(final String[] theArgs, final String optString) {
        this.theArgs = null;
        this.argCount = 0;
        this.optString = null;
        this.optErr = false;
        this.optIndex = 0;
        this.optArg = null;
        this.optPosition = 1;
        this.theArgs = theArgs;
        this.argCount = this.theArgs.length;
        this.optString = optString;
    }
    
    public int processArg(final String s, final int n) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            if (this.optErr) {
                System.err.println("processArg cannot process " + s + " as an integer");
            }
            return n;
        }
        return int1;
    }
    
    public int tryArg(final int i, final int n) {
        int processArg;
        try {
            processArg = this.processArg(this.theArgs[i], n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.optErr) {
                System.err.println("tryArg: no theArgs[" + i + "]");
            }
            return n;
        }
        return processArg;
    }
    
    public long processArg(final String s, final long n) {
        long long1;
        try {
            long1 = Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            if (this.optErr) {
                System.err.println("processArg cannot process " + s + " as a long");
            }
            return n;
        }
        return long1;
    }
    
    public long tryArg(final int i, final long n) {
        long processArg;
        try {
            processArg = this.processArg(this.theArgs[i], n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.optErr) {
                System.err.println("tryArg: no theArgs[" + i + "]");
            }
            return n;
        }
        return processArg;
    }
    
    public double processArg(final String s, final double n) {
        double doubleValue;
        try {
            doubleValue = Double.valueOf(s);
        }
        catch (NumberFormatException ex) {
            if (this.optErr) {
                System.err.println("processArg cannot process " + s + " as a double");
            }
            return n;
        }
        return doubleValue;
    }
    
    public double tryArg(final int i, final double n) {
        double processArg;
        try {
            processArg = this.processArg(this.theArgs[i], n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.optErr) {
                System.err.println("tryArg: no theArgs[" + i + "]");
            }
            return n;
        }
        return processArg;
    }
    
    public float processArg(final String s, final float n) {
        float floatValue;
        try {
            floatValue = Float.valueOf(s);
        }
        catch (NumberFormatException ex) {
            if (this.optErr) {
                System.err.println("processArg cannot process " + s + " as a float");
            }
            return n;
        }
        return floatValue;
    }
    
    public float tryArg(final int i, final float n) {
        float processArg;
        try {
            processArg = this.processArg(this.theArgs[i], n);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.optErr) {
                System.err.println("tryArg: no theArgs[" + i + "]");
            }
            return n;
        }
        return processArg;
    }
    
    public boolean processArg(final String s, final boolean b) {
        return Boolean.valueOf(s);
    }
    
    public boolean tryArg(final int i, final boolean b) {
        boolean processArg;
        try {
            processArg = this.processArg(this.theArgs[i], b);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.optErr) {
                System.err.println("tryArg: no theArgs[" + i + "]");
            }
            return b;
        }
        return processArg;
    }
    
    public String tryArg(final int i, final String s) {
        String s2;
        try {
            s2 = this.theArgs[i];
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (this.optErr) {
                System.err.println("tryArg: no theArgs[" + i + "]");
            }
            return s;
        }
        return s2;
    }
    
    private static void writeError(final String str, final char c) {
        System.err.println("GetOpt: " + str + " -- " + c);
    }
    
    public int optIndexGet() {
        return this.optIndex;
    }
    
    public void optIndexSet(final int optIndex) {
        this.optIndex = optIndex;
    }
    
    public String optArgGet() {
        return this.optArg;
    }
    
    public int getopt() {
        this.optArg = null;
        if (this.theArgs == null || this.optString == null) {
            return -1;
        }
        if (this.optIndex < 0 || this.optIndex >= this.argCount) {
            return -1;
        }
        final String s = this.theArgs[this.optIndex];
        final int length = s.length();
        if (length <= 1 || s.charAt(0) != '-') {
            return -1;
        }
        if (s.equals("--")) {
            ++this.optIndex;
            return -1;
        }
        char char1 = s.charAt(this.optPosition);
        final int index = this.optString.indexOf(char1);
        if (index == -1 || char1 == ':') {
            if (this.optErr) {
                writeError("illegal option", char1);
            }
            char1 = '?';
        }
        else if (index < this.optString.length() - 1 && this.optString.charAt(index + 1) == ':') {
            if (this.optPosition != length - 1) {
                this.optArg = s.substring(this.optPosition + 1);
                this.optPosition = length - 1;
            }
            else {
                ++this.optIndex;
                if (this.optIndex < this.argCount && (this.theArgs[this.optIndex].charAt(0) != '-' || (this.theArgs[this.optIndex].length() >= 2 && (this.optString.indexOf(this.theArgs[this.optIndex].charAt(1)) == -1 || this.theArgs[this.optIndex].charAt(1) == ':')))) {
                    this.optArg = this.theArgs[this.optIndex];
                }
                else {
                    if (this.optErr) {
                        writeError("option requires an argument", char1);
                    }
                    this.optArg = null;
                    char1 = ':';
                }
            }
        }
        ++this.optPosition;
        if (this.optPosition >= length) {
            ++this.optIndex;
            this.optPosition = 1;
        }
        return char1;
    }
    
    public static void main(final String[] array) {
        final GetOpt getOpt = new GetOpt(array, "Uab:f:h:w:");
        getOpt.optErr = true;
        boolean b = false;
        int i = 0;
        boolean processArg = false;
        String optArgGet = "out";
        int processArg2 = 80;
        double processArg3 = 1.0;
        int getopt;
        while ((getopt = getOpt.getopt()) != -1) {
            if ((char)getopt == 'U') {
                b = true;
            }
            else if ((char)getopt == 'a') {
                ++i;
            }
            else if ((char)getopt == 'b') {
                processArg = getOpt.processArg(getOpt.optArgGet(), processArg);
            }
            else if ((char)getopt == 'f') {
                optArgGet = getOpt.optArgGet();
            }
            else if ((char)getopt == 'h') {
                processArg3 = getOpt.processArg(getOpt.optArgGet(), processArg3);
            }
            else if ((char)getopt == 'w') {
                processArg2 = getOpt.processArg(getOpt.optArgGet(), processArg2);
            }
            else {
                System.exit(1);
            }
        }
        if (b) {
            System.out.println("Usage: -a -b bool -f file -h height -w width");
            System.exit(0);
        }
        System.out.println("These are all the command line arguments before processing with GetOpt:");
        for (int j = 0; j < array.length; ++j) {
            System.out.print(" " + array[j]);
        }
        System.out.println();
        System.out.println("-U " + b);
        System.out.println("-a " + i);
        System.out.println("-b " + processArg);
        System.out.println("-f " + optArgGet);
        System.out.println("-h " + processArg3);
        System.out.println("-w " + processArg2);
        for (int k = getOpt.optIndexGet(); k < array.length; ++k) {
            System.out.println("normal argument " + k + " is " + array[k]);
        }
    }
}
