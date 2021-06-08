// 
// Decompiled by Procyon v0.5.36
// 

package groovy.util;

import java.util.Enumeration;
import java.util.Vector;
import org.xml.sax.SAXParseException;
import java.util.logging.Level;
import groovy.xml.QName;
import java.util.Iterator;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import java.util.Collections;
import java.lang.reflect.Method;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.dispatch.DispatchUtils;
import org.apache.tools.ant.RuntimeConfigurable;
import java.io.InputStream;
import org.apache.tools.ant.DemuxInputStream;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.BuildListener;
import org.apache.tools.ant.NoBannerLogger;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.Task;
import org.codehaus.groovy.ant.FileScanner;
import java.util.Map;
import java.util.HashMap;
import org.xml.sax.Locator;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.helper.ProjectHelper2;
import org.apache.tools.ant.helper.AntXMLContext;
import org.apache.tools.ant.Project;
import java.util.logging.Logger;

public class AntBuilder extends BuilderSupport
{
    private final Logger log;
    private Project project;
    private final AntXMLContext antXmlContext;
    private final ProjectHelper2.ElementHandler antElementHandler;
    private final ProjectHelper2.TargetHandler antTargetHandler;
    private final Target collectorTarget;
    private final Target implicitTarget;
    private Object lastCompletedNode;
    boolean insideTask;
    
    public AntBuilder() {
        this(createProject());
    }
    
    public AntBuilder(final Project project) {
        this(project, new Target());
    }
    
    public AntBuilder(final Project project, final Target owningTarget) {
        this.log = Logger.getLogger(this.getClass().getName());
        this.antElementHandler = new ProjectHelper2.ElementHandler();
        this.antTargetHandler = new ProjectHelper2.TargetHandler();
        this.project = project;
        this.collectorTarget = owningTarget;
        this.antXmlContext = new AntXMLContext(project);
        this.collectorTarget.setProject(project);
        this.antXmlContext.setCurrentTarget(this.collectorTarget);
        this.antXmlContext.setLocator((Locator)new AntBuilderLocator());
        this.antXmlContext.setCurrentTargets((Map)new HashMap());
        (this.implicitTarget = new Target()).setProject(project);
        this.implicitTarget.setName("");
        this.antXmlContext.setImplicitTarget(this.implicitTarget);
        project.addDataTypeDefinition("fileScanner", (Class)FileScanner.class);
    }
    
    public AntBuilder(final Task parentTask) {
        this(parentTask.getProject(), parentTask.getOwningTarget());
        final UnknownElement ue = new UnknownElement(parentTask.getTaskName());
        ue.setProject(parentTask.getProject());
        ue.setTaskType(parentTask.getTaskType());
        ue.setTaskName(parentTask.getTaskName());
        ue.setLocation(parentTask.getLocation());
        ue.setOwningTarget(parentTask.getOwningTarget());
        ue.setRuntimeConfigurableWrapper(parentTask.getRuntimeConfigurableWrapper());
        parentTask.getRuntimeConfigurableWrapper().setProxy((Object)ue);
        this.antXmlContext.pushWrapper(parentTask.getRuntimeConfigurableWrapper());
    }
    
    public Project getProject() {
        return this.project;
    }
    
    public AntXMLContext getAntXmlContext() {
        return this.antXmlContext;
    }
    
    protected static Project createProject() {
        final Project project = new Project();
        final ProjectHelper helper = ProjectHelper.getProjectHelper();
        project.addReference("ant.projectHelper", (Object)helper);
        helper.getImportStack().addElement("AntBuilder");
        final BuildLogger logger = (BuildLogger)new NoBannerLogger();
        logger.setMessageOutputLevel(2);
        logger.setOutputPrintStream(System.out);
        logger.setErrorPrintStream(System.err);
        project.addBuildListener((BuildListener)logger);
        project.init();
        project.getBaseDir();
        return project;
    }
    
    @Override
    protected void setParent(final Object parent, final Object child) {
    }
    
    @Override
    protected Object doInvokeMethod(final String methodName, final Object name, final Object args) {
        super.doInvokeMethod(methodName, name, args);
        return this.lastCompletedNode;
    }
    
    @Override
    protected void nodeCompleted(final Object parent, final Object node) {
        if (parent == null) {
            this.insideTask = false;
        }
        this.antElementHandler.onEndElement((String)null, (String)null, this.antXmlContext);
        this.lastCompletedNode = node;
        if (parent != null && !(parent instanceof Target)) {
            this.log.finest("parent is not null: no perform on nodeCompleted");
            return;
        }
        if (node instanceof Task) {
            final Task task = (Task)node;
            final String taskName = task.getTaskName();
            if ("antcall".equals(taskName) && parent == null) {
                throw new BuildException("antcall not supported within AntBuilder, consider using 'ant.project.executeTarget('targetName')' instead.");
            }
            final InputStream savedIn = System.in;
            final InputStream savedProjectInputStream = this.project.getDefaultInputStream();
            if (!(savedIn instanceof DemuxInputStream)) {
                this.project.setDefaultInputStream(savedIn);
                System.setIn((InputStream)new DemuxInputStream(this.project));
            }
            try {
                this.lastCompletedNode = this.performTask(task);
            }
            finally {
                this.project.setDefaultInputStream(savedProjectInputStream);
                System.setIn(savedIn);
            }
            if ("import".equals(taskName)) {
                this.antXmlContext.setCurrentTarget(this.collectorTarget);
            }
        }
        else if (node instanceof Target) {
            this.antXmlContext.setCurrentTarget(this.collectorTarget);
        }
        else {
            final RuntimeConfigurable r = (RuntimeConfigurable)node;
            r.maybeConfigure(this.project);
        }
    }
    
    private Object performTask(final Task task) {
        Throwable reason = null;
        try {
            final Method fireTaskStarted = Project.class.getDeclaredMethod("fireTaskStarted", Task.class);
            fireTaskStarted.setAccessible(true);
            fireTaskStarted.invoke(this.project, task);
            Object realThing = task;
            task.maybeConfigure();
            if (task instanceof UnknownElement) {
                realThing = ((UnknownElement)task).getRealThing();
            }
            DispatchUtils.execute((Object)task);
            return (realThing != null) ? realThing : task;
        }
        catch (BuildException ex) {
            if (ex.getLocation() == Location.UNKNOWN_LOCATION) {
                ex.setLocation(task.getLocation());
            }
            reason = (Throwable)ex;
            throw ex;
        }
        catch (Exception ex2) {
            reason = ex2;
            final BuildException be = new BuildException((Throwable)ex2);
            be.setLocation(task.getLocation());
            throw be;
        }
        catch (Error ex3) {
            reason = ex3;
            throw ex3;
        }
        finally {
            try {
                final Method fireTaskFinished = Project.class.getDeclaredMethod("fireTaskFinished", Task.class, Throwable.class);
                fireTaskFinished.setAccessible(true);
                fireTaskFinished.invoke(this.project, task, reason);
            }
            catch (Exception e) {
                final BuildException be2 = new BuildException((Throwable)e);
                be2.setLocation(task.getLocation());
                throw be2;
            }
        }
    }
    
    @Override
    protected Object createNode(final Object tagName) {
        return this.createNode(tagName, Collections.EMPTY_MAP);
    }
    
    @Override
    protected Object createNode(final Object name, final Object value) {
        final Object task = this.createNode(name);
        this.setText(task, value.toString());
        return task;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes, final Object value) {
        final Object task = this.createNode(name, attributes);
        this.setText(task, value.toString());
        return task;
    }
    
    protected static Attributes buildAttributes(final Map attributes) {
        final AttributesImpl attr = new AttributesImpl();
        for (final Map.Entry entry : attributes.entrySet()) {
            final String attributeName = entry.getKey();
            final String attributeValue = String.valueOf(entry.getValue());
            attr.addAttribute(null, attributeName, attributeName, "CDATA", attributeValue);
        }
        return attr;
    }
    
    @Override
    protected Object createNode(final Object name, final Map attributes) {
        final Attributes attrs = buildAttributes(attributes);
        String tagName = name.toString();
        String ns = "";
        if (name instanceof QName) {
            final QName q = (QName)name;
            tagName = q.getLocalPart();
            ns = q.getNamespaceURI();
        }
        if ("import".equals(name)) {
            this.antXmlContext.setCurrentTarget(this.implicitTarget);
        }
        else if ("target".equals(name) && !this.insideTask) {
            return this.onStartTarget(attrs, tagName, ns);
        }
        try {
            this.antElementHandler.onStartElement(ns, tagName, tagName, attrs, this.antXmlContext);
        }
        catch (SAXParseException e) {
            this.log.log(Level.SEVERE, "Caught: " + e, e);
        }
        this.insideTask = true;
        final RuntimeConfigurable wrapper = this.antXmlContext.getWrapperStack().lastElement();
        return wrapper.getProxy();
    }
    
    private Target onStartTarget(final Attributes attrs, final String tagName, final String ns) {
        final Target target = new Target();
        target.setProject(this.project);
        target.setLocation(new Location(this.antXmlContext.getLocator()));
        try {
            this.antTargetHandler.onStartElement(ns, tagName, tagName, attrs, this.antXmlContext);
            final Target newTarget = this.getProject().getTargets().get(attrs.getValue("name"));
            final Vector targets = new Vector();
            final Enumeration deps = newTarget.getDependencies();
            while (deps.hasMoreElements()) {
                final String targetName = deps.nextElement();
                targets.add(this.project.getTargets().get(targetName));
            }
            this.getProject().executeSortedTargets(targets);
            this.antXmlContext.setCurrentTarget(newTarget);
            return newTarget;
        }
        catch (SAXParseException e) {
            this.log.log(Level.SEVERE, "Caught: " + e, e);
            return null;
        }
    }
    
    protected void setText(final Object task, final String text) {
        final char[] characters = text.toCharArray();
        try {
            this.antElementHandler.characters(characters, 0, characters.length, this.antXmlContext);
        }
        catch (SAXParseException e) {
            this.log.log(Level.WARNING, "SetText failed: " + task + ". Reason: " + e, e);
        }
    }
    
    public Project getAntProject() {
        return this.project;
    }
}
