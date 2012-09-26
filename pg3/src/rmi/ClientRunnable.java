public class ClientRunnable implements Runnable {
    protected Client c;
    protected String text;
    protected int index;

    public void run() {
        try{
            c = new Client("rmi://127.0.0.1:1988/RMI");
            c.connect();
            c.debug(""+this.index);
            c.process(this.text);
            c.printResult();
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
    }
    
    public ClientRunnable(String text, int index){
        this.text = text;
        this.index = index;
    }

    public static void main(String[] args){
        try{
            for(int i=1;i<=10;i++){
                ClientRunnable c = new ClientRunnable("test", i);
                Thread t = new Thread(c);
                (new Thread(c)).start();
            }
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
    }
}
