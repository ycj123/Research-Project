// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.reloc.commons.digester;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.io.Reader;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;
import org.xml.sax.Attributes;
import java.util.List;
import java.util.Iterator;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.SAXException;
import java.util.Properties;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.EmptyStackException;
import org.mudebug.prapr.reloc.commons.logging.LogFactory;
import org.mudebug.prapr.reloc.commons.logging.Log;
import org.xml.sax.XMLReader;
import javax.xml.parsers.SAXParser;
import org.xml.sax.Locator;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.ErrorHandler;
import java.util.HashMap;
import org.xml.sax.EntityResolver;
import org.mudebug.prapr.reloc.commons.collections.ArrayStack;
import org.xml.sax.helpers.DefaultHandler;

public class Digester extends DefaultHandler
{
    protected StringBuffer bodyText;
    protected ArrayStack bodyTexts;
    protected ArrayStack matches;
    protected ClassLoader classLoader;
    protected boolean configured;
    protected EntityResolver entityResolver;
    protected HashMap entityValidator;
    protected ErrorHandler errorHandler;
    protected SAXParserFactory factory;
    protected String JAXP_SCHEMA_LANGUAGE;
    protected Locator locator;
    protected String match;
    protected boolean namespaceAware;
    protected HashMap namespaces;
    protected ArrayStack params;
    protected SAXParser parser;
    protected String publicId;
    protected XMLReader reader;
    protected Object root;
    protected Rules rules;
    protected String schemaLanguage;
    protected String schemaLocation;
    protected ArrayStack stack;
    protected boolean useContextClassLoader;
    protected boolean validating;
    protected Log log;
    protected Log saxLog;
    protected static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    protected Substitutor substitutor;
    private HashMap stacksByName;
    
    public Digester() {
        this.bodyText = new StringBuffer();
        this.bodyTexts = new ArrayStack();
        this.matches = new ArrayStack(10);
        this.classLoader = null;
        this.configured = false;
        this.entityValidator = new HashMap();
        this.errorHandler = null;
        this.factory = null;
        this.JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        this.locator = null;
        this.match = "";
        this.namespaceAware = false;
        this.namespaces = new HashMap();
        this.params = new ArrayStack();
        this.parser = null;
        this.publicId = null;
        this.reader = null;
        this.root = null;
        this.rules = null;
        this.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
        this.schemaLocation = null;
        this.stack = new ArrayStack();
        this.useContextClassLoader = false;
        this.validating = false;
        this.log = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester");
        this.saxLog = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester.sax");
        this.stacksByName = new HashMap();
    }
    
    public Digester(final SAXParser parser) {
        this.bodyText = new StringBuffer();
        this.bodyTexts = new ArrayStack();
        this.matches = new ArrayStack(10);
        this.classLoader = null;
        this.configured = false;
        this.entityValidator = new HashMap();
        this.errorHandler = null;
        this.factory = null;
        this.JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        this.locator = null;
        this.match = "";
        this.namespaceAware = false;
        this.namespaces = new HashMap();
        this.params = new ArrayStack();
        this.parser = null;
        this.publicId = null;
        this.reader = null;
        this.root = null;
        this.rules = null;
        this.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
        this.schemaLocation = null;
        this.stack = new ArrayStack();
        this.useContextClassLoader = false;
        this.validating = false;
        this.log = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester");
        this.saxLog = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester.sax");
        this.stacksByName = new HashMap();
        this.parser = parser;
    }
    
    public Digester(final XMLReader reader) {
        this.bodyText = new StringBuffer();
        this.bodyTexts = new ArrayStack();
        this.matches = new ArrayStack(10);
        this.classLoader = null;
        this.configured = false;
        this.entityValidator = new HashMap();
        this.errorHandler = null;
        this.factory = null;
        this.JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
        this.locator = null;
        this.match = "";
        this.namespaceAware = false;
        this.namespaces = new HashMap();
        this.params = new ArrayStack();
        this.parser = null;
        this.publicId = null;
        this.reader = null;
        this.root = null;
        this.rules = null;
        this.schemaLanguage = "http://www.w3.org/2001/XMLSchema";
        this.schemaLocation = null;
        this.stack = new ArrayStack();
        this.useContextClassLoader = false;
        this.validating = false;
        this.log = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester");
        this.saxLog = LogFactory.getLog("org.mudebug.prapr.reloc.commons.digester.Digester.sax");
        this.stacksByName = new HashMap();
        this.reader = reader;
    }
    
    public String findNamespaceURI(final String prefix) {
        final ArrayStack stack = this.namespaces.get(prefix);
        if (stack == null) {
            return null;
        }
        try {
            return (String)stack.peek();
        }
        catch (EmptyStackException e) {
            return null;
        }
    }
    
    public ClassLoader getClassLoader() {
        if (this.classLoader != null) {
            return this.classLoader;
        }
        if (this.useContextClassLoader) {
            final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader != null) {
                return classLoader;
            }
        }
        return this.getClass().getClassLoader();
    }
    
    public void setClassLoader(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
    
    public int getCount() {
        return this.stack.size();
    }
    
    public String getCurrentElementName() {
        String elementName = this.match;
        final int lastSlash = elementName.lastIndexOf(47);
        if (lastSlash >= 0) {
            elementName = elementName.substring(lastSlash + 1);
        }
        return elementName;
    }
    
    public int getDebug() {
        return 0;
    }
    
    public void setDebug(final int debug) {
    }
    
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }
    
    public void setErrorHandler(final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    public SAXParserFactory getFactory() {
        if (this.factory == null) {
            (this.factory = SAXParserFactory.newInstance()).setNamespaceAware(this.namespaceAware);
            this.factory.setValidating(this.validating);
        }
        return this.factory;
    }
    
    public boolean getFeature(final String feature) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        return this.getFactory().getFeature(feature);
    }
    
    public void setFeature(final String feature, final boolean value) throws ParserConfigurationException, SAXNotRecognizedException, SAXNotSupportedException {
        this.getFactory().setFeature(feature, value);
    }
    
    public Log getLogger() {
        return this.log;
    }
    
    public void setLogger(final Log log) {
        this.log = log;
    }
    
    public Log getSAXLogger() {
        return this.saxLog;
    }
    
    public void setSAXLogger(final Log saxLog) {
        this.saxLog = saxLog;
    }
    
    public String getMatch() {
        return this.match;
    }
    
    public boolean getNamespaceAware() {
        return this.namespaceAware;
    }
    
    public void setNamespaceAware(final boolean namespaceAware) {
        this.namespaceAware = namespaceAware;
    }
    
    public void setPublicId(final String publicId) {
        this.publicId = publicId;
    }
    
    public String getPublicId() {
        return this.publicId;
    }
    
    public String getRuleNamespaceURI() {
        return this.getRules().getNamespaceURI();
    }
    
    public void setRuleNamespaceURI(final String ruleNamespaceURI) {
        this.getRules().setNamespaceURI(ruleNamespaceURI);
    }
    
    public SAXParser getParser() {
        if (this.parser != null) {
            return this.parser;
        }
        try {
            if (this.validating) {
                final Properties properties = new Properties();
                properties.put("SAXParserFactory", this.getFactory());
                if (this.schemaLocation != null) {
                    properties.put("schemaLocation", this.schemaLocation);
                    properties.put("schemaLanguage", this.schemaLanguage);
                }
                this.parser = ParserFeatureSetterFactory.newSAXParser(properties);
            }
            else {
                this.parser = this.getFactory().newSAXParser();
            }
        }
        catch (Exception e) {
            this.log.error("Digester.getParser: ", e);
            return null;
        }
        return this.parser;
    }
    
    public Object getProperty(final String property) throws SAXNotRecognizedException, SAXNotSupportedException {
        return this.getParser().getProperty(property);
    }
    
    public void setProperty(final String property, final Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        this.getParser().setProperty(property, value);
    }
    
    public XMLReader getReader() {
        try {
            return this.getXMLReader();
        }
        catch (SAXException e) {
            this.log.error("Cannot get XMLReader", e);
            return null;
        }
    }
    
    public Rules getRules() {
        if (this.rules == null) {
            (this.rules = new RulesBase()).setDigester(this);
        }
        return this.rules;
    }
    
    public void setRules(final Rules rules) {
        (this.rules = rules).setDigester(this);
    }
    
    public String getSchema() {
        return this.schemaLocation;
    }
    
    public void setSchema(final String schemaLocation) {
        this.schemaLocation = schemaLocation;
    }
    
    public String getSchemaLanguage() {
        return this.schemaLanguage;
    }
    
    public void setSchemaLanguage(final String schemaLanguage) {
        this.schemaLanguage = schemaLanguage;
    }
    
    public boolean getUseContextClassLoader() {
        return this.useContextClassLoader;
    }
    
    public void setUseContextClassLoader(final boolean use) {
        this.useContextClassLoader = use;
    }
    
    public boolean getValidating() {
        return this.validating;
    }
    
    public void setValidating(final boolean validating) {
        this.validating = validating;
    }
    
    public XMLReader getXMLReader() throws SAXException {
        if (this.reader == null) {
            this.reader = this.getParser().getXMLReader();
        }
        this.reader.setDTDHandler(this);
        this.reader.setContentHandler(this);
        if (this.entityResolver == null) {
            this.reader.setEntityResolver(this);
        }
        else {
            this.reader.setEntityResolver(this.entityResolver);
        }
        this.reader.setErrorHandler(this);
        return this.reader;
    }
    
    public Substitutor getSubstitutor() {
        return this.substitutor;
    }
    
    public void setSubstitutor(final Substitutor substitutor) {
        this.substitutor = substitutor;
    }
    
    public void characters(final char[] buffer, final int start, final int length) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("characters(" + new String(buffer, start, length) + ")");
        }
        this.bodyText.append(buffer, start, length);
    }
    
    public void endDocument() throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            if (this.getCount() > 1) {
                this.saxLog.debug("endDocument():  " + this.getCount() + " elements left");
            }
            else {
                this.saxLog.debug("endDocument()");
            }
        }
        while (this.getCount() > 1) {
            this.pop();
        }
        for (final Rule rule : this.getRules().rules()) {
            try {
                rule.finish();
            }
            catch (Exception e) {
                this.log.error("Finish event threw exception", e);
                throw this.createSAXException(e);
            }
            catch (Error e2) {
                this.log.error("Finish event threw error", e2);
                throw e2;
            }
        }
        this.clear();
    }
    
    public void endElement(final String namespaceURI, final String localName, final String qName) throws SAXException {
        final boolean debug = this.log.isDebugEnabled();
        if (debug) {
            if (this.saxLog.isDebugEnabled()) {
                this.saxLog.debug("endElement(" + namespaceURI + "," + localName + "," + qName + ")");
            }
            this.log.debug("  match='" + this.match + "'");
            this.log.debug("  bodyText='" + (Object)this.bodyText + "'");
        }
        String name = localName;
        if (name == null || name.length() < 1) {
            name = qName;
        }
        final List rules = (List)this.matches.pop();
        if (rules != null && rules.size() > 0) {
            String bodyText = this.bodyText.toString();
            final Substitutor substitutor = this.getSubstitutor();
            if (substitutor != null) {
                bodyText = substitutor.substitute(bodyText);
            }
            for (int i = 0; i < rules.size(); ++i) {
                try {
                    final Rule rule = rules.get(i);
                    if (debug) {
                        this.log.debug("  Fire body() for " + rule);
                    }
                    rule.body(namespaceURI, name, bodyText);
                }
                catch (Exception e) {
                    this.log.error("Body event threw exception", e);
                    throw this.createSAXException(e);
                }
                catch (Error e2) {
                    this.log.error("Body event threw error", e2);
                    throw e2;
                }
            }
        }
        else if (debug) {
            this.log.debug("  No rules found matching '" + this.match + "'.");
        }
        this.bodyText = (StringBuffer)this.bodyTexts.pop();
        if (debug) {
            this.log.debug("  Popping body text '" + this.bodyText.toString() + "'");
        }
        if (rules != null) {
            for (int j = 0; j < rules.size(); ++j) {
                final int k = rules.size() - j - 1;
                try {
                    final Rule rule2 = rules.get(k);
                    if (debug) {
                        this.log.debug("  Fire end() for " + rule2);
                    }
                    rule2.end(namespaceURI, name);
                }
                catch (Exception e3) {
                    this.log.error("End event threw exception", e3);
                    throw this.createSAXException(e3);
                }
                catch (Error e4) {
                    this.log.error("End event threw error", e4);
                    throw e4;
                }
            }
        }
        final int slash = this.match.lastIndexOf(47);
        if (slash >= 0) {
            this.match = this.match.substring(0, slash);
        }
        else {
            this.match = "";
        }
    }
    
    public void endPrefixMapping(final String prefix) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("endPrefixMapping(" + prefix + ")");
        }
        final ArrayStack stack = this.namespaces.get(prefix);
        if (stack == null) {
            return;
        }
        try {
            stack.pop();
            if (stack.empty()) {
                this.namespaces.remove(prefix);
            }
        }
        catch (EmptyStackException e) {
            throw this.createSAXException("endPrefixMapping popped too many times");
        }
    }
    
    public void ignorableWhitespace(final char[] buffer, final int start, final int len) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("ignorableWhitespace(" + new String(buffer, start, len) + ")");
        }
    }
    
    public void processingInstruction(final String target, final String data) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("processingInstruction('" + target + "','" + data + "')");
        }
    }
    
    public Locator getDocumentLocator() {
        return this.locator;
    }
    
    public void setDocumentLocator(final Locator locator) {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("setDocumentLocator(" + locator + ")");
        }
        this.locator = locator;
    }
    
    public void skippedEntity(final String name) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("skippedEntity(" + name + ")");
        }
    }
    
    public void startDocument() throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("startDocument()");
        }
        this.configure();
    }
    
    public void startElement(final String namespaceURI, final String localName, final String qName, Attributes list) throws SAXException {
        final boolean debug = this.log.isDebugEnabled();
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("startElement(" + namespaceURI + "," + localName + "," + qName + ")");
        }
        this.bodyTexts.push(this.bodyText);
        if (debug) {
            this.log.debug("  Pushing body text '" + this.bodyText.toString() + "'");
        }
        this.bodyText = new StringBuffer();
        String name = localName;
        if (name == null || name.length() < 1) {
            name = qName;
        }
        final StringBuffer sb = new StringBuffer(this.match);
        if (this.match.length() > 0) {
            sb.append('/');
        }
        sb.append(name);
        this.match = sb.toString();
        if (debug) {
            this.log.debug("  New match='" + this.match + "'");
        }
        final List rules = this.getRules().match(namespaceURI, this.match);
        this.matches.push(rules);
        if (rules != null && rules.size() > 0) {
            final Substitutor substitutor = this.getSubstitutor();
            if (substitutor != null) {
                list = substitutor.substitute(list);
            }
            for (int i = 0; i < rules.size(); ++i) {
                try {
                    final Rule rule = rules.get(i);
                    if (debug) {
                        this.log.debug("  Fire begin() for " + rule);
                    }
                    rule.begin(namespaceURI, name, list);
                }
                catch (Exception e) {
                    this.log.error("Begin event threw exception", e);
                    throw this.createSAXException(e);
                }
                catch (Error e2) {
                    this.log.error("Begin event threw error", e2);
                    throw e2;
                }
            }
        }
        else if (debug) {
            this.log.debug("  No rules found matching '" + this.match + "'.");
        }
    }
    
    public void startPrefixMapping(final String prefix, final String namespaceURI) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("startPrefixMapping(" + prefix + "," + namespaceURI + ")");
        }
        ArrayStack stack = this.namespaces.get(prefix);
        if (stack == null) {
            stack = new ArrayStack();
            this.namespaces.put(prefix, stack);
        }
        stack.push(namespaceURI);
    }
    
    public void notationDecl(final String name, final String publicId, final String systemId) {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("notationDecl(" + name + "," + publicId + "," + systemId + ")");
        }
    }
    
    public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notation) {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("unparsedEntityDecl(" + name + "," + publicId + "," + systemId + "," + notation + ")");
        }
    }
    
    public void setEntityResolver(final EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }
    
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }
    
    public InputSource resolveEntity(final String publicId, final String systemId) throws SAXException {
        if (this.saxLog.isDebugEnabled()) {
            this.saxLog.debug("resolveEntity('" + publicId + "', '" + systemId + "')");
        }
        if (publicId != null) {
            this.publicId = publicId;
        }
        String entityURL = null;
        if (publicId != null) {
            entityURL = this.entityValidator.get(publicId);
        }
        if (this.schemaLocation != null && entityURL == null && systemId != null) {
            entityURL = this.entityValidator.get(systemId);
        }
        if (entityURL == null) {
            if (systemId == null) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(" Cannot resolve entity: '" + entityURL + "'");
                }
                return null;
            }
            if (this.log.isDebugEnabled()) {
                this.log.debug(" Trying to resolve using system ID '" + systemId + "'");
            }
            entityURL = systemId;
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug(" Resolving to alternate DTD '" + entityURL + "'");
        }
        try {
            return new InputSource(entityURL);
        }
        catch (Exception e) {
            throw this.createSAXException(e);
        }
    }
    
    public void error(final SAXParseException exception) throws SAXException {
        this.log.error("Parse Error at line " + exception.getLineNumber() + " column " + exception.getColumnNumber() + ": " + exception.getMessage(), exception);
        if (this.errorHandler != null) {
            this.errorHandler.error(exception);
        }
    }
    
    public void fatalError(final SAXParseException exception) throws SAXException {
        this.log.error("Parse Fatal Error at line " + exception.getLineNumber() + " column " + exception.getColumnNumber() + ": " + exception.getMessage(), exception);
        if (this.errorHandler != null) {
            this.errorHandler.fatalError(exception);
        }
    }
    
    public void warning(final SAXParseException exception) throws SAXException {
        if (this.errorHandler != null) {
            this.log.warn("Parse Warning Error at line " + exception.getLineNumber() + " column " + exception.getColumnNumber() + ": " + exception.getMessage(), exception);
            this.errorHandler.warning(exception);
        }
    }
    
    public void log(final String message) {
        this.log.info(message);
    }
    
    public void log(final String message, final Throwable exception) {
        this.log.error(message, exception);
    }
    
    public Object parse(final File file) throws IOException, SAXException {
        this.configure();
        final InputSource input = new InputSource(new FileInputStream(file));
        input.setSystemId("file://" + file.getAbsolutePath());
        this.getXMLReader().parse(input);
        return this.root;
    }
    
    public Object parse(final InputSource input) throws IOException, SAXException {
        this.configure();
        this.getXMLReader().parse(input);
        return this.root;
    }
    
    public Object parse(final InputStream input) throws IOException, SAXException {
        this.configure();
        final InputSource is = new InputSource(input);
        this.getXMLReader().parse(is);
        return this.root;
    }
    
    public Object parse(final Reader reader) throws IOException, SAXException {
        this.configure();
        final InputSource is = new InputSource(reader);
        this.getXMLReader().parse(is);
        return this.root;
    }
    
    public Object parse(final String uri) throws IOException, SAXException {
        this.configure();
        final InputSource is = new InputSource(uri);
        this.getXMLReader().parse(is);
        return this.root;
    }
    
    public void register(final String publicId, final String entityURL) {
        if (this.log.isDebugEnabled()) {
            this.log.debug("register('" + publicId + "', '" + entityURL + "'");
        }
        this.entityValidator.put(publicId, entityURL);
    }
    
    public void addRule(final String pattern, final Rule rule) {
        rule.setDigester(this);
        this.getRules().add(pattern, rule);
    }
    
    public void addRuleSet(final RuleSet ruleSet) {
        final String oldNamespaceURI = this.getRuleNamespaceURI();
        final String newNamespaceURI = ruleSet.getNamespaceURI();
        if (this.log.isDebugEnabled()) {
            if (newNamespaceURI == null) {
                this.log.debug("addRuleSet() with no namespace URI");
            }
            else {
                this.log.debug("addRuleSet() with namespace URI " + newNamespaceURI);
            }
        }
        this.setRuleNamespaceURI(newNamespaceURI);
        ruleSet.addRuleInstances(this);
        this.setRuleNamespaceURI(oldNamespaceURI);
    }
    
    public void addBeanPropertySetter(final String pattern) {
        this.addRule(pattern, new BeanPropertySetterRule());
    }
    
    public void addBeanPropertySetter(final String pattern, final String propertyName) {
        this.addRule(pattern, new BeanPropertySetterRule(propertyName));
    }
    
    public void addCallMethod(final String pattern, final String methodName) {
        this.addRule(pattern, new CallMethodRule(methodName));
    }
    
    public void addCallMethod(final String pattern, final String methodName, final int paramCount) {
        this.addRule(pattern, new CallMethodRule(methodName, paramCount));
    }
    
    public void addCallMethod(final String pattern, final String methodName, final int paramCount, final String[] paramTypes) {
        this.addRule(pattern, new CallMethodRule(methodName, paramCount, paramTypes));
    }
    
    public void addCallMethod(final String pattern, final String methodName, final int paramCount, final Class[] paramTypes) {
        this.addRule(pattern, new CallMethodRule(methodName, paramCount, paramTypes));
    }
    
    public void addCallParam(final String pattern, final int paramIndex) {
        this.addRule(pattern, new CallParamRule(paramIndex));
    }
    
    public void addCallParam(final String pattern, final int paramIndex, final String attributeName) {
        this.addRule(pattern, new CallParamRule(paramIndex, attributeName));
    }
    
    public void addCallParam(final String pattern, final int paramIndex, final boolean fromStack) {
        this.addRule(pattern, new CallParamRule(paramIndex, fromStack));
    }
    
    public void addCallParam(final String pattern, final int paramIndex, final int stackIndex) {
        this.addRule(pattern, new CallParamRule(paramIndex, stackIndex));
    }
    
    public void addCallParamPath(final String pattern, final int paramIndex) {
        this.addRule(pattern, new PathCallParamRule(paramIndex));
    }
    
    public void addObjectParam(final String pattern, final int paramIndex, final Object paramObj) {
        this.addRule(pattern, new ObjectParamRule(paramIndex, paramObj));
    }
    
    public void addFactoryCreate(final String pattern, final String className) {
        this.addFactoryCreate(pattern, className, false);
    }
    
    public void addFactoryCreate(final String pattern, final Class clazz) {
        this.addFactoryCreate(pattern, clazz, false);
    }
    
    public void addFactoryCreate(final String pattern, final String className, final String attributeName) {
        this.addFactoryCreate(pattern, className, attributeName, false);
    }
    
    public void addFactoryCreate(final String pattern, final Class clazz, final String attributeName) {
        this.addFactoryCreate(pattern, clazz, attributeName, false);
    }
    
    public void addFactoryCreate(final String pattern, final ObjectCreationFactory creationFactory) {
        this.addFactoryCreate(pattern, creationFactory, false);
    }
    
    public void addFactoryCreate(final String pattern, final String className, final boolean ignoreCreateExceptions) {
        this.addRule(pattern, new FactoryCreateRule(className, ignoreCreateExceptions));
    }
    
    public void addFactoryCreate(final String pattern, final Class clazz, final boolean ignoreCreateExceptions) {
        this.addRule(pattern, new FactoryCreateRule(clazz, ignoreCreateExceptions));
    }
    
    public void addFactoryCreate(final String pattern, final String className, final String attributeName, final boolean ignoreCreateExceptions) {
        this.addRule(pattern, new FactoryCreateRule(className, attributeName, ignoreCreateExceptions));
    }
    
    public void addFactoryCreate(final String pattern, final Class clazz, final String attributeName, final boolean ignoreCreateExceptions) {
        this.addRule(pattern, new FactoryCreateRule(clazz, attributeName, ignoreCreateExceptions));
    }
    
    public void addFactoryCreate(final String pattern, final ObjectCreationFactory creationFactory, final boolean ignoreCreateExceptions) {
        creationFactory.setDigester(this);
        this.addRule(pattern, new FactoryCreateRule(creationFactory, ignoreCreateExceptions));
    }
    
    public void addObjectCreate(final String pattern, final String className) {
        this.addRule(pattern, new ObjectCreateRule(className));
    }
    
    public void addObjectCreate(final String pattern, final Class clazz) {
        this.addRule(pattern, new ObjectCreateRule(clazz));
    }
    
    public void addObjectCreate(final String pattern, final String className, final String attributeName) {
        this.addRule(pattern, new ObjectCreateRule(className, attributeName));
    }
    
    public void addObjectCreate(final String pattern, final String attributeName, final Class clazz) {
        this.addRule(pattern, new ObjectCreateRule(attributeName, clazz));
    }
    
    public void addSetNestedProperties(final String pattern) {
        this.addRule(pattern, new SetNestedPropertiesRule());
    }
    
    public void addSetNestedProperties(final String pattern, final String elementName, final String propertyName) {
        this.addRule(pattern, new SetNestedPropertiesRule(elementName, propertyName));
    }
    
    public void addSetNestedProperties(final String pattern, final String[] elementNames, final String[] propertyNames) {
        this.addRule(pattern, new SetNestedPropertiesRule(elementNames, propertyNames));
    }
    
    public void addSetNext(final String pattern, final String methodName) {
        this.addRule(pattern, new SetNextRule(methodName));
    }
    
    public void addSetNext(final String pattern, final String methodName, final String paramType) {
        this.addRule(pattern, new SetNextRule(methodName, paramType));
    }
    
    public void addSetRoot(final String pattern, final String methodName) {
        this.addRule(pattern, new SetRootRule(methodName));
    }
    
    public void addSetRoot(final String pattern, final String methodName, final String paramType) {
        this.addRule(pattern, new SetRootRule(methodName, paramType));
    }
    
    public void addSetProperties(final String pattern) {
        this.addRule(pattern, new SetPropertiesRule());
    }
    
    public void addSetProperties(final String pattern, final String attributeName, final String propertyName) {
        this.addRule(pattern, new SetPropertiesRule(attributeName, propertyName));
    }
    
    public void addSetProperties(final String pattern, final String[] attributeNames, final String[] propertyNames) {
        this.addRule(pattern, new SetPropertiesRule(attributeNames, propertyNames));
    }
    
    public void addSetProperty(final String pattern, final String name, final String value) {
        this.addRule(pattern, new SetPropertyRule(name, value));
    }
    
    public void addSetTop(final String pattern, final String methodName) {
        this.addRule(pattern, new SetTopRule(methodName));
    }
    
    public void addSetTop(final String pattern, final String methodName, final String paramType) {
        this.addRule(pattern, new SetTopRule(methodName, paramType));
    }
    
    public void clear() {
        this.match = "";
        this.bodyTexts.clear();
        this.params.clear();
        this.publicId = null;
        this.stack.clear();
    }
    
    public Object peek() {
        try {
            return this.stack.peek();
        }
        catch (EmptyStackException e) {
            this.log.warn("Empty stack (returning null)");
            return null;
        }
    }
    
    public Object peek(final int n) {
        try {
            return this.stack.peek(n);
        }
        catch (EmptyStackException e) {
            this.log.warn("Empty stack (returning null)");
            return null;
        }
    }
    
    public Object pop() {
        try {
            return this.stack.pop();
        }
        catch (EmptyStackException e) {
            this.log.warn("Empty stack (returning null)");
            return null;
        }
    }
    
    public void push(final Object object) {
        if (this.stack.size() == 0) {
            this.root = object;
        }
        this.stack.push(object);
    }
    
    public void push(final String stackName, final Object value) {
        ArrayStack namedStack = this.stacksByName.get(stackName);
        if (namedStack == null) {
            namedStack = new ArrayStack();
            this.stacksByName.put(stackName, namedStack);
        }
        namedStack.push(value);
    }
    
    public Object pop(final String stackName) {
        Object result = null;
        final ArrayStack namedStack = this.stacksByName.get(stackName);
        if (namedStack == null) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Stack '" + stackName + "' is empty");
            }
            throw new EmptyStackException();
        }
        result = namedStack.pop();
        return result;
    }
    
    public Object peek(final String stackName) {
        Object result = null;
        final ArrayStack namedStack = this.stacksByName.get(stackName);
        if (namedStack == null) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Stack '" + stackName + "' is empty");
            }
            throw new EmptyStackException();
        }
        result = namedStack.peek();
        return result;
    }
    
    public boolean isEmpty(final String stackName) {
        boolean result = true;
        final ArrayStack namedStack = this.stacksByName.get(stackName);
        if (namedStack != null) {
            result = namedStack.isEmpty();
        }
        return result;
    }
    
    public Object getRoot() {
        return this.root;
    }
    
    protected void configure() {
        if (this.configured) {
            return;
        }
        this.initialize();
        this.configured = true;
    }
    
    protected void initialize() {
    }
    
    Map getRegistrations() {
        return this.entityValidator;
    }
    
    List getRules(final String match) {
        return this.getRules().match(match);
    }
    
    public Object peekParams() {
        try {
            return this.params.peek();
        }
        catch (EmptyStackException e) {
            this.log.warn("Empty stack (returning null)");
            return null;
        }
    }
    
    public Object peekParams(final int n) {
        try {
            return this.params.peek(n);
        }
        catch (EmptyStackException e) {
            this.log.warn("Empty stack (returning null)");
            return null;
        }
    }
    
    public Object popParams() {
        try {
            if (this.log.isTraceEnabled()) {
                this.log.trace("Popping params");
            }
            return this.params.pop();
        }
        catch (EmptyStackException e) {
            this.log.warn("Empty stack (returning null)");
            return null;
        }
    }
    
    public void pushParams(final Object object) {
        if (this.log.isTraceEnabled()) {
            this.log.trace("Pushing params");
        }
        this.params.push(object);
    }
    
    public SAXException createSAXException(final String message, Exception e) {
        if (e != null && e instanceof InvocationTargetException) {
            final Throwable t = ((InvocationTargetException)e).getTargetException();
            if (t != null && t instanceof Exception) {
                e = (Exception)t;
            }
        }
        if (this.locator != null) {
            final String error = "Error at (" + this.locator.getLineNumber() + ", " + this.locator.getColumnNumber() + ": " + message;
            if (e != null) {
                return new SAXParseException(error, this.locator, e);
            }
            return new SAXParseException(error, this.locator);
        }
        else {
            this.log.error("No Locator!");
            if (e != null) {
                return new SAXException(message, e);
            }
            return new SAXException(message);
        }
    }
    
    public SAXException createSAXException(Exception e) {
        if (e instanceof InvocationTargetException) {
            final Throwable t = ((InvocationTargetException)e).getTargetException();
            if (t != null && t instanceof Exception) {
                e = (Exception)t;
            }
        }
        return this.createSAXException(e.getMessage(), e);
    }
    
    public SAXException createSAXException(final String message) {
        return this.createSAXException(message, null);
    }
}
