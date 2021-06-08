// 
// Decompiled by Procyon v0.5.36
// 

package groovy.swing.factory;

import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import org.codehaus.groovy.binding.FullBinding;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.GStringImpl;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class BindFactory$_finishContextualBinding_closure1 extends Closure implements GeneratedClosure
{
    private Reference<Object> fb;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$java$lang$String;
    private static /* synthetic */ Class $class$org$codehaus$groovy$binding$FullBinding;
    private static /* synthetic */ Class $class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1;
    
    public BindFactory$_finishContextualBinding_closure1(final Object _outerInstance, final Object _thisObject, final Reference<Object> fb) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
        this.fb = fb;
    }
    
    public Object doCall(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        $getCallSiteArray();
        final Object value = ((Reference<Object>)v2).get();
        ScriptBytecodeAdapter.setProperty(value, $get$$class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1(), this.fb.get(), (String)ScriptBytecodeAdapter.castToType(new GStringImpl(new Object[] { ((Reference<Object>)i).get() }, new String[] { "", "" }), $get$$class$java$lang$String()));
        return value;
    }
    
    public Object call(final Object k, final Object v) {
        final Object i = new Reference(k);
        final Object v2 = new Reference(v);
        return $getCallSiteArray()[0].callCurrent(this, ((Reference<Object>)i).get(), ((Reference<Object>)v2).get());
    }
    
    public FullBinding getFb() {
        $getCallSiteArray();
        return (FullBinding)ScriptBytecodeAdapter.castToType(this.fb.get(), $get$$class$org$codehaus$groovy$binding$FullBinding());
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = { null };
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (BindFactory$_finishContextualBinding_closure1.$callSiteArray == null || ($createCallSiteArray = BindFactory$_finishContextualBinding_closure1.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            BindFactory$_finishContextualBinding_closure1.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$java$lang$String() {
        Class $class$java$lang$String;
        if (($class$java$lang$String = BindFactory$_finishContextualBinding_closure1.$class$java$lang$String) == null) {
            $class$java$lang$String = (BindFactory$_finishContextualBinding_closure1.$class$java$lang$String = class$("java.lang.String"));
        }
        return $class$java$lang$String;
    }
    
    private static /* synthetic */ Class $get$$class$org$codehaus$groovy$binding$FullBinding() {
        Class $class$org$codehaus$groovy$binding$FullBinding;
        if (($class$org$codehaus$groovy$binding$FullBinding = BindFactory$_finishContextualBinding_closure1.$class$org$codehaus$groovy$binding$FullBinding) == null) {
            $class$org$codehaus$groovy$binding$FullBinding = (BindFactory$_finishContextualBinding_closure1.$class$org$codehaus$groovy$binding$FullBinding = class$("org.codehaus.groovy.binding.FullBinding"));
        }
        return $class$org$codehaus$groovy$binding$FullBinding;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1() {
        Class $class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1;
        if (($class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1 = BindFactory$_finishContextualBinding_closure1.$class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1) == null) {
            $class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1 = (BindFactory$_finishContextualBinding_closure1.$class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1 = class$("groovy.swing.factory.BindFactory$_finishContextualBinding_closure1"));
        }
        return $class$groovy$swing$factory$BindFactory$_finishContextualBinding_closure1;
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
