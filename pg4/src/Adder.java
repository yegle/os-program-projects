import java.util.*;
import java.lang.*;
import java.util.concurrent.Semaphore;

/**
 * Main server class
 */
class Adder implements Runnable{
    /**
	 * internal variable used to represent the range values
	 */
    private int start, end, threadId;

	/**
	 * function of Adder
	 *
	 * @param threadId
	 * @param threadCount
	 */
    public Adder(int threadId, int threadCount){
        this.threadId = threadId;

        int per = Global.size/threadCount;
        this.start = threadId * per;
        this.end = this.start+per-1;

        if(Global.size - this.end < per){
            this.end = Global.size-1;
        }
    }

	/**
	 * function of run
	 *
	 */
    public void run(){
        try{

            Formatter fmt = new Formatter(new StringBuilder());
            //System.err.println(fmt.format("Adder: threadId=%s, range=%s-%s", this.threadId, this.start, this.end));

            for(int i=this.start; i<=this.end; i++){
                Global.sum[this.threadId] += Global.c[i];
            }
            Global.EnteringPhase3.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
