// 
// Decompiled by Procyon v0.5.36
// 

package org.pitest.maven.report;

import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import java.io.InputStream;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.apache.maven.plugin.MojoExecutionException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugin.AbstractMojo;

@org.apache.maven.plugins.annotations.Mojo(name = "help", requiresProject = false, threadSafe = true)
public class HelpMojo extends AbstractMojo
{
    @Parameter(property = "detail", defaultValue = "false")
    private boolean detail;
    @Parameter(property = "goal")
    private String goal;
    @Parameter(property = "lineLength", defaultValue = "80")
    private int lineLength;
    @Parameter(property = "indentSize", defaultValue = "2")
    private int indentSize;
    private static final String PLUGIN_HELP_PATH = "/META-INF/maven/org.pitest/pitest-maven/plugin-help.xml";
    private static final int DEFAULT_LINE_LENGTH = 80;
    
    private Document build() throws MojoExecutionException {
        this.getLog().debug("load plugin-help.xml: /META-INF/maven/org.pitest/pitest-maven/plugin-help.xml");
        InputStream is = null;
        try {
            is = this.getClass().getResourceAsStream("/META-INF/maven/org.pitest/pitest-maven/plugin-help.xml");
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            return dBuilder.parse(is);
        }
        catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        catch (ParserConfigurationException e2) {
            throw new MojoExecutionException(e2.getMessage(), e2);
        }
        catch (SAXException e3) {
            throw new MojoExecutionException(e3.getMessage(), e3);
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException e4) {
                    throw new MojoExecutionException(e4.getMessage(), e4);
                }
            }
        }
    }
    
    @Override
    public void execute() throws MojoExecutionException {
        if (this.lineLength <= 0) {
            this.getLog().warn("The parameter 'lineLength' should be positive, using '80' as default.");
            this.lineLength = 80;
        }
        if (this.indentSize <= 0) {
            this.getLog().warn("The parameter 'indentSize' should be positive, using '2' as default.");
            this.indentSize = 2;
        }
        final Document doc = this.build();
        final StringBuilder sb = new StringBuilder();
        final Node plugin = this.getSingleChild(doc, "plugin");
        final String name = this.getValue(plugin, "name");
        final String version = this.getValue(plugin, "version");
        final String id = this.getValue(plugin, "groupId") + ":" + this.getValue(plugin, "artifactId") + ":" + version;
        if (isNotEmpty(name) && !name.contains(id)) {
            this.append(sb, name + " " + version, 0);
        }
        else if (isNotEmpty(name)) {
            this.append(sb, name, 0);
        }
        else {
            this.append(sb, id, 0);
        }
        this.append(sb, this.getValue(plugin, "description"), 1);
        this.append(sb, "", 0);
        final String goalPrefix = this.getValue(plugin, "goalPrefix");
        final Node mojos1 = this.getSingleChild(plugin, "mojos");
        final List<Node> mojos2 = this.findNamedChild(mojos1, "mojo");
        if (this.goal == null || this.goal.length() <= 0) {
            this.append(sb, "This plugin has " + mojos2.size() + ((mojos2.size() > 1) ? " goals:" : " goal:"), 0);
            this.append(sb, "", 0);
        }
        for (final Node mojo : mojos2) {
            this.writeGoal(sb, goalPrefix, (Element)mojo);
        }
        if (this.getLog().isInfoEnabled()) {
            this.getLog().info(sb.toString());
        }
    }
    
    private static boolean isNotEmpty(final String string) {
        return string != null && string.length() > 0;
    }
    
    private String getValue(final Node node, final String elementName) throws MojoExecutionException {
        return this.getSingleChild(node, elementName).getTextContent();
    }
    
    private Node getSingleChild(final Node node, final String elementName) throws MojoExecutionException {
        final List<Node> namedChild = this.findNamedChild(node, elementName);
        if (namedChild.isEmpty()) {
            throw new MojoExecutionException("Could not find " + elementName + " in plugin-help.xml");
        }
        if (namedChild.size() > 1) {
            throw new MojoExecutionException("Multiple " + elementName + " in plugin-help.xml");
        }
        return namedChild.get(0);
    }
    
    private List<Node> findNamedChild(final Node node, final String elementName) {
        final List<Node> result = new ArrayList<Node>();
        final NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            final Node item = childNodes.item(i);
            if (elementName.equals(item.getNodeName())) {
                result.add(item);
            }
        }
        return result;
    }
    
    private Node findSingleChild(final Node node, final String elementName) throws MojoExecutionException {
        final List<Node> elementsByTagName = this.findNamedChild(node, elementName);
        if (elementsByTagName.isEmpty()) {
            return null;
        }
        if (elementsByTagName.size() > 1) {
            throw new MojoExecutionException("Multiple " + elementName + "in plugin-help.xml");
        }
        return elementsByTagName.get(0);
    }
    
    private void writeGoal(final StringBuilder sb, final String goalPrefix, final Element mojo) throws MojoExecutionException {
        final String mojoGoal = this.getValue(mojo, "goal");
        final Node configurationElement = this.findSingleChild(mojo, "configuration");
        final Node description = this.findSingleChild(mojo, "description");
        if (this.goal == null || this.goal.length() <= 0 || mojoGoal.equals(this.goal)) {
            this.append(sb, goalPrefix + ":" + mojoGoal, 0);
            final Node deprecated = this.findSingleChild(mojo, "deprecated");
            if (deprecated != null && isNotEmpty(deprecated.getTextContent())) {
                this.append(sb, "Deprecated. " + deprecated.getTextContent(), 1);
                if (this.detail && description != null) {
                    this.append(sb, "", 0);
                    this.append(sb, description.getTextContent(), 1);
                }
            }
            else if (description != null) {
                this.append(sb, description.getTextContent(), 1);
            }
            this.append(sb, "", 0);
            if (this.detail) {
                final Node parametersNode = this.getSingleChild(mojo, "parameters");
                final List<Node> parameters = this.findNamedChild(parametersNode, "parameter");
                this.append(sb, "Available parameters:", 1);
                this.append(sb, "", 0);
                for (final Node parameter : parameters) {
                    this.writeParameter(sb, parameter, configurationElement);
                }
            }
        }
    }
    
    private void writeParameter(final StringBuilder sb, final Node parameter, final Node configurationElement) throws MojoExecutionException {
        final String parameterName = this.getValue(parameter, "name");
        final String parameterDescription = this.getValue(parameter, "description");
        Element fieldConfigurationElement = null;
        if (configurationElement != null) {
            fieldConfigurationElement = (Element)this.findSingleChild(configurationElement, parameterName);
        }
        String parameterDefaultValue = "";
        if (fieldConfigurationElement != null && fieldConfigurationElement.hasAttribute("default-value")) {
            parameterDefaultValue = " (Default: " + fieldConfigurationElement.getAttribute("default-value") + ")";
        }
        this.append(sb, parameterName + parameterDefaultValue, 2);
        final Node deprecated = this.findSingleChild(parameter, "deprecated");
        if (deprecated != null && isNotEmpty(deprecated.getTextContent())) {
            this.append(sb, "Deprecated. " + deprecated.getTextContent(), 3);
            this.append(sb, "", 0);
        }
        this.append(sb, parameterDescription, 3);
        if ("true".equals(this.getValue(parameter, "required"))) {
            this.append(sb, "Required: Yes", 3);
        }
        if (fieldConfigurationElement != null && isNotEmpty(fieldConfigurationElement.getTextContent())) {
            final String property = this.getPropertyFromExpression(fieldConfigurationElement.getTextContent());
            this.append(sb, "User property: " + property, 3);
        }
        this.append(sb, "", 0);
    }
    
    private static String repeat(final String str, final int repeat) {
        final StringBuilder buffer = new StringBuilder(repeat * str.length());
        for (int i = 0; i < repeat; ++i) {
            buffer.append(str);
        }
        return buffer.toString();
    }
    
    private void append(final StringBuilder sb, final String description, final int indent) {
        for (final String line : toLines(description, indent, this.indentSize, this.lineLength)) {
            sb.append(line).append('\n');
        }
    }
    
    private static List<String> toLines(final String text, final int indent, final int indentSize, final int lineLength) {
        final List<String> lines = new ArrayList<String>();
        final String ind = repeat("\t", indent);
        final String[] split;
        final String[] plainLines = split = text.split("(\r\n)|(\r)|(\n)");
        for (final String plainLine : split) {
            toLines(lines, ind + plainLine, indentSize, lineLength);
        }
        return lines;
    }
    
    private static void toLines(final List<String> lines, final String line, final int indentSize, final int lineLength) {
        final int lineIndent = getIndentLevel(line);
        final StringBuilder buf = new StringBuilder(256);
        final String[] split;
        final String[] tokens = split = line.split(" +");
        for (final String token : split) {
            if (buf.length() > 0) {
                if (buf.length() + token.length() >= lineLength) {
                    lines.add(buf.toString());
                    buf.setLength(0);
                    buf.append(repeat(" ", lineIndent * indentSize));
                }
                else {
                    buf.append(' ');
                }
            }
            for (int j = 0; j < token.length(); ++j) {
                final char c = token.charAt(j);
                if (c == '\t') {
                    buf.append(repeat(" ", indentSize - buf.length() % indentSize));
                }
                else if (c == ' ') {
                    buf.append(' ');
                }
                else {
                    buf.append(c);
                }
            }
        }
        lines.add(buf.toString());
    }
    
    private static int getIndentLevel(final String line) {
        int level = 0;
        for (int i = 0; i < line.length() && line.charAt(i) == '\t'; ++i) {
            ++level;
        }
        for (int i = level + 1; i <= level + 4 && i < line.length(); ++i) {
            if (line.charAt(i) == '\t') {
                ++level;
                break;
            }
        }
        return level;
    }
    
    private String getPropertyFromExpression(final String expression) {
        if (expression != null && expression.startsWith("${") && expression.endsWith("}") && !expression.substring(2).contains("${")) {
            return expression.substring(2, expression.length() - 1);
        }
        return null;
    }
}
