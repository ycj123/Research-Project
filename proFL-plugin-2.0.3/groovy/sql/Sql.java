// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import java.util.Collections;
import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Collection;
import java.util.regex.Matcher;
import org.codehaus.groovy.runtime.SqlGroovyMethods;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.util.Arrays;
import groovy.lang.GString;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.Properties;
import java.sql.SQLException;
import java.sql.DriverManager;
import groovy.lang.Tuple;
import java.sql.Statement;
import java.util.Map;
import groovy.lang.Closure;
import java.sql.Connection;
import javax.sql.DataSource;
import java.util.regex.Pattern;
import java.util.List;
import java.util.logging.Logger;

public class Sql
{
    protected static final Logger LOG;
    private static final List<Object> EMPTY_LIST;
    private static final Pattern NAMED_QUERY_PATTERN;
    private DataSource dataSource;
    private Connection useConnection;
    private int resultSetType;
    private int resultSetConcurrency;
    private int resultSetHoldability;
    private int updateCount;
    private Closure configureStatement;
    private boolean cacheConnection;
    private boolean cacheStatements;
    private boolean cacheNamedQueries;
    private boolean enableNamedQueries;
    private boolean withinBatch;
    private final Map<String, Statement> statementCache;
    private final Map<String, String> namedParamSqlCache;
    private final Map<String, List<Tuple>> namedParamIndexPropCache;
    public static final OutParameter ARRAY;
    public static final OutParameter BIGINT;
    public static final OutParameter BINARY;
    public static final OutParameter BIT;
    public static final OutParameter BLOB;
    public static final OutParameter BOOLEAN;
    public static final OutParameter CHAR;
    public static final OutParameter CLOB;
    public static final OutParameter DATALINK;
    public static final OutParameter DATE;
    public static final OutParameter DECIMAL;
    public static final OutParameter DISTINCT;
    public static final OutParameter DOUBLE;
    public static final OutParameter FLOAT;
    public static final OutParameter INTEGER;
    public static final OutParameter JAVA_OBJECT;
    public static final OutParameter LONGVARBINARY;
    public static final OutParameter LONGVARCHAR;
    public static final OutParameter NULL;
    public static final OutParameter NUMERIC;
    public static final OutParameter OTHER;
    public static final OutParameter REAL;
    public static final OutParameter REF;
    public static final OutParameter SMALLINT;
    public static final OutParameter STRUCT;
    public static final OutParameter TIME;
    public static final OutParameter TIMESTAMP;
    public static final OutParameter TINYINT;
    public static final OutParameter VARBINARY;
    public static final OutParameter VARCHAR;
    
    public static Sql newInstance(final String url) throws SQLException {
        final Connection connection = DriverManager.getConnection(url);
        return new Sql(connection);
    }
    
    public static Sql newInstance(final String url, final Properties properties) throws SQLException {
        final Connection connection = DriverManager.getConnection(url, properties);
        return new Sql(connection);
    }
    
    public static Sql newInstance(final String url, final Properties properties, final String driverClassName) throws SQLException, ClassNotFoundException {
        loadDriver(driverClassName);
        return newInstance(url, properties);
    }
    
    public static Sql newInstance(final String url, final String user, final String password) throws SQLException {
        final Connection connection = DriverManager.getConnection(url, user, password);
        return new Sql(connection);
    }
    
    public static Sql newInstance(final String url, final String user, final String password, final String driverClassName) throws SQLException, ClassNotFoundException {
        loadDriver(driverClassName);
        return newInstance(url, user, password);
    }
    
    public static Sql newInstance(final String url, final String driverClassName) throws SQLException, ClassNotFoundException {
        loadDriver(driverClassName);
        return newInstance(url);
    }
    
    public static Sql newInstance(final Map<String, Object> args) throws SQLException, ClassNotFoundException {
        if (args.containsKey("driverClassName") && args.containsKey("driver")) {
            throw new IllegalArgumentException("Only one of 'driverClassName' and 'driver' should be provided");
        }
        Object driverClassName = args.remove("driverClassName");
        if (driverClassName == null) {
            driverClassName = args.remove("driver");
        }
        if (driverClassName != null) {
            loadDriver(driverClassName.toString());
        }
        final Object url = args.remove("url");
        if (url == null) {
            throw new IllegalArgumentException("Argument 'url' is required");
        }
        final Properties props = args.remove("properties");
        if (props != null && args.containsKey("user")) {
            throw new IllegalArgumentException("Only one of 'properties' and 'user' should be supplied");
        }
        if (props != null && args.containsKey("password")) {
            throw new IllegalArgumentException("Only one of 'properties' and 'password' should be supplied");
        }
        if (args.containsKey("user") ^ args.containsKey("password")) {
            throw new IllegalArgumentException("Found one but not both of 'user' and 'password'");
        }
        Connection connection;
        if (props != null) {
            connection = DriverManager.getConnection(url.toString(), props);
        }
        else if (args.containsKey("user")) {
            final Object user = args.remove("user");
            final Object password = args.remove("password");
            connection = DriverManager.getConnection(url.toString(), (user == null) ? null : user.toString(), (password == null) ? null : password.toString());
        }
        else {
            connection = DriverManager.getConnection(url.toString());
        }
        final Sql result = (Sql)InvokerHelper.invokeConstructorOf(Sql.class, args);
        result.setConnection(connection);
        return result;
    }
    
    public int getResultSetType() {
        return this.resultSetType;
    }
    
    public void setResultSetType(final int resultSetType) {
        this.resultSetType = resultSetType;
    }
    
    public int getResultSetConcurrency() {
        return this.resultSetConcurrency;
    }
    
    public void setResultSetConcurrency(final int resultSetConcurrency) {
        this.resultSetConcurrency = resultSetConcurrency;
    }
    
    public int getResultSetHoldability() {
        return this.resultSetHoldability;
    }
    
    public void setResultSetHoldability(final int resultSetHoldability) {
        this.resultSetHoldability = resultSetHoldability;
    }
    
    public static void loadDriver(final String driverClassName) throws ClassNotFoundException {
        try {
            Class.forName(driverClassName);
        }
        catch (ClassNotFoundException e) {
            try {
                Thread.currentThread().getContextClassLoader().loadClass(driverClassName);
            }
            catch (ClassNotFoundException e2) {
                try {
                    Sql.class.getClassLoader().loadClass(driverClassName);
                }
                catch (ClassNotFoundException e3) {
                    throw e;
                }
            }
        }
    }
    
    public static InParameter ARRAY(final Object value) {
        return in(2003, value);
    }
    
    public static InParameter BIGINT(final Object value) {
        return in(-5, value);
    }
    
    public static InParameter BINARY(final Object value) {
        return in(-2, value);
    }
    
    public static InParameter BIT(final Object value) {
        return in(-7, value);
    }
    
    public static InParameter BLOB(final Object value) {
        return in(2004, value);
    }
    
    public static InParameter BOOLEAN(final Object value) {
        return in(16, value);
    }
    
    public static InParameter CHAR(final Object value) {
        return in(1, value);
    }
    
    public static InParameter CLOB(final Object value) {
        return in(2005, value);
    }
    
    public static InParameter DATALINK(final Object value) {
        return in(70, value);
    }
    
    public static InParameter DATE(final Object value) {
        return in(91, value);
    }
    
    public static InParameter DECIMAL(final Object value) {
        return in(3, value);
    }
    
    public static InParameter DISTINCT(final Object value) {
        return in(2001, value);
    }
    
    public static InParameter DOUBLE(final Object value) {
        return in(8, value);
    }
    
    public static InParameter FLOAT(final Object value) {
        return in(6, value);
    }
    
    public static InParameter INTEGER(final Object value) {
        return in(4, value);
    }
    
    public static InParameter JAVA_OBJECT(final Object value) {
        return in(2000, value);
    }
    
    public static InParameter LONGVARBINARY(final Object value) {
        return in(-4, value);
    }
    
    public static InParameter LONGVARCHAR(final Object value) {
        return in(-1, value);
    }
    
    public static InParameter NULL(final Object value) {
        return in(0, value);
    }
    
    public static InParameter NUMERIC(final Object value) {
        return in(2, value);
    }
    
    public static InParameter OTHER(final Object value) {
        return in(1111, value);
    }
    
    public static InParameter REAL(final Object value) {
        return in(7, value);
    }
    
    public static InParameter REF(final Object value) {
        return in(2006, value);
    }
    
    public static InParameter SMALLINT(final Object value) {
        return in(5, value);
    }
    
    public static InParameter STRUCT(final Object value) {
        return in(2002, value);
    }
    
    public static InParameter TIME(final Object value) {
        return in(92, value);
    }
    
    public static InParameter TIMESTAMP(final Object value) {
        return in(93, value);
    }
    
    public static InParameter TINYINT(final Object value) {
        return in(-6, value);
    }
    
    public static InParameter VARBINARY(final Object value) {
        return in(-3, value);
    }
    
    public static InParameter VARCHAR(final Object value) {
        return in(12, value);
    }
    
    public static InParameter in(final int type, final Object value) {
        return new InParameter() {
            public int getType() {
                return type;
            }
            
            public Object getValue() {
                return value;
            }
        };
    }
    
    public static OutParameter out(final int type) {
        return new OutParameter() {
            public int getType() {
                return type;
            }
        };
    }
    
    public static InOutParameter inout(final InParameter in) {
        return new InOutParameter() {
            public int getType() {
                return in.getType();
            }
            
            public Object getValue() {
                return in.getValue();
            }
        };
    }
    
    public static ResultSetOutParameter resultSet(final int type) {
        return new ResultSetOutParameter() {
            public int getType() {
                return type;
            }
        };
    }
    
    public static ExpandedVariable expand(final Object object) {
        return new ExpandedVariable() {
            public Object getObject() {
                return object;
            }
        };
    }
    
    public Sql(final DataSource dataSource) {
        this.resultSetType = 1003;
        this.resultSetConcurrency = 1007;
        this.resultSetHoldability = -1;
        this.updateCount = 0;
        this.cacheNamedQueries = true;
        this.enableNamedQueries = true;
        this.statementCache = new HashMap<String, Statement>();
        this.namedParamSqlCache = new HashMap<String, String>();
        this.namedParamIndexPropCache = new HashMap<String, List<Tuple>>();
        this.dataSource = dataSource;
    }
    
    public Sql(final Connection connection) {
        this.resultSetType = 1003;
        this.resultSetConcurrency = 1007;
        this.resultSetHoldability = -1;
        this.updateCount = 0;
        this.cacheNamedQueries = true;
        this.enableNamedQueries = true;
        this.statementCache = new HashMap<String, Statement>();
        this.namedParamSqlCache = new HashMap<String, String>();
        this.namedParamIndexPropCache = new HashMap<String, List<Tuple>>();
        if (connection == null) {
            throw new NullPointerException("Must specify a non-null Connection");
        }
        this.useConnection = connection;
    }
    
    public Sql(final Sql parent) {
        this.resultSetType = 1003;
        this.resultSetConcurrency = 1007;
        this.resultSetHoldability = -1;
        this.updateCount = 0;
        this.cacheNamedQueries = true;
        this.enableNamedQueries = true;
        this.statementCache = new HashMap<String, Statement>();
        this.namedParamSqlCache = new HashMap<String, String>();
        this.namedParamIndexPropCache = new HashMap<String, List<Tuple>>();
        this.dataSource = parent.dataSource;
        this.useConnection = parent.useConnection;
    }
    
    private Sql() {
        this.resultSetType = 1003;
        this.resultSetConcurrency = 1007;
        this.resultSetHoldability = -1;
        this.updateCount = 0;
        this.cacheNamedQueries = true;
        this.enableNamedQueries = true;
        this.statementCache = new HashMap<String, Statement>();
        this.namedParamSqlCache = new HashMap<String, String>();
        this.namedParamIndexPropCache = new HashMap<String, List<Tuple>>();
    }
    
    public DataSet dataSet(final String table) {
        return new DataSet(this, table);
    }
    
    public DataSet dataSet(final Class<?> type) {
        return new DataSet(this, type);
    }
    
    public void query(final String sql, final Closure closure) throws SQLException {
        final Connection connection = this.createConnection();
        final Statement statement = this.getStatement(connection, sql);
        ResultSet results = null;
        try {
            results = statement.executeQuery(sql);
            closure.call(results);
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement, results);
        }
    }
    
    public void query(final String sql, final List<Object> params, final Closure closure) throws SQLException {
        final Connection connection = this.createConnection();
        PreparedStatement statement = null;
        ResultSet results = null;
        try {
            statement = this.getPreparedStatement(connection, sql, params);
            results = statement.executeQuery();
            closure.call(results);
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement, results);
        }
    }
    
    public void query(final GString gstring, final Closure closure) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        this.query(sql, params, closure);
    }
    
    public void eachRow(final String sql, final Closure closure) throws SQLException {
        this.eachRow(sql, (Closure)null, closure);
    }
    
    public void eachRow(final String sql, final Closure metaClosure, final Closure rowClosure) throws SQLException {
        final Connection connection = this.createConnection();
        final Statement statement = this.getStatement(connection, sql);
        ResultSet results = null;
        try {
            results = statement.executeQuery(sql);
            if (metaClosure != null) {
                metaClosure.call(results.getMetaData());
            }
            final GroovyResultSet groovyRS = new GroovyResultSetProxy(results).getImpl();
            groovyRS.eachRow(rowClosure);
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement, results);
        }
    }
    
    public void eachRow(final String sql, final List<Object> params, final Closure metaClosure, final Closure closure) throws SQLException {
        final Connection connection = this.createConnection();
        PreparedStatement statement = null;
        ResultSet results = null;
        try {
            statement = this.getPreparedStatement(connection, sql, params);
            results = statement.executeQuery();
            if (metaClosure != null) {
                metaClosure.call(results.getMetaData());
            }
            final GroovyResultSet groovyRS = new GroovyResultSetProxy(results).getImpl();
            while (groovyRS.next()) {
                closure.call(groovyRS);
            }
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement, results);
        }
    }
    
    public void eachRow(final String sql, final List<Object> params, final Closure closure) throws SQLException {
        this.eachRow(sql, params, null, closure);
    }
    
    public void eachRow(final GString gstring, final Closure metaClosure, final Closure closure) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        this.eachRow(sql, params, metaClosure, closure);
    }
    
    public void eachRow(final GString gstring, final Closure closure) throws SQLException {
        this.eachRow(gstring, null, closure);
    }
    
    public List<GroovyRowResult> rows(final String sql) throws SQLException {
        return this.rows(sql, (Closure)null);
    }
    
    public List<GroovyRowResult> rows(final String sql, final Closure metaClosure) throws SQLException {
        final AbstractQueryCommand command = this.createQueryCommand(sql);
        ResultSet rs = null;
        try {
            rs = command.execute();
            final List<GroovyRowResult> result = this.asList(sql, rs, metaClosure);
            rs = null;
            return result;
        }
        finally {
            command.closeResources(rs);
        }
    }
    
    public List<GroovyRowResult> rows(final String sql, final List<Object> params) throws SQLException {
        return this.rows(sql, params, null);
    }
    
    public List<GroovyRowResult> rows(final String sql, final Object[] params) throws SQLException {
        return this.rows(sql, Arrays.asList(params), null);
    }
    
    public List<GroovyRowResult> rows(final String sql, final List<Object> params, final Closure metaClosure) throws SQLException {
        final AbstractQueryCommand command = this.createPreparedQueryCommand(sql, params);
        try {
            return this.asList(sql, command.execute(), metaClosure);
        }
        finally {
            command.closeResources();
        }
    }
    
    public List<GroovyRowResult> rows(final GString gstring) throws SQLException {
        return this.rows(gstring, null);
    }
    
    public List<GroovyRowResult> rows(final GString gstring, final Closure metaClosure) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        return this.rows(sql, params, metaClosure);
    }
    
    public Object firstRow(final String sql) throws SQLException {
        final List<GroovyRowResult> rows = this.rows(sql);
        if (rows.isEmpty()) {
            return null;
        }
        return rows.get(0);
    }
    
    public Object firstRow(final GString gstring) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        return this.firstRow(sql, params);
    }
    
    public Object firstRow(final String sql, final List<Object> params) throws SQLException {
        final List<GroovyRowResult> rows = this.rows(sql, params);
        if (rows.isEmpty()) {
            return null;
        }
        return rows.get(0);
    }
    
    public Object firstRow(final String sql, final Object[] params) throws SQLException {
        return this.firstRow(sql, Arrays.asList(params));
    }
    
    public boolean execute(final String sql) throws SQLException {
        final Connection connection = this.createConnection();
        Statement statement = null;
        try {
            statement = this.getStatement(connection, sql);
            final boolean isResultSet = statement.execute(sql);
            this.updateCount = statement.getUpdateCount();
            return isResultSet;
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public boolean execute(final String sql, final List<Object> params) throws SQLException {
        final Connection connection = this.createConnection();
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(connection, sql, params);
            final boolean isResultSet = statement.execute();
            this.updateCount = statement.getUpdateCount();
            return isResultSet;
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public boolean execute(final String sql, final Object[] params) throws SQLException {
        return this.execute(sql, Arrays.asList(params));
    }
    
    public boolean execute(final GString gstring) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        return this.execute(sql, params);
    }
    
    public List<List<Object>> executeInsert(final String sql) throws SQLException {
        final Connection connection = this.createConnection();
        Statement statement = null;
        try {
            statement = this.getStatement(connection, sql);
            this.updateCount = statement.executeUpdate(sql, 1);
            final ResultSet keys = statement.getGeneratedKeys();
            return this.calculateKeys(keys);
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public List<List<Object>> executeInsert(final String sql, final List<Object> params) throws SQLException {
        final Connection connection = this.createConnection();
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(connection, sql, params, 1);
            this.updateCount = statement.executeUpdate();
            final ResultSet keys = statement.getGeneratedKeys();
            return this.calculateKeys(keys);
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public List<List<Object>> executeInsert(final String sql, final Object[] params) throws SQLException {
        return this.executeInsert(sql, Arrays.asList(params));
    }
    
    public List<List<Object>> executeInsert(final GString gstring) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        return this.executeInsert(sql, params);
    }
    
    public int executeUpdate(final String sql) throws SQLException {
        final Connection connection = this.createConnection();
        Statement statement = null;
        try {
            statement = this.getStatement(connection, sql);
            return this.updateCount = statement.executeUpdate(sql);
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public int executeUpdate(final String sql, final List<Object> params) throws SQLException {
        final Connection connection = this.createConnection();
        PreparedStatement statement = null;
        try {
            statement = this.getPreparedStatement(connection, sql, params);
            return this.updateCount = statement.executeUpdate();
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public int executeUpdate(final String sql, final Object[] params) throws SQLException {
        return this.executeUpdate(sql, Arrays.asList(params));
    }
    
    public int executeUpdate(final GString gstring) throws SQLException {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        return this.executeUpdate(sql, params);
    }
    
    public int call(final String sql) throws Exception {
        return this.call(sql, Sql.EMPTY_LIST);
    }
    
    public int call(final GString gstring) throws Exception {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        return this.call(sql, params);
    }
    
    public int call(final String sql, final List<Object> params) throws Exception {
        final Connection connection = this.createConnection();
        final CallableStatement statement = connection.prepareCall(sql);
        try {
            Sql.LOG.fine(sql + " | " + params);
            this.setParameters(params, statement);
            this.configure(statement);
            return statement.executeUpdate();
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
        }
    }
    
    public int call(final String sql, final Object[] params) throws Exception {
        return this.call(sql, Arrays.asList(params));
    }
    
    public void call(final String sql, final List<Object> params, final Closure closure) throws Exception {
        final Connection connection = this.createConnection();
        final CallableStatement statement = connection.prepareCall(sql);
        final List<GroovyResultSet> resultSetResources = new ArrayList<GroovyResultSet>();
        try {
            Sql.LOG.fine(sql + " | " + params);
            this.setParameters(params, statement);
            statement.execute();
            final List<Object> results = new ArrayList<Object>();
            int indx = 0;
            int inouts = 0;
            for (final Object value : params) {
                if (value instanceof OutParameter) {
                    if (value instanceof ResultSetOutParameter) {
                        final GroovyResultSet resultSet = CallResultSet.getImpl(statement, indx);
                        resultSetResources.add(resultSet);
                        results.add(resultSet);
                    }
                    else {
                        final Object o = statement.getObject(indx + 1);
                        if (o instanceof ResultSet) {
                            final GroovyResultSet resultSet2 = new GroovyResultSetProxy((ResultSet)o).getImpl();
                            results.add(resultSet2);
                            resultSetResources.add(resultSet2);
                        }
                        else {
                            results.add(o);
                        }
                    }
                    ++inouts;
                }
                ++indx;
            }
            closure.call(results.toArray(new Object[inouts]));
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to execute: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            this.closeResources(connection, statement);
            for (final GroovyResultSet rs : resultSetResources) {
                this.closeResources(null, null, rs);
            }
        }
    }
    
    public void call(final GString gstring, final Closure closure) throws Exception {
        final List<Object> params = this.getParameters(gstring);
        final String sql = this.asSql(gstring, params);
        this.call(sql, params, closure);
    }
    
    public void close() {
        this.namedParamSqlCache.clear();
        this.namedParamIndexPropCache.clear();
        this.clearStatementCache();
        if (this.useConnection != null) {
            try {
                this.useConnection.close();
            }
            catch (SQLException e) {
                Sql.LOG.finest("Caught exception closing connection: " + e.getMessage());
            }
        }
    }
    
    public DataSource getDataSource() {
        return this.dataSource;
    }
    
    public void commit() throws SQLException {
        if (this.useConnection == null) {
            Sql.LOG.info("Commit operation not supported when using datasets unless using withTransaction or cacheConnection - attempt to commit ignored");
            return;
        }
        try {
            this.useConnection.commit();
        }
        catch (SQLException e) {
            Sql.LOG.warning("Caught exception committing connection: " + e.getMessage());
            throw e;
        }
    }
    
    public void rollback() throws SQLException {
        if (this.useConnection == null) {
            Sql.LOG.info("Rollback operation not supported when using datasets unless using withTransaction or cacheConnection - attempt to rollback ignored");
            return;
        }
        try {
            this.useConnection.rollback();
        }
        catch (SQLException e) {
            Sql.LOG.warning("Caught exception rolling back connection: " + e.getMessage());
            throw e;
        }
    }
    
    public int getUpdateCount() {
        return this.updateCount;
    }
    
    public Connection getConnection() {
        return this.useConnection;
    }
    
    private void setConnection(final Connection connection) {
        this.useConnection = connection;
    }
    
    public void withStatement(final Closure configureStatement) {
        this.configureStatement = configureStatement;
    }
    
    public synchronized void setCacheStatements(final boolean cacheStatements) {
        if (!(this.cacheStatements = cacheStatements)) {
            this.clearStatementCache();
        }
    }
    
    public boolean isCacheStatements() {
        return this.cacheStatements;
    }
    
    public synchronized void cacheConnection(final Closure closure) throws SQLException {
        final boolean savedCacheConnection = this.cacheConnection;
        this.cacheConnection = true;
        Connection connection = null;
        try {
            connection = this.createConnection();
            this.callClosurePossiblyWithConnection(closure, connection);
        }
        finally {
            this.cacheConnection = false;
            this.closeResources(connection, null);
            this.cacheConnection = savedCacheConnection;
            if (this.dataSource != null && !this.cacheConnection) {
                this.useConnection = null;
            }
        }
    }
    
    public synchronized void withTransaction(final Closure closure) throws SQLException {
        final boolean savedCacheConnection = this.cacheConnection;
        this.cacheConnection = true;
        Connection connection = null;
        boolean savedAutoCommit = true;
        try {
            connection = this.createConnection();
            savedAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            this.callClosurePossiblyWithConnection(closure, connection);
            connection.commit();
        }
        catch (SQLException e) {
            this.handleError(connection, e);
            throw e;
        }
        catch (RuntimeException e2) {
            this.handleError(connection, e2);
            throw e2;
        }
        catch (Error e3) {
            this.handleError(connection, e3);
            throw e3;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(savedAutoCommit);
            }
            this.cacheConnection = false;
            this.closeResources(connection, null);
            this.cacheConnection = savedCacheConnection;
            if (this.dataSource != null && !this.cacheConnection) {
                this.useConnection = null;
            }
        }
    }
    
    public boolean isWithinBatch() {
        return this.withinBatch;
    }
    
    public synchronized int[] withBatch(final Closure closure) throws SQLException {
        return this.withBatch(0, closure);
    }
    
    public synchronized int[] withBatch(final int batchSize, final Closure closure) throws SQLException {
        final boolean savedCacheConnection = this.cacheConnection;
        this.cacheConnection = true;
        Connection connection = null;
        BatchingStatementWrapper statement = null;
        boolean savedAutoCommit = true;
        final boolean savedWithinBatch = this.withinBatch;
        try {
            this.withinBatch = true;
            connection = this.createConnection();
            savedAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            statement = new BatchingStatementWrapper(this.createStatement(connection), batchSize, Sql.LOG, connection);
            closure.call(statement);
            final int[] result = statement.executeBatch();
            connection.commit();
            return result;
        }
        catch (SQLException e) {
            this.handleError(connection, e);
            throw e;
        }
        catch (RuntimeException e2) {
            this.handleError(connection, e2);
            throw e2;
        }
        catch (Error e3) {
            this.handleError(connection, e3);
            throw e3;
        }
        finally {
            if (connection != null) {
                connection.setAutoCommit(savedAutoCommit);
            }
            this.cacheConnection = false;
            this.closeResources(statement);
            this.closeResources(connection);
            this.cacheConnection = savedCacheConnection;
            this.withinBatch = savedWithinBatch;
            if (this.dataSource != null && !this.cacheConnection) {
                this.useConnection = null;
            }
        }
    }
    
    public synchronized void cacheStatements(final Closure closure) throws SQLException {
        final boolean savedCacheStatements = this.cacheStatements;
        this.cacheStatements = true;
        Connection connection = null;
        try {
            connection = this.createConnection();
            this.callClosurePossiblyWithConnection(closure, connection);
        }
        finally {
            this.cacheStatements = false;
            this.closeResources(connection, null);
            this.cacheStatements = savedCacheStatements;
        }
    }
    
    protected final ResultSet executeQuery(final String sql) throws SQLException {
        final AbstractQueryCommand command = this.createQueryCommand(sql);
        ResultSet rs = null;
        try {
            rs = command.execute();
        }
        finally {
            command.closeResources();
        }
        return rs;
    }
    
    protected final ResultSet executePreparedQuery(final String sql, final List<Object> params) throws SQLException {
        final AbstractQueryCommand command = this.createPreparedQueryCommand(sql, params);
        ResultSet rs = null;
        try {
            rs = command.execute();
        }
        finally {
            command.closeResources();
        }
        return rs;
    }
    
    protected List<GroovyRowResult> asList(final String sql, final ResultSet rs) throws SQLException {
        return this.asList(sql, rs, null);
    }
    
    protected List<GroovyRowResult> asList(final String sql, final ResultSet rs, final Closure metaClosure) throws SQLException {
        final List<GroovyRowResult> results = new ArrayList<GroovyRowResult>();
        try {
            if (metaClosure != null) {
                metaClosure.call(rs.getMetaData());
            }
            while (rs.next()) {
                results.add(SqlGroovyMethods.toRowResult(rs));
            }
            return results;
        }
        catch (SQLException e) {
            Sql.LOG.warning("Failed to retrieve row from ResultSet for: " + sql + " because: " + e.getMessage());
            throw e;
        }
        finally {
            rs.close();
        }
    }
    
    protected String asSql(final GString gstring, final List<Object> values) {
        final String[] strings = gstring.getStrings();
        if (strings.length <= 0) {
            throw new IllegalArgumentException("No SQL specified in GString: " + (Object)gstring);
        }
        boolean nulls = false;
        final StringBuffer buffer = new StringBuffer();
        boolean warned = false;
        final Iterator<Object> iter = values.iterator();
        for (int i = 0; i < strings.length; ++i) {
            final String text = strings[i];
            if (text != null) {
                buffer.append(text);
            }
            if (iter.hasNext()) {
                final Object value = iter.next();
                if (value != null) {
                    if (value instanceof ExpandedVariable) {
                        buffer.append(((ExpandedVariable)value).getObject());
                        iter.remove();
                    }
                    else {
                        boolean validBinding = true;
                        if (i < strings.length - 1) {
                            final String nextText = strings[i + 1];
                            if ((text.endsWith("\"") || text.endsWith("'")) && (nextText.startsWith("'") || nextText.startsWith("\""))) {
                                if (!warned) {
                                    Sql.LOG.warning("In Groovy SQL please do not use quotes around dynamic expressions (which start with $) as this means we cannot use a JDBC PreparedStatement and so is a security hole. Groovy has worked around your mistake but the security hole is still there. The expression so far is: " + buffer.toString() + "?" + nextText);
                                    warned = true;
                                }
                                buffer.append(value);
                                iter.remove();
                                validBinding = false;
                            }
                        }
                        if (validBinding) {
                            buffer.append("?");
                        }
                    }
                }
                else {
                    nulls = true;
                    iter.remove();
                    buffer.append("?'\"?");
                }
            }
        }
        String sql = buffer.toString();
        if (nulls) {
            sql = this.nullify(sql);
        }
        return sql;
    }
    
    protected String nullify(String sql) {
        final int firstWhere = this.findWhereKeyword(sql);
        if (firstWhere >= 0) {
            final Pattern[] patterns = { Pattern.compile("(?is)^(.{" + firstWhere + "}.*?)!=\\s{0,1}(\\s*)\\?'\"\\?(.*)"), Pattern.compile("(?is)^(.{" + firstWhere + "}.*?)<>\\s{0,1}(\\s*)\\?'\"\\?(.*)"), Pattern.compile("(?is)^(.{" + firstWhere + "}.*?[^<>])=\\s{0,1}(\\s*)\\?'\"\\?(.*)") };
            final String[] replacements = { "$1 is not $2null$3", "$1 is not $2null$3", "$1 is $2null$3" };
            for (int i = 0; i < patterns.length; ++i) {
                for (Matcher matcher = patterns[i].matcher(sql); matcher.matches(); matcher = patterns[i].matcher(sql)) {
                    sql = matcher.replaceAll(replacements[i]);
                }
            }
        }
        return sql.replaceAll("\\?'\"\\?", "null");
    }
    
    protected int findWhereKeyword(final String sql) {
        final char[] chars = sql.toLowerCase().toCharArray();
        final char[] whereChars = "where".toCharArray();
        int i = 0;
        boolean inString = false;
        int inWhere = 0;
        while (i < chars.length) {
            switch (chars[i]) {
                case '\'': {
                    inString = !inString;
                    break;
                }
                default: {
                    if (!inString && chars[i] == whereChars[inWhere] && ++inWhere == whereChars.length) {
                        return i;
                    }
                    break;
                }
            }
            ++i;
        }
        return -1;
    }
    
    protected List<Object> getParameters(final GString gstring) {
        return new ArrayList<Object>(Arrays.asList(gstring.getValues()));
    }
    
    protected void setParameters(final List<Object> params, final PreparedStatement statement) throws SQLException {
        int i = 1;
        for (final Object value : params) {
            this.setObject(statement, i++, value);
        }
    }
    
    protected void setObject(final PreparedStatement statement, final int i, final Object value) throws SQLException {
        if (value instanceof InParameter || value instanceof OutParameter) {
            if (value instanceof InParameter) {
                final InParameter in = (InParameter)value;
                final Object val = in.getValue();
                if (null == val) {
                    statement.setNull(i, in.getType());
                }
                else {
                    statement.setObject(i, val, in.getType());
                }
            }
            if (!(value instanceof OutParameter)) {
                return;
            }
            try {
                final OutParameter out = (OutParameter)value;
                ((CallableStatement)statement).registerOutParameter(i, out.getType());
                return;
            }
            catch (ClassCastException e) {
                throw new SQLException("Cannot register out parameter.");
            }
        }
        statement.setObject(i, value);
    }
    
    protected Connection createConnection() throws SQLException {
        if ((this.cacheStatements || this.cacheConnection) && this.useConnection != null) {
            return this.useConnection;
        }
        if (this.dataSource != null) {
            Connection con;
            try {
                con = AccessController.doPrivileged((PrivilegedExceptionAction<Connection>)new PrivilegedExceptionAction<Connection>() {
                    public Connection run() throws SQLException {
                        return Sql.this.dataSource.getConnection();
                    }
                });
            }
            catch (PrivilegedActionException pae) {
                final Exception e = pae.getException();
                if (e instanceof SQLException) {
                    throw (SQLException)e;
                }
                throw (RuntimeException)e;
            }
            if (this.cacheStatements || this.cacheConnection) {
                this.useConnection = con;
            }
            return con;
        }
        return this.useConnection;
    }
    
    protected void closeResources(final Connection connection, final Statement statement, final ResultSet results) {
        if (results != null) {
            try {
                results.close();
            }
            catch (SQLException e) {
                Sql.LOG.finest("Caught exception closing resultSet: " + e.getMessage() + " - continuing");
            }
        }
        this.closeResources(connection, statement);
    }
    
    protected void closeResources(final Connection connection, final Statement statement) {
        if (this.cacheStatements) {
            return;
        }
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
                Sql.LOG.finest("Caught exception closing statement: " + e.getMessage() + " - continuing");
            }
        }
        this.closeResources(connection);
    }
    
    private void closeResources(final BatchingStatementWrapper statement) {
        if (this.cacheStatements) {
            return;
        }
        if (statement != null) {
            try {
                statement.close();
            }
            catch (SQLException e) {
                Sql.LOG.finest("Caught exception closing statement: " + e.getMessage() + " - continuing");
            }
        }
    }
    
    protected void closeResources(final Connection connection) {
        if (this.cacheConnection) {
            return;
        }
        if (connection != null && this.dataSource != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                Sql.LOG.finest("Caught exception closing connection: " + e.getMessage() + " - continuing");
            }
        }
    }
    
    protected void configure(final Statement statement) {
        final Closure configureStatement = this.configureStatement;
        if (configureStatement != null) {
            configureStatement.call(statement);
        }
    }
    
    private List<List<Object>> calculateKeys(final ResultSet keys) throws SQLException {
        final List<List<Object>> autoKeys = new ArrayList<List<Object>>();
        final int count = keys.getMetaData().getColumnCount();
        while (keys.next()) {
            final List<Object> rowKeys = new ArrayList<Object>(count);
            for (int i = 1; i <= count; ++i) {
                rowKeys.add(keys.getObject(i));
            }
            autoKeys.add(rowKeys);
        }
        return autoKeys;
    }
    
    private Statement createStatement(final Connection connection) throws SQLException {
        if (this.resultSetHoldability == -1) {
            return connection.createStatement(this.resultSetType, this.resultSetConcurrency);
        }
        return connection.createStatement(this.resultSetType, this.resultSetConcurrency, this.resultSetHoldability);
    }
    
    private void handleError(final Connection connection, final Throwable t) throws SQLException {
        if (connection != null) {
            Sql.LOG.warning("Rolling back due to: " + t.getMessage());
            connection.rollback();
        }
    }
    
    private void callClosurePossiblyWithConnection(final Closure closure, final Connection connection) {
        if (closure.getMaximumNumberOfParameters() == 1) {
            closure.call(connection);
        }
        else {
            closure.call();
        }
    }
    
    private void clearStatementCache() {
        final Statement[] statements;
        synchronized (this.statementCache) {
            if (this.statementCache.isEmpty()) {
                return;
            }
            statements = new Statement[this.statementCache.size()];
            this.statementCache.values().toArray(statements);
            this.statementCache.clear();
        }
        for (final Statement s : statements) {
            try {
                s.close();
            }
            catch (Exception e) {
                Sql.LOG.info("Failed to close statement. Already closed? Exception message: " + e.getMessage());
            }
        }
    }
    
    private Statement getAbstractStatement(final AbstractStatementCommand cmd, final Connection connection, final String sql) throws SQLException {
        Statement stmt;
        if (this.cacheStatements) {
            synchronized (this.statementCache) {
                stmt = this.statementCache.get(sql);
                if (stmt == null) {
                    stmt = cmd.execute(connection, sql);
                    this.statementCache.put(sql, stmt);
                }
            }
        }
        else {
            stmt = cmd.execute(connection, sql);
        }
        return stmt;
    }
    
    private Statement getStatement(final Connection connection, final String sql) throws SQLException {
        Sql.LOG.fine(sql);
        final Statement stmt = this.getAbstractStatement(new CreateStatementCommand(), connection, sql);
        this.configure(stmt);
        return stmt;
    }
    
    private PreparedStatement getPreparedStatement(final Connection connection, final String sql, final List<Object> params, final int returnGeneratedKeys) throws SQLException {
        final SqlWithParams updated = this.checkForNamedParams(sql, params);
        Sql.LOG.fine(updated.getSql() + " | " + updated.getParams());
        final PreparedStatement statement = (PreparedStatement)this.getAbstractStatement(new CreatePreparedStatementCommand(returnGeneratedKeys), connection, updated.getSql());
        this.setParameters(updated.getParams(), statement);
        this.configure(statement);
        return statement;
    }
    
    public SqlWithParams checkForNamedParams(final String sql, final List<Object> params) {
        if (!this.enableNamedQueries || !Sql.NAMED_QUERY_PATTERN.matcher(sql).find()) {
            return new SqlWithParams(sql, params);
        }
        String newSql;
        List<Tuple> indexPropList;
        if (this.cacheNamedQueries && this.namedParamSqlCache.containsKey(sql)) {
            newSql = this.namedParamSqlCache.get(sql);
            indexPropList = this.namedParamIndexPropCache.get(sql);
        }
        else {
            indexPropList = new ArrayList<Tuple>();
            final StringBuilder sb = new StringBuilder();
            StringBuilder currentChunk = new StringBuilder();
            final char[] chars = sql.toCharArray();
            int i = 0;
            boolean inString = false;
            while (i < chars.length) {
                switch (chars[i]) {
                    case '\'': {
                        inString = !inString;
                        if (inString) {
                            sb.append(this.adaptForNamedParams(currentChunk.toString(), indexPropList));
                            currentChunk = new StringBuilder();
                            currentChunk.append(chars[i]);
                            break;
                        }
                        currentChunk.append(chars[i]);
                        sb.append((CharSequence)currentChunk);
                        currentChunk = new StringBuilder();
                        break;
                    }
                    default: {
                        currentChunk.append(chars[i]);
                        break;
                    }
                }
                ++i;
            }
            if (inString) {
                throw new IllegalStateException("Failed to process query. Unterminated ' character?");
            }
            sb.append(this.adaptForNamedParams(currentChunk.toString(), indexPropList));
            newSql = sb.toString();
            this.namedParamSqlCache.put(sql, newSql);
            this.namedParamIndexPropCache.put(sql, indexPropList);
        }
        if (sql.equals(newSql)) {
            return new SqlWithParams(sql, params);
        }
        final List<Object> updatedParams = new ArrayList<Object>();
        for (final Tuple tuple : indexPropList) {
            final int index = (int)tuple.get(0);
            final String prop = (String)tuple.get(1);
            if (index < 0 || index >= params.size()) {
                throw new IllegalArgumentException("Invalid index " + index + " should be in range 1.." + params.size());
            }
            updatedParams.add(InvokerHelper.getProperty(params.get(index), prop));
        }
        return new SqlWithParams(newSql, updatedParams);
    }
    
    private String adaptForNamedParams(final String sql, final List<Tuple> indexPropList) {
        final StringBuilder newSql = new StringBuilder();
        int txtIndex = 0;
        final Matcher matcher = Sql.NAMED_QUERY_PATTERN.matcher(sql);
        while (matcher.find()) {
            newSql.append(sql.substring(txtIndex, matcher.start())).append('?');
            final String indexStr = matcher.group(1);
            final int index = (indexStr == null || indexStr.length() == 0) ? 0 : (new Integer(matcher.group(1)) - 1);
            final String prop = matcher.group(2);
            indexPropList.add(new Tuple(new Object[] { index, prop }));
            txtIndex = matcher.end();
        }
        newSql.append(sql.substring(txtIndex));
        return newSql.toString();
    }
    
    private PreparedStatement getPreparedStatement(final Connection connection, final String sql, final List<Object> params) throws SQLException {
        return this.getPreparedStatement(connection, sql, params, 0);
    }
    
    public boolean isCacheNamedQueries() {
        return this.cacheNamedQueries;
    }
    
    public void setCacheNamedQueries(final boolean cacheNamedQueries) {
        this.cacheNamedQueries = cacheNamedQueries;
    }
    
    public boolean isEnableNamedQueries() {
        return this.enableNamedQueries;
    }
    
    public void setEnableNamedQueries(final boolean enableNamedQueries) {
        this.enableNamedQueries = enableNamedQueries;
    }
    
    protected AbstractQueryCommand createQueryCommand(final String sql) {
        return new QueryCommand(sql);
    }
    
    protected AbstractQueryCommand createPreparedQueryCommand(final String sql, final List<Object> queryParams) {
        return new PreparedQueryCommand(sql, queryParams);
    }
    
    protected void setInternalConnection(final Connection conn) {
    }
    
    static {
        LOG = Logger.getLogger(Sql.class.getName());
        EMPTY_LIST = Collections.emptyList();
        NAMED_QUERY_PATTERN = Pattern.compile("(?::|\\?(\\d?)\\.)(\\w+)");
        ARRAY = new OutParameter() {
            public int getType() {
                return 2003;
            }
        };
        BIGINT = new OutParameter() {
            public int getType() {
                return -5;
            }
        };
        BINARY = new OutParameter() {
            public int getType() {
                return -2;
            }
        };
        BIT = new OutParameter() {
            public int getType() {
                return -7;
            }
        };
        BLOB = new OutParameter() {
            public int getType() {
                return 2004;
            }
        };
        BOOLEAN = new OutParameter() {
            public int getType() {
                return 16;
            }
        };
        CHAR = new OutParameter() {
            public int getType() {
                return 1;
            }
        };
        CLOB = new OutParameter() {
            public int getType() {
                return 2005;
            }
        };
        DATALINK = new OutParameter() {
            public int getType() {
                return 70;
            }
        };
        DATE = new OutParameter() {
            public int getType() {
                return 91;
            }
        };
        DECIMAL = new OutParameter() {
            public int getType() {
                return 3;
            }
        };
        DISTINCT = new OutParameter() {
            public int getType() {
                return 2001;
            }
        };
        DOUBLE = new OutParameter() {
            public int getType() {
                return 8;
            }
        };
        FLOAT = new OutParameter() {
            public int getType() {
                return 6;
            }
        };
        INTEGER = new OutParameter() {
            public int getType() {
                return 4;
            }
        };
        JAVA_OBJECT = new OutParameter() {
            public int getType() {
                return 2000;
            }
        };
        LONGVARBINARY = new OutParameter() {
            public int getType() {
                return -4;
            }
        };
        LONGVARCHAR = new OutParameter() {
            public int getType() {
                return -1;
            }
        };
        NULL = new OutParameter() {
            public int getType() {
                return 0;
            }
        };
        NUMERIC = new OutParameter() {
            public int getType() {
                return 2;
            }
        };
        OTHER = new OutParameter() {
            public int getType() {
                return 1111;
            }
        };
        REAL = new OutParameter() {
            public int getType() {
                return 7;
            }
        };
        REF = new OutParameter() {
            public int getType() {
                return 2006;
            }
        };
        SMALLINT = new OutParameter() {
            public int getType() {
                return 5;
            }
        };
        STRUCT = new OutParameter() {
            public int getType() {
                return 2002;
            }
        };
        TIME = new OutParameter() {
            public int getType() {
                return 92;
            }
        };
        TIMESTAMP = new OutParameter() {
            public int getType() {
                return 93;
            }
        };
        TINYINT = new OutParameter() {
            public int getType() {
                return -6;
            }
        };
        VARBINARY = new OutParameter() {
            public int getType() {
                return -3;
            }
        };
        VARCHAR = new OutParameter() {
            public int getType() {
                return 12;
            }
        };
    }
    
    private abstract class AbstractStatementCommand
    {
        abstract Statement execute(final Connection p0, final String p1) throws SQLException;
    }
    
    private class CreatePreparedStatementCommand extends AbstractStatementCommand
    {
        private final int returnGeneratedKeys;
        
        CreatePreparedStatementCommand(final int returnGeneratedKeys) {
            this.returnGeneratedKeys = returnGeneratedKeys;
        }
        
        @Override
        PreparedStatement execute(final Connection connection, final String sql) throws SQLException {
            if (this.returnGeneratedKeys != 0) {
                return connection.prepareStatement(sql, this.returnGeneratedKeys);
            }
            if (this.appearsLikeStoredProc(sql)) {
                return connection.prepareCall(sql);
            }
            return connection.prepareStatement(sql);
        }
        
        boolean appearsLikeStoredProc(final String sql) {
            return sql.matches("\\s*[{]?\\s*[?]?\\s*[=]?\\s*[cC][aA][lL][lL].*");
        }
    }
    
    private class CreateStatementCommand extends AbstractStatementCommand
    {
        @Override
        Statement execute(final Connection conn, final String sql) throws SQLException {
            return Sql.this.createStatement(conn);
        }
    }
    
    protected abstract class AbstractQueryCommand
    {
        protected final String sql;
        protected Statement statement;
        private Connection connection;
        
        AbstractQueryCommand(final String sql) {
            this.sql = sql;
        }
        
        final ResultSet execute() throws SQLException {
            this.connection = Sql.this.createConnection();
            Sql.this.setInternalConnection(this.connection);
            this.statement = null;
            try {
                final ResultSet result = this.runQuery(this.connection);
                assert null != this.statement;
                return result;
            }
            catch (SQLException e) {
                Sql.LOG.warning("Failed to execute: " + this.sql + " because: " + e.getMessage());
                this.closeResources();
                this.connection = null;
                this.statement = null;
                throw e;
            }
        }
        
        public final void closeResources() {
            Sql.this.closeResources(this.connection, this.statement);
        }
        
        public final void closeResources(final ResultSet rs) {
            Sql.this.closeResources(this.connection, this.statement, rs);
        }
        
        protected abstract ResultSet runQuery(final Connection p0) throws SQLException;
    }
    
    protected final class PreparedQueryCommand extends AbstractQueryCommand
    {
        private List<Object> params;
        
        PreparedQueryCommand(final String sql, final List<Object> queryParams) {
            super(sql);
            this.params = queryParams;
        }
        
        @Override
        protected ResultSet runQuery(final Connection connection) throws SQLException {
            final PreparedStatement s = Sql.this.getPreparedStatement(connection, this.sql, this.params);
            this.statement = s;
            return s.executeQuery();
        }
    }
    
    protected final class QueryCommand extends AbstractQueryCommand
    {
        QueryCommand(final String sql) {
            super(sql);
        }
        
        @Override
        protected ResultSet runQuery(final Connection connection) throws SQLException {
            this.statement = Sql.this.getStatement(connection, this.sql);
            return this.statement.executeQuery(this.sql);
        }
    }
}
