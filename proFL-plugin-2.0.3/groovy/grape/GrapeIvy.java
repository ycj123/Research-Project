// 
// Decompiled by Procyon v0.5.36
// 

package groovy.grape;

import java.util.Iterator;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.apache.ivy.plugins.resolver.IBiblioResolver;
import org.apache.ivy.plugins.resolver.ChainResolver;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import java.net.URI;
import java.util.regex.Pattern;
import java.util.List;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.event.IvyListener;
import org.apache.ivy.core.resolve.ResolveOptions;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import java.io.IOException;
import java.io.File;
import org.codehaus.groovy.runtime.callsite.CallSite;
import java.text.ParseException;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import org.apache.ivy.core.settings.IvySettings;
import java.util.Map;
import java.util.Set;
import org.apache.ivy.Ivy;
import groovy.lang.GroovyObject;

public class GrapeIvy implements GrapeEngine, GroovyObject
{
    private static final int DEFAULT_DEPTH;
    private final Object exclusiveGrabArgs;
    private boolean enableGrapes;
    private Ivy ivyInstance;
    private Set<String> resolvedDependencies;
    private Set<String> downloadedArtifacts;
    private Map<ClassLoader, Set<IvyGrabRecord>> loadedDeps;
    private Set<IvyGrabRecord> grabRecordsForCurrDependencies;
    private IvySettings settings;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    private static final /* synthetic */ Integer $const$1;
    private static final /* synthetic */ Integer $const$2;
    private static final /* synthetic */ Integer $const$3;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524202418;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$org$apache$ivy$core$event$IvyListener;
    private static /* synthetic */ Class $class$org$codehaus$groovy$reflection$ReflectionUtils;
    private static /* synthetic */ Class $class$groovy$lang$GroovySystem;
    private static /* synthetic */ Class $class$java$lang$RuntimeException;
    private static /* synthetic */ Class array$$class$java$util$Map;
    private static /* synthetic */ Class $class$java$util$List;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$apache$ivy$Ivy;
    private static /* synthetic */ Class $class$java$util$Map;
    private static /* synthetic */ Class $class$java$io$File;
    private static /* synthetic */ Class $class$org$apache$ivy$core$report$ResolveReport;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$id$ModuleRevisionId;
    private static /* synthetic */ Class $class$java$util$Set;
    private static /* synthetic */ Class $class$java$util$regex$Pattern;
    private static /* synthetic */ Class $class$groovy$grape$GrapeIvy;
    private static /* synthetic */ Class $class$org$apache$ivy$core$resolve$ResolveOptions;
    private static /* synthetic */ Class $class$org$apache$ivy$plugins$resolver$ChainResolver;
    private static /* synthetic */ Class array$$class$java$net$URI;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor;
    private static /* synthetic */ Class $class$org$apache$ivy$util$DefaultMessageLogger;
    private static /* synthetic */ Class $class$org$apache$ivy$util$Message;
    private static /* synthetic */ Class $class$groovy$grape$IvyGrabRecord;
    private static /* synthetic */ Class $class$java$util$LinkedHashSet;
    private static /* synthetic */ Class array$$class$java$lang$String;
    private static /* synthetic */ Class $class$java$lang$Integer;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$Configuration;
    private static /* synthetic */ Class $class$java$lang$System;
    private static /* synthetic */ Class $class$java$lang$Boolean;
    private static /* synthetic */ Class $class$org$apache$ivy$core$cache$ResolutionCacheManager;
    private static /* synthetic */ Class $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor;
    private static /* synthetic */ Class $class$org$apache$ivy$plugins$resolver$IBiblioResolver;
    private static /* synthetic */ Class $class$java$util$WeakHashMap;
    private static /* synthetic */ Class $class$org$apache$ivy$core$settings$IvySettings;
    
    public GrapeIvy() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        this.exclusiveGrabArgs = $getCallSiteArray[0].call(ScriptBytecodeAdapter.createList(new Object[] { ScriptBytecodeAdapter.createList(new Object[] { "group", "groupId", "organisation", "organization", "org" }), ScriptBytecodeAdapter.createList(new Object[] { "module", "artifactId", "artifact" }), ScriptBytecodeAdapter.createList(new Object[] { "version", "revision", "rev" }), ScriptBytecodeAdapter.createList(new Object[] { "conf", "scope", "configuration" }) }), ScriptBytecodeAdapter.createMap(new Object[0]), new GrapeIvy$_closure1(this, this));
        this.loadedDeps = (Map<ClassLoader, Set<IvyGrabRecord>>)ScriptBytecodeAdapter.castToType($getCallSiteArray[1].callConstructor($get$$class$java$util$WeakHashMap()), $get$$class$java$util$Map());
        this.grabRecordsForCurrDependencies = (Set<IvyGrabRecord>)ScriptBytecodeAdapter.castToType($getCallSiteArray[2].callConstructor($get$$class$java$util$LinkedHashSet()), $get$$class$java$util$Set());
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        if (DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.enableGrapes))) {
            return;
        }
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[3].callConstructor($get$$class$org$apache$ivy$util$DefaultMessageLogger(), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType($getCallSiteArray[4].call($get$$class$java$lang$System(), "ivy.message.logger.level", "-1"), $get$$class$java$lang$Integer()), Integer.TYPE)), $get$$class$groovy$grape$GrapeIvy(), $get$$class$org$apache$ivy$util$Message(), "defaultLogger");
        this.settings = (IvySettings)ScriptBytecodeAdapter.castToType($getCallSiteArray[5].callConstructor($get$$class$org$apache$ivy$core$settings$IvySettings()), $get$$class$org$apache$ivy$core$settings$IvySettings());
        Object grapeConfig = $getCallSiteArray[6].callCurrent(this);
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[7].call(grapeConfig))) {
            grapeConfig = $getCallSiteArray[8].call($get$$class$groovy$grape$GrapeIvy(), "defaultGrapeConfig.xml");
        }
        try {
            $getCallSiteArray[9].call(this.settings, grapeConfig);
        }
        catch (ParseException ex) {
            $getCallSiteArray[10].call($getCallSiteArray[11].callGetProperty($get$$class$java$lang$System()), $getCallSiteArray[12].call(new GStringImpl(new Object[] { $getCallSiteArray[13].callGetProperty(grapeConfig) }, new String[] { "Local Ivy config file '", "' appears corrupt - ignoring it and using default config instead\nError was: " }), $getCallSiteArray[14].callGetProperty(ex)));
            grapeConfig = $getCallSiteArray[15].call($get$$class$groovy$grape$GrapeIvy(), "defaultGrapeConfig.xml");
            $getCallSiteArray[16].call(this.settings, grapeConfig);
        }
        ScriptBytecodeAdapter.setProperty($getCallSiteArray[17].callCurrent(this), $get$$class$groovy$grape$GrapeIvy(), this.settings, "defaultCache");
        $getCallSiteArray[18].call(this.settings, "ivy.default.configuration.m2compatible", "true");
        this.ivyInstance = (Ivy)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[19].call($get$$class$org$apache$ivy$Ivy(), this.settings), $get$$class$org$apache$ivy$Ivy()), $get$$class$org$apache$ivy$Ivy());
        this.resolvedDependencies = (Set<String>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$Set()), $get$$class$java$util$Set());
        this.downloadedArtifacts = (Set<String>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$Set()), $get$$class$java$util$Set());
        this.enableGrapes = DefaultTypeTransformation.booleanUnbox(Boolean.TRUE);
    }
    
    public File getGroovyRoot() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String root = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[20].call($get$$class$java$lang$System(), "groovy.root"), $get$$class$java$lang$String());
        Object groovyRoot = null;
        Label_0095: {
            if (ScriptBytecodeAdapter.compareEqual(root, null)) {
                groovyRoot = $getCallSiteArray[21].callConstructor($get$$class$java$io$File(), $getCallSiteArray[22].call($get$$class$java$lang$System(), "user.home"), ".groovy");
                break Label_0095;
            }
            groovyRoot = $getCallSiteArray[23].callConstructor($get$$class$java$io$File(), root);
            try {
                groovyRoot = $getCallSiteArray[24].callGetProperty(groovyRoot);
            }
            catch (IOException e) {}
        }
        return (File)ScriptBytecodeAdapter.castToType(groovyRoot, $get$$class$java$io$File());
    }
    
    public File getLocalGrapeConfig() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String grapeConfig = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[25].call($get$$class$java$lang$System(), "grape.config"), $get$$class$java$lang$String());
        if (DefaultTypeTransformation.booleanUnbox(grapeConfig)) {
            return (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[26].callConstructor($get$$class$java$io$File(), grapeConfig), $get$$class$java$io$File());
        }
        return (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[27].callConstructor($get$$class$java$io$File(), $getCallSiteArray[28].callCurrent(this), "grapeConfig.xml"), $get$$class$java$io$File());
    }
    
    public File getGrapeDir() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final String root = (String)ScriptBytecodeAdapter.castToType($getCallSiteArray[29].call($get$$class$java$lang$System(), "grape.root"), $get$$class$java$lang$String());
        if (ScriptBytecodeAdapter.compareEqual(root, null)) {
            return (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[30].callCurrent(this), $get$$class$java$io$File());
        }
        File grapeRoot = (File)$getCallSiteArray[31].callConstructor($get$$class$java$io$File(), root);
        try {
            grapeRoot = (File)ScriptBytecodeAdapter.castToType($getCallSiteArray[32].callGetProperty(grapeRoot), $get$$class$java$io$File());
        }
        catch (IOException e) {}
        return (File)ScriptBytecodeAdapter.castToType(grapeRoot, $get$$class$java$io$File());
    }
    
    public File getGrapeCacheDir() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final File cache = (File)$getCallSiteArray[33].callConstructor($get$$class$java$io$File(), $getCallSiteArray[34].callCurrent(this), "grapes");
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[35].call(cache))) {
            $getCallSiteArray[36].call(cache);
        }
        else if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[37].call(cache))) {
            throw (Throwable)$getCallSiteArray[38].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { cache }, new String[] { "The grape cache dir ", " is not a directory" }));
        }
        return (File)ScriptBytecodeAdapter.castToType(cache, $get$$class$java$io$File());
    }
    
    public Object chooseClassLoader(final Map args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object loader = $getCallSiteArray[39].callGetProperty(args);
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[40].callCurrent(this, loader))) {
            final CallSite callSite = $getCallSiteArray[41];
            Object o;
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[42].callGetPropertySafe($getCallSiteArray[43].callGetProperty(args)))) {
                final CallSite callSite2 = $getCallSiteArray[44];
                final Class $get$$class$org$codehaus$groovy$reflection$ReflectionUtils = $get$$class$org$codehaus$groovy$reflection$ReflectionUtils();
                Object o2;
                if (!DefaultTypeTransformation.booleanUnbox(o2 = $getCallSiteArray[45].callGetProperty(args))) {
                    o2 = GrapeIvy.$const$1;
                }
                o = callSite2.call($get$$class$org$codehaus$groovy$reflection$ReflectionUtils, o2);
            }
            for (loader = callSite.callGetPropertySafe(o); DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox(loader) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[46].callCurrent(this, loader))) ? Boolean.TRUE : Boolean.FALSE); loader = $getCallSiteArray[47].callGetProperty(loader)) {}
            if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[48].callCurrent(this, loader))) {
                throw (Throwable)$getCallSiteArray[49].callConstructor($get$$class$java$lang$RuntimeException(), "No suitable ClassLoader found for grab");
            }
        }
        return loader;
    }
    
    private boolean isValidTargetClassLoader(final Object loader) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType($getCallSiteArray[50].callCurrent(this, $getCallSiteArray[51].callGetPropertySafe(loader)), $get$$class$java$lang$Boolean()));
    }
    
    private boolean isValidTargetClassLoaderClass(final Class loaderClass) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return DefaultTypeTransformation.booleanUnbox(ScriptBytecodeAdapter.castToType((ScriptBytecodeAdapter.compareNotEqual(loaderClass, null) && DefaultTypeTransformation.booleanUnbox((!DefaultTypeTransformation.booleanUnbox((!ScriptBytecodeAdapter.compareEqual($getCallSiteArray[52].callGetProperty(loaderClass), "groovy.lang.GroovyClassLoader") && !ScriptBytecodeAdapter.compareEqual($getCallSiteArray[53].callGetProperty(loaderClass), "org.codehaus.groovy.tools.RootLoader")) ? Boolean.FALSE : Boolean.TRUE) && !DefaultTypeTransformation.booleanUnbox($getCallSiteArray[54].callCurrent(this, $getCallSiteArray[55].callGetProperty(loaderClass)))) ? Boolean.FALSE : Boolean.TRUE)) ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean()));
    }
    
    public IvyGrabRecord createGrabRecord(final Map deps) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object object;
        if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[56].callGetProperty(deps))) {
            if (!DefaultTypeTransformation.booleanUnbox(object = $getCallSiteArray[57].callGetProperty(deps))) {
                object = $getCallSiteArray[58].callGetProperty(deps);
            }
        }
        final String module = (String)ScriptBytecodeAdapter.castToType(object, $get$$class$java$lang$String());
        if (!DefaultTypeTransformation.booleanUnbox(module)) {
            throw (Throwable)$getCallSiteArray[59].callConstructor($get$$class$java$lang$RuntimeException(), "grab requires at least a module: or artifactId: or artifact: argument");
        }
        Object object2;
        if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[60].callGetProperty(deps))) {
            if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[61].callGetProperty(deps))) {
                if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[62].callGetProperty(deps))) {
                    if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[63].callGetProperty(deps))) {
                        if (!DefaultTypeTransformation.booleanUnbox(object2 = $getCallSiteArray[64].callGetProperty(deps))) {
                            object2 = "";
                        }
                    }
                }
            }
        }
        final String groupId = (String)ScriptBytecodeAdapter.castToType(object2, $get$$class$java$lang$String());
        Object object3;
        if (!DefaultTypeTransformation.booleanUnbox(object3 = $getCallSiteArray[65].callGetProperty(deps))) {
            if (!DefaultTypeTransformation.booleanUnbox(object3 = $getCallSiteArray[66].callGetProperty(deps))) {
                if (!DefaultTypeTransformation.booleanUnbox(object3 = $getCallSiteArray[67].callGetProperty(deps))) {
                    object3 = "*";
                }
            }
        }
        String version = (String)ScriptBytecodeAdapter.castToType(object3, $get$$class$java$lang$String());
        if (ScriptBytecodeAdapter.compareEqual("*", version)) {
            version = "latest.default";
        }
        final ModuleRevisionId mrid = (ModuleRevisionId)ScriptBytecodeAdapter.castToType($getCallSiteArray[68].call($get$$class$org$apache$ivy$core$module$id$ModuleRevisionId(), groupId, module, version), $get$$class$org$apache$ivy$core$module$id$ModuleRevisionId());
        final Boolean force = (Boolean)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox($getCallSiteArray[69].call(deps, "force")) ? $getCallSiteArray[70].callGetProperty(deps) : Boolean.TRUE, $get$$class$java$lang$Boolean());
        final Boolean changing = (Boolean)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox($getCallSiteArray[71].call(deps, "changing")) ? $getCallSiteArray[72].callGetProperty(deps) : Boolean.FALSE, $get$$class$java$lang$Boolean());
        final Boolean transitive = (Boolean)ScriptBytecodeAdapter.castToType(DefaultTypeTransformation.booleanUnbox($getCallSiteArray[73].call(deps, "transitive")) ? $getCallSiteArray[74].callGetProperty(deps) : Boolean.TRUE, $get$$class$java$lang$Boolean());
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[75].callGetProperty(deps))) {
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[76].callGetProperty(deps))) {
                if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[77].callGetProperty(deps))) {
                    o = ScriptBytecodeAdapter.createList(new Object[] { "default" });
                }
            }
        }
        Object conf = o;
        if (conf instanceof String) {
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[78].call(conf, "[")) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[79].call(conf, "]"))) ? Boolean.TRUE : Boolean.FALSE)) {
                conf = $getCallSiteArray[80].call(conf, ScriptBytecodeAdapter.createRange(GrapeIvy.$const$1, GrapeIvy.$const$2, true));
            }
            conf = $getCallSiteArray[81].call($getCallSiteArray[82].call(conf, ","));
        }
        Object callGetProperty;
        if (!DefaultTypeTransformation.booleanUnbox(callGetProperty = $getCallSiteArray[83].callGetProperty(deps))) {
            callGetProperty = null;
        }
        final Object classifier = callGetProperty;
        return (IvyGrabRecord)ScriptBytecodeAdapter.castToType($getCallSiteArray[84].callConstructor($get$$class$groovy$grape$IvyGrabRecord(), ScriptBytecodeAdapter.createMap(new Object[] { "mrid", mrid, "conf", conf, "changing", changing, "transitive", transitive, "force", force, "classifier", classifier })), $get$$class$groovy$grape$IvyGrabRecord());
    }
    
    public Object grab(final String endorsedModule) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        return $getCallSiteArray[85].callCurrent(this, ScriptBytecodeAdapter.createMap(new Object[] { "group", "groovy.endorsed", "module", endorsedModule, "version", $getCallSiteArray[86].callGetProperty($get$$class$groovy$lang$GroovySystem()) }));
    }
    
    public Object grab(final Map args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Object messageArgument;
        if (!DefaultTypeTransformation.booleanUnbox(messageArgument = $getCallSiteArray[87].callGetProperty(args))) {
            messageArgument = $getCallSiteArray[88].call(DefaultTypeTransformation.box(GrapeIvy.DEFAULT_DEPTH), GrapeIvy.$const$1);
        }
        ScriptBytecodeAdapter.setProperty(messageArgument, $get$$class$groovy$grape$GrapeIvy(), args, "calleeDepth");
        return $getCallSiteArray[89].callCurrent(this, args, args);
    }
    
    public Object grab(final Map args, final Map... dependencies) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_3       
        //     4: aconst_null    
        //     5: astore          loader
        //     7: aload_3        
        //     8: ldc_w           90
        //    11: aaload         
        //    12: aload_0         /* this */
        //    13: getfield        groovy/grape/GrapeIvy.grabRecordsForCurrDependencies:Ljava/util/Set;
        //    16: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    21: pop            
        //    22: aload_3        
        //    23: ldc_w           91
        //    26: aaload         
        //    27: aload_0         /* this */
        //    28: bipush          6
        //    30: anewarray       Ljava/lang/Object;
        //    33: dup            
        //    34: iconst_0       
        //    35: ldc_w           "classLoader"
        //    38: aastore        
        //    39: dup            
        //    40: iconst_1       
        //    41: aload_3        
        //    42: ldc_w           92
        //    45: aaload         
        //    46: aload_1         /* args */
        //    47: ldc_w           "classLoader"
        //    50: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    55: aastore        
        //    56: dup            
        //    57: iconst_2       
        //    58: ldc_w           "refObject"
        //    61: aastore        
        //    62: dup            
        //    63: iconst_3       
        //    64: aload_3        
        //    65: ldc_w           93
        //    68: aaload         
        //    69: aload_1         /* args */
        //    70: ldc_w           "refObject"
        //    73: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    78: aastore        
        //    79: dup            
        //    80: iconst_4       
        //    81: ldc_w           "calleeDepth"
        //    84: aastore        
        //    85: dup            
        //    86: iconst_5       
        //    87: aload_3        
        //    88: ldc_w           94
        //    91: aaload         
        //    92: aload_1         /* args */
        //    93: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //    98: dup            
        //    99: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   102: ifeq            108
        //   105: goto            115
        //   108: pop            
        //   109: getstatic       groovy/grape/GrapeIvy.DEFAULT_DEPTH:I
        //   112: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.box:(I)Ljava/lang/Object;
        //   115: aastore        
        //   116: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.createMap:([Ljava/lang/Object;)Ljava/util/Map;
        //   119: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   124: dup            
        //   125: astore          loader
        //   127: pop            
        //   128: aload           loader
        //   130: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   133: ifne            140
        //   136: iconst_1       
        //   137: goto            141
        //   140: iconst_0       
        //   141: ifeq            155
        //   144: aconst_null    
        //   145: astore          5
        //   147: nop            
        //   148: nop            
        //   149: aload           5
        //   151: areturn        
        //   152: goto            155
        //   155: aconst_null    
        //   156: astore          uri
        //   158: aload_3        
        //   159: ldc_w           95
        //   162: aaload         
        //   163: aload_3        
        //   164: ldc_w           96
        //   167: aaload         
        //   168: aload_0         /* this */
        //   169: aload           loader
        //   171: aload_1         /* args */
        //   172: aload_2         /* dependencies */
        //   173: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   178: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   183: astore          6
        //   185: aload           6
        //   187: invokeinterface java/util/Iterator.hasNext:()Z
        //   192: ifeq            232
        //   195: aload           6
        //   197: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   202: astore          uri
        //   204: aload_3        
        //   205: ldc_w           97
        //   208: aaload         
        //   209: aload           loader
        //   211: aload_3        
        //   212: ldc_w           98
        //   215: aaload         
        //   216: aload           uri
        //   218: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   223: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   228: pop            
        //   229: goto            185
        //   232: goto            332
        //   235: astore          e
        //   237: aload_3        
        //   238: ldc_w           99
        //   241: aaload         
        //   242: aload_0         /* this */
        //   243: aload           loader
        //   245: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callCurrent:(Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;
        //   250: invokestatic    groovy/grape/GrapeIvy.$get$$class$java$util$Set:()Ljava/lang/Class;
        //   253: invokestatic    org/codehaus/groovy/runtime/ScriptBytecodeAdapter.castToType:(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
        //   256: checkcast       Ljava/util/Set;
        //   259: astore          grabRecordsForCurrLoader
        //   261: aload_3        
        //   262: ldc_w           100
        //   265: aaload         
        //   266: aload           grabRecordsForCurrLoader
        //   268: aload_0         /* this */
        //   269: getfield        groovy/grape/GrapeIvy.grabRecordsForCurrDependencies:Ljava/util/Set;
        //   272: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   277: pop            
        //   278: aload_3        
        //   279: ldc_w           101
        //   282: aaload         
        //   283: aload_0         /* this */
        //   284: getfield        groovy/grape/GrapeIvy.grabRecordsForCurrDependencies:Ljava/util/Set;
        //   287: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //   292: pop            
        //   293: aload_3        
        //   294: ldc_w           102
        //   297: aaload         
        //   298: aload_1         /* args */
        //   299: invokeinterface org/codehaus/groovy/runtime/callsite/CallSite.callGetProperty:(Ljava/lang/Object;)Ljava/lang/Object;
        //   304: invokestatic    org/codehaus/groovy/runtime/typehandling/DefaultTypeTransformation.booleanUnbox:(Ljava/lang/Object;)Z
        //   307: ifeq            322
        //   310: aload           e
        //   312: astore          7
        //   314: nop            
        //   315: nop            
        //   316: aload           7
        //   318: areturn        
        //   319: goto            328
        //   322: aload           e
        //   324: checkcast       Ljava/lang/Throwable;
        //   327: athrow         
        //   328: nop            
        //   329: goto            332
        //   332: nop            
        //   333: goto            341
        //   336: astore          5
        //   338: aload           5
        //   340: athrow         
        //   341: aconst_null    
        //   342: areturn        
        //   343: nop            
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     148    235    319    Ljava/lang/Exception;
        //  149    235    235    319    Ljava/lang/Exception;
        //  22     148    336    341    Any
        //  149    235    336    341    Any
        //  235    315    336    341    Any
        //  316    329    336    341    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2895)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public ResolveReport getDependencies(final Map args, final IvyGrabRecord... grabRecords) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ResolutionCacheManager cacheManager = (ResolutionCacheManager)ScriptBytecodeAdapter.castToType($getCallSiteArray[103].callGetProperty(this.ivyInstance), $get$$class$org$apache$ivy$core$cache$ResolutionCacheManager());
        Object md = $getCallSiteArray[104].callConstructor($get$$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor(), $getCallSiteArray[105].call($get$$class$org$apache$ivy$core$module$id$ModuleRevisionId(), "caller", "all-caller", "working"), "integration", null, Boolean.TRUE);
        $getCallSiteArray[106].call(md, $getCallSiteArray[107].callConstructor($get$$class$org$apache$ivy$core$module$descriptor$Configuration(), "default"));
        $getCallSiteArray[108].call(md, $getCallSiteArray[109].call($get$$class$java$lang$System()));
        $getCallSiteArray[110].callCurrent(this, args, md);
        IvyGrabRecord grabRecord = null;
        final Object call = $getCallSiteArray[111].call(grabRecords);
        while (((Iterator)call).hasNext()) {
            grabRecord = ((Iterator<IvyGrabRecord>)call).next();
            final DefaultDependencyDescriptor dd = (DefaultDependencyDescriptor)new Reference($getCallSiteArray[112].callConstructor($get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor(), ArrayUtil.createArray(md, $getCallSiteArray[113].callGroovyObjectGetProperty(grabRecord), $getCallSiteArray[114].callGroovyObjectGetProperty(grabRecord), $getCallSiteArray[115].callGroovyObjectGetProperty(grabRecord), $getCallSiteArray[116].callGroovyObjectGetProperty(grabRecord))));
            Object o;
            if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[117].callGroovyObjectGetProperty(grabRecord))) {
                o = ScriptBytecodeAdapter.createList(new Object[] { "*" });
            }
            final Object conf = o;
            $getCallSiteArray[118].call(conf, new GrapeIvy$_getDependencies_closure2(this, this, (Reference<Object>)dd));
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[119].callGroovyObjectGetProperty(grabRecord))) {
                final CallSite callSite = $getCallSiteArray[120];
                final Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor = $get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor();
                final Object value = ((Reference<Object>)dd).get();
                final Object callGetProperty = $getCallSiteArray[121].callGetProperty($getCallSiteArray[122].callGroovyObjectGetProperty(grabRecord));
                final String arg2 = "jar";
                Object callGroovyObjectGetProperty;
                if (!DefaultTypeTransformation.booleanUnbox(callGroovyObjectGetProperty = $getCallSiteArray[123].callGroovyObjectGetProperty(grabRecord))) {
                    callGroovyObjectGetProperty = "jar";
                }
                final Object dad = new Reference(callSite.callConstructor($get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor, ArrayUtil.createArray(value, callGetProperty, arg2, callGroovyObjectGetProperty, null, ScriptBytecodeAdapter.createMap(new Object[] { "classifier", $getCallSiteArray[124].callGroovyObjectGetProperty(grabRecord) }))));
                $getCallSiteArray[125].call(conf, new GrapeIvy$_getDependencies_closure3(this, this, (Reference<Object>)dad));
                $getCallSiteArray[126].call(((Reference<Object>)dd).get(), "default", ((Reference<Object>)dad).get());
            }
            $getCallSiteArray[127].call(md, ((Reference<Object>)dd).get());
        }
        final ResolveOptions resolveOptions = (ResolveOptions)ScriptBytecodeAdapter.castToType($getCallSiteArray[128].call($getCallSiteArray[129].call($getCallSiteArray[130].call($getCallSiteArray[131].callConstructor($get$$class$org$apache$ivy$core$resolve$ResolveOptions()), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createList(new Object[] { "default" }), $get$array$$class$java$lang$String()), $get$array$$class$java$lang$String())), Boolean.FALSE), DefaultTypeTransformation.booleanUnbox($getCallSiteArray[132].call(args, "validate")) ? $getCallSiteArray[133].callGetProperty(args) : Boolean.FALSE), $get$$class$org$apache$ivy$core$resolve$ResolveOptions());
        ScriptBytecodeAdapter.setProperty(DefaultTypeTransformation.booleanUnbox($getCallSiteArray[134].callGetProperty(args)) ? "downloadGrapes" : "cachedGrapes", $get$$class$groovy$grape$GrapeIvy(), $getCallSiteArray[135].callGetProperty(this.ivyInstance), "defaultResolver");
        final Boolean reportDownloads = (Boolean)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareEqual($getCallSiteArray[136].call($get$$class$java$lang$System(), "groovy.grape.report.downloads", "false"), "true") ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean());
        if (DefaultTypeTransformation.booleanUnbox(reportDownloads)) {
            $getCallSiteArray[137].call($getCallSiteArray[138].callGetProperty(this.ivyInstance), ScriptBytecodeAdapter.createPojoWrapper(ScriptBytecodeAdapter.asType(ScriptBytecodeAdapter.createMap(new Object[] { "progress", new GrapeIvy$_getDependencies_closure4(this, this) }), $get$$class$org$apache$ivy$core$event$IvyListener()), $get$$class$org$apache$ivy$core$event$IvyListener()));
        }
        final ResolveReport report = (ResolveReport)ScriptBytecodeAdapter.castToType($getCallSiteArray[139].call(this.ivyInstance, md, resolveOptions), $get$$class$org$apache$ivy$core$report$ResolveReport());
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[140].call(report))) {
            throw (Throwable)$getCallSiteArray[141].callConstructor($get$$class$java$lang$RuntimeException(), new GStringImpl(new Object[] { $getCallSiteArray[142].callGetProperty(report) }, new String[] { "Error grabbing Grapes -- ", "" }));
        }
        if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[143].callGetProperty(report)) && DefaultTypeTransformation.booleanUnbox(reportDownloads)) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[144].call($getCallSiteArray[145].callGetProperty($get$$class$java$lang$System()), new GStringImpl(new Object[] { $getCallSiteArray[146].call($getCallSiteArray[147].callGetProperty(report), GrapeIvy.$const$3), $getCallSiteArray[148].callGetProperty(report), $getCallSiteArray[149].call(ScriptBytecodeAdapter.invokeMethod0SpreadSafe($get$$class$groovy$grape$GrapeIvy(), $getCallSiteArray[150].callGetProperty(report), "toString"), "\n  ") }, new String[] { "Downloaded ", " Kbytes in ", "ms:\n  ", "" }));
        }
        md = $getCallSiteArray[151].callGetProperty(report);
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[152].callGetProperty(args))) {
            $getCallSiteArray[153].call($getCallSiteArray[154].call(cacheManager, $getCallSiteArray[155].callGetProperty(md)));
            $getCallSiteArray[156].call($getCallSiteArray[157].call(cacheManager, $getCallSiteArray[158].callGetProperty(md)));
        }
        return (ResolveReport)ScriptBytecodeAdapter.castToType(report, $get$$class$org$apache$ivy$core$report$ResolveReport());
    }
    
    private Object addExcludesIfNeeded(final Map args, final DefaultModuleDescriptor md) {
        final DefaultModuleDescriptor md2 = (DefaultModuleDescriptor)new Reference(md);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[159].call(args, "excludes"))) {
            return null;
        }
        return $getCallSiteArray[160].call($getCallSiteArray[161].callGetProperty(args), new GrapeIvy$_addExcludesIfNeeded_closure5(this, this, (Reference<Object>)md2));
    }
    
    public Map<String, Map<String, List<String>>> enumerateGrapes() {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Map bunches = (Map)new Reference(ScriptBytecodeAdapter.createMap(new Object[0]));
        final Pattern ivyFilePattern = (Pattern)new Reference(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.bitwiseNegate("ivy-(.*)\\.xml"), $get$$class$java$util$regex$Pattern()));
        $getCallSiteArray[162].call($getCallSiteArray[163].callGroovyObjectGetProperty(this), new GrapeIvy$_enumerateGrapes_closure6(this, this, (Reference<Object>)bunches, (Reference<Object>)ivyFilePattern));
        return (Map<String, Map<String, List<String>>>)ScriptBytecodeAdapter.castToType(((Reference)bunches).get(), $get$$class$java$util$Map());
    }
    
    public URI[] resolve(final Map args, final Map... dependencies) {
        return (URI[])ScriptBytecodeAdapter.castToType($getCallSiteArray()[164].callCurrent(this, args, null, dependencies), $get$array$$class$java$net$URI());
    }
    
    public URI[] resolve(final Map args, final List depsInfo, final Map... dependencies) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final CallSite callSite = $getCallSiteArray[165];
        final Object[] values = { "classLoader", $getCallSiteArray[166].call(args, "classLoader"), "refObject", $getCallSiteArray[167].call(args, "refObject"), "calleeDepth", null };
        final int n = 5;
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[168].callGetProperty(args))) {
            o = DefaultTypeTransformation.box(GrapeIvy.DEFAULT_DEPTH);
        }
        values[n] = o;
        final Object loader = callSite.callCurrent(this, ScriptBytecodeAdapter.createMap(values));
        if (!DefaultTypeTransformation.booleanUnbox(loader)) {
            return (URI[])ScriptBytecodeAdapter.castToType(null, $get$array$$class$java$net$URI());
        }
        return (URI[])ScriptBytecodeAdapter.castToType($getCallSiteArray[169].callCurrent(this, loader, args, depsInfo, dependencies), $get$array$$class$java$net$URI());
    }
    
    public URI[] resolve(final ClassLoader loader, final Map args, final Map... dependencies) {
        return (URI[])ScriptBytecodeAdapter.castToType($getCallSiteArray()[170].callCurrent(this, loader, args, null, dependencies), $get$array$$class$java$net$URI());
    }
    
    public URI[] resolve(final ClassLoader loader, final Map args, final List depsInfo, final Map... dependencies) {
        final List depsInfo2 = (List)new Reference(depsInfo);
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final Set keys = (Set)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[171].call(args), $get$$class$java$util$Set()));
        $getCallSiteArray[172].call(((Reference)keys).get(), new GrapeIvy$_resolve_closure7(this, this, (Reference<Object>)keys));
        if (!DefaultTypeTransformation.booleanUnbox(DefaultTypeTransformation.box(this.enableGrapes))) {
            return (URI[])ScriptBytecodeAdapter.castToType(null, $get$array$$class$java$net$URI());
        }
        final Boolean populateDepsInfo = (Boolean)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.compareNotEqual(((Reference)depsInfo2).get(), null) ? Boolean.TRUE : Boolean.FALSE, $get$$class$java$lang$Boolean());
        final Set localDeps = (Set)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[173].callCurrent(this, loader), $get$$class$java$util$Set()));
        $getCallSiteArray[174].call(dependencies, new GrapeIvy$_resolve_closure8(this, this, (Reference<Object>)localDeps));
        final ResolveReport report = (ResolveReport)ScriptBytecodeAdapter.castToType($getCallSiteArray[175].callCurrent(this, ScriptBytecodeAdapter.despreadList(new Object[] { args }, new Object[] { $getCallSiteArray[176].call($getCallSiteArray[177].call(((Reference)localDeps).get())) }, new int[] { DefaultTypeTransformation.intUnbox(ScriptBytecodeAdapter.castToType(1, Integer.TYPE)) })), $get$$class$org$apache$ivy$core$report$ResolveReport());
        List results = ScriptBytecodeAdapter.createList(new Object[0]);
        ArtifactDownloadReport adl = null;
        final Object call = $getCallSiteArray[178].call($getCallSiteArray[179].callGetProperty(report));
        while (((Iterator)call).hasNext()) {
            adl = ((Iterator<ArtifactDownloadReport>)call).next();
            if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[180].callGetProperty(adl))) {
                results = (List)ScriptBytecodeAdapter.castToType($getCallSiteArray[181].call(results, $getCallSiteArray[182].call($getCallSiteArray[183].callGetProperty(adl))), $get$$class$java$util$List());
            }
        }
        if (DefaultTypeTransformation.booleanUnbox(populateDepsInfo)) {
            final Object deps = $getCallSiteArray[184].callGetProperty(report);
            $getCallSiteArray[185].call(deps, new GrapeIvy$_resolve_closure9(this, this, (Reference<Object>)depsInfo2));
        }
        return (URI[])ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.asType(results, $get$array$$class$java$net$URI()), $get$array$$class$java$net$URI());
    }
    
    private Set<IvyGrabRecord> getLoadedDepsForLoader(final ClassLoader loader) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        Set localDeps = (Set)ScriptBytecodeAdapter.castToType($getCallSiteArray[186].call(this.loadedDeps, loader), $get$$class$java$util$Set());
        if (ScriptBytecodeAdapter.compareEqual(localDeps, null)) {
            localDeps = (Set)$getCallSiteArray[187].callConstructor($get$$class$java$util$LinkedHashSet());
            $getCallSiteArray[188].call(this.loadedDeps, loader, localDeps);
        }
        return (Set<IvyGrabRecord>)ScriptBytecodeAdapter.castToType(localDeps, $get$$class$java$util$Set());
    }
    
    public Map[] listDependencies(final ClassLoader classLoader) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        if (DefaultTypeTransformation.booleanUnbox($getCallSiteArray[189].call(this.loadedDeps, classLoader))) {
            final List results = (List)new Reference(ScriptBytecodeAdapter.createList(new Object[0]));
            $getCallSiteArray[190].call($getCallSiteArray[191].call(this.loadedDeps, classLoader), new GrapeIvy$_listDependencies_closure10(this, this, (Reference<Object>)results));
            return (Map[])ScriptBytecodeAdapter.castToType(((Reference)results).get(), $get$array$$class$java$util$Map());
        }
        return (Map[])ScriptBytecodeAdapter.castToType(null, $get$array$$class$java$util$Map());
    }
    
    public void addResolver(final Map<String, Object> args) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        final ChainResolver chainResolver = (ChainResolver)ScriptBytecodeAdapter.castToType($getCallSiteArray[192].call(this.settings, "downloadGrapes"), $get$$class$org$apache$ivy$plugins$resolver$ChainResolver());
        final CallSite callSite = $getCallSiteArray[193];
        final Class $get$$class$org$apache$ivy$plugins$resolver$IBiblioResolver = $get$$class$org$apache$ivy$plugins$resolver$IBiblioResolver();
        final Object[] values = { "name", $getCallSiteArray[194].callGetProperty(args), "root", $getCallSiteArray[195].callGetProperty(args), "m2compatible", null, null, null };
        final int n = 5;
        Object o;
        if (!DefaultTypeTransformation.booleanUnbox(o = $getCallSiteArray[196].callGetProperty(args))) {
            o = Boolean.TRUE;
        }
        values[n] = o;
        values[6] = "settings";
        values[7] = this.settings;
        final IBiblioResolver resolver = (IBiblioResolver)callSite.callConstructor($get$$class$org$apache$ivy$plugins$resolver$IBiblioResolver, ScriptBytecodeAdapter.createMap(values));
        $getCallSiteArray[197].call(chainResolver, resolver);
        this.ivyInstance = (Ivy)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType($getCallSiteArray[198].call($get$$class$org$apache$ivy$Ivy(), this.settings), $get$$class$org$apache$ivy$Ivy()), $get$$class$org$apache$ivy$Ivy());
        this.resolvedDependencies = (Set<String>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$Set()), $get$$class$java$util$Set());
        this.downloadedArtifacts = (Set<String>)ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.castToType(ScriptBytecodeAdapter.createList(new Object[0]), $get$$class$java$util$Set()), $get$$class$java$util$Set());
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$grape$GrapeIvy()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = GrapeIvy.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (GrapeIvy.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        GrapeIvy.__timeStamp__239_neverHappen1292524202418 = 0L;
        GrapeIvy.__timeStamp = 1292524202418L;
        $const$3 = 10;
        $const$2 = -2;
        $const$1 = 1;
        $const$0 = 3;
        DEFAULT_DEPTH = DefaultTypeTransformation.intUnbox(3);
    }
    
    public static final int getDEFAULT_DEPTH() {
        return GrapeIvy.DEFAULT_DEPTH;
    }
    
    public boolean getEnableGrapes() {
        return this.enableGrapes;
    }
    
    public boolean isEnableGrapes() {
        return this.enableGrapes;
    }
    
    public void setEnableGrapes(final boolean enableGrapes) {
        this.enableGrapes = enableGrapes;
    }
    
    public Ivy getIvyInstance() {
        return this.ivyInstance;
    }
    
    public void setIvyInstance(final Ivy ivyInstance) {
        this.ivyInstance = ivyInstance;
    }
    
    public Set<String> getResolvedDependencies() {
        return this.resolvedDependencies;
    }
    
    public void setResolvedDependencies(final Set<String> resolvedDependencies) {
        this.resolvedDependencies = resolvedDependencies;
    }
    
    public Set<String> getDownloadedArtifacts() {
        return this.downloadedArtifacts;
    }
    
    public void setDownloadedArtifacts(final Set<String> downloadedArtifacts) {
        this.downloadedArtifacts = downloadedArtifacts;
    }
    
    public Map<ClassLoader, Set<IvyGrabRecord>> getLoadedDeps() {
        return this.loadedDeps;
    }
    
    public void setLoadedDeps(final Map<ClassLoader, Set<IvyGrabRecord>> loadedDeps) {
        this.loadedDeps = loadedDeps;
    }
    
    public Set<IvyGrabRecord> getGrabRecordsForCurrDependencies() {
        return this.grabRecordsForCurrDependencies;
    }
    
    public void setGrabRecordsForCurrDependencies(final Set<IvyGrabRecord> grabRecordsForCurrDependencies) {
        this.grabRecordsForCurrDependencies = grabRecordsForCurrDependencies;
    }
    
    public IvySettings getSettings() {
        return this.settings;
    }
    
    public void setSettings(final IvySettings settings) {
        this.settings = settings;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[199];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$grape$GrapeIvy(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (GrapeIvy.$callSiteArray == null || ($createCallSiteArray = GrapeIvy.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            GrapeIvy.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$event$IvyListener() {
        Class $class$org$apache$ivy$core$event$IvyListener;
        if (($class$org$apache$ivy$core$event$IvyListener = GrapeIvy.$class$org$apache$ivy$core$event$IvyListener) == null) {
            $class$org$apache$ivy$core$event$IvyListener = (GrapeIvy.$class$org$apache$ivy$core$event$IvyListener = class$("org.apache.ivy.core.event.IvyListener"));
        }
        return $class$org$apache$ivy$core$event$IvyListener;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$reflection$ReflectionUtils() {
        Class $class$org$codehaus$groovy$reflection$ReflectionUtils;
        if (($class$org$codehaus$groovy$reflection$ReflectionUtils = GrapeIvy.$class$org$codehaus$groovy$reflection$ReflectionUtils) == null) {
            $class$org$codehaus$groovy$reflection$ReflectionUtils = (GrapeIvy.$class$org$codehaus$groovy$reflection$ReflectionUtils = class$("org.codehaus.groovy.reflection.ReflectionUtils"));
        }
        return $class$org$codehaus$groovy$reflection$ReflectionUtils;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$GroovySystem() {
        Class $class$groovy$lang$GroovySystem;
        if (($class$groovy$lang$GroovySystem = GrapeIvy.$class$groovy$lang$GroovySystem) == null) {
            $class$groovy$lang$GroovySystem = (GrapeIvy.$class$groovy$lang$GroovySystem = class$("groovy.lang.GroovySystem"));
        }
        return $class$groovy$lang$GroovySystem;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$RuntimeException() {
        Class $class$java$lang$RuntimeException;
        if (($class$java$lang$RuntimeException = GrapeIvy.$class$java$lang$RuntimeException) == null) {
            $class$java$lang$RuntimeException = (GrapeIvy.$class$java$lang$RuntimeException = class$("java.lang.RuntimeException"));
        }
        return $class$java$lang$RuntimeException;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$util$Map() {
        Class array$$class$java$util$Map;
        if ((array$$class$java$util$Map = GrapeIvy.array$$class$java$util$Map) == null) {
            array$$class$java$util$Map = (GrapeIvy.array$$class$java$util$Map = class$("[Ljava.util.Map;"));
        }
        return array$$class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$List() {
        Class $class$java$util$List;
        if (($class$java$util$List = GrapeIvy.$class$java$util$List) == null) {
            $class$java$util$List = (GrapeIvy.$class$java$util$List = class$("java.util.List"));
        }
        return $class$java$util$List;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = GrapeIvy.$class$java$lang$String) == null) {
            $class$java$lang$String = (GrapeIvy.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$Ivy() {
        Class $class$org$apache$ivy$Ivy;
        if (($class$org$apache$ivy$Ivy = GrapeIvy.$class$org$apache$ivy$Ivy) == null) {
            $class$org$apache$ivy$Ivy = (GrapeIvy.$class$org$apache$ivy$Ivy = class$("org.apache.ivy.Ivy"));
        }
        return $class$org$apache$ivy$Ivy;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Map() {
        Class $class$java$util$Map;
        if (($class$java$util$Map = GrapeIvy.$class$java$util$Map) == null) {
            $class$java$util$Map = (GrapeIvy.$class$java$util$Map = class$("java.util.Map"));
        }
        return $class$java$util$Map;
    }
    
    private static /* synthetic */ Class $get$$class$java$io$File() {
        Class $class$java$io$File;
        if (($class$java$io$File = GrapeIvy.$class$java$io$File) == null) {
            $class$java$io$File = (GrapeIvy.$class$java$io$File = class$("java.io.File"));
        }
        return $class$java$io$File;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$report$ResolveReport() {
        Class $class$org$apache$ivy$core$report$ResolveReport;
        if (($class$org$apache$ivy$core$report$ResolveReport = GrapeIvy.$class$org$apache$ivy$core$report$ResolveReport) == null) {
            $class$org$apache$ivy$core$report$ResolveReport = (GrapeIvy.$class$org$apache$ivy$core$report$ResolveReport = class$("org.apache.ivy.core.report.ResolveReport"));
        }
        return $class$org$apache$ivy$core$report$ResolveReport;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$id$ModuleRevisionId() {
        Class $class$org$apache$ivy$core$module$id$ModuleRevisionId;
        if (($class$org$apache$ivy$core$module$id$ModuleRevisionId = GrapeIvy.$class$org$apache$ivy$core$module$id$ModuleRevisionId) == null) {
            $class$org$apache$ivy$core$module$id$ModuleRevisionId = (GrapeIvy.$class$org$apache$ivy$core$module$id$ModuleRevisionId = class$("org.apache.ivy.core.module.id.ModuleRevisionId"));
        }
        return $class$org$apache$ivy$core$module$id$ModuleRevisionId;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$Set() {
        Class $class$java$util$Set;
        if (($class$java$util$Set = GrapeIvy.$class$java$util$Set) == null) {
            $class$java$util$Set = (GrapeIvy.$class$java$util$Set = class$("java.util.Set"));
        }
        return $class$java$util$Set;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$regex$Pattern() {
        Class $class$java$util$regex$Pattern;
        if (($class$java$util$regex$Pattern = GrapeIvy.$class$java$util$regex$Pattern) == null) {
            $class$java$util$regex$Pattern = (GrapeIvy.$class$java$util$regex$Pattern = class$("java.util.regex.Pattern"));
        }
        return $class$java$util$regex$Pattern;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$GrapeIvy() {
        Class $class$groovy$grape$GrapeIvy;
        if (($class$groovy$grape$GrapeIvy = GrapeIvy.$class$groovy$grape$GrapeIvy) == null) {
            $class$groovy$grape$GrapeIvy = (GrapeIvy.$class$groovy$grape$GrapeIvy = class$("groovy.grape.GrapeIvy"));
        }
        return $class$groovy$grape$GrapeIvy;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$resolve$ResolveOptions() {
        Class $class$org$apache$ivy$core$resolve$ResolveOptions;
        if (($class$org$apache$ivy$core$resolve$ResolveOptions = GrapeIvy.$class$org$apache$ivy$core$resolve$ResolveOptions) == null) {
            $class$org$apache$ivy$core$resolve$ResolveOptions = (GrapeIvy.$class$org$apache$ivy$core$resolve$ResolveOptions = class$("org.apache.ivy.core.resolve.ResolveOptions"));
        }
        return $class$org$apache$ivy$core$resolve$ResolveOptions;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$plugins$resolver$ChainResolver() {
        Class $class$org$apache$ivy$plugins$resolver$ChainResolver;
        if (($class$org$apache$ivy$plugins$resolver$ChainResolver = GrapeIvy.$class$org$apache$ivy$plugins$resolver$ChainResolver) == null) {
            $class$org$apache$ivy$plugins$resolver$ChainResolver = (GrapeIvy.$class$org$apache$ivy$plugins$resolver$ChainResolver = class$("org.apache.ivy.plugins.resolver.ChainResolver"));
        }
        return $class$org$apache$ivy$plugins$resolver$ChainResolver;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$net$URI() {
        Class array$$class$java$net$URI;
        if ((array$$class$java$net$URI = GrapeIvy.array$$class$java$net$URI) == null) {
            array$$class$java$net$URI = (GrapeIvy.array$$class$java$net$URI = class$("[Ljava.net.URI;"));
        }
        return array$$class$java$net$URI;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor() {
        Class $class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor;
        if (($class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor = GrapeIvy.$class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor) == null) {
            $class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor = (GrapeIvy.$class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor = class$("org.apache.ivy.core.module.descriptor.DefaultDependencyArtifactDescriptor"));
        }
        return $class$org$apache$ivy$core$module$descriptor$DefaultDependencyArtifactDescriptor;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$util$DefaultMessageLogger() {
        Class $class$org$apache$ivy$util$DefaultMessageLogger;
        if (($class$org$apache$ivy$util$DefaultMessageLogger = GrapeIvy.$class$org$apache$ivy$util$DefaultMessageLogger) == null) {
            $class$org$apache$ivy$util$DefaultMessageLogger = (GrapeIvy.$class$org$apache$ivy$util$DefaultMessageLogger = class$("org.apache.ivy.util.DefaultMessageLogger"));
        }
        return $class$org$apache$ivy$util$DefaultMessageLogger;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$util$Message() {
        Class $class$org$apache$ivy$util$Message;
        if (($class$org$apache$ivy$util$Message = GrapeIvy.$class$org$apache$ivy$util$Message) == null) {
            $class$org$apache$ivy$util$Message = (GrapeIvy.$class$org$apache$ivy$util$Message = class$("org.apache.ivy.util.Message"));
        }
        return $class$org$apache$ivy$util$Message;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$grape$IvyGrabRecord() {
        Class $class$groovy$grape$IvyGrabRecord;
        if (($class$groovy$grape$IvyGrabRecord = GrapeIvy.$class$groovy$grape$IvyGrabRecord) == null) {
            $class$groovy$grape$IvyGrabRecord = (GrapeIvy.$class$groovy$grape$IvyGrabRecord = class$("groovy.grape.IvyGrabRecord"));
        }
        return $class$groovy$grape$IvyGrabRecord;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$LinkedHashSet() {
        Class $class$java$util$LinkedHashSet;
        if (($class$java$util$LinkedHashSet = GrapeIvy.$class$java$util$LinkedHashSet) == null) {
            $class$java$util$LinkedHashSet = (GrapeIvy.$class$java$util$LinkedHashSet = class$("java.util.LinkedHashSet"));
        }
        return $class$java$util$LinkedHashSet;
    }
    
    private static /* synthetic */ Class $get$array$$class$java$lang$String() {
        Class array$$class$java$lang$String;
        if ((array$$class$java$lang$String = GrapeIvy.array$$class$java$lang$String) == null) {
            array$$class$java$lang$String = (GrapeIvy.array$$class$java$lang$String = class$("[Ljava.lang.String;"));
        }
        return array$$class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Integer() {
        Class $class$java$lang$Integer;
        if (($class$java$lang$Integer = GrapeIvy.$class$java$lang$Integer) == null) {
            $class$java$lang$Integer = (GrapeIvy.$class$java$lang$Integer = class$("java.lang.Integer"));
        }
        return $class$java$lang$Integer;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = GrapeIvy.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (GrapeIvy.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor() {
        Class $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor;
        if (($class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor = GrapeIvy.$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor) == null) {
            $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor = (GrapeIvy.$class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor = class$("org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor"));
        }
        return $class$org$apache$ivy$core$module$descriptor$DefaultModuleDescriptor;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$Configuration() {
        Class $class$org$apache$ivy$core$module$descriptor$Configuration;
        if (($class$org$apache$ivy$core$module$descriptor$Configuration = GrapeIvy.$class$org$apache$ivy$core$module$descriptor$Configuration) == null) {
            $class$org$apache$ivy$core$module$descriptor$Configuration = (GrapeIvy.$class$org$apache$ivy$core$module$descriptor$Configuration = class$("org.apache.ivy.core.module.descriptor.Configuration"));
        }
        return $class$org$apache$ivy$core$module$descriptor$Configuration;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$System() {
        Class $class$java$lang$System;
        if (($class$java$lang$System = GrapeIvy.$class$java$lang$System) == null) {
            $class$java$lang$System = (GrapeIvy.$class$java$lang$System = class$("java.lang.System"));
        }
        return $class$java$lang$System;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$Boolean() {
        Class $class$java$lang$Boolean;
        if (($class$java$lang$Boolean = GrapeIvy.$class$java$lang$Boolean) == null) {
            $class$java$lang$Boolean = (GrapeIvy.$class$java$lang$Boolean = class$("java.lang.Boolean"));
        }
        return $class$java$lang$Boolean;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$cache$ResolutionCacheManager() {
        Class $class$org$apache$ivy$core$cache$ResolutionCacheManager;
        if (($class$org$apache$ivy$core$cache$ResolutionCacheManager = GrapeIvy.$class$org$apache$ivy$core$cache$ResolutionCacheManager) == null) {
            $class$org$apache$ivy$core$cache$ResolutionCacheManager = (GrapeIvy.$class$org$apache$ivy$core$cache$ResolutionCacheManager = class$("org.apache.ivy.core.cache.ResolutionCacheManager"));
        }
        return $class$org$apache$ivy$core$cache$ResolutionCacheManager;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor() {
        Class $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor;
        if (($class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor = GrapeIvy.$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor) == null) {
            $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor = (GrapeIvy.$class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor = class$("org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor"));
        }
        return $class$org$apache$ivy$core$module$descriptor$DefaultDependencyDescriptor;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$plugins$resolver$IBiblioResolver() {
        Class $class$org$apache$ivy$plugins$resolver$IBiblioResolver;
        if (($class$org$apache$ivy$plugins$resolver$IBiblioResolver = GrapeIvy.$class$org$apache$ivy$plugins$resolver$IBiblioResolver) == null) {
            $class$org$apache$ivy$plugins$resolver$IBiblioResolver = (GrapeIvy.$class$org$apache$ivy$plugins$resolver$IBiblioResolver = class$("org.apache.ivy.plugins.resolver.IBiblioResolver"));
        }
        return $class$org$apache$ivy$plugins$resolver$IBiblioResolver;
    }
    
    private static /* synthetic */ Class $get$$class$java$util$WeakHashMap() {
        Class $class$java$util$WeakHashMap;
        if (($class$java$util$WeakHashMap = GrapeIvy.$class$java$util$WeakHashMap) == null) {
            $class$java$util$WeakHashMap = (GrapeIvy.$class$java$util$WeakHashMap = class$("java.util.WeakHashMap"));
        }
        return $class$java$util$WeakHashMap;
    }
    
    private static /* synthetic */ Class $get$$class$org$apache$ivy$core$settings$IvySettings() {
        Class $class$org$apache$ivy$core$settings$IvySettings;
        if (($class$org$apache$ivy$core$settings$IvySettings = GrapeIvy.$class$org$apache$ivy$core$settings$IvySettings) == null) {
            $class$org$apache$ivy$core$settings$IvySettings = (GrapeIvy.$class$org$apache$ivy$core$settings$IvySettings = class$("org.apache.ivy.core.settings.IvySettings"));
        }
        return $class$org$apache$ivy$core$settings$IvySettings;
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
