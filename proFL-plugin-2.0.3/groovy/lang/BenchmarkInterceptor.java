// 
// Decompiled by Procyon v0.5.36
// 

package groovy.lang;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BenchmarkInterceptor implements Interceptor
{
    protected Map calls;
    
    public BenchmarkInterceptor() {
        this.calls = new LinkedHashMap();
    }
    
    public Map getCalls() {
        return this.calls;
    }
    
    public void reset() {
        this.calls = new HashMap();
    }
    
    public Object beforeInvoke(final Object object, final String methodName, final Object[] arguments) {
        if (!this.calls.containsKey(methodName)) {
            this.calls.put(methodName, new LinkedList());
        }
        this.calls.get(methodName).add(new Long(System.currentTimeMillis()));
        return null;
    }
    
    public Object afterInvoke(final Object object, final String methodName, final Object[] arguments, final Object result) {
        this.calls.get(methodName).add(new Long(System.currentTimeMillis()));
        return result;
    }
    
    public boolean doInvoke() {
        return true;
    }
    
    public List statistic() {
        final List result = new LinkedList();
        final Iterator iter = this.calls.keySet().iterator();
        while (iter.hasNext()) {
            final Object[] line = new Object[3];
            result.add(line);
            line[0] = iter.next();
            final List times = this.calls.get(line[0]);
            line[1] = new Integer(times.size() / 2);
            int accTime = 0;
            final Iterator it = times.iterator();
            while (it.hasNext()) {
                final Long start = it.next();
                final Long end = it.next();
                accTime += (int)(end - start);
            }
            line[2] = new Long(accTime);
        }
        return result;
    }
}
