// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime;

import org.apache.velocity.runtime.parser.Parser;
import org.apache.velocity.util.introspection.Introspector;
import org.apache.velocity.app.event.EventCartridge;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.introspection.Uberspect;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.resource.ContentResource;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.Template;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import java.io.Reader;
import java.util.Properties;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;

public interface RuntimeServices extends RuntimeLogger
{
    void init() throws Exception;
    
    void setProperty(final String p0, final Object p1);
    
    void setConfiguration(final ExtendedProperties p0);
    
    void addProperty(final String p0, final Object p1);
    
    void clearProperty(final String p0);
    
    Object getProperty(final String p0);
    
    void init(final Properties p0) throws Exception;
    
    void init(final String p0) throws Exception;
    
    SimpleNode parse(final Reader p0, final String p1) throws ParseException;
    
    SimpleNode parse(final Reader p0, final String p1, final boolean p2) throws ParseException;
    
    Template getTemplate(final String p0) throws ResourceNotFoundException, ParseErrorException, Exception;
    
    Template getTemplate(final String p0, final String p1) throws ResourceNotFoundException, ParseErrorException, Exception;
    
    ContentResource getContent(final String p0) throws ResourceNotFoundException, ParseErrorException, Exception;
    
    ContentResource getContent(final String p0, final String p1) throws ResourceNotFoundException, ParseErrorException, Exception;
    
    String getLoaderNameForResource(final String p0);
    
    String getString(final String p0, final String p1);
    
    Directive getVelocimacro(final String p0, final String p1);
    
    boolean addVelocimacro(final String p0, final String p1, final String[] p2, final String p3);
    
    boolean isVelocimacro(final String p0, final String p1);
    
    boolean dumpVMNamespace(final String p0);
    
    String getString(final String p0);
    
    int getInt(final String p0);
    
    int getInt(final String p0, final int p1);
    
    boolean getBoolean(final String p0, final boolean p1);
    
    ExtendedProperties getConfiguration();
    
    Object getApplicationAttribute(final Object p0);
    
    Object setApplicationAttribute(final Object p0, final Object p1);
    
    Uberspect getUberspect();
    
    Log getLog();
    
    EventCartridge getApplicationEventCartridge();
    
    Introspector getIntrospector();
    
    boolean isInitialized();
    
    Parser createNewParser();
}
