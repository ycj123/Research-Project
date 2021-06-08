// 
// Decompiled by Procyon v0.5.36
// 

package groovy.xml;

import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;
import org.codehaus.groovy.runtime.GStringImpl;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import java.lang.ref.SoftReference;
import groovy.lang.MetaClass;
import org.codehaus.groovy.reflection.ClassInfo;
import groovy.lang.GroovyObject;
import groovy.lang.Buildable;

public class Entity implements Buildable, GroovyObject
{
    public static final Entity nbsp;
    public static final Entity iexcl;
    public static final Entity cent;
    public static final Entity pound;
    public static final Entity curren;
    public static final Entity yen;
    public static final Entity brvbar;
    public static final Entity sect;
    public static final Entity uml;
    public static final Entity copy;
    public static final Entity ordf;
    public static final Entity laquo;
    public static final Entity not;
    public static final Entity shy;
    public static final Entity reg;
    public static final Entity macr;
    public static final Entity deg;
    public static final Entity plusmn;
    public static final Entity sup2;
    public static final Entity sup3;
    public static final Entity acute;
    public static final Entity micro;
    public static final Entity para;
    public static final Entity middot;
    public static final Entity cedil;
    public static final Entity sup1;
    public static final Entity ordm;
    public static final Entity raquo;
    public static final Entity frac14;
    public static final Entity frac12;
    public static final Entity frac34;
    public static final Entity iquest;
    public static final Entity Agrave;
    public static final Entity Aacute;
    public static final Entity Acirc;
    public static final Entity Atilde;
    public static final Entity Auml;
    public static final Entity Aring;
    public static final Entity AElig;
    public static final Entity Ccedil;
    public static final Entity Egrave;
    public static final Entity Eacute;
    public static final Entity Ecirc;
    public static final Entity Euml;
    public static final Entity Igrave;
    public static final Entity Iacute;
    public static final Entity Icirc;
    public static final Entity Iuml;
    public static final Entity ETH;
    public static final Entity Ntilde;
    public static final Entity Ograve;
    public static final Entity Oacute;
    public static final Entity Ocirc;
    public static final Entity Otilde;
    public static final Entity Ouml;
    public static final Entity times;
    public static final Entity Oslash;
    public static final Entity Ugrave;
    public static final Entity Uacute;
    public static final Entity Ucirc;
    public static final Entity Uuml;
    public static final Entity Yacute;
    public static final Entity THORN;
    public static final Entity szlig;
    public static final Entity agrave;
    public static final Entity aacute;
    public static final Entity acirc;
    public static final Entity atilde;
    public static final Entity auml;
    public static final Entity aring;
    public static final Entity aelig;
    public static final Entity ccedil;
    public static final Entity egrave;
    public static final Entity eacute;
    public static final Entity ecirc;
    public static final Entity euml;
    public static final Entity igrave;
    public static final Entity iacute;
    public static final Entity icirc;
    public static final Entity iuml;
    public static final Entity eth;
    public static final Entity ntilde;
    public static final Entity ograve;
    public static final Entity oacute;
    public static final Entity ocirc;
    public static final Entity otilde;
    public static final Entity ouml;
    public static final Entity divide;
    public static final Entity oslash;
    public static final Entity ugrave;
    public static final Entity uacute;
    public static final Entity ucirc;
    public static final Entity uuml;
    public static final Entity yacute;
    public static final Entity thorn;
    public static final Entity yuml;
    public static final Entity lt;
    public static final Entity gt;
    public static final Entity amp;
    public static final Entity apos;
    public static final Entity quot;
    public static final Entity OElig;
    public static final Entity oelig;
    public static final Entity Scaron;
    public static final Entity scaron;
    public static final Entity Yuml;
    public static final Entity circ;
    public static final Entity tilde;
    public static final Entity ensp;
    public static final Entity emsp;
    public static final Entity thinsp;
    public static final Entity zwnj;
    public static final Entity zwj;
    public static final Entity lrm;
    public static final Entity rlm;
    public static final Entity ndash;
    public static final Entity mdash;
    public static final Entity lsquo;
    public static final Entity rsquo;
    public static final Entity sbquo;
    public static final Entity ldquo;
    public static final Entity rdquo;
    public static final Entity bdquo;
    public static final Entity dagger;
    public static final Entity Dagger;
    public static final Entity permil;
    public static final Entity lsaquo;
    public static final Entity rsaquo;
    public static final Entity euro;
    private final Object entity;
    private static /* synthetic */ ClassInfo $staticClassInfo;
    private transient /* synthetic */ MetaClass metaClass;
    public static /* synthetic */ Long __timeStamp;
    public static /* synthetic */ Long __timeStamp__239_neverHappen1292524203659;
    private static /* synthetic */ SoftReference $callSiteArray;
    private static /* synthetic */ Class $class$groovy$lang$MetaClass;
    private static /* synthetic */ Class $class$groovy$xml$Entity;
    
    public Entity(final String name) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.entity = new GStringImpl(new Object[] { name }, new String[] { "&", ";" });
    }
    
    public Entity(final int name) {
        $getCallSiteArray();
        this.metaClass = (MetaClass)ScriptBytecodeAdapter.castToType(this.$getStaticMetaClass(), $get$$class$groovy$lang$MetaClass());
        this.entity = new GStringImpl(new Object[] { DefaultTypeTransformation.box(name) }, new String[] { "&#", ";" });
    }
    
    public void build(final GroovyObject builder) {
        final CallSite[] $getCallSiteArray = $getCallSiteArray();
        $getCallSiteArray[0].call($getCallSiteArray[1].callGetProperty(builder), this.entity);
    }
    
    protected /* synthetic */ MetaClass $getStaticMetaClass() {
        if (this.getClass() == $get$$class$groovy$xml$Entity()) {
            return ScriptBytecodeAdapter.initMetaClass(this);
        }
        ClassInfo $staticClassInfo = Entity.$staticClassInfo;
        if ($staticClassInfo == null) {
            $staticClassInfo = (Entity.$staticClassInfo = ClassInfo.getClassInfo(this.getClass()));
        }
        return $staticClassInfo.getMetaClass();
    }
    
    static {
        Entity.__timeStamp__239_neverHappen1292524203659 = 0L;
        Entity.__timeStamp = 1292524203659L;
        nbsp = (Entity)$getCallSiteArray()[2].callConstructor($get$$class$groovy$xml$Entity(), "nbsp");
        iexcl = (Entity)$getCallSiteArray()[3].callConstructor($get$$class$groovy$xml$Entity(), "iexcl");
        cent = (Entity)$getCallSiteArray()[4].callConstructor($get$$class$groovy$xml$Entity(), "cent");
        pound = (Entity)$getCallSiteArray()[5].callConstructor($get$$class$groovy$xml$Entity(), "pound");
        curren = (Entity)$getCallSiteArray()[6].callConstructor($get$$class$groovy$xml$Entity(), "curren");
        yen = (Entity)$getCallSiteArray()[7].callConstructor($get$$class$groovy$xml$Entity(), "yen");
        brvbar = (Entity)$getCallSiteArray()[8].callConstructor($get$$class$groovy$xml$Entity(), "brvbar");
        sect = (Entity)$getCallSiteArray()[9].callConstructor($get$$class$groovy$xml$Entity(), "sect");
        uml = (Entity)$getCallSiteArray()[10].callConstructor($get$$class$groovy$xml$Entity(), "uml");
        copy = (Entity)$getCallSiteArray()[11].callConstructor($get$$class$groovy$xml$Entity(), "copy");
        ordf = (Entity)$getCallSiteArray()[12].callConstructor($get$$class$groovy$xml$Entity(), "ordf");
        laquo = (Entity)$getCallSiteArray()[13].callConstructor($get$$class$groovy$xml$Entity(), "laquo");
        not = (Entity)$getCallSiteArray()[14].callConstructor($get$$class$groovy$xml$Entity(), "not");
        shy = (Entity)$getCallSiteArray()[15].callConstructor($get$$class$groovy$xml$Entity(), "shy");
        reg = (Entity)$getCallSiteArray()[16].callConstructor($get$$class$groovy$xml$Entity(), "reg");
        macr = (Entity)$getCallSiteArray()[17].callConstructor($get$$class$groovy$xml$Entity(), "macr");
        deg = (Entity)$getCallSiteArray()[18].callConstructor($get$$class$groovy$xml$Entity(), "deg");
        plusmn = (Entity)$getCallSiteArray()[19].callConstructor($get$$class$groovy$xml$Entity(), "plusmn");
        sup2 = (Entity)$getCallSiteArray()[20].callConstructor($get$$class$groovy$xml$Entity(), "sup2");
        sup3 = (Entity)$getCallSiteArray()[21].callConstructor($get$$class$groovy$xml$Entity(), "sup3");
        acute = (Entity)$getCallSiteArray()[22].callConstructor($get$$class$groovy$xml$Entity(), "acute");
        micro = (Entity)$getCallSiteArray()[23].callConstructor($get$$class$groovy$xml$Entity(), "micro");
        para = (Entity)$getCallSiteArray()[24].callConstructor($get$$class$groovy$xml$Entity(), "para");
        middot = (Entity)$getCallSiteArray()[25].callConstructor($get$$class$groovy$xml$Entity(), "middot");
        cedil = (Entity)$getCallSiteArray()[26].callConstructor($get$$class$groovy$xml$Entity(), "cedil");
        sup1 = (Entity)$getCallSiteArray()[27].callConstructor($get$$class$groovy$xml$Entity(), "sup1");
        ordm = (Entity)$getCallSiteArray()[28].callConstructor($get$$class$groovy$xml$Entity(), "ordm");
        raquo = (Entity)$getCallSiteArray()[29].callConstructor($get$$class$groovy$xml$Entity(), "raquo");
        frac14 = (Entity)$getCallSiteArray()[30].callConstructor($get$$class$groovy$xml$Entity(), "frac14");
        frac12 = (Entity)$getCallSiteArray()[31].callConstructor($get$$class$groovy$xml$Entity(), "frac12");
        frac34 = (Entity)$getCallSiteArray()[32].callConstructor($get$$class$groovy$xml$Entity(), "frac34");
        iquest = (Entity)$getCallSiteArray()[33].callConstructor($get$$class$groovy$xml$Entity(), "iquest");
        Agrave = (Entity)$getCallSiteArray()[34].callConstructor($get$$class$groovy$xml$Entity(), "Agrave");
        Aacute = (Entity)$getCallSiteArray()[35].callConstructor($get$$class$groovy$xml$Entity(), "Aacute");
        Acirc = (Entity)$getCallSiteArray()[36].callConstructor($get$$class$groovy$xml$Entity(), "Acirc");
        Atilde = (Entity)$getCallSiteArray()[37].callConstructor($get$$class$groovy$xml$Entity(), "Atilde");
        Auml = (Entity)$getCallSiteArray()[38].callConstructor($get$$class$groovy$xml$Entity(), "Auml");
        Aring = (Entity)$getCallSiteArray()[39].callConstructor($get$$class$groovy$xml$Entity(), "Aring");
        AElig = (Entity)$getCallSiteArray()[40].callConstructor($get$$class$groovy$xml$Entity(), "AElig");
        Ccedil = (Entity)$getCallSiteArray()[41].callConstructor($get$$class$groovy$xml$Entity(), "Ccedil");
        Egrave = (Entity)$getCallSiteArray()[42].callConstructor($get$$class$groovy$xml$Entity(), "Egrave");
        Eacute = (Entity)$getCallSiteArray()[43].callConstructor($get$$class$groovy$xml$Entity(), "Eacute");
        Ecirc = (Entity)$getCallSiteArray()[44].callConstructor($get$$class$groovy$xml$Entity(), "Ecirc");
        Euml = (Entity)$getCallSiteArray()[45].callConstructor($get$$class$groovy$xml$Entity(), "Euml");
        Igrave = (Entity)$getCallSiteArray()[46].callConstructor($get$$class$groovy$xml$Entity(), "Igrave");
        Iacute = (Entity)$getCallSiteArray()[47].callConstructor($get$$class$groovy$xml$Entity(), "Iacute");
        Icirc = (Entity)$getCallSiteArray()[48].callConstructor($get$$class$groovy$xml$Entity(), "Icirc");
        Iuml = (Entity)$getCallSiteArray()[49].callConstructor($get$$class$groovy$xml$Entity(), "Iuml");
        ETH = (Entity)$getCallSiteArray()[50].callConstructor($get$$class$groovy$xml$Entity(), "ETH");
        Ntilde = (Entity)$getCallSiteArray()[51].callConstructor($get$$class$groovy$xml$Entity(), "Ntilde");
        Ograve = (Entity)$getCallSiteArray()[52].callConstructor($get$$class$groovy$xml$Entity(), "Ograve");
        Oacute = (Entity)$getCallSiteArray()[53].callConstructor($get$$class$groovy$xml$Entity(), "Oacute");
        Ocirc = (Entity)$getCallSiteArray()[54].callConstructor($get$$class$groovy$xml$Entity(), "Ocirc");
        Otilde = (Entity)$getCallSiteArray()[55].callConstructor($get$$class$groovy$xml$Entity(), "Otilde");
        Ouml = (Entity)$getCallSiteArray()[56].callConstructor($get$$class$groovy$xml$Entity(), "Ouml");
        times = (Entity)$getCallSiteArray()[57].callConstructor($get$$class$groovy$xml$Entity(), "times");
        Oslash = (Entity)$getCallSiteArray()[58].callConstructor($get$$class$groovy$xml$Entity(), "Oslash");
        Ugrave = (Entity)$getCallSiteArray()[59].callConstructor($get$$class$groovy$xml$Entity(), "Ugrave");
        Uacute = (Entity)$getCallSiteArray()[60].callConstructor($get$$class$groovy$xml$Entity(), "Uacute");
        Ucirc = (Entity)$getCallSiteArray()[61].callConstructor($get$$class$groovy$xml$Entity(), "Ucirc");
        Uuml = (Entity)$getCallSiteArray()[62].callConstructor($get$$class$groovy$xml$Entity(), "Uuml");
        Yacute = (Entity)$getCallSiteArray()[63].callConstructor($get$$class$groovy$xml$Entity(), "Yacute");
        THORN = (Entity)$getCallSiteArray()[64].callConstructor($get$$class$groovy$xml$Entity(), "THORN");
        szlig = (Entity)$getCallSiteArray()[65].callConstructor($get$$class$groovy$xml$Entity(), "szlig");
        agrave = (Entity)$getCallSiteArray()[66].callConstructor($get$$class$groovy$xml$Entity(), "agrave");
        aacute = (Entity)$getCallSiteArray()[67].callConstructor($get$$class$groovy$xml$Entity(), "aacute");
        acirc = (Entity)$getCallSiteArray()[68].callConstructor($get$$class$groovy$xml$Entity(), "acirc");
        atilde = (Entity)$getCallSiteArray()[69].callConstructor($get$$class$groovy$xml$Entity(), "atilde");
        auml = (Entity)$getCallSiteArray()[70].callConstructor($get$$class$groovy$xml$Entity(), "auml");
        aring = (Entity)$getCallSiteArray()[71].callConstructor($get$$class$groovy$xml$Entity(), "aring");
        aelig = (Entity)$getCallSiteArray()[72].callConstructor($get$$class$groovy$xml$Entity(), "aelig");
        ccedil = (Entity)$getCallSiteArray()[73].callConstructor($get$$class$groovy$xml$Entity(), "ccedil");
        egrave = (Entity)$getCallSiteArray()[74].callConstructor($get$$class$groovy$xml$Entity(), "egrave");
        eacute = (Entity)$getCallSiteArray()[75].callConstructor($get$$class$groovy$xml$Entity(), "eacute");
        ecirc = (Entity)$getCallSiteArray()[76].callConstructor($get$$class$groovy$xml$Entity(), "ecirc");
        euml = (Entity)$getCallSiteArray()[77].callConstructor($get$$class$groovy$xml$Entity(), "euml");
        igrave = (Entity)$getCallSiteArray()[78].callConstructor($get$$class$groovy$xml$Entity(), "igrave");
        iacute = (Entity)$getCallSiteArray()[79].callConstructor($get$$class$groovy$xml$Entity(), "iacute");
        icirc = (Entity)$getCallSiteArray()[80].callConstructor($get$$class$groovy$xml$Entity(), "icirc");
        iuml = (Entity)$getCallSiteArray()[81].callConstructor($get$$class$groovy$xml$Entity(), "iuml");
        eth = (Entity)$getCallSiteArray()[82].callConstructor($get$$class$groovy$xml$Entity(), "eth");
        ntilde = (Entity)$getCallSiteArray()[83].callConstructor($get$$class$groovy$xml$Entity(), "ntilde");
        ograve = (Entity)$getCallSiteArray()[84].callConstructor($get$$class$groovy$xml$Entity(), "ograve");
        oacute = (Entity)$getCallSiteArray()[85].callConstructor($get$$class$groovy$xml$Entity(), "oacute");
        ocirc = (Entity)$getCallSiteArray()[86].callConstructor($get$$class$groovy$xml$Entity(), "ocirc");
        otilde = (Entity)$getCallSiteArray()[87].callConstructor($get$$class$groovy$xml$Entity(), "otilde");
        ouml = (Entity)$getCallSiteArray()[88].callConstructor($get$$class$groovy$xml$Entity(), "ouml");
        divide = (Entity)$getCallSiteArray()[89].callConstructor($get$$class$groovy$xml$Entity(), "divide");
        oslash = (Entity)$getCallSiteArray()[90].callConstructor($get$$class$groovy$xml$Entity(), "oslash");
        ugrave = (Entity)$getCallSiteArray()[91].callConstructor($get$$class$groovy$xml$Entity(), "ugrave");
        uacute = (Entity)$getCallSiteArray()[92].callConstructor($get$$class$groovy$xml$Entity(), "uacute");
        ucirc = (Entity)$getCallSiteArray()[93].callConstructor($get$$class$groovy$xml$Entity(), "ucirc");
        uuml = (Entity)$getCallSiteArray()[94].callConstructor($get$$class$groovy$xml$Entity(), "uuml");
        yacute = (Entity)$getCallSiteArray()[95].callConstructor($get$$class$groovy$xml$Entity(), "yacute");
        thorn = (Entity)$getCallSiteArray()[96].callConstructor($get$$class$groovy$xml$Entity(), "thorn");
        yuml = (Entity)$getCallSiteArray()[97].callConstructor($get$$class$groovy$xml$Entity(), "yuml");
        lt = (Entity)$getCallSiteArray()[98].callConstructor($get$$class$groovy$xml$Entity(), "lt");
        gt = (Entity)$getCallSiteArray()[99].callConstructor($get$$class$groovy$xml$Entity(), "gt");
        amp = (Entity)$getCallSiteArray()[100].callConstructor($get$$class$groovy$xml$Entity(), "amp");
        apos = (Entity)$getCallSiteArray()[101].callConstructor($get$$class$groovy$xml$Entity(), "apos");
        quot = (Entity)$getCallSiteArray()[102].callConstructor($get$$class$groovy$xml$Entity(), "quot");
        OElig = (Entity)$getCallSiteArray()[103].callConstructor($get$$class$groovy$xml$Entity(), "OElig");
        oelig = (Entity)$getCallSiteArray()[104].callConstructor($get$$class$groovy$xml$Entity(), "oelig");
        Scaron = (Entity)$getCallSiteArray()[105].callConstructor($get$$class$groovy$xml$Entity(), "Scaron");
        scaron = (Entity)$getCallSiteArray()[106].callConstructor($get$$class$groovy$xml$Entity(), "scaron");
        Yuml = (Entity)$getCallSiteArray()[107].callConstructor($get$$class$groovy$xml$Entity(), "Yuml");
        circ = (Entity)$getCallSiteArray()[108].callConstructor($get$$class$groovy$xml$Entity(), "circ");
        tilde = (Entity)$getCallSiteArray()[109].callConstructor($get$$class$groovy$xml$Entity(), "tilde");
        ensp = (Entity)$getCallSiteArray()[110].callConstructor($get$$class$groovy$xml$Entity(), "ensp");
        emsp = (Entity)$getCallSiteArray()[111].callConstructor($get$$class$groovy$xml$Entity(), "emsp");
        thinsp = (Entity)$getCallSiteArray()[112].callConstructor($get$$class$groovy$xml$Entity(), "thinsp");
        zwnj = (Entity)$getCallSiteArray()[113].callConstructor($get$$class$groovy$xml$Entity(), "zwnj");
        zwj = (Entity)$getCallSiteArray()[114].callConstructor($get$$class$groovy$xml$Entity(), "zwj");
        lrm = (Entity)$getCallSiteArray()[115].callConstructor($get$$class$groovy$xml$Entity(), "lrm");
        rlm = (Entity)$getCallSiteArray()[116].callConstructor($get$$class$groovy$xml$Entity(), "rlm");
        ndash = (Entity)$getCallSiteArray()[117].callConstructor($get$$class$groovy$xml$Entity(), "ndash");
        mdash = (Entity)$getCallSiteArray()[118].callConstructor($get$$class$groovy$xml$Entity(), "mdash");
        lsquo = (Entity)$getCallSiteArray()[119].callConstructor($get$$class$groovy$xml$Entity(), "lsquo");
        rsquo = (Entity)$getCallSiteArray()[120].callConstructor($get$$class$groovy$xml$Entity(), "rsquo");
        sbquo = (Entity)$getCallSiteArray()[121].callConstructor($get$$class$groovy$xml$Entity(), "sbquo");
        ldquo = (Entity)$getCallSiteArray()[122].callConstructor($get$$class$groovy$xml$Entity(), "ldquo");
        rdquo = (Entity)$getCallSiteArray()[123].callConstructor($get$$class$groovy$xml$Entity(), "rdquo");
        bdquo = (Entity)$getCallSiteArray()[124].callConstructor($get$$class$groovy$xml$Entity(), "bdquo");
        dagger = (Entity)$getCallSiteArray()[125].callConstructor($get$$class$groovy$xml$Entity(), "dagger");
        Dagger = (Entity)$getCallSiteArray()[126].callConstructor($get$$class$groovy$xml$Entity(), "Dagger");
        permil = (Entity)$getCallSiteArray()[127].callConstructor($get$$class$groovy$xml$Entity(), "permil");
        lsaquo = (Entity)$getCallSiteArray()[128].callConstructor($get$$class$groovy$xml$Entity(), "lsaquo");
        rsaquo = (Entity)$getCallSiteArray()[129].callConstructor($get$$class$groovy$xml$Entity(), "rsaquo");
        euro = (Entity)$getCallSiteArray()[130].callConstructor($get$$class$groovy$xml$Entity(), "euro");
    }
    
    private static /* synthetic */ CallSiteArray $createCallSiteArray() {
        final String[] names = new String[131];
        $createCallSiteArray_1(names);
        return new CallSiteArray($get$$class$groovy$xml$Entity(), names);
    }
    
    private static /* synthetic */ CallSite[] $getCallSiteArray() {
        CallSiteArray $createCallSiteArray;
        if (Entity.$callSiteArray == null || ($createCallSiteArray = Entity.$callSiteArray.get()) == null) {
            $createCallSiteArray = $createCallSiteArray();
            Entity.$callSiteArray = new SoftReference($createCallSiteArray);
        }
        return $createCallSiteArray.array;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$lang$MetaClass() {
        Class $class$groovy$lang$MetaClass;
        if (($class$groovy$lang$MetaClass = Entity.$class$groovy$lang$MetaClass) == null) {
            $class$groovy$lang$MetaClass = (Entity.$class$groovy$lang$MetaClass = class$("groovy.lang.MetaClass"));
        }
        return $class$groovy$lang$MetaClass;
    }
    
    private static /* synthetic */ Class $get$$class$groovy$xml$Entity() {
        Class $class$groovy$xml$Entity;
        if (($class$groovy$xml$Entity = Entity.$class$groovy$xml$Entity) == null) {
            $class$groovy$xml$Entity = (Entity.$class$groovy$xml$Entity = class$("groovy.xml.Entity"));
        }
        return $class$groovy$xml$Entity;
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
