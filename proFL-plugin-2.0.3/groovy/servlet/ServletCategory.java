// 
// Decompiled by Procyon v0.5.36
// 

package groovy.servlet;

import javax.servlet.jsp.PageContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

public class ServletCategory
{
    public static Object get(final ServletContext context, final String key) {
        return context.getAttribute(key);
    }
    
    public static Object get(final HttpSession session, final String key) {
        return session.getAttribute(key);
    }
    
    public static Object get(final ServletRequest request, final String key) {
        return request.getAttribute(key);
    }
    
    public static Object get(final PageContext context, final String key) {
        return context.getAttribute(key);
    }
    
    public static Object getAt(final ServletContext context, final String key) {
        return context.getAttribute(key);
    }
    
    public static Object getAt(final HttpSession session, final String key) {
        return session.getAttribute(key);
    }
    
    public static Object getAt(final ServletRequest request, final String key) {
        return request.getAttribute(key);
    }
    
    public static Object getAt(final PageContext context, final String key) {
        return context.getAttribute(key);
    }
    
    public static void set(final ServletContext context, final String key, final Object value) {
        context.setAttribute(key, value);
    }
    
    public static void set(final HttpSession session, final String key, final Object value) {
        session.setAttribute(key, value);
    }
    
    public static void set(final ServletRequest request, final String key, final Object value) {
        request.setAttribute(key, value);
    }
    
    public static void set(final PageContext context, final String key, final Object value) {
        context.setAttribute(key, value);
    }
    
    public static void putAt(final ServletContext context, final String key, final Object value) {
        context.setAttribute(key, value);
    }
    
    public static void putAt(final HttpSession session, final String key, final Object value) {
        session.setAttribute(key, value);
    }
    
    public static void putAt(final ServletRequest request, final String key, final Object value) {
        request.setAttribute(key, value);
    }
    
    public static void putAt(final PageContext context, final String key, final Object value) {
        context.setAttribute(key, value);
    }
}
