// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.util;

import java.util.Vector;
import java.util.NoSuchElementException;

public class Queue
{
    private Node head;
    private Node tail;
    private int size;
    
    public Queue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public void enqueue(final Object o) {
        if (this.tail == null) {
            this.head = new Node(o);
            this.tail = this.head;
        }
        else {
            this.tail.next = new Node(o);
            this.tail = this.tail.next;
        }
        ++this.size;
    }
    
    public Object dequeue() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        final Object o = this.head.data;
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.head = this.head.next;
        }
        --this.size;
        return o;
    }
    
    public boolean isEmpty() {
        return this.head == null;
    }
    
    public int size() {
        return this.size;
    }
    
    public static void main(final String[] args) {
        final Queue q = new Queue();
        final Vector v = new Vector();
        final int count = Integer.parseInt(args[0]);
        System.out.println("Start vector");
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            v.addElement(new Integer(i));
        }
        while (!v.isEmpty()) {
            v.removeElementAt(0);
        }
        System.out.println("Done vector; elapsed time: " + (System.currentTimeMillis() - start));
        System.out.println("Start queue");
        start = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            q.enqueue(new Integer(i));
        }
        while (!q.isEmpty()) {
            q.dequeue();
        }
        System.out.println("Done queue; elapsed time: " + (System.currentTimeMillis() - start));
    }
    
    private class Node
    {
        public Object data;
        public Node next;
        
        public Node(final Object data) {
            this.data = data;
            this.next = null;
        }
    }
}
