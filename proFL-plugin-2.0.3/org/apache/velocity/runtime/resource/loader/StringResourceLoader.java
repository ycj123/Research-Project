// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource.loader;

import org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.util.ClassUtils;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.util.StringResource;
import java.io.UnsupportedEncodingException;
import org.apache.velocity.exception.VelocityException;
import java.io.ByteArrayInputStream;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.mudebug.prapr.reloc.commons.lang.StringUtils;
import java.io.InputStream;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

public class StringResourceLoader extends ResourceLoader
{
    public static final String REPOSITORY_CLASS = "repository.class";
    public static final String REPOSITORY_CLASS_DEFAULT;
    public static final String REPOSITORY_ENCODING = "repository.encoding";
    public static final String REPOSITORY_ENCODING_DEFAULT = "UTF-8";
    
    public static StringResourceRepository getRepository() {
        return RepositoryFactory.getRepository();
    }
    
    public void init(final ExtendedProperties configuration) {
        this.log.info("StringResourceLoader : initialization starting.");
        final String repositoryClass = configuration.getString("repository.class", StringResourceLoader.REPOSITORY_CLASS_DEFAULT);
        final String encoding = configuration.getString("repository.encoding", "UTF-8");
        RepositoryFactory.setRepositoryClass(repositoryClass);
        RepositoryFactory.setEncoding(encoding);
        RepositoryFactory.init(this.log);
        this.log.info("StringResourceLoader : initialization complete.");
    }
    
    public InputStream getResourceStream(final String name) throws ResourceNotFoundException {
        if (StringUtils.isEmpty(name)) {
            throw new ResourceNotFoundException("No template name provided");
        }
        final StringResource resource = getRepository().getStringResource(name);
        if (resource == null) {
            throw new ResourceNotFoundException("Could not locate resource '" + name + "'");
        }
        byte[] byteArray = null;
        try {
            byteArray = resource.getBody().getBytes(resource.getEncoding());
            return new ByteArrayInputStream(byteArray);
        }
        catch (UnsupportedEncodingException ue) {
            throw new VelocityException("Could not convert String using encoding " + resource.getEncoding(), ue);
        }
    }
    
    public boolean isSourceModified(final Resource resource) {
        StringResource original = null;
        boolean result = true;
        original = getRepository().getStringResource(resource.getName());
        if (original != null) {
            result = (original.getLastModified() != resource.getLastModified());
        }
        return result;
    }
    
    public long getLastModified(final Resource resource) {
        StringResource original = null;
        original = getRepository().getStringResource(resource.getName());
        return (original != null) ? original.getLastModified() : 0L;
    }
    
    static {
        REPOSITORY_CLASS_DEFAULT = StringResourceRepositoryImpl.class.getName();
    }
    
    private static final class RepositoryFactory
    {
        private static boolean isInitialized;
        private static StringResourceRepository repository;
        
        public static void setRepositoryClass(final String className) {
            if (RepositoryFactory.isInitialized) {
                throw new IllegalStateException("The RepositoryFactory has already been initialized!");
            }
            try {
                RepositoryFactory.repository = (StringResourceRepository)ClassUtils.getNewInstance(className);
            }
            catch (ClassNotFoundException cnfe) {
                throw new VelocityException("Could not find '" + className + "'", cnfe);
            }
            catch (IllegalAccessException iae) {
                throw new VelocityException("Could not access '" + className + "'", iae);
            }
            catch (InstantiationException ie) {
                throw new VelocityException("Could not instantiante '" + className + "'", ie);
            }
        }
        
        public static void setEncoding(final String encoding) {
            if (RepositoryFactory.repository == null) {
                throw new IllegalStateException("The Repository class has not yet been set!");
            }
            RepositoryFactory.repository.setEncoding(encoding);
        }
        
        public static synchronized void init(final Log log) throws VelocityException {
            if (RepositoryFactory.isInitialized) {
                throw new IllegalStateException("Attempted to re-initialize Factory!");
            }
            if (log.isInfoEnabled()) {
                log.info("Using " + RepositoryFactory.repository.getClass().getName() + " as repository implementation");
                log.info("Current repository encoding is " + RepositoryFactory.repository.getEncoding());
            }
            RepositoryFactory.isInitialized = true;
        }
        
        public static StringResourceRepository getRepository() {
            if (!RepositoryFactory.isInitialized) {
                throw new IllegalStateException("RepositoryFactory was not properly set up");
            }
            return RepositoryFactory.repository;
        }
        
        static {
            RepositoryFactory.isInitialized = false;
            RepositoryFactory.repository = null;
        }
    }
}
