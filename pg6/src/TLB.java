import java.util.*;
import java.lang.*;

public class TLB{
    Hashtable table;
    LinkedList <Integer> list = new LinkedList<Integer>();

    public TLB(){
        this.table = new Hashtable();
        for(int i=0; i<16; i++){
            this.table.put(-i, -1);
            this.list.add(-i);
        }
    }

    public int get(int p_num){
        if(this.table.containsKey(p_num)){
            return (int) this.table.get(p_num);
        }
        else{
            return -1;
        }
    }

    public void put(int p_num, int f_num){
        // we need to do two things:
        // 1. delete the oldest data in TLB
        // 2. insert new data
        //
        // oldest is determined by the Queue this.list


        // get the first item in queue
        // may return null, so we needs Integer
        Integer i = this.list.poll();
        if(i != null){
            // remove this item from hashtable
            this.table.remove(i.intValue());
        }

        this.list.add(p_num);
        this.table.put(p_num, f_num);
    }

    public static void main(String[] args){
        TLB tlb = new TLB();
        for(int i=0; i<=16; i++){
            tlb.put(i,i);
        }
        System.out.println(tlb.get(0));
    }
}
