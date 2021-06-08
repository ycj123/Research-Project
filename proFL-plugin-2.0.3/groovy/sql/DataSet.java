// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.GroovyCodeVisitor;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.CodeVisitorSupport;
import groovy.lang.GroovyRuntimeException;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import groovy.lang.Closure;

public class DataSet extends Sql
{
    private Closure where;
    private Closure sort;
    private boolean reversed;
    private DataSet parent;
    private String table;
    private SqlWhereVisitor visitor;
    private SqlOrderByVisitor sortVisitor;
    private String sql;
    private List params;
    private Sql delegate;
    
    public DataSet(final Sql sql, final Class type) {
        super(sql);
        this.reversed = false;
        this.delegate = sql;
        String table = type.getName();
        final int idx = table.lastIndexOf(46);
        if (idx > 0) {
            table = table.substring(idx + 1);
        }
        this.table = table.toLowerCase();
    }
    
    public DataSet(final Sql sql, final String table) {
        super(sql);
        this.reversed = false;
        this.delegate = sql;
        this.table = table;
    }
    
    private DataSet(final DataSet parent, final Closure where) {
        super(parent);
        this.reversed = false;
        this.delegate = parent.delegate;
        this.table = parent.table;
        this.parent = parent;
        this.where = where;
    }
    
    private DataSet(final DataSet parent, final Closure where, final Closure sort) {
        super(parent);
        this.reversed = false;
        this.delegate = parent.delegate;
        this.table = parent.table;
        this.parent = parent;
        this.where = where;
        this.sort = sort;
    }
    
    private DataSet(final DataSet parent) {
        super(parent);
        this.reversed = false;
        this.delegate = parent.delegate;
        this.table = parent.table;
        this.parent = parent;
        this.reversed = true;
    }
    
    @Override
    protected Connection createConnection() throws SQLException {
        return this.delegate.createConnection();
    }
    
    @Override
    protected void closeResources(final Connection connection, final Statement statement, final ResultSet results) {
        this.delegate.closeResources(connection, statement, results);
    }
    
    @Override
    protected void closeResources(final Connection connection, final Statement statement) {
        this.delegate.closeResources(connection, statement);
    }
    
    @Override
    public void cacheConnection(final Closure closure) throws SQLException {
        this.delegate.cacheConnection(closure);
    }
    
    @Override
    public void withTransaction(final Closure closure) throws SQLException {
        this.delegate.withTransaction(closure);
    }
    
    @Override
    public void commit() throws SQLException {
        this.delegate.commit();
    }
    
    @Override
    public void rollback() throws SQLException {
        this.delegate.rollback();
    }
    
    public void add(final Map<String, Object> map) throws SQLException {
        final StringBuffer buffer = new StringBuffer("insert into ");
        buffer.append(this.table);
        buffer.append(" (");
        final StringBuffer paramBuffer = new StringBuffer();
        boolean first = true;
        for (final String column : map.keySet()) {
            if (first) {
                first = false;
                paramBuffer.append("?");
            }
            else {
                buffer.append(", ");
                paramBuffer.append(", ?");
            }
            buffer.append(column);
        }
        buffer.append(") values (");
        buffer.append(paramBuffer.toString());
        buffer.append(")");
        final int answer = this.executeUpdate(buffer.toString(), new ArrayList<Object>(map.values()));
        if (answer != 1) {
            DataSet.LOG.warning("Should have updated 1 row not " + answer + " when trying to add: " + map);
        }
    }
    
    public DataSet findAll(final Closure where) {
        return new DataSet(this, where);
    }
    
    public DataSet sort(final Closure sort) {
        return new DataSet(this, null, sort);
    }
    
    public DataSet reverse() {
        if (this.sort == null) {
            throw new GroovyRuntimeException("reverse() only allowed immediately after a sort()");
        }
        return new DataSet(this);
    }
    
    public void each(final Closure closure) throws SQLException {
        this.eachRow(this.getSql(), this.getParameters(), closure);
    }
    
    private String getSqlWhere() {
        String whereClaus = "";
        String parentClaus = "";
        if (this.parent != null) {
            parentClaus = this.parent.getSqlWhere();
        }
        if (this.where != null) {
            whereClaus += this.getSqlWhereVisitor().getWhere();
        }
        if (parentClaus.length() == 0) {
            return whereClaus;
        }
        if (whereClaus.length() == 0) {
            return parentClaus;
        }
        return parentClaus + " and " + whereClaus;
    }
    
    private String getSqlOrderBy() {
        String sortByClaus = "";
        String parentClaus = "";
        if (this.parent != null) {
            parentClaus = this.parent.getSqlOrderBy();
        }
        if (this.reversed && parentClaus.length() > 0) {
            parentClaus += " DESC";
        }
        if (this.sort != null) {
            sortByClaus += this.getSqlOrderByVisitor().getOrderBy();
        }
        if (parentClaus.length() == 0) {
            return sortByClaus;
        }
        if (sortByClaus.length() == 0) {
            return parentClaus;
        }
        return parentClaus + ", " + sortByClaus;
    }
    
    public String getSql() {
        if (this.sql == null) {
            this.sql = "select * from " + this.table;
            final String whereClaus = this.getSqlWhere();
            if (whereClaus.length() > 0) {
                this.sql = this.sql + " where " + whereClaus;
            }
            final String orerByClaus = this.getSqlOrderBy();
            if (orerByClaus.length() > 0) {
                this.sql = this.sql + " order by " + orerByClaus;
            }
        }
        return this.sql;
    }
    
    public List getParameters() {
        if (this.params == null) {
            this.params = new ArrayList();
            if (this.parent != null) {
                this.params.addAll(this.parent.getParameters());
            }
            this.params.addAll(this.getSqlWhereVisitor().getParameters());
        }
        return this.params;
    }
    
    protected SqlWhereVisitor getSqlWhereVisitor() {
        if (this.visitor == null) {
            this.visitor = new SqlWhereVisitor();
            this.visit(this.where, this.visitor);
        }
        return this.visitor;
    }
    
    protected SqlOrderByVisitor getSqlOrderByVisitor() {
        if (this.sortVisitor == null) {
            this.sortVisitor = new SqlOrderByVisitor();
            this.visit(this.sort, this.sortVisitor);
        }
        return this.sortVisitor;
    }
    
    private void visit(final Closure closure, final CodeVisitorSupport visitor) {
        if (closure != null) {
            final ClassNode classNode = closure.getMetaClass().getClassNode();
            if (classNode == null) {
                throw new GroovyRuntimeException("Could not find the ClassNode for MetaClass: " + closure.getMetaClass());
            }
            final List methods = classNode.getDeclaredMethods("doCall");
            if (!methods.isEmpty()) {
                final MethodNode method = methods.get(0);
                if (method != null) {
                    final org.codehaus.groovy.ast.stmt.Statement statement = method.getCode();
                    if (statement != null) {
                        statement.visit(visitor);
                    }
                }
            }
        }
    }
    
    public DataSet createView(final Closure criteria) {
        return new DataSet(this, criteria);
    }
    
    public List rows() throws SQLException {
        return this.rows(this.getSql(), this.getParameters());
    }
    
    public Object firstRow() throws SQLException {
        final List rows = this.rows();
        if (rows.isEmpty()) {
            return null;
        }
        return rows.get(0);
    }
}
