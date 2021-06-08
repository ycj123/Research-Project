// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.classgen;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class genArrayAccess$_genInners_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> res;
    private static /* synthetic */ SoftReference $callSiteArray;
    
    public genArrayAccess$_genInners_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> res) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.res = res;
    }
    
    public Object doCall(final Object primName, final Object clsName) {
        final Object primName2 = new Reference(primName);
        final Object clsName2 = new Reference(clsName);
        final Object call = $getCallSiteArray()[0].call(this.res.get(), new GStringImpl(new Object[] { ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get(), ((Reference<Object>)primName2).get() }, new String[] { "\n         public static class ", "ArrayGetAtMetaMethod extends ArrayGetAtMetaMethod {\n            private static final CachedClass ARR_CLASS = ReflectionCache.getCachedClass(", "[].class);\n\n            public Class getReturnType() {\n                return ", ".class;\n            }\n\n            public final CachedClass getDeclaringClass() {\n                return ARR_CLASS;\n            }\n\n            public Object invoke(Object object, Object[] args) {\n                final ", "[] objects = (", "[]) object;\n                return objects[normaliseIndex(((Integer) args[0]).intValue(), objects.length)];\n            }\n\n            public CallSite createPojoCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object receiver, Object[] args) {\n                if (!(args [0] instanceof Integer))\n                  return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);\n                else\n                    return new PojoMetaMethodSite(site, metaClass, metaMethod, params) {\n                        public Object invoke(Object receiver, Object[] args) {\n                            final ", "[] objects = (", "[]) receiver;\n                            return objects[normaliseIndex(((Integer) args[0]).intValue(), objects.length)];\n                        }\n\n                        public Object callBinop(Object receiver, Object arg) {\n                            if ((receiver instanceof ", "[] && arg instanceof Integer)\n                                    && checkMetaClass()) {\n                                final ", "[] objects = (", "[]) receiver;\n                                return objects[normaliseIndex(((Integer) arg).intValue(), objects.length)];\n                            }\n                            else\n                              return super.callBinop(receiver,arg);\n                        }\n\n                        public Object invokeBinop(Object receiver, Object arg) {\n                            final ", "[] objects = (", "[]) receiver;\n                            return objects[normaliseIndex(((Integer) arg).intValue(), objects.length)];\n                        }\n                    };\n            }\n         }\n\n\n        public static class ", "ArrayPutAtMetaMethod extends ArrayPutAtMetaMethod {\n            private static final CachedClass OBJECT_CLASS = ReflectionCache.OBJECT_CLASS;\n            private static final CachedClass ARR_CLASS = ReflectionCache.getCachedClass(", "[].class);\n            private static final CachedClass [] PARAM_CLASS_ARR = new CachedClass[] {INTEGER_CLASS, OBJECT_CLASS};\n\n            public ", "ArrayPutAtMetaMethod() {\n                parameterTypes = PARAM_CLASS_ARR;\n            }\n\n            public final CachedClass getDeclaringClass() {\n                return ARR_CLASS;\n            }\n\n            public Object invoke(Object object, Object[] args) {\n                final ", "[] objects = (", "[]) object;\n                final int index = normaliseIndex(((Integer) args[0]).intValue(), objects.length);\n                Object newValue = args[1];\n                if (!(newValue instanceof ", ")) {\n                    Number n = (Number) newValue;\n                    objects[index] = ((Number)newValue).", "Value();\n                }\n                else\n                  objects[index] = ((", ")args[1]).", "Value();\n                return null;\n            }\n\n            public CallSite createPojoCallSite(CallSite site, MetaClassImpl metaClass, MetaMethod metaMethod, Class[] params, Object receiver, Object[] args) {\n                if (!(args [0] instanceof Integer) || !(args [1] instanceof ", "))\n                  return PojoMetaMethodSite.createNonAwareCallSite(site, metaClass, metaMethod, params, args);\n                else\n                    return new PojoMetaMethodSite(site, metaClass, metaMethod, params) {\n                        public Object call(Object receiver, Object[] args) {\n                            if ((receiver instanceof ", "[] && args[0] instanceof Integer && args[1] instanceof ", " )\n                                    && checkMetaClass()) {\n                                final ", "[] objects = (", "[]) receiver;\n                                objects[normaliseIndex(((Integer) args[0]).intValue(), objects.length)] = ((", ")args[1]).", "Value();\n                                return null;\n                            }\n                            else\n                              return super.call(receiver,args);\n                        }\n                    };\n            }\n        }\n\n       " }));
        this.res.set(call);
        return call;
    }
    
    public Object call(final Object primName, final Object clsName) {
        final Object primName2 = new Reference(primName);
        final Object clsName2 = new Reference(clsName);
        return $getCallSiteArray()[1].callCurrent(this, ((Reference<Object>)primName2).get(), ((Reference<Object>)clsName2).get());
    }
    
    public Object getRes() {
        $getCallSiteArray();
        return this.res.get();
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[2];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$org$codehaus$groovy$classgen$genArrayAccess$_genInners_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (genArrayAccess$_genInners_closure1.$callSiteArray == null || ($createCallSiteArray = genArrayAccess$_genInners_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            genArrayAccess$_genInners_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
}
