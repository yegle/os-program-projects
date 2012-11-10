import java.util.*;

public class TLB{
    Hashtable table;

    public TLB(){
        this.table = new Hashtable();
        for(int i=0; i<16; i++){
            this.table.put(-i, -1);
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
}
