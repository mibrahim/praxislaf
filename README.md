praxislaf
=========

The praxis look and feel.

Based on the praxis code available https://code.google.com/p/praxis/source/browse .

I mavenized the code, separated the icons in the resources package. To use in conjuncion with netbeans, compile, 
put the jar somewhere, and execute netbeans with the following commandline:


netbeans -cp:p <path to your praxis jar> --laf net.neilcsmith.praxis.laf.PraxisLookAndFeel
