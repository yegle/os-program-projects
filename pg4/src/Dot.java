import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.Semaphore;

public class Dot{
    protected int threadCount;

    public Dot(int threadCount){
        this.threadCount = threadCount;
    }

    public static void main(String[] args){
        try{
            Dot dot = new Dot(10);
            dot.init();

            dot.start();
        } catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

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

        System.err.println("Waiting for others to phase2");
        Global.EnteringPhase2.waitForOthers();
        System.err.println("Entering phase2");

        for(int i=0;i<this.threadCount;i++){
            sum[i] = new Thread(new Adder(i, this.threadCount));
			Global.EnteringPhase3.acquire();
            sum[i].start();
        }

        System.err.println("Waiting for others to phase3");
        Global.EnteringPhase3.waitForOthers();
        System.err.println("Entering phase3");
        long result = 0;
        for(int i=0;i<this.threadCount;i++){
            result += Global.sum[i];
        }
        System.out.println(result);
    }
}
// vim: set ts=4 sw=4 tw=0 :
