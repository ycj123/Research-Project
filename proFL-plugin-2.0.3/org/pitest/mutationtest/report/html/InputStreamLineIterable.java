// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.mutationtest.report.html;

import java.util.List;
import java.util.Collection;
import org.pitest.functional.SideEffect1;
import org.pitest.functional.FCollection;
import org.pitest.functional.FunctionalList;
import org.pitest.functional.F;
import java.util.Iterator;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import org.pitest.functional.FunctionalIterable;

public class InputStreamLineIterable implements FunctionalIterable<String>
{
    private final BufferedReader reader;
    private String next;
    
    public InputStreamLineIterable(final Reader reader) {
        this.reader = new BufferedReader(reader);
        this.advance();
    }
    
    private void advance() {
        try {
            this.next = this.reader.readLine();
        }
        catch (IOException e) {
            this.next = null;
        }
    }
    
    public String next() {
        final String t = this.next;
        this.advance();
        return t;
    }
    
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return InputStreamLineIterable.this.next != null;
            }
            
            @Override
            public String next() {
                return InputStreamLineIterable.this.next();
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public FunctionalList<String> filter(final F<String, Boolean> predicate) {
        return FCollection.filter(this, predicate);
    }
    
    @Override
    public void forEach(final SideEffect1<String> e) {
        FCollection.forEach(this, e);
    }
    
    @Override
    public <B> FunctionalList<B> map(final F<String, B> f) {
        return FCollection.map(this, f);
    }
    
    @Override
    public <B> void mapTo(final F<String, B> f, final Collection<? super B> bs) {
        FCollection.mapTo(this, f, bs);
    }
    
    @Override
    public <B> FunctionalList<B> flatMap(final F<String, ? extends Iterable<B>> f) {
        return FCollection.flatMap((Iterable<? extends String>)this, f);
    }
    
    @Override
    public boolean contains(final F<String, Boolean> predicate) {
        return FCollection.contains(this, predicate);
    }
}
