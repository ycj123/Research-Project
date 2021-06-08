// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.reflection;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import groovy.lang.GroovyRuntimeException;
import groovy.lang.MetaMethod;

public abstract class GeneratedMetaMethod extends MetaMethod
{
    private final String name;
    private final CachedClass declaringClass;
    private final Class returnType;
    
    public GeneratedMetaMethod(final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
        this.name = name;
        this.declaringClass = declaringClass;
        this.returnType = returnType;
        this.nativeParamTypes = parameters;
    }
    
    @Override
    public int getModifiers() {
        return 1;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public Class getReturnType() {
        return this.returnType;
    }
    
    @Override
    public CachedClass getDeclaringClass() {
        return this.declaringClass;
    }
    
    public static class Proxy extends GeneratedMetaMethod
    {
        private volatile MetaMethod proxy;
        private final String className;
        
        public Proxy(final String className, final String name, final CachedClass declaringClass, final Class returnType, final Class[] parameters) {
            super(name, declaringClass, returnType, parameters);
            this.className = className;
        }
        
        @Override
        public boolean isValidMethod(final Class[] arguments) {
            return this.proxy().isValidMethod(arguments);
        }
        
        @Override
        public Object doMethodInvoke(final Object object, final Object[] argumentArray) {
            return this.proxy().doMethodInvoke(object, argumentArray);
        }
        
        @Override
        public Object invoke(final Object object, final Object[] arguments) {
            return this.proxy().invoke(object, arguments);
        }
        
        public final synchronized MetaMethod proxy() {
            if (this.proxy == null) {
                this.createProxy();
            }
            return this.proxy;
        }
        
        private void createProxy() {
            try {
                final Class<?> aClass = this.getClass().getClassLoader().loadClass(this.className.replace('/', '.'));
                final Constructor<?> constructor = aClass.getConstructor(String.class, CachedClass.class, Class.class, Class[].class);
                this.proxy = (MetaMethod)constructor.newInstance(this.getName(), this.getDeclaringClass(), this.getReturnType(), this.getNativeParameterTypes());
            }
            catch (Throwable t) {
                t.printStackTrace();
                throw new GroovyRuntimeException("Failed to create DGM method proxy : " + t, t);
            }
        }
    }
    
    public static class DgmMethodRecord implements Serializable
    {
        public String className;
        public String methodName;
        public Class returnType;
        public Class[] parameters;
        private static final Class[] PRIMITIVE_CLASSES;
        
        public static void saveDgmInfo(final List<DgmMethodRecord> records, final String file) throws IOException {
            final DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            final Map<String, Integer> classes = new LinkedHashMap<String, Integer>();
            int nextClassId = 0;
            for (int i = 0; i < DgmMethodRecord.PRIMITIVE_CLASSES.length; ++i) {
                classes.put(DgmMethodRecord.PRIMITIVE_CLASSES[i].getName(), nextClassId++);
            }
            for (final DgmMethodRecord record : records) {
                String name = record.returnType.getName();
                Integer id = classes.get(name);
                if (id == null) {
                    id = nextClassId++;
                    classes.put(name, id);
                }
                for (int j = 0; j < record.parameters.length; ++j) {
                    name = record.parameters[j].getName();
                    id = classes.get(name);
                    if (id == null) {
                        id = nextClassId++;
                        classes.put(name, id);
                    }
                }
            }
            for (final Map.Entry<String, Integer> stringIntegerEntry : classes.entrySet()) {
                out.writeUTF(stringIntegerEntry.getKey());
                out.writeInt(stringIntegerEntry.getValue());
            }
            out.writeUTF("");
            out.writeInt(records.size());
            for (final DgmMethodRecord record : records) {
                out.writeUTF(record.className);
                out.writeUTF(record.methodName);
                out.writeInt(classes.get(record.returnType.getName()));
                out.writeInt(record.parameters.length);
                for (int k = 0; k < record.parameters.length; ++k) {
                    final Integer key = classes.get(record.parameters[k].getName());
                    out.writeInt(key);
                }
            }
            out.close();
        }
        
        public static List<DgmMethodRecord> loadDgmInfo() throws IOException, ClassNotFoundException {
            final ClassLoader loader = DgmMethodRecord.class.getClassLoader();
            final DataInputStream in = new DataInputStream(new BufferedInputStream(loader.getResourceAsStream("META-INF/dgminfo")));
            final Map<Integer, Class> classes = new HashMap<Integer, Class>();
            for (int i = 0; i < DgmMethodRecord.PRIMITIVE_CLASSES.length; ++i) {
                classes.put(i, DgmMethodRecord.PRIMITIVE_CLASSES[i]);
            }
            int skip = 0;
            while (true) {
                final String name = in.readUTF();
                if (name.length() == 0) {
                    break;
                }
                final int key = in.readInt();
                if (skip++ < DgmMethodRecord.PRIMITIVE_CLASSES.length) {
                    continue;
                }
                Class cls = null;
                try {
                    cls = loader.loadClass(name);
                }
                catch (ClassNotFoundException e) {
                    continue;
                }
                classes.put(key, cls);
            }
            final int size = in.readInt();
            final List<DgmMethodRecord> res = new ArrayList<DgmMethodRecord>(size);
            for (int j = 0; j != size; ++j) {
                boolean skipRecord = false;
                final DgmMethodRecord record = new DgmMethodRecord();
                record.className = in.readUTF();
                record.methodName = in.readUTF();
                record.returnType = classes.get(in.readInt());
                if (record.returnType == null) {
                    skipRecord = true;
                }
                final int psize = in.readInt();
                record.parameters = new Class[psize];
                for (int k = 0; k < record.parameters.length; ++k) {
                    record.parameters[k] = classes.get(in.readInt());
                    if (record.parameters[k] == null) {
                        skipRecord = true;
                    }
                }
                if (!skipRecord) {
                    res.add(record);
                }
            }
            in.close();
            return res;
        }
        
        static {
            PRIMITIVE_CLASSES = new Class[] { Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Double.TYPE, Float.TYPE, Void.TYPE, boolean[].class, char[].class, byte[].class, short[].class, int[].class, long[].class, double[].class, float[].class, Object[].class, String[].class, Class[].class, Byte[].class };
        }
    }
}
