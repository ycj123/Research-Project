// 
// Decompiled by Procyon v0.5.36
// 

package org.netbeans.lib.cvsclient;

import org.netbeans.lib.cvsclient.connection.ConnectionFactory;
import java.io.IOException;
import java.io.File;
import java.util.StringTokenizer;
import java.util.Properties;

public class CVSRoot
{
    public static final String METHOD_LOCAL = "local";
    public static final String METHOD_FORK = "fork";
    public static final String METHOD_SERVER = "server";
    public static final String METHOD_PSERVER = "pserver";
    public static final String METHOD_EXT = "ext";
    private String method;
    private String username;
    private String password;
    private String hostname;
    private int port;
    private String repository;
    
    public static CVSRoot parse(final String s) throws IllegalArgumentException {
        return new CVSRoot(s);
    }
    
    public static CVSRoot parse(final Properties properties) throws IllegalArgumentException {
        return new CVSRoot(properties);
    }
    
    protected CVSRoot(final Properties properties) throws IllegalArgumentException {
        final String property = properties.getProperty("method");
        if (property != null) {
            this.method = property.intern();
        }
        this.hostname = properties.getProperty("hostname");
        if (this.hostname.length() == 0) {
            this.hostname = null;
        }
        if (this.hostname != null) {
            this.username = properties.getProperty("username");
            this.password = properties.getProperty("password");
            try {
                final int int1 = Integer.parseInt(properties.getProperty("port"));
                if (int1 <= 0) {
                    throw new IllegalArgumentException("The port is not a positive number.");
                }
                this.port = int1;
            }
            catch (NumberFormatException ex) {
                throw new IllegalArgumentException("The port is not a number: '" + properties.getProperty("port") + "'.");
            }
        }
        final String property2 = properties.getProperty("repository");
        if (property2 == null) {
            throw new IllegalArgumentException("Repository is obligatory.");
        }
        this.repository = property2;
    }
    
    protected CVSRoot(final String str) throws IllegalArgumentException {
        int index = 0;
        boolean b;
        if (!str.startsWith(":")) {
            b = str.startsWith("/");
            if (!b) {
                if (str.indexOf(58) == 1 && str.indexOf(92) == 2) {
                    this.method = "local";
                    this.repository = str;
                    return;
                }
                if (str.indexOf(58) < 0) {
                    if (str.indexOf(47) < 0) {
                        throw new IllegalArgumentException("CVSROOT must be an absolute pathname.");
                    }
                    this.method = "server";
                }
                else {
                    this.method = "ext";
                }
                index = 0;
            }
            else {
                this.method = "local";
            }
        }
        else {
            index = str.indexOf(58, 1);
            if (index < 0) {
                throw new IllegalArgumentException("The connection method does not end with ':'.");
            }
            int endIndex = index;
            final int index2 = str.indexOf(";", 1);
            if (index2 != -1 && index2 < index) {
                endIndex = index2;
                final StringTokenizer stringTokenizer = new StringTokenizer(str.substring(index2 + 1, index), "=;");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (!stringTokenizer.hasMoreTokens()) {
                        throw new IllegalArgumentException("Undefined " + nextToken + " option value.");
                    }
                    final String nextToken2 = stringTokenizer.nextToken();
                    if ("hostname".equals(nextToken)) {
                        this.hostname = nextToken2;
                    }
                    else if ("username".equals(nextToken)) {
                        this.username = nextToken2;
                    }
                    else if ("password".equals(nextToken)) {
                        this.password = nextToken2;
                    }
                    if ("port".equals(nextToken)) {
                        try {
                            this.port = Integer.parseInt(nextToken2, 10);
                            continue;
                        }
                        catch (NumberFormatException ex) {
                            throw new IllegalArgumentException("Port option must be number.");
                        }
                        break;
                    }
                }
            }
            this.method = str.substring(1, endIndex).intern();
            if ("extssh".equals(this.method)) {
                this.method = "ext";
            }
            ++index;
            b = this.isLocalMethod(this.method);
        }
        if (b) {
            this.repository = str.substring(index);
        }
        else {
            int index3 = str.indexOf(64, index);
            if (index3 < 0) {
                index3 = index;
            }
            int index4 = -1;
            final int index5 = str.indexOf(58, index3);
            String hostname;
            if (index5 == -1) {
                index4 = str.indexOf(47, index3);
                if (index4 < 0) {
                    throw new IllegalArgumentException("cvsroot " + str + " is malformed, host name is missing.");
                }
                hostname = str.substring(index, index4);
            }
            else {
                hostname = str.substring(index, index5);
            }
            final int index6 = hostname.indexOf(64);
            if (index6 == -1) {
                if (hostname.length() > 0) {
                    this.hostname = hostname;
                }
            }
            else {
                final String substring = hostname.substring(0, index6);
                if (substring.length() > 0) {
                    final int index7 = substring.indexOf(58);
                    if (index7 != -1) {
                        this.username = substring.substring(0, index7);
                        this.password = substring.substring(index7 + 1);
                    }
                    else {
                        this.username = substring;
                    }
                }
                this.hostname = hostname.substring(index6 + 1);
            }
            if (this.hostname == null || this.hostname.length() == 0) {
                throw new IllegalArgumentException("Didn't specify hostname in CVSROOT '" + str + "'.");
            }
            if (index5 > 0) {
                String repository = str.substring(index5 + 1);
                int n = 0;
                int port = 0;
                char char1;
                while (repository.length() > n && Character.isDigit(char1 = repository.charAt(n))) {
                    port = port * 10 + Character.digit(char1, 10);
                    ++n;
                }
                this.port = port;
                if (n > 0) {
                    repository = repository.substring(n);
                }
                this.repository = repository;
            }
            else {
                this.port = 0;
                this.repository = str.substring(index4);
            }
        }
    }
    
    public boolean isLocal() {
        return this.hostname == null;
    }
    
    public String toString() {
        if (this.hostname != null) {
            final StringBuffer sb = new StringBuffer();
            if (this.method != null) {
                sb.append(':');
                sb.append(this.method);
                sb.append(':');
            }
            if (this.username != null) {
                sb.append(this.username);
                sb.append('@');
            }
            sb.append(this.hostname);
            sb.append(':');
            if (this.port > 0) {
                sb.append(this.port);
            }
            sb.append(this.repository);
            return sb.toString();
        }
        if (this.method == null) {
            return this.repository;
        }
        return ":" + this.method + ":" + this.repository;
    }
    
    public int getCompatibilityLevel(final CVSRoot cvsRoot) {
        if (this.equals(cvsRoot)) {
            return 0;
        }
        final boolean sameRepository = this.isSameRepository(cvsRoot);
        final boolean sameHost = this.isSameHost(cvsRoot);
        final boolean sameMethod = this.isSameMethod(cvsRoot);
        final boolean samePort = this.isSamePort(cvsRoot);
        final boolean sameUser = this.isSameUser(cvsRoot);
        if (sameRepository && sameHost && sameMethod && samePort && sameUser) {
            return 1;
        }
        if (sameRepository && sameHost && sameMethod) {
            return 2;
        }
        if (sameRepository && sameHost) {
            return 3;
        }
        return -1;
    }
    
    private boolean isSameRepository(final CVSRoot cvsRoot) {
        if (this.repository.equals(cvsRoot.repository)) {
            return true;
        }
        try {
            return new File(this.repository).getCanonicalFile().equals(new File(cvsRoot.repository).getCanonicalFile());
        }
        catch (IOException ex) {
            return false;
        }
    }
    
    private boolean isSameHost(final CVSRoot cvsRoot) {
        final String hostName = cvsRoot.getHostName();
        return this.hostname == hostName || (this.hostname != null && this.hostname.equalsIgnoreCase(hostName));
    }
    
    private boolean isSameMethod(final CVSRoot cvsRoot) {
        if (this.method == null) {
            return cvsRoot.getMethod() == null;
        }
        return this.method.equals(cvsRoot.getMethod());
    }
    
    private boolean isSamePort(final CVSRoot cvsRoot) {
        if (this.isLocal() == cvsRoot.isLocal()) {
            if (this.isLocal()) {
                return true;
            }
            if (this.port == cvsRoot.getPort()) {
                return true;
            }
            try {
                return ConnectionFactory.getConnection(this).getPort() == ConnectionFactory.getConnection(cvsRoot).getPort();
            }
            catch (IllegalArgumentException ex) {
                return false;
            }
        }
        return false;
    }
    
    private boolean isSameUser(final CVSRoot cvsRoot) {
        final String userName = cvsRoot.getUserName();
        return userName == this.getUserName() || (userName != null && userName.equals(this.getUserName()));
    }
    
    public boolean equals(final Object o) {
        return o instanceof CVSRoot && this.toString().equals(((CVSRoot)o).toString());
    }
    
    public int hashCode() {
        return this.toString().hashCode();
    }
    
    public String getMethod() {
        return this.method;
    }
    
    protected void setMethod(String s) {
        if (s != null) {
            this.method = s.intern();
        }
        else {
            s = null;
        }
        if (this.isLocalMethod(s)) {
            this.username = null;
            this.password = null;
            this.hostname = null;
            this.port = 0;
        }
        else if (this.hostname == null) {
            throw new IllegalArgumentException("Hostname must not be null when setting a remote method.");
        }
    }
    
    private boolean isLocalMethod(final String s) {
        return "local" == s || "fork" == s;
    }
    
    public String getUserName() {
        return this.username;
    }
    
    protected void setUserName(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    protected void setPassword(final String password) {
        this.password = password;
    }
    
    public String getHostName() {
        return this.hostname;
    }
    
    protected void setHostName(final String hostname) {
        this.hostname = hostname;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public void setPort(final int port) {
        this.port = port;
    }
    
    public String getRepository() {
        return this.repository;
    }
    
    protected void setRepository(final String repository) {
        if (repository == null) {
            throw new IllegalArgumentException("The repository must not be null.");
        }
        this.repository = repository;
    }
}
