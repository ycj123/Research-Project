// 
// Decompiled by Procyon v0.5.36
// 

package groovy.jmx.builder;

import java.util.HashMap;
import groovy.lang.Closure;
import javax.management.AttributeChangeNotification;
import java.util.concurrent.atomic.AtomicLong;
import javax.management.Notification;
import javax.management.ReflectionException;
import javax.management.MBeanServer;
import java.util.Map;
import javax.management.modelmbean.ModelMBeanInfo;
import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.management.InstanceNotFoundException;
import javax.management.RuntimeOperationsException;
import javax.management.MBeanException;
import java.util.ArrayList;
import java.util.List;
import javax.management.NotificationListener;
import javax.management.modelmbean.RequiredModelMBean;

public class JmxBuilderModelMBean extends RequiredModelMBean implements NotificationListener
{
    private List<String> methodListeners;
    private Object managedObject;
    
    public JmxBuilderModelMBean(final Object objectRef) throws MBeanException, RuntimeOperationsException, InstanceNotFoundException, InvalidTargetObjectTypeException {
        this.methodListeners = new ArrayList<String>(0);
        super.setManagedResource(objectRef, "ObjectReference");
    }
    
    public JmxBuilderModelMBean() throws MBeanException, RuntimeOperationsException {
        this.methodListeners = new ArrayList<String>(0);
    }
    
    public JmxBuilderModelMBean(final ModelMBeanInfo mbi) throws MBeanException, RuntimeOperationsException {
        super(mbi);
        this.methodListeners = new ArrayList<String>(0);
    }
    
    public synchronized void setManagedResource(final Object obj) {
        this.managedObject = obj;
        try {
            super.setManagedResource(obj, "ObjectReference");
        }
        catch (Exception ex) {
            throw new JmxBuilderException(ex);
        }
    }
    
    public void addOperationCallListeners(final Map<String, Map> descriptor) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnonnull       5
        //     4: return         
        //     5: aload_1         /* descriptor */
        //     6: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    11: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    16: astore_2        /* i$ */
        //    17: aload_2         /* i$ */
        //    18: invokeinterface java/util/Iterator.hasNext:()Z
        //    23: ifeq            238
        //    26: aload_2         /* i$ */
        //    27: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    32: checkcast       Ljava/util/Map$Entry;
        //    35: astore_3        /* item */
        //    36: aload_3         /* item */
        //    37: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //    42: checkcast       Ljava/util/Map;
        //    45: ldc             "methodListener"
        //    47: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    52: ifeq            235
        //    55: aload_3         /* item */
        //    56: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //    61: checkcast       Ljava/util/Map;
        //    64: ldc             "methodListener"
        //    66: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    71: checkcast       Ljava/util/Map;
        //    74: astore          listener
        //    76: aload           listener
        //    78: ldc             "target"
        //    80: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    85: checkcast       Ljava/lang/String;
        //    88: astore          target
        //    90: aload_0         /* this */
        //    91: getfield        groovy/jmx/builder/JmxBuilderModelMBean.methodListeners:Ljava/util/List;
        //    94: aload           target
        //    96: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   101: pop            
        //   102: aload           listener
        //   104: ldc             "type"
        //   106: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   111: checkcast       Ljava/lang/String;
        //   114: astore          listenerType
        //   116: aload           listener
        //   118: ldc             "managedObject"
        //   120: aload_0         /* this */
        //   121: getfield        groovy/jmx/builder/JmxBuilderModelMBean.managedObject:Ljava/lang/Object;
        //   124: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   129: pop            
        //   130: aload           listenerType
        //   132: ldc             "attributeChangeListener"
        //   134: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   137: ifeq            176
        //   140: aload_0         /* this */
        //   141: invokestatic    groovy/jmx/builder/JmxBuilderModelMBean$AttributeChangedListener.getListener:()Lgroovy/jmx/builder/JmxBuilderModelMBean$AttributeChangedListener;
        //   144: aload           listener
        //   146: ldc             "attribute"
        //   148: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   153: checkcast       Ljava/lang/String;
        //   156: aload           listener
        //   158: invokevirtual   groovy/jmx/builder/JmxBuilderModelMBean.addAttributeChangeNotificationListener:(Ljavax/management/NotificationListener;Ljava/lang/String;Ljava/lang/Object;)V
        //   161: goto            176
        //   164: astore          e
        //   166: new             Lgroovy/jmx/builder/JmxBuilderException;
        //   169: dup            
        //   170: aload           e
        //   172: invokespecial   groovy/jmx/builder/JmxBuilderException.<init>:(Ljava/lang/Throwable;)V
        //   175: athrow         
        //   176: aload           listenerType
        //   178: ldc             "operationCallListener"
        //   180: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   183: ifeq            235
        //   186: new             Ljava/lang/StringBuilder;
        //   189: dup            
        //   190: invokespecial   java/lang/StringBuilder.<init>:()V
        //   193: ldc             "jmx.operation.call."
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: aload           target
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   206: astore          eventType
        //   208: new             Ljavax/management/NotificationFilterSupport;
        //   211: dup            
        //   212: invokespecial   javax/management/NotificationFilterSupport.<init>:()V
        //   215: astore          filter
        //   217: aload           filter
        //   219: aload           eventType
        //   221: invokevirtual   javax/management/NotificationFilterSupport.enableType:(Ljava/lang/String;)V
        //   224: aload_0         /* this */
        //   225: invokestatic    groovy/jmx/builder/JmxEventListener.getListner:()Lgroovy/jmx/builder/JmxEventListener;
        //   228: aload           filter
        //   230: aload           listener
        //   232: invokevirtual   groovy/jmx/builder/JmxBuilderModelMBean.addNotificationListener:(Ljavax/management/NotificationListener;Ljavax/management/NotificationFilter;Ljava/lang/Object;)V
        //   235: goto            17
        //   238: return         
        //    Signature:
        //  (Ljava/util/Map<Ljava/lang/String;Ljava/util/Map;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  140    161    164    176    Ljavax/management/MBeanException;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.accept(CoreMetadataFactory.java:653)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitMethod(TypeSubstitutionVisitor.java:276)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2591)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2669)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:881)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public void addEventListeners(final MBeanServer server, final Map<String, Map> descriptor) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //     6: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    11: astore_3        /* i$ */
        //    12: aload_3         /* i$ */
        //    13: invokeinterface java/util/Iterator.hasNext:()Z
        //    18: ifeq            143
        //    21: aload_3         /* i$ */
        //    22: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    27: checkcast       Ljava/util/Map$Entry;
        //    30: astore          item
        //    32: aload           item
        //    34: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //    39: checkcast       Ljava/util/Map;
        //    42: astore          listener
        //    44: aload           listener
        //    46: ldc             "from"
        //    48: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    53: checkcast       Ljavax/management/ObjectName;
        //    56: astore          broadcaster
        //    58: aload           listener
        //    60: ldc             "event"
        //    62: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    67: checkcast       Ljava/lang/String;
        //    70: astore          eventType
        //    72: aload           eventType
        //    74: ifnull          111
        //    77: new             Ljavax/management/NotificationFilterSupport;
        //    80: dup            
        //    81: invokespecial   javax/management/NotificationFilterSupport.<init>:()V
        //    84: astore          filter
        //    86: aload           filter
        //    88: aload           eventType
        //    90: invokevirtual   javax/management/NotificationFilterSupport.enableType:(Ljava/lang/String;)V
        //    93: aload_1         /* server */
        //    94: aload           broadcaster
        //    96: invokestatic    groovy/jmx/builder/JmxEventListener.getListner:()Lgroovy/jmx/builder/JmxEventListener;
        //    99: aload           filter
        //   101: aload           listener
        //   103: invokeinterface javax/management/MBeanServer.addNotificationListener:(Ljavax/management/ObjectName;Ljavax/management/NotificationListener;Ljavax/management/NotificationFilter;Ljava/lang/Object;)V
        //   108: goto            125
        //   111: aload_1         /* server */
        //   112: aload           broadcaster
        //   114: invokestatic    groovy/jmx/builder/JmxEventListener.getListner:()Lgroovy/jmx/builder/JmxEventListener;
        //   117: aconst_null    
        //   118: aload           listener
        //   120: invokeinterface javax/management/MBeanServer.addNotificationListener:(Ljavax/management/ObjectName;Ljavax/management/NotificationListener;Ljavax/management/NotificationFilter;Ljava/lang/Object;)V
        //   125: goto            140
        //   128: astore          e
        //   130: new             Lgroovy/jmx/builder/JmxBuilderException;
        //   133: dup            
        //   134: aload           e
        //   136: invokespecial   groovy/jmx/builder/JmxBuilderException.<init>:(Ljava/lang/Throwable;)V
        //   139: athrow         
        //   140: goto            12
        //   143: return         
        //    Signature:
        //  (Ljavax/management/MBeanServer;Ljava/util/Map<Ljava/lang/String;Ljava/util/Map;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  58     125    128    140    Ljavax/management/InstanceNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.UnsupportedOperationException: The requested operation is not supported.
        //     at com.strobel.util.ContractUtils.unsupported(ContractUtils.java:27)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:276)
        //     at com.strobel.assembler.metadata.TypeReference.getRawType(TypeReference.java:271)
        //     at com.strobel.assembler.metadata.TypeReference.makeGenericType(TypeReference.java:150)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:187)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedGenericType.accept(CoreMetadataFactory.java:653)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:173)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitParameterizedType(TypeSubstitutionVisitor.java:25)
        //     at com.strobel.assembler.metadata.ParameterizedType.accept(ParameterizedType.java:103)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visit(TypeSubstitutionVisitor.java:39)
        //     at com.strobel.assembler.metadata.TypeSubstitutionVisitor.visitMethod(TypeSubstitutionVisitor.java:276)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2591)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2669)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:770)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:881)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    @Override
    public Object invoke(final String opName, final Object[] opArgs, final String[] signature) throws MBeanException, ReflectionException {
        final Object result = super.invoke(opName, opArgs, signature);
        if (this.methodListeners.contains(opName)) {
            this.sendNotification(this.buildCallListenerNotification(opName));
        }
        return result;
    }
    
    public void handleNotification(final Notification note, final Object handback) {
    }
    
    private Notification buildCallListenerNotification(final String target) {
        return new Notification("jmx.operation.call." + target, this, NumberSequencer.getNextSequence(), System.currentTimeMillis());
    }
    
    private static class NumberSequencer
    {
        private static AtomicLong num;
        
        public static long getNextSequence() {
            return NumberSequencer.num.incrementAndGet();
        }
        
        static {
            NumberSequencer.num = new AtomicLong(0L);
        }
    }
    
    private static final class AttributeChangedListener implements NotificationListener
    {
        private static AttributeChangedListener listener;
        
        public static synchronized AttributeChangedListener getListener() {
            if (AttributeChangedListener.listener == null) {
                AttributeChangedListener.listener = new AttributeChangedListener();
            }
            return AttributeChangedListener.listener;
        }
        
        public void handleNotification(final Notification notification, final Object handback) {
            final AttributeChangeNotification note = (AttributeChangeNotification)notification;
            final Map event = (Map)handback;
            if (event != null) {
                final Object del = event.get("managedObject");
                final Object callback = event.get("callback");
                if (callback != null && callback instanceof Closure) {
                    final Closure closure = (Closure)callback;
                    closure.setDelegate(del);
                    if (closure.getMaximumNumberOfParameters() == 1) {
                        closure.call(buildAttributeNotificationPacket(note));
                    }
                    else {
                        closure.call();
                    }
                }
            }
        }
        
        private static Map buildAttributeNotificationPacket(final AttributeChangeNotification note) {
            final Map<String, Object> result = new HashMap<String, Object>();
            result.put("oldValue", note.getOldValue());
            result.put("newValue", note.getNewValue());
            result.put("attribute", note.getAttributeName());
            result.put("attributeType", note.getAttributeType());
            result.put("sequenceNumber", note.getSequenceNumber());
            result.put("timeStamp", note.getTimeStamp());
            return result;
        }
    }
}
