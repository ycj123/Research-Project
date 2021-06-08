// 
// Decompiled by Procyon v0.5.36
// 

package groovy.sql;

import java.sql.SQLException;
import org.codehaus.groovy.runtime.InvokerHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import groovy.lang.GroovyObjectSupport;

public class BatchingStatementWrapper extends GroovyObjectSupport
{
    private Statement delegate;
    private int batchSize;
    private int batchCount;
    private Connection connection;
    private Logger log;
    private List<Integer> results;
    
    public BatchingStatementWrapper(final Statement delegate, final int batchSize, final Logger log, final Connection connection) {
        this.delegate = delegate;
        this.batchSize = batchSize;
        this.connection = connection;
        this.log = log;
        this.batchCount = 0;
        this.results = new ArrayList<Integer>();
    }
    
    @Override
    public Object invokeMethod(final String name, final Object args) {
        return InvokerHelper.invokeMethod(this.delegate, name, args);
    }
    
    public void addBatch(final String sql) throws SQLException {
        this.delegate.addBatch(sql);
        ++this.batchCount;
        if (this.batchSize != 0 && this.batchCount % this.batchSize == 0) {
            final int[] result = this.delegate.executeBatch();
            this.connection.commit();
            for (final int i : result) {
                this.results.add(i);
            }
            this.log.fine("Successfully executed batch with " + result.length + " command(s)");
        }
    }
    
    public void clearBatch() throws SQLException {
        if (this.batchSize != 0) {
            this.results = new ArrayList<Integer>();
        }
        this.delegate.clearBatch();
    }
    
    public int[] executeBatch() throws SQLException {
        if (this.batchSize == 0) {
            final int[] result = this.delegate.executeBatch();
            this.log.fine("Successfully executed batch with " + result.length + " command(s)");
            return result;
        }
        final int[] arr$;
        final int[] lastResult = arr$ = this.delegate.executeBatch();
        for (final int i : arr$) {
            this.results.add(i);
        }
        this.log.fine("Successfully executed batch with " + lastResult.length + " command(s)");
        final int[] result2 = new int[this.results.size()];
        for (int j = 0; j < this.results.size(); ++j) {
            result2[j] = this.results.get(j);
        }
        this.results = new ArrayList<Integer>();
        return result2;
    }
    
    public void close() throws SQLException {
        this.delegate.close();
    }
}
