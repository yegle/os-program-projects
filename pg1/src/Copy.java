import org.apache.commons.cli.*;
import java.io.*;
import java.nio.file.*;
import java.nio.channels.*;
import static java.nio.file.StandardCopyOption.*;

/**
 * <h1>HW #1 for Operating System</h1>
 *
 * Demo of different copy API in Java
 *
 * @author Yuchen Ying, Yue Yin
 * @version $Revision$, $Date$
 */
public class Copy {

    /**
     * Hold the start time of copy operation
     */
    public long copy_start;

    /**
     * Hold the end time of copy operation
     */
    public long copy_end;

    /**
     * Main function
     *
     * @param args Commandline Options
     */
    public static void main(String[] args) {
        Option help = new Option("h", "Print this message");
        Option mode = OptionBuilder.withArgName("mode")
                        .hasArg()
                        .isRequired()
                        .withDescription("Which API should I use. 1 for java.io.*, 2 for NIO.2, 3 for JNI")
                        .create("m");
        Option from = OptionBuilder.withArgName("file")
                        .hasArg()
                        .isRequired()
                        .withDescription("File to be copied")
                        .create("f");
        Option to = OptionBuilder.withArgName("file")
                        .hasArg()
                        .isRequired()
                        .withDescription("Destination file")
                        .create("t");
        Options options = new Options();
        options.addOption(help);
        options.addOption(mode);
        options.addOption(from);
        options.addOption(to);

        CommandLineParser parser = new PosixParser();
        CommandLine cmd = null;

        Copy copy = new Copy();

        try {
            cmd = parser.parse(options, args);
        }
        catch(ParseException e){
            System.err.println( "ERR: command line option parsing failed. reason: " + e.getMessage() );
            copy.usage(options);
            return;
        }
        String optMode, optTo, optFrom;

        if(cmd.hasOption("h")){
            copy.usage(options);
            return;
        }
        optMode = cmd.getOptionValue("m");
        optTo = cmd.getOptionValue("t");
        optFrom = cmd.getOptionValue("f");

        switch(optMode){
            case "1":
                copy.ioCopy(optFrom, optTo);
                break;
            case "2":
                copy.nio2Copy(optFrom, optTo);
                break;
            case "3":
                copy.jniCopy(optFrom, optTo);
                break;
            default:
                copy.usage(options);
                System.exit(1);
        }
        System.err.println(String.format("Time used in copy operation is %s nano seconds", copy.copy_end - copy.copy_start));
    }

    /**
     * Print usage information
     *
     * @param options Options required by package `commandline`
     */
    protected void usage(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "Program #1 for Operating Systems, demostrating different ways to copy file using different level of APIs.", options );
    }

    /**
     * Copies src file to dst file using java.io.*
     * @param from The file to be copied
     * @param to The destination file
     * @see java.io
     */
    protected void ioCopy(String from, String to){
        this.copy_start = System.nanoTime();
        try {
            InputStream in = new FileInputStream(new File(from));
            OutputStream out = new FileOutputStream(new File(to));
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            long bytes_copied = 0;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
                bytes_copied += len;
            }
            in.close();
            out.close();
            System.err.println(String.format("Copied %s bytes, from %s to %s", bytes_copied, from, to));
        }
        catch(IOException e){
            System.err.println( "Copy using java.io failed, error message:" + e.getMessage() );
        }
        this.copy_end = System.nanoTime();
    }

    /**
     * Copies src file to dst file using java.nio.file.*
     * @param from The file to be copied
     * @param to The destination file
     * @see java.nio.file
     */
    protected void nio2Copy(String from, String to){
        try {
        } catch (Exception e) {
            System.err.println( "Copy using java.nio failed, error message:" + e.toString() );
            return;
        }

        try {
            Path from_path = Paths.get(from);
            Path to_path = Paths.get(to);
            long bytes_copied = Files.size(from_path);
            this.copy_start = System.nanoTime();
            Files.copy(from_path, to_path, REPLACE_EXISTING);
            this.copy_end = System.nanoTime();
            System.err.println(String.format("Copied %s bytes, from %s to %s", bytes_copied, from, to));
        } catch (Exception e) {
            System.err.println( "Copy using java.nio failed, error message:" + e.toString() );
        }
    }

    /**
     * Copies src file to dst file using JNI, a wrapper function around _jniCopy() function
     *
     * @param from The file to be copied
     * @param to The destination file
     * @see #_jniCopy
     */
    protected void jniCopy(String from, String to){
        this.copy_start = System.nanoTime();
        int ret = this._jniCopy(from, to);
        this.copy_end = System.nanoTime();
        if (ret == 0){
            long bytes_copied;
            try{
                bytes_copied = Files.size(Paths.get(from));
            } catch(Exception e){
                System.err.println( "Copy using JNI succeed but failed in getting copied_bytes, error message:" + e.toString() );
                return;
            }
            System.err.println(String.format("Copied %s bytes, from %s to %s", bytes_copied, from, to));
        }
        else{
            System.err.println("JNI copy failed! Return code: " + ret);
        }
    }

    /**
     * The actual JNI function
     *
     * @param from The file to be copied
     * @param to The destination file
     */
    protected native int _jniCopy(String from, String to);
    static {
        System.loadLibrary("copy");
    }
} 
