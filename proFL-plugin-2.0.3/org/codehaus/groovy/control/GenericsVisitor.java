// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.control;

import org.codehaus.groovy.ast.GenericsType;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.Parameter;
import org.codehaus.groovy.ast.MethodNode;
import org.codehaus.groovy.ast.FieldNode;
import org.codehaus.groovy.ast.GroovyClassVisitor;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.ast.ClassCodeVisitorSupport;

public class GenericsVisitor extends ClassCodeVisitorSupport
{
    private SourceUnit source;
    
    public GenericsVisitor(final SourceUnit source) {
        this.source = source;
    }
    
    @Override
    protected SourceUnit getSourceUnit() {
        return this.source;
    }
    
    @Override
    public void visitClass(final ClassNode node) {
        final boolean error = this.checkWildcard(node);
        if (error) {
            return;
        }
        this.checkGenericsUsage(node.getUnresolvedSuperClass(false), node.getSuperClass());
        final ClassNode[] interfaces = node.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            this.checkGenericsUsage(interfaces[i], interfaces[i].redirect());
        }
        node.visitContents(this);
    }
    
    @Override
    public void visitField(final FieldNode node) {
        final ClassNode type = node.getType();
        this.checkGenericsUsage(type, type.redirect());
    }
    
    @Override
    public void visitMethod(final MethodNode node) {
        final Parameter[] arr$;
        final Parameter[] parameters = arr$ = node.getParameters();
        for (final Parameter param : arr$) {
            final ClassNode paramType = param.getType();
            this.checkGenericsUsage(paramType, paramType.redirect());
        }
        final ClassNode returnType = node.getReturnType();
        this.checkGenericsUsage(returnType, returnType.redirect());
    }
    
    private boolean checkWildcard(final ClassNode cn) {
        final ClassNode sn = cn.getUnresolvedSuperClass(false);
        if (sn == null) {
            return false;
        }
        final GenericsType[] generics = sn.getGenericsTypes();
        if (generics == null) {
            return false;
        }
        boolean error = false;
        for (int i = 0; i < generics.length; ++i) {
            if (generics[i].isWildcard()) {
                this.addError("A supertype may not specifiy a wildcard type", sn);
                error = true;
            }
        }
        return error;
    }
    
    private void checkGenericsUsage(final ClassNode n, final ClassNode cn) {
        if (n.isGenericsPlaceHolder()) {
            return;
        }
        final GenericsType[] nTypes = n.getGenericsTypes();
        final GenericsType[] cnTypes = cn.getGenericsTypes();
        if (nTypes == null) {
            return;
        }
        if (cnTypes == null) {
            this.addError("The class " + n.getName() + " refers to the class " + cn.getName() + " and uses " + nTypes.length + " parameters, but the referred class takes no parameters", n);
            return;
        }
        if (nTypes.length != cnTypes.length) {
            this.addError("The class " + n.getName() + " refers to the class " + cn.getName() + " and uses " + nTypes.length + " parameters, but the refered class needs " + cnTypes.length, n);
            return;
        }
        for (int i = 0; i < nTypes.length; ++i) {
            final ClassNode nType = nTypes[i].getType();
            final ClassNode cnType = cnTypes[i].getType();
            if (!nType.isDerivedFrom(cnType)) {
                if (!cnType.isInterface() || !nType.declaresInterface(cnType)) {
                    this.addError("The type " + nTypes[i].getName() + " is not a valid substitute for the bounded parameter <" + this.getPrintName(cnTypes[i]) + ">", n);
                }
            }
        }
    }
    
    private String getPrintName(final GenericsType gt) {
        String ret = gt.getName();
        final ClassNode[] upperBounds = gt.getUpperBounds();
        final ClassNode lowerBound = gt.getLowerBound();
        if (upperBounds != null) {
            ret += " extends ";
            for (int i = 0; i < upperBounds.length; ++i) {
                ret += this.getPrintName(upperBounds[i]);
                if (i + 1 < upperBounds.length) {
                    ret += " & ";
                }
            }
        }
        else if (lowerBound != null) {
            ret = ret + " super " + this.getPrintName(lowerBound);
        }
        return ret;
    }
    
    private String getPrintName(final ClassNode cn) {
        String ret = cn.getName();
        final GenericsType[] gts = cn.getGenericsTypes();
        if (gts != null) {
            ret += "<";
            for (int i = 0; i < gts.length; ++i) {
                if (i != 0) {
                    ret += ",";
                }
                ret += this.getPrintName(gts[i]);
            }
            ret += ">";
        }
        return ret;
    }
    
    private void checkBounds(final ClassNode[] given, final ClassNode[] restrictions) {
        if (restrictions == null) {
            return;
        }
        for (int i = 0; i < given.length; ++i) {
            for (int j = 0; j < restrictions.length; ++j) {
                if (!given[i].isDerivedFrom(restrictions[j])) {}
            }
        }
    }
}
