import java.util.concurrent.Semaphore;

class BarrierImpl implements Barrier {

    /**
	 * variable for number of thread
	 */
	protected int threadCount;

    /**
	 * the semaphore
	 */
    public Semaphore s;

    /**
	 * function of BarrierImpl
	 * 
	 * @param threadCount	number of thread
	 */
    public BarrierImpl(int threadCount) throws InterruptedException{
        this.threadCount = threadCount;
        this.s = new Semaphore(threadCount);
    }

    /**
	 * function of waitForOthers
	 * 
	 */
    public void waitForOthers(){
        try{
            this.s.acquire(this.threadCount);
        } catch(InterruptedException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
	 * function of feeAll
	 * 
	 * 
	 */
    public void freeAll(){
        this.s.release(this.s.availablePermits()-1);
    }

    /**
	 * function of acquire
	 * 
	 * 
	 */
    public void acquire() throws InterruptedException{
        this.s.acquire();
    }

    /**
	 * function of release
	 * 
	 * 
	 */
    public void release() throws InterruptedException{
        this.s.release();
    }
}
