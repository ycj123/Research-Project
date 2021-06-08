// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import org.apache.velocity.util.ExceptionUtils;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.io.BufferedInputStream;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.InputStream;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.util.StringUtils;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import javax.sql.DataSource;
import javax.naming.InitialContext;

public class DataSourceResourceLoader extends ResourceLoader
{
    private String dataSourceName;
    private String tableName;
    private String keyColumn;
    private String templateColumn;
    private String timestampColumn;
    private InitialContext ctx;
    private DataSource dataSource;
    
    public void init(final ExtendedProperties configuration) {
        this.dataSourceName = StringUtils.nullTrim(configuration.getString("resource.datasource"));
        this.tableName = StringUtils.nullTrim(configuration.getString("resource.table"));
        this.keyColumn = StringUtils.nullTrim(configuration.getString("resource.keycolumn"));
        this.templateColumn = StringUtils.nullTrim(configuration.getString("resource.templatecolumn"));
        this.timestampColumn = StringUtils.nullTrim(configuration.getString("resource.timestampcolumn"));
        if (this.dataSource != null) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("DataSourceResourceLoader: using dataSource instance with table \"" + this.tableName + "\"");
                this.log.debug("DataSourceResourceLoader: using columns \"" + this.keyColumn + "\", \"" + this.templateColumn + "\" and \"" + this.timestampColumn + "\"");
            }
            this.log.trace("DataSourceResourceLoader initialized.");
        }
        else if (this.dataSourceName != null) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("DataSourceResourceLoader: using \"" + this.dataSourceName + "\" datasource with table \"" + this.tableName + "\"");
                this.log.debug("DataSourceResourceLoader: using columns \"" + this.keyColumn + "\", \"" + this.templateColumn + "\" and \"" + this.timestampColumn + "\"");
            }
            this.log.trace("DataSourceResourceLoader initialized.");
        }
        else {
            this.log.warn("DataSourceResourceLoader not properly initialized. No DataSource was identified.");
        }
    }
    
    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public boolean isSourceModified(final Resource resource) {
        return resource.getLastModified() != this.readLastModified(resource, "checking timestamp");
    }
    
    public long getLastModified(final Resource resource) {
        return this.readLastModified(resource, "getting timestamp");
    }
    
    public synchronized InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        if (org.mudebug.prapr.reloc.commons.lang.StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("DataSourceResourceLoader: Template name was empty or null");
        }
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = this.openDbConnection();
            rs = this.readData(conn, this.templateColumn, name);
            if (!rs.next()) {
                throw new ResourceNotFoundException("DataSourceResourceLoader: could not find resource '" + name + "'");
            }
            final InputStream ascStream = rs.getAsciiStream(this.templateColumn);
            if (ascStream == null) {
                throw new ResourceNotFoundException("DataSourceResourceLoader: template column for '" + name + "' is null");
            }
            return new BufferedInputStream(ascStream);
        }
        catch (SQLException sqle) {
            final String msg = "DataSourceResourceLoader: database problem while getting resource '" + name + "': ";
            this.log.error(msg, sqle);
            throw new ResourceNotFoundException(msg);
        }
        catch (NamingException ne) {
            final String msg = "DataSourceResourceLoader: database problem while getting resource '" + name + "': ";
            this.log.error(msg, ne);
            throw new ResourceNotFoundException(msg);
        }
        finally {
            this.closeResultSet(rs);
            this.closeDbConnection(conn);
        }
    }
    
    private long readLastModified(final Resource resource, final String operation) {
        long timeStamp = 0L;
        final String name = resource.getName();
        if (name == null || name.length() == 0) {
            this.log.error("DataSourceResourceLoader: Template name was empty or null");
        }
        else {
            Connection conn = null;
            ResultSet rs = null;
            try {
                conn = this.openDbConnection();
                rs = this.readData(conn, this.timestampColumn, name);
                if (rs.next()) {
                    final Timestamp ts = rs.getTimestamp(this.timestampColumn);
                    timeStamp = ((ts != null) ? ts.getTime() : 0L);
                }
                else {
                    this.log.error("DataSourceResourceLoader: could not find resource " + name + " while " + operation);
                }
            }
            catch (SQLException sqle) {
                final String msg = "DataSourceResourceLoader: database problem while " + operation + " of '" + name + "': ";
                this.log.error(msg, sqle);
                throw ExceptionUtils.createRuntimeException(msg, sqle);
            }
            catch (NamingException ne) {
                final String msg = "DataSourceResourceLoader: database problem while " + operation + " of '" + name + "': ";
                this.log.error(msg, ne);
                throw ExceptionUtils.createRuntimeException(msg, ne);
            }
            finally {
                this.closeResultSet(rs);
                this.closeDbConnection(conn);
            }
        }
        return timeStamp;
    }
    
    private Connection openDbConnection() throws NamingException, SQLException {
        if (this.dataSource != null) {
            return this.dataSource.getConnection();
        }
        if (this.ctx == null) {
            this.ctx = new InitialContext();
        }
        this.dataSource = (DataSource)this.ctx.lookup(this.dataSourceName);
        return this.dataSource.getConnection();
    }
    
    private void closeDbConnection(final Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (RuntimeException re) {
                throw re;
            }
            catch (Exception e) {
                this.log.warn("DataSourceResourceLoader: problem when closing connection", e);
            }
        }
    }
    
    private void closeResultSet(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (RuntimeException re) {
                throw re;
            }
            catch (Exception e) {
                this.log.warn("DataSourceResourceLoader: problem when closing result set: ", e);
            }
        }
    }
    
    private ResultSet readData(final Connection conn, final String columnNames, final String templateName) throws SQLException {
        final PreparedStatement ps = conn.prepareStatement("SELECT " + columnNames + " FROM " + this.tableName + " WHERE " + this.keyColumn + " = ?");
        ps.setString(1, templateName);
        return ps.executeQuery();
    }
}
