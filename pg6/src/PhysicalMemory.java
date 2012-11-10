public class PhysicalMemory{
    Frame[] frames;
    int currentFreeFrame;

    public PhysicalMemory(){
        this.frames = new Frame[256];
        this.currentFreeFrame = 0;
    }

    public int addFrame(Frame f){
        this.frames[this.currentFreeFrame] = new Frame(f);
        this.currentFreeFrame++;
        return this.currentFreeFrame-1;
    }

    public int getValue(int f_num, int offset){
        return this.frames[f_num].data[offset];
    }
}

class Frame {
    int[] data;

    public Frame(int[] d){
        this.data = new int[32];
        for(int i=0;i<32;i++){
            this.data[i] = d[i];
        }
    }

    public Frame(Frame f){
        System.arraycopy(f.data, 0, this.data, 0, 32);
    }
}
