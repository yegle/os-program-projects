public abstract class ReplacementAlgorithm {
    protected int pageFaultCount;

    protected int pageFrameCount;

    public ReplacementAlgorithm(int pageFrameCount){
        if (pageFrameCount < 0){
            throw new IllegalArgumentException();
        }

        this.pageFrameCount = pageFrameCount;
        pageFaultCount = 0;
    }

    public int getPageFaultCount(){
        return this.pageFaultCount;
    }

    public abstract void insert(int pageNumber);
}
