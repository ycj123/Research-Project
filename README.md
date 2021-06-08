# Research-Project
This is the research project repo of Chijiang Yang.

This research mainly focuses on the Unified Debugging tool ProFL.

We brought up five research questions and answered all of them through experiment.

Files
-----------------

- Folder `proFL-plugin-2.0.3` contains all the source code.
- Folder `Closure-pomFile` contains all the pom.xml file for RQ5.
- Script `gen.sh` is used to generate all the buggy projects from Defects4J.
- Folder `Buggy projects` contains example projects of our research. (Because of the size limit on Github and my Macbook hard drive, we could not store and upload all the projects in Defects4J.)
- File `Results.csv` stores all the results of this project.

Study Design
================

- RQ1 : How does rank of categories affect performance of ProFL?
- RQ2 : How does different formula affect performance of ProFL?
- RQ3 : How does aggregation score affect performance of ProFL?
- RQ4 : Can we improve the ProFL by designing a new way to calculate the final score?
- RQ5 : How does ProFL tool perform with Defects4J 1.5.0?

RQ1
-----------------
First of all, as for the research on categories, ProFL define the category type in org/mudebug/profl/core/PatchCategory.java

```
package org.mudebug.profl.core;

public enum PatchCategory
{
    CLEAN_FIX, 
    NOISY_FIX, 
    NONE_FIX, 
    NEG_FIX;
}
```
And execute the compare in ProFLComparator.java
```{Java}
// original version of ProFL
package org.mudebug.profl.core;

import java.util.Comparator;

class ProFLComparator implements Comparator<Tuple>
{
    @Override
    public int compare(final Tuple t1, final Tuple t2) {
        final PatchCategory caty1 = t1.getCategory();
        final PatchCategory caty2 = t2.getCategory();
        if (caty1.compareTo(caty2) != 0) {
            return caty1.compareTo(caty2);
        }
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
}
```
In the research of RQ1, we modified the `compare` function. 

C1 :
```{Java}
public int compare(final Tuple t1, final Tuple t2) {
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
```

C2 :
```
public int compare(final Tuple t1, final Tuple t2) {
        final PatchCategory caty1 = t1.getCategory();
        final PatchCategory caty2 = t2.getCategory();
        if (caty1 == PatchCategory.CLEAN_FIX && caty2 != PatchCategory.CLEAN_FIX) {
            return True;
        } 
        if (caty1 != PatchCategory.CLEAN_FIX && caty2 == PatchCategory.CLEAN_FIX) {
            return False;
        }
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
```

C3 :
```
public int compare(final Tuple t1, final Tuple t2) {
        final PatchCategory caty1 = t1.getCategory();
        final PatchCategory caty2 = t2.getCategory();
        if (caty1 == PatchCategory.NEG_FIX) {
            caty1 = PatchCategory.NONE_FIX;
        } 
        if (caty2 == PatchCategory.NEG_FIX) {
            caty2 = PatchCategory.NONE_FIX;
        } 
        if (caty1.compareTo(caty2) != 0) {
            return caty1.compareTo(caty2);
        }
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
```
C4 :
```
public int compare(final Tuple t1, final Tuple t2) {
        final PatchCategory caty1 = t1.getCategory();
        final PatchCategory caty2 = t2.getCategory();
        if (caty1 == PatchCategory.CLEAN_FIX) {
            caty1 = PatchCategory.NOISY_FIX;
        } 
        if (caty2 == PatchCategory.CLEAN_FIX) {
            caty2 = PatchCategory.NOISY_FIX;
        }  
        if (caty1.compareTo(caty2) != 0) {
            return caty1.compareTo(caty2);
        }
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
```
C5 :
```
public int compare(final Tuple t1, final Tuple t2) {
        final PatchCategory caty1 = t1.getCategory();
        final PatchCategory caty2 = t2.getCategory();
        if (caty1 == PatchCategory.NEG_FIX && caty2 != PatchCategory.NEG_FIX) {
            return False;
        } 
        if (caty1 != PatchCategory.NEG_FIX && caty2 == PatchCategory.NEG_FIX) {
            return True;
        }
        final double susp1 = t1.getSuspiciousnessValue();
        final double susp2 = t2.getSuspiciousnessValue();
        return (susp1 == susp2) ? 0 : ((susp1 > susp2) ? -1 : 1);
    }
```
RQ2
-----------------
In RQ2, we studied three different SBFL suspiciousness value formulae. And define all of them in org/mudebug/prapr/core/SuspStrategyImpl.java

```
public enum SuspStrategyImpl implements SuspStrategy
{
    OCHIAI {
        @Override
        public double computeSusp(final int ef, final int ep, final int nf, final int np) {
            final double denom = Math.sqrt((ef + ep) * (ef + nf));
            return (denom > 0.0) ? (ef / denom) : 0.0;
        }
    }, 
    TARANTULA {
        @Override
        public double computeSusp(final int ef, final int ep, final int nf, final int np) {
            final double denom1 = (ef + nf == 0) ? 0.0 : (ef / (double)(ef + nf));
            final double denom2 = (ep + np == 0) ? 0.0 : (ep / (double)(ep + np));
            final double denom3 = denom1 + denom2;
            return (denom3 > 0.0) ? (denom1 / denom3) : 0.0;
        }
    },
    DSTAR {
        @Override
        public double computeSusp(final int ef, final int ep, final int nf, final int np) {
            final int n = 2;// n = 2,2.5,,,
            final double denom1 = ep + nf;
            final double denom2 = ef^n;
            return (denom1 > 0.0) ? (denom2 / denom1) : 0.0;
        }
    };
}
```
RQ3
-----------------
In order to answer RQ3, we have to modify the process of setting suspicious value to each method based on the value of each statements. So we modified the code in org/mudebug/profl/core/Aggregator.java 

We declare an hashmap called `suspList` to store all the suspicious value for each method before aggregation. And then calculate the final score in different ways.
```
public static List<Tuple> aggregate(final List<Mutation> mutations, final boolean profl) {
        final Map<String, Tuple> grouped = new HashMap<String, Tuple>();
        final HashMap<String,List<Double>> suspList = new HashMap<String,List<Double>>; //store suspicious value
        final set<String> methodSet = new HashSet<String>();
        for (final Mutation mutation : mutations) {
            final String methodId = mutation.getMethodId();
            final double susp = mutation.getSuspiciousnessValue();
            final PatchCategory caty = mutation.getCategorization();
            if (!grouped.containsKey(methodId)) {
                grouped.put(methodId, new Tuple(methodId, susp, caty));
            }
            else {
                methodSet.add(methodId); // Add methodID to set
                if (!suspList.get(methodId)) {
                    suspList.put(methodId, new List<Double>());
                } 
                new List<Double> currentList = suspList.get(methodId);
                currentList.add(susp);

                //if (susp > grouped.get(methodId).getSuspiciousnessValue()) {
                    //grouped.get(methodId).setSuspiciousnessValue(susp);
                //}
                if (caty.compareTo(grouped.get(methodId).getCategory()) >= 0) {
                    continue;
                }
                grouped.get(methodId).setCategory(caty);
            }
        }
        for (String temp : methodSet) {
            currentList = suspList.get(temp);
            final double finalScore = getValue(currentList); // differnet way to calcute final score
            grouped.get(temp).setSuspiciousnessValue(finalScore);
        }
        final List<Tuple> ranked = new LinkedList<Tuple>();
        ranked.addAll(grouped.values());
        if (profl) {
            Collections.sort(ranked, new ProFLComparator());
        }
        else {
            Collections.sort(ranked, new SBFLComparator());
        }
        return ranked;
    }
```

RQ4
-----------------
In order to answer RQ4, we have to count number of different categories. So we modified the code in org/mudebug/profl/core/Aggregator.java

We count the total number for each type of Fix and use them as input of funcation `ProFLComparatorRQ4`. Finally, compare the reranking based on the information of four types of categories.
```
public class AggregatorJDK7
{
    public static List<Tuple> aggregate(final List<Mutation> mutations, final boolean profl) {
        final Map<String, Tuple> grouped = new HashMap<String, Tuple>();
        final int n1,n2,n3,n4;
        n1 = n2 = n3 = n4 = 0;
        for (final Mutation mutation : mutations) {
            final String methodId = mutation.getMethodId();
            final double susp = mutation.getSuspiciousnessValue();
            final PatchCategory caty = mutation.getCategorization();
            if (caty == PatchCategory.CLEAN_FIX) {
                n1 += 1;
            }
            if (caty == PatchCategory.NOISY_FIX) {
                n2 += 1;
            }
            if (caty == PatchCategory.NONE_FIX) {
                n3 += 1;
            }
            if (caty == PatchCategory.NEG_FIX) {
                n4 += 1;
            }  
            if (!grouped.containsKey(methodId)) {
                grouped.put(methodId, new Tuple(methodId, susp, caty));
            }
            else {
                if (susp > grouped.get(methodId).getSuspiciousnessValue()) {
                    grouped.get(methodId).setSuspiciousnessValue(susp);
                }
                if (caty.compareTo(grouped.get(methodId).getCategory()) >= 0) {
                    continue;
                }
                grouped.get(methodId).setCategory(caty);
            }
        }
        final List<Tuple> ranked = new LinkedList<Tuple>();
        ranked.addAll(grouped.values());
        if (profl) {
            Collections.sort(ranked, new ProFLComparatorRQ4(n1,n2,n3,n4));
        }
        else {
            Collections.sort(ranked, new SBFLComparator());
        }
        return ranked;
    }
}
```
RQ5
-----------------
Defects4J V1.5.0 contains 438 bugs from the following open-source projects:

| Identifier      | Project name               | Number of bugs | 
|-----------------|----------------------------|---------------:|
| Chart           | jfreechart                 |       26       | 
| Closure         | closure-compiler           |      176       | 
| Lang            | commons-lang               |       65       |
| Math            | commons-math               |      106       | 
| Mockito         | mockito                    |       38       | 
| Time            | joda-time                  |       27       |

while Defects4J V1.4.0 contains 395 bugs:
| Identifier      | Project name               | Number of bugs | 
|-----------------|----------------------------|---------------:|
| Chart           | jfreechart                 |       26       | 
| Closure         | closure-compiler           |      133       | 
| Lang            | commons-lang               |       65       |
| Math            | commons-math               |      106       | 
| Mockito         | mockito                    |       38       | 
| Time            | joda-time                  |       27       |


Guidance
---------------
1. Generate buggy projects from Defects4J using script `gen.sh`
2. Enter target project
3. Use command-line `mvn org.mudebug:prapr-plugin:profl -Dhttps.protocols=TLSv1.2` to run ProFL in target project
4. Collect information from reports `profl.log` and `sbfl.log`

Results
----
All the experiment results are collected from the reports manually, and they are stored in the csv file `Results.csv`.

Research Project of Chijiang Yang Unimelb
