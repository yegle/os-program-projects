class adder implements Runnable{
    private int i;
    Adder(int id){
        i=id;
    }

    public void run(){
        try{
            Global.sum[i] + = Global.c[i];
            //System.err.println("c[" + i + "]=" + Global.c[i]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
