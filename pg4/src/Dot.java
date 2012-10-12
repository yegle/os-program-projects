import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.Semaphore;

public class Dot{
    //private static int[] sum = new int[10];
    //
    //

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
            System.err.println( "Exception catched, message: " + e.toString());
            System.exit(0);
        }
        ////read data from files and assign values to a,b
        //for(int j=0;j<Global.size;j++){
        //    Global.a[j]=j;
        //    Global.b[j]=j;
        //}

        ////create thread to multiply
        //int tsize = 10;
        //Thread[] mul = new Thread[tsize];

        //for(int j=0;j<tsize;j++){
        //    //System.err.println(j);
        //    mul[j]=new Thread(new Multiplier(j));

        //}

        //for(int j=0;j<tsize;j++){
        //    System.err.println(j);
        //    mul[j].start();
        //}

        ////create thread to add
        ///*Thread[] add = new Thread[tsize];

        //  for(int j=0;j<tsize;j++){
        ////System.err.println(j);
        //add[j]=new Thread(new Multiplier(j));

        //  }

        //  for(int j=0;j<tsize;j++){
        //  System.err.println(j);
        //  add[j].start();
        //  }*/

    }




    /*static class adder implements Runnable{
      private int i;

      adder(int id){
      i=id;
      }

      public void run(){
      try{
      sum+=c[i];
      }catch(Exception e){
      e.printStackTrace();
      }
      }
      }*/
    void init() throws FileNotFoundException{
        for(int i=0;i<9000000;i++){
            Global.a[i] = (i+1)%100;
            Global.b[i] = (i+1)%100;
        }
    }

    protected void start(){
        //create thread to multiply
        Thread[] mul = new Thread[this.threadCount];

        //start each thread
        for(int i=0; i<this.threadCount; i++){
            Global.permitToEnterPhase2[i] = new Semaphore(1);
            mul[i] = new Thread(new Multiplier(i, this.threadCount));
            mul[i].start();
        }

        Global.permitToEnterPhase3 = new Semaphore(this.threadCount);
    }
}
// vim: set ts=4 sw=4 tw=0 :
