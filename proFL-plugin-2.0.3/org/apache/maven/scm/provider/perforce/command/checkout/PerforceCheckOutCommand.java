// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.scm.provider.perforce.command.checkout;

import java.util.regex.Matcher;
import org.codehaus.plexus.util.cli.CommandLineException;
import java.io.IOException;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.scm.provider.perforce.PerforceScmProvider;
import org.codehaus.plexus.util.cli.Commandline;
import java.io.File;
import org.apache.maven.scm.provider.perforce.repository.PerforceScmProviderRepository;
import org.apache.maven.scm.ScmException;
import org.apache.maven.scm.command.checkout.CheckOutScmResult;
import org.apache.maven.scm.ScmVersion;
import org.apache.maven.scm.ScmFileSet;
import org.apache.maven.scm.provider.ScmProviderRepository;
import org.apache.maven.scm.provider.perforce.command.PerforceCommand;
import org.apache.maven.scm.command.checkout.AbstractCheckOutCommand;

public class PerforceCheckOutCommand extends AbstractCheckOutCommand implements PerforceCommand
{
    private String actualLocation;
    
    @Override
    protected CheckOutScmResult executeCheckOutCommand(final ScmProviderRepository repo, final ScmFileSet files, final ScmVersion version, final boolean recursive) throws ScmException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: checkcast       Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;
        //     4: astore          prepo
        //     6: new             Ljava/io/File;
        //     9: dup            
        //    10: aload_2         /* files */
        //    11: invokevirtual   org/apache/maven/scm/ScmFileSet.getBasedir:()Ljava/io/File;
        //    14: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    17: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    20: astore          workingDirectory
        //    22: aload_0         /* this */
        //    23: aload_0         /* this */
        //    24: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //    27: aload           prepo
        //    29: aload_2         /* files */
        //    30: invokevirtual   org/apache/maven/scm/ScmFileSet.getBasedir:()Ljava/io/File;
        //    33: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.getRepoPath:(Lorg/apache/maven/scm/log/ScmLogger;Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;)Ljava/lang/String;
        //    36: putfield        org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.actualLocation:Ljava/lang/String;
        //    39: aload_0         /* this */
        //    40: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //    43: aload           prepo
        //    45: aload           workingDirectory
        //    47: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.getClientspecName:(Lorg/apache/maven/scm/log/ScmLogger;Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;)Ljava/lang/String;
        //    50: astore          specname
        //    52: new             Lorg/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer;
        //    55: dup            
        //    56: aload           specname
        //    58: aload_0         /* this */
        //    59: getfield        org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.actualLocation:Ljava/lang/String;
        //    62: invokespecial   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    65: astore          consumer
        //    67: aload_0         /* this */
        //    68: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //    71: invokeinterface org/apache/maven/scm/log/ScmLogger.isInfoEnabled:()Z
        //    76: ifeq            108
        //    79: aload_0         /* this */
        //    80: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //    83: new             Ljava/lang/StringBuilder;
        //    86: dup            
        //    87: invokespecial   java/lang/StringBuilder.<init>:()V
        //    90: ldc             "Checkout working directory: "
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: aload           workingDirectory
        //    97: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   100: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   103: invokeinterface org/apache/maven/scm/log/ScmLogger.info:(Ljava/lang/String;)V
        //   108: aconst_null    
        //   109: astore          cl
        //   111: aload           prepo
        //   113: aload           workingDirectory
        //   115: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.createP4Command:(Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;)Lorg/codehaus/plexus/util/cli/Commandline;
        //   118: astore          cl
        //   120: aload           cl
        //   122: invokevirtual   org/codehaus/plexus/util/cli/Commandline.createArg:()Lorg/codehaus/plexus/util/cli/Arg;
        //   125: ldc             "client"
        //   127: invokeinterface org/codehaus/plexus/util/cli/Arg.setValue:(Ljava/lang/String;)V
        //   132: aload           cl
        //   134: invokevirtual   org/codehaus/plexus/util/cli/Commandline.createArg:()Lorg/codehaus/plexus/util/cli/Arg;
        //   137: ldc             "-i"
        //   139: invokeinterface org/codehaus/plexus/util/cli/Arg.setValue:(Ljava/lang/String;)V
        //   144: aload_0         /* this */
        //   145: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   148: invokeinterface org/apache/maven/scm/log/ScmLogger.isInfoEnabled:()Z
        //   153: ifeq            191
        //   156: aload_0         /* this */
        //   157: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   160: new             Ljava/lang/StringBuilder;
        //   163: dup            
        //   164: invokespecial   java/lang/StringBuilder.<init>:()V
        //   167: ldc             "Executing: "
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: aload           cl
        //   174: invokevirtual   org/codehaus/plexus/util/cli/Commandline.toString:()Ljava/lang/String;
        //   177: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.clean:(Ljava/lang/String;)Ljava/lang/String;
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   186: invokeinterface org/apache/maven/scm/log/ScmLogger.info:(Ljava/lang/String;)V
        //   191: aload_0         /* this */
        //   192: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   195: aload           prepo
        //   197: aload           workingDirectory
        //   199: aload_0         /* this */
        //   200: getfield        org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.actualLocation:Ljava/lang/String;
        //   203: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.createClientspec:(Lorg/apache/maven/scm/log/ScmLogger;Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;Ljava/lang/String;)Ljava/lang/String;
        //   206: astore          client
        //   208: aload_0         /* this */
        //   209: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   212: invokeinterface org/apache/maven/scm/log/ScmLogger.isDebugEnabled:()Z
        //   217: ifeq            249
        //   220: aload_0         /* this */
        //   221: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   224: new             Ljava/lang/StringBuilder;
        //   227: dup            
        //   228: invokespecial   java/lang/StringBuilder.<init>:()V
        //   231: ldc             "Updating clientspec:\n"
        //   233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   236: aload           client
        //   238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   244: invokeinterface org/apache/maven/scm/log/ScmLogger.debug:(Ljava/lang/String;)V
        //   249: new             Lorg/codehaus/plexus/util/cli/CommandLineUtils$StringStreamConsumer;
        //   252: dup            
        //   253: invokespecial   org/codehaus/plexus/util/cli/CommandLineUtils$StringStreamConsumer.<init>:()V
        //   256: astore          err
        //   258: aload           cl
        //   260: new             Ljava/io/ByteArrayInputStream;
        //   263: dup            
        //   264: aload           client
        //   266: invokevirtual   java/lang/String.getBytes:()[B
        //   269: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //   272: aload           consumer
        //   274: aload           err
        //   276: invokestatic    org/codehaus/plexus/util/cli/CommandLineUtils.executeCommandLine:(Lorg/codehaus/plexus/util/cli/Commandline;Ljava/io/InputStream;Lorg/codehaus/plexus/util/cli/StreamConsumer;Lorg/codehaus/plexus/util/cli/StreamConsumer;)I
        //   279: istore          exitCode
        //   281: iload           exitCode
        //   283: ifeq            385
        //   286: aload           cl
        //   288: invokevirtual   org/codehaus/plexus/util/cli/Commandline.getCommandline:()[Ljava/lang/String;
        //   291: invokestatic    org/codehaus/plexus/util/cli/CommandLineUtils.toString:([Ljava/lang/String;)Ljava/lang/String;
        //   294: astore          cmdLine
        //   296: new             Ljava/lang/StringBuilder;
        //   299: dup            
        //   300: new             Ljava/lang/StringBuilder;
        //   303: dup            
        //   304: invokespecial   java/lang/StringBuilder.<init>:()V
        //   307: ldc             "Exit code: "
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: iload           exitCode
        //   314: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   317: ldc             " - "
        //   319: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   322: aload           err
        //   324: invokevirtual   org/codehaus/plexus/util/cli/CommandLineUtils$StringStreamConsumer.getOutput:()Ljava/lang/String;
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   330: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   333: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   336: astore          msg
        //   338: aload           msg
        //   340: bipush          10
        //   342: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   345: pop            
        //   346: aload           msg
        //   348: new             Ljava/lang/StringBuilder;
        //   351: dup            
        //   352: invokespecial   java/lang/StringBuilder.<init>:()V
        //   355: ldc             "Command line was:"
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: aload           cmdLine
        //   362: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   365: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   368: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   371: pop            
        //   372: new             Lorg/codehaus/plexus/util/cli/CommandLineException;
        //   375: dup            
        //   376: aload           msg
        //   378: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   381: invokespecial   org/codehaus/plexus/util/cli/CommandLineException.<init>:(Ljava/lang/String;)V
        //   384: athrow         
        //   385: goto            436
        //   388: astore          e
        //   390: aload_0         /* this */
        //   391: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   394: invokeinterface org/apache/maven/scm/log/ScmLogger.isErrorEnabled:()Z
        //   399: ifeq            436
        //   402: aload_0         /* this */
        //   403: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   406: new             Ljava/lang/StringBuilder;
        //   409: dup            
        //   410: invokespecial   java/lang/StringBuilder.<init>:()V
        //   413: ldc             "CommandLineException "
        //   415: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   418: aload           e
        //   420: invokevirtual   org/codehaus/plexus/util/cli/CommandLineException.getMessage:()Ljava/lang/String;
        //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   429: aload           e
        //   431: invokeinterface org/apache/maven/scm/log/ScmLogger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   436: aload           consumer
        //   438: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.isSuccess:()Z
        //   441: istore          clientspecExists
        //   443: iload           clientspecExists
        //   445: ifeq            857
        //   448: aload_0         /* this */
        //   449: aload           prepo
        //   451: aload           workingDirectory
        //   453: aload           specname
        //   455: invokespecial   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLastChangelist:(Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;Ljava/lang/String;)I
        //   458: pop            
        //   459: aload           prepo
        //   461: aload           workingDirectory
        //   463: aload_3         /* version */
        //   464: aload           specname
        //   466: invokestatic    org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.createCommandLine:(Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;Lorg/apache/maven/scm/ScmVersion;Ljava/lang/String;)Lorg/codehaus/plexus/util/cli/Commandline;
        //   469: astore          cl
        //   471: aload_0         /* this */
        //   472: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   475: invokeinterface org/apache/maven/scm/log/ScmLogger.isDebugEnabled:()Z
        //   480: ifeq            518
        //   483: aload_0         /* this */
        //   484: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   487: new             Ljava/lang/StringBuilder;
        //   490: dup            
        //   491: invokespecial   java/lang/StringBuilder.<init>:()V
        //   494: ldc             "Executing: "
        //   496: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   499: aload           cl
        //   501: invokevirtual   org/codehaus/plexus/util/cli/Commandline.toString:()Ljava/lang/String;
        //   504: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.clean:(Ljava/lang/String;)Ljava/lang/String;
        //   507: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   510: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   513: invokeinterface org/apache/maven/scm/log/ScmLogger.debug:(Ljava/lang/String;)V
        //   518: aload           cl
        //   520: invokevirtual   org/codehaus/plexus/util/cli/Commandline.execute:()Ljava/lang/Process;
        //   523: astore          proc
        //   525: new             Ljava/io/BufferedReader;
        //   528: dup            
        //   529: new             Ljava/io/InputStreamReader;
        //   532: dup            
        //   533: aload           proc
        //   535: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //   538: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   541: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   544: astore          br
        //   546: aload           br
        //   548: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   551: dup            
        //   552: astore          line
        //   554: ifnull          608
        //   557: aload_0         /* this */
        //   558: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   561: invokeinterface org/apache/maven/scm/log/ScmLogger.isDebugEnabled:()Z
        //   566: ifeq            598
        //   569: aload_0         /* this */
        //   570: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   573: new             Ljava/lang/StringBuilder;
        //   576: dup            
        //   577: invokespecial   java/lang/StringBuilder.<init>:()V
        //   580: ldc             "Consuming: "
        //   582: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   585: aload           line
        //   587: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   590: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   593: invokeinterface org/apache/maven/scm/log/ScmLogger.debug:(Ljava/lang/String;)V
        //   598: aload           consumer
        //   600: aload           line
        //   602: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.consumeLine:(Ljava/lang/String;)V
        //   605: goto            546
        //   608: new             Lorg/codehaus/plexus/util/cli/CommandLineUtils$StringStreamConsumer;
        //   611: dup            
        //   612: invokespecial   org/codehaus/plexus/util/cli/CommandLineUtils$StringStreamConsumer.<init>:()V
        //   615: astore          err
        //   617: aload           cl
        //   619: aload           consumer
        //   621: aload           err
        //   623: invokestatic    org/codehaus/plexus/util/cli/CommandLineUtils.executeCommandLine:(Lorg/codehaus/plexus/util/cli/Commandline;Lorg/codehaus/plexus/util/cli/StreamConsumer;Lorg/codehaus/plexus/util/cli/StreamConsumer;)I
        //   626: istore          exitCode
        //   628: iload           exitCode
        //   630: ifeq            732
        //   633: aload           cl
        //   635: invokevirtual   org/codehaus/plexus/util/cli/Commandline.getCommandline:()[Ljava/lang/String;
        //   638: invokestatic    org/codehaus/plexus/util/cli/CommandLineUtils.toString:([Ljava/lang/String;)Ljava/lang/String;
        //   641: astore          cmdLine
        //   643: new             Ljava/lang/StringBuilder;
        //   646: dup            
        //   647: new             Ljava/lang/StringBuilder;
        //   650: dup            
        //   651: invokespecial   java/lang/StringBuilder.<init>:()V
        //   654: ldc             "Exit code: "
        //   656: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   659: iload           exitCode
        //   661: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   664: ldc             " - "
        //   666: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   669: aload           err
        //   671: invokevirtual   org/codehaus/plexus/util/cli/CommandLineUtils$StringStreamConsumer.getOutput:()Ljava/lang/String;
        //   674: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   677: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   680: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   683: astore          msg
        //   685: aload           msg
        //   687: bipush          10
        //   689: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   692: pop            
        //   693: aload           msg
        //   695: new             Ljava/lang/StringBuilder;
        //   698: dup            
        //   699: invokespecial   java/lang/StringBuilder.<init>:()V
        //   702: ldc             "Command line was:"
        //   704: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   707: aload           cmdLine
        //   709: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   712: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   715: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   718: pop            
        //   719: new             Lorg/codehaus/plexus/util/cli/CommandLineException;
        //   722: dup            
        //   723: aload           msg
        //   725: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   728: invokespecial   org/codehaus/plexus/util/cli/CommandLineException.<init>:(Ljava/lang/String;)V
        //   731: athrow         
        //   732: aload_0         /* this */
        //   733: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   736: invokeinterface org/apache/maven/scm/log/ScmLogger.isDebugEnabled:()Z
        //   741: ifeq            755
        //   744: aload_0         /* this */
        //   745: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   748: ldc             "Perforce sync complete."
        //   750: invokeinterface org/apache/maven/scm/log/ScmLogger.debug:(Ljava/lang/String;)V
        //   755: goto            857
        //   758: astore          e
        //   760: aload_0         /* this */
        //   761: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   764: invokeinterface org/apache/maven/scm/log/ScmLogger.isErrorEnabled:()Z
        //   769: ifeq            806
        //   772: aload_0         /* this */
        //   773: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   776: new             Ljava/lang/StringBuilder;
        //   779: dup            
        //   780: invokespecial   java/lang/StringBuilder.<init>:()V
        //   783: ldc             "CommandLineException "
        //   785: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   788: aload           e
        //   790: invokevirtual   org/codehaus/plexus/util/cli/CommandLineException.getMessage:()Ljava/lang/String;
        //   793: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   796: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   799: aload           e
        //   801: invokeinterface org/apache/maven/scm/log/ScmLogger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   806: goto            857
        //   809: astore          e
        //   811: aload_0         /* this */
        //   812: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   815: invokeinterface org/apache/maven/scm/log/ScmLogger.isErrorEnabled:()Z
        //   820: ifeq            857
        //   823: aload_0         /* this */
        //   824: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //   827: new             Ljava/lang/StringBuilder;
        //   830: dup            
        //   831: invokespecial   java/lang/StringBuilder.<init>:()V
        //   834: ldc             "IOException "
        //   836: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   839: aload           e
        //   841: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //   844: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   847: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   850: aload           e
        //   852: invokeinterface org/apache/maven/scm/log/ScmLogger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   857: aload           consumer
        //   859: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.isSuccess:()Z
        //   862: ifeq            890
        //   865: new             Lorg/apache/maven/scm/command/checkout/CheckOutScmResult;
        //   868: dup            
        //   869: aload           cl
        //   871: invokevirtual   org/codehaus/plexus/util/cli/Commandline.toString:()Ljava/lang/String;
        //   874: aload           consumer
        //   876: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.getCheckedout:()Ljava/util/List;
        //   879: invokespecial   org/apache/maven/scm/command/checkout/CheckOutScmResult.<init>:(Ljava/lang/String;Ljava/util/List;)V
        //   882: astore          11
        //   884: jsr             930
        //   887: aload           11
        //   889: areturn        
        //   890: new             Lorg/apache/maven/scm/command/checkout/CheckOutScmResult;
        //   893: dup            
        //   894: aload           cl
        //   896: invokevirtual   org/codehaus/plexus/util/cli/Commandline.toString:()Ljava/lang/String;
        //   899: ldc             "Unable to sync.  Are you logged in?"
        //   901: aload           consumer
        //   903: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.getOutput:()Ljava/lang/String;
        //   906: aload           consumer
        //   908: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.isSuccess:()Z
        //   911: invokespecial   org/apache/maven/scm/command/checkout/CheckOutScmResult.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
        //   914: astore          11
        //   916: jsr             930
        //   919: aload           11
        //   921: areturn        
        //   922: astore          18
        //   924: jsr             930
        //   927: aload           18
        //   929: athrow         
        //   930: astore          19
        //   932: iload           clientspecExists
        //   934: ifeq            1373
        //   937: aload           prepo
        //   939: invokevirtual   org/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository.isPersistCheckout:()Z
        //   942: ifne            1373
        //   945: aconst_null    
        //   946: astore          isReader
        //   948: aconst_null    
        //   949: astore          isReaderErr
        //   951: aload           prepo
        //   953: aload           workingDirectory
        //   955: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.createP4Command:(Lorg/apache/maven/scm/provider/perforce/repository/PerforceScmProviderRepository;Ljava/io/File;)Lorg/codehaus/plexus/util/cli/Commandline;
        //   958: astore          cl
        //   960: aload           cl
        //   962: invokevirtual   org/codehaus/plexus/util/cli/Commandline.createArg:()Lorg/codehaus/plexus/util/cli/Arg;
        //   965: ldc             "client"
        //   967: invokeinterface org/codehaus/plexus/util/cli/Arg.setValue:(Ljava/lang/String;)V
        //   972: aload           cl
        //   974: invokevirtual   org/codehaus/plexus/util/cli/Commandline.createArg:()Lorg/codehaus/plexus/util/cli/Arg;
        //   977: ldc             "-d"
        //   979: invokeinterface org/codehaus/plexus/util/cli/Arg.setValue:(Ljava/lang/String;)V
        //   984: aload           cl
        //   986: invokevirtual   org/codehaus/plexus/util/cli/Commandline.createArg:()Lorg/codehaus/plexus/util/cli/Arg;
        //   989: aload           specname
        //   991: invokeinterface org/codehaus/plexus/util/cli/Arg.setValue:(Ljava/lang/String;)V
        //   996: aload_0         /* this */
        //   997: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1000: invokeinterface org/apache/maven/scm/log/ScmLogger.isInfoEnabled:()Z
        //  1005: ifeq            1043
        //  1008: aload_0         /* this */
        //  1009: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1012: new             Ljava/lang/StringBuilder;
        //  1015: dup            
        //  1016: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1019: ldc             "Executing: "
        //  1021: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1024: aload           cl
        //  1026: invokevirtual   org/codehaus/plexus/util/cli/Commandline.toString:()Ljava/lang/String;
        //  1029: invokestatic    org/apache/maven/scm/provider/perforce/PerforceScmProvider.clean:(Ljava/lang/String;)Ljava/lang/String;
        //  1032: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1035: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1038: invokeinterface org/apache/maven/scm/log/ScmLogger.info:(Ljava/lang/String;)V
        //  1043: aload           cl
        //  1045: invokevirtual   org/codehaus/plexus/util/cli/Commandline.execute:()Ljava/lang/Process;
        //  1048: astore          proc
        //  1050: new             Ljava/io/InputStreamReader;
        //  1053: dup            
        //  1054: aload           proc
        //  1056: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //  1059: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //  1062: astore          isReader
        //  1064: new             Ljava/io/BufferedReader;
        //  1067: dup            
        //  1068: aload           isReader
        //  1070: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //  1073: astore          br
        //  1075: aload           br
        //  1077: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1080: dup            
        //  1081: astore          line
        //  1083: ifnull          1137
        //  1086: aload_0         /* this */
        //  1087: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1090: invokeinterface org/apache/maven/scm/log/ScmLogger.isDebugEnabled:()Z
        //  1095: ifeq            1127
        //  1098: aload_0         /* this */
        //  1099: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1102: new             Ljava/lang/StringBuilder;
        //  1105: dup            
        //  1106: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1109: ldc             "Consuming: "
        //  1111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1114: aload           line
        //  1116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1119: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1122: invokeinterface org/apache/maven/scm/log/ScmLogger.debug:(Ljava/lang/String;)V
        //  1127: aload           consumer
        //  1129: aload           line
        //  1131: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.consumeLine:(Ljava/lang/String;)V
        //  1134: goto            1075
        //  1137: aload           br
        //  1139: invokevirtual   java/io/BufferedReader.close:()V
        //  1142: new             Ljava/io/InputStreamReader;
        //  1145: dup            
        //  1146: aload           proc
        //  1148: invokevirtual   java/lang/Process.getErrorStream:()Ljava/io/InputStream;
        //  1151: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //  1154: astore          isReaderErr
        //  1156: new             Ljava/io/BufferedReader;
        //  1159: dup            
        //  1160: aload           isReaderErr
        //  1162: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //  1165: astore          brErr
        //  1167: aload           brErr
        //  1169: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //  1172: dup            
        //  1173: astore          line
        //  1175: ifnull          1229
        //  1178: aload_0         /* this */
        //  1179: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1182: invokeinterface org/apache/maven/scm/log/ScmLogger.isDebugEnabled:()Z
        //  1187: ifeq            1219
        //  1190: aload_0         /* this */
        //  1191: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1194: new             Ljava/lang/StringBuilder;
        //  1197: dup            
        //  1198: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1201: ldc             "Consuming stderr: "
        //  1203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1206: aload           line
        //  1208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1214: invokeinterface org/apache/maven/scm/log/ScmLogger.debug:(Ljava/lang/String;)V
        //  1219: aload           consumer
        //  1221: aload           line
        //  1223: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutConsumer.consumeLine:(Ljava/lang/String;)V
        //  1226: goto            1167
        //  1229: aload           brErr
        //  1231: invokevirtual   java/io/BufferedReader.close:()V
        //  1234: jsr             1356
        //  1237: goto            1370
        //  1240: astore          e
        //  1242: aload_0         /* this */
        //  1243: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1246: invokeinterface org/apache/maven/scm/log/ScmLogger.isErrorEnabled:()Z
        //  1251: ifeq            1288
        //  1254: aload_0         /* this */
        //  1255: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1258: new             Ljava/lang/StringBuilder;
        //  1261: dup            
        //  1262: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1265: ldc             "CommandLineException "
        //  1267: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1270: aload           e
        //  1272: invokevirtual   org/codehaus/plexus/util/cli/CommandLineException.getMessage:()Ljava/lang/String;
        //  1275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1278: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1281: aload           e
        //  1283: invokeinterface org/apache/maven/scm/log/ScmLogger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1288: jsr             1356
        //  1291: goto            1370
        //  1294: astore          e
        //  1296: aload_0         /* this */
        //  1297: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1300: invokeinterface org/apache/maven/scm/log/ScmLogger.isErrorEnabled:()Z
        //  1305: ifeq            1342
        //  1308: aload_0         /* this */
        //  1309: invokevirtual   org/apache/maven/scm/provider/perforce/command/checkout/PerforceCheckOutCommand.getLogger:()Lorg/apache/maven/scm/log/ScmLogger;
        //  1312: new             Ljava/lang/StringBuilder;
        //  1315: dup            
        //  1316: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1319: ldc             "IOException "
        //  1321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1324: aload           e
        //  1326: invokevirtual   java/io/IOException.getMessage:()Ljava/lang/String;
        //  1329: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1332: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1335: aload           e
        //  1337: invokeinterface org/apache/maven/scm/log/ScmLogger.error:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1342: jsr             1356
        //  1345: goto            1370
        //  1348: astore          26
        //  1350: jsr             1356
        //  1353: aload           26
        //  1355: athrow         
        //  1356: astore          27
        //  1358: aload           isReader
        //  1360: invokestatic    org/codehaus/plexus/util/IOUtil.close:(Ljava/io/Reader;)V
        //  1363: aload           isReaderErr
        //  1365: invokestatic    org/codehaus/plexus/util/IOUtil.close:(Ljava/io/Reader;)V
        //  1368: ret             27
        //  1370: goto            1387
        //  1373: iload           clientspecExists
        //  1375: ifeq            1387
        //  1378: ldc_w           "maven.scm.perforce.clientspec.name"
        //  1381: aload           specname
        //  1383: invokestatic    java/lang/System.setProperty:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //  1386: pop            
        //  1387: ret             19
        //    Exceptions:
        //  throws org.apache.maven.scm.ScmException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                               
        //  -----  -----  -----  -----  ---------------------------------------------------
        //  111    385    388    436    Lorg/codehaus/plexus/util/cli/CommandLineException;
        //  448    755    758    809    Lorg/codehaus/plexus/util/cli/CommandLineException;
        //  448    755    809    857    Ljava/io/IOException;
        //  443    887    922    930    Any
        //  890    919    922    930    Any
        //  922    927    922    930    Any
        //  951    1234   1240   1294   Lorg/codehaus/plexus/util/cli/CommandLineException;
        //  951    1234   1294   1348   Ljava/io/IOException;
        //  951    1237   1348   1356   Any
        //  1240   1291   1348   1356   Any
        //  1294   1345   1348   1356   Any
        //  1348   1353   1348   1356   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #1820 (coming from #1797).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2183)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static Commandline createCommandLine(final PerforceScmProviderRepository repo, final File workingDirectory, final ScmVersion version, final String specname) {
        final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
        command.createArg().setValue("-c" + specname);
        command.createArg().setValue("sync");
        final String[] files = workingDirectory.list();
        if (files == null || files.length == 0) {
            command.createArg().setValue("-f");
        }
        if (version != null && StringUtils.isNotEmpty(version.getName())) {
            command.createArg().setValue("@" + version.getName());
        }
        return command;
    }
    
    private int getLastChangelist(final PerforceScmProviderRepository repo, final File workingDirectory, final String specname) {
        int lastChangelist = 0;
        try {
            final Commandline command = PerforceScmProvider.createP4Command(repo, workingDirectory);
            command.createArg().setValue("-c" + specname);
            command.createArg().setValue("changes");
            command.createArg().setValue("-m1");
            command.createArg().setValue("-ssubmitted");
            command.createArg().setValue("//" + specname + "/...");
            this.getLogger().debug("Executing: " + PerforceScmProvider.clean(command.toString()));
            final Process proc = command.execute();
            final BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String lastChangelistStr = "";
            String line;
            while ((line = br.readLine()) != null) {
                this.getLogger().debug("Consuming: " + line);
                final Pattern changeRegexp = Pattern.compile("Change (\\d+)");
                final Matcher matcher = changeRegexp.matcher(line);
                if (matcher.find()) {
                    lastChangelistStr = matcher.group(1);
                }
            }
            br.close();
            try {
                lastChangelist = Integer.parseInt(lastChangelistStr);
            }
            catch (NumberFormatException nfe) {
                this.getLogger().debug("Could not parse changelist from line " + line);
            }
        }
        catch (IOException e) {
            this.getLogger().error(e);
        }
        catch (CommandLineException e2) {
            this.getLogger().error(e2);
        }
        return lastChangelist;
    }
}
