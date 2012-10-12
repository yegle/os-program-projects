class Multiplier implements Runnable{
    private int i;
    Multiplier(int id){
        i=id;
    }

    public void run(){
        try{
            Global.c[i] = Global.a[i] * Global.b[i];
            System.err.println("c[" + i + "]=" + Global.c[i]);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
