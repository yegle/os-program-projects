import java.util.concurrent.Semaphore;

class BarrierImpl implements Barrier {
    protected int threadCount;
    protected Semaphore s;

    public BarrierImpl(int threadCount){
        this.threadCount = threadCount;
        this.s = new Semaphore(threadCount);
    }

    public void waitForOthers(){
        this.s.acquire(this.threadCount);
    }

    public void freeAll(){
        this.s.release(this.s.availablePermits());
    }
}
