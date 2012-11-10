public class PageTable{
    PageTableItem[] table;

    public PageTable(){
        table = new PageTableItem[256];
        for(int i=0;i<256;i++){
            this.table[i] = new PageTableItem(-1, false);
        }
    }

    public int get(int p_num){
        int frameNumber = this.table[p_num].getFrameNumber();
        if(frameNumber == -1){
            // frame number not in page table
            return -1;
        }
        return frameNumber;
    }

    public void add(int p_num, int f_num){
        this.table[p_num] = new PageTableItem(f_num, true);
    }
}

class PageTableItem{
    int frameNumber;
    boolean valid;

    public PageTableItem(int i, boolean b){
        this.frameNumber = i;
        this.valid = b;
    }

    public int getFrameNumber(){
        return this.frameNumber;
    }
}
