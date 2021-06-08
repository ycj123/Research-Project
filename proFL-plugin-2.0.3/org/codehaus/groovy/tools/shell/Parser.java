// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.util.Logger;
import groovy.lang.GroovyObject;

public class Parser implements GroovyObject
{
    private static final String NEWLINE;
    private static final Logger log;
    private final Object delegate;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204026;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$ParseStatus;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$RelaxedParser;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$Parser;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Logger;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$RigidParser;
    
    public Parser() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        final Object f = $getCallSiteArray[0].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$util$Preferences());
        $getCallSiteArray[1].call(Parser.log, new GStringImpl(new Object[] { f }, new String[] { "Using parser flavor: ", "" }));
        final Object o = f;
        if (ScriptBytecodeAdapter.isCase(o, "relaxed")) {
            this.delegate = $getCallSiteArray[2].callConstructor($get$$class$org$codehaus$groovy$tools$shell$RelaxedParser());
        }
        else if (ScriptBytecodeAdapter.isCase(o, "rigid")) {
            this.delegate = $getCallSiteArray[3].callConstructor($get$$class$org$codehaus$groovy$tools$shell$RigidParser());
        }
        else {
            $getCallSiteArray[4].call(Parser.log, new GStringImpl(new Object[] { f }, new String[] { "Invalid parser flavor: ", "; using default" }));
            this.delegate = $getCallSiteArray[5].callConstructor($get$$class$org$codehaus$groovy$tools$shell$RigidParser());
        }
    }
    
    public ParseStatus parse(final List buffer) {
        return (ParseStatus)ScriptBytecodeAdapter.castToType($getCallSiteArray()[6].call(this.delegate, buffer), $get$$class$org$codehaus$groovy$tools$shell$ParseStatus());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$Parser()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Parser.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Parser.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Parser.__timeStamp__239_neverHappen1292524204026 = 0L;
        Parser.__timeStamp = 1292524204026L;
        NEWLINE = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray()[7].call($get$$class$java$lang$System(), "line.separator"), $get$$class$java$lang$String());
        log = (Logger)ScriptBytecodeAdapter.castToType($getCallSiteArray()[8].call($get$$class$org$codehaus$groovy$tools$shell$util$Logger(), $get$$class$org$codehaus$groovy$tools$shell$Parser()), $get$$class$org$codehaus$groovy$tools$shell$util$Logger());
    }
    
    public static final String getNEWLINE() {
        return Parser.NEWLINE;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[9];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$Parser(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Parser.$callSiteArray == null || ($createCallSiteArray = Parser.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Parser.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Parser.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Parser.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$ParseStatus() {
        Class $class$org$codehaus$groovy$tools$shell$ParseStatus;
        if (($class$org$codehaus$groovy$tools$shell$ParseStatus = Parser.$class$org$codehaus$groovy$tools$shell$ParseStatus) == null) {
            $class$org$codehaus$groovy$tools$shell$ParseStatus = (Parser.$class$org$codehaus$groovy$tools$shell$ParseStatus = class$("org.codehaus.groovy.tools.shell.ParseStatus"));
        }
        return $class$org$codehaus$groovy$tools$shell$ParseStatus;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$RelaxedParser() {
        Class $class$org$codehaus$groovy$tools$shell$RelaxedParser;
        if (($class$org$codehaus$groovy$tools$shell$RelaxedParser = Parser.$class$org$codehaus$groovy$tools$shell$RelaxedParser) == null) {
            $class$org$codehaus$groovy$tools$shell$RelaxedParser = (Parser.$class$org$codehaus$groovy$tools$shell$RelaxedParser = class$("org.codehaus.groovy.tools.shell.RelaxedParser"));
        }
        return $class$org$codehaus$groovy$tools$shell$RelaxedParser;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = Parser.$class$java$lang$System) == null) {
            $class$java$lang$System = (Parser.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$Parser() {
        Class $class$org$codehaus$groovy$tools$shell$Parser;
        if (($class$org$codehaus$groovy$tools$shell$Parser = Parser.$class$org$codehaus$groovy$tools$shell$Parser) == null) {
            $class$org$codehaus$groovy$tools$shell$Parser = (Parser.$class$org$codehaus$groovy$tools$shell$Parser = class$("org.codehaus.groovy.tools.shell.Parser"));
        }
        return $class$org$codehaus$groovy$tools$shell$Parser;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Logger() {
        Class $class$org$codehaus$groovy$tools$shell$util$Logger;
        if (($class$org$codehaus$groovy$tools$shell$util$Logger = Parser.$class$org$codehaus$groovy$tools$shell$util$Logger) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Logger = (Parser.$class$org$codehaus$groovy$tools$shell$util$Logger = class$("org.codehaus.groovy.tools.shell.util.Logger"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Logger;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = Parser.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (Parser.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = Parser.$class$java$lang$String) == null) {
            $class$java$lang$String = (Parser.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$RigidParser() {
        Class $class$org$codehaus$groovy$tools$shell$RigidParser;
        if (($class$org$codehaus$groovy$tools$shell$RigidParser = Parser.$class$org$codehaus$groovy$tools$shell$RigidParser) == null) {
            $class$org$codehaus$groovy$tools$shell$RigidParser = (Parser.$class$org$codehaus$groovy$tools$shell$RigidParser = class$("org.codehaus.groovy.tools.shell.RigidParser"));
        }
        return $class$org$codehaus$groovy$tools$shell$RigidParser;
    }
    
    static /* synthetic */ Class class$(final String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
