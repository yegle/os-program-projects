import java.io.*;

public class ClientRunnable implements Runnable {
    protected Client c;
    protected String text;

    public void run() {
        try{
            c = new Client("rmi://127.0.0.1:1988/RMI");
            c.connect();
            c.debug("Client "+ Thread.currentThread().getName() + " connected");
            c.process(this.text);
            c.printResult();
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
    }
    
    public ClientRunnable(String text){
        this.text = text;
    }

    public static void main(String[] args){
        try{
            InputStream in = new FileInputStream(new File("ServerImpl.java"));
            String text = "";
            byte[] buf = new byte[1024];
            while(in.read(buf) > 0){
                text += new String(buf);
            }
            for(int i=1;i<=10;i++){
                ClientRunnable c = new ClientRunnable(text);
                Thread t = new Thread(c);
                (new Thread(c, ""+i)).start();
            }
        } catch(Exception e){
            System.err.println( "Exception catched, message: " + e.toString());
        }
    }
}
