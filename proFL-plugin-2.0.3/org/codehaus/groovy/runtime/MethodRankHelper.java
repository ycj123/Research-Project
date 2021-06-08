// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.runtime;

import java.util.Arrays;
import org.codehaus.groovy.reflection.CachedClass;
import java.util.Collections;
import groovy.lang.MetaProperty;
import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import groovy.lang.MetaMethod;
import java.util.ArrayList;
import org.codehaus.groovy.reflection.ClassInfo;

public class MethodRankHelper
{
    public static final int DL_SUBSTITUTION = 10;
    public static final int DL_DELETE = 10;
    public static final int DL_TRANSPOSITION = 5;
    public static final int DL_CASE = 5;
    public static final int MAX_RECOMENDATIONS = 5;
    public static final int MAX_METHOD_SCORE = 50;
    public static final int MAX_CONSTRUCTOR_SCORE = 20;
    public static final int MAX_FIELD_SCORE = 30;
    
    public static String getMethodSuggestionString(final String methodName, final Class type, final Object[] arguments) {
        final ClassInfo ci = ClassInfo.getClassInfo(type);
        final List<MetaMethod> methods = new ArrayList<MetaMethod>(ci.getMetaClass().getMethods());
        methods.addAll(ci.getMetaClass().getMetaMethods());
        final List<MetaMethod> sugg = rankMethods(methodName, arguments, methods);
        final StringBuffer sb = new StringBuffer();
        if (!sugg.isEmpty()) {
            sb.append("\nPossible solutions: ");
            for (int i = 0; i < sugg.size(); ++i) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(sugg.get(i).getName()).append("(");
                sb.append(listParameterNames(sugg.get(i).getParameterTypes()));
                sb.append(")");
            }
        }
        final Class[] argumentClasses = getArgumentClasses(arguments);
        final List<Pair<Class, Class>> conflictClasses = getConflictClasses(sugg, argumentClasses);
        if (!conflictClasses.isEmpty()) {
            sb.append("\nThe following classes appear as argument class and as parameter class, ");
            sb.append("but are defined by different class loader:\n");
            boolean first = true;
            for (final Pair<Class, Class> pair : conflictClasses) {
                if (!first) {
                    sb.append(", ");
                }
                else {
                    first = false;
                }
                sb.append(((Class)((Pair<Object, Object>)pair).u).getName()).append(" (defined by '");
                sb.append(((Class)((Pair<Object, Object>)pair).u).getClassLoader());
                sb.append("' and '");
                sb.append(((Class)((Pair<Object, Object>)pair).v).getClassLoader());
                sb.append("')");
            }
            sb.append("\nIf one of the method suggestions matches the method you wanted to call, ");
            sb.append("\nthen check your class loader setup.");
        }
        return sb.toString();
    }
    
    private static List<Pair<Class, Class>> getConflictClasses(final List<MetaMethod> sugg, final Class[] argumentClasses) {
        final List<Pair<Class, Class>> ret = new LinkedList<Pair<Class, Class>>();
        final Set<Class> recordedClasses = new HashSet<Class>();
        for (final MetaMethod method : sugg) {
            final Class[] arr$;
            final Class[] para = arr$ = method.getNativeParameterTypes();
            for (final Class aPara : arr$) {
                if (!recordedClasses.contains(aPara)) {
                    for (final Class argumentClass : argumentClasses) {
                        if (argumentClass != null) {
                            if (argumentClass != aPara) {
                                if (argumentClass.getName().equals(aPara.getName())) {
                                    ret.add(new Pair<Class, Class>(argumentClass, aPara));
                                }
                            }
                        }
                    }
                    recordedClasses.add(aPara);
                }
            }
        }
        return ret;
    }
    
    private static Class[] getArgumentClasses(final Object[] arguments) {
        final Class[] argumentClasses = new Class[arguments.length];
        for (int i = 0; i < argumentClasses.length; ++i) {
            final Object arg = arguments[i];
            if (arg != null) {
                argumentClasses[i] = arg.getClass();
            }
        }
        return argumentClasses;
    }
    
    public static String getConstructorSuggestionString(final Class type, final Object[] arguments) {
        final Constructor[] sugg = rankConstructors(arguments, type.getConstructors());
        if (sugg.length > 0) {
            final StringBuffer sb = new StringBuffer();
            sb.append("\nPossible solutions: ");
            for (int i = 0; i < sugg.length; ++i) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(type.getName()).append("(");
                sb.append(listParameterNames(sugg[i].getParameterTypes()));
                sb.append(")");
            }
            return sb.toString();
        }
        return "";
    }
    
    public static String getPropertySuggestionString(final String fieldName, final Class type) {
        final ClassInfo ci = ClassInfo.getClassInfo(type);
        final List<MetaProperty> fi = ci.getMetaClass().getProperties();
        final List<RankableField> rf = new ArrayList<RankableField>(fi.size());
        final StringBuffer sb = new StringBuffer();
        sb.append("\nPossible solutions: ");
        for (final MetaProperty mp : fi) {
            rf.add(new RankableField(fieldName, mp));
        }
        Collections.sort(rf);
        int i = 0;
        for (final RankableField f : rf) {
            if (i > 5) {
                break;
            }
            if (f.score > 30) {
                break;
            }
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(f.f.getName());
            ++i;
        }
        return (i > 0) ? sb.toString() : "";
    }
    
    private static String listParameterNames(final Class[] cachedClasses) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cachedClasses.length; ++i) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(cachedClasses[i].getName());
        }
        return sb.toString();
    }
    
    private static String listParameterNames(final CachedClass[] cachedClasses) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cachedClasses.length; ++i) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(cachedClasses[i].getName());
        }
        return sb.toString();
    }
    
    private static List<MetaMethod> rankMethods(final String name, Object[] original, final List<MetaMethod> methods) {
        final List<RankableMethod> rm = new ArrayList<RankableMethod>(methods.size());
        if (original == null) {
            original = new Object[0];
        }
        final Class[] ta = new Class[original.length];
        final Class nullC = NullObject.class;
        for (int i = 0; i < original.length; ++i) {
            ta[i] = ((original[i] == null) ? nullC : original[i].getClass());
        }
        for (final MetaMethod m : methods) {
            rm.add(new RankableMethod(name, ta, m));
        }
        Collections.sort(rm);
        final List<MetaMethod> l = new ArrayList<MetaMethod>(rm.size());
        for (final RankableMethod j : rm) {
            if (l.size() > 5) {
                break;
            }
            if (j.score > 50) {
                break;
            }
            l.add(j.m);
        }
        return l;
    }
    
    private static Constructor[] rankConstructors(final Object[] original, final Constructor[] candidates) {
        final RankableConstructor[] rc = new RankableConstructor[candidates.length];
        final Class[] ta = new Class[original.length];
        final Class nullC = NullObject.class;
        for (int i = 0; i < original.length; ++i) {
            ta[i] = ((original[i] == null) ? nullC : original[i].getClass());
        }
        for (int i = 0; i < candidates.length; ++i) {
            rc[i] = new RankableConstructor(ta, candidates[i]);
        }
        Arrays.sort(rc);
        final List<Constructor> l = new ArrayList<Constructor>();
        for (int index = 0; l.size() < 5 && index < rc.length && rc[index].score < 20; ++index) {
            l.add(rc[index].c);
        }
        return l.toArray(new Constructor[l.size()]);
    }
    
    protected static Class boxVar(final Class c) {
        if (Boolean.TYPE.equals(c)) {
            return Boolean.class;
        }
        if (Character.TYPE.equals(c)) {
            return Character.class;
        }
        if (Byte.TYPE.equals(c)) {
            return Byte.class;
        }
        if (Double.TYPE.equals(c)) {
            return Double.class;
        }
        if (Float.TYPE.equals(c)) {
            return Float.class;
        }
        if (Integer.TYPE.equals(c)) {
            return Integer.class;
        }
        if (Long.TYPE.equals(c)) {
            return Long.class;
        }
        if (Short.TYPE.equals(c)) {
            return Short.class;
        }
        return c;
    }
    
    public static int delDistance(final CharSequence s, final CharSequence t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        final int n = s.length();
        final int m = t.length();
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        final int[][] vals = new int[3][n + 1];
        for (int i = 0; i <= n; ++i) {
            vals[1][i] = i * 10;
        }
        for (int j = 1; j <= m; ++j) {
            final char t_j = t.charAt(j - 1);
            vals[0][0] = j * 10;
            for (int i = 1; i <= n; ++i) {
                final char s_i = s.charAt(i - 1);
                int cost;
                if (Character.isLowerCase(s_i) ^ Character.isLowerCase(t_j)) {
                    cost = (caselessCompare(s_i, t_j) ? 5 : 10);
                }
                else {
                    cost = ((s_i == t_j) ? 0 : 10);
                }
                vals[0][i] = Math.min(Math.min(vals[0][i - 1] + 10, vals[1][i] + 10), vals[1][i - 1] + cost);
                if (i > 1 && j > 1) {
                    cost = ((Character.isLowerCase(s_i) ^ Character.isLowerCase(t.charAt(j - 2))) ? 5 : 0);
                    cost = ((Character.isLowerCase(s.charAt(i - 2)) ^ Character.isLowerCase(t_j)) ? (cost + 5) : cost);
                    if (caselessCompare(s_i, t.charAt(j - 2)) && caselessCompare(s.charAt(i - 2), t_j)) {
                        vals[0][i] = Math.min(vals[0][i], vals[2][i - 2] + 5 + cost);
                    }
                }
            }
            final int[] _d = vals[2];
            vals[2] = vals[1];
            vals[1] = vals[0];
            vals[0] = _d;
        }
        return vals[1][n];
    }
    
    private static boolean caselessCompare(final char a, final char b) {
        return Character.toLowerCase(a) == Character.toLowerCase(b);
    }
    
    public static int damerauLevenshteinDistance(final Object[] s, final Object[] t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Arrays must not be null");
        }
        final int n = s.length;
        final int m = t.length;
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        final int[][] vals = new int[3][n + 1];
        for (int i = 0; i <= n; ++i) {
            vals[1][i] = i * 10;
        }
        for (int j = 1; j <= m; ++j) {
            final Object t_j = t[j - 1];
            vals[0][0] = j * 10;
            for (int i = 1; i <= n; ++i) {
                final int cost = s[i - 1].equals(t_j) ? 0 : 10;
                vals[0][i] = Math.min(Math.min(vals[0][i - 1] + 10, vals[1][i] + 10), vals[1][i - 1] + cost);
                if (i > 1 && j > 1 && s[i - 1].equals(t[j - 2]) && s[i - 2].equals(t_j)) {
                    vals[0][i] = Math.min(vals[0][i], vals[2][i - 2] + 5);
                }
            }
            final int[] _d = vals[2];
            vals[2] = vals[1];
            vals[1] = vals[0];
            vals[0] = _d;
        }
        return vals[1][n];
    }
    
    private static final class Pair<U, V>
    {
        private U u;
        private V v;
        
        public Pair(final U u, final V v) {
            this.u = u;
            this.v = v;
        }
    }
    
    private static final class RankableMethod implements Comparable
    {
        final MetaMethod m;
        final Integer score;
        
        public RankableMethod(final String name, final Class[] argumentTypes, final MetaMethod m2) {
            this.m = m2;
            final int nameDist = MethodRankHelper.delDistance(name, m2.getName());
            final Class[] mArgs = new Class[m2.getParameterTypes().length];
            for (int i = 0; i < mArgs.length; ++i) {
                mArgs[i] = MethodRankHelper.boxVar(m2.getParameterTypes()[i].getTheClass());
            }
            final int argDist = MethodRankHelper.damerauLevenshteinDistance(argumentTypes, mArgs);
            this.score = nameDist + argDist;
        }
        
        public int compareTo(final Object o) {
            final RankableMethod mo = (RankableMethod)o;
            return this.score.compareTo(mo.score);
        }
    }
    
    private static final class RankableConstructor implements Comparable
    {
        final Constructor c;
        final Integer score;
        
        public RankableConstructor(final Class[] argumentTypes, final Constructor c) {
            this.c = c;
            final Class[] cArgs = new Class[c.getParameterTypes().length];
            for (int i = 0; i < cArgs.length; ++i) {
                cArgs[i] = MethodRankHelper.boxVar(c.getParameterTypes()[i]);
            }
            this.score = MethodRankHelper.damerauLevenshteinDistance(argumentTypes, cArgs);
        }
        
        public int compareTo(final Object o) {
            final RankableConstructor co = (RankableConstructor)o;
            return this.score.compareTo(co.score);
        }
    }
    
    private static final class RankableField implements Comparable
    {
        final MetaProperty f;
        final Integer score;
        
        public RankableField(final String name, final MetaProperty mp) {
            this.f = mp;
            this.score = MethodRankHelper.delDistance(name, mp.getName());
        }
        
        public int compareTo(final Object o) {
            final RankableField co = (RankableField)o;
            return this.score.compareTo(co.score);
        }
    }
    
    private static class NullObject
    {
    }
}
