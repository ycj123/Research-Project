// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.tools.shell.commands;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import jline.Completor;
import java.util.Map;
import jline.History;
import org.codehaus.groovy.tools.shell.BufferManager;
import java.util.List;
import org.codehaus.groovy.runtime.callsite.CallSite;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.tools.shell.Shell;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.tools.shell.CommandSupport;

public class EditCommand extends CommandSupport
{
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524204797;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
    private static /* synthetic */ Class $class$org$codehaus$groovy$tools$shell$commands$EditCommand;
    private static /* synthetic */ Class $class$java$lang$String;
    
    public EditCommand(final Shell shell) {
        $getCallSiteArray();
        Object[] array3;
        Object[] array2;
        Object[] array;
        final Object[] arguments = array = (array2 = (array3 = new Object[3]));
        arguments[0] = shell;
        arguments[1] = "edit";
        arguments[2] = "\\e";
        final int selectConstructorAndTransformArguments = ScriptBytecodeAdapter.selectConstructorAndTransformArguments(arguments, 1, $get$$class$org$codehaus$groovy$tools$shell$CommandSupport());
        if ((selectConstructorAndTransformArguments & 0x1) != 0x0) {
            array2 = (array = (array3 = (Object[])arguments[0]));
        }
        switch (selectConstructorAndTransformArguments >> 8) {
            case 0: {
                super((Shell)array[0], (String)array2[1], (String)array3[2]);
                this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
            }
            default: {
                throw new IllegalArgumentException("illegal constructor number");
            }
        }
    }
    
    private String getEditorCommand() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Object editor = $getCallSiteArray[0].callGetProperty($get$$class$org$codehaus$groovy$tools$shell$util$Preferences());
        $getCallSiteArray[1].call($getCallSiteArray[2].callGroovyObjectGetProperty(this), new GStringImpl(new Object[] { editor }, new String[] { "Using editor: ", "" }));
        if (!DefaultTypeTransformation.booleanUnbox(editor)) {
            $getCallSiteArray[3].callCurrent(this, "Unable to determine which editor to use; check $EDITOR");
        }
        return (String)ScriptBytecodeAdapter.castToType(editor, $get$$class$java$lang$String());
    }
    
    public Object execute(final List args) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: aload_2        
        //     5: ldc             4
        //     7: aaload         
        //     8: aload_0         /* this */
        //     9: aload_1         /* args */
        //    10: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //    15: pop            
        //    16: aload_2        
        //    17: ldc             5
        //    19: aaload         
        //    20: invokestatic    org/codehaus/groovy/tools/shell/commands/EditCommand.$get$$class$java$io$File:()Ljava/lang/Class;
        //    23: ldc             "groovysh-buffer"
        //    25: ldc             ".groovy"
        //    27: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    32: astore_3        /* file */
        //    33: aload_2        
        //    34: ldc             6
        //    36: aaload         
        //    37: aload_3         /* file */
        //    38: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    43: pop            
        //    44: aload_2        
        //    45: ldc             7
        //    47: aaload         
        //    48: aload_3         /* file */
        //    49: aload_2        
        //    50: ldc             8
        //    52: aaload         
        //    53: aload_2        
        //    54: ldc             9
        //    56: aaload         
        //    57: aload_0         /* this */
        //    58: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    63: aload_2        
        //    64: ldc             10
        //    66: aaload         
        //    67: aload_0         /* this */
        //    68: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    73: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    78: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    83: pop            
        //    84: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //    87: dup            
        //    88: iconst_2       
        //    89: anewarray       Ljava/lang/Object;
        //    92: dup            
        //    93: iconst_0       
        //    94: aload_2        
        //    95: ldc             11
        //    97: aaload         
        //    98: aload_0         /* this */
        //    99: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   104: aastore        
        //   105: dup            
        //   106: iconst_1       
        //   107: aload_3         /* file */
        //   108: aastore        
        //   109: iconst_3       
        //   110: anewarray       Ljava/lang/String;
        //   113: dup            
        //   114: iconst_0       
        //   115: ldc             ""
        //   117: aastore        
        //   118: dup            
        //   119: iconst_1       
        //   120: ldc             " "
        //   122: aastore        
        //   123: dup            
        //   124: iconst_2       
        //   125: ldc             ""
        //   127: aastore        
        //   128: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   131: astore          cmd
        //   133: aload_2        
        //   134: ldc             12
        //   136: aaload         
        //   137: aload_2        
        //   138: ldc             13
        //   140: aaload         
        //   141: aload_0         /* this */
        //   142: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   147: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   150: dup            
        //   151: iconst_1       
        //   152: anewarray       Ljava/lang/Object;
        //   155: dup            
        //   156: iconst_0       
        //   157: aload           cmd
        //   159: aastore        
        //   160: iconst_2       
        //   161: anewarray       Ljava/lang/String;
        //   164: dup            
        //   165: iconst_0       
        //   166: ldc             "Executing: "
        //   168: aastore        
        //   169: dup            
        //   170: iconst_1       
        //   171: ldc             ""
        //   173: aastore        
        //   174: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   177: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   182: pop            
        //   183: aload_2        
        //   184: ldc             14
        //   186: aaload         
        //   187: aload           cmd
        //   189: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   194: astore          p
        //   196: aload_2        
        //   197: ldc             15
        //   199: aaload         
        //   200: aload_2        
        //   201: ldc             16
        //   203: aaload         
        //   204: aload_0         /* this */
        //   205: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   210: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   213: dup            
        //   214: iconst_1       
        //   215: anewarray       Ljava/lang/Object;
        //   218: dup            
        //   219: iconst_0       
        //   220: aload           p
        //   222: aastore        
        //   223: iconst_2       
        //   224: anewarray       Ljava/lang/String;
        //   227: dup            
        //   228: iconst_0       
        //   229: ldc             "Waiting for process: "
        //   231: aastore        
        //   232: dup            
        //   233: iconst_1       
        //   234: ldc             ""
        //   236: aastore        
        //   237: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   240: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   245: pop            
        //   246: aload_2        
        //   247: ldc             17
        //   249: aaload         
        //   250: aload           p
        //   252: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   257: pop            
        //   258: aload_2        
        //   259: ldc             18
        //   261: aaload         
        //   262: aload_2        
        //   263: ldc             19
        //   265: aaload         
        //   266: aload_0         /* this */
        //   267: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGroovyObjectGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   272: new             Lorg/codehaus/groovy/runtime/GStringImpl;
        //   275: dup            
        //   276: iconst_1       
        //   277: anewarray       Ljava/lang/Object;
        //   280: dup            
        //   281: iconst_0       
        //   282: aload_2        
        //   283: ldc             20
        //   285: aaload         
        //   286: aload_3         /* file */
        //   287: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   292: aastore        
        //   293: iconst_2       
        //   294: anewarray       Ljava/lang/String;
        //   297: dup            
        //   298: iconst_0       
        //   299: ldc             "Editor contents: "
        //   301: aastore        
        //   302: dup            
        //   303: iconst_1       
        //   304: ldc             ""
        //   306: aastore        
        //   307: invokespecial   org/codehaus/groovy/runtime/GStringImpl.<init>:([Ljava/lang/Object;[Ljava/lang/String;)V
        //   310: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   315: pop            
        //   316: aload_2        
        //   317: ldc             21
        //   319: aaload         
        //   320: aload_3         /* file */
        //   321: new             Lorg/codehaus/groovy/tools/shell/commands/EditCommand$_execute_closure1;
        //   324: dup            
        //   325: aload_0         /* this */
        //   326: aload_0         /* this */
        //   327: invokespecial   org/codehaus/groovy/tools/shell/commands/EditCommand$_execute_closure1.<init>:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   330: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   335: invokestatic    org/codehaus/groovy/tools/shell/commands/EditCommand.$get$$class$java$lang$Object:()Ljava/lang/Class;
        //   338: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   341: checkcast       Ljava/lang/Object;
        //   344: astore          6
        //   346: nop            
        //   347: aload_2        
        //   348: ldc             22
        //   350: aaload         
        //   351: aload_3         /* file */
        //   352: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   357: pop            
        //   358: nop            
        //   359: aload           6
        //   361: areturn        
        //   362: goto            365
        //   365: aload_2        
        //   366: ldc             23
        //   368: aaload         
        //   369: aload_3         /* file */
        //   370: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   375: pop            
        //   376: nop            
        //   377: goto            396
        //   380: astore          4
        //   382: aload_2        
        //   383: ldc             24
        //   385: aaload         
        //   386: aload_3         /* file */
        //   387: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   392: pop            
        //   393: aload           4
        //   395: athrow         
        //   396: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  44     347    380    396    Any
        //  359    365    380    396    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$org$codehaus$groovy$tools$shell$commands$EditCommand()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = EditCommand.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (EditCommand.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        EditCommand.__timeStamp__239_neverHappen1292524204797 = 0L;
        EditCommand.__timeStamp = 1292524204797L;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[25];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$tools$shell$commands$EditCommand(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (EditCommand.$callSiteArray == null || ($createCallSiteArray = EditCommand.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            EditCommand.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = EditCommand.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (EditCommand.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$CommandSupport() {
        Class $class$org$codehaus$groovy$tools$shell$CommandSupport;
        if (($class$org$codehaus$groovy$tools$shell$CommandSupport = EditCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport) == null) {
            $class$org$codehaus$groovy$tools$shell$CommandSupport = (EditCommand.$class$org$codehaus$groovy$tools$shell$CommandSupport = class$("org.codehaus.groovy.tools.shell.CommandSupport"));
        }
        return $class$org$codehaus$groovy$tools$shell$CommandSupport;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$util$Preferences() {
        Class $class$org$codehaus$groovy$tools$shell$util$Preferences;
        if (($class$org$codehaus$groovy$tools$shell$util$Preferences = EditCommand.$class$org$codehaus$groovy$tools$shell$util$Preferences) == null) {
            $class$org$codehaus$groovy$tools$shell$util$Preferences = (EditCommand.$class$org$codehaus$groovy$tools$shell$util$Preferences = class$("org.codehaus.groovy.tools.shell.util.Preferences"));
        }
        return $class$org$codehaus$groovy$tools$shell$util$Preferences;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$tools$shell$commands$EditCommand() {
        Class $class$org$codehaus$groovy$tools$shell$commands$EditCommand;
        if (($class$org$codehaus$groovy$tools$shell$commands$EditCommand = EditCommand.$class$org$codehaus$groovy$tools$shell$commands$EditCommand) == null) {
            $class$org$codehaus$groovy$tools$shell$commands$EditCommand = (EditCommand.$class$org$codehaus$groovy$tools$shell$commands$EditCommand = class$("org.codehaus.groovy.tools.shell.commands.EditCommand"));
        }
        return $class$org$codehaus$groovy$tools$shell$commands$EditCommand;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = EditCommand.$class$java$lang$String) == null) {
            $class$java$lang$String = (EditCommand.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
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
