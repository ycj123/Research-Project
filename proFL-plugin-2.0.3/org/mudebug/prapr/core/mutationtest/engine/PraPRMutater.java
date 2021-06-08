// 
// Decompiled by Procyon v0.5.36
// 

package org.mudebug.prapr.core.mutationtest.engine;

import org.pitest.functional.prelude.Prelude;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.Commons;
import org.pitest.bytecode.NullVisitor;
import org.pitest.functional.MutableList;
import org.pitest.util.Log;
import org.pitest.classinfo.ClassName;
import java.util.List;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.CollectedClassInfo;
import org.pitest.reloc.asm.ClassWriter;
import org.pitest.mutationtest.engine.MutationDetails;
import org.pitest.reloc.asm.ClassVisitor;
import org.pitest.functional.F;
import org.pitest.functional.FCollection;
import org.mudebug.prapr.core.mutationtest.engine.mutators.util.ClassInfoCollector;
import org.pitest.classinfo.ComputeClassWriter;
import org.pitest.bytecode.FrameOptions;
import org.pitest.reloc.asm.ClassReader;
import org.pitest.functional.Option;
import org.pitest.mutationtest.engine.gregor.PraPRMutaterClassContext;
import org.pitest.mutationtest.engine.Mutant;
import org.pitest.mutationtest.engine.MutationIdentifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Collection;
import org.mudebug.prapr.core.analysis.GlobalInfo;
import org.mudebug.prapr.core.SuspChecker;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import java.util.Set;
import org.pitest.classinfo.ClassByteArraySource;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.functional.predicate.Predicate;
import java.util.Map;
import org.pitest.mutationtest.engine.Mutater;

public class PraPRMutater implements Mutater
{
    private final Map<String, String> computeCache;
    private final Predicate<MethodInfo> filter;
    private final ClassByteArraySource byteSource;
    private final Set<MethodMutatorFactory> mutators;
    private final SuspChecker suspChecker;
    private final GlobalInfo classHierarchy;
    
    public PraPRMutater(final Predicate<MethodInfo> filter, final Collection<MethodMutatorFactory> mutators, final ClassByteArraySource byteSource, final SuspChecker suspChecker, final GlobalInfo classHierarchy) {
        this.filter = filter;
        this.mutators = new HashSet<MethodMutatorFactory>(mutators);
        this.byteSource = byteSource;
        this.suspChecker = suspChecker;
        this.classHierarchy = classHierarchy;
        this.computeCache = new HashMap<String, String>();
    }
    
    @Override
    public Mutant getMutation(final MutationIdentifier id) {
        final PraPRMutaterClassContext context = new PraPRMutaterClassContext();
        context.setTargetMutation(Option.some(id));
        final Option<byte[]> bytes = this.byteSource.getBytes(id.getClassName().asJavaName());
        final ClassReader reader = new ClassReader(bytes.value());
        final ClassWriter w = new ComputeClassWriter(this.byteSource, this.computeCache, FrameOptions.pickFlags(bytes.value()));
        final CollectedClassInfo cci = ClassInfoCollector.collect(bytes.value());
        final MutatingClassVisitor mca = new MutatingClassVisitor(w, context, this.filterMethods(), (Collection<MethodMutatorFactory>)FCollection.filter(this.mutators, (F<Object, Boolean>)isMutatorFor(id)), cci, this.byteSource, this.classHierarchy);
        reader.accept(mca, 8);
        final List<MutationDetails> details = (List<MutationDetails>)context.getMutationDetails(context.getTargetMutation().value());
        return new Mutant(details.get(0), w.toByteArray());
    }
    
    private static Predicate<MethodMutatorFactory> isMutatorFor(final MutationIdentifier id) {
        return new Predicate<MethodMutatorFactory>() {
            @Override
            public Boolean apply(final MethodMutatorFactory a) {
                return id.getMutator().equals(a.getGloballyUniqueId());
            }
        };
    }
    
    @Override
    public List<MutationDetails> findMutations(final ClassName classToMutate) {
        if (!this.suspChecker.isHit(classToMutate.asJavaName())) {
            Log.getLogger().info(String.format("*** THE CLASS %s IS LEFT UNMUTATED.", classToMutate.asJavaName()));
            return new MutableList<MutationDetails>();
        }
        final PraPRMutaterClassContext context = new PraPRMutaterClassContext();
        context.setTargetMutation((Option)Option.none());
        return (List<MutationDetails>)this.byteSource.getBytes(classToMutate.asInternalName()).flatMap((F<byte[], ? extends Iterable<Object>>)this.findMutations(context));
    }
    
    private F<byte[], Iterable<MutationDetails>> findMutations(final PraPRMutaterClassContext context) {
        return new F<byte[], Iterable<MutationDetails>>() {
            @Override
            public Iterable<MutationDetails> apply(final byte[] bytes) {
                return PraPRMutater.this.findMutationsForBytes(context, bytes);
            }
        };
    }
    
    private Collection<MutationDetails> findMutationsForBytes(final PraPRMutaterClassContext context, final byte[] classToMutate) {
        final CollectedClassInfo cci = ClassInfoCollector.collect(classToMutate);
        final ClassReader first = new ClassReader(classToMutate);
        final NullVisitor nv = new NullVisitor();
        final MutatingClassVisitor mca = new MutatingClassVisitor(nv, context, this.filterMethods(), this.mutators, cci, this.byteSource, this.classHierarchy);
        first.accept(mca, 8);
        return FCollection.filter(context.getCollectedMutations(), this.effectiveMutationChecker());
    }
    
    private F<MutationDetails, Boolean> effectiveMutationChecker() {
        return new F<MutationDetails, Boolean>() {
            @Override
            public Boolean apply(final MutationDetails mutationDetails) {
                return mutationDetails.getInstructionIndex() >= 0;
            }
        };
    }
    
    private static Predicate<MethodInfo> shouldMutate(final SuspChecker suspChecker) {
        return new Predicate<MethodInfo>() {
            @Override
            public Boolean apply(final MethodInfo a) {
                final ClassName owningClassName = Commons.getOwningClassName(a);
                return suspChecker.isHit(owningClassName.asJavaName(), a.getName() + a.getMethodDescriptor());
            }
        };
    }
    
    private Predicate<MethodInfo> filterMethods() {
        return (Predicate<MethodInfo>)Prelude.and(this.filter, filterSyntheticMethods(), shouldMutate(this.suspChecker), Prelude.not((F<Object, Boolean>)isGeneratedEnumMethod()), Prelude.not(isGroovyClass()));
    }
    
    private static F<MethodInfo, Boolean> isGroovyClass() {
        return new Predicate<MethodInfo>() {
            @Override
            public Boolean apply(final MethodInfo a) {
                return a.isInGroovyClass();
            }
        };
    }
    
    private static Predicate<MethodInfo> filterSyntheticMethods() {
        return new Predicate<MethodInfo>() {
            @Override
            public Boolean apply(final MethodInfo a) {
                return !a.isSynthetic() || a.getName().startsWith("lambda$");
            }
        };
    }
    
    private static Predicate<MethodInfo> isGeneratedEnumMethod() {
        return new Predicate<MethodInfo>() {
            @Override
            public Boolean apply(final MethodInfo a) {
                return a.isGeneratedEnumMethod();
            }
        };
    }
}
