import java.util.concurrent.Semaphore;

class BarrierImpl implements Barrier {
    protected int threadCount;
    public Semaphore s;

    public BarrierImpl(int threadCount) throws InterruptedException{
        this.threadCount = threadCount;
        this.s = new Semaphore(threadCount);
    }

    public void waitForOthers(){
        try{
            this.s.acquire(this.threadCount);
        } catch(InterruptedException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void freeAll(){
        this.s.release(this.s.availablePermits()-1);
    }

    public void acquire() throws InterruptedException{
        this.s.acquire();
    }
    public void release() throws InterruptedException{
        this.s.release();
    }
}
