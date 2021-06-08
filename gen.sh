#!/bin/bash -x
cd ./Bugs
for ((i=1;i<177;i++));
do
	# defects4j checkout -p Math -v ${i}b -w ./Math_${i}_buggy
	# defects4j checkout -p Lang -v ${i}b -w ./Lang_${i}_buggy
	# defects4j checkout -p Time -v ${i}b -w ./Time_${i}_buggy
    defects4j checkout -p Closure -v ${i}b -w ./Closure_${i}_buggy
    cd ./Closure_${i}_buggy
    defects4j complie
    defects4j test
    cd ../
done
