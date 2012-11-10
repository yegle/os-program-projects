public class PageTable{
    PageTableItem[] table;

    public PageTable(){
        table = new PageTableItem[256];
        for(int i=0;i<256;i++){
            this.table[i] = new PageTableItem(-1, false);
        }
    }
}

class PageTableItem{
    int frameNumber;
    boolean valid;

    public PageTableItem(int i, boolean b){
        this.frameNumber = i;
        this.valid = b;
    }
}
