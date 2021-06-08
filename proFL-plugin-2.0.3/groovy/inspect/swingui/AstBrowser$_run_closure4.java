// 
// Decompiled by Procyon v0.5.36
// 

package groovy.inspect.swingui;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import groovy.lang.MetaClass;
import groovy.lang.GroovyObject;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import groovy.lang.Reference;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import javax.swing.tree.TreeNode;
import javax.swing.event.TreeSelectionEvent;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.GeneratedClosure;
import groovy.lang.Closure;

class AstBrowser$_run_closure4 extends Closure implements GeneratedClosure
{
    private static final /* synthetic */ Integer $const$0;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$javax$swing$tree$TreeNode;
    
    public AstBrowser$_run_closure4(final Object _outerInstance, final Object _thisObject) {
        $getCallSiteArray();
        super(_outerInstance, _thisObject);
    }
    
    public Object doCall(final TreeSelectionEvent e) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty($getCallSiteArray[2].callGetProperty($getCallSiteArray[3].callGroovyObjectGetProperty(this))));
        final TreeNode node = (TreeNode)new Reference(ScriptBytecodeAdapter.castToType($getCallSiteArray[4].callGetProperty($getCallSiteArray[5].callGroovyObjectGetProperty(this)), $get$$class$javax$swing$tree$TreeNode()));
        if (DefaultTypeTransformation.booleanUnbox((ScriptBytecodeAdapter.compareNotEqual(((Reference<Object>)node).get(), null) && ((Reference<Object>)node).get() instanceof TreeNodeWithProperties) ? Boolean.TRUE : Boolean.FALSE)) {
            $getCallSiteArray[6].call($getCallSiteArray[7].callGetProperty(((Reference<Object>)node).get()), new AstBrowser$_run_closure4_closure30(this, this.getThisObject()));
            if (DefaultTypeTransformation.booleanUnbox((DefaultTypeTransformation.booleanUnbox($getCallSiteArray[8].callGroovyObjectGetProperty(this)) && DefaultTypeTransformation.booleanUnbox($getCallSiteArray[9].callGroovyObjectGetProperty(this))) ? Boolean.TRUE : Boolean.FALSE)) {
                final Object lineInfo = $getCallSiteArray[10].call($getCallSiteArray[11].callGetProperty(((Reference<Object>)node).get()), new AstBrowser$_run_closure4_closure31(this, this.getThisObject()));
                final Object lineInfoMap = new Reference($getCallSiteArray[12].call(lineInfo, ScriptBytecodeAdapter.createMap(new Object[0]), new AstBrowser$_run_closure4_closure32(this, this.getThisObject())));
                if (!DefaultTypeTransformation.booleanUnbox($getCallSiteArray[13].call(((Reference<Object>)lineInfoMap).get(), new AstBrowser$_run_closure4_closure33(this, this.getThisObject())))) {
                    final Object startOffset = $getCallSiteArray[14].callGetProperty($getCallSiteArray[15].call($getCallSiteArray[16].callGroovyObjectGetProperty(this), $getCallSiteArray[17].call($getCallSiteArray[18].callGetProperty(((Reference<Object>)lineInfoMap).get()), AstBrowser$_run_closure4.$const$0)));
                    $getCallSiteArray[19].call($getCallSiteArray[20].callGroovyObjectGetProperty(this), $getCallSiteArray[21].call($getCallSiteArray[22].call(startOffset, $getCallSiteArray[23].callGetProperty(((Reference<Object>)lineInfoMap).get())), AstBrowser$_run_closure4.$const$0));
                    final Object endOffset = $getCallSiteArray[24].callGetProperty($getCallSiteArray[25].call($getCallSiteArray[26].callGroovyObjectGetProperty(this), $getCallSiteArray[27].call($getCallSiteArray[28].callGetProperty(((Reference<Object>)lineInfoMap).get()), AstBrowser$_run_closure4.$const$0)));
                    $getCallSiteArray[29].call($getCallSiteArray[30].callGroovyObjectGetProperty(this), $getCallSiteArray[31].call($getCallSiteArray[32].call(endOffset, $getCallSiteArray[33].callGetProperty(((Reference<Object>)lineInfoMap).get())), AstBrowser$_run_closure4.$const$0));
                }
                else {
                    $getCallSiteArray[34].call($getCallSiteArray[35].callGroovyObjectGetProperty(this), $getCallSiteArray[36].call($getCallSiteArray[37].callGroovyObjectGetProperty(this)));
                }
            }
        }
        return $getCallSiteArray[38].call($getCallSiteArray[39].callGetProperty($getCallSiteArray[40].callGroovyObjectGetProperty(this)));
    }
    
    public Object call(final TreeSelectionEvent e) {
        return $getCallSiteArray()[41].callCurrent(this, e);
    }
    
    static {
        $const$0 = 1;
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[42];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$inspect$swingui$AstBrowser$_run_closure4(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (AstBrowser$_run_closure4.$callSiteArray == null || ($createCallSiteArray = AstBrowser$_run_closure4.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            AstBrowser$_run_closure4.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$javax$swing$tree$TreeNode() {
        Class $class$javax$swing$tree$TreeNode;
        if (($class$javax$swing$tree$TreeNode = AstBrowser$_run_closure4.$class$javax$swing$tree$TreeNode) == null) {
            $class$javax$swing$tree$TreeNode = (AstBrowser$_run_closure4.$class$javax$swing$tree$TreeNode = class$("javax.swing.tree.TreeNode"));
        }
        return $class$javax$swing$tree$TreeNode;
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
