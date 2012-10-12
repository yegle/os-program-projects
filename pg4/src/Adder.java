import java.util.*;
import java.lang.*;
import java.util.concurrent.Semaphore;

class Adder implements Runnable{
    private int start, end, threadId;
    public Adder(int threadId, int threadCount){
        this.threadId = threadId;

		int per = GLobal.size/threadCount;
		this.start = threadId * per;
		this.end = this.start+per-1;

		if(this.end > Global.size){
			this.end = Global.size-1;
		}
    }

    public void run(){
		try{
			Global.permitToEnterPhase2[this.threadId].acquire();
			Global.permitToEnterPhase3[this.threadId].acquire();

			Formatter fmt = new Formatter(new StringBuilder());
			System.err.println(fmt.format("threadId=%s, range=%s-%s", this.threadId, this.start, this.end));

			Global.sum[threadID]=0;			

			for(int i=this.start; i<=this.end; i++){
            	Global.sum[threadID] + = Global.c[i];
			}
            Global.permitToEnterPhase3[this.threadId].release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
