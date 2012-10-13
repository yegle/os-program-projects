import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.Semaphore;

public class Dot{

    /**
	 * variable of number of thread
	 */
    protected int threadCount;

    /**
	 * function of Dot
	 * 
	 * @param threadCount	number of thread
	 */
    public Dot(int threadCount){
        this.threadCount = threadCount;
    }

    /**
	 * function of help
	 * 
	 * @param name	String
	 */
    public static void help(String name){
        System.out.println("Usage: \njava "+ name + " NUMBER_OF_THREADS");
        System.exit(0);
    }

    /**
	 * function of main
	 */
    public static void main(String[] args){
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        StackTraceElement main = stack[stack.length-1];
        String programName = main.getClassName();

        if(args.length!=1){
            Dot.help(programName);
        }

        int threadCount;
        try{
            threadCount = Integer.parseInt(args[0]);
            Dot dot = new Dot(threadCount);
            dot.init();

            dot.start();
        } catch(NumberFormatException e){
            e.printStackTrace();
            Dot.help(programName);
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
	 * function of init
	 */
    void init(){
        for(int i=0;i<9000000;i++){
            Global.a[i] = (i+1)%100;
            Global.b[i] = (i+1)%100;
        }
        Global.sum = new long[this.threadCount];
        for(int i=0;i<this.threadCount;i++){
            Global.sum[i] = 0;
        }
    }

    /**
	 * function of start
	 */
    protected void start() throws InterruptedException{
        //create threads
        Thread[] mul = new Thread[this.threadCount];
        Thread[] sum = new Thread[this.threadCount];

        //create Barriers
        Global.EnteringPhase2 = new BarrierImpl(this.threadCount);
        Global.EnteringPhase3 = new BarrierImpl(this.threadCount);

        //start each thread
        for(int i=0; i<this.threadCount; i++){
            mul[i] = new Thread(new Multiplier(i, this.threadCount));
            // acquire lock
            Global.EnteringPhase2.acquire();
            mul[i].start();
        }

        //System.err.println("Waiting for others to phase2");
        Global.EnteringPhase2.waitForOthers();
        //System.err.println("Entering phase2");

        for(int i=0;i<this.threadCount;i++){
            sum[i] = new Thread(new Adder(i, this.threadCount));
			Global.EnteringPhase3.acquire();
            sum[i].start();
        }

        //System.err.println("Waiting for others to phase3");
        Global.EnteringPhase3.waitForOthers();
        //System.err.println("Entering phase3");
        long result = 0;
        for(int i=0;i<this.threadCount;i++){
            result += Global.sum[i];
        }
        System.out.println(result);
    }
}
// vim: set ts=4 sw=4 tw=0 :
