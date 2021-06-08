// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.velocity.runtime.resource;

import java.io.InputStream;
import java.io.IOException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.util.Vector;
import java.util.Iterator;
import org.apache.velocity.util.ClassUtils;
import org.apache.velocity.runtime.resource.loader.ResourceLoaderFactory;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.apache.velocity.util.StringUtils;
import org.mudebug.prapr.reloc.commons.collections.ExtendedProperties;
import java.util.ArrayList;
import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.runtime.RuntimeServices;
import java.util.List;

public class ResourceManagerImpl implements ResourceManager
{
    public static final int RESOURCE_TEMPLATE = 1;
    public static final int RESOURCE_CONTENT = 2;
    private static final String RESOURCE_LOADER_IDENTIFIER = "_RESOURCE_LOADER_IDENTIFIER_";
    protected ResourceCache globalCache;
    protected final List resourceLoaders;
    private final List sourceInitializerList;
    private boolean isInit;
    private boolean logWhenFound;
    protected RuntimeServices rsvc;
    protected Log log;
    
    public ResourceManagerImpl() {
        this.globalCache = null;
        this.resourceLoaders = new ArrayList();
        this.sourceInitializerList = new ArrayList();
        this.isInit = false;
        this.logWhenFound = true;
        this.rsvc = null;
        this.log = null;
    }
    
    public synchronized void initialize(final RuntimeServices rsvc) throws Exception {
        if (this.isInit) {
            this.log.warn("Re-initialization of ResourceLoader attempted!");
            return;
        }
        ResourceLoader resourceLoader = null;
        this.rsvc = rsvc;
        (this.log = rsvc.getLog()).debug("Default ResourceManager initializing. (" + this.getClass() + ")");
        this.assembleResourceLoaderInitializers();
        for (final ExtendedProperties configuration : this.sourceInitializerList) {
            final String loaderClass = StringUtils.nullTrim(configuration.getString("class"));
            final ResourceLoader loaderInstance = configuration.get("instance");
            if (loaderInstance != null) {
                resourceLoader = loaderInstance;
            }
            else {
                if (loaderClass == null) {
                    this.log.error("Unable to find '" + configuration.getString("_RESOURCE_LOADER_IDENTIFIER_") + ".resource.loader.class' specification in configuration." + " This is a critical value.  Please adjust configuration.");
                    continue;
                }
                resourceLoader = ResourceLoaderFactory.getLoader(rsvc, loaderClass);
            }
            resourceLoader.commonInit(rsvc, configuration);
            resourceLoader.init(configuration);
            this.resourceLoaders.add(resourceLoader);
        }
        this.logWhenFound = rsvc.getBoolean("resource.manager.logwhenfound", true);
        final String cacheClassName = rsvc.getString("resource.manager.cache.class");
        Object cacheObject = null;
        if (org.mudebug.prapr.reloc.commons.lang.StringUtils.isNotEmpty(cacheClassName)) {
            try {
                cacheObject = ClassUtils.getNewInstance(cacheClassName);
            }
            catch (ClassNotFoundException cnfe) {
                this.log.error("The specified class for ResourceCache (" + cacheClassName + ") does not exist or is not accessible to the current classloader.");
                cacheObject = null;
            }
            if (!(cacheObject instanceof ResourceCache)) {
                this.log.error("The specified class for ResourceCache (" + cacheClassName + ") does not implement " + ResourceCache.class.getName() + " ResourceManager. Using default ResourceCache implementation.");
                cacheObject = null;
            }
        }
        if (cacheObject == null) {
            cacheObject = new ResourceCacheImpl();
        }
        (this.globalCache = (ResourceCache)cacheObject).initialize(rsvc);
        this.log.trace("Default ResourceManager initialization complete.");
    }
    
    private void assembleResourceLoaderInitializers() {
        final Vector resourceLoaderNames = this.rsvc.getConfiguration().getVector("resource.loader");
        StringUtils.trimStrings(resourceLoaderNames);
        for (final String loaderName : resourceLoaderNames) {
            final StringBuffer loaderID = new StringBuffer(loaderName);
            loaderID.append(".").append("resource.loader");
            final ExtendedProperties loaderConfiguration = this.rsvc.getConfiguration().subset(loaderID.toString());
            if (loaderConfiguration == null) {
                this.log.warn("ResourceManager : No configuration information for resource loader named '" + loaderName + "'. Skipping.");
            }
            else {
                loaderConfiguration.setProperty("_RESOURCE_LOADER_IDENTIFIER_", loaderName);
                this.sourceInitializerList.add(loaderConfiguration);
            }
        }
    }
    
    public synchronized Resource getResource(final String resourceName, final int resourceType, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        final String resourceKey = resourceType + resourceName;
        Resource resource = this.globalCache.get(resourceKey);
        if (resource != null) {
            try {
                this.refreshResource(resource, encoding);
                return resource;
            }
            catch (ResourceNotFoundException rnfe) {
                this.globalCache.remove(resourceKey);
                return this.getResource(resourceName, resourceType, encoding);
            }
            catch (ParseErrorException pee) {
                this.log.error("ResourceManager.getResource() exception", pee);
                throw pee;
            }
            catch (RuntimeException re) {
                throw re;
            }
            catch (Exception e) {
                this.log.error("ResourceManager.getResource() exception", e);
                throw e;
            }
        }
        try {
            resource = this.loadResource(resourceName, resourceType, encoding);
            if (resource.getResourceLoader().isCachingOn()) {
                this.globalCache.put(resourceKey, resource);
            }
        }
        catch (ResourceNotFoundException rnfe) {
            this.log.error("ResourceManager : unable to find resource '" + resourceName + "' in any resource loader.");
            throw rnfe;
        }
        catch (ParseErrorException pee) {
            this.log.error("ResourceManager.getResource() parse exception", pee);
            throw pee;
        }
        catch (RuntimeException re) {
            throw re;
        }
        catch (Exception e) {
            this.log.error("ResourceManager.getResource() exception new", e);
            throw e;
        }
        return resource;
    }
    
    protected Resource loadResource(final String resourceName, final int resourceType, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        final Resource resource = ResourceFactory.getResource(resourceName, resourceType);
        resource.setRuntimeServices(this.rsvc);
        resource.setName(resourceName);
        resource.setEncoding(encoding);
        long howOldItWas = 0L;
        for (final ResourceLoader resourceLoader : this.resourceLoaders) {
            resource.setResourceLoader(resourceLoader);
            try {
                if (resource.process()) {
                    if (this.logWhenFound && this.log.isDebugEnabled()) {
                        this.log.debug("ResourceManager : found " + resourceName + " with loader " + resourceLoader.getClassName());
                    }
                    howOldItWas = resourceLoader.getLastModified(resource);
                    break;
                }
                continue;
            }
            catch (ResourceNotFoundException ex) {}
        }
        if (resource.getData() == null) {
            throw new ResourceNotFoundException("Unable to find resource '" + resourceName + "'");
        }
        resource.setLastModified(howOldItWas);
        resource.setModificationCheckInterval(resource.getResourceLoader().getModificationCheckInterval());
        resource.touch();
        return resource;
    }
    
    protected void refreshResource(final Resource resource, final String encoding) throws ResourceNotFoundException, ParseErrorException, Exception {
        if (resource.requiresChecking()) {
            resource.touch();
            if (resource.isSourceModified()) {
                if (!org.mudebug.prapr.reloc.commons.lang.StringUtils.equals(resource.getEncoding(), encoding)) {
                    this.log.warn("Declared encoding for template '" + resource.getName() + "' is different on reload. Old = '" + resource.getEncoding() + "' New = '" + encoding);
                    resource.setEncoding(encoding);
                }
                final long howOldItWas = resource.getResourceLoader().getLastModified(resource);
                resource.process();
                resource.setLastModified(howOldItWas);
            }
        }
    }
    
    public Resource getResource(final String resourceName, final int resourceType) throws ResourceNotFoundException, ParseErrorException, Exception {
        return this.getResource(resourceName, resourceType, "ISO-8859-1");
    }
    
    public String getLoaderNameForResource(final String resourceName) {
        for (final ResourceLoader resourceLoader : this.resourceLoaders) {
            InputStream is = null;
            try {
                is = resourceLoader.getResourceStream(resourceName);
                if (is != null) {
                    return resourceLoader.getClass().toString();
                }
            }
            catch (ResourceNotFoundException rnfe) {}
            finally {
                if (is != null) {
                    try {
                        is.close();
                    }
                    catch (IOException ex) {}
                }
            }
        }
        return null;
    }
}
