// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.plugin;

import java.util.HashMap;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.codehaus.plexus.util.introspection.ReflectionValueExtractor;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;
import java.io.File;
import java.util.Properties;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.logging.Logger;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.path.PathTranslator;
import java.util.Map;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluator;

public class PluginParameterExpressionEvaluator implements ExpressionEvaluator
{
    private static final Map BANNED_EXPRESSIONS;
    private static final Map DEPRECATED_EXPRESSIONS;
    private final PathTranslator pathTranslator;
    private final MavenSession context;
    private final Logger logger;
    private final MojoExecution mojoExecution;
    private final MavenProject project;
    private final String basedir;
    private final Properties properties;
    
    public PluginParameterExpressionEvaluator(final MavenSession context, final MojoExecution mojoExecution, final PathTranslator pathTranslator, final Logger logger, final MavenProject project, final Properties properties) {
        this.context = context;
        this.mojoExecution = mojoExecution;
        this.pathTranslator = pathTranslator;
        this.logger = logger;
        this.project = project;
        this.properties = properties;
        String basedir = null;
        if (project != null) {
            final File projectFile = project.getFile();
            if (projectFile != null) {
                basedir = projectFile.getParentFile().getAbsolutePath();
            }
        }
        if (basedir == null) {
            basedir = System.getProperty("user.dir");
        }
        this.basedir = basedir;
    }
    
    public Object evaluate(final String expr) throws ExpressionEvaluationException {
        Object value = null;
        if (expr == null) {
            return null;
        }
        final String expression = this.stripTokens(expr);
        if (expression.equals(expr)) {
            final int index = expr.indexOf("${");
            if (index >= 0) {
                final int lastIndex = expr.indexOf("}", index);
                if (lastIndex >= 0) {
                    String retVal = expr.substring(0, index);
                    if (index > 0 && expr.charAt(index - 1) == '$') {
                        retVal += expr.substring(index + 1, lastIndex + 1);
                    }
                    else {
                        retVal += this.evaluate(expr.substring(index, lastIndex + 1));
                    }
                    retVal += this.evaluate(expr.substring(lastIndex + 1));
                    return retVal;
                }
            }
            if (expression.indexOf("$$") > -1) {
                return expression.replaceAll("\\$\\$", "\\$");
            }
            return expression;
        }
        else {
            final MojoDescriptor mojoDescriptor = this.mojoExecution.getMojoDescriptor();
            if (PluginParameterExpressionEvaluator.BANNED_EXPRESSIONS.containsKey(expression)) {
                throw new ExpressionEvaluationException("The parameter expression: '" + expression + "' used in mojo: '" + mojoDescriptor.getGoal() + "' is banned. Use '" + PluginParameterExpressionEvaluator.BANNED_EXPRESSIONS.get(expression) + "' instead.");
            }
            if (PluginParameterExpressionEvaluator.DEPRECATED_EXPRESSIONS.containsKey(expression)) {
                this.logger.warn("The parameter expression: '" + expression + "' used in mojo: '" + mojoDescriptor.getGoal() + "' has been deprecated. Use '" + PluginParameterExpressionEvaluator.DEPRECATED_EXPRESSIONS.get(expression) + "' instead.");
            }
            Label_0967: {
                if ("localRepository".equals(expression)) {
                    value = this.context.getLocalRepository();
                }
                else if ("session".equals(expression)) {
                    value = this.context;
                }
                else if ("reactorProjects".equals(expression)) {
                    value = this.context.getSortedProjects();
                }
                else if ("reports".equals(expression)) {
                    value = this.mojoExecution.getReports();
                }
                else if ("mojoExecution".equals(expression)) {
                    value = this.mojoExecution;
                }
                else if ("project".equals(expression)) {
                    value = this.project;
                }
                else if ("executedProject".equals(expression)) {
                    value = this.project.getExecutionProject();
                }
                else {
                    if (expression.startsWith("project")) {
                        try {
                            final int pathSeparator = expression.indexOf("/");
                            if (pathSeparator > 0) {
                                final String pathExpression = expression.substring(0, pathSeparator);
                                value = ReflectionValueExtractor.evaluate(pathExpression, this.project);
                                value += expression.substring(pathSeparator);
                            }
                            else {
                                value = ReflectionValueExtractor.evaluate(expression.substring(1), this.project);
                            }
                            break Label_0967;
                        }
                        catch (Exception e) {
                            throw new ExpressionEvaluationException("Error evaluating plugin parameter expression: " + expression, e);
                        }
                    }
                    if (expression.startsWith("plugin")) {
                        try {
                            final int pathSeparator = expression.indexOf("/");
                            final PluginDescriptor pluginDescriptor = mojoDescriptor.getPluginDescriptor();
                            if (pathSeparator > 0) {
                                final String pathExpression2 = expression.substring(1, pathSeparator);
                                value = ReflectionValueExtractor.evaluate(pathExpression2, pluginDescriptor);
                                value += expression.substring(pathSeparator);
                            }
                            else {
                                value = ReflectionValueExtractor.evaluate(expression.substring(1), pluginDescriptor);
                            }
                            break Label_0967;
                        }
                        catch (Exception e) {
                            throw new ExpressionEvaluationException("Error evaluating plugin parameter expression: " + expression, e);
                        }
                    }
                    if ("settings".equals(expression)) {
                        value = this.context.getSettings();
                    }
                    else {
                        if (expression.startsWith("settings")) {
                            try {
                                final int pathSeparator = expression.indexOf("/");
                                if (pathSeparator > 0) {
                                    final String pathExpression = expression.substring(1, pathSeparator);
                                    value = ReflectionValueExtractor.evaluate(pathExpression, this.context.getSettings());
                                    value += expression.substring(pathSeparator);
                                }
                                else {
                                    value = ReflectionValueExtractor.evaluate(expression.substring(1), this.context.getSettings());
                                }
                                break Label_0967;
                            }
                            catch (Exception e) {
                                throw new ExpressionEvaluationException("Error evaluating plugin parameter expression: " + expression, e);
                            }
                        }
                        if ("basedir".equals(expression)) {
                            value = this.basedir;
                        }
                        else if (expression.startsWith("basedir")) {
                            final int pathSeparator = expression.indexOf("/");
                            if (pathSeparator > 0) {
                                value = this.basedir + expression.substring(pathSeparator);
                            }
                            else {
                                this.logger.error("Got expression '" + expression + "' that was not recognised");
                            }
                        }
                    }
                }
            }
            if (value == null) {
                if (this.project != null && this.project.getProperties() != null) {
                    value = this.project.getProperties().getProperty(expression);
                }
                if (value == null && this.properties != null) {
                    value = this.properties.getProperty(expression);
                }
            }
            if (value instanceof String) {
                final String val = (String)value;
                final int exprStartDelimiter = val.indexOf("${");
                if (exprStartDelimiter >= 0) {
                    if (exprStartDelimiter > 0) {
                        value = val.substring(0, exprStartDelimiter) + this.evaluate(val.substring(exprStartDelimiter));
                    }
                    else {
                        value = this.evaluate(val.substring(exprStartDelimiter));
                    }
                }
            }
            return value;
        }
    }
    
    private String stripTokens(String expr) {
        if (expr.startsWith("${") && expr.indexOf("}") == expr.length() - 1) {
            expr = expr.substring(2, expr.length() - 1);
        }
        return expr;
    }
    
    public File alignToBaseDirectory(final File file) {
        File basedir;
        if (this.project != null && this.project.getFile() != null) {
            basedir = this.project.getFile().getParentFile();
        }
        else {
            basedir = new File(".").getAbsoluteFile().getParentFile();
        }
        return new File(this.pathTranslator.alignToBaseDirectory(file.getPath(), basedir));
    }
    
    static {
        final Map deprecated = new HashMap();
        deprecated.put("project.build.resources", "project.resources");
        deprecated.put("project.build.testResources", "project.testResources");
        DEPRECATED_EXPRESSIONS = deprecated;
        final Map banned = BANNED_EXPRESSIONS = new HashMap();
    }
}
